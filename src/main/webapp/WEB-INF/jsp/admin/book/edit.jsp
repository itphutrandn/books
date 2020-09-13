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
  <div id="wait" style="display:none;width:106px;height:106px;border:1px solid black;position:absolute;top:50%;left:50%;padding:2px;"><img src='/resources/templates/demo_wait.gif' width="100" height="100" /><br>Loading..</div>
<%@include file="/WEB-INF/jsp/templates/admin/inc/footer.jsp" %>
