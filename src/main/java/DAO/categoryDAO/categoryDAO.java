package DAO.categoryDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.categoryDTO.categoryDTO;
import dto.goodsDTO.goodsDTO;

public class categoryDAO {
	private static categoryDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public static categoryDAO getInstance() {
		if (dao == null) {
			dao = new categoryDAO();
		}
		return dao;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//카테고리리스트
	public List<categoryDTO> categoryList() {
		List<categoryDTO> lc = new ArrayList<categoryDTO>(); 
		
		String sql = "select sno,cateNm,cateCd from category where length(cateCd) = 3 and cateDisplayFl = 0";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				categoryDTO lc2 = new categoryDTO();
				lc2.setSno(rs.getInt("sno"));
				lc2.setCateNm(rs.getString("cateNm"));
				lc2.setCateCd(rs.getString("cateCd"));
				lc.add(lc2);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lc;
	}
	
	//중분류 카테고리 리스트
	public List<categoryDTO> mediumCategoryList(String cateCd) {
		List<categoryDTO> mlc = new ArrayList<categoryDTO>(); 
		
		String sql = "select sno,cateNm,cateCd from category where length(cateCd) = 6 and cateDisplayFl = 0 and cateCd Like ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cateCd+"%");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				categoryDTO mlc2 = new categoryDTO();
				mlc2.setSno(rs.getInt("sno"));
				mlc2.setCateNm(rs.getString("cateNm"));
				mlc2.setCateCd(rs.getString("cateCd"));
				mlc.add(mlc2);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mlc;
	}
	
	//소분류 카테고리 리스트
	public List<categoryDTO> smallCategoryList(String cateCd) {
		List<categoryDTO> slc = new ArrayList<categoryDTO>(); 
		
		String sql = "select sno,cateNm,cateCd from category where length(cateCd) = 9 and cateDisplayFl = 0 and cateCd Like ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cateCd+"%");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				categoryDTO slc2 = new categoryDTO();
				slc2.setSno(rs.getInt("sno"));
				slc2.setCateNm(rs.getString("cateNm"));
				slc2.setCateCd(rs.getString("cateCd"));
				slc.add(slc2);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("cate == "+slc);
		return slc;
	}
	
public ArrayList<categoryDTO> getAllCategory(){
		
		ArrayList<categoryDTO> list = new ArrayList<>();
		
		String sql = "select * from category";
		
		
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				categoryDTO dto = new categoryDTO();
				
				dto.setSno(rs.getInt("sno"));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setCateNm(rs.getString("cateNm"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setCateDisplayFl(rs.getBoolean("cateDisplayFl"));
				dto.setCateImg(rs.getString("cateImg"));
				dto.setCateOverImg(rs.getString("cateOverImg"));
				dto.setCateOnlyAdultFl(rs.getBoolean("cateOnlyAdultFl"));
				dto.setCateOnlyAdultDisplayFl(rs.getBoolean("cateOnlyAdultDisplayFl"));
				dto.setCateSort(rs.getInt("cateSort"));
				dto.setRecomGoodsNo(rs.getString("recomGoodsNo"));
				dto.setCommission(rs.getBigDecimal("commission"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				
				list.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<categoryDTO> getCateList(int idx){
		
		ArrayList<categoryDTO> list = new ArrayList<>();
		
		String sql = "select * from category where length(cateCd)=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				categoryDTO dto = new categoryDTO();
				
				dto.setSno(rs.getInt("sno"));
				dto.setCateNm(rs.getString("cateNm"));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setAdsgoods(rs.getString("adsgoods"));
				dto.setCateDisplayFl(rs.getBoolean("cateDisplayFl"));
				dto.setCateImg(rs.getString("cateImg"));
				dto.setCateOverImg(rs.getString("cateOverImg"));
				dto.setCateOnlyAdultFl(rs.getBoolean("cateOnlyAdultFl"));
				dto.setCateOnlyAdultDisplayFl(rs.getBoolean("cateOnlyAdultDisplayFl"));
				dto.setCateSort(rs.getInt("cateSort"));
				dto.setRecomGoodsNo(rs.getString("recomGoodsNo"));
				dto.setCommission(rs.getBigDecimal("commission"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<categoryDTO> getCateList(String cateCd, int idx){
		
		ArrayList<categoryDTO> list = new ArrayList<>();
		
		String sql = "select * from category where cateCd like ? and length(cateCd)=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cateCd + "%");
			pstmt.setInt(2, idx);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				categoryDTO dto = new categoryDTO();
				
				dto.setSno(rs.getInt("sno"));
				dto.setCateNm(rs.getString("cateNm"));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setAdsgoods(rs.getString("adsgoods"));
				dto.setCateDisplayFl(rs.getBoolean("cateDisplayFl"));
				dto.setCateImg(rs.getString("cateImg"));
				dto.setCateOverImg(rs.getString("cateOverImg"));
				dto.setCateOnlyAdultFl(rs.getBoolean("cateOnlyAdultFl"));
				dto.setCateOnlyAdultDisplayFl(rs.getBoolean("cateOnlyAdultDisplayFl"));
				dto.setCateSort(rs.getInt("cateSort"));
				dto.setRecomGoodsNo(rs.getString("recomGoodsNo"));
				dto.setCommission(rs.getBigDecimal("commission"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				
				list.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public categoryDTO getCate(String cateCd){
		
		categoryDTO dto = null;
		
		String sql = "select * from category where cateCd=?";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cateCd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new categoryDTO();
				
				dto.setSno(rs.getInt("sno"));
				dto.setCateNm(rs.getString("cateNm"));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setAdsgoods(rs.getString("adsgoods"));
				dto.setCateDisplayFl(rs.getBoolean("cateDisplayFl"));
				dto.setCateImg(rs.getString("cateImg"));
				dto.setCateOverImg(rs.getString("cateOverImg"));
				dto.setCateOnlyAdultFl(rs.getBoolean("cateOnlyAdultFl"));
				dto.setCateOnlyAdultDisplayFl(rs.getBoolean("cateOnlyAdultDisplayFl"));
				dto.setCateSort(rs.getInt("cateSort"));
				dto.setRecomGoodsNo(rs.getString("recomGoodsNo"));
				dto.setCommission(rs.getBigDecimal("commission"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}
	
	//카테고리를 생성하기 전에 상위카테고리코드의 제일 마지막번째의 카테고리코드를 가지고 간다.
	public String getCateCd(String cateCd, int i) {
		String code = "";
		String sql = "select max(cateCd) from category where cateCd like ? and length(cateCd)=?";
		try{
			pstmt = con.prepareStatement(sql);
			if(cateCd == "") {				
				pstmt.setString(1, "%");
			}else {
				pstmt.setString(1, cateCd+"%");
			}
			pstmt.setInt(2, i);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				code = rs.getString("MAX(cateCd)");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return code;
	}	
	
	
	//카테고리 생성
	public int categoryAdd(categoryDTO dto) {
		int add = 0;
		String sql = "insert into category (cateNm,cateCd,cateDisplayFl,commission,keyword,"
				+ "cateImg,cateOverImg,cateOnlyAdultFl,cateOnlyAdultDisplayFl,recomGoodsNo,regDt)"
				+ "values(?,?,?,?,?,?,?,?,?,?,now())";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getCateNm());
			pstmt.setString(2, dto.getCateCd());
			pstmt.setBoolean(3, dto.getCateDisplayFl());
			pstmt.setBigDecimal(4, dto.getCommission());
			pstmt.setString(5, dto.getKeyword());
			pstmt.setString(6, dto.getCateImg());
			pstmt.setString(7, dto.getCateOverImg());
			pstmt.setBoolean(8, dto.getCateOnlyAdultFl());
			pstmt.setBoolean(9, dto.getCateOnlyAdultDisplayFl());
			pstmt.setString(10, dto.getRecomGoodsNo());	
			add = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return add;
	}
	
	
	//카테고리 수정
	public int categoryMod(categoryDTO dto) {
		int add = 0;
		String sql = "update category set cateNm = ?, cateCd = ?, cateDisplayFl = ?, commission = ?, keyword = ?,"
				+ "cateImg = ?, cateOverImg = ?, cateOnlyAdultFl = ? ,cateOnlyAdultDisplayFl = ?, recomGoodsNo = ?,"
				+ "modDt = now() where sno = ?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getCateNm());
			pstmt.setString(2, dto.getCateCd());
			pstmt.setBoolean(3, dto.getCateDisplayFl());
			pstmt.setBigDecimal(4, dto.getCommission());
			pstmt.setString(5, dto.getKeyword());
			pstmt.setString(6, dto.getCateImg());
			pstmt.setString(7, dto.getCateOverImg());
			pstmt.setBoolean(8, dto.getCateOnlyAdultFl());
			pstmt.setBoolean(9, dto.getCateOnlyAdultDisplayFl());
			pstmt.setString(10, dto.getRecomGoodsNo());
			pstmt.setInt(11, dto.getSno());	
			add = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return add;
	}
	
	//카테고리 삭제
	public int categoryDel(int sno) {
		int del = 0;
		String sql = "delete from category where sno = ?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sno);
			del = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return del;
	}
	
	
}
