package service.myPageService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.List;

import DAO.myPageDAO.myPageDAO;
import dto.myPageDTO.myPageDTO;

public class myPageDeliveryAddService {
	
	//기본배송지 변경
	public int selectDeliveryFl(String deliveryFl, int memNo, String zone, String addr, String address, String getName, String phone) {
		myPageDAO dao = myPageDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int ud = dao.deliveryUpdate(deliveryFl,memNo,zone,addr,address,getName,phone);
		
		if(ud > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return ud;
	}


	public int deliveryAddressAdd(int memNo, String deliveryFl, String zone, String addr, String address,
				String getName, String phone, String time1) {
		
		myPageDAO dao = myPageDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int mpd = dao.myPageDeliveryAdd(memNo,deliveryFl,zone,addr,address,getName,phone,time1);
		
		if(mpd > 0) {
			commit(con);			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return mpd;
	}

	
	//배송지 정보 불러오기
	public List<myPageDTO> deliveryAddressSelect(int memNo) {
		myPageDAO dao = myPageDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<myPageDTO> mpd2 = dao.myPageDeliveryDAO(memNo);
		
		if(mpd2 != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return mpd2;
	}
	
	//기본배송지 n -> y변경
	public void changeDeliveryFl(int dNo, String deliveryFl, int memNo) {
		myPageDAO dao = myPageDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int d = dao.changeDeliveryFl(dNo,deliveryFl,memNo);
		if(d > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
	}	


}
