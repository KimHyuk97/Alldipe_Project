package controller.memberController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.memberService.memberEmailCheckService;


/**
 * Servlet implementation class memberEmailCheckController
 */
@WebServlet("/memberEmailCheck")
public class memberEmailCheckController extends HttpServlet {

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
			
			String userEmail = request.getParameter("userEmail");
			
			memberEmailCheckService mics = new memberEmailCheckService();
			
			String emailBol = mics.memberEmailCheck(userEmail);
			
			//ajax 데이터 내보낼때
			PrintWriter out = response.getWriter();

			out.print(emailBol);
			
		}

}
