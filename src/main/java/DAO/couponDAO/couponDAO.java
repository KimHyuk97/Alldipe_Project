package DAO.couponDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.couponDTO.couponDTO;

public class couponDAO {

	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
	
	private static couponDAO dao;
	
	public static couponDAO getInstance(){
		if(dao==null){
			dao = new couponDAO();
		}
		return dao;
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	//	쿠폰 입력
	public int insertCoupon(couponDTO dto){
		int chk = 0;
		
		String sql = "insert into coupon (couponKind, useType, saveType, couponNm, couponDesc,"
				+ "usePeriodStartDate, usePeriodEndDate, usedDate, kindType, benefit, benefitType,"
				+ "maxBenefitFl, maxBenefit, displayStartDate, displayEndDate, limitSmsFl, applyProductType, "
				+ "minOrderPrice, applyDuplicateType) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getKind());
			pstmt.setString(2, dto.getUseType());
			pstmt.setString(3, dto.getSaveType());
			pstmt.setString(4, dto.getCouponNm());
			pstmt.setString(5, dto.getCouponDesc());
			pstmt.setDate(6, dto.getUsePeriodStartDate());
			pstmt.setDate(7, dto.getUsePeriodEndDate());
			pstmt.setInt(8, dto.getUsedDate());
			pstmt.setString(9, dto.getKindType());
			pstmt.setBigDecimal(10, dto.getBenefit());
			pstmt.setString(11, dto.getBenefitType());
			pstmt.setBoolean(12, dto.isMaxBenefitFl());
			pstmt.setBigDecimal(13, dto.getMaxBenefit());
			pstmt.setDate(14, dto.getDisplayStartDate());
			pstmt.setDate(15, dto.getDisplayEndDate());
			pstmt.setBoolean(16, dto.isLimitSmsFl());
			pstmt.setString(17, dto.getApplyProductType());
			pstmt.setBigDecimal(18, dto.getMinOrderPrice());
			pstmt.setBoolean(19, dto.isApplyDuplicateType());
			
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
//	쿠폰 리스트 받아오기
	public ArrayList<couponDTO> getList(){
		ArrayList<couponDTO> list = null;
		
		String sql = "select * from coupon";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			list = list(rs);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return list;
	}
	
	//쿠폰정보
	public couponDTO getCoupon(String couponNo) {		
		couponDTO dto = new couponDTO();
		String sql = "select * from coupon where couponNo = ?";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, couponNo);
			rs = pstmt.executeQuery();
			
			dto = get(rs);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return dto;
	}
	
	
//	리스트 만들기
	private ArrayList<couponDTO> list(ResultSet rs){
		ArrayList<couponDTO> list = new ArrayList<>();
		
		try{
			
			while(rs.next()){
				couponDTO dto = new couponDTO();
				
				dto.setCouponNo(rs.getInt("couponNo"));
				dto.setKind(rs.getString("couponKind"));
				dto.setState(rs.getString("couponState"));
				dto.setUseType(rs.getString("couponUseType"));
				dto.setSaveType(rs.getString("couponSaveType"));
				dto.setCouponNm(rs.getString("couponNm"));
				dto.setCouponDesc(rs.getString("couponDescribed"));
				dto.setUsedDate(rs.getInt("couponUsedDay"));
				dto.setKindType(rs.getString("couponKindType"));
				dto.setBenefit(rs.getBigDecimal("couponBenefit"));
				dto.setBenefitType(rs.getString("couponBenefitType"));
				dto.setBenefitFixApply(rs.getString("couponBenefitFixApply"));
				dto.setMaxBenefitFl(rs.getBoolean("couponMaxBenefitType"));
				dto.setMaxBenefit(rs.getBigDecimal("couponMaxBenefit"));
				dto.setDisplayStartDate(rs.getDate("couponDisplayStartDate"));
				dto.setDisplayEndDate(rs.getDate("couponDisplayEndDate"));
				dto.setLimitSmsFl(rs.getBoolean("couponLimitSmsFl"));
				dto.setUseAblePaymentType(rs.getString("couponUseAblePaymentType"));
				dto.setAmountType(rs.getBoolean("couponAmountType"));
				dto.setAmount(rs.getInt("couponAmount"));
				dto.setSaveDuplicateType(rs.getBoolean("couponSaveDuplicateType"));
				dto.setSaveDuplicateLimitType(rs.getBoolean("couponSaveDuplicateLimitType"));
				dto.setSaveDuplicateLimit(rs.getInt("couponSaveDuplicateLimit"));
				dto.setApplyMemberGroup(rs.getString("couponApplyMemberGroup"));
				dto.setApplyMemberGroupDisplayType(rs.getString("couponApplyMemberGroupDisplayType"));
				dto.setApplyProductType(rs.getString("couponApplyProductType"));
				dto.setApplyScm(rs.getString("couponApplyProvider"));
				dto.setApplyCategory(rs.getString("couponApplyCategory"));
				dto.setApplyBrand(rs.getString("couponApplyBrand"));
				dto.setApplyGoods(rs.getString("couponApplyGoods"));
				dto.setMinOrderPrice(rs.getBigDecimal("couponMinOrderPrice"));
				dto.setApplyDuplicateType(rs.getBoolean("couponApplyDuplicateType"));
				dto.setAdminNo(rs.getInt("adminNo"));
				dto.setCouponSaveCnt(rs.getInt("couponSaveCount"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				
				list.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
//	객체 만들기
	private couponDTO get(ResultSet rs){
		
		couponDTO dto = new couponDTO();
		
		try{
			if(rs.next()){	
				dto.setCouponNo(rs.getInt("couponNo"));
				dto.setKind(rs.getString("couponKind"));
				dto.setState(rs.getString("couponState"));
				dto.setUseType(rs.getString("couponUseType"));
				dto.setSaveType(rs.getString("couponSaveType"));
				dto.setCouponNm(rs.getString("couponNm"));
				dto.setCouponDesc(rs.getString("couponDescribed"));
				dto.setUsedDate(rs.getInt("couponUsedDay"));
				dto.setKindType(rs.getString("couponKindType"));
				dto.setBenefit(rs.getBigDecimal("couponBenefit"));
				dto.setBenefitType(rs.getString("couponBenefitType"));
				dto.setBenefitFixApply(rs.getString("couponBenefitFixApply"));
				dto.setMaxBenefitFl(rs.getBoolean("couponMaxBenefitType"));
				dto.setMaxBenefit(rs.getBigDecimal("couponMaxBenefit"));
				dto.setDisplayStartDate(rs.getDate("couponDisplayStartDate"));
				dto.setDisplayEndDate(rs.getDate("couponDisplayEndDate"));
				dto.setLimitSmsFl(rs.getBoolean("couponLimitSmsFl"));
				dto.setUseAblePaymentType(rs.getString("couponUseAblePaymentType"));
				dto.setAmountType(rs.getBoolean("couponAmountType"));
				dto.setAmount(rs.getInt("couponAmount"));
				dto.setSaveDuplicateType(rs.getBoolean("couponSaveDuplicateType"));
				dto.setSaveDuplicateLimitType(rs.getBoolean("couponSaveDuplicateLimitType"));
				dto.setSaveDuplicateLimit(rs.getInt("couponSaveDuplicateLimit"));
				dto.setApplyMemberGroup(rs.getString("couponApplyMemberGroup"));
				dto.setApplyMemberGroupDisplayType(rs.getString("couponApplyMemberGroupDisplayType"));
				dto.setApplyProductType(rs.getString("couponApplyProductType"));
				dto.setApplyScm(rs.getString("couponApplyProvider"));
				dto.setApplyCategory(rs.getString("couponApplyCategory"));
				dto.setApplyBrand(rs.getString("couponApplyBrand"));
				dto.setApplyGoods(rs.getString("couponApplyGoods"));
				dto.setMinOrderPrice(rs.getBigDecimal("couponMinOrderPrice"));
				dto.setApplyDuplicateType(rs.getBoolean("couponApplyDuplicateType"));
				dto.setAdminNo(rs.getInt("adminNo"));
				dto.setCouponSaveCnt(rs.getInt("couponSaveCount"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	}
	
	
}
