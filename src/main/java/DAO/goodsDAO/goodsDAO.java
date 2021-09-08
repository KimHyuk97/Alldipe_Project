package DAO.goodsDAO;

import static db.JdbcUtil.close;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.boardDTO.boardDTO;
import dto.boardDTO.qaDTO;
import dto.boardDTO.reviewDTO;
import dto.brandDTO.brandDTO;
import dto.goodsDTO.goodsDTO;
import dto.goodsDTO.goodsOptionDTO;
import dto.scmDTO.scmDTO;
import service.paging.paging;


public class goodsDAO {
	private static goodsDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	Statement stmt;


	public static goodsDAO getInstance() {
		if (dao == null) {
			dao = new goodsDAO();
		}
		return dao;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//상품 목록 리스트 카운트
	public int goodsListCount(String cateCd) {
		int listCount = 0;
		String sql = "select COUNT(*) from goods where cateCd = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cateCd);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt("COUNT(*)");
			}
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	

		return listCount;
	}
	
	public List<goodsDTO> getCategoryGoodsList(String cateCd) {
		List<goodsDTO> goodsListResult = new ArrayList<goodsDTO>();
		goodsDTO goodsList = null;
		String sql = "select * from goods where cateCd = ? order by regDt";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cateCd);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				goodsList = new goodsDTO();
				goodsList.setGoodsNo(rs.getInt("goodsNo"));
				goodsList.setRepresentImg(rs.getString("representImg"));
				goodsList.setGoodsNm(rs.getString("goodsNm"));
				goodsList.setScmNo(rs.getInt("scmNo"));
				goodsList.setCateCd(rs.getString("cateCd"));
				goodsList.setKeyword(rs.getString("keyword"));			
				goodsList.setBrandCd(rs.getString("brandCd"));
				goodsList.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				goodsList.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				goodsList.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				goodsList.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				goodsList.setHitCnt(rs.getInt("hitCnt"));
				goodsList.setWishCnt(rs.getInt("wishCnt"));
				goodsListResult.add(goodsList);
			}
				
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return goodsListResult;
	}
	
	//상품 목록
	public List<goodsDTO> goodsList(String cateCd, int startRow, int endRow) {
		List<goodsDTO> goodsListResult = new ArrayList<goodsDTO>();
		goodsDTO goodsList = null;
		String sql = "select*from goods where cateCd = ? order by regDt LIMIT ?, ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cateCd);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				goodsList = new goodsDTO();
				goodsList.setGoodsNo(rs.getInt("goodsNo"));
				goodsList.setRepresentImg(rs.getString("representImg"));
				goodsList.setGoodsNm(rs.getString("goodsNm"));
				goodsList.setScmNo(rs.getInt("scmNo"));
				goodsList.setCateCd(rs.getString("cateCd"));
				goodsList.setKeyword(rs.getString("keyword"));			
				goodsList.setBrandCd(rs.getString("brandCd"));
				goodsList.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				goodsList.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				goodsList.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				goodsList.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				goodsList.setHitCnt(rs.getInt("hitCnt"));
				goodsList.setWishCnt(rs.getInt("wishCnt"));
				goodsListResult.add(goodsList);
			}
				
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return goodsListResult;
	}
	
	//상단카테고리바 페이지
	public List<goodsDTO> HeaderCategorygoodsList(String cateCd) {
		List<goodsDTO> goodsListResult = new ArrayList<goodsDTO>();
		goodsDTO goodsList = null;
		String sql = "";

		if(cateCd.equals("1")) {
			//특딜
			sql = "select*from goods where cateCd = ? order by regDt";
		}else if(cateCd.equals("2")) {
			//신산
			sql = "select*from goods where cateCd = ? order by regDt";
		}else if(cateCd.equals("3")) {
			//베스트
			sql = "select*from goods where cateCd = ? order by hitCnt";
		}else if(cateCd.equals("4")) {
			//무료배송
			sql = "select*from goods where cateCd = ? order by regDt";
		}else if(cateCd.equals("5")) {
			//페스티벌
			sql = "select*from goods where cateCd = ? order by regDt";
		}else if(cateCd.equals("6")) {
			//이벤트
			sql = "select*from goods where cateCd = ? order by regDt";
		}else if(cateCd.equals("7")) {
			//브랜드관
			sql = "select*from goods where cateCd = ? order by regDt";
		}
		

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cateCd);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				goodsList = new goodsDTO();
				goodsList.setGoodsNo(rs.getInt("goodsNo"));
				goodsList.setRepresentImg(rs.getString("representImg"));
				goodsList.setGoodsNm(rs.getString("goodsNm"));
				goodsList.setScmNo(rs.getInt("scmNo"));
				goodsList.setCateCd(rs.getString("cateCd"));
				goodsList.setKeyword(rs.getString("keyword"));			
				goodsList.setBrandCd(rs.getString("brandCd"));
				goodsList.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				goodsList.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				goodsList.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				goodsList.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				goodsList.setHitCnt(rs.getInt("hitCnt"));
				goodsList.setWishCnt(rs.getInt("wishCnt"));
			
				goodsListResult.add(goodsList);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return goodsListResult;
	}
	
	//브랜드관
	public List<brandDTO> HeaderCategoryBrand(String cateCd) {
		List<brandDTO> brandListResult = new ArrayList<brandDTO>();
		brandDTO brandList = null;
		
		String sql = "select * from brand where cateCd = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cateCd);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				brandList = new brandDTO();
				brandList.setSno(rs.getInt("sno"));
				brandList.setBrandCd(rs.getString("brandCd"));
				brandList.setBrandNm(rs.getString("brandNm"));
				brandList.setCateCd(rs.getString("cateCd"));
				brandList.setKeyword(rs.getString("keyword"));	
				brandList.setBrandImg(rs.getString("brandImg"));
				brandList.setLikeCnt(rs.getInt("likeCnt"));
				brandList.setRegIp(rs.getString("regIp"));
				brandList.setRegDt(rs.getTimestamp("regDt"));
				brandList.setModDt(rs.getTimestamp("modDt"));
				brandListResult.add(brandList);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return brandListResult;
	}	
	
	//브랜드 상품개수
	public int brandGoodsCount(String brandCd) {
		int listCount = 0;
		String sql = "select COUNT(*) from goods where brandCd = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, brandCd);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt("COUNT(*)");
			}
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	

		return listCount;
	}
	
	
	//브랜드 상품리스트
	public List<goodsDTO> HeaderCategoryBrandGoodsList(String brandCd) {
		List<goodsDTO> goodsListResult = new ArrayList<goodsDTO>();
		goodsDTO goodsList = null;
		
		String sql = "select*from goods where brandCd = ? order by regDt";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, brandCd);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				goodsList = new goodsDTO();
				goodsList.setGoodsNo(rs.getInt("goodsNo"));
				goodsList.setRepresentImg(rs.getString("representImg"));
				goodsList.setGoodsNm(rs.getString("goodsNm"));
				goodsList.setScmNo(rs.getInt("scmNo"));
				goodsList.setCateCd(rs.getString("cateCd"));
				goodsList.setKeyword(rs.getString("keyword"));			
				goodsList.setBrandCd(rs.getString("brandCd"));
				goodsList.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				goodsList.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				goodsList.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				goodsList.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				goodsList.setHitCnt(rs.getInt("hitCnt"));
				goodsList.setWishCnt(rs.getInt("wishCnt"));
				goodsListResult.add(goodsList);
			}
				
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return goodsListResult;
	}
	

	//상품상세페이지
	public goodsDTO goodsView(int goodsNo) {
		goodsDTO gd = null;
		String sql = "SELECT * FROM goods  WHERE goodsNo = ?";		

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodsNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {					
				gd = new goodsDTO();
				gd.setGoodsNo(rs.getInt("goodsNo"));
				gd.setGoodsNm(rs.getString("goodsNm"));
				gd.setRepresentImg(rs.getString("representImg"));
				gd.setSubImg(rs.getString("subImg"));
				gd.setGoodsNm(rs.getString("goodsNm"));
				gd.setScmNo(rs.getInt("scmNo"));
				gd.setCateCd(rs.getString("cateCd"));
				gd.setKeyword(rs.getString("keyword"));			
				gd.setBrandCd(rs.getString("brandCd"));
				gd.setMakerNm(rs.getString("makerNm"));
				gd.setGoodsDescription(rs.getString("goodsDescription"));
				gd.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				gd.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				gd.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				gd.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				
				//퍼센트 xx.00 -> 0.x 형태로 계산하기
				BigDecimal discountPercent = rs.getBigDecimal("DiscountPercent");
				BigDecimal discountPercent2 = new BigDecimal(100.00);
				
				BigDecimal dp = discountPercent.divide(discountPercent2, BigDecimal.ROUND_DOWN);
				gd.setDiscountPercent(dp);
				gd.setTotalStock(rs.getInt("totalStock"));
				gd.setRelationGoodsNo(rs.getString("relationGoodsNo"));
				gd.setDeliveryCompany(rs.getString("deliveryCompany"));
				gd.setDeliveryCost(rs.getBigDecimal("deliveryCost")); //배송비
				gd.setDeliveryCostAddJeju(rs.getBigDecimal("deliveryCostAddJeju"));
				gd.setDeliveryCostAdd(rs.getBigDecimal("deliveryCostAdd"));
				gd.setDeliveryFreeCondition(rs.getBigDecimal("deliveryFreeCondition"));
				gd.setDetailInfoDelivery(rs.getString("detailInfoDelivery"));
				gd.setDetailInfoAS(rs.getString("detailInfoAS"));
				gd.setDetailInfoRefund(rs.getString("detailInfoRefund"));
				gd.setDetailInfoExchange(rs.getString("detailInfoExchange"));
				
				gd.setOrderGoodsCnt(rs.getInt("orderGoodsCnt"));//주문상품 수
				gd.setOrderCnt(rs.getInt("orderCnt")); //주문량
				gd.setHitCnt(rs.getInt("hitCnt"));
				gd.setWishCnt(rs.getInt("wishCnt"));
				gd.setReviewCnt(rs.getInt("reviewCnt"));
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return gd;
	}
	
	//옵션정보
	public List<goodsOptionDTO> getOptionList(int goodsNo) {
		goodsOptionDTO gd = null;
		List<goodsOptionDTO> go = new ArrayList<goodsOptionDTO>();
		String sql = "SELECT * FROM goodsoption  WHERE goodsNo = ?";	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodsNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {		
				gd = new goodsOptionDTO();
				gd.setSno(rs.getInt("sno"));
				gd.setGoodsNo(rs.getInt("goodsNo"));
				gd.setOptionNo(rs.getInt("optionNo"));
				gd.setOptionNm1(rs.getString("optionNm1"));
				gd.setOptionValue1(rs.getString("optionValue1"));
				gd.setOptionNm2(rs.getString("optionNm2"));
				gd.setOptionValue2(rs.getString("optionValue2"));	
				gd.setOptionNm3(rs.getString("optionNm3"));
				gd.setOptionValue3(rs.getString("optionValue3"));
				gd.setOptionNm4(rs.getString("optionNm4"));
				gd.setOptionValue4(rs.getString("optionValue4"));
				gd.setOptionNm5(rs.getString("optionNm5"));
				gd.setOptionValue5(rs.getString("optionValue5"));
				gd.setOptionPrice(rs.getBigDecimal("optionPrice"));
				gd.setOptionFixedPrice(rs.getBigDecimal("optionFixedPrice"));
				gd.setOptionViewFl(rs.getBoolean("optionViewFl"));
				gd.setOptionSellFl(rs.getBoolean("optionSellFl"));
				gd.setStockCnt(rs.getInt("stockCnt"));
				go.add(gd);
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return go;
	}
	
	//관련상품
	public List<goodsDTO> relationGoods(String relationGoodsNo) {
		List<goodsDTO> gdList = new ArrayList<goodsDTO>();
		
		String[] goods = relationGoodsNo.split(",");
		for(int l = 0; l<goods.length; l++) {
		String sql = "select * from goods where goodsNo = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, goods[l]);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					goodsDTO gd= new goodsDTO();
					gd.setGoodsNo(rs.getInt("goodsNo"));
					gd.setRepresentImg(rs.getString("representImg"));
					gd.setGoodsNm(rs.getString("goodsNm"));		
					gd.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
					gd.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
					gd.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
					gd.setFixedPrice(rs.getBigDecimal("fixedPrice"));
					gd.setHitCnt(rs.getInt("hitCnt"));
					gd.setWishCnt(rs.getInt("wishCnt"));
					gd.setReviewCnt(rs.getInt("reviewCnt"));
					gdList.add(gd);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}	
		}
		
		return gdList;
	}

	
	public int insertGoods(goodsDTO dto){
		int chk = 0;
		
		String sql = "insert into goods (goodsNm, goodsSellFl, scmNo, applyMsg, commission,"
				+ "cateCd, keyword, brandCd, makerNm, originNm, goodsMustInfo, onlyAdultFl, taxFreeFl,"
				+ "taxPercent, goodsWeight, salesUnit, minOrderCnt, maxOrderCnt, goodsPrice, fixedPrice, costPrice,"
				+ "shortDescription, eventDescription,"
				+ "goodsDescription, deliveryCompany, deliveryWay, deliveryKind, deliveryFreeCondition,"
				+ "relationGoodsNo, detailInfoDelivery, detailInfoAS, detailInfoRefund, detailInfoExchange, totalStock, goodsNo)"
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getGoodsNm());
			pstmt.setInt(2, dto.getGoodsSellFl());
			pstmt.setInt(3, dto.getScmNo());
			pstmt.setString(4, dto.getApplyMsg());
			pstmt.setBigDecimal(5, dto.getCommission());
			pstmt.setString(6, dto.getCateCd());
			pstmt.setString(7, dto.getKeyword());
			pstmt.setString(8, dto.getBrandCd());
			pstmt.setString(9, dto.getMakerNm());
			pstmt.setString(10, dto.getOriginNm());
			pstmt.setString(11, dto.getGoodsMustInfo());
			pstmt.setBoolean(12, dto.isOnlyAdultFl());
			pstmt.setString(13, dto.getTaxFreeFl());
			pstmt.setBigDecimal(14, dto.getTaxPercent());
			pstmt.setBigDecimal(15, dto.getGoodsWeight());
			pstmt.setInt(16, dto.getSalesUnit());
			pstmt.setInt(17, dto.getMinOrderCnt());
			pstmt.setInt(18, dto.getMaxOrderCnt());
			pstmt.setBigDecimal(19, dto.getGoodsPrice());
			pstmt.setBigDecimal(20, dto.getFixedPrice());
			pstmt.setBigDecimal(21, dto.getCostPrice());
			pstmt.setString(22, dto.getShortDescription());
			pstmt.setString(23, dto.getEventDescription());
			pstmt.setString(24, dto.getGoodsDescription());
			pstmt.setString(25, dto.getDeliveryCompany());
			pstmt.setString(26, dto.getDeliveryWay());
			pstmt.setString(27, dto.getDeliveryKind());
			pstmt.setBigDecimal(28, dto.getDeliveryFreeCondition());
			pstmt.setString(29, dto.getRelationGoodsNo());
			pstmt.setString(30, dto.getDetailInfoDelivery());
			pstmt.setString(31, dto.getDetailInfoAS());
			pstmt.setString(32, dto.getDetailInfoRefund());
			pstmt.setString(33, dto.getDetailInfoExchange());
			pstmt.setInt(34, dto.getTotalStock());
			pstmt.setInt(35, dto.getGoodsNo());
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return chk;
	}
	
	
	
	public ArrayList<goodsDTO> getScmList(int scmNo){
		
		ArrayList<goodsDTO> goodsListResult = new ArrayList<>();
		
		String sql = "select * from goods where scmNo=? and delDt is null";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scmNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				goodsDTO goodsList = new goodsDTO();
				goodsList.setGoodsNo(rs.getInt("goodsNo"));
				goodsList.setRepresentImg(rs.getString("representImg"));
				goodsList.setGoodsNm(rs.getString("goodsNm"));
				goodsList.setScmNo(rs.getInt("scmNo"));
				goodsList.setCateCd(rs.getString("cateCd"));
				goodsList.setKeyword(rs.getString("keyword"));			
				goodsList.setBrandCd(rs.getString("brandCd"));
				goodsList.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				goodsList.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				goodsList.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				goodsList.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				goodsList.setHitCnt(rs.getInt("hitCnt"));
				goodsList.setWishCnt(rs.getInt("wishCnt"));
			
				goodsListResult.add(goodsList);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return goodsListResult;
		
	}
	
	public int getGoodsMaxNo(){
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
	
	
	public goodsDTO getGoods(int goodsNo){
		goodsDTO dto = null;
		
		String sql = "select * from goods where goodsNo=?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodsNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new goodsDTO();
				
				dto.setGoodsNo(rs.getInt("goodsNo"));
				dto.setGoodsNm(rs.getString("goodsNm"));
				dto.setGoodsSellFl(rs.getInt("goodsSellFl"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setMemberOnly(rs.getBoolean("memberOnly"));
				dto.setApplyFl(rs.getString("applyFl"));
				dto.setApplyMsg(rs.getString("applyMsg"));
				dto.setApplyDt(rs.getTimestamp("applyDt"));
				dto.setSalesStartDt(rs.getTimestamp("salesStartDt"));
				dto.setSalesEndDt((rs.getTimestamp("salesEndDt")));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setBrandCd(rs.getString("brandCd"));
				dto.setMakerNm(rs.getString("makerNm"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setCommission(rs.getBigDecimal("commission"));
				dto.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				dto.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				dto.setDiscountPercent(rs.getBigDecimal("DiscountPercent"));
				dto.setTotalStock(rs.getInt("totalStock"));
				dto.setDiscountInfo(rs.getString("discountInfo"));
				dto.setPeriodDiscountStart(rs.getTimestamp("periodDiscountStart"));
				dto.setPeriodDiscountEnd(rs.getTimestamp("periodDiscountEnd"));
				dto.setGoodsDiscountFl(rs.getBoolean("goodsDiscountFl"));
				dto.setGoodsDiscountType(rs.getBoolean("goodsDiscountType"));
				dto.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				dto.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				dto.setCostPrice(rs.getBigDecimal("costPrice"));
				dto.setOriginNm(rs.getString("originNm"));
				dto.setGoodsMustInfo(rs.getString("goodsMustInfo"));
				dto.setKcmarkInfo(rs.getString("kcmarkInfo"));
				dto.setOnlyAdultFl(rs.getBoolean("onlyAdultFl"));
				dto.setTaxFreeFl(rs.getString("taxFreeFl"));
				dto.setTaxPercent(rs.getBigDecimal("taxPercent"));
				dto.setGoodsWeight(rs.getBigDecimal("goodsWeight"));
				dto.setFixedSales(rs.getString("fixedSales"));
				dto.setFixedOrderCnt(rs.getString("fixedOrderCnt"));
				dto.setSalesUnit(rs.getInt("salesUnit"));
				dto.setLimitFl(rs.getBoolean("limitFl"));
				dto.setLimitOption(rs.getString("limitOption"));
				dto.setMinOrderCnt(rs.getInt("minOrderCnt"));
				dto.setMaxOrderCnt(rs.getInt("maxOrderCnt"));
				dto.setRestockFl(rs.getBoolean("restockFl"));
				dto.setRepresentImg(rs.getString("representImg"));
				dto.setSubImg(rs.getString("subImg"));
				dto.setShortDescription(rs.getString("shortDescription"));
				dto.setEventDescription(rs.getString("eventDescription"));
				dto.setGoodsDescription(rs.getString("goodsDescription"));
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
				dto.setOrderCnt(rs.getInt("orderCnt"));
				dto.setOrderGoodsCnt(rs.getInt("orderGoodsCnt"));
				dto.setHitCnt(rs.getInt("hitCnt"));
				dto.setWishCnt(rs.getInt("wishCnt"));
				dto.setReviewCnt(rs.getInt("reviewCnt"));
				dto.setCouponCd(rs.getString("couponCd"));
				dto.setIcon(rs.getString("icon"));
				dto.setDelFl(rs.getBoolean("delFl"));
				dto.setAdminMsg(rs.getString("adminMsg"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDelDt(rs.getTimestamp("delDt"));
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public ArrayList<goodsDTO> GoodsList(String applyFl){
		
		ArrayList<goodsDTO> list = new ArrayList<>();
		
		String sql = "select * from goods where applyFl=?";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, applyFl);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				goodsDTO dto = new goodsDTO();
				
				dto.setGoodsNo(rs.getInt("goodsNo"));
				dto.setGoodsNm(rs.getString("goodsNm"));
				dto.setGoodsSellFl(rs.getInt("goodsSellFl"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setMemberOnly(rs.getBoolean("memberOnly"));
				dto.setApplyFl(rs.getString("applyFl"));
				dto.setApplyMsg(rs.getString("applyMsg"));
				dto.setApplyDt(rs.getTimestamp("applyDt"));
				dto.setSalesStartDt(rs.getTimestamp("salesStartDt"));
				dto.setSalesEndDt((rs.getTimestamp("salesEndDt")));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setBrandCd(rs.getString("brandCd"));
				dto.setMakerNm(rs.getString("makerNm"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setCommission(rs.getBigDecimal("commission"));
				dto.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				dto.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				dto.setDiscountPercent(rs.getBigDecimal("DiscountPercent"));
				dto.setTotalStock(rs.getInt("totalStock"));
				dto.setDiscountInfo(rs.getString("discountInfo"));
				dto.setPeriodDiscountStart(rs.getTimestamp("periodDiscountStart"));
				dto.setPeriodDiscountEnd(rs.getTimestamp("periodDiscountEnd"));
				dto.setGoodsDiscountFl(rs.getBoolean("goodsDiscountFl"));
				dto.setGoodsDiscountType(rs.getBoolean("goodsDiscountType"));
				dto.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				dto.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				dto.setCostPrice(rs.getBigDecimal("costPrice"));
				dto.setOriginNm(rs.getString("originNm"));
				dto.setGoodsMustInfo(rs.getString("goodsMustInfo"));
				dto.setKcmarkInfo(rs.getString("kcmarkInfo"));
				dto.setOnlyAdultFl(rs.getBoolean("onlyAdultFl"));
				dto.setTaxFreeFl(rs.getString("taxFreeFl"));
				dto.setTaxPercent(rs.getBigDecimal("taxPercent"));
				dto.setGoodsWeight(rs.getBigDecimal("goodsWeight"));
				dto.setFixedSales(rs.getString("fixedSales"));
				dto.setFixedOrderCnt(rs.getString("fixedOrderCnt"));
				dto.setSalesUnit(rs.getInt("salesUnit"));
				dto.setLimitFl(rs.getBoolean("limitFl"));
				dto.setLimitOption(rs.getString("limitOption"));
				dto.setMinOrderCnt(rs.getInt("minOrderCnt"));
				dto.setMaxOrderCnt(rs.getInt("maxOrderCnt"));
				dto.setRestockFl(rs.getBoolean("restockFl"));
				dto.setRepresentImg(rs.getString("representImg"));
				dto.setSubImg(rs.getString("subImg"));
				dto.setShortDescription(rs.getString("shortDescription"));
				dto.setEventDescription(rs.getString("eventDescription"));
				dto.setGoodsDescription(rs.getString("goodsDescription"));
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
				dto.setOrderCnt(rs.getInt("orderCnt"));
				dto.setOrderGoodsCnt(rs.getInt("orderGoodsCnt"));
				dto.setHitCnt(rs.getInt("hitCnt"));
				dto.setWishCnt(rs.getInt("wishCnt"));
				dto.setReviewCnt(rs.getInt("reviewCnt"));
				dto.setCouponCd(rs.getString("couponCd"));
				dto.setIcon(rs.getString("icon"));
				dto.setDelFl(rs.getBoolean("delFl"));
				dto.setAdminMsg(rs.getString("adminMsg"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDelDt(rs.getTimestamp("delDt"));
				
				list.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<goodsDTO> GoodsList(){
		
		ArrayList<goodsDTO> list = new ArrayList<>();
		
		String sql = "select * from goods";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				goodsDTO dto = new goodsDTO();
				
				dto.setGoodsNo(rs.getInt("goodsNo"));
				dto.setGoodsNm(rs.getString("goodsNm"));
				dto.setGoodsSellFl(rs.getInt("goodsSellFl"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setApplyFl(rs.getString("applyFl"));
				dto.setApplyMsg(rs.getString("applyMsg"));
				dto.setApplyDt(rs.getTimestamp("applyDt"));
				dto.setSalesStartDt(rs.getTimestamp("salesStartDt"));
				dto.setSalesEndDt((rs.getTimestamp("salesEndDt")));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setBrandCd(rs.getString("brandCd"));
				dto.setMakerNm(rs.getString("makerNm"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setCommission(rs.getBigDecimal("commission"));
				dto.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				dto.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				dto.setDiscountPercent(rs.getBigDecimal("DiscountPercent"));
				dto.setTotalStock(rs.getInt("totalStock"));
				dto.setDiscountInfo(rs.getString("discountInfo"));
				dto.setPeriodDiscountStart(rs.getTimestamp("periodDiscountStart"));
				dto.setPeriodDiscountEnd(rs.getTimestamp("periodDiscountEnd"));
				dto.setGoodsDiscountFl(rs.getBoolean("goodsDiscountFl"));
				dto.setGoodsDiscountType(rs.getBoolean("goodsDiscountType"));
				dto.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				dto.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				dto.setCostPrice(rs.getBigDecimal("costPrice"));
				dto.setOriginNm(rs.getString("originNm"));
				dto.setGoodsMustInfo(rs.getString("goodsMustInfo"));
				dto.setKcmarkInfo(rs.getString("kcmarkInfo"));
				dto.setOnlyAdultFl(rs.getBoolean("onlyAdultFl"));
				dto.setTaxFreeFl(rs.getString("taxFreeFl"));
				dto.setTaxPercent(rs.getBigDecimal("taxPercent"));
				dto.setGoodsWeight(rs.getBigDecimal("goodsWeight"));
				dto.setFixedSales(rs.getString("fixedSales"));
				dto.setFixedOrderCnt(rs.getString("fixedOrderCnt"));
				dto.setSalesUnit(rs.getInt("salesUnit"));
				dto.setMinOrderCnt(rs.getInt("minOrderCnt"));
				dto.setMaxOrderCnt(rs.getInt("maxOrderCnt"));
				dto.setRestockFl(rs.getBoolean("restockFl"));
				dto.setRepresentImg(rs.getString("representImg"));
				dto.setSubImg(rs.getString("subImg"));
				dto.setShortDescription(rs.getString("shortDescription"));
				dto.setEventDescription(rs.getString("eventDescription"));
				dto.setGoodsDescription(rs.getString("goodsDescription"));
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
				dto.setOrderCnt(rs.getInt("orderCnt"));
				dto.setOrderGoodsCnt(rs.getInt("orderGoodsCnt"));
				dto.setHitCnt(rs.getInt("hitCnt"));
				dto.setWishCnt(rs.getInt("wishCnt"));
				dto.setReviewCnt(rs.getInt("reviewCnt"));
				dto.setCouponCd(rs.getString("couponCd"));
				dto.setIcon(rs.getString("icon"));
				dto.setDelFl(rs.getBoolean("delFl"));
				dto.setAdminMsg(rs.getString("adminMsg"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDelDt(rs.getTimestamp("delDt"));
				
				list.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
		
	
	public int setApplyReject(int goodsNo, String msg){
		int chk = 0;
		
		String sql = "update goods set applyFl='n', adminMsg=? where goodsNo=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, msg);
			pstmt.setInt(2, goodsNo);
			
			chk = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	public int setApplyApprove(int goodsNo){
		int chk = 0;
		
		String sql = "update goods set applyFl='y' where goodsNo=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodsNo);
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	//리뷰
	public List<reviewDTO> goodsReviewList(int goodsNo) {
		List<reviewDTO> rv = new ArrayList<reviewDTO>();
		
		String sql = "select * from review where goodsNo = ? order by regDt";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodsNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				reviewDTO rr = new reviewDTO();
				rr.setSno(rs.getInt("sno"));
				rr.setOrderNo(rs.getInt("orderNo"));
				rr.setGoodsNo(rs.getInt("goodsNo"));
				rr.setTitle(rs.getString("title"));
				rr.setMemNo(rs.getInt("memNo"));
				rr.setWriter(rs.getString("writer"));
				rr.setIp(rs.getString("ip"));
				rr.setPrivateFl(rs.getBoolean("privateFl"));
				rr.setPw(rs.getString("pw"));
				rr.setContents(rs.getString("contents"));
				rr.setGoodsPt(rs.getInt("goodsPt"));
				rr.setReviewImg(rs.getString("reviewImg"));
				rr.setAddMileageFl(rs.getBoolean("addMileageFl"));
				rr.setRegDt(rs.getTimestamp("regDt"));
				rr.setViewCnt(rs.getInt("viewCnt"));
				rv.add(rr);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		
		return rv;
	}
	
	
	//문의글
	public List<boardDTO> qaList(int goodsNo) {
		List<boardDTO> qa = new ArrayList<>();
		
		String sql = "select * from board where goodsNo =? and theme = '상품문의' and partnersFl = 0 order by regDt";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodsNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				boardDTO qq = new boardDTO();
				qq.setNo(rs.getInt("sno"));
				qq.setMemNo(rs.getInt("memNo"));
				qq.setWriterNm(rs.getString("writerNm"));
				qq.setTheme(rs.getString("theme"));
				qq.setTitle(rs.getString("title"));
				qq.setContents(rs.getString("contents"));
				qq.setViewCnt(rs.getInt("viewCnt"));
				qq.setGoodsNo(rs.getInt("goodsNo"));
				qq.setSecret(rs.getBoolean("isSecret"));
				qq.setReplyStatus(rs.getString("replyStatus"));
				qq.setRegDt(rs.getTimestamp("regDt"));
				qq.setAdminNo(rs.getInt("adminNo"));
				qq.setAnswerDt(rs.getTimestamp("answerDt"));
				qq.setAnswerContent(rs.getString("answerContent"));
				
				qa.add(qq);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		
		return qa;
	}
	
	
	//공급사
	public scmDTO scmInfo(int scmNo) {
		scmDTO scm = new scmDTO();
		String sql ="select * from scm where scmNo = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scmNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				scm.setScmNo(rs.getInt("scmNo")); //공급사번호
				scm.setCompanyNm(rs.getString("companyNm")); //공급사명
				scm.setCeoNm(rs.getString("ceoNm")); //대표자 명
				scm.setPhone(rs.getString("phone"));			// 핸드폰
				scm.setEmail(rs.getString("email"));			//이메일
				scm.setZonecode(rs.getString("zonecode")); 	   // 우편번호
				scm.setAddress(rs.getString("address"));	   // 주소
				scm.setAddressSub(rs.getString("addressSub")); // 상세주소
				scm.setBusinessNo(rs.getString("businessNo")); //사업자 번호
				scm.setOnlineOrderSerial(rs.getNString("onlineOrderSerial")); //통신판매업

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return scm;
	}

	public int qaWrite(int memNo, String writerNm, String writerId, String writerIp, String title,
			String contents, Boolean isSecret, String writerPw, int goodsNo, int scmNo) {
		System.out.println(isSecret);
		int qa = 0;
		String sql = "insert into qa (memNo,writerNm,writerId,writerIp,cate,title,contents,isSecret,writerPw,goodsNo,scmNo,regDt)"
				+ "values(?,?,?,?,'상품문의',?,?,?,?,?,?,NOW())";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			pstmt.setString(2, writerNm);
			pstmt.setString(3, writerId);
			pstmt.setString(4, writerIp);
			pstmt.setString(5, title);
			pstmt.setString(6, contents);
			pstmt.setBoolean(7, isSecret);
			pstmt.setString(8, writerPw);
			pstmt.setInt(9, goodsNo);
			pstmt.setInt(10, scmNo);
			qa = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return qa;
	}

	public String qaPwCheck(String qw2) {
		String qa = null;
		String sql = "select title from qa where writerPw = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qw2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qa = rs.getString("title");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return qa;
	}
	
	//조회수 +
	public void goodsHit(int goodsNo) {
		String sql = "update goods set hitCnt = hitCnt + 1 where goodsNo = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodsNo);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
			close(con);
		}
		
	}
	
	
	//상품명찾기
	public List<goodsDTO> getGoodsNoList(String keyword) {
		List<goodsDTO> goodsNo = new ArrayList<goodsDTO>();
		String sql = "select goodsNo from goods where goodsNm LIKE ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				goodsDTO g = new goodsDTO();
				g.setGoodsNo(rs.getInt("goodsNo")); 
				goodsNo.add(g);
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
			close(con);
		}		
		return goodsNo;
	}
	
	//공급사 명으로 상품번호 찾기
	public List<goodsDTO> scmNmList(String keyword) {
		List<scmDTO> scmNo = new ArrayList<scmDTO>();
		List<goodsDTO> gd = new ArrayList<goodsDTO>();
		String sql = "select scmNo from scm where companyNm LIKE ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				scmDTO s = new scmDTO();
				s.setScmNo(rs.getInt("scmNo"));
				scmNo.add(s);
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		} 		
		if(!scmNo.isEmpty()) {
			String sql2 = "select goodsNo from goods where scmNo = ?";
			try {
				for(int i = 0; i < scmNo.size(); i++) {
					pstmt = con.prepareStatement(sql2);
					pstmt.setInt(1, scmNo.get(i).getScmNo());
					rs = pstmt.executeQuery();
					while(rs.next()) {
						goodsDTO g = new goodsDTO();
						g.setGoodsNo(rs.getInt("goodsNo"));
						gd.add(g);
					}
				}
			} catch (SQLException e) { 
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
				close(con);
			}
		}
		
		return gd;
	}
	
	public ArrayList<goodsDTO> getList(paging p){
	
		ArrayList<goodsDTO> list = new ArrayList<>();
		
		String sql = "select * from goods order by regDt desc limit ?, ?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (p.getPageSize() * (p.getPageNo()-1)));
			pstmt.setInt(2, p.getPageSize());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				goodsDTO dto = new goodsDTO();
				
				dto.setGoodsNo(rs.getInt("goodsNo"));
				dto.setGoodsNm(rs.getString("goodsNm"));
				dto.setGoodsSellFl(rs.getInt("goodsSellFl"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setMemberOnly(rs.getBoolean("memberOnly"));
				dto.setApplyFl(rs.getString("applyFl"));
				dto.setApplyMsg(rs.getString("applyMsg"));
				dto.setApplyDt(rs.getTimestamp("applyDt"));
				dto.setSalesStartDt(rs.getTimestamp("salesStartDt"));
				dto.setSalesEndDt((rs.getTimestamp("salesEndDt")));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setBrandCd(rs.getString("brandCd"));
				dto.setMakerNm(rs.getString("makerNm"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setCommission(rs.getBigDecimal("commission"));
				dto.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				dto.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				dto.setDiscountPercent(rs.getBigDecimal("DiscountPercent"));
				dto.setTotalStock(rs.getInt("totalStock"));
				dto.setDiscountInfo(rs.getString("discountInfo"));
				dto.setPeriodDiscountStart(rs.getTimestamp("periodDiscountStart"));
				dto.setPeriodDiscountEnd(rs.getTimestamp("periodDiscountEnd"));
				dto.setGoodsDiscountFl(rs.getBoolean("goodsDiscountFl"));
				dto.setGoodsDiscountType(rs.getBoolean("goodsDiscountType"));
				dto.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				dto.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				dto.setCostPrice(rs.getBigDecimal("costPrice"));
				dto.setOriginNm(rs.getString("originNm"));
				dto.setGoodsMustInfo(rs.getString("goodsMustInfo"));
				dto.setKcmarkInfo(rs.getString("kcmarkInfo"));
				dto.setOnlyAdultFl(rs.getBoolean("onlyAdultFl"));
				dto.setTaxFreeFl(rs.getString("taxFreeFl"));
				dto.setTaxPercent(rs.getBigDecimal("taxPercent"));
				dto.setGoodsWeight(rs.getBigDecimal("goodsWeight"));
				dto.setFixedSales(rs.getString("fixedSales"));
				dto.setFixedOrderCnt(rs.getString("fixedOrderCnt"));
				dto.setSalesUnit(rs.getInt("salesUnit"));
				dto.setLimitFl(rs.getBoolean("limitFl"));
				dto.setLimitOption(rs.getString("limitOption"));
				dto.setMinOrderCnt(rs.getInt("minOrderCnt"));
				dto.setMaxOrderCnt(rs.getInt("maxOrderCnt"));
				dto.setRestockFl(rs.getBoolean("restockFl"));
				dto.setRepresentImg(rs.getString("representImg"));
				dto.setSubImg(rs.getString("subImg"));
				dto.setShortDescription(rs.getString("shortDescription"));
				dto.setEventDescription(rs.getString("eventDescription"));
				dto.setGoodsDescription(rs.getString("goodsDescription"));
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
				dto.setOrderCnt(rs.getInt("orderCnt"));
				dto.setOrderGoodsCnt(rs.getInt("orderGoodsCnt"));
				dto.setHitCnt(rs.getInt("hitCnt"));
				dto.setWishCnt(rs.getInt("wishCnt"));
				dto.setReviewCnt(rs.getInt("reviewCnt"));
				dto.setCouponCd(rs.getString("couponCd"));
				dto.setIcon(rs.getString("icon"));
				dto.setDelFl(rs.getBoolean("delFl"));
				dto.setAdminMsg(rs.getString("adminMsg"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDelDt(rs.getTimestamp("delDt"));
				
				list.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return list;
		
	}
	
	
	public ArrayList<goodsDTO> getList(String cateCd, paging p){
		
		ArrayList<goodsDTO> list = new ArrayList<>();
		
		String sql = "select * from goods where goodsSellFl>0 and cateCd like ? order by regDt desc limit ?, ?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,cateCd + "%");
			pstmt.setInt(2, (p.getPageSize() * (p.getPageNo()-1)));
			pstmt.setInt(3, p.getPageSize());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				goodsDTO dto = new goodsDTO();
				
				dto.setGoodsNo(rs.getInt("goodsNo"));
				dto.setGoodsNm(rs.getString("goodsNm"));
				dto.setGoodsSellFl(rs.getInt("goodsSellFl"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setMemberOnly(rs.getBoolean("memberOnly"));
				dto.setApplyFl(rs.getString("applyFl"));
				dto.setApplyMsg(rs.getString("applyMsg"));
				dto.setApplyDt(rs.getTimestamp("applyDt"));
				dto.setSalesStartDt(rs.getTimestamp("salesStartDt"));
				dto.setSalesEndDt((rs.getTimestamp("salesEndDt")));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setBrandCd(rs.getString("brandCd"));
				dto.setMakerNm(rs.getString("makerNm"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setCommission(rs.getBigDecimal("commission"));
				dto.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				dto.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				dto.setDiscountPercent(rs.getBigDecimal("DiscountPercent"));
				dto.setTotalStock(rs.getInt("totalStock"));
				dto.setDiscountInfo(rs.getString("discountInfo"));
				dto.setPeriodDiscountStart(rs.getTimestamp("periodDiscountStart"));
				dto.setPeriodDiscountEnd(rs.getTimestamp("periodDiscountEnd"));
				dto.setGoodsDiscountFl(rs.getBoolean("goodsDiscountFl"));
				dto.setGoodsDiscountType(rs.getBoolean("goodsDiscountType"));
				dto.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				dto.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				dto.setCostPrice(rs.getBigDecimal("costPrice"));
				dto.setOriginNm(rs.getString("originNm"));
				dto.setGoodsMustInfo(rs.getString("goodsMustInfo"));
				dto.setKcmarkInfo(rs.getString("kcmarkInfo"));
				dto.setOnlyAdultFl(rs.getBoolean("onlyAdultFl"));
				dto.setTaxFreeFl(rs.getString("taxFreeFl"));
				dto.setTaxPercent(rs.getBigDecimal("taxPercent"));
				dto.setGoodsWeight(rs.getBigDecimal("goodsWeight"));
				dto.setFixedSales(rs.getString("fixedSales"));
				dto.setFixedOrderCnt(rs.getString("fixedOrderCnt"));
				dto.setSalesUnit(rs.getInt("salesUnit"));
				dto.setLimitFl(rs.getBoolean("limitFl"));
				dto.setLimitOption(rs.getString("limitOption"));
				dto.setMinOrderCnt(rs.getInt("minOrderCnt"));
				dto.setMaxOrderCnt(rs.getInt("maxOrderCnt"));
				dto.setRestockFl(rs.getBoolean("restockFl"));
				dto.setRepresentImg(rs.getString("representImg"));
				dto.setSubImg(rs.getString("subImg"));
				dto.setShortDescription(rs.getString("shortDescription"));
				dto.setEventDescription(rs.getString("eventDescription"));
				dto.setGoodsDescription(rs.getString("goodsDescription"));
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
				dto.setOrderCnt(rs.getInt("orderCnt"));
				dto.setOrderGoodsCnt(rs.getInt("orderGoodsCnt"));
				dto.setHitCnt(rs.getInt("hitCnt"));
				dto.setWishCnt(rs.getInt("wishCnt"));
				dto.setReviewCnt(rs.getInt("reviewCnt"));
				dto.setCouponCd(rs.getString("couponCd"));
				dto.setIcon(rs.getString("icon"));
				dto.setDelFl(rs.getBoolean("delFl"));
				dto.setAdminMsg(rs.getString("adminMsg"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDelDt(rs.getTimestamp("delDt"));
				
				list.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return list;
	}
	
	public ArrayList<goodsDTO> getToCondition(String condition, paging p){
		
		ArrayList<goodsDTO> list = new ArrayList<>();
		
		String sql = "select * from goods " + condition +" limit "+ (p.getPageSize() * (p.getPageNo()-1)) + ", " + p.getPageSize();
		
		try{
			stmt = con.createStatement();
			
			System.out.println("dao sql : " + sql);
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				goodsDTO dto = new goodsDTO();
				
				dto.setGoodsNo(rs.getInt("goodsNo"));
				dto.setGoodsNm(rs.getString("goodsNm"));
				dto.setGoodsSellFl(rs.getInt("goodsSellFl"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setMemberOnly(rs.getBoolean("memberOnly"));
				dto.setApplyFl(rs.getString("applyFl"));
				dto.setApplyMsg(rs.getString("applyMsg"));
				dto.setApplyDt(rs.getTimestamp("applyDt"));
				dto.setSalesStartDt(rs.getTimestamp("salesStartDt"));
				dto.setSalesEndDt((rs.getTimestamp("salesEndDt")));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setBrandCd(rs.getString("brandCd"));
				dto.setMakerNm(rs.getString("makerNm"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setCommission(rs.getBigDecimal("commission"));
				dto.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				dto.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				dto.setDiscountPercent(rs.getBigDecimal("DiscountPercent"));
				dto.setTotalStock(rs.getInt("totalStock"));
				dto.setDiscountInfo(rs.getString("discountInfo"));
				dto.setPeriodDiscountStart(rs.getTimestamp("periodDiscountStart"));
				dto.setPeriodDiscountEnd(rs.getTimestamp("periodDiscountEnd"));
				dto.setGoodsDiscountFl(rs.getBoolean("goodsDiscountFl"));
				dto.setGoodsDiscountType(rs.getBoolean("goodsDiscountType"));
				dto.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				dto.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				dto.setCostPrice(rs.getBigDecimal("costPrice"));
				dto.setOriginNm(rs.getString("originNm"));
				dto.setGoodsMustInfo(rs.getString("goodsMustInfo"));
				dto.setKcmarkInfo(rs.getString("kcmarkInfo"));
				dto.setOnlyAdultFl(rs.getBoolean("onlyAdultFl"));
				dto.setTaxFreeFl(rs.getString("taxFreeFl"));
				dto.setTaxPercent(rs.getBigDecimal("taxPercent"));
				dto.setGoodsWeight(rs.getBigDecimal("goodsWeight"));
				dto.setFixedSales(rs.getString("fixedSales"));
				dto.setFixedOrderCnt(rs.getString("fixedOrderCnt"));
				dto.setSalesUnit(rs.getInt("salesUnit"));
				dto.setLimitFl(rs.getBoolean("limitFl"));
				dto.setLimitOption(rs.getString("limitOption"));
				dto.setMinOrderCnt(rs.getInt("minOrderCnt"));
				dto.setMaxOrderCnt(rs.getInt("maxOrderCnt"));
				dto.setRestockFl(rs.getBoolean("restockFl"));
				dto.setRepresentImg(rs.getString("representImg"));
				dto.setSubImg(rs.getString("subImg"));
				dto.setShortDescription(rs.getString("shortDescription"));
				dto.setEventDescription(rs.getString("eventDescription"));
				dto.setGoodsDescription(rs.getString("goodsDescription"));
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
				dto.setOrderCnt(rs.getInt("orderCnt"));
				dto.setOrderGoodsCnt(rs.getInt("orderGoodsCnt"));
				dto.setHitCnt(rs.getInt("hitCnt"));
				dto.setWishCnt(rs.getInt("wishCnt"));
				dto.setReviewCnt(rs.getInt("reviewCnt"));
				dto.setCouponCd(rs.getString("couponCd"));
				dto.setIcon(rs.getString("icon"));
				dto.setDelFl(rs.getBoolean("delFl"));
				dto.setAdminMsg(rs.getString("adminMsg"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDelDt(rs.getTimestamp("delDt"));
				
				list.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return list;
	}
	
	
	//리뷰 삭제여부 1로 변경
	public int reviewDeleteFl(int reviewSno2) {
		int d = 0;
		String sql = "update review set isDelete = 1 , deleteDt = now() where sno =  ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reviewSno2);
			d = pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
		}		
		return d;
	}
	
	//상품 삭제여부 1로 변경
	public int goodsDeleteFl(int goodsNo) {
		int d = 0;
		String sql = "update goods set delFl = 1 , delDt = now() where goodsNo =  ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodsNo);
			d = pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
		}		
		return d;
	}

	
	//상품수정
	public int modGoods(goodsDTO dto) {
		int mod = 0;
		String sql = "update goods set goodsNm = ?, applyFl = ?, cateCd = ?, brandCd = ?,"
				   + "makerNm = ?, keyword = ?, commission = ?, goodsPrice = ?, fixedPrice = ?,"
				   + "discountPercent = ?, costPrice = ?, goodsWeight = ?, taxPercent = ?, originNm = ?,"
				   + "onlyAdultFl = ?, taxFreeFl = ?, fixedOrderCnt = ?, salesUnit = ?, limitFl = ?,"
				   + "maxOrderCnt = ?, minOrderCnt = ?, representImg = ?, subImg = ?, detailInfoDelivery = ?,"
				   + "detailInfoAs = ?,detailInfoRefund = ?,detailInfoExchange = ?, limitOption = ?,"
				   + "goodsDescription = ?, goodsSellFl = ?,memberOnly = ?, modDt = now()   where goodsNo = ?";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getGoodsNm());
			pstmt.setString(2, dto.getApplyFl());
			pstmt.setString(3, dto.getCateCd());
			pstmt.setString(4, dto.getBrandCd());
			pstmt.setString(5, dto.getMakerNm());
			pstmt.setString(6, dto.getKeyword());
			pstmt.setBigDecimal(7, dto.getCommission());
			pstmt.setBigDecimal(8, dto.getGoodsPrice());
			pstmt.setBigDecimal(9, dto.getFixedPrice());			
			pstmt.setBigDecimal(10, dto.getDiscountPercent());
			pstmt.setBigDecimal(11, dto.getCostPrice());
			pstmt.setBigDecimal(12, dto.getGoodsWeight());
			pstmt.setBigDecimal(13, dto.getTaxPercent());
			pstmt.setString(14, dto.getOriginNm());			
			pstmt.setBoolean(15, dto.isOnlyAdultFl());
			pstmt.setString(16, dto.getTaxFreeFl());
			pstmt.setString(17, dto.getFixedOrderCnt());
			pstmt.setInt(18, dto.getSalesUnit());
			pstmt.setBoolean(19, dto.isLimitFl());
			pstmt.setInt(20, dto.getMaxOrderCnt());
			pstmt.setInt(21, dto.getMinOrderCnt());
			pstmt.setString(22, dto.getRepresentImg());
			pstmt.setString(23, dto.getSubImg());
			pstmt.setString(24, dto.getDetailInfoDelivery());
			pstmt.setString(25, dto.getDetailInfoAS());
			pstmt.setString(26, dto.getDetailInfoRefund());
			pstmt.setString(27, dto.getDetailInfoExchange());
			pstmt.setString(28, dto.getLimitOption());
			pstmt.setString(29, dto.getGoodsDescription());
			pstmt.setInt(30, dto.getGoodsSellFl());
			pstmt.setBoolean(31, dto.isMemberOnly());
			pstmt.setInt(32, dto.getGoodsNo());
			
			mod = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		if(mod > 0) {
			System.out.println("업데이트 성공!");
		}
		
		return mod;
	}
	
	public int updateOption(String option, String value, int goodsNo){
		int chk = 0;
		
		String sql = "update goods set " + option + "=" +value +  " where goodsNo=" + goodsNo;
		
		System.out.println(sql);
		try{
			
			stmt = con.createStatement();
			chk = stmt.executeUpdate(sql);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	
}
