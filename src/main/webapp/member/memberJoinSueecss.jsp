<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/member/member.css" rel="stylesheet" type="text/css">

<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
<div class="id_find_confirm">
	<h1>회원가입 완료되었습니다.</h1>
	<input type="button" id="login" value="로그인하러가기">
	<input type="button" id="main" value="홈으로">
</div>
<script>
const login = document.getElementById('login'),
	  main = document.getElementById('main');

login.addEventListener('click', () => {
	location.href="${pageContext.request.contextPath}/login";	
});

main.addEventListener('click', () => {
	location.href="${pageContext.request.contextPath}/index?displayFl=y";	
});

</script>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>