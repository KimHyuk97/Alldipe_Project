package DAO.partners.goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.goodsDTO.goodsDTO;
import dto.goodsDTO.goodsOptionDTO;

public class goodsDAO {

	private static goodsDAO dao;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public static goodsDAO getInstance(){
		
		if(dao==null){
			dao = new goodsDAO();
		}
		return dao;
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	public int insertGoods(goodsDTO dto){
		int chk = 0;
		
		String sql = "insert into goods (goodsNm, scmNo, applyFl, cateCd,"
				+ "brandCd, makerNm, keyword, commission, goodsPrice, fixedPrice,"
				+ "discountPercent, costPrice, goodsWeight, taxPercent, originNm,"
				+ "onlyAdultFl, taxFreeFl, fixedOrderCnt, salesUnit, limitFl,"
				+ "maxOrderCnt, minOrderCnt, representImg, subImg, shipmentZonecode,"
				+ "shipmentAddress, shipmentAddressSub, recoveryZonecode, "
				+ "recoveryAddress, recoveryAddressSub, deliveryCompany, "
				+ "deliveryType, deliveryWay, deliveryKind, deliveryArea,"
				+ "deliveryCostAddJeju, deliveryCostAdd, deliveryFreeCondition,"
				+ "detailInfoDelivery, detailInfoAs, detailInfoRefund, detailInfoExchange, limitOption, deliveryCost,"
				+ "goodsDescription,deliveryRefundCost)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?)";
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getGoodsNm());
			pstmt.setInt(2, dto.getScmNo());
			pstmt.setString(3, dto.getApplyFl());
			pstmt.setString(4, dto.getCateCd());
			pstmt.setString(5, dto.getBrandCd());
			pstmt.setString(6, dto.getMakerNm());
			pstmt.setString(7, dto.getKeyword());
			pstmt.setBigDecimal(8, dto.getCommission());
			pstmt.setBigDecimal(9, dto.getGoodsPrice());
			pstmt.setBigDecimal(10, dto.getFixedPrice());
			pstmt.setBigDecimal(11, dto.getDiscountPercent());
			pstmt.setBigDecimal(12, dto.getCostPrice());
			pstmt.setBigDecimal(13, dto.getGoodsWeight());
			pstmt.setBigDecimal(14, dto.getTaxPercent());
			pstmt.setString(15, dto.getOriginNm());
			pstmt.setBoolean(16, dto.isOnlyAdultFl());
			pstmt.setString(17, dto.getTaxFreeFl());
			pstmt.setString(18, dto.getFixedOrderCnt());
			pstmt.setInt(19, dto.getSalesUnit());
			pstmt.setBoolean(20, dto.isLimitFl());
			pstmt.setInt(21, dto.getMaxOrderCnt());
			pstmt.setInt(22, dto.getMinOrderCnt());
			pstmt.setString(23, dto.getRepresentImg());
			pstmt.setString(24, dto.getSubImg());
			pstmt.setString(25, dto.getShipmentZonecode());
			pstmt.setString(26, dto.getShipmentAddress());
			pstmt.setString(27, dto.getShipmentAddressSub());
			pstmt.setString(28, dto.getRecoveryZonecode());
			pstmt.setString(29, dto.getRecoveryAddress());
			pstmt.setString(30, dto.getRecoveryAddressSub());
			pstmt.setString(31, dto.getDeliveryCompany());
			pstmt.setString(32, dto.getDeliveryType());
			pstmt.setString(33, dto.getDeliveryWay());
			pstmt.setString(34, dto.getDeliveryKind());
			pstmt.setString(35, dto.getDeliveryArea());
			pstmt.setBigDecimal(36, dto.getDeliveryCostAddJeju());
			pstmt.setBigDecimal(37, dto.getDeliveryCostAdd());
			pstmt.setBigDecimal(38, dto.getDeliveryFreeCondition());
			pstmt.setString(39, dto.getDetailInfoDelivery());
			pstmt.setString(40, dto.getDetailInfoAS());
			pstmt.setString(41, dto.getDetailInfoRefund());
			pstmt.setString(42, dto.getDetailInfoExchange());
			pstmt.setString(43, dto.getLimitOption());
			pstmt.setBigDecimal(44, dto.getDeliveryCost());
			pstmt.setString(45, dto.getGoodsDescription());
			pstmt.setBigDecimal(46, dto.getDeliveryRefundCost());
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	public int getGoodsNo(){
		
		String sql = "select max(goodsNo) from goods";
		int result = 0;
		try{
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt("max(goodsNo)");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insertGoodsOption(goodsOptionDTO dto){
		int chk = 0;
		
		String sql = "insert into goodsoption (goodsNo, optionNo, optionNm1, optionValue1,"
				+ "optionNm2, optionValue2, optionNm3, optionValue3, optionNm4, optionValue4,"
				+ "optionNm5, optionValue5, sellerCd, optionPrice, optionfixedPrice,"
				+ "stockCnt, optionMemo) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getGoodsNo());
			pstmt.setInt(2, dto.getOptionNo());
			pstmt.setString(3, dto.getOptionNm1());
			pstmt.setString(4, dto.getOptionValue1());
			pstmt.setString(5, dto.getOptionNm2());
			pstmt.setString(6, dto.getOptionValue2());
			pstmt.setString(7, dto.getOptionNm3());
			pstmt.setString(8, dto.getOptionValue3());
			pstmt.setString(9, dto.getOptionNm4());
			pstmt.setString(10, dto.getOptionValue4());
			pstmt.setString(11, dto.getOptionNm5());
			pstmt.setString(12, dto.getOptionValue5());
			pstmt.setString(13, dto.getSellerCd());
			pstmt.setBigDecimal(14, dto.getOptionPrice());
			pstmt.setBigDecimal(15, dto.getOptionFixedPrice());
			pstmt.setInt(16, dto.getStockCnt());
			pstmt.setString(17, dto.getOptionMemo());

			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	
	public int updateGoods(goodsDTO goods){
		
		int chk = 0;
		
		String sql = "update goods set goodsNm=?,"
				+ "cateCd=?, brandCd=?, makerNm=?, "
				+ "keyword=?, commission=?, goodsPrice=?, fixedPrice=?, DiscountPercent=?, "
				+ " costPrice=?, originNm=?, goodsMustInfo=?, kcmarkInfo=?, onlyAdultFl=?, taxFreeFl=?, "
				+ "taxPercent=?, goodsWeight=?, fixedSales=?, fixedOrderCnt=?, salesUnit=?, limitFl=?,"
				+ " limitOption=?, minOrderCnt=?, maxOrderCnt=?, restockFl=?, representImg=?, subImg=?, shortDescription=?, "
				+ "eventDescription=?, goodsDescription=?, shipmentZonecode=?, shipmentAddress=?, "
				+ "shipmentAddressSub=?, recoveryZonecode=?, recoveryAddress=?, recoveryAddressSub=?, "
				+ "deliveryCompany=?, deliveryType=?, deliveryWay=?, deliveryKind=?, deliveryFreeCondition=?,"
				+ "deliveryCost=?, deliveryArea=?, deliveryCostAddJeju=?, deliveryCostAdd=?, deliveryRefundCost=?,"
				+ " relationGoodsNo=?, detailInfoDelivery=?, detailInfoAS=?, detailInfoRefund=?, detailInfoExchange=?, "
				 + "modDt=now() where goodsNo=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, goods.getGoodsNm());
			pstmt.setString(2, goods.getCateCd());
			pstmt.setString(3, goods.getBrandCd());
			pstmt.setString(4, goods.getMakerNm());
			pstmt.setString(5, goods.getKeyword());
			pstmt.setBigDecimal(6, goods.getCommission());
			pstmt.setBigDecimal(7, goods.getGoodsPrice());
			pstmt.setBigDecimal(8, goods.getFixedPrice());
			pstmt.setBigDecimal(9, goods.getDiscountPercent());
			pstmt.setBigDecimal(10, goods.getCostPrice());
			pstmt.setString(11, goods.getOriginNm());
			pstmt.setString(12, goods.getGoodsMustInfo());
			pstmt.setString(13, goods.getKcmarkInfo());
			pstmt.setBoolean(14, goods.isOnlyAdultFl());
			pstmt.setString(15, goods.getTaxFreeFl());
			pstmt.setBigDecimal(16, goods.getTaxPercent());
			pstmt.setBigDecimal(17, goods.getGoodsWeight());
			pstmt.setString(18, goods.getFixedSales());
			pstmt.setString(19, goods.getFixedOrderCnt());
			pstmt.setInt(20, goods.getSalesUnit());
			pstmt.setBoolean(21, goods.isLimitFl());
			pstmt.setString(22, goods.getLimitOption());
			pstmt.setInt(23, goods.getMinOrderCnt());
			pstmt.setInt(24, goods.getMaxOrderCnt());
			pstmt.setBoolean(25, goods.isRestockFl());
			pstmt.setString(26, goods.getRepresentImg());
			pstmt.setString(27, goods.getSubImg());
			pstmt.setString(28, goods.getShortDescription());
			pstmt.setString(29, goods.getEventDescription());
			pstmt.setString(30, goods.getGoodsDescription());
			pstmt.setString(31, goods.getShipmentZonecode());
			pstmt.setString(32, goods.getShipmentAddress());
			pstmt.setString(33, goods.getShipmentAddressSub());
			pstmt.setString(34, goods.getRecoveryZonecode());
			pstmt.setString(35, goods.getRecoveryAddress());
			pstmt.setString(36, goods.getRecoveryAddressSub());
			pstmt.setString(37, goods.getDeliveryCompany());
			pstmt.setString(38, goods.getDeliveryType());
			pstmt.setString(39, goods.getDeliveryWay());
			pstmt.setString(40, goods.getDeliveryKind());
			pstmt.setBigDecimal(41, goods.getDeliveryFreeCondition());
			pstmt.setBigDecimal(42, goods.getDeliveryCost());
			pstmt.setString(43, goods.getDeliveryArea());
			pstmt.setBigDecimal(44, goods.getDeliveryCostAddJeju());
			pstmt.setBigDecimal(45, goods.getDeliveryCostAdd());
			pstmt.setBigDecimal(46, goods.getDeliveryRefundCost());
			pstmt.setString(47, goods.getRelationGoodsNo());
			pstmt.setString(48, goods.getDetailInfoDelivery());
			pstmt.setString(49, goods.getDetailInfoAS());
			pstmt.setString(50, goods.getDetailInfoRefund());
			pstmt.setString(51, goods.getDetailInfoExchange());
			pstmt.setInt(52, goods.getGoodsNo());
			
			System.out.println(pstmt.toString());
			
			chk = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
				
		
		return chk;
	}
	
}
