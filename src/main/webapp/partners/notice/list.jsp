<%@page import="service.paging.paging"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dto.boardDTO.noticeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/notice_list.css">

<%
	ArrayList<noticeDTO> nList = (ArrayList)session.getAttribute("nList");
	paging p = (paging)session.getAttribute("paging");
	session.removeAttribute("nList");
	session.removeAttribute("paging");
	
%>
	<div class="cont_wrap">
		<div class="wrap">
			<div class="title">
	            공지사항
	        </div>
	        <ul class="nav">
	            <li><a href="./list?no=1&theme=all">전체</a></li>
	            <li><a href="./list?no=1&theme=normal">일반</a></li>
	            <li><a href="./list?no=1&theme=news">파트너뉴스</a></li>
	            <li><a href="./list?no=1&theme=edu">파트너교육</a></li>
	            <li><a href="./list?no=1&theme=improval">시스템개선</a></li>
	        </ul>
	        <div class="content">
	            <div class="main">
	                <div class="main_title main_title_header">
	                    <div class="main_title_td main_title_td1">No</div>
	                    <div class="main_title_td main_title_td2">분류</div>
	                    <div class="main_title_td main_title_td3">제목</div>
	                    <div class="main_title_td main_title_td4">작성일</div>
	                </div>
	  				<div class="main_list_div">              
	<%
		if(nList != null && nList.size()>0){
			for(noticeDTO dto : nList){
		%>			
						<div class="main_title select_notice">
		                    <div class="main_title_td1"><%=dto.getSno() %></div>
		                    <div class="main_title_td2"><%=dto.getTheme() %></div>
		                    <div class="main_title_td3 select_notice" onclick="location.href='./notice?no=<%=dto.getSno()%>';"><%=dto.getTitle() %></div>
		                    <div class="main_title_td4"><%=new SimpleDateFormat("yyyy/MM/dd").format(dto.getRegDt())%></div>
		                </div>
	<%
			}
		}else{
		%>				<div>
		                    <div>등록된 공지사항이 없습니다.</div>
		                </div>
	<%		
		}
		%>                              
		            </div>
	            </div>
	        </div>
		        <div class="number">
	<%
		if(p!=null){
			if(p.getFirstPageNo()!=p.getStartPageNo()){
	%>
				<a href="./list?no=<%=p.getStartPageNo()-1%>&theme=<%=request.getParameter("theme") %>" class="arrow">&lt;</a>
	<%
			}
			if(p.getStartPageNo()>0){
				for(int i = p.getStartPageNo(); i<=p.getEndPageNo(); i++){
	%>
				<a href="./list?no=<%=i %>&theme=<%=request.getParameter("theme") %>"><%=i %></a>
	<%		
				}
			}
			if(p.getFinalPageNo()!=p.getEndPageNo()){
	%>
				<a href="./list?no=<%=p.getEndPageNo()+1%>&theme=<%=request.getParameter("theme") %>" class="arrow">></a>
	<%
			}
		}
	%>
	        </div>
	        <div class="input">
	            <div class="input_btn">
	                <select name="선택">
	                <option value="제목">제목</option>
	                <option value="전체">전체</option>
	                <option value="분류">분류</option>
	                </select>
	                <input type="text">
	                <input type="button" value="검색" class="button">
	            </div>
	        </div>
	    
			
			<div class="notice_div">
				<ul>
	
				</ul>
			</div>
		</div>	
	</div>
	<script>
		window.onload=function(){
			endLoading();
		}
	</script>
<jsp:include page="../footer.jsp"/>