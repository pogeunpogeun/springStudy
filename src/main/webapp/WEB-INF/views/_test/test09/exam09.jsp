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
<h2>exam09</h2>
<table border="1" width="50%">
	<tr>
		<td>아이디</td>
		<td><input type="text" name="id" id="id"></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" id="name"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email" id="email"></td>
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
		name = $("#name").val();
		email = $("#email").val();
		param = {"id" : id, "name" : name, "email" : email}
		url = "${path }/test09Proc";
		$.ajax({
			type: "post",
			data: param,
			url: url,
			success: function(result) {
				console.log("result : " + result);
				$("#result").html("id : " + result.id + "/" + "name : " + result.name + "/" + "email : " + result.email);
			}
		})
	});
});
</script>