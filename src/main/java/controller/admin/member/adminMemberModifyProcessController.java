package controller.admin.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.memberDTO.memberDTO;
import service.adminService.adminMemberService;

/**
 * Servlet implementation class adminMemberModifyProcessController
 */
@WebServlet("/admin/member/memberModifyProcess")
public class adminMemberModifyProcessController extends HttpServlet {
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
		
		adminMemberService am = new adminMemberService();
		
		String birthDt = request.getParameter("birth");
		java.sql.Date birth = java.sql.Date.valueOf(birthDt);//스트링타입 sql로 변환
		

		memberDTO dto = new memberDTO();
		
		dto.setMemNo((request.getParameter("memNo") != null)?Integer.parseInt(request.getParameter("memNo")):0);
		dto.setMemId((request.getParameter("memId") != null)?request.getParameter("memId"):"");
		dto.setMemPw(request.getParameter("memPw") != null?request.getParameter("memPw"):null);
		dto.setMemNm((request.getParameter("memNm") != null)?request.getParameter("memNm"):"");
		dto.setGradeSno((request.getParameter("grade") != null)?Integer.parseInt(request.getParameter("grade")):0);
		dto.setAppFl((request.getParameter("apply") != null)?((Integer.parseInt(request.getParameter("apply")) == 1)?true:false):false);
		dto.setSleepFl((request.getParameter("sleepFl") != null)?((Integer.parseInt(request.getParameter("sleepFl")) == 1)?true:false):false);
		dto.setBirthDt(birth);
		dto.setDeleteFl((request.getParameter("deleteFl") != null)?request.getParameter("deleteFl"):"n");
		dto.setSexFl(request.getParameter("gender") != null?request.getParameter("gender"):"");
		dto.setEmail(request.getParameter("email") != null?request.getParameter("email"):"" );
		dto.setCellPhone(request.getParameter("cellPhone") != null?request.getParameter("cellPhone"):"");
		dto.setMaillingFl((request.getParameter("milingFl") != null)?((Integer.parseInt(request.getParameter("milingFl")) == 1)?true:false):false);
		dto.setSmsFl((request.getParameter("smsFl") != null)?((Integer.parseInt(request.getParameter("smsFl")) == 1)?true:false):false);
		dto.setZonecode(request.getParameter("zonecode") != null?request.getParameter("zonecode"):"" );
		dto.setAddress(request.getParameter("address") != null?request.getParameter("address"):"" );
		dto.setAddressSub(request.getParameter("addressSub") != null?request.getParameter("addressSub"):"" );
				
		String ip = request.getParameter("ip");
		int mod = am.memberModify(dto,ip);
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		
		if(mod > 0) {
			out.println("alert('"+dto.getMemNm().toString()+"님의 정보를 수정하였습니다!');");
			out.println("location.href='memberList'");
		}else{
			out.println("alert('수정 실패하였습니다.');");
			out.println("window.history.back();");
		}
		out.println("</script>");
		
		
	}

}
