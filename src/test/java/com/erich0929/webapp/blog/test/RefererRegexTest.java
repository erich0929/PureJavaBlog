package com.erich0929.webapp.blog.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.regex.*;

import com.erich0929.webapp.blog.domain.*;
import com.erich0929.webapp.blog.helper.*;

public class RefererRegexTest 
{
	private static final String START_REGEX = "^";
	private static final String ARTICLEID_END = "/(.+)$";
	
	@Test
	public void regexTest ()
	{
		String referer = "http://localhost:8080/blog/article/some_article_id";
		Pattern refererPattern = Pattern.compile(START_REGEX + UrlDomain.ARTICLE_VIEW + ARTICLEID_END);
		Matcher matcher = refererPattern.matcher(referer);
		assertTrue (matcher.matches());
		String articleId = matcher.group(1);
		System.out.println (articleId);
		assertEquals ("some_article_id", articleId);
	}
}
