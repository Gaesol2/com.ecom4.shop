<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/asside.css" type="text/css">
<link rel="stylesheet" href="/css/common.css" type="text/css">
<script src="/script/commonScript.js" type="text/javascript"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희수네 쇼핑몰</title>
<style></style>
</head>
<body>
	<div id="contents">
		<div id="login_wrap">
		<h3 style="color:#ffaea5;">로그인</h3>
		<form action="loginProc" method="post" name="topForm" id="topForm">
			<ul id="login_input">
				<li><input type="text" name="mem_id" class="chkt" title="아이디"></li>
				<li><input type="password" name="m_passwd" class="chkt" title="비밀번호"></li>
			</ul>
			<button class="login_btn" id="submitTop">로그인</button>
			<div id="join_search">
				<button onclick="location.href='/join'">회원가입</button>
				<button onclick="location.href='/searchId'">아이디/비밀번호 찾기</button>				
			</div>		
		</form>
		</div>
		<div class="clear"></div>
		
	</div>
</body>
</html>