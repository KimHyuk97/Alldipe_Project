package DAO.adminDAO;

import static db.JdbcUtil.close;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.adminDTO.adminDTO;
import dto.boardDTO.boardDTO;
import dto.boardDTO.qaDTO;
import dto.boardDTO.reviewDTO;
import dto.scmDTO.scmDTO;

public class adminDAO {

	private static adminDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static adminDAO getInstance(){
		if(dao == null){
			dao = new adminDAO();
		}
		return dao;
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	//	아이디 중복 검사
	
	public int adminIdChk(String id){
		
		int chk = 1;
		
		String sql = "select sno from admin where id=?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				chk=0;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return chk;
	}
	
	//	아이디 중복 검사
	//	로그인 확인
	public adminDTO adminLogin(String id, String pw){
		
		adminDTO dto = null;
		
		String sql = "select * from admin where id=? and pw=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dto = new adminDTO();
				dto.setSno(rs.getInt("sno"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setRank(rs.getString("rank"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setAddAdminSno(rs.getInt("addAdminSno"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
		
	}
	
	//	로그인 확인
	
	
	//문의글 리스트
	public List<boardDTO> boardList() {
		List<boardDTO> b = new ArrayList<boardDTO>();
		String sql = "select * from board";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				boardDTO qq = new boardDTO();
				qq.setNo(rs.getInt("sno"));
				qq.setMemNo(rs.getInt("memNo"));
				qq.setWriterNm(rs.getString("writerNm"));
				qq.setWriterId(rs.getString("writerId"));
				qq.setWriterIp(rs.getString("writerIp"));
				qq.setPartnersFl(rs.getBoolean("partnersFl"));
				qq.setBoardType(rs.getString("boardType"));
				qq.setTheme(rs.getString("theme"));
				qq.setCate(rs.getString("cate"));
				qq.setSort(rs.getInt("sort"));
				qq.setTitle(rs.getString("title"));
				qq.setContents(rs.getString("contents"));
				qq.setSecret(rs.getBoolean("isSecret"));
				qq.setPassword(rs.getString("pw"));
				qq.setViewCnt(rs.getInt("viewCnt"));
				qq.setGoodsNo(rs.getInt("goodsNo"));
				qq.setScmNo(rs.getInt("scmNo"));
				qq.setOrderNo(rs.getString("orderNo"));
				qq.setReplyStatus(rs.getString("replyStatus"));
				qq.setDelete(rs.getBoolean("isDelete"));
				qq.setRegDt(rs.getTimestamp("regDt"));
				qq.setModDt(rs.getTimestamp("modDt"));
				qq.setDeleteDt(rs.getTimestamp("deleteDt"));
				qq.setAdminNo(rs.getInt("adminNo"));
				qq.setAnswerDt(rs.getTimestamp("answerDt"));
				qq.setAnswerContent(rs.getString("answerContent"));
				
				b.add(qq);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return b;
	}
	
	
	//매출현황
	public BigDecimal sumPrice(String tomorrow, String today, String status) {
		BigDecimal settlePrice = new BigDecimal(0);
		String sql ="select sum(settlePrice) from orderlist where orderStatus = ? and regDt >= ? and regDt < ?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setString(2, today);
			pstmt.setString(3, tomorrow);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(rs.getBigDecimal(1) != null) {
					settlePrice = rs.getBigDecimal(1);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return settlePrice;
	}
	
	
	//리뷰불러오기
	public reviewDTO reviewView(int sno) {
		reviewDTO rr = new reviewDTO();
		String sql = "select * from review where sno = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rr.setSno(rs.getInt("sno"));
				rr.setOrderNo(rs.getInt("orderNo"));
				rr.setGoodsNo(rs.getInt("goodsNo"));
				rr.setTitle(rs.getString("title"));
				rr.setMemNo(rs.getInt("memNo"));
				rr.setWriter(rs.getString("writer"));
				rr.setIp(rs.getString("ip"));
				rr.setPrivateFl(rs.getBoolean("privateFl"));
				rr.setContents(rs.getString("contents"));
				rr.setGoodsPt(rs.getInt("goodsPt"));
				rr.setReviewImg(rs.getString("reviewImg"));
				rr.setAddMileageFl(rs.getBoolean("addMileageFl"));
				rr.setRegDt(rs.getTimestamp("regDt"));
				rr.setViewCnt(rs.getInt("viewCnt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		
		return rr;
	}
	
}
