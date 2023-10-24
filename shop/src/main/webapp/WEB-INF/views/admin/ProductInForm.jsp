<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
</head>
<body>
	<c:import url="./Top.jsp"/>
	<div id="contents">
		<div id="productmgt">
			<h3>상품 등록</h3>
			<form action="/productMgtProc?flag=insert" method="post" name="formInForm" enctype="multipart/form-data">
				<table class="product">
					<tr>
						<th>상품명</th>
						<td><input type="text" name="p_name" class="chk" title="상품명" placeholder="상품명을 입력하시오"></td>
					</tr>
					<tr>
						<th>재고 수량</th>
						<td><input type="text" name="stock" class="chk" title="재고량" placeholder="재고 수량을 입력하시오">&nbsp;개</td>
					</tr>
					<tr>
						<th>상품가격</th>
						<td><input type="text" name="price" class="chk" title="상품가격" placeholder="단가를 입력하시오">&nbsp;원</td>
					</tr>
					<tr>
						<th>상품설명</th>
						<td><input type="text" name="detail" class="chk" title="상품 설명" placeholder="상품 설명을 입력하시오"></td>
					</tr>
					<tr>
						<th>상품 이미지</th>
						<td><input type="file" name="image2" class="chk" title="이미지" placeholder="상품 설명을 입력하시오"></td>
					</tr>
					<tr>
						<th colspan="2"><input id="submitInForm" type="button" value="상품등록전송"></th>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<c:import url="./Bottom.jsp"></c:import>
</body>
</html>