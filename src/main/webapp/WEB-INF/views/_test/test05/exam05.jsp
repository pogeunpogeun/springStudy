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
<h2>exam05</h2>
<form name="DirForm">
<table border="1" width="50%">
	<tr>
		<td>이름</td>
		<td><input type="text" name="name"></td>
	</tr>
	<tr>
		<td>국어</td>
		<td><input type="text" name="kor"></td>
	</tr>
	<tr>
		<td>영어</td>
		<td><input type="text" name="eng"></td>
	</tr>
	<tr>
		<td>수학</td>
		<td><input type="text" name="mat"></td>
	</tr>
	<tr>
		<td>과학</td>
		<td><input type="text" name="sci"></td>
	</tr>
	<tr>
		<td>역사</td>
		<td><input type="text" name="his"></td>
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
			document.DirForm.action="${path }/test05Proc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
</script>
</body>
</html>