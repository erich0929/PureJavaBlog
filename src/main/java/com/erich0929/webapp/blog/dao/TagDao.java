package com.erich0929.webapp.blog.dao;

import java.util.*;
import com.erich0929.webapp.blog.domain.*;
import java.sql.*;

public interface TagDao
{
	public List<String> findTagByArticleId (String articleId) throws SQLException;
	public boolean insertTag (String articleId, List<String> tags);
	public boolean deleteTag (String articleId);
	public boolean updateTag (String articleId, List<String> tags);
}
