package controller.partners.order;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.order.orderGoodsService;

@WebServlet("/partners/order/orderChange")
public class orderChangeController extends HttpServlet {

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
		String[] sno = request.getParameterValues("select_item");
		
		String orderStatus = request.getParameter("changeStatus");
		String page = request.getParameter("page");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(sno!=null && sno.length>0){
			
			orderGoodsService ogs = new orderGoodsService();
			int chk = ogs.orderChange(orderStatus, sno);
			
			out.println("<script>");
			
			if(chk>0){
				out.println("alert('주문 정보를 변경했습니다.');");
				out.println("location.href='./" + page + "';");
			}else{
				out.println("alert('작업을 실패했습니다');");
				out.println("history.back();");
			}
			
			out.println("</script>");
		}else{
			out.println("<script>");
			out.println("alert('선택된 항목이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
	}
	
}
