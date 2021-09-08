<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- <link href="${pageContext.request.contextPath}/css/member/member.css" rel="stylesheet" type="text/css"> -->

<div class="contentTitle">
	<h1>개인 정보 수정</h1>
</div>
<div id="impromationForm01">
	<div>
		<h3>비밀번호 재확인</h3>
		<span>회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인해주세요.</span>
	</div>
	<div>
		<table id="mpM">
			<tr>
				<th>아이디</th>
				<td><input type="text" id="id" value="${sessionScope.mem.memId}" readonly></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" id="pw" placeholder="비밀번호를 입력해주세요"></td>
			</tr>
		</table>
		<input type="button" id="submit2" value="확인">
	</div>
</div>

<div id="impromationForm02">
	<form action="../memberModify" name="modify" method="post">
			<table>
				<tr id="line01">
					<th>아이디</th>
					<td>
						<input type="text" name="userId" value="${sessionScope.mem.memId}" readonly>
						<input type="hidden" name="no" value="${sessionScope.mem.memNo}">
					</td>
				</tr>
				<tr id="line02">
					<th>비밀번호</th>
					<td>
						<input type="password" id="inputClick02" name="userPw" placeholder="비밀번호를 입력해주세요"  maxlength="20">
						<ul class="sub2">
							<li class="list_style">10자 이상 입력 <span id="pwcheck"></span></li>
							<li class="list_style">영문/숫자/특수문자(공백 제외)만 허용하며, 2개 이상 조합 <span id="pwcheck02"></span></li>
						</ul>							
					</td>
				</tr>
				<tr id="line03">
					<th>비밀번호확인</th>
					<td>
						<input type="hidden" id="pw_value">
						<input type="password" id="inputClick03" placeholder="비밀번호를 한번 더 입력해주세요">
						<ul class="sub3">
							<li class="list_style">동일한 비밀번호를 입력해주세요. <span id="pwcheckOn"></span></li>
						</ul>							
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text"value="${sessionScope.mem.memNm}" readonly>
					</td>
				</tr>
				<tr id="line04">
					<th>이메일</th>
					<td>
						<input type="hidden" id="email_value">
						<input type="text" id="userEmail" class="inputText" name="userEmail" value="${sessionScope.mem.email}">
						<input type="button" id="email_check" class="check" value="중복확인">						
						<ul class="sub4">
							<li class="list_style">이메일 형식<span id="emailCheckOn"></span></li>
							<li class="list_style">이메일 중복확인 <span id="emailCheckOn02"></span></li>
						</ul>
					</td>
				</tr>
				<tr id="line05">
					<th>휴대폰</th>
					<td>
						<input type="hidden" id="phone_value">
						<div id="certificationBefore">
							<input type="text" id="talVal" class="inputText" name="userPhone" value="${sessionScope.mem.cellPhone}" maxlength="11">
							<input type="button" class="check" id="talbtn" value="다른번호 인증">
						</div>
						<div id="certificationAfter">
							<input type="text" id="certificationNum" maxlength="6" class="inputText" placeholder="인증받을 코드를 입력해주세요">
							<input type="button" id="certification" class="check" value="인증하기">
						</div>
						<ul class="sub5">
							<li class="list_style">휴대폰 정규식 <span id="talCheck"></span></li>
							<li class="list_style">휴대폰 인증 <span id="talCheck2"></span></li>
						</ul>
					</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>
						<input type="text" value="${sessionScope.mem.birthDt}" readonly>
					</td>
				</tr>
				<tr style="border-top:3px solid #396ef3; vertical-align: baseline;">
					<th>이용약관동의</th>
					<td>
						<div class="selectAll">
							<input type="checkBox" name="" value="selectAll"> 전체 동의합니다.
							<p class="sub_help">선택항목에 동의하지 않은 경우도 회원가입 및 일반적인 서비스를 이용할 수 있습니다.</p>
						</div>
						<div class="select01"><input type="checkBox" name="" value="select01"> 이용약관 동의<a class="agreement" href="#">약관보기 ></a></div>
						<div class="select02"><input type="checkBox" name="" value="select02"> 개인정보 수집 및 이용<a class="agreement" href="#">약관보기 ></a></div>
					</td>
				</tr>		
			</table>
			<div>
				<input type="button" id="memberDelete" value="회원탈퇴">
				<input type="button" id="memberModify" value="수정하기">
			</div>
	</form>
</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myPage/myPageMemberModify.js"></script>	