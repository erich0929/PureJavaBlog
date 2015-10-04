package com.erich0929.webapp.blog.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.erich0929.webapp.blog.helper.*;


public class FacebookLoginServlet extends HttpServlet
{
	
	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String articleId = req.getParameter("id");
		req.getSession ().setAttribute("articleId", articleId);
		res.sendRedirect(UrlDomain.FACEBOOK_USER_LOGIN);
		return;
	}
}
