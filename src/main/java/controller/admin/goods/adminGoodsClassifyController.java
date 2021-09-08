package controller.admin.goods;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.brandDTO.brandDTO;
import dto.categoryDTO.categoryDTO;
import dto.goodsDTO.goodsDTO;
import dto.themeDTO.themeDTO;
import service.adminService.adminGoodsService;

/**
 * Servlet implementation class adminGoodsClassifyController
 */
@WebServlet("/admin/goods/goodsClassify")
public class adminGoodsClassifyController extends HttpServlet {
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
		
		String mode = (request.getParameter("mode") != null)? request.getParameter("mode"): "" ;
		
		String cate = "";
		String cate2 = "";
		String cate3 = "";
		String cateCd = "";
		if(request.getParameter("cate") != null || request.getParameter("cate2") != null || request.getParameter("cate3") != null) {			
			if(request.getParameter("cate") != null) {				
				cate = request.getParameter("cate");
				//대
				categoryDTO c1 = ags.getCategory(cate);
				request.setAttribute("cate1", c1);
			}
			if(request.getParameter("cate2") != null) {				
				cate2 = request.getParameter("cate2");
				//중
				categoryDTO c2 = ags.getCategory(cate2);
				request.setAttribute("cate2", c2);
			}
			if(request.getParameter("cate3") != null) {				
				cate3 = request.getParameter("cate3");
				//소
				categoryDTO c3 = ags.getCategory(cate3);
				request.setAttribute("cate3", c3);
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
			
			if(mode.equals("수정")) {
				categoryDTO c = ags.getCategory(cateCd);
				if(c != null && c.getRecomGoodsNo() != null && !c.getRecomGoodsNo().equals("")) {
					List<goodsDTO> cl = ags.getCategoryRecomGoodsList(c.getRecomGoodsNo());
					request.setAttribute("categoryRecomGoodsList", cl);
				}	
				request.setAttribute("cateList", c);
			}
		}
		
		String brand = "";
		if(request.getParameter("brand") != null && request.getParameter("brand") != "") {
			brand = request.getParameter("brand");
			brandDTO b = ags.getBrand(brand); 
			request.setAttribute("brand", b);
		}
		
		int theme = 0;
		if(request.getParameter("theme") != null && request.getParameter("theme") != "") {
			theme = Integer.parseInt(request.getParameter("theme"));
			themeDTO t = ags.getTheme(theme);
			if(t != null && t.getRelationCd() != null) {
				if(t.getRelationCd().contains("B") == true) {					
					brandDTO b = ags.getBrand(t.getRelationCd());
					request.setAttribute("brand", b);
				}else {
					if(t.getRelationCd().length() == 3) {
						categoryDTO c1 = ags.getCategory(t.getRelationCd());
						request.setAttribute("cate1", c1);
						
					}else if(t.getRelationCd().length() == 6) {
						categoryDTO c1 = ags.getCategory(t.getRelationCd().substring(0,3));
						request.setAttribute("cate1", c1);
						categoryDTO c2 = ags.getCategory(t.getRelationCd());
						request.setAttribute("cate2", c2);	
						
					}else if(t.getRelationCd().length() == 9) {
						categoryDTO c1 = ags.getCategory(t.getRelationCd().substring(0,3));
						request.setAttribute("cate1", c1);
						categoryDTO c2 = ags.getCategory(t.getRelationCd().substring(0,6));
						request.setAttribute("cate2", c2);	
						categoryDTO c3 = ags.getCategory(t.getRelationCd());
						request.setAttribute("cate3", c3);						
					}
				}
			}else if(t != null && t.getRelationCd() == null) {
				t.setRelationCd("");
			}
			if(t != null && t.getGoodsNos() != null && t.getGoodsNos() != "") {
				List<goodsDTO> tg = ags.getThemeGoodsList(t.getGoodsNos());
				request.setAttribute("themeGoodsList", tg);
			}
			request.setAttribute("theme", t);
		}
		
		int page = 0;
		if(request.getParameter("page") != null && request.getParameter("page") != "") {
			page = Integer.parseInt(request.getParameter("page"));
		}		
		
		request.setAttribute("mode", mode);
		request.setAttribute("page", page);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./goodsClassify.jsp");
		dispatcher.forward(request, response);	
	}
}
