<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>exam07 Result</h2>
<table border="1" width="50%">
	<tr>
		<td>상품분류</td>
		<td>${dto.pCategory }</td>
	</tr>
	<tr>
		<td>상품이름</td>
		<td>${dto.pName }</td>
	</tr>
	<tr>
		<td>상품가격</td>
		<td>${dto.pPrice }</td>
	</tr>
	<tr>
		<td>할인률</td>
		<td>${dto.sale }</td>
	</tr>
	<tr>
		<td>할인가</td>
		<td>${dto.salePrice }</td>
	</tr>
	<tr>
		<td>제조사</td>
		<td>${dto.vendor }</td>
	</tr>
</table>
</body>
</html>