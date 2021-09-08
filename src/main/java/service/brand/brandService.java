package service.brand;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.brandDAO.brandDAO;

import static db.JdbcUtil.*;

import dto.brandDTO.brandDTO;

public class brandService {

	public ArrayList<brandDTO> getList(String brandNm){
		
		brandDAO dao = brandDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		ArrayList<brandDTO> list = dao.getList(brandNm);
		
		close(con);
		
		return list;
	}
	
	public String getCode(String brandNm){
		
		brandDAO dao = brandDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		String value = dao.getCode(brandNm);
		
		close(con);
		
		return value;
	}
	
	public int insertBrand(brandDTO dto){
		
		int chk = 0;
		
		Connection con = getConnection();
		
		brandDAO dao = brandDAO.getInstance();
		dao.setConnection(con);
		
		chk = dao.insertBrand(dto);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	
	public brandDTO getBrand(String brandNm){
		
		Connection con = getConnection();
		
		brandDAO dao = brandDAO.getInstance();
		dao.setConnection(con);
		brandDTO dto = dao.getBrand(brandNm);
		
		close(con);
		
		return dto;
	}
	
	public brandDTO getToCode(String brandCd){
		Connection con = getConnection();
		brandDAO dao = brandDAO.getInstance();
		dao.setConnection(con);
		
		brandDTO dto = dao.getToCode(brandCd);
		
		close(con);
		
		return dto;
	}
	
	
}
