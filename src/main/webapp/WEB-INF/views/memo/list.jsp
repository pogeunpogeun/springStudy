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
<table border="1" width="50%">
	<tr>
		<th>순번</th>
		<th>작성자</th>
		<th>등록일</th>
	</tr>
	<c:if test="${list.size() == 0 }">
	<tr>
		<td colspan="3">
			등록된 메모가 없습니다.
		</td>
	</tr>
	</c:if>
	<c:forEach var="dto" items="${list }">
		<tr>	
			<td>${dto.no }</td>
			<td><a href="#" onClick="move('view','${dto.no }');">${dto.writer }</a></td>
			<td>${dto.regiDate }</td>
		</tr>
	</c:forEach>
</table>
<div style="border: 0px blue solid; width:50%; margin-top:10px;" align="right">
|
<a href="#" onClick="move('chuga','');">등록하기</a>
|
</div>
</body>
</html>
<script>
	function move(value1, value2) {
		location.href = "${path}/memo/" + value1 + "?no=" + value2;
	}
</script>
