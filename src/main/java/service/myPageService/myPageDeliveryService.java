package service.myPageService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.List;

import DAO.myPageDAO.myPageDAO;
import dto.myPageDTO.myPageDTO;

public class myPageDeliveryService {

	public List<myPageDTO> deliveryAdd(int memNo) {
		myPageDAO dao = myPageDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<myPageDTO> mpd = dao.myPageDeliveryDAO(memNo);
		
		if(mpd != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return mpd;
	}

}
