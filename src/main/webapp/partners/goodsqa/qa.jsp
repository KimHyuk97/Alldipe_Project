<%@page import="dto.boardDTO.qaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"/>
<%
	qaDTO dto = (qaDTO)session.getAttribute("qa");
	session.removeAttribute("qa");
	if(dto!= null){
%>
<div class="cont_wrap">
	<table>
	  <tr>
	    <th>No</th>
	    <th>작성자</th>
	    <th>제목</th>
	    <th>내용</th>
	  </tr>
	  <tr>
		<td><%=dto.getSno() %></td>
		<td><%=dto.getWriterNm() %></td>
		<td><%=dto.getTitle() %></td>
		<td><%=dto.getContents() %></td>
  	</tr>
<% 	if(dto.getAnswerContents()!=null){%>
  	<tr>
  		<td>답변 내용 : </td>
  		<td><%=dto.getAnswerManagerId() %></td>
  		<td colspan="2"><%=dto.getAnswerContents() %></td>
  		<td><%=dto.getAnswerModDt() %></td>
  		
  	</tr>
<%	}%>
	</table>
	
	<form action="./answerQa?sno=<%=dto.getSno()%>" method="post">
		<input type="text" name="answer"><br>
		<input type="submit" value="답변저장">
	</form>
	
</div>
<%		
	}
%>
<jsp:include page="../footer.jsp"/>