<%@page import="dto.memberDTO.memberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/goods/new.css">
<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
<%
	memberDTO mem = (memberDTO)session.getAttribute("mem");
%>
<h1 style="text-align: center;">특딜</h1>
<!-- 상품 -->
	<div style="width: 1200px; margin: 0 auto; display: flex; flex-wrap: wrap;">
		<input type="hidden" id="memNo" value="<%=(mem!=null)?mem.getMemNo():null %>">
		<input type="hidden" id="adultFl" value="<%=(mem!=null)?mem.isAdultFl():0%>">
		<ul id="item_list" class="clearfix">
			
		</ul>
	</div>
<!--하단-->
	<script src="${pageContext.request.contextPath }/js/goods/goodsList.js"></script>
	<script>
		window.onload = function(){
			endLoading();
			setList('69');
		}
		document.addEventListener('scroll', function() {
    		addList('69');
    	});
	</script>
<jsp:include page="../footer.jsp" flush="false"/>