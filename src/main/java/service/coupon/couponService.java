package service.coupon;

import dto.couponDTO.couponDTO;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.couponDAO.couponDAO;

import static db.JdbcUtil.*;

public class couponService {

	//	쿠폰 등록
	public int insertCoupon(couponDTO dto){
		
		int chk = 0;
		
		Connection con = getConnection();
		
		couponDAO dao = couponDAO.getInstance();
		dao.setConnection(con);
		
		chk = dao.insertCoupon(dto);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		return chk;
	}
	
	//	쿠폰 리스트 받아오기
	public ArrayList<couponDTO> getList(){
		
		ArrayList<couponDTO> list = new ArrayList<>();
		
		couponDAO dao = couponDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		list = dao.getList();
		
		close(con);
		
		return list;
	}

	
	
}
