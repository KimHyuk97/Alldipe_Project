<%@page import="dto.adminDTO.adminDTO"%>
<%@page import="dto.boardDTO.boardDTO"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	boardDTO board = (boardDTO)request.getAttribute("board");
	adminDTO admin = (adminDTO)request.getAttribute("admin");
	String mode = (String)request.getAttribute("mode");
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/board/noticeInsert.css">
<script type="text/javascript" src="http://jsgetip.appspot.com"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
	<div class="cont_wrap">
        <div class="top_tit">
            <h2>공지사항 작성하기</h2>
        </div>
        <form action="./insertNotice" method="POST" enctype="multipart/form-data">
        	<input type="hidden" name="sno" value=<%=(board != null && board.getNo() > 0)?board.getNo():0%>>
        	<input type="hidden" name="mode" value="<%=mode%>">
        	<input type="hidden" name="ip" id="ip">
	        <div class="content">
	            <div class="cont cont01">
	                <div class="cont_tit">
	                    <p>게시 페이지</p>
	                </div>
	                <select name="partnersFl">
						<option value="true" <%if(board != null && board.isPartnersFl()){ %> selected="selected" <%} %>>파트너스 공지</option>
						<option value="false"<%if(board != null && !board.isPartnersFl()){ %> selected="selected" <%} %>>일반 공지</option>		
					</select>
	            </div>
	            <div class="cont cont02">
	                <div class="cont_tit">
	                    <p>유형</p>
	                </div>
	                <div class="cont_txt">
	                   	<select name="theme">
							<option value="normal" selected="selected">일반</option>
							<option value="news">파트너스뉴스</option>
							<option value="edu">파트너교육</option>
							<option value="approval">시스템개선</option>
						</select>
	                </div>
	            </div>
	            <div class="cont cont03">
	                <div class="cont_tit">
	                    <p>제목</p>
	                </div>
	                <div class="cont_txt">
	                    <input type="text" name="title" placeholder="제목을 최대 50자 이내로 등록하세요" value="<%=(board != null && !board.getTitle().equals(""))?board.getTitle():""%>">
	                </div>
	            </div>
	            <div class="cont cont04">
	                <div class="cont_tit">
	                    <p>내용</p>
	                </div>
	                <div class="cont_txt">
						<textarea name="contents" placeholder="내용을 입력하세요."><%=(board != null && !board.getContents().equals(""))?board.getContents():""%></textarea>
	                </div>
	            </div>
	            <div class="cont cont05">
	                <div class="cont_tit">
	                    <p>첨부파일</p>
	                </div>
	                <div class="cont_txt">
	                    <input type="file" name="img"  flies="<%=(board!=null && !board.getImg().equals(""))?board.getImg():""%>">
	                </div>
	             </div>
	        </div>
	        <div class="btn_box">
	          <input type="submit" value="게시하기"> 
	        </div>
        </form>
    </div>
    <script>
    document.getElementById('ip').value=ip();
    </script>