<%@page import="dto.orderDTO.orderGoods"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.scmDTO.scmDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"/>
<script src="${pageContext.request.contextPath }/partners/js/order/delivery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/order_deliver.css">
<%
	scmDTO scm = (scmDTO)session.getAttribute("scm");
	ArrayList<orderGoods> list = (ArrayList)session.getAttribute("orderList");
	int a =0, b = 0, c = 0, d = 0;
	if(list != null && list.size()>0){
		
		for(orderGoods dto : list){
			if(dto.getOrderStatus().equals("배송중")){
				a++;
			}else if(dto.getOrderStatus().equals("배송완료")){
				b++;
			}else if(dto.getOrderStatus().equals("미수취")){
				c++;
			}else if(dto.getOrderStatus().equals("미수취 철회")){
				d++;
			}
		}
	}
%>
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
	<div class="cont_wrap">
	<div class="wrap">
        <div class="title">
            <span class="title_span">배송현황</span>
            <input type="hidden" id="scmNo" value=<%=(scm!=null)?scm.getScmNo():"" %>>
            <div class="top_state_div">
                <div class="top_state_inner">
                	배송중
                    <p><%=a %></p>
                </div>
                <div class="top_state_inner">
                	배송완료
                    <p><%=b %></p>
                </div>
                <div class="top_state_inner">
                	미수취 신고
                    <p><%=c %></p>
                </div>
                <div class="top_state_inner">
                	미수취 철회
                    <p><%=d %></p>
                </div>
            </div>
        </div>
        <div class="top_btn">
			<button type="button" onclick="search()">검색</button>
			<button type="submit">초기화</button>
        </div>
        <div class="top_wrap">
            <div class='top_cont'>
                <div class="tit">검색기간</div>
                <select class="srch_select" id="dateType">
                    <option value="regDt">주문일</option>
<!--                     <option value="주문확인일">주문확인일</option> -->
                    <option value="paymentDt">결제일</option>
                    <option value="deliveryCompleteDt">배송완료일</option>
                </select>
                <input class="srch_input" type="date" title="달력" id="startDt" > ~
                <input class="srch_input" type="date" title="달력" id="endDt" >
				<button>어제</button>
				<button>일주일</button>
				<button>1개월</button>
            </div>
            <div class='top_cont '>
                <div class="tit">주문상태</div>
                <select class="srch_select" id="orderState">
                    <option value="all">전체</option>
                    <option value="배송중">배송중</option>
                    <option value="배송완료">배송완료</option>
                    <option value="미수취">미수취</option>
                </select>
                <br>
            </div>
            <div class='top_cont '>
                <div class="tit">검색조건</div>
                <select class="srch_select" id="keywordType">
                    <option value="all">전체</option>
                    <option value="구매번호">구매번호</option>
                    <option value="주문번호">주문번호</option>
                    <option value="상품명">상품명</option>
                    <option value="상품번호">상품번호</option>
                    <option value="배송번호">배송번호</option>
                    <option value="송장번호">송장번호</option>
                    <option value="받는사람">받는사람</option>
                </select>
               <input class="srch_input" type="text" id="keyword" placeholder="검색어를 입력하세요.">
                <br>
            </div>
        </div>
        <div class="bottom_wrap">
            <div class="btm_tit">
               상세 검색 결과 <span class="color_span">0</span> 건
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
            	<input id="page" type="hidden" name="page" value="delivery">
	            <div class="btn_wrap">
<!-- 	                <button onclick="">송장수정</button> -->
	                <button type="button" onclick="deliveryComplete()">배송완료</button>
	                <button type="button" onclick="notReceived()">미수취 신고 철회요청</button>
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
    </div>
    <script>
    
    	document.getElementById("endDt").value = new Date().toISOString().substring(0,10);
    	
    	window.onload = function(){
    		endLoading();
    	}
    	
    </script>
</body>
</html>