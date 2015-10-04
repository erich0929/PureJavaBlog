package com.erich0929.webapp.blog.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.erich0929.webapp.blog.dao.*;
import com.erich0929.webapp.blog.domain.*;

public class UpdateArticleView extends HttpServlet
{
	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{
		Article article = null;
		//try
		//{
			article = getArticleFromRequest (req);
		//} catch (ArrayIndexOutOfBoundsException e)
		//{
			//res.sendError(res.SC_BAD_REQUEST);
			//return;
		//}
		
		req.setAttribute("article", article);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/update_article.jsp");
		dispatcher.forward(req, res);
	}
	
	private Article getArticleFromRequest (HttpServletRequest req) 
		throws ArrayIndexOutOfBoundsException
	{
		// url pattern : /admin/article/update/*
		String[] pathInfo = req.getPathInfo ().split("/");
		String articleId = pathInfo [1];
		ArticleDao articleDao = new PlainArticleDao ();
		return articleDao.findArticleByArticleId(articleId);
	}
}
