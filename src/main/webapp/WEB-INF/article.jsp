<%@page contentType="text/html;charset=utf-8" 
		pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="../css/style.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="../css/comment.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="../css/article.css" />
		<link rel="stylesheet" type="text/css" href="../css/tag.css" media="screen" />
		<script type="text/javascript" src="../lib/jquery/dist/jquery.min.js"></script>
		<script type="text/javascript" src="../js/comment.js"></script>
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
					
					</ul>
				</div>
			</div>
			<div id="content">
				<div id="article_header">
					<h2><b>${article.title}</b></h2>
				</div>
				<div id="article_meta"><i>작성자 : erich0929</i> <i class="timestamp">${article.timestamp}</i></div>
				<img src="../img/technics-q-c-680-280-4.jpg" class="post-img" alt="Bild" />
				<p id="article_content">${article.content}</p>
			<div id="tags">
				<div id="tagentry">Tags : </div>
				<div id="tag">
					<ul>
						<c:forEach items="${article.tags }" var="tag">
							<li><a href="#">${tag}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div id="commentbox">
				<div id="comment_box_label">Comment</div>
				<div id="login_icon_box">
					<a href="/blog/facebook_user_login?id=${article.id }">
						<img src="../img/facebook.png" alt="Login with facebook" class="login_icon">	
					</a>
				</div>
				<div id="comment_text_box">
					<textarea name="comment"></textarea>	
				</div>
				<button type="button" id="comment_post"
				<c:if test="${sessionScope.user == null}">
					disabled
				</c:if>
				>Post</button>
			</div>
			<div id="comments">
				<div id="count">Comments  ${article.commentsCount}</div>
				<c:forEach items="${article.comments}" var="comment">
					<div id="${comment.id}" class="comment">
						<ul>
							<li>${comment.user }</li>
							<li class="date">${comment.timestamp}</li>
						</ul>
						<p>${comment.comment} </p>
					</div>
				</c:forEach>
				
				
				
			</div>	
			</div>
			
			<div id="footer">
				<div id="icon">
					<a href="#" title="Contact Us"><img src="../img/contact.gif" alt="Contact" /></a>
					<a href="#" title="Sitemap"><img src="../img/sitemap.gif" alt="Sitemap" /></a>
					<a href="#" title="Rss Feed"><img src="../img/rss.png" alt="Rss Feed" /></a>
				</div>
				Java Blog Copyright by erich0929@gmail.com
			</div>
		</div>
	</body>
</html>