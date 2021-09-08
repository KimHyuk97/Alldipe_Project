package controller.admin.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.memberDTO.memberDTO;
import dto.memberDTO.membergradeDTO;
import dto.memberDTO.memberhackoutDTO;
import dto.memberDTO.memberhistoryDTO;
import service.adminService.adminMemberService;

/**
 * Servlet implementation class adminMemberModifyController
 */
@WebServlet("/admin/member/adminMemberMod")
public class adminMemberModifyController extends HttpServlet {
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
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		
		memberDTO info = am.getMember(memNo);
		int grade = 0;
		if(request.getParameter("grade") != null && request.getParameter("grade") != "") {
			grade = Integer.parseInt(request.getParameter("grade"));
			membergradeDTO g = am.getGrade(grade); 
			request.setAttribute("grade", g);
		}
		
		List<memberhistoryDTO> mh = am.memberhistoryList(memNo);
		
		List<memberhackoutDTO> md = am.memberhackoutList(memNo);
		
		request.setAttribute("hackout", md);
		request.setAttribute("history", mh);
		request.setAttribute("info", info);
		RequestDispatcher dis = request.getRequestDispatcher("./memberMod.jsp");
		dis.forward(request, response);
	}

}
