package service.memberService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.memberDAO.memberDAO;

public class memberDeleteService {
	
	//멤버삭제정보 테이블에 값넣기
	public int memberDeleteReason(String id, String pw, int no, StringBuffer reasons, String reasonDesc, String time1) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int md = dao.memberDeleteReason(id,pw,no,reasons,reasonDesc,time1);
		
		if(md > 0) {
			commit(con);			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return md;
	}
	
	//회원삭제

	public int memberDeleteFl(String id) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int md = dao.memberDeleteFl(id);
		
		if(md > 0) {
			commit(con);			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return md;
	}

}
