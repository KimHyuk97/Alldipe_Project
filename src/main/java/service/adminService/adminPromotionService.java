package service.adminService;
import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import DAO.couponDAO.couponDAO;
import dto.couponDTO.couponDTO;


public class adminPromotionService {
	
	//쿠폰리스트
	public List<couponDTO> getCouponList() {
		couponDAO dao = couponDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<couponDTO> list = dao.getList(); 
		
		close(con);
		
		return list;
	}

}
