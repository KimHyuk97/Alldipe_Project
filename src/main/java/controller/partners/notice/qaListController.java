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
import dto.scmDTO.scmDTO;
import service.boardService.QAservice;
import service.paging.paging;
import service.paging.pagingService;

@WebServlet("/partners/notice/qalist")
public class qaListController extends HttpServlet{

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
		HttpSession session = request.getSession();
		scmDTO scm = (scmDTO)session.getAttribute("scm");
		
		if(scm!=null){
			
			String cate = "";
			int pageNo = 1;
			
			if(request.getParameter("no")!=null && !request.getParameter("no").equals("")){
				pageNo = Integer.parseInt(request.getParameter("no"));
			}
			
			
			QAservice qs = new QAservice();
			ArrayList<qaDTO> list = new ArrayList<>();
			
			paging p = pagingService.getPage(pageNo, 15);
			
			if(request.getParameter("cate")== null || request.getParameter("cate").equals("") || request.getParameter("cate").equals("all")){
				p.setTotalCount(qs.getTotalQaCnt(true, "1:1문의"));
				list = qs.getQaList(true, "1:1문의", p);
			}else{
				cate = request.getParameter("cate");
				p.setTotalCount(qs.getTotalQaCnt(true, "1:1문의", cate));
				list = qs.getQaList(true, "1:1문의", cate, p);
			}
			
			
			session.setAttribute("qaList", list);
			session.setAttribute("paging", p);
			

			RequestDispatcher dis = request.getRequestDispatcher("./adminqalist.jsp");
			dis.forward(request, response);
			
		}else{
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('잘못된 접근입니다!');");
			out.println("location.href='../Main';");
			out.println("</script>");
		}
		
		
		
	}
	
}
