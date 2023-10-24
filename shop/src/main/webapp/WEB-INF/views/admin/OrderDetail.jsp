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
<script src="script/productMgt.js"></script>
</head>
<body>
<div id = "customProduct">
	<div id="contents">
	<form action="updateOrder" name="updateOrder" id="" method="post">
	<table class="order">
		<thead><tr style="text-align:center;"><th colspan="5">주문번호 ${odto.o_no}</th></tr></thead>
			<tr><th>주문번호</th><td>${odto.o_no}
				<input type="hidden" name="o_no" value="${odto.o_no}">
				<input type="hidden" name="p_no" value="${odto.p_no}">
				<input type="hidden" name="mem_id" value="${odto.mem_id}">
			</td></tr>
			<tr><th>상품명</th><td>${odto.p_name}</td></tr>
			<tr><th>단가</th><td class="price">${odto.price}</td></tr>
			<tr><th>구매 수량</th><td>${odto.quantity}</td></tr>
			<tr><th>결제 금액</th><td class="price">${odto.amount}</td></tr>
			<tr><th>고객 정보</th><td>${odto.m_name}(${odto.mem_id})</td></tr>
			<tr><th>배송 상태</th><td>
				<select name="state" id="state${i.count}">
					<option value="1">결제 중</option>
					<option value="2">배송 준비</option>
					<option value="3">배송 중</option>
					<option value="4">배송 완료</option>
					<option value="5">구매 확정</option>
				</select>
				<script type="text/javascript">
				$(function(){
					$("#state").val('${odto.state}');
				})
				</script>
			</td></tr>
		<tr><th colspan="2" class="tableBtn">
			<input type="button" class="submit1" value="상태수정">
			<a href="orderMgt"><input type="button" value="구매목록으로"></a>
		</th>
		</tr>
	</table>
	</form>
	</div>
</div>
</body>
</html>