package service.searchService;

import static db.JdbcUtil.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import DAO.searchDAO.searchDAO;
import dto.brandDTO.brandDTO;
import dto.categoryDTO.categoryDTO;
import dto.goodsDTO.goodsDTO;
import dto.searchDTO.searchDTO;

public class searchService {
	
	//최근검색어
	public List<searchDTO> latestKeyword(String siteKey, int memNo) {
		searchDAO dao = searchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<searchDTO> sd = dao.latestKeyword(siteKey,memNo);
				
		close(con);
		
		
		return sd;
	}
	
	//인기검색어
	public List<searchDTO> bestKeyword() {
		searchDAO dao = searchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<searchDTO> bd = dao.searchList();
		
		close(con);
			
		return bd;
	}
	
	//검색기록 조회
	public searchDTO searchSelect(String keyword) {
		searchDAO dao = searchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		searchDTO ss = dao.searchSelect(keyword);
		
		close(con);
		
		return ss;
	}
	
	//검색기록 업데이트
	public int searchCntUp(String keyword, int keywordCnt, String time1) {
		searchDAO dao = searchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int su = dao.searchCntUp(keyword,keywordCnt,time1);
		
		if(su>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return su;
	}
	
	
	//검색기록 등록
	public int searchAdd(String siteKey, int memNo, String keyword, String time1) {
		searchDAO dao = searchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int sa = dao.searchAdd(siteKey,memNo,keyword,time1);
		
		if(sa>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return 0;
	}
	
	//검색결과 카운트
	public int listCount(String keyword, String cateCd, String brandCd, int reviewCnt, BigDecimal price, BigDecimal price2) {
		
		searchDAO dao = searchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);		

		int listCount = dao.goodsListCount(keyword,cateCd,brandCd,reviewCnt,price,price2);
		
		if(listCount > 0) {
			commit(con);			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return listCount;
	}
	
	//검색결과
	public List<goodsDTO> searchResultList(String keyword, int startRow, int endRow, String cateCd, String brandCd, int reviewCnt, BigDecimal price, BigDecimal price2) {
		searchDAO dao = searchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<goodsDTO> sg = dao.searchResultList(keyword,startRow,endRow,cateCd,brandCd,reviewCnt,price,price2);
		
		close(con);
		
		return sg;
	}
	
	
	//검색결과 브랜드코드 찾기
	public brandDTO searchBrand(String brandCd) {
		searchDAO dao = searchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
	
		brandDTO bd = dao.searchBrandList(brandCd);
		
		close(con);
		
		return bd;
	}
	
	//검색결과 카테고리 찾기
	public categoryDTO searchCateList(String cateCd) {
		searchDAO dao = searchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
	
		categoryDTO cd = dao.searchCateList(cateCd);
		
		close(con);
		
		return cd;
	}
	
	

}
