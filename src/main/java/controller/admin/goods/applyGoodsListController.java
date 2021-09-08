package controller.admin.goods;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.goodsDTO.goodsDTO;
import service.adminService.adminGoodsService;

@WebServlet("/admin/goods/applyList")
public class applyGoodsListController extends HttpServlet {

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
		adminGoodsService ags = new adminGoodsService();
		
		ArrayList<goodsDTO> list = ags.getGoodsList("a");
		
		HttpSession session = request.getSession();
		session.setAttribute("goodsList", list);
		
		RequestDispatcher dis = request.getRequestDispatcher("./applyList.jsp");
		dis.forward(request, response);
		
	}
	
}
