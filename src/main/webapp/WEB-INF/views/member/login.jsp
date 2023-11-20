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
    <title>JS Mall 로그인</title>

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
      <h3 class="box-title">로그인</h3>
      </div>
      
      <form role="form" id="loginForm" method="post" action="/member/login">
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
      <button type="button" id="btnLogin" class="btn btn-primary">로그인</button>
      <button type="button" id="btnJoin" class="btn btn-primary">회원가입</button>
      <button type="button" id="find_id" class="btn btn-primary">아이디찾기</button>
      <button type="button" id="find_pw" class="btn btn-primary">비밀번호찾기</button>
      </div>
      </form>
      </div>
  </div>
  
 <%@include file="/WEB-INF/views/comm/footer.jsp" %> 

  <%@include file="/WEB-INF/views/comm/plugin.jsp" %>

<script>
  // jquery.slim.min.js 파일에 jquery 명령어가 정의되어 있음
  // 별칭 : $ -> jQuery()함수
  // read() 이벤트 메서드 : 브라우저가 html태그를 모두 읽고난 후에 동작하는 이벤트 특징.
  // 자바스크립트 이벤트 등록 :  https://www.w3schools.com/js/js_htmldom_eventlistener.asp
  let msg = "${msg}";
  // 아이디 비밀번호 확인
  if(msg != "") {
      alert(msg);
  }

  $(document).ready(() => {

    let loginForm = $("#loginForm");// form 태그 참조 <form role="form" id="loginForm" method="post" action="">
    
    // 회원가입 버튼
    $("#btnJoin").click(() => {
      location.href = "/member/join";
    });

    // 로그인 버튼
    $("#btnLogin").click(() => {

      // 아이디 입력검사
      if($("#member_id").val() =="") {
        alert("아이디를 입력해주세요");
        $("#member_id").focus();
        return;
      }

      // 비밀번호를 입력검사
      if($("#member_password").val() =="") {
        alert("비밀번호를 입력해주세요");
        $("#member_password").focus();
        return;
      }

      
      // 폼 전송작업
      loginForm.submit();
    });
    
    // 아이디 찾기
    $("#find_id").click(()=>{

      location.href = "/member/find_id";
    });
    find_pw
    // 비밀번호 찾기
    $("#find_pw").click(()=>{

    location.href = "/member/find_pw";
    });
  });

  </script>
  </body>
</html>
    