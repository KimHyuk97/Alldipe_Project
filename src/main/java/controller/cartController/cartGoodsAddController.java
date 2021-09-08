package controller.cartController;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.cartDTO.cartDTO;
import service.cartService.cartService;

/**
 * Servlet implementation class goodsCartAddController
 */
@WebServlet("/cartGoodsAdd")
public class cartGoodsAddController extends HttpServlet {
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
		int scmNo = Integer.parseInt(request.getParameter("scmNo"));
		int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
		int optionNo = Integer.parseInt(request.getParameter("optionNo"));
		int goodsCnt = Integer.parseInt(request.getParameter("goodsCnt"));
		String siteKey = request.getParameter("siteKey");
		String goodsOptionNm = request.getParameter("goodsOptionNm");
		
		String goodsNm = request.getParameter("goodsNm");
		String makerNm = request.getParameter("makerNm");
		String representImg = request.getParameter("representImg");
		String deliveryPrice = request.getParameter("deliveryPrice");
		
		//현재시간
		
		cartDTO cart = new cartDTO();
		cart.setMemNo(memNo);
		cart.setScmNo(scmNo);
		cart.setSiteKey(siteKey);
		cart.setGoodsNo(goodsNo);
		cart.setOptionNo(optionNo);
		cart.setGoodsNm(goodsNm);
		cart.setGoodsCnt(goodsCnt);
		cart.setGoodsOptionNm(goodsOptionNm);
		cart.setMakerNm(makerNm);
		cart.setRepresentImg(representImg);
		cart.setDeliveryCost(new BigDecimal(deliveryPrice));
		
		
		String cartSelect = cs.cartSelect(memNo,goodsNo,optionNo,siteKey,goodsOptionNm);
		   
		if(cartSelect == null || cartSelect == "") {
		   	int cartAdd = cs.cartAdd(cart);
			if(cartAdd > 0) {
				PrintWriter out = response.getWriter();				
				out.println(1);
			}
		}else {
		   PrintWriter out = response.getWriter();
		   out.println(2);
		}			
	}
	
}
