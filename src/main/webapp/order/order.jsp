<%@page import="dto.orderDTO.orderGoods"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="org.apache.commons.codec.binary.Hex" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.cartDTO.cartDTO"%>
<%@ page import="dto.memberDTO.memberDTO"%>
<%@ page import="java.math.BigDecimal" %>
<%
/*
*******************************************************
* <결제요청 파라미터>
* 결제시 Form 에 보내는 결제요청 파라미터입니다.
* 샘플페이지에서는 기본(필수) 파라미터만 예시되어 있으며, 
* 추가 가능한 옵션 파라미터는 연동메뉴얼을 참고하세요.
*******************************************************
*/

Calendar week = Calendar.getInstance();
week.add(Calendar.DATE , +7);
String VbankExpDate = new java.text.SimpleDateFormat("yyyyMMdd").format(week.getTime());

memberDTO md = (memberDTO)session.getAttribute("mem");

SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
String format_time = format.format (System.currentTimeMillis()) + md.getMemNo();

ArrayList<orderGoods> list = (ArrayList)request.getAttribute("orderGoods");


String merchantKey 		= "CkYO+31uozmYDQqWTG8TK6/tjR9hzdbr17hgDuOC0aSHJm25Oi0jfukkwMUPlQgvLIrqpmqpm8iBS0KSOU2PEQ=="; // 상점키
String merchantID 		= "vmnetworkm"; 				// 상점아이디
String goodsName = "";
if(list!=null){
	if(list.size()>1){	
		goodsName 	= list.get(0).getGoodsNm()+"...외 상품"+(list.size()-1)+"건";					// 결제상품명
	}else{
		goodsName 	= list.get(0).getGoodsNm();					// 결제상품명
	}
}
String buyerName 		= md.getMemNm(); 						// 구매자명
String buyerTel 		= md.getCellPhone(); 				// 구매자연락처
String buyerEmail 		= md.getEmail(); 			// 구매자메일주소
String moid 			= format_time; 			// 상품주문번호	
//String returnURL 		= "http://localhost:8080/nicepay3.0_utf-8/payResult_utf.jsp"; // 결과페이지(절대경로) - 모바일 결제창 전용

%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
<script src="https://web.nicepay.co.kr/v3/webstd/js/nicepay-3.0.js" type="text/javascript"></script>
<script type="text/javascript" src="http://jsgetip.appspot.com"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order/order.css">

<!--상단-->
<jsp:include page="../header.jsp" flush="false" />
<div id="deliveryPopup"></div>
<div id="deliveryMod22" data-no="${sessionScope.mem.memNo}"></div>
<div id="deliveryadd22" data-no="${sessionScope.mem.memNo}"></div>
<div class="orderContainer">
	<div class="mainTitle"><h1>주문/결제</h1></div>
	<form name="payForm" method="post" action="orderPayment">
		<input type="hidden" name="ip" id="myIp">
		<!-- 옵션 --> 
		<input type="hidden" name="GoodsCl" value="1"/>					<!-- 상품구분(실물(1),컨텐츠(0)) -->
		<input type="hidden" name="TransType" value="0"/>				<!-- 일반(0)/에스크로(1) --> 
		<input type="hidden" name="CharSet" value="utf-8"/>				<!-- 응답 파라미터 인코딩 방식 -->
		<input type="hidden" name="ReqReserved" value=""/>				<!-- 상점 예약필드 -->
		<input type="hidden" name="MID" value="<%=merchantID%>"/>		
							
		<input type="hidden" name="GoodsName" value="<%=goodsName%>">
		<input type="hidden" name="Moid" value="<%=moid%>">
		<input type="hidden" name="BuyerName" value="<%=buyerName%>">
		<input type="hidden" name="BuyerEmail" value="<%=buyerEmail%>">
		<input type="hidden" name="BuyerTel" value="<%=buyerTel%>">
		<input type="hidden" name="VbankExpDate" value="<%=VbankExpDate%>">
		
		<!-- 주문자 정보 -->
		<div class="sec01">
			<div class="sec01_sub">
				<div class="subTitle"><h3>주문자 정보</h3></div>	
				<div class="orderor">
					<input type="hidden" name="memNo" value="<%=md.getMemNo()%>">
					<input type="hidden" name="orderName" value="<%=buyerName%>">
					<input type="hidden" name="orderCellPhone" value="<%=buyerTel%>">
					<input type="hidden" name="orderEmail" value="<%=buyerEmail%>">
					<table>
						<tr>
							<th>보내는 분</th>
							<td><%=buyerName%></td>
						</tr>
						<tr>
							<th>핸드폰</th>
							<td><%=buyerTel%></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><%=buyerEmail%></td>
						</tr>
					</table>
				</div>
			</div>
		<!-- 배송 정보 -->
			<div class="sec01_sub">
				<div class="subTitle" style="display: flex;">
					<h3>배송 정보</h3>
					<input type="button" class="deliveryMod" onclick="deliveryMod(<%=md.getMemNo()%>)" value="수정">
				</div>	
				<div id="de1" class="deliveryTitle">
					<input type="hidden" name="receiverName" id="receiverName" value="<%=md.getMemNm()%>">
					<input type="hidden" name="receiverZonecode" id="receiverZonecode" value="<%=md.getZonecode()%>">
					<input type="hidden" name="receiverAddress" id="receiverAddress" value="<%=md.getAddress()%>">
					<input type="hidden" name="receiverAddressSub" id="receiverAddressSub" value="<%=md.getAddressSub()%>">
					<input type="hidden" name="receiverCellPhone" id="receiverCellPhone" value="<%=md.getCellPhone()%>">
					<table>
						<tr>
							<th>받으실 분</th>
							<td><p id='dGetName'>${sessionScope.mem.memNm}</p></td>
						</tr>
						<tr>
							<th>주소</th>
							<td>
								<p id='dAddress'>${sessionScope.mem.zonecode} ${sessionScope.mem.address} ${sessionScope.mem.addressSub}</p>
							</td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><p id='dPhone'>${sessionScope.mem.cellPhone}</p></td>
						</tr>
						<tr>
							<th>받으실 장소</th>
							<td>
								<div class="deliveryWay">
									<label class="radioBtn">문 앞
										<input type="radio" onclick='subAddr(event)' value="문앞" name="pickUpType" checked>
										<span class="radioCheck"></span>
									</label>
									<label class="radioBtn">경비실
										<input type="radio" onclick='subAddr(event)' value="경비실" name="pickUpType">
										<span class="radioCheck"></span>
									</label>
									<label class="radioBtn">택배함
										<input type="radio" onclick='subAddr(event)' value="택배함" name="pickUpType">
										<span class="radioCheck"></span>
									</label>
								</div>
								<div id="subAddr"></div>
							</td>
						</tr>
						<tr>
							<th>공동현관<br> 출입방법</th>
							<td>
								<div class="deliveryWay">
									<label class="radioBtn">자유출입 기능
										<input type="radio" onclick="doorPw22(event)" value="자유출입" name="meansType" checked>
										<span class="radioCheck"></span>
									</label>
									<label class="radioBtn">공동현관 비밀번호
										<input type="radio" onclick="doorPw22(event)" value="공동현관" name="meansType">
										<span class="radioCheck"></span>
									</label>
								</div>
								<div id="doorPassword"></div>
								<p class="pr">※ 자유출입이 불가능한 경우, 부득이하게 1층 공동현관 앞 또는 경비실 앞에 배송될 수 있습니다.</p>
							</td>
						</tr>
						<tr>
							<th>추가사항</th>
							<td><input class="addIssues" name="orderMemo" type='text' placeholder="추가사항이 있을 시 작성해주세요."></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<!-- 주문 상품 -->
		<div class="sec02">
			<div class="subTitle"><h3>주문 상품</h3></div>	
			<div class="orderGoods">
			<table>
				<colgroup>
					<col width="40%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="15%"/>
				</colgroup>
				<tr>
					<th>상품정보</th>
					<th>상품금액</th>
					<th>쿠폰할인금액</th>
					<th>배송비</th>
				</tr>
<%
	if(list.size()>0){
		for(orderGoods og : list){%>
					<tr class="orderGoodsTitle">
						<td>
							<div class="orderFlex">
		                        <div>
		                       		<img src="${pageContext.request.contextPath }/fileF/goods/<%=og.getRepresentImg() %>" alt="상품이미지" style="width:160px; height:160px;">
		                        </div>
		                        <div class="orderGoodsContainer">
                                    <p class="brand_name"><%=og.getMakerNm() %></p>
	                                <p class="item_name"><%=og.getGoodsNm() %></p>
	                                <p class="order_01"><%=og.getGoodsOptionNm() %> (+ <fmt:formatNumber value="<%=og.getOptionFixedPrice() %>" type="number"/>원)</p>
									<p>수량 : <%=og.getGoodsCnt() %>개</p>
			                    </div>
	                        </div>
						</td>
						<td >
							<span class="sumPrice"><fmt:formatNumber value="<%=og.getSumPrice() %>" type="number"/></span>원
						</td>
						<td>
							<span class="couponDcPrice"><fmt:formatNumber value="<%=og.getCouponGoodsDcPrice() %>" type="number"/></span>원
						</td>
						<td >
							<span class="deliverytCost"><fmt:formatNumber value="<%=og.getDeliveryPrice() %>" type="number"/></span>원
						</td>
					</tr>
			
	<%	}
	}
%>					
				</table>
			</div>
		</div>
		<!-- 결제 정보 -->
		<div class="sec03">
			<div class="subTitle"><h3>결제수단</h3></div>	
			<div class="couponFl">			
				<table>
					<tr>
						<th>할인쿠폰</th>
						<td>
							<div><input type="button" id="couponFied" value="쿠폰 조회/적용"></div>									
						</td>
					</tr>
					<tr>
						<th>포인트사용</th>
						<td>
							<div class="mileageFlex">
								<div class="mf"><input type="text" id="useMileage" name="useMileage" min="1" value="0" onkeyup="mileageCheck(this.value)"></div>
								<div class="mf"><input type="button" onclick="usePoint()" value="최대 사용">&nbsp;(사용가능한 적립금 : <span class="mPrice" id="mileageUseFl"></span>원)<input type="hidden" id="possibleMileage"></div>
								<div id="mileage" data-mileage="${sessionScope.mem.mileage}">총 보유 적립금 <span class="mPrice2"><fmt:formatNumber value="${sessionScope.mem.mileage}" type="number"/></span>원</div>
							</div>
						</td>
					</tr>
					<tr>
						<th>결제수단</th>
						<td>
							<div class="transType">
								<label class="radioBtn">일반결제
									<input type="radio" value="0" name="TransType" onclick="transType(event)" checked>
									<span class="radioCheck radioCheck02"></span>
								</label>
								<label class="radioBtn">에스크로
									<input type="radio" value="1" name="TransType" onclick="transType(event)">
									<span class="radioCheck radioCheck02"></span>
								</label>	
							</div>
						</td>	
					</tr>
					<tr>
						<th>결제방식</th>
						<td>
							<div id="paymethodClick">
								<label class='radioBtn'>신용카드
								<input type='radio' value='CARD' name='PayMethod' checked>
								<span class='radioCheck radioCheck02'></span>
								</label>
						
								<label class='radioBtn'>계좌이체
								<input type='radio' value='BANK' name='PayMethod'>
								<span class='radioCheck radioCheck02'></span>
								</label>
							
								<label class='radioBtn'>가상계좌
								<input type='radio' value='VBANK' name='PayMethod'>
								<span class='radioCheck radioCheck02'></span>
								</label>
							
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div>
				<p class="pr3">
					※ 쿠폰 또는 적립금 사용은 총가격이 20,000원 이상 사용가능합니다. <br/><br/>
					※ 적립금 사용은 총금액의 최대 50%를 사용할 수 있고, 최대금액 10,000원까지 사용이 가능합니다.
				</p>
				
			</div>
			<div id="couponMode">
				<h1>COUPON</h1>
				<div><input type="button" value="X" id="cClose"></div>
				<div class="goodsList">
					<table>
					  <colgroup>
					    <col width="40%"/>
					    <col width="15%"/>
					    <col width="15%"/>
					    <col width="15%"/>
					  </colgroup>
						<tr>
							<th>상품정보</th>
							<th>상품금액</th>
							<th>쿠폰선택</th>
							<th>할인금액</th>
						</tr>
<%	if(list!=null && list.size()>0){
		int cnt = 0;
		for(orderGoods dto : list){
%>
							<tr class="orderGoodsTitle">
			                    <td>
			                    	<div class="orderFlex">
				                        <div>
				                       		<img src="<%=dto.getRepresentImg() %>" alt="" style="width:60px; height:60px;">
				                       		<input type="hidden" id="ordergoodsNo" value="<%=dto.getSno()%>">	<!-- 적용할 주문상품  -->
				                       		<input type="hidden" name="mcn" id="mcn<%=cnt%>"> <!-- 적용쿠폰시리얼넘버  -->
				                       		<input type="hidden" id="cn<%=cnt%>">
				                        </div>
				                        <div class="orderGoodsContainer">
			                                <p><%=dto.getGoodsNm() %></p>
			                                <p><%=dto.getGoodsOptionNm() %></p>
					                    </div>
			                        </div>
			                    </td>
			                    <td ><fmt:formatNumber value="<%=dto.getSumPrice() %>" type="number"/><span>원</span></td>
			                    <td id="couponBtnChange<%=cnt%>"><input type="button" onclick="couponSelect(<%=cnt %>,<%=dto.getSumPrice() %>,<%=dto.getGoodsNo() %>,<%=dto.getScmNo() %>)" value="쿠폰선택"></td>			           
			                    <td ><span id="couponSalePrice<%=cnt%>">0 원</span></td>				                    
			            	</tr>
<%		}
		cnt++;
	}else{%>
							<tr class="orderGoodsTitle">
			                    <td>
			                    	상품이 없습니다.
			                    </td>				                    
			            	</tr>	
<%	}
%>
					</table>
				</div>
				<div class="couponNote">
					<p>ㆍ쿠폰 적용 시 한 주문, 한 상품에 한해서만 적용됩니다.</p>
					<p>ㆍ각 쿠폰은 사용기한이 정해져 있습니다.</p>
					<p>ㆍ주문 후 반품/환불/주문취소의 경우 사용하신 쿠폰은 소멸됩니다.</p>
					<p>ㆍ쿠폰 적용품목이 한정된 쿠폰은 해당 품목에서만 사용가능 합니다.</p>
					<p>ㆍ해당 상품에 대한 쿠폰은 해당 상품만 구매시 적용이 가능합니다.</p>					
				</div>
				<div id="couponModeBtn"></div>
				<div id="couponListModal"></div>
			</div>		
		</div>
		<!-- 결제 금액 -->
		<div class="sec04">
			<div class="subTitle"><h3>결제 금액</h3></div>	
			<div class="paymentFlex">
		        <div class="paymentFlex_1">
		            결제 예정 금액
		        </div>
		        <div class="paymentFlex_2">
		        	<table>
			            <tr>
			                <th>상품금액</th>
			                <td>
			                    <span></span>
			                    <span>원<input type="hidden" name="totalGoodsPrice" value=""></span>
			                </td>
			            </tr>
			            <tr>
			                <th>배송비</th>
			                <td >
			                    <span>(+) </span>
			                    <span></span>
			                    <span>원</span>
			                </td>
			            </tr>
			            <tr>
			                <th>쿠폰 할인 금액</th>
			                <td>
			                	<input type="hidden" id="coupontotalSalePrice2" value="0">
			                    <span>(-) </span>
			                    <span id="coupontotalSalePrice">0</span>
			                    <span>원</span>
			                </td>
			            </tr>
			            <tr>
			                <th>보유 적립금 사용</th>
 			                <td>
 			                	<input type="hidden" id="mileagetotalSalePrice2" value="0">
			                    <span>(-) </span>
 			                    <span id="mileagetotalSalePrice">0</span>
 			                    <span>원<input type="hidden" id="mileageGoodsDcPrice" name="mileageGoodsDcPrice"></span>
			                </td>
			            </tr>
		            </table>
		        </div>
		        <div class="paymentFlex_3">
		               <div>총가격</div>
		               <div>
		               		<input id="amt" name="Amt" type="hidden" value="">
		               		<span id="orderTotalPrice">원</span>
		               	</div>
		               <div>
		               	총 적립금 <span id="memberAddMileage"></span>원
		               	<input type="hidden" id="memberAdd">	
		               </div>
		        </div>
   			</div>
		</div>
		<!-- 결제 약관동의,결제 버튼 -->
		<div class="subContainer">
			<!-- 변경 불가능 -->
			<input type="hidden" name="EdiDate" value=""/>		<!-- 전문 생성일시 -->
			<input type="hidden" name="SignData" value=""/>	<!-- 해쉬값 -->	
			<div class="checkBox_1"><input type="checkbox" id="check_1" name="agreement" class="check"><label for="check_1">구매조건 확인 및 결제대행 서비스 약관 동의 <span>(필수) </span></label><a href="#"> 약관보기&nbsp;></a></div>
			<div id="buyBtn"><input type="button" class="btn_blue" onClick="orderPayment();" value="결제하기"></div>
				
		</div>
	</form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/order/order.js"></script>
<script>
	window.onload = function(){
		endLoading();
	}
</script>
<!-- 하단 -->
<jsp:include page="../footer.jsp" flush="false" />
<%!
public final synchronized String getyyyyMMddHHmmss(){
	SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	return yyyyMMddHHmmss.format(new Date());
}
// SHA-256 형식으로 암호화
public class DataEncrypt{
	MessageDigest md;
	String strSRCData = "";
	String strENCData = "";
	String strOUTData = "";
	
	public DataEncrypt(){ }
	public String encrypt(String strData){
		String passACL = null;
		MessageDigest md = null;
		try{
			md = MessageDigest.getInstance("SHA-256");
			md.reset();
			md.update(strData.getBytes());
			byte[] raw = md.digest();
			passACL = encodeHex(raw);
		}catch(Exception e){
			System.out.print("암호화 에러" + e.toString());
		}
		return passACL;
	}
	
	public String encodeHex(byte [] b){
		char [] c = Hex.encodeHex(b);
		return new String(c);
	}
}
%>