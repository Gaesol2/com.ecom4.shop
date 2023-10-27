<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용</title>
<link rel="stylesheet" href="css/form.css" type="text/css">
<script src="jquery/jquery-3.7.0.min.js"></script>
<script src="script/content.js"></script>

</head>
<body>
<form name="form1" action="" method="post">
<table>
	<tr><td colspan="2"><h3>글 내용</h3></td></tr>
	<tr>
		<th>제목</th>
		<td><input type="text" name="subject" title="제목" value="${article.subject}" readonly="readonly"></td>
	</tr>
	<tr>
		<th>작성자</th>
	<td><input type="text" name="writer" title="작성자" value="${article.writer}" readonly="readonly"></td>
	</tr>
	<tr>
		<th>IP</th>
	<td><input type="text" name="ip" title="IP" value="${article.ip}" disabled="disabled"></td>
	</tr>
	<tr>
		<th>작성일자</th>
	<td><input type="text" name="regdate" title="작성일자" value="${article.regdate}" disabled="disabled"></td>
	</tr>
	<tr>
		<th>글 내용</th>
		<td><textarea name="content" title="글내용" readonly="readonly">${article.content}</textarea></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="passwd" title="비밀번호" readonly="readonly" value="${article.passwd}"></td>
	</tr>
	<tr>
		<td colspan="2">
			<input class="btn" type="button" value="글 수정" id="update">
			<input class="btn" type="button" value="글 삭제" id="delete">
			<input class="btn" type="button" value="답글" id="reply">
			<input class="btn" type="button" value="글 목록" onclick="location.href='list?curPage=${pdto.curPage}'">
		</td>
	</tr>
</table>
<input type="hidden" name="bno" value="${article.bno}">
<input type="hidden" name="bref" value="${article.bref}">
<input type="hidden" name="bstep" value="${article.bstep}">
<input type="hidden" name="blevel" value="${article.blevel}">
<input type="hidden" name="curPage" value="${pdto.curPage}">
</form>
</body>
</html>