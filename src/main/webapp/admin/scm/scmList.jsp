<%@page import="dto.pageDTO.pageDTO"%>
<%@page import="dto.scmDTO.scmDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/scm/total_supply.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
<%
	ArrayList<scmDTO> list = (ArrayList)request.getAttribute("list");
	String kind = (String)request.getAttribute("kind");
	String keyword = (String)request.getAttribute("keyword");
	String date01 = (String)request.getAttribute("date01");
	String date02 = (String)request.getAttribute("date02");
	String scmState = (String)request.getAttribute("scmState");
	int limit = (Integer)request.getAttribute("limit");

	pageDTO paging = (pageDTO)request.getAttribute("paging");
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	DecimalFormat df = new DecimalFormat("#,###,###");
	
%>

<div class="cont_wrap">
	<form action="./scmList" method="post">
        <div class="top_tit">
            <h1>공급사 관리</h1>
        </div>
        <div class="search_cont">
            <div class="sch_tit">
              <h2>공급사 검색</h2> 
              <button type="submit">검색</button> 
            </div>
            <div class="sch_ck_cont">
                <div class="sch_check">
                    <p>검색어</p>
                    <select name="kind">
                        <option value="전체" <%if(kind != null && kind.equals("전체")){  %> selected = "selected" <%} %>>통합검색</option>
                        <option value="공급사명" <%if(kind != null && kind.equals("공급사명")){  %> selected = "selected" <%} %>>공급사명</option>
                        <option value="공급사코드" <%if(kind != null && kind.equals("공급사코드")){  %> selected = "selected" <%} %>>공급사코드</option>
                        <option value="대표자" <%if(kind != null && kind.equals("대표자")){  %> selected = "selected" <%} %>>대표자</option>
                        <option value="사업자등록번호" <%if(kind != null && kind.equals("사업자등록번호")){  %> selected = "selected" <%} %>>사업자등록번호</option>
                    </select>
                    <input type="text" name="keyword" placeholder="검색어를 입력해주세요." value="<%=(keyword != null && !keyword.equals(""))?keyword:"" %>">
                </div>
                <div class="sch_check" id="srch_area">
                    <p>일자</p>
                    <input type='text' id="search_start_date" name="date01" value="<%=(date01 != null && !date01.equals(""))?date01:"" %>"> ~ <input type='text' id="search_end_date" name="date02" value="<%=(date02 != null && !date02.equals(""))?date02:"" %>">
                    <ul>
                        <li><button type="button" id="r_yes" name="yesterday">어제</button></li>
                        <li><button type="button" id="r_today" name="today">오늘</button></li>
                        <li><button type="button" id="r_week" name="week">1주일</button></li>
                        <li><button type="button" id="r_month" name="month">1개월</button></li>
                        <li><button type="button" id="r_3month" name="3month">3개월</button></li>
                    </ul>
                </div>
                <div class="sch_check">
                    <p>운영상태</p>
                    <label><input type="radio" name="scmState" value="전체" <%if((scmState != null && scmState.equals("전체")) || scmState == null){  %> checked = "checked" <%} %>>전체</label>
                    <label><input type="radio" name="scmState" value="운영" <%if(scmState != null && scmState.equals("운영")){  %> checked = "checked" <%} %>>운영</label>
                    <label><input type="radio" name="scmState" value="탈퇴" <%if(scmState != null && scmState.equals("탈퇴")){  %> checked = "checked" <%} %>>탈퇴</label>
                </div>
            </div>     
        </div>
        <div class="sch_result">
            <h3>공급사 검색결과 <p><%=paging.getListCount()%>건</p> </h3>
            <div class="sch_btn">
                <button>선택삭제</button>
                <select name="limit" onchange="formSubmit()">
                    <option value="10" <%if(limit == 10){ %> selected = "selected" <%} %>>10개보기</option>
                    <option value="20" <%if(limit == 20){ %> selected = "selected" <%} %>>20개보기</option>
                    <option value="30" <%if(limit == 30){ %> selected = "selected" <%} %>>30개보기</option>
                    <option value="40" <%if(limit == 40){ %> selected = "selected" <%} %>>40개보기</option>
                    <option value="50" <%if(limit == 50){ %> selected = "selected" <%} %>>50개보기</option>
                </select>  
            </div>
            <table>
            	<colgroup>
            		<col width="5%"/>
            		<col width="5%"/>
            		<col width="10%"/>
            		<col width="10%"/>
            		<col width="10%"/>
            		<col width="5%"/>
            		<col width="15%"/>
            		<col width="10%"/>
            		<col width="10%"/>
            		<col width="10%"/>
            		<col width="5%"/>
            	</colgroup>
                <tr>
                    <td><input type="checkbox" name="ckall" class="ck" id="ck_all"> </td>
                    <td>상태</td>
                    <td>아이디</td>
                    <td>공급사명</td>
                    <td>공급사코드</td>
                    <td>상품등록권한</td>
                    <td>상품수정권한</td>
                    <td>상품삭제권한</td>
                    <td>등록일</td>
                    <td>담당자</td>
                    <td>수정</td>
                </tr>
<%
	if(list!= null && list.size()>0){
		for(scmDTO dto : list){
%>
		  		<tr>
                    <td><input type="checkbox" name="ck" class="ck" id="ck01" value=<%=dto.getScmNo()%>></td>
                    <td><%=dto.getScmState() %></td>
                    <td><%=dto.getMemNo() %></td>
                    <td><%=dto.getCompanyNm()%></td>
                    <td><%=dto.getScmNo()%></td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td></td>
                    <td><button onclick="./updateScm?no=<%=dto.getScmNo()%>">수정</button></td>
                </tr>
<%
		}
		
	}else{
%>		
		<tr><td colspan="11">등록된 리스트가 없습니다.</td></tr>
<%	} %>
 	 </table>
			<div class="num">
                <%if(paging.getPage() > 1){ %>        	
				<a href="boardList?page=${paging.page-1}&kind=<%=kind %>&keyword=<%=keyword %>&scmState=<%=scmState%>&minDay=<%=date01 %>&maxDay=<%=date02 %>&limit=<%=limit %>">[이전]</a>&nbsp;
        	<%}%>
			<c:forEach begin="${paging.startPage}" end="${paging.endPage }" var="i" step="1">
				<c:choose>
					<c:when test="${i eq paging.page }">	
						[${i}]								
					</c:when>
					<c:otherwise>
						<a href="boardList?page=${i}&kind=<%=kind %>&keyword=<%=keyword %>&scmState=<%=scmState%>&minDay=<%=date01 %>&maxDay=<%=date02 %>&limit=<%=limit %>">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<%if(paging.getPage() < paging.getMaxPage()){ %>
				<a href="boardList?page=${paging.page+1}&kind=<%=kind %>&keyword=<%=keyword %>&scmState=<%=scmState%>&minDay=<%=date01 %>&maxDay=<%=date02 %>&limit=<%=limit %>">&nbsp;[다음]</a>			
			<%} %>
            </div>
        </div>
	</form>
</div>
<script src="${pageContext.request.contextPath}/admin/js/common/checkbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/common/day.js"></script>