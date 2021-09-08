package controller.partners.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.memberDTO.memberDTO;
import service.partners.user.partnersUserService;

@WebServlet("/partners/join/agreement")
public class partnersJoinController extends HttpServlet {

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
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		memberDTO mem = (memberDTO)session.getAttribute("mem");
		
		if(mem!=null){
		
			boolean approval1 = (request.getParameter("approval1")!=null && request.getParameter("approval1").equals("on"))?true:false;
			boolean approval2 = (request.getParameter("approval2")!=null && request.getParameter("approval2").equals("on"))?true:false;
			boolean approval3 = (request.getParameter("approval3")!=null && request.getParameter("approval3").equals("on"))?true:false;
			
			if(approval1 && approval2 && approval3){
				
				
				int memNo = mem.getMemNo();
				
				partnersUserService pas = new partnersUserService();
				int chk = pas.agreeService(memNo);
				
				session.invalidate();
				
				if(chk>0){
					//	성공
					out.println("<script>");
					out.println("alert('파트너 회원이 되신 것을 환영합니다! 다시 로그인 해주세요.');");
					out.println("location.href='../login';");
					out.println("</script>");
				}else{
					//	실패
					out.println("<script>");
					out.println("alert('파트너스 등록에 실패하셨습니다. 파트너스 로그인 페이지로 넘어갑니다.');");
					out.println("location.href='../login';");
					out.println("</script>");
				}
				
			}else{
				out.println("<script>");
				out.println("alert('약관 동의가 필요합니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			
			out.close();
		
		}else{
			out.println("<script>");
			out.println("alert('올디프 회원이 아닙니다.');");
			out.println("location.href='../../login';");
			out.println("</script>");
			
			out.close();
		}
		
	}
	
	
}
