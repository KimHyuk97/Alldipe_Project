<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/ad/total_biz.css">
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
 <div class="cont_wrap">
        <div class="top_tit">
            <h1>비즈머니 충전내역</h1>
        </div>
        <div class="search_cont">
            <div class="sch_tit">
              <h2>충전내역</h2> 
              <button>검색</button> 
            </div>
            <div class="sch_ck_cont">
                <div class="sch_check">
                    <p>공급사 검색</p>
                    <input type="text" placeholder="검색어를 입력해주세요.">
                    <button>공급사 선택</button>
                </div>
                <div class="sch_check">
                    <p>진행범위</p>
                    <label for="ck01" id="la01"><input type="radio" name="radio01" id="ck01">전체</label>
                    <label for="ck02" id="la01"><input type="radio" name="radio01" id="ck02">실행</label>
                    <label for="ck03" id="la01"><input type="radio" name="radio01" id="ck03">중지</label>
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
            <h3>검색결과 <p>0건</p> </h3>
            <div class="sch_btn">
                <select name="" id="">
                    <option value="">검색순</option>
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
                    <td>공급사</td>
                    <td>공급사코드</td>
                    <td>충전일자</td>
                    <td>충전내역</td>
                    <td>환불내역</td>
                    <td>환급내역</td>
                    <td>총금액</td>
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
    </script>
<!--하단-->
<jsp:include page="../adminFooter.jsp" flush="false"/>