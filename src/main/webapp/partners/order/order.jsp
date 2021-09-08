<%@page import="dto.scmDTO.scmDTO"%>
<%@page import="dto.orderDTO.orderGoods"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"/>
<script src="${pageContext.request.contextPath }/partners/js/order/order.js"></script>
<%
	scmDTO scm = (scmDTO)session.getAttribute("scm");
	ArrayList<orderGoods> list = (ArrayList)session.getAttribute("ogList");
	
	int a =0, b=0, c=0, d = 0;
	
	session.removeAttribute("ogList");
	if(list!=null && list.size()>0){
		for(orderGoods dto : list){
			if(dto.getOrderStatus().equals("주문완료")){
				a++;
			}else if(dto.getOrderStatus().equals("상품준비중")){
				b++;
			}else if(dto.getOrderStatus().equals("환불신청")){
				c++;
			}else if(dto.getOrderStatus().equals("구매확정")){
				d++;
			}
		}
	}
%>

<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/order_list.css">
	<div class="cont_wrap">
		<div class="wrap">
        <div class="title">
            주문관리
            <input type="hidden" value=<%=(scm!=null)?scm.getScmNo():"" %> id="scmNo">
            <br>
            <div class="top_txt_box">
                <li class="top_txt_in top_txt_in01 ">
                    <img src="" alt="">
                    <h3>결제완료 <p><%=a %>건</p></h3> 
                    <div class="top_txt01">
                        현재 정상적으로 결제가 완료된 주문 건 입니다. <br>
                        주문확인과 발송처리가 필요한 상태입니다.
                    </div>
                </li>
                <li class="top_txt_in top_txt_in02 ">
                    <img src="" alt="">
                    <h3>주문확인 <p><%=b %>건</p></h3>
                    <div class="top_txt01">
                        주문확인 처리가 되어 '상품준비중'인 주문건입니다. <br>
                        이후 발송처리되면 취소신청이 불가합니다.
                    </div>
                </li>
            </div>
            <div class="top_txt_box">
                <li class="top_txt_in top_txt_in01 ">
                    <img src="" alt="">
                    <h3>환불신청 <p><%=c %>건</p></h3>
                    <div class="top_txt02">
                        환불 신청 주문건입니다.<br>
                        발송 처리 이후에는 수거 후 환불 가능합니다.
                    </div>
                </li>
                <li class="top_txt_in top_txt_in02 ">
                    <img src="" alt="">
                    <h3>구매확정 <p><%=d %>건</p></h3>
                    <div class="top_txt02">
                        구매확정 처리된 주문건입니다<br>
                        정산 처리 후 목록에서 제외됩니다.
                    </div>
                </li>
            </div>
        </div>
        <div class="top_btn">
			<button type="button" onclick="search()">검색</button>
			<button type="button">초기화</button>
		</div>
        <div class="top_wrap">
            <div class='top_cont'>
				<div class="tit">검색기간</div>
				<select class="srch_select" id="dateType">
					<option value="" selected>선택안함</option>
					<option value="deliveryCompleteDt">배송완료일</option>
					<option value="paymentDt">결제완료일</option>
				</select>
				<input class="srch_input" type="date" title="달력" id="startDt" > ~
				<input class="srch_input" type="date" title="달력" id="endDt">
				<button>오늘</button>
				<button>일주일</button> 
				<button>1개월</button>
            </div>
            <div class='top_cont'>
                <div class="tit">주문상태</div>
                <select class="srch_select" id="orderState">
                    <option value="all" selected>전체</option>
                    <option value="주문완료">주문완료</option>
                    <option value="상품준비중">상품준비중</option>
                    <option value="환불신청">환불신청</option>
                    <option value="구매확정">구매확정</option>
                </select>
<!--                 <div class="tit">배송방법</div> -->
<!--                 <select > -->
<!--                     <option value="전체">전체</option> -->
<!--                     <option value="택배배송">택배배송</option> -->
<!--                     <option value="직접배송">직접배송</option> -->
<!--                     <option value="우편배송">우편배송</option> -->
<!--                 </select> -->
<!--                 <div class="tit">발송지연안내</div> -->
<!--                 <select > -->
<!--                     <option value="전체">전체</option> -->
<!--                     <option value="처리">처리</option> -->
<!--                     <option value="미처리">미처리</option> -->
<!--                 </select> -->
            </div>
            <div class='top_cont'>
                <div class="tit">검색어</div>
                <select class="srch_select" id="keywordType">
                    <option value="전체" selected>전체</option>
                    <option value="orderNo">구매번호</option>
                    <option value="배송번호">배송번호</option>
                    <option value="구매자명">구매자명</option>
                    <option value="구매자ID">구매자ID</option>
                    <option value="구매자휴대폰">구매자휴대폰</option>
                    <option value="받는사람">받는사람</option>
                    <option value="상품명">상품명</option>
                    <option value="상품번호">상품번호</option>
                </select>
               <input class="srch_input" id="keyword" type="text" placeholder="검색어를 입력하세요.">
                <br>
            </div>
        </div>
        <div class="bottom_wrap">
            <div class="btm_tit">
               검색결과 <span class="color_span">0</span>건
<!--                 <div class="btm_tit_btn btm_tit_btn02"> -->
<!--                     <button type="submit" class="exel01" id="download_btn01"><i class="far fa-file-excel"></i> 엑셀 다운로드</button> -->
<!--                     <select> -->
<!--                         <option value="20개">20개</option> -->
<!--                         <option value="50개">50개</option> -->
<!--                         <option value="100개">100개</option> -->
<!--                         <option value="200개">200개</option> -->
<!--                         <option value="300개">300개</option> -->
<!--                     </select> -->
<!--                 </div>  -->
            </div>
            <form action="./orderChange" method="post">
            	<input id="changeStatus" type="hidden" name="changeStatus" value="">
            	<input id="page" type="hidden" name="page" value="order">
	            <div class="btn_wrap">
	                <button type="button" onclick="orderCheck()">주문확인</button>
<!-- 	                <button type="button">발송지연안내</button> -->
	                <button type="button" onclick="deliveryStart()">발송처리</button>
<!-- 	                <button class="btn_last">수수료안내</button> -->
	            </div>
	            <div id="main">
	            	<div id="main_table">
	                    <div class="list_col1"><input type="checkbox" id="selectAll" onclick="selectItem()"></div>
	                    <div class="list_col2">No.</div>
	                    <div class="list_col3">주문일시</div>
	                    <div class="list_col4">경과일자</div>
	                    <div class="list_col5">주문번호</div>
	                    <div class="list_col6">주문상태</div>
	                    <div class="list_col7">주문자</div>
	                    <div class="list_col8">주문상품</div>
	                   	<div class="list_col9">결제금액</div>
	                   	<div class="list_col10">배송비</div>
	                   	<div class="list_col11">관리자메모</div>
	                </div>   
	            </div>
	            <div class="orderList">
	            	<div id="list_table">
		            	<div class="orderList_tr">
		            		<span>주문 내역이 없습니다.</span>
			            </div>
	            	</div>
	            </div>
            </form>
        </div>
    </div>
    <script>
    
    	document.getElementById("endDt").value = new Date().toISOString().substring(0,10);
    	
    	
    	window.onload = function(){
    		endLoading();
    	}
    	
    </script>
<jsp:include page="../footer.jsp"/>