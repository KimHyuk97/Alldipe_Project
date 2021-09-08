package DAO.tempDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.goodsDTO.goodsDTO;
import dto.goodsDTO.goodsOptionDTO;

public class tempGoodsDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	private static tempGoodsDAO dao;
	
	public static tempGoodsDAO getInstance(){
		if(dao == null){
			dao = new tempGoodsDAO();
		}
		return dao;
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	//	임시데이터 존재여부
	public boolean isGoodsTemp(int scmNo){
		
		boolean chk = false;
		
		String sql = "select goodsNo from goodstemp where scmNo = ?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scmNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				chk = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	//	임시데이터 추가
	public int insertGoodsTemp(goodsDTO dto){
		int chk = 0;
		
		String sql = "insert into goodstemp (goodsNm, scmNo, applyFl, cateCd,"
				+ "brandCd, makerNm, keyword, commission, goodsPrice, fixedPrice,"
				+ "discountPercent, costPrice, goodsWeight, taxPercent, originNm,"
				+ "onlyAdultFl, taxFreeFl, fixedOrderCnt, salesUnit, limitFl,"
				+ "maxOrderCnt, minOrderCnt, representImg, subImg, shipmentZonecode,"
				+ "shipmentAddress, shipmentAddressSub, recoveryZonecode, "
				+ "recoveryAddress, recoveryAddressSub, deliveryCompany, "
				+ "deliveryType, deliveryWay, deliveryKind, deliveryArea,"
				+ "deliveryCostAddJeju, deliveryCostAdd, deliveryFreeCondition,"
				+ "detailInfoDelivery, detailInfoAs, detailInfoRefund, detailInfoExchange, limitOption, deliveryCost,"
				+ "goodsDescription, deliveryRefundCost)"
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
	
	//	상품 옵션 임시데이터 추가
	
	public int insertOptionTemp(goodsOptionDTO dto){
		
		int chk = 0;
		
		String sql = "insert into goodsoptiontemp (scmNo, optionNo, optionNm1, optionValue1,"
				+ "optionNm2, optionValue2, optionNm3, optionValue3, optionNm4, optionValue4,"
				+ "optionNm5, optionValue5, sellerCd, optionPrice, optionfixedPrice,"
				+ "stockCnt, optionMemo) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getScmNo());
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
	
	
	//	임시데이터 불러오기
	public goodsDTO getTempGoods(int scm){
		
		String sql = "select * from goodstemp where scmNo=?";
		goodsDTO dto = null;
		
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scm);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto = new goodsDTO();
				
				dto.setGoodsNo(rs.getInt("goodsNo"));
				dto.setGoodsNm(rs.getString("goodsNm"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setBrandCd(rs.getString("brandCd"));
				dto.setMakerNm(rs.getString("makerNm"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setCommission(rs.getBigDecimal("commission"));
				dto.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				dto.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				dto.setDiscountPercent(rs.getBigDecimal("DiscountPercent"));
				dto.setCostPrice(rs.getBigDecimal("costPrice"));
				dto.setOriginNm(rs.getString("originNm"));
				dto.setGoodsMustInfo(rs.getString("goodsMustInfo"));
				dto.setOnlyAdultFl(rs.getBoolean("onlyAdultFl"));
				dto.setTaxFreeFl(rs.getString("taxFreeFl"));
				dto.setTaxPercent(rs.getBigDecimal("taxPercent"));
				dto.setGoodsWeight(rs.getBigDecimal("goodsWeight"));
				dto.setFixedSales(rs.getString("fixedSales"));
				dto.setFixedOrderCnt(rs.getString("fixedOrderCnt"));
				dto.setSalesUnit(rs.getInt("salesUnit"));
				dto.setMinOrderCnt(rs.getInt("minOrderCnt"));
				dto.setMaxOrderCnt(rs.getInt("maxOrderCnt"));
				dto.setRepresentImg(rs.getString("representImg"));
				dto.setSubImg(rs.getString("subImg"));
				dto.setShipmentZonecode(rs.getString("shipmentZonecode"));
				dto.setShipmentAddress(rs.getString("shipmentAddress"));
				dto.setShipmentAddressSub(rs.getString("shipmentAddressSub"));
				dto.setRecoveryZonecode(rs.getString("recoveryZonecode"));
				dto.setRecoveryAddress(rs.getString("recoveryAddress"));
				dto.setRecoveryAddressSub(rs.getString("recoveryAddressSub"));
				dto.setDeliveryCompany(rs.getString("deliveryCompany"));
				dto.setDeliveryType(rs.getString("deliveryType"));
				dto.setDeliveryWay(rs.getString("deliveryWay"));
				dto.setDeliveryKind(rs.getString("deliveryKind"));
				dto.setDeliveryFreeCondition(rs.getBigDecimal("deliveryFreeCondition"));
				dto.setDeliveryCost(rs.getBigDecimal("deliveryCost"));
				dto.setDeliveryArea(rs.getString("deliveryArea"));
				dto.setDeliveryCostAddJeju(rs.getBigDecimal("deliveryCostAddJeju"));
				dto.setDeliveryCostAdd(rs.getBigDecimal("deliveryCostAdd"));
				dto.setDeliveryRefundCost(rs.getBigDecimal("deliveryRefundCost"));
				dto.setRelationGoodsNo(rs.getString("relationGoodsNo"));
				dto.setDetailInfoDelivery(rs.getString("detailInfoDelivery"));
				dto.setDetailInfoAS(rs.getString("detailInfoAS"));
				dto.setDetailInfoRefund(rs.getString("detailInfoRefund"));
				dto.setDetailInfoExchange(rs.getString("detailInfoExchange"));
				dto.setLimitOption(rs.getString("limitOption"));
				dto.setDeliveryCost(rs.getBigDecimal("deliveryCost"));
				dto.setGoodsDescription(rs.getString("goodsDescription"));
				dto.setDeliveryRefundCost(rs.getBigDecimal("deliveryRefundCost"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try{
			if(pstmt != null)pstmt.close();
			if(rs!=null)rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return dto;	
	}
	
	//	임시데이터 상품 옵션 불러오기
	public ArrayList<goodsOptionDTO> getTempOption(int scmNo){
		
		ArrayList<goodsOptionDTO> list = new ArrayList<>();
		
		String sql = "select * from goodsoptiontemp where scmNo=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scmNo);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				goodsOptionDTO dto = new goodsOptionDTO();
				
				dto.setSno(rs.getInt("sno"));
				dto.setOptionNo(rs.getInt("optionNo"));
				dto.setOptionNm1(rs.getString("optionNm1"));
				dto.setOptionValue1(rs.getString("optionValue1"));
				dto.setOptionNm2(rs.getString("optionNm2"));
				dto.setOptionValue2(rs.getString("optionValue2"));
				dto.setOptionNm3(rs.getString("optionNm3"));
				dto.setOptionValue3(rs.getString("optionValue3"));
				dto.setOptionNm4(rs.getString("optionNm4"));
				dto.setOptionValue4(rs.getString("optionValue4"));
				dto.setOptionNm5(rs.getString("optionNm5"));
				dto.setOptionValue5(rs.getString("optionValue5"));
				dto.setOptionPrice(rs.getBigDecimal("optionPrice"));
				dto.setOptionFixedPrice(rs.getBigDecimal("optionFixedPrice"));
				dto.setStockCnt(rs.getInt("stockCnt"));
				dto.setSellerCd(rs.getString("sellerCd"));
				dto.setOptionMemo(rs.getString("optionMemo"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	//	상품 임시데이터 삭제
	public int deleteGoodsTemp(int scmNo){
		int chk = 0;
		
		String sql = "delete from goodstemp where scmNo=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scmNo);
			
			chk = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	//	상품 옵션 임시데이터 삭제
	public int deleteOptionTemp(int scmNo){
		int chk = 0;
		
		String sql = "delete from goodsoptiontemp where scmNo=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scmNo);
			chk = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return chk;
	}
	
}
