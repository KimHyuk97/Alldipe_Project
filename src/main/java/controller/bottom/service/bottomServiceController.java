package controller.bottom.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.boardDTO.boardDTO;
import dto.boardDTO.noticeDTO;
import service.boardService.boardService;
import service.paging.paging;
import service.paging.pagingService;

@WebServlet("/service")
public class bottomServiceController extends HttpServlet {

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
		
		String boardType = request.getParameter("type");	//	공지사항, qa구분
		String theme = "";
		if(boardType.equals("qa")){
			theme="fqa";
		}
		String cate = request.getParameter("cate");		//	카테고리 구분
		String strNo = request.getParameter("no");		//	페이징 
		
		int pageNo = 1;
		
		if(strNo!=null && !strNo.equals("")){
			pageNo = Integer.parseInt(strNo);
		}
		
		System.out.println("cate :" + cate);
		
		boardService bs = new boardService();
		
		paging p = pagingService.getPage(pageNo, 15);
		
		int total = 0;
		ArrayList<boardDTO> list = new ArrayList<>();
		
		if(cate==null || cate.equals("null") || cate.equals("")){
			total = bs.getTotalCnt(false, boardType, theme);
			p.setTotalCount(total);
			System.out.println("cnt : " + total);
			list = bs.getBoardList(false, boardType, theme, p);
		}else{
			total = bs.getTotalCnt(false, boardType, theme, cate);
			p.setTotalCount(total);
			list = bs.getBoardList(false, boardType, theme, cate, p);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("list",list);
		session.setAttribute("paging", p);
		
		RequestDispatcher dis = request.getRequestDispatcher("./bottom/service.jsp");
		dis.forward(request, response);
		
	}
	
	
}
