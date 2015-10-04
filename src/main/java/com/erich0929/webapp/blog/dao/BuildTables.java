package com.erich0929.webapp.blog.dao;

import java.sql.*;
import java.util.*;

public class BuildTables
{
	static 
	{
		try
		{
			Class.forName ("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	private Connection getConnection () throws SQLException
	{
		return DriverManager
				.getConnection("jdbc:mysql://localhost:3306/javablog",
								"admin", "2642805");
	}
	
	private void closeConnection (Connection conn)
	{
		if (conn == null)
		{
			return;
		}
		try 
		{
			conn.close ();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
 	
	private static final String DROP_ARTICLE = "DROP TABLE IF EXISTS Article;";
	private static final String DROP_TAG = "DROP TABLE IF EXISTS Tag;";
	private static final String DROP_COMMENT = "DROP TABLE IF EXISTS Comment;";
	private static final String DROP_CATEGORY = "DROP TABLE IF EXISTS Category;";
	private static final String CREATE_ARTICLE =  "CREATE TABLE Article ("
												+ "id VARCHAR(50), "
												+ "category VARCHAR(50) NOT NULL, "
												+ "title VARCHAR(50) NOT NULL, "
												+ "content TEXT NOT NULL, "
												+ "timestamp BIGINT NOT NULL, "
												+ "PRIMARY KEY (id), "
												+ "FOREIGN KEY (category) REFERENCES Category(name) "
												+ "ON DELETE CASCADE ON UPDATE CASCADE)";
	private static final String CREATE_TAG = "CREATE TABLE Tag (" 
												+ "name VARCHAR(30), "
												+ "articleId VARCHAR(50), "
												+ "PRIMARY KEY (name, articleId), "
												+ "FOREIGN KEY (articleId) REFERENCES Article (id)"
												+ "ON DELETE CASCADE ON UPDATE CASCADE)";
	private static final String CREATE_COMMENT = "CREATE TABLE Comment ("
												+ "id VARCHAR(50) NOT NULL, "
												+ "articleId VARCHAR(50) NOT NULL, "
												+ "user VARCHAR(30) NOT NULL, "
												+ "comment VARCHAR(100) NOT NULL, "
												+ "timestamp BIGINT NOT NULL, "
												+ "PRIMARY KEY (id), "
												+ "FOREIGN KEY (articleId) REFERENCES Article (id) "
												+ "ON DELETE CASCADE ON UPDATE CASCADE"
												+ " )";
	private static final String CREATE_CATEGORY = "CREATE TABLE Category ("
												+ "name VARCHAR(50) NOT NULL, "
												+ "description VARCHAR(200) NOT NULL, "
												+ "PRIMARY KEY (name));";
	private static final String FOR_TEST_CATEGORY1 = "INSERT INTO Category (name, description) VALUES ('some category', 'some description')";
	private static final String FOR_TEST_CATEGORY2 = "INSERT INTO Category (name, description) VALUES ('수혜', 'some description')";
	private static final String [] INIT_DATABASE = {  DROP_TAG, DROP_COMMENT, DROP_ARTICLE, DROP_CATEGORY, CREATE_CATEGORY, CREATE_ARTICLE, CREATE_TAG, CREATE_COMMENT, FOR_TEST_CATEGORY1, FOR_TEST_CATEGORY2};
	//private static ArrayList<Connection> connected_pool = new ArrayList <Connection> ();
	public void initDatabase () throws SQLException
	{
		Connection conn = null;
		try 
		{
			conn = getConnection ();
			conn.setAutoCommit(false);
			for (String query : INIT_DATABASE)
			{
				PreparedStatement sql = conn.prepareStatement (query);
				//connected_pool.add(sql);
				sql.execute();
			}
			conn.commit();
			System.out.println ("DATABASE INITIALIZED");
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			conn.setAutoCommit(true);
			closeConnection (conn);
		}
		
	}
	
}
