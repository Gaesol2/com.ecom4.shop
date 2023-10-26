<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희수네 쇼핑몰</title>
<link rel="stylesheet" href="/css/product.css" type="text/css">
<script src="/script/productMgt.js" type="text/javascript"></script>
</head>
<body>
<div id = "customOrder">
	<div id="contents">
	<table>
		<thead><tr style="text-align:center;"><th colspan="8">총 주문 내역 (총 주문 ${fn:length(orderList)}건)</th></tr></thead>
		<tr>
			<th>상품번호</th>
			<th>상품명</th>
			<th>고객 아이디</th>
			<th>주문수량</th>
			<th>결제금액</th>
			<th>단가</th>
			<th>결제일</th>
			<th>주문상태</th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(orderList)==0}">
				<tr><td colspan="8" style="text-align:center">목록이 없습니다.</td></tr>
			</c:when>
			<c:when test="${fn:length(orderList)>0}">
				<c:forEach var="order" items="${orderList}" varStatus="i">
					<tr>
						<td><input type="checkbox" name="ck" value="${i.index}"></td>
					</tr>
					<tr>
						<td>${order.p_no }</td>
						<td class="acl2">
							<a onclick="javascript:orderDetail(this)">${order.p_name }</a>
							<input type="hidden" name="p_no" value="${order.p_no}">
							<input type="hidden" name="o_no" value="${order.o_no}">
							<input type="hidden" name="mem_id" value="${order.mem_id}">
						</td>
						<td>${order.m_name }(${order.mem_id})</td>
						<td>${order.quantity }</td>
						<td class="price">${order.price }</td>
						<td class="price">${order.amount }</td>
						<td>${order.o_regdate }</td>
						<td>
							<select name="state" id="state${i.count}">
								<option value="1">결제 중</option>
								<option value="2">배송 준비</option>
								<option value="3">배송 중</option>
								<option value="4">배송 완료</option>
								<option value="5">구매 확정</option>
							</select>
							<script type="text/javascript">
							$(function(){
								$("#state"+'${i.count}').val('${order.state}');
							})
							</script>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		<tr>
			<th colspan="9">
				<input name="s" type="button" class="selectBtn" value="상태수정">
			</th>
		</tr>
	</table>
	</div>
	<form action="OrDetailMgt" method="post" name="orform">
		<input type="hidden" name="p_no" value="${order.p_no}">
		<input type="hidden" name="o_no" value="${order.o_no}">
		<input type="hidden" name="mem_id" value="${order.mem_id}">
	</form>
</div>
</body>
</html>