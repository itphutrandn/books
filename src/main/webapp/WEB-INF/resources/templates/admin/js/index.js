function logout() {
	$.ajax({
		url: "/api/admin/logout", 
		type: "GET",
		success: function(result){
			console.log(result);
     	 	if(result.code == '200') {
     	 		localStorage.removeItem("token");
     	 		localStorage.removeItem("userInfo");
     	 		window.location = "/auth/login";
     	 		return;
     	 	} 
      	}, 
	    error: function(xhr, status, error) {
	    	window.location = "/auth/login";
		}
	});
}

$( document ).ready(function() {
	$("#wait").css("display", "block");
	$(function () {
	    $("#books_table").DataTable({
	      "responsive": true,
	      "autoWidth" : false,
	      "pagingType": 'full_numbers',
	      "responsive": true,
	      "lengthMenu": [10, 20, 50],
	      "columnDefs": [
	    	    { "searchable": false, "orderable": false, "targets": 0 },
	    	    { 
	    	    	"searchable": false, 
	    	    	"orderable": false, 
	    	    	"targets": 4,
	    	    	"data": "id",
	    	    	"render": function(data,type,full,meta) { 
	    	    	 	return '<a class="btn btn-info btn-sm" href="javascript:void(0)"  onclick="return handleEditBook('+data+')"><i class="fas fa-pencil-alt"></i>Edit</a>'+
	    	    	 	'<a class="btn btn-danger btn-sm" href="javascript:void(0)" onclick="return handleDeleteBook('+data+')"><i class="fas fa-trash"></i>Delete</a>' + 
	    	    	 	'<a class="btn btn-primary btn-sm" href="javascript:void(0)" onclick="return handleViewBook('+data+')"><i class="fas fa-folder"></i>View</a>';
	    	    	 }
	    	    },
	    	    { 
	    	    	"targets": 3,
	    	    	"data": "_all",
	    	    	"render": function(data,type,full,meta) { 
	    	    		if(data == '1') {
	    	    			return '<a href="javascript:void(0)" onclick="return handleAvtive('+full.enabled+', '+full.id+')"><img width="30px" src="/resources/templates/admin/icon/on.png"></a>';
	    	    		}
	    	    		return '<a href="javascript:void(0)" onclick="return handleAvtive('+full.enabled+','+full.id+')"><img width="30px" src="/resources/templates/admin/icon/off.png"></a>';
	    	    	 }
	    	    },
	      ],
	      "createdRow": function (row, data, dataIndex) {
	    	   $(row).attr('id','result_book_'+data.id);
	      },
	      "ajax": {
	    	url: "/api/admin/bookList", 
	  		type: "POST",
	  		beforeSend: function (xhr) {
	  		    xhr.setRequestHeader ("Authorization", localStorage.getItem('token'));
	  		},
	  		"dataSrc": function ( json ) {
	  			$("#wait").css("display", "none");
                return json.data.books;
	  	    },error: function(xhr, status, error) {
	  	    	$("#wait").css("display", "none");
	  	    	localStorage.removeItem("token");
	 	 		localStorage.removeItem("userInfo");
	 	 		window.location = "/login";
	  	    	return;
    		}
	      },
	      "columns": [
	            { "data": "id" },
	            { "data": "title" },
	            { "data": "author" },
	            { "data": "enabled" }
	     ]
	    });
	  });
});
function handleAvtive(status, bookId) {
	if(status == 0) {
		status = 1;
	} else {
		status = 0;
	}
	
	data = {
		"bookId" : bookId,
		"enabled" : status
	}
	$.ajax({
		url: "/api/admin/books/active", 
		type: "POST",
		dataType : "json",
		contentType: "application/json; charset=utf-8",
		data : JSON.stringify(data),
		beforeSend: function (xhr) {
  		    xhr.setRequestHeader ("Authorization", localStorage.getItem('token'));
  		},
		success: function(result){
     	 	if(result.code == '200') {
     	 		if(data.enabled == '1') {
     	 			$("#result_book_"+bookId+ " td:eq(3)").html('<a href="javascript:void(0)" onclick="return handleAvtive('+status+', '+bookId+')"><img width="30px" src="/resources/templates/admin/icon/on.png"></a>');
     	 		} else {
     	 			$("#result_book_"+bookId+ " td:eq(3)").html('<a href="javascript:void(0)" onclick="return handleAvtive('+status+','+bookId+')"><img width="30px" src="/resources/templates/admin/icon/off.png"></a>');
     	 		}
    		}
      	}, 
	    error: function(xhr, status, error) {
	    	$("#wait").css("display", "none");
	    	localStorage.removeItem("token");
 	 		localStorage.removeItem("userInfo");
 	 		window.location = "/login";
  	    	return;
		}
	});
}

function handleViewBook(bookId) {
	window.location = "/admincp/books/detail/"+bookId;
	return;
}

function handleDeleteBook(bookId) {
	var isDel = confirm('You want to delete?');
	if(isDel){
		$("#wait").css("display", "block");
		$.ajax({
			url: "/api/admin/books/delete/"+bookId, 
			type: "POST",
			beforeSend: function (xhr) {
	  		    xhr.setRequestHeader ("Authorization", localStorage.getItem('token'));
	  		},
			success: function(result){
				$("#wait").css("display", "none");
	     	 	if(result.code == '200') {
	     	 		alert('Delete successfully!');
	     	 		location.reload();
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
	}
}

function handleEditBook(bookId) {
	window.location = "/admincp/books/edit/"+bookId;
	return;
}

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


$( document ).ready(function() {
	var url_string = window.location.pathname; 
	var bookId = url_string.substring(url_string.lastIndexOf('/') + 1);
	$.ajax({
		url: "/api/admin/books/detail/"+bookId, 
		type: "GET",
		beforeSend: function (xhr) {
  		    xhr.setRequestHeader ("Authorization", localStorage.getItem('token'));
  		},
		success: function(result){
     	 	if(result.code == '200') {
     	 		var book = result.data.book;
     	 		$("#title").text(book.title);
     	 		$("#bkTitle").text(book.title);
     	 		$("#bkId").text(book.id);
     	 		$("#bkAuthor").text(book.author);
     	 		$("#bkStatus").text(book.enabled == 1 ? 'Enabled' : 'Disable');
     	 		$("#bkDes").text(book.description);
     	 		if(book.image != '' && book.image != null) {
     	 			$("#bkImg").html('<img style="width:400px" src="/resources/uploads/'+book.image+'" />');
     	 		} else {
     	 			$("#bkImg").html('No image');
     	 		}
     	 		return;
     	 	} 
      	}, 
	    error: function(xhr, status, error) {
	    	alert(JSON.parse(xhr.responseText).text);
	    	localStorage.removeItem("token");
 	 		localStorage.removeItem("userInfo");
	    	window.location = "/news";
		}
	});
});
	
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