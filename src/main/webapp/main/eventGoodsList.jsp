<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/goods/event.css">
<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
<div class="wrap">
    <div class="cont_top">
        <div class="top_banner">
           <div class="event_click">
            <div class="click">
            	<ul>
            		<li><a href="#slide1">이벤트이벤트</a></li>
            		<li><a href="#slide2">이벤트이벤트</a></li>
            		<li><a href="#slide3">이벤트이벤트</a></li>
            		<li><a href="#slide4">이벤트이벤트</a></li>
            		<li><a href="#slide5">이벤트이벤트</a></li>
            	</ul>
            </div>
        </div>
        <div class="slider_container">
            <!-- Slider main container -->
            <div class="swiper-container">
                <div class="swiper-wrapper">
                  <div class="swiper-slide" id="slide1">Slide 1</div>
                  <div class="swiper-slide" id="slide2">Slide 2</div>
                  <div class="swiper-slide" id="slide3">Slide 3</div>
                  <div class="swiper-slide" id="slide4">Slide 4</div>
                  <div class="swiper-slide" id="slide5">Slide 5</div>
                </div>
                <!-- Add Arrows -->
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
        </div> 
        </div>
        <div class="top_img">
            <img src="" alt="">
            <img src="" alt="">
            <img src="" alt="">
            <img src="" alt="">
            <img src="" alt="">
        </div>
        
        <div class="line">
          <div class="line_box"></div>   
        </div>
       
        <div class="title title02">
            <a href=""><h1>이벤트 기획 상품 &gt;&gt;</h1></a>
        </div>
    </div>
    <div class="content">
        <div class="content_box">
            <!-- 상품 -->
			<jsp:include page="../goods/headerCategoryGoodsList.jsp" flush="false"/>
        </div>
    </div>
</div>

<script>
    var swiper = new Swiper('.swiper-container', {
        loop:true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });

    var swiper = new Swiper('.swiper-container02', {
        
        slidesPerView: 1,
        spaceBetween: 10,
        // init: false,
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        breakpoints: {
            640: {
            slidesPerView: 2,
            spaceBetween: 20,
            },
            768: {
            slidesPerView: 4,
            spaceBetween: 40,
            },
            1024: {
            slidesPerView: 5,
            spaceBetween: 50,
            },
        }
	});
</script>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>