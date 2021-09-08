package service.memberService;

import java.sql.Connection;
import static db.JdbcUtil.*;

import DAO.memberDAO.memberDAO;
import dto.memberDTO.membergradeDTO;

public class memberGradeService {

	public membergradeDTO getGrade(int sno){
		
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		membergradeDTO dto = dao.getGrade(sno);
		
		close(con);
		
		return dto;
	}
	
}
