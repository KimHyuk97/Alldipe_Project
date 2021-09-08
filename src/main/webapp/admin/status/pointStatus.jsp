<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/status/total_points.css">
<link href="/path/to/c3.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
  <div class="cont_wrap">
        <div class="top_tit">
            <h1>포인트 분석</h1>
        </div>
        <div class="search_cont">
            <div class="sch_tit">
              <h2>포인트 검색</h2> 
              <button>검색</button> 
            </div>
            <div class="sch_ck_cont">
                 <div class="sch_check">
                    <p>유형검색</p>
                    <label for="ck01" id="la01"><input type="radio" name="radio01" id="ck01">전체</label>
                    <label for="ck02" id="la01"><input type="radio" name="radio01" id="ck02">올디프페이</label>
                    <label for="ck03" id="la01"><input type="radio" name="radio01" id="ck03">포인트</label>
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
          <ul>
              <li>일별 포인트 현황</li>
              <li>월별 포인트 현황</li>
          </ul>
          <table>
              <tr>
                  <td>총 잔여 포인트</td>
                  <td>총 지급건수</td>
                  <td>총 지급금액</td>
                  <td>총 사용건수</td>
                  <td>총 사용금액</td>

              </tr>
              <tr>
                  <td>1000명</td>
                  <td>0</td>
                  <td>0</td>
                  <td>0</td>
                  <td>0</td>
              </tr>
          </table>
        </div>
        <div>
            <canvas id="myChart" height="100"></canvas>
        </div>
        <table class="btm_table">
            <tr>
                <td>날짜</td>
                <td>잔여 예치금</td>
                <td>지급건수</td>
                <td>지급금액</td>
                <td>사용건수</td>
                <td>사용금액</td>
            </tr>
            <tr>
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