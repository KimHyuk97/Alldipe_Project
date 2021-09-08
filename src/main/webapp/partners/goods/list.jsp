<%@page import="dto.goodsDTO.goodsDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"/>
<%	ArrayList<goodsDTO> list = (ArrayList)session.getAttribute("gList");
	session.removeAttribute("gList");
%>

	<div class="cont_wrap">
		<h2>상품리스트</h2>
<%	if(list != null && list.size()>0){ 
		for(goodsDTO dto : list){
			System.out.println("goodsDTO : " + dto.getGoodsNm());
%>

		<div class="list">
			<h3><%=dto.getGoodsNm() %></h3>
			<span>가격 : <%=dto.getGoodsPrice() %></span><br>
			<span>브랜드 : <%=dto.getBrandCd() %></span>
			<button onclick="location.href='./update?goodsNo=<%=dto.getGoodsNo()%>'">상품관리</button>
		</div>

<% 		}
	}else{%>
		<div class="list">
			<h3>등록된 상품이 없습니다.</h3>
		</div>
<%	} %>
	</div>
<jsp:include page="../footer.jsp"/>