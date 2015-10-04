package com.erich0929.webapp.blog.domain;

import com.erich0929.webapp.blog.domain.*;
import java.util.*;

public class AjaxComment
{
	private String commentId;
	private List<Comment> comments;
	private int status;
	public AjaxComment () {}
	public AjaxComment (String commentId, List<Comment> comments, int status) 
	{
		this.commentId = commentId;
		this.comments = comments;
		this.status = status;
	}
	public void setCommentId (String commentId)
	{
		this.commentId = commentId;
	}
	public String getCommentId ()
	{
		return commentId;
	}
	public void setComments (List<Comment> comments)
	{
		this.comments = comments;
	}
	public List<Comment> getComments ()
	{
		return comments;
	}
	public void setStatus (int status)
	{
		this.status = status;
	}
	public int getStatus ()
	{
		return status;
	}
	@Override
	public String toString ()
	{
		StringBuffer stringBuffer = new StringBuffer ();
		stringBuffer.append("[AjaxComment INFO] commentId : " + commentId + "\n");
		stringBuffer.append(comments.toString() + "\n");
		stringBuffer.append("[AjaxComment INFO] status : " + status);
		return stringBuffer.toString();
		
		
	}
}
