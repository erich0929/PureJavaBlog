package com.erich0929.webapp.blog.dao;

import java.sql.DriverManager;
import java.util.*;



import java.sql.*;
public class PlainTagDao implements TagDao {
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
	private Connection getConnection () throws SQLException 
	{
		return DriverManager
			.getConnection("jdbc:mysql://localhost:3306/javablog", "admin", "2642805");
	}
	private void closeConnection (Connection conn)
	{
		if (conn == null) return ;
		try
		{
			conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private static final String FIND_TAG = "SELECT * FROM Tag WHERE articleId = ?";
	@Override
	public List<String> findTagByArticleId(String articleId) throws SQLException
	{
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement sql = null;
		List<String> tags = new ArrayList<String> ();
		conn = getConnection ();
		sql = conn.prepareStatement(FIND_TAG);
		sql.setString(1, articleId);
		ResultSet resultSet = sql.executeQuery();
		while (resultSet.next())
		{
			String tag = resultSet.getString("name");
			tags.add(tag);
		}
		closeConnection (conn);
		return tags;
	}
	
	private static final String INSERT_TAG = "INSERT INTO Tag (name, articleId) VALUES (?, ?)";
	@Override
	public boolean insertTag (String articleId, List<String> tags)
	{
		Connection conn = null;
		boolean res = true;
		try
		{
			conn = getConnection ();
			conn.setAutoCommit(false);
			for (String tag : tags)
			{
				doInsert (conn, tag, articleId);
			}
			conn.commit();
		} catch (SQLException e)
		{
			rollback (conn);
			res = false;
			e.printStackTrace();
		} finally
		{
			setAutoCommitTrue (conn);
			closeConnection (conn);
		}
		return res;
	}
	private void rollback (Connection conn)
	{
		try
		{
			conn.rollback();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	private void setAutoCommitTrue (Connection conn)
	{
		try
		{
			conn.setAutoCommit(true);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private boolean doInsert (Connection conn, String tag, String articleId) throws SQLException
	{
		PreparedStatement sql = null;
		sql = conn.prepareStatement(INSERT_TAG);
		sql.setString(1, tag);
		sql.setString(2, articleId);
		return sql.execute();
	}
	
	private boolean doInsert (Connection conn, List<String> tags, String articleId)
		throws SQLException
	{
		for (String tag : tags)
		{
			doInsert (conn, tag, articleId);
		}
		return true;
	}
	
	private static final String DELETE_TAG = "DELETE FROM Tag WHERE articleId = ?";
	@Override
	public boolean deleteTag (String articleId)
	{
		Connection conn = null;
		boolean res = true;
		try
		{
			conn = getConnection ();
			doDelete (conn, articleId);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection (conn);
		}
		return res;
	}
	
	private boolean doDelete (Connection conn, String articleId)
		throws SQLException
	{
		PreparedStatement sql = null;
		sql = conn.prepareStatement(DELETE_TAG);
		sql.setString(1, articleId);
		return sql.execute();
	}
	public boolean updateTag (String articleId, List<String> tags)
	{
		Connection conn = null;
		boolean res = true;
		try
		{
			conn = getConnection ();
			conn.setAutoCommit(false);
			doDelete (conn, articleId);
			doInsert (conn, tags, articleId);
			res = false;
			
		} catch (SQLException e)
		{
			rollback(conn);
			e.printStackTrace();
		} finally
		{
			setAutoCommitTrue (conn);
			closeConnection (conn);
		}
		return res;
	}

}
