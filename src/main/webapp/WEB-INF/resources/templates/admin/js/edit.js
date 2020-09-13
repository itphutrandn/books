
  	
  $( document ).ready(function() {
	    $("#wait").css("display", "block");
		var url_string = window.location.pathname; 
		var bookId = url_string.substring(url_string.lastIndexOf('/') + 1);
		$.ajax({
			url: "/api/admin/books/detail/"+bookId, 
			type: "GET",
			beforeSend: function (xhr) {
	  		    xhr.setRequestHeader ("Authorization", localStorage.getItem('token'));
	  		},
			success: function(result){
				$("#wait").css("display", "none");
	     	 	if(result.code == '200') {
	     	 		var book = result.data.book;
	     	 		str = '';
	     	 		/*
	     	 		if(book.enabled == '1') {
	     	 			str =  '<option  value="">Select status</option>';
	     	 			str += '<option value="1" selected>Enable</option>';
	     	 			str += ' <option value="0">Disable</option>';
	     	 		} else {
	     	 			str =  '<option  value="">Select status</option>';
	     	 			str += '<option value="1">Enable</option>';
	     	 			str += '<option value="0" selected>Disable</option>';
	     	 		}
	     	 		*/
	     	 		$("#bkName").val(book.title);
	     	 		$("#bkId").val(book.id);
	     	 		$("#bkDes").val(book.description);
	     	 		$("#bkStatus").html(str);
	     	 		if(book.image != '' && book.image != null) {
	     	 			$("#bkImgDes").html('<img style="width:230px;height:150px;" src="/resources/uploads/'+book.image+'" /> <br /> <input type="checkbox" name="delImg" id="delImg" value="0" /> Delete image');
	     	 		} else {
	     	 			$("#bkImgDes").html('No image');
	     	 		}
	     	 		return;
	     	 	} 
	      	}, 
		    error: function(xhr, status, error) {
		    	$("#wait").css("display", "none");
		    	alert(JSON.parse(xhr.responseText).text);
		    	localStorage.removeItem("token");
	 	 		localStorage.removeItem("userInfo");
		    	window.location = "/news";
			}
		});
	});
  	
	function editBook() {
		$("#wait").css("display", "block");
		var url_string = window.location.pathname; 
		var bookId = url_string.substring(url_string.lastIndexOf('/') + 1);
		var file = $('#bkImg')[0].files[0];
		var formData = new FormData();
		var delImg = 0;
		if (file != null){
			formData.append('avatar', file);
		}
		
		$('input[name="delImg"]:checked').each(function() {
			delImg = 1;
		});
		
		var title = $("#bkName").val();
		var des = $("#bkDes").val();
		var enabled = $("#bkStatus").find(":selected").val();
		formData.append('title', title);
		formData.append('description', des);
		//formData.append('enabled', enabled);
		formData.append('id', bookId);
		formData.append('delImg', delImg);
		
		$.ajax({
			url: "/api/admin/books/update", 
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
	     	 		alert('Update Book Successfully!');
	     	 		window.location = "/admincp/books/index";
	     	 		return;
	     	 	} 
	      	},
	      	error: function(xhr, status, error) {
	      		$("#wait").css("display", "none");
		    	$("#errorMsg").text(JSON.parse(xhr.responseText).text);
			}
		});
	}