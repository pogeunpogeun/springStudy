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
		<td>순번</td>
		<td>${dto.no }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${dto.name }</td>
	</tr>
	<tr>
		<td>아이디</td>
		<td>${dto.id }</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>${dto.passwd }</td>
	</tr>
	<tr>
		<td>연락처</td>
		<td>${dto.phone }</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>${dto.email }</td>
	</tr>
	<tr>
		<td>주민번호</td>
		<td>${fn:substring(dto.jumin, 0, 6)} - ${fn:substring(dto.jumin, 6, 7)}******</td>
	</tr>
	<tr>
		<td>주소</td>
		<td>${dto.juso1 } ${dto.juso2 } ${dto.juso3 } ${dto.juso4 }</td>
	</tr>
	<tr>
		<td>회원등급</td>
		<td>${dto.grade }</td>
	</tr>
	<tr>
		<td>등록일</td>
		<td>${dto.regiDate }</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button type="button" onClick="save();">확인</button>	
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script>
	function save() {
		if(confirm('삭제할까요?')) {
			document.DirForm.action="${path }/member/sakjeProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
</script>