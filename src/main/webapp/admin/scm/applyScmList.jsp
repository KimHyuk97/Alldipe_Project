<%@page import="dto.scmDTO.scmDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
<%
	ArrayList<scmDTO> list = (ArrayList)session.getAttribute("scmList");
	session.removeAttribute("scmList");
%>
	<div class="cont_wrap">
	<h2>입점 관리</h2>
	<hr>
	<h3>입점 신청 현황</h3>
	<ul>
<%	if(list != null && list.size()>0){
		for(scmDTO dto : list){
%>
		<li>
			<div>
				<%=dto.getScmNo() %>
				<%=dto.getImageStorage() %>
				<%=dto.getCompanyNm() %>
				<%=dto.getScmState() %>
				<%=dto.getPhone() %>
				<button onclick="location.href='./applyScmDetails?scmNo=<%=dto.getScmNo()%>';">관리</button>
			</div>
		</li>
<%		}
	}else{%>
		<li>입점 신청 내역이 없습니다.</li>
<%	}%>
	</ul>
	</div>