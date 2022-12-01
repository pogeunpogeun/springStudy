<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../_include/inc_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "../_include/inc_menu.jsp" %>
<h2>회원수정</h2>
<form name="DirForm">
	<input type="hidden" name="no" value="${dto.no }">
	[${dto.no }] / ${dto.id} / passwd <input type="password" name="passwd"> / ${dto.name } / phone <input type="text" name="phone"> / ${dto.regiDate }
	<hr>
	<button type="button" onClick="save();">확인</button>
</form>
</body>
</html>
<script>
	function save() {
		if(confirm("ok?")) {
			document.DirForm.action="${path }/memberNew/sujungProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
</script>