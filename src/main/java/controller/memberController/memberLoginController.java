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

import dto.memberDTO.memberDTO;
import service.cartService.cartService;
import service.memberService.memberLoginService;

/**
 * Servlet implementation class memberLoginController
 */
@WebServlet("/memberLogin")
public class memberLoginController extends HttpServlet {
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
		
		String userId = request.getParameter("id");
		String userPw = request.getParameter("pw");
		String siteKey = request.getParameter("siteKey");
		
		memberLoginService mls = new memberLoginService();
		
		memberDTO mem = mls.memberLoginService(userId,userPw);
		
		if(mem != null) {
			HttpSession session = request.getSession();				
			
			if(mem.getMemId() !=null) {
				session.setAttribute("mem", mem);
				cartService cs = new cartService();
				
				//로그인기록 업데이트
				
				//현재시간
				SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd hh:mm:ss");
				Date time = new Date();				
				String time1 = format.format(time);	
				
				
				//ip주소
				String ipAddress = request.getParameter("ipAddress");
			    
				
				//pc,mob구분
				String device = null;
				
				int isMobile = 0;

				String agent = request.getHeader("USER-AGENT");

				String[] mobileos = {"iPhone","iPod","Android","BlackBerry","Windows CE","Nokia","Webos","Opera Mini","SonyEricsson","Opera Mobi","IEMobile"};

				int j = -1;
				for(int i=0 ; i<mobileos.length ; i++) {
					j=agent.indexOf(mobileos[i]);
					if(j > -1 ){
						// 모바일로 접근했을 때
						isMobile = 1;
						break;
					}
				}
			
				if(isMobile == 1) {
					device = "moblie";
				}else {
					device = "pc";
				}
				
				mls.memberLoginLog(mem.getMemNo(),device,ipAddress,time1);
							
				
				//로그인 하기전에 장바구니에 담았던 정보를 로그인하려는 아이디에게 줌
				cs.memberCartAdd(siteKey,mem.getMemNo());			
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("Fail", "fail");
				RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberLogin.jsp");
				dispatcher.forward(request, response);
			}
		}else {
			request.setAttribute("Fail", "fail");
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberLogin.jsp");
			dispatcher.forward(request, response);
		}
				
	}

}
