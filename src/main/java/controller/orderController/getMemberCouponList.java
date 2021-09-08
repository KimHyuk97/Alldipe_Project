package controller.orderController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dto.couponDTO.memberCouponDTO;
import dto.goodsDTO.goodsDTO;
import dto.memberDTO.memberDTO;
import service.goodsService.goodsService;
import service.order.orderService;

public class getMemberCouponList extends HttpServlet {

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
		
		HttpSession session = request.getSession();
		memberDTO mem = (memberDTO)session.getAttribute("mem");

		String goodsStr = request.getParameter("goods");
		
		int goodsNo = 0;
		if(goodsStr!=null && !goodsStr.equals("")){
			goodsNo = Integer.parseInt(goodsStr);
		}
		
		goodsService gs = new goodsService();
		orderService os = new orderService();
		
		goodsDTO goods = gs.getGoodsInfo(goodsNo);
		
		List<memberCouponDTO> list = os.membercouponList(mem.getMemNo(), goods.getScmNo(), goodsNo);
		
		JsonArray jlist = new JsonArray();
		
		for(memberCouponDTO dto : list ){
			
			JsonObject jobj = new JsonObject();
			
			jobj.addProperty("sno", dto.getSno());
			jobj.addProperty("couponNo", dto.getCouponNo());
			jobj.addProperty("memNo", dto.getMemNo());
			jobj.addProperty("adminNo", dto.getAdminNo());
			jobj.addProperty("couponNm", dto.getCouponNm());
			jobj.addProperty("couponBenifit", dto.getCouponBenefit());
			jobj.addProperty("couponBenefitType", dto.isCouponBenefitType());
			jobj.addProperty("ordergoodsNo", dto.getOrdergoodsNo());
			jobj.addProperty("sno", dto.getSno());
			jobj.addProperty("memberCouponStartDt", dto.getMemberCouponStartDt().toString());
			jobj.addProperty("memberCouponEndDt", dto.getMemberCouponEndDt().toString());
			jobj.addProperty("memberCouponUseDt", dto.getMemberCouponUseDt().toString());
			jobj.addProperty("useState", dto.isUseState());
			jobj.addProperty("regDt", dto.getRegDt().toString());
			jobj.addProperty("modDt", dto.getModDt().toString());
			
			jlist.add(jobj);
		}
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		String str = gson.toJson(jlist);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println(str);
		System.out.println(str);
		
	}
	
}
