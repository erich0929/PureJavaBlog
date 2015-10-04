package com.erich0929.webapp.blog.dao;

import java.util.*;

import com.erich0929.webapp.blog.domain.*;
import com.erich0929.webapp.blog.helper.*;

import java.sql.*;

public class PlainCommentDao implements CommentDao {
	
	static
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public Connection getConnection () throws SQLException
	{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/javablog", "admin", "2642805");
	}
	
	public void closeConnection (Connection conn)
	{
		if (conn == null) return;
		try
		{
			conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private static final String FIND_COMMENTS_WITH_ARTICLEID = 
			"SELECT * FROM Comment WHERE articleId = ? ORDER BY id ASC";
	@Override
	public List<Comment> findCommentsByArticleId(String articleId) {
		Connection conn = null;
		PreparedStatement sql = null;
		List<Comment> comments = new ArrayList<Comment> ();
		Comment comment = null;
		try
		{
			conn = getConnection ();
			sql = conn.prepareStatement(FIND_COMMENTS_WITH_ARTICLEID);
			sql.setString(1, articleId);
			ResultSet resultSet = sql.executeQuery();
			while (resultSet.next ())
			{
				comment = new Comment ();
				comment.setId(resultSet.getString ("id"));
				comment.setArticleId(resultSet.getString ("articleId"));
				comment.setUser(resultSet.getString("user"));
				comment.setComment(resultSet.getString ("comment"));
				comment.setTimestamp (resultSet.getLong("timestamp"));
				comments.add(comment);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection (conn);
		}
		return comments;
	}
	
	private final String INSERT_COMMENT = "INSERT INTO Comment (id, articleId, user, comment, timestamp) " 
										 + "VALUES (?, ?, ?, ?, ?)";
	@Override
	public void insertComment (Comment comment)
	{
		Connection conn = null;
		PreparedStatement sql = null;
		try
		{
			conn = getConnection ();
			sql = conn.prepareStatement(INSERT_COMMENT);
			sql.setString (1, comment.getId());
			sql.setString(2, comment.getArticleId());
			sql.setString(3, comment.getUser());
			sql.setString (4, comment.getComment());
			sql.setLong (5, comment.getTimestamp());
			sql.execute();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection (conn);
		}
	}
	
	@Override
	public void insertComment (List<Comment> comments)
	{
		for (Comment comment : comments)
		{
			insertComment (comment);
		}
	}
	
	@Override
	public void insertCommentWithFacebook (Comment comment, Facebook facebook) throws SQLException, FacebookExpiredException
	{
		Connection conn = null;
		PreparedStatement sql = null;
		try
		{
			conn = getConnection ();
			conn.setAutoCommit(false);
			sql = conn.prepareStatement(INSERT_COMMENT);
			sql.setString (1, comment.getId());
			sql.setString(2, comment.getArticleId());
			sql.setString(3, comment.getUser());
			sql.setString (4, comment.getComment());
			sql.setLong (5, comment.getTimestamp());
			sql.execute();
			FacebookFeed feedObject = facebook.postFeed(comment.getComment());
			if (feedObject == null)
			{
				conn.rollback();
				throw new FacebookExpiredException ();
				//throw new RuntimeException ("Failed to Post facebook feed");
			}
			conn.commit();
		} catch (SQLException e)
		{
			
		} finally
		{
			conn.setAutoCommit(true);
			closeConnection(conn);
		}
		
		
		
		
	}
	
	private static final String COMMENT_COUNT_BY_ARTICLEID = "SELECT COUNT(*) count FROM Comment WHERE articleId = ?";
	
	@Override
	public int getCommentCountByArticleId (String articleId)
	{
		Connection conn = null;
		PreparedStatement sql = null;
		int count = 0;
		try
		{
			conn = getConnection ();
			sql = conn.prepareStatement(COMMENT_COUNT_BY_ARTICLEID);
			sql.setString (1, articleId);
			ResultSet resultSet = sql.executeQuery();
			resultSet.next();
			count = resultSet.getInt("count");
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection (conn);
		}
		return count;
	}
}
