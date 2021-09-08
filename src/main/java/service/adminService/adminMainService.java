package service.adminService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import DAO.adminDAO.adminDAO;
import DAO.memberDAO.memberDAO;
import DAO.orderDAO.orderDAO;
import dto.boardDTO.boardDTO;
import dto.boardDTO.qaDTO;
import dto.memberDTO.memberDTO;
import dto.orderDTO.orderDTO;

public class adminMainService {
	//주문리스트
		public List<orderDTO> orderList(String time1) {
			orderDAO dao = orderDAO.getInstance();
			Connection con = getConnection();
			dao.setConnection(con);
			
			List<orderDTO> o = dao.getOrderAll(time1);
			
			close(con);
			
			return o;
		}
		
		//문의글 리스트
		public List<boardDTO> boradList() {
			adminDAO dao = adminDAO.getInstance();
			Connection con = getConnection();
			dao.setConnection(con);
			
			List<boardDTO> b = dao.boardList();
			
			close(con);
			
			return b;
		}
		
//		//맴버 리스트
//		public List<memberDTO> memberList() {
//			memberDAO dao = memberDAO.getInstance();
//			Connection con = getConnection();
//			dao.setConnection(con);
//			
//			List<memberDTO> m = dao.getListAll();
//			
//			close(con);
//			
//			return m;
//		}
		
		//총회원수
		public int memberAllCount() {
			memberDAO dao = memberDAO.getInstance();
			Connection con = getConnection();
			dao.setConnection(con);
			
			int a = dao.allCount();
			
			close(con);
			
			return a;
		}
		
		//신규회원수
		public int memberNewCount() {
			memberDAO dao = memberDAO.getInstance();
			Connection con = getConnection();
			dao.setConnection(con);
			
			//현재시간
			SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd 00:00:00");
			Date time = new Date();				
			String time1 = format.format(time);	
			
			int n = dao.newCount(time1);
			
			close(con);
			
			return n;
		}
		
		//휴먼회원수
		public int memberSleepCount() {
			memberDAO dao = memberDAO.getInstance();
			Connection con = getConnection();
			dao.setConnection(con);
			
			int s = dao.sleepCount();

			close(con);
			
			return s;
		}
		
		//탈퇴회원수
		public int memberDeleteCount() {
			memberDAO dao = memberDAO.getInstance();
			Connection con = getConnection();
			dao.setConnection(con);

			int d = dao.deleteCount();
			
			close(con);
			
			return d;
		}
		
		
		//매출
		public BigDecimal sumPrice(String tomorrow, String today, String status) {
			adminDAO dao = adminDAO.getInstance();
			Connection con = getConnection();
			dao.setConnection(con);
			
			BigDecimal sumPrice = dao.sumPrice(tomorrow,today,status);
			
			close(con);
			
			return sumPrice;
		}
	
}
