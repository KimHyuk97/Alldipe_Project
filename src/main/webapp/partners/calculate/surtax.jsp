<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/surtax.css">
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
	<div class="cont_wrap">
		<div class="title">
            부가세 신고 내역
            <div class="top_txt_box">
                <p class="p_title">부가세 신고내역 안내</p>
                <p class="p_txt">
                    * 현금영수증은 배송완료일 기준으로 발행되며 2018년 10월부터 조회 가능합니다. <br>
                    * 부가세 신고 내역은 파트너님의 편의를 위해 제공되는 자료로, 작성하는 자료와 차이가 있을 수 있으니 참고자료로 활용 부탁드립니다. <br>
                    * 부가세 신고 내역은 익월 4일에 확인 가능하며, 분기 익월 ( 1월, 4월 7월 10월 ) 4일에 분기별 조회를 확인할 수 있습니다. <br>
                    * 현금영수증 메뉴는 배송완료일 익일 후 금액이 반영되지만 부가세신고내역의 경우, 익월 4일 후 반영됩니다. <br>
                    * 총매출액 = 배송완료 금액(환불반영) - 메출에누리 - 파트너사부담쿠폰 + 계산서 대상 임의조정 금액 + 상품 공제금액 + 정산 대상 배송비 = 현금영수증합계 + 기타 <br>
                    * 상세내역은 파트너2.0 > 정산관리 > 매출현황 및 지급내역 메뉴를 참고하시기 바랍니다. <br>
                    * 매출에누리는 파트너2.0 > 정산관리 > 세금계산서 메뉴의 세금계산서 상세 내역에서 확인 가능합니다. <br>
                    * 계산서 대상 임의조정 금액은 등록일 기준으로 합산되므로 때문에 정산내역서의 '판매자매출'과 금액이 다를 수 있습니다.
                </p>
            </div>
            <div class="top_btn">
                <button type="submit">검색</button>
                <button type="submit">초기화</button>
            </div>
        </div>
        <div class="top_wrap">
            <div class='top_cont srch_area'>
                <div class="tit">배송완료일</div>
                <img src="" alt="">
                <p> 월별</p>
                <input type="date" title="달력" id="search_start_date" > ~
                <input type="date" title="달력" id="search_end_date" >
                <ul>
                    <li><button>어제</button> </li>
                    <li><button>일주일</button> </li>
                    <li><button>1개월</button> </li>
                </ul>
                <br>
                
                <p class="p_block">
                    <img src="" alt="">
                    분기별
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
          
        </div>
        <div class="bottom_wrap">
            <div class="btm_tit">
                검색결과 <p>0건</p>
                <div class="btm_tit_btn btm_tit_btn02">
                    <button type="submit" class="exel01" id="download_btn01"><i class="far fa-file-excel"></i> 엑셀 다운로드</button>
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
                    <td>월</td>
                    <td>총 매출액</td>
                    <td>현금영수증 (소득공제)</td>
                    <td>현금영수증 (지출증빙)</td>
                    <td>기타</td>
                    <td>&#32;</td>
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
        <div class="bottom_wrap">
            <div class="btm_tit">
                현금영수증 내역
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
            <table class="main main02">
                <tr>
                    <td>구매번호</td>
                    <td>배송번호</td>
                    <td>배송완료일</td>
                    <td>결제일</td>
                    <td>거래구분</td>
                    <td>금액</td>
                    <td>신고일</td>
                    <td>거래번호</td>
                    <td>승인번호</td>
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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script>
        $("#download_btn01").click(function() {
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