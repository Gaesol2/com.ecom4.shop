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
<h3 style="text-align:center">공지사항 (총 글 수 : ${totCnt})</h3>
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
	<tfoot>
		<tr>
			<td colspan="6" style="text-align:center; border: 1px solid #ffffff;">
				<c:if test = "${pageDto.startPg > pBlock}">
					<a href="notice?curPage=${pageDto.startPg-1}&curBlock=${pageDto.curBlock-1}">[이전]</a>
				</c:if>
				<c:forEach begin="${pageDto.startPg}" end="${pageDto.endPg}" var="p" step="1">
					<c:choose>
						<c:when test="${p==pageDto.curPage}">
							<a href="notice?curPage=${p}&curBlock=${pageDto.curBlock}"><span style="font-weight:bold;"><c:out value="${p}"/></span></a>&nbsp;
						</c:when>
						<c:when test="${p!=pageDto.curPage}">
							<a href="notice?curPage=${p}&curBlock=${pageDto.curBlock}"><span><c:out value="${p}"/></span></a>&nbsp;
						</c:when>
					</c:choose>
				</c:forEach>
				<c:if test = "${pageDto.endPg<pageDto.pgCnt}">
					<a href="notice?curPage=${pageDto.startPg+pBlock}&curBlock=${pageDto.curBlock+1}">[다음]</a>
				</c:if>
			</td>
		</tr>
	</tfoot>
	<input type="button" value="공지 등록" onclick="location.href='noticeGenerate'">
</table>
</body>
</html>