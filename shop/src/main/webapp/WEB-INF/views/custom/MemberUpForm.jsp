<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="memUpProc" name="form1" method="post">
	<table>
		<thead>
			<tr>
				<th colspan="4">회원가입</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="col1">아이디</td>
				<td class="col2">${mdto.mem_id}
				<input type="hidden" name="mem_id" value="${mdto.mem_id}"></td>
			</tr>
			<tr>
				<td class="col1">비밀번호</td>
				<td class="col2">
					<input type="password" name="m_passwd" id="check1" class="chk" title="비밀번호" value="${mdto.m_passwd}">
				</td>
				<td class="col3"></td>				
			</tr>
			<tr>
				<td class="col1">확인</td>
				<td class="col2">
					<input type="password" name="m_repasswd" id="check2" class="chk" title="비밀번호 확인" placeholder="비밀번호를 확인하시오.">
				</td>
				<td class="col3"><font id="check" size="2" color="red"></font></td>				
			</tr>
			<tr>
				<td class="col1">이름</td>
				<td class="col2">
					<input type="text" name="m_name" class="chk" title="이름" value="${mdto.m_name}">
				</td>
				<td class="col3"><font id="name" size="2" color="red"></font></td>				
			</tr>
			<tr>
				<td class="col1">이메일</td>
				<td class="col2">
					<input type="text" name="m_email" value="${mdto.m_email}">
				</td>
				<td class="col3"></td>				
			</tr>
			<tr>
				<td class="col1">연락처</td>
				<td class="col2"><input type="text" name="m_phone" class="chk" title="연락처" value="${mdto.m_phone}"></td>
			</tr>
			<tr>
				<td class="col1">직업</td>
				<td class="col2">
					<select name="m_job" id="m_job">
						<option value="">선택하시오.</option>
						<option value="회사원">회사원</option>
						<option value="기술사">기술사</option>
						<option value="연구전문직">연구전문직</option>
						<option value="학생">학생</option>
						<option value="교수">교수</option>
						<option value="일반 자영업">일반 자영업</option>
						<option value="공무원">공무원</option>
						<option value="의료인">의료인</option>
						<option value="전문직">전문직</option>
						<option value="종교, 언론, 예술인">종교, 언론, 예술인</option>
						<option value="농, 축, 수산, 광업인">농, 축, 수산, 광업인</option>
						<option value="주부">주부</option>
						<option value="무직">무직</option>
						<option value="기타">기타</option>
					</select>
					<script type="text/javascript">
					$(function(){
						$("#m_job").val('${mdto.m_job}');
					})
					</script>
				</td>
				<td class="col3"></td>
			</tr>
			<tr>
				<td class="col1">우편번호</td>
				<td class="col2"><input type="text" class="chk" name="zipcode" value="${mdto.zipcode}" readonly="readonly"></td>
			</tr>
			<tr>
				<td class="col1">주소</td>
				<td class="col2">
					<input type="text" class="chk" name="address" id="sample6_address" value="${mdto.address}" title="주소" readonly="readonly"><br>
					<input type="text" class="chk" name="address2" id="sample6_detailAddress" title="상세주소" value="${mdto.address2}"><br>
					<input type="hidden" id="sample6_extraAddress" placeholder="참고항목"><br>
				</td>
				<td class="col3"></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3" class="tableBtn">
					<button id="submit11" type="button">회원 정보 수정</button>
				</td>
			</tr>
		</tfoot>
	</table>
</form>
