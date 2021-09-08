package service.memberService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.memberDAO.memberDAO;

public class memberPwChanageService {
	
	//이메일
	public int memberPwChanageService(String userPw, String way) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int pwchanage = dao.memberPwChanage(userPw,way);
		
		if(pwchanage > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return pwchanage;
		
	}
	
	//핸드폰
	public int memberPwChanageService2(String userPw, String way) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int pwchanage = dao.memberPwChanage2(userPw,way);
		
		if(pwchanage > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return pwchanage;
	}

}
