package com.erich0929.webapp.blog.helper;

public class FacebookFeed 
{
	private String id;
	public FacebookFeed ()
	{
		
	}
	public FacebookFeed (String id)
	{
		this.id = id;
	}
	public void setId (String id)
	{
		this.id = id;
	}
	public String getId ()
	{
		return id;
	}
	@Override
	public String toString ()
	{
		return "[FacebookFeed INFO] id : " + id;
	}
}
