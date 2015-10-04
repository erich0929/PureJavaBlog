package com.erich0929.webapp.blog.servlet;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import com.erich0929.webapp.blog.helper.*;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)  
public class ProcessFile extends HttpServlet 
{
	@Override
	public void doPost (HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException
	{
		req.setCharacterEncoding ("utf-8");
		Part part = req.getPart("uploadfile");
		process (res, part);
	}

	private void process (HttpServletResponse res, Part part) 
			throws IOException
	{
		// Part (파일 파트헤더 정보를 가지고 있다.)
		// content-type : text/plain
		// content-disposition : form-data; name="uploadfile"; filename="somefilename.txt"
		// get file name
		
			String filename = null;
			String contentDispositionHeader = part.getHeader("content-disposition");
			String [] elements = contentDispositionHeader.split(";");
			for (String element : elements )
			{
				if (element.trim().startsWith ("filename"))
				{
					filename = element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
				}
			}
			
			filename = RandString.getRandString(30);
			part.write(getServletContext ().getRealPath ("/WEB-INF") +  "/" + filename);
			
			String url = "http://localhost:8080/blog/resource/" + filename;
			
			res.setContentType("application/json;charset=UTF-8");
			res.setCharacterEncoding("UTF-8");
			
			String responseJson = "{\"status\" : \"200\", \"url\" : \"" + url + "\"}" ;
			res.getWriter().print(responseJson);

	}
}
