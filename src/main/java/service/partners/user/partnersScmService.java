package service.partners.user;

import DAO.memberDAO.memberDAO;
import DAO.partnerDAO.partnerDAO;
import dto.scmDTO.scmDTO;
import static db.JdbcUtil.*;

import java.sql.Connection;

public class partnersScmService{

	public int scmInsertService(scmDTO sdto){
		//	scm 새로 등록
		int chk = 0;
		
		partnerDAO pdao = partnerDAO.getInstance();
		Connection con = getConnection();
		pdao.setConnection(con);
		
//		chk = pdao.addScm(sdto);
		
		if(chk>0){
			commit(con);
			memberDAO mdao = memberDAO.getInstance();
			mdao.setConnection(con);
			System.out.println("managerNo : "+sdto.getMemNo());
			chk = mdao.memberPartnerFl(sdto.getMemNo());
			
			System.out.println("scmInsertService chk : " + chk);
			
		}else{
			rollback(con);
		}
		
		close(con);
		return chk;
	}
	
	public scmDTO getScmInfo(int scmNo){
		scmDTO dto = new scmDTO();
		
		partnerDAO dao = new partnerDAO();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		dto = dao.getScmInfo(scmNo);
		
		close(con);
		
		return dto;
	}
	
}
