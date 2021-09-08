<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="orderGoodsList">
	<div class="contentTitle">
		<h1>주문내역</h1>
	</div>
	<div class="orderGoodsHistory">
		<table>
			<colgroup>
			    <col width="40%"/>
			    <col width="15%"/>
			    <col width="15%"/>
			    <col width="15%"/>
			    <col width="15%"/>
			</colgroup>
			<tr>
				<th>상품정보</th>
				<th>주문일자</th>
				<th>주문번호</th>
				<th>주문금액</th>
				<th>주문상태</th>
			</tr>
			<tfoot data-no="${sessionScope.mem.memNo}" id="orderGoodsViewTable">
				
			</tfoot>
		</table>
	</div>
</div>
<div id="orderGoodsView">
	<div class="contentTitle">
		<h1>주문상세내역</h1>
		<div>
			<div>주문번호<span id="orderNumber"></span></div>
			<div>주문일자<span id="orderDate"></span></div>
		</div>
	</div>
	<div>
		<table>
			<tr>
				<th>상품정보</th>
				<th>등급 할인</th>
				<th>쿠폰할인</th>
				<th>적립금</th>
				<th>주문금액(수량)</th>
				<th>배송정보</th>
				<th>주문상태</th>
			</tr>
			<tr id="orderGoodsViewContent"></tr>
		</table>
	</div>
</div>

