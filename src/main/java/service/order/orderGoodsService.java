package service.order;

import java.sql.Connection;
import java.util.ArrayList;

import static db.JdbcUtil.*;

import DAO.orderDAO.orderDAO;
import DAO.orderGoodsDAO.orderGoodsDAO;
import dto.orderDTO.orderGoods;

public class orderGoodsService {
	
	public int orderChange(String status, String[] arr){
		int chk = 0;
		
		Connection con = getConnection();
		
		orderGoodsDAO dao = orderGoodsDAO.getInstance();
		dao.setConnection(con);
		
		chk = dao.orderChange(status, arr);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	
	
//카트에서 주문할 상품정보 저장
	public int insertOrdergoods(orderGoods og) {
		
		orderGoodsDAO dao = orderGoodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int chk = dao.insertOrdergoods(og);
		
		
		if(chk > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return chk;
	}
	
	
	
//	카트에서 주문할 상품정보 불러오기
	public ArrayList<orderGoods> getOGList(String orderStatus, int memNo){
		orderGoodsDAO dao = orderGoodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<orderGoods> list = dao.getOGList(orderStatus, memNo);
		
		close(con);
		
		return list;
	}
	
	
	//	주문 취소한 상품정보 삭제
	//	조건 => orderstatus = '주문중', 회원번호
	public int deleteOrderGoods(int memNo){
		
		orderGoodsDAO dao = orderGoodsDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		int chk = dao.deleteOrdergoods(memNo);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
		
	}
	
	
}
