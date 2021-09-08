package controller.searchController;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import dto.searchDTO.searchDTO;
import service.searchService.searchService;

/**
 * Servlet implementation class searchController
 */
@WebServlet("/search")
public class searchController extends HttpServlet {
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
		searchService search = new searchService();
		
		String keyword = request.getParameter("keyword");
		String siteKey;
		
		if(request.getParameter("siteKey") != null) {
			siteKey = request.getParameter("siteKey");
		}else {
			siteKey = null;
		}
		
		int memNo;
		if(request.getParameter("memNo") != null) {
			memNo = Integer.parseInt(request.getParameter("memNo"));			
		}else {
			memNo = 0;
		}
		
		if((siteKey != null || siteKey != "") && memNo >= 0) {		
			//현재시간
			SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd hh:mm:ss");
			Date time = new Date();				
			String time1 = format.format(time);	
			
			
			searchDTO ss = search.searchSelect(keyword);
			if(ss.getKeyword() != null) {
				//조회해서 검색한 결과가 있으면 카운트를 올려주고 날짜도 업데이트 시켜준다
				int sCnt = search.searchCntUp(ss.getKeyword(),ss.getKeywordCnt(),time1);
			}else{
				//조회한 결과가 없으면 새로 생성시켜준다.
				int sa = search.searchAdd(siteKey,memNo,keyword,time1);
			}
		}
			
		String cateCd;
		String brandCd;
		int reviewCnt;
		BigDecimal price;
		BigDecimal price2;
		
		if(request.getParameter("cateCd") == null) {
			cateCd = "";
		}else {
			cateCd = request.getParameter("cateCd");
		}

		if(request.getParameter("brandCd") == null) {
			brandCd = "";
		}else {
			brandCd = request.getParameter("brandCd");
		}
		
		if(request.getParameter("reviewCnt") == null) {
			reviewCnt = 0;
		}else {
			reviewCnt = Integer.parseInt(request.getParameter("reviewCnt"));
		}
		
		if(request.getParameter("price") == null) {
			price = BigDecimal.valueOf(0);
		}else {			
			price = new BigDecimal(request.getParameter("price"));
		}

		if(request.getParameter("price2") == null) {
			price2 = BigDecimal.valueOf(0);
		}else {			
			price2 = new BigDecimal(request.getParameter("price2"));
		}
				
		System.out.println("cateCd = "+cateCd);
		System.out.println("brandCd = "+brandCd);
		System.out.println("reviewCnt = "+reviewCnt);
		System.out.println("price = "+price);
		System.out.println("price2 = "+price2);
		
		
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
		int listCount = search.listCount(keyword,cateCd,brandCd,reviewCnt,price,price2);

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
		
		
		//키워드검색
		List<goodsDTO> sg = search.searchResultList(keyword,startRow,endRow,cateCd,brandCd,reviewCnt,price,price2);
		
		//검색한 결과가 있다면
		if(!sg.isEmpty()) {
			List<categoryDTO> cd = new ArrayList<categoryDTO>();
			for(int k = 0; k < sg.size(); k++) {
				//카테고리 들고오기
				if(sg.get(k).getCateCd() != "" || sg.get(k).getCateCd() != null) {
					categoryDTO cc = search.searchCateList(sg.get(k).getCateCd());
					if(cc != null) {
						cd.add(cc);						
					}
				}
			}
			request.setAttribute("cate", cd);
			
			List<brandDTO> bd = new ArrayList<brandDTO>();
			for(int w = 0; w < sg.size(); w++) {
				//브랜드 들고오기			
				if(sg.get(w).getBrandCd() != "" || sg.get(w).getBrandCd() != null) {
					brandDTO bb = search.searchBrand(sg.get(w).getBrandCd());
					if(bb != null) {						
						bd.add(bb);
					}
				}
			}
			request.setAttribute("brand", bd);
		}
		
		
		request.setAttribute("keyword", keyword);
		request.setAttribute("listCount", listCount);
		request.setAttribute("paging", paging);	
		request.setAttribute("sg", sg);
		RequestDispatcher dispatcher = request.getRequestDispatcher("search/search.jsp");
		dispatcher.forward(request, response);			
		
		

		
	}

}
