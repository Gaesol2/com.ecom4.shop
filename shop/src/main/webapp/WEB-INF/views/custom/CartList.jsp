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
<script src="/script/productMgt.js"></script>
</head>
<body>
<c:import url="Top.jsp"/>
<div id = "customProduct">
	<div id="contents">
	<table class="productList">
		<thead><tr style="text-align:center;"><th colspan="5">장바구니 목록</th></tr></thead>
		<tr>
			<th>상품명</th>
			<th>상품가격</th>
			<th>수량</th>
			<th>결제금액</th>
			<th>수정/삭제</th>
			<th>조회</th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(hCartList)==0}">
				<tbody>
					<tr><td colspan="5" style="text-align:center">목록이 없습니다.</td></tr>
				</tbody>
			</c:when>
			<c:when test="${fn:length(hCartList)>0}">
			<tbody>
				<c:forEach var="cvo" items="${hCartList}">
					<tr>
						<td>${cvo.value.p_name}</td>
						<td>${cvo.value.price}</td>
						<td>
							<input type="text" name="quantity" value="${cvo.value.quantity}" size="5">
							<input type="hidden" name="p_no" value="${cvo.value.p_no}">
							<input type="hidden" name="p_name" value="${cvo.value.p_name}">
							<input type="hidden" name="price" value="${cvo.value.price}">
						</td>
						<td>
							${cvo.value.price*cvo.value.quantity}
						</td>
						<td>
							<input type="button" value="수정" onclick="javascript:cartUpdate('U',this)">/
							<input type="button" value="삭제" onclick="javascript:cartUpdate('D',this)">
						</td>
						<td><a href="productDetail?p_no=${cvo.value.p_no}">상세 조회</a></td>
					</tr>
				</c:forEach>
				<tr>
					<th><a href="orderProc">주문하기</a></th>
				</tr>
			</tbody>
			</c:when>
		</c:choose>
	</table>
	</div>
</div>
<c:import url="Bottom.jsp"/>
</body>
</html>