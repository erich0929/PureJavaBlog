$ (function () {
	check_all_button_bind ();
	buttons_bind ();
})

function check_all_button_bind ()
{
	$ ("#check_all").change (function () {
		var checked = this.checked;
		$ ("#checkbox input").each (function (i) {
			this.checked = checked; 
		});
	});
}

function buttons_bind ()
{
	$("#button_box #update").click (function (e) {
		var count_checked = 0;
		var articleId;
		$ ("#checkbox input").each (function (i) {
			if (this.checked === true) 
			{
				articleId = $(this).val();
				console.log(articleId);
				count_checked++;
			}
		});
		if (count_checked === 0) 
		{
			alert ("You must select one article.");	
			return false;
		} else if (count_checked > 1) 
		{
			alert ("You must select just one article.");
			return false;
		}
		$("#action_form").attr ("action", "/blog/admin/article/update/" + articleId);
		$("#action_form").attr ("method", "get");
		return true;
	});
	$("#button_box #delete").click (function (e) {
		var url = window.location;
		url = String (url);
		var regex = /category\/(.+)\?page=([0-9]+)/i;
		var res = url.match (regex);
		$("#action_form").attr ("action", "/blog/admin/article/delete?category=" + res[1] + "&page=" + res[2]);
		$("#action_form").attr ("method", "post");
		return true;
	});
}