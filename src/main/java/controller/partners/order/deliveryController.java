package controller.partners.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.orderDTO.orderGoods;
import dto.scmDTO.scmDTO;
import service.order.orderGoodsService;
import service.partnerService.getOrderGoodsService;

@WebServlet("/partners/order/delivery")
public class deliveryController extends HttpServlet {

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
		scmDTO scm = (scmDTO)session.getAttribute("scm");
		
		if(scm==null){
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('허용되지 않은 접근입니다.');");
			out.println("location.href='../Main';");
			out.println("</script>");
		}else{
			String condition = "where scmNo= " + scm.getScmNo() + " and regDt<now() and orderStatus in ('배송중','배송완료','미수취','미수취 철회')";
			
			getOrderGoodsService ogs = new getOrderGoodsService();
			ArrayList<orderGoods> list = ogs.getOGScmAllService(condition);
			
			session.setAttribute("orderList", list);
			
			RequestDispatcher dis = request.getRequestDispatcher("./delivery.jsp");
			dis.forward(request, response);
		}
		
		
	}
	
}
