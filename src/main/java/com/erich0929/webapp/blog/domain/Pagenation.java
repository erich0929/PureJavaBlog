package com.erich0929.webapp.blog.domain;

import java.util.*;
import com.erich0929.webapp.blog.dao.*;

public class Pagenation 
{
	private String frontArticleId;
	private int currentPage;
	private List<Article> articles;
	private int totalPage;
	private String category;
	private List<Integer> commentCountList; 
	private int chapterBegin;
	private int chapterEnd;
	
	public Pagenation ()
	{
		frontArticleId = null;
		currentPage = 0;
		articles = null;
		totalPage = 0;
		commentCountList = null;
	}
	public Pagenation (String frontArticleId, int currentPage)
	{
		this.frontArticleId = frontArticleId;
		this.currentPage = currentPage;
	}
	public void setFrontArticle (String frontArticleId)
	{
		this.frontArticleId = frontArticleId;
	}
	public String getFrontArticle ()
	{
		return frontArticleId;
	}
	public void setCurrentPage (int currentPage)
	{
		this.currentPage = currentPage;
		makeChapterRange ();
	}
	public int getCurrentPage ()
	{
		return currentPage;
	}
	public void setArticles (List<Article> articles)
	{
		this.articles = articles;
	}
	public List<Article> getArticles ()
	{
		return articles;
	}
	public void setTotalPage (int totalPage)
	{
		this.totalPage = totalPage;
		makeChapterRange ();
	}
	public int getTotalPage ()
	{
		return totalPage;
	}
	
	public void setCategory (String category)
	{
		this.category = category;
	}
	public String getCategory ()
	{
		return category;
	}
	public void setCommentCountList (List<Integer> commentCountList)
	{
		this.commentCountList = commentCountList;
	}
	public List<Integer> getCommentCountList ()
	{
		return commentCountList;
	}
	public int getChapterBegin ()
	{
		return chapterBegin;
	}
	public int getChapterEnd ()
	{
		return chapterEnd;
	}
	private void makeChapterRange ()
	{
		chapterBegin = getFirstOfChapter ();
		chapterEnd = getEndOfChaper ();
	}
	
	public static final int SIZE_OF_CHAPTER = 3;
	
	public int getFirstOfChapter ()
	{
		return (int)(Math.floor((double)currentPage/(double)SIZE_OF_CHAPTER) * SIZE_OF_CHAPTER);
	}
	public int getEndOfChaper ()
	{
		int tempEnd = chapterBegin + SIZE_OF_CHAPTER - 1;
		return (totalPage > tempEnd) ? tempEnd : totalPage;
				
	}
	
	@Override
	public String toString ()
	{
		StringBuffer stringBuffer = new StringBuffer ();
		stringBuffer.append("[Pagenation info] current page / total page : " + currentPage + " / " + totalPage + "\n");
		stringBuffer.append("[Pagenation info] page count : " + articles.size() + "\n");
		stringBuffer.append("[Pagenation info] category : " + category + "\n");
		for (Article article : articles)
		{
			stringBuffer.append(article.toString() + "\n");
		}
		return stringBuffer.toString();
	}
	
}
