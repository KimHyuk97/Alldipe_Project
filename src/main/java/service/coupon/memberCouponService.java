package service.coupon;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.couponDAO.memberCouponDAO;
import dto.couponDTO.memberCouponDTO;
import static db.JdbcUtil.*;

public class memberCouponService {

	public ArrayList<memberCouponDTO> getList(String[] arr){
		ArrayList<memberCouponDTO> list = null;
		
		memberCouponDAO dao = memberCouponDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		list = dao.getList(arr);
		
		close(con);
		
		return list;
	}
	
	
}
