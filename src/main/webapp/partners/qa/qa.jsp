<%@page import="dto.boardDTO.qaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	qaDTO dto = (qaDTO)session.getAttribute("qa");
%>
<jsp:include page="../header.jsp"/>

	<div class="wrap">
		<div>
		<%	if(dto!=null){ %>
			<%=dto.getContents() %>
		<%	} %>
		</div>
	</div>
</body>
</html>