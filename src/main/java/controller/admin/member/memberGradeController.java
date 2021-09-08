package controller.admin.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.memberDTO.membergradeDTO;
import service.adminService.adminMemberService;

/**
 * Servlet implementation class memberGrade
 */
@WebServlet("/admin/member/memberGrade")
public class memberGradeController extends HttpServlet {
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
		
		List<membergradeDTO> mg = am.getmembergrade();
		int[] memberCnt = new int[mg.size()];
		for(int i=0; i<mg.size(); i++) {
			int cnt = am.membergradeCnt(mg.get(i).getSno());
			memberCnt[i]=cnt;
		}
				
		request.setAttribute("cnt",memberCnt);
		request.setAttribute("grade", mg);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./memberGrade.jsp");
		dispatcher.forward(request, response);	
	}

}
