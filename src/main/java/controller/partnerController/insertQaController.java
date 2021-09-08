package controller.partnerController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.boardDTO.qaDTO;
import dto.memberDTO.memberDTO;
import service.boardService.QAservice;

@WebServlet("/partners/qa/insertQa")
public class insertQaController extends HttpServlet {

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
		HttpSession session = request.getSession();
		memberDTO mem = (memberDTO)session.getAttribute("mem");
		
		System.out.println("request content:" + request.getParameter("ir1"));
		
		String cate = request.getParameter("cate");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String pw = request.getParameter("password");
		String sms = request.getParameter("sms");
		String email = request.getParameter("email");
		String privacyUseApproval = request.getParameter("agree01");
		String othersOfferApproval = request.getParameter("agree02");
		String ip = request.getParameter("ip");
		String file1 = request.getParameter("file1");
		
		qaDTO dto = new qaDTO();
		
		dto.setMemNo(mem.getMemNo());
		dto.setWriterNm(mem.getMemNm());
		dto.setPartnersFl(true);
		dto.setWriterId(mem.getMemId());
		dto.setWriterIp(ip);
		dto.setTheme("1:1문의");
		dto.setCate(cate);
		dto.setTitle(title);
		dto.setContents(contents);
		dto.setFile1(file1);
		
		System.out.println("contents: " + contents);
		
		if(pw.equals("")){
			dto.setSecret(false);
		}else{
			dto.setSecret(true);
			dto.setWriterPw(pw);
		}
		
		if(sms != null){
			dto.setSmsFl(true);
		}
		if(email != null){
			dto.setEmailFl(true);
		}
		if(privacyUseApproval != null){
			dto.setPrivacyuseapproval(true);
		}
		if(othersOfferApproval != null){
			dto.setOthersofferapproval(true);
		}
		
		QAservice qs = new QAservice();
		
		int chk = qs.insertQa(dto);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		if(chk>0){
			out.println("alert('등록되었습니다.');");
			out.println("location.href='./list?no=1';");
		}else{
			
			out.println("alert('실패했습니다.');");
			out.println("history.back();");
			
		}
		out.println("</script>");
	}
	
}
