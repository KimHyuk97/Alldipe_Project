package controller.admin.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.couponDTO.couponDTO;
import dto.memberDTO.membergradeDTO;
import service.adminService.adminMemberService;

/**
 * Servlet implementation class memberGradeInsert
 */
@WebServlet("/admin/member/memberGradeInsert")
public class memberGradeInsertController extends HttpServlet {
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
		adminMemberService am = new adminMemberService();
		
		
		String mode = (request.getParameter("mode")!=null)?request.getParameter("mode"):"추가";
		int sno = (request.getParameter("sno") != null)?Integer.parseInt(request.getParameter("sno")):0;
		if(sno != 0) {			
			membergradeDTO dto = am.getGrade(sno);
			request.setAttribute("grade", dto);
			
			//쿠폰리스트
			if(dto.getCouponCd() != null) {
				List<couponDTO> couponlist = new ArrayList<>();
				String[] coupon = dto.getCouponCd().split(",");
				for(int i = 0; i < coupon.length; i++) {
					couponDTO c = am.getCouponList(coupon[i]);
					couponlist.add(c);
				}
				if(!couponlist.isEmpty()) {
					request.setAttribute("couponList", couponlist);
				}
			}
			
		}

		request.setAttribute("mode", mode);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./memberGradeInsert.jsp");
		dispatcher.forward(request, response);	
	}

}
