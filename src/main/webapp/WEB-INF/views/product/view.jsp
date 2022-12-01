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
		<td>상품번호</td>
		<td>${dto.productNo }</td>
		<td rowspan="8">
			<c:choose>
				<c:when test="${dto.attachInfo == '-' }">
					&nbsp;
				</c:when>
				<c:otherwise>
					<c:set var="tempArray1" value="${fn:split(dto.attachInfo, ',') }" />
					<c:set var="temp1" value="${tempArray1[0] }" />
					<c:set var="temp2" value="${tempArray1[1] }" />
					<img src="${path }/attach${path }/product/${tempArray1[1] }" alt="${tempArray1[0] }" title="" style="width:150px; height:200px;">
				</c:otherwise>
			</c:choose>	
		</td>			
	</tr>
	<tr>
		<td>상품이름</td>
		<td>${dto.productName }</td>
	</tr>
	<tr>
		<td>상품설명</td>
		<td id="content">${dto.productDescription }</td>
	</tr>
	<tr>
		<td>상품가격</td>
		<td>${dto.productPrice }</td>
	</tr>
	<tr>
		<td>제조사</td>
		<td>${dto.vendorName }</td>
	</tr>
	<tr>
		<td>등록일</td>
		<td>${dto.regiDate }</td>
	</tr>
	<tr>
		<td colspan="2" align="right">
		<form name="cartForm">
			<input type="hidden" name="productNo" value="${dto.productNo }">
			<input type="hidden" name="memberNo" value="${imsiMemberNo }">
			<select name="amount">
			<c:forEach begin="1" end="10" var="i">
				<option value="${i }">${i }</option>
			</c:forEach> 
			</select>&nbsp;
			<button type="button" onClick="cartAdd('','');">장바구니담기</button>
			</form>
	<tr>
		<td colspan="2"align="right">
			<a href="#" onClick="move('sujung','${dto.productNo}');">수정</a>
			/
			<a href="#" onClick="move('sakje','${dto.productNo}');">삭제</a>
			/
			<a href="#" onClick="move('list','');">목록으로</a>
		</td>
	</tr>
</table>
</body>
</html>
<script>
$(document).ready(function() {
	var content = $("#content").text().replace(/(?:\r\n|\r|\n)/g,'<br/>');
	$("#content").html(content);
});
	function move(value1, value2) {
		location.href = "${path }/product/" + value1 + "?productNo=" + value2;
	}
	function cartAdd(value1, value2) {
		if (confirm('장바구니에 담을까요?')) {
			document.cartForm.action="${path }/cart/chugaProc";
			document.cartForm.method="post";
			document.cartForm.submit();
		}
	}
</script>