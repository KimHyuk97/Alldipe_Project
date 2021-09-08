package controller.cartController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.cartService.cartService;

/**
 * Servlet implementation class cartDeleteController
 */
@WebServlet("/cartD")
public class cartDeleteController extends HttpServlet {
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
		
		String sno = request.getParameter("sno");
		
		int chk = 0;
		
		if(sno!=null || !sno.equals("")){
			
			int no = Integer.parseInt(sno);
			
			cartService cs = new cartService();
			chk = cs.cartDelete(no);
		}
		
		PrintWriter out = response.getWriter();
		out.print(chk);
		
		System.out.println(chk);
		out.close();
	}

}
