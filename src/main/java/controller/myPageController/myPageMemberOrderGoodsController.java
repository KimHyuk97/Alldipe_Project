
package controller.myPageController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.myPageDTO.memberMileageDTO;
import dto.orderDTO.orderGoods;
import service.myPageService.myPageMemberService;

/**
 * Servlet implementation class myPageMemberOrderGoodsController
 */
@WebServlet("/memberOrderGoods")
public class myPageMemberOrderGoodsController extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
				
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		
		myPageMemberService mms = new myPageMemberService();
		
		List<orderGoods> mpd = mms.memberOrderGoods(memNo);
		
		if(mpd != null) {
			PrintWriter out = response.getWriter();
			
			JSONArray arr = new JSONArray();
			
			for(int i=0; i<mpd.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("sno",mpd.get(i).getSno());
				obj.put("orderNo",mpd.get(i).getOrderNo());
				obj.put("orderStatus",mpd.get(i).getOrderStatus());
				obj.put("invoiceNo",mpd.get(i).getInvoiceNo());
				obj.put("goodsNo",mpd.get(i).getGoodsNo());
				obj.put("goodsImg",mpd.get(i).getRepresentImg());
				obj.put("goodsNm",mpd.get(i).getGoodsNm());
				obj.put("goodsCnt",mpd.get(i).getGoodsCnt());
				obj.put("goodsOptionNm",mpd.get(i).getGoodsOptionNm());
				obj.put("goodsPrice",mpd.get(i).getGoodsPrice());
				obj.put("optionPrice",mpd.get(i).getOptionPrice());
				obj.put("fixedPrice",mpd.get(i).getFixedPrice());
				obj.put("goodsDcPrice",mpd.get(i).getGoodsDcPrice());
				obj.put("memberDcPrice",mpd.get(i).getMemberDcPrice());
				obj.put("couponGoodsDcPrice",mpd.get(i).getCouponGoodsDcPrice());
				obj.put("couponCd",mpd.get(i).getCouponCd());
				obj.put("timeSaleDcPrice",mpd.get(i).getTimeSaleDcPrice());
				obj.put("goodsMileage",mpd.get(i).getGoodsMileage());
				obj.put("memMileage",mpd.get(i).getMemMileage());
				obj.put("cancelDt",mpd.get(i).getCancelDt());
				obj.put("paymentDt",mpd.get(i).getPaymentDt());
				obj.put("sendSmsFl",mpd.get(i).getSendSmsFl());
				obj.put("invoiceCompanySno",mpd.get(i).getInvoiceCompanySno());
				obj.put("invoiceDt",mpd.get(i).getInvoiceDt());
				obj.put("deliveryDt",mpd.get(i).getDeliveryDt());
				obj.put("deliveryCompleteDt",mpd.get(i).getDeliveryCompleteDt());
				obj.put("finishDt",mpd.get(i).getFinishDt());
				obj.put("goodsDiscountInfo",mpd.get(i).getGoodsDiscountInfo());
				obj.put("goodsMileageAddInfo",mpd.get(i).getGoodsMileageAddInfo());
				obj.put("deliveryPrice",mpd.get(i).getDeliveryPrice());
				obj.put("regDt",mpd.get(i).getRegDt());
				arr.add(obj);
			}
	       String jsonInfo = arr.toJSONString();			
		   out.print(jsonInfo);		
		}
	}
}

