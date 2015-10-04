package com.erich0929.webapp.blog.dao;

import com.erich0929.webapp.blog.domain.*;
import java.sql.*;

public interface PagenationDao {
	//public Pagenation loadPage (String frontArticleId) ;
	public Pagenation loadPage (String category, int currentPage);
	
}
