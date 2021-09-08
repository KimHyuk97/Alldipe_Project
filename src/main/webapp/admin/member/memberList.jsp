<%@page import="dto.memberDTO.membergradeDTO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="dto.memberDTO.memberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.pageDTO.pageDTO"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DecimalFormat"%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String kind = (String)request.getAttribute("kind");
	String keyword = (String)request.getAttribute("keyword");
	String apply = (String)request.getAttribute("apply");
	String minDay = (String)request.getAttribute("minDay");
	String maxDay = (String)request.getAttribute("maxDay");
	BigDecimal minMileage = (BigDecimal)request.getAttribute("minMileage");
	BigDecimal maxMileage = (BigDecimal)request.getAttribute("maxMileage");
	String gender = (String)request.getAttribute("gender");
	String sort = (String)request.getAttribute("sort");
	int limit = (Integer)request.getAttribute("limit");
	ArrayList<memberDTO> list = (ArrayList)request.getAttribute("list");
	pageDTO paging = (pageDTO)request.getAttribute("paging");
	membergradeDTO grade = (membergradeDTO)request.getAttribute("grade");
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	DecimalFormat df = new DecimalFormat("#,###,###");
%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/member/total_memberlist.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
	<div class="cont_wrap">
  		<form action="memberList" method="post" name="list">
	        <div class="search_cont">
	            <div class="sch_tit">
	              <h2>회원 리스트</h2> 
	              <button type="submit">검색</button> 
	            </div>
	            <div class="sch_ck_cont">
	                <div class="sch_check">
	                    <p>검색어</p>
	                    <select name="kind">
	                        <option value="아이디"  <%if(kind != null && kind.equals("아이디")){  %> selected = "selected" <%} %>>아이디</option>
	                        <option value="이름"   <%if(kind != null && kind.equals("이름")){    %> selected = "selected" <%} %>>이름</option>
	                        <option value="이메일"  <%if(kind != null && kind.equals("이메일")){  %>  selected = "selected"<%} %>>이메일</option>
	                        <option value="전화번호" <%if(kind != null && kind.equals("전화번호")){ %> selected = "selected" <%} %>>전화번호</option>
	                    </select>
	                    <input type="text" name="keyword" value=<%=(keyword != null && !keyword.equals(""))?keyword:"" %>>
	                </div>
					<div class="sch_check">
	                    <p>회원등급</p>
	                    <select name="grade" onclick="membergrade()" id="grade">
	                       <option value=<%=(grade != null && grade.getSno() != 0)?grade.getSno():""%>><%=(grade != null)?grade.getGradeNm():"등급선택"%></option>
	                    </select>
	                </div>
	                <div class="sch_check">
	                    <p>가입승인</p>
	                    <label for="ra01"><input type="radio" name="apply" id="ra01" value="전체" <%if((apply != null && apply.equals("전체")) || (apply == null && apply.equals(""))){  %> checked = "checked" <%} %>>전체</label>
	                    <label for="ra02"><input type="radio" name="apply" id="ra02" value="승인" <%if(apply != null && apply.equals("승인")){  %> checked = "checked" <%} %>>승인</label>
	                    <label for="ra03"><input type="radio" name="apply" id="ra03" value="미승인"<%if(apply != null && apply.equals("미승인")){  %> checked = "checked" <%} %>>미승인</label>
	                </div>
	                <div class="sch_check" id="srch_area">
	                    <p>회원가입일</p>
	                    <input type='text' name="minDay" id="search_start_date" value=<%=(minDay != null && !minDay.equals(""))?minDay:"" %>> ~ <input type='text' name="maxDay" id="search_end_date" value=<%=(maxDay != null && !maxDay.equals(""))?maxDay:"" %>>
	                    <ul>
	                        <li><button id="r_yes" name="yesterday">어제</button></li>
	                        <li><button id="r_today" name="today">오늘</button></li>
	                        <li><button id="r_week" name="week">1주일</button></li>
	                        <li><button id="r_month" name="month">1개월</button></li>
	                        <li><button id="r_3month" name="3month">3개월</button></li>
	                    </ul>
	                </div>
	                <div class="sch_check">
	                    <p>마일리지</p>
	                    <input type="text" name="minMileage" value=<%=(minMileage != null && minMileage.intValue() != 0)?minMileage.intValue():"" %>> 이상 ~ <input type="text" name="maxMileage" value=<%=(maxMileage != null && maxMileage.intValue() != 0)?maxMileage.intValue():"" %>> 이하
	                </div>
	                <div class="sch_check">
	                    <p>성별</p>
	                    <label for="ra04"><input type="radio" name="gender" value="전체" id="ra04" <%if((gender != null && gender.equals("전체")) || (gender == null && gender.equals(""))){  %> checked = "checked" <%} %>>전체</label>
	                    <label for="ra05"><input type="radio" name="gender" value="남자" id="ra05" <%if(gender != null && gender.equals("남자")){  %> checked = "checked" <%} %>>남자</label>
	                    <label for="ra06"><input type="radio" name="gender" value="여자" id="ra06" <%if(gender != null && gender.equals("여자")){  %> checked = "checked" <%} %>>여자</label>
	                </div>
	            </div>     
	        </div>
	        <div class="sch_result">
	            <h3>검색결과 <p><%=paging.getListCount()%>건</p> </h3>
	            <div class="sch_btn">
	                <div class="btn_left">
	                    <button type="button" onclick="deleteFl()">선택탈퇴처리</button>
	                    <button type="button" onclick="sleepFl()">선택휴면해제</button>
	                </div>
	                <div class="btn_right">
	                    <button><i class="far fa-file-excel"></i> 엑셀다운로드</button>
	                    <select name="sort" onchange="formSubmit()">
	                        <option value="가입일순"  <%if(sort != null && sort.equals("가입일순")){ %> selected = "selected" <%} %>>가입일순  ↑</option>
	                        <option value="가입일순2" <%if(sort != null && sort.equals("가입일순2")){ %> selected = "selected" <%} %>>가입일순  ↓</option>
	                        <option value="등급순"   <%if(sort != null && sort.equals("등급순")){ %> selected = "selected" <%} %>>등급순  ↑</option>
	                        <option value="등급수2"  <%if(sort != null && sort.equals("등급순2")){ %> selected = "selected" <%} %>>등급순  ↓</option>
	                        <option value="주문금액"  <%if(sort != null && sort.equals("주문금액")){ %> selected = "selected" <%} %>>주문금액  ↑</option>
	                        <option value="주문금액2" <%if(sort != null && sort.equals("주문금액2")){ %> selected = "selected" <%} %>>주문금액  ↓</option>
	                        <option value="올페이"   <%if(sort != null && sort.equals("올페이")){ %> selected = "selected" <%} %>>올페이  ↑</option>
	                        <option value="올페이2"  <%if(sort != null && sort.equals("올페이2")){ %> selected = "selected" <%} %>>올페이  ↓</option>
	                        <option value="적립금"   <%if(sort != null && sort.equals("적립금")){ %> selected = "selected" <%} %>>적립금  ↑</option>
	                        <option value="적립금2"  <%if(sort != null && sort.equals("적립금2")){ %> selected = "selected" <%} %>>적립금  ↓</option>
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
	                    <th><input type="checkbox" name="ckAll" class="ck" id="ck_all"> </th>
	                    <th>번호</th>
	                    <th>아이디</th>
	                    <th>이름</th>
	                    <th>등급</th>
	                    <th>적립금</th>
	                    <th>올페이</th>
	                    <th>상품주문건수</th>
	                    <th>주문금액</th>
	                    <th>최종로그인</th>
	                    <th>휴면여부</th>
	                    <th>휴면해제일</th>
	                    <th>가입승인</th>
	                    <th>탈퇴여부</th>
	                    <th>메일/SMS발송</th>
	                    <th>회원가입일</th>
	                    <th>정보수정</th>
	                </tr>
	                <tfoot>
	                	<%if(!list.isEmpty()){ %>
		                	<%for(int i = 0; i < list.size(); i++){ %>
			                <tr>
			                    <td><input type="checkbox" name="ck" class="ck" id="ck01" value="<%=list.get(i).getMemNo()%>"></td>
			                    <td><%=(i+1) %></td>
			                    <td><%=list.get(i).getMemId()%></td>
			                    <td><%=list.get(i).getMemNm()%></td>
			                    <td>
			                    	<%if(list.get(i).getGradeSno()==1){%>
			                    		ALLDIPES
			                    	<%}else if(list.get(i).getGradeSno()==2){%>
			                    		FAMILY
			                    	<%}else if(list.get(i).getGradeSno()==3){%>
			                    		VIP
			                    	<%}else if(list.get(i).getGradeSno()==4){%>
			                    		VVIP
			                    	<%}else if(list.get(i).getGradeSno()==5){%>
			                    		ALL KING
			                    	<%} %>
			                    </td>
			                    <td><%=df.format(list.get(i).getMileage())%></td>
			                    <td><%=df.format(list.get(i).getDeposit())%></td>
			                    <td><%=list.get(i).getSaleCnt()%></td>
			                    <td><%=df.format(list.get(i).getSaleAmt())%></td>
			                    <td><%=(list.get(i).getLastLoginDt() != null)?date.format(list.get(i).getLastLoginDt()):"-"%></td>
			                    <td><%=(list.get(i).isSleepFl())?"휴면회원":"-"%></td>
			                    <td><%=((!list.get(i).isSleepFl() && list.get(i).getSleepWakeDt() != null))?date.format(list.get(i).getSleepWakeDt()):"-" %></td>
			                    <td><%=(list.get(i).isAppFl() == true)?"승인":"미승인"%></td>
			                    <td><%=(list.get(i).getDeleteFl().equals("y"))?"탈퇴회원":"-" %></td>
			                    <td><%=(list.get(i).isSleepMailFl() == true)?"승인":"미승인" %> / <%=(list.get(i).isSleepSmsFl() == true)?"승인":"미승인"%></td>
			                    <td><%=date.format(list.get(i).getRegDt()) %></td>
			                    <td><button type="button" id="modBtn" onclick="memberMod(<%=list.get(i).getMemNo()%>,<%=list.get(i).getGradeSno()%>)">수정</button></td>
			                </tr>
		                    <%} %>
	                    <%}%>
	            	</tfoot>
	            </table>
	        </div>
	        <div style="margin: 30px 0; text-align: center;">
	        	<%if(paging.getPage() > 1){ %>        	
					<a href="memberList?page=${paging.page-1}&kind=<%=kind %>&keyword=<%=keyword %>&apply=<%=apply %>&minDay=<%=minDay %>&maxDay=<%=maxDay %>&minMileage=<%=minMileage %>&maxMileage=<%=maxMileage %>&gender=<%=gender %>&sort=<%=sort %>&limit=<%=limit %>">[이전]</a>&nbsp;
	        	<%}%>
				<c:forEach begin="${paging.startPage}" end="${paging.endPage }" var="i" step="1">
					<c:choose>
						<c:when test="${i eq paging.page }">	
							[${i}]								
						</c:when>
						<c:otherwise>
							<a href="memberList?page=${i}&kind=<%=kind %>&keyword=<%=keyword %>&apply=<%=apply %>&minDay=<%=minDay %>&maxDay=<%=maxDay %>&minMileage=<%=minMileage %>&maxMileage=<%=maxMileage %>&gender=<%=gender %>&sort=<%=sort %>&limit=<%=limit %>">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<%if(paging.getPage() < paging.getMaxPage()){ %>
					<a href="memberList?page=${paging.page+1}&kind=<%=kind %>&keyword=<%=keyword %>&apply=<%=apply %>&minDay=<%=minDay %>&maxDay=<%=maxDay %>&minMileage=<%=minMileage %>&maxMileage=<%=maxMileage %>&gender=<%=gender %>&sort=<%=sort %>&limit=<%=limit %>">&nbsp;[다음]</a>			
				<%} %>
			</div>
		</form>
    </div>
<script src="${pageContext.request.contextPath}/admin/js/common/day.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/common/checkbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/common/grade.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/member/list.js"></script>