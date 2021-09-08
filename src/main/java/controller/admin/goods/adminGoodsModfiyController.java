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
import dto.goodsDTO.goodsOptionDTO;
import service.adminService.adminGoodsService;

/**
 * Servlet implementation class adminGoodsModfiyController
 */
@WebServlet("/admin/goods/goodsMod")
public class adminGoodsModfiyController extends HttpServlet {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		adminGoodsService ags = new adminGoodsService();
		
		int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		goodsDTO g = ags.getGoods(goodsNo);
		List<goodsOptionDTO> o = ags.getOption(goodsNo);
		brandDTO b = ags.getBrand(g.getBrandCd());
		
		String cateCd = g.getCateCd();
		String cateCd01 = "";
		String cateCd02 = "";
		String cateCd03 = "";
		categoryDTO c1 = new categoryDTO();//대분류
		categoryDTO c2 = new categoryDTO();//중분류
		categoryDTO c3 = new categoryDTO();//소분류
		System.out.println("cateCd == "+cateCd);
		System.out.println("cateCd길이 == "+cateCd.length());
		if(cateCd.length() == 3) {
			cateCd01 = cateCd;
			c1 = ags.getCategory(cateCd01);
		}else if(cateCd.length() == 6) {
			cateCd01 = cateCd.substring(0, cateCd.length()-3);
			cateCd02 = cateCd;
			c1 = ags.getCategory(cateCd01);
			c2 = ags.getCategory(cateCd02);
		}else if(cateCd.length() == 9){
			cateCd01 = cateCd.substring(0, cateCd.length()-6);
			cateCd02 = cateCd.substring(0, cateCd.length()-3);
			cateCd03 = cateCd;
			
			c1 = ags.getCategory(cateCd01);
			c2 = ags.getCategory(cateCd02);
			c3 = ags.getCategory(cateCd03);
		}
		System.out.println("대분류 == "+c1);
		System.out.println("중분류 == "+c2);
		System.out.println("소분류 == "+c3);


		if(g != null) {
			request.setAttribute("g", g);
			request.setAttribute("o", o);
			request.setAttribute("b", b);
			request.setAttribute("c1", c1);
			request.setAttribute("c2", c2);
			request.setAttribute("c3", c3);
			request.setAttribute("page", page);
			RequestDispatcher dis = request.getRequestDispatcher("./goodsMod.jsp");
			dis.forward(request, response);
		}
		
	}

}
