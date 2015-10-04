$ (document).ready (function () {
	dialogBind("#fileupload_button", "#filedialog");
	CKEDITOR.replace ('editor', {width : '600px', height: '400px'});
	fileupload_submit_bind ();
	tag_bind ();
});

function dialogBind (button, dialog) 
{
	$ (button).click (function () {
		var css = $(dialog).css ("display");
		css = (css == "none") ? "block" : "none";
		$(dialog).css ("display", css);
	});
	
	$(dialog + " #close_button").click (function () {
		$(dialog).css("display", "none");
	});
}

function fileupload_submit_bind ()
{
	$ ("#upload_form").submit (function (e) {
		e.preventDefault ();
		console.log("ajax before");
		//alert("ajax before");
		dialog = $("#filedialog");
		var fd = new FormData (this);
		$.ajax ({
			url : "http://localhost:8080/blog/upload",
			type : "post",
			data : fd,
			mimeType : "multipart/form-data",
			processData:false,
			contentType:false,
			success : function (res) {
				var data = JSON.parse(res);
				if (data.status)
				{
					dialog.css ("display", "none");
					window.prompt("File url : ", data.url);
				} else 
				{
					alert ("Response code : " + data.error);
				}
			},
			error : function () {
				alert ("Ajax request failed.");
			}
		});
	});
}

function tag_bind ()
{
	
	var tag_input = $ ("#tag_input");
	tag_input.keyup(function () {
		var tags = $ ("#tag_input").val ();
		var html_regex = /<.+?>/gi;
		var delimeter = /[,\|,\.`'";]+/gi;
		var front_trim = /^[,\|,\.`'";\s]+/gi;
		var rear_trim = /[,\|,\.`'";\s]+$/gi;
		tags = tags.replace (front_trim, '');
		tags = tags.replace (rear_trim, '');
		tags = tags.replace (html_regex, '');
		tags = tags.replace (delimeter, ' ');
		$ ("#tag_output").val (tags);
		var tag_preview = tags.split (/\s+/gi, 3);
		$ ('#tagPreview').html ('<p style="color: green;">' + tag_preview + '</p>');
	});
	
}