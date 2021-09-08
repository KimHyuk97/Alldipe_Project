package service.goodsService;

import java.sql.Connection;

import java.util.ArrayList;
import static db.JdbcUtil.*;

import DAO.goodsDAO.goodsOptionDAO;
import dto.goodsDTO.goodsOptionDTO;

public class goodsOptionService {

	public ArrayList<goodsOptionDTO> getList(int goodsNo){
		ArrayList<goodsOptionDTO> list = new ArrayList<>();
		
		Connection con = getConnection();
		goodsOptionDAO dao = goodsOptionDAO.getInstance();
		dao.setConnection(con);
		
		list = dao.getList(goodsNo);
		
		close(con);
		
		return list;
	}
	
	public goodsOptionDTO getGoodsOption(int sno){
		
		goodsOptionDAO dao = goodsOptionDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		goodsOptionDTO god = dao.getGoodsoption(sno);
		
		close(con);
		
		return god;	
	}
	
}
