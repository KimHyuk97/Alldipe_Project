package controller.goodsController;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.boardDTO.boardDTO;
import dto.boardDTO.qaDTO;
import dto.boardDTO.reviewDTO;
import dto.goodsDTO.goodsDTO;
import dto.goodsDTO.goodsOptionDTO;
import dto.scmDTO.scmDTO;
import service.goodsService.goodsService;
import service.partners.user.partnersScmService;


/**
 * Servlet implementation class goodsViewController
 */
@WebServlet("/goodsView")
public class goodsViewController extends HttpServlet {
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
		goodsService gd = new goodsService();
		
		int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
		
		partnersScmService pss = new partnersScmService();
		
		goodsDTO goodsView = gd.goodsView(goodsNo);
		
		scmDTO scm = pss.getScmInfo(goodsView.getScmNo());
		
		if(goodsView != null) {
			
			List<goodsOptionDTO> go = gd.goodsOptionList(goodsNo);
			
			if(go != null) {				
				request.setAttribute("option", go);
				
				if(goodsView.getRelationGoodsNo() != null) {	
					List<goodsDTO> relationGoods = gd.relationGoods(goodsView.getRelationGoodsNo());
					
					if(!relationGoods.isEmpty()) {
						request.setAttribute("relationGoods", relationGoods);
					}
				}
				
			}else {
				if(goodsView.getRelationGoodsNo() != null) {	
					List<goodsDTO> relationGoods = gd.relationGoods(goodsView.getRelationGoodsNo());
					
					if(!relationGoods.isEmpty()) {
						request.setAttribute("relationGoods", relationGoods);
					}
				}

				
			}
			
			//해당상품 리뷰 불러오기
			List<reviewDTO> rv = gd.goodsReviewList(goodsNo);
			if(!rv.isEmpty()) {
				request.setAttribute("review", rv);
			}
			//해당삼품 문의글 불러오기
			List<boardDTO> qa = gd.qaList(goodsNo);
			if(!qa.isEmpty()) {
				request.setAttribute("qa", qa);
			}
						
			//공급사 불러오기
			scmDTO sc = gd.scmInfo(goodsView.getScmNo());
			if(sc.getScmNo() > 0 || sc != null) {
				request.setAttribute("scm", sc);
			}
			
			//상품조회수 +
			gd.goodsHit(goodsNo);
			request.setAttribute("scm", scm);
			request.setAttribute("goods", goodsView);
			RequestDispatcher rd = request.getRequestDispatcher("goods/goodsView.jsp");
			rd.forward(request, response);			
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('해당 상품이 존재하지 않습니다.');");
			out.println("location.href='main.jsp?displayFl=y'");
			out.println("</script>");
		}
		
				
	}

}
