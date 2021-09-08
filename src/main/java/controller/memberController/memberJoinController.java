package controller.memberController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.memberService.memberJoinService;


@WebServlet("/memberJoin")
public class memberJoinController extends HttpServlet {
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
		
		String memId = request.getParameter("userId");
		String memPw = request.getParameter("userPw");
		String memName = request.getParameter("userName");
		String email = request.getParameter("userEmail");
		String cellphone = request.getParameter("userPhone");
		String zonecode = request.getParameter("zonecode");
		String address = request.getParameter("address");
		String addressSub = request.getParameter("addressSub");
		String birthDt = request.getParameter("birthday");
		String recommId = request.getParameter("evnetId");
		String gender = request.getParameter("gender");
		int privateUtilizationFl = Integer.parseInt(request.getParameter("privateUtilizationFl"));
		int privateApprovalFl = Integer.parseInt(request.getParameter("privateApprovalFl"));
		int privateFinanceFl = Integer.parseInt(request.getParameter("privateFinanceFl"));
		int privateOfferFl = Integer.parseInt(request.getParameter("privateOfferFl"));
		int under14ConsentFl = Integer.parseInt(request.getParameter("under14ConsentFl"));		
		
		//현재시간
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd hh:mm:ss");
		Date time = new Date();				
		String time1 = format.format(time);	
				
		
		memberJoinService mjs = new memberJoinService(); 
		
		int join = mjs.memberJoin(memId,memPw,memName,gender,email,cellphone,zonecode,address,addressSub,birthDt,recommId,privateUtilizationFl,privateApprovalFl,privateFinanceFl,privateOfferFl,under14ConsentFl,time1);
		
		if(join>0){
			int de = mjs.memberDelivery(memId,zonecode,address,addressSub,time1);
			if(de > 0) {				
				response.sendRedirect("member/memberJoinSueecss.jsp");
			}
		}else {
			System.out.println("놉!");
		}
		
	}

}
