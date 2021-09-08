package controller.partners.notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.boardDTO.qaDTO;
import service.boardService.QAservice;

@WebServlet("/partners/notice/qa")
public class qaPageController extends HttpServlet {

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
		
		if(request.getParameter("no")==null || request.getParameter("no").equals("")){
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			
			out.println("alert('잘못된 접근입니다!');");
			out.println("location.href='../Main';");
			
			out.println("</script>");
		}else{
			
			int sno = Integer.parseInt(request.getParameter("no"));
			QAservice qs = new QAservice();
			
			qaDTO dto = qs.getQa(sno);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("qa", dto);
			
			RequestDispatcher dis = request.getRequestDispatcher("./qa.jsp");
			dis.forward(request, response);
			
		}
		
	}
	
	
}
