package controller.admin.goods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import dto.boardDTO.reviewDTO;
import dto.goodsDTO.goodsDTO;
import dto.pageDTO.pageDTO;
import service.adminService.adminGoodsService;
import service.goodsService.goodsService;

/**
 * Servlet implementation class goodsReviewController
 */
@WebServlet("/admin/goods/goodsReview")
public class adminGoodsReviewController extends HttpServlet {
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
		adminGoodsService ags = new adminGoodsService();
		
		// page 작업
		int page = 1; // 페이지 순번
		int limit = 10; // 하나의 페이지 안에 몇개의 글이 있는지
		int page1 = 0;
		int page2 = 0;
		
		// null 값이 들어가면 오류가 뜨는 것을 방지하기 위해서 page1과 page2를 만들었음.
		if(request.getAttribute("paging")!=null) {
			page1 = (int)request.getAttribute("paging");
		}
		if(request.getParameter("page") != null && request.getParameter("page")!="") {
			page2 = Integer.parseInt(request.getParameter("page"));
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
					
		int potoFl = 2;
		String poto = "";
		String kind = "";
		String keyword = "";
		int goodsPt = 6;
		
		//포토리뷰가 있는지 없는지
		if(request.getParameter("poto") != null) {		
			potoFl =  Integer.parseInt(request.getParameter("poto"));
		}
				
		//상품평점이 있는지 없는지
		if(request.getParameter("goodsPt") != null) {
			goodsPt = Integer.parseInt(request.getParameter("goodsPt"));
		}
		
		//검색어가 있는지 없는지
		if(request.getParameter("keyword") != null && request.getParameter("keyword") != "") {
			keyword = request.getParameter("keyword");		
			if(request.getParameter("kind") != null) {
				kind = request.getParameter("kind");
			}
		}
				
		List<reviewDTO> rw = new ArrayList<reviewDTO>();
		List<goodsDTO> gd = new ArrayList<goodsDTO>();
		

		List<String> goodsNo = new ArrayList<String>();
		if(kind.equals("상품명") || kind.equals("공급사명")) {				
			gd = ags.goodsNoList(kind,keyword);
			for(int i = 0; i < gd.size(); i++) {
				goodsNo.add(Integer.toString(gd.get(i).getGoodsNo()));
			}

		}
		String gNo = goodsNo.toString();			
		String gNo2 = gNo.substring(1);
		String gNo3 = StringUtils.substring(gNo2, 0, -1);		
		
		
		rw = ags.reviewSearchResult(goodsPt,potoFl,kind,keyword,gNo3,startRow,endRow);

		
		// 전체 게시글 가져오기 위한 ListCount메소드 호출 ( 전체 글 개수 세는 것)
		int listCount = ags.reviewSearchResultCount(goodsPt,potoFl,kind,keyword,gNo3);
		
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
		
		System.out.println("poto == "+potoFl);
		
		request.setAttribute("kind", kind);
		request.setAttribute("keyword", keyword);
		request.setAttribute("goodsPt", goodsPt);
		request.setAttribute("poto", potoFl);
		
		request.setAttribute("paging", paging);
		request.setAttribute("rw", rw);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./goodsReview.jsp");
		dispatcher.forward(request, response);			
		
		
	}

}
