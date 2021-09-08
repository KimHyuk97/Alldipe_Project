<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dto.boardDTO.noticeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/notice.css">
<jsp:include page="../header.jsp"/>

	<div class="cont_wrap">
		<div class="wrap">
			<div class="title">
	            공지사항
	            <div class="top_btn">
	                <button type="submit">검색</button>
	                <button type="submit">초기화</button>
	            </div>
	        </div>
	        <ul class="nav">
	            <li><a href="./list?category=all&no=1">전체</a></li>
	            <li><a href="./list?category=normal&no=1">일반</a></li>
	            <li><a href="./list?category=news&no=1">파트너뉴스</a></li>
	            <li><a href="./list?category=edu&no=1">파트너교육</a></li>
	            <li><a href="./list?category=improve&no=1">시스템개선</a></li>
	        </ul>
	        <div class="content">
	<%
		noticeDTO notice = (noticeDTO)session.getAttribute("notice");
		if(notice==null){%>
			<script>
				alert('게시물이 없습니다!');
				location.href='./list';
			</script>
	<%	}else{%>
	            <table>
	            	<colgroup>
	            		<col width="7%">
	            		<col width="78%">
	            		<col width="15%">
	            	</colgroup>
	                <tr>
	                    <td><%=notice.getTheme() %></td>
	                    <td> <a href=""><%=notice.getTitle() %></a> </td>
	                    <td><%=new SimpleDateFormat("yyyy/MM/dd").format(notice.getRegDt()) %></td>
	                </tr>
	            </table>
	            <div class="content_txt">
	                <%=notice.getContents() %>
	            </div>
	<%	} %>
	        </div>
	
	        <div class="prev_next">
	            <a href="" class="arrow">&lt;&lt;</a>
	            <a href="">이전글</a>
	            <a href="">목록</a>
	            <a href="">다음글</a>
	            <a href="" class="arrow">&gt;&gt;</a>
	        </div>
		</div>
	</div>
	<script>
		window.onload = function(){
			endLoading();
		}
	</script>
<jsp:include page="../footer.jsp"/>