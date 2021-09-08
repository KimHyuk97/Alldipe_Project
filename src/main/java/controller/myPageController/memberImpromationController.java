package controller.myPageController;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.myPageService.myPageMemberImpromationModifyService;

/**
 * Servlet implementation class memberImpromationController
 */
@WebServlet("/memberImpromationModify")
public class memberImpromationController extends HttpServlet {
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
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		myPageMemberImpromationModifyService mmim = new myPageMemberImpromationModifyService();
		
		String pwCheck =  mmim.pwCheck(id,pw);
		
		PrintWriter out = response.getWriter();
		out.print(pwCheck);		
				
	}

}
