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
  <title>JS Mall 상품등록  | Admin</title>
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
      <h1>상품 등록</h1>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">
      <div class="row">
        <div class="col-md-12">
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title mt-5">Product</h3>
            </div>
            <form role="form" method="post" action="/admin/product/pro_insert" enctype="multipart/form-data">
              <div class="box-body">
                <div class="form-group row">
                  <label class="col-sm-2 col-form-label">1차 카테고리</label> 
                  <div class="col-sm-4">
                    <select class="form-control" id="firstCategory">
                      <option>1차 카테고리 선택</option>
                      <c:forEach items="${firstCategoryList }" var="categoryVO">
                        <option value="${categoryVO.cg_code }">${categoryVO.cg_name }</option>
                      </c:forEach>
                    </select>
                  </div>
                  <label class="col-sm-2 col-form-label">2차 카테고리</label> 
                  <div class="col-sm-4">
                    <select class="form-control" id="secondCategory" name="cg_code">
                      <option>2차 카테고리 선택</option>
                    </select>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="pro_name" class="col-sm-2 col-form-label">상품명</label> 
                  <div class="col-sm-4">
                    <input type="text" class="form-control" name="pro_name" id="pro_name" placeholder="상품명">
                  </div>
                  <label for="pro_price" class="col-sm-2 col-form-label">상품가격</label> 
                  <div class="col-sm-4">
                    <input type="text" class="form-control" name="pro_price" id="pro_price" placeholder="상품가격">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="pro_discount" class="col-sm-2 col-form-label">할인율</label> 
                  <div class="col-sm-4">
                    <input type="text" class="form-control" name="pro_discount" id="pro_discount" placeholder="할인율">
                  </div>
                  <label for="pro_publisher" class="col-sm-2 col-form-label">제조사</label> 
                  <div class="col-sm-4">
                    <input type="text" class="form-control" name="pro_publisher" id="pro_publisher" placeholder="제조사">
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-2 col-form-label">상품이미지</label> 
                  <div class="col-sm-4">
                    <input type="file" class="form-control" name="uploadFile" id="uploadFile">
                  </div>
                  <label class="col-sm-2 col-form-label">미리보기 이미지</label> 
                  <div class="col-sm-4">
                    <img id="img_preview" style="width: 200px; height:200px;" />
                  </div>
                </div>
                <div class="form-group row">
                  <label for="pro_content" class="col-sm-2 col-form-label">상품설명</label> 
                  <div class="col-sm-10">
                    <textarea class="form-control" id="pro_content" name="pro_content" rows="3"></textarea>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="pro_amount" class="col-sm-2 col-form-label">수량</label> 
                  <div class="col-sm-4">
                    <input type="text" class="form-control" name="pro_amount" id="pro_amount" placeholder="작성자 입력...">
                  </div>
                  <label for="pro_buy" class="col-sm-2 col-form-label">판매여부</label> 
                  <div class="col-sm-4">
                    <select class="form-control" id="pro_buy" name="pro_buy">
                      <option value="Y">판매가능</option>
                      <option value="N">판매불가능</option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="box-footer">
                <div class="form-group">
                  <ul class="uploadedlist"></ul>
                </div>
                <div class="text-center">
                  <button type="submit" class="btn btn-primary">상품등록</button>
                  <button type="button" id="btnCancel" class="btn btn-primary">취소</button>
                </div>
              </div>
            </form>
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
  $(document).ready(() =>{
    // ckeditor 환경설정. 자바스크립트 Ojbect문법
    var ckeditor_config = {
          resize_enabled : false,
          enterMode : CKEDITOR.ENTER_BR,
          shiftEnterMode : CKEDITOR.ENTER_P,
          toolbarCanCollapse : true,
          removePlugins : "elementspath", 
          //업로드 탭기능추가 속성. CKEditor에서 파일업로드해서 서버로 전송클릭하면 , 이 주소가 동작된다.
          filebrowserUploadUrl: '/admin/product/imageUpload' 
    }
	
	 
    CKEDITOR.replace("pro_content",ckeditor_config);

    // 1차 카테고리를 선택했을때
    $("#firstCategory").change(function() {
      // $(this) : option태그중 선택한 option 태그를 가리킴.
      let cg_parent_code = $(this).val();

      // console.log("1차 카테고리 코드" , cg_code);

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

    // 파일 첨부시 이미지 미리보비
    // 파일첨부에 따른 이벤트관련정보를 e라는 매개변수로 통하여, 참조가됨.
    $("#uploadFile").change(function(e) {
      let file = e.target.files[0];
      let reader = new FileReader(); // 첨부된 파일을 이동하여 , File 객체를 생성하는 용도
      reader.readAsDataURL(file);

      reader.onload = function(e){
          // event.target.result : reader객체의 이미지 파일 정보
        $("#img_preview").attr("src", e.target.result);
      };
          
    });

    $("#btnCancel").click(()=>{
      if(!confirm("취소 하시겠습니까?")) return;
      history.back();
    });
  });
</script>
</body>
</html>