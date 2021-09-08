<%@page import="dto.memberDTO.memberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/goods/new.css">
<%
	memberDTO mem = (memberDTO)session.getAttribute("mem");
%>
<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
<div class="wrap">
    <div class="cont_top">
        <div class="title">
            <h1>신상품 추천</h1>
            <p>< 판매자 AD</p>
        </div>
        <!-- Swiper -->
	    <div class="swiper-container">
	        <div class="swiper-wrapper">
	            <div class="swiper-slide">Slide 1</div>
	            <div class="swiper-slide">Slide 2</div>
	            <div class="swiper-slide">Slide 3</div>
	            <div class="swiper-slide">Slide 4</div>
	        </div>
	        <!-- Add Pagination -->
	        <div class="swiper-pagination"></div>
	        <!-- Add Arrows -->
	        <div class="swiper-button-next"></div>
	        <div class="swiper-button-prev"></div>
	    </div>
	    <div class="image_box">
	        <img src="" alt="">
	    </div>    
    </div>
    
	<!-- 상품 -->
	<div style="width: 1200px; margin: 0 auto; display: flex; flex-wrap: wrap;">
		<input type="hidden" id="memNo" value="<%=(mem!=null)?true:false%>">
		<input type="hidden" id="adultFl" value="<%=(mem!=null)?mem.isAdultFl():false %>">
		<ul id="item_list" class="clearfix">
			
		</ul>
	</div>
	<script src="${pageContext.request.contextPath }/js/goods/goodsList.js"></script>
</div>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>
<script>
    var swiper = new Swiper('.swiper-container', {
      slidesPerView: 3,
      spaceBetween: 30,
      slidesPerGroup: 3,
      loop: true,
      loopFillGroupWithBlank: true,
      pagination: {
        el: '.swiper-pagination',
        clickable: true,
      },
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
    }); 
    
    window.onload = function(){
		endLoading();
		setList('70');
	}
	document.addEventListener('scroll', function() {
		addList('70');
	});
</script>