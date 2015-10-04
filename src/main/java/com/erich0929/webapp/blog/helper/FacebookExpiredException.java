package com.erich0929.webapp.blog.helper;

public class FacebookExpiredException extends Exception 
{
	public FacebookExpiredException ()
	{
		
	}
	@Override
	public String getMessage ()
	{
		return "Facebook Access Token Invaild";
	}
}
