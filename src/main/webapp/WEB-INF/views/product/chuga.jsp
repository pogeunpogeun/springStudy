<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file = "../_include/inc_menu.jsp" %>
<h2>${title }</h2>
<form name="DirForm">
<table border="1" width="50%">
	<tr>
		<td>상품이름</td>
		<td><input type="text" name="productName"></td>
	</tr>
	<tr>
		<td>상품가격</td>
		<td><input type="text" name="productPrice"></td>
	</tr>
	<tr>
		<td>상품설명</td>
		<td><textarea name="productDescription" style="width:99%; height:100px"></textarea></td>
	</tr>
	<tr>
		<td>제조사번호</td>
		<td><input type="text" name="vendorNo"></td>
	</tr>
	<tr>
		<td>제조사이름</td>
		<td><input type="text" name="vendorName"></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td><input type="text" name="attachInfo" value="-"></td>
	</tr>
	<tr>
		<td colspan="2">
			<button type="button" onClick="save();">등록하기</button>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script>
	function save() {
		if(confirm('등록할까요?')) {
			document.DirForm.action="${path }/product/chugaProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
</script>