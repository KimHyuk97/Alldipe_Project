<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/order_change.css">
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
	<div class="cont_wrap">
		<div class="wrap">
		<div class="title">
            교환관리
            <p>
                <button onClick="window.location.reload()"><i class="fas fa-sync-alt"></i> 새로고침</button>
            </p>
            <div class="top_txt_box">
                <li class="top_txt_in top_txt_in01 ">
                    교환신청
                    <p>0</p>
                </li>
                <li class="top_txt_in top_txt_in02 ">
                    교환완료
                    <p>0</p>
                </li>
                <li class="top_txt_in top_txt_in03 ">
                    교환지연
                    <p>0</p>
                </li>
                <li class="top_txt_in top_txt_in04 ">
                    교환보류
                    <p>0</p>
                </li>
                <li class="top_txt_in top_txt_in05 ">
                    교환완료
                    <p>0</p>
                </li>
            </div>
            <div class="top_btn">
                <button type="submit">검색</button>
                <button type="submit">초기화</button>
            </div>
        </div>
        <div class="top_wrap">
            <div class='top_cont srch_area'>
                <div class="tit">검색기간</div>
                <select>
                    <option value="주문일">주문일</option>
                    <option value="교환신청일">교환신청일</option>
                    <option value="교환완료일">교환완료일</option>
                    <option value="주문완료일">주문완료일</option>
                </select>
                <input type="date" title="달력" id="search_start_date" > ~
                <input type="date" title="달력" id="search_end_date" >
                <ul>
                    <li><button>어제</button> </li>
                    <li><button>일주일</button> </li>
                    <li><button>1개월</button> </li>
                </ul>
                <br>
            </div>
            <div class='top_cont '>
                <div class="tit">처리상태</div>
                <select >
                    <option value="처리상태전체">처리상태전체</option>
                    <option value="교환신청">교환신청</option>
                    <option value="교환승인">교환승인</option>
                    <option value="교환완료">교환완료</option>
                    <option value="교환보류">교환보류</option>
                    <option value="교환철회">교환철회</option>
                    <option value="교환거부">교환신거부</option>
                </select>
                <select >
                    <option value="전체">수거배송전체</option>
                    <option value="요청대기">요청대기</option>
                    <option value="수거예약">수거예약</option>
                    <option value="자동접수요청대기">자동접수요청대기</option>
                    <option value="요청완료">요청완료</option>
                    <option value="수거중">수거중</option>
                    <option value="수거완료">수거완료</option>
                    <option value="수거실패">수거실패</option>
                </select>
                <select >
                    <option value="전체">교환배송전체</option>
                    <option value="전체">전체</option>
                    <option value="상품출고">상품출고</option>
                    <option value="배송중">배송중</option>
                    <option value="배송완료">배송완료</option>
                </select>
                <br>
            </div>
            <div class='top_cont '>
                <div class="tit">검색어</div>
                <select>
                    <option value="전체">전체</option>
                    <option value="구매번호">구매번호</option>
                    <option value="주문번호">주문번호</option>
                    <option value="구매자">구매자</option>
                    <option value="상품번호">상품번호</option>
                    <option value="배송번호">배송번호</option>
                    <option value="클레임번호">클레임번호</option>
                    <option value="받는사람">받는사람</option>
                </select>
               <input type="text" placeholder="검색어를 입력하세요.">
                <br>
            </div>
        </div>
        <div class="bottom_wrap">
            <div class="btm_tit">
               상세 검색 결과 <p>0건</p>
                <div class="btm_tit_btn btm_tit_btn02">
                    <button type="submit" class="exel01" id="download_btn01"><i class="far fa-file-excel"></i> 엑셀 다운로드</button>
                    <select>
                        <option value="20개">20개</option>
                        <option value="50개">50개</option>
                        <option value="100개">100개</option>
                        <option value="200개">200개</option>
                        <option value="300개">300개</option>
                    </select>
                </div> 
            </div>
            <div class="btm_button">
                <button>수거요청</button>
                <button>수거완료</button>
                <button>교환배송</button>
                <button>교환보류</button>
                <button>수거방법변경</button>
                <button>교환배송변경</button>
                <button>교환완료</button>
            </div>
            <table class="main">
                <tr>
                    <td><div class="square"></div></td>
                    <td>클레임번호</td>
                    <td>구매번호</td>
                    <td>주문번호</td>
                    <td>옵션주문번호</td>
                    <td>처리상태</td>
                    <td>교환신청일</td>
                    <td>교환사유</td>
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
</body>
</html>