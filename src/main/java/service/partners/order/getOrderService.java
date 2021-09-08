package service.partners.order;

import DAO.orderDAO.orderDAO;
import DAO.orderDAO.orderInfoDAO;
import dto.orderDTO.orderDTO;
import dto.orderDTO.orderInfoDTO;

import static db.JdbcUtil.*;

import java.sql.Connection;

public class getOrderService {

	public orderDTO getOrder(String orderNo){
		
		orderDAO dao = orderDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		orderDTO dto = dao.getOrder(orderNo);
		
		close(con);
		
		return dto;
		
	}
	
	public orderInfoDTO getOrderInfo(String orderNo){
		orderInfoDAO dao = orderInfoDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		orderInfoDTO dto = dao.getOrderInfo(orderNo);
		
		close(con);
		
		return dto;
	}
	
}
