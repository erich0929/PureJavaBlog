package com.erich0929.webapp.blog.dao;

import java.sql.*;
import java.util.*;
import com.erich0929.webapp.blog.domain.*;

public class PlainCategoryDao implements CategoryDao {

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
	public PlainCategoryDao ()
	{
		
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
	
	private static final String FIND_ALL_CATEGORY = "SELECT * FROM Category;";
	@Override
	public List<Category> findCategories () throws SQLException
	{
		List<Category> categories = new ArrayList<Category> ();
		Connection conn = null;
		PreparedStatement sql = null;
		try
		{
			conn = getConnection ();
			sql = conn.prepareStatement(FIND_ALL_CATEGORY);
			ResultSet resultSet = sql.executeQuery();
			while (resultSet.next())
			{
				Category category = new Category ();
				category.setName(resultSet.getString("name"));
				category.setDescription(resultSet.getString("description"));
				categories.add(category);
			}
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			closeConnection (conn);
		}
		return categories;
	}
	private static final String CONTAIN_SQL = "SELECT * FROM Category WHERE name = ?";
	
	@Override
	public boolean contain(String category) {
		// TODO Auto-generated method stub
		if (category == null)
		{
			return false;
		}
		Connection conn = null;
		PreparedStatement sql = null;
		boolean res = false;
		try
		{
			conn = getConnection ();
			sql = conn.prepareStatement(CONTAIN_SQL);
			sql.setString(1, category);
			ResultSet resultSet = sql.executeQuery();
			while (resultSet.next())
			{
				res = true;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	private static final String INSERT_SQL = "INSERT INTO Category (name, description) VALUES (?, ?);";
	@Override
	public void insertCategory (String category, String description)
	{
		Connection conn = null;
		PreparedStatement sql = null;
		try
		{
			conn = getConnection ();
			sql = conn.prepareStatement(INSERT_SQL);
			sql.setString(1, category);
			sql.setString (2, description);
			sql.execute();
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private static final String DELETE_CATEGORY = "DELETE FROM Category WHERE name = ?";
	@Override
	public void deleteCategory (String category) throws SQLException
	{
		Connection conn = null;
		PreparedStatement sql = null;
		try
		{
			conn = getConnection ();
			sql = conn.prepareStatement(DELETE_CATEGORY);
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			closeConnection (conn);
		}
		sql.setString (1, category);
		sql.execute();
	}
	
	private static final String UPDATE_CATEGORY = "UPDATE Category SET name = ? , description = ? WHERE name = ?";;
	@Override
	public void updateCategory (String oldCategory, String newCategory, String newDesc ) throws SQLException
	{
		Connection conn = null;
		PreparedStatement sql = null;
		try
		{
			conn = getConnection ();
			sql = conn.prepareStatement(UPDATE_CATEGORY);
			sql.setString(1, newCategory);
			sql.setString(2, newDesc);
			sql.setString (3, oldCategory);
			sql.execute();
		} catch (SQLException e)
		{
			throw e;
		} finally
		{
			closeConnection (conn);
		}
	}
}
