package service.categoryService;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import DAO.categoryDAO.categoryDAO;
import dto.categoryDTO.categoryDTO;

public class categoryService {
	
	//카테고리 리스트
	public List<categoryDTO> categoryList() {
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<categoryDTO> lc = dao.categoryList();
		
		close(con);
		
		return lc;
	}
	
	//중분류 카테고리 리스트
	public List<categoryDTO> mediumCategoryList(String cateCd) {
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<categoryDTO> mlc = dao.mediumCategoryList(cateCd);
		
		close(con);
		
		return mlc;
	}
	
	//소분류 카테고리 리스트
	public List<categoryDTO> smallCategoryList(String cateCd) {
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<categoryDTO> slc = dao.smallCategoryList(cateCd);
		
		close(con);
		
		return slc;
	}
	
	
	public ArrayList<categoryDTO> getCateList(String cateCd, int idx){
		
		ArrayList<categoryDTO> list = new ArrayList<>();
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		list = dao.getCateList(cateCd, idx);
		
		close(con);
		
		return list;
	}
	
	public ArrayList<categoryDTO> getCateList(int idx){
		
		ArrayList<categoryDTO> list = new ArrayList<>();
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		list = dao.getCateList(idx);
		
		close(con);
		
		return list;
	}
	
	public categoryDTO getCate(String cateCd){
		
		Connection con = getConnection();
		categoryDAO dao = categoryDAO.getInstance();
		dao.setConnection(con);
		
		categoryDTO dto = dao.getCate(cateCd);
		
		close(con);
		
		return dto;
		
	}
	
}
