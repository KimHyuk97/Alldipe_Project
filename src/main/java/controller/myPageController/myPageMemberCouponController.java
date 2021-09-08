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

import dto.myPageDTO.memberCouponDTO;
import service.myPageService.myPageMemberService;

/**
 * Servlet implementation class myPageMemberCouponController
 */
@WebServlet("/memberCoupon")
public class myPageMemberCouponController extends HttpServlet {
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
		
		myPageMemberService coupon = new myPageMemberService();
		
		List<memberCouponDTO> mpd = coupon.couponSelect(memNo);
		
		if(mpd != null) {
			PrintWriter out = response.getWriter();
			
			JSONArray arr = new JSONArray();
			
			for(int i=0; i<mpd.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("memberCouponNo",mpd.get(i).getMemberCouponNo());
				obj.put("couponNm",mpd.get(i).getCouponNm());
				obj.put("couponBenefit",mpd.get(i).getCouponBenefit());
				obj.put("memberCouponStartDate",mpd.get(i).getMemberCouponStartDate());
				obj.put("memberCouponEndDate",mpd.get(i).getMemberCouponEndDate());
				arr.add(obj);
			}
	       String jsonInfo = arr.toJSONString();	
		   out.print(jsonInfo);		
		}
	}

}
