package controller.goodsController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.themeDTO.themeDTO;
import dto.goodsDTO.goodsDTO;
import dto.pageDTO.pageDTO;
import service.goodsService.goodsService;

/**
 * Servlet implementation class goodsListController
 */
@WebServlet("/goodsList")
public class goodsListController extends HttpServlet {
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
		
		PrintWriter out = response.getWriter();
		goodsService goods = new goodsService();
		
		
		String cateCd = request.getParameter("cateCd");

		// page 작업
		int page = 1; // 페이지 순번
		int limit = 12; // 하나의 페이지 안에 몇개의 글이 있는지
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

		// 전체 게시글 가져오기 위한 ListCount메소드 호출 ( 전체 글 개수 세는 것)
		int listCount = goods.listCount(cateCd);

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
		
		//상품만불러오기 상단카테고리상품
		List<goodsDTO> hg = goods.HeaderCategorygoodsList(cateCd);
				
		
		//카테고리상품
//		List<goodsDTO> gd = goods.goodsList(cateCd,startRow,endRow);			
//		request.setAttribute("paging", paging);
//		request.setAttribute("gd", gd);
		
		if (hg.isEmpty()) {
			out.println("<script>");
			out.println("alert('해당 상품이 존재하지 않습니다.');");
			out.println("location.href='main.jsp?displayFl=y'");
			out.println("</script>");
		}else {			
			request.setAttribute("hg", hg);
			if(cateCd.equals("1")) {
//				//특딜
//				RequestDispatcher dispatcher = request.getRequestDispatcher("headerCategoryGoodsList/specialGoodsList.jsp");
//				dispatcher.forward(request, response);

			}else if(cateCd.equals("2")) {
				//신상
				RequestDispatcher dispatcher = request.getRequestDispatcher("headerCategoryGoodsList/newGoodsList.jsp");
				dispatcher.forward(request, response);

			}else if(cateCd.equals("3")) {
				//베스트
				RequestDispatcher dispatcher = request.getRequestDispatcher("headerCategoryGoodsList/bestGoodsList.jsp");
				dispatcher.forward(request, response);

			}else if(cateCd.equals("4")) {
				//무료배송
				RequestDispatcher dispatcher = request.getRequestDispatcher("headerCategoryGoodsList/freeDGoodsList.jsp");
				dispatcher.forward(request, response);

			}else if(cateCd.equals("5")) {
				//페스티벌 
				
				//페스티벌 테마 가져오기
				List<themeDTO> th = new ArrayList<themeDTO>();
				
				String festival = "페스티벌";
				
				List<themeDTO> goodsNos = new ArrayList<themeDTO>();
			
				
				request.setAttribute("themeList", th);
				request.setAttribute("goodsList", goodsNos);	
				RequestDispatcher dispatcher = request.getRequestDispatcher("headerCategoryGoodsList/festivalGoodsList.jsp");
				dispatcher.forward(request, response);

			}else if(cateCd.equals("6")) {
				//이벤트
				RequestDispatcher dispatcher = request.getRequestDispatcher("headerCategoryGoodsList/eventGoodsList.jsp");
				dispatcher.forward(request, response);

			}else if(cateCd.equals("7")) {
				//브랜드관
				RequestDispatcher dispatcher = request.getRequestDispatcher("headerCategoryGoodsList/brandGoodsList.jsp");
				dispatcher.forward(request, response);

			}
		}
		
	}

}
