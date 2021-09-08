package controller.admin.scm;

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

import dto.adminDTO.adminDTO;
import dto.pageDTO.pageDTO;
import dto.scmDTO.scmDTO;
import service.adminService.adminScmService;

@WebServlet("/admin/scm/scmList")
public class scmListController extends HttpServlet {

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
		
		HttpSession session = request.getSession();
		
		adminDTO admin = (adminDTO)session.getAttribute("admin");
		
		if(admin==null) {
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('관리자 전용 페이지 입니다.');");
			out.println("location.href='../login';");
			out.println("</script>");
		}else{
			adminScmService ass = new adminScmService();
				
			String kind = (request.getParameter("kind") != null && request.getParameter("kind") != "")?request.getParameter("kind"):"전체";
			String keyword = (request.getParameter("keyword") != null && request.getParameter("keyword") != "")?request.getParameter("keyword"):"";
			String date01 = (request.getParameter("date01") != null && request.getParameter("date01") != "")?request.getParameter("date01"):"";
			String date02 = (request.getParameter("date02") != null && request.getParameter("date02") != "")?request.getParameter("date02"):"";
			String scmState = (request.getParameter("scmState") != null && request.getParameter("scmState") != "")?request.getParameter("scmState"):"전체";
			System.out.println("scmState == "+scmState);
			
			// page 작업
			int page = 1; // 페이지 순번
			int page1 = 0;
			int page2 = 0;
			
			// null 값이 들어가면 오류가 뜨는 것을 방지하기 위해서 page1과 page2를 만들었음.
			if(request.getAttribute("paging")!=null) {
				page1 = (int)request.getAttribute("paging");
			}
			if(request.getParameter("page") != null && request.getParameter("page")!="") {
				page2 = Integer.parseInt(request.getParameter("page"));
			}
			
			int limit = 10; // 하나의 페이지 안에 몇개의 글이 있는지
			if(request.getParameter("limit") != null && request.getParameter("limit") != "") {
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			
			
			// page1이나 page2가 null이 아닐 경우에 page에 값을 입력해주는 작업.
			if (page2 != 0) {
				page = page2;
			} else if (page1 != 0) {
				page = page1;
			}
			
			// limit 값을 걸어놓은 만큼 범위에 해당하는 글만 가져오는 방법
			int startRow = (page - 1) * limit;
			int endRow = limit;

			ArrayList<scmDTO> list = ass.getScmList(kind,keyword,scmState,date01,date02,startRow,endRow);
			
			// 전체 게시글 가져오기 위한 ( 전체 글 개수 세는 것)
			int listCount = ass.getScmListCount(kind,keyword,scmState,date01,date02,startRow,endRow);
			
			// 최대로 필요한 페이지 개수 계산
			int maxPage = (int) ((double) listCount / limit + 0.9); // 끝 페이지를 계산하기 위해서 필요한 코드
			
			// 현재 페이지에 보여줄 시작페이지
			int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1; // => 1, 11, 21, 31, 41, . . . .
			
			// 현재 페이지에 보여줄 끝페이지
			int endPage = startPage + 10 - 1; // => 10, 20, 30, 40, . . . .
			
			if (endPage > maxPage) {
				endPage = maxPage;
			}
			
			pageDTO paging = new pageDTO();
			paging.setEndPage(endPage);
			paging.setListCount(listCount);
			paging.setMaxPage(maxPage);
			paging.setPage(page);
			paging.setStartPage(startPage);
			paging.setEndRow(endRow);
			
			
			request.setAttribute("kind", kind);
			request.setAttribute("keyword", keyword);
			request.setAttribute("date01", date01);
			request.setAttribute("date02", date02);
			request.setAttribute("scmState", scmState);
			request.setAttribute("limit", limit);
			request.setAttribute("list", list);
			request.setAttribute("paging", paging);
			RequestDispatcher dis = request.getRequestDispatcher("./scmList.jsp");
			dis.forward(request, response);
			
		}
		
		
	}
	
	
}
