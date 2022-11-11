"use strict";

/**
 * Delete something.
 */
function deleteById(name, id, path) {
	if (confirm("Are you sure you want to delete '" + name + "'?")) {
		$.ajax({
			url: getUrlPrefix() + path + "/" + id,
			type: 'DELETE',
			success: function(result) {
				document.write(unescape(result));
			},
			error: function(request, msg, error, status) {
				alert(msg);
				console.log("request=" + request.url + ", msg=" + msg, "error=" + error, " status=" + status);
			}
		});
	}
}


/**
 * Hook up a click listener to table rows.
 */
jQuery(document).ready(function($) {
	$(".clickable-row").click(function() {
		window.location = $(this).data("href");
	});
});

function toggleSelected(elem) {
	elem.className = elem.className == "selected" ? "unselected" : "selected";
}

function getUrlPrefix() {
	return window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + "/";
}
