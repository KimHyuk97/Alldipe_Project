<%@page import="dto.goodsDTO.goodsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
<%
	goodsDTO goods = (goodsDTO)session.getAttribute("goods");
	session.removeAttribute("goods");
%>
	<div class="cont_wrap">
		<h2>상품 등록 신청</h2>
		<hr>
		<h3>신청 상품 정보</h3>
<%
	if(goods!=null){
%>
		<h3><%=goods.getGoodsNm() %></h3>
		<%=goods.getBrandCd() %>, <%=goods.getCateCd() %>
		<%=goods.getApplyMsg() %>
		<hr>
		등록날짜 : <%=goods.getApplyDt() %>
		
		<button onclick="location.href='./goodsApprove?goodsNo=<%=goods.getGoodsNo()%>';">등록 승인</button>
		
		<form action="./goodsReject?goodsNo=<%=goods.getGoodsNo()%>" method="post">
			관리자 메시지 : <textarea name="adminMsg"></textarea><br>
			<input type="submit" value="승인 거절">
		</form>
		
<%
	}
%>
	</div>
	
<!--하단-->
<jsp:include page="../adminFooter.jsp" flush="false"/>
