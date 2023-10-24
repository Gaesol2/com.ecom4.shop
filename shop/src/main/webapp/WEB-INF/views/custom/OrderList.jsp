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
		<thead><tr style="text-align:center;"><th colspan="5">${ssKey.m_name}님 주문 내역 (총 주문 ${orderTot}건)</th></tr></thead>
		<tr>
			<th>상품명</th>
			<th>주문수량</th>
			<th>결제금액</th>
			<th>단가</th>
			<th>결제일</th>
			<th>주문상태</th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(orderList)==0}">
				<tr><td colspan="6" style="text-align:center">목록이 없습니다.</td></tr>
			</c:when>
			<c:when test="${fn:length(orderList)>0}">
				<c:forEach var="order" items="${orderList}">
					<tr>
						<td>
							<a onclick="javascript:orderDetail(this)">${order.p_name }</a>
							<input type="hidden" name="p_no" value="${order.p_no}">
							<input type="hidden" name="o_no" value="${order.o_no}">
							<input type="hidden" name="mem_id" value="${order.mem_id}">
						</td>
						<td>${order.quantity }</td>
						<td class="price">${order.price }</td>
						<td class="price">${order.amount }</td>
						<td>${order.o_regdate }</td>
						<td>
							<c:choose>
								<c:when test="${order.state==1 }">결제 중</c:when>
								<c:when test="${order.state==2 }">배송 준비</c:when>
								<c:when test="${order.state==3 }">배송 중</c:when>
								<c:when test="${order.state==4 }">배송 완료</c:when>
								<c:when test="${order.state==5 }">구매 확정</c:when>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		<tr>
		</tr>
	</table>
	</div>
	<form action="customOrDetail" method="post" name="orform">
		<input type="hidden" name="p_no" value="${order.p_no}">
		<input type="hidden" name="o_no" value="${order.o_no}">
		<input type="hidden" name="mem_id" value="${order.mem_id}">
	</form>
</div>
</body>
</html>