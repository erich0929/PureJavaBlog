package com.erich0929.webapp.blog.test;

import org.junit.Test;
import com.erich0929.webapp.blog.helper.*;

public class RandStringTest {
	
	public void getRandStringTest ()
	{
		RandString randString = new RandString ();
		for (int i = 0; i < 10; i++)
		{
			System.out.println (randString.getRandString(20));
		}
	}
}
