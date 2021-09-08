<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/member/member.css" rel="stylesheet" type="text/css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
<div class="join_impromation">
	<div class="join_impromation_title">
		<h1 class="join_title01">회원가입</h1>
		<span class="join_sub_title01"><span class="required">ㆍ</span>필수입력사항</span>
	</div>
	<div>
		<form action="${pageContext.request.contextPath}/memberJoin" name="join_form" method="post">
			<table>
				<tr id="line01">
					<th>아이디 <span class="required">ㆍ</span></th>
					<td>
						<input type="text" class="inputText" id="inputClick01" name="userId" placeholder="6자 이상의 영문 혹은 영문과 숫자를 조합">
						<input type="button" id="id_check" class="check" value="중복확인">
						<input type="hidden" id="id_value">
						<ul class="sub1">
							<li class="list_style">6자 이상의 영문 혹은 영문과 숫자를 조합 <span id="idcheck"></span></li>
							<li class="list_style">아이디 중복확인<span id="idcheck02"></span></li>
						</ul>
					</td>
				</tr>
				<tr id="line02">
					<th>비밀번호 <span class="required">ㆍ</span></th>
					<td>
						<input type="password" id="inputClick02" class="inputText" name="userPw" placeholder="비밀번호를 입력해주세요"  maxlength="20">
						<ul class="sub2">
							<li class="list_style">10자 이상 입력 <span id="pwcheck"></span></li>
							<li class="list_style">영문/숫자/특수문자(공백 제외)만 허용하며, 2개 이상 조합 <span id="pwcheck02"></span></li>
						</ul>							
					</td>
				</tr>
				<tr id="line03">
						<th>비밀번호확인 <span class="required">ㆍ</span></th>
						<td>
							<input type="hidden" id="pw_value">
							<input type="password" id="inputClick03" class="inputText" placeholder="비밀번호를 한번 더 입력해주세요">
							<ul class="sub3">
								<li class="list_style">동일한 비밀번호를 입력해주세요. <span id="pwcheckOn"></span></li>
							</ul>							
						</td>
				</tr>
				<tr>
					<th>이름 <span class="required">ㆍ</span></th>
					<td>
						<input type="text" class="inputText" name="userName" id="nameVal" placeholder="이름을 입력해주세요" maxlength="5">
						<input type="hidden" id="name_value">
					</td>
				</tr>
				<tr id="line04">
					<th>이메일 <span class="required">ㆍ</span></th>
					<td>
						<input type="hidden" id="email_value">
						<input type="text" id="userEmail" class="inputText" name="userEmail" placeholder="예:alldipe@alldipe.com">
						<input type="button" id="email_check" class="check" value="중복확인">						
						<ul class="sub4">
							<li class="list_style">이메일 형식<span id="emailCheckOn"></span></li>
							<li class="list_style">이메일 중복확인 <span id="emailCheckOn02"></span></li>
						</ul>
					</td>
				</tr>
				<tr id="line05">
					<th>휴대폰 <span class="required">ㆍ</span></th>
					<td>
						<input type="hidden" id="phone_value">
						<div id="certificationBefore">
							<input type="text" id="talVal" class="inputText" name="userPhone" placeholder="숫자만 입력해주세요" maxlength="11">
							<input type="button" class="check" id="talbtn" value="인증번호 받기">
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
				<tr id="line05">
					<th>주소 <span class="required">ㆍ</span></th>
					<td>
						<input type="hidden" id="adr_value">
						<div class="adr01">
							<input type="button" value="주소 검색" onclick="adrSearch()" class="adrSearch">					
						</div>
						<div class="adr02">
							<div class="adr03">
								<input type="text" id="sample6_postcode" name="zonecode" placeholder="우편번호">
								<input type="text" id="sample6_address" name="address" placeholder="주소">
								<input type="text" id="sample6_detailAddress" name="addressSub" placeholder="상세주소">
							</div>
							<div class="adr04">
								<input type="button" onclick="adrSearch()" value="재검색">
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<div class="radioBox">
							<div><input type="radio" name="gender" value="m" checked> <span>남자</span></div>
							<div><input type="radio" name="gender" value="w"> <span>여자</span></div>				
						</div>
					</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input type="date"	class="inputText"  name="birthday" placeholder="생년월일"></td>
				</tr>
				<tr>
					<th>추가입력 사항</th>
					<td class="checkBox_1">
						<div class="radioBox">
							<div><input type="checkBox" id="eventId" name="evnetId"> <label for="">추천인 아이디</label></div>
						</div>
						<div id="eventIdView">
							<input type="text" class="inputText" placeholder="추천하실 아이디를 입력해주세요.">
							<div class="subEventP">
								<p>추천인 아이디와 참여 이벤트명 중 하나만 선택 가능합니다.</p>
								<p>가입 이후 수정 불가합니다.</p>
							</div>	
						</div>
					</td>
				</tr>
				<tr style="border-top:3px solid #396ef3; vertical-align: baseline;">
					<th style="vertical-align: top; padding-top: 35px;">이용약관동의 <span class="required">ㆍ</span></th>
					<td class="checkBox_1">
						<input type="hidden" id="requiredBox">
						<div class="box_02">
						    <div>
						        <input type="checkbox" id="check_all" class="">
						        <label for="">올디프 서비스약관에 모두 동의합니다.</label>
						    </div>
						    <div>
						        <input type="checkbox" id="check_1" name="privateUtilizationFl" value="" onclick="check(this)" class="check">
						        <label for="">이용약관 동의 <span>(필수)</span></label>
						        <a href="#">약관보기&nbsp;&nbsp;&nbsp;></a>
						    </div>
						    <div>
						        <input type="checkbox" id="check_3" name="privateApprovalFl" value="" class="check" onclick="check(this)">
						        <label for="">개인정보수집 및 이용약관 동의 <span>(필수)</span></label>
						        <a href="#">약관보기&nbsp;&nbsp;&nbsp;></a>
						    </div>
						    <div>
						        <input type="checkbox" id="check_4" name="privateFinanceFl" value="" class="check" onclick="check(this)">
						        <label for="">전자금융거래 이용약관 동의 <span>(필수)</span></label>
						        <a href="#">약관보기&nbsp;&nbsp;&nbsp;></a>
						    </div>
						    <div>
						        <input type="checkbox" id="check_5" name="privateOfferFl" value="" class="check" onclick="check(this)">
						        <label for="">개인정보 제 3자 제공 동의 <span>(필수)</span></label>
						        <a href="#">약관보기&nbsp;&nbsp;&nbsp;></a>
						    </div>
						    <div>
						        <input type="checkbox" id="check_2" name="under14ConsentFl" value="" class="check" onclick="check(this)"> 
						        <label for="">만 14세 이상입니다. <span>(필수)</span></label>
						        <a href="#">약관보기&nbsp;&nbsp;&nbsp;></a>
						    </div>
						</div>
					</td>
				</tr>		
			</table>
			<div class="join_submit"><input type="button" id="join_submit" value="가입하기"></div>
		</form>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/member/joinImpromation.js"></script>
	</div>
</div>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>