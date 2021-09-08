package controller.partnerController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.boardDTO.answerDTO;
import dto.memberDTO.memberDTO;
import service.boardService.QAservice;

@WebServlet("/partners/qa/answerQa")
public class answerQaController extends HttpServlet {

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
		
		if(request.getParameter("sno").equals("")){
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("location.href='../Main';");
			out.println("</script>");
		}else{
			
			int sno = Integer.parseInt(request.getParameter("sno"));
			
			HttpSession session = request.getSession();
			memberDTO mdto = (memberDTO)session.getAttribute("mem");
			
			answerDTO ans = new answerDTO();
			ans.setSno(sno);
			ans.setAnswerManagerId(mdto.getMemId());
			ans.setAnswerManagerNo(mdto.getMemNo());
			System.out.println("ccontroller answer : "+request.getParameter("answer"));
			ans.setAnswerContents(request.getParameter("answer"));
			
			QAservice qs = new QAservice();
			
			int chk = qs.answerQa(ans);
			
			
			if(chk>0){
				out.println("<script>");
				out.println("alert('답변이 등록되었습니다.');");
				out.println("location.href='./qa?sno=" + sno + "';");
				out.println("</script>");
			}else{
				out.println("<script>");
				out.println("alert('잘못된 접근입니다.');");
				out.println("location.href='./qa?sno=" + sno + "';");
				out.println("</script>");
			}
			
		}
		
	}

	
}
