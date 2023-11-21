<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>회원탈퇴</title>


    <!-- Bootstrap core CSS -->
	<%@include file="/WEB-INF/views/comm/plugin2.jsp" %>

    <!-- Favicons -->


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>


    
  </head>
  <body>
    
	<%@include file="/WEB-INF/views/comm/header.jsp" %>

<div class="container">

  <div class="text-center">

    <div class="box box-primary">

      <div class="box-header with-border">
        <h3 class="box-title">회원 탈퇴 인증</h3><br>
      </div>

      <form role="form" id="delMemberForm" method="post" action="/member/delMember">
        <div class="box-body">

          <div class="form-group row">
            <label for="member_id" class="col-2">아이디</label>
            <div class="col-10">
              <input type="text" class="form-control" name="member_id" id="member_id" placeholder="아이디">
            </div>
          </div>

          <div class="form-group row">
            <label for="member_password" class="col-2">비밀번호</label>
            <div class="col-10">
              <input type="password" class="form-control" name="member_password" id="member_password" placeholder="비밀번호">
            </div>
          </div>

      <div class="box-footer">
        <button type="submit" class="btn btn-primary" id="btn_delete">탈퇴하기</button><br>
      </div>
    </form>

    </div>

  </div>

	<%@include file="/WEB-INF/views/comm/footer.jsp" %>

</div>

  <%@include file="/WEB-INF/views/comm/plugin.jsp" %>

  <script>

    let msg = "${msg}";
    if(msg != "") {
      alert(msg);
    }

    $("#btn_delete").click(function() {
      if($("#member_id").val() == "") {
        alert("아이디를 입력해 주세요.");
        $("#member_id").focus();
        return false;
      }else if($("#member_password").val() == "") {
        alert("비밀번호를 입력해 주세요.");
        $("#member_password").focus();
        return false;
      }
    })



  </script>

</body>

</html>