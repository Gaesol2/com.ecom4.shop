<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="/css/header.css" type="text/css">
<link rel="stylesheet" href="/css/common.css" type="text/css">
<link rel="stylesheet" href="/css/asside.css" type="text/css">
<link rel="stylesheet" href="/css/main.css" type="text/css">
<script src="/jquery/jquery-3.7.0.min.js"></script>
<script src="/script/commonScript.js" type="text/javascript"></script>
<script src="/script/productMgt.js" type="text/javascript"></script>
<script src="/script/zipCheck.js" type="text/javascript"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<div id="top">
	<header>
		<a href="/admin/"><img class="logo" src="/cusImage/logo.png"></a>
		<nav id="top_menu">
			<table class="top1">
				<tr>
					<th><a href="/admin/">HOME</a></th>
					<c:choose>
						<c:when test="${ssKey!=null and ssKey.m_role=='admin' }">
							<c:choose>
								<c:when test="${ssKey!=null}">
									<th><a href="/logoutProc">LOGOUT</a></th>
									<th><a href="/admin/info">INFO</a></th>
								</c:when>
							</c:choose>							
							<th><a href="/notice">NOTICE</a></th>
						</c:when>
					</c:choose>
				</tr>
			</table>
		</nav>	
		<div class="line"></div>
		<nav id="main_menu">
			<ul>
				<li><a href="/freeBList">자유 게시판 관리</a></li>
				<li><a href="/productMgt">상품관리</a></li>
				<li><a href="/orderMgt">주문 관리</a></li>
				<li><a href="/memberMgt">회원 관리</a></li>
				<li><a href="">회원 전용 게시판</a></li>
			</ul>
		</nav>
	</header>
	<div class="clear"></div>
	<div class="blank"></div>
	<aside>
		<article id="login_box">
		<c:choose>
			<c:when test="${ssKey!=null}">
				<h3>회원 정보</h3>
				<div id="logout_button">
				<form action="/logoutProc" method="post" name="topForm">
					<ul id="logout_input">
						<li>${ssKey.m_name }님</li>
					</ul>
					<button class="logout_btn" onclick="location.href='/logoutProc'">로그아웃</button>
					<div class="clear"></div>				
					<a href='/info'>마이페이지</a>
				</form>
				</div>
			</c:when>
			<c:when test="${ssKey==null}">
				<h3>로그인</h3>
				<div id="login_button">
				<form action="loginProc" method="post" name="topForm" id="topForm">
					<ul id="login_input">
						<li><input type="text" name="mem_id" class="chkt" title="아이디"></li>
						<li><input type="password" name="m_passwd" class="chkt" title="비밀번호"></li>
					</ul>
					<button class="login_btn" id="submitTop">로그인</button>
				</form>
				</div>
				<div class="clear"></div>
			</c:when>
		</c:choose>

		</article>
	</aside>
</div>