package com.erich0929.webapp.blog.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

import com.erich0929.webapp.blog.domain.*;
import com.erich0929.webapp.blog.dao.*;

public class AdminCategoryView extends HttpServlet
{
	// url-pattern : /admin/category/*
	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException 
	{
		req.setCharacterEncoding("utf-8");
		String [] pathInfo = req.getPathInfo().split("/");
		String category = null;
		int page = 0;
		try
		{
			page = Integer.parseInt(req.getParameter ("page"));
			category = pathInfo [2];
		} catch (NumberFormatException e)
		{
			page = 0;
		} catch (ArrayIndexOutOfBoundsException e)
		{
			category = null;
		} finally
		{
			processResponse (req, res, category, page);
		}	
		
	}
	
	private void processResponse (HttpServletRequest req, HttpServletResponse res, String category , int page)
		throws IOException, ServletException
	{
		Pagenation pagenation = getPagenationByCategory (category, page);
		req.setAttribute("pagenation", pagenation);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin_category.jsp");
		dispatcher.forward(req, res);		
	}
	
	private Pagenation getPagenationByCategory (String category, int page) 
	{
		PagenationDao pagenationDao = new PlainPagenationDao ();	
		return pagenationDao.loadPage(category , page);
	}
	
	private void processError (HttpServletResponse res) 
	{
		try 
		{
			res.sendError(res.SC_BAD_REQUEST);
		} catch (IOException e)
		{
			e.printStackTrace();
		}	
	}
}
