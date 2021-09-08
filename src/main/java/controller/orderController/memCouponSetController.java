package controller.orderController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.couponDTO.couponDTO;
import dto.couponDTO.memberCouponDTO;
import dto.goodsDTO.goodsDTO;
import dto.memberDTO.memberDTO;
import dto.orderDTO.orderGoods;
import service.coupon.couponService;
import service.coupon.memberCouponService;
import service.goodsService.goodsService;
import service.order.orderGoodsService;
import service.order.orderService;

@WebServlet("/couponSet")
public class memCouponSetController extends HttpServlet {

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
		
		if(mem!=null){
			
			orderGoodsService os = new orderGoodsService();
			
			ArrayList<orderGoods> ogList = os.getOGList("주문중", mem.getMemNo());
			
			request.setAttribute("list", ogList);
			
			RequestDispatcher dis = request.getRequestDispatcher("./member/memberCouponSet.jsp");
			dis.forward(request, response);
			
		}
		
	}
	
	
}
