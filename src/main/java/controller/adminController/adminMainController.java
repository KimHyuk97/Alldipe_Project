package controller.adminController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.el.fmt.RequestEncodingTag;

import dto.boardDTO.boardDTO;
import dto.boardDTO.qaDTO;
import dto.memberDTO.memberDTO;
import dto.orderDTO.orderDTO;
import service.adminService.adminLoginService;
import service.adminService.adminMainService;

@WebServlet("/admin/Main")
public class adminMainController extends HttpServlet{

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
		
		String contextPath = request.getContextPath();
		
		adminMainService m = new adminMainService();
				
		Calendar week = Calendar.getInstance();
		week.add(Calendar.DATE,0);
		String time1 = new java.text.SimpleDateFormat("yyyy-MM-dd 00:00:00").format(week.getTime());

		//주문리스트
		List<orderDTO> od = m.orderList(time1);
		
		//문의글 리스트
		List<boardDTO> b = m.boradList();

//		//맴버 리스트
//		List<memberDTO> md = m.memberList();
		
		//총 회원수
		int memberAll = m.memberAllCount();

		//신규회원수
		int memberNew = m.memberNewCount();

		//휴먼회원수
		int memberSleep = m.memberSleepCount();

		//탈퇴회원수
		int memberDelete = m.memberDeleteCount();
			
		request.setAttribute("orderList", od);
		request.setAttribute("b", b);
		
		request.setAttribute("allCount",memberAll);
		request.setAttribute("newCount",memberNew);
		request.setAttribute("sleepCount",memberSleep);
		request.setAttribute("deleteCount",memberDelete);

		RequestDispatcher dis = request.getRequestDispatcher("./adminMain.jsp");
		dis.forward(request, response);
		
		
	}
	
	
}
