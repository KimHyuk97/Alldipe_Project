<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/order/total_cash_r.css">
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
    <div class="cont_wrap">
        <div class="top_tit01">
            <h2>현금영수증 발급/조회</h2>
        </div>
        <div class="nav">
            <ul>
                <li value="1" onclick="CB()" class="on">현금영수증 발급/조회</li>
                <li value="2" onclick="CB()">현금영수증 개별발급</li>
                
            </ul>
                <button>검색</button>
                <button>초기화</button>
        </div>
        <section class="content01 on" id="content01">
            <div class="cont_wrap_box" >
                
                <div class="cont_box">
                    <p>검색어</p> 
                    <select name="" id="">
                        <option value="">상품명</option>
                    </select>
                    <input type="text">
                 </div>
                 <div class="cont_box" id="srch_area">
                    <p>기간검색</p>
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
                 <div class="cont_box">
                    <p>주문상태</p> 
                   <label for="ra01"><input type="radio" name="ra01" id="ra01">전체</label>
                   <label for="ra02"><input type="radio" name="ra01" id="ra02">미입금</label>
                   <label for="ra03"><input type="radio" name="ra01" id="ra03">입금</label>
                   <label for="ra04"><input type="radio" name="ra01" id="ra04">미배송</label>
                   <label for="ra05"><input type="radio" name="ra01" id="ra05">배송</label>
                   <label for="ra06"><input type="radio" name="ra01" id="ra06">취소</label>
                   <label for="ra07"><input type="radio" name="ra01" id="ra07">반품</label>
                   <label for="ra08"><input type="radio" name="ra01" id="ra08">교환</label>
                   <label for="ra09"><input type="radio" name="ra01" id="ra09">환불</label>
                 </div>
                 <div class="cont_box">
                    <p>발행상태</p> 
                   <label for="ra10"><input type="radio" name="ra02" id="ra10">전체</label>
                   <label for="ra11"><input type="radio" name="ra02" id="ra11">발급요청</label>
                   <label for="ra12"><input type="radio" name="ra02" id="ra12">발행완료</label>
                   <label for="ra13"><input type="radio" name="ra02" id="ra13">발행취소</label>
                   <label for="ra14"><input type="radio" name="ra02" id="ra14">발행거절</label>
                   <label for="ra15"><input type="radio" name="ra02" id="ra15">발행실패</label>
                 </div>
                 <div class="cont_box">
                    <p>발행용도</p> 
                   <label for="ra16"><input type="radio" name="ra03" id="ra16">전체</label>
                   <label for="ra17"><input type="radio" name="ra03" id="ra17">소득공제용</label>
                   <label for="ra18"><input type="radio" name="ra03" id="ra18">지출증빙용</label>
                 </div>
                 <div class="cont_box">
                    <p>과세/면세</p> 
                   <label for="ra19"><input type="radio" name="ra04" id="ra19">전체</label>
                   <label for="ra20"><input type="radio" name="ra04" id="ra20">과세</label>
                   <label for="ra21"><input type="radio" name="ra04" id="ra21">면세</label>
                   <label for="ra22"><input type="radio" name="ra04" id="ra22">복합과세</label>
                 </div>
                 <div class="cont_box">
                     <p>상품가격</p>
                     <input type="text"> 이상 ~ <input type="text"> 이하
                 </div>
                 <div class="cont_box">
                    <p>인증종류</p> 
                   <label for="ra23"><input type="radio" name="ra05" id="ra23">전체</label>
                   <label for="ra24"><input type="radio" name="ra05" id="ra24">사업자번호</label>
                   <label for="ra25"><input type="radio" name="ra05" id="ra25">휴대폰번호</label>
                </div>
                <div class="cont_box">
                    <p>금액변경확인</p>
                    <input type="checkbox" name="" id=""> <span>발급금액 변경건만 보기</span>
                </div>
                <div class="cont_box">
                    <p>거래번호</p>
                    <input type="text">
                </div>

                 <div class="cont_box">
                    <p>검색결과 <span>0건</span></p> 
                    <br>
                    <div class="btn_left">
                        <span>선택한 현금영수증</span>
                        <button>일괄발급</button>
                    </div>
                    <div class="btn_right">
                        <select name="" id="">
                            <option value="">주문번호 ↓</option>
                        </select>
                        <select name="" id="">
                           <option value="">20개</option>
                           <option value="">20개</option>
                           <option value="">20개</option>
                        </select>
                        <select name="" id="">
                            <option value="">인쇄선택</option>
                        </select>
                        <button class="print">프린트</button>
                        <button><i class="far fa-file-excel"></i> 엑셀 다운로드</button>
                    </div>
                        
                    <table>
                        <tr>
                            <td><input type="checkbox" name="ck" class="ck" id="ck_all"> </td>
                            <td>번호</td>
                            <td>신청일자</td>
                            <td>처리일자</td>
                            <td>주문번호</td>
                            <td>신청자</td>
                            <td>과세/면세</td>
                            <td>발급금액</td>
                            <td>결제방법</td>
                            <td>주문상태</td>
                            <td>발행상태</td>
                            <td>정보</td>
                            <td>영수증</td>
                            <td>처리</td>
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
                            <td>-</td>
                            
                        </tr>
                    </table>
                 </div>
            </div>
        </section>
        <section class="content01" id="content02">
           
            <div class="cont_wrap_box" id="cont_wrap01">
                <div class="cont_box">
                   <p>검색어</p> 
                   <input type="text">
                </div>
                <div class="cont_box">
                    <p>주문번호</p> 
                    <input type="text">
                    <button>유효성 검사</button>
                 </div>
                 <div class="cont_box">
                    <p>신청자명</p> 
                    <input type="text">
                 </div>
                 <div class="cont_box">
                    <p>휴대폰 번호</p>
                    <input type="tel" name="tel01"> - <input type="tel" name="tel02"> - <input type="tel" name="tel03"> 
                 </div>
                 <div class="cont_box">
                    <p>과세/면세</p> 
                    <label for="rara01"><input type="radio" name="rara01" id="rara01">과세</label>
                    <label for="rara02"><input type="radio" name="rara01" id="rara02">면세</label>
                    <label for="rara03"><input type="radio" name="rara01" id="rara03">복합과세</label>
                 </div>
                 <div class="cont_box">
                    <p>발행용도</p> 
                    <label for="rara04"><input type="radio" name="rara02" id="rara04">소득공제용</label>
                    <label for="rara05"><input type="radio" name="rara02" id="rara05">지출증빙용</label>
                 </div>
                 <div class="cont_box">
                    <p>인증종류</p> 
                    <label for="rara06"><input type="radio" name="rara03" id="rara06">사업자번호</label>
                    <label for="rara07"><input type="radio" name="rara03" id="rara07">
                        휴대폰번호 <span>사업자 번호</span> <input type="tel" name="tel04" id="">- <input type="tel" name="tel05" id=""> - <input type="tel" name="tel06" id="">
                    </label>
                 </div>
                 
                      <div class="cont_box">
                     <p>상품명</p>
                     <input type="text">
                    </div>
                    <div class="cont_box">
                        <p>이메일</p>
                        <input type="text">
                    </div>
                    <div class="cont_box">
                        <p>신청금액</p>
                        <span>발행액 :</span>  <input type="text"> <br>
                        <span>공급액 :</span>  <input type="text"><br>
                        <span>부가세 :</span>  <input type="text"><br>
                        <span>면세 :</span>  <input type="text" class="last">
                    </div>
                 
                
            </div>
            <div class="text">
                <p>관리자 메모</p> <textarea name="" id="" cols="30" rows="10"></textarea>

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
                document.getElementById('content01').setAttribute("class","on");
                document.getElementById('content02').setAttribute("class","content01");
            }else if(event.target.value == 2){
                document.getElementById('content01').setAttribute("class","content01");
                document.getElementById('content02').setAttribute("class","on");
            }
        }



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