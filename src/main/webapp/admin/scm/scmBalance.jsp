<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/scm/total_balance.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
 <div class="cont_wrap">
       <div class="top_tit">
           정산 관리
       </div>
        <div class="search_cont">
            <div class="sch_tit">
              <h2>정산 검색</h2> 
              <button>검색</button> 
            </div>
            <div class="sch_ck_cont">
                <div class="sch_check">
                    <p>공급사 검색</p>
                    <select name="" id="">
                        <option value="">통합검색</option>
                    </select>
                    <input type="text" placeholder="검색어를 입력해주세요.">
                </div>
                <div class="sch_check">
                        <p>정산구분</p>
                        <label for="bal01" id="ck01"><input type="checkbox" name="bal" id="bal01"><span>전체</span> </label>
                        <label for="bal02" id="ck01"><input type="checkbox" name="bal" id="bal02">주문상품</label>
                        <label for="bal03" id="ck01"><input type="checkbox" name="bal" id="bal03">정산후환불</label>

                </div>
                <div class="sch_check">
                    <p>정산상태</p>
                    <label for="bal04" id="ck01"><input type="checkbox" name="bal" id="bal04"><span>전체</span> </label>
                    <label for="bal05" id="ck01"><input type="checkbox" name="bal" id="bal05">정산요청</label>
                    <label for="bal06" id="ck01"><input type="checkbox" name="bal" id="bal06">이월</label>
                    <label for="bal07" id="ck01"><input type="checkbox" name="bal" id="bal07">보류</label>
                    <label for="bal08" id="ck01"><input type="checkbox" name="bal" id="bal08">반려</label>
                    <label for="bal09" id="ck01"><input type="checkbox" name="bal" id="bal09">정산확정</label>
                    <label for="bal10" id="ck01"><input type="checkbox" name="bal" id="bal10">지급완료</label>


                </div>
                <div class="sch_check" id="srch_area">
                    <p>기간검색</p>
                    <input type='text' id="search_start_date"> ~ <input type='text' id="search_end_date">
                    <ul>
                        <li><button id="r_yes" name="yesterday">어제</button></li>
                        <li><button id="r_today" name="today">오늘</button></li>
                        <li><button id="r_week" name="week">1주일</button></li>
                        <li><button id="r_month" name="month">1개월</button></li>
                        <li><button id="r_3month" name="3month">3개월</button></li>
                    </ul>
                 </div>
                
              
            </div>     
        </div>
        <div class="sch_result">
            <h3>응모권 검색결과 <p>0건</p> </h3>
            <div class="sch_btn">
                <select name="" id="">
                    <option value="">요청일순</option>
                </select>
                <select name="" id="">
                    <option value="">10개보기</option>
                    <option value="">10개보기</option>
                    <option value="">10개보기</option>
                </select>
            </div>
            <table>
                <tr>
                    <td> <input type="checkbox" name="ck" class="ck" id="ck_all"> </td>
                    <td>번호</td>
                    <td>정산번호</td>
                    <td>요청일</td>
                    <td>공급사</td>
                    <td>정산타입</td>
                    <td>판매금액</td>
                    <td>수수료</td>
                    <td>정산금액</td>
                    <td>정산상태</td>
                    <td>상세정보</td>
                </tr>
                <tr>
                    <td> <input type="checkbox" name="ck" class="ck" id="ck01"></td>
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
            <div class="btm_box">
                <span>※ 선택한 정산을</span> 
                <select name="" id="">
                    <option value="">정산상태</option>
                </select>
                <button>일괄처리</button>
            </div>
            <div class="num">
                <a href="">&lt;</a>
                <a href="">1</a>
                <a href="">2</a>
                <a href="">3</a>
                <a href="">&gt;</a>
            </div>
        </div>
    </div>

<script src="${pageContext.request.contextPath}/admin/js/common/checkbox.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/common/day.js"></script>