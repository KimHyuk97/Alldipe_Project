package controller.partners.goods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

import dto.goodsDTO.goodsDTO;
import service.partnerService.goodsListService;

@WebServlet("/partners/goods/getGoodsList")
public class getGoodsList extends HttpServlet {

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
		
		String scmNo = jobj.getAsJsonObject().get("scmNo").getAsString();
		String dateCate = jobj.getAsJsonObject().get("dateCate").getAsString();
		String startDate = jobj.getAsJsonObject().get("startDate").getAsString();
		String endDate = jobj.getAsJsonObject().get("endDate").getAsString();
		String cateCd = jobj.getAsJsonObject().get("cateCd").getAsString();
		String state = jobj.getAsJsonObject().get("state").getAsString();
		String saleState = jobj.getAsJsonObject().get("saleState").getAsString();
		String keywordType = jobj.getAsJsonObject().get("keywordType").getAsString();
		String keyword = jobj.getAsJsonObject().get("keyword").getAsString();
		
		goodsListService gls = new goodsListService();
		
		ArrayList<goodsDTO> list = gls.getGoodsList(scmNo, dateCate, startDate, endDate, cateCd, state, saleState, keywordType, keyword);
		
		System.out.println("list size : "+list.size());
		
		JsonArray arr = new JsonArray();
		
		for(goodsDTO dto : list){
			JsonObject j = new JsonObject();
			j.addProperty("goodsNo", dto.getGoodsNo());
			j.addProperty("goodsNm", dto.getGoodsNm());
			j.addProperty("goodsSellFl", dto.getGoodsSellFl());
			j.addProperty("scmNo", dto.getScmNo());
			j.addProperty("applyFl", dto.getApplyFl());
			j.addProperty("applyMsg", dto.getApplyMsg());
			j.addProperty("applyDt", (dto.getApplyDt()!=null?dto.getApplyDt().toString():null));
			j.addProperty("salesStartDt", (dto.getSalesStartDt()!=null?dto.getSalesStartDt().toString():null));
			j.addProperty("salesEndDt", (dto.getSalesEndDt()!=null?dto.getSalesEndDt().toString():null));
			j.addProperty("cateCd", dto.getCateCd());
			j.addProperty("brandCd", dto.getBrandCd());
			j.addProperty("keyword", dto.getKeyword());
			j.addProperty("commission", dto.getCommission());
			j.addProperty("goodsPrice", dto.getGoodsPrice());
			j.addProperty("fixedPrice", dto.getFixedPrice());
			j.addProperty("discountPercent", dto.getDiscountPercent());
			j.addProperty("totalStock", dto.getTotalStock());
			j.addProperty("discountInfo", dto.getDiscountInfo());
			j.addProperty("periodDiscountStart", (dto.getPeriodDiscountStart()!=null?dto.getPeriodDiscountStart().toString():null));
			j.addProperty("periodDiscountEnd", (dto.getPeriodDiscountEnd()!=null?dto.getPeriodDiscountEnd().toString():null));
			j.addProperty("goodsDiscountFl", dto.isGoodsDiscountFl());
			j.addProperty("goodsDiscountType", dto.isGoodsDiscountType());
			j.addProperty("goodsDiscountPrice", dto.getGoodsDiscountPrice());
			j.addProperty("goodsDiscountPercent", dto.getGoodsDiscountPercent());
			j.addProperty("goodsMustInfo", dto.getGoodsMustInfo());
			j.addProperty("kcmarkInfo", dto.getKcmarkInfo());
			j.addProperty("onlyAdultFl", dto.isOnlyAdultFl());
			j.addProperty("taxFreeFl", dto.getTaxFreeFl());
			j.addProperty("taxPercent", dto.getTaxPercent());
			j.addProperty("goodsWeight", dto.getGoodsWeight());
			j.addProperty("fixedSales", dto.getFixedSales());
			j.addProperty("fixedOrderCnt", dto.getFixedOrderCnt());
			j.addProperty("salesUnit", dto.getSalesUnit());
			j.addProperty("minOrderCnt", dto.getMinOrderCnt());
			j.addProperty("maxOrderCnt", dto.getMaxOrderCnt());
			j.addProperty("restockFl", dto.isRestockFl());
			j.addProperty("representImg", dto.getRepresentImg());
			j.addProperty("subImg", dto.getSubImg());
			j.addProperty("shortDescription", dto.getShortDescription());
			j.addProperty("eventDescription", dto.getEventDescription());
			j.addProperty("goodsDescription", dto.getGoodsDescription());
			j.addProperty("shipmentZonecode", dto.getShipmentZonecode());
			j.addProperty("shipmentAddress", dto.getShipmentAddress());
			j.addProperty("shipmentAddressSub", dto.getShipmentAddressSub());
			j.addProperty("recoveryZonecode", dto.getRecoveryZonecode());
			j.addProperty("recoveryAddress", dto.getRecoveryAddress());
			j.addProperty("recoveryAddressSub", dto.getRecoveryAddressSub());
			j.addProperty("deliveryCompany", dto.getDeliveryCompany());
			j.addProperty("deliveryType", dto.getDeliveryType());
			j.addProperty("deliveryWay", dto.getDeliveryWay());
			j.addProperty("deliveryKind", dto.getDeliveryKind());
			j.addProperty("deliveryFreeCondition", dto.getDeliveryFreeCondition());
			j.addProperty("deliveryCost", dto.getDeliveryCost());
			j.addProperty("deliveryArea", dto.getDeliveryArea());
			j.addProperty("deliveryCostAddJeju", dto.getDeliveryCostAddJeju());
			j.addProperty("deliveryCostAdd", dto.getDeliveryCostAdd());
			j.addProperty("deliveryRefundCost", dto.getDeliveryRefundCost());
			j.addProperty("relationGoodsNo", dto.getRelationGoodsNo());
			j.addProperty("detailInfoDelivery", dto.getDetailInfoDelivery());
			j.addProperty("detailInfoAS", dto.getDetailInfoAS());
			j.addProperty("detailInfoRefund", dto.getDetailInfoRefund());
			j.addProperty("detailInfoExchange", dto.getDetailInfoExchange());
			j.addProperty("orderCnt", dto.getOrderCnt());
			j.addProperty("orderGoodsCnt", dto.getOrderGoodsCnt());
			j.addProperty("hitCnt", dto.getHitCnt());
			j.addProperty("wishCnt", dto.getWishCnt());
			j.addProperty("reviewCnt", dto.getReviewCnt());
			j.addProperty("couponCd", dto.getCouponCd());
			j.addProperty("icon", dto.getIcon());
			j.addProperty("delFl", dto.isDelFl());
			j.addProperty("adminMsg", dto.getAdminMsg());
			j.addProperty("regDt", (dto.getRegDt()!=null?dto.getRegDt().toString():null));
			j.addProperty("modDt", (dto.getModDt()!=null?dto.getModDt().toString():null));
			j.addProperty("delDt", (dto.getDelDt()!=null?dto.getDelDt().toString():null));
			
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
