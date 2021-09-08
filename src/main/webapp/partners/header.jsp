<%@page import="dto.memberDTO.memberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>올디프 파트너스 어드민 페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/partners/reset.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/partners/header.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">
<!-- 	차트 		-->
<script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<script src="https://d3js.org/d3.v3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>

<script type="text/javascript">
	function logout() {
		var a = confirm('로그아웃 하시겠습니까?');
		if (a) {
			location.href = "${pageContext.request.contextPath}/partners/Logout";
		}
	}
</script>
</head>
<%
	memberDTO mdto = (memberDTO) session.getAttribute("mem");
	if (mdto == null) {
		System.out.println(request.getRequestURL());
		session.setAttribute("url", request.getRequestURL());
%>
<script type="text/javascript">
	alert('로그인이 필요한 페이지입니다.');
	location.href = '${pageContext.request.contextPath}/partners/login';
</script>
<%
	} else {
%>
<body>
	<div id="load">
		<img
			src="${pageContext.request.contextPath }/fileF/partners/loading.gif"
			alt="loading">
	</div>
	<div class="header">
		<header class="clearfix">
		<div class="inner">
			<div class="menu_icon">
				<span></span> <span></span> <span></span>
			</div>
			<h1 class="partners_logo">
				<a href="${pageContext.request.contextPath}/partners/Main"> </a>
			</h1>
			<ul class="top_menu clearfix">
				<li><a href="#">파트너스</a></li>
				<li><a href="#">문의하기</a></li>
				<li><a onclick="logout();">로그아웃</a></li>
				<span class="user_name"><%=mdto.getMemNm()%><span>님</span></span>
			</ul>
		</div>
		</header>
		<nav class="main_menu">
		<ul class="menu_li_title main_menu01 clearfix" id="menu01">
			<li><a href="">내 정보</a></li>
		</ul>
		<ul class="inner_menu" id="list01" style="display: none;">
			<li><a href="">정보 수정</a></li>
			<li><a href="">마이샵 관리</a></li>
		</ul>
		<ul class="menu_li_title main_menu02 clearfix" id="menu02">
			<li><a href="#" class="color">상품
					관리</a></li>
		</ul>
		<ul class="inner_menu" id="list02" style="display: none;">
			<li><a
				href="${pageContext.request.contextPath }/partners/goods/insert">상품
					등록</a></li>
			<li><a href="">상품 일괄 등록</a></li>
			<li><a href="">통합솔루션 연동</a></li>
			<li><a
				href="${pageContext.request.contextPath}/partners/goods/list">상품
					조회/수정</a></li>
			<li><a
				href="${pageContext.request.contextPath}/partners/goods/applySpecialPrice">특가
					참여/신청</a></li>
		</ul>
		<ul class="menu_li_title main_menu03 clearfix" id="menu03">
			<li><a href="#" class="color">주문배송</a></li>
		</ul>
		<ul class="inner_menu" id="list03" style="display: none;">
			<li><a
				href="${pageContext.request.contextPath}/partners/order/order">주문
					관리</a></li>
			<li><a
				href="${pageContext.request.contextPath}/partners/order/delivery">배송
					현황</a></li>
			<li><a
				href="${pageContext.request.contextPath}/partners/order/return">반품
					관리</a></li>
			<li><a
				href="${pageContext.request.contextPath}/partners/order/exchange">교환
					관리</a></li>
			<li><a
				href="${pageContext.request.contextPath}/partners/order/cancel">취소
					관리</a></li>
		</ul>
		<ul class="menu_li_title main_menu04 clearfix" id="menu04">
			<li><a href="#" class="color">정산</a></li>
		</ul>
		<ul class="inner_menu" id="list04" style="display: none;">
			<li><a
				href="${pageContext.request.contextPath}/partners/calculate/salesStatus">매출
					현황</a></li>
			<li><a href="">지급 내역</a></li>
			<li><a
				href="${pageContext.request.contextPath}/partners/calculate/tax">세금계산서</a></li>
			<li><a
				href="${pageContext.request.contextPath}/partners/calculate/surtax">부가세
					신고내역</a></li>
			<li><span><a href="">정산주기 관리</a></li>
		</ul>
		<ul class="menu_li_title main_menu05 clearfix" id="menu05">
			<li><a href="#" class="color">문의관리</a></li>
		</ul>
		<ul class="inner_menu" id="list05" style="display: none;">
			<li><a href="">고객문의</a></li>
			<li><a href="">구매후기</a></li>
		</ul>
		<ul class="menu_li_title main_menu06 clearfix" id="menu06">
			<li><a href="#" class="color">올디프AD</a></li>
		</ul>
		<ul class="inner_menu" id="list06" style="display: none;">
			<li><a href="">광고 현황</a></li>
			<li><a href="">광고 관리</a></li>
			<li><a
				href="${pageContext.request.contextPath}/partners/ads/bizMoney">비즈머니</a></li>
		</ul>
		<ul class="menu_li_title main_menu07 clearfix" id="menu07">
			<li><a href="#" class="color">프로모션</a></li>
		</ul>
		<ul class="inner_menu" id="list07" style="display: none;">
			<li><a href="">쿠폰 관리</a></li>
			<li><a href="">쿠폰 사용조회</a></li>
			<li><a href="">파트너스 쿠폰 관리</a></li>
			<li><a href="">프로모션 신청</a></li>
		</ul>
		<ul class="menu_li_title main_menu08 clearfix" id="menu08">
			<li><a href="#" class="color">공지사항</a></li>
		</ul>
		<ul class="inner_menu" id="list08" style="display: none;">
			<li><a
				href="${pageContext.request.contextPath}/partners/notice/list?no=1&theme=all">공지사항</a></li>
			<li><a
				href="${pageContext.request.contextPath}/partners/notice/fqa?cate=all">자주
					묻는 질문</a></li>
			<li><a
				href="${pageContext.request.contextPath}/partners/notice/qalist?cate=all">1:1
					문의</a></li>
		</ul>
		</nav>
	</div>
	<script src="${pageContext.request.contextPath }/js/partners/header.js"></script>
	<script>
		function endLoading() {
			document.getElementById("load").style.display = "none";
		}

		function startLoading() {
			document.getElementById("load").style.display = "block";
		}
	</script>
	<%
		}
	%>