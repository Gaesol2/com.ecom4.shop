<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희수네 쇼핑몰</title>
</head>
<body>
<c:if test="${msg!=null}">
		<script type="text/javascript">
			let msg = "${msg}";
			alert("정상처리: "+msg);
		</script>
	</c:if>
	
	<c:import url="./Top.jsp"/>
	<div id="contents">
		<c:choose>
			<c:when test="${id==1}">
				<c:import url="./SearchId.jsp"/>
			</c:when>
			<c:when test="${id==0}">
				<c:import url="./SearchPw.jsp"/>
			</c:when>
			<c:when test="${pw==1}">
				<c:import url="./UpdatePw.jsp"/>
			</c:when>
		</c:choose>
	</div>
	<c:import url="./Bottom.jsp"></c:import>
</body>
</html>