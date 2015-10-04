package com.erich0929.webapp.blog.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

import com.erich0929.webapp.blog.domain.*;
import com.erich0929.webapp.blog.dao.*;
import java.util.*;
import java.sql.*;

public class ArticleView extends HttpServlet
{
	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException
	{
		Article article = null;
		String [] pathInfo = req.getPathInfo().split("/");
		String articleId = null;
		try
		{
			articleId = pathInfo [1];
			article = getArticleByArticleId (articleId);
			if (article == null)
			{
				res.sendError(res.SC_BAD_REQUEST);
			}
			/* TODO : 세션에 articleId를 저장하는 로직을 필터로 옮길 것 */
			req.getSession().setAttribute("articleId", article.getId ());
			
			req.setAttribute("article", article);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article.jsp");
			dispatcher.forward(req, res);
		} catch (ArrayIndexOutOfBoundsException e)
		{
			res.sendError(res.SC_BAD_REQUEST);
		} catch (SQLException e)
		{
			res.sendError(500);
		}
		
	}
	
	public Article getArticleByArticleId (String articleId) throws SQLException
	{
		ArticleDao articleDao = new PlainArticleDao ();
		CommentDao commentDao = new PlainCommentDao ();
		TagDao tagDao = new PlainTagDao ();
		
		Article article = articleDao.findArticleByArticleId(articleId);
		List<Comment> comments = commentDao.findCommentsByArticleId(articleId);
		article.setComments(comments);
		article.setCommentsCount(comments.size());
		List<String> tags = tagDao.findTagByArticleId(articleId);
		article.setTags (tags);
		return article;
	}
	
}
