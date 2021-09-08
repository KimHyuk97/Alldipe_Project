<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/goods/best.css">

<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>

<div class="wrap">
    <div class="cont_top">
        <div class="title">
            <a href="#"><h1>이날의 베스트 &gt;&gt;</h1></a>
             <p>베스트광고?</p>
        </div>
        <div class="slider_container">
            <!-- Slider main container -->
            <div class="swiper-container">
                <div class="swiper-wrapper">
                  <div class="swiper-slide">Slide 1</div>
                  <div class="swiper-slide">Slide 2</div>
                  <div class="swiper-slide">Slide 3</div>
                  <div class="swiper-slide">Slide 4</div>
                </div>
                <!-- Add Arrows -->
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
        </div>
        <div class="line_box"></div>
        <div class="best100">
            <p>베스트 TOP 100</p>
        </div>
        <div class="top_menu">
            <div class="icon_box">
            	<ul>
            		<li value="3">전체</li>
            		<li value="1">패션/잡화</li>
            		<li value="2">뷰티</li>
            		<li value="3">출산/유아동</li>
            		<li value="4">식품</li>
            		<li value="5">가구/인테리어</li>
            		<li value="6">가전/디지털</li>
            		<li value="7">주방/생활용품</li>
            		<li value="1">스포츠/자동차</li>
            		<li value="2">문구/취미</li>
            		<li value="3">여행/E-티켓</li>
            	</ul>
            </div>
        </div>
    </div>
    <div class="content" id="goodsContents" style="width: 1200px; margin: 100px auto; display: flex; flex-wrap: wrap;">
		<jsp:include page="../goods/headerCategoryGoodsList.jsp" flush="false"/>
    </div>
    <script>
    let li = document.querySelectorAll('.icon_box ul li');
    for(var i = 0; i < li.length; i++) {
    	li[i].addEventListener('click',function(e){
    		$.ajax({
    			type:"get",
    			url:"goodsBestList",
    			data:{
    				cateCd : e.target.value,
    			},
    			dataType:"json",
    			success:function(result){
    				goodsList(result);
    			},
    			error:function(){
    				
    			}
    		});
    	});
    }
    
    function goodsList(s){
    	var o = "";
    	o += "<div>"
		for(var i = 0; i < s.length; i++){
			o += "<div class='content_box'>";
			o += "<a href='goodsView?goodsNo="+s[i].goodsNo+"'>";
			o += "<img src='"+s[i].representImg+"'>";
			o += "<div class='content_txt'>";
			o += "<div class='content_title'>"+s[i].goodsNm+"</div>";
			o += "<div class='price'>";
			o += "할인혜택가 <span>"+s[i].goodsDiscountPercent+"%</span> <p>"+s[i].goodsPrice+"원</p>  <span>"+s[i].fixedPrice+"원</span>";
			o += "</div>";
    	    o += "<div class='mark'>";
    	    o += "<p>페스티벌</p>";
    	    o += "<p>무료배송</p>";
    	    o += "</div>";
    	    o += "</div> ";
    	    o += "</a>";
    	    o += "</div>";
		}
    	o += "</div>";
	    document.getElementById('goodsContents').innerHTML = o;
    }
    </script>
</div>
<script>
    var swiper = new Swiper('.swiper-container', {
      slidesPerView: 3,
      spaceBetween: 30,
      slidesPerGroup: 3,
      loop: false,
      loopFillGroupWithBlank: false,
      pagination: {
        el: '.swiper-pagination',
        clickable: true,
      },
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
    }); 
    
</script>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>
