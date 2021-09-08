<%@page import="dto.scmDTO.scmDTO"%>
<%@page import="dto.memberDTO.memberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	memberDTO mem = (memberDTO)session.getAttribute("mem");
	scmDTO scm = (scmDTO)session.getAttribute("scm");
%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/partners_bizmoney.css">
<jsp:include page="../header.jsp"/>
	<div class="cont_wrap">
		<div class="top">
            <div class="top_tit">
                비즈머니 관리
            </div>
            <div class="money_use">
                비즈머니 총 <span class="total_bizmoney">0</span> 원 이번주 사용액 <p><span class="week_bizmoney">0</span> 원</p>
                <a href="" class="refund">환불하기</a>
                <a class="charge" onclick="window.open('./bizmoneyCharge.jsp','_blank', 'width=505, height=565, top=200, left=700')">비즈머니 충전</a>
            </div>
            <div class="card_wrap">
               <div class="money_card">
                    <p class="spare">총 비즈머니 잔액</p> <br>
                    <p class="card_blue"><%=mem.getDeposit() + mem.getFreedeposit() %></p><span>원</span>
                </div>
                <div class="money_card">
                    <p class="spare">유상(현금/카드)잔액</p> <br>
                    <p class="card_blue"><%=mem.getDeposit() %></p><span>원</span>
                </div>
                <div class="money_card">
                    <p class="spare">무상 잔액</p> <br>
                    <p class="card_blue"><%=mem.getFreedeposit() %></p><span>원</span>
                </div>
                <div class="money_card">
                    <p class="spare">사용 가능한 쿠폰</p> <br>
                    <p class="card_blue"><span class="coupon_num">0</span></p><span>장</span>
                </div>  
            </div>
        </div>
        <div class="bottom">
            <div class="bottom_tit">
                내역 확인
            </div>
            <div class="list_box">
                <ul class="list01">
                <li>
                    <a href="">
                     기간별 내역   
                    </a>
                </li>
                <li>
                    <a href="">
                     소진 내역   
                    </a>
                </li>
                <li>
                    <a href="">
                     충전 내역   
                    </a>
                </li>
                <li>
                    <a href="">
                     환불 내역   
                    </a>
                </li>
                <li>
                    <a href="">
                     환급 내역   
                    </a>
                </li>
                </ul>
                <ul class="list02">
                    <li>
                        <a href="">
                            기간
                        </a>
                    </li>
                    <li>
                        <a href="">
                            충전액
                        </a>
                    </li>
                    <li>
                        <a href="">
                            소진액
                        </a>
                    </li>
                    <li>
                        <a href="">
                            환불
                        </a>
                    </li>
                    <li>
                        <a href="">
                            잔액
                        </a>
                    </li>
                </ul> 
                <div class="list_txt_box">
                </div>
            </div>
            <div class="bottom_btn">
                <a href="">초기화</a>
                <a href="">등록/수정</a> 
            </div>
            
        </div>
	</div>
	<script>
		window.onload = function(){
			endLoading();
		}
	</script>
</body>
</html>