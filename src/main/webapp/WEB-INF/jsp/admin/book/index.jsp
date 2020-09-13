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
            <h1 class="title_book">Book list</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active title_book">Book list</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

        <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <div class="card">
            </div>
            <!-- /.card -->

            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Book >> <span class="title_book">Book list</span></h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="books_table" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Id</th>
                    <th width="40%">Title (Sort)</th>
                    <th width="30%">Author (Sort)</th>
                     <th width="2%">Status</th>
                    <th width="14%">Action</th>
                  </tr>
                  </thead>
                </table>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <div id="wait" style="display:none;width:106px;height:106px;border:1px solid black;position:absolute;top:50%;left:50%;padding:2px;"><img src='/resources/templates/demo_wait.gif' width="100" height="100" /><br>Loading..</div>
  <!-- /.content-wrapper -->
<%@include file="/WEB-INF/jsp/templates/admin/inc/footer.jsp" %>
<script src="/resources/templates/admin/js/index.js"></script>