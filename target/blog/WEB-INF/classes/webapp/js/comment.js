$(function () {
	$("#comment_post").click(function () {
		var comment = "comment=" + $("#comment_text_box textarea").val();
		$("#comment_text_box textarea").val("");
		console.log(comment);
		$.ajax({
			url: "http://localhost:8080/blog/comment",
			type: "post",
			data: comment,
			success: function (res) {
				$("#comments").html("");
				console.log(res.comments);
				for (var index in res.comments) {
					console.log(comment);
					var commentDiv = document.createElement("div");
					$(commentDiv).addClass("comment")
						.attr("id", res.comments[index].id)
						.html("<ul><li>" + res.comments[index].user
						+ "</li><li class='date'>" + getDateTime (res.comments[index].timestamp)
						+ "</li></ul><p>" + res.comments[index].comment + "</p>");
					$(commentDiv).appendTo("#comments");
				}
				location.hash = "#" + res.commentId;
			},
			error: function () {
				alert("댓글게시 실패 오류.");
			}
		});
	});
});

function getDateTime (timestamp)
{
	var date = new Date (timestamp);
	var year = date.getFullYear ();
	var month = date.getMonth () + 1;
	var day_of_month = date.getDate ();
	var hour = date.getHours ();
	var minute = date.getMinutes ();
	return year + "/" + month + "/" + day_of_month + " " + hour + ":" + minute;
}
