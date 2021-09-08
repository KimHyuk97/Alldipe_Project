package DAO.searchDAO;

import static db.JdbcUtil.close;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.brandDTO.brandDTO;
import dto.categoryDTO.categoryDTO;
import dto.goodsDTO.goodsDTO;
import dto.searchDTO.searchDTO;

public class searchDAO {
	private static searchDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public static searchDAO getInstance() {
		if (dao == null) {
			dao = new searchDAO();
		}
		return dao;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//최근검색어 불러오기
	public List<searchDTO> latestKeyword(String siteKey, int memNo) {
		List<searchDTO> sd = new ArrayList<searchDTO>(); 
		searchDTO sd2 = null;
		
		String sql = "select keyword from search where siteKey = ? and memNo = ? and regDt >= date_add(now(), interval -7 day) order by regDt DESC LIMIT 0, 5";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, siteKey);
			pstmt.setInt(2, memNo);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				sd2 = new searchDTO();
				sd2.setKeyword(rs.getString(1));
				sd.add(sd2);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}	
		return sd;
	}

	//인기검색어
	public List<searchDTO> searchList() {
		List<searchDTO> bd = new ArrayList<searchDTO>(); 
		
		String sql = "select keyword from search where regDt >= date_add(now(), interval -7 day)  order by keywordCnt DESC LIMIT 0, 10";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				searchDTO bd2 = new searchDTO();
				bd2.setKeyword(rs.getString(1));
				bd.add(bd2);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return bd;
	}
	
	//검색기록 조회
	public searchDTO searchSelect(String keyword) {
		searchDTO resultKeyword = new searchDTO();
		String sql = "select * from search where keyword = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				resultKeyword.setSno(rs.getInt("sno"));
				resultKeyword.setKeyword(rs.getString("keyword"));
				resultKeyword.setKeywordCnt(rs.getInt("keywordCnt"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	

		return resultKeyword;
	}
	
	//검색기록 업데이트
	public int searchCntUp(String keyword, int keywordCnt, String time1) {
		int su = 0;
		String sql = "update search set keywordCnt = ? + 1 , regDt = ? where keyword = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, keywordCnt);
			pstmt.setString(2, time1);
			pstmt.setString(3, keyword);
			su = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		
		
		return su;
	}
	
	//검색기록 등록
	public int searchAdd(String siteKey, int memNo, String keyword, String time1) {
		int sa = 0;
		String sql = "insert into search (memNo,siteKey,keyword,keywordCnt,regDt) values(?,?,?,1,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			pstmt.setString(2, siteKey);
			pstmt.setString(3, keyword);
			pstmt.setString(4, time1);
			sa=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		return sa;
	}
	
	//검색결과리스트
	public List<goodsDTO> searchResultList(String keyword, int startRow, int endRow, String cateCd, String brandCd, int reviewCnt, BigDecimal price, BigDecimal price2) {
		List<goodsDTO> sg = new ArrayList<goodsDTO>();
		BigDecimal bol = BigDecimal.valueOf(0);
		goodsDTO goodsList = null;
		if(cateCd != "") {
			
			String sql = "SELECT * FROM goods WHERE (keyword LIKE ? OR goodsNm LIKE ?) and cateCd = ? order by regDt DESC LIMIT ?, ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setString(3, cateCd);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
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
					sg.add(goodsList);
				}
					
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
		}else if(brandCd != ""){
			
			String sql = "SELECT * FROM goods WHERE (keyword LIKE ? OR goodsNm LIKE ?) and brandCd = ? order by regDt DESC LIMIT ?, ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setString(3, brandCd);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
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
					sg.add(goodsList);
				}
					
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
		}else if(reviewCnt > 0) {
			
			String sql = "SELECT * FROM goods WHERE (keyword LIKE ? OR goodsNm LIKE ?) and reviewCnt = ? order by regDt DESC LIMIT ?, ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, reviewCnt);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
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
					sg.add(goodsList);
				}
					
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
		}else if(price.compareTo(bol) > 0 && price2.compareTo(bol) > 0) {
			
			String sql = "SELECT * FROM goods WHERE (keyword LIKE ? OR goodsNm LIKE ?) and fixedPrice between ? and ? order by regDt DESC LIMIT ?, ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setBigDecimal(3, price);
				pstmt.setBigDecimal(4, price2);
				pstmt.setInt(5, startRow);
				pstmt.setInt(6, endRow);
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
					sg.add(goodsList);
				}
					
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
		}else {
			
			String sql = "SELECT * FROM goods WHERE keyword LIKE ? OR goodsNm LIKE ? order by regDt DESC LIMIT ?, ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
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
					sg.add(goodsList);
				}
					
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
		}
		

		return sg;
	}
	
	//검색한 결과 카운트값
	public int goodsListCount(String keyword, String cateCd, String brandCd, int reviewCnt, BigDecimal price, BigDecimal price2) {
		int listCount = 0;
		BigDecimal bol = BigDecimal.valueOf(0);
		if(cateCd != "") {	
			String sql = "select COUNT(*) from goods where (keyword = ? or goodsNm LIKE ?) and cateCd = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setString(3, cateCd);
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
		}else if(brandCd != ""){
			String sql = "select COUNT(*) from goods where (keyword = ? or goodsNm LIKE ?) and brandCd = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setString(3, brandCd);
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
		}else if(reviewCnt > 0){
			String sql = "select COUNT(*) from goods where (keyword = ? or goodsNm LIKE ?) and reviewCnt = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, reviewCnt);
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
		}else if(price.compareTo(bol) > 0 && price2.compareTo(bol) > 0){
			String sql = "select COUNT(*) from goods where (keyword = ? or goodsNm LIKE ?) and fixedPrice between ? and ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setBigDecimal(3, price);
				pstmt.setBigDecimal(4, price2);
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
		}else {
			String sql = "select COUNT(*) from goods where keyword = ? or goodsNm LIKE ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setString(2, "%"+keyword+"%");
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
			
		}
		return listCount;
	}
	
	
	//브랜드코드 찾기
	public brandDTO searchBrandList(String brandCd) {
		brandDTO dd = null;
		
		String sql = "select brandNm,brandCd from brand where brandCd = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, brandCd);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dd = new brandDTO();
				dd.setBrandNm(rs.getString("brandNm"));
				dd.setBrandCd(rs.getString("brandCd"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dd;
	}

	//카테고리코드 찾기
	public categoryDTO searchCateList(String cateCd) {
		categoryDTO cc = null;
		
		String sql = "select cateNm,cateCd from category where cateCd = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cateCd);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				cc = new categoryDTO();
				cc.setCateNm(rs.getString("cateNm"));
				cc.setCateCd(rs.getString("cateCd"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(cc);
		return cc;
	}
		
	
	
}
