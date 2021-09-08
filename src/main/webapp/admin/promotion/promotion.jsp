<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/promotion/total_promotion.css">
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
 <div class="cont_wrap">
        <div class="top_tit">
            <h1>프로모션 등록/수정</h1>
        </div>
        <div class="search_cont">
            <div class="sch_tit">
              <h2>프로모션 등록</h2> 
            </div> 
            <div class="sch_ck_cont">
                <div class="sch_check">
                    <p>프로모션 유형</p>
                    <label for="ck00" id="la00"><input type="radio" name="radio00" id="ck00">이벤트</label>
                    <select name="" id="">
                        <option value="">기획전</option>
                    </select>
                    <label for="ck00_1" id="la00"><input type="radio" name="radio00" id="ck00_1">MD쿠폰</label>
                </div>
                <div class="sch_check">
                    <p>쿠폰유형</p>
                    <label for="ck01" id="la01"><input type="radio" name="radio01" id="ck01">상품적용</label>
                    <label for="ck02" id="la01"><input type="radio" name="radio01" id="ck02">주문적용</label>
                    <label for="ck03" id="la01"><input type="radio" name="radio01" id="ck03">배송비적용</label>
                </div>
                <div class="sch_check">
                    <p>발급방식</p>
                    <label for="ck04" id="la02"><input type="radio" name="radio02" id="ck04">수동발급</label>
                    <label for="ck05" id="la02"><input type="radio" name="radio02" id="ck05">자동발급</label>
                    <label for="ck06" id="la02"><input type="radio" name="radio02" id="ck06">회원다운로드</label>
                </div>
                <div class="sch_check">
                    <p>쿠폰사용설정</p>
                    <label for="ck07" id="la03"><input type="radio" name="radio03" id="ck07">기획전</label>
                    <label for="ck08" id="la03"><input type="radio" name="radio03" id="ck08">공급사</label>
                    <label for="ck09" id="la03"><input type="radio" name="radio03" id="ck09">카테고리</label>
                    <label for="ck10" id="la03"><input type="radio" name="radio03" id="ck10">특정상품</label>
                    <select name="" id="">
                        <option value="">공급사선택</option>
                        <option value="">카테고리선택</option>
                        <option value="">상품선택</option>
                    </select>
                </div>
                <div class="sch_check">
                    <p>쿠폰명</p>
                   <input type="text" placeholder="검색어를 입력해주세요.">
                </div>
                <div class="sch_check" id="srch_area">
                    <p>사용기간</p>
                    <label for="ck11" id="la04"><input type="radio" name="radio04" id="ck11">직접입력</label>
                    <input type='date' id="search_start_date"> ~ <input type='date' id="search_end_date">
                    
                    <br>
                    <label for="ck12" class="la04_2" id="la04"><input type="radio" name="radio04" id="ck12">쿠폰 발급일로부터 <input type="number" name="" id="">일까지 사용가능 </label>

                 </div>
                <div class="sch_check">
                    <p>지급혜택</p>
                    <label for="ck13" id="la05"><input type="radio" name="radio05" id="ck13">상품할인</label>
                    <label for="ck14" id="la05"><input type="radio" name="radio05" id="ck14">마일리지적립 <input type="number" name="" id="">% 할인 (구매금액 기준) </label>

                </div>
            </div>     
        </div>
        <div class="btn">
            <button>초기화</button>
            <button>등록/수정</button>
        </div>
    </div>
<!--하단-->
<jsp:include page="../adminFooter.jsp" flush="false"/>