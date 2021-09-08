<%@page import="dto.scmDTO.scmDTO"%>
<%@page import="dto.goodsDTO.goodsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	goodsDTO goods = (goodsDTO)request.getAttribute("goods");
	scmDTO scm = (scmDTO)request.getAttribute("scm");
%>
<!-- Swiper CSS-->
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" /> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/goods/goods.css">

<script type="text/javascript" src="http://jsgetip.appspot.com"></script>
<!-- <!-- Swiper JS -->
<script src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery-ui.min.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

<!--상단-->
<jsp:include page="../header.jsp" flush="false" />

<input type="hidden" id="memNo" value="${sessionScope.mem.memNo}">


<div class="inner">
    <ul class="cate_nav">
        <li><a href="#">카테고리1-1</a></li>
        <li><a href="#">카테고리1-2</a></li>
        <li><a href="#">카테고리1-3</a></li>
    </ul>
    <form action="order" method="post" name="orderGoods">
	<input type="hidden" name="scmNo" id="scmNo" value="<%=goods.getScmNo()%>"> <!-- 공급사 번호 -->
	<input type="hidden" name="brandCd" value="<%=goods.getBrandCd()%>"> <!-- 브랜드 코드 -->
	<input type="hidden" name="goodsNo" id="goodsNo" value="<%=goods.getGoodsNo()%>">
	<input type="hidden" name="goodsNm" id="goodsNm" value="<%=goods.getGoodsNm()%>"> <!-- 상품명 -->
	<input type="hidden" name="makerNm" id="makerNm" value="<%=goods.getMakerNm()%>"> <!-- 제조사 -->
	<input type="hidden" name="fixedPrice" id="fixedPrice" value="<%=goods.getFixedPrice()%>"> <!-- 판매가격 -->
	<input type="hidden" name="representImg" id="representImg" value="<%=goods.getRepresentImg()%>"> <!-- 대표이미지 -->
	<input type="hidden" name="goodsPage" value="goods"> <!-- 페이지정보 -->
    <div class="main_box clearfix">
        <div class="img_box">
            <div class="img_wrap">
                <div class="top_img">
                    <div class="swiper-container gallery-top">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide">
                            	<img src="${pageContext.request.contextPath }/fileF/goods/<%=goods.getRepresentImg()%>" alt="대표이미지">
                           	</div>
                        </div>
                    </div>
                </div>
                <div class="thums_img">
                    <div class="swiper-container gallery-thumbs">
                        <div class="swiper-wrapper">
                        	<div class="swiper-slide thumbs thumbs_01">${goods.representImg}</div>
                            <div class="swiper-slide thumbs thumbs_02">${goods.subImg}</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="store_wrap">
                <span class="store_name">스토어이름</span><a href="#" class="store_more">상품 더보기</a>
            </div>
        </div>
        <div class="info_wrap">
            <div class="title_box">
                ${goods.goodsNm}
            </div>
            <div class="price_box">
                <span class="before_price" id="goodsPrice22"><fmt:formatNumber value="${goods.goodsPrice}" type="number"/></span>
                <span class="dc" id="gdPercent"><fmt:formatNumber value="${goods.discountPercent}" type="percent"/></span>
                <p class="price" id="fixedPrice22"><fmt:formatNumber value="${goods.fixedPrice }" type="number"/></p>
                
            </div>
            <div class="coupon_box">
                쿠폰<a href="#" class="coupon_btn">쿠폰다운받기</a>
            </div>
            <div class="shop_info">
                <div class="shop_info_box">
                    <span>혜택</span>
                    <ul>
                        <li>최대 7개월 무이자</li>
                        <li>출석체크하고 포인트 받자!</li>
                        <li>신규회원 최대 500포인트 제공</li>
                        <li>최대 10% 적립가능</li>
                    </ul>
                </div>
                <div class="shop_info_box">
                    <span>배송비</span>
                    <ul>
                    	<li class="d_price"><fmt:formatNumber value="${goods.deliveryCost}" type="number"/></li>            		
                    </ul>
                </div>
                <div class="shop_info_box">
                    <span>구분</span>
                    <ul>
                        <li>택배</li>
                    </ul>
                </div>
            </div>
            
            <!-- 옵션관련 -->
            <div class="option_wrap">
                <div class="option_box clearfix">
                    <span>옵션선택</span>
                    <span>&or;</span>
                </div>
                <div class="op_list_wrap" id="op_list_wrap">
                    <ul>
                    	<c:forEach var="option" items="${option}" varStatus="status">
	                        <li class="op_list">
	                        	<div><input type="button" class="optionBtnStyle" onclick="option(${status.index},${goods.totalStock},${option.sno},'${option.optionNm1}${option.optionValue1 }','${option.optionNm2}${option.optionValue2 }','${option.optionNm3}${option.optionValue3 }','${option.optionNm4}${option.optionValue4 }','${option.optionNm5}${option.optionValue5 }',${option.optionFixedPrice},${goods.fixedPrice},${goods.goodsPrice})" value="${option.optionNm1}-${option.optionValue1} ${option.optionNm2}-${option.optionValue2} ${option.optionNm3}${option.optionValue3} ${option.optionNm4}${option.optionValue4} ${option.optionNm5}${option.optionValue5}"></div>
	                        	<div>+<fmt:formatNumber value="${option.optionFixedPrice}" type="number"/>원</div>																						
	                        </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="add_option" id="optionVal" >           	
           		<div class="option_Content" id="optionVal"></div>
            </div>
            
            <!-- 총가격 -->
            <div id="total_price" class="total_price_wrap clearfix">
                총 합계 금액<span class="total_price" id="goods_total_price"></span>
            </div>
            <div class="btn_wrap">
		        <input type="button" id="cartBtn" value="장바구니">
                <c:choose>
                	<c:when test="${empty sessionScope.mem.memNo}">
                		<input type="button" id="loginBtn" value="결제하기">
                	</c:when>
                	<c:otherwise>
                		<input type="button" id="buyBtn" value="결제하기">	
                	</c:otherwise>
                </c:choose>
            </div>
            <div class="info_box_ad"></div>
        </div>
    </div>
    </form>
        
    <div class="cont_nav_wrap">
        <ul class="cont_nav clearfix">
            <li class="cont_list"><a href="#detail_sec">상품 상세 정보</a></li>
            <li class="cont_list"><a href="#review_sec">상품후기</a></li>
            <li class="cont_list"><a href="#question_sec">상품문의</a></li>
            <li class="cont_list"><a href="#delivery_sec">배송 교환 관련</a></li>
        </ul>
    </div>
    <div>
	    <div id="detail_sec" class="section">
	    	${goods.goodsDescription }
	    </div>
	    <div id="review_sec" class="section">
	        <div class="review_top clearfix">
	            <div class="total_grade">
	                <p>평균평</p>
	                <c:choose>
	                	<c:when test="${!empty review}">
	                		<c:forEach var="rv" items="${review}" varStatus="status">  
	                			<input type="hidden" id="reviewPt${status.index}" value="${rv.goodsPt}">
	                		</c:forEach>
                			<p class="grade_num" id="reviewPtVal"></p>
                			<span id="grade_icon"></span>
	                		<script>
	                			let ppInt = parseInt(0);
	                			const rLength = ${fn:length(review)};
	                			for(let z = 0; z < rLength; z++){	                				
		                			ppInt += parseInt(document.getElementById('reviewPt'+z).value);
	                			}
	                			const pt = ppInt / rLength;
	                			if(pt > 0){	                				
		                			document.getElementById('reviewPtVal').innerHTML = pt.toFixed(1);
	               					document.getElementById('grade_icon').style.backgroundImage = "url(fileF/review/star"+pt.toFixed(0)+".png)";	
	               					document.getElementById('grade_icon').setAttribute('class','reviewImgOn');
	                			}
	                		</script>
	                	</c:when>
	                	<c:otherwise>
	                		<p class="grade_num">0.0</p>
	                		<span class="grade_iconZero"></span>
	                	</c:otherwise>
	                </c:choose>
	            </div>
	            <div class="review_potho_all clearfix">
	                <c:if test="${!empty review}"> 
		                <c:forEach var="pr" items="${review}" varStatus="status">
		                	<input type="hidden" id="prGoodsNo" value="${pr.goodsNo}">
		                	<c:if test="${status.index < 7}">
		                		<c:choose>
		                			<c:when test="${status.last == true}">
		                				<span onclick="reviewGallery()" class="p_review_list lastImg">${pr.reviewImg}<span class="lastImg2">${fn:length(review)}<br/>더보기+</span></span>	
		                			</c:when>
		                			<c:otherwise>
		                				<span onclick="reviewGallery()" class="p_review_list">${pr.reviewImg}</span>	
		                			</c:otherwise> 
		                		</c:choose>
		                	</c:if>
		                </c:forEach>
		                <div id="primgList">
		                	<div class="primgListContent">
			                	<input type="button" value="X" id="primgListClose">
			                	<div>
			                		<div class="primgListTitle2"><h1>리뷰</h1></div>
					                <div id="prImgListMain2">
					                	<c:forEach var="primgList1" items="${review}" varStatus="status1">
						                	<div id="reviewView${status1.index}" style="display:none;">
					                			<div class="review_list clearfix">
										            <span class="user_profile"></span>
										            <p class="user_id_text">${primgList1.writer}</p>
										            <div class="user_review_data">
										                <span class="user_grade_icon${primgList1.goodsPt}"></span>
										                <span class="user_grade">${primgList1.goodsPt}</span>
										                <span class="review_date"><fmt:formatDate value="${primgList1.regDt}" pattern="yyyy-MM-dd HH:mm"/></span>
										                <span class=""><a href="#">신고</a></span>
										            </div>
										            <div class="review_text">
										                <p>${primgList1.contents}</p>
										            </div>
										        </div>
							                	<input type="button" value="닫기" onclick="primgListClose2(${status1.index})" class="primgListClose2">
					                		</div>
				                		</c:forEach>
					                </div>
			                	</div>
		                	</div>
		                </div>
		            </c:if>
	            </div>
	        </div>
	        
	        <c:if test="${!empty review}"> 
		        <c:forEach var="rv" items="${review}" varStatus="status">        
			        <div class="review_list clearfix">
			            <span class="user_profile"></span>
			            <p class="user_id_text">${rv.writer}</p>
			            <div class="user_review_data">
			                <span class="user_grade_icon${rv.goodsPt}"></span>
			                <span class="user_grade">${rv.goodsPt}</span>
			                <span class="review_date"><fmt:formatDate value="${rv.regDt}" pattern="yyyy-MM-dd HH:mm"/></span>
			                <span class=""><a href="#">신고</a></span>
			            </div>
			            <div class="review_text">
			                <p>${rv.contents}</p>
			            </div>
			        </div>
		        </c:forEach>
	        </c:if>
	
	    </div>
	    <div id="question_sec" class="section clearfix">
	        <div class="table_wrap">
	            <table class="question_table">
	                <thead>
	                    <tr>
	                        <th class="num">No.</th>
	                        <th class="type">문의유형</th>
	                        <th class="title">문의/답변</th>
	                        <th class="user">작성자</th>
	                        <th class="date">작성일</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<c:if test="${!empty qa}">
		                	<c:forEach var="qa" items="${qa}" varStatus="status">                		
			                    <tr>
			                        <td class="num">${qa.sno}</td>
			                        <td class="type">${qa.theme}</td>
			                        <td class="title">
	                        			<c:choose>
			                                <c:when test="${qa.secret}">
			                        			<div>
				                        			<button id="secretContent${status.index}" onclick="secretPw(${status.index},${qa.memNo},${sessionScope.mem.memNo})">비밀글입니다.&nbsp;<img style="vertical-align: middle;" src="${pageContext.request.contextPath}/fileF/review/lock.png"></button>
				                        			<span id="secretContent2${status.index}" style="position:relative;"></span>
				                        			<button id="stc${status.index}" onclick="showQa(${status.index})"></button>
			                        			</div>
			                        		</c:when>
			                        		<c:otherwise>
			                        			<button onclick="showQa(${status.index})">${qa.title}</button>
			                        		</c:otherwise>
			                        	</c:choose>		                         	
			                        </td>
			                        <td class="user">${qa.writerNm}</td>
			                        <td class="date"><fmt:formatDate value="${qa.regDt}" pattern="yyyy-MM-dd HH:mm"/></td>
			                    </tr>
			                	<tr id="qaContentView${status.index}" style="display:none;">
			                		<td colspan="5" id="qaContentShow${status.index}" style="display:none">
							            <div class="qaAdminTitle">
							            	<span>문의내용 : ${qa.contents}</span>
						            		<c:if test="${qa.replyStatus && qa.answerManagerId != null}">
						            			<div class="qaAdminContent">
							            			<table>
							            				<colgroup>
														    <col width="10%"/>
														    <col width="75%"/>
														    <col width="15%"/>
														</colgroup>
							            				<tr>
							            					<th>관리자</th>
							            					<th>문의답변</th>
							            					<th>작성일</th>
							            				</tr>
							            				<tr>
							            					<td>${qa.answerManagerId}</td>
							            					<td style="text-align: start;">${qa.answerContents}</td>
							            					<td><fmt:formatDate value="${qa.answerModDt}" pattern="yyyy-MM-dd HH:mm"/></td>
							            				</tr>
							            			</table>
						            			</div>
						            		</c:if>
							            </div>
						            </td>
					            </tr>
				            </c:forEach>
	                	</c:if>
	                </tbody>
	            </table>	            
	           	<c:choose>
	               	<c:when test="${empty sessionScope.mem.memNo}">
	               		<input type="button" id="loginBtn2" class="write_btn" value="상품문의하기">
	               	</c:when>
	               	<c:otherwise>
	               		<input type="button" id="qaWrite" class="write_btn" value="상품문의하기">
	               	</c:otherwise>
	            </c:choose>
	            <div id="qaModal">
	         		<div class="qaMode">
	         			 <div class="qaModalTitle"><h1>문의글</h1></div>
	         			 <div class="qaModalContent">
	         			 	<form action="goodsQAWrite" method="post" name="goodsQAWrite">
		         				<table>
		         			 		<tr>
		         			 			<th>판매자</th>
		         			 			<td>
		         			 				<input type="hidden" name="scmNo" value="${scm.scmNo}">
		         			 				<input type="hidden" name="memNo" value="${sessionScope.mem.memNo}">
		         			 				<input type="hidden" name="memNm" value="${sessionScope.mem.memNm}" >
		         			 				<input type="hidden" name="writerId" value="${sessionScope.mem.memId}" >	         			 				
		         			 				<input type="hidden" name="partnersFl" value="n" >	      
		         			 				<input type="hidden" name="writerIp" id="writerIp" >	
		         			 				<input type="hidden" name="goodsNo" value="${goods.goodsNo }">     
		         			 				${scm.companyNm}
		         			 			</td>
		         			 		</tr>
		         			 		<tr>
		         			 			<th>제목</th>
		         			 			<td>
		         			 				<input type="text" id="qaTitle" name="title" placeholder="제목을 입력해주세요.">
		         			 				<input type="checkBox" name="isSecret" id="scFl" onclick="secretFl()">비밀글
		         			 			</td>
		         			 		</tr>
		         			 		<tr id="scPW">
		         			 			<th>비밀번호</th>
		         			 			<td><input type="password" id="qaPw" name="pw" placeholder="비밀글 비밀번호를 입력해주세요."></td>
		         			 		</tr>
		         			 		<tr>
		         			 			<th style="vertical-align: top;">내용</th>
		         			 			<td>
		         			 				<textarea name="contents" id="qaContent" placeholder="문의할 내용을 입력해주세요." rows="5" cols="33"></textarea>
		         			 			</td>
		         			 		</tr>
		         				</table>
		         				<div class="qaBtnContainer">
			         				<input type="button" class="qaBtnStyle" onclick="qaClose()" value="취소">
			         				<input type="button" class="qaBtnStyle" id="qaBtn" value="문의하기">
		         				</div>
	         			 	</form>
	         			 	<p class="qaPr">※ 상품과 관계없는 글, 양도, 광고성, 욕설, 비방, 도배 등의 글은 예고없이 삭제됩니다.<br/><br/>
							※ 개인정보(주민번호, 연락처, 주소, 계좌번호, 카드번호 등)가 포함된다면 비밀글로 작성해 주시기 바랍니다.<br/><br/>
							※ 비밀글 작성 시 비밀번호는 암호화되어 관리자에서도 확인이 불가합니다.
							</p>
	         			 </div>
	         		</div>   	
	            </div>
	        </div>
	    </div>
	    <div id="delivery_sec" class="section">
	        <div class="table_title">배송정보</div>
	        <table class="delivery_info">
	            <colgroup>
	                <col style="width:250px">
	                <col style="width:920px">
	            </colgroup>
	            <tbody>
	                <tr>
	                    <th>교환 및 반품안내</th>
	                    <td>- 상품 택(tag)제거 또는 개봉으로 상품 가치 훼손 시에는 상품수령후 7일 이내라도 교환 및 반품이 불가능합니다.<br><br>
	
	                        - 저단가 상품, 일부 특가 상품은 고객 변심에 의한 교환, 반품은 고객께서 배송비를 부담하셔야 합니다(제품의 하자,배송오류는 제외)<br><br>
	
	                        - 일부 상품은 신모델 출시, 부품가격 변동 등 제조사 사정으로 가격이 변동될 수 있습니다.<br><br>
	
	                        - 신발의 경우, 실외에서 착화하였거나 사용흔적이 있는 경우에는 교환/반품 기간내라도 교환 및 반품이 불가능 합니다.<br><br>
	
	                        - 수제화 중 개별 주문제작상품(굽높이,발볼,사이즈 변경)의 경우에는 제작완료, 인수 후에는 교환/반품기간내라도 교환 및 반품이 불가능 합니다.<br><br>
	
	                        - 수입,명품 제품의 경우, 제품 및 본 상품의 박스 훼손, 분실 등으로 인한 상품 가치 훼손 시 교환 및 반품이 불가능 하오니, 양해 바랍니다.<br><br>
	
	                        - 일부 특가 상품의 경우, 인수 후에는 제품 하자나 오배송의 경우를 제외한 고객님의 단순변심에 의한 교환, 반품이 불가능할 수 있사오니, 각 상품의 상품상세정보를 꼭 참조하십시오.</td>
	                </tr>
	                <tr>
	                    <th>배송안내</th>
	                    <td>- 배송비 : 기본배송료는 2,500원 입니다. (도서,산간,오지 일부지역은 배송비가 추가될 수 있습니다) 50,000원 이상 구매시 무료배송입니다.<br><br>
	
	                        - 본 상품의 평균 배송일은 1~2일입니다.(입금 확인 후) 설치 상품의 경우 다소 늦어질수 있습니다.
	                        [배송예정일은 주문시점(주문순서)에 따른 유동성이 발생하므로 평균 배송일과는 차이가 발생할 수 있습니다.]<br><br>
	
	                        - 본 상품의 배송 가능일은 1~2일 입니다. 배송 가능일이란 본 상품을 주문 하신 고객님들께 상품 배송이 가능한 기간을 의미합니다.
	                        (단, 연휴 및 공휴일은 기간 계산시 제외하며 현금 주문일 경우 입금일 기준 입니다.)</td>
	                </tr>
	                <tr>
	                    <th>환불안내</th>
	                    <td>- 상품 청약철회 가능기간은 상품 수령일로 부터 7일 이내 입니다.</td>
	                </tr>
	                <tr>
	                    <th>A/S 안내</th>
	                    <td>- 소비자분쟁해결 기준(공정거래위원회 고시)에 따라 피해를 보상받을 수 있습니다.<br><br>
	
	                        - A/S는 판매자에게 문의하시기 바랍니다.</td>
	                </tr>
	            </tbody>
	        </table>
	        <div class="table_title">판매자 정보</div>
	        <table class="seller_info">
	            <colgroup>
	                <col style="width:250px">
	                <col style="width:920px">
	            </colgroup>
	            <tbody>
	            	<c:if test="${scm.scmNo > 0}">
		                <tr>
		                    <th>업체명</th>
		                    <td>${scm.companyNm}</td>
		                </tr>
		                <tr>
		                    <th>상호 / 대표자</th>
		                    <td>${scm.ceoNm}</td>
		                </tr>
		                <tr>
		                    <th>연락처</th>
		                    <td>${scm.phone}</td>
		                </tr>
		                <tr>
		                    <th>이메일</th>
		                    <td>${scm.email}</td>
		                </tr>
		                <tr>
		                    <th>주소</th>
		                    <td>${scm.address} &nbsp; ${scm.addressSub}</td>
		                </tr>
		                <tr>
		                    <th>사업자번호</th>
		                    <td>${scm.businessNo}</td>
		                </tr>
		                <tr>
		                    <th>통신판매업 신고번호</th>
		                    <td>${scm.onlineOrderSerial}</td>
		                </tr>
	            	</c:if>
	            </tbody>
	        </table>
	    </div>
    </div>
</div>
<script>
	window.onload = function(){
		endLoading();
	};
</script>

<!--하단-->
<jsp:include page="../footer.jsp" flush="false" />

<script src="${pageContext.request.contextPath}/js/goods/goods.js"></script>
