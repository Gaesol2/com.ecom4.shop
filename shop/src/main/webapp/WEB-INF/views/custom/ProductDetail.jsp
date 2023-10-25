<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품상세페이지</title>
</head>
<body>
<div id="contents">
		<div id="productmgt">
			<h3>${product.p_name}</h3>
			<table>
				<tr>
					<td>
						<a href="upload/${product.image}">
							<img src="upload/${product.image}" height="150" width="150">
						</a>
					</td>
					<td>
					<form action="cartProc?flag=add" name="formCart" method="post">
						<table>
							<tr>
								<th>상품명 : </th>
								<td>
									${product.p_name}
									<input type="hidden" value="${product.p_name}" name="p_name">
									<input type="hidden" value="${product.price}" name="price">
								</td>
							</tr>
							<tr>
								<th>가 격 : </th>
								<td class="price">
									${product.price}
								</td>
							</tr>
							<tr>
								<th>재 고 : </th>
								<td class="stock">
									<input type="text" name="stock" value="${product.stock}" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th>구매수량 : </th>
								<td>
									<input type="text" value="" name="quantity" size="5" class="chk" title="구매 수량">
								</td>
							</tr>
							<tr>
								<th colspan="2">
									<input type="button" class="cartAdd" id="submitCart" value="장바구니 담기">
									<input type="button" value="상품목록" onclick="location.href='productList'">
									<input type="hidden" value="${product.p_no}" name="p_no">
								</th>
								<td>
									<input type="hidden" value="1" name="quantity" size="5" class="chk1">
								</td>
							</tr>
						</table>
					</form>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>