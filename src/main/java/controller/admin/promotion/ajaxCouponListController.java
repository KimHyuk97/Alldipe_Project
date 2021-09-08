package controller.admin.promotion;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.couponDTO.couponDTO;
import service.adminService.adminMemberService;
import service.adminService.adminPromotionService;

/**
 * Servlet implementation class couponlist
 */
@WebServlet("/admin/promotion/ajaxcouponList")
public class ajaxCouponListController extends HttpServlet {
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
		adminPromotionService ap = new adminPromotionService();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		List<couponDTO> couponList = ap.getCouponList();
		
		PrintWriter out = response.getWriter();
		JSONArray arr = new JSONArray();

		for(int i=0; i<couponList.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("couponNo",couponList.get(i).getCouponNo()); 			//쿠폰고유번호
			obj.put("couponNm",couponList.get(i).getCouponNm());			//쿠폰이름
			obj.put("couponDesc",couponList.get(i).getCouponDesc());		//쿠폰설명
			obj.put("couponBenefit",couponList.get(i).getBenefit());		//할인퍼센트
			obj.put("couponUsedDay",couponList.get(i).getUsedDate());	    //쿠폰사용가능일수
			obj.put("couponDisplayStartDate",date.format(couponList.get(i).getDisplayStartDate()));		//노출기간
			obj.put("couponDisplayEndDate",date.format(couponList.get(i).getDisplayEndDate()));			//노출기간 끝
			obj.put("couponAmount",couponList.get(i).getAmount());			//쿠폰수량
			obj.put("regDt",date.format(couponList.get(i).getRegDt()));					//등록일
			arr.add(obj);
		}
        String jsonInfo = arr.toJSONString();

	    out.print(jsonInfo);		
		
	}

}
