package service.memberService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.memberDAO.memberDAO;

public class memberIdFindService {
	
	//이메일
	public String memberIdFindService(String userEmailVal) {
		
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		String idFind = dao.memberIdFind(userEmailVal);
		
		if(idFind != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return idFind;
	}
	
	//핸드폰
	public String memberIdFindService2(String userPhoneVal) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		String idFind = dao.memberIdFind2(userPhoneVal);
		
		if(idFind != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return idFind;
	}

}
