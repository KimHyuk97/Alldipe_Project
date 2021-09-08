<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="width: 1200px; margin: 0 auto; display: flex; flex-wrap: wrap;">
   	<c:forEach var="g" items="${hg}">
    	<div class="content_box">
        	<a href="${pageContext.request.contextPath}/goodsView?goodsNo=${g.goodsNo}">
            	<img src="${g.representImg}">
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
</div>
