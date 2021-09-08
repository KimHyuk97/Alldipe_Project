<%@page import="dto.memberDTO.memberDTO"%>
<%@page import="dto.memberDTO.membermileageDTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="dto.memberDTO.memberdepositDTO"%>
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
	String handleMode = (String)request.getAttribute("handleMode");
	BigDecimal price01 = (BigDecimal)request.getAttribute("price01");
	BigDecimal price02 = (BigDecimal)request.getAttribute("price02");
	String sort = (String)request.getAttribute("sort");
	int limit = (Integer)request.getAttribute("limit");
	ArrayList<memberdepositDTO> list = (ArrayList)request.getAttribute("list");
	ArrayList<memberDTO> mList = (ArrayList)request.getAttribute("mList");
	pageDTO paging = (pageDTO)request.getAttribute("paging");
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	DecimalFormat df = new DecimalFormat("#,###,###");

%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/member/total_deposit.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
<div class="cont_wrap">
	<form action="memberDeposit" method="post" name="form">
        <div class="search_cont">
            <div class="sch_tit">
              <h2>올페이 관리</h2> 
              <button type="reset">초기화</button>
              <button type="submit">검색</button> 
            </div>
            <div class="sch_ck_cont">
                <div class="sch_check">
                    <p>검색어</p>
                    <select name="kind">
                        <option value="아이디"  <%if(kind != null && kind.equals("아이디")){  %> selected = "selected" <%} %>>아이디</option>
                        <option value="이름"   <%if(kind != null && kind.equals("이름")){    %> selected = "selected" <%} %>>이름</option>
                        <option value="관리자"  <%if(kind != null && kind.equals("관리자")){  %>  selected = "selected"<%} %>>관리자</option>
                    </select>
                    <input type="text" name="keyword" value="<%=(keyword != null && !keyword.equals(""))?keyword:"" %>">
                </div>
                 <div class="sch_check" id="srch_area">
                    <p>지급/차감일</p>
                    <input type="text" name="date01" id="search_start_date" value="<%=(date01 != null && !date01.equals(""))?date01:"" %>"> ~ <input type="text" name="date02" id="search_end_date" value="<%=(date02 != null && !date02.equals(""))?date02:"" %>">
                    <ul>
                        <li><button type="button" id="r_yes" name="yesterday">어제</button></li>
                        <li><button type="button" id="r_today" name="today">오늘</button></li>
                        <li><button type="button" id="r_week" name="week">1주일</button></li>
                        <li><button type="button" id="r_month" name="month">1개월</button></li>
                        <li><button type="button" id="r_3month" name="3month">3개월</button></li>
                    </ul>
                 </div>
                <div class="sch_check">
                    <p>지급/차감구분</p>
                    <label><input type="radio" name="handleMode"  value="전체" <%if((handleMode != null && handleMode.equals("전체")) || (handleMode == null && handleMode.equals(""))){  %> checked = "checked" <%} %>>전체</label>
                    <label><input type="radio" name="handleMode"  value="지급" <%if(handleMode != null && handleMode.equals("지급")){  %> checked = "checked" <%} %>>지급</label>
                    <label><input type="radio" name="handleMode"  value="차감" <%if(handleMode != null && handleMode.equals("차감")){  %> checked = "checked" <%} %>>차감</label>
                    <label><input type="radio" name="handleMode"  value="입금" <%if(handleMode != null && handleMode.equals("입금")){  %> checked = "checked" <%} %>>입금</label>
                    <label><input type="radio" name="handleMode"  value="환불" <%if(handleMode != null && handleMode.equals("환불")){  %> checked = "checked" <%} %>>환불</label>
                    
                </div>
                <div class="sch_check">
                    <p>금액범위</p>
                    <input type="text" name="price01" value=<%=(price01 != null && price01.intValue() != 0)?price01.intValue():"" %>>&nbsp;원 ~ <input type="text" name="price02" value=<%=(price02 != null && price02.intValue() != 0)?price02.intValue():"" %>>&nbsp;원
                </div>
            </div>     
        </div>
        <div class="sch_result">
            <h3>검색결과 <p><%=(paging != null)?paging.getListCount():0 %>건</p> </h3>
            <div class="sch_btn">
                <div class="btn_left">
 					<button type="button" onclick="del()">선택삭제</button>
 					<button type="button" onclick="payThedeposit()">올페이 지급</button>
                </div>
                <div class="btn_right">
                    <select name="sort" onchange="formSubmit()">
                        <option value="등록순"   <%if(sort != null && sort.equals("등록순")){ %> selected = "selected" <%} %>>등록순  ↑</option>
                        <option value="등록순2"  <%if(sort != null && sort.equals("등록순2")){ %> selected = "selected" <%} %>>등록순  ↓</option>
                        <option value="올페이"   <%if(sort != null && sort.equals("올페이")){ %> selected = "selected" <%} %>>올페이  ↑</option>
                        <option value="올페이2"  <%if(sort != null && sort.equals("올페이2")){ %> selected = "selected" <%} %>>올페이  ↓</option>
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
                <tr>
                    <th><input type="checkbox" name="ckall" class="ck" id="ck_all"> </th>
                    <th>번호</th>
                    <th>아이디</th>
                    <th>이름</th>
                    <th>등급</th>
                    <th>지급액</th>
                    <th>차감액</th>
                    <th>잔여올페이</th>
                    <th>지급/차감일</th>
                    <th>처리자</th>
                    <th>사유</th>
                </tr>
				<tfoot>
                <%if(list != null){ %>
                <%for(int i = 0; i < list.size(); i++){ %>
                <tr>
                    <td><input type="checkbox" name="ck" class="ck" id="ck01" value=<%=list.get(i).getSno() %>></td>
                    <td><%=list.get(i).getSno()%></td>
                    <td><%=mList.get(i).getMemId() %></td>
                    <td><%=mList.get(i).getMemNm() %></td>
	                <td>
	                   	<%if(mList.get(i).getGradeSno()==1){%>
	                   		ALLDIPES
	                   	<%}else if(mList.get(i).getGradeSno()==2){%>
	                   		FAMILY
	                   	<%}else if(mList.get(i).getGradeSno()==3){%>
	                   		VIP
	                   	<%}else if(mList.get(i).getGradeSno()==4){%>
	                   		VVIP
	                   	<%}else if(mList.get(i).getGradeSno()==5){%>
	                   		ALL KING
	                   	<%} %>
	                </td>
                    <td><%=df.format(list.get(i).getBeforeDeposit()) %></td>
                    <td><%=df.format(list.get(i).getAfterDeposit())%></td>
                    <td><%=df.format(list.get(i).getDeposit())%></td>
                    <td><%=date.format(list.get(i).getRegDt())%></td>
                    <td>관리자 : <%=list.get(i).getManagerNo() %></td>
                    <td><%=list.get(i).getReasonCd() %></td>
                </tr>
                <%} %>
                <%} %>
                </tfoot>
            </table>
        </div>
        <div style="margin: 30px 0; text-align: center;">
        	<%if(paging.getPage() > 1){ %>        	
				<a href="memberDeposit?page=${paging.page-1}&kind=<%=kind %>&keyword=<%=keyword %>&handleMode=<%=handleMode %>&minDay=<%=date01 %>&maxDay=<%=date02 %>&minMileage=<%=price01 %>&maxMileage=<%=price02 %>&sort=<%=sort %>&limit=<%=limit %>">[이전]</a>&nbsp;
        	<%}%>
			<c:forEach begin="${paging.startPage}" end="${paging.endPage }" var="i" step="1">
				<c:choose>
					<c:when test="${i eq paging.page }">	
						[${i}]								
					</c:when>
					<c:otherwise>
						<a href="memberDeposit?page=${i}&kind=<%=kind %>&keyword=<%=keyword %>&handleMode=<%=handleMode %>&minDay=<%=date01 %>&maxDay=<%=date02 %>&minMileage=<%=price01 %>&maxMileage=<%=price02 %>&sort=<%=sort %>&limit=<%=limit %>">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<%if(paging.getPage() < paging.getMaxPage()){ %>
				<a href="memberDeposit?page=${paging.page+1}&kind=<%=kind %>&keyword=<%=keyword %>&handleMode=<%=handleMode %>&minDay=<%=date01 %>&maxDay=<%=date02 %>&minMileage=<%=price01 %>&maxMileage=<%=price02 %>&sort=<%=sort %>&limit=<%=limit %>">&nbsp;[다음]</a>			
			<%} %>
		</div>
	</form>
</div>
<script src="${pageContext.request.contextPath}/admin/js/common/checkbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/common/day.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/member/deposit.js"></script>