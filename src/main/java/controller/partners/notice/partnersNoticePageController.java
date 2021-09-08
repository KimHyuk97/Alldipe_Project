package controller.partners.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.boardDTO.noticeDTO;
import service.boardService.boardService;

@WebServlet("/partners/notice/notice")
public class partnersNoticePageController extends HttpServlet{

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
		
		System.out.println(request.getParameter("no"));
		
		int sno = Integer.parseInt(request.getParameter("no"));
		
		
		boardService gns = new boardService();
		noticeDTO dto = gns.getNotice(sno);
		
		HttpSession session = request.getSession();
		session.setAttribute("notice", dto);
		
		RequestDispatcher dis = request.getRequestDispatcher("./notice.jsp");
		dis.forward(request, response);
		
	}
	
}
