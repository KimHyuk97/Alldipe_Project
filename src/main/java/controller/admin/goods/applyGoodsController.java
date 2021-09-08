package controller.admin.goods;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.goodsDTO.goodsDTO;
import service.adminService.adminGoodsService;

@WebServlet("/admin/goods/applyGoods")
public class applyGoodsController extends HttpServlet{

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
		
		if(request.getParameter("goodsNo").equals("")){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('잘못된 접근입니다!');");
			out.println("location.href='./applyList';");
			out.println("</script>");
		}else{
			int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
			
			adminGoodsService ags = new adminGoodsService();
			
			goodsDTO dto = ags.getApplyGoods(goodsNo);
			
			HttpSession session = request.getSession();
			session.setAttribute("goods", dto);
			
			RequestDispatcher dis = request.getRequestDispatcher("./applyGoods.jsp");
			dis.forward(request, response);
		}
		
	}
	
}
