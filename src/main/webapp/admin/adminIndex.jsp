<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>올디프 관리자페이지</title>
<link href="${pageContext.request.contextPath}/admin/css/admin/adminlogin.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="adminContainer">
		<div class="adminModal">
			<div class="adminLog">
				<img src="${pageContext.request.contextPath}/admin/adminfile/alldipelog.png">
			</div>
			<form action="./loginAction" method="post" name="loginAction">
				<div class="adminContent">
					<div class="adminText">
						<div>
							<p>아이디</p>
							<input type="text" name="id" id="id"  placeholder="아이디를 입력해주세요.">
						</div>
						<div>
							<p>비밀번호</p>
							<input type="password" name="pw" id="pw" placeholder="비밀번호를 입력해주세요." onkeyup="enterkey()">					
						</div>
						<div class="loginCheck"><input type="checkBox" id="checkOn">아이디저장</div>
					</div>
					<div><input type="button" id="loginBtn" value="로그인"></div>
				</div>
			</form>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/admin/js/admin/login.js"></script>
</body>
</html>