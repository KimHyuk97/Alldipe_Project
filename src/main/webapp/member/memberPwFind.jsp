<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="${pageContext.request.contextPath}/css/member/member.css" rel="stylesheet" type="text/css">

<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
<c:if test="${requestScope.Fail eq 'fail' }">
	<script>
		alert('실패하였습니다.');
	</script>
</c:if>
<div class="id_find_page">
	<h1 class="id_find_page_title">비밀번호 찾기</h1>
	<div class="id_find_page_Box">
		<div id="phone" class="choiceBox">등록된 핸드폰 인증</div>
		<div id="email" class="choice">등록된 이메일로 찾기</div>
	</div>
	<div id="phoneFind">
		<div id="certificationBeforeP">
			<div class="findName">이름</div>
			<div class="findText"><input type="text" id="nameVal" maxlength="5" placeholder="이름을 입력해주세요"></div>
			<div class="findName">휴대폰번호</div>
			<div class="findText"><input type="text" id="phoneVal" maxlength="11" placeholder="숫자만 입력해주세요"></div>
			<div class="findBtn"><input type="button" id="phoneBtn" value="휴대폰인증"></div>	
		</div>
		<div id="certificationAfterP">
			<p class="find02">휴대폰 인증</p>
			<input type="text" id="certificationNumP"><input type="button" id="certificationP" value="인증하기">
		</div>
	</div>
	<div id="emailFind">
		<div id="certificationBefore">
			<div class="findName">이름</div>
			<div class="findText"><input type="text" id="nameVal" maxlength="5" placeholder="이름을 입력해주세요"></div>
			<div class="findName">이메일</div>
			<div class="findText"><input type="text" id="emailVal" placeholder="예:alldipe@alldipe.com"></div>
			<div id="emailCheckOn"></div>
			<div class="findBtn"><input type="button" id="emailBtn" value="이메일인증"></div>
		</div>	
		<div id="certificationAfter">
			<p class="find02">이메일 인증</p>
			<input type="text" id="certificationNum"><input type="button" id="certification" value="인증하기">
		</div>
	</div>
	<div id="resultId">
		<form action='../pwChanage' name='pw_chanage' method='post'>
			<input type='hidden' id='way' name='way'>
			<p class='findName'>새로운 비밀번호</p>
			<input class='findText' name='pw' type='password' id='afterPw'>
			<p class='findName'>새로운 비밀번호 확인</p>
			<input class='findText' type='password' id='afterPwCheck'>
			<div class='findBtn'><input type='button' id="chanageBtn" value='변경하기'></div>
		</form>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/member/memberPwFind.js"></script>



<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>