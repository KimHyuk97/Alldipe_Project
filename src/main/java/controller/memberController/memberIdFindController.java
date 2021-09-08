package controller.memberController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.memberService.memberIdFindService;

@WebServlet("/idFind")
public class memberIdFindController extends HttpServlet {
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
		
		memberIdFindService mifs = new memberIdFindService();
		
		String userEmailVal = request.getParameter("userEmailVal");
		String userPhoneVal = request.getParameter("userPhoneVal");
		
		String idFind = "";
		
		if(userEmailVal != null) {
			idFind = mifs.memberIdFindService(userEmailVal);
		}
		
		if(userPhoneVal != null) {
			idFind = mifs.memberIdFindService2(userPhoneVal);
		}
		
		System.out.println("idFind= " + idFind);
		PrintWriter out = response.getWriter();
		out.print(idFind);		
	}

}
