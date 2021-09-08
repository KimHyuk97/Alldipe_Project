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
 * Servlet implementation class adminMemberGradeRatingController
 */
@WebServlet("/admin/member/gradeRating")
public class adminMemberGradeRatingController extends HttpServlet {
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
		
		//등급 수동평가
		int rating = am.gradeRating();
		
		PrintWriter out = response.getWriter();
		int msg = 0;
		if(rating > 0) {
			msg = 1;
		}else {
			msg = 0;
		}
		
		out.print(msg);
		
	}

}
