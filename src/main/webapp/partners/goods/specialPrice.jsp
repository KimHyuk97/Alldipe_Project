<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/special_price.css">
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
	<div class="cont_wrap">
	<div class="wrap">
		<div class="top_tit">
            특가 참여 신청
        </div>
        <div class="top_txt">
            <div class="top_txt_tit">
                파트너사 특가 신청 프로세스
                <a href="">특가 신청 메뉴얼</a>
            </div>
            <div class="top_txt_cont">
                * 특가 신청 마감 > 1차 특가 선정 여부 알림 > 파트너사 특가 세팅 마감 > 최종 특가 확정 여부 알림 > 특가 오픈 <br>
                * 특가 신청 가능일은 활성화된 일이후만 신청 가능합니다.(참여 가능한 일자는 아래 특가 참여일 메뉴에서 선택 가능하도록 활성화 되어있습니다.) <br>
                * 관련하여 문의 사항이 있는 경우, 위메프특가제안 (wmp_special_apply@wemakeprice.com)을 통하여 문의하여 주시기 바랍니다. <br> (신청 건 관련 사항은 신청번호 혹은 딜/상품번호, 파트너사ID와 함께 문의 부탁드립니다.)
            </div>
            </div>
        <div class="title">
            특가 참여 상품/딜 선택
            <div class="top_btn">
                <button type="submit">검색</button>
                <button type="submit">초기화</button>
            </div>
        </div>
        <div class="top_wrap">
            <div class='top_cont srch_area'>
                <div class="tit">카테고리</div>
                <select name="category" >
                    <option value="specialPrice">투데이특가</option>
                </select>
            </div>
            <div class="top_cont">
                <div class="tit">조회기간</div>
                <input type="date" name="cate">
                <button class="check_btn">일정확인하기</button>
            </div>
             <div class="top_cont">
                <div class="tit">상품상태</div>
                <button>상품 선택하기</button>
                <button>딜 선택하기</button>
                <button>복사하기</button>
            </div>
        </div>
        <div class="bottom_wrap">
            <div class="btm_tit">
                상품/딜 수 <p>0건</p>
                <div class="btm_tit_put">
                  <select name="검색" >
                    <option value="상품번호">상품번호</option>
                    <option value="상품명">상품명</option>
                    <option value="딜번호">딜번호</option>
                    <option value="딜명">딜명</option>
                  </select>
                  <input type="text" placeholder="">  
                </div>
                <button><i class="far fa-file-excel"></i> 엑셀 다운로드</button>
            </div>
            
            <div id="main">
	            	<div id="main_table">
	                    <div class="list_col1"><input type="checkbox" id="selectAll" onclick="selectItem()"></div>
	                    <div class="list_col2">No</div>
	                    <div class="list_col3">대표이미지</div>
	                    <div class="list_col4">딜 타이틀</div>
	                    <div class="list_col5">대표상품명</div>
	                    <div class="list_col6">대표판매가</div>
	                    <div class="list_col7">특가참여가</div>
	                    <div class="list_col8">상품수수료</div>
	                </div>   
	            </div>
	            <div class="orderList">
	            	<div id="list_table">
		            	<div class="orderList_tr">
		            		<span>주문 내역이 없습니다.</span>
			            </div>
	            	</div>
	            </div>
            
            <table class="main">
                <tr>
                    <td><div class="box"></div></td>
                    <td>상품/딜 번호</td>
                    <td>상품/딜 대표이미지</td>
                    <td>상품/딜 명</td>
                    <td>대표상품번호</td>
                    <td>대표상품명</td>
                    <td>대표판매가</td>
                    <td>특가참여가</td>
                    <td>상품수수료</td>
                </tr>   
            </table>
            <table class="cont">
                <tr>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td>
                    </td>
                </tr>
            </table>
            <div class="del_btn">
              <button>선택삭제</button> 
              <div class="btm_tit">선택한 상품/딜 <p>0건</p> </div>
            </div>
            <div class="bottom_txt">
                * 4월 19일(월요일)부터 투데이 특가 참여시 최대 9.9%(VAT포함) 수수료로 적용됩니다. <br>
                * 딜 내 구성 상품의수수료는 오픈 당일 00시 일괄 변경 작업이 진행됩니다. <br>
                * 투데이특가는 상품 단일 구매 시에도 무료배송으로만 진행이 가능합니다. <br>
                * 당일/익일 발송 상품/딜로만 특가 진행이 가능합니다. <br>
                * 신청이 완료된 후에는 진행 딜/상품번호 변경이 불가합니다.
            </div>
            <div class="agree_box">
                <input type="checkbox" name="동의" class="agree"> 
                특가 참여 조건에 동의합니다.
               <div class="btm_btn">
                <button>신청하기</button>
                </div> 
            </div>
            <div class="title last_tit">
                    특가 참여 상품/딜 선택
                    <div class="top_btn">
                        <button type="submit">검색</button>
                        <button type="submit">초기화</button>
                    </div>
                </div>
            <div class="top_wrap top_wrap_last">
                <div class='top_cont srch_area'>
                    <div class="tit">조회기간</div>
                    <select name="조회기간" >
                        <option value="특가참여일">특가참여일</option>
                        <option value="신청일">신청일</option>
                        <option value="수정일">수정일</option>
                    </select>
                    <input type="date" title="달력" id="search_start_date" > ~
                    <input type="date" title="달력" id="search_end_date" >
                    <ul class="btm_last">
                        <li><button>어제</button> </li>
                        <li><button>오늘</button> </li>
                        <li><button>1주일전</button> </li>
                        <li><button>1개월전</button> </li>
                        <li><button>3개월전</button> </li>
                        <li><button>6개월전</button> </li>
                    </ul>
                </div>
                
                <div class="top_cont">
                    <div class="tit">진행상태</div>
                    <select name="진행상태" >
                        <option value="전체">전체</option>
                        <option value="신청">신청</option>
                        <option value="검토중">검토중</option>
                        <option value="확정완료">확정완료</option>
                        <option value="반려">반려</option>
                        <option value="신청취소">신청취소</option>
                    </select>
                </div>
                 <div class="top_cont btm_last_input">
                    <div class="tit tit03">검색어</div>
                    <select name="검색어" >
                        <option value="상품번호">상품번호</option>
                        <option value="상품명">상품명</option>
                        <option value="딜번호">딜번호</option>
                        <option value="딜명">딜명</option>
                        <option value="신청번호">신청번호</option>
                    </select>
                    <input type="text">
                </div>
            </div>
            <div class="btm_tit btm_last_tit">
                검색결과 <p>0건</p>
                <button><i class="far fa-file-excel"></i> 엑셀 다운로드</button>

            </div>
            <table class="main">
                <tr>
                    <td><div class="box"></div></td>
                    <td>상품/딜 번호</td>
                    <td>상품/딜 대표이미지</td>
                    <td>상품/딜 명</td>
                    <td>대표상품번호</td>
                    <td>대표상품명</td>
                    <td>대표판매가</td>
                    <td>특가참여가</td>
                    <td>상품수수료</td>
                </tr>   
            </table>
            <table class="cont">
                <tr>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td>
                    </td>
                </tr>
            </table>
            <div class="del_btn">
              <button>신청취소</button> 
              <div class="btm_tit">선택한 신청 수 <p>0건</p> </div>
            </div>
        </div>
	</div>
	</div>
	<script>
		window.onload=function(){
			endLoading();
		}
	</script>
</body>
</html>