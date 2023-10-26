<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/script/notice.js" type="text/javascript"></script>
</head>
<body>
<form method="post" name="noticeForm" action="">
	<table>
	<h3 style="text-align:center">공지사항 수정</h3>
		<tr>
			<th>번호</th>
			<td><input type="text" name="noti_no" style="width:300px;" value="${notice.noti_no }" readonly="readonly"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject" style="width:300px;" value="${notice.subject }" class="chk"></td>
		</tr>
			<th>내용</th>
			<td><textarea name="content" rows="20" cols="40" style="resize: none" class="chk">${notice.content }</textarea></td>
		</tr>
			<th>종료일</th>
			<td><input type="date" name="vdate" style="width:300px;" value="${notice.vdate }"></td>
		</tr>
	</table>
	<input type="button" value="수정" class="noticeUpProc">
	<input type="button" value="취소" onclick="location.href='notice'">
	<input type="hidden" name="flag" value="insert">
	
	<input type="hidden" value="${ssKey.m_name}" name="writer">
	<input type="hidden" value="${noticeList.regdate}" name="regdate">
	<input type="hidden" value="${noticeList.state}" name="state">
</form>
</body>
</html>