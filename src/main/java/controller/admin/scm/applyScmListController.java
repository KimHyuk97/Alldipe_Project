package controller.admin.scm;

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

import dto.adminDTO.adminDTO;
import dto.scmDTO.scmDTO;
import service.adminService.adminScmService;


@WebServlet("/admin/scm/applyScmList")
public class applyScmListController extends HttpServlet {

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
		
		
		adminDTO admin = (adminDTO)session.getAttribute("admin");
		
		if(admin==null){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('관리자 전용 페이지 입니다.');");
			out.println("location.href='../login';");
			out.println("</script>");
		}else{
			
			adminScmService ss = new adminScmService();
			ArrayList<scmDTO> list = ss.getScmList("a");
			
			System.out.println("list : " + list.size());
			
			session.setAttribute("scmList", list);
			
			RequestDispatcher dis = request.getRequestDispatcher("./applyScmList.jsp");
			dis.forward(request, response);
			
		}
				
	}
	
}
