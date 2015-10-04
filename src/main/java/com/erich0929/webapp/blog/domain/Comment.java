package com.erich0929.webapp.blog.domain;

public class Comment 
{
	private String id;
	private String articleId;
	private String user;
	private String comment;
	private long timestamp;
	
	public Comment () 
	{
		
	}
	
	public Comment (String id, String articleId, String user, String comment, long timestamp)
	{
		this.id = id;
		this.articleId = articleId;
		this.user = user;
		this.comment = comment;
		this.timestamp = timestamp;
	}
	public void setId (String id)
	{
		this.id = id;
	}
	public String getId ()
	{
		return id;
	}
	public void setArticleId (String articleId)
	{
		this.articleId = articleId;
	}
	public String getArticleId ()
	{
		return articleId;
	}
	public void setUser (String user)
	{
		this.user = user;
	}
	public String getUser ()
	{
		return user;
	}
	public void setComment (String comment)
	{
		this.comment = comment;
	}
	public String getComment ()
	{
		return comment;
	}
	public void setTimestamp (long timestamp)
	{
		this.timestamp = timestamp;
	}
	public long getTimestamp ()
	{
		return timestamp;
	}
	
	@Override
	public String toString ()
	{
		StringBuffer stringBuffer = new StringBuffer ();
		stringBuffer.append ("[Comment INFO]  id : " + id + ", ");
		stringBuffer.append ("articleId : " + articleId + ", ");
		stringBuffer.append ("user : " + user + ", ");
		stringBuffer.append ("comment : " + comment + ", ");
		stringBuffer.append ("timestamp : " + timestamp + "\n");
		return stringBuffer.toString();
	}
}
