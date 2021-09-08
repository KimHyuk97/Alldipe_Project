<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.apache.commons.codec.binary.Hex" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order/order.css">
<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
<div class="order_result_container">
    <div class="order_result_title">
        <h1>주문완료</h1>
        <div class="order_result_title2">
            <div><p>주문번호<span>${orderList.orderNo}</span></p></div>
        </div>
    </div>
    <div class="orderGoods">
        <table>
		  <colgroup>
		    <col width="40%"/>
		    <col width="15%"/>
		    <col width="15%"/>
		    <col width="15%"/>
		  </colgroup>
			<tr>
				<th>상품정보</th>
				<th>상품금액</th>
				<th>할인금액</th>
				<th>배송비</th>
			</tr>
			<c:forEach var="g" items="${orderGoods}" varStatus="status">
				<tr class="orderGoodsTitle">
                    <td>
                    	<div class="orderFlex">
	                        <div>
	                       		<img src="${g.representImg}" alt="" style="width:160px; height:160px;">
	                        </div>
	                        <div class="orderGoodsContainer">
	                                <p>${g.goodsNm}</p>
	                                <p>${g.goodsOptionNm }</p>
									<p>수량 : ${g.goodsCnt }</p>
	                        </div>
                        </div>
                    </td>
                    <td ><fmt:formatNumber value="${g.goodsPrice}" type="number"/><span>원</span></td>
                    <td ><span id="salePrice${status.index}">원</span></td>
                    <td ><fmt:formatNumber value="${g.deliveryPrice}" type="number"/>원</td>
            	</tr>
	            <script>
		            var gp = '${o.goodsPrice}',
            			fp = '${o.fixedPrice}';
            		var gd2 = parseInt(gp),
            			fp2 = parseInt(fp);
            		document.getElementById('salePrice${status.index}').innerHTML = (gd2-fp2)+"원";
            		document.getElementById('salePrice${status.index}').value = gd2-fp2;
	            </script>
 				</c:forEach>
		</table>
    </div>
    <div class="orderInfo">
    	<div class="orderInfoContainer">
    		<h1>배송지 정보</h1>
    		<div>
  				<table>
  					<tr>
  						<th>이름</th>
  						<td>${info.receiverName}</td>
  					</tr>
  					<tr>
  						<th>연락처</th>
  						<td>${info.receiverCellPhone}</td>
  					</tr>
  					<tr>
  						<th>배송지 주소</th>
  						<td>
  							<div class="deliveryInfo">
	  							<p>(${info.receiverZonecode})&nbsp;${info.receiverAddress}&nbsp;${info.receiverAddressSub}</p>
	  							<p>받으실 장소 : ${info.pickUpType}&nbsp;<c:if test="${info.pickUpContent != null}">택배함정보 : ${info.pickUpContent}</c:if></p>
	  							<p>공동현관 출입방법 : ${info.meansType}&nbsp;<c:if test="${info.meansContent != null}">공동현관 비밀번호 : ${info.meansContent}</c:if></p>
  							</div>
  						</td>
  					</tr>
  					<tr>
  						<th>배송 메세지</th>
  						<td>${info.orderMemo}</td>
  					</tr>
  				</table>
    		</div>
    		<p class="pr2">ㆍ발송전 일때만 배송지 주소를 변경하실 수 있습니다.(주문완료/결제완료/상품준비중)</p>
    	</div>
     	<div class="orderInfoContainer"> 
     		<h1>할인 정보</h1>
    		<div>
  				<table>
  					<tr>
  						<th>상품할인</th>
  						<td>${info.receiverCellPhone}</td>
  					</tr>
  					<tr>
  						<th>쿠폰할인</th>
  						<td>${info.orderMemo}</td>
  					</tr>
  					<tr>
  						<th>적립금 할인</th>
  						<td>${info.orderMemo}</td>
  					</tr>
  					<tr>
  						<th>배송비 할인</th>
  						<td>${info.receiverName}</td>
  					</tr>
  					<tr>
  						<th>등급할인</th>
  						<td></td>
  					</tr>
  				</table>
    		</div>
     	</div>
    	<div class="orderInfoContainer">
    		<h1>최종 결제 정보</h1>
    		<div>
  				<table>
  					<tr>
  						<th>상품 합계 금액</th>
  						<td>${orderList.totalGoodsPrice}</td>
  					</tr>
  					<tr>
  						<th>할인 합계</th>
  						<td>${orderList.totalGoodsDcPrice}</td>
  					</tr>
  					<tr>
  						<th>최종 결제 금액</th>
  						<td>${orderList.settlePrice}</td>
  					</tr>
  					<tr>
  						<th>결제 수단</th>
  						<td>${orderList.settleKind}</td>
  					</tr>
  				</table>
  				<p class="pr2">ㆍ카드결제시 현금영주증/세금계산서 발급이 불가능하며 카드전표로 대체하실 있습니다.<br/>
							  ㆍPG사 또는 카드사에서 제공하는 즉시 할인은 최종 결제 금액에 반영되지 않습니다.</p>
    		</div>
    	</div>
    </div>
    <div class="selectBtn">
    	<a href="${pageContext.request.contextPath}/index?displayFl=y">확인</a>
    </div>
</div>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>