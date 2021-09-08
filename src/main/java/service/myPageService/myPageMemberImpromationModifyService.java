package service.myPageService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.List;

import DAO.myPageDAO.myPageDAO;
import dto.memberDTO.memberDTO;
import dto.myPageDTO.myPageDTO;

public class myPageMemberImpromationModifyService {
	
	//비밀번호 확인
	public String pwCheck(String id, String pw) {
		myPageDAO dao = myPageDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		String mpd = dao.PwCheck(id,pw);
		
		if(mpd != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return mpd;
	}

}
