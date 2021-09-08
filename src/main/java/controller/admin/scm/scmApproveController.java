package controller.admin.scm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.adminService.adminScmService;

@WebServlet("/admin/scm/scmApprove")
public class scmApproveController extends HttpServlet{

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
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if(request.getParameter("scmNo").equals("")){
			
			out.println("alert('잘못된 접근입니다!');");
			out.println("location.href='../../adminIndex.jsp';");
			
		}else{
			int scmNo = Integer.parseInt(request.getParameter("scmNo"));
			
			adminScmService ss = new adminScmService();
			int chk = ss.approveScm(scmNo);
			
			if(chk>0){
				out.println("alert('입점 승인 완료');");
				out.println("location.href='./list';");
			}else{
				out.println("alert('승인 실패...');");
				out.println("location.href='./list';");
			}
			
		}
		out.println("</script>");
	}
	
	
}
