package service.themeService;

import java.sql.Connection;
import java.util.ArrayList;
import static db.JdbcUtil.*;

import DAO.themeDAO.themeDAO;
import dto.themeDTO.themeDTO;

public class themeService {

	public ArrayList<themeDTO> getThemeList(int[] arr){
		
		ArrayList<themeDTO> list = new ArrayList<>();
		
		themeDAO dao = themeDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		for(int sno : arr){
			themeDTO dto = dao.getTheme(sno);
			list.add(dto);
		}
		
		close(con);
		
		return list;
	}
	
	public themeDTO getTheme(int sno){
		
		themeDTO dto = new themeDTO();
		
		themeDAO dao = themeDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		dto = dao.getTheme(sno);
		
		close(con);
		
		return dto;
	}
	
	public themeDTO getTheme(String sno){
		
		themeDTO dto = new themeDTO();
		
		themeDAO dao = themeDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		dto = dao.getTheme(sno);
		
		close(con);
		
		return dto;
	}
	
	public themeDTO getToCode(String relationCd){
		
		themeDTO dto = new themeDTO();
		
		themeDAO dao = themeDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		dto = dao.getToCode(relationCd);
		
		close(con);
		
		return dto;
	}
	
}
