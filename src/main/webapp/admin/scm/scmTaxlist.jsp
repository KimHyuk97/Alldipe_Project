<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/scm/total_taxlist.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
 <div class="cont_wrap">

        <div class="search_cont">
            <div class="sch_tit">
              <h2>세금계산서 발행 내역 리스트</h2> 
              <button>검색</button> 
            </div>
            <div class="sch_ck_cont">
                <div class="sch_check">
                    <p>검색어</p>
                    <select name="" id="">
                        <option value="">상품명</option>
                    </select>
                    <input type="text">
                </div>
                <div class="sch_check">
                    <p>사업자번호</p>
                    <input type="text">
                </div>
                 <div class="sch_check" id="srch_area">
                    <p>발행요청일</p>
                    <select name="" id="">
                        <option value="">전체</option>
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
                    <p>주문상태</p>
                    <div class="ck_wrap ck_wrap01">
                        <label for="ck01"><input type="checkbox" name="" id="ck01">전체</label>
                        <label for="ck02"><input type="checkbox" name="" id="ck02">결제완료</label>
                        <label for="ck03"><input type="checkbox" name="" id="ck03">상품준비중</label>
                        <label for="ck04"><input type="checkbox" name="" id="ck04">배송중</label>
                        <label for="ck05"><input type="checkbox" name="" id="ck05">배송완료</label>
                        <label for="ck06"><input type="checkbox" name="" id="ck06">구매확정</label>
                        <label for="ck07"><input type="checkbox" name="" id="ck07">PG 확인요망</label>
                    </div>
                    <div class="ck_wrap ck_wrap02">
                        <label for="ck08"> <input type="checkbox" name="" id="ck08">반품접수</label>
                        <label for="ck09"> <input type="checkbox" name="" id="ck09">반송중</label>
                    </div>
                    <div class="ck_wrap ck_wrap03">
                        <label for="ck10"> <input type="checkbox" name="" id="ck10">반품보류</label>
                        <label for="ck11"> <input type="checkbox" name="" id="ck11">반픔회수완료</label>
                        <label for="ck12"> <input type="checkbox" name="" id="ck12">교환접수</label>
                        <label for="ck13"> <input type="checkbox" name="" id="ck13">반송중</label>
                        <label for="ck14"> <input type="checkbox" name="" id="ck14">재배송중</label>
                        <label for="ck15"> <input type="checkbox" name="" id="ck15">교환보류</label>
                    </div>
                    <div class="ck_wrap ck_wrap04">
                        <label for="ck16"> <input type="checkbox" name="" id="ck16">교환완료</label>
                        <label for="ck17"> <input type="checkbox" name="" id="ck17">환불접수</label>
                    </div>
                    <div class="ck_wrap ck_wrap05">
                        <label for="ck18"> <input type="checkbox" name="" id="ck18">환불보류</label>
                        <label for="ck19"> <input type="checkbox" name="" id="ck19">환불완료</label>
                    </div>
                </div>
                <div class="sch_check">
                    <p>과세/면세</p>
                    <label for="ck20"><input type="checkbox" name="ck20" id="ck20">전체</label>
                    <label for="ck21"><input type="checkbox" name="ck20" id="ck21">과세</label>
                    <label for="ck22"><input type="checkbox" name="ck20" id="ck22">면세</label>
                </div>
            </div>     
        </div>
        <div class="center_table">
            <table>
                <tr>
                    <td>종류</td>
                    <td>업체수</td>
                    <td>발행수</td>
                    <td>발행액</td>
                    <td>공급가액</td>
                    <td>세액</td>
                </tr>
                <tr>
                    <td>일반 세금 계산서</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                </tr>
                <tr>
                    <td>일반계산서</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                </tr>
            </table>
            <p>* 전자세금계산서 이용시, 국세청 시스템에서 지원되지 않는 일부 특정문자는 제외되어 전송됩니다.</p>

        </div>
        <div class="sch_result">
            <h3>검색결과 <p>0건</p> </h3>
            <div class="sch_btn">
                <div class="btn_left">
                 <button>선택수정</button> 
                 <button>선택삭제</button> 
                </div>
                <div class="btn_right">
                    <button><i class="far fa-file-excel"></i> 엑셀 다운로드</button>
                    <button>일반세금계산서 발행</button>
                    <select name="" id="">
                        <option value="">20개보기</option>
                        <option value="">10개보기</option>
                        <option value="">10개보기</option>
                    </select>  
                </div>
                
            </div>
            <table>
                <tr>
                    <td><input type="checkbox" name="ck" class="ck" id="ck_all"> </td>
                    <td>번호</td>
                    <td>발행요청일</td>
                    <td>주문번호/주문자</td>
                    <td>주문상태</td>
                    <td>요청인</td>
                    <td>사업자정보</td>
                    <td>결제금액</td>
                    <td>세금등급</td>
                    <td>발행액</td>
                    <td>공급가액</td>
                    <td>세액</td>
                    <td>발행일</td>
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
<script src="${pageContext.request.contextPath}/admin/js/common/checkbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/common/day.js"></script>