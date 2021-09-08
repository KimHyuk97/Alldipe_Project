<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/search/alldipe_search.css">
<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
    <div class="cont_wrap">
        <div class="nav_left">
            <ul class="bene"> <p>혜택</p> 
                <li>
                    <label for="ck01">무료배송</label>
                </li>
                <li>
                    <label for="ck02">특가</label>
                </li>
                <li>
                    <label for="ck03">기획전</label>
                </li>
                <li>
                    <label for="ck04">당일배송</label>
                </li>
            </ul>
            
            <c:if test="${!empty cate}">
	            <ul class="cate"><p>카테고리</p> 
	            	<c:forEach var="c" items="${cate}" varStatus="status">
		                <li id="cate${status.count}"><a href="search?keyword=${keyword}&cateCd=${c.cateCd}">${c.cateNm}</a></li>
	            	</c:forEach>
	            </ul>
            </c:if>
            
            <c:if test="${!empty brand}">
	            <ul class="cate"><p>브랜드</p> 
	            	<c:forEach var="b" items="${brand}" varStatus="status">
		                <li id="brand${status.count}"><a href="search?keyword=${keyword}&brandCd=${b.brandCd}">${b.brandNm}</a></li>
	            	</c:forEach>
	            </ul>
            </c:if>
            
            <ul class="star"><p>별점</p> 
                <li id="star_under">
                     <a href="search?keyword=${keyword}&reviewCnt=5"><label for="chk02"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></label></a>
                </li>
                <li id="star_under">
                     <a href="search?keyword=${keyword}&reviewCnt=4"><label for="chk03"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="far fa-star"></i></label></a>
                </li>
                <li id="star_under">
                     <a href="search?keyword=${keyword}&reviewCnt=3"><label for="chk04"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></label></a>
                </li>
                <li id="star_under">
                     <a href="search?keyword=${keyword}&reviewCnt=2"><label for="chk05"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></label></a>
                </li>
                <li id="star_under">
                     <a href="search?keyword=${keyword}&reviewCnt=1"><label for="chk06"><i class="fas fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></label></a>
                </li>
            </ul>
            <ul class="price_menu"><p>가격</p> 
                <li id="click01"><a href="search?keyword=${keyword}&price=0&price2=10000">만원 이하</a></li>
                <li id="click01"><a href="search?keyword=${keyword}&price=10000&price2=50000">만원 ~ 5만원</a></li>
                <li id="click01"><a href="search?keyword=${keyword}&price=50000&price2=100000">5만원 ~ 10만원</a></li>
                <li id="click01"><a href="search?keyword=${keyword}&price=100000&price2=500000">10만원 ~ 50만원</a></li>
                <li id="click01"><a href="search?keyword=${keyword}&price=500000&price2=1000000">50만원 ~ 100만원</a></li>
                <li id="click01"><a href="search?keyword=${keyword}&price=1000000&price2=10000000">100만원 이상</a></li>
                <li class="price_sch"><input type="text" id="price1" class="squ"> ~ <input type="text" id="price2" class="squ"><input type="button" value="검색" id="priceSearch"></li>
                <script>
                	const sc = document.getElementById("priceSearch");
                	let p1 = document.getElementById("price1").value,
                	    p2 = document.getElementById("price2").value;
                	sc.addEventListener('click',()=>{
	                	location.href="searchFilter?keywrod="+${keyword}+"&price="+p1+"&price2="+p2+"";                		
                	});
                </script>
            </ul>
        </div>
            <c:choose>
            	<c:when test="${!empty sg}">
			        <div class="cont_right">
			        	<div class="searchResult">
			        		<p>"<span class="highlightText">${keyword}</span>"에 대한 검색 결과 <span class="highlightText">${listCount}</span>건</p>
			        	</div>
			        	
			        	<!-- 검색필터 -->
			        	<div>
			        			
			        	</div>
			        	
			            <div class="top_tit02">
			                <h1>전체상품</h1>
			                <div class="top_tit02_right">
			                    <select class="selectBOX">
			                        <option value="">최신순</option>
			                        <option value="">인기순</option>
			                        <option value="">낮은 가격순</option>
			                        <option value="">높은 가격순</option>
			                    </select> 
	                		</div>
	            		</div>
		            	<div class="content">
			                <c:forEach var="g" items="${sg}">
			                    <div class="content_box">
			                        <a href="${pageContext.request.contextPath}/goodsView?goodsNo=${g.goodsNo}">
			                            <img src="${g.representImg}" alt="">
			                            <div class="content_txt">
			                                <div class="content_title">${g.goodsNm }</div>
			                                <div class="price">
			                                    할인혜택가 <span>${g.goodsDiscountPercent}%</span> <p>${g.goodsPrice}원</p>  <span>${g.fixedPrice}원</span>
			                                </div>
			                                <div class="mark">
			                                    <p>페스티벌</p>
			                                    <p>무료배송</p>
			                                </div>
			                            </div> 
			                        </a>            
			                    </div>
			                </c:forEach>
			                <div style="width:100%; margin:10px; display: flex; justify-content: center;">
			                    <c:if test="${paging.page<=1 }">[이전]&nbsp;</c:if>
			                    <c:if test="${paging.page>1 }">
			                        <a href="goodsList?page=${paging.page-1}&cateCd=${gd.get(0).cateCd}">[이전]</a>&nbsp;</c:if> 
			                        <c:forEach begin="${paging.startPage}" end="${paging.endPage }" var="i" step="1">
			                        <c:choose>
			                            <c:when test="${i eq paging.page }">	
			                                [${i}]								
			                            </c:when>
			                            <c:otherwise>
			                                <a href="goodsList?page=${i}&cateCd=${gd.get(0).cateCd}">${i}</a>
			                            </c:otherwise>
			                        </c:choose>
			                    </c:forEach>
			                    <c:if test="${paging.page>=paging.maxPage}">&nbsp;[다음]</c:if>
			                    <c:if test="${paging.page<paging.maxPage}">
			                        <a href="goodsList?page=${paging.page+1}&cateCd=${gd.get(0).cateCd}">&nbsp;[다음]</a>
			                    </c:if>
			                </div>
		            	</div>		
			        </div>
            	</c:when>
            	<c:otherwise>
            		<div class="cont_right" style="align-self: flex-start;">
			        	<div class="searchResult">
			        		<p>"<span class="highlightText">${keyword}</span>"에 대한 검색 결과 <span class="highlightText">${listCount}</span>건</p>
			        	</div>
			            <div class="top_tit02">
			                <h1>전체상품</h1>
			                <div class="top_tit02_right">
			                    <select class="selectBOX">
			                        <option value="">최신순</option>
			                        <option value="">인기순</option>
			                        <option value="">낮은 가격순</option>
			                        <option value="">높은 가격순</option>
			                    </select> 
	                		</div>
	            		</div>
		            	<div class="content">
			                <div class="searchResultNone">
			                	<p>"<span>${keyword}</span>"에 대한 검색결과가 없습니다.</p>
			                </div>
		            	</div>		
			        </div>
            	</c:otherwise>
            </c:choose>
    </div>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>