package com.erich0929.webapp.blog.dao;

import java.util.*;
import java.sql.*;

import com.erich0929.webapp.blog.domain.*;

public interface ArticleDao {
	public int getCount ();
	public int getCount (String category);
	public Article findArticleByArticleId (String articleId);
	public List<Article> findArticles (int limits); 
	public List<Article> findArticles (String articleId, int limit);
	public List<Article> findArticles (String category, String articleId, int limit);
	public List<Article> findArticlesWithOffset (String category, int limit, int offset);
	public boolean InsertArticle (Article article) throws SQLException;
	public boolean InsertArticleList (List <Article> articleList);
	public void deleteArticle (Article article);
	public void deleteArticles (List<Article> articles);
	public void updateArticle (Article article) throws SQLException ;
}
