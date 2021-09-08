package controller.partners.calculate;

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

@WebServlet("/partners/calculate/salesStatus")
public class salesStatusController extends HttpServlet {

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
			out.println("alert('입점사 전용 페이지입니다!');");
			out.println("location.href='../login';");
			out.println("</script>");
			
			out.close();
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("./salesStatus.jsp");
		dis.forward(request, response);
		
	}
	
	
}
