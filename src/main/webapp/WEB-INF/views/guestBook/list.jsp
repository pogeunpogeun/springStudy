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
	<c:if test = "${list.size() == 0 }">
	<tr>
		<td>등록된 방명록이 없습니다.</td>
	</tr>
	</c:if>
	<c:forEach var = "dto" items = "${list }">
		<tr>
			<td>이름</td>
			<td>${dto.name }</td>
			<td>이메일</td>
			<td>${dto.email }</td>
		</tr>
		<tr>
			<td>회원번호</td>
			<td>${dto.memberNo }</td>
			<td>등록일</td>
			<td>${dto.regiDate }</td>
		</tr>
		<tr>
			<td colspan="4">
				<textarea name="content" style="width:99%; height:200px;">${dto.content }</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="right">
				<a href="#" onClick="move('chuga','');">추가</a>
				/
				<a href="#" onClick="move('sujung','${dto.no }');">수정</a>
				/
				<a href="#" onClick="move('sakje','${dto.no }');">삭제</a>
			</td>
		</tr>
</c:forEach>
</table>
</body>
</html>
<script>
	function move(value1, value2) {
		location.href = "${path }/guestBook/" + value1 + "?no=" + value2;
	}
</script>