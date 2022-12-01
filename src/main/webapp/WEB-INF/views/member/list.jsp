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
		<th>이름</th>
		<th>등록일</th>
		<th>비고</th>
	</tr>
		<c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="4">
			등록된 회원이 없습니다.
			</td>
		</tr>
		</c:if>
		<c:forEach var="listDto" items="${list }">
		<tr>
			<td>${listDto.no }</td>
			<td><a href="#" onClick="move('view','${listDto.no}');">${listDto.name }</a></td>
			<td>${listDto.regiDate }</td>
			<td>
				<a href="#" onClick="move('sujung','${listDto.no}');">수정 
				/
				<a href="#" onClick="move('sakje','${listDto.no}');">삭제
		</tr>
	</c:forEach>
</table>
</body>
</html>
<div style="border: 0px solid red; width: 50%; margin-top: 10px;" align="right">
|
<a href="${path }/member/list">전체목록</a>
|
<a href="#" onClick="move('list','');">목록</a>
|
<a href="#" onClick="move('chuga','');">등록</a>
|
<a href="#" onClick="move('chugaAttach','');">등록(attach)</a>
|
</div>
<script>
	function move(value1, value2) {
		location.href = "${path }/member/" + value1 + "?no=" + value2;
	}
</script>