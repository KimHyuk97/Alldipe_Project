package DAO.orderDAO;

import static db.JdbcUtil.close;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.couponDTO.couponDTO;
import dto.couponDTO.memberCouponDTO;
import dto.goodsDTO.goodsDTO;
import dto.goodsDTO.goodsOptionDTO;
import dto.orderDTO.orderDTO;
import dto.orderDTO.orderGoods;

public class orderDAO {
	private static orderDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;


	public static orderDAO getInstance() {
		if (dao == null) {
			dao = new orderDAO();
		}
		return dao;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//맴버 쿠폰리스트
	public List<memberCouponDTO> membercouponList(int memNo) {
		List<memberCouponDTO> cd = new ArrayList<memberCouponDTO>();
		String sql = "select * from membercoupon where memNo = ? and memberCouponState = 'y'";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				memberCouponDTO mc = new memberCouponDTO();
				mc.setSno(rs.getInt("sno"));
				mc.setCouponNo(rs.getInt("couponNo"));
				mc.setMemNo(rs.getInt("memNo"));
				mc.setAdminNo(rs.getInt("adminNo"));
				mc.setCouponNm(rs.getString("couponNm"));
				mc.setCouponBenefit(rs.getBigDecimal("couponBenefit"));
				mc.setOrdergoodsNo(rs.getInt("goodsNo"));
				mc.setMemberCouponStartDt(rs.getTimestamp("memberCouponStartDate"));
				mc.setMemberCouponEndDt(rs.getTimestamp("memberCouponEndDate"));
				mc.setMemberCouponUseDt(rs.getTimestamp("memberCouponUseDate"));
				mc.setUseState(rs.getBoolean("useState"));
				mc.setRegDt(rs.getTimestamp("regDt"));
				cd.add(mc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return cd;
	}
	
	//적용상품 알아보기
	public couponDTO couponApplyProductType(int couponNo) {
		couponDTO c = new couponDTO();
		String sql = "select couponNo,couponApplyProductType from coupon where couponNo = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, couponNo);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				c.setCouponNo(rs.getInt("couponNo"));
				c.setApplyProductType(rs.getString("couponApplyProductType"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return c;
	}
	
	//productType이 올이면
	public memberCouponDTO couponProductTypeAll(int memNo, int couponNo) {
		memberCouponDTO mc = new memberCouponDTO();
		String sql = "select * from membercoupon where memNo = ? and couponNo = ?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, couponNo);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				mc.setSno(rs.getInt("sno"));
				mc.setCouponNo(rs.getInt("couponNo"));
				mc.setMemNo(rs.getInt("memNo"));
				mc.setAdminNo(rs.getInt("adminNo"));
				mc.setCouponNm(rs.getString("couponNm"));
				mc.setCouponBenefit(rs.getBigDecimal("couponBenefit"));
				mc.setOrdergoodsNo(rs.getInt("goodsNo"));
				mc.setMemberCouponStartDt(rs.getTimestamp("memberCouponStartDate"));
				mc.setMemberCouponEndDt(rs.getTimestamp("memberCouponEndDate"));
				mc.setMemberCouponUseDt(rs.getTimestamp("memberCouponUseDate"));
				mc.setUseState(rs.getBoolean("useState"));
				mc.setRegDt(rs.getTimestamp("regDt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return mc;
	}
	
	//상품 카테고리코드, 브랜드코드
	public goodsDTO goodsInfo(String goodsNo) {
		goodsDTO gcb = new goodsDTO();
		String sql = "select cateCd,brandCd from goods where goodsNo = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, goodsNo);

			rs= pstmt.executeQuery();
			if(rs.next()) {
				gcb.setBrandCd(rs.getString("brandCd"));
				gcb.setCateCd(rs.getString("cateCd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}

		return gcb;
	}
	
	
	
	
	//productType이 all이 아니면
	public int couponProductTypeNotAll(int memNo, int couponNo, int goodsNo, String brandCd, String cateCd,
			int scmNo) {
		int mc = 0;
		String sql = "select couponNo from coupon where couponNo = ? and (couponApplyProvider LIKE ? || couponApplyCategory LIKE ? || "
				                                                 + "couponApplyBrand LIKE ? || couponApplyGoods LIKE ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, couponNo);
			pstmt.setString(2, "%"+scmNo+"%");
			pstmt.setString(3, "%"+cateCd+"%");
			pstmt.setString(4, "%"+brandCd+"%");
			pstmt.setString(5, "%"+goodsNo+"%");
			rs= pstmt.executeQuery();
			if(rs.next()) {
				mc = rs.getInt(couponNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return mc;
	}
	
	
	//주문자 , 수취인 정보
	public int orderInfo(String orderNo, String orderName, String orderEmail, String orderPhone, String orderCellPhone,
			String receiverName, String receiverPhone, String receiverCellPhone, String receiverZonecode,
			String receiverAddress, String receiverAddressSub, String pickUpType, String pickUpContent,
			String meansType, String meansContent, String orderMemo) {
		int oi = 0;
		String sql = "INSERT INTO orderinfo (orderNo,orderName,orderEmail,orderPhone,orderCellPhone,receiverName,receiverPhone,"
				+ "receiverCellPhone,receiverZonecode,receiverAddress,receiverAddressSub,pickUpType,pickUpContent,meansType,meansContent,"
				+ "orderMemo,regDt) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW())";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			pstmt.setString(2, orderName);
			pstmt.setString(3, orderEmail);
			pstmt.setString(4, orderPhone);
			pstmt.setString(5, orderCellPhone);
			
			pstmt.setString(6, receiverName);
			pstmt.setString(7, receiverPhone);
			pstmt.setString(8, receiverCellPhone);
			pstmt.setString(9, receiverZonecode);
			pstmt.setString(10, receiverAddress);
			pstmt.setString(11, receiverAddressSub);
			pstmt.setString(12, pickUpType);
			pstmt.setString(13, pickUpContent);
			pstmt.setString(14, meansType);
			pstmt.setString(15, meansContent);
			pstmt.setString(16, orderMemo);
			oi=pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
		}

		return oi;
	}
	
	//orderList
	public int insertOrder(orderDTO dto) {
		
		int order = 0;
		
		String sql = "INSERT INTO orderlist(orderNo,memNo,orderStatus,orderIp,orderEmail,settlePrice,taxSupplyPrice,taxVatPrice,taxFreePrice," //9				
				+ "realTaxSupplyPrice,realTaxVatPrice,realTaxFreePrice,useMileage,useDeposit,totalGoodsDcPrice,totalMemberDcPrice," 		   //7	
				+ "totalMileage,totalDeliveryCharge,totalGoodsPrice,mileageGiveExclude,eventCouponFl,pgResultCode,pgTid,pgAuthCode," 		   //8
				+ "pgAuthDate,transType,settleKind,"																						   //3
				+ "bankCode,bankName,RcptType,RcptTID,RcptAuthCode,VbankBankCode,VbankBankName,VbankNum,VbankExpDate,VbankExpTime,CardCode,"   //11
				+ "CardName,CardNo,CardQuota,CardInterest,AcquCardCode,AcquCardName,CardCl,CcPartCl,ClickpayCl,couponAmt,couponMinAmt,pointAppAmt,"	//12
				+ "MultiCl,MultiCardAcquAmt,MultiPointAmt,MultiCouponAmt,regDt)"																	//4
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW())";

		try {
			pstmt=con.prepareStatement(sql);
			
			
						
			order=pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return order;
	}
	
	public orderDTO getOrder(String orderNo){
		
		orderDTO dto = new orderDTO();
		
		String sql = "select * from orderlist where orderNo=?";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto.setOrderNo(rs.getString("orderNo"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setOrderStatus(rs.getString("orderStatus"));
				dto.setOrderIp(rs.getString("orderIp"));
				dto.setOrderEmail(rs.getString("orderEmail"));
				dto.setSettlePrice(rs.getBigDecimal("settlePrice"));
				dto.setTaxSupplyPrice(rs.getBigDecimal("taxSupplyPrice"));
				dto.setTaxVatPrice(rs.getBigDecimal("taxVatPrice"));
				dto.setTaxFreePrice(rs.getBigDecimal("taxFreePrice"));
				dto.setRealTaxSupplyPrice(rs.getBigDecimal("realTaxSupplyPrice"));
				dto.setRealTaxVatPrice(rs.getBigDecimal("realTaxVatPrice"));
				dto.setRealTaxFreePrice(rs.getBigDecimal("realTaxFreePrice"));
				dto.setUseMileage(rs.getBigDecimal("useMileage"));
				dto.setUseDeposit(rs.getBigDecimal("useDeposit"));
				dto.setTotalGoodsDcPrice(rs.getBigDecimal("totalGoodsDcPrice"));
				dto.setTotalMemberDcPrice(rs.getBigDecimal("totalMemberDcPrice"));
				dto.setTotalMileage(rs.getBigDecimal("totalMileage"));
				dto.setTotalEnuriDcPrice(rs.getBigDecimal("totalEnuriDcPrice"));
				dto.setTotalDeliveryCharge(rs.getBigDecimal("totalDeliveryCharge"));
				dto.setTotalGoodsPrice(rs.getBigDecimal("totalGoodsPrice"));
				dto.setMileageGiveExclude(rs.getBoolean("mileageGiveExclude"));
				dto.setEventCouponFl(rs.getBoolean("eventCouponFl"));
				dto.setAdminMemo(rs.getString("adminMemo"));
				dto.setOrderPGLog(rs.getString("orderPgLog"));
				dto.setOrderDeliveryLog(rs.getString("orderDeliveryLog"));
				dto.setPgName(rs.getString("pgName"));
				dto.setPgResultCode(rs.getString("pgResultCode"));
				dto.setPgTid(rs.getString("pgTid"));
				dto.setPgAuthCode(rs.getString("pgAuthCode"));
				dto.setPgAuthDate(rs.getString("pgAuthDate"));
				dto.setTransType(rs.getString("transType"));
				dto.setSettleKind(rs.getString("settleKind"));
				dto.setBankCode(rs.getString("bankCode"));
				dto.setBankName(rs.getString("bankName"));
				dto.setRcptType(rs.getString("rcptType"));
				dto.setRcptTID(rs.getString("rcptTid"));
				dto.setRcptAuthCode(rs.getString("rcptAuthCode"));
				dto.setVbankBankCode(rs.getString("vbankBankCode"));
				dto.setVbankBankName(rs.getString("vbankBankName"));
				dto.setVbankNum(rs.getString("vbankNum"));
				dto.setVbankExpDate(rs.getString("vbankExpDate"));
				dto.setVbankExpTime(rs.getString("vbankExpTime"));
				dto.setCardCode(rs.getString("cardCode"));
				dto.setCardName(rs.getString("cardName"));
				dto.setCardNo(rs.getString("cardNo"));
				dto.setCardQuota(rs.getString("cardQuota"));
				dto.setCardInterest(rs.getString("cardInterest"));
				dto.setAcquCardCode(rs.getString("acquCardCode"));
				dto.setAcquCardName(rs.getString("acquCardName"));
				dto.setCardCl(rs.getString("cardCl"));
				dto.setCcPartCl(rs.getString("CcpartCl"));
				dto.setClickPayCl(rs.getString("ClickPayCl"));
				dto.setCouponAmt(rs.getBigDecimal("couponAmt"));
				dto.setCouponMinAmt(rs.getBigDecimal("couponMinAmt"));
				dto.setPointAppAmt(rs.getBigDecimal("pointAppAmt"));
				dto.setMultiCl(rs.getString("MultiCl"));
				dto.setMultiCardAcquAmt(rs.getInt("MultiCardAcquAmt"));
				dto.setMultiPointAmt(rs.getInt("MultiPointAmt"));
				dto.setMultiCouponAmt(rs.getInt("MultiCouponAmt"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return dto;
		
	}

	//orderList
	public List<orderDTO> getOrderAll(String time1) {
		List<orderDTO> od = new ArrayList<orderDTO>();
		String sql = "select * from orderlist where regDt > ? order by regDt desc LIMIT 3";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, time1);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				orderDTO dto = new orderDTO();
				dto.setOrderNo(rs.getString("orderNo"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setOrderStatus(rs.getString("orderStatus"));
				dto.setOrderIp(rs.getString("orderIp"));
				dto.setOrderEmail(rs.getString("orderEmail"));
				dto.setSettlePrice(rs.getBigDecimal("settlePrice"));
				dto.setTaxSupplyPrice(rs.getBigDecimal("taxSupplyPrice"));
				dto.setTaxVatPrice(rs.getBigDecimal("taxVatPrice"));
				dto.setTaxFreePrice(rs.getBigDecimal("taxFreePrice"));
				dto.setRealTaxSupplyPrice(rs.getBigDecimal("realTaxSupplyPrice"));
				dto.setRealTaxVatPrice(rs.getBigDecimal("realTaxVatPrice"));
				dto.setRealTaxFreePrice(rs.getBigDecimal("realTaxFreePrice"));
				dto.setUseMileage(rs.getBigDecimal("useMileage"));
				dto.setUseDeposit(rs.getBigDecimal("useDeposit"));
				dto.setTotalGoodsDcPrice(rs.getBigDecimal("totalGoodsDcPrice"));
				dto.setTotalMemberDcPrice(rs.getBigDecimal("totalMemberDcPrice"));
				dto.setTotalMileage(rs.getBigDecimal("totalMileage"));
				dto.setTotalEnuriDcPrice(rs.getBigDecimal("totalEnuriDcPrice"));
				dto.setTotalDeliveryCharge(rs.getBigDecimal("totalDeliveryCharge"));
				dto.setTotalGoodsPrice(rs.getBigDecimal("totalGoodsPrice"));
				dto.setMileageGiveExclude(rs.getBoolean("mileageGiveExclude"));
				dto.setEventCouponFl(rs.getBoolean("eventCouponFl"));
				dto.setAdminMemo(rs.getString("adminMemo"));
				dto.setOrderPGLog(rs.getString("orderPgLog"));
				dto.setOrderDeliveryLog(rs.getString("orderDeliveryLog"));
				dto.setPgName(rs.getString("pgName"));
				dto.setPgResultCode(rs.getString("pgResultCode"));
				dto.setPgTid(rs.getString("pgTid"));
				dto.setPgAuthCode(rs.getString("pgAuthCode"));
				dto.setPgAuthDate(rs.getString("pgAuthDate"));
				dto.setTransType(rs.getString("transType"));
				dto.setSettleKind(rs.getString("settleKind"));
				dto.setBankCode(rs.getString("bankCode"));
				dto.setBankName(rs.getString("bankName"));
				dto.setRcptType(rs.getString("rcptType"));
				dto.setRcptTID(rs.getString("rcptTid"));
				dto.setRcptAuthCode(rs.getString("rcptAuthCode"));
				dto.setVbankBankCode(rs.getString("vbankBankCode"));
				dto.setVbankBankName(rs.getString("vbankBankName"));
				dto.setVbankNum(rs.getString("vbankNum"));
				dto.setVbankExpDate(rs.getString("vbankExpDate"));
				dto.setVbankExpTime(rs.getString("vbankExpTime"));
				dto.setCardCode(rs.getString("cardCode"));
				dto.setCardName(rs.getString("cardName"));
				dto.setCardNo(rs.getString("cardNo"));
				dto.setCardQuota(rs.getString("cardQuota"));
				dto.setCardInterest(rs.getString("cardInterest"));
				dto.setAcquCardCode(rs.getString("acquCardCode"));
				dto.setAcquCardName(rs.getString("acquCardName"));
				dto.setCardCl(rs.getString("cardCl"));
				dto.setCcPartCl(rs.getString("CcpartCl"));
				dto.setClickPayCl(rs.getString("ClickPayCl"));
				dto.setCouponAmt(rs.getBigDecimal("couponAmt"));
				dto.setCouponMinAmt(rs.getBigDecimal("couponMinAmt"));
				dto.setPointAppAmt(rs.getBigDecimal("pointAppAmt"));
				dto.setMultiCl(rs.getString("MultiCl"));
				dto.setMultiCardAcquAmt(rs.getInt("MultiCardAcquAmt"));
				dto.setMultiPointAmt(rs.getInt("MultiPointAmt"));
				dto.setMultiCouponAmt(rs.getInt("MultiCouponAmt"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				
				od.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return od;
	}	

}
