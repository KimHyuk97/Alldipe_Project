package DAO.orderGoodsDAO;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.orderDTO.orderGoods;

public class orderGoodsDAO {

	private static orderGoodsDAO odao;
	Connection con;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	public static orderGoodsDAO getInstance(){
		if(odao == null){
			odao = new orderGoodsDAO();
		}
		
		return odao;
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	
//주문한 상품 담기
	public int insertOrdergoods(orderGoods og) {
		
		int chk = 0;
		String sql = "insert into ordergoods (memNo,scmNo,goodsNo,representImg,goodsNm,goodsCnt,goodsOptionNm,"
				+ "fixedPrice,optionFixedPrice,deliveryPrice,taxSupplyGoodsPrice,taxVatGoodsPrice,taxFreeGoodsPrice,goodsDcPrice,"
				+ "memberDcPrice,couponGoodsDcPrice,couponCd,timeSaleDcPrice,goodsMileage,memMileage, minusDepositFl,  minusRestoreDepositFl,"
				+ "minusMileageFl,minusRestoreMileageFl,plusMileageFl,plusRestoreMileageFl,minusStockFl,minusRestoreStockFl, goodsDiscountInfo,"
				+ "goodsMileageAddInfo,regDt, sumPrice) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, og.getMemNo());
			pstmt.setInt(2, og.getScmNo());
			pstmt.setInt(3, og.getGoodsNo());
			pstmt.setString(4, og.getRepresentImg());
			pstmt.setString(5, og.getGoodsNm());
			pstmt.setInt(6, og.getGoodsCnt());
			pstmt.setString(7, og.getGoodsOptionNm());
			pstmt.setBigDecimal(8, og.getFixedPrice());
			pstmt.setBigDecimal(9, og.getOptionFixedPrice());
			pstmt.setBigDecimal(10, og.getDeliveryPrice());
			pstmt.setBigDecimal(11, og.getTaxSupplyGoodsPrice());
			pstmt.setBigDecimal(12, og.getTaxVatGoodsPrice());
			pstmt.setBigDecimal(13, og.getTaxFreeGoodsPrice());
			pstmt.setBigDecimal(14, og.getGoodsDcPrice());
			pstmt.setBigDecimal(15, og.getMemberDcPrice());
			pstmt.setBigDecimal(16, og.getCouponGoodsDcPrice());
			pstmt.setInt(17, og.getCouponCd());
			pstmt.setBigDecimal(18, og.getTimeSaleDcPrice());
			pstmt.setBigDecimal(19, og.getGoodsMileage());
			pstmt.setBigDecimal(20, og.getMemMileage());
			pstmt.setBoolean(21, og.isMinusDepositFl());
			pstmt.setBoolean(22, og.isMinusRestoreDepositFl());
			pstmt.setBoolean(23, og.isMinusMileageFl());
			pstmt.setBoolean(24, og.isMinusRestoreMileageFl());
			pstmt.setBoolean(25, og.isPlusMileageFl());
			pstmt.setBoolean(26, og.isPlusRestoreMileageFl());
			pstmt.setBoolean(27, og.isMinusStockFl());
			pstmt.setBoolean(28, og.isMinusRestoreStockFl());
			pstmt.setString(29, og.getGoodsDiscountInfo());
			pstmt.setString(30, og.getGoodsMileageAddInfo());
			pstmt.setBigDecimal(31, og.getSumPrice());
			
			chk=pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return chk;
	}
	
	public ArrayList<orderGoods> getList(ResultSet rs){
		ArrayList<orderGoods> list = new ArrayList<>();
		try{
			while(rs.next()){
				
				orderGoods og = new orderGoods();
				
				og.setSno(rs.getInt("sno"));
				og.setOrderNo(rs.getString("orderNo"));
				og.setOrderCd(rs.getInt("orderCd"));
				og.setEventSno(rs.getInt("eventSno"));
				og.setOrderStatus(rs.getString("orderStatus"));
				og.setInvoiceNo(rs.getString("invoiceNo"));
				og.setMemNo(rs.getInt("memNo"));
				og.setScmNo(rs.getInt("scmNo"));
				og.setScmAdjustNo(rs.getInt("scmAdjustNo"));
				og.setTimeSaleFl(rs.getBoolean("timeSaleFl"));
				og.setGoodsNo(rs.getInt("goodsNo"));
				og.setRepresentImg(rs.getString("representImg"));
				og.setGoodsNm(rs.getString("goodsNm"));
				og.setOptionNo(rs.getInt("optionNo"));
				og.setGoodsCnt(rs.getInt("goodsCnt"));
				og.setGoodsOptionNm(rs.getString("goodsOptionNm"));
				og.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				og.setOptionFixedPrice(rs.getBigDecimal("optionFixedPrice"));
				og.setDeliveryPrice(rs.getBigDecimal("deliveryPrice"));
				og.setSumPrice(rs.getBigDecimal("sumPrice"));
				og.setTaxSupplyGoodsPrice(rs.getBigDecimal("taxSupplyGoodsPrice"));
				og.setTaxVatGoodsPrice(rs.getBigDecimal("taxVatGoodsPrice"));
				og.setTaxFreeGoodsPrice(rs.getBigDecimal("taxFreeGoodsPrice"));
				og.setGoodsDcPrice(rs.getBigDecimal("goodsDcPrice"));
				og.setMemberDcPrice(rs.getBigDecimal("memberDcPrice"));
				og.setCouponGoodsDcPrice(rs.getBigDecimal("couponGoodsDcPrice"));
				og.setCouponCd(rs.getInt("couponCd"));
				og.setTimeSaleDcPrice(rs.getBigDecimal("timeSaleDcPrice"));
				og.setGoodsMileage(rs.getBigDecimal("goodsMileage"));
				og.setMemMileage(rs.getBigDecimal("memMileage"));
				og.setMinusDepositFl(rs.getBoolean("minusDepositFl"));
				og.setMinusRestoreDepositFl(rs.getBoolean("minusRestoreDepositFl"));
				og.setMinusMileageFl(rs.getBoolean("minusMileageFl"));
				og.setMinusRestoreMileageFl(rs.getBoolean("minusRestoreMileageFl"));
				og.setPlusMileageFl(rs.getBoolean("plusMileageFl"));
				og.setPlusRestoreMileageFl(rs.getBoolean("plusRestoreMileageFl"));
				og.setOptionTextInfo(rs.getString("optionTextInfo"));
				og.setGoodsTaxInfo(rs.getString("goodsTaxInfo"));
				og.setCancelDt(rs.getTimestamp("cancelDt"));
				og.setPaymentDt(rs.getTimestamp("paymentDt"));
				og.setSendSmsFl(rs.getString("sendSmsFl"));
				og.setInvoiceCompanySno(rs.getInt("invoiceCompanySno"));
				og.setInvoiceDt(rs.getTimestamp("invoiceDt"));
				og.setDeliveryDt(rs.getTimestamp("deliveryDt"));
				og.setDeliveryCompleteDt(rs.getTimestamp("deliveryCompleteDt"));
				og.setFinishDt(rs.getTimestamp("finishDt"));
				og.setGoodsDiscountInfo(rs.getString("goodsDiscountInfo"));
				og.setGoodsMileageAddInfo(rs.getString("goodsMileageAddInfo"));
				og.setRegDt(rs.getTimestamp("regDt"));
				og.setModDt(rs.getTimestamp("modDt"));
				
				list.add(og);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	
//	주문중인 상품목록 가져오기
	public ArrayList<orderGoods> getOGList(String orderStatus, int memNo){
		String sql = "select * from ordergoods where orderStatus=? and memNo=?";
		
		ArrayList<orderGoods> list = new ArrayList<>();
		try{
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, orderStatus);
			pstmt.setInt(2, memNo);
			
			rs = pstmt.executeQuery();
			
			list = getList(rs);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	

	
	
//	주문 상품 목록 상태변경
	public int orderChange(String status, String[] arr){
		
		int chk = 0;
		
		String sql = "update ordergoods set orderStatus='" + status + "' where sno in (";
		
		if(arr!=null && arr.length>0){
			for(String str : arr){
				sql += str + ",";
			}
			
			sql = sql.substring(0, sql.length()-1) + ")";
			
			try{
				stmt = con.createStatement();
				chk = stmt.executeUpdate(sql);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return chk;
	}	


//주문 취소한 상품 목록 삭제
	public int deleteOrdergoods(int memNo){
		String sql = "delete from ordergoods where orderStatus=? and memNo=?";
		int chk = 0;
		try{
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "주문중");
			pstmt.setInt(2, memNo);
			
			chk = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
		
	}

}
