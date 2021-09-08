<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/goods/festival.css">

<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
<div class="wrap">
    <div class="cont_top">
       <div class="banner_img">
          <!-- Swiper -->
          <div class="swiper-container swiper1">
              <div class="swiper-wrapper">
                  <div class="swiper-slide">슬라이드1</div>
                  <div class="swiper-slide">슬라이드2</div>
              </div>
              <!-- 네비게이션 버튼 -->
              <div class="swiper-button-next"></div><!-- 다음 버튼 (오른쪽에 있는 버튼) -->
              <div class="swiper-button-prev"></div><!-- 이전 버튼 -->
          </div>
       </div>
       <div class="top_cont_box">
            <div class="top_tit">
                <h2>팡팡 터지는 혜택</h2>
            </div>
            <c:forEach var="t" items="${themeList}">
	            <div class="top_txt">
	                <h2>${t.themeNm}</h2>
	                <div class="top_box">
	                    <div class="top_box_left"></div>
	                    <div class="top_box_right">
	                      <div class="swiper-container">
	                          <div class="swiper-wrapper">
	                          	<c:forEach var="g" items="${goodsList}">
		                            <div class="swiper-slide">
		                              <img src="${g.representImg}" alt="">
		                              <p class="slide_tit">
		                                ${g.goodsNm}
		                              </p>  
		                              <p class="red">${g.goodsDiscountPercent }%</p><span>${g.goodsPrice }원</span><p>${g.fixedPrice }원</p>
		                            </div>
	                          	</c:forEach>	                            
	                          </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
            </c:forEach>
            
       </div>
       <div class="line_box"></div>
    </div>
    <div class="content">
		<jsp:include page="../goods/headerCategoryGoodsList.jsp" flush="false"/>
    </div>
</div>

<script>
  var swiper = new Swiper('.swiper-container', {
    slidesPerView: 3,
    spaceBetween: 30,
    slidesPerGroup: 3,
    loop: false,
    loopFillGroupWithBlank: true,
    
  //   navigation: {
  //     nextEl: '.swiper-button-next',
  //     prevEl: '.swiper-button-prev',
  //   },
  });
  new Swiper('.swiper1', {
      pagination : { // 페이징 설정
          el : '.swiper-pagination',
          clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
      },
      loop: true,
      navigation : { // 네비게이션 설정
          nextEl : '.swiper-button-next', // 다음 버튼 클래스명
          prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
      },
  });

</script>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>