package controller.admin.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.adminDTO.adminDTO;
import dto.boardDTO.boardDTO;
import dto.goodsDTO.goodsDTO;
import service.adminService.adminBoardService;
import service.adminService.adminGoodsService;

/**
 * Servlet implementation class answerWindowOpenController
 */
@WebServlet("/admin/board/boardAdminAnswer")
public class boardAdminAnswerController extends HttpServlet {
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
		adminBoardService ab = new adminBoardService();	
		HttpSession session = request.getSession();
		
		adminDTO admin = (adminDTO) session.getAttribute("admin");
		
		
		PrintWriter out = response.getWriter();
		
		if(admin != null && admin.getSno() > 0){
			request.setAttribute("admin", admin);
			
			int sno = Integer.parseInt(request.getParameter("sno"));
			
			boardDTO dto = ab.getBoard(sno);
			
			if(dto.getBoardType().equals("상품문의")) {
				adminGoodsService g = new adminGoodsService();
				goodsDTO goods = g.getGoods(dto.getGoodsNo());
				request.setAttribute("goods", goods);
			}
			
			request.setAttribute("board", dto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("./answer.jsp");
			dispatcher.forward(request, response);	
		}else{					
			out.println("<script>");
			out.println("alert('로그인해주세요.');");
			out.println("window.close();");
			out.println("</script>");
		}
	}

}
