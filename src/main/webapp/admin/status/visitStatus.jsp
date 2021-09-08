<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/status/total_visit.css">
<link href="/path/to/c3.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
  <div class="cont_wrap">
        <div class="top_tit">
            <h1>방문자 분석</h1>
        </div>
        <div class="search_cont">
            <div class="sch_tit">
              <h2>방문자 검색</h2> 
              <button>검색</button> 
            </div>
            <div class="sch_ck_cont">
                 <div class="sch_check">
                    <p>분석유형</p>
                    <label for="ck01" id="la01"><input type="radio" name="radio01" id="ck01">기간분석</label>
                    <label for="ck02" id="la01"><input type="radio" name="radio01" id="ck02">경로분석</label>
                    <label for="ck03" id="la01"><input type="radio" name="radio01" id="ck03">IP분석</label>
                </div>
                <div class="sch_check">
                    <p>기간검색</p>
                    <input type='date'/> ~ <input type='date'/>
                </div>
            </div>     
        </div>
        <div class="sch_result">
          <ul>
              <li>당일 방문현황</li>
              <li>일별 방문현황</li>
              <li>시간대별 방문현황</li>
              <li>요일별 방문현황</li>
              <li>월별 방문현황</li>
              <li>페이지뷰 현황</li>
          </ul>
          <table>
              <tr>
                  <td>총 방문자수</td>
                  <td>PC 방문자수</td>
                  <td>모바일 방문자수</td>
                  <td>총 페이지뷰</td>
                  <td>PC 페이지뷰</td>
                  <td>모바일 페이지뷰</td>
              </tr>
              <tr>
                  <td>175</td>
                  <td>69</td>
                  <td>106</td>
                  <td>658</td>
                  <td>226</td>
                  <td>432</td>
              </tr>
          </table>
        </div>
        <div>
            <canvas id="myChart" height="100"></canvas>
        </div>
        <table class="btm_table">
            <tr>
                <td>시간대</td>
                <td>총 방문자수</td>
                <td>PC 방문자수</td>
                <td>총 페이지뷰</td>
                <td>PC 페이지뷰</td>
                <td>모바일 페이지뷰</td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                
            </tr>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        var ctx = document.getElementById('myChart').getContext('2d');
        var chart = new Chart(ctx, {  type: 'line', 
         data: { labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'], 
         datasets: [{ label: 'My First dataset', backgroundColor: 'rgb(255, 99, 132)', 
         borderColor: 'rgb(255, 99, 132)', data: [0, 10, 5, 2, 20, 30, 45] }] }, 
          options: {}
         });

    </script>