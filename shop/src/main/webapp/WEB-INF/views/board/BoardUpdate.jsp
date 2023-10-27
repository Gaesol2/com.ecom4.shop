<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 폼</title>

<link rel="stylesheet" href="css/form.css" type="text/css">
<script src="jquery/jquery-3.7.0.min.js"></script>
<script src="script/form.js"></script>

</head>
<body>
<form name="form1" action="updateAction" method="post">
<table>
	<tr><td colspan="2"><h3>게시글 수정</h3></td></tr>
	<tr>
		<th>제목</th>
		<td><input type="text" name="subject" title="제목" class="chk" value="${article.subject}"></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><input type="text" name="writer" class="chk" title="작성자" value="${article.writer}"></td>
	</tr>
	<tr>
		<th>글 내용</th>
		<td><textarea name="content" class="chk" title="글내용">${article.content}</textarea></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="passwd" class="chk" title="비밀번호" value="${article.passwd}"></td>
	</tr>
	<tr>
		<td colspan="2">
			<input class="btn" type="button" value="글 수정" id="submit11">
			<input class="btn" type="reset" value="초기화">
			<input class="btn" type="button" value="글 목록" onclick="location.href='list.do'">
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