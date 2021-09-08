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
import dto.scmDTO.scmDTO;
import service.memberService.memberLoginService;
import service.partnerService.getScmService;
import service.partners.user.partnersUserService;


@WebServlet("/partners/LoginAction")
public class partnersLoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
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
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		partnersUserService pls = new partnersUserService();
		int chk = pls.partnersLoginService(id,pw);
		
		System.out.println("partners Login controller chk : " + chk);
		
		HttpSession session = request.getSession();
		StringBuffer beforeURL = (StringBuffer)session.getAttribute("url");
		session.removeAttribute("url");
		System.out.println("beforeURL : "+beforeURL);
		if(chk>0){
			//	파트너 회원	
			System.out.println("파트너 회원");
			getScmService gss = new getScmService();
			scmDTO sdto = gss.getScmService(chk);
			memberLoginService mls = new memberLoginService();
			memberDTO mdto = mls.memberLoginService(id, pw);
			
			session.setAttribute("scm", sdto);
			session.setAttribute("mem", mdto);
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(beforeURL==null){
				out.println("alert('관리자 페이지로 이동합니다.');");
				out.println("location.href='./Main';");
			}else{
				out.println("location.href='" + beforeURL + "';");
			}
			out.println("</script>");
			
		}else if(chk<0){
			//	비회원
			System.out.println("비회원");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 혹은 비밀번호가 틀렸습니다.');");
			out.println("history.back();");
			out.println("</script>");
			
			
		}else{
			//	일반회원
			System.out.println("일반회원");
			memberLoginService mls = new memberLoginService();
			memberDTO mdto = mls.memberLoginService(id,pw);
			session.setAttribute("mem", mdto);
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('파트너스 회원 전환이 필요합니다.');");
			out.println("location.href='./join/approval';");
			out.println("</script>");
			
		}
		
	}

	
}
