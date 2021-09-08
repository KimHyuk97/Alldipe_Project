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
 * Servlet implementation class adminReviewDeleteFlController
 */
@WebServlet("/admin/goods/deleteFl")
public class adminReviewDeleteFlController extends HttpServlet {
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
		
		String[] sno = request.getParameterValues("sno");
		
		int deleteFl = 0;
		
		for(int i = 0; i < sno.length; i++) {
			int reviewSno = Integer.parseInt(sno[i]);
			deleteFl = ags.deleteFl(reviewSno);
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
