package com.erich0929.webapp.blog.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.erich0929.webapp.blog.helper.*;
import com.erich0929.webapp.blog.domain.*;

public class FacebookRedirect extends HttpServlet
{
	private static final String REDIRECT_URL = "http://localhost:8080/blog/facebook_redirect";
	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		req.setCharacterEncoding("utf-8");
		String code = req.getParameter("code");
		Facebook facebook = new Facebook (code);
		String accessToken = facebook.getAccessToken(REDIRECT_URL);
		User user = facebook.getFacebookUser();
		
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("facebook", facebook);
		String articleId = (String)req.getSession ().getAttribute("articleId");
		res.sendRedirect ("http://localhost:8080/blog/article/" + articleId + "#comment_text_box");
		
		//res.setCharacterEncoding("utf-8");
		//res.getWriter().println("code : " + code);
		//res.getWriter().println("accessToken : " + accessToken);
		//res.getWriter().println ("user : " + user.toString() );
		//res.getWriter().println ("articleId : " + articleId);
	}
}
