package controller.partners.scm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.memberDTO.memberDTO;
import dto.scmDTO.scmDTO;
import service.partners.user.partnersScmService;


@WebServlet("/partners/businessCertification")
public class certificationController extends HttpServlet {
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
		
		// 회원정보 불러오기
		HttpSession session = request.getSession();
		memberDTO mdto = (memberDTO)session.getAttribute("mem");
		
		scmDTO sdto = new scmDTO();
		
		sdto.setCompanyNm(request.getParameter("companyNm"));
		sdto.setManagerNo(mdto.getMemNo());
		sdto.setCeoNm(request.getParameter("ceoNm"));
		sdto.setBusinessNo(request.getParameter("businessNo"));
		sdto.setOnlineOrderNo(request.getParameter("onlineOrderNo"));
		sdto.setService(request.getParameter("service"));
		sdto.setItem(request.getParameter("item"));
		sdto.setEmail(request.getParameter("email"));
		sdto.setZonecode(request.getParameter("zonecode"));
		sdto.setAddress(request.getParameter("address"));
		sdto.setAddressSub(request.getParameter("addressSub"));
		sdto.setPhone(request.getParameter("phone"));
		sdto.setCenterphone(request.getParameter("centerPhone"));
		sdto.setFax(request.getParameter("fax"));
		sdto.setAccount(request.getParameter("account"));
		
		partnersScmService pjs = new partnersScmService();
		int chk = pjs.scmInsertService(sdto);
		
		if(chk>0){
			response.sendRedirect("partner/partnerJoinSuccess.jsp");
		}else{
			System.out.println("실패");
			
		}
		
	}	
	
}
