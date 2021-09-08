package controller.admin.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.adminService.adminBoardService;

/**
 * Servlet implementation class answerController
 */
@WebServlet("/admin/board/answer")
public class answerController extends HttpServlet {
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
		adminBoardService ab = new adminBoardService();	
		
		int sno = Integer.parseInt(request.getParameter("sno"));
		int adminNo = Integer.parseInt(request.getParameter("adminNo"));
		String answerContent = request.getParameter("answerContent");
		
		int update = ab.answerInsert(adminNo,answerContent,sno);
		
		PrintWriter out = response.getWriter();
		if(update > 0) {			
			out.println("<script>");
			out.println("alert('등록되었습니다.');");
			out.println("window.close();");
			out.println("</script>");
		}else{
			out.println("<script>");
			out.println("alert('실패하였습니다');");
			out.println("window.close();");
			out.println("</script>");
		}
		
	}

}
