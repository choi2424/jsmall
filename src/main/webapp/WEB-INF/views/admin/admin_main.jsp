<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>JS Mall  | Admin</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="/css/stylesheet.css" />
  <%@include file="/WEB-INF/views/admin/include/plugin1.jsp" %>
  <style>
    th{text-align: center;}
  </style>
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

    <!-- Main content -->
    <section class="sidx_head01">
      <div id="main_wrap">
        <section>
          <h2>전체 주문통계<a href="/admin/order/order_list" class="btn_small">주문목록 바로가기</a></h2>
          <table>
            <thead>
              <tr>
                <th scope="col">총 주문건수</th>
                <th scope="col">총 주문액</th>
                <th scope="col">이달 주문건수</th>
                <th scope="col">이달 주문액</th>
                <th scope="col">저번달 주문건수</th>
                <th scope="col">저번달 주문액</th>
              </tr>
            </thead>
            <tbody style="text-align: center;">
              <tr>
                <td>${adCount.total_orders}</td>
                <td>${adCount.total_order_amount}원</td>
                <td>${adCount.this_month_orders}</td>
                <td>${adCount.this_month_order_amount}원</td>
                <td>${adCount.last_month_orders}</td>
                <td>${adCount.last_month_order_amount}원</td>
              </tr>
            </tbody>
          </table>
        </section>
      
        <section class="sidx_head01">
          <h2>최근 주문내역<a href="/admin/order/order_list" class="btn_small">주문목록 바로가기</a></h2>
          <table>
            <thead>
              <tr>
                <th scope="col">주문번호</th>
                <th scope="col">주문자아이디</th>
                <th scope="col">수령자명</th>
                <th scope="col">전화번호</th>
                <th scope="col">결제방법</th>
                <th scope="col">총주문액</th>
                <th scope="col">주문일시</th>
              </tr>
            </thead>
            <tbody style="text-align: center;">
              <c:forEach items="${adOrderDTO }" var="adOrderDTO">
                <tr>
                  <td>${adOrderDTO.ord_code}</td>
                  <td>${adOrderDTO.member_id}</td>
                  <td>${adOrderDTO.ord_name}</td>
                  <td>${adOrderDTO.ord_tel}</td>
                  <td>${adOrderDTO.pay_method}</td>
                  <td>${adOrderDTO.ord_price}원</td>
                  <td><fmt:formatDate value='${adOrderDTO.ord_regdate}' pattern='yyyy/MM/dd hh:mm:ss' /></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </section>
      
        <section class="sidx_head01">
          <h2>최근 회원가입<a href="" class="btn_small">회원관리 바로가기</a></h2>
          <table>
            <thead>
              <tr>
                <th scope="col">이름</th>
                <th scope="col">아이디</th>
                <th scope="col">전화번호</th>
                <th scope="col">이메일</th>
                <th scope="col">가입일시</th>
              </tr>
            </thead>
            <tbody style="text-align: center;">
              <c:forEach items="${adMember }" var="adMember">
                <tr>
                  <td>${adMember.member_name}</td>
                  <td>${adMember.member_id}</td>
                  <td>${adMember.member_phone}</td>
                  <td>${adMember.member_email}</td>
                  <td><fmt:formatDate value='${adMember.member_datesub}' pattern='yyyy/MM/dd hh:mm:ss' /></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </section>
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
</body>
</html>