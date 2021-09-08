<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="service.paging.paging"%>
<%@page import="dto.boardDTO.boardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<boardDTO> list = (ArrayList)session.getAttribute("list");
	paging p = (paging)session.getAttribute("paging");
%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bottom/service.css">
<jsp:include page="../header.jsp"/>
	<div id="container">
		<div id="container_inner">
			<span class="inner_title">고객센터</span>
			<div class="main_menu">
				<ul class="main_menu_li">
					<li onclick="location.href='./service?type=notice'">공지사항</li>
					<li onclick="location.href='./service?type=qa&theme=fqa'">FAQ</li>
				</ul>
			</div>
			<div class="sub_menu">
<%	if(request.getParameter("type").equals("notice")){ %>
				<ul class="sub_menu_li sub_menu_notice">
					<li><a href="./service?type=notice&no=1&cate=null">전체</a></li>
					<li><a href="./service?type=notice&no=1&cate=공지">공지</a></li>
					<li><a href="./service?type=notice&no=1&cate=이벤트">이벤트</a></li>
				</ul>
<%	}else{ %>
				<ul class="sub_menu_li sub_menu_fqa">
					<li><a href="./service?type=qa&theme=fqa&no=1&cate=null">전체</a></li>
					<li><a href="./service?type=qa&theme=fqa&no=1&cate=배송">배송</a></li>
					<li><a href="./service?type=qa&theme=fqa&no=1&cate=교환/환불">교환/환불</a></li>
					<li><a href="./service?type=qa&theme=fqa&no=1&cate=구매/결제">구매/결제</a></li>
					<li><a href="./service?type=qa&theme=fqa&no=1&cate=쿠폰/포인트">쿠폰/포인트</a></li>
					<li><a href="./service?type=qa&theme=fqa&no=1&cate=기타">기타</a></li>
				</ul>


<%	} %>
				<div class="content">
					<div class="content_row content_title">
						<span class="list01">No</span>
						<span class="list02">분류</span>
						<span class="list03">제목</span>
						<span class="list04">작성자</span>
						<span class="list05">등록일</span>
					</div>
<%
	if(list!=null && list.size()>0){
		for(boardDTO dto : list){
%>				
					<div class="content_row content_list">
						<span class="list01"><%=dto.getNo() %></span>
						<span class="list02"><%=dto.getCate() %></span>
						<span class="list03"><%=dto.getTitle() %></span>
						<span class="list04"><%=dto.getWriterNm() %></span>
						<span class="list05"><%=new SimpleDateFormat("YYYY MM/dd").format(dto.getRegDt().getTime())%></span>
					</div>
				
<%		}
	}
%>				</div>			
			</div>
			<div class="paging">
				<ul>
<%
		if(p!=null){
			System.out.println(p);
			if(p.getFirstPageNo()!=p.getStartPageNo()){
	%>
				<li><a href="./service?type=<%=request.getParameter("type") %>&no=<%=p.getStartPageNo()-1%>&cate=<%=request.getParameter("theme") %>" class="arrow">&lt;</a></li>
	<%
			}
			if(p.getStartPageNo()>0){
				for(int i = p.getStartPageNo(); i<=p.getEndPageNo(); i++){
	%>
				<li><a href="./service?type=<%=request.getParameter("type") %>&no=<%=i %>&cate=<%=request.getParameter("theme") %>"><%=i %></a></li>
	<%		
				}
			}
			if(p.getFinalPageNo()!=p.getEndPageNo()){
	%>
				<li><a href="./service?type=<%=request.getParameter("type") %>&no=<%=p.getEndPageNo()+1%>&cate=<%=request.getParameter("theme") %>" class="arrow">></a></li>
	<%
			}
		}
	%>
				</ul>
			</div>
		</div>
	</div>
<jsp:include page="../footer.jsp" flush="false"/>