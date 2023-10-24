<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="/script/member.js" type="text/javascript"></script>

<form action="/registerProc" name="form1" method="post">
	<table>
		<thead>
			<tr>
				<th colspan="2">회원 정보</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="col1">아이디</td>
				<td class="col2">
					${member.mem_id}
				</td>
			</tr>
			<tr>
				<td class="col1">비밀번호</td>
				<td class="col2">
					<input type="password" name="m_passwd" id="check1" class="chk" title="비밀번호" readonly="readonly" value="${member.m_passwd}">
				</td>
			</tr>
			<tr>
				<td class="col1">이름</td>
				<td class="col2">
					<input type="text" name="m_name" class="chk" title="이름" readonly="readonly" value="${member.m_name}">
				</td>
			</tr>
			<tr>
				<td class="col1">이메일</td>
				<td class="col2">
					<input type="text" name="m_email"  readonly="readonly" value="${member.m_email}">
				</td>
			</tr>
			<tr>
				<td class="col1">연락처</td>
				<td class="col2">
					<input type="text" name="m_phone" class="chk" title="연락처" readonly="readonly" value="${member.m_phone}">
				</td>
			</tr>
			<tr>
				<td class="col1">직업</td>
				<td class="col2">
					<input type="text" name="m_job" class="chk" title="직업" readonly="readonly" value="${member.m_job}">
				</td>
			</tr>
			<tr>
				<td class="col1">우편번호</td>
				<td class="col2">
					<input type="text" class="chk" name="zipcode" id="sample6_postcode" readonly="readonly" value="${member.zipcode}">
				</td>
			</tr>
			<tr>
				<td class="col1">주소</td>
				<td class="col2">
					<input type="text" class="chk" name="address" id="sample6_address" title="주소" readonly="readonly" value="${member.address}"><br>
					<input type="text" class="chk" name="address2" id="sample6_detailAddress" readonly="readonly" title="상세주소" value="${member.address2}"><br>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td>
					<input class="update" name="update" type="button" value="정보 수정" onclick="javascript:updates('u')">
					<input class="update" name="delete" type="button" value="회원 탈퇴" onclick="javascript:updates('d')">
				</td>
			</tr>
		</tfoot>
	</table>
</form>
	<form id="upForm" name="upForm" method="post" action="">
		<input type="hidden" id="memId" name="mem_id" value="${member.mem_id}">
		<input type="hidden" id="pw" name="m_passwd" value="${member.m_passwd}">
	</form>
