<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/member.css" type="text/css">
</head>
<body>
<div>
총 회원 수 : ${memberTot }
</div>
<c:choose>
	<c:when test="${memberTot == 0}">
		회원이 없습니다.
	</c:when>
	<c:otherwise>
		<table id="member">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>연락처</th>
				<th class="col">주소</th>
			</tr>
			<c:forEach var="member" items="${members}">
					<tr>
						<td>${member.mem_id}</td>
						<td>${member.m_name}</td>
						<td>${member.m_phone}</td>
						<td>${member.address}</td>
					</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</body>
</html>