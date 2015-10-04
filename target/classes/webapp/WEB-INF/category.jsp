<%@page contentType="text/html;charset=utf-8"
		pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="../css/style.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="../css/category.css" media="screen" />
		<script type="text/javascript" src="../lib/jquery/dist/jquery.min.js"></script>
		<script type="text/javascript" src="../js/timestamp.js"></script>
	</head>
	<body>
		<div id="wrap">
			<div id="header">
				<div id="headerlinks">
					<a href="#">Blog</a>
					<a href="#">profile</a>
					<a href="#">Facebook</a>
				</div>
				<h1><a href="#">Java Blog</a></h1>
			</div>
			<div id="sidebar">
				<h2>Recent Posts:</h2>
				<div class="box">
					<ul>
						<li><a href="#">Hello World</a></li>
						<li><a href="#">All starts here</a></li>
						<li><a href="#">Whatever</a></li>
						<li><a href="#">Simple Build</a></li>
						<li><a href="#">Testing</a></li>
					</ul>
				</div>
				<h2>Category:</h2>
				<div class="box">
					<ul>
						<li><a href="#">Messi</a></li>
						<li><a href="#">Java</a></li>
						<li><a href="#">C++</a></li>
					</ul>
				</div>
			</div>
			<div id="content">
				<div id="about_category">
					<b>${pagenation.category } (${pagenation.currentPage + 1}/${pagenation.totalPage + 1})</b>
				</div>
				<div id="category_table">
					<c:forEach items="${pagenation.articles}" var="article" varStatus="status">
						<div class="table_row">
							<div id="title" class="table_col">
								<a href="/blog/article/${article.id}">${article.title}</a>
							</div>
							<span id="comment_count" class="table_col">
								<c:if test="${pagenation.commentCountList[status.count-1] != 0 }">
									(${pagenation.commentCountList[status.count-1] })
								</c:if>
							</span>
							<div class="table_col date">
								<i class="timestamp">${article.timestamp}</i>
							</div>
						</div>
					</c:forEach>
				</div>
				<div id="out_box">
					<div id="pagenation">
						<c:if test="${pagenation.chapterBegin != 0 }">
							<div class="page">
								<a
									href="/blog/category/${pagenation.category }?page=${pagenation.chapterBegin - 1}">Prev</a>
							</div>
						</c:if>

						<c:forEach var="x" begin="${pagenation.chapterBegin }"
							end="${pagenation.chapterEnd }">
							<div class="page">
								<a href="/blog/category/${pagenation.category }?page=${x}"><c:out
										value="${x+1 }" /></a>
							</div>
						</c:forEach>
						<c:if test="${pagenation.chapterEnd != pagenation.totalPage }">
							<div class="page">
								<a
									href="/blog/category/${pagenation.category }?page=${pagenation.chapterEnd + 1}">Next</a>
							</div>
						</c:if>

					</div>
				</div>
			</div>
			<div id="footer">
				<div id="icon">
					<a href="#" title="Contact Us"><img src="img/contact.gif" alt="Contact" /></a>
					<a href="#" title="Sitemap"><img src="img/sitemap.gif" alt="Sitemap" /></a>
					<a href="#" title="Rss Feed"><img src="img/rss.png" alt="Rss Feed" /></a>
				</div>
				Java Blog Copyright by erich0929@gmail.com
			</div>
		</div>
	</body>
</html>