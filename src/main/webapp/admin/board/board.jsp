<%@page import="dto.boardDTO.boardDTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.pageDTO.pageDTO"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String kind = (String)request.getAttribute("kind");
String keyword = (String)request.getAttribute("keyword");
String date01 = (String)request.getAttribute("date01");
String date02 = (String)request.getAttribute("date02");
String boardType = (String)request.getAttribute("boardType");
String sort = (String)request.getAttribute("sort");
int limit = (Integer)request.getAttribute("limit");
ArrayList<boardDTO> list = (ArrayList)request.getAttribute("list");
pageDTO paging = (pageDTO)request.getAttribute("paging");
SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
DecimalFormat df = new DecimalFormat("#,###,###");
%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/board/total_board.css">
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
<div class="cont_wrap">
	<form action="./boardList"  method="post" name="form">
        <div class="search_cont">
            <div class="sch_tit">
              <h2>게시글 관리</h2> 
              <button type="reset">초기화</button>
              <button type="submit">검색</button> 
            </div>
            <div class="sch_ck_cont">
                <div class="sch_check">
                    <p>게시판</p>
                    <select name="boardType">
                        <option value="전체"    <%if(boardType != null && boardType.equals("전체")){  %> selected = "selected" <%} %>>전체</option>
                        <option value="상품문의" <%if(boardType != null && boardType.equals("상품문의")){  %> selected = "selected" <%} %>>상품문의</option>
                        <option value="자주묻는질문" <%if(boardType != null && boardType.equals("자주묻는질문")){  %> selected = "selected" <%} %>>자주묻는질문</option>
                        <option value="1:1문의" <%if(boardType != null && boardType.equals("1:1문의")){  %> selected = "selected" <%} %>>1:1문의</option>
                        <option value="공지사항" <%if(boardType != null && boardType.equals("공지사항")){  %> selected = "selected" <%} %>>공지사항</option>
                    </select>
                </div>
            	<div class="sch_check">
                    <p>검색어</p>
                    <select name="kind">
                        <option value="전체"    <%if(kind != null && kind.equals("전체")){  %> selected = "selected" <%} %> >전체</option>
                        <option value="작성자"   <%if(kind != null && kind.equals("작성자")){  %> selected = "selected" <%} %>  >작성자</option>
                        <option value="작성자ID" <%if(kind != null && kind.equals("작성자ID")){  %> selected = "selected" <%} %>>작성자ID</option>
                        <option value="제목"    <%if(kind != null && kind.equals("제목")){  %> selected = "selected" <%} %>>제목</option>
                    </select>
                    <input type="text" name="keyword" value="<%=(keyword != null && !keyword.equals(""))?keyword:"" %>">
                </div>
                 <div class="sch_check" id="srch_area">
                    <p>일자</p>
                    <input type='text' id="search_start_date" name="date01" value="<%=(date01 != null && !date01.equals(""))?date01:"" %>"> ~ <input type='text' id="search_end_date" name="date02" value="<%=(date02 != null && !date02.equals(""))?date02:"" %>">
                    <ul>
                        <li><button type="button"  id="r_yes" name="yesterday">어제</button></li>
                        <li><button type="button"  id="r_today" name="today">오늘</button></li>
                        <li><button type="button"  id="r_week" name="week">1주일</button></li>
                        <li><button type="button"  id="r_month" name="month">1개월</button></li>
                        <li><button type="button"  id="r_3month" name="3month">3개월</button></li>
                    </ul>
                 </div>             
            </div>     
        </div>
        <div class="sch_result">
            <h3>검색결과 <p>0건</p> </h3>
            <div class="sch_btn">
                <div class="btn_left">
 					<button type="button" onclick="del()">선택삭제</button>
                </div>
                <div class="btn_right">
                    <select name="sort" onchange="formSubmit()">
                        <option value="등록순"   <%if(sort != null && sort.equals("등록순")){ %> selected = "selected" <%} %>>등록순  ↑</option>
                        <option value="등록순2"  <%if(sort != null && sort.equals("등록순2")){ %> selected = "selected" <%} %>>등록순  ↓</option>
                        <option value="조회수"   <%if(sort != null && sort.equals("조회수")){ %> selected = "selected" <%} %>>조회수  ↑</option>
                        <option value="조회수2"  <%if(sort != null && sort.equals("조회수2")){ %> selected = "selected" <%} %>>조회수  ↓</option>
                    </select>
                    <select name="limit" onchange="formSubmit()">
                        <option value="10" <%if(limit == 10){ %> selected = "selected" <%} %>>10개보기</option>
                        <option value="20" <%if(limit == 20){ %> selected = "selected" <%} %>>20개보기</option>
                        <option value="30" <%if(limit == 30){ %> selected = "selected" <%} %>>30개보기</option>
                        <option value="40" <%if(limit == 40){ %> selected = "selected" <%} %>>40개보기</option>
                        <option value="50" <%if(limit == 50){ %> selected = "selected" <%} %>>50개보기</option>
                    </select>  
                </div>  
            </div>
            <table>
            	<colgroup>
            		<col width="5%"/>
            		<col width="5%"/>
            		<col width="15%"/>
            		<col width="25%"/>
            		<col width="10%"/>
            		<col width="5%"/>
            		<col width="15%"/>
            		<col width="10%"/>
            		<col width="10%"/>
            	</colgroup>
                <tr>
                    <th><input type="checkbox" name="ckall" class="ck" id="ck_all"> </th>
                    <th>번호</th>
                    <th>게시판종류</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>답변상태</th>
                    <th>수정/답변</th>
                </tr>
                <tfoot>
                <%if(list != null){ %>
                <%for(int i = 0; i < list.size(); i++){ %>
                <tr>
                    <td><input type="checkbox" name="ck" class="ck" id="ck01" value=<%=list.get(i).getNo()%>></td> 
                    <td><%=(i+1)%></td>
                    <td><%=list.get(i).getBoardType() %></td>
                    <td><%=list.get(i).getTitle() %></td>
                    <td><%=list.get(i).getWriterNm() %></td>
                    <td><%=list.get(i).getViewCnt() %></td>
                    <td><%=date.format(list.get(i).getRegDt()) %></td>
                    <td>
                    	<%if(list.get(i).getAdminNo() > 0){ %>
                    	답변완료
                    	<%}else{ %>
                    	답변대기           	
                    	<%} %>
                    </td>
					<td>
						<%if(!list.get(i).getBoardType().equals("공지사항")){%>
						<button type="button" onclick="answer(<%=list.get(i).getNo()%>)">답변하기</button>
						<%}else{ %>
						<button type="button" onclick="noticeMod(<%=list.get(i).getNo()%>)">수정</button>
						<%} %>
					</td>
                </tr>
                <%} %>
                <%} %>
                </tfoot>
            </table>
        </div>
        <div style="margin: 30px 0; text-align: center;">
        	<%if(paging.getPage() > 1){ %>        	
				<a href="boardList?page=${paging.page-1}&kind=<%=kind %>&keyword=<%=keyword %>&boardType=<%=boardType%>&minDay=<%=date01 %>&maxDay=<%=date02 %>&sort=<%=sort %>&limit=<%=limit %>">[이전]</a>&nbsp;
        	<%}%>
			<c:forEach begin="${paging.startPage}" end="${paging.endPage }" var="i" step="1">
				<c:choose>
					<c:when test="${i eq paging.page }">	
						[${i}]								
					</c:when>
					<c:otherwise>
						<a href="boardList?page=${i}&kind=<%=kind %>&keyword=<%=keyword %>&boardType=<%=boardType%>&minDay=<%=date01 %>&maxDay=<%=date02 %>&sort=<%=sort %>&limit=<%=limit %>">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<%if(paging.getPage() < paging.getMaxPage()){ %>
				<a href="boardList?page=${paging.page+1}&kind=<%=kind %>&keyword=<%=keyword %>&boardType=<%=boardType%>&minDay=<%=date01 %>&maxDay=<%=date02 %>&sort=<%=sort %>&limit=<%=limit %>">&nbsp;[다음]</a>			
			<%} %>
		</div>
	</form>
</div>
<script src="${pageContext.request.contextPath}/admin/js/common/checkbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/common/day.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/board/board.js"></script>