package com.erich0929.webapp.blog.dao;

import com.erich0929.webapp.blog.domain.*;

import java.sql.*;
import java.util.*;


public class PlainPagenationDao implements PagenationDao 
{
	private static final int ARTICLES_PER_PAGE = 21;
	private static final String FIND_BY_FIRST_ARTICLE = "SELECT * FROM Article ORDER BY id DESC LIMIT " + ARTICLES_PER_PAGE;	
	private static final String FIND_TOTAL_COUNT = "SELECT COUNT (*) FROM Article";
	
	static 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	private Connection getConnection () throws SQLException
	{
		return DriverManager
				.getConnection("jdbc:mysql://localhost:3306/javablog", "admin", "2642805");
	}
	private void closeConnection (Connection conn)
	{
		if (conn == null)
		{
			return ;
		}
		try 
		{
			conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	/*
	@Override
	public Pagenation loadPage(String frontArticleId)
	{
		String find_query = null;
				
		Pagenation pagenation = new Pagenation ();
		pagenation.setFrontArticle(frontArticleId);
		List<Article> articleList = getArticleList (frontArticleId);
		pagenation.setArticles(articleList);
		pagenation.setTotalPage((int)Math.ceil((double)getTotalCount () / (double) ARTICLES_PER_PAGE ));
		
		return null;
	}
	*/
	
	private String validateCategory (String category)
	{
		CategoryDao categoryDao = new PlainCategoryDao ();
		if (categoryDao.contain(category))
		{
			return category;
		}
		return null;
		
	}
	
	@Override
	public Pagenation loadPage (String category, int currentPage)
	{
		String find_query = null;
		List<Article> articleList = null;
		List<Integer> commentCountList = null;
		Pagenation pagenation = new Pagenation ();
		category = validateCategory (category);
		pagenation.setCategory (category);
		pagenation.setCurrentPage(currentPage);
		int totalPage = (int)Math.ceil((double)getTotalCount (category) / (double) ARTICLES_PER_PAGE ) - 1 ;
		pagenation.setTotalPage(totalPage);
		
		int offset = currentPage * ARTICLES_PER_PAGE;
		//invalid page
		if (totalPage < currentPage)
		{
			pagenation.setArticles(null);
			return pagenation;
		}
		if (category == null)
		{
			pagenation.setCategory("All"); // category : all
			articleList = getArticleList (null, offset);
			
		} else
		{
			articleList = getArticleList (category, offset);
			
		}
		
		/*
		 * duplicated : 카테고리는 있지만 게시물이 없는 경우도 있다.
		if (articleList.size() == 0 )
		{
			 //get first page
			pagenation.setCategory("All"); // category : all
			articleList = getArticleList (null, offset);
			
		}
		*/
		
		pagenation.setArticles(articleList);	
		commentCountList = getCommentCountList (articleList);
		pagenation.setCommentCountList(commentCountList);
		return pagenation;
	}
	
	private List<Article> getArticleList (String category, int offset)
	{
		List<Article> articleList = null;
		ArticleDao articleDao = new PlainArticleDao ();
		articleList = articleDao.findArticlesWithOffset(category, ARTICLES_PER_PAGE, offset);
		return articleList;
	}
	
	private List<Article> getArticleList (String frontArticleId)
	{
		List<Article> articleList = null;
		ArticleDao articleDao = new PlainArticleDao ();
		if (frontArticleId != null)
		{
			articleList = articleDao.findArticles(frontArticleId, ARTICLES_PER_PAGE);
		} else 
		{
			articleList = articleDao.findArticles(ARTICLES_PER_PAGE);
		}
		return articleList;
	}
	
	private int getTotalCount (String category)
	{
		ArticleDao articleDao = new PlainArticleDao ();
		return articleDao.getCount(category);
	}
	
	private int getTotalCount ()
	{
		ArticleDao articleDao = new PlainArticleDao ();
		return articleDao.getCount();
	}
	
	private List<Integer> getCommentCountList (List<Article> articles)
	{
		List<Integer> result = new ArrayList <Integer> ();
		CommentDao commentDao = new PlainCommentDao ();
		for (Article article : articles)
		{
			result.add(commentDao.getCommentCountByArticleId(article.getId()));
		}
		return result;
	}
}
