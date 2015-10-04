package com.erich0929.webapp.blog.test;

import org.junit.*;
import static org.junit.Assert.assertTrue;
import com.erich0929.webapp.blog.dao.*;

public class CategoryTest 
{
	@Test
	public void containTest ()
	{
		CategoryDao categoryDao = new PlainCategoryDao ();
		
		// insert some category
		//categoryDao.insertCategory("some category", "some description");
		assertTrue (categoryDao.contain("some category"));
		assertTrue (!categoryDao.contain(null));
	}
}
