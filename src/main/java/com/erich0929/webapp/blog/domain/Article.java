package com.erich0929.webapp.blog.domain;

import java.util.*;

public class Article 
{
	
	private String id;
	private String category;
	private String title;
	private String content;
	private List<String> tags;
	private List<Comment> comments;
	private long timestamp;
	private int commentsCount;
	
	public Article ()
	{
		
	}
	
	public Article (String id, String category, String title, String content, List<String> tags, long timestamp)
	{
		this.id = id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.tags = tags;
		this.timestamp = timestamp;
	}
	
	public void setId (String id )
	{
		this.id = id;
	}
	
	public String getId ()
	{
		return id;
	}
	
	public void setCategory (String category)
	{
		this.category = category;
	}
	
	public String getCategory ()
	{
		return category;
	}
	
	public void setTitle (String title)
	{
		this.title = title;
	}
	
	public String getTitle ()
	{
		return title;
	}
	
	public void setContent (String content)
	{
		this.content = content;
	}
	
	public String getContent ()
	{
		return content;
	}
	
	public void setTags (List<String> tags)
	{
		this.tags = tags;
	}
	
	public List<String> getTags ()
	{
		return tags;
	}
	
	public void setTimestamp (long timestamp)
	{
		this.timestamp = timestamp;
	}
	
	public long getTimestamp ()
	{
		return timestamp;
	}
	
	public void setComments (List<Comment> comments)
	{
		this.comments = comments;
	}
	public List<Comment> getComments ()
	{
		return comments;
	}
	public void setCommentsCount (int count)
	{
		this.commentsCount = count;
	}
	public int getCommentsCount ()
	{
		return commentsCount;
	}
	@Override 
	public String toString ()
	{
		return	"[Article INFO : " + 
			"id : " + id + ", " +
			"category : " + category + ", " + 
			"title : " + title + ", " + 
			"content : " + content + ", " +
			"timestamp : " + timestamp + " ]"
		;
	}
	
}
