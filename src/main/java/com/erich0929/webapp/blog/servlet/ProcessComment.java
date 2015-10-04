package com.erich0929.webapp.blog.servlet;

import com.erich0929.webapp.blog.domain.*;
import com.erich0929.webapp.blog.helper.*;
import com.erich0929.webapp.blog.dao.*;
import com.erich0929.webapp.blog.helper.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.regex.*;
import java.sql.*;
import java.util.*;

public class ProcessComment extends HttpServlet
{
	private static final String START_REGEX = "^";
	private static final String ARTICLEID_END = "/(.+)$";
	
	@Override
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		
		req.setCharacterEncoding("utf-8");
		
		String referer = req.getHeader("referer");
		Pattern refererPattern = Pattern.compile(START_REGEX + UrlDomain.ARTICLE_VIEW + ARTICLEID_END);
		Matcher matcher = refererPattern.matcher(referer);
		
		
		if (!matcher.matches())
		{
			sendErrorJson (res, "Regex not match");
			return;
		}
		String articleId = (String)req.getSession().getAttribute("articleId");
		if (!matcher.group(1).equals(articleId))
		{
			sendErrorJson (res, "articleId is not equal. articleId = " + articleId
						+ " group = " + matcher.group(1));
			return;
		}
		User user = (User)req.getSession().getAttribute("user");
		Comment comment = getComment (req.getParameter("comment"), articleId, user.getName());
		
		CommentDao commentDao = new PlainCommentDao ();
		List<Comment> comments = null;
		try
		{
			commentDao.insertCommentWithFacebook(comment, (Facebook) req.getSession().getAttribute("facebook"));
			comments = commentDao.findCommentsByArticleId(articleId);
		} catch (SQLException e)
		{
			e.printStackTrace();
			sendErrorJson (res, e.toString());
			return;
		} catch (FacebookExpiredException e)
		{
			res.sendRedirect (UrlDomain.FACEBOOK_USER_LOGIN);
		}
		sendJsonResponse (res, comments, comment.getId ());
		
	}
	private void sendErrorJson (HttpServletResponse res, String error) 
		throws IOException
	{
		res.setCharacterEncoding("utf-8");
		res.setContentType("application/json");
		
		ObjectMapper mapper = new ObjectMapper ();
		AjaxComment ajaxResponse = new AjaxComment (error, null, 0);
		try
		{
			res.getWriter().print(mapper.writeValueAsString(ajaxResponse));
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}	
	}
	private Comment getComment (String commentString, String articleId, String user)
	{
		Comment comment = new Comment ();
		long timestamp = System.currentTimeMillis();
		comment.setId (timestamp + RandString.getRandString(20));
		comment.setArticleId(articleId);
		comment.setComment(commentString);
		comment.setUser(user);
		comment.setTimestamp(timestamp);
		
		return comment;
	}
	public void sendJsonResponse (HttpServletResponse res, List<Comment>comments, String commentId)
		throws IOException
	{
		res.setCharacterEncoding("utf-8");
		res.setContentType("application/json");
		
		ObjectMapper mapper = new ObjectMapper ();
		AjaxComment ajaxResponse = new AjaxComment (commentId, comments, 1);
		
		try
		{
			res.getWriter().print(mapper.writeValueAsString(ajaxResponse));
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}	
	}
}
