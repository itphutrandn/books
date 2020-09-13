<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Book - itphutran.com</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
 <link rel="stylesheet" href="/resources/templates/admin/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="/resources/templates/admin/dist/css/adminlte.min.css">
</head>
<body style="background: aliceblue;">
<div id="wrapper" style="width: 80% !important;
    margin: 20px auto;">
  <div id="wrapper2">
    <div id="header">
      <div id="logo">
        <h1>Book List Project Demo | <img style="width:40px;" src="/resources/uploads/hinh1.jpg" /> Open Web Technology</h1>
      </div>
    </div>
    <div id="page">
      <div id="content">
       <table id="books_table" class="table table-bordered table-striped post" >
          <thead>
	          <tr>
	            <th width="5%">Id</th>
	            <th width="20%" class="sorting_asc">Title (Sort)</th>
	            <th width="10%" >Author (Sort)</th>
	            <th width="30%">Description</th>
	            <th width="10%">Image</th>
	          </tr>
          </thead>
        </table>
      </div>
      <div style="clear: both;">&nbsp;</div>
      <div id="widebar">
        <div style="clear: both;">&nbsp;</div>
      </div>
    </div>
  </div>
  <div id="footer">
    <p>(c) 2020 BookMG. Design by <a target="_blank" href="https://itphutran.com">itphutran.com</a> </p>
  </div>
</div>
<script src="/resources/templates/admin/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/resources/templates/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- DataTables -->
<script src="/resources/templates/admin/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/resources/templates/admin/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="/resources/templates/admin/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="/resources/templates/admin/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>

<script>
$( document ).ready(function() {
	$(function () {
	    $("#books_table").DataTable({
	      "responsive": true,
	      "autoWidth" : false,
	      "pagingType": 'full_numbers',
	      "responsive": true,
	      "lengthMenu": [10, 20, 50],
	      "columnDefs": [
	    	    { 
	    	    	"searchable": false, 
	    	    	"orderable": false, 
	    	    	"targets": 3
	    	    },
	    	    { 
	    	    	"targets": 4,
	    	    	"render": function(data,type,full,meta) { 
	    	    		if(full.image != '') {
	    	    			return '<img width="250px" src="/resources/uploads/'+full.image+'">';
	    	    		}
	    	    		return 'No image';
	    	    	 }
	    	    }
	      ],
	      "ajax": {
	    	url: "/api/admin/bookListEnabled", 
	  		type: "POST",
	  		beforeSend: function (xhr) {
	  		    xhr.setRequestHeader ("Authorization", localStorage.getItem('token'));
	  		},
	  		"dataSrc": function ( json ) {
	  			console.log(json.data.books);
                return json.data.books;
	  	    },
	  	    error: function(xhr, status, error) {
	  	    	window.location = "/auth/login";
	  	    	return;
    		}
	      },
	      "columns": [
	            { "data": "id" },
	            { "data": "title" },
	            { "data": "author" },
	            { "data": "desciption" },
	            { "data": "image" }
	     ]
	    });
	  });
});
</script>
</html>
