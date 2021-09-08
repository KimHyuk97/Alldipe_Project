package controller.admin.goods;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.adminService.adminGoodsService;

@WebServlet("/admin/goods/goodsApprove")
public class applyGoodsApproveController extends HttpServlet {

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
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		
		if(request.getParameter("goodsNo").equals("")){
			out.println("alert('잘못된 접근입니다!');");
			out.println("location.href='./applyList';");
		}else{
			int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
			
			adminGoodsService ags = new adminGoodsService();
			int chk = ags.setApplyApprove(goodsNo);
			
			if(chk>0){
				out.println("alert('승인 완료');");
				out.println("location.href='./applyList';");
			}else{
				out.println("alert('수정 실패..');");
				out.println("location.href='./applyGoods?goodsNo=" + goodsNo + "';");
			}
		}
		out.println("</script>");
		
	}
	
	
}
