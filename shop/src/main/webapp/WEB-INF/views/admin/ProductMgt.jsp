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
	<c:import url="./Top.jsp"/>
<div id = "productmgt">
	<div id="contents">
	<table>
		<thead><tr style="text-align:center;"><th colspan="5">상품 목록(상품 수 : ${pcnt})</th></tr></thead>
		<tr>
			<th>상품번호</th>
			<th>상품명</th>
			<th>상품가격</th>
			<th>등록일</th>
			<th>재고</th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(Products)==0}">
				<tr><td colspan="5" style="text-align:center">목록이 없습니다.</td></tr>
			</c:when>
			<c:when test="${fn:length(Products)>0}">
				<c:forEach var="products" items="${Products}">
					<tr>
						<td>${products.p_no }</td>
						<td><a href="javascript:shopDetail('${products.p_no}')">${products.p_name }</a></td>
						<td class="price">${products.price }</td>
						<td>${products.pr_date }</td>
						<td>${products.stock }</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		<tr>
			<th></th><th></th><th></th><th></th>
			<th><button onclick="location.href='/productInForm'">상품등록</button></th>
		</tr>
	</table>
	</div>
</div>
	<c:import url="./Bottom.jsp"></c:import>

<form action="productDetail" name="detail" method="post">
	<input type="hidden" name="p_no" id="no" value="${products.p_no }">
</form>
</body>
</html>