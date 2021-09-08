package service.memberService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.memberDAO.memberDAO;
import DAO.myPageDAO.myPageDAO;

public class memberModifyService {



	public int memModify(int no, String id, String pw, String email, String phone, String time1) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int mpd = dao.memModify(id,pw,email,phone,time1);
		
		if(mpd > 0) {
			int mpda = dao.memModifyHistory(no,pw,email,phone,time1);
			if(mpda > 0) {
				commit(con);			
			} else {
				rollback(con);
			}		
		} else {
			rollback(con);
		}
		
		close(con);
		
		return mpd;
	}

}
