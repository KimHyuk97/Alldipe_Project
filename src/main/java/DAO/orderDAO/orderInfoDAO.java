package DAO.orderDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.orderDTO.orderInfoDTO;

public class orderInfoDAO {

	private static orderInfoDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static orderInfoDAO getInstance(){
		if(dao == null) dao = new orderInfoDAO();
		
		return dao;
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	public orderInfoDTO getOrderInfo(String orderNo){
		
		orderInfoDTO dto = new orderInfoDTO();
		
		String sql = "select * from orderInfo where orderNo=?";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto.setSno(rs.getInt("sno"));
				dto.setOrderNo(rs.getString("orderNo"));
				dto.setOrderName(rs.getString("orderName"));
				dto.setOrderEmail(rs.getString("orderEmail"));
				dto.setOrderPhonePrefixCode(rs.getString("orderPhonePrefixCode"));
				dto.setOrderPhonePrefix(rs.getInt("orderPhonePrefix"));
				dto.setOrderPhone(rs.getString("orderPhone"));
				dto.setOrderCellPhonePrefixCode(rs.getString("orderCellPhonePrefixCode"));
				dto.setOrderCellPhonePrefix(rs.getInt("orderCellPhonePrefix"));
				dto.setOrderCellPhone(rs.getString("orderCellPhone"));
				dto.setOrderZonecode(rs.getString("orderZonecode"));
				dto.setOrderAddress(rs.getString("orderAddress"));
				dto.setOrderAddressSub(rs.getString("orderAddressSub"));
				dto.setReceiverName(rs.getString("receiverName"));
				dto.setReceiverCountryCode(rs.getString("receiverCountryCode"));
				dto.setReceiverPhonePrefixCode(rs.getString("receiverPhonePrefixCode"));
				dto.setReceiverPhonePrefix(rs.getInt("receiverPhonePrefix"));
				dto.setReceiverPhone(rs.getString("receiverPhone"));
				dto.setReceiverCellPhonePrefixCode(rs.getString("receiverCellPhonePrefixCode"));
				dto.setReceiverCellPhonePrefix(rs.getInt("receiverCellPhonePrefix"));
				dto.setReceiverCellPhone(rs.getString("receiverCellPhone"));
				dto.setReceiverUseSafeNumberFl(rs.getString("receiverUseSafeNumberFl"));
				dto.setReceiverSafeNumber(rs.getString("receiverSafeNumber"));
				dto.setReceiverSafeNumberDt(rs.getTimestamp("receiverSafeNumberDt"));
				dto.setReceiverZonecode(rs.getString("receiverZonecode"));
				dto.setReceiverAddress(rs.getString("receiverAddress"));
				dto.setReceiverAddressSub(rs.getString("receiverAddressSub"));
				dto.setPickUpType(rs.getString("pickUpType"));
				dto.setPickUpContent(rs.getString("pickUpContent"));
				dto.setOrderMemo(rs.getString("orderMemo"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setSmsFl(rs.getString("smsFl"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
		
	}
	
	
}
