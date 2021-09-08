package controller.admin.promotion;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.couponDTO.couponDTO;
import service.coupon.couponService;

@WebServlet("/admin/promotion/couponList")
public class adminCouponListController extends HttpServlet {

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
		
		//	쿠폰 리스트 받아오기
		couponService cs = new couponService();
		ArrayList<couponDTO> list = cs.getList();
				
		HttpSession session = request.getSession();
		session.setAttribute("couponList", list);
		
		
		//	couponList.jsp로 페이지 이동
		RequestDispatcher dis = request.getRequestDispatcher("./couponList.jsp");
		dis.forward(request, response);
		
	}
	
}
