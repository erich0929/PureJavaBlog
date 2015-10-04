package com.erich0929.webapp.blog.domain;

public class Category 
{
	private String name;
	private String description;
	
	public Category ()
	{
		
	}
	public Category (String name, String description)
	{
		this.name = name;
		this.description = description;
	}
	public void setName (String name)
	{
		this.name = name;
	}
	public void setDescription (String description)
	{
		this.description = description;
	}
	@Override
	public String toString ()
	{
		return "[Category INFO] name : " + name +
						"description : " + description;
	}
}
