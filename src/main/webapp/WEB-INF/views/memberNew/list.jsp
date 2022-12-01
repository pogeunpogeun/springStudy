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
<h2>회원목록</h2>
	<c:forEach var="dto" items="${list }">
		[${dto.no }] / <a href="#" onClick="move('view','${dto.no}');">${dto.id }</a> / ${dto.name } / ${dto.phone } / ${dto.regiDate }<br>
	</c:forEach>
<hr>
|
<a href="#" onClick="move('list','0');">목록</a>
|
<a href="#" onClick="move('chuga','');">추가</a>
|
<hr>
<!-- 검색 ------------------------------------------------->
	<form name="searchForm">
		<select name="searchGubun">
		<option value="" <c:if test="${searchGubun == ''}">selected</c:if>>--선택--</option>
		<option value="id" <c:if test="${searchGubun == 'id'}">selected</c:if>>아이디</option>
		<option value="name" <c:if test="${searchGubun == 'name'}">selected</c:if>>이름</option>
		<option value="id_name" <c:if test="${searchGubun == 'id_name'}">selected</c:if>>아이디+이름</option> 
		</select>
		&nbsp;
		<input type="text" name="searchData" value="${searchData }">
		&nbsp;
		<button type="button" onClick="search();">검색</button>
	</form>
<!-- 페이징 --------------------------------------------------------------------- -->
<hr>
<c:if test="${totalRecord > 0 }">
<c:set var="value1" value="list"></c:set>
		<a href="#" onclick="goPage('${value1 }', '1');">[첫페이지]</a>
		&nbsp;&nbsp;
		<c:if test="${startPage > blockSize}">
			<a href="#" onclick="goPage('${value1 }', '${startPage - blockSize }');">[이전10개]</a>
		</c:if>
		<c:if test="${startPage <= blockSize}">
			[이전10개]
		</c:if>
		&nbsp;	
		<c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
			<c:if test="${i == pageNumber}">
				[${i }]
			</c:if>
			<c:if test="${i != pageNumber}">
				<a href="#" onclick="goPage('${value1 }', '${i }');">${i }</a>
			</c:if>			    
		</c:forEach>	
		&nbsp;
		<c:if test="${lastPage < totalPage}">
			<a href="#" onclick="goPage('${value1 }', '${startPage + blockSize }');">[다음10개]</a>
		</c:if>
		<c:if test="${lastPage >= totalPage}">
			[다음10개]
		</c:if>	
		&nbsp;&nbsp;
		<a href="#" onclick="goPage('${value1 }', '${totalPage }');">[끝페이지]</a>
</c:if>
<!------------------------------------------------------------------------------- -->
</body>
</html>
<script>
	function move(value1, value2) {
		location.href = '${path }/memberNew/' + value1 + "?no=" + value2 + "&pageNumber=" + ${pageNumber} + "&${searchQueryString }";
	}
	function search() {
		if(confirm('search?')) {
			document.searchForm.action="${path }/memberNew/listSearch";
			document.searchForm.method="post";
			document.searchForm.submit();
		}
	}
	function goPage(value1, value2) {
		location.href = "${path }/memberNew/" + value1 + "?pageNumber=" + value2 + "&${searchQueryString }";
	}
</script>