<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="contentTitle">
	<h1>상품후기</h1>
</div>
<div>
	<div>
		<ul class="reviewFlex">
			<li id="reviewPossble">작성기능 후기</li>
			<li id="myReviewView">작성완료 후기</li>
		</ul>
	</div>
	<div id='reviewAfter' class="reviewView" data-no="${sessionScope.mem.memNo}">
		<table>
			<colgroup>
			    <col width="30%"/>
			    <col width="15%"/>
			    <col width="15%"/>
			    <col width="15%"/>
			    <col width="25%"/>
			</colgroup>
			<tr>
				<td>내가 구매한 상품</td>
				<td>주문번호</td>
				<td>주문일자</td>
				<td>배송정보</td>
				<td>작성가능 후기</td>
			</tr>
			<tfoot id="reviewAfterView">
			</tfoot>
		</table>
	</div>
	<div id='reviewBefore' class="reviewView" data-no="${sessionScope.mem.memNo}">
		<table>
			<colgroup>
			    <col width="30%"/>
			    <col width="15%"/>
			    <col width="15%"/>
			    <col width="15%"/>
			    <col width="25%"/>
			</colgroup>
			<tr>
				<td>내가 구매한 상품</td>
				<td>주문번호</td>
				<td>주문일자</td>
				<td>배송정보</td>
				<td>작성가능 후기</td>
			</tr>
			<tfoot id="reviewBeforeViewTable">
			</tfoot>	
		</table>
	</div>
</div>
