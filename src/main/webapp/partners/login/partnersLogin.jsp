<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파트너스 로그인 페이지</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/reset.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/partners_login.css">
    <script src="${pageContext.request.contextPath }/js/jquery/jquery.min.js"></script>

</head>

<body>
    <div class="top_bg">
        <h1 class="partners_logo">
        </h1>
    </div>
    <h2 class="page_title">로그인</h2>
    <div class="inner">
        <form action="./LoginAction" method="post" class="login_form">
        <div>
            <input type="text" id="" name="id" placeholder="아이디를 입력해주세요."  class="user_id">
        </div>
        <div>
            <input type="password" id="" name="pw" placeholder="비밀번호를 입력해주세요."  class="user_pw">
        </div>
        <div class="user_info">
            <input type="checkbox" id="" name="" class="login_checkbox">
            <label>자동저장</label>
            <span class="id_find"><a href="#">아이디 찾기</a></span>
            <span class="pw_find"><a href="#">비밀번호 찾기</a></span>
        </div>
        <div>
            <input type="submit" value="로그인" class="login_btn">
        </div>
        <div class="join_btn">
            <a href="#">회원가입</a>
        </div>
    </form>
    </div>
</body>
</html>