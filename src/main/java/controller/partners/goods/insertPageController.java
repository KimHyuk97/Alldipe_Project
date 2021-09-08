package controller.partners.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.categoryDTO.categoryDTO;
import dto.scmDTO.scmDTO;
import service.goodsService.goodsService;
import service.partners.goods.partnersGoodsService;

@WebServlet("/partners/goods/insert")
public class insertPageController extends HttpServlet {
	//	상품 정보 페이지로 이동
	//	임시저장 데이터 있는지 없는지 확인
	
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
		HttpSession session = request.getSession();
		scmDTO scm = (scmDTO)session.getAttribute("scm");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(scm == null){
			out.println("<script>");
			out.println("alert('관리자 전용 페이지입니다!');");
			out.println("location.href='../login';");
			out.println("</script>");
		}else{
			//	등록중이던 정보가 있는가?
			partnersGoodsService gts = new partnersGoodsService();
			if(!gts.isGoodsTemp(scm.getScmNo())){
				//	없으면 바로 등록
				RequestDispatcher dis = request.getRequestDispatcher("./insert.jsp");
				dis.forward(request, response);
				session.setAttribute("flag", false);
				
			}else{
				//	없으면 확인 후 이어서 등록 or 새로 등록
				out.println("<script>");
				out.println("var chk = confirm('저장중인 정보가 있습니다. 이어서 작성하시겠습니까?');");
				out.println("if(chk){location.href='./insertGoods?continue=true';}else{location.href='./insertGoods?continue=false';}");
				out.println("</script>");
			}	
		}
	}
	
}
