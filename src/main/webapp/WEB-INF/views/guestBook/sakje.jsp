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
<input type="hidden" name="no" value="${dto.no }">
<table border="1" width="50%">
	<tr>
		<td>이름</td>
		<td>${dto.name }</td>
		<td>이메일</td>
		<td>${dto.email }</td>
	</tr>
	<tr>
		<td>등록일</td>
		<td>${dto.regiDate }</td>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>
	<tr>
		<td colspan="4">
			<textarea name="content" style="width:99%; height:200px;">${dto.content }</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<button type="button" onClick="save();">삭제</button>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script>
	function save() {
		if(confirm('삭제하시겠습니까?')) {
			document.DirForm.action="${path }/guestBook/sakjeProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
</script>