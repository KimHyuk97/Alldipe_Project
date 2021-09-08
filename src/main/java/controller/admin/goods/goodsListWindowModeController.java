package controller.admin.goods;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.brandDTO.brandDTO;
import dto.categoryDTO.categoryDTO;
import dto.goodsDTO.goodsDTO;
import dto.pageDTO.pageDTO;
import dto.scmDTO.scmDTO;
import service.adminService.adminGoodsService;

/**
 * Servlet implementation class goodsListWindowModeController
 */
@WebServlet("/admin/goods/searchGoodsList")
public class goodsListWindowModeController extends HttpServlet {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		adminGoodsService ags = new adminGoodsService();
		
		//parameter 작업
		String kind = "";
		String keyword = "";
		
		if(request.getParameter("keyword") != null && request.getParameter("keyword") != "") {
			keyword = request.getParameter("keyword");		
			if(request.getParameter("kind") != null) {
				kind = request.getParameter("kind");
			}
		}
		

		String minDate = "";
		String maxDate = "";
		
		if(request.getParameter("minDate") != null && request.getParameter("minDate") != "") {
			minDate = request.getParameter("minDate")+" 00:00:00";
		}
		if(request.getParameter("maxDate") != null && request.getParameter("maxDate") != "") {
			minDate = request.getParameter("maxDate")+" 00:00:00";
		}
				
		
		String cate = "";
		String cate2 = "";
		String cate3 = "";
		String cateCd = "";
		if(request.getParameter("cate") != null || request.getParameter("cate2") != null || request.getParameter("cate3") != null) {			
			if(request.getParameter("cate") != null) {				
				cate = request.getParameter("cate");
				//대
				categoryDTO c1 = ags.getCategory(cate);
				request.setAttribute("c1", c1);
			}
			if(request.getParameter("cate2") != null) {				
				cate2 = request.getParameter("cate2");
				//중
				categoryDTO c2 = ags.getCategory(cate2);
				request.setAttribute("c2", c2);
			}
			if(request.getParameter("cate3") != null) {				
				cate3 = request.getParameter("cate3");
				//소
				categoryDTO c3 = ags.getCategory(cate3);
				request.setAttribute("c3", c3);
			}

			if(cate.length() < cate2.length()) {
				if(cate2.length() < cate3.length()) {
					cateCd = cate3;
				}else {
					cateCd = cate2;
				}
			}else if(cate.length() < cate3.length()) {
				cateCd = cate3;
				
			}else {
				cateCd = cate;
			}
				
		}
		
		String brand = "";
		if(request.getParameter("brand") != null && request.getParameter("brand") != "") {
			brand = request.getParameter("brand");
			brandDTO b = ags.getBrand(brand); 
			request.setAttribute("brand", b);
		}
		
		int minPrice = 0;
		int maxPrice = 0;
		
		if(request.getParameter("minPrice") != null && request.getParameter("minPrice") != "") {
			minPrice = Integer.parseInt(request.getParameter("minPrice"));
		}
		if(request.getParameter("maxPrice") != null && request.getParameter("maxPrice") != "") {
			maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
		}
		
		
		String goodsStatus = "";
		if(request.getParameter("goodsStatus") != null && request.getParameter("goodsStatus") != "") {
			goodsStatus = request.getParameter("goodsStatus");
		}
		
		String search = "order by regDt desc";
		if(request.getParameter("search") != null && request.getParameter("search") != "") {
			search = request.getParameter("search");
		}
		
		
		//parameter작업 end
		
		
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

		//상품리스트
		List<goodsDTO> list = ags.searchGoodsList(kind,keyword,minDate,maxDate,cateCd,brand,minPrice,maxPrice,goodsStatus,search,startRow,endRow);	
		
		// 전체 게시글 가져오기 위한 ( 전체 글 개수 세는 것)
		int listCount = ags.searchGoodsCount(kind,keyword,minDate,maxDate,cateCd,brand,minPrice,maxPrice,goodsStatus);
		
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
		
		scmDTO scm = new scmDTO();
		if(kind != null && keyword != null && kind == "공급사명") {
			scm = ags.scmList(keyword);
		}
		
				
		request.setAttribute("search",search);
		request.setAttribute("limit",limit);
		request.setAttribute("listCount",listCount);
		request.setAttribute("kind", kind);
		request.setAttribute("keyword", keyword);
		request.setAttribute("minDate", minDate);
		request.setAttribute("maxDate", maxDate);
		request.setAttribute("cateCd", cateCd);
		request.setAttribute("minPrice", minPrice);
		request.setAttribute("maxPrice", maxPrice);
		request.setAttribute("goodsStatus", goodsStatus);
		request.setAttribute("gd", list);
		request.setAttribute("paging", paging);

		RequestDispatcher dis = request.getRequestDispatcher("../search/goodsSearch.jsp");
		dis.forward(request, response);
		
	}

}
