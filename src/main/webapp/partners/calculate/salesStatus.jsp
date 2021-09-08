<%@page import="dto.scmDTO.scmDTO"%>
<%@page import="dto.orderDTO.orderGoods"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	scmDTO scm = (scmDTO)session.getAttribute("scm");
%>
<jsp:include page="../header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/sales_status.css">
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
	<div class="salesState_info_background">
		<div class="salesStatus_info">
			<p class="p_title">매출현황 안내</p>
			<p class="p_txt">
                * 상품 및 배송비 매출은 전일자까지 배송완료된 건 기준으로 집계됩니다. <br>
                * 배송비 매출은 상품번호/상품명으로 검색이 불가합니다.   
            </p>
		</div>	
	</div>
	<div class="cont_wrap">
	<div class="wrap">
		<div class="title">
            매출현황
            <div class="top_txt_box">
            	<input type="hidden" id="scmNo" value="<%=(scm!=null)?scm.getScmNo():"" %>">  
            </div>
            <div class="top_btn">
                <button type="submit" onclick="search()">검색</button>
                <button type="submit">초기화</button>
            </div>
        </div>
        <div class="top_wrap">
            <div class='top_cont srch_area'>
                <div class="tit">검색기간</div>
                <img src="" alt="">
                <p> 일별</p>
                <input type="date" title="달력" id="startDt" > ~
                <input type="date" title="달력" id="endDt" >
                <ul>
                    <li><button>어제</button> </li>
                    <li><button>일주일</button> </li>
                    <li><button>1개월</button> </li>
                </ul>
                <br>
                
                <p class="p_block">
                    <img src="" alt="">
                    월별
                </p>
                <select name="년도" class="year">
                    <option value="2021년">2021년</option>
                    <option value="2021년">2017년</option>
                    <option value="2021년">2018년</option>
                    <option value="2021년">2019년</option>
                    <option value="2021년">2020년</option>
                </select>
                <select name="월" >
                    <option value="01월">01월</option>
                    <option value="02월">02월</option>
                    <option value="03월">03월</option>
                    <option value="04월">04월</option>
                    <option value="05월">05월</option>
                    <option value="06월">06월</option>
                    <option value="07월">07월</option>
                    <option value="08월">08월</option>
                    <option value="09월">09월</option>
                    <option value="10월">10월</option>
                    <option value="11월">11월</option>
                    <option value="12월">12월</option>
                </select>
            </div>
<!--             <div class="top_cont"> -->
<!--                 <div class="tit">정산주기</div> -->
<!--                 <select name="정산주기" > -->
<!--                     <option >전체</option> -->
<!--                     <option >월정산</option> -->
<!--                     <option >주정산</option> -->
<!--                 </select> -->
<!--             </div> -->
<!--             <div class="top_cont"> -->
<!--                 <div class="tit">검색어</div> -->
<!--                 <select name="keywordType" id="keywordType" > -->
<!--                     <option value="all" >전체</option> -->
<!--                     <option value="goodsNo" >상품번호</option> -->
<!--                     <option value="goodsNm" >상품명</option> -->
<!--                     <option value="invoiceNo">배송번호</option> -->
<!--                 </select> -->
<!--                 <input id="keyword" type="text" placeholder="검색어를 입력하세요"> -->
<!--             </div> -->
        </div>
        <div class="bottom_wrap">
            <div class="btm_tit">
                상품 매출 검색결과 <span class="color_span">0건</span>
<!--                 <div class="btm_tit_btn btm_tit_btn02"> -->
<!--                     <button type="submit" class="exel01" id="download_btn01"><i class="far fa-file-excel"></i> 엑셀 다운로드</button> -->
<!--                     <button type="submit" class="download" id="download_btn02">총 주문내역 다운</button> -->
<!--                     <select name="개수" > -->
<!--                         <option>20개</option> -->
<!--                         <option>50개</option> -->
<!--                         <option>100개</option> -->
<!--                         <option>200개</option> -->
<!--                         <option>300개</option> -->
<!--                     </select> -->
<!--                 </div>  -->
            </div>
            <div class="main">
                <div>
                    <div>NO</div>
                    <div>일자</div>
                    <div>판매업자ID</div>
                    <div>상품명</div>
                    <div>상품번호</div>
                    <div>정산주기</div>
                    <div>정산예정일</div>
                    <div>판매기간</div>
                </div>   
            </div>
            <div class="cont">
                <div>
                    <div>
                    </div>
                </div>
                <div>
                    <div>
                    </div>
                </div>
            </div>
        </div>
        <div class="bottom_wrap">
            <div class="btm_tit">
                배송비 매출 검색결과 <p>0건</p>
                <div class="btm_tit_btn btm_tit_btn02 ">
                    <button type="submit" class="exel02" id="download_btn03"><i class="far fa-file-excel"></i> 엑셀 다운로드</button>
                    <select name="개수" >
                        <option>20개</option>
                        <option>50개</option>
                        <option>100개</option>
                        <option>200개</option>
                        <option>300개</option>
                    </select>
                </div>
            </div>
            <table class="main">
                <tr>
                    <td>NO</td>
                    <td>배송번호</td>
                    <td>클레임배송번호</td>
                    <td>판매업체</td>
                    <td>상품명</td>
                    <td>구분:배송/환불</td>
                    <td>고객결제배송비</td>
                    <td>배송비할인 올디프 부담쿠폰</td>
                </tr>   
            </table>
            <table class="cont">
                <tr>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td>
                    </td>
                </tr>
            </table>
        </div>
        </div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script>
    	window.onload=function(){
    		endLoading();
    	}
        $("#download_btn01").click(function() {
            // // hope the server sets Content-Disposition: attachment!
            window.location = '';
        });
        $("#download_btn02").click(function() {
            // // hope the server sets Content-Disposition: attachment!
            window.location = '';
        });
        $("#download_btn03").click(function() {
            // // hope the server sets Content-Disposition: attachment!
            window.location = '';
        });
    </script>
</body>
</html>