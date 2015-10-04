package com.erich0929.webapp.blog.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.erich0929.webapp.blog.dao.*;
import com.erich0929.webapp.blog.domain.*;
import com.erich0929.webapp.blog.helper.*;

import java.sql.SQLException;
import java.util.*;

public class PlainDaoTest {
	
	@Test
	public void InsertArticleTest ()
	{
		for (int i =0; i < 100; i++)
		{
			Article article = new Article ();
			article.setId(System.currentTimeMillis() + RandString.getRandString(30));
			article.setCategory("some category");
			article.setTitle(i + "th 게시물입니다." );
			article.setContent(i + "th 게시물입니다.");
			article.setTags(null);
			article.setTimestamp(System.currentTimeMillis());
			PlainArticleDao articleDao = new PlainArticleDao ();
			try 
			{
				articleDao.InsertArticle(article);
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}	
	}

	@Test
	public void findArticles ()
	{
		PlainArticleDao articleDao = new PlainArticleDao ();
		List <Article> articleList = articleDao.findArticles(0);
		//assertEquals (articleList.size(), 0);
		for (Article article : articleList)
		{
			System.out.println (article.getId());
		}
	}
	
	@Test
	public void getCountTest ()
	{
		PlainArticleDao articleDao = new PlainArticleDao ();
		int count = articleDao.getCount();
		System.out.println ("Article Table count : " + count );
	}
	
	
	public void findArticlesWithCategory ()
	{
		PlainArticleDao articleDao = new PlainArticleDao ();
		List<Article> articleList = articleDao.findArticles("some category", "14392618336662r6cUP7jwcdyziEIuDSjK1gV1LQ2jC", 5);
		for (Article article : articleList)
		{
			System.out.println (article.getCategory());
		}
	}
}
