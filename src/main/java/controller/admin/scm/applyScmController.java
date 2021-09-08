package controller.admin.scm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.scmDTO.scmDTO;
import service.adminService.adminScmService;

@WebServlet("/admin/scm/applyScmDetails")
public class applyScmController extends HttpServlet {

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
		
		
		if(request.getParameter("scmNo").equals("")){
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("location.href='../adminIndex.jsp';");
			out.println("</script>");
		}else{
			
			int scmNo = Integer.parseInt(request.getParameter("scmNo"));
			
			adminScmService ss = new adminScmService();
			
			scmDTO dto = ss.applyScm(scmNo);
			
			HttpSession session = request.getSession();
			session.setAttribute("scm", dto);
			
			RequestDispatcher dis = request.getRequestDispatcher("./scmDetails.jsp");
			dis.forward(request, response);
		}
		
	}

}
