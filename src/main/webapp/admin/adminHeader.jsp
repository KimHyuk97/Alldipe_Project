<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>올디프관리자 페이지</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/fileF/header/alldipeIcon.ico" type="image/x-icon"/>
    <!--css-->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">
    <script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/main/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/main/admin_head.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/main/admin_footer.css">

</head>

<body>
    <!--header-->
    <div class="header">
        <header>
	         <div>
	             <a onclick="location.href='${pageContext.request.contextPath}/admin/Main';"><img src="${pageContext.request.contextPath}/admin/adminfile/alldipelog.png" width="130px"></a>
	         </div>

	          <ul class="top_menu">
	              <li><span class="user_name">${sessionScope.admin.name}<span>님</span></span></li>
	              <li><a href="${pageContext.request.contextPath}/partners/login">파트너스</a></li>
	              <li><a href="#">문의하기</a></li>
	              <li><a onclick="logout()">로그아웃</a></li>
	          </ul>
	      
        </header>
        <nav class="main_menu">
            <ul class="main_menu01 clearfix" id="menu01">
                <li><a href="#" class="color"><i class="fas fa-cog"></i> 상품관리 <i class="fas fa-chevron-down"></i></a>
                </li>
            </ul>
            <ul class="inner_menu" id="list01" style="display: none;">
                <li><a href="${pageContext.request.contextPath}/admin/goods/goodsList">상품리스트</a> </li>
                <li><a href="${pageContext.request.contextPath}/admin/goods/goodsClassify">상품분류관리</a> </li>
                <li><a href="${pageContext.request.contextPath}/admin/goods/goodsReview">상품후기관리</a> </li>
            </ul>
            <ul class="main_menu01 clearfix" id="menu02">
                <li><a href="#" class="color"><i class="fas fa-cog"></i> 주문관리 <i class="fas fa-chevron-down"></i></a>
                </li>
            </ul>
            <ul class="inner_menu" id="list02" style="display: none;">
                <li><a href="${pageContext.request.contextPath}/admin/order/order">주문통합리스트</a>  </li>
                <li><a href="${pageContext.request.contextPath}/admin/order/orderManagement">취소/교환/반품관리</a> </li>
                <li><a href="${pageContext.request.contextPath}/admin/order/cash">현금영수증 관리</a> </li>
            </ul>
            <ul class="main_menu01 clearfix" id="menu03">
                <li><a href="#" class="color"><i class="fas fa-cog"></i> 회원관리 <i class="fas fa-chevron-down"></i></a>
                </li>
            </ul>
	         <ul class="inner_menu" id="list03" style="display: none;">
    	         <li><a href="${pageContext.request.contextPath}/admin/member/memberList">회원리스트</a> </li>
        	     <li><a href="${pageContext.request.contextPath}/admin/member/memberGrade">등급관리</a> </li>
            	 <li><a href="${pageContext.request.contextPath}/admin/member/memberGradeInsert">등급등록</a> </li>
            	 <li><a href="${pageContext.request.contextPath}/admin/member/memberMileage">적립금 관리</a> </li>
            	 <li><a href="${pageContext.request.contextPath}/admin/member/memberDeposit">올페이 관리</a> </li>
         	</ul>
            <ul class="main_menu01 clearfix" id="menu04">
                <li><a href="#" class="color"><i class="fas fa-cog"></i> 공급사관리 <i class="fas fa-chevron-down"></i></a>
                </li>
            </ul>
            <ul class="inner_menu" id="list04" style="display: none;">
                <li><a href="${pageContext.request.contextPath}/admin/scm/scmList">공급사관리</a> </li>
                <li><a href="${pageContext.request.contextPath}/admin/scm/applyScmList">입점 요청 공급사</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/goods/applyList">상품승인관리</a> </li>
                <li><a href="${pageContext.request.contextPath}/admin/scm/balance">정산관리</a> </li>
                <li><a href="${pageContext.request.contextPath}/admin/scm/publishList">세금계산서 요청</a> </li>
                <li><a href="${pageContext.request.contextPath}/admin/scm/taxList">세금계산서 발행</a> </li>
            </ul>
            <ul class="main_menu01 clearfix" id="menu05">
                <li><a href="#" class="color"><i class="fas fa-cog"></i> 프로모션 <i class="fas fa-chevron-down"></i></a>
                </li>
            </ul>
            <ul class="inner_menu" id="list05" style="display: none;">
                <li><a href="${pageContext.request.contextPath}/admin/promotion/couponList">쿠폰</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/promotion/couponAdd">쿠폰등록</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/promotion/couponList2">응모권관리</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/promotion/promotion">프로모션 등록/수정</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/promotion/plan">기획전</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/promotion/timeSale">타임세일관리</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/promotion/attend">출석체크관리</a> </li>
            </ul>
            <ul class="main_menu01 clearfix" id="menu06">
                <li><a href="#" class="color"><i class="fas fa-cog"></i> 통계 <i class="fas fa-chevron-down"></i></a>
                </li>
            </ul>
		    <ul class="inner_menu" id="list06" style="display: none;">
		        <li><a href="${pageContext.request.contextPath}/admin/status/visitStatus">방문자분석</a> </li>
		        <li><a href="${pageContext.request.contextPath}/admin/status/salesStatus">매출분석</a> </li>
		        <li><a href="${pageContext.request.contextPath}/admin/status/memberStatus">회원분석</a> </li>
		        <li><a href="${pageContext.request.contextPath}/admin/status/pointStatus">포인트분석</a> </li>
		    </ul>
            <ul class="main_menu01 clearfix" id="menu07">
                <li><a href="#" class="color"><i class="fas fa-cog"></i> 게시판 <i class="fas fa-chevron-down"></i></a>
                </li>
            </ul>
            <ul class="inner_menu" id="list07" style="display: none;">
                <li><a href="${pageContext.request.contextPath}/admin/board/boardList">게시글관리</a> </li>
                <li><a href="${pageContext.request.contextPath}/admin/board/notice">공지사항 작성</a></li>
            </ul>
            <ul class="main_menu01 clearfix" id="menu08">
                <li><a href="#" class="color"><i class="fas fa-cog"></i> 올디프AD관리 <i class="fas fa-chevron-down"></i></a></li>
            </ul>
            <ul class="inner_menu" id="list08" style="display: none;">
                <li><a href="${pageContext.request.contextPath}/admin/ad/ad">광고관리</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/ad/biz">비즈머니 충전내역</a> </li>
            </ul>
            <ul class="main_menu02 clearfix">
                <li><a href="${pageContext.request.contextPath}/index?displayFl=y">올디프 바로가기</a></li>
                <li><a href="${pageContext.request.contextPath}/partners/login">올디프 파트너스</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/ad/ad">올디프 AD</a></li>
            </ul>
        </nav>
    </div>
    <script src="${pageContext.request.contextPath}/admin/js/main/main.js"></script>
    <!--//header-->
    