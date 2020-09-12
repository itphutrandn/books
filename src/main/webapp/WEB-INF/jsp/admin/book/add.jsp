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
            <h1>Book Add</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Book Add</li>
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
              <h3 class="card-title" id="errorMsg">Please enter the information below</h3>

              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                  <i class="fas fa-minus"></i></button>
              </div>
            </div>
            <div class="card-body">
              <div class="form-group">
                <label for="inputName">Book Name</label>
                <input type="text"  class="form-control" id="bkName">
              </div>
              <div class="form-group">
                <label for="inputDescription">Book Description</label>
                <textarea id="bkDes" class="form-control" rows="4"></textarea>
              </div>
              <div class="form-group">
                <label for="inputStatus">Status</label>
                <select class="form-control custom-select" id="bkStatus">
                  <option selected value="">Select status</option>
                  <option value="1">Enable</option>
                  <option value="0">Disable</option>
                </select>
              </div>
              <div class="form-group">
                <label for="inputClientCompany">Image</label>
                <input type="file" id="bkImg" class="form-control">
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
          <input type="submit" value="Create new Book" class="btn btn-success float-right" onclick="return addBook()">
        </div>
      </div>
    </section>
    <!-- /.content -->
  </div>
  
  <script>
  
	function addBook() {
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
		</script>
<%@include file="/WEB-INF/jsp/templates/admin/inc/footer.jsp" %>
