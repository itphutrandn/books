<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/templates/admin/inc/header.jsp"%>
<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<%@include file="/WEB-INF/jsp/templates/admin/inc/left-bar.jsp"%>
</aside>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>Book Detail</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">Book Detail</li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Default box -->
		<div class="card">
			<div class="card-header">
				<h3 class="card-title">Book Detail</h3>
			</div>
			<div class="card-body">
				<div class="row">
					<div class="col-12 col-md-12 col-lg-12 order-12 order-md-12">
						<div class="row">
							<div class="col-12">
								<h4 id="title">Title</h4>
								<div class="post">
									<table border="1" width="80%">
										<tr>
											<th width="15%">Id</th>
											<td id="bkId" style="padding-left:40px">1</td>
										</tr>
										
										<tr>
											<th>Title</th>
											<td id="bkTitle"  style="padding-left:40px">title</td>
										</tr>
										
										<tr>
											<th>Author</th>
											<td id="bkAuthor"  style="padding-left:40px">title</td>
										</tr>
										
										<tr>
											<th>Description</th>
											<td id="bkDes"  style="padding-left:40px">title</td>
										</tr>
										
										<tr>
											<th>Status</th>
											<td id="bkStatus" style="font-weight:bold;padding-left:40px">title</td>
										</tr>
										
										<tr>
											<th>Images</th>
											<td id="bkImg"  style="padding-left:40px">
											</td>
										</tr>
									</table>
									<p style="margin-top:20px;">
										<a href="javascript:void(0)" onclick="window.history.back();" class="link-black text-sm"><i
											class="fas fa-link mr-1"></i> Back Book List</a>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.card-body -->
</div>
<!-- /.card -->

</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@include file="/WEB-INF/jsp/templates/admin/inc/footer.jsp"%>

<script>
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
	    	window.location = "/auth/login";
		}
	});
});

</script>
