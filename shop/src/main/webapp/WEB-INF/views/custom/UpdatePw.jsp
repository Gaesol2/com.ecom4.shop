<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="updatePwForm" method="post" action="updatePW">
		<table>
			<tr><td colspan="2"><h3>비밀번호 바꾸기</h3></td></tr>
			<tr>
				<td>바꿀 비밀번호 : </td>
				<td><input type="password" id="check1" name="m_passwd" title="바꿀 비밀번호" class="chk"></td>
			</tr>
			<tr>
				<td>비밀번호 확인 : </td>
				<td><input type="password" id="check2" name="m_passwd2" title="비밀번호 확인" class="chk"></td>
			</tr>
			<tr><td></td><td><font id="check" size="2" color="red"></font></td></tr>
			<tr><td colspan="2">
				<button type="button" id="submitUpdatePw">변경</button>
				<button type="button" onclick="location.href='/searchId'">취소</button>
			</td></tr>
		</table>
		<input type="hidden" name="mem_id" value="${mdto.mem_id}">
	</form>
</body>
</html>