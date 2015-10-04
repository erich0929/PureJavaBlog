package com.erich0929.webapp.blog.domain;

public class FacebookToken 
{
	private String access_token;
	private String token_type;
	private int expires_in;
	
	public void setAccess_token (String access_token)
	{
		this.access_token = access_token;
	}
	public String getAccess_token ()
	{
		return access_token;
	}
	public void setToken_type (String token_type)
	{
		this.token_type = token_type;
	}
	public String getToken_type ()
	{
		return token_type;
	}
	public void setExpires_in (String expire_in)
	{
		this.expires_in = Integer.parseInt(expire_in);
	}
	public int getExpires_in ()
	{
		return expires_in;
	}
}
