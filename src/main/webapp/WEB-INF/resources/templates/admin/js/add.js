function addBook() {
		$("#wait").css("display", "block");
		var file = $('#bkImg')[0].files[0];
		var formData = new FormData();
		if (file != null){
			formData.append('avatar', file);
		}
		var title = $("#bkName").val();
		var des = $("#bkDes").val();
		var enabled = $("#bkStatus").find(":selected").val();
		formData.append('title', title);
		formData.append('description', des);
		formData.append('enabled', enabled);
		$.ajax({
			url: "/api/admin/books/create", 
			type: "POST",
			dataType : "json",
			data : formData,
	    	contentType: false,
            processData: false,
			beforeSend: function (xhr) {
	  		    xhr.setRequestHeader ("Authorization", localStorage.getItem('token'));
	  		},
			success: function(result){
				$("#wait").css("display", "none");
	     	 	if(result.code == '200') {
	     	 		alert('Add Book Successfully!');
	     	 		window.location = "/admincp/books/index";
	     	 		return;
	     	 	} 
	      	},
	      	error: function(xhr, status, error) {
		    	$("#errorMsg").text(JSON.parse(xhr.responseText).text);
			}
		});
	}