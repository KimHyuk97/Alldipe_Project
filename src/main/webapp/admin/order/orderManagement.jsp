<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/order/total_ccr.css">
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
  <div class="cont_wrap">
        <div class="top_tit01">
            <h2>취소/교환/반품 관리</h2>
        </div>
        <div class="nav">
            <ul>
                <li value="1" onclick="CB()" class="on">취소 리스트</li>
                <li value="2" onclick="CB()">교환 리스트</li>
                <li value="3" onclick="CB()">반품 리스트</li>
            </ul>
                <button>검색</button>
                <button>초기화</button>
        </div>
        <section class="content01 on" id="content01">
            <div class="sch_check">
                <h2>공급사 구분</h2>
                <label for="ra01"><input type="radio" name="ra01" id="ra01">전체</label>
                <label for="ra02"><input type="radio" name="ra01" id="ra02">본사</label>
                <label for="ra03"><input type="radio" name="ra01" id="ra03">공급사</label>
            </div>
            <div class="sch_check">
                <h2>검색어</h2>
                <input type="text" placeholder="상품명">
                <input type="text">
            </div>
            <div class="sch_check" id="srch_area">
                <h2>기간검색</h2>
                <select name="" id="">
                    <option value="">전체</option>
                    <option value="">주문일</option>
                    <option value="">결제확인일</option>
                    <option value="">송장입력일</option>
                    <option value="">배송일</option>
                    <option value="">배송완료일</option>
                    <option value="">구매확정일</option>
                    <option value="">취소완료일</option>
                    <option value="">반품접수일</option>
                    <option value="">반품완료일</option>
                    <option value="">교환접수일</option>
                    <option value="">교환완료일</option>
                    <option value="">환불접수일</option>
                    <option value="">환불완료일</option>
                    <option value="">묶음배송</option>
                </select>
                <input type='text' id="search_start_date"> ~ <input type='text' id="search_end_date">
                <ul>
                    <li><button id="r_yes" name="yesterday">어제</button></li>
                    <li><button id="r_today" name="today">오늘</button></li>
                    <li><button id="r_week" name="week">1주일</button></li>
                    <li><button id="r_month" name="month">1개월</button></li>
                    <li><button id="r_3month" name="3month">3개월</button></li>
                </ul>
             </div>
             <div class="sch_check">
                <h2>카테고리</h2>
                <select name="" id="">
                    <option value="">카테고리 선택</option>
                    <option value="">주문번호</option>
                    <option value="">송장번호</option>
                    <option value="">상품코드</option>
                    <option value="">자체 상품고드</option>
                    <option value="">상품모델명</option>
                    <option value="">제조사</option>
                </select>
                <select name="" id="">
                    <option value="">전체</option>
                    <option value="">주문자명</option>
                    <option value="">주문자 전화번호</option>
                    <option value="">주문자 휴대폰 번호</option>
                    <option value="">주문자 이메일</option>
                    <option value="">수령자명</option>
                    <option value="">수령자 전화번호</option>
                    <option value="">수령자 휴대폰번호</option>
                    <option value="">입금자명</option>
                </select>
                <select name="" id="">
                    <option value="">아이디</option>
                    <option value="">닉네임</option>
                </select>
                <select name="" id="">
                    <option value="">전체</option>
                    <option value="">공급사명</option>
                </select>
            </div>
            <div class="sch_check">
                <h2>주문상태</h2>
                <label for="ra22"><input type="radio" name="ra06" id="ra22">전체</label>
                <label for="ra23"><input type="radio" name="ra06" id="ra23">자동취소</label>
                <label for="ra24"><input type="radio" name="ra06" id="ra24">품절취소</label>
                <label for="ra25"><input type="radio" name="ra06" id="ra25">관리자취소</label>
                <label for="ra26"><input type="radio" name="ra06" id="ra26">고객요청취소</label>
            </div>
            <div class="sch_check">
                <h2>결제수단</h2>
                <label for="ra04"><input type="radio" name="ra02" id="ra04">전체</label>
                <label for="ra05"><input type="radio" name="ra02" id="ra05">무통장입금</label>
                <label for="ra05_1"><input type="radio" name="ra02" id="ra05_1">전액할인</label>
                <label for="ra06"><input type="radio" name="ra02" id="ra06">포인트</label>
                <label for="ra07"><input type="radio" name="ra02" id="ra07">예치금</label>
                <label for="ra08"><input type="radio" name="ra02" id="ra08">신용카드</label>
                <label for="ra09"><input type="radio" name="ra02" id="ra09">계좌이체</label>
                <label for="ra10"><input type="radio" name="ra02" id="ra10">가상계좌</label>
                <label for="ra11"><input type="radio" name="ra02" id="ra11">휴대폰결제</label>
                <label for="ra12"><input type="radio" name="ra02" id="ra12">기타</label>
            </div>
            <div class="sch_check">
                <h2>송장번호</h2>
                <select name="" id="">
                    <option value="">배송업체</option>
                </select>
                <label for="ra13"><input type="radio" name="ra03" id="ra13">전체</label>
                <label for="ra14"><input type="radio" name="ra03" id="ra14">송장번호 등록</label>
                <label for="ra15"><input type="radio" name="ra03" id="ra15">송장번호 미등록</label>
            </div>
            <div class="sch_check">
                <h2>회원검색</h2>
                <label for="ra16"><input type="radio" name="ra04" id="ra16">전체</label>
                <label for="ra17"><input type="radio" name="ra04" id="ra17">회원</label>
                <label for="ra18"><input type="radio" name="ra04" id="ra18">비회원</label>
            </div>
            <div class="sch_check">
                <h2>프로모션 정보</h2>
                <a href="">쿠폰선택</a>
                <label for="ck01"><input type="checkbox" name="" id="ck01"> 쿠폰사용 주문 전체 검색</label>
            </div>
            <div class="sch_check">
                <h2>결제금액</h2>
                <input type="text"> 이상 ~
                <input type="text"> 이하
            </div>
            <div class="sch_check">
                <h2>영수증 신청</h2>
                <label for="ra19"><input type="radio" name="ra05" id="ra19">전체</label>
                <label for="ra20"><input type="radio" name="ra05" id="ra20">현금영수증</label>
                <label for="ra21"><input type="radio" name="ra05" id="ra21">세금계산서</label>
            </div>
            <div class="sch_check">
                <h2>브랜드 정보</h2>
                <select name="" id="">
                    <option value=""> 브랜드 선택</option>
                </select>
               <label for=""><input type="checkbox" name="" id="">브랜드 미지정 상품</label> 
            </div>





            <div class="cont_wrap_box" >
                 <div class="cont_box">
                    <p>검색결과 <span>0건</span> </p> 
                    <br>
                    <div class="btn_right">
                        <select name="" id="">
                            <option value="">주문번호 ↓</option>
                            <option value="">주문번호 ↑</option>
                            <option value="">상품명 ↓</option>
                            <option value="">상품명 ↑</option>
                            <option value="">주문자 ↓</option>
                            <option value="">주문자 ↑</option>
                            <option value="">총 결제금액 ↓</option>
                            <option value="">총 결제금액 ↑</option>
                            <option value="">수령자 ↓</option>
                            <option value="">수령자 ↑</option>
                            <option value="">공급사 ↓</option>
                            <option value="">공급사 ↑</option>
                        </select>
                        <select name="" id="">
                           <option value="">20개</option>
                           <option value="">20개</option>
                           <option value="">20개</option>
                        </select>
                        <select name="" id="">
                            <option value="">인쇄선택</option>
                        </select>
                        <input type="button" value="인쇄하기" id="print" onclick="window.print()"/>
                        <button><i class="far fa-file-excel"></i> 엑셀 다운로드</button>
                    </div>
                        
                    <table>
                        <tr>
                            <td><input type="checkbox" name="ck" class="ck" id="ck_all"> </td>
                            <td>번호</td>
                            <td>상점구분</td>
                            <td>주문일시</td>
                            <td>주문번호</td>
                            <td>주문자</td>
                            <td>주문상품</td>
                            <td>총 상품금액</td>
                            <td>총 배송비</td>
                            <td>총 주문금액</td>
                            <td>결제방법</td>
                            <td>결제상태</td>
                            <td>미배송</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="ck" class="ck" id="ck01"></td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                        </tr>
                    </table>
                 </div>
            </div>
        </section>
        <section class="content01 " id="content02">
            <div class="sch_check">
                <h2>공급사 구분</h2>
                <label for="ra01"><input type="radio" name="ra01" id="ra01">전체</label>
                <label for="ra02"><input type="radio" name="ra01" id="ra02">본사</label>
                <label for="ra03"><input type="radio" name="ra01" id="ra03">공급사</label>
            </div>
            <div class="sch_check">
                <h2>검색어</h2>
                <input type="text" placeholder="상품명">
                <input type="text">
            </div>
            <div class="sch_check" id="srch_area02">
                <h2>기간검색</h2>
                <select name="" id="">
                    <option value="">전체</option>
                    <option value="">주문일</option>
                    <option value="">결제확인일</option>
                    <option value="">송장입력일</option>
                    <option value="">배송일</option>
                    <option value="">배송완료일</option>
                    <option value="">구매확정일</option>
                    <option value="">취소완료일</option>
                    <option value="">반품접수일</option>
                    <option value="">반품완료일</option>
                    <option value="">교환접수일</option>
                    <option value="">교환완료일</option>
                    <option value="">환불접수일</option>
                    <option value="">환불완료일</option>
                    <option value="">묶음배송</option>
                </select>
                <input type='text' id="search_start_date01"> ~ <input type='text' id="search_end_date01">
                <ul>
                    <li><button id="r_yes" name="yesterday">어제</button></li>
                    <li><button id="r_today" name="today">오늘</button></li>
                    <li><button id="r_week" name="week">1주일</button></li>
                    <li><button id="r_month" name="month">1개월</button></li>
                    <li><button id="r_3month" name="3month">3개월</button></li>
                </ul>
             </div>
             <div class="sch_check">
                <h2>카테고리</h2>
                <select name="" id="">
                    <option value="">카테고리 선택</option>
                    <option value="">주문번호</option>
                    <option value="">송장번호</option>
                    <option value="">상품코드</option>
                    <option value="">자체 상품고드</option>
                    <option value="">상품모델명</option>
                    <option value="">제조사</option>
                </select>
                <select name="" id="">
                    <option value="">전체</option>
                    <option value="">주문자명</option>
                    <option value="">주문자 전화번호</option>
                    <option value="">주문자 휴대폰 번호</option>
                    <option value="">주문자 이메일</option>
                    <option value="">수령자명</option>
                    <option value="">수령자 전화번호</option>
                    <option value="">수령자 휴대폰번호</option>
                    <option value="">입금자명</option>
                </select>
                <select name="" id="">
                    <option value="">아이디</option>
                    <option value="">닉네임</option>
                </select>
                <select name="" id="">
                    <option value="">전체</option>
                    <option value="">공급사명</option>
                </select>
            </div>
            <div class="sch_check">
                <h2>주문상태</h2>
                <label for="ra22"><input type="radio" name="ra06" id="ra22">전체</label>
                <label for="ra23"><input type="radio" name="ra06" id="ra23">교환접수</label>
                <label for="ra24"><input type="radio" name="ra06" id="ra24">반송중</label>
                <label for="ra25"><input type="radio" name="ra06" id="ra25">재배송중</label>
                <label for="ra26"><input type="radio" name="ra06" id="ra26">교환보류</label>
                <label for="ra27"><input type="radio" name="ra06" id="ra27">교환완료</label>
            </div>
            <div class="sch_check">
                <h2>결제수단</h2>
                <label for="ra04"><input type="radio" name="ra02" id="ra04">전체</label>
                <label for="ra05"><input type="radio" name="ra02" id="ra05">무통장입금</label>
                <label for="ra06"><input type="radio" name="ra02" id="ra06">포인트</label>
                <label for="ra07"><input type="radio" name="ra02" id="ra07">예치금</label>
                <label for="ra08"><input type="radio" name="ra02" id="ra08">신용카드</label>
                <label for="ra09"><input type="radio" name="ra02" id="ra09">계좌이체</label>
                <label for="ra10"><input type="radio" name="ra02" id="ra10">가상계좌</label>
                <label for="ra11"><input type="radio" name="ra02" id="ra11">휴대폰결제</label>
                <label for="ra12"><input type="radio" name="ra02" id="ra12">기타</label>
            </div>
            <div class="sch_check">
                <h2>송장번호</h2>
                <select name="" id="">
                    <option value="">배송업체</option>
                </select>
                <label for="ra13"><input type="radio" name="ra03" id="ra13">전체</label>
                <label for="ra14"><input type="radio" name="ra03" id="ra14">송장번호 등록</label>
                <label for="ra15"><input type="radio" name="ra03" id="ra15">송장번호 미등록</label>
            </div>
            <div class="sch_check">
                <h2>회원검색</h2>
                <label for="ra16"><input type="radio" name="ra04" id="ra16">전체</label>
                <label for="ra17"><input type="radio" name="ra04" id="ra17">회원</label>
                <label for="ra18"><input type="radio" name="ra04" id="ra18">비회원</label>
            </div>
            <div class="sch_check">
                <h2>프로모션 정보</h2>
                <a href="">쿠폰선택</a>
                <label for="ck01"><input type="checkbox" name="" id="ck01"> 쿠폰사용 주문 전체 검색</label>
            </div>
            <div class="sch_check">
                <h2>결제금액</h2>
                <input type="text"> 이상 ~
                <input type="text"> 이하
            </div>
            <div class="sch_check">
                <h2>영수증 신청</h2>
                <label for="ra19"><input type="radio" name="ra05" id="ra19">전체</label>
                <label for="ra20"><input type="radio" name="ra05" id="ra20">현금영수증</label>
                <label for="ra21"><input type="radio" name="ra05" id="ra21">세금계산서</label>
            </div>
            <div class="sch_check">
                <h2>브랜드 정보</h2>
                <select name="" id="">
                    <option value=""> 브랜드 선택</option>
                </select>
               <label for=""><input type="checkbox" name="" id="">브랜드 미지정 상품</label> 
            </div>



            <div class="cont_wrap_box" >
                 <div class="cont_box">
                    <p>검색결과 <span>0건</span> </p> 
                    <br>
                    <div class="btn_right">
                        <select name="" id="">
                            <option value="">주문번호 ↓</option>
                            <option value="">주문번호 ↑</option>
                            <option value="">상품명 ↓</option>
                            <option value="">상품명 ↑</option>
                            <option value="">주문자 ↓</option>
                            <option value="">주문자 ↑</option>
                            <option value="">총 결제금액 ↓</option>
                            <option value="">총 결제금액 ↑</option>
                            <option value="">수령자 ↓</option>
                            <option value="">수령자 ↑</option>
                            <option value="">공급사 ↓</option>
                            <option value="">공급사 ↑</option>
                        </select>
                        <select name="" id="">
                           <option value="">20개</option>
                           <option value="">20개</option>
                           <option value="">20개</option>
                        </select>
                        <select name="" id="">
                            <option value="">인쇄선택</option>
                        </select>
                        <input type="button" value="인쇄하기" id="print" onclick="window.print()"/>
                        <button><i class="far fa-file-excel"></i> 엑셀 다운로드</button>
                    </div>
                        
                    <table>
                        <tr>
                            <td><input type="checkbox" name="ck" class="ck" id="ck_all"> </td>
                            <td>번호</td>
                            <td>상점구분</td>
                            <td>주문일시</td>
                            <td>주문번호</td>
                            <td>주문자</td>
                            <td>주문상품</td>
                            <td>총 상품금액</td>
                            <td>총 배송비</td>
                            <td>총 주문금액</td>
                            <td>결제방법</td>
                            <td>결제상태</td>
                            <td>미배송</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="ck" class="ck" id="ck01"></td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                        </tr>
                    </table>
                 </div>
            </div>
        </section>
        <section class="content01 " id="content03">
            <div class="sch_check">
                <h2>공급사 구분</h2>
                <label for="ra01"><input type="radio" name="ra01" id="ra01">전체</label>
                <label for="ra02"><input type="radio" name="ra01" id="ra02">본사</label>
                <label for="ra03"><input type="radio" name="ra01" id="ra03">공급사</label>
            </div>
            <div class="sch_check">
                <h2>검색어</h2>
                <input type="text" placeholder="상품명">
                <input type="text">
            </div>
            <div class="sch_check" id="srch_area03">
                <h2>기간검색</h2>
                <select name="" id="">
                    <option value="">전체</option>
                    <option value="">주문일</option>
                    <option value="">결제확인일</option>
                    <option value="">송장입력일</option>
                    <option value="">배송일</option>
                    <option value="">배송완료일</option>
                    <option value="">구매확정일</option>
                    <option value="">취소완료일</option>
                    <option value="">반품접수일</option>
                    <option value="">반품완료일</option>
                    <option value="">교환접수일</option>
                    <option value="">교환완료일</option>
                    <option value="">환불접수일</option>
                    <option value="">환불완료일</option>
                    <option value="">묶음배송</option>
                </select>
                <input type='text' id="search_start_date02"> ~ <input type='text' id="search_end_date02">
                <ul>
                    <li><button id="r_yes" name="yesterday">어제</button></li>
                    <li><button id="r_today" name="today">오늘</button></li>
                    <li><button id="r_week" name="week">1주일</button></li>
                    <li><button id="r_month" name="month">1개월</button></li>
                    <li><button id="r_3month" name="3month">3개월</button></li>
                </ul>
             </div>
             <div class="sch_check">
                <h2>카테고리</h2>
                <select name="" id="">
                    <option value="">카테고리 선택</option>
                    <option value="">주문번호</option>
                    <option value="">송장번호</option>
                    <option value="">상품코드</option>
                    <option value="">자체 상품고드</option>
                    <option value="">상품모델명</option>
                    <option value="">제조사</option>
                </select>
                <select name="" id="">
                    <option value="">전체</option>
                    <option value="">주문자명</option>
                    <option value="">주문자 전화번호</option>
                    <option value="">주문자 휴대폰 번호</option>
                    <option value="">주문자 이메일</option>
                    <option value="">수령자명</option>
                    <option value="">수령자 전화번호</option>
                    <option value="">수령자 휴대폰번호</option>
                    <option value="">입금자명</option>
                </select>
                <select name="" id="">
                    <option value="">아이디</option>
                    <option value="">닉네임</option>
                </select>
                <select name="" id="">
                    <option value="">전체</option>
                    <option value="">공급사명</option>
                </select>
            </div>
            <div class="sch_check">
                <h2>주문상태</h2>
                <label for="ra22"><input type="radio" name="ra06" id="ra22">전체</label>
                <label for="ra23"><input type="radio" name="ra06" id="ra23">교환접수</label>
                <label for="ra24"><input type="radio" name="ra06" id="ra24">반송중</label>
                <label for="ra25"><input type="radio" name="ra06" id="ra25">재배송중</label>
                <label for="ra26"><input type="radio" name="ra06" id="ra26">교환보류</label>
                <label for="ra27"><input type="radio" name="ra06" id="ra27">교환완료</label>
            </div>
            <div class="sch_check">
                <h2>결제수단</h2>
                <label for="ra04"><input type="radio" name="ra02" id="ra04">전체</label>
                <label for="ra05"><input type="radio" name="ra02" id="ra05">무통장입금</label>
                <label for="ra06"><input type="radio" name="ra02" id="ra06">포인트</label>
                <label for="ra07"><input type="radio" name="ra02" id="ra07">예치금</label>
                <label for="ra08"><input type="radio" name="ra02" id="ra08">신용카드</label>
                <label for="ra09"><input type="radio" name="ra02" id="ra09">계좌이체</label>
                <label for="ra10"><input type="radio" name="ra02" id="ra10">가상계좌</label>
                <label for="ra11"><input type="radio" name="ra02" id="ra11">휴대폰결제</label>
                <label for="ra12"><input type="radio" name="ra02" id="ra12">기타</label>
            </div>
            <div class="sch_check">
                <h2>송장번호</h2>
                <select name="" id="">
                    <option value="">배송업체</option>
                </select>
                <label for="ra13"><input type="radio" name="ra03" id="ra13">전체</label>
                <label for="ra14"><input type="radio" name="ra03" id="ra14">송장번호 등록</label>
                <label for="ra15"><input type="radio" name="ra03" id="ra15">송장번호 미등록</label>
            </div>
            <div class="sch_check">
                <h2>회원검색</h2>
                <label for="ra16"><input type="radio" name="ra04" id="ra16">전체</label>
                <label for="ra17"><input type="radio" name="ra04" id="ra17">회원</label>
                <label for="ra18"><input type="radio" name="ra04" id="ra18">비회원</label>
            </div>
            <div class="sch_check">
                <h2>프로모션 정보</h2>
                <a href="">쿠폰선택</a>
                <label for="ck01"><input type="checkbox" name="" id="ck01"> 쿠폰사용 주문 전체 검색</label>
            </div>
            <div class="sch_check">
                <h2>결제금액</h2>
                <input type="text"> 이상 ~
                <input type="text"> 이하
            </div>
            <div class="sch_check">
                <h2>영수증 신청</h2>
                <label for="ra19"><input type="radio" name="ra05" id="ra19">전체</label>
                <label for="ra20"><input type="radio" name="ra05" id="ra20">현금영수증</label>
                <label for="ra21"><input type="radio" name="ra05" id="ra21">세금계산서</label>
            </div>
            <div class="sch_check">
                <h2>브랜드 정보</h2>
                <select name="" id="">
                    <option value=""> 브랜드 선택</option>
                </select>
               <label for=""><input type="checkbox" name="" id="">브랜드 미지정 상품</label> 
            </div>





            <div class="cont_wrap_box" >
                 <div class="cont_box">
                    <p>검색결과 <span>0건</span> </p> 
                    <br>
                    <div class="btn_right">
                        <select name="" id="">
                            <option value="">주문번호 ↓</option>
                            <option value="">주문번호 ↑</option>
                            <option value="">상품명 ↓</option>
                            <option value="">상품명 ↑</option>
                            <option value="">주문자 ↓</option>
                            <option value="">주문자 ↑</option>
                            <option value="">총 결제금액 ↓</option>
                            <option value="">총 결제금액 ↑</option>
                            <option value="">수령자 ↓</option>
                            <option value="">수령자 ↑</option>
                            <option value="">공급사 ↓</option>
                            <option value="">공급사 ↑</option>
                        </select>
                        <select name="" id="">
                           <option value="">20개</option>
                           <option value="">20개</option>
                           <option value="">20개</option>
                        </select>
                        <select name="" id="">
                            <option value="">인쇄선택</option>
                        </select>
                        <input type="button" value="인쇄하기" id="print" onclick="window.print()"/>
                        <button><i class="far fa-file-excel"></i> 엑셀 다운로드</button>
                    </div>
                        
                    <table>
                        <tr>
                            <td><input type="checkbox" name="ck" class="ck" id="ck_all"> </td>
                            <td>번호</td>
                            <td>상점구분</td>
                            <td>주문일시</td>
                            <td>주문번호</td>
                            <td>주문자</td>
                            <td>주문상품</td>
                            <td>총 상품금액</td>
                            <td>총 배송비</td>
                            <td>총 주문금액</td>
                            <td>결제방법</td>
                            <td>결제상태</td>
                            <td>미배송</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="ck" class="ck" id="ck01"></td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                            <td>-</td>
                        </tr>
                    </table>
                 </div>
            </div>
        </section>
     
    </div>

    


    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
    <script>
         //체크박스 전체 선택 및 전체 해제

         $(document).ready(function(){
        //최상단 체크박스 클릭
            $("#ck_all").click(function(){
                //클릭되었으면
                if($("#ck_all").prop("checked")){
                    //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
                    $(".ck").prop("checked",true);
                    //클릭이 안되있으면
                }else{
                    //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
                    $(".ck").prop("checked",false);
                }
            })
        })


        //탭이벤트//

        function CB(){
            console.log(event.target.value);
            if(event.target.value == 1){
                var contents=$("#content01").attr("class","on");
                var contents=$("#content02").attr("class","content01");
                var contents=$("#content03").attr("class","content01");
            }else if(event.target.value == 2){
                var contents=$("#content01").attr("class","content01");
                var contents=$("#content02").attr("class","on");
                var contents=$("#content03").attr("class","content01");
            }else if(event.target.value == 3){
                var contents=$("#content01").attr("class","content01");
                var contents=$("#content02").attr("class","content01");
                var contents=$("#content03").attr("class","on");
            }
        }
 
 
 
        /* 날짜 객체 받아서 문자열로 리턴하는 함수 */
        function getDateStr(myDate){
                return (myDate.getFullYear() + '-' + (myDate.getMonth() + 1) + '-' + myDate.getDate())
            }
            /* 어제 날짜를 문자열로 반환 */
            function yesterday() {
            var d = new Date()
            var dayOfMonth = d.getDate()
            d.setDate(dayOfMonth - 1)
            return getDateStr(d)
            }

            /* 오늘 날짜를 문자열로 반환 */
            function today() {
            var d = new Date()
            return getDateStr(d)
            }

            /* 오늘로부터 1주일전 날짜 반환 */
            function lastWeek() {
            var d = new Date()
            var dayOfMonth = d.getDate()
            d.setDate(dayOfMonth - 7)
            return getDateStr(d)
            }

            /* 오늘로부터 1개월전 날짜 반환 */
            function lastMonth() {
            var d = new Date()
            var monthOfYear = d.getMonth()
            d.setMonth(monthOfYear - 1)
            return getDateStr(d)
            }

            /* 오늘로부터 3개월전 날짜 반환 */
            function threeMonth() {
            var d = new Date()
            var monthOfYear = d.getMonth()
            d.setMonth(monthOfYear - 3)
            return getDateStr(d)
            }


            $("#srch_area :button").click(function(){
            var rname = $(this).attr("name")
            $("#search_end_date").val(today())
            //------오늘날짜로 기본설정-----
                if( rname == "yesterday"){
                $("#search_start_date").val(yesterday())
            //------어제날짜
            }else if( rname == "today"){
                $("#search_start_date").val(today())
            //------오늘날짜
            }else if(rname == "week") {
                $("#search_start_date").val(lastWeek())
            //------1주일전날짜
            }else if(rname == "month"){
                $("#search_start_date").val(lastMonth())
            //------1달전날짜
            }else{
                $("#search_start_date").val(threeMonth())
            //------3달전날짜
            }


    })

         /* 날짜 객체 받아서 문자열로 리턴하는 함수 */
         function getDateStr(myDate){
                return (myDate.getFullYear() + '-' + (myDate.getMonth() + 1) + '-' + myDate.getDate())
            }
            /* 어제 날짜를 문자열로 반환 */
            function yesterday() {
            var d = new Date()
            var dayOfMonth = d.getDate()
            d.setDate(dayOfMonth - 1)
            return getDateStr(d)
            }

            /* 오늘 날짜를 문자열로 반환 */
            function today() {
            var d = new Date()
            return getDateStr(d)
            }

            /* 오늘로부터 1주일전 날짜 반환 */
            function lastWeek() {
            var d = new Date()
            var dayOfMonth = d.getDate()
            d.setDate(dayOfMonth - 7)
            return getDateStr(d)
            }

            /* 오늘로부터 1개월전 날짜 반환 */
            function lastMonth() {
            var d = new Date()
            var monthOfYear = d.getMonth()
            d.setMonth(monthOfYear - 1)
            return getDateStr(d)
            }

            /* 오늘로부터 3개월전 날짜 반환 */
            function threeMonth() {
            var d = new Date()
            var monthOfYear = d.getMonth()
            d.setMonth(monthOfYear - 3)
            return getDateStr(d)
            }


            $("#srch_area02 :button").click(function(){
            var rname = $(this).attr("name")
            $("#search_end_date01").val(today())
            //------오늘날짜로 기본설정-----
                if( rname == "yesterday"){
                $("#search_start_date01").val(yesterday())
            //------어제날짜
            }else if( rname == "today"){
                $("#search_start_date01").val(today())
            //------오늘날짜
            }else if(rname == "week") {
                $("#search_start_date01").val(lastWeek())
            //------1주일전날짜
            }else if(rname == "month"){
                $("#search_start_date01").val(lastMonth())
            //------1달전날짜
            }else{
                $("#search_start_date01").val(threeMonth())
            //------3달전날짜
            }


    })

       /* 날짜 객체 받아서 문자열로 리턴하는 함수 */
       function getDateStr(myDate){
                return (myDate.getFullYear() + '-' + (myDate.getMonth() + 1) + '-' + myDate.getDate())
            }
            /* 어제 날짜를 문자열로 반환 */
            function yesterday() {
            var d = new Date()
            var dayOfMonth = d.getDate()
            d.setDate(dayOfMonth - 1)
            return getDateStr(d)
            }

            /* 오늘 날짜를 문자열로 반환 */
            function today() {
            var d = new Date()
            return getDateStr(d)
            }

            /* 오늘로부터 1주일전 날짜 반환 */
            function lastWeek() {
            var d = new Date()
            var dayOfMonth = d.getDate()
            d.setDate(dayOfMonth - 7)
            return getDateStr(d)
            }

            /* 오늘로부터 1개월전 날짜 반환 */
            function lastMonth() {
            var d = new Date()
            var monthOfYear = d.getMonth()
            d.setMonth(monthOfYear - 1)
            return getDateStr(d)
            }

            /* 오늘로부터 3개월전 날짜 반환 */
            function threeMonth() {
            var d = new Date()
            var monthOfYear = d.getMonth()
            d.setMonth(monthOfYear - 3)
            return getDateStr(d)
            }


            $("#srch_area03 :button").click(function(){
            var rname = $(this).attr("name")
            $("#search_end_date02").val(today())
            //------오늘날짜로 기본설정-----
                if( rname == "yesterday"){
                $("#search_start_date02").val(yesterday())
            //------어제날짜
            }else if( rname == "today"){
                $("#search_start_date02").val(today())
            //------오늘날짜
            }else if(rname == "week") {
                $("#search_start_date02").val(lastWeek())
            //------1주일전날짜
            }else if(rname == "month"){
                $("#search_start_date02").val(lastMonth())
            //------1달전날짜
            }else{
                $("#search_start_date02").val(threeMonth())
            //------3달전날짜
            }


    })
    </script>
<!--하단-->
<jsp:include page="../adminFooter.jsp" flush="false"/>