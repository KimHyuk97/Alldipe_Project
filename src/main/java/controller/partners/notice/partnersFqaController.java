package controller.partners.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.boardDTO.qaDTO;
import service.boardService.QAservice;
import service.paging.paging;
import service.paging.pagingService;

@WebServlet("/partners/notice/fqa")
public class partnersFqaController extends HttpServlet {

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
		
		String cate = request.getParameter("cate");
		
		if(cate.equals("")){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("location.href='../Main';");
			out.println("</script>");		
		}else{
		
			int pageNo = 1; 
			if(request.getParameter("no")!=null && !request.getParameter("no").equals("")){
				pageNo = Integer.parseInt(request.getParameter("no"));
			}
			
			paging p = pagingService.getPage(pageNo, 10);
			ArrayList<qaDTO> list = new ArrayList<>();
			
			QAservice qs = new QAservice();
			if(cate.equals("all")){
				p.setTotalCount(qs.getTotalQaCnt(true, "fqa"));
				list =  qs.getQaList(true, "fqa", p);
			}else{
				p.setTotalCount(qs.getTotalQaCnt(true, "fqa", cate));
				list =  qs.getQaList(true, "fqa", cate, p);
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("qaList", list);
			session.setAttribute("paging", p);
			
			System.out.println("paging : " + p);			
			RequestDispatcher dis = request.getRequestDispatcher("./fqa.jsp");
			dis.forward(request, response);
			
		}
	}
	
}
