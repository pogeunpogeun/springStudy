<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>exam05 Result</h2>
<table border="1" width="50%">
	<tr>
		<td>이름</td>
		<td>${dto.name }</td>
	</tr>
	<tr>
		<td>국어</td>
		<td>${dto.kor }</td>
	</tr>
	<tr>
		<td>영어</td>
		<td>${dto.eng }</td>
	</tr>
	<tr>
		<td>수학</td>
		<td>${dto.mat }</td>
	</tr>
	<tr>
		<td>과학</td>
		<td>${dto.sci }</td>
	</tr>
	<tr>
		<td>역사</td>
		<td>${dto.his }</td>
	</tr>
	<tr>
		<td>총점</td>
		<td>${dto.tot }</td>
	</tr>
	<tr>
		<td>평균</td>
		<td>${dto.avg }</td>
	</tr>
</table>
</body>
</html>