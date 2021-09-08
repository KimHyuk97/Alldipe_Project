<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/goods/free_del.css">



<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>

<div class="wrap">
    <div class="cont_top">
        <div class="title">
            <a href=""></a>
             <h1>무료배송 &gt;&gt;</h1>
        </div>
        <div class="slider_container">
            <div class="slider_txt">
               <p>3만원 이상 결제 시 <br> 무료배송 이용 가능</p> 
            </div>
            <!-- Slider main container -->
            <div class="swiper-container">
                <div class="swiper-wrapper">
                  <div class="swiper-slide">Slide 1</div>
                  <div class="swiper-slide">Slide 2</div>
                  
                </div>
                <!-- Add Arrows -->
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
              </div>
        </div>
        <div class="line_box"></div>
    </div>
    <div class="content">
		<!-- 상품 -->
		<jsp:include page="../goods/headerCategoryGoodsList.jsp" flush="false"/>
    </div>
</div>

<script>
    var swiper = new Swiper('.swiper-container', {
      loop: true,
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
    });
</script>

<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>