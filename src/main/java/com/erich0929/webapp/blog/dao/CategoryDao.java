package com.erich0929.webapp.blog.dao;

import java.sql.*;
import java.util.*;
import com.erich0929.webapp.blog.domain.*;

public interface CategoryDao 
{
	public boolean contain (String category);
	public List<Category> findCategories () throws SQLException;
	public void insertCategory (String category, String description);
	public void deleteCategory (String category) throws SQLException;
	public void updateCategory (String oldCategory, String newCategory, String newDesc ) throws SQLException;
}
