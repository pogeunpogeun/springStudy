<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../../_include/inc_header.jsp" %>
<%@include file = "../../_include/inc_menu.jsp" %>
<h2>디렉토리 목록 및 삭제</h2>
<form name="imsiForm">
<input type="hidden" name="fileInfo" style="width:300px;">
</form>
	<c:set var="k" value="${k = 0 }"/>
		<c:forEach var="fileFolderName" items="${list }">
		<input type="hidden" name="box_${k }" id="box_${k }" value="${fileFolderName.substring(3) }"><br>
		<a href="#" onClick="sakje('box_${k }');">[삭제]</a>&nbsp;&nbsp;${fileFolderName }<br>
	<c:set var="k" value="${k = k + 1 }"/>
</c:forEach>
<c:if test="${list.size() == 0 }">
	등록된 파일이 없습니다.
</c:if>
<script>
	function sakje(value1) {
		if(confirm('정말 삭제할까요?')) {
			var info = document.getElementById(value1).value;
			document.imsiForm.fileInfo.value = info;
			
			document.imsiForm.action="${path }/test10Proc";
			document.imsiForm.method="post";
			document.imsiForm.submit();			
		}
	}
</script>