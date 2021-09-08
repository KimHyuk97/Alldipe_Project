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

import service.memberService.memberDeleteService;

/**
 * Servlet implementation class memberDeleteController
 */
@WebServlet("/memberDelete")
public class memberDeleteController extends HttpServlet {
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
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		int no = Integer.parseInt(request.getParameter("memNo"));
		String[] reason = request.getParameterValues("reason");
		StringBuffer reasons = new StringBuffer();    //배열을 하나의 String으로 보냄
	     for(String i : reason){                        //배열을 하나의 String으로 합침
	    	 reasons.append(i).append(" ");
	     }		
		String reasonDesc = request.getParameter("reasonDesc");
		
		//현재시간
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd hh:mm:ss");
		Date time = new Date();				
		String time1 = format.format(time);	
		memberDeleteService md = new memberDeleteService();
		
		int memberDeleteCkout = md.memberDeleteReason(id,pw,no,reasons,reasonDesc,time1);
		
		if(memberDeleteCkout>0) {
			int memberDeleteFl = md.memberDeleteFl(id);
			if(memberDeleteFl>0) {
				session.invalidate();
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}
	
}
