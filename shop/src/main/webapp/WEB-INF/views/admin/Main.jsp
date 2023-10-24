<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="/css/common.css" type="text/css">
<link rel="stylesheet" href="/css/main.css" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희수네 쇼핑몰</title>
</head>
<body>
	
	<c:import url="./Top.jsp"/>
	<div id="contents">
		<c:import url="./${contentsJsp}.jsp"/>
	</div>
	<c:import url="./Bottom.jsp"></c:import>
</body>
</html>