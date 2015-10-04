package com.erich0929.webapp.blog.test;

import org.junit.*;

import java.sql.SQLException;
import java.util.*;

import com.erich0929.webapp.blog.dao.*;
import com.erich0929.webapp.blog.domain.*;
import com.erich0929.webapp.blog.helper.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;

public class CommentTest 
{
	@Test
	public void insertAndFindComments ()
	{
		Article article = getNewArticle ();
		article.setComments(getNewComments (article.getId ()));
		ArticleDao articleDao = new PlainArticleDao ();
		CommentDao commentDao = new PlainCommentDao ();
		TagDao tagDao = new PlainTagDao ();
		try 
		{
			articleDao.InsertArticle(article);
			articleDao.updateArticle(article);
			commentDao.insertComment(article.getComments());
			List<Comment> comments = commentDao.findCommentsByArticleId(article.getId ());
			String commentId = null;
			for (Comment comment : comments)
			{
				System.out.println (comment);
				commentId = comment.getId();
			}
			AjaxComment ajaxComment = new AjaxComment (commentId, comments, 1);
			ObjectMapper mapper = new ObjectMapper ();
			System.out.println (mapper.writeValueAsString(ajaxComment));
			tagDao.insertTag(article.getId (), article.getTags());
			tagDao.updateTag(article.getId(), article.getTags ());
			List<String> tags = tagDao.findTagByArticleId(article.getId());
			System.out.println(tags);
		}catch (SQLException e)
		{
			e.printStackTrace();
		}catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void pagenationWithCommentCountList ()
	{
		PagenationDao pagenationDao = new PlainPagenationDao (); 
		Pagenation pagenation = pagenationDao.loadPage("수혜", 0);
		if (pagenation.getArticles() != null)
		{
			for (Integer count : pagenation.getCommentCountList())
			{
				System.out.println (count);
			}
		}
		
	}
	
	private Article getNewArticle ()
	{
		Article article = new Article ();
		article.setId(System.currentTimeMillis() + RandString.getRandString(30));
		article.setCategory("수혜");
		article.setTitle("수혜야 사랑해");
		article.setContent ("수혜야 정말 사랑해");
		article.setTimestamp(System.currentTimeMillis());
		List<String> tags = new ArrayList<String> ();
		tags.add("수혜");
		tags.add("love");
		article.setTags(tags);
		return article;
	}
	private List<Comment> getNewComments (String articleId)
	{
		List<Comment> comments = new ArrayList<Comment> () ;
		for (int i =0; i < 10 ; i++)
		{
			Comment comment = new Comment ();
			comment.setArticleId(articleId);
			comment.setId(System.currentTimeMillis() + RandString.getRandString(30));
			comment.setTimestamp(System.currentTimeMillis());
			comment.setUser("erich0929");
			comment.setComment("수혜야 사랑해");
			comments.add(comment);
		}
		
		return comments;
	}
}
