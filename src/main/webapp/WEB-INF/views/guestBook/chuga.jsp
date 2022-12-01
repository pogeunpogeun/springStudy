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
		<td>이름</td>
		<td><input type="text" name="name"></td>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email"></td>
		<td>회원번호</td>
		<td><input type="text" name="memberNo"></td>
	</tr>
	<tr>
		<td colspan="4">
			<textarea name="content" style="width:99%; height:200px;"></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<button type="button" onClick="save();">등록하기</button>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script>
	function save() {
		if(confirm('등록하시겠습니까?')) {
			document.DirForm.action="${path }/guestBook/chugaProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
</script>