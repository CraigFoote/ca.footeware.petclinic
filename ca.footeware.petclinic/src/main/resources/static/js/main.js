function deletePet(id) {
	if (confirm("Are you sure you want to delete this poor helpless pet?")) {
		$.ajax({
			url: '/pets/' + id,
			type: 'DELETE',
			success: function(result) {
				window.location.href = "/pets";
			},
			error: function(request, msg, error) {
				alert(msg);
				console.log("request=" + request + ", msg=" + msg, "error=" + error);
			}
		});
	}
}

function updatePet() {
	var form = window.document.getElementById("editPetForm");
	const formData = new FormData(form);
	$.ajax({
		url: '/pets',
		type: 'PUT',
		data: formData,
		processData: false,
		contentType: false,
		success: function(result) {
			window.location.href = "/pets";
		},
		error: function(request, msg, error) {
			alert(msg);
			console.log("request=" + request + ", msg=" + msg, "error=" + error);
		}
	});
}