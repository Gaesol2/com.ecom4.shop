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
</head>
<body>
<div id = "customProduct">
	<div id="contents">
	<table>
		<thead><tr style="text-align:center;"><th colspan="5">주문번호 ${odto.o_no}</th></tr></thead>
			<tr><th>주문번호</th><td>${odto.o_no}</td></tr>
			<tr><th>상품명</th><td>${odto.p_name}</td></tr>
			<tr><th>단가</th><td class="price">${odto.price}</td></tr>
			<tr><th>구매 수량</th><td>${odto.quantity}</td></tr>
			<tr><th>결제 금액</th><td class="price">${odto.amount}</td></tr>
			<tr><th>배송 상태</th><td>
				<c:choose>
					<c:when test="${odto.state==1 }">결제 중</c:when>
					<c:when test="${odto.state==2 }">배송 준비</c:when>
					<c:when test="${odto.state==3 }">배송 중</c:when>
					<c:when test="${odto.state==4 }">배송 완료</c:when>
					<c:when test="${odto.state==5 }">구매 확정</c:when>
				</c:choose>
			</td></tr>
		<tr><th colspan="2" class="tableBtn">
			<a href="/orderList"><input type="button" value="구매목록으로"></a>
		</th>
		</tr>
	</table>
	</div>
</div>
</body>
</html>