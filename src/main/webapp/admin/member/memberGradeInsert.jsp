<%@page import="dto.couponDTO.couponDTO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="dto.memberDTO.membergradeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	membergradeDTO grade = (membergradeDTO)request.getAttribute("grade");
	String mode = (String)request.getAttribute("mode");
	ArrayList<couponDTO> couponlist = (ArrayList)request.getAttribute("couponList");
	System.out.println("coupoinlist == "+couponlist);
%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/member/total_membergrade02.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
<div id="modal">
	<div id="couponContent" class="container"></div>
</div>
  <div class="cont_wrap">
        <div class="top_tit">
            <h1>회원등급 등록/수정</h1>
        </div>
        <form action="./gradeAction" method="post" name="form" enctype="multipart/form-data" id="formData">
        <input type="hidden" name="mode" value=<%=mode%>>
        <div class="search_cont">
            <div class="sch_tit">
              <h2>기본 설정</h2> 
              <button type="reset">초기화</button>
              <button type="button" onclick="save()">저장</button> 
            </div>
            <table class="ta01">
                <tr>
                    <th>등급번호</th>
                    <td>
                    	<input type="hidden" name="sno" value=<%=(grade != null && grade.getSno() > 0)?grade.getSno():"" %>>
                    	<%=(grade != null && grade.getSno() > 0)?grade.getSno():"" %>
                    </td>
                </tr>
                <tr>
                    <th>등급이름</th>
                    <td>
                    	<div class="flex" id="gradeNameContent"> 
                    		<input type="hidden" id="gradeNameOverlap" value="0">
	                    	<input id="gradeNm" type="text" onkeyup="gradeVal()" name="gradeNm" value="<%=(grade != null && grade.getGradeNm() != null)?grade.getGradeNm():"" %>">&nbsp;
	                    	<p><span id="gradeNmlimit">0</span>/25</p>&nbsp;
	                    	<button type="button" id="overlapBtn" onclick="overlap()" disabled="disabled">등급이름 중복확인</button>&nbsp;
                    	</div>
                    </td>
                </tr>
                <tr>
                    <th>등급이미지</th>
                    <td> 
                        <label><input name="file" type="file" value=<%=(grade != null && grade.getGradeImage() != null)? grade.getGradeImage() : "" %>></label> 
                        <p class="pr">* jpg, jpeg, png, gif만 등록 가능하며, 기본 등급표시 아이콘은 16x16 pixel 입니다.</p>
                    </td>
                </tr>
                <tr>
                    <th>등급평가기준치</th>
                    <td> 
                    	<div class="gradeStandard">
                    		주문금액 : <input type="text" id="orderPriceMore" name="orderPriceMore" value=<%=(grade != null && grade.getOrderPriceMore() >= 0)?grade.getOrderPriceMore():""%>> 원 이상~
                    		  &nbsp;<input type="text" id="orderPriceBelow" name="orderPriceBelow" value=<%=(grade != null && grade.getOrderPriceBelow() > 0)?grade.getOrderPriceBelow():""%>> 원 미만
                    	</div>
                    	
                    	<div class="gradeStandard">상품주문건수 : <input type="text" id="orderCnt" name="orderCnt" value=<%=(grade != null && grade.getOrderCnt() >= 0)? grade.getOrderCnt():"" %>> 건 이상</div>
                    </td>
                </tr>
            </table>   
        </div>
        <div class="search_cont">
            <div class="sch_tit">
              <h2>혜택설정</h2> 
            </div>
            <table class="ta02">
<!--                 <tr> -->
<!--                     <th>정률(%)할인/적립 시 <br> 구매금액 기준</th> -->
<!--                     <td> 판매가 + -->
<!--                         <label for="ra09">(<input type="radio" name="ra04" id="ra09"> 옵션가 </label>  -->
<!--                         <label for="ra10"><input type="radio" name="ra04" id="ra10">추가상품가 </label> -->
<!--                         <label for="ra11"><input type="radio" name="ra04" id="ra11"> 텍스트옵션가 )</label> -->

<!--                     </td> -->
<!--                 </tr> -->
<!--                 <tr> -->
<!--                     <th>할인시 절사기준</th> -->
<!--                     <td>  0.1원 단위로 버림 -->
<!--                     </td> -->
<!--                 </tr> -->
<!--                 <tr> -->
<!--                     <th>할인/적립 시 적용 금액 기준</th> -->
<!--                     <td>  -->
<!--                         <label for="ra12"><input type="radio" name="ra05" id="ra12">판매금액 </label> -->
<!--                         <label for="ra13"><input type="radio" name="ra05" id="ra13"> 결제금액 </label> -->
<!--                         <p>*무통장 사용시에만 혜택을 제공하는 것은 여신전문금융법에 저촉될 수 있습니다.</p> -->
<!--                     </td> -->
<!--                 </tr> -->
<!--                 <tr> -->
<!--                     <th>추가 할인 </th> -->
<!--                     <td>  -->
<!--                         <select name="" id=""> -->
<!--                             <option value="">옵션별</option> -->
<!--                         </select> -->
<!--                         구매 금액이 <input type="text" name="" id="">원 이상인 경우 해당상품 <input type="text"> % 추가 할인 -->
<!--                     </td> -->
<!--                 </tr> -->
<!--                 <tr> -->
<!--                     <th>추가 할인 적용 제외</th> -->
<!--                     <td> -->
<!--                         <label for="ra14"><input type="radio" name="ra06" id="ra14">특정 공급사 </label> -->
<!--                         <label for="ra15"><input type="radio" name="ra06" id="ra15"> 특정 카테고리 </label> -->
<!--                         <label for="ra16"><input type="radio" name="ra06" id="ra16"> 특정 브랜드 </label> -->
<!--                         <label for="ra17"><input type="radio" name="ra06" id="ra17"> 특정 상품 </label> -->
<!--                     </td> -->
<!--                 </tr> -->
<!--                 <tr> -->
<!--                     <th>중복 할인 </th> -->
<!--                     <td>  -->
<!--                         <select name="" id=""> -->
<!--                             <option value="">옵션별</option> -->
<!--                         </select> -->
<!--                         구매 금액이 <input type="text" name="" id="">원 이상인 경우 해당상품 <input type="text"> % 중복 할인 -->
<!--                     </td> -->
<!--                 </tr> -->
<!--                 <tr> -->
<!--                     <th>중복 할인 적용</th> -->
<!--                     <td> -->
<!--                         <label><input type="radio" name="ra06">특정 공급사 </label> -->
<!--                         <label><input type="radio" name="ra06"> 특정 카테고리 </label> -->
<!--                         <label><input type="radio" name="ra06"> 특정 브랜드 </label> -->
<!--                         <label><input type="radio" name="ra06"> 특정 상품 </label> -->
<!--                     </td> -->
<!--                 </tr> -->
                <tr>
                    <th>추가 마일리지 적립 </th>
                    <td> 
                        구매 금액이 <input type="text" id="priceLine" name="priceLine" value=<%=(grade != null && grade.getPriceLine() > 0)?grade.getPriceLine() : 0%>> 원 이상인 경우 해당상품 <input type="text" id="mileagePercent" name="mileagePercent" value=<%=(grade != null && grade.getMileagePercent() != new BigDecimal(0))?grade.getMileagePercent():0 %>> % 추가 적립
                    </td>
                </tr>
                <tr>
                    <th>추가 적립시 절사기준</th>
                    <td>0.1포인트 단위로 버림</td>
                </tr>
<!--                 <tr> -->
<!--                     <th>배송비 혜택</th> -->
<!--                     <td> -->
<!--                         <label for="ck01"><input type="checkbox" name="ck01" id="ck01">배송비 무료</label> -->
<!--                     </td> -->
<!--                 </tr> -->
                <tr>
                    <th>쿠폰 혜택</th>
                    <td>
                        <button type="button" onclick="coupon(<%=(couponlist != null)?couponlist.size():0%>)">쿠폰선택</button>
                        <button type="button" onclick="couponAdd()">신규쿠폰 등록</button>
                        <div id="selectedCouponList">
                        	<div class="couponFlexBox">
                        		<p>선택된 쿠폰 : </p>
		                       	<div id="couponList">
                        		<%if(couponlist != null && !couponlist.isEmpty()){ %>
		                       		<%for(int j=0; j < couponlist.size(); j++){%>
		                       			<div class="coupon" id="coupon<%=j%>">
			                       			<span><%=(couponlist.get(j).getCouponNm() != null)?couponlist.get(j).getCouponNm():""%></span>
				                        	<input type="hidden" name="couponCd" value=<%=(couponlist.get(j).getCouponNo() > 0)?couponlist.get(j).getCouponNo():""%>>
				                        	<button onclick="couponDel(<%=j%>)">x</button>
			                        	</div>
		                       		<%} %>
                        		<%} %>
		                       	</div>
                        	</div>
                        </div>
                        <p class="pr">*설정쿠폰이 발급된 회원이 있을 경우 쿠폰혜택은 수정하실 수 없습니다.</p>
                    </td>
                </tr>
            </table>   
        </div>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/admin/js/member/gradeMode.js"></script>