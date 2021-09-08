package controller.partnerController;

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
import dto.scmDTO.scmDTO;

@WebServlet("/partners/ads/bizMoney")
public class bizmoneyController extends HttpServlet {

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
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		memberDTO mem = (memberDTO)session.getAttribute("mem");
		scmDTO scm = (scmDTO)session.getAttribute("scm");
		
		if(mem == null || scm == null){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('잘못된 접근입니다!');");
			out.println("location.href='./login';");
			out.println("</script>");
			
			out.close();
		}else{
			
			RequestDispatcher dis = request.getRequestDispatcher("./bizmoney.jsp");
			dis.forward(request, response);
			
		}
	}
}
