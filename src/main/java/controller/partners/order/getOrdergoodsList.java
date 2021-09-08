package controller.partners.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dto.memberDTO.memberDTO;
import dto.orderDTO.orderDTO;
import dto.orderDTO.orderGoods;
import dto.orderDTO.orderInfoDTO;
import service.partnerService.getOrderGoodsService;
import service.partners.order.getOrderService;

@WebServlet("/partners/order/getOrderList")
public class getOrdergoodsList extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		
		try{
			BufferedReader r = request.getReader();
			
			while((line=r.readLine())!= null){
				jb.append(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JsonParser ps = new JsonParser();
		JsonElement jobj = ps.parse(jb.toString()).getAsJsonObject();
		
		String scm = jobj.getAsJsonObject().get("scmNo").getAsString();
		String dateType = jobj.getAsJsonObject().get("dateType").getAsString();
		String startDt= jobj.getAsJsonObject().get("startDt").getAsString();
		String endDt = jobj.getAsJsonObject().get("endDt").getAsString();
		String orderState = jobj.getAsJsonObject().get("orderState").getAsString();
		String keywordType = jobj.getAsJsonObject().get("keywordType").getAsString();
		String keyword = jobj.getAsJsonObject().get("keyword").getAsString();
		
		String condition = "where scmNo=" + scm ;
		
		if(!dateType.equals("")){
			condition+= " and " + dateType + "<'" + endDt + "'" +" and " + dateType + "!=0";
			if(!startDt.equals("")){
				condition+=" and " + dateType + ">'" + startDt + "'";
			}
		}
		if(!orderState.equals("")){
			String[] str = orderState.split(",");
			condition+=" and orderStatus in (";
			for(String s : str){
				condition+="'" + s + "',";
			}
			condition = condition.substring(0, condition.length()-1) + ")";
		}
		
		if(!keywordType.equals("") && !keyword.equals("")){
			condition+=" and " + keywordType + " like '%" + keyword + "%'";
		}
		
		condition += " order by regDt desc";
		
		System.out.println(condition);
		
		getOrderGoodsService ogs = new getOrderGoodsService();
		
		ArrayList<orderGoods> list = ogs.getOGScmAllService(condition);
		
		getOrderService gos = new getOrderService();
		
		JsonArray arr = new JsonArray();
		
		for(orderGoods dto : list){

			orderInfoDTO orderInfo = gos.getOrderInfo(dto.getOrderNo());
			JsonObject j = new JsonObject();
			
			j.addProperty("memNm", orderInfo.getOrderName());
			j.addProperty("sno", dto.getSno());
			j.addProperty("orderNo", dto.getOrderNo());
			j.addProperty("orderCd", dto.getOrderCd());
			j.addProperty("eventSno", dto.getEventSno());
			j.addProperty("orderStatus", dto.getOrderStatus());
			j.addProperty("invoiceNo", dto.getInvoiceNo());
			j.addProperty("scmNo", dto.getScmNo());
			j.addProperty("scmAdjustNo", dto.getScmAdjustNo());
			j.addProperty("timeSaleFl", dto.isTimeSaleFl());
			j.addProperty("goodsNo", dto.getGoodsNo());
			j.addProperty("goodsImg", dto.getRepresentImg());
			j.addProperty("goodsNm", dto.getGoodsNm());
			j.addProperty("goodsCnt", dto.getGoodsCnt());
			j.addProperty("goodsOptionNm", dto.getGoodsOptionNm());
			j.addProperty("goodsPrice", dto.getGoodsPrice());
			j.addProperty("optionPrice", dto.getOptionPrice());
			j.addProperty("deliveryPrice", dto.getDeliveryPrice());
			j.addProperty("fixedPrice", dto.getFixedPrice());
			j.addProperty("taxSupplyGoodsPrice", dto.getTaxSupplyGoodsPrice());
			j.addProperty("taxVatGoodsPrice", dto.getTaxVatGoodsPrice());
			j.addProperty("taxFreeGoodsPrice", dto.getTaxFreeGoodsPrice());
			j.addProperty("goodsDcPrice", dto.getGoodsDcPrice());
			j.addProperty("memberDcPrice", dto.getMemberDcPrice());
			j.addProperty("couponGoodsDcPrice", dto.getCouponGoodsDcPrice());
			j.addProperty("couponCd", dto.getCouponCd());
			j.addProperty("timeSaleDcPrice", dto.getTimeSaleDcPrice());
			j.addProperty("goodsMileage", dto.getGoodsMileage());
			j.addProperty("memMileage", dto.getMemMileage());
			j.addProperty("minusDepositFl", dto.isMinusDepositFl());
			j.addProperty("usedDeposit", dto.getUsedDeposit());
			j.addProperty("minusRestoreDepositFl", dto.isMinusRestoreDepositFl());
			j.addProperty("minusMileageFl", dto.isMinusMileageFl());
			j.addProperty("usedMileage", dto.getUsedMileage());
			j.addProperty("minusRestoreMileageFl", dto.isMinusRestoreMileageFl());
			j.addProperty("plusMileageFl", dto.isPlusMileageFl());
			j.addProperty("plusRestoreMileageFl", dto.isPlusRestoreMileageFl());
			j.addProperty("minusStockFl", dto.isMinusStockFl());
			j.addProperty("minusRestoreStockFl", dto.isMinusRestoreStockFl());
			j.addProperty("optionNo", dto.getOptionNo());
			j.addProperty("optionTextInfo", dto.getOptionTextInfo());
			j.addProperty("goodsTaxInfo", dto.getGoodsTaxInfo());
			j.addProperty("cancelDt", (dto.getCancelDt()!=null?dto.getCancelDt().toString():null));
			j.addProperty("paymentDt", (dto.getPaymentDt()!=null?dto.getPaymentDt().toString():null));
			j.addProperty("sendSmsFl", dto.getSendSmsFl());
			j.addProperty("invoiceCompanySno", dto.getInvoiceCompanySno());
			j.addProperty("invoiceDt", (dto.getInvoiceDt()!=null?dto.getInvoiceDt().toString():null));
			j.addProperty("deliveryDt", (dto.getDeliveryDt()!=null?dto.getDeliveryDt().toString():null));
			j.addProperty("deliveryCompleteDt", (dto.getDeliveryCompleteDt()!=null?dto.getDeliveryCompleteDt().toString():null));
			j.addProperty("finishDt", (dto.getFinishDt()!=null?dto.getFinishDt().toString():null));
			j.addProperty("goodsDiscountInfo", dto.getGoodsDiscountInfo());
			j.addProperty("goodsMileageAddInfo", dto.getGoodsMileageAddInfo());
			j.addProperty("regDt", (dto.getRegDt()!=null?dto.getRegDt().toString():null));
			j.addProperty("modDt", (dto.getModDt()!=null?dto.getModDt().toString():null));
			
			arr.add(j);
		}
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		String str = gson.toJson(arr);  
				
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(str);
		System.out.println(str);
		
	}

}
