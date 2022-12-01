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
<table border="1" width="30%">
	<tr>
		<td>작성자</td>
		<td><input type="text" name="writer"></td>
	</tr>
	<tr>
		<td>메모</td>
		<td><textarea name="content" style="width:99%; height:100px;"></textarea></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<button type="button" onClick="save();">등록하기</button></td>
	</tr>
</table>
<div style="border: 0px blue solid; width:30%; margin-top:10px;" align="right">
|
<a href="#" onClick="move('list','');">목록으로</a>
|
</div>
</form>
</body>
</html>
<script>
	function save() {
		if(confirm('등록하시겠습니까?')) {
			document.DirForm.action="${path}/memo/chugaProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
	function move(value1, value2) {
		location.href = "${path}/memo/" + value1 + "?no=" + value2;
	}
</script>

</body>
</html>