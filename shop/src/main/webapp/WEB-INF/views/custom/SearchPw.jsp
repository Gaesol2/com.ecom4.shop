<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="searchForm" method="post" action="searchPW">
		<table>
			<tr><td colspan="2"><h3>비밀번호 찾기</h3></td></tr>
			<tr>
				<td>아이디 : </td>
				<td><input type="text" name="mem_id" title="아이디" class="chk"></td>
			</tr>
			<tr>
				<td>이름 : </td>
				<td><input type="text" name="m_name" title="이름" class="chk"></td>
			</tr>
			<tr>
				<td>전화번호 : </td>
				<td><input type="text" name="m_phone" title="전화번호" class="chk"></td>
			</tr>
			<tr><td colspan="2">
				<button type="button" id="submitSearch">검색</button>
				<button type="button" onclick="location.href='/searchId'">아이디 찾기</button>
			</td></tr>
		</table>
	</form>
</body>
</html>