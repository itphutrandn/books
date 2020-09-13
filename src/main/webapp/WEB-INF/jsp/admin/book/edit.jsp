<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@include file="/WEB-INF/jsp/templates/admin/inc/header.jsp" %>
  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
   	<%@include file="/WEB-INF/jsp/templates/admin/inc/left-bar.jsp" %>
  </aside>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Book Edit</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Book Edit</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title" id="errorMsg">Please enter the information below, If you want to edit.</h3>

              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                  <i class="fas fa-minus"></i></button>
              </div>
            </div>
            <div class="card-body">
             <div class="form-group">
                <label for="inputName">Book Id</label>
                <input type="text" readonly="readonly"  class="form-control" id="bkId">
              </div>
              <div class="form-group">
                <label for="inputName">Book Name</label>
                <input type="text"  class="form-control" id="bkName">
              </div>
              <div class="form-group">
                <label for="inputDescription">Book Description</label>
                <textarea id="bkDes" class="form-control" rows="4"></textarea>
              </div>
              <div class="form-group">
                <label for="inputClientCompany">Image</label>
                <input type="file" id="bkImg" class="form-control">
                <div id="bkImgDes" style="margin-top:10px;"></div>
              </div>
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->
        </div>
      </div>
      <div class="row">
        <div class="col-12">
          <a  href="javascript:void(0)" onclick="window.history.back();" class="btn btn-secondary">Cancel</a>
          <input type="submit" value="Update Book" class="btn btn-success float-right" onclick="return editBook()">
        </div>
      </div>
    </section>
    <!-- /.content -->
  </div>
<%@include file="/WEB-INF/jsp/templates/admin/inc/footer.jsp" %>
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
		    	window.location = "/auth/login";
			}
		});
	});
  	
	function editBook() {
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
	     	 	if(result.code == '200') {
	     	 		alert('Update Book Successfully!');
	     	 		window.location = "/admincp/books/index";
	     	 		return;
	     	 	} 
	      	},
	      	error: function(xhr, status, error) {
		    	$("#errorMsg").text(JSON.parse(xhr.responseText).text);
			}
		});
	}
</script>
