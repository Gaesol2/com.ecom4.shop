<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" name="noticeGenerate" action="/noticeProc">
	<table>
	<h3 style="text-align:center">공지사항 등록</h3>
		<tr>
			<th>제목</th>
			<td><input type="text" title="제목" name="subject" class="chk" style="width:300px;"></td>
		</tr>
			<th>내용</th>
			<td><textarea title="내용" name="content" class="chk" rows="20" cols="40" style="resize: none"></textarea></td>
		</tr>
			<th>종료일</th>
			<td><input type="date" title="종료일" name="vdate" class="chk" style="width:300px;"></td>
		</tr>
	</table>
	<input type="button" value="공지사항 등록" class="submitNotice">
	<input type="button" value="공지사항 목록" onclick="location.href='/notice'">
	<input type="hidden" name="flag" value="insert">
	
	<input type="hidden" value="${ssKey.m_name}" name="writer">
</form>
</body>
</html>