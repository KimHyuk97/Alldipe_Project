package service.memberService;

import java.sql.Connection;

import DAO.memberDAO.memberDAO;

import static db.JdbcUtil.*;

public class memberIdCheckService {
	
	public String memberIdCheck(String userId) {
		
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		
		String idCheck = dao.memberIdCheck(userId);
		
		if(idCheck != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return idCheck;
	}





}
