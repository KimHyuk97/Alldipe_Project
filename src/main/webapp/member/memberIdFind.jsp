<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/member/member.css" rel="stylesheet" type="text/css">

<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
<div class="id_find_page">
	<h1 class="id_find_page_title">아이디 찾기</h1>
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
		<div id="resultIdP"></div>
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
		<div id="resultId"></div>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/member/memberFind.js"></script>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>