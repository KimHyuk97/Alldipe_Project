<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파트너스 회원가입 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/partners/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/partners/font.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/partners/partners_join.css">
<script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/partners/partners_join.js"></script>
</head>
<body>
	<div class="top_bg">
        <h1 class="partners_logo">
            <img src="${pageContext.request.contextPath}/fileF/partners/partnersLogo.png" alt="올디프 파트너스 로고">
        </h1>
    </div>
    <h2 class="page_title">회원가입</h2>
    <div class="inner">
        <form action="./agreement" method="post" name="form_name" class="join_form">
            <div class="box_02">
                <div>
                    <p>이용약관동의 </p>
                    <input type="checkbox" id="d" name="a" class="all_check">
                    <label for="">올디프 파트너스약관에 모두 동의합니다.</label>
                </div>
                <div>
                    <input type="checkbox" id="" name="approval1" class="">
                    <label>위탁판매 이용약관 동의 <span>(필수)</span></label>
                    <a href="#">약관보기&nbsp&nbsp&nbsp></a>
                </div>
<!--                 <div> -->
<!--                     <input type="checkbox" id="" name="a" class=""> -->
<!--                     <label>오픈마켓 서비스 이용정책 동의 <span>(필수)</span></label> -->
<!--                     <a href="#">약관보기&nbsp&nbsp&nbsp></a> -->
<!--                 </div> -->
                <div>
                    <input type="checkbox" id="" name="approval2" class="">
                    <label>개인정보 수집 및 이용 동의 <span>(필수)</span></label>
                    <a href="#">약관보기&nbsp&nbsp&nbsp></a>
                </div>
                <div>
                    <input type="checkbox" id="" name="approval3" class="">
                    <label>회원 이번트 약관동의 <span>(필수)</span></label>
                    <a href="#">약관보기&nbsp&nbsp&nbsp></a>
                </div>
                    <input type="submit" value="가입하기" class="partners_join_btn">
            </div>
        </form>
    </div>
</body>
</html>