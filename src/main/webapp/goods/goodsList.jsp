<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="width: 1200px; margin: 100px auto; display: flex; flex-wrap: wrap;">
	   	<c:forEach var="g" items="${gd}">
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
	<div style="width:100%; margin:10px;">
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
