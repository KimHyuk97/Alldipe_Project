package service.partnerService;

import java.sql.Connection;
import java.util.ArrayList;

import static db.JdbcUtil.*;
import DAO.orderGoodsDAO.orderGoodsDAO;
import dto.orderDTO.orderGoods;

public class getOrderGoodsService {

	public ArrayList<orderGoods> getOGMainService(int scmNo){
		
		Connection con = getConnection();
		orderGoodsDAO ogd = new orderGoodsDAO();
		ogd.setConnection(con);
		
		ArrayList<orderGoods> list = ogd.getOGList(scmNo, 4);
		
		close(con);
		
		return list;
	}
	
	public ArrayList<orderGoods> getOGScmAllService(String condition){
		
		Connection con = getConnection();
		orderGoodsDAO ogd = new orderGoodsDAO();
		ogd.setConnection(con);
		
		ArrayList<orderGoods> list = ogd.getOGScmCondition(condition);
		
		close(con);
		
		return list;
	}
	
}
