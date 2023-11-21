<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>내정보</title>


    <!-- Bootstrap core CSS -->
	<%@ include file="/WEB-INF/views/comm/plugin2.jsp" %>

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
        <h3 class="box-title">내정보</h3><br>
      </div>

      <form role="form" id="" method="" action="" >
        <div class="box-body">
          <div class="form-group row">
            <label for="member_id" class="col-2">아이디</label>
            <div class="col-10">
              <input type="text" class="form-control" name="member_id" id="member_id" value="${memberVO.member_id }" readonly>
            </div>
          </div>

          <div class="form-group row">
            <label for="member_name" class="col-2">이름</label>
            <div class="col-10">
              <input type="text" class="form-control" name="member_name" id="member_name" value="${memberVO.member_name }" readonly>
            </div>
          </div>

          <div class="form-group row">
            <label for="member_email" class="col-2">이메일</label>
            <div class="col-10">
              <input type="email" class="form-control" name="member_email" id="member_email" value="${memberVO.member_email }" readonly >
            </div>
          </div>

          <div class="form-group row">
            <label for="sample2_postcode" class="col-2">우편번호</label>
            <div class="col-8">
              <input type="text" class="form-control" name="member_zipcode" id="sample2_postcode" value="${memberVO.member_zipcode }" readonly>
            </div>
          </div>

          <div class="form-group row">
            <label for="sample2_address" class="col-2">주소</label>
            <div class="col-10">
              <input type="text" class="form-control" name="member_addr" id="sample2_address" value="${memberVO.member_addr }" readonly>
            </div>
          </div>

          <div class="form-group row">
            <label for="sample2_detailAddress" class="col-2">상세주소</label>
            <div class="col-10">
              <input type="text" class="form-control" name="member_deaddr" id="sample2_detailAddress" value="${memberVO.member_deaddr }" readonly>
              <input type="hidden" id="sample2_extraAddress" placeholder="참고항목">
            </div>
          </div>

          <div class="form-group row">
            <label for="member_phone" class="col-2">전화번호</label>
            <div class="col-10">
              <input type="text" class="form-control" name="member_phone" id="member_phone" value="${memberVO.member_phone }" readonly>
            </div>
          </div>
        </div>

      <div class="box-footer">
        <button type="button" class="btn btn-primary" id="btn_home" style="margin-right: 30px;">확인</button>
        <button type="button" class="btn btn-primary" id="btn_modify" style="margin-right: 30px;">회원수정</button>
        <button type="button" class="btn btn-danger" id="btn_Delete">회원탈퇴</button>
      </div>
    </form>

    </div>

  </div>

	<%@include file="/WEB-INF/views/comm/footer.jsp" %>

</div>
  <%@include file="/WEB-INF/views/comm/plugin.jsp" %>

  <script>
    // jquery.slim.min.js 파일에 jquery명령어가 정의되어 있음.
    // 별칭: $ -> JQuery() 함수
    // ready() 이벤트 메소드 : 브라우저가 html태그를 모두 읽고 난 후에 동작하는 이벤트 특징.
    // 자바 스크립트 이벤트 등록 : https://www.w3schools.com/js/js_htmldom_eventlistener.asp
    $(document).ready(function() {
      // document.getElementById("idCheck");

      $("#btn_home").click(()=>{
        location.href = "/";
      });

      $("#btn_modify").click(()=>{
        location.href = "/member/modify";
      });

      $("#btn_Delete").click(function() {
        location.href = "/member/delMember";
      });
        


    });

  </script>

</body>

</html>
    