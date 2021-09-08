<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/myPage/myPage.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.mem.memNo}">
	<script>
		window.history.back();
	</script>
</c:if>
<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>

<div class="myPageContainer">
	<div class="myPageImpormation">
		<ul class="impormationBox">
			<li>
				<div><div style="border:1px solid; width:50px; height:50px;"></div></div>
				<div class="memberContent">
					<div><p class="text01"><span class="text02">회원</span>님</p></div>
					<div><p class="text01">적립 5%</p><p class="text01">최초 1회 무료배송</p></div>
					<div class="memberImpormationBtn">
						<span id="memberGrade">전체등급 보기</span><span id="title06">정보수정</span>
					</div>
				</div>
			</li>
			<li id="c"><p class="text01">쿠폰</p><a class="text02" href="#">0개</a></li>
			<li id="p"><p class="text01">적립금</p><a class="text02" href="#" ><span id="mileage"></span>원</a></li>
			<script>
				let mileage = Math.floor(${sessionScope.mem.mileage});
				document.getElementById('mileage').innerHTML = mileage;
			</script>
		</ul>
	</div>
	<div class="myPageImpormation02">
		<div class="myPageTitle">
			<div class="myPageTitle02"><h1 class="">마이페이지</h1></div>	
			<ul class="myPageTitle03">
				<li id="title01" class="on">주문내역<span class="go">></span></li>
				<li id="title02">배송지 관리<span class="go">></span></li>
				<li id="title03">상품 후기<span class="go">></span></li>
				<li id="title04">쿠폰<span class="go">></span></li>
				<li id="title05">적립금<span class="go">></span></li>
			</ul>
			<div class="myPageAsk">
				<div><p class="text03">도움이 필요하신가요?</p><span class="text04">1:1문의하기</span></div>
				<div><span class="go">></span></div>
			</div>
		</div>
		<div class="myPageContent">
			<div id="content01">
				<jsp:include page="myPageOrderGoods.jsp" flush="false"/>
			</div>
			<div id="content02">
				<jsp:include page="myPageDelivery.jsp" flush="false"/>
			</div>
			<div id="content03">
				<jsp:include page="myPageReview.jsp" flush="false"/>
			</div>
			<div id="content04">
				<jsp:include page="myPageCoupon.jsp" flush="false"/>
			</div>
			<div id="content05">
				<jsp:include page="myPagePoint.jsp" flush="false"/>
			</div>
			<div id="content06">
				<jsp:include page="myPageMemberImpromation.jsp" flush="false"/>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myPage/myPage.js"></script>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>