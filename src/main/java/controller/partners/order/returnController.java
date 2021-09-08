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
import service.partnerService.getOrderGoodsService;

@WebServlet("/partners/order/return")
public class returnController extends HttpServlet {

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
		
		if(scm == null){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('파트너 전용 페이지입니다.');");
			out.println("location.href='../login';");
			out.println("</script>");
		}else{
			
			getOrderGoodsService ogs = new getOrderGoodsService();
			String condition = "where scmNo = " + scm.getScmNo() + " and orderStatus in ('주문완료','상품준비중','환불신청','구매확정')";
			ArrayList<orderGoods> list = ogs.getOGScmAllService(condition);
			
			session.setAttribute("ogList", list);
			
			RequestDispatcher dis = request.getRequestDispatcher("./order.jsp");
			dis.forward(request, response);
		}
	}
	
}
