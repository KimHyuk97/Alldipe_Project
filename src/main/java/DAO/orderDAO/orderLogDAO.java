package DAO.orderDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.orderDTO.orderLogDTO;

public class orderLogDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	private static orderLogDAO dao;

	public static orderLogDAO getInstance(){
		if(dao==null){
			dao = new orderLogDAO();
		}
		return dao;
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	public int insertLog(orderLogDTO dto){
		int chk = 0;
		
		String sql = "insert into orderlog (memNo, memIp, orderNo, goodsNO, before, after, logDesc)"
				+ " values (?,?,?,?,?,?,?)";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getMemNo());
			pstmt.setString(2, dto.getMemIp());
			pstmt.setString(3, dto.getOrderNo());
			pstmt.setInt(4, dto.getGoodsNo());
			pstmt.setString(5, dto.getBefore());
			pstmt.setString(6, dto.getAfter());
			pstmt.setString(7, dto.getLogDesc());
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	
}
