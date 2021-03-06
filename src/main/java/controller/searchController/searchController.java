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
			//íėŽėę°
			SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd hh:mm:ss");
			Date time = new Date();				
			String time1 = format.format(time);	
			
			
			searchDTO ss = search.searchSelect(keyword);
			if(ss.getKeyword() != null) {
				//ėĄ°ííīė ęēėí ęē°ęģžę° ėėžëĐī ėđīėīíļëĨž ėŽë ĪėĢžęģ  ë ė§ë ėë°ėīíļ ėėžėĪëĪ
				int sCnt = search.searchCntUp(ss.getKeyword(),ss.getKeywordCnt(),time1);
			}else{
				//ėĄ°íí ęē°ęģžę° ėėžëĐī ėëĄ ėėąėėžėĪëĪ.
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
		
		
		// page ėė
		int page = 1; // íėīė§ ėëē
		int limit = 12; // íëė íėīė§ ėė ëŠę°ė ęļėī ėëė§
		int page1 = 0;
		int page2 = 0;
		
		// null ę°ėī ëĪėīę°ëĐī ėĪëĨę° ëĻë ęēė ë°Đė§íęļ° ėíīė page1ęģž page2ëĨž ë§ëĪėė.
		if(request.getAttribute("paging")!=null) {
			page1 = (int)request.getAttribute("paging");
		}
		if(request.getParameter("page") != null && request.getParameter("page")!="") {
			page2 = Integer.parseInt(request.getParameter("page"));
		}
		

		// page1ėīë page2ę° nullėī ėë ęē―ė°ė pageė ę°ė ėë ĨíīėĢžë ėė.
		if (page2 != 0) {
			page = page2;
		} else if (page1 != 0) {
			page = page1;
		}
		
		// limit ę°ė ęąļėīëė ë§íž ëēėė íīëđíë ęļë§ ę°ė ļėĪë ë°Đëē
		int startRow = (page - 1) * limit;
		int endRow = limit;

		// ė ėēī ęēėęļ ę°ė ļėĪęļ° ėí ListCountëĐėë íļėķ ( ė ėēī ęļ ę°ė ėļë ęē)
		int listCount = search.listCount(keyword,cateCd,brandCd,reviewCnt,price,price2);

		// ėĩëëĄ íėí íėīė§ ę°ė ęģė°
		int maxPage = (int) ((double) listCount / limit + 0.9); // ë íėīė§ëĨž ęģė°íęļ° ėíīė íėí ė―ë

		// íėŽ íėīė§ė ëģīėŽėĪ ėėíėīė§
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1; // => 1, 11, 21, 31, 41, . . . .

		// íėŽ íėīė§ė ëģīėŽėĪ ëíėīė§
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
		
		
		//íĪėëęēė
		List<goodsDTO> sg = search.searchResultList(keyword,startRow,endRow,cateCd,brandCd,reviewCnt,price,price2);
		
		//ęēėí ęē°ęģžę° ėëĪëĐī
		if(!sg.isEmpty()) {
			List<categoryDTO> cd = new ArrayList<categoryDTO>();
			for(int k = 0; k < sg.size(); k++) {
				//ėđīíęģ ëĶŽ ëĪęģ ėĪęļ°
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
				//ëļëë ëĪęģ ėĪęļ°			
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
