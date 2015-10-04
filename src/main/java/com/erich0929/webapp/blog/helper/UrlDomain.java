package com.erich0929.webapp.blog.helper;

public class UrlDomain 
{
	public static final String BASE_DOMAMIN = "http://localhost:8080/blog";
	public static final String ARTICLE_VIEW = "http://localhost:8080/blog/article";
	public static final String FACEBOOK_USER_LOGIN= "https://www.facebook.com/dialog/oauth?"
			+ "client_id="
			+ "889525324455647&"
			+ "display=page&"
			+ "redirect_uri="
			+ "http://localhost:8080/blog/facebook_redirect&"
			+ "response_type=code&"
			+ "scope=email,publish_actions";
}
