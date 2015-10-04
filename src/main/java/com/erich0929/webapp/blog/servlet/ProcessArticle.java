package com.erich0929.webapp.blog.servlet;

import javax.servlet.*;
import java.util.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;

import com.erich0929.webapp.blog.helper.*;
import com.erich0929.webapp.blog.dao.*;
import com.erich0929.webapp.blog.domain.*;

public class ProcessArticle extends HttpServlet
{
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		//
		request.setCharacterEncoding("UTF-8");
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content").trim().replaceAll("\n", "");
		String [] tagArray = request.getParameter("tags").split("\\s+");
		List<String> tags = new ArrayList<String> (); 
		for (String tag : tagArray)
		{
			tags.add(tag);
		}
		long timestamp = System.currentTimeMillis();
		String id = timestamp + RandString.getRandString(30);
		Article article = new Article (id, category, title, content, tags, timestamp);
		ArticleDao articleDao = new PlainArticleDao ();
		TagDao tagDao = new PlainTagDao ();
		try 
		{
			articleDao.InsertArticle(article);
			tagDao.insertTag(article.getId (), tags);
		} catch (SQLException e)
		{
			e.printStackTrace();
			response.sendError (response.SC_BAD_REQUEST);
		}
		
		response.sendRedirect("http://localhost:8080/blog/"); 
	}

}
