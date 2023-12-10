<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>JS Mall  | category_list</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

  <%@include file="/WEB-INF/views/admin/include/plugin1.jsp" %>
  
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
  <div class="wrapper">

    <!-- Main Header -->
  <%@include file="/WEB-INF/views/admin/include/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
  <%@include file="/WEB-INF/views/admin/include/nav.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <section class="content-header">
        <h1>카테고리 목록</h1>
        <ol class="breadcrumb">
          <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
          <li class="active">Here</li>
        </ol>
      </section>

      <!-- Main content -->
      <section class="content container-fluid">
        <div class="row">
          <!-- row는 하위 div를 12개를 사용 할 수 있다. -->
          <!-- <div class="col-해상도-숫자(하위 div에서 사용하는 사이즈. 총합이 12가 되어야 한다.)"></div> -->
          <div class="col-md-12">
            <div class="box">
              <div class="row mb-3">
                <div class="col-md-8 themed-grid-col">
                  <div class="pb-3">
                    <div class="box-header with-border">
                      <h3 class="box-title">카테고리 목록</h3>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6 themed-grid-col">
                      <th scope="col-6" class="tac">1차 카테고리</th>
                      <tr>
                        <td>
                          <div>
                            <select multiple="" style="width: 100%; height: 380px;" id="firstCategory">
                              <c:forEach items="${firstCategoryList }" var="categoryVO">
                                <option value="${categoryVO.cg_code }">${categoryVO.cg_name }</option>
                              </c:forEach>
                            </select>
                          </div>
                        </td>
                      </tr>
                    </div>
                    <div class="col-md-6 themed-grid-col">
                      <th scope="col-6" class="tac">2차 카테고리</th>
                      <tr>
                        <td>
                          <div>
                            <select multiple="" style="width: 100%; height: 380px;" id="secondCategory" name="cg_code">

                            </select>
                          </div>
                        </td>
                      </tr>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 themed-grid-col">
                  <div class="box-header with-border">
                    <h3>카테고리 추가</h3>
                  </div>
                  <div>
                    <th scope="col-6" class="tac">1차 카테고리</th><br>
                    <tr>
                      <td>
                        <b>카테고리 이름</b>
                        <input type="text" id="f_category">
                        <button type="button" id="f_cte_add" class="btn btn-primary">추가</button>
                      </td>
                    </tr>
                  </div>
                  <br>
                  <div>
                    <th scope="col-6" class="tac">2차 카테고리</th><br>
                    <tr>
                      <td>
                        <div>
                          <b>1차 카테고리 선택</b>
                          <select style="width: 165px;" id="a_firstCategory">
                            <c:forEach items="${firstCategoryList }" var="categoryVO">
                              <option value="${categoryVO.cg_code }">${categoryVO.cg_name }</option>
                            </c:forEach>
                          </select>
                        </div>
                        <b>2차 카테고리 이름</b>
                        <input type="text" id="s_category">
                        <button type="button" id="s_cte_add" class="btn btn-primary">추가</button>
                      </td>
                    </tr>
                  </div>
                </div>
                <div class="col-md-4 themed-grid-col">
                  <div class="box-header with-border">
                      <h3>카테고리 제거</h3>
                  </div>
                  <div>
                      <th scope="col-6" class="tac">1차 카테고리</th><br>
                      <tr>
                          <td>
                              <b>1차 카테고리 선택</b>
                              <select style="width: 165px;" id="r_firstCategory">
                                  <c:forEach items="${firstCategoryList}" var="categoryVO">
                                      <option value="${categoryVO.cg_code}">${categoryVO.cg_name}</option>
                                  </c:forEach>
                              </select>
                          </td>
                      </tr>
                  </div>
                  <br>
                  <div>
                      <th scope="col-6" class="tac">2차 카테고리</th><br>
                      <tr>
                          <td>
                              <div>
                                  <b>2차 카테고리 선택</b>
                                  <select style="width: 165px;" id="r_secondCategory" name="cg_code">
  
                                  </select>
                              </div>
                              <br>
                              <button type="button" id="r_first_cte_remove" class="btn btn-danger">1차 카테고리 제거</button>
                              <button type="button" id="r_cte_remove" class="btn btn-danger">2차 카테고리 제거</button>
                          </td>
                      </tr>
                  </div>
                </div>
              </div>
            </div>
            <div>
              주의<br>
              1. 카테고리를 삭제 하시기전에 카테고리에 포함된 상품을 먼저 삭제해주세요<br>
              2. 1차 카테고리를 제거하시기 전에 1차카테고리에 포함된 2차카테고리를 먼저 제거해주세요
            </div>
          </div>
        </div>
      </section>
      <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
  <%@include file="/WEB-INF/views/admin/include/footer.jsp" %> 

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
      <!-- Create the tabs -->
      <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
        <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
        <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
      </ul>
      <!-- Tab panes -->
      <div class="tab-content">
        <!-- Home tab content -->
        <div class="tab-pane active" id="control-sidebar-home-tab">
          <h3 class="control-sidebar-heading">Recent Activity</h3>
          <ul class="control-sidebar-menu">
            <li>
              <a href="javascript:;">
                <i class="menu-icon fa fa-birthday-cake bg-red"></i>

                <div class="menu-info">
                  <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                  <p>Will be 23 on April 24th</p>
                </div>
              </a>
            </li>
          </ul>
          <!-- /.control-sidebar-menu -->

          <h3 class="control-sidebar-heading">Tasks Progress</h3>
          <ul class="control-sidebar-menu">
            <li>
              <a href="javascript:;">
                <h4 class="control-sidebar-subheading">
                  Custom Template Design
                  <span class="pull-right-container">
                      <span class="label label-danger pull-right">70%</span>
                    </span>
                </h4>

                <div class="progress progress-xxs">
                  <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                </div>
              </a>
            </li>
          </ul>
          <!-- /.control-sidebar-menu -->

        </div>
        <!-- /.tab-pane -->
        <!-- Stats tab content -->
        <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
        <!-- /.tab-pane -->
        <!-- Settings tab content -->
        <div class="tab-pane" id="control-sidebar-settings-tab">
          <form method="post">
            <h3 class="control-sidebar-heading">General Settings</h3>

            <div class="form-group">
              <label class="control-sidebar-subheading">
                Report panel usage
                <input type="checkbox" class="pull-right" checked>
              </label>

              <p>
                Some information about this general settings option
              </p>
            </div>
            <!-- /.form-group -->
          </form>
        </div>
        <!-- /.tab-pane -->
      </div>
    </aside>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
    immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
  </div>
  <!-- ./wrapper -->

  <!-- REQUIRED JS SCRIPTS -->
  <%@include file="/WEB-INF/views/admin/include/plugin2.jsp" %>
  <script src="/bower_components/ckeditor/ckeditor.js"></script>
  <script>
    $(document).ready(function() {

      // 1차 카테고리를 선택했을때
      $("#firstCategory").change(function() {

        let cg_parent_code = $(this).val();

        // 1차 카테고리 선택에 의한 2차 카테고리 정보를 가져오는 url
        let url = "/admin/category/secondCategory/" + cg_parent_code; //".json";
        
        // $.getJSON() : 스프링에 요청시 데이터를 json으로 받는 기능
        $.getJSON(url,function(secondCategoryList) {
          // console.log("2차 카테고리 코드" , secondCategoryList);

          // console.log("2차 카테고리 개수",secondCategoryList.length + 1);

          // 2차 카테고리 select태그참조
          let secondCategory = $("#secondCategory");
          let optionStr = "";

          // find("css선택자") : 태그명 , id속성이름 , class속성이름
          secondCategory.find("option").remove(); // 2차 카테고리의 option제거

          for(let i=0; i<secondCategoryList.length; i++){
            optionStr += "<option value='" + secondCategoryList[i].cg_code + "'>" + secondCategoryList[i].cg_name + "</option>";
          }
          // console.log(optionStr);
          secondCategory.append(optionStr); // 2차 카테고리 <option>태그들이 추가. 

        });
      });

      // 1차 카테고리 추가
      $("#f_cte_add").on("click", function() {
        let cg_name = $("#f_category").val();

        // Ajax 요청
        $.ajax({
          type: "POST",
          url: "/admin/category/addFirstCategory",
          data: { cg_name : cg_name },
          success: function(response) {
            alert("1차 카테고리가 추가되었습니다.");
            location.reload();

          },
          error: function(error) {
            alert("오류가 발생했습니다. 다시 시도해주세요.");
            console.error(error);
          }
        });
      });

      // 2차 카테고리 추가
      $("#s_cte_add").on("click", function() {
        let cg_name = $("#s_category").val();
        let cg_parent_code = $("#a_firstCategory").val();

        // Ajax 요청
        $.ajax({
          type: "POST",
          url: "/admin/category/addSecondCategory",
          data: { cg_name : cg_name , cg_parent_code : cg_parent_code},
          success: function(response) {
            alert("2차 카테고리가 추가되었습니다.");
            location.reload();

          },
          error: function(error) {
            alert("오류가 발생했습니다. 다시 시도해주세요.");
            console.error(error);
          }
        });
      });

      // 카테고리 제거
      // 1차 카테고리를 선택했을때
      $("#r_firstCategory").change(function() {

        let cg_parent_code = $(this).val();

        // 1차 카테고리 선택에 의한 2차 카테고리 정보를 가져오는 url
        let url = "/admin/category/secondCategory/" + cg_parent_code; //".json";

        // $.getJSON() : 스프링에 요청시 데이터를 json으로 받는 기능
        $.getJSON(url,function(secondCategoryList) {

          // 2차 카테고리 select태그참조
          let secondCategory = $("#r_secondCategory");
          let optionStr = "";

          // find("css선택자") : 태그명 , id속성이름 , class속성이름
          secondCategory.find("option").remove(); // 2차 카테고리의 option제거

          for(let i=0; i<secondCategoryList.length; i++){
            optionStr += "<option value='" + secondCategoryList[i].cg_code + "'>" + secondCategoryList[i].cg_name + "</option>";
          }
          // console.log(optionStr);
          secondCategory.append(optionStr); // 2차 카테고리 <option>태그들이 추가. 
        });
      });

      // 2차 카테고리 제거
      $("#r_cte_remove").on("click",function() {
        let cg_code = $("#r_secondCategory").val(); 

        if (!confirm($("#r_firstCategory option:selected").text() + "의 " + $("#r_secondCategory option:selected").text() + "를(을) 정말 삭제하시겠습니까?")) return;
        
        $.ajax({
          type: "POST",
          url: "/admin/category/removeCategory",
          data: { cg_code : cg_code },
          success: function(response) {
            alert("선택된 카테고리가 제거되었습니다.");
            location.reload();

          },
          error: function(error) {
            alert("오류가 발생했습니다. 다시 시도해주세요.");
            console.error(error);
          }
        });
      });

      // 1차 카테고리 제거
      $("#r_first_cte_remove").on("click",function() {
        let cg_code = $("#r_firstCategory").val(); 

        if (!confirm($("#r_firstCategory option:selected").text() + "를(을) 정말 삭제하시겠습니까?")) return;
        
        $.ajax({
          type: "POST",
          url: "/admin/category/removeCategory",
          data: { cg_code : cg_code },
          success: function(response) {
            alert("선택된 카테고리가 제거되었습니다.");
            location.reload();

          },
          error: function(error) {
            alert("오류가 발생했습니다. 다시 시도해주세요.");
            console.error(error);
          }
        });
      });



    });
  </script>
</body>
</html>