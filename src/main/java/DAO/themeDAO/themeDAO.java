package DAO.themeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import dto.themeDTO.themeDTO;

public class themeDAO {

	static themeDAO dao;
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
	
	public static themeDAO getInstance(){
		if(dao==null) dao = new themeDAO();
		
		return dao;
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
//	=========================================
	
	public themeDTO getTheme(int sno){
		
		themeDTO dto = null;
		
		String sql = "select * from displaytheme where sno=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto = new themeDTO();
				
				dto.setSno(rs.getInt("sno"));
				dto.setAdminNo(rs.getInt("adminNo"));
				dto.setThemeNm(rs.getString("themeNm"));
				dto.setThemeDescription(rs.getString("themeDescription"));
				dto.setRelationCd(rs.getString("relationCd"));
				dto.setRepresentImg(rs.getString("representImg"));
				dto.setSeoTagFl(rs.getString("seoTagFl"));
				dto.setSeoTagSno(rs.getInt("seoTagSno"));
				dto.setSort(rs.getInt("sort"));
				dto.setGoodsNos(rs.getString("goodsNos"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public themeDTO getTheme(String sno){
		
		themeDTO dto = null;
		
		String sql = "select * from displaytheme where sno=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto = new themeDTO();
				
				dto.setSno(rs.getInt("sno"));
				dto.setAdminNo(rs.getInt("adminNo"));
				dto.setThemeNm(rs.getString("themeNm"));
				dto.setThemeDescription(rs.getString("themeDescription"));
				dto.setRelationCd(rs.getString("relationCd"));
				dto.setRepresentImg(rs.getString("representImg"));
				dto.setSeoTagFl(rs.getString("seoTagFl"));
				dto.setSeoTagSno(rs.getInt("seoTagSno"));
				dto.setSort(rs.getInt("sort"));
				dto.setGoodsNos(rs.getString("goodsNos"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public themeDTO getToCode(String relationCd){
		
		themeDTO dto = null;
		
		String sql = "select * from displaytheme where relationCd=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, relationCd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto = new themeDTO();
				
				dto.setSno(rs.getInt("sno"));
				dto.setAdminNo(rs.getInt("adminNo"));
				dto.setThemeNm(rs.getString("themeNm"));
				dto.setThemeDescription(rs.getString("themeDescription"));
				dto.setRelationCd(rs.getString("relationCd"));
				dto.setRepresentImg(rs.getString("representImg"));
				dto.setSeoTagFl(rs.getString("seoTagFl"));
				dto.setSeoTagSno(rs.getInt("seoTagSno"));
				dto.setSort(rs.getInt("sort"));
				dto.setGoodsNos(rs.getString("goodsNos"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	}
	
	
	//테마리스트
	public List<themeDTO> getThemeList() {
		List<themeDTO> th = new ArrayList<themeDTO>();
		themeDTO dto = null;
		
		String sql = "select * from displaytheme";
		
		try{
			pstmt = con.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				dto = new themeDTO();
				dto.setSno(rs.getInt("sno"));
				dto.setAdminNo(rs.getInt("adminNo"));
				dto.setThemeNm(rs.getString("themeNm"));
				dto.setThemeDescription(rs.getString("themeDescription"));
				dto.setRelationCd(rs.getString("relationCd"));
				dto.setRepresentImg(rs.getString("representImg"));
				dto.setSeoTagFl(rs.getString("seoTagFl"));
				dto.setSeoTagSno(rs.getInt("seoTagSno"));
				dto.setSort(rs.getInt("sort"));
				dto.setGoodsNos(rs.getString("goodsNos"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				th.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return th;
	}
	
	
	//테마생성
	public int themeInsert(themeDTO dto) {
		int add = 0;
		String sql = "insert into displaytheme (adminNo , themeNm , themeDescription,"
				+ "relationCd , representImg , goodsNos , regDt) values(1,?,?,?,?,?,now())";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getThemeNm());
			pstmt.setString(2, dto.getThemeDescription());
			pstmt.setString(3, dto.getRelationCd());
			pstmt.setString(4, dto.getRepresentImg());
			pstmt.setString(5, dto.getGoodsNos());			
			add = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return add;
	}
	
	//테마수정
	public int themeMod(themeDTO dto) {
		int add = 0;
		String sql = "update displaytheme set themeNm = ? , themeDescription = ?,"
				+ "relationCd = ?, representImg = ?, goodsNos =?, modDt = now() where sno = ?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getThemeNm());
			pstmt.setString(2, dto.getThemeDescription());
			pstmt.setString(3, dto.getRelationCd());
			pstmt.setString(4, dto.getRepresentImg());
			pstmt.setString(5, dto.getGoodsNos());
			pstmt.setInt(6, dto.getSno());
			add = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return add;
	}
	
	//테마삭제
	public int themeDel(int sno) {
		int del = 0;
		String sql = "delete from displaytheme where sno = ?";
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
