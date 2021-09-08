package service.memberService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.memberDAO.memberDAO;

public class memberEmailCheckService {

	public String memberEmailCheck(String userEmail) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		
		String userEmailCheck = dao.memberEmailCheck(userEmail);
		
		if(userEmailCheck != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return userEmailCheck;
	}

}
