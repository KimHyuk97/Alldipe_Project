package service.partnerService;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.goodsDAO.goodsDAO;
import DAO.goodsDAO.goodsSearchDAO;
import dto.goodsDTO.goodsDTO;
import static db.JdbcUtil.*;

public class goodsListService {

	public ArrayList<goodsDTO> goodsListService(int scmNo){
		
		ArrayList<goodsDTO> list = new ArrayList<>();
		
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		list = dao.getScmList(scmNo);
		
		close(con);
		
		return list;
		
	}
	
	public ArrayList<goodsDTO> getGoodsList(String scmNo, String dateCate, String startDate, String endDate, String cateCd,
			String state, String saleState, String keywordType, String keyword){
		
		ArrayList<goodsDTO> list = null;
		
		goodsSearchDAO dao = goodsSearchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		list = dao.getGoodsList(scmNo, dateCate, startDate, endDate, cateCd, state,
				saleState, keywordType, keyword);
		
		close(con);
		
		return list;
		
	}
	
}
