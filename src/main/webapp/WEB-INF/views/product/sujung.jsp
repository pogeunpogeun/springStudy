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
<input type="hidden" name="productNo" value="${dto.productNo }">
<table border="1" width="50%">
	<tr>
		<td>상품이름</td>
		<td><input type="text" name="productName" value="${dto.productName }"></td>
		<td rowspan="6">
		<c:choose>
				<c:when test="${dto.attachInfo == '-' }">
					&nbsp;
				</c:when>
				<c:otherwise>
					<c:set var="tempArray1" value="${fn:split(dto.attachInfo, ',') }" />
					<c:set var="temp1" value="${tempArray1[0] }" />
					<c:set var="temp2" value="${tempArray1[1] }" />
					<img src="${path }/attach${path }/product/${tempArray1[1] }" alt="${tempArray1[0] }" title="" style="width:50px; height:50px;">
				</c:otherwise>
			</c:choose>				
	</tr>
	<tr>
		<td>상품가격</td>
		<td><input type="text" name="productPrice" value="${dto.productPrice }"></td>
	</tr>
	<tr>
		<td>상품설명</td>
		<td><textarea name="productDescription" style="width:99%; height:100px">${dto.productDescription }</textarea></td>
	</tr>
	<tr>
		<td>제조사번호</td>
		<td><input type="text" name="vendorNo" value="${dto.vendorNo }"></td>
	</tr>
	<tr>
		<td>제조사이름</td>
		<td><input type="text" name="vendorName" value="${dto.vendorName }"></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td><input type="text" name="attachInfo" value="${dto.attachInfo }"></td>
	</tr>
	<tr>
		<td colspan="3">
			<button type="button" onClick="save();">수정하기</button>
		</td>
	</tr>		
</table>
</form>
</body>
</html>
<script>
	function save() {
		if(confirm('수정할까요?')) {
			document.DirForm.action="${path }/product/sujungProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
</script>