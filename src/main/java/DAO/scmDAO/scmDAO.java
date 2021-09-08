package DAO.scmDAO;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.scmDTO.scmDTO;


public class scmDAO {
	private static scmDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public static scmDAO getInstance() {
		if (dao == null) {
			dao = new scmDAO();
		}
		return dao;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	//	scm 리스트 받아오기
	public ArrayList<scmDTO> getScmList(String applyFl){
		
		ArrayList<scmDTO> list = new ArrayList<>();
		
		String sql = "select * from scm where applyFl=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, applyFl);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				scmDTO dto = new scmDTO();
				
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setCompanyNm(rs.getString("companyNm"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setScmState(rs.getString("scmState"));
				dto.setScmCommission(rs.getBigDecimal("scmCommission"));
				dto.setScmCommissionDelivery(rs.getBigDecimal("scmCommissionDelivery"));
				dto.setImageStorage(rs.getString("imageStorage"));
				dto.setCertificationFl(rs.getBoolean("certificationFl"));
				dto.setApplyFl(rs.getString("applyFl"));
				dto.setCeoNm (rs.getString("ceoNm"));
				dto.setBusinessNo(rs.getString("businessNo"));
				dto.setBusinessLicenseImg(rs.getString("businessLicenseImage"));
				dto.setOnlineOrderSerial(rs.getString("onlineOrderSerial"));
				dto.setService(rs.getString("service"));
				dto.setItem(rs.getString("item"));
				dto.setEmail(rs.getString("email"));
				dto.setZonecode(rs.getString("zonecode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddressSub(rs.getString("addressSub"));
				dto.setPhone(rs.getString("phone"));
				dto.setCenterphone(rs.getString("centerPhone"));
				dto.setFax(rs.getString("fax"));
				dto.setAccount(rs.getString("account"));
				dto.setDelFl(rs.getBoolean("delFl"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDeleteDt(rs.getTimestamp("deleteDt"));
				
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public ArrayList<scmDTO> getScmList(String kind, String keyword, String scmState, String date01, String date02, int startRow, int endRow, String mode){
		
		ArrayList<scmDTO> list = new ArrayList<>();
		
		String sql = scmListSql(kind,keyword,scmState,date01,date02,startRow,endRow,mode);
		
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				scmDTO dto = new scmDTO();
				
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setCompanyNm(rs.getString("companyNm"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setScmState(rs.getString("scmState"));
				dto.setScmCommission(rs.getBigDecimal("scmCommission"));
				dto.setScmCommissionDelivery(rs.getBigDecimal("scmCommissionDelivery"));
				dto.setImageStorage(rs.getString("imageStorage"));
				dto.setCertificationFl(rs.getBoolean("certificationFl"));
				dto.setApplyFl(rs.getString("applyFl"));
				dto.setCeoNm (rs.getString("ceoNm"));
				dto.setBusinessNo(rs.getString("businessNo"));
				dto.setBusinessLicenseImg(rs.getString("businessLicenseImage"));
				dto.setOnlineOrderSerial(rs.getString("onlineOrderSerial"));
				dto.setService(rs.getString("service"));
				dto.setItem(rs.getString("item"));
				dto.setEmail(rs.getString("email"));
				dto.setZonecode(rs.getString("zonecode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddressSub(rs.getString("addressSub"));
				dto.setPhone(rs.getString("phone"));
				dto.setCenterphone(rs.getString("centerPhone"));
				dto.setFax(rs.getString("fax"));
				dto.setAccount(rs.getString("account"));
				dto.setDelFl(rs.getBoolean("delFl"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDeleteDt(rs.getTimestamp("deleteDt"));
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	//카운트
	public int getScmListCount(String kind, String keyword, String scmState, String date01, String date02, int startRow,
			int endRow, String mode) {
		int cnt = 0;
		String sql = scmListSql(kind,keyword,scmState,date01,date02,startRow,endRow,mode);
		try{
			pstmt = con.prepareStatement(sql);		
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				cnt = rs.getInt("count(*)");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return cnt;
	}
	
	
	//scmList 검색조건
	private String scmListSql(String kind, String keyword, String scmState, String date01, String date02, int startRow,
			int endRow, String mode) {
		String sql = (mode == "리스트") ? "select * from scm where scmNo > 0" : "select count(*) from scm where scmNo > 0";
		String con = "";
		
		if(!keyword.equals("")){
			if(kind.equals("공급사명")) {		
				con += " and companyNm like '%"+keyword+"%'";
			}else if(kind.equals("공급사코드")) {
				con += " and memNo like '%"+keyword+"%'";
			}else if(kind.equals("사업자등록번호")) {
				con += " and businessNo like '%"+keyword+"%'";
			}else if(kind.equals("대표자")) {
				con += " and ceoNm like '%"+keyword+"%'";
			}else if(kind.equals("전체")){
				con += " and (companyNm like '%"+keyword+"%' or memNo like '%"+keyword+"%' or businessNo like '%"+keyword+"%' or ceoNm like '%"+keyword+"%')";
			}
		}
		
		if(!scmState.equals("")) {
			if(scmState.equals("전체")) {
				con += "";
			}else if(scmState.equals("운영")) {
				con += " and scmState = 'y'";
			}else if(scmState.equals("탈퇴")) {
				con += " and scmState = 'x'";
			}
		}
		
		if(!date01.equals("")) {
			con += " and regDt >= '"+date01+"'";
		}
		
		if(!date02.equals("")&& !date02.equals(" 00:00:00")) {
			con += " and regDt <= '"+date02+"'";
		}
		
				
		if(mode == "리스트") {
			sql += con + " limit "+startRow+" , "+endRow+"";
		}else if(mode == "카운트"){
			sql += con;			
		}
			
		System.out.println("sql = "+sql);
			
		return sql;
	}

	//	scm 리스트 받아오기
	public scmDTO getScm(int scmNo){
		
		scmDTO dto = null;
		String sql = "select * from scm where scmNo = ?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scmNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new scmDTO();
				
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setCompanyNm(rs.getString("companyNm"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setScmState(rs.getString("scmState"));
				dto.setScmCommission(rs.getBigDecimal("scmCommission"));
				dto.setScmCommissionDelivery(rs.getBigDecimal("scmCommissionDelivery"));
				dto.setImageStorage(rs.getString("imageStorage"));
				dto.setCertificationFl(rs.getBoolean("certificationFl"));
				dto.setApplyFl(rs.getString("applyFl"));
				dto.setCeoNm (rs.getString("ceoNm"));
				dto.setBusinessNo(rs.getString("businessNo"));
				dto.setBusinessLicenseImg(rs.getString("businessLicenseImage"));
				dto.setOnlineOrderSerial(rs.getString("onlineOrderSerial"));
				dto.setService(rs.getString("service"));
				dto.setItem(rs.getString("item"));
				dto.setEmail(rs.getString("email"));
				dto.setZonecode(rs.getString("zonecode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddressSub(rs.getString("addressSub"));
				dto.setPhone(rs.getString("phone"));
				dto.setCenterphone(rs.getString("centerPhone"));
				dto.setFax(rs.getString("fax"));
				dto.setAccount(rs.getString("account"));
				dto.setDelFl(rs.getBoolean("delFl"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDeleteDt(rs.getTimestamp("deleteDt"));
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("파트너스 업체명 : " + dto.getCompanyNm());
		
		return dto;
		
	}
	
	public int approveScm(int scmNo){
		int chk = 0;
		
		String sql = "update scm set certifiAppFl='y' where scmNo=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scmNo);
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	
}
