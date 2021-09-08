package controller.partners.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.memberDTO.memberDTO;

@WebServlet("/partners/join/approval")
public class partnersAgreementController extends HttpServlet{

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
			RequestDispatcher dis = request.getRequestDispatcher("./partnerJoin.jsp");
			dis.forward(request, response);
		}else{
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('로그인이 필요한 페이지입니다.');");
			out.println("location.href='../login';");
			out.println("</script>");
			
			out.close();
		}
		
		
	}
	
	
}
