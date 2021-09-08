package DAO.couponDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import dto.couponDTO.memberCouponDTO;

public class memberCouponDAO {

	Connection con;
	static memberCouponDAO dao;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static memberCouponDAO getInstance(){
		if(dao==null)dao=new memberCouponDAO();
		return dao;
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	public ArrayList<memberCouponDTO> list(ResultSet rs){
		ArrayList<memberCouponDTO> list = new ArrayList<>();
		
		try{
			
			while(rs.next()){
				memberCouponDTO dto = new memberCouponDTO();
				
				dto.setSno(rs.getInt("sno"));
				dto.setCouponNo(rs.getInt("couponNo"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setAdminNo(rs.getInt("adminNo"));
				dto.setCouponNm(rs.getString("couponNm"));
				dto.setCouponBenefit(rs.getBigDecimal("couponBenefit"));
				dto.setCouponBenefitType(rs.getBoolean("couponBenefitType"));
				dto.setOrdergoodsNo(rs.getInt("ordergoodsNo"));
				dto.setMemberCouponStartDt(rs.getTimestamp("memberCouponStartDt"));
				dto.setMemberCouponEndDt(rs.getTimestamp("memberCouponEndDt"));
				dto.setMemberCouponUseDt(rs.getTimestamp("memberCouponUseDt"));
				dto.setUseState(rs.getBoolean("useState"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				
				list.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public memberCouponDTO get(ResultSet rs){
		memberCouponDTO dto = null;
		try{
			if(rs.next()){
			
				dto = new memberCouponDTO();
				
				dto.setSno(rs.getInt("sno"));
				dto.setCouponNo(rs.getInt("couponNo"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setAdminNo(rs.getInt("adminNo"));
				dto.setCouponNm(rs.getString("couponNm"));
				dto.setCouponBenefit(rs.getBigDecimal("couponBenefit"));
				dto.setCouponBenefitType(rs.getBoolean("couponBenefitType"));
				dto.setOrdergoodsNo(rs.getInt("ordergoodsNo"));
				dto.setMemberCouponStartDt(rs.getTimestamp("memberCouponStartDt"));
				dto.setMemberCouponEndDt(rs.getTimestamp("memberCouponEndDt"));
				dto.setMemberCouponUseDt(rs.getTimestamp("memberCouponUseDt"));
				dto.setUseState(rs.getBoolean("useState"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	}
	
	
	public ArrayList<memberCouponDTO> getList(String[] arr){
		
		ArrayList<memberCouponDTO> list = null;
		String condition = "";
		for(String str : arr){
			condition +=",?";
		}
		
		String sql = "select * from membercoupon where sno in (" + condition.substring(1) + ")" ;
		
		try{
			pstmt = con.prepareStatement(sql);
			for(int i = 1; i<=arr.length; i++){
				pstmt.setString(i, arr[i]);
			}
			
			rs = pstmt.executeQuery();
			
			list = list(rs);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	
	
	
	
}
