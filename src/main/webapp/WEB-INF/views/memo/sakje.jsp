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
${title }
<form name="DirForm">
<input type="hidden" name="no" value="${dto.no }">
<table border="1" width="50%">
	<tr>
		<td>순번</td>
		<td>${dto.no }</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${dto.writer }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<textarea name="content" style="width:99%; height:100px;">${dto.content }</textarea>
		</td>
	</tr>
	<tr>
		<td>등록일</td>
		<td>${dto.regiDate }</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<button type="button" onClick="save();">삭제하기</button>
	</tr>
</table>
</form>
<div style="border: 0px blue solid; width:50%; margin-top:10px;" align="right">
|
<a href="#" onClick="move('list','');">목록으로</a>
|
</div>
</body>
</html>
<script>
	function save() {
		if(confirm('삭제하시겠습니까?')) {
			document.DirForm.action="${path}/memo/sakjeProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
	function move(value1, value2) {
		location.href = "${path}/memo/" + value1 + "?no=" + value2;
	}
</script>