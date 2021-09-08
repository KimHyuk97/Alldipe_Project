package service.myPageService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.List;

import DAO.myPageDAO.myPageDAO;
import dto.myPageDTO.memberCouponDTO;
import dto.myPageDTO.memberMileageDTO;
import dto.orderDTO.orderGoods;


public class myPageMemberService {
	//주문내역
	public List<orderGoods> memberOrderGoods(int memNo) {
		myPageDAO dao = myPageDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
			
		List<orderGoods> mpd = dao.memberOrderGoods(memNo);
			
		if(mpd != null) {
			commit(con);
		} else {
			rollback(con);
		}
			
		close(con);
			
		return mpd;
	}
	
	//쿠폰
	public List<memberCouponDTO> couponSelect(int memNo) {
		myPageDAO dao = myPageDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
			
		List<memberCouponDTO> mpd = dao.memberCounpon(memNo);
			
		if(mpd != null) {
			commit(con);
		} else {
			rollback(con);
		}
			
		close(con);
			
		return mpd;
	}	
	
	//적립금
	public List<memberMileageDTO> mileageSelect(int memNo) {
		myPageDAO dao = myPageDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<memberMileageDTO> mpd = dao.memberMileage(memNo);
		
		if(mpd != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return mpd;
	}
	
	
}
