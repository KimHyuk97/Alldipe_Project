<%@page import="org.apache.commons.codec.binary.Hex"%>
<%@page import="java.util.Date"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
*******************************************************
* <결제요청 파라미터>
* 결제시 Form 에 보내는 결제요청 파라미터입니다.
* 샘플페이지에서는 기본(필수) 파라미터만 예시되어 있으며, 
* 추가 가능한 옵션 파라미터는 연동메뉴얼을 참고하세요.
*******************************************************
*/
String merchantKey 		= "EYzu8jGGMfqaDEp76gSckuvnaHHu+bC4opsSN6lHv3b2lurNYkVXrZ7Z1AoqQnXI3eLuaUFyoRNC6FkrzVjceg=="; // 상점키	o
String merchantID 		= "nicepay00m"; 				// 상점아이디	o
String goodsName 		= "나이스페이"; 					// 결제상품명	o
String price 			= "100"; 						// 결제상품금액		x
String buyerName 		= "나이스"; 						// 구매자명	o
String buyerTel 		= "01000000000"; 				// 구매자연락처	o
String buyerEmail 		= "happy@day.co.kr"; 			// 구매자메일주소	o
String moid 			= "mnoid1234567890"; 			// 상품주문번호		o
String returnURL 		= "http://localhost:8080/nicepay3.0_utf-8/payResult_utf.jsp"; // 결과페이지(절대경로) - 모바일 결제창 전용

/*
*******************************************************
* <해쉬암호화> (수정하지 마세요)
* SHA-256 해쉬암호화는 거래 위변조를 막기위한 방법입니다. 
*******************************************************
*/
DataEncrypt sha256Enc 	= new DataEncrypt();
String ediDate 			= getyyyyMMddHHmmss();	
String hashString 		= sha256Enc.encrypt(ediDate + merchantID + price + merchantKey);
%>
<jsp:include page="../header.jsp"></jsp:include>
<script src="https://web.nicepay.co.kr/v3/webstd/js/nicepay-3.0.js" type="text/javascript"></script>
<script type="text/javascript" src="http://jsgetip.appspot.com"></script>
<script src="https://web.nicepay.co.kr/v3/webstd/js/nicepay-3.0.js" type="text/javascript"></script>
<script type="text/javascript">
	//결제창 최초 요청시 실행됩니다.
	function nicepayStart(){
		if(checkPlatform(window.navigator.userAgent) == "mobile"){//모바일 결제창 진입
			document.payForm.action = "https://web.nicepay.co.kr/v3/v3Payment.jsp";
			document.payForm.acceptCharset="euc-kr";
			document.payForm.submit();
		}else{//PC 결제창 진입
			goPay(document.payForm);
		}
	}
	
	//[PC 결제창 전용]결제 최종 요청시 실행됩니다. <<'nicepaySubmit()' 이름 수정 불가능>>
	function nicepaySubmit(){
		document.payForm.submit();
	}
	
	//[PC 결제창 전용]결제창 종료 함수 <<'nicepayClose()' 이름 수정 불가능>>
	function nicepayClose(){
		alert("결제가 취소 되었습니다");
	}
	
	//pc, mobile 구분(가이드를 위한 샘플 함수입니다.)
	function checkPlatform(ua) {
		if(ua === undefined) {
			ua = window.navigator.userAgent;
		}
		
		ua = ua.toLowerCase();
		var platform = {};
		var matched = {};
		var userPlatform = "pc";
		var platform_match = /(ipad)/.exec(ua) || /(ipod)/.exec(ua) 
			|| /(windows phone)/.exec(ua) || /(iphone)/.exec(ua) 
			|| /(kindle)/.exec(ua) || /(silk)/.exec(ua) || /(android)/.exec(ua) 
			|| /(win)/.exec(ua) || /(mac)/.exec(ua) || /(linux)/.exec(ua)
			|| /(cros)/.exec(ua) || /(playbook)/.exec(ua)
			|| /(bb)/.exec(ua) || /(blackberry)/.exec(ua)
			|| [];
		
		matched.platform = platform_match[0] || "";
		
		if(matched.platform) {
			platform[matched.platform] = true;
		}
		
		if(platform.android || platform.bb || platform.blackberry
				|| platform.ipad || platform.iphone 
				|| platform.ipod || platform.kindle 
				|| platform.playbook || platform.silk
				|| platform["windows phone"]) {
			userPlatform = "mobile";
		}
		
		if(platform.cros || platform.mac || platform.linux || platform.win) {
			userPlatform = "pc";
		}
		
		return userPlatform;
	}
</script>
<!-- 페이지 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/order/ordersheet.css">
	<div class="container">
		<form name="payForm" method="post" action="orderPayment">
			<input type="hidden" name="PayMethod" value="">
			<input type="hidden" name="GoodsName" value="">
			<input type="hidden" name="Amt" value="">
			<input type="hidden" name="MID" value="">
			<input type="hidden" name="Moid" value="">
			<input type="hidden" name="BuyerName" value="">
			<input type="hidden" name="BuyerEmail" value="">
			<input type="hidden" name="BuyerTel" value="">
			<input type="hidden" name="ReturnURL" value="">
			<input type="hidden" name="VbankExpDate" value="">
			<input type="hidden" name="GoodsCl" value="1"/>						<!-- 상품구분(실물(1),컨텐츠(0)) -->
			<input type="hidden" name="TransType" value="0"/>					<!-- 일반(0)/에스크로(1) --> 
			<input type="hidden" name="CharSet" value="utf-8"/>					<!-- 응답 파라미터 인코딩 방식 -->
			<input type="hidden" name="ReqReserved" value=""/>					<!-- 상점 예약필드 -->
			<!-- 변경 불가능 -->
			<input type="hidden" name="EdiDate" value="<%=ediDate%>"/>			<!-- 전문 생성일시 -->
			<input type="hidden" name="SignData" value="<%=hashString%>"/>		<!-- 해쉬값 -->
		</form>
		<button type="button" onclick="nicepayStart();">결제하기</button>
	</div>
<jsp:include page="../footer.jsp"></jsp:include>
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