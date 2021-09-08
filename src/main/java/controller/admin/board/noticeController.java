package controller.admin.board;

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

import dto.adminDTO.adminDTO;
import dto.boardDTO.boardDTO;
import dto.categoryDTO.categoryDTO;
import dto.goodsDTO.goodsDTO;
import service.adminService.adminBoardService;
import service.adminService.adminGoodsService;
import service.goodsService.goodsService;

@WebServlet("/admin/board/notice")
public class noticeController extends HttpServlet {

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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		adminBoardService ab = new adminBoardService();	
		
		HttpSession session = request.getSession();
		adminDTO admin = (adminDTO) session.getAttribute("admin");
		
		PrintWriter out = response.getWriter();
		
		if(admin != null && admin.getSno() > 0){
			request.setAttribute("admin", admin);
			
			int sno = (request.getParameter("sno") != null)?Integer.parseInt(request.getParameter("sno")):0;
			if(sno > 0) {
				boardDTO dto = ab.getBoard(sno);
						
				request.setAttribute("mode", "수정");
				request.setAttribute("board", dto);
				RequestDispatcher dispatcher = request.getRequestDispatcher("./notice.jsp");
				dispatcher.forward(request, response);	
			}else {
				request.setAttribute("mode", "등록");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./notice.jsp");
				dispatcher.forward(request, response);	
			}
				
		}else{					
			out.println("<script>");
			out.println("alert('로그인해주세요.');");
			out.println("location.href='../login';");
			out.println("</script>");
		}
		
	}
	
}
