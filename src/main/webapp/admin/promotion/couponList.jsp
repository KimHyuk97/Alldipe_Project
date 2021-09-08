<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/promotion/total_coupon02.css">    
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>

 <div class="cont_wrap">
        <div class="top_tit">
            <h1>쿠폰 관리</h1>
        </div>
        <div class="search_cont">
            <div class="sch_tit">
              <h2>쿠폰 검색</h2> 
              <button>검색</button> 
              <button>쿠폰등록</button>
            </div>
            <div class="sch_ck_cont">
                <div class="sch_check">
                    <p>검색어</p>
                    <select name="" id="">
                        <option value="">통합검색</option>
                    </select>
                    <input type="text" placeholder="검색어를 입력해주세요.">
                </div>
                <div class="sch_check">
                    <p>공급사</p>
                    <input type="text" placeholder="검색어를 입력해주세요.">
                    <button>공급사선택</button>
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
                    <p>쿠폰유형</p>
                    <label for="ra01" id="la01"><input type="radio" name="use01" id="ra01">쿠폰</label>
                    <label for="ra02" id="la01"><input type="radio" name="use01" id="ra02">페이퍼쿠폰</label>
                    <select name="" id="">
                        <option value="">통합검색</option>
                    </select>
                </div>
                <div class="sch_check">
                    <p>발급방식</p>
                    <label for="ra05" id="la02"><input type="radio" name="use02" id="ra05">전체</label>
                    <label for="ra06" id="la02"><input type="radio" name="use02" id="ra06">회원다운로드</label>
                    <label for="ra07" id="la02"><input type="radio" name="use02" id="ra07">자동발급</label>
                    <label for="ra08" id="la02"><input type="radio" name="use02" id="ra08">수동발급</label>
                </div>
                <div class="sch_check">
                    <p>발급상태</p>
                    <label for="ra09" id="la03"><input type="radio" name="use03" id="ra09">전체</label>
                    <label for="ra10" id="la03"><input type="radio" name="use03" id="ra10">발급중</label>
                    <label for="ra11" id="la03"><input type="radio" name="use03" id="ra11">발급중단</label>
                    <label for="ra12" id="la03"><input type="radio" name="use03" id="ra12">발급종료</label>
                </div>
            </div>     
        </div>
        <div class="sch_result">
            <h3>쿠폰 검색결과 <p>0건</p> </h3>
            <div class="sch_btn">
                <button>선택삭제</button>
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
                    <td>쿠폰명</td>
                    <td>발행 공급사</td>
                    <td>등록일</td>
                    <td>쿠폰유형</td>
                    <td>사용기간</td>
                    <td>사용범위</td>
                    <td>발급상태</td>
                    <td>발급수</td>
                    <td>수정</td>
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