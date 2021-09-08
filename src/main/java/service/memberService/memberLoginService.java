package service.memberService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.memberDAO.memberDAO;
import dto.memberDTO.memberDTO;

public class memberLoginService {

	public memberDTO memberLoginService(String userId, String userPw) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);		
		
		String deleteFl = dao.memberFl(userId);
		
		memberDTO mem = new memberDTO();

		if(deleteFl.equals("y")) {
			mem = null;
		}else {
			mem = dao.memberLogin(userId,userPw);
			if(mem != null) {
				commit(con);
			} else {
				rollback(con);
			}
			close(con);
		}
		return mem;
	}

	//로그인 기록
	public void memberLoginLog(int memNo, String device, String ipAddress, String time1) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);		
		dao.memberLoginLog(memNo,device,ipAddress,time1);
	}


	


}
