package com.erich0929.webapp.blog.dao;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.erich0929.webapp.blog.domain.*;

public class PlainArticleDao implements ArticleDao
{
	static 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
 			/* do nothing */
			e.printStackTrace();
		}
	}
	
	private Connection getConnection () throws SQLException
	{
		return DriverManager
				.getConnection ("jdbc:mysql://localhost:3306/javablog",
								"admin", "2642805");
	}
	
	private void closeConnection (Connection connection)
	{
		if (connection == null) return;
		try
		{
			connection.close ();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private static final String FIND_ONE_ARTICLE = "SELECT * FROM Article WHERE id = ?";
	
	@Override
	public Article findArticleByArticleId (String articleId)
	{
		Connection conn = null;
		PreparedStatement sql = null;
		Article article = new Article ();
		try 
		{
			conn = getConnection ();
			sql = conn.prepareStatement(FIND_ONE_ARTICLE);
			sql.setString(1, articleId);
			ResultSet resultSet = sql.executeQuery();
			resultSet.next();
			article.setCategory(resultSet.getString("category"));
			article.setId(resultSet.getString("id"));
			article.setTitle(resultSet.getString("title"));
			article.setContent(resultSet.getString("content"));
			article.setTimestamp(resultSet.getLong("timestamp"));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return article;
	}
	
	private static String COUNT_SQL = "SELECT COUNT(*) AS 'count' FROM Article";
	
	@Override
	public int getCount ()
	{
		Connection conn = null;
		PreparedStatement sql;
		int count = 0;
		try 
		{
			conn = getConnection ();
			sql = conn.prepareStatement(COUNT_SQL);
			ResultSet resultSet = sql.executeQuery();
			resultSet.next();
			count =  Integer.parseInt(resultSet.getString ("count"));
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return count;
	}
	
	private static String COUNT_BY_CATEGORY_SQL = "SELECT COUNT(*) AS 'count' FROM Article WHERE category = ?";
	@Override
	public int getCount (String category)
	{
		if (category == null)
		{
			// get all article count
			return getCount ();
		}
		Connection conn = null;
		PreparedStatement sql;
		int count = 0;
		try 
		{
			conn = getConnection ();
			sql = conn.prepareStatement(COUNT_BY_CATEGORY_SQL);
			sql.setString(1, category);
			ResultSet resultSet = sql.executeQuery();
			resultSet.next();
			count =  Integer.parseInt(resultSet.getString ("count"));
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection(conn);
		}
		return count;
	}
	
	@Override
	public List<Article> findArticles (int limits)
	{
		List<Article> result = new ArrayList<Article> ();
		Connection conn = null;
		try 
		{
			conn = getConnection ();
			PreparedStatement statement
				= conn.prepareStatement("SELECT * FROM Article LIMIT ?");
			statement.setInt(1, limits);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next ())
			{
				Article article = new Article ();
				article.setId(resultSet.getString("id"));
				article.setCategory(resultSet.getString("category"));
				article.setContent(resultSet.getString("content"));
				article.setTitle(resultSet.getString("title"));
				article.setTimestamp(resultSet.getLong("timestamp"));
				result.add(article);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally 
		{
			closeConnection (conn);
		}
		return result;
	}
	
	private static final String FIND_ALL_CATEGORY_WITH_OFFSET = "SELECT * FROM Article ORDER BY id DESC LIMIT ? OFFSET ?";;
	
	public List<Article> findArticles (int limit, int offset)
	{
		List<Article> articleList = new ArrayList<Article> ();
		Connection conn = null;
		try 
		{
			conn = getConnection ();
			PreparedStatement statement
				= conn.prepareStatement(FIND_ALL_CATEGORY_WITH_OFFSET);
			statement.setInt(1, limit);
			statement.setInt(2, offset);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next ())
			{
				Article article = new Article ();
				article.setId(resultSet.getString("id"));
				article.setCategory(resultSet.getString("category"));
				article.setContent(resultSet.getString("content"));
				article.setTitle(resultSet.getString("title"));
				article.setTimestamp(resultSet.getLong("timestamp"));
				articleList.add(article);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally 
		{
			closeConnection (conn);
		}
		return articleList;
	}
	
	private static final String FIND_BY_FRONT_ARTICLE = "SELECT * FROM Article WHERE id < ? ORDER BY id DESC LIMIT ?";
	
	@Override
	public List<Article> findArticles (String articleId, int limit)
	{
		List<Article> articleList = new ArrayList<Article> ();
		Connection conn = null;
		try 
		{
			conn = getConnection ();
			PreparedStatement statement
				= conn.prepareStatement(FIND_BY_FRONT_ARTICLE);
			statement.setString(1, articleId);
			statement.setInt(2, limit);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next ())
			{
				Article article = new Article ();
				article.setId(resultSet.getString("id"));
				article.setCategory(resultSet.getString("category"));
				article.setContent(resultSet.getString("content"));
				article.setTitle(resultSet.getString("title"));
				article.setTimestamp(resultSet.getLong("timestamp"));
				articleList.add(article);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally 
		{
			closeConnection (conn);
		}
		return articleList;
	}
	
	private static String FIND_BY_FRONT_ARTICLE_WITH_CATEGORY
		= "SELECT * FROM Article WHERE category = ? AND id < ? ORDER BY id DESC LIMIT ?";
	@Override
	public List<Article> findArticles (String category, String articleId, int limit)
	{
		List<Article> articleList = new ArrayList<Article> ();
		Connection conn = null;
		try 
		{
			conn = getConnection ();
			PreparedStatement statement
				= conn.prepareStatement(FIND_BY_FRONT_ARTICLE_WITH_CATEGORY);
			statement.setString (1, category);
			statement.setString(2, articleId);
			statement.setInt(3, limit);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next ())
			{
				Article article = new Article ();
				article.setId(resultSet.getString("id"));
				article.setCategory(resultSet.getString("category"));
				article.setContent(resultSet.getString("content"));
				article.setTitle(resultSet.getString("title"));
				article.setTimestamp(resultSet.getLong("timestamp"));
				articleList.add(article);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally 
		{
			closeConnection (conn);
		}
		return articleList;
		
	}
	
	private static String OFFSET_SQL = "SELECT * FROM Article WHERE category = ? ORDER BY id DESC LIMIT ? OFFSET ?";
	@Override
	public List<Article> findArticlesWithOffset (String category, int limit, int offset)
	{
		List<Article> articleList = new ArrayList<Article> ();
		Connection conn = null;
		
		if (category == null)
		{
			return findArticles (limit, offset);
		}
		try 
		{
			conn = getConnection ();
			PreparedStatement statement
				= conn.prepareStatement(OFFSET_SQL);
			statement.setString (1, category);
			statement.setInt(2, limit);
			statement.setInt(3, offset);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next ())
			{
				Article article = new Article ();
				article.setId(resultSet.getString("id"));
				article.setCategory(resultSet.getString("category"));
				article.setContent(resultSet.getString("content"));
				article.setTitle(resultSet.getString("title"));
				article.setTimestamp(resultSet.getLong("timestamp"));
				articleList.add(article);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally 
		{
			closeConnection (conn);
		}
		return articleList;
	}
	
	private static final String INSERT_ARTICLE_SQL = 
			"INSERT INTO `Article` " + 
			"(`id`, `category`, `title`, `content`, `timestamp`) " + 
			"VALUES (?, ?, ?, ?, ?)";
	
	
	@Override
	public boolean InsertArticle (Article article) throws SQLException
	{
		
		boolean ret = false;
		Connection conn = null;
		
		try 
		{
			conn = getConnection ();
			conn.setAutoCommit(false);
			PreparedStatement sql = 
					conn.prepareStatement(
						INSERT_ARTICLE_SQL
					);
			sql.setString (1, article.getId());
			sql.setString (2, article.getCategory ());
			sql.setString (3, article.getTitle());
			sql.setString (4, article.getContent());
			sql.setLong (5, article.getTimestamp ());
			sql.execute();
			/*
			List<String> tags = null;
			if ((tags = article.getTags()) != null)
			{
				String articleId = article.getId ();
				TagDao tagDao = new PlainTagDao ();
				tagDao.insertTag(articleId, tags);
			}
			*/
			conn.commit();
			return true;
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			conn.setAutoCommit (true);
			closeConnection (conn);
		}
		return ret;
	}
	
	@Override
	public boolean InsertArticleList (List <Article> articleList)
	{
		boolean ret = false;
		
		return ret;
	}
	
	private static final String DELETE_ARTICLE = "DELETE FROM Article WHERE id = ?";
	@Override 
	public void deleteArticle (Article article) 
	{
		Connection conn = null;
		
		try
		{
			conn = getConnection ();
			doDelete (conn, article.getId());
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			closeConnection (conn);
		}
	}
	
	private void doDelete (Connection conn, String articleId) throws SQLException
	{
		PreparedStatement sql = null;
		sql = conn.prepareStatement(DELETE_ARTICLE);
		sql.setString(1, articleId);
		sql.execute();
	}
	
	@Override
	public void deleteArticles (List<Article> articles)
	{
		Connection conn = null;
		try
		{
			conn = getConnection ();
			conn.setAutoCommit(false);
			for (Article article : articles)
			{
				doDelete (conn, article.getId());
			}
		} catch (SQLException e)
		{
			doRollback (conn);
		} finally
		{
			setAutoCommitTrue (conn);
			closeConnection (conn);
		}
	}
	
	private void doRollback (Connection conn)
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
	
	private static final String UPDATE_ARTICLE = "UPDATE Article SET title = ?, content = ?, category = ? WHERE id = ?";
	
	@Override
	public void updateArticle (Article article) throws SQLException
	{
		Connection conn = null;
		PreparedStatement sql = null;
		//try
		//{
			conn = getConnection ();
			sql = conn.prepareStatement(UPDATE_ARTICLE);
			sql.setString(1, article.getTitle ());
			sql.setString(2, article.getContent ());
			sql.setString(3, article.getCategory());
			sql.setString(4, article.getId());
			sql.execute();
		//} catch (SQLException e)
		//{
		//	e.printStackTrace();
		//} finally
		//{
			closeConnection (conn);
		//}
	}
}
