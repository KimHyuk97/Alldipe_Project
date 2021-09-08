package controller.admin.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.adminService.adminMemberService;

/**
 * Servlet implementation class adminMemberSleepFlController
 */
@WebServlet("/admin/member/sleepFl")
public class adminMemberSleepFlController extends HttpServlet {
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
		adminMemberService am = new adminMemberService();
		
		String[] noList = request.getParameterValues("memNo");
		
		int sleepFl = 0;
		
		for(int i = 0; i < noList.length; i++) {
			int memNo = Integer.parseInt(noList[i]);
			sleepFl = am.memberSleepFl(memNo);
		}
		
		int msg = 2;
		if(sleepFl > 0) {
			msg = 1;
		}else {
			msg = 2;
		}
		
		out.print(msg);	
	}

}
