package controller.myPageController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.myPageService.myPageDeliveryAddService;

/**
 * Servlet implementation class basicDeliveryChangeController
 */
@WebServlet("/basicDeliveryChange")
public class basicDeliveryChangeController extends HttpServlet {
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
		
		int dNo = Integer.parseInt(request.getParameter("dNo"));
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		String deliveryFl = request.getParameter("deliveryFl");
		if(deliveryFl ==  "") {
			deliveryFl = "n";
		}
		System.out.println(dNo+"====dNo");
		System.out.println(memNo+"====dMNemNo");

		myPageDeliveryAddService mpda = new myPageDeliveryAddService();
		mpda.changeDeliveryFl(dNo,deliveryFl,memNo);

	}

}
