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
	
	String cate = request.getParameter("cate");
	
%>
<jsp:include page="../header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/partners_q_a.css">
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
	<div class="cont_wrap">
		<div class="wrap">
	        <div class="title">
	            자주묻는질문
	        </div>
	        <div class="ask">
	           <i class="far fa-question-circle"></i><span>1:1 문의하기</span>
	        </div>
	        <ul class="nav">
	            <li <%if(cate.equals("all")){ %>id="color_cate"<%} %>><a href="./fqa?no=1&cate=">전체</a></li>
	            <li <%if(cate.equals("goods")){ %>id="color_cate"<%} %>><a href="">상품등록/관리</a></li>
	            <li <%if(cate.equals("delivery")){ %>id="color_cate"<%} %>><a href="">배송/반품/교환</a></li>
	            <li <%if(cate.equals("calculate")){ %>id="color_cate"<%} %>><a href="">정산/세금계산서</a></li>
	            <li <%if(cate.equals("user")){ %>id="color_cate"<%} %>><a href="">회원정보</a></li>
	        </ul>
	        <div class="content">
	            <div class="main">
	                <div class="main_title main_title_header">
	                    <div class="main_title_td main_title_td1">No</div>
	                    <div class="main_title_td main_title_td2">분류</div>
	                    <div class="main_title_td main_title_td3">제목</div>
	                    <div class="main_title_td main_title_td4">작성일</div>
	                </div>
<%
	if(list!=null && list.size()>0){
		int cnt = 0;
		for(qaDTO dto : list){
			
%>					<div class="main_list_div">
						<div class="main_title">
		                    <div class="main_title_td1"><%=cnt %></div>
		                    <div class="main_title_td2"><%=dto.getCate() %></div>
		                    <div class="main_title_td3" onclick="showContents(<%=cnt%>)"><%=dto.getTitle() %></div>
		                    <div class="main_title_td4"><%=dto.getRegDt() %></div>
		                </div>
		                <div class="main_content_div">
		                    <div>
								<%=dto.getContents() %>
		                    </div>
		                </div>
	                </div>
<%
		cnt++;
		}
	}
%>	            
	        </div>
	        <div class="number">
	<%
		if(p!=null){
			if(p.getFirstPageNo()!=p.getStartPageNo()){
	%>
				<a href="./fqa?no=<%=p.getStartPageNo()-1%>&cate=<%=request.getParameter("cate") %>" class="arrow">&lt;</a>
	<%
			}
			if(p.getStartPageNo()>0){
				for(int i = p.getStartPageNo(); i<=p.getEndPageNo(); i++){
	%>
				<a href="./fqa?no=<%=i %>&cate=<%=request.getParameter("cate") %>"><%=i %></a>
	<%		
				}
			}
			if(p.getFinalPageNo()!=p.getEndPageNo()){
	%>
				<a href="./fqa?no=<%=p.getEndPageNo()+1%>&cate=<%=request.getParameter("cate") %>" class="arrow">></a>
	<%
			}
		}
	%>	   </div>
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
        </div>
    </div>
    </div>
    <script>
	    window.onload = function(){
	 		endLoading();
	 	}
	    
	    function showContents(i){
	    	
	    	const contents = document.getElementsByClassName("main_content_div");
	    	
	    	if(contents[i].style.height == "0px"){
	    		contents[i].style.height = "auto";
	    	}else{
	    		contents[i].style.height = "0px";
	    	}
	    }
	    
    </script>

</body>
</html>