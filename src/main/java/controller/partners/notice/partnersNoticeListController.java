package controller.partners.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.boardDTO.noticeDTO;
import service.boardService.boardService;
import service.paging.paging;
import service.paging.pagingService;

@WebServlet("/partners/notice/list")
public class partnersNoticeListController extends HttpServlet {

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
		
		int pageNo = 1;
		String theme = "all";
		
		if(!request.getParameter("no").equals("")){
			pageNo = Integer.parseInt(request.getParameter("no"));
		}
		if(!request.getParameter("theme").equals("all")){
			theme = request.getParameter("theme");
		}
		
		paging p = pagingService.getPage(pageNo, 15);
		
		boardService ns = new boardService();
		ArrayList<noticeDTO> list = new ArrayList<>();
		
		if(theme.equals("all")){
			//	전체 불러오기
			p.setTotalCount(ns.getTotalNoticeCnt(true));
			System.out.println("list count : " + ns.getTotalNoticeCnt(true));
			list = ns.getNoticeList(true, p);
		}else{
			//	테마별 불러오기
			p.setTotalCount(ns.getTotalNoticeCnt(true, theme));
			
			System.out.println("list count : " + ns.getTotalNoticeCnt(true, theme));
			list = ns.getNoticeList(true, theme, p);
		}
		
		System.out.println("list size : " + list.size());
		
		HttpSession session = request.getSession();
		session.setAttribute("nList", list);
		session.setAttribute("paging", p);
		
		RequestDispatcher dis = request.getRequestDispatcher("./list.jsp");
		dis.forward(request, response);
		
	}
	
}
