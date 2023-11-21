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
    <title>회원 정보 인증</title>

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
      <h3 class="box-title">마이페이지 인증확인</h3>
      </div>
      
      <form role="form" id="confirmMypageForm" method="post" action="/member/confirmMypage">
      <div class="box-body">
        <div class="form-group row">
          <label for="member_id" class="col-2">아이디</label>
          <div class="col-10">
            <input type="text" class="form-control" name="member_id" id="member_id" placeholder="아이디 입력">
          </div>
        </div>
        <div class="form-group row">
          <label for="member_password" class="col-2">비밀번호</label>
          <div class="col-10">
            <input type="password" class="form-control" name="member_password" id="member_password" placeholder="비밀번호 입력">
          </div>
        </div>
      </div>
      
      <div class="box-footer">
      <button type="submit" id="btn_mypage" class="btn btn-primary">확인</button>
      </div>
      </form>
      </div>
  </div>
  
 <%@include file="/WEB-INF/views/comm/footer.jsp" %> 

  <%@include file="/WEB-INF/views/comm/plugin.jsp" %>

  <script>

    let msg = "${msg}";
    let sessionMember_id = "${loginOn.getMember_id()}"

    if(msg != "") {
      alert(msg);
    }

    $("#btn_mypage").click(() => {
      if($("#member_id").val() == "") {
        alert("아이디를 입력해 주세요.");
        $("#member_id").focus();
        return false;
      }else if($("#member_password").val() == "") {
        alert("비밀번호를 입력해 주세요.");
        $("#member_password").focus();
        return false;
      }else if($("#member_id").val() != sessionMember_id) {
        alert("아이디를 다시 확인 해 주세요.");
        $("#member_id").focus();
        return false;
      }
    })



  </script>
  </body>
</html>
    