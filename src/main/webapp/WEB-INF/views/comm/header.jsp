<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
  <h5 class="my-0 mr-md-auto font-weight-normal"><a href="/">JS Mall</a></h5>
  <c:if test="${sessionScope.loginOn != null }">
    <div>
      <b>${sessionScope.loginOn.member_name }님</b>
      <b>${sessionScope.loginOn.member_email }</b>
      <b>최근 로그인시간: <fmt:formatDate value="${sessionScope.loginOn.member_lastlogin }" pattern="yyyy-MM-dd hh:mm:ss" /></b>
    </div>
  </c:if>
  <nav class="my-2 my-md-0 mr-md-3">
  
	<!-- 로그인 이전 표시 -->  
	<c:if test="${sessionScope.loginOn == null }">
    	<a class="p-2 text-dark" href="/member/login">Login</a>
    	<a class="p-2 text-dark" href="/member/join">Join</a>
    </c:if>
    
    <!-- 로그인 이후 표시 -->
    <c:if test="${sessionScope.loginOn != null }">
    	<a class="p-2 text-dark" href="/member/logout">Logout</a>
    	<a class="p-2 text-dark" href="/member/confirmModify">Modify</a>
    	<a class="p-2 text-dark" href="/member/confirmMypage">MyPage</a>
    </c:if>
        
    <a class="p-2 text-dark" href="/user/order/order_info">Order</a>
    <a class="p-2 text-dark" href="/user/cart/cart_list">Cart</a>
    <a class="p-2 text-dark" href="/admin/ad_login">[Admin]</a>
  </nav>
</div>
