<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 style="text-align:center">자유게시판 (총 글 수 : ${totCnt})</h3>
<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>조회수</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>vdate</th>
	</tr>
	<c:choose>
		<c:when test="${totCnt == 0}">
			<tr>
				<td colspan="6">게시글이 없습니다.</td>
			</tr>
		</c:when>
		<c:when test="${totCnt!= 0}">
			<c:forEach var="notice" items="${noticeList}">
				<tr>
					<td>${notice.noti_no }</td>
					<td><a href="/noticeDetail?noti_no=${notice.noti_no}">${notice.subject }</a></td>
					<td>${notice.readcount }</td>
					<td>${notice.writer }</td>
					<td>${notice.regdate }</td>
					<td>${notice.vdate }</td>
				</tr>
			</c:forEach>
		</c:when>
	</c:choose>
	<input type="button" value="공지 등록" onclick="location.href='noticeGenerate'">
</table>
</body>
</html>