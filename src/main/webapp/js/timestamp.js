$ (function () {
	$ (".timestamp").each (function (i) {
		var timestamp = $ (this).text ();
		console.log(timestamp);
		$ (this).text (getDateTime(parseInt(timestamp) ));
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