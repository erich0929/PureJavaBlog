package com.erich0929.webapp.blog.test;

import com.fasterxml.jackson.core.*;

import java.util.*;
import com.fasterxml.jackson.databind.*;
import org.junit.*;
import java.io.*;

public class JacksonTest {
	private String access_token;
	private int num;
	
	public String getAccess_token () 
	{
		return access_token;
	}
	public void setName (String access_token)
	{
		this.access_token = access_token;
	}
	public void setNum (String num)
	{
		this.num = Integer.parseInt(num);
	}
	public int getNum ()
	{
		return num;
	}
	@Test
	public void jsonTest ()
	{
		ObjectMapper mapper = new ObjectMapper ();
		String json = "{\"access_token\" : \"erich0929\", \"num\" : \"518\"}";
		
		try 
		{
			JacksonTest testObject = mapper.readValue (json, JacksonTest.class);
			System.out.println (mapper.writeValueAsString(testObject));
		} catch (JsonMappingException e)
		{
			e.printStackTrace();
		} catch (JsonParseException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void jasonToMap ()
	{
		ObjectMapper mapper = new ObjectMapper ();
		String json = "{  "
				+ "\"error\": { \"message\": \"(#100) Tried accessing nonexisting field (nam) on node type (User)\","
				+ "  \"type\": \"OAuthException\", " 
				+ "\"code\": 100} }";
		try
		{
			HashMap<String, Map> map = mapper.readValue(json, HashMap.class);
			System.out.println (map.get("error"));
			System.out.println (mapper.writeValueAsString(map));
		} catch (JsonMappingException e)
		{
			e.printStackTrace();
		} catch (JsonParseException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
