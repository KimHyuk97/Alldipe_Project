package service.myPageService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.List;

import DAO.myPageDAO.myPageDAO;
import dto.myPageDTO.myPageDTO;

public class myPageDeliveryDeleteService {

	public int deliveryDelete(int dNo) {
		myPageDAO dao = myPageDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int mpd = dao.myPageDeliveryDelete(dNo);
		
		if(mpd > 0) {
			commit(con);			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return mpd;
	}

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

}
