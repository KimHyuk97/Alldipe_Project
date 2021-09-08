package DAO.brandDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.brandDTO.brandDTO;

public class brandDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private static brandDAO dao;
	
	public static brandDAO getInstance(){
		if(dao==null)dao = new brandDAO();
		return dao;
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	public int insertBrand(brandDTO dto){
		int chk = 0;
		
		String sql = "insert into brand (brandCd, brandNm) values (?,?)";
		
		String brandCd = getCode();
		int brandNum = Integer.parseInt(!brandCd.equals("")?brandCd.substring(1,brandCd.length()):"1"); 
		brandCd = "B" + String.format("%04d", brandNum+1);
		
		System.out.println("new BrandCd : " + brandCd);
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, brandCd);
			pstmt.setString(2, dto.getBrandNm());
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	public String getCode(String name){
		
		String value = "";
		String sql = "select brandCd from brand where brandNm=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				value=rs.getString("brandCd");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return value;
	}
	
	public String getCode(){
		
		String value = "";
		String sql = "select brandCd from brand order by brandCd desc limit 1";
		
		try{
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				value=rs.getString("brandCd");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return value;
	}
	
	public ArrayList<brandDTO> getList(String brandNm){
		
		ArrayList<brandDTO> list = new ArrayList<>();
		
		String sql = "select * from brand where brandNm like ?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + brandNm +"%");
			
			rs = pstmt.executeQuery();
			
			brandDTO dto = null;
			
			while(rs.next()){
				dto = new brandDTO();
				
				dto.setSno(rs.getInt("sno"));
				dto.setBrandCd(rs.getString("brandCd"));
				dto.setBrandNm(rs.getString("brandNm"));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setBrandImg(rs.getString("brandImg"));
				dto.setBrandImgMobile(rs.getString("brandImgMobile"));
				dto.setLikeCnt(rs.getInt("likeCnt"));
				dto.setRegIp(rs.getString("regIp"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public brandDTO getBrand(String brandNm){
		
		String sql = "select * from brand where brandNm=?";
		brandDTO dto = new brandDTO();
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, brandNm);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto = new brandDTO();
				
				dto.setSno(rs.getInt("sno"));
				dto.setBrandCd(rs.getString("brandCd"));
				dto.setBrandNm(rs.getString("brandNm"));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setBrandImg(rs.getString("brandImg"));
				dto.setBrandImgMobile(rs.getString("brandImgMobile"));
				dto.setLikeCnt(rs.getInt("likeCnt"));
				dto.setRegIp(rs.getString("regIp"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	}
	
public brandDTO getToCode(String brandCd){
		
		String sql = "select * from brand where brandCd=?";
		brandDTO dto = new brandDTO();
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, brandCd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto = new brandDTO();
				
				dto.setSno(rs.getInt("sno"));
				dto.setBrandCd(rs.getString("brandCd"));
				dto.setBrandNm(rs.getString("brandNm"));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setBrandImg(rs.getString("brandImg"));
				dto.setBrandImgMobile(rs.getString("brandImgMobile"));
				dto.setLikeCnt(rs.getInt("likeCnt"));
				dto.setRegIp(rs.getString("regIp"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public List<brandDTO> brandList() {
		List<brandDTO> bd = new ArrayList<brandDTO>();
		String sql = "select * from brand";
		try{
			pstmt = con.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				brandDTO b = new brandDTO();
				
				b.setSno(rs.getInt("sno"));
				b.setBrandCd(rs.getString("brandCd"));
				b.setBrandNm(rs.getString("brandNm"));
				b.setCateCd(rs.getString("cateCd"));
				b.setKeyword(rs.getString("keyword"));
				b.setBrandImg(rs.getString("brandImg"));
				b.setBrandImgMobile(rs.getString("brandImgMobile"));
				b.setLikeCnt(rs.getInt("likeCnt"));
				b.setRegIp(rs.getString("regIp"));
				b.setRegDt(rs.getTimestamp("regDt"));
				b.setModDt(rs.getTimestamp("modDt"));
				bd.add(b);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return bd;
	}
	
	//브랜드 추가(admin)
	public int adminBrandInsert(brandDTO dto) {
		int add = 0;
		String sql = "insert into brand (brandCd, brandNm, keyword, brandImg, regDt) values (?,?,?,?,now())";
		
		String brandCd = getCode();
		int brandNum = Integer.parseInt(!brandCd.equals("")?brandCd.substring(1,brandCd.length()):"1"); 
		brandCd = "B" + String.format("%04d", brandNum+1);
		
		System.out.println("new BrandCd : " + brandCd);
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, brandCd);
			pstmt.setString(2, dto.getBrandNm());
			pstmt.setString(3, dto.getKeyword());
			pstmt.setString(4, dto.getBrandImg());
			
			add = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return add;
	}
	
	//브랜드 수정
	public int brandMod(brandDTO dto) {
		int add = 0;
		String sql = "update brand set brandNm = ?, keyword = ?, brandImg = ?, modDt = now() where sno = ?";
				
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBrandNm());
			pstmt.setString(2, dto.getKeyword());
			pstmt.setString(3, dto.getBrandImg());
			pstmt.setInt(4, dto.getSno());
			
			add = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return add;
	}
	
	//브랜드 삭제
	public int brandDel(int sno) {
		int del = 0;
		String sql = "delete from brand where sno = ?";
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
