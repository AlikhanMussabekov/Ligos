var formData = JSON.stringify()

function submitForm() {
	var formData = JSON.stringify($("registrationForm").serializeArray());

	$.ajax({
		type: "POST",
		url: "localhost:8080/user/registration",
		data: formData,
		success: function(data){
			alert(data)
		},
		dataType: "json",
		contentType : "application/json"
	});

}