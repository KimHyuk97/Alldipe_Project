<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/tax.css">
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
	<div class="cont_wrap">
		<div class="wrap">
		<div class="title">
            세금계산서
            <div class="top_btn">
                <button type="submit">검색</button>
                <button type="submit">초기화</button>
            </div>
        </div>
        <div class="top_wrap">
            <div class='top_cont srch_area'>
                <div class="tit">발행일</div>
                <img src="" alt="">
                <p> 일별</p>
                <input type="date" title="달력" id="search_start_date" > ~
                <input type="date" title="달력" id="search_end_date" >
                <ul>
                    <li><button>어제</button> </li>
                    <li><button>일주일</button> </li>
                    <li><button>1개월</button> </li>
                </ul>     
            </div>
          
        </div>
        <div class="bottom_wrap">
            <div class="btm_tit">
                상세 검색결과<p>0건</p>
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
            <table class="main main04" id="main04">
                <tr>
                    <td>발행일</td>
                    <td>공급가액</td>
                    <td>부가세</td>
                    <td>합계액</td>
                    <td>사업자번호</td>
                    <td>세금계산서 담당자</td>
                    <td>전화번호</td>
                    <td>휴대폰번호</td>
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
                세금계산서 상세내역
                <div class="btm_tit_btn btm_tit_btn02">
                    <button type="submit" class="exel01" id="download_btn01"><i class="far fa-file-excel"></i> 엑셀 다운로드</button>
                    
                </div> 
            </div>
            <table class="main main03">
                <tr>
                   <td colspan ='6' class="cal01">대상금액(A)</td>
                   <td colspan="3" class="cal_none"></td>
                </tr> 
                <tr class="tr02">
                    <td class="td02">판매수수료</td>
                    <td class="td02">제휴채널 <br>노출수수료</td>
                    <td class="td02">지원할인 <br> 수수료</td>
                    <td class="td02">영세율할인<br> 수수료</td>
                    <td>
                        <table class="small">
                            <tr>
                                <td colspan ='3' class= 'fee_box fee_box01' id="fee_box">고정수수료</td>
                            </tr>
                            <tr class="fee_box" id="fee_box">
                                <td class="fee_box" id="fee_box fee_box02">서버이용료</td>
                                <td class="fee_box" id="fee_box fee_box02">컨텐츠제작비</td>
                                <td class="fee_box" id="fee_box fee_box02">기타고정수수료</td>
                            </tr>
                            <tr class="fee_box" id="fee_box">
                                <td class="fee_box" id="fee_box fee_box02">-</td>
                                <td class="fee_box" id="fee_box fee_box02">-</td>
                                <td class="fee_box" id="fee_box fee_box02">-</td>
                            </tr>
                        </table>
                    </td>
                    <td>물류비</td>
                    <td>-</td>
                   <td class="cal02">매출에누리(A)</td>
                   <td>합계금액(A-B)</td>
                 </tr> 
                 <tr>
                     <td>-</td>
                     <td>-</td>
                     <td>-</td>
                     <td>-</td>
                     <td>-</td>
                     <td>-</td>
                     <td>-</td>
                     <td>-</td>
                     <td>
                         <table>
                             <tr>
                                 <td colspan="4" class="fee_box" id="fee_box">-</td>
                             </tr>
                             <tr class="fee_box" id="fee_box">
                                 <td class="fee_box" id="fee_box fee_box02">공급가</td>
                                 <td class="fee_box" id="fee_box fee_box02"></td>
                                 <td class="fee_box" id="fee_box fee_box02">부가세</td>
                                 <td class="fee_box" id="fee_box fee_box02"></td>
                             </tr>
                         </table>
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
            <table class="main main02" id="main02">
                <tr>
                    <td>상품번호</td>
                    <td>상품명</td>
                    <td>컨텐츠제작비</td>
                    <td>기타고정수수료</td>
                    <td>&nbsp;</td>
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