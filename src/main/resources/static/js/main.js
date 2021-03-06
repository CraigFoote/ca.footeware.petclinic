/**
 * Delete a pet.
 */
function deletePet(id) {
	if (confirm("Are you sure you want to delete this poor helpless pet?")) {
		$.ajax({
			url: window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + '/pets/' + id,
			type: 'DELETE',
			success: function(result) {
				window.location.href = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + "/pets";
			},
			error: function(request, msg, error, status) {
				alert(msg);
				console.log("request=" + request + ", msg=" + msg, "error=" + error, " status=" + status);
			}
		});
	}
}

/**
 * Update a pet.
 */
function updatePet() {
	var form = window.document.getElementById("editPetForm");
	const formData = new FormData(form);
	$.ajax({
		url: window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + '/pets',
		type: 'PUT',
		data: formData,
		processData: false,
		contentType: false,
		success: function(result) {
			window.location.href = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + "/pets";
		},
		error: function(request, msg, error, status) {
			alert(msg);
			console.log("request=" + request + ", msg=" + msg, "error=" + error, " status=" + status);
		}
	});
}

/**
 * Hook up a click listener to table rows.
 */
jQuery(document).ready(function($) {
	$(".clickable-row").click(function() {
		window.location = $(this).data("href");
	});
});

function toggleSelected(elem){
	console.log(elem.className);
	elem.className = elem.className == "selected" ? "unselected" : "selected";
	console.log(elem.className);
}
