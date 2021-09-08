<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="contentTitle">
	<h1>쿠폰</h1>
</div>
<div>
	<table>
		<tr>
			<th>쿠폰번호</th>
			<th>쿠폰명</th>
			<th>할인금액</th>
			<th>유효기간</th>
			<th>만료일</th>
		</tr>
		<tfoot data-no="${sessionScope.mem.memNo}" id="coupon">
			
		</tfoot>
	</table>
</div>
