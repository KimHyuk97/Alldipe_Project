package controller.admin.member;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.memberDTO.memberDTO;
import dto.memberDTO.memberdepositDTO;
import dto.memberDTO.membermileageDTO;
import dto.pageDTO.pageDTO;
import service.adminService.adminMemberService;

/**
 * Servlet implementation class memberDeposit
 */
@WebServlet("/admin/member/memberDeposit")
public class memberDepositController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		adminMemberService am = new adminMemberService();
		
		String kind = (request.getParameter("kind") != null && request.getParameter("kind") != "")?request.getParameter("kind"):"";
		String keyword = (request.getParameter("keyword") != null && request.getParameter("keyword") != "")?request.getParameter("keyword"):"";
		String date01 = (request.getParameter("date01") != null && request.getParameter("date01") != "")?request.getParameter("date01"):"";
		String date02 = (request.getParameter("date02") != null && request.getParameter("date02") != "")?request.getParameter("date02"):"";
		String handleMode = (request.getParameter("handleMode") != null && request.getParameter("handleMode") != "")?request.getParameter("handleMode"):"??????";
		BigDecimal price01 =  (request.getParameter("price01") != null && request.getParameter("price01") != "")?new BigDecimal(request.getParameter("price01")):new BigDecimal(0);
		BigDecimal price02 =  (request.getParameter("price02") != null && request.getParameter("price02") != "" )?new BigDecimal(request.getParameter("price02")):new BigDecimal(0);
	
		
		// page ??????
		int page = 1; // ????????? ??????
		int page1 = 0;
		int page2 = 0;
		
		// null ?????? ???????????? ????????? ?????? ?????? ???????????? ????????? page1??? page2??? ????????????.
		if(request.getAttribute("paging")!=null) {
			page1 = (int)request.getAttribute("paging");
		}
		if(request.getParameter("page") != null && request.getParameter("page")!="") {
			page2 = Integer.parseInt(request.getParameter("page"));
		}
		
		int limit = 10; // ????????? ????????? ?????? ????????? ?????? ?????????
		if(request.getParameter("limit") != null && request.getParameter("limit") != "") {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		
		String sort = (request.getParameter("sort") != null) ? request.getParameter("sort") : "order by regDt desc";

		
		// page1?????? page2??? null??? ?????? ????????? page??? ?????? ??????????????? ??????.
		if (page2 != 0) {
			page = page2;
		} else if (page1 != 0) {
			page = page1;
		}
		
		// limit ?????? ???????????? ?????? ????????? ???????????? ?????? ???????????? ??????
		int startRow = (page - 1) * limit;
		int endRow = limit;

		//??????????????????
		List<memberdepositDTO> list = am.getDepositList(kind,keyword,handleMode,date01,date02,price01,price02,startRow,endRow,sort);	
		if(!list.isEmpty() && list != null) {
			ArrayList<memberDTO> mList = new ArrayList<>();
			for(int i = 0; i < list.size(); i++) {				
				memberDTO dto = am.getMember(list.get(i).getMemNo()); 
				mList.add(dto);
			}
			request.setAttribute("mList", mList);
		}
		
		// ?????? ????????? ???????????? ?????? ( ?????? ??? ?????? ?????? ???)
		int listCount = am.DepositListCount(kind,keyword,handleMode,date01,date02,price01,price02,startRow,endRow,sort);	
		
		// ????????? ????????? ????????? ?????? ??????
		int maxPage = (int) ((double) listCount / limit + 0.9); // ??? ???????????? ???????????? ????????? ????????? ??????
		
		// ?????? ???????????? ????????? ???????????????
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1; // => 1, 11, 21, 31, 41, . . . .
		
		// ?????? ???????????? ????????? ????????????
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
		request.setAttribute("handleMode", handleMode);
		request.setAttribute("price01", price01);
		request.setAttribute("price02", price02);
		request.setAttribute("sort", sort);
		request.setAttribute("limit", limit);
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./memberDeposit.jsp");
		dispatcher.forward(request, response);	
	}

}
