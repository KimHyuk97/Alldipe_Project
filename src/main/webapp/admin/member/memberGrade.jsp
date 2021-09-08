<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.memberDTO.membergradeDTO"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import=" service.adminService.adminMemberService"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/member/total_membergrade.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<%
	ArrayList<membergradeDTO> grade = (ArrayList)request.getAttribute("grade");
	int[] count = (int[])request.getAttribute("cnt");
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	DecimalFormat df = new DecimalFormat("#,###,###");
%>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
   <div class="cont_wrap">

        <div class="search_cont">
            <div class="sch_tit">
              <h2>회원 등급 관리</h2> 
              <button type="button" onclick="gradeAdd()">등급 추가</button> 
            </div>   
        </div>
        <div class="sch_result">
            <h3>등급 조건</h3>
            <div class="sch_btn">
                <div class="btn_left">
                    <button type="button" onclick="del()">선택삭제</button>
                    <button type="button" onclick="gradeRating(<%=grade.size()%>)">회원등급 수동평가</button>
                </div>                
            </div>
            <table>
                <tr>
                    <th><input type="checkbox" name="ckall" class="ck" id="ck_all"> </th>
                    <th>등급순서</th>
                    <th>등급명</th>
                    <th>회원수</th>
                    <th>등급조건</th>
                    <th>등급혜택</th>
                    <th>등록자</th>
                    <th>등록일</th>
                    <th>정보수정</th>
                </tr>
                <tfoot>
                	<%for(int i=0; i<grade.size(); i++){ %>                
	                <tr>
	                    <td><input type="checkbox" name="ck" class="ck" id="ck01" value=<%=grade.get(i).getSno()%>></td>
	                    <td><%=grade.get(i).getGradeSort()%></td>
	                    <td><%if(grade != null && grade.get(i).getGradeImage()!=null){ %><img src="${pageContext.request.contextPath}/fileF/grade/<%=grade.get(i).getGradeImage()%>" width="16px"><%}%><%=grade.get(i).getGradeNm()%></td>
	                    <td><%=count[i]%></td>
	                    <td>최근 3개월 주문건 <%=grade.get(i).getOrderCnt()%> 이상 / 주문금액 <%=df.format(grade.get(i).getOrderPriceMore())%>원 이상~ <%=df.format(grade.get(i).getOrderPriceBelow())%>원 이하</td>
	                    <td>추가 <%=grade.get(i).getMileagePercent()%>% 적립</td>
	                    <td><p>관리자</p><p>(<%=grade.get(i).getManagerNm()%>)</p></td>
	                    <td><%=date.format(grade.get(i).getRegDt())%></td>
	                    <td><button type="button" onclick="gradeMod(<%=grade.get(i).getSno()%>)">수정</button></td>
	                </tr>
	                <%} %>
                </tfoot>
            </table>
            <div class="pr">            
	            <span>* 쿠폰 적용 후 가격에 판매수수료 부과 </span>
	            <span>* 상품 구매 시 쿠폰 + 회원혜택 동시 중복적용 가능</span> 
	            <span>* 2만원 이상 사용 시 쿠폰 사용 가능</span>
            </div>      
        </div>
    </div>

<script src="${pageContext.request.contextPath}/admin/js/common/checkbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/member/grade.js"></script>