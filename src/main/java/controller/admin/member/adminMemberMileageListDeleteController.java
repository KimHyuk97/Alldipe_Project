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
 * Servlet implementation class adminMemberMileageListDeleteController
 */
@WebServlet("/admin/member/mileageListDel")
public class adminMemberMileageListDeleteController extends HttpServlet {
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
		
		adminMemberService am = new adminMemberService();
		
		String[] sno = request.getParameterValues("sno");
		
		int del = 0;
		for(int i = 0; i <sno.length; i++) {
			int no = Integer.parseInt(sno[i]);
			del = am.mileageListDel(no);
		}
		
		PrintWriter out = response.getWriter();
		int msg = 2;
		if(del > 0) {
			msg = 1;
		}else {
			msg = 2;
		}
		
		out.print(msg);
		
	}

}
