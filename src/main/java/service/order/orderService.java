package service.order;
import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.*;

import DAO.orderDAO.orderDAO;
import DAO.orderGoodsDAO.orderGoodsDAO;
import dto.couponDTO.couponDTO;
import dto.couponDTO.memberCouponDTO;
import dto.goodsDTO.goodsDTO;
import dto.orderDTO.orderDTO;
import dto.orderDTO.orderGoods;


public class orderService {	
	
	//멤버 쿠폰리스트
	public List<memberCouponDTO> membercouponList(int memNo, int scmNo, int goodsNo) {
		
		orderDAO dao = orderDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		//내가 쓸수 있는 쿠폰 리스트 담을 곳
		List<memberCouponDTO> md = new ArrayList<memberCouponDTO>();
		
		//내가 가지고 있는 쿠폰리스트 검색
		List<memberCouponDTO> cd = dao.membercouponList(memNo);
		
		if(!cd.isEmpty()) {
			for(int i = 0; i < cd.size(); i++) {
				//내가 가지고있는 쿠폰 중 적용상품 조회
				couponDTO c = dao.couponApplyProductType(cd.get(i).getCouponNo());
				
				//내가 가지고있는 쿠폰이 적용상품이 all이면 
				if(c.getApplyProductType().equals("all")) {
					memberCouponDTO cm = dao.couponProductTypeAll(memNo,c.getCouponNo());
					md.add(cm);

				}else if(c.getApplyProductType().equals("provider") || c.getApplyProductType().equals("category") || c.getApplyProductType().equals("brand") || c.getApplyProductType().equals("goods")) {
					goodsDTO gd = dao.goodsInfo(String.valueOf(goodsNo));
					int cm = dao.couponProductTypeNotAll(memNo,cd.get(i).getSno(),goodsNo,gd.getBrandCd(),gd.getCateCd(),scmNo);
					if(cm > 0) {
						memberCouponDTO mm = dao.couponProductTypeAll(memNo,c.getCouponNo());
						md.add(mm);
						System.out.println("md = "+md);
					}
				}
				
			}
		}
		
		close(con);
		
		return md;
	}	
		
	//주문자, 수취인 정보 insert
	public int orderInfo(String orderNo, String orderName, String orderEmail, String orderPhone,
			String orderCellPhone, String receiverName, String receiverPhone, String receiverCellPhone,
			String receiverZonecode, String receiverAddress, String receiverAddressSub, String pickUpType,
			String pickUpContent, String meansType, String meansContent, String orderMemo) {
		
		orderDAO dao = orderDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int oi = dao.orderInfo(orderNo,orderName,orderEmail,orderPhone,orderCellPhone,receiverName,receiverPhone,receiverCellPhone,receiverZonecode,receiverAddress,receiverAddressSub,pickUpType,pickUpContent,meansType,meansContent,orderMemo);
		
		if(oi > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		
		return oi;
	}
	
	
	
	//주문정보 저장
	public int insertOrder(orderDTO order) {
		
		orderDAO dao = orderDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int chk = dao.insertOrder(order);
		
		if(chk > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}

}
