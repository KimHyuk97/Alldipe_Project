package service.memberService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.memberDAO.memberDAO;

public class memberPwFindService {
	
	//이메일
	public String memberPwFindService(String userEmailVal) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		String pwFind = dao.memberPwFind(userEmailVal);
		
		if(pwFind != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return pwFind;
	}
	
	//핸드폰
	public String memberPwFindService2(String userPhoneVal) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		String pwFind = dao.memberPwFind2(userPhoneVal);
		
		if(pwFind != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return pwFind;
	}

}
