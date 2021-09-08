package controller.memberController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.memberService.memberModifyService;


/**
 * Servlet implementation class memberModify
 */
@WebServlet("/memberModify")
public class memberModifyController extends HttpServlet {
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
		HttpSession session = request.getSession();
	
		int no = Integer.parseInt(request.getParameter("no"));
		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");
		String email = request.getParameter("userEmail");
		String phone = request.getParameter("userPhone");
		//현재시간
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd hh:mm:ss");
		Date time = new Date();				
		String time1 = format.format(time);	
		
		memberModifyService mmim = new memberModifyService();
		
		int memModify =  mmim.memModify(no,id,pw,email,phone,time1);
		if(memModify>0) {
			session.invalidate();
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}		
	}

}
