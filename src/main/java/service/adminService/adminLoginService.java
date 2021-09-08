package service.adminService;

import java.sql.Connection;
import java.util.List;

import DAO.adminDAO.adminDAO;
import DAO.goodsDAO.goodsDAO;
import DAO.memberDAO.memberDAO;
import DAO.orderDAO.orderDAO;

import static db.JdbcUtil.*;

import dto.adminDTO.adminDTO;
import dto.boardDTO.qaDTO;
import dto.goodsDTO.goodsDTO;
import dto.memberDTO.memberDTO;
import dto.orderDTO.orderDTO;
import dto.orderDTO.orderGoods;

public class adminLoginService {

	public adminDTO adminLoginService(String id, String pw){	
		adminDTO dto = null;
		adminDAO dao = adminDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		dto = dao.adminLogin(id, pw);
		
		close(con);
		
		return dto;
		
	}
	
}
