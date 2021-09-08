package controller.partnerController;

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

import dto.categoryDTO.categoryDTO;
import dto.goodsDTO.goodsDTO;
import dto.scmDTO.scmDTO;
import service.goodsService.goodsService;
import service.partnerService.goodsListService;

@WebServlet("/partners/goods/list")
public class goodsListController extends HttpServlet {

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
		
		goodsService gs = new goodsService();
		ArrayList<categoryDTO> cateList = gs.getAllCategory();
		session.setAttribute("cateList", cateList);
		
		if(scm != null){
		
			RequestDispatcher dis = request.getRequestDispatcher("./update.jsp");
			dis.forward(request, response);
		}else{
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다!');");
			out.println("location.href='../Main';");
			out.println("</script>");
		}
		
	}
	
}
