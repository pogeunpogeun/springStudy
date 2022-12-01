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
		<td>순번</td>
		<td>${dto.no }</td>
		<td rowspan="7" style="width:200px; height: 250px;">
			<%-- ${fn:replace(dto.attachInfo, '|', '<br>') } --%>
			<hr>
			<c:set var="tempArray1" value="${fn:split(dto.attachInfo,'|') }"></c:set> <!------------------ tempArray1 = {파일1, 파일2, 파일3} -->
			<c:forEach var="i" begin="0" end="${fn:length(tempArray1) - 1 }" step="1"> <!----------------- 0부터 tempArray1 마지막까지 1씩 증가하는 반복문 -->
			 <c:choose>
				<c:when test="${tempArray1[i] == ',,0,,'}"> <!-------------------------------------------- 첨부파일이 없을 경우 -->
					<a href="#" onClick="suntaek('view','0','${dto.no}');">이미지X</a>
				</c:when>
				<c:otherwise>
					<c:set var="tempArray2" value="${fn:split(tempArray1[i], ',') }" /> <!---------------- tempArray2 = {originalFileName,UUID,파일크기,파일타입,mimeType}  -->
					<c:set var="temp1" value="${tempArray2[0] }" /> <!------------------------------------ temp1 = OriginalFileName  -->
					<c:set var="temp2" value="${tempArray2[1] }" /> <!------------------------------------ temp2 = UUID -->
					<a href="#" onClick="downLoad('${dto.no }','${i }');"> <!----------------------------- no값과 index번호를 "num"으로 넘김 -->
					<img src="${path }/attach${path }/member/${temp2 }" alt="${dto.name }" title="${dto.name }" style="width:150px; height:200px;">
					</a>
				</c:otherwise>
			</c:choose>
			<hr>
		</c:forEach>
		</td>
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
		<td colspan="2">${dto.juso1 } ${dto.juso2 } ${dto.juso3 } ${dto.juso4 }</td>
	</tr>
	<tr>
		<td>회원등급</td>
		<td colspan="2">${dto.grade }</td>
	</tr>
	<tr>
		<td>등록일</td>
		<td colspan="2">${dto.regiDate }</td>
	</tr>
</table>
<div style="border: 0px solid red; width: 50%; margin-top: 10px;" align="right">
|
<a href="${path }/member/list">전체목록</a>
|
<a href="#" onClick="move('list','');">목록</a>
|
<a href="#" onClick="move('chuga','');">등록</a>
|
</div>
</body>
</html>
<script>
	function move(value1, value2) {
		location.href = "${path }/member/" + value1 + "?no=" + value2;
	}
	function downLoad(value1, value2) {
		location.href = "${path }/member/download?no=" + value1 + "&num=" + value2;
	}
</script>