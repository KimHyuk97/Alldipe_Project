package controller.adminController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.adminDTO.adminDTO;
import dto.boardDTO.qaDTO;
import dto.goodsDTO.goodsDTO;
import dto.memberDTO.memberDTO;
import dto.orderDTO.orderDTO;
import dto.orderDTO.orderGoods;
import service.adminService.adminLoginService;

@WebServlet("/admin/loginAction")
public class adminLoginController extends HttpServlet{

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
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		adminLoginService als = new adminLoginService();
		adminDTO dto = als.adminLoginService(id,pw);		
				
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(dto!=null){
			
			HttpSession session = request.getSession();
			session.setAttribute("admin", dto);
			
			response.sendRedirect("./Main");
		}else{
			out.println("<script>");
			out.println("alert('등록되지 않은 계정입니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		
		
	}
	
	
}
