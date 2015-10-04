package com.erich0929.webapp.blog.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.erich0929.webapp.blog.dao.*;
import com.erich0929.webapp.blog.domain.*;

public class UpdateArticle extends HttpServlet
{
	@Override
	public void doPost (HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException
	{
		req.setCharacterEncoding("UTF-8");
		Article article = getArticleFromRequest (req);
		try
		{
			updateArticle (article);
		} catch (SQLException e)
		{
			res.sendError(res.SC_BAD_REQUEST);
			return;
		}
		
		res.sendRedirect("/blog/admin/category/" + article.getCategory() + "?page=0");
	}
	
	private Article getArticleFromRequest (HttpServletRequest req)
	{
		
		String articleId = req.getParameter("id");
		String title = req.getParameter("title");
		String content = req.getParameter("content").trim().replaceAll("\n", "");
		String category = req.getParameter("category");
		String [] tagArray = req.getParameter("tags").split("\\s+");
		List<String> tags = new ArrayList<String> (); 
		for (String tag : tagArray)
		{
			tags.add(tag);
		}
		Article article = new Article ();
		article.setId(articleId);
		article.setContent(content);
		article.setTitle(title);
		article.setCategory(category);
		article.setTags(tags);
		
		return article;
	}
	
	private void updateArticle (Article article) throws SQLException
	{
		ArticleDao articleDao = new PlainArticleDao ();
		TagDao tagDao = new PlainTagDao ();
		articleDao.updateArticle (article);
		tagDao.updateTag(article.getId (), article.getTags ());
	}
}
