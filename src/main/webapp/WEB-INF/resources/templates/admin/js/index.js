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