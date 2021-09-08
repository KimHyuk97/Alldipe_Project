package DAO.partnerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.scmDTO.scmAddressDTO;
import dto.scmDTO.scmDTO;

public class partnerDAO {
	private static partnerDAO dao;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public static partnerDAO getInstance(){
		if(dao==null){
			dao = new partnerDAO();
		}
		return dao;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	//	파트너스 회원 전환
	
	public int partnersUpdate(String id){
		int chk = 0;
		
		String sql = "update member set partnersFl=1 where memId=?";
		
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(0, id);
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return chk;		
	}
	
	//	파트너스 회원 전환
	
	public int partnersDelete(int memNo){
		
		int chk = 0;
		
		String sql = "update member set partnersFl=0 where memNo =?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(0, memNo);
			chk = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	// 파트너스 회원 삭제
	
	//	파트너스 여부 확인
	
	public boolean memberPartnersFl(int memNo){
		
		String sql = "select partnersFl from member where memNo=?";
		boolean chk = false;
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			
			System.out.println("partnerDAO pstmt : "+pstmt);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				chk = rs.getBoolean("partnersFl");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	
	
	//	파트너스 여부 확인
	
	//	입점사 등록
	public int addScm(int memNo){
		
		int chk = 0;
		
		String sql = "insert into scm(memNo) values(?)";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
		
	}
	//	입점사 등록

	public scmDTO getScm(int managerNo){
		
		scmDTO sdto = null;
		String sql = "select * from scm where memNo = ?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, managerNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				sdto = new scmDTO();
				
				sdto.setScmNo(rs.getInt("scmNo"));
				sdto.setCompanyNm(rs.getString("companyNm"));
				sdto.setMemNo(rs.getInt("memNo"));
				sdto.setScmState(rs.getString("scmState"));
				sdto.setScmCommission(rs.getBigDecimal("scmCommission"));
				sdto.setScmCommissionDelivery(rs.getBigDecimal("scmCommissionDelivery"));
				sdto.setImageStorage(rs.getString("imageStorage"));
				sdto.setCertificationFl(rs.getBoolean("certificationFl"));
				sdto.setCeoNm(rs.getString("ceoNm"));
				sdto.setBusinessNo(rs.getString("businessNo"));
				sdto.setBusinessLicenseImg(rs.getString("businessLicenseImage"));
				sdto.setOnlineOrderSerial(rs.getString("onlineOrderSerial"));
				sdto.setService(rs.getString("service"));
				sdto.setItem(rs.getString("item"));
				sdto.setEmail(rs.getString("email"));
				sdto.setZonecode(rs.getString("zonecode"));
				sdto.setAddress(rs.getString("address"));
				sdto.setAddressSub(rs.getString("addressSub"));
				sdto.setPhone(rs.getString("phone"));
				sdto.setCenterphone(rs.getString("centerPhone"));
				sdto.setFax(rs.getString("fax"));
				sdto.setAccount(rs.getString("account"));
				sdto.setDelFl(rs.getBoolean("delFl"));
				sdto.setRegDt(rs.getTimestamp("regDt"));
				sdto.setModDt(rs.getTimestamp("modDt"));
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return sdto;
		
	}
	
	//	입점사 주소록
	
	public int insertScmAddress(scmAddressDTO dto){
		
		int chk = 0;
		
		String sql = "insert into scmaddress (addrNm, scmNo, zonecode, address, addressSub,"
				+ "extraAddress) values (?,?,?,?,?,?)";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getAddrNm());
			pstmt.setInt(2, dto.getScmNo());
			pstmt.setString(3, dto.getZonecode());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getAddressSub());
			pstmt.setString(6, dto.getExtraAddress());
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
		
	}
	
	public ArrayList<scmAddressDTO> getScmAddress(int scmNo){
		
		String sql = "select * from scmaddress where scmNo = ?";
		
		ArrayList<scmAddressDTO> list = new ArrayList<>();
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, scmNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				scmAddressDTO dto = new scmAddressDTO();
				dto.setSno(rs.getInt("sno"));
				dto.setAddrNm(rs.getString("addrNm"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setZonecode(rs.getString("zonecode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddressSub(rs.getString("addressSub"));
				dto.setExtraAddress(rs.getString("extraAddress"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int deleteScmAddress(int sno){
		
		int chk = 0;
		
		String sql = "delete from scmaddress where sno=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sno);
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("dao Check : " + chk);
		return chk;
	}
	
	public scmDTO getScmInfo(int scmNo){
		
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
				dto.setCeoNm(rs.getString("ceoNm"));
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
		
		return dto;
		
	}
	
}
