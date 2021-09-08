package controller.memberController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.memberService.memberIdFindService;
import service.memberService.memberPwFindService;


@WebServlet("/pwFind")
public class memberPwFindController extends HttpServlet {
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
	
		memberPwFindService mpfs = new memberPwFindService();
		
		String userEmailVal = request.getParameter("userEmailVal");
		String userPhoneVal = request.getParameter("userPhoneVal");
		
		String pwFind = "";
		
		if(userEmailVal != null) {
			pwFind = mpfs.memberPwFindService(userEmailVal);
		}
		
		if(userPhoneVal != null) {
			pwFind = mpfs.memberPwFindService2(userPhoneVal);
		}
		
		System.out.println("idFind= " + pwFind);
		PrintWriter out = response.getWriter();
		out.print(pwFind);		
	}

}
