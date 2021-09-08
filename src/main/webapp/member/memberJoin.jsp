<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/member/member.css" rel="stylesheet" type="text/css">

<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
	<div class="jojn_method">
		<div>
			<h1 class="join_title01">회원가입</h1>
			<span class="join_title02">올디프 회원이 되면, <span style="color:#396ef3; font-size:20px;">다양한 혜택과 할인</span>을 받을 수 있습니다.</span>
		</div>
		<div><a href="${pageContext.request.contextPath}/memberJoinImpromation" class="join">올디프 간편 회원가입</a></div>

		<div class="join_title03"><span>이미 올디프 회원이세요?</span> <a href="${pageContext.request.contextPath}/login">로그인</a></div>
	</div>	
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>