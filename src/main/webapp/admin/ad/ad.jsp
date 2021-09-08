<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/ad/total_ad.css">
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
  <div class="cont_wrap">
        <div class="top_tit">
            <h1>광고관리</h1>
        </div>
        <div class="search_cont">
            <div class="sch_tit">
              <h2>광고 리스트</h2> 
              <button>검색</button> 
            </div>
            <div class="sch_ck_cont">
                <div class="sch_check">
                    <p>노출유형</p>
                    <select name="통합검색" id="">
                        <option value="">메인배너</option>
                        <option value="">메인배너</option>
                    </select>
                    <select name="통합검색" id="">
                        <option value="">중분류</option>
                        <option value="">중분류</option>
                    </select>
                </div>
                <div class="sch_check">
                    <p>공급사 검색</p>
                    <input type="text" placeholder="검색어를 입력해주세요.">
                    <button>공급사 선택</button>
                </div>
                <div class="sch_check">
                    <p>진행범위</p>
                    <label for="ck01" id="la01"><input type="radio" name="radio01" id="ck01">전체</label>
                    <label for="ck02" id="la01"><input type="radio" name="radio01" id="ck02">PC</label>
                    <label for="ck03" id="la01"><input type="radio" name="radio01" id="ck03">모바일</label>
                </div>
                <div class="sch_check">
                    <p>진행상태</p>
                    <label for="ck04" id="la01"><input type="radio" name="radio02" id="ck04">전체</label>
                    <label for="ck05" id="la01"><input type="radio" name="radio02" id="ck05">실행</label>
                    <label for="ck06" id="la01"><input type="radio" name="radio02" id="ck06">중지</label>
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
            <h3>광고 검색결과 <p>0건</p> </h3>
            <div class="sch_btn">
                <select name="" id="">
                    <option value="">등록일순</option>
                </select>
                <select name="" id="">
                    <option value="">10개보기</option>
                    <option value="">10개보기</option>
                    <option value="">10개보기</option>
                </select>
            </div>
            <table>
                <tr>
                    <td class="img"> <img src="" alt=""> </td>
                    <td>진행상태</td>
                    <td>광고유형</td>
                    <td>광고이름</td>
                    <td>노출유형</td>
                    <td>노출수</td>
                    <td>클릭수</td>
                    <td>클릭률(%)</td>
                    <td>평균클릭비용(VAT포함,원)</td>
                    <td>총비용(VAT포함,원)</td>
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
                    <td>-</td>
                    <td>-</td>
                </tr>
            </table>
            <div class="bottom_btn">
                <button>선택상품 승인</button>
                <button>선택상품 반려</button>
                <button>선택상품 삭제</button>
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
    </script>
<!--하단-->
<jsp:include page="../adminFooter.jsp" flush="false"/>