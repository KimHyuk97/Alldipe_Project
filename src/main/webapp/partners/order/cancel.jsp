<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/order_cancel.css">
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
	<div class="cont_wrap">
		<div class="wrap">
			<div class="title">
          	  취소관리
            <p>
                <button onClick="window.location.reload()"><i class="fas fa-sync-alt"></i> 새로고침</button>
            </p>
            <div class="top_txt_box">
                <li class="top_txt_in top_txt_in01 ">
                    취소신청
                    <p>0</p>
                </li>
                <li class="top_txt_in top_txt_in02 ">
                    자동환불대기
                    <p>0</p>
                </li>
                <li class="top_txt_in top_txt_in03 ">
                    취소보류
                    <p>0</p>
                </li>
                <li class="top_txt_in top_txt_in04 ">
                    보류 후 환불대기
                    <p>0</p>
                </li>
                <li class="top_txt_in top_txt_in05 ">
                    취소완료
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
                    <option value="취소신청일">취소신청일</option>
                    <option value="반품완료일">취소완료일</option>
                    <option value="주문일">주문일</option>
                </select>
                <input type="date" title="달력" id="search_start_date" > ~
                <input type="date" title="달력" id="search_end_date" >
                <ul>
                    <li><button>오늘</button> </li>
                    <li><button>일주일</button> </li>
                    <li><button>1개월</button> </li>
                </ul>
                <br>
            </div>
            <div class='top_cont '>
                <div class="tit">처리상태</div>
                <select >
                    <option value="취소신청">취소신청</option>
                    <option value="취소승인">취소승인</option>
                    <option value="취소완료">취소완료</option>
                    <option value="취소보류">취소보류</option>
                    <option value="취소철회">취소철회</option>
                    <option value="취소거부">취소거부</option>
                </select>
                <div class="tit tit_agree">승인처리자</div>
                    <select>
                        <option value="전체">전체</option>
                        <option value="판매자">판매자</option>
                        <option value="구매자">구매자</option>
                        <option value="고객센터">고객센터</option>
                        <option value="시스템">시스템</option>
                    </select>
                <br>
            </div>
            
            <div class='top_cont '>
                <div class="tit">검색조건</div>
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
                <button>취소보류</button>
                <button>취소승인</button>
                <button>취소거부(이미출고)</button>
            </div>
            <table class="main">
                <tr>
                    <td><div class="square"></div></td>
                    <td>클레임번호</td>
                    <td>구매번호</td>
                    <td>주문번호</td>
                    <td>옵션주문번호</td>
                    <td>처리상태</td>
                    <td>취소신청일</td>
                    <td>접수채널</td>
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