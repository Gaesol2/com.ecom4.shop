<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<c:when test="${totCnt==0}">
			<tr>
				<td colspan="6">게시글이 없습니다.</td>
			</tr>
		</c:when>
		<c:when test="${totCnt>0}">
			<c:forEach var="notice" items="${noticeList}">
				<tr>
					
				</tr>
			</c:forEach>
		</c:when>
	</c:choose>
</table>
</body>
</html>