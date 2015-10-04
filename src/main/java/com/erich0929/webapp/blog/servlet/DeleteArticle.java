package com.erich0929.webapp.blog.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

import com.erich0929.webapp.blog.dao.*;
import com.erich0929.webapp.blog.domain.*;

public class DeleteArticle extends HttpServlet
{
	@Override
	public void doPost (HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException
	{
		String currentPage = req.getParameter("page");
		String category = req.getParameter("category");
		List<Article> articles = getArticlesFromParams (req);
		deleteArticles (articles);
		res.sendRedirect("/blog/admin/category/" + category + "?page=" + currentPage);
	}
	
	private List<Article> getArticlesFromParams (HttpServletRequest req)
	{
		List<Article> articles = new ArrayList<Article> ();
		String [] params = req.getParameterValues("article_list");
		for (String param : params)
		{
			Article article = new Article ();
			article.setId(param);
			articles.add(article);
		}
		return articles;
	}
	private void deleteArticles (List<Article> articles)
	{
		ArticleDao articleDao = new PlainArticleDao ();
		articleDao.deleteArticles (articles);		
	}
}
