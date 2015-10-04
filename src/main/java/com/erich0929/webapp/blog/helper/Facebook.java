package com.erich0929.webapp.blog.helper;

import java.io.IOException;
import java.net.*;
import java.io.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.erich0929.webapp.blog.domain.*;

public class Facebook {
	private static final String CLIENT_ID = "889525324455647";
	private static final String CLIENT_SECRET = "291a6ad4ae24548a153d934e15f8f859";
	private static final String OAUTH_ENDPOINT = "https://graph.facebook.com/v2.3/oauth/access_token";
	private static final String REDIRECT_URL = "https://www.facebook.com/dialog/oauth";
	private static final String GRAPH_ENDPOINT = "https://graph.facebook.com/v2.4/me";

	private String code;
	private FacebookToken facebookToken;

	// private String access_token;
	public Facebook() 
	{

	}

	public Facebook(String code) {
		this.code = code;
		facebookToken = null;
	}

	public String getCode() {
		return code;
	}

	public String getAccessToken(String redirectURI) {
		if (facebookToken == null) {
			ExchangeCodeForAccessToken(redirectURI);
		}
		return facebookToken.getAccess_token();
	}

	private void ExchangeCodeForAccessToken(String redirectURI) {
		try {
			StringBuffer endpoint = new StringBuffer(OAUTH_ENDPOINT + "?");
			endpoint.append("client_id=").append(CLIENT_ID)
					.append("&redirect_uri=").append(redirectURI)
					.append("&client_secret=").append(CLIENT_SECRET)
					.append("&code=").append(code);

			URL url = new URL(endpoint.toString());
			facebookToken = getFacebookToken(url);
			if (facebookToken == null)
				throw new RuntimeException("facebook token is null");
			return;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private FacebookToken getFacebookToken(URL requestToken) throws IOException {
		facebookToken = getMappedObject(requestToken, FacebookToken.class);
		return facebookToken;
	}

	public User getFacebookUser() {
		if (facebookToken == null) {
			throw new RuntimeException("Access Token is not loaded");
		}
		StringBuffer stringBuffer = new StringBuffer(GRAPH_ENDPOINT);
		stringBuffer.append("?access_token=")
				.append(facebookToken.getAccess_token())
				.append("&fields=id,name,email")
				.append("&method=get&pretty=0&suppress_http_code=1");
		URL graph_endpoint = null;
		try {
			graph_endpoint = new URL(stringBuffer.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return getMappedObject(graph_endpoint, User.class);
	}

	private <E> E getMappedObject(URL url, Class<E> mappedClass) {
		E mappedObject = null;
		try {
			InputStream is = url.openStream();
			ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
			int read = 0;
			byte[] readBytes = new byte[1024];
			while ((read = is.read(readBytes)) != -1) {
				byteBuffer.write(readBytes);
			}
			String json = new String(byteBuffer.toByteArray());
			ObjectMapper mapper = new ObjectMapper();
			mappedObject = mapper.readValue(json, mappedClass);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return mappedObject;
	}
	
	private <E> E getMappedObject(HttpURLConnection conn, Class<E> mappedClass) {
		E mappedObject = null;
		try {
			InputStream is = conn.getInputStream();
			ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
			int read = 0;
			byte[] readBytes = new byte[1024];
			while ((read = is.read(readBytes)) != -1) {
				byteBuffer.write(readBytes);
			}
			String json = new String(byteBuffer.toByteArray());
			ObjectMapper mapper = new ObjectMapper();
			mappedObject = mapper.readValue(json, mappedClass);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return mappedObject;
	}
	
	private static final String FEED_ENDPOINT = "https://graph.facebook.com/v2.4/me/feed";
	public FacebookFeed postFeed (String message)
	{
		HttpURLConnection conn = null;
		int responseCode = 0;
		String params = null;
		try 
		{
			URL facebook_feed_endpoint = new URL (FEED_ENDPOINT);
			conn = (HttpURLConnection ) facebook_feed_endpoint.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			conn.setDoOutput(true);
			DataOutputStream dos = new DataOutputStream (conn.getOutputStream());
			params = "access_token=" + this.facebookToken.getAccess_token() 
					+ "&message=" + message
					+ "&format=json"
					+ "&method=post"
					+ "&pretty=0" 
					+ "&suppress_http_code=1";
			dos.write(params.getBytes ("UTF-8"));
			dos.flush();
			dos.close();
			responseCode = conn.getResponseCode();
			
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		if (responseCode != 200)
		{
			//throw new RuntimeException (Integer.toString(responseCode) + " params : "+ params);
			return null;
		}
		return getMappedObject (conn, FacebookFeed.class);
		
	}
}
