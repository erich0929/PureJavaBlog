<%@page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="/blog/css/insert_article.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="/blog/css/fileupload.css" media="screen" />
		<script type="text/javascript" src="/blog/lib/jquery/dist/jquery.min.js"></script>
		<script type="text/javascript" src="/blog/lib/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="/blog/js/fileupload.js"></script>
		<script type="text/javascript">
			$ (function () {
				CKEDITOR.instances["editor"].setData("<c:out value="${article.content}" escapeXml="false" ></c:out>");
				$("#select_category").val("<c:out value="${article.category}" escapeXml="false" />");
			});
		</script>
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
				<h2><a href="#">Articles by Category:</a></h2>
				<div class="box">
					<ul>
						<li><a href="#">Messi</a></li>
						<li><a href="#">Java</a></li>
						<li><a href="#">C++</a></li>
					</ul>
				</div>
				<h2><a href="#">New Article</a></h2>
				<h2><a href="#">New Category</a></h2>
				
			</div>
			<div id="content">
				<div id="article_form_box">
					<form  action="/blog/admin/article/update" method="post">
						<div id="category_box" class="field">
							<input type="hidden" name="id" value="${article.id }" />
							<span>Category</span>
							<select name="category" id="select_category">
								<option value="Messi">Messi</option>
								<option value="Java">Java</option>
								<option value="Template c++">C++</option>
								<option value="some category">some category</option>
							</select>
						</div>
						<div id="fileupload_box" class="field">
							<span>File Upload</span>
							<button id="fileupload_button" type="button">...</button>
						</div>
						<div id="title_box" class="field">
							<div>Title</div>
							<input type="text" name="title" value="${article.title }">
						</div>
						<div id="content_box" class="field">
							<div>Content</div>
							<textarea name="content" id="editor" }"></textarea>
						</div>
						<div id="tag_box" class="field">
							<div>Tags</div>
							<input type="text" id="tag_input" />
							<input type="hidden" id="tag_output" name="tags" />
							<div id="tagPreview"></div>
						</div>
						<input type="submit" value="저장하기" / >
					</form>
				</div>
				<div id="filedialog">
					<div id="title_bar">
						<div id="title">File Upload Dialog</div>
						<div id="button_box">
							<ul>
								<li><img class="button" id="close_button" src="../img/cancel.png" alt="Cancel" /></li>
							</ul>	
						</div>
					</div>
					<div id="form_box"  class="field">
						<form id="upload_form" action="/blog/upload" enctype="multipart/form-data" method="post">
							<div id="label" class="field">Select files ...</div>
							<input type="file" name="uploadfile" />
							<input type="submit" id="fileupload_submit" value="Upload" />
						</form>
					</div>
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