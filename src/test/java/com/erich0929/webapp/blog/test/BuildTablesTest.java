package com.erich0929.webapp.blog.test;

import com.erich0929.webapp.blog.dao.*;
import org.junit.*;
import java.sql.*;

public class BuildTablesTest {
	@Test
	public void initDB ()
	{
		BuildTables initializer = new BuildTables ();
		try 
		{
			initializer.initDatabase ();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
}
