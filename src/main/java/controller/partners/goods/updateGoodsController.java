package controller.partners.goods;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.brandDTO.brandDTO;
import dto.goodsDTO.goodsDTO;
import dto.scmDTO.scmDTO;
import service.brand.brandService;
import service.goodsService.goodsService;

@WebServlet("/partners/goods/Update")
public class updateGoodsController extends HttpServlet {

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
		
		HttpSession session = request.getSession();
		
		scmDTO scm = (scmDTO)session.getAttribute("scm");
		
		if(scm==null){
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('파트너 전용 페이지입니다!');");
			out.println("location.href='../Main';");
			out.println("</script>");
			
			out.close();
		}
		
		String goodsStr = request.getParameter("goods");
		
		if (goodsStr == null || goodsStr.equals("")){
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('잘못된 접근입니다!');");
			out.println("location.href='../Main';");
			out.println("</script>");
			
			out.close();
		}else{
		
			int goodsNo = Integer.parseInt(goodsStr);
			System.out.println("goodsSTR : "+goodsStr);
			goodsService gs = new goodsService();
			brandService bs = new brandService();
			
			goodsDTO goods = gs.getGoodsInfo(goodsNo);
			brandDTO brand = bs.getToCode(goods.getBrandCd());
			
			session.setAttribute("goods", goods);
			session.setAttribute("brand", brand);
			
			RequestDispatcher dis = request.getRequestDispatcher("./updateGoods.jsp");
			dis.forward(request, response);
			
		}
	}	
	
}
