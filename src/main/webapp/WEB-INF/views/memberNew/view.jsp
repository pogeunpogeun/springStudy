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
<h2>회원상세보기</h2>
	[${dto.no }] / ${dto.id } / ${dto.name } / ${dto.phone } / ${dto.regiDate }
<hr>
|
<a href="${path }/memberNew/list">전체목록</a>
|
<a href="#" onClick="move('list','0');">목록</a>
|
<a href="#" onClick="move('chuga','');">등록</a>
|
<a href="#" onClick="move('sujung','${dto.no}');">수정</a>
|
<a href="#" onClick="move('sakje','${dto.no}');">삭제</a>
|
</body>
</html>
<script>
	function move(value1, value2) {
		location.href = "${path }/memberNew/" + value1 + "?no=" + value2 + "&${searchQueryString }";
	}
</script>