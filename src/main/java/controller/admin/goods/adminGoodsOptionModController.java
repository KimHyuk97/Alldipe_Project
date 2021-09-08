package controller.admin.goods;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.goodsDTO.goodsOptionDTO;
import service.adminService.adminGoodsService;

/**
 * Servlet implementation class adminGoodsOptionModController
 */
@WebServlet("/admin/goods/goodsOptionMod")
public class adminGoodsOptionModController extends HttpServlet {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		adminGoodsService ags = new adminGoodsService();
		System.out.println("1");
		int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
		List<goodsOptionDTO> o = ags.getOption(goodsNo);
		
		request.setAttribute("goodsNo", goodsNo);
		request.setAttribute("o", o);
		RequestDispatcher dis = request.getRequestDispatcher("./goodsOptionMod.jsp");
		dis.forward(request, response);
	}

}
