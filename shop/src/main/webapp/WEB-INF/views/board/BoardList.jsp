<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>

<link rel="stylesheet" href="css/list.css" type="text/css">
<script src="jquery/jquery-3.7.0.min.js"></script>
<script src="script/list.js"></script>

</head>
<body>
<table>
	<thead>
		<tr><th colspan="6">게시글 목록 (전체 게시글 수 : ${totalCnt})</th></tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>최종수정일</th>
			<th>조회수</th>
			<th>아이피</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test = "${fn:length(articles)==0}">
				<tr>
					<td colspan="6">게시글이 없습니다.</td>
				</tr>
			</c:when>
			<c:when test = "${fn:length(articles)>0}">
				<c:forEach var = "article" items = "${articles}" varStatus="i">
					<tr>
						<td class="col1">${article.rn}</td>
						<td class="col2">
							<c:choose>
								<c:when test="${article.blevel==0}">
									<img src="images/level.gif" width="5px;">
								</c:when>
								<c:when test="${article.blevel>0 }">
									<img src="images/level.gif" width="${article.blevel*10}px;" style="height:15px;">
									<img src="images/re.gif">
								</c:when>								
							</c:choose>
							<a class="content">
								${article.subject}
								<input type = "hidden" name="no" value="${article.bno}">
							</a>
						</td>
						<td class="col3">${article.writer}</td>
						<td class="col4">${article.regdate}</td>
						<td class="col5">
							${article.readcount}
							<c:if test="${article.readcount>10}">
								<img src="images/hot.gif" style="width:30px; height:15px;">
							</c:if>
						</td>
						<td class="col6">${article.ip}</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6" style="text-align:center;">
				<c:if test = "${pageDto.startPg > pBlock}">
					<a href="freeBList?curPage=${pageDto.startPg-1}&curBlock=${pageDto.curBlock-1}">[이전]</a>
				</c:if>
				<c:forEach begin="${pageDto.startPg}" end="${pageDto.endPg}" var="p" step="1">
					<c:choose>
						<c:when test="${p==pageDto.curPage}">
							<a href="freeBList?curPage=${p}&curBlock=${pageDto.curBlock}"><span style="font-weight:bold;"><c:out value="${p}"/></span></a>&nbsp;
						</c:when>
						<c:when test="${p!=pageDto.curPage}">
							<a href="freeBList?curPage=${p}&curBlock=${pageDto.curBlock}"><span><c:out value="${p}"/></span></a>&nbsp;
						</c:when>
					</c:choose>
				</c:forEach>
				<c:if test = "${pageDto.endPg<pageDto.pgCnt}">
					<a href="freeBList?curPage=${pageDto.startPg+pBlock}&curBlock=${pageDto.curBlock+1}">[다음]</a>
				</c:if>
			</td>
		</tr>
		<tr><td><a href="writeForm">글쓰기</a></td></tr>
	</tfoot>
</table>
<form action="" method="post" name="content">
	<input type="hidden" name="bno" value="${article.bno}">
	<input type="hidden" name="curPage" value="${pageDto.curPage}">
	<input type="hidden" name="curBlock" value="${pageDto.curPage}">
</form>
</body>
</html>