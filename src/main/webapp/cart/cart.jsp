<%@page import="dto.cartDTO.cartDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	ArrayList<cartDTO> list = (ArrayList)request.getAttribute("cart");
%>
<fmt:parseNumber  value="${goods.goodsPrice }" integerOnly="true"/> <!-- 소주점버림 -->
<fmt:parseNumber  value="${goods.fixedPrice }" integerOnly="true"/> <!-- 소수점버림 -->
<fmt:parseNumber value="${goods.deliveryCost }" integerOnly="true"/> <!-- 소수점버림 -->
<input type="hidden" id="memNo" value="${sessionScope.mem.memNo}">

<!--상단-->
<jsp:include page="../header.jsp" flush="false" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart/cart.css">
<div class="inner">
      <div class="cate_box">
          <p class="cate_nav">
              장바구니
          </p>
      </div>
      <!--장바구니 목록-->
       <table class="cart_table">
       	<colgroup>
		    <col width="5%"/>
		    <col width="47%"/>
		    <col width="12%"/>
		    <col width="12%"/>
		    <col width="12%"/>
		    <col width="12%"/>
		</colgroup>
           <thead>
               <tr>
                   <th class="item_select"><input type="checkbox" id="allCheck" class="check_all"></th>
                   <th class="item_info_title">상품정보</th>
                   <th class="item_price">상품금액</th>
                   <th class="dc_price">할인</th>
                   <th class="delivery_price">합계금액</th>
                   <th class="delivery_price">배송비</th>
               </tr>
           </thead>
           <tbody>
           	<form action="./order" method="post" name="form">
<%	if(list!= null && list.size()>0){
		int cnt = 0;
		for(cartDTO cart : list){%>
           				<tr>
		                    <td class="item_select">
		                        <input type="checkbox" id="check<%=cnt %>" class="check" name="chList" onclick="chk(<%=cnt %>)" value="<%=cart.getSno()%>"<% if(cart.getOptionNo()==0 || cart.getGoodsNo()==0){ %> disabled="disabled" <%} %>>
		                    </td>
		                    <td class="item_info">
<% if(cart.getOptionNo()==0 || cart.getGoodsNo()==0){ %> 
		                    	<div class="change_box">상품 정보가 수정되었습니다.</div>
<%} %>
		                        <div class="img_box">
		                            <a href="${pageContext.request.contextPath}/goodsView?goodsNo=<%=cart.getGoodsNo()%>">
		                                <img src="${pageContext.request.contextPath }/fileF/goods/<%=cart.getRepresentImg() %>" alt="상품대표이미지">
		                            </a>
		                        </div>
		                        <div class="info_box">
		                            <a href="${pageContext.request.contextPath}/goodsView?goodsNo=<%=cart.getGoodsNo()%>">
		                                <p class="brand_name"><%=cart.getMakerNm() %></p>
		                                <p class="item_name"><%=cart.getGoodsNm() %></p>
		                                <p class="order_info">
		                                	<span class="order_01"><%=cart.getGoodsOptionNm() %>  +<span id="opPrice<%=cnt%>"><fmt:formatNumber value="<%=cart.getOptionPrice() %>" type="number"/></span>원</span>
		                                	<input type="hidden" id="optionfixedPrice<%=cnt%>">
		                                </p>
		                            </a>
		                            <div class="item_count">
		                                <input type='button' value='-' onclick='minus(<%=cnt%>,<%=cart.getSumPrice()%>,<%=cart.getGoodsPrice() %>)' class='minus_btn' field='quantity' />
										<input type='text' name='goodsCnt' id='goodsCnt<%=cnt%>' value='<%=cart.getGoodsCnt() %>' min='1' class='qty' readonly/>
										<input type='button' value='+' onclick='plus(<%=cnt%>,<%=cart.getSumPrice() %>,<%=cart.getGoodsPrice() %>,<%=cart.getTotalStock() %>)' class='plus_btn' field='quantity' />
		                            </div>
		                        </div>
                        		
		                    </td>
		                    <!-- 상품금액 -->
		                    <td class="item_price">
		                    	<span id="price<%=cnt%>" >
		                    		<fmt:formatNumber value="<%=cart.getSumPrice() %>" type="number"/><span>원</span>
		                    	</span>
	                    		<input type="hidden" id="tp<%=cnt%>" value="<%=cart.getSumPrice()%>">
		                    	<input type="hidden" name="goodsPrice" id="goodsPrice<%=cnt%>" value="<%=cart.getGoodsPrice()%>">
		                    	<input type="hidden" name="fixedPrice" id="fixedPrice<%=cnt%>" value="<%=cart.getSumPrice()%>">
		                    </td>
		                    <!-- 할인금액 -->
		                    <td class="dc_price">
		                    	<span id="dc_price<%=cnt%>">0</span><span>원</span>
		                    	<input type="hidden" name="goodsDiscountPercent" id="dc<%=cnt%>">
		                    </td>
		                    <!-- 합계금액 -->
		                     <td class="dc_price">
		                    	<span id="totalFixedPrice<%=cnt%>"><fmt:formatNumber value="<%=cart.getSumPrice() %>" type="number"/></span><span>원</span>
		                    </td>
		                    <!-- 배송비 -->
		                    <td class="delivery_price">
		                    	<span><fmt:formatNumber value="<%=cart.getDeliveryCost() %>" type="number"/></span><span>원</span>
		                    	<input type="hidden" name="deliveryCost" id="deliveryCost<%=cnt%>" value="<%=cart.getDeliveryCost()%>">
		                   		<input type="button" value="x" class="cartDelete" onclick="cartD(<%=cart.getSno()%>)"/>
		                    </td>
              			</tr>
<%		cnt++;		}
	}
%>
           	</form>
           </tbody>
       </table>
    <div class="cart_price_wrap clearfix">
        <div class="price_box01">
            결제 예정 금액
        </div>
        <div class="price_box02">
            <div class="price01">
                <span class="price_title">상품금액</span>
                <input type="hidden" value="0" id="itemsPrice">
                <span class="price_wrap">
                    <span class="price"></span>
                    <span id="total_goodsPrice">원</span>
                </span>
            </div>
            <div class="price02">
                <span class="price_title">배송비</span>
                <input type="hidden" value="0" id="deliveryPrice">
                <span class="price_wrap">
                    <span>(+) </span>
                    <span class="price"></span>
                    <span id="total_deliveryCost">원</span>
                </span>
            </div>
        </div>
        <div class="price_box03">
        	<input type="hidden" value="0" id="totalItemsPrice">
            <p class="total_price">
            	=<span id="total_fixedPrice"></span>원
            </p>
        </div>
    </div>
    <div class="btn_wrap">
        <span class="shop_btn">
            <a href="javascript:history.back();">쇼핑 계속하기</a>
        </span>
        <span>
        	<c:choose>
        		<c:when test="${empty sessionScope.mem.memNo}"> 
        			<input  class="payment_btn" type="button" value="주문하기" id="login">      		
        		</c:when>
        		<c:otherwise>        		
		            <input  class="payment_btn" type="button" value="주문하기" id="payment">
        		</c:otherwise>
        	</c:choose>
        </span>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/cart/cart.js"></script>
<script>
	window.onload = function(){
		endLoading();
	}
</script>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false" />