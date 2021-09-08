<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
<div class="contentTitle d2">
	<h1>배송지 관리</h1>
	<div id="addAddress">배송지 추가 +</div>
</div>
<div class="deliveryTable">
	<table>
		<tr>
			<th>기본 배송지</th>
			<th>주소</th>
			<th>받으실 분</th>
			<th>연락처</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<tfoot data-no="${sessionScope.mem.memNo}" id="delivery">

		</tfoot>
	</table>
</div>

<!-- 배송지추가창 -->
<div id="deAdd">
	<div id="dClose">x</div>
		<input type="hidden" name="memNo" value="${sessionScope.mem.memNo}">
		<h1>배송지 추가</h1>
		<div><input type="text" name="zone" id="zone2"><input type="button" id="addAddress02" value="재검색"></div>
		<div><input type="text" name="addr" id="addrVal2"></div>
		<div><input type="text" name="address" id="addAddress2" placeholder="나머지 주소를 입력해주세요."></div>
		<div>기본배송지<input type="checkBox" id="deliveryFl2" value="n"></div>
		<div><input type="button" id="dSave" value="저장"></div>
</div>

<div id="deModify">
	<div id="mClose">x</div>
		<input type="hidden" id="dNo">
		<input type="hidden" name="memNo" id="memNo33" value="${sessionScope.mem.memNo}">
		<h1>배송지 수정</h1>
		<div><input type="text" name="zone" id="zone3"><input type="button" id="addAddress03" value="재검색"></div>
		<div><input type="text" name="addr" id="addrVal3"></div>
		<div><input type="text" name="address" id="addAddress3" placeholder="나머지 주소를 입력해주세요."></div>
		<div><input type="text" name="getName" id="nn3"></div>
		<div><input type="text" name="phone" id="pp3"></div>
		<div>기본배송지<input type="checkBox" id="deliveryFl3" value="n"></div>
		<div><input type="button" id="dModify" value="수정"></div>
</div>
