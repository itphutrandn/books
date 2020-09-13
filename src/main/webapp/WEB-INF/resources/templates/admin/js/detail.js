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
	    	localStorage.removeItem("token");
 	 		localStorage.removeItem("userInfo");
	    	window.location = "/news";
		}
	});
});