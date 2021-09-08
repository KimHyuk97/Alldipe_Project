<%@page import="dto.scmDTO.scmDTO"%>
<%@page import="dto.categoryDTO.categoryDTO"%>
<%@page import="dto.goodsDTO.goodsOptionDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.goodsDTO.goodsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/view_product.css">
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/partners/js/category.js"></script>
<script src="${pageContext.request.contextPath}/partners/js/update.js"></script>

<%
	scmDTO scm = (scmDTO)session.getAttribute("scm");
	ArrayList<goodsDTO> gList = (ArrayList)session.getAttribute("gList");
	ArrayList<categoryDTO> cateList = (ArrayList)session.getAttribute("cateList");
	session.removeAttribute("cateList");
	session.removeAttribute("gList");
	
%>
	<div id="sales_term_background">
		<div id="sales_term_div">
			<div class="option_change_div">
				<h3 class="option_change_title">판매기간 변경</h3>
				<div class="change_div">
					<span>판매시작</span>
					<input type="date" class="change_input" title="달력" id="search_start_date" name="startDate">
				</div>
				<div class="change_div">
					<span>판매종료</span>
					<input type="date" class="change_input" title="달력" id="search_end_date" name="endDate">
				</div>
			</div>
			<div class="option_btn_div">
				<button onclick="">확인</button>
				<button onclick="closeDiv('sales_term_background')">닫기</button>
			</div>
		</div>
	</div>
	<div id="brand_background">
		<div id="brand_div">
			<div class="option_change_div">
				<h3 class="option_change_title">상품 정보 변경</h3>
				<div class="change_div">
					<span>변경 항목</span>
					<select class="change_input" name="change_option" id="brandMaker" onchange="brandMaker()">
						<option value="brand" selected="selected">브랜드</option>
						<option value="makerNm">제조사</option>
					</select>
				</div>
				<div class="change_div" id="brand_search_div">
					<span>브랜드 검색</span>
					<input type="text" class="change_input" id="search_brand" name="brand">
					<div class=""></div>
					<input type="hidden" name="brandCd">
				</div>
				<div class="change_div" id="maker_input_div">
					<span>제조사 입력</span>
					<input type="text" class="change_input" name="makerNm">
				</div>
			</div>
			<div class="option_btn_div">
				<button onclick="">확인</button>
				<button onclick="closeDiv('brand_background')">닫기</button>
			</div>
		</div>
	</div>
	
	<div id="memberOnly_background">
		<div id="memberOnly_div">
			<div class="option_change_div">
				<h3 class="option_change_title">노출여부 변경</h3>
				<label for="member_only"><div class="change_div memberOnly_css"><input type="radio" class="memberOnly" id="member_only" name="memberOnly" value="1" onchange="choiceDisplayFl('memberOnly','memberOnly_css')">회원에게만 공개</div></label>
				<label for="everyone"><div class="change_div memberOnly_css"><input type="radio" class="memberOnly" id="everyone" name="memberOnly" value="0" onchange="choiceDisplayFl('memberOnly','memberOnly_css')">모두에게 공개</div></label>
				<div class="change_div"></div>
			</div>
			<div class="option_btn_div">
				<button onclick="optionChange('memberOnly')">확인</button>
				<button onclick="closeDiv('memberOnly_background')">닫기</button>
			</div>
		</div>
	</div>
	<div id="adultFl_background">
		<div id="adultFl_div">
			<div class="option_change_div">
				<h3 class="option_change_title">19금 제한 변경</h3>
				<label for="adultOnly"><div class="change_div adultFl_css"><input type="radio" class="adultFl" id="adultOnly" name="onlyAdultFl" value="1" onchange="choiceDisplayFl('adultFl','adultFl_css')">성인 회원 공개</div></label>
				<label for="adultFree"><div class="change_div adultFl_css"><input type="radio" class="adultFl" id="adultFree" name="onlyAdultFl" value="0" onchange="choiceDisplayFl('adultFl','adultFl_css')">모두에게 공개</div></label>
				<div class="change_div"></div>
			</div>
			<div class="option_btn_div">
				<button onclick="optionChange('adultFl')">확인</button>
				<button onclick="closeDiv('adultFl_background')">닫기</button>
			</div>
		</div>
	</div>
	<div id="saleState_background">
		<div id="saleState_div">
			<div class="option_change_div">
				<h3 class="option_change_title">판매 상태 변경</h3>
				<label for="saleStart"><div class="change_div saleState_css"><input type="radio" class="saleState" id="saleStart" name="saleState" value="1" onchange="choiceDisplayFl('saleState','saleState_css')">판매중</div></label>
				<label for="saleWait"><div class="change_div saleState_css"><input type="radio" class="saleState" id="saleWait" name="saleState" value="0" onchange="choiceDisplayFl('saleState','saleState_css')">판매 대기</div></label>
				<div class="change_div"></div>
			</div>
			<div class="option_btn_div">
				<button onclick="optionChange('saleState')">확인</button>
				<button onclick="closeDiv('saleState_background')">닫기</button>
			</div>
		</div>
	</div>
	<div class="cont_wrap">
		<input type="hidden" id="scmNo" value=<%=(scm!=null)?scm.getScmNo():"" %>>
		<div id="wrap">
	        <div class="title">
	            상품 조회/수정
	            <div class="top_btn">
	                <button type="submit" onclick="getSearch()">검색</button>
	                <button type="submit">초기화</button>
	            </div>
	        </div>
	        <div class="top_wrap">
	            <div class="top_cont">
	                <div class="tit">조회기간</div>
	                <select id="dateCate" name="dateCate" >
	                    <option value="regDt" selected="selected" >상품등록일</option>
	                    <option value="modDt">상품수정일</option>
	                    <option value="salesStartDt">판매시작일</option>
	                    <option value="salesEndDt">판매종료일</option>
	                </select>
	                <input type="date" class="date_input" title="달력" id="search_start_date" name="startDate"> ~
	                <input type="date" class="date_input" title="달력" id="search_end_date" name="endDate">
	                <ul class="date_btn">
	                    <li><button class="blue" onclick="">오늘</button> </li>
	                    <li><button class="blue" onclick="">어제</button> </li>
	                    <li><button class="blue" onclick="">1주일전</button> </li>
	                    <li><button class="blue" onclick="">1개월전</button> </li>
	                    <li><button class="blue" onclick="">3개월전</button> </li>
	                    <li><button class="blue" onclick="">6개월전</button> </li>
	                </ul>
	            </div>
	            <div class="top_cont">
	                <div class="tit">카테고리</div>
	                <select class="cate_box" id="cate1" onchange="cate2Action();">
	                    <option value="">대분류</option>
	                </select>
	                <select class="cate_box" id="cate2" onchange="cate3Action();">
	                    <option value="">중분류</option>
	                </select>
	                <select class="cate_box" id="cate3">
	                    <option value="">소분류</option>
	                </select>
	                <input type="hidden" id="cateCd">
	            </div>
	            <div class="top_cont">
	                <div class="tit">판매상태</div>
	            	<div class="check_div">
	            		<input class="check_item" id="stateAll" type="checkbox" onclick="stateAll()"><span class="check_name">전체 선택</span>
            		</div>
            		<div class="check_div">
	            		<input class="check_item state" type="checkbox" name="state" value="apply"><span class="check_name">판매 신청중</span>
            		</div>
            		<div class="check_div">
	            		<input class="check_item state" type="checkbox" name="state" value="wait"><span class="check_name">대기중</span>
            		</div>
            		<div class="check_div">
	            		<input class="check_item state" type="checkbox" name="state" value="saleOff"><span class="check_name">판매 중지</span>
            		</div>
            		<div class="check_div">
	            		<input class="check_item state" type="checkbox" name="state" value="adminOff"><span class="check_name">관리자 판매중지</span>
            		</div>  
	            </div>
	            <div class="top_cont">
	                <div class="tit">판매상태</div>
	                <div class="check_div">
	            		<input class="check_item" type="checkbox" name="soldout" id="saleState" value="soldOut"><span class="check_name">품절</span>
            		</div>    
	            </div>
	            <div class="top_cont">
	                <div class="tit">검색어</div>
	                <select name="keywordType" id="keywordType">
	                    <option value="goodsNm">상품명</option>
	                    <option value="goodsNo">상품번호</option>
	                    <option value="scmNo">업체코드</option>
	                    <option value="brandNm">브랜드명</option>
	                    <option value="makerNm">제조사명</option>
	                </select>
	                <input type="text" name="keyword" id="keyword" placeholder="검색어를 입력하세요">
	            </div>
<!-- 	            <div class="top_cont"> -->
<!-- 	                <div class="tit">노출여부</div> -->
<!-- 	                <select name="displayFl" > -->
<!-- 	                    <option value="true">노출</option> -->
<!-- 	                    <option value="false">비노출-상세접근가능</option>   -->
<!-- 	                </select> -->
<!-- 	                <div class="tit tit02"> -->
<!-- 	                    <p>딜에서만 <br> 노출여부</p>  -->
<!-- 	                </div> -->
<!-- 	                <select name="displayDeal" > -->
<!-- 	                    <option value="true">노출</option> -->
<!-- 	                    <option value="false">비노출</option>   -->
<!-- 	                </select> -->
<!-- 	                <div class="tit tit03"> -->
<!-- 	                    <p>제휴채널 <br> 신청여부</p>  -->
<!-- 	                </div> -->
<!-- 	                <select name="제휴채널신청" > -->
<!-- 	                    <option value="true">신청</option> -->
<!-- 	                    <option value="false">신청안함</option>   -->
<!-- 	                </select> -->
<!-- 	            </div> -->
<!-- 	            <div class="top_cont"> -->
<!-- 	                <div class="tit">배송유형</div> -->
<!-- 	                <select name="deliveryType" > -->
<!-- 	                    <option value="상품별배송">상품별배송</option> -->
<!-- 	                    <option value="묶음배송">묶음배송(판매자별)</option> -->
<!-- 	                </select> -->
<!-- 	                <div class="tit tit02">배송비 종류</div> -->
<!-- 	                <select name="deliveryKind" > -->
<!-- 	                    <option value="전체">전체</option> -->
<!-- 	                    <option value="무료">무료</option> -->
<!-- 	                    <option value="유료">유료</option>   -->
<!-- 	                    <option value="조건부무료">조건부 무료</option>  -->
<!-- 	                </select> -->
<!-- 	                <div class="tit tit03">배송방법</div> -->
<!-- 	                <select name=deliveryWay > -->
<!-- 	                    <option value="전체">전체</option> -->
<!-- 	                    <option value="일반배송">일반 배송</option> -->
<!-- 	                </select> -->
<!-- 	            </div> -->
<!-- 	            <div class="top_cont"> -->
<!-- 	                <div class="tit">판매유형</div> -->
<!-- 	                <select name="판매유형" > -->
<!-- 	                    <option value="전체">전체</option>  -->
<!-- 	                </select> -->
<!-- 	                <div class="tit tit02">당일배송 여부</div> -->
<!-- 	                <select name="당일배송여부" class="select02" > -->
<!-- 	                    <option value="당일배송">출고기한 1일이내 (당일배송)</option> -->
<!-- 	                    <option value="출고1일이상">출고기한 1일이상</option> -->
<!-- 	                </select> -->
<!-- 	            </div> -->
	        </div>
	        <div class="bottom_wrap">
	            <div class="btm_tit">
	                검색결과 <p><span class="color_span" id="list_cnt">0</span>건</p>
	                <button class="btn01"><i class="fas fa-file-excel"></i> 엑셀 다운로드</button>
	            </div>
	            <div class="main">
	                <div id="main_table">
	                	<div class="list_col1"><input type="checkbox" id="selectAll" onchange="selectAll()"></div>
	                	<div class="list_col2">상품번호</div>
	                    <div class="list_col3">판매상태</div>
	                    <div class="list_col4">상품명</div>
	                    <div class="list_col5">평점</div>
	                    <div class="list_col6">좋아요</div>
	                    <div class="list_col7">판매량</div>
	                    <div class="list_col8">재고</div>
	                    <div class="list_col9">할인정보</div>
	                    <div class="list_col10">등록일</div>
	                    <div class="list_col11"></div>
	                </div>   
	            </div>
	            <div class="goodsList">
	            	<div id="list_table">
		            	<div class="goodsList_tr">
		            		<span>검색된 상품이 없습니다.</span>
			            </div>
	            	</div>
	            </div>
	            <div class="btm_tit">
	                선택상품 일괄변경 (총<span class="color_span" id="selectCnt">0</span>건)
	            </div>
	            <div class="btm_button">
	                <div class="btm_tit">기본정보</div>
	                    <div class="btm_wrap btm_wrap01">
	                        <button class="blue" onclick="openDiv('sales_term_background')">판매기간 변경</button>
	                        <button class="blue" onclick="openDiv('memberOnly_background')">노출여부 변경</button>
	                        <button class="blue" onclick="openDiv('brand_background')">브랜드/제조사 변경</button>
	                    </div>
	                
	            </div>
	            <div class="btm_button">
	                <div class="btm_tit">  배송정보</div>
	                    <div class="btm_wrap btm_wrap02">
	                        <button class="blue">배송정보 변경</button>
	                        <button class="blue">반품/교환배송비 변경</button>
	                        <button class="blue">출고지/회수지주소 변경</button>
	                        <button class="blue">출고기한 변경</button>
	                    </div>
	            </div>
	            <div class="btm_button">
	                <div class="btm_tit">부가정보</div>
	                    <div class="btm_wrap btm_wrap03">
	                        <button class="blue" onclick="openDiv('adultFl_background')">19금제한 변경</button>
	                    </div>
	                
	            </div>
	            <div class="btm_button">
	                <div class="btm_tit"> 상품상태</div>
	                    <div class="btm_wrap btm_wrap04">
	                        <button class="blue" onclick="openDiv('saleState_background')">판매상태 변경</button>
	                    </div>
	            </div>
	        </div>
	    </div>
	    <jsp:include page="../footer.jsp"/>
	</div>
	<script>
		document.getElementById("search_end_date").value = new Date().toISOString().substring(0,10);
		
		window.onload = function(){
			cate1Action();
			endLoading();
		}
	</script>
</body>
</html>