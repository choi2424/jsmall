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
    <title>JS Mall 비밀번호 찾기</title>

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
      <h3 class="box-title">비밀번호 찾기</h3>
      </div>
      
      <form role="form" id="find_pwForm" method="post" action="/member/find_pw">
      <div class="box-body">
        <div class="form-group row">
          <label for="member_name" class="col-2">아이디</label>
          <div class="col-10">
            <input type="text" class="form-control" name="member_id" id="member_id" placeholder="아이디 입력">
          </div>
        </div>
        <div class="form-group row">
          <label for="member_email" class="col-2">이메일</label>
          <div class="col-8">
            <input type="text" class="form-control" name="member_email" id="member_email" placeholder="이메일 입력">
          </div>
          <div class="col-2">
            <button type="button" class="btn btn-outline-info" id="mailAuth">메일인증</button>
          </div>
        </div>
        <div class="form-group row">
          <label for="authCode" class="col-2">메일인증</label>
          <div class="col-8">
            <input type="text" class="form-control" name="authCode" id="authCode" placeholder="인증코드 입력">
          </div>
          <div class="col-2">
            <button type="button" class="btn btn-outline-info" id="btnConfirmAuth">인증확인</button>
          </div>
        </div>
      </div>
      
      <div class="box-footer">
      <button type="button" id="btn_find_pw" class="btn btn-primary">확인</button>
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

    let find_pwForm = $("#find_pwForm");// form 태그 참조 <form role="form" id="loginForm" method="post" action="">
    let isConfirmAuth = false; // 이메일 중복체크 사용유무 확인

    // 이메일 인증코드 발송
    $("#mailAuth").click(() => {
      if($("#member_email").val() == ""){
        alert("이메일을 입력하세요.");
        $("#member_email").focus();
        return;
      }

      $.ajax({
      url : "/email/authCode",
      type : "get" ,
      dataType : "text", // 스프링에서 보내는 데이터의 타입.   'success'
      data : {receiverMail: $("#member_email").val()},
      success : (result) => {
        if(result == 'success') {
          alert("인증메일이 발송되었습니다. 메일 확인바랍니다.");
        }
      }
      });
    });

    $("#btnConfirmAuth").click(() => {

      if($("#authCode").val() =="") {
        alert("인증코드를 입력해주세요.");
        $("#authCode").focus();
        return;
      }

      // 이메일인증확인 요청
      $.ajax({
        url : "/email/confirmAuthCode",
        type : "get",
        dataType : "text",
        data : {authCode : $("#authCode").val()} ,
        success : (result) => {
          if(result == "success") {
            alert("인증성공");
            isConfirmAuth = true;
          }else if(result == "fail"){
            alert("인증코드 불일치");
            isConfirmAuth = false;
          }else if(result == "request"){
            alert("인증코드 유효시간 초과");
            $("#authCode").val("");
            isConfirmAuth = false;
          }
        }
      });
    });
    
    // 확인버튼 클릭
    $("#btn_find_pw").click(()=>{

      // 이름 입력검사
      if($("#member_id").val() =="") {
        alert("아이디를 입력해주세요");
        $("#member_id").focus();
        return;
      }

      // 이메일 입력검사
      if($("#member_email").val() =="") {
        alert("이메일를 입력해주세요");
        $("#member_email").focus();
        return;
      }

      if(!isConfirmAuth){
        alert("이메일 인증확인 바랍니다");
        return;
      }
      // 폼 전송작업
      find_pwForm.submit();
    });
  });

  </script>
  </body>
</html>
    