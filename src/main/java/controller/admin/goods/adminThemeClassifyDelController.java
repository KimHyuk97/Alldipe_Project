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
 * Servlet implementation class adminThemeClassifyDelController
 */
@WebServlet("/admin/goods/themeClassifyDel")
public class adminThemeClassifyDelController extends HttpServlet {
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
		
		adminGoodsService ags = new adminGoodsService();
				
		int sno = Integer.parseInt(request.getParameter("themeSno"));
		
		int chk = ags.themeDel(sno);
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		
		if(chk>0){
			out.println("alert('삭제되었습니다.!');");
			out.println("location.href='./goodsClassify?page=2'");
		}else{
			out.println("alert('실패..');");
			out.println("window.history.back();");
		}
		out.println("</script>");
		
			
	}

}

