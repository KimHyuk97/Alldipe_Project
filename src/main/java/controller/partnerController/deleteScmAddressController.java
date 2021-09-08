package controller.partnerController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.partnerService.getScmService;

@WebServlet("/partners/deleteAddress")
public class deleteScmAddressController extends HttpServlet {

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
		
		System.out.println("delete Controller sno:" + request.getParameter("sno"));
		
		if(request.getParameter("sno").equals("")){
			
			out.println("<script>");
			out.println("alert('선택된 항목이 없습니다!');");
			out.println("history.back();");
			out.println("</script>");
			
		}else{
			
			int sno = Integer.parseInt(request.getParameter("sno"));
			
			getScmService gss = new getScmService();
			
			int chk = gss.deleteScmAddress(sno);
			
			System.out.println("delete 된 주소 sno:" + sno);
			
			if(chk>0){
				out.println("<script>");
				out.println("alert('주소지가 삭제되었습니다.');");
				out.println("location.href='./addressList';");
				out.println("</script>");
			}
			
		}
		
		
		
		
	}
	
}
