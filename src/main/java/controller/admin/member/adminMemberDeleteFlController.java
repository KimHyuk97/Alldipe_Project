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
 * Servlet implementation class adminMemberDeleteFlController
 */
@WebServlet("/admin/member/deleteFl")
public class adminMemberDeleteFlController extends HttpServlet {
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
		
		adminMemberService am = new adminMemberService();
		
		String[] noList = request.getParameterValues("memNo");
		
		int del = 0;
		for(int i = 0; i <noList.length; i++) {
			int memNo = Integer.parseInt(noList[i]);
			del = am.memberDel(memNo);
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
