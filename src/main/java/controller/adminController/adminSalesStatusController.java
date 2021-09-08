package controller.adminController;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.adminService.adminMainService;

/**
 * Servlet implementation class adminSalesStatusController
 */
@WebServlet("/admin/adminSalesStatus")
public class adminSalesStatusController extends HttpServlet {
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
		adminMainService m = new adminMainService();
		
		int i = Integer.parseInt(request.getParameter("no"));
		
		Calendar week = Calendar.getInstance();
		week.add(Calendar.DATE, -(i));
		String tomorrow = new java.text.SimpleDateFormat("yyyy-MM-dd 00:00:00").format(week.getTime());
		
		Calendar week2 = Calendar.getInstance();
		week2.add(Calendar.DATE, -(i+1));
		String today = new java.text.SimpleDateFormat("yyyy-MM-dd 00:00:00").format(week2.getTime());
			
		String status = request.getParameter("status");

		
		//매출현황
		BigDecimal sumPrice = m.sumPrice(tomorrow,today,status);
		
		String a = sumPrice.toString();

		PrintWriter out = response.getWriter();
		out.println(a);

	}

}
