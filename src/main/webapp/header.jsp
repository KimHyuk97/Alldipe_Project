<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>올디프</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/fileF/header/alldipeIcon.ico" type="image/x-icon"/>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">
<link href="${pageContext.request.contextPath}/css/index/reset.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/index/header.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/index/footer.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/index/index.css" rel="stylesheet" type="text/css">
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- kakao -->
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>
	<div id="load">
		<img
			src="${pageContext.request.contextPath }/fileF/partners/loading.gif"
			alt="loading">
	</div>
<div class="header_popup"><a href="#">지금 가입하고 'TOP 10 인기상품' 100원에 받아가세요!</a><span class="popup_close">x</span></div>
<div class="container">
	<div id="header">
		<div class="header_main">
			<div class="header_width">
				<!--sub_meun-->
				<div class="sub_meun">
					<ul class="sub_meun01">
						<li><a href="#">즐겨찾기</a></li>
						<li><a href="${pageContext.request.contextPath}/partners/login">파트너센터</a></li>
						<li><a href="#">입점신청</a></li>
					</ul>
					<ul class="sub_meun02">
						<c:choose>
							<c:when  test="${empty sessionScope.mem.memId}">
								<li><a href="${pageContext.request.contextPath}/login">로그인</a></li>
								<li><a href="${pageContext.request.contextPath}/join">회원가입</a></li>
								<li><a id="cart">장바구니</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
								<li><a href="${pageContext.request.contextPath}/myPage">마이페이지</a></li>
								<li><a href="${pageContext.request.contextPath}/cart?memNo=${sessionScope.mem.memNo}">장바구니</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				
				
				<!-- main_meun -->
				<div class="main_meun">
					<!-- 메인로그  -->
					<div><a href="${pageContext.request.contextPath}/index?displayFl=y"><img src="${pageContext.request.contextPath}/fileF/header/alldipe.png"></a></div>
					
					<!-- 검색기능  -->
					<div data-no="${sessionScope.mem.memNo}" id="memNumber" style="position:relative;"> 
						<form action="search" method="get" name="searchForm" style="position:relative;" autocomplete="off">
							<input type="hidden" name="memNo" id="memNoVal" value="${sessionScope.mem.memNo}"><input type="hidden" name="siteKey" id="siteKeyVal">
							<input type="search" name="keyword"  placeholder="올디프에서 검색하세요!" class="headerSearch" id="keyword">
							<input type="button" id="searchBtn" class="headerSearchImage" class="area">
						</form>
						<!-- 최근검색어 or 인기검색어 -->
						<div id="searchTerms">
							<!-- 최근검색어 -->
							<div id="latestkeyword">
								
							</div>
							<!-- 인기검색어 -->
							<div id="bestKeyword">
								
							</div>
						</div>
					</div>
					
					<!-- 카드혜택 롤링배너 -->
					<div class="header_rolling">
					    <div class="header_rolling02">
						   <div><img src="${pageContext.request.contextPath}/fileF/header/headerRollingimage1.png"></div>
						   <div><img src="${pageContext.request.contextPath}/fileF/header/headerRollingimage2.png"></div>
						</div>
						<button class="header_leftBtn"><img src="${pageContext.request.contextPath}/fileF/header/leftBtn.png"></button>
						<button class="header_rightBtn"><img src="${pageContext.request.contextPath}/fileF/header/rightBtn.png"></button>
					</div>
				</div>
			</div>
			
			
			<!-- cate -->
			<div class="main_cate">
				<ul class="main_cate_ul">
					<li id="cate_view">전체카테고리</li>
					<li><a id="special" href="${pageContext.request.contextPath}/main/special">특딜</a></li>
					<li><a id="new" href="${pageContext.request.contextPath}/main/new">신상</a></li>
					<li><a id="best" href="${pageContext.request.contextPath}/goodsList?cateCd=3">베스트</a></li>
					<li><a id="freeD" href="${pageContext.request.contextPath}/goodsList?cateCd=4">무료배송</a></li>
					<li><a id="festival" href="${pageContext.request.contextPath}/goodsList?cateCd=5">페스티벌</a></li>
					<li><a id="event" href="${pageContext.request.contextPath}/goodsList?cateCd=6">이벤트</a></li>
					<li><a id="brand" href="${pageContext.request.contextPath}/goodsList?cateCd=7">브랜드관</a></li>
				</ul>
			</div>
			<div class="categoryList">
				<!-- 대분류 -->
				<div class="sub_cate">		
				</div>
				<!-- 중분류 -->
				<div class="medium_cate">
				</div>
				<!-- 소분류 -->
				<div class="small_cate">
				</div>
			</div>
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/header/header.js"></script>
		</div>
	</div>
