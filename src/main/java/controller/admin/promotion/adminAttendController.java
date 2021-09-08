package controller.admin.promotion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.adminDTO.adminDTO;

/**
 * Servlet implementation class adminAttendController
 */
@WebServlet("/admin/promotion/attend")
public class adminAttendController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		
		adminDTO admin = (adminDTO)session.getAttribute("admin");
		
		if(admin==null){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('관리자 전용 페이지 입니다.');");
			out.println("location.href='../login';");
			out.println("</script>");
		}else{
			RequestDispatcher dis = request.getRequestDispatcher("./attend.jsp");
			dis.forward(request, response);
			
		}
		
	}

}
