package controller.admin.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dto.adminDTO.adminDTO;
import dto.memberDTO.membermileageDTO;
import service.adminService.adminMemberService;

/**
 * Servlet implementation class payTheMileageController
 */
@WebServlet("/admin/member/payTheMileage")
public class payTheMileageController extends HttpServlet {
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
		adminMemberService am = new adminMemberService();
		
		HttpSession session = request.getSession();
		adminDTO admin = (adminDTO)session.getAttribute("admin");	//관리자 정보
		
		membermileageDTO dto = new membermileageDTO();
		dto.setManagerNo(admin.getSno());
		dto.setHandleMode(request.getParameter("handlemode"));
		dto.setMileage(new BigDecimal(request.getParameter("price")));
		dto.setReasonCd(request.getParameter("reasonCd"));
		dto.setRegIp(request.getParameter("ip"));	
				
		String[] sno = request.getParameterValues("sno"); //회원 번호리스트
		
		int log = 0;
		for(int i = 0; i <sno.length; i++) {
			int memNo = Integer.parseInt(sno[i]);
			dto.setMemNo(memNo);
			
			BigDecimal pay = am.getMileage(dto.getMemNo());			
			dto.setBeforeMileage(pay);
			
			BigDecimal resultPay = new BigDecimal(0); 			
			//지급이면 + 차감이면 -
			if(dto.getHandleMode().equals("지급")) {
				resultPay = pay.add(dto.getMileage());
			}else if(dto.getHandleMode().equals("차감")){
				resultPay = pay.subtract(dto.getMileage());
			}
			dto.setAfterMileage(resultPay);
		
			int payTo = am.payTheMileage(dto);
			if(payTo > 0) {				
				log = am.payTheMileageLog(dto);	
			}
		}
		
		
		
			
		PrintWriter out = response.getWriter();
		int msg = 0;
		if(log > 0) {
			msg = 1;
		}else {
			msg = 0;
		}
		
		out.print(msg);
		
	}

}
