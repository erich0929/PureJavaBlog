package com.erich0929.webapp.blog.dao;

import com.erich0929.webapp.blog.domain.*;
import com.erich0929.webapp.blog.helper.*;
import java.util.*;
import java.sql.*;

public interface CommentDao 
{
	public List<Comment> findCommentsByArticleId (String articleId);
	public void insertComment (Comment comment);
	public void insertCommentWithFacebook (Comment comment, Facebook facebook) throws SQLException, FacebookExpiredException;
	public void insertComment (List<Comment> comments);
	public int getCommentCountByArticleId (String articleId);
}
