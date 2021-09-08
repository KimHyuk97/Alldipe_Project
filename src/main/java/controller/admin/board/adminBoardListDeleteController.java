package controller.admin.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.adminService.adminBoardService;
import service.adminService.adminMemberService;

/**
 * Servlet implementation class adminBoardListDeleteController
 */
@WebServlet("/admin/board/boardDel")
public class adminBoardListDeleteController extends HttpServlet {
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
		
		adminBoardService ab = new adminBoardService();
		
		String[] sno = request.getParameterValues("sno");
		
		int del = 0;
		for(int i = 0; i <sno.length; i++) {
			int no = Integer.parseInt(sno[i]);
			del = ab.boardDel(no);
		}
		
		PrintWriter out = response.getWriter();
		int msg = 0;
		if(del > 0) {
			msg = 1;
		}else {
			msg = 0;
		}
		
		out.print(msg);	
	}

}
