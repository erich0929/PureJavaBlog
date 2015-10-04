package com.erich0929.webapp.blog.test;

import com.erich0929.webapp.blog.dao.*;
import com.erich0929.webapp.blog.domain.*;

import java.util.*;

import org.junit.*;

public class PagenationDaoTest {

	
	public void loadPageTest ()
	{
		PagenationDao pagenationDao = new PlainPagenationDao ();
		for (int i = 0 ; i < 5 ; i++)
		{
			Pagenation pagenation = pagenationDao.loadPage("some category", i);
			System.out.println (pagenation);
		}
	}
	
	public void loadPageTest1 ()
	{
		PagenationDao pagenationDao = new PlainPagenationDao (); 
		Pagenation pagenation = pagenationDao.loadPage("v", 3);
		if (pagenation.getArticles() != null)
		{
			for (Article article : pagenation.getArticles())
			{
				System.out.println (article);
			}
		}
	}
	@Test
	public void chapterTest ()
	{
		Pagenation pagenation = new Pagenation ();
		for (int i = 0; i < 100 ; i++)
		{
			pagenation.setCurrentPage(i);
			System.out.println ("fist of chapter : " + pagenation.getFirstOfChapter());
		}
		
	}
}
