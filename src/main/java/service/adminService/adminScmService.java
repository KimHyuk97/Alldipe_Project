package service.adminService;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.adminDAO.adminDAO;
import DAO.scmDAO.scmDAO;
import dto.scmDTO.scmDTO;

public class adminScmService {

	public ArrayList<scmDTO> getScmList(String applyFl){
		
		ArrayList<scmDTO> list = null;
		
		Connection con = getConnection();
		
		scmDAO adao = scmDAO.getInstance();
		adao.setConnection(con);
		
		list = adao.getScmList(applyFl);
		
		close(con);
		
		return list;
	}
	public ArrayList<scmDTO> getScmList(String kind, String keyword, String scmState, String date01, String date02, int startRow, int endRow){
		
		ArrayList<scmDTO> list = null;
		
		scmDAO adao = scmDAO.getInstance();
		
		Connection con = getConnection();
		adao.setConnection(con);
		
		String mode = "리스트";
		list = adao.getScmList(kind,keyword,scmState,date01,date02,startRow,endRow,mode);
		
		close(con);
		
		return list;
	}
	
	public int getScmListCount(String kind, String keyword, String scmState, String date01, String date02, int startRow,
			int endRow) {
		scmDAO dao = scmDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		String mode = "카운트";
		int cnt = dao.getScmListCount(kind,keyword,scmState,date01,date02,startRow,endRow,mode);
		
		close(con);
		
		return cnt;
	}
	
	public scmDTO applyScm(int scmNo){
		
		scmDTO dto = null;
		
		scmDAO dao = scmDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		dto = dao.getScm(scmNo);
		
		close(con);
		
		return dto;
	}
	
	public int approveScm(int scmNo){
		
		int chk = 0;
		
		scmDAO dao = scmDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		chk = dao.approveScm(scmNo);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
		
	}
	
	
}
