<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/member/member.css" rel="stylesheet" type="text/css">

<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
<div class="id_find_confirm">
	<div class="confirm">
		<h1 class="confirm_title">인증수단 선택</h1>
		<span class="confirm_title01">본인인증 방법을 선택해주세요.</span>
	</div>
	<div  class="confirm02">
		<button id="emailConfirm">이메일 인증(이메일값)</button>
		<button id="phoneConfirm">휴대폰 인증(전화번호)</button>
	</div>
</div>

<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>