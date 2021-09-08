package controller.cartController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.cartDTO.cartDTO;
import dto.goodsDTO.goodsDTO;
import dto.goodsDTO.goodsOptionDTO;
import service.cartService.cartService;
import service.goodsService.goodsOptionService;
import service.goodsService.goodsService;

/**
 * Servlet implementation class cartPageController
 */
@WebServlet("/cart")
public class cartController extends HttpServlet {
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
		cartService cs = new cartService();
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		if(memNo < 0 ) {
			memNo = 0;
		}
		String siteKey = request.getParameter("siteKey");
		List<cartDTO> cd = cs.cart(siteKey,memNo);
		
		goodsService gs = new goodsService();
		goodsOptionService gos = new goodsOptionService();
		
		for(int i = 0; i<cd.size(); i++){
			
			cartDTO cart = cd.get(i);
			
			goodsDTO goods = gs.getGoods(cart.getGoodsNo());
			goodsOptionDTO option = gos.getGoodsOption(cart.getOptionNo());
			
			if(goods==null){
				cart.setGoodsNo(0);
				cart.setGoodsPrice(new BigDecimal(0));
			}else{
				cart.setGoodsPrice(goods.getFixedPrice());
				cart.setDeliveryCost(goods.getDeliveryCost());
				cart.setDeliveryCostAddJeju(goods.getDeliveryCostAddJeju());
				cart.setDeliveryCostAdd(goods.getDeliveryCostAdd());
			}
			if(option==null){
				cart.setOptionNo(0);
				cart.setOptionPrice(new BigDecimal(0));
			}else{
				cart.setOptionPrice(option.getOptionFixedPrice());
			}
			
			cart.setSumPrice(cart.getGoodsPrice().add(cart.getOptionPrice()));
			cd.set(i, cart);
		}
		
		if(!cd.isEmpty()) {
			
			System.out.println("cart List : " + cd.get(0).getSno());
			
			request.setAttribute("cart", cd);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cart/cart.jsp");
			dispatcher.forward(request, response);	
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("cart/cart.jsp");
			dispatcher.forward(request, response);			
		}
		
				
	}

}
