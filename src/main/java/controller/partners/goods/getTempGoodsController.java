package controller.partners.goods;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.categoryDTO.categoryDTO;
import dto.goodsDTO.goodsDTO;
import dto.goodsDTO.goodsOptionDTO;
import dto.scmDTO.scmDTO;
import service.goodsService.goodsService;
import service.partners.goods.partnersGoodsService;

@WebServlet("/partners/goods/insertGoods")
public class getTempGoodsController extends HttpServlet {

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
		boolean flag = Boolean.valueOf(request.getParameter("continue"));
		
		
		HttpSession session = request.getSession();
		scmDTO scm = (scmDTO)session.getAttribute("scm");
		
		partnersGoodsService gts = new partnersGoodsService();
		
		if(flag && scm!=null){
		
			goodsDTO goods = gts.getGoodsTemp(scm.getScmNo());
			ArrayList<goodsOptionDTO> list = gts.getOptionTemp(scm.getScmNo());
			
			session.setAttribute("goodsTemp", goods);
			session.setAttribute("optionTempList", list);
			
			session.setAttribute("flag", true);
		}else{
			session.setAttribute("flag", false);
		}
				
		RequestDispatcher dis = request.getRequestDispatcher("./insert.jsp");
		dis.forward(request, response);
		
	}
	
	
}
