package controller.orderController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import dto.orderDTO.orderDTO;
import dto.orderDTO.orderGoods;
import dto.orderDTO.orderInfoDTO;
import service.order.orderService;

/**
 * Servlet implementation class orderPayment
 */
@WebServlet("/orderPayment")
public class orderPaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//	주문 결과 저장 및 orderlist insert
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		orderService os = new orderService();
		
		//order
		String orderNo = (String)request.getParameter("Moid");
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		
		String orderStatus;  //주문상태
		if(request.getParameter("payMethod") != "VBANK") {
			orderStatus = "주문완료";
		}else{
			orderStatus = "결제완료";
		}

		String ip = request.getParameter("ip");
		
		BigDecimal settlePrice = new BigDecimal(request.getParameter("Amt")); //총가격
		int vatPrice = (int) (settlePrice.intValue() * 0.1); //부가세 구하기
		BigDecimal taxVatPrice = new BigDecimal(vatPrice); //부가세

		int taxSupply = settlePrice.intValue() - taxVatPrice.intValue(); //과세 구하기 
		BigDecimal taxSupplyPrice = new BigDecimal(taxSupply); //과세

		BigDecimal taxFreePrice = new BigDecimal(0); //면세

		
		
		BigDecimal realTaxSupplyPrice = new BigDecimal(taxSupply); //실제과세(환불제외)
	
		BigDecimal realTaxVatPrice =  new BigDecimal(vatPrice); //실제부가세 (환불제외).

		BigDecimal realTaxFreePrice = new BigDecimal(0); //실제면세 (환불제외)

		
		BigDecimal totalGoodsPrice = new BigDecimal(request.getParameter("totalGoodsPrice")); //총 상품가격
		BigDecimal totalGoodsDcPrice = new BigDecimal(request.getParameter("totalGoodsDcPrice")); //총가격 할인가격
		BigDecimal totalDeliveryCharge = new BigDecimal(request.getParameter("totalDeliveryCharge")); // 총배송비
		
		BigDecimal useMileage = new BigDecimal(request.getParameter("useMileage")); //사용한 마일리지
		
		BigDecimal useDeposit = new BigDecimal(0); // 사용한 예치금
		if(request.getParameter("useDeposit") != null) {
			useDeposit = new BigDecimal(request.getParameter("useDeposit")); //사용한 예치금			
		}
		
		BigDecimal totalMemberDcPrice = new BigDecimal(0); //회원등급별 할인가격
		if(request.getParameter("totalMemberDcPrice") != null) {
			totalMemberDcPrice = new BigDecimal(request.getParameter("totalMemberDcPrice"));
		}
		
		BigDecimal totalMileage = new BigDecimal(0); //총 적립마일리지
		if(request.getParameter("totalMileage") != null) {
			totalMileage = new BigDecimal(request.getParameter("totalMileage"));
		}
		
		
		int mileageGiveExclude = 0; //적립금지급예외여부
		if(request.getParameter("mileageGiveExclude") != null) {
			mileageGiveExclude = Integer.parseInt(request.getParameter("mileageGiveExclude")); //적립금지급예외여부			
		}
		
		int eventCouponFl = 0; //구매쿠폰지급여부
		if(request.getParameter("eventCouponFl") != null) {
			eventCouponFl = Integer.parseInt(request.getParameter("eventCouponFl")); //구매쿠폰지급여부
		}
		
		int transType2 = Integer.parseInt(request.getParameter("TransType"));  // 결제방식
		String transType = "";
		if(transType2 == 0) {
			transType = "일반결제";
		}else if(transType2 == 1){
			transType = "카드결제";
		}
 		String settleKind = (String)request.getParameter("PayMethod"); // 결제수단
		
 		//계좌이체
 		String BankCode = ""; //결제은행 코드
 		String BankName = ""; //결제은행 명
 		String RcptType = "0"; //현금영수증 타입(0:발행안함 1:소득공제 2:지출증비)
 		String RcptTID  = ""; //현금영수증 TID
 		String RcptAuthCode = ""; //현금영수증 승인번호
 		
 		//가상계좌
 		String VbankBankCode = ""; //가상계좌 결제은행 코드
 		String VbankBankName = ""; //가상계좌 결제은행명
 		String VbankNum  	 = ""; //가상계좌 번호
 		String VbankExpDate  = null; //가상계좌 입금 만료일
 		String VbankExpTime  = null; //가상계좌 입금 만료기간
 		
 		//신용카드
 		String CardCode 		= ""; //카드코드
 		String CardName 		= ""; //결제 카드사명
 		String CardNo   		= ""; //카드번호
 		String CardQuota		= ""; //할부개월
 		String CardInterest 	= "0";  //상점분담 무이자 적용 여부(0:일반 , 1:무이자)
 		String AcquCardCode 	= ""; //매입카드사코드
 		String AcquCardName 	= ""; //매입카드사명
 		String CardCl	 		= "0"; //카드구분(0:신용, 1:체크)
 		String CcPartCl  		= "0"; //부분취소 가능 여부 (0:불가능 , 1:가능)
 		String ClickpayCl   	= ""; //간편결제 서비스명
 		BigDecimal CouponAmt	= new BigDecimal(0); //쿠폰금액
 		BigDecimal CouponMinAmt = new BigDecimal(0); //쿠폰최소기준금액
 		BigDecimal PointAppAmt  = new BigDecimal(0); //포인트승인금액
 		String MultiCl			= "0"; //(페이코,카카오 결제 시에만 응답) 복합결제 여부 ==   복합결제 사용 : 1 , 복합결제 사용 : 0
 		int MultiCardAcquAmt    = 0;	//(페이코, 카카오 결제 시에만 응답)복합 신용카드 금액, 반드시 숫자로만 입력, 예시) 1000 원인 경우 -> 1000
 		int MultiPointAmt       = 0;		//(페이코, 카카오 결제 시에만 응답)합 포인트 금액, 반드시 숫자로만 입력, 예시) 5000 원인 경우 -> 5000
 		int MultiCouponAmt      = 0;		//(페이코, 카카오 결제 시에만 응답)복합 쿠폰 금액, 반드시 숫자로만 입력, 예시) 5000원인 경우 -> 5000
// 		int RcptType		    = 0;	//(네이버페이-포인트 결제 시에만 응답) 현금영수증 타입, 예시) 1:소득공제, 2:지출증빙, 그외:발행안함
// 		String RcptTID 			= "";	//(네이버페이-포인트 결제 시에만 응답) 현금영수증 TID, 현금영수증 거래인 경우 필수
// 		String RcptAuthCode		= "";   //(네이버페이-포인트 결제 시에만 응답) 현금영수증 승인번호, 현금영수증 거래인 경우 필수
 		
 		
		/*
		****************************************************************************************
		* <인증 결과 파라미터>
		****************************************************************************************
		*/
		
		String pgResultCode = (String)request.getParameter("AuthResultCode"); //결제결과코드
		
		String pgResultMsg = (String)request.getParameter("AuthResultMsg"); //pg실패사유
		
		String authResultCode 	= (String)request.getParameter("AuthResultCode"); 	// 인증결과 : 0000(성공)
		
		String authResultMsg 	= (String)request.getParameter("AuthResultMsg"); 	// 인증결과 메시지
		
		String nextAppURL = (String)request.getParameter("NextAppURL"); // 승인 요청 URL
		
		String pgTid = (String)request.getParameter("TxTid"); //거래 ID
		
		String txTid = (String)request.getParameter("TxTid"); // 거래 ID
		
		String authToken = (String)request.getParameter("AuthToken"); // 인증 TOKEN
		
		String mid 	= (String)request.getParameter("MID"); 	// 상점 아이디
		
		String amt 	= (String)request.getParameter("Amt"); 				// 결제 금액
		
		
		String netCancelURL	= (String)request.getParameter("NetCancelURL"); // 망취소 요청 URL
		
		//String authSignature = (String)request.getParameter("Signature");			// Nicepay에서 내려준 응답값의 무결성 검증 Data

		/*  
		****************************************************************************************
		* Signature : 요청 데이터에 대한 무결성 검증을 위해 전달하는 파라미터로 허위 결제 요청 등 결제 및 보안 관련 이슈가 발생할 만한 요소를 방지하기 위해 연동 시 사용하시기 바라며 
		* 위변조 검증 미사용으로 인해 발생하는 이슈는 당사의 책임이 없음 참고하시기 바랍니다.
		****************************************************************************************
		 */
		
		DataEncrypt sha256Enc = new DataEncrypt();
		String merchantKey = "CkYO+31uozmYDQqWTG8TK6/tjR9hzdbr17hgDuOC0aSHJm25Oi0jfukkwMUPlQgvLIrqpmqpm8iBS0KSOU2PEQ=="; // 상점키
		
		//인증 응답 Signature = hex(sha256(AuthToken + MID + Amt + MerchantKey)
		//String authComparisonSignature = sha256Enc.encrypt(authToken + mid + amt + merchantKey);

		/*
		****************************************************************************************
		* <승인 결과 파라미터 정의>
		* 샘플페이지에서는 승인 결과 파라미터 중 일부만 예시되어 있으며, 
		* 추가적으로 사용하실 파라미터는 연동메뉴얼을 참고하세요.
		****************************************************************************************
		*/
		
		
		String ResultCode 	= ""; String ResultMsg 	= ""; String PayMethod 	= "";
		String GoodsName 	= ""; String Amt 		= ""; String TID 		= "";
		String pgAuthCode   = ""; String pgAuthDate = "";
		
		
		
		
		/*
		****************************************************************************************
		* <인증 결과 성공시 승인 진행>
		****************************************************************************************
		*/
		String resultJsonStr = "";
		if(authResultCode.equals("0000") /*&& authSignature.equals(authComparisonSignature)*/){
			/*
			****************************************************************************************
			* <해쉬암호화> (수정하지 마세요)
			* SHA-256 해쉬암호화는 거래 위변조를 막기위한 방법입니다. 
			****************************************************************************************
			*/
			String ediDate			= getyyyyMMddHHmmss();
			String signData 		= sha256Enc.encrypt(authToken + mid + amt + ediDate + merchantKey);

			/*
			****************************************************************************************
			* <승인 요청>
			* 승인에 필요한 데이터 생성 후 server to server 통신을 통해 승인 처리 합니다.
			****************************************************************************************
			*/
			StringBuffer requestData = new StringBuffer();
			requestData.append("TID=").append(txTid).append("&");
			requestData.append("AuthToken=").append(authToken).append("&");
			requestData.append("MID=").append(mid).append("&");
			requestData.append("Amt=").append(amt).append("&");
			requestData.append("EdiDate=").append(ediDate).append("&");
			requestData.append("CharSet=").append("utf-8").append("&");
			requestData.append("SignData=").append(signData);

	
			resultJsonStr = connectToServer(requestData.toString(), nextAppURL);


			HashMap resultData = new HashMap();
			boolean paySuccess = false;
			if("9999".equals(resultJsonStr)){
				/*
				*************************************************************************************
				* <망취소 요청>
				* 승인 통신중에 Exception 발생시 망취소 처리를 권고합니다.
				*************************************************************************************
				*/
				StringBuffer netCancelData = new StringBuffer();
				requestData.append("&").append("NetCancel=").append("1");
				String cancelResultJsonStr;

				cancelResultJsonStr = connectToServer(requestData.toString(), netCancelURL);
				
				
				HashMap cancelResultData = jsonStringToHashMap(cancelResultJsonStr);
				ResultCode = (String)cancelResultData.get("ResultCode");
				ResultMsg = (String)cancelResultData.get("ResultMsg");
				/*Signature = (String)cancelResultData.get("Signature");
				String CancelAmt = (String)cancelResultData.get("CancelAmt");
				paySignature = sha256Enc.encrypt(TID + mid + CancelAmt + merchantKey);*/
			}else{
				resultData = jsonStringToHashMap(resultJsonStr);				
				ResultCode 	= (String)resultData.get("ResultCode");	// 결과코드 (정상 결과코드:3001)
				ResultMsg 	= (String)resultData.get("ResultMsg");	// 결과메시지
				PayMethod 	= (String)resultData.get("PayMethod");	// 결제수단
				GoodsName   = (String)resultData.get("GoodsName");	// 상품명
				Amt       	= (String)resultData.get("Amt");		// 결제 금액
				TID       	= (String)resultData.get("TID");		// 거래번호
				pgAuthCode = (String)resultData.get("pgAuthCode"); // 승인번호
				pgAuthDate = (String)resultData.get("pgAuthDate"); // 승인일자

				
				
				//가상계좌일때만
				if(settleKind.equals("VBANK")) {
					VbankBankCode = (String)resultData.get("VbankBankCode");   // 가상계좌 결제은행 코드
					VbankBankName = (String)resultData.get("VbankBankName");	// 가상계좌 결제은행명
					VbankNum = (String)resultData.get("VbankNum");   // 가상계좌 번호
					VbankExpDate = (String)resultData.get("VbankExpDate");//가상계좌 입금 만료일
					VbankExpTime = (String)resultData.get("VbankExpTime");//가상계좌 입금 만료기간
					 
				}else if(settleKind.equals("BANK")){
					System.out.println("계좌이체");
					BankCode = (String)resultData.get("BankCode"); //결제은행 코드
					BankName = (String)resultData.get("BankName");//결제은행 명
					RcptType = (String)resultData.get("RcptType");//결제은행 명
					RcptTID = (String)resultData.get("RcptTID");//결제은행 명
					RcptAuthCode = (String)resultData.get("RcptAuthCode");//결제은행 명
				}else if(settleKind.equals("CARD")) {
					System.out.println("카드결제");
					CardCode = (String)resultData.get("CardCode");   
					CardName = (String)resultData.get("CardName");    
					CardNo = (String)resultData.get("CardNo");    
					CardQuota = (String)resultData.get("CardQuota");  
					CardInterest = (String)resultData.get("CardInterest");    
					AcquCardCode = (String)resultData.get("AcquCardCode");    
					AcquCardName = (String)resultData.get("AcquCardName");  
					CardCl = (String)resultData.get("CardCl");    
					CcPartCl = (String)resultData.get("CcPartCl");    
					ClickpayCl = (String)resultData.get("ClickpayCl");    
					CouponAmt =  new BigDecimal((String)resultData.get("CouponAmt"));    
					CouponMinAmt = new BigDecimal((String)resultData.get("CouponMinAmt"));   
					PointAppAmt = new BigDecimal((String)resultData.get("PointAppAmt"));   
					MultiCl = (String)resultData.get("MultiCl");   
					MultiCardAcquAmt = Integer.parseInt((String)resultData.get("MultiCardAcquAmt"));   
					MultiPointAmt = Integer.parseInt((String)resultData.get("MultiPointAmt"));   
					MultiCouponAmt = Integer.parseInt((String)resultData.get("MultiCouponAmt"));   
					RcptType = (String)resultData.get("RcptType");//결제은행 명
					RcptTID = (String)resultData.get("RcptTID");//결제은행 명
					RcptAuthCode = (String)resultData.get("RcptAuthCode");//결제은행 명
				}
							
				// Signature : Nicepay에서 내려준 응답값의 무결성 검증 Data
				// 가맹점에서 무결성을 검증하는 로직을 구현하여야 합니다.
				/*Signature = (String)resultData.get("Signature");
				paySignature = sha256Enc.encrypt(TID + mid + Amt + merchantKey);*/
				
				/*
				*************************************************************************************
				* <결제 성공 여부 확인>
				*************************************************************************************
				*/
				if(PayMethod != null){
					if(PayMethod.equals("CARD")){
						if(ResultCode.equals("3001")) paySuccess = true; // 신용카드(정상 결과코드:3001)       	
					}else if(PayMethod.equals("BANK")){
						if(ResultCode.equals("4000")) paySuccess = true; // 계좌이체(정상 결과코드:4000)	
					}else if(PayMethod.equals("CELLPHONE")){
						if(ResultCode.equals("A000")) paySuccess = true; // 휴대폰(정상 결과코드:A000)	
					}else if(PayMethod.equals("VBANK")){
						if(ResultCode.equals("4100")) paySuccess = true; // 가상계좌(정상 결과코드:4100)
					}else if(PayMethod.equals("SSG_BANK")){
						if(ResultCode.equals("0000")) paySuccess = true; // SSG은행계좌(정상 결과코드:0000)
					}else if(PayMethod.equals("CMS_BANK")){
						if(ResultCode.equals("0000")) paySuccess = true; // 계좌간편결제(정상 결과코드:0000)
					}
				}
			}
		}else/*if(authSignature.equals(authComparisonSignature))*/{
			ResultCode 	= authResultCode; 	
			ResultMsg 	= authResultMsg;
		}/*else{
			System.out.println("인증 응답 Signature : " + authSignature);
			System.out.println("인증 생성 Signature : " + authComparisonSignature);
		}*/
		 
		
		//order테이블
		String orderEmail2 = "";
		if(request.getParameter("orderEmail") != null) {
			orderEmail2 = request.getParameter("orderEmail");
		}
		
		int order = os.order(orderNo,memNo,orderStatus,ip,settlePrice,taxSupplyPrice,taxVatPrice,taxFreePrice,realTaxSupplyPrice,
				realTaxVatPrice,realTaxFreePrice,useMileage,useDeposit,totalMemberDcPrice,totalMileage,mileageGiveExclude,eventCouponFl,
				transType,settleKind,pgResultCode,pgTid,pgAuthCode,pgAuthDate,pgResultMsg,totalGoodsDcPrice,totalDeliveryCharge,totalGoodsPrice,authToken,
				BankCode,BankName,RcptType,RcptTID,RcptAuthCode,VbankBankCode,VbankBankName,VbankNum,VbankExpDate,VbankExpTime,CardCode,
				CardName,CardNo,CardQuota,CardInterest,AcquCardCode,AcquCardName,CardCl,CcPartCl,ClickpayCl,CouponAmt,CouponMinAmt,
				PointAppAmt,MultiCl,MultiCardAcquAmt,MultiPointAmt,MultiCouponAmt,orderEmail2);
		
		if(order > 0) {
			orderDTO od = new orderDTO();
			
			od.setOrderNo(orderNo);
			od.setMemNo(memNo);
			od.setOrderStatus(orderStatus);
			od.setOrderIp(ip);
			od.setSettlePrice(settlePrice);
			od.setTaxSupplyPrice(taxSupplyPrice);
			od.setTaxVatPrice(taxVatPrice);
			od.setTaxFreePrice(taxFreePrice);
			od.setRealTaxSupplyPrice(realTaxSupplyPrice);
			od.setRealTaxVatPrice(realTaxVatPrice);
			od.setRealTaxFreePrice(realTaxFreePrice);
			od.setUseMileage(useMileage);
			od.setUseDeposit(useDeposit);
			od.setTotalGoodsDcPrice(totalGoodsDcPrice);
			od.setTotalMemberDcPrice(totalMemberDcPrice);
			od.setTotalMileage(totalMileage);
			od.setTotalDeliveryCharge(totalDeliveryCharge);
			od.setTotalGoodsDcPrice(totalGoodsPrice);
			od.setTransType(transType);
			od.setSettleKind(settleKind);
			od.setBankCode(BankCode);
			od.setBankName(BankName);
			od.setRcptType(RcptType);
			od.setRcptTID(RcptTID);
			od.setRcptAuthCode(RcptAuthCode);
			od.setVbankBankCode(VbankBankCode);
			od.setVbankBankName(VbankBankName);
			od.setVbankNum(VbankNum);
			od.setVbankExpDate(VbankExpDate);
			od.setVbankExpTime(VbankExpTime);
			od.setCardCode(CardCode);
			od.setCardName(CardName);
			od.setCardNo(CardNo);
			od.setCardQuota(CardQuota);
			od.setCardInterest(CardInterest);
			od.setAcquCardCode(AcquCardCode);
			od.setAcquCardName(AcquCardName);
			od.setCardCl(CardCl);
			od.setCcPartCl(CcPartCl);
			od.setClickPayCl(ClickpayCl);
			od.setCouponAmt(CouponAmt);
			od.setCouponMinAmt(CouponMinAmt);
			od.setPointAppAmt(PointAppAmt);
			od.setMultiCl(MultiCl);
			od.setMultiCardAcquAmt(MultiCardAcquAmt);
			od.setMultiPointAmt(MultiPointAmt);
			od.setMultiCouponAmt(MultiCouponAmt);
			od.setOrderEmail(orderEmail2);
			
			request.setAttribute("orderList", od);
		}
		
		
		
		//orderGoods
		
		orderGoods og = new orderGoods();
	
		
		int timeSaleFl = 0; //타임세일여부
		if(request.getParameterValues("timeSaleFl") != null) {
			String[] timeSaleFl2 = request.getParameterValues("timeSaleFl");
			String[] timeSaleFl3 = timeSaleFl2[0].split(",");
			for(int i=0; i < timeSaleFl3.length; i++) {
				timeSaleFl = Integer.parseInt(timeSaleFl3[i]);
				
				if(timeSaleFl == 0) {
					boolean timeSaleFlbool = false;
					og.setTimeSaleFl(timeSaleFlbool);					
				}else if(timeSaleFl == 1) {
					boolean timeSaleFlbool = true;
					og.setTimeSaleFl(timeSaleFlbool);	
				}
			}
		}
		String[] timeSaleDcPriceb; //타임세일 할인가격
		
		//배열로 받은 값을 배열로 저장
		//배열로 받은 값을 ,기준으로 짤라서 배열로 저장함
		String[] scmNob = request.getParameterValues("scmNo")[0].split(","); //공급사 번호
		String[] goodsNob = request.getParameterValues("goodsNo")[0].split(","); //상품번호
		String[] representImgb = request.getParameterValues("representImg")[0].split(","); //상품이미지
		String[] goodsNmb = request.getParameterValues("goodsNm")[0].split(","); //상품이름
		String[] goodsCntb = request.getParameterValues("goodsCnt")[0].split(","); //상품갯수
		String[] goodsPriceb = request.getParameterValues("goodsPrice")[0].split(",");	//상품가격
		String[] deliveryPriceb = request.getParameterValues("deliveryCost")[0].split(","); //상품배송비
		String[] goodsOptionNmb = request.getParameterValues("goodsOptionNm")[0].split(","); //상품옵션명
		String[] fixedPriceb = request.getParameterValues("fixedPrice")[0].split(","); //옵션포함가격	
		
//		String[] goodsDcPriceb = new BigDecimal(request.getParameter("goodsDcPrice"))[0].split(","); //상품할인가
//		String[] memberDcPriceb = new BigDecimal(request.getParameter("memberDcPrice"))[0].split(","); //회원등급할인가
//		String[] couponGoodsDcPriceb = new BigDecimal(request.getParameter("couponGoodsDcPrice"))[0].split(","); //쿠폰할인가
//	    String[] couponCdb = request.getParameterValues("couponCd")[0].split(","); //사용된쿠폰번호
//		if(timeSaleFl > 0) {
//			timeSaleDcPriceb = request.getParameterValues("timeSaleDcPrice")[0].split(","); //타임세일적용할인가
//		}
//		BigDecimal goodsMileageb = new BigDecimal(request.getParameterValues("goodsMileage"))[0].split(","); //적립마일리지
//		BigDecimal memMileageb = new BigDecimal(request.getParameterValues("memMileage"))[0].split(",");	//등급별마일리지	
		

//		String[] minusDepositFlb = request.getParameterValues("minusDepositFl")[0].split(","); //마일리지차감여부
//		String[] minusRestoreDepositFlb = request.getParameterValues("minusRestoreDepositFl")[0].split(","); //복원여부(적립 적립금)
//		String[] minusMileageFlb = request.getParameterValues("minusMileageFl")[0].split(","); //  사용마일리지차감여부
//		String[] minusRestoreMileageFlb = request.getParameterValues("minusRestoreMileageFl")[0].split(","); // 사용마일리지복원여부
//		String[] plusRestoreMileageFlb = request.getParameterValues("plusRestoreMileageFl")[0].split(","); // 적립마일리지 복원여부 
//		String[] minusStockFlb = request.getParameterValues("minusStockFl")[0].split(","); //차감여부(재고)
//		String[] minusRestoreStockFlb = request.getParameterValues("minusRestoreStockFl")[0].split(","); //마일리지차감여부
		String[] optionNob = request.getParameterValues("optionNo")[0].split(","); //옵션번호
//		String[] goodsDiscountInfob = request.getParameterValues("goodsDiscountInfo")[0].split(","); //주문당시할인정보
//		String[] goodsMileageAddInfob = request.getParameterValues("goodsMileageAddInfo")[0].split(","); //주문당시상품적립정보
		
		
		//list에 담을 객체를 생성
		List<orderGoods> gdList = new ArrayList<orderGoods>();
		

		//짜른 배열의 값을 하나하나 저장하기위해 변수를 지정
		int scmNo = 0;
		int goodsNo = 0;
		String representImg = "";
		String goodsNm = "";
		int goodsCnt = 0;
		String goodsOptionNm = "";
		BigDecimal goodsPrice = new BigDecimal(0);
		BigDecimal optionPrice = new BigDecimal(0);
		BigDecimal deliveryPrice = new BigDecimal(0);
		BigDecimal fixedPrice = new BigDecimal(0);
		int optionNo = 0;
		BigDecimal taxSupplyGoodsPrice = new BigDecimal(0);
		BigDecimal taxVatGoodsPrice = new BigDecimal(0);
		BigDecimal taxFreeGoodsPrice = new BigDecimal(0);
		BigDecimal goodsDcPrice = new BigDecimal(0);
		BigDecimal memberDcPrice = new BigDecimal(0);
		BigDecimal couponGoodsDcPrice = new BigDecimal(0);
		int couponCd = 0;
		BigDecimal timeSaleDcPrice = new BigDecimal(0);
		BigDecimal goodsMileage = new BigDecimal(0);
		BigDecimal memMileage = new BigDecimal(0);
		boolean minusDepositFl = false;
		boolean minusRestoreDepositFl = false;
		boolean minusMileageFl = false;
		boolean minusRestoreMileageFl = false;
		boolean plusMileageFl = false;
		boolean plusRestoreMileageFl = false;
		boolean minusStockFl = false;
		boolean minusRestoreStockFl = false;
		String goodsDiscountInfo = "";
		String  goodsMileageAddInfo = "";
		
		BigDecimal taxVat = new BigDecimal(0.1);
		
		//짜른 배열의 길이만큼 반복문을 돌림
		for(int i=0; i < goodsNob.length; i++) {
			//짜른 배열의 값을 하나하나 cartDTO에 담음
			orderGoods gd = new orderGoods();
			scmNo = Integer.parseInt(scmNob[i]);
			gd.setScmNo(scmNo);
			goodsNo = Integer.parseInt(goodsNob[i]);
			gd.setGoodsNo(goodsNo);
			optionNo = Integer.parseInt(optionNob[i]);
			gd.setOptionNo(optionNo);
			representImg  = representImgb[i];
			gd.setRepresentImg(representImg);
			goodsNm = goodsNmb[i];
			gd.setGoodsNm(goodsNm);
			goodsCnt = Integer.parseInt(goodsCntb[i]);
			gd.setGoodsCnt(goodsCnt);
			goodsPrice = new BigDecimal(goodsPriceb[i]);
			gd.setGoodsPrice(goodsPrice);
			goodsOptionNm = goodsOptionNmb[i];
			gd.setGoodsOptionNm(goodsOptionNm);
			fixedPrice = new BigDecimal(fixedPriceb[i]);
			gd.setFixedPrice(fixedPrice);
			gd.setOptionPrice(optionPrice);
			deliveryPrice = new BigDecimal(deliveryPriceb[i]);
			gd.setDeliveryPrice(deliveryPrice);
			
			taxVatGoodsPrice = goodsPrice.multiply(taxVat); 								//부가세
			gd.setTaxVatGoodsPrice(taxVatGoodsPrice);
			
			taxSupplyGoodsPrice = goodsPrice.subtract(taxVatGoodsPrice) ; 					//과세
			gd.setTaxSupplyGoodsPrice(taxSupplyGoodsPrice);
			                                                 
			gd.setTaxFreeGoodsPrice(taxFreeGoodsPrice);											//면세
						
//			goodsDcPrice = new BigDecimal(goodsDcPriceb[i]);
			gd.setGoodsDcPrice(goodsDcPrice);
			
//			memberDcPrice = new BigDecimal(memberDcPriceb[i]);
			gd.setMemberDcPrice(memberDcPrice);
			
//			couponGoodsDcPrice = new BigDecimal(couponGoodsDcPrice[i]);
			gd.setCouponGoodsDcPrice(couponGoodsDcPrice);
			
//			couponCd = couponCdb[i];
			gd.setCouponCd(couponCd);
			
//			timeSaleDcPrice = new BigDecimal(timeSaleDcPriceb[i]);
			gd.setTimeSaleDcPrice(timeSaleDcPrice);
			
//			goodsMileage = new BigDecimal(goodsMileageb[i]);
			gd.setGoodsMileage(goodsMileage);
			
//			memMileage = new BigDecimal(memMileageb[i]);
			gd.setMemMileage(memMileage);
			
//			minusDepositFl = minusDepositFlb[i];
			gd.setMinusDepositFl(minusDepositFl);
			
//			minusRestoreDepositFl = minusRestoreDepositFlb[i];
			gd.setMinusRestoreDepositFl(minusRestoreDepositFl);
			
//			minusMileageFl = minusMileageFlb[i];
			gd.setMinusMileageFl(minusMileageFl);
			
//			minusRestoreMileageFl = minusRestoreMileageFlb[i];
			gd.setMinusRestoreMileageFl(minusRestoreMileageFl);
			
//			plusRestoreMileageFl = plusRestoreMileageFlb[i];
			gd.setPlusMileageFl(plusMileageFl);
			gd.setPlusRestoreMileageFl(plusRestoreMileageFl);
			
//			minusStockFl = minusStockFlb[i];
			gd.setMinusStockFl(minusStockFl);
			
//			minusRestoreStockFl = minusRestoreStockFlb[i];
			gd.setMinusRestoreStockFl(minusRestoreStockFl);
			
//			goodsDiscountInfo = goodsDiscountInfob[i];
			gd.setGoodsDiscountInfo(goodsDiscountInfo);
			
//			goodsMileageAddInfo = goodsMileageAddInfo[i];
			gd.setGoodsMileageAddInfo(goodsMileageAddInfo);
			
			//리스트로 담음
			gdList.add(gd);
		}		
		

		int oginsert = os.orderGoods(orderNo,orderStatus,memNo,scmNo,goodsNo,representImg,goodsNm,goodsCnt,goodsOptionNm,goodsPrice,optionPrice,deliveryPrice,fixedPrice,
				taxSupplyGoodsPrice,taxVatGoodsPrice,taxFreeGoodsPrice,goodsDcPrice,memberDcPrice,couponGoodsDcPrice,couponCd,timeSaleDcPrice,goodsMileage,memMileage,
				minusDepositFl,  minusRestoreDepositFl,minusMileageFl,minusRestoreMileageFl,plusMileageFl,plusRestoreMileageFl,minusStockFl,minusRestoreStockFl,
				goodsDiscountInfo,goodsMileageAddInfo);
		
		if(oginsert > 0) {
			request.setAttribute("orderGoods", gdList);			
		}
		
		
		
		
		//orderinfo
			//주문자정보
			String orderName = request.getParameter("BuyerName"); //주문자
			String orderEmail = request.getParameter("BuyerEmail"); //주문자 이메일			
			String orderPhone = request.getParameter("BuyerTel"); //주문자 전화번호
			String orderCellPhone = request.getParameter("BuyerTel"); //주문자 핸드폰 
			
		    //수취인 정보
			String receiverName = request.getParameter("receiverName"); //수취인
			String receiverPhone = request.getParameter("receiverCellPhone"); //주문자 전화번호
			String receiverCellPhone = request.getParameter("receiverCellPhone"); //수취인전화번호
			String receiverZonecode = request.getParameter("receiverZonecode"); // 수취인우편번호
			String receiverAddress = request.getParameter("receiverAddress"); //수취인주소
			String receiverAddressSub = request.getParameter("receiverAddressSub"); //수취인상세주소
			
			String pickUpType = request.getParameter("pickUpType"); //받으실 장소
			String pickUpContent = null; //받으실 장소 상세내용
			if(request.getParameter("pickUpContent") != null) {
				pickUpContent = request.getParameter("pickUpContent");
			}
			
			String meansType = request.getParameter("meansType"); //공동현관
			String meansContent = null; //공동현관비밀번호
			if(request.getParameter("meansContent") != null) {
				meansContent = request.getParameter("meansContent");
			}
			
			//추가사항
			String orderMemo; //남기는 말(추가사항)
			if(request.getParameter("orderMemo") == null) {
				orderMemo = ""; //남기는 말(추가사항)
			}else {
				orderMemo = request.getParameter("orderMemo"); //남기는 말(추가사항)
			}

			int oi = os.orderInfo(orderNo,orderName,orderEmail,orderPhone,orderCellPhone,receiverName,receiverPhone,receiverCellPhone,receiverZonecode,receiverAddress,receiverAddressSub,pickUpType,pickUpContent,meansType,meansContent,orderMemo);
			
			if(oi > 0) {
				orderInfoDTO oid = new orderInfoDTO(); 
				oid.setOrderName(orderName);
				oid.setOrderEmail(orderEmail);
				oid.setOrderPhone(orderPhone);
				oid.setOrderCellPhone(orderCellPhone);
				oid.setReceiverName(receiverName);
				oid.setReceiverPhone(receiverPhone);
				oid.setReceiverCellPhone(receiverCellPhone);
				oid.setReceiverZonecode(receiverZonecode);
				oid.setReceiverAddress(receiverAddress);
				oid.setReceiverAddressSub(receiverAddressSub);
				oid.setPickUpType(pickUpType);
				oid.setPickUpContent(pickUpContent);
				oid.setMeansType(meansType);
				oid.setMeansContent(meansContent);
				oid.setOrderMemo(orderMemo);
				
				//수취인 정보 담기
				request.setAttribute("info", oid);
			}
			
			
			
			if(oi > 0 && oginsert > 0 && order >0) {
				
//				int minusGoodsCnt = os.minusGoodsCnt();
//				int deleteCartGoodsList = os.deleteCartGoodsList();
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("order/orderResult.jsp");
				dispatcher.forward(request, response);
			}
			
			
			
	}
	
	
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

	//server to server 통신
	public String connectToServer(String data, String reqUrl) throws Exception{
		HttpURLConnection conn 		= null;
		BufferedReader resultReader = null;
		PrintWriter pw 				= null;
		URL url 					= null;
		
		int statusCode = 0;
		StringBuffer recvBuffer = new StringBuffer();
		try{
			url = new URL(reqUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(15000);
			conn.setReadTimeout(25000);
			conn.setDoOutput(true);
			
			pw = new PrintWriter(conn.getOutputStream());
			pw.write(data);
			pw.flush();
			
			statusCode = conn.getResponseCode();
			resultReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			for(String temp; (temp = resultReader.readLine()) != null;){
				recvBuffer.append(temp).append("\n");
			}
			
			if(!(statusCode == HttpURLConnection.HTTP_OK)){
				throw new Exception();
			}
			
			return recvBuffer.toString().trim();
		}catch (Exception e){
			return "9999";
		}finally{
			recvBuffer.setLength(0);
			
			try{
				if(resultReader != null){
					resultReader.close();
				}
			}catch(Exception ex){
				resultReader = null;
			}
			
			try{
				if(pw != null) {
					pw.close();
				}
			}catch(Exception ex){
				pw = null;
			}
			
			try{
				if(conn != null) {
					conn.disconnect();
				}
			}catch(Exception ex){
				conn = null;
			}
		}
	}

	//JSON String -> HashMap 변환
	private static HashMap jsonStringToHashMap(String str) throws Exception{
		HashMap dataMap = new HashMap();
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(str);
			JSONObject jsonObject = (JSONObject)obj;

			Iterator<String> keyStr = jsonObject.keySet().iterator();
			while(keyStr.hasNext()){
				String key = keyStr.next();
				Object value = jsonObject.get(key);
				
				dataMap.put(key, value);
			}
		}catch(Exception e){
			
		}
		return dataMap;
	}
	
	
	

}
