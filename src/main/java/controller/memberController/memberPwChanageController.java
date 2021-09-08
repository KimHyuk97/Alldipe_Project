package controller.memberController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.memberService.memberPwChanageService;

/**
 * Servlet implementation class memberPwChanageController
 */
@WebServlet("/pwChanage")
public class memberPwChanageController extends HttpServlet {
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
		
		memberPwChanageService mpcs = new memberPwChanageService();
		
		String userPw = request.getParameter("pw");
		String way = request.getParameter("way");
		
		int pwChanage = 0;
		
		if(way.indexOf("@")!= -1) {
			pwChanage = mpcs.memberPwChanageService(userPw,way);
		}else {
			pwChanage = mpcs.memberPwChanageService2(userPw,way);
		}
		
		System.out.println("pwChanage=="+pwChanage);
		
		if(pwChanage>0) {
			response.sendRedirect("./member/memberLogin.jsp");
		}else{
			request.setAttribute("Fail", "fail");
			response.sendRedirect("./member/memberPwFind.jsp");
		}
		
	
	}

}
