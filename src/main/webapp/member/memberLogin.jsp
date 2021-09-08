<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/css/member/member.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="http://jsgetip.appspot.com"></script>


<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
<c:if test="${requestScope.Fail eq 'fail' }">
	<script>
		alert('없는 아이디와 비밀번호입니다.');
	</script>
</c:if>
<div class="member_login">
	<div class="member_login_main01">
		<div style="margin-bottom: 20px;"><span class="title">로그인</span></div>
		<form action="${pageContext.request.contextPath}/memberLogin" method="post" name="login_form">
			<input type="hidden" name="siteKey" id="siteKey">
			<input type="hidden" name="ipAddress" id="myIp">
			<div class="login_impromation">
				<div class="login_impromation01">
					<input type="text" name="id" id="userId" placeholder="아이디" onkeyup="enterkey()">
					<p id="idNull">아이디를 입력해주세요</p>
					<input type="password" name="pw" id="userPw" placeholder="비밀번호" onkeyup="enterkey()">
					<p id="pwNull">비밀번호를 입력해주세요</p>				
				</div>
				<div class="login_impromation02">
					<div><input type="checkBox" id="submitId"> <span>아이디 저장</span></div>
					<div class="member_find">
						<a href="${pageContext.request.contextPath}/memberIdFind" class="find">아이디찾기</a> |
						<a href="${pageContext.request.contextPath}/memberPwFind" class="find">비밀번호찾기</a>
					</div>
				</div>
				<div class="login_impromation03">
					<input type="button" id="loginBtn" value="로그인">
					<a href="${pageContext.request.contextPath}/join">회원가입</a>
				</div>
			</div>
		</form>
	</div>
	
	<!-- 비회원 주문자 정보 -->
	<div class="member_login_main02">
		<div style="margin-bottom: 15px;"><span class="title02">비회원 주문조회 하기</span></div>
		<form action="#" method="post">
			<div class="member_login_main03">
				<div class="login_impromation04">
					<input type="text" name="" placeholder="주문자명">
					<input type="text" name="" placeholder="주문번호">
				</div>
				<div class="login_impromation05"><input type="submit" value="확인"></div>
			</div>
		</form>

		<div><span class="title03">주문번호와 비밀번호를 잊으신 경우, 고객센터로 문의하여 주시기 바랍니다.</span></div>
	</div>
</div>
<script>
	window.onload = function(){
		endLoading();
	}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member/memberLogin.js"></script>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>