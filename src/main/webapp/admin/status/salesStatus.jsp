<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/status/total_salesan.css">
<link href="/path/to/c3.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
<div class="cont_wrap">
        <div class="top_tit">
            <h1>매출 분석</h1>
        </div>
        <div class="search_cont">
            <div class="sch_tit">
              <h2>매출 검색</h2> 
              <button>검색</button> 
            </div>
            <div class="sch_ck_cont">
                 <div class="sch_check">
                    <p>분석유형</p>
                    <label for="ck01" id="la01"><input type="radio" name="radio01" id="ck01">매출분석</label>
                    <label for="ck02" id="la01"><input type="radio" name="radio01" id="ck02">주문분석</label>
                </div>
                <div class="sch_check">
                    <p>공급사 검색</p>
                    <input type="text" placeholder="검색어를 입력해주세요.">
                    <button>공급사 선택</button>
                </div>
                <div class="sch_check">
                    <p>항목검색</p>
                    <label for="ck04" id="la01"><input type="radio" name="radio02" id="ck04">연령별</label>
                    <label for="ck05" id="la01"><input type="radio" name="radio02" id="ck05">결제수단</label>
                    <label for="ck06" id="la01"><input type="radio" name="radio02" id="ck06">지역별</label>
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
                <div class="sch_check">
                    <p>카테고리 검색</p>
                    <select name="" id="">
                        <option value="">대분류</option>
                    </select>
                    <select name="" id="">
                        <option value="">중분류</option>
                    </select>
                    <select name="" id="">
                        <option value="">소분류</option>
                    </select>
                </div>
            </div>     
        </div>
        <div class="sch_result">
          <ul>
              <li>일별 매출현황</li>
              <li>시간대별 매출현황</li>
              <li>요일별 매출현황</li>
              <li>월별 매출현황</li>
              <li>과세구분 매출현황</li>
          </ul>
          <table>
              <tr>
                  <td>총 매출금액</td>
                  <td>최대 매출금액</td>
                  <td>최소 매출금액</td>
                  <td>PC 매출금액</td>
                  <td>모바일 매출금액</td>
                  <td>교환 및 환불금액</td>
              </tr>
              <tr>
                  <td>212,040원</td>
                  <td>94,840원</td>
                  <td>0원</td>
                  <td>54,940원</td>
                  <td>157,100원</td>
                  <td>22,600원</td>
              </tr>
          </table>
        </div>
        <div>
            <canvas id="myChart" height="100"></canvas>
        </div>
        <table class="btm_table">
            <tr>
                <td rowspan="2">날짜</td>
                <td rowspan="2">유입경로</td>
                <td rowspan="2">매출금액</td>
                <td colspan="5">판매금액</td>
                <td colspan="10">환불금액</td>
            </tr>
            <tr>
                
                <td>상품판매가</td>
                <td>배송비</td>
                <td>결제금액</td>
                <td>할인금액</td>
                <td>총 판매금액</td>
                <td>상품판매가</td>
                <td>배송비</td>
                <td>결제금액</td>
                <td>할인금액</td>
                <td>총 판매금액</td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                
            </tr>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
    <script>
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
        





        var ctx = document.getElementById('myChart').getContext('2d');
        var chart = new Chart(ctx, {  type: 'line', 
         data: { labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'], 
         datasets: [{ label: 'My First dataset', backgroundColor: 'rgb(255, 99, 132)', 
         borderColor: 'rgb(255, 99, 132)', data: [0, 10, 5, 2, 20, 30, 45] }] }, 
          options: {}
         });

    </script>