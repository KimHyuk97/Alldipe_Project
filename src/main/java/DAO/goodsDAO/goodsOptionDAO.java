package DAO.goodsDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.goodsDTO.goodsOptionDTO;

public class goodsOptionDAO {

	private static goodsOptionDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static goodsOptionDAO getInstance(){
		if(dao == null){
			dao = new goodsOptionDAO();
		}
		return dao;
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	public int insertList(ArrayList<goodsOptionDTO> list){
		
		int chk = 0;
		
		for(goodsOptionDTO dto : list){
			System.out.println(dto.getGoodsNo() + " 옵션" + dto.getOptionNo());
			chk = insertDto(dto);
			System.out.println("chk : " + chk);
		}
		System.out.println("리스트 등록 완료!");
		
		return chk;
	}
	
	public int insertListTemp(ArrayList<goodsOptionDTO> list){
		
		int chk = 0;
		
		for(goodsOptionDTO dto : list){
			
			chk = insertDtoTemp(dto);
			System.out.println("chk : " + chk);
		}
		System.out.println("리스트 등록 완료!");
		
		return chk;
	}
	
	public int insertDto(goodsOptionDTO dto){
		String sql = "insert into goodsoption (goodsNo, optionNo, optionNm1,optionValue1,"
				+ "optionNm2, optionValue2, optionNm3, optionValue3, optionNm4, optionValue4,"
				+ "optionNm5, optionValue5, optionPrice, optionfixedPrice, stockCnt,"
				+ "optionMemo, regDt) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
		int chk = 0;
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
			pstmt.setBigDecimal(13, dto.getOptionPrice());
			pstmt.setBigDecimal(14, dto.getOptionFixedPrice());
			pstmt.setInt(15, dto.getStockCnt());
			pstmt.setString(16, dto.getOptionMemo());
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	public int insertDtoTemp(goodsOptionDTO dto){
		String sql = "insert into goodsoptiontemp (goodsNo, optionNo, optionNm1,optionValue1,"
				+ "optionNm2, optionValue2, optionNm3, optionValue3, optionNm4, optionValue4,"
				+ "optionNm5, optionValue5, optionPrice, optionfixedPrice, stockCnt,"
				+ "optionMemo) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int chk = 0;
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
			pstmt.setBigDecimal(13, dto.getOptionPrice());
			pstmt.setBigDecimal(14, dto.getOptionFixedPrice());
			pstmt.setInt(15, dto.getStockCnt());
			pstmt.setString(16, dto.getOptionMemo());
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	public ArrayList<goodsOptionDTO> getList(int goodsNo){
		ArrayList<goodsOptionDTO> list = new ArrayList<>();
		
		String sql = "select * from goodsoption where goodsNo=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodsNo);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				goodsOptionDTO dto = new goodsOptionDTO();
				
				System.out.println("sno : " + rs.getInt("sno"));
				System.out.println("optionNm1 : " + rs.getString("optionNm1"));
				System.out.println("optionValue1 : " + rs.getString("optionValue1"));
				
				dto.setSno(rs.getInt("sno"));
				dto.setGoodsNo(rs.getInt("goodsNo"));
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
				dto.setOptionViewFl(rs.getBoolean("optionViewFl"));
				dto.setOptionSellFl(rs.getBoolean("optionSellFl"));
				dto.setStockCnt(rs.getInt("stockCnt"));
				dto.setOptionMemo(rs.getString("optionMemo"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				
				list.add(dto);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	//goodsoptiondelete
	public int deleteGoodsOption(int goodsNo) {
		int chk = 0;
		
		String sql = "delete from goodsoption where goodsNo = ?";
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodsNo);
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	//optionUpdate
	public int updateGoodsOption(goodsOptionDTO dto) {
		int chk = 0;
				
		String sql = "update goodsoption set optionNo = ?, optionNm1 = ?, optionValue1 =?,"
				+ "optionNm2 = ?, optionValue2 = ?, optionNm3 = ?, optionValue3 = ?, optionNm4 = ?, optionValue4 = ?,"
				+ "optionNm5 = ?, optionValue5 = ?, sellerCd = ?, optionPrice = ?, optionfixedPrice = ?,"
				+ "stockCnt = ?, optionMemo = ?, modDt = now() where sno = ?";
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getOptionNo());
			pstmt.setString(2, dto.getOptionNm1());
			pstmt.setString(3, dto.getOptionValue1());
			pstmt.setString(4, dto.getOptionNm2());
			pstmt.setString(5, dto.getOptionValue2());
			pstmt.setString(6, dto.getOptionNm3());
			pstmt.setString(7, dto.getOptionValue3());
			pstmt.setString(8, dto.getOptionNm4());
			pstmt.setString(9, dto.getOptionValue4());
			pstmt.setString(10, dto.getOptionNm5());
			pstmt.setString(11, dto.getOptionValue5());
			pstmt.setString(12, dto.getSellerCd());
			pstmt.setBigDecimal(13, dto.getOptionPrice());
			pstmt.setBigDecimal(14, dto.getOptionFixedPrice());
			pstmt.setInt(15, dto.getStockCnt());
			pstmt.setString(16, dto.getOptionMemo());
			pstmt.setInt(17, dto.getSno());

			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	public goodsOptionDTO getGoodsoption(int sno){
		
		goodsOptionDTO dto = null;
		String sql = "select * from goodsoption where sno=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto = new goodsOptionDTO();
				dto.setSno(rs.getInt("sno"));
				dto.setGoodsNo(rs.getInt("goodsNo"));
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
				dto.setSellerCd(rs.getString("sellerCd"));
				dto.setOptionPrice(rs.getBigDecimal("optionPrice"));
				dto.setOptionFixedPrice(rs.getBigDecimal("optionFixedPrice"));
				dto.setOptionViewFl(rs.getBoolean("optionViewFl"));
				dto.setOptionSellFl(rs.getBoolean("optionSellFl"));
				dto.setStockCnt(rs.getInt("stockCnt"));
				dto.setOptionMemo(rs.getString("optionMemo"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
		
	}

	
}
