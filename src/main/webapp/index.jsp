<%@page import="dto.themeDTO.themeDTO"%>
<%@page import="dto.brandDTO.brandDTO"%>
<%@page import="dto.memberDTO.memberDTO"%>
<%@page import="dto.goodsDTO.goodsDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="css/swiper/swiper.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	memberDTO mem = (memberDTO)session.getAttribute("mem");
	ArrayList themeList = (ArrayList)session.getAttribute("themeList");
	ArrayList goodsList = (ArrayList)session.getAttribute("goodsList");
	brandDTO brand = (brandDTO)session.getAttribute("brand");
	String cate = (String)request.getAttribute("cate");
%>
<!--상단-->
<jsp:include page="header.jsp" flush="false"/>
<!-- Swiper CSS-->
 <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<!-- Swiper JS -->
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery-ui.min.js"></script>
<script src="js/jquery/script.js"></script>
<!-- 메인롤링슬라이드 -->
<div id="mainrollingColor" style="position:relative;">
    <div class="mainSwiper">
        <ul class="mainSwiperUl">
            <li class="mainSwiperLi"><a href="#"><img src="./fileF/index/mainRolling/mainRolling13.jpg" width="100%"></a></li>
			<li class="mainSwiperLi"><a href="#"><img src="./fileF/index/mainRolling/mainRolling12.jpg" width="100%"></a></li>
			<li class="mainSwiperLi"><a href="#"><img src="./fileF/index/mainRolling/mainRolling11.jpg" width="100%"></a></li>
            <li class="mainSwiperLi"><a href="#"><img src="./fileF/index/mainRolling/mainRolling10.jpg" width="100%"></a></li>
			<li class="mainSwiperLi"><a href="#"><img src="./fileF/index/mainRolling/mainRolling09.jpg" width="100%"></a></li>
            <li class="mainSwiperLi"><a href="#"><img src="./fileF/index/mainRolling/mainRolling08.jpg" width="100%"></a></li>
            <li class="mainSwiperLi"><a href="#"><img src="./fileF/index/mainRolling/mainRolling07.jpg" width="100%"></a></li>
            <li class="mainSwiperLi"><a href="#"><img src="./fileF/index/mainRolling/mainRolling06.jpg" width="100%"></a></li>
            <li class="mainSwiperLi"><a href="#"><img src="./fileF/index/mainRolling/mainRolling05.jpg" width="100%"></a></li>
            <li class="mainSwiperLi"><a href="#"><img src="./fileF/index/mainRolling/mainRolling04.jpg" width="100%"></a></li>
            <li class="mainSwiperLi"><a href="#"><img src="./fileF/index/mainRolling/mainRolling03.jpg" width="100%"></a></li>
            <li class="mainSwiperLi"><a href="#"><img src="./fileF/index/mainRolling/mainRolling02.jpg" width="100%"></a></li>
			<li class="mainSwiperLi"><a href="#"><img src="./fileF/index/mainRolling/mainRolling01.png" width="100%"></a></li>
        </ul>
        <div class="mainSwiperBtn">
            <span class="spanMeun"><span id="liCount"></span> / <span id="liLength"></span><button onclick="stop()" class="playRolling"><img id="imgChange" src="./fileF/swiper/ongoing.png"></button></span>
            <button class="rollingView" onclick="view()">전체보기</button>
        </div>
		<div class="mainSwiperBtn2">
			<button id="mainPrev"><img src="./fileF/swiper/rollingPrev.png"></button>
			<button id="mainNext"><img src="./fileF/swiper/rollingNext.png"></button>
		</div>
    </div>
	<div id="viewController22">
		<div class="ulViewController">
			<ul class="ulView11">
					<ul class="ulView22">
						<li><a href="#"><img src="./fileF/index/mainRolling/mainRolling13.jpg"></a></li>
						<li><a href="#"><img src="./fileF/index/mainRolling/mainRolling12.jpg"></a></li>
						<li><a href="#"><img src="./fileF/index/mainRolling/mainRolling11.jpg"></a></li>
						<li><a href="#"><img src="./fileF/index/mainRolling/mainRolling10.jpg"></a></li>
						<li><a href="#"><img src="./fileF/index/mainRolling/mainRolling09.jpg"></a></li>
						<li><a href="#"><img src="./fileF/index/mainRolling/mainRolling08.jpg"></a></li>
						<li><a href="#"><img src="./fileF/index/mainRolling/mainRolling07.jpg"></a></li>
						<li><a href="#"><img src="./fileF/index/mainRolling/mainRolling06.jpg"></a></li>
						<li><a href="#"><img src="./fileF/index/mainRolling/mainRolling05.jpg"></a></li>
						<li><a href="#"><img src="./fileF/index/mainRolling/mainRolling04.jpg"></a></li>
						<li><a href="#"><img src="./fileF/index/mainRolling/mainRolling03.jpg"></a></li>
						<li><a href="#"><img src="./fileF/index/mainRolling/mainRolling02.jpg"></a></li>
					</ul>				
					<ul class="ulView22">
						<li><a href="#"><img src="./fileF/index/mainRolling/mainRolling01.png"></a></li>
					</ul>
			</ul>
			<button class="rollingViewClose" onclick="none()">닫기</button>
			<button id="mainPrev22"><img src="./fileF/swiper/rollingPrev.png"></button>
			<button id="mainNext22"><img src="./fileF/swiper/rollingNext.png"></button>
		</div>
	</div>
</div>
<script type="text/javascript" src="./js/swiper/swiper.js"></script>

<!-- 메인페이지  -->
<div class="wrap">
        <div class="inner">
            <!--이번주 핫딜-->
            <div class="section01 hot_deal">
                <p class="cont_tit">이번주 핫딜</p>
                <span class="all_view"><a href="#">전체보기</a></span>
                <div class="hot_deal_list_wrap clearfix ">
                    <!-- Swiper -->
                    <div class="swiper-container">
                        <div class="swiper-wrapper">
<!-- 	                       이번주 핫딜 -->
<%	
		ArrayList<goodsDTO> list = (ArrayList<goodsDTO>)goodsList.get(0);
		if(list.size()>0){
			System.out.println(list.size());
			for(goodsDTO dto : list){
				if(dto.getGoodsSellFl()>0){
%>
		                        	<a href="#" class="hot_deal_list swiper-slide">
		                                <figure>
		                                    <div class="img_warp">
		                                        <img src="<%=dto.getRepresentImg() %>" alt="">
		                                    </div>
		                                    <figcaption>
		                                        <p class="list_name">
		                                            <%=dto.getGoodsNm() %>
		                                        </p>
		                                        <p class="list_price">
		                                            <span class="discountRate">&dtrif;<%=dto.getDiscountPercent() %></span>
		                                            <%if(dto.isMemberOnly() && mem!=null){ %>
		                                            <span class="non_member_price">
		                                            	할인클럽
		                                            </span>
		                                            <%}else{ %>
		                                            <span class="before_price"><%=dto.getGoodsPrice() %> 원</span>
		                                            <span class="after_price"><%=dto.getFixedPrice() %>원</span>
		                                            <%} %>
		                                        </p>
		                                    </figcaption>
		                                </figure>
		                            </a> 
<!-- 	                       이번주 핫딜 -->
<%				}
			}
		}
%>
                        </div>
                        <!-- Add Arrows -->
                    </div>
                    <div class="swiper-button-next hot_deal_next"></div>
                    <div class="swiper-button-prev hot_deal_prev"></div>
                </div>
            </div>
            <div class="section02 clearfix">
                <!--특가브랜드데이-->
<%	themeDTO theme = (themeDTO)themeList.get(1); %>                
                <div class="brand_day">
                    <p class="cont_tit">특가 브랜드데이</p>
                    <span class="all_view"><a href="#">전체보기</a></span>
                    <div class="item_ad">
                        <a href="#"></a>
                    </div>
                </div>
                <!--오늘의 추천-->
                <div class="today_recommend">
                    <p class="cont_tit">오늘의 추천</p>
                    <span class="all_view"><a href="#">전체보기</a></span>
                    <div class="item_ad">
                        <a href="#"></a>
                    </div>
                </div>
                <div class="contents_wrap clearfix">
                    <div class="contents_01">
                        <div class="contents_inner clearfix">
                            <a href="#" class="view_btn"><%=brand.getBrandNm() %> 바로보기 ></a>
                            <div class="item_wrap">
                                <ul class="clearfix">
                                
<!--          =======================  브랜드 상품 -->
<%	list = (ArrayList)goodsList.get(1); 
	if(list.size()>0){
		for(goodsDTO goods : list){
			if(goods.getGoodsSellFl()>0){
%>                                 	<li class="item_list clearfix">
                                        <div class="item_img_box">
                                            <a href="#">
                                                <img src="" alt="">
                                            </a>
                                        </div>
                                        <div class="itme_info_box">
                                            <a href="#">
                                                <p class="list_keyword">[<%=brand.getBrandNm() %>]</p>
                                                <p class="list_name"><%=goods.getGoodsNm() %></p>
                                                <p class="list_price">
                                                <%if(goods.isMemberOnly() && mem==null){ %>
                                                	<span class="non_member_price">할인클럽</span>
                                                <%}else{ %>
                                                	<p class="discountRate">&dtrif;<%=goods.getDiscountPercent() %></p>
                                                    <span class="before_price"><%=goods.getGoodsPrice() %>원</span>
                                                    <span class="after_price"><%=goods.getFixedPrice() %>원</span>
                                                <%} %>
                                                </p>
                                            </a>
                                        </div>
                                    </li>
<% 			}
		}
	}%>
<!--          =======================  브랜드 상품 -->	                               	
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="contents_02">
                        <div class="contents_inner clearfix">
                            <a href="#" class="view_btn">인기상품 바로보기 ></a>
                            <div class="item_wrap">
                                <ul class="clearfix">
<!--          =======================  인기 상품 -->
<%	list = (ArrayList)goodsList.get(2); 
	if(list.size()>0){
		for(goodsDTO goods : list){
			if(goods.getGoodsSellFl()>0){
%> 	                                <li class="item_list clearfix">
                                        <div class="item_img_box">
                                            <a href="#">
                                                <img src="" alt="">
                                            </a>
                                        </div>
                                        <div class="itme_info_box">
                                            <a href="#">
                                                <p class="list_name"><%=goods.getGoodsNm() %></p>
                                                <p class="list_price">
                                                <%if(goods.isMemberOnly() && mem==null){ %>
                                                	<span class="non_member_price">할인클럽</span>
                                                <%}else{ %>
                                                	<p class="discountRate">&dtrif;<%=goods.getDiscountPercent() %></p>
                                                    <span class="before_price"><%=goods.getGoodsPrice() %>원</span>
                                                    <span class="after_price"><%=goods.getFixedPrice() %>원</span>
                                                <%} %>
                                                </p>
                                            </a>
                                        </div>
                                    </li>
<% 			}
		}
	}%>
                            	</ul>
                            </div>
                        </div>
                    </div>
                    <div class="contents_03">
                        <div class="contents_inner clearfix">
                            <a href="#" class="view_btn">무료배송 상품 ></a>
                            <div class="item_wrap">
                                <ul class="clearfix">
<!--          =======================  무료배송 상품 -->
<%	list = (ArrayList)goodsList.get(3); 
	if(list.size()>0){
		for(goodsDTO goods : list){
			if(goods.getGoodsSellFl()>0){
%>									<li class="item_list clearfix">
                                       <div class="item_img_box">
                                           <a href="#">
                                               <img src="" alt="">
                                           </a>
                                       </div>
                                       <div class="itme_info_box">
                                            <a href="#">
                                                <p class="list_name"><%=goods.getGoodsNm() %></p>
                                                <p class="list_price">
                                                <%if(goods.isMemberOnly() && mem==null){ %>
                                                	<span class="non_member_price">할인클럽</span>
                                                <%}else{ %>
                                                	<p class="discountRate">&dtrif;<%=goods.getDiscountPercent() %></p>
                                                    <span class="before_price"><%=goods.getGoodsPrice() %>원</span>
                                                    <span class="after_price"><%=goods.getFixedPrice() %>원</span>
                                                <%} %>
                                                </p>
                                            </a>
                                        </div>
                                   </li>
<% 			}
		}
	}%>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!--서브 롤링광고배너-->
                <div class="swiper-container sub_baner01">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide"><a href="#"><img src="fileF/index/baner/air.jpg" alt=""></a></div>
                        <div class="swiper-slide"><a href="#"><img src="fileF/index/baner/chef.jpg" alt=""></a></div>
                    </div>
                    <!-- Add Arrows -->
                    <div class="swiper-button-next sub_baner01_next"></div>
                    <div class="swiper-button-prev sub_baner01_prev"></div>
                </div>
            </div>
        </div>
        <!--올타임딜-->
        <div class="section03">
            <div class="inner">
                <p class="cont_tit">올타임딜</p>
                <!-- Swiper -->
                <div class="swiper-container allTime_warp clearfix">
                    <div class="swiper-wrapper">
<%	list = (ArrayList)goodsList.get(4); 
	if(list.size()>0){
		for(goodsDTO goods : list){
			if(goods.getGoodsSellFl()>0){
%>	
		                        <div class="allTime_item swiper-slide">
		                            <a href="#"><img src="" alt=""></a>
		                        </div>
<% 			}
		}
	}%>
                    </div>
                    <!-- Add Arrows -->
                    <div class="swiper-button-next allTime_next"></div>
                    <div class="swiper-button-prev allTime_prev"></div>
                    <!-- Add Pagination -->
                    <div class="swiper-pagination"></div>
                </div>
            </div>
        </div>
        <!--서브광고 배너02-->
        <div class="inner">
            <div class="sub_baner02"><a href="#"><img src="" alt=""></a></div>
        </div>
        <div class="section04">
            <div class="inner">
                <div id="category_wrap">
                <input type="hidden" id="themeCd" value="56">
                    <ul class="category_menu clearfix">
                        <li class="category_list" onclick="selectTheme('56')"><img src="fileF/index/category/all.png" alt="">
                                <p class="category_name">전체</p>
                        </li>
                        <li class="category_list" onclick="selectTheme('57')"><img src="fileF/index/category/fashion.png" alt="">
                                <p class="category_name">패션/잡화</p>
                        </li>
                        <li class="category_list" onclick="selectTheme('58')"><img src="fileF/index/category/cosmetics.png" alt="">
                                <p class="category_name">뷰티</p>
                        </li>
                        <li class="category_list" onclick="selectTheme('59')"><img src="fileF/index/category/kids.png" alt="">
                                <p class="category_name">출산/유아동</p>
                        </li>
                        <li class="category_list" onclick="selectTheme('60')"><img src="fileF/index/category/food.png" alt="">
                                <p class="category_name">식품</p>
                        </li>
                        <li class="category_list" onclick="selectTheme('61')"><img src="fileF/index/category/purniture.png" alt="">
                                <p class="category_name">가구/인테리어</p>
                        </li>
                        <li class="category_list" onclick="selectTheme('62')"><img src="fileF/index/category/appliances.png" alt="">
                                <p class="category_name">가전/디지털</p>
                        </li>
                        <li class="category_list" onclick="selectTheme('63')"><img src="fileF/index/category/kitchen.png" alt="">
                                <p class="category_name">주방/생활용품</p>
                        </li>
                        <li class="category_list" onclick="selectTheme('64')"><img src="fileF/index/category/sport.png" alt="">
                                <p class="category_name">스포츠/자동차</p>
                        </li>
                        <li class="category_list" onclick="selectTheme('65')"><img src="fileF/index/category/art.png" alt="">
                                <p class="category_name">문구/취미</p>
                        </li>
                    </ul>
                    
                    <div class="category_contents">
                       <!--전체 카테고리-->
                        <div class="category_panel">
                        	<input type="hidden" id="memNo" value="<%=(mem!=null)?true:false%>">
                        	<input type="hidden" id="adultFl" value="<%=(mem!=null)?mem.isAdultFl():false%>">
                            <ul id="item_list" class="clearfix">
								
                            </ul>
                        </div>
                    </div>
                </div>
                <a href="#" class="category_more_btn">상품 더 보러가기 ></a>
                <!--서브광고 배너03-->
                <div class="sub_baner03"><a href="#"><img src="" alt=""></a></div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath }/js/setList.js"></script>
    <script>
    	window.onload = function(){
    		setList(document.getElementById("themeCd").value);
    	}
    	document.addEventListener('scroll', function() {
    		addList(document.getElementById("themeCd").value);
    	});
    	function selectTheme(cateCd){
    		document.getElementById("themeCd").value = cateCd;
    		changeTheme();
    	}
    	function changeTheme(){
    		setList(document.getElementById("themeCd").value);
    	}
    </script>
<!--하단-->
<jsp:include page="footer.jsp" flush="false"/>


