<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../../_include/inc_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<%@include file = "../../_include/inc_menu.jsp" %>
<h2>exam08</h2>

<table border="1" width="50%">
	<tr>
		<td>아이디</td>
		<td><input type="text" name="id" id="id"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd" id="passwd"></td>
	</tr>
	<tr>
		<td colspan="2">
			<button type="button" id="btnLogin">로그인</button>
		</td>
	</tr>
</table>
<div id="result"></div>
</body>
</html>
<script>
	$(document).ready(function() {
		$("#btnLogin").click(function(){
			id = $("#id").val();
			passwd = $("#passwd").val();
			param = {"id" : id, "passwd" : passwd}
			url = "${path }/test08Proc";
			$.ajax({
				type: "post",
				data: param,
				url: url,
				success: function(result) {
					$("#result").html(result);
				}
			})
		});
	});
</script>