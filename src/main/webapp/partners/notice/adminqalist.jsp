<%@page import="java.text.SimpleDateFormat"%>
<%@page import="service.paging.paging"%>
<%@page import="dto.boardDTO.qaDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<qaDTO> list = (ArrayList)session.getAttribute("qaList");
	paging p = (paging)session.getAttribute("paging");
	session.removeAttribute("qaList");
	session.removeAttribute("paging");
%>
<jsp:include page="../header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/partners_ask.css">
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
	<div class="cont_wrap">
		<div class="wrap">
		<div class="title">
            1:1 문의
        </div>
        <div class="content">
            <div id="main">
            	<div id="main_table">
            		<div class="list_col1">No</div>
            		<div class="list_col2">카테고리</div>
            		<div class="list_col3">답변상태</div>
                    <div class="list_col4">제목</div>
                    <div class="list_col5">작성일</div>
                </div>
<%
	if(list!=null && list.size()>0){
		for(qaDTO dto : list){%>
				<div class="list_tr">
                    <div class="list_col1"><%=dto.getSno() %></div>
                    <div class="list_col2"><%=dto.getCate() %></div>
                    <div class="list_col3"><%=dto.isReplyStatus()?"답변완료":"미응답" %></div>
                    <div class="list_col4 content" onclick="location.href='./qa?no=<%=dto.getSno()%>';"><i class="fas fa-lock"></i> <%=dto.getTitle() %></div>
                    <div class="list_col5"><%=new SimpleDateFormat("yyyy/MM/dd").format(dto.getRegDt()) %></div>
                </div>		
<%		}
	}
%>
            </div>
        </div>
        <div class="write_btn">
        	<button onclick="location.href='./ask';">글쓰기</button>
       	</div>
        <div class="number">
<%
	if(p!=null){
		if(p.getFirstPageNo()!=p.getStartPageNo()){
%>
			<a href="./qalist?no=<%=p.getStartPageNo()-1%>&theme=<%=request.getParameter("theme") %>" class="arrow">&lt;</a>
<%
		}
		if(p.getStartPageNo()>0){
			for(int i = p.getStartPageNo(); i<=p.getEndPageNo(); i++){
%>
			<a href="./qalist?no=<%=i %>&theme=<%=request.getParameter("theme") %>"><%=i %></a>
<%		
			}
		}
		if(p.getFinalPageNo()!=p.getEndPageNo()){
%>
			<a href="./qalist?no=<%=p.getEndPageNo()+1%>&theme=<%=request.getParameter("theme") %>" class="arrow">></a>
<%
		}
	}
%>      	
		</div>
        <div class="input">
			<select name="선택">
				<option value="제목">제목</option>
				<option value="전체">전체</option>
				<option value="분류">분류</option>
			</select>
			<input type="text" >
			<button>검색</button>
        </div>
	</div>
	</div>
	<script>
		window.onload = function(){
			endLoading();
		}
	</script>
<jsp:include page="../footer.jsp"/>