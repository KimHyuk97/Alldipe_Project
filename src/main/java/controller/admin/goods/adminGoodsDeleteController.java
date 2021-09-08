package controller.admin.goods;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.adminService.adminGoodsService;

/**
 * Servlet implementation class adminGoodsDeleteController
 */
@WebServlet("/admin/goods/delete")
public class adminGoodsDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		adminGoodsService ags = new adminGoodsService();
		
		String[] goodsNos = request.getParameterValues("goodsNo");
		
		int deleteFl = 0;
		
		for(int i = 0; i < goodsNos.length; i++) {
			int goodsNo = Integer.parseInt(goodsNos[i]);
			deleteFl = ags.goodsDeleteFl(goodsNo);
		}
		
		int msg = 2;
		if(deleteFl > 0) {
			msg = 1;
		}else {
			msg = 2;
		}
		
		out.print(msg);	
		
		
	}

}
