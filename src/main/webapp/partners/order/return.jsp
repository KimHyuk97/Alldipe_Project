<%@page import="dto.orderDTO.orderGoods"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.scmDTO.scmDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	scmDTO scm = (scmDTO)session.getAttribute("scm");
	ArrayList<orderGoods> list = (ArrayList)session.getAttribute("orderList");
	int a=0,b=0,c=0,d=0,e=0;
	if(list != null && list.size()>0){
		
		for(orderGoods dto : list){
			if(dto.getOrderStatus().equals("취소신청")){
				a++;
			}else if(dto.getOrderStatus().equals("환불신청")){
				b++;
			}else if(dto.getOrderStatus().equals("수거요청중")){
				c++;
			}else if(dto.getOrderStatus().equals("환불보류")){
				d++;
			}else if(dto.getOrderStatus().equals("환불완료")){
				e++;
			}
		}
	}
%>
<jsp:include page="../header.jsp"/>
<script src="${pageContext.request.contextPath }/partners/js/order/return.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/order_return.css">
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
	<div class="cont_wrap">
	<div class="wrap">
        <div class="title">
            <span class="title_span">환불관리</span>
            <input type="hidden" id="scmNo" value=<%=(scm!=null)?scm.getScmNo():"" %>>
            <div class="top_state_div">
            	<div class="top_state_inner">
                    취소 신청
                    <p>0</p>
                </div>
                <div class="top_state_inner">
                    환불 신청
                    <p>0</p>
                </div>
                <div class="top_state_inner">
                    수거 요청중
                    <p>0</p>
                </div>
                <div class="top_state_inner">
                    환불 보류
                    <p>0</p>
                </div>
                <div class="top_state_inner">
                    환불 완료
                    <p>0</p>
                </div>
            </div>
        </div>
        <div class="top_btn">
			<button type="button" onclick="search()">검색</button>
			<button type="button">초기화</button>
        </div>
        <div class="top_wrap">
            <div class='top_cont srch_area'>
                <div class="tit">검색기간</div>
                <select class="srch_select" id="dateType">
                    <option value="">환불신청일</option>
                    <option value="">환불완료일</option>
                    <option value="paymentDt">주문일</option>
                </select>
                <input class="srch_input" type="date" title="달력" id="startDt" > ~
                <input class="srch_input" type="date" title="달력" id="endDt" >
                <button>어제</button>
				<button>일주일</button>
				<button>1개월</button>
            </div>
            <div class='top_cont '>
                <div class="tit">처리상태</div>
                <select class="srch_select" id="orderState">
                    <option value="all">전체</option>
                    <option value="환불신청">환불신청</option>
                    <option value="수거진행중">수거진행중</option>
                    <option value="환불보류">환불보류</option>
                    <option value="환불불가">환불불가</option>
                    <option value="환불완료">환불완료</option>
                </select>
<!--                 <select > -->
<!--                     <option value="전체">수거배송전체</option> -->
<!--                     <option value="요청대기">요청대기</option> -->
<!--                     <option value="수거예약">수거예약</option> -->
<!--                     <option value="자동접수요청대기">자동접수요청대기</option> -->
<!--                     <option value="요청완료">요청완료</option> -->
<!--                     <option value="수거중">수거중</option> -->
<!--                     <option value="수거완료">수거완료</option> -->
<!--                     <option value="수거실패">수거실패</option> -->
<!--                 </select> -->
                
            </div>
<!--             <div class='top_cont '> -->
<!--                 <div class="tit">접수채널</div> -->
<!--                 <select> -->
<!--                     <option value="전체">전체</option> -->
<!--                     <option value="판매자">판매자</option> -->
<!--                     <option value="구매자">구매자</option> -->
<!--                     <option value="고객센터">고객센터</option> -->
<!--                     <option value="시스템">시스템</option> -->
<!--                 </select> -->
<!--                 <div class="tit tit_agree">승인처리자</div> -->
<!--                 <select> -->
<!--                     <option value="전체">전체</option> -->
<!--                     <option value="판매자">판매자</option> -->
<!--                     <option value="구매자">구매자</option> -->
<!--                     <option value="고객센터">고객센터</option> -->
<!--                     <option value="시스템">시스템</option> -->
<!--                 </select> -->
                
<!--             </div> -->
            <div class='top_cont '>
                <div class="tit">검색조건</div>
                <select class="srch_select" id="keywordType">
                    <option value="all">전체</option>
                    <option value="구매번호">구매번호</option>
                    <option value="주문번호">주문번호</option>
                    <option value="구매자">구매자</option>
                    <option value="상품번호">상품번호</option>
                    <option value="배송번호">배송번호</option>
                    <option value="클레임번호">클레임번호</option>
                    <option value="받는사람">받는사람</option>
                </select>
               <input class="srch_input" id="keyword" type="text" placeholder="검색어를 입력하세요.">
                <br>
            </div>
        </div>
        <div class="bottom_wrap">
            <div class="btm_tit">
               상세 검색 결과 <span class="color_span">0</span>건
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
            	<input id="page" type="hidden" name="page" value="return">
	            <div class="btn_wrap">
	                <button type="button" onclick="callbackApply()">수거요청</button>
	                <button type="button" onclick="refundApprove()">환불승인</button>
	                <button type="button" onclick="refundWait()">환불보류</button>
	                <button type="button" onclick="refundReject()">환불거절</button>
	                <button type="button" onclick="callbackChange()">수거방법변경</button>
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
	<script src="${pageContext.request.contextPath }/partners/js/order/return.js"></script>
	<script>
	
		document.getElementById("endDt").value = new Date().toISOString().substring(0,10);
	
		window.onload = function(){
			endLoading();
		}
	</script>
</body>
</html>