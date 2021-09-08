package service.partnerService;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.partnerDAO.partnerDAO;
import dto.scmDTO.scmAddressDTO;
import dto.scmDTO.scmDTO;
import static db.JdbcUtil.*;

public class getScmService {

	public scmDTO getScmService(int managerNo){
		
		Connection conn = getConnection();
		partnerDAO pdao = partnerDAO.getInstance();
		
		pdao.setConnection(conn);
		
		scmDTO sdto = pdao.getScm(managerNo);
		
		close(conn);
		return sdto;
	}
	
	public int insertScmAddress(scmAddressDTO dto){
		
		int chk = 0;
		
		Connection con = getConnection();
		partnerDAO dao = partnerDAO.getInstance();
		dao.setConnection(con);
		
		chk = dao.insertScmAddress(dto);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	
	public ArrayList<scmAddressDTO> getScmAddress(int scmNo){
		
		ArrayList<scmAddressDTO> list = new ArrayList<>();
		
		partnerDAO dao = partnerDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		list = dao.getScmAddress(scmNo);
		
		close(con);
		
		return list;
		
	}
	
	public int deleteScmAddress(int sno){
		int chk = 0;
		
		partnerDAO dao = partnerDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		chk = dao.deleteScmAddress(sno);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	
}
