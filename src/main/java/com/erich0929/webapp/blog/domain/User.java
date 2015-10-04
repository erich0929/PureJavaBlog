package com.erich0929.webapp.blog.domain;

public class User 
{
	private String id;
	private String name;
	private String email;
	
	public void setId (String id)
	{
		this.id = id;
	}
	public String getId ()
	{
		return id;
	}
	public void setName (String name)
	{
		this.name = name;
	}
	public String getName ()
	{
		return name;
	}
	public void setEmail (String email)
	{
		this.email = email;
	}
	public String getEmail ()
	{
		return email;
	}
	@Override
	public String toString ()
	{
		return  "[USER INFO] id : " + id +
				"name : " + name +
				"email : " + email + "\n"; 
	}
}
