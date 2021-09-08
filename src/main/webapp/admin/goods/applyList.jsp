<%@page import="dto.goodsDTO.goodsDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
<%
	ArrayList<goodsDTO> list = (ArrayList)session.getAttribute("goodsList");
	session.removeAttribute("goodsList");
%>

	<div class="cont_wrap">
		<h2>상품 등록 신청</h2>
		<hr>
		<h3>등록 신청 현황</h3>
		<ul>
<%
	if(list != null && list.size()>0){
		for(goodsDTO dto : list){
%>
			<li>
				<div>
					<h4><%=dto.getGoodsNm() %></h4>
					<%=dto.getCateCd() %>
					<%=dto.getDeliveryArea() %>
					<button onclick="location.href='./applyGoods?goodsNo=<%=dto.getGoodsNo()%>';">관리</button>
				</div>
			</li>
<%	
		}
	}else{
%>
			<li>등록된 승인요청이 없습니다.</li>
<%		
	}
%>		
		</ul>
	</div>
<!--하단-->
<jsp:include page="../adminFooter.jsp" flush="false"/>
