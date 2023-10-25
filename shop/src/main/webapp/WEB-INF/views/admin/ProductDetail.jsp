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
						<a href="/upload/${product.image}">
							<img src="/upload/${product.image}" height="150" width="150">
						</a>
					</td>
					<td>
					<form action="cartProc?flag=add" name="formCart" method="post">
						<table>
							<tr>
								<th>상품 번호 : </th>
								<td>
									${product.p_no}
								</td>
							</tr>
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
								<th>상품 설명 : </th>
								<td>
									${product.detail}
								</td>
							</tr>
							<tr>
								<th>상품 등록일 : </th>
								<td>
									${product.pr_date}
								</td>
							</tr>
							<tr>
								<th>재 고 : </th>
								<td class="stock">
									<input type="text" name="stock" value="${product.stock}" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th colspan="2" class="tableBtn">
									<input type="button" class="productUp" value="상품수정">
									<input type="button" class="productDel" value="상품삭제">
									<input type="button" value="상품목록" onclick="location.href='productMgt'">
									<input type="hidden" value="${product.p_no}" name="p_no">
								</th>
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