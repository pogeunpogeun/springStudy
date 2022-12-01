<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../../_include/inc_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file = "../../_include/inc_menu.jsp" %>
<h2>exam07</h2>
<form name="DirForm">
<table border="1" width="50%">
	<tr>
		<td>상품분류</td>
		<td><input type="text" name="pCategory"></td>
	</tr>
	<tr>
		<td>상품이름</td>
		<td><input type="text" name="pName"></td>
	</tr>
	<tr>
		<td>상품가격</td>
		<td><input type="text" name="pPrice"></td>
	</tr>
	<tr>
		<td>할인률</td>
		<td><input type="text" name="sale"></td>
	</tr>
	<tr>
		<td>제조사</td>
		<td><input type="text" name="vendor"></td>
	</tr>
	<tr>
		<td colspan="2">
			<button type="button" onClick="proc();">확인</button>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script>
	function proc() {
		if (confirm('ok?')) {
			document.DirForm.action="${path }/test06Proc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
</script>
</body>
</html>