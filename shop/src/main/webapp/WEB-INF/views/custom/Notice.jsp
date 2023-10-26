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
	<h3 style="text-align:center">공지사항</h3>
		<tr>
			<th>번호</th>
			<td><input type="text" name="noti_no" style="width:300px;" value="${notice.noti_no }" readonly="readonly"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject" style="width:300px;" value="${notice.subject }" readonly="readonly"></td>
		</tr>
			<th>내용</th>
			<td><textarea name="content" rows="20" cols="40" style="resize: none" readonly="readonly">${notice.content }</textarea></td>
		</tr>
			<th>종료일</th>
			<td><input type="date" name="vdate" style="width:300px;" value="${notice.vdate }"></td>
		</tr>
	</table>
	
	<input type="hidden" value="${ssKey.m_name}" name="writer">
	<input type="hidden" value="${noticeList.regdate}" name="regdate">
	<input type="hidden" value="${noticeList.state}" name="state">
</form>
</body>
</html>