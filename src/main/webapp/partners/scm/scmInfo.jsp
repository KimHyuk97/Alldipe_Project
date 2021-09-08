<%@page import="dto.scmDTO.scmDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	scmDTO sdto = (scmDTO)session.getAttribute("scm");
%>
<jsp:include page="../header.jsp"/>
<div class="cont_wrap">
	<h1>SCM Info</h1>
<%
	if(sdto!=null){
%>
	<ul>
		<li><%=sdto.getMemNo() %></li>
		<li><%=sdto.getCompanyNm() %></li>
	</ul>
<%
	}
%>
</div>
<jsp:include page="../footer.jsp"/>