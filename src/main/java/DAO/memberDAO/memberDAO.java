package DAO.memberDAO;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import org.apache.tomcat.util.codec.binary.StringUtils;

import dto.adminDTO.adminDTO;
import dto.goodsDTO.goodsDTO;
import dto.memberDTO.memberDTO;
import dto.memberDTO.memberdepositDTO;
import dto.memberDTO.membergradeDTO;
import dto.memberDTO.memberhackoutDTO;
import dto.memberDTO.memberhistoryDTO;
import dto.memberDTO.membermileageDTO;

import static db.JdbcUtil.*;

public class memberDAO {

	private static memberDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public static memberDAO getInstance() {
		if (dao == null) {
			dao = new memberDAO();
		}
		return dao;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	//아이디중복검사
	public String memberIdCheck(String userId) {
		String idCheck = null;
		
		String sql = "SELECT * FROM MEMBER WHERE MEMID = ?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()) {
				idCheck = rs.getString(1);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
	
		return idCheck;
	}
	
	//이메일중복검사
	public String memberEmailCheck(String userEmail) {
		String emailCheck = null;
		
		String sql = "SELECT * FROM MEMBER WHERE EMAIL = ?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userEmail);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()) {
				emailCheck = rs.getString(1);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		
		return emailCheck;
	}
	
	//회원가입
	public int memberJoin(String memId, String memPw, String memName, String gender, String email, String cellphone,
			String zonecode, String address, String addressSub, String birthDt, String recommId, int privateUtilizationFl, int privateApprovalFl, int privateFinanceFl, int privateOfferFl, int under14ConsentFl, String time1) {
		
		String sql = "INSERT INTO MEMBER"
				+ "(memId,gradeSno,gradeModDt,gradeValidDt,memName,memPw,sexFl,birthDt,email,zonecode,address,addressSub,cellphone,recommId,privateUtilizationFl,privateApprovalFl,privateFinanceFl,privateOfferFl,under14ConsentFl,regDt)"
				+ " VALUES(?,1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
				int join = 0;

				try {
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, memId);
					pstmt.setString(2, time1);
					pstmt.setString(3, time1);
					pstmt.setString(4, memName);
					pstmt.setString(5, memPw);
					pstmt.setString(6, gender);
					pstmt.setString(7, birthDt);
					pstmt.setString(8, email);
					pstmt.setString(9, zonecode);
					pstmt.setString(10, address);
					pstmt.setString(11, addressSub);
					pstmt.setString(12, cellphone);
					pstmt.setString(13, recommId);
					pstmt.setInt(14, privateUtilizationFl);
					pstmt.setInt(15, privateApprovalFl);
					pstmt.setInt(16, privateFinanceFl);
					pstmt.setInt(17, privateOfferFl);
					pstmt.setInt(18, under14ConsentFl);
					pstmt.setString(19, time1);
					
					join = pstmt.executeUpdate();						
				}catch(SQLException se){
					se.printStackTrace();
				}	
					
				return join;
	}
	
	//탈퇴회원구분
	public String memberFl(String userId) {
		String fl = "";
		String sql = "select deleteFl from member where memId=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();

			if(rs.next()) {
				fl = rs.getString(1);
			}			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		return fl;
	}
	
	//로그인
	public memberDTO memberLogin(String userId, String userPw) {
		memberDTO mem = new memberDTO();
		String sql = "select * from member where memid = ? and memPw = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				mem.setMemNo(rs.getInt("memNo"));
				mem.setMemId(rs.getString("memId"));
				mem.setGradeSno(rs.getInt("gradeSno"));
				mem.setMemNm(rs.getString("memName"));
				mem.setMemPw(rs.getString("memPw"));
				mem.setEmail(rs.getString("email"));
				mem.setAdultFl(rs.getBoolean("adultFl"));
				mem.setZonecode(rs.getString("zonecode"));
				mem.setAddress(rs.getString("address"));
				mem.setAddressSub(rs.getString("addressSub"));
				mem.setCellPhone(rs.getString("cellphone"));
				mem.setBirthDt(rs.getDate("birthDt"));
				mem.setMileage(rs.getInt("mileage"));
			}
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return mem;
	}
	
	public memberDTO getMember(int memNo) {
		memberDTO dto = new memberDTO();
		String sql = "select * from member where memNo = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setMemNo(rs.getInt("memNo"));
				dto.setMemId(rs.getString("memId"));
				dto.setGradeSno(rs.getInt("gradeSno"));
				dto.setGradeModDt(rs.getTimestamp("gradeModDt"));
				dto.setGradeValidDt(rs.getTimestamp("gradeValidDt"));
				dto.setMemNm(rs.getString("memName"));
				dto.setMemPw(rs.getString("memPw"));
				dto.setChangPwDt(rs.getTimestamp("changePwDt"));
				dto.setGuidePwDt(rs.getTimestamp("guidePwDt"));
				dto.setAppFl(rs.getBoolean("appFl"));
				dto.setApprovalDt(rs.getTimestamp("approvalDt"));
				dto.setMemFl(rs.getString("memFl"));
				dto.setEntryBenefitOfferDt(rs.getTimestamp("entryBenefitOfferDt"));
				dto.setSexFl(rs.getString("sexFl"));
				dto.setBirthDt(rs.getDate("birthDt"));
				dto.setBirthEventFl(rs.getBoolean("birthEventFl"));
				dto.setEmail(rs.getString("email"));
				dto.setZonecode(rs.getString("zonecode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddressSub(rs.getString("addressSub"));
				dto.setPhoneCountryCode(rs.getString("phoneCountryCode"));
				dto.setPhone(rs.getString("phone"));
				dto.setCellPhoneCountryCode(rs.getNString("cellPhoneCountryCode"));
				dto.setCellPhone(rs.getString("cellPhone"));
				dto.setFax(rs.getString("fax"));
				dto.setPartnersFl(rs.getBoolean("partnersFl"));
				dto.setCompany(rs.getString("company"));
				dto.setApprovalFl1(rs.getBoolean("approvalFl1"));
				dto.setApprovalFl2(rs.getBoolean("approvalFl2"));
				dto.setApprovalFl3(rs.getBoolean("approvalFl3"));
				dto.setService(rs.getString("service"));
				dto.setItem(rs.getString("item"));
				dto.setBusiNo(rs.getString("busiNo"));
				dto.setCeo(rs.getString("ceo"));
				dto.setComZonecode(rs.getString("comZonecode"));
				dto.setComAddress(rs.getString("comAddress"));
				dto.setComAddressSub(rs.getString("comAddressSub"));
				dto.setMileage(rs.getFloat("mileage"));
				dto.setDeposit(rs.getFloat("deposit"));
				dto.setFreedeposit(rs.getFloat("freedeposit"));
				dto.setMaillingFl(rs.getBoolean("mailingFl"));
				dto.setSmsFl(rs.getBoolean("smsFl"));
				dto.setMarriFl(rs.getBoolean("marriFl"));
				dto.setMarriDt(rs.getDate("marriDt"));
				dto.setJob(rs.getString("job"));
				dto.setInterest(rs.getString("interest"));
				dto.setReEntryFl(rs.getBoolean("reEntryFl"));
				dto.setEntryPath(rs.getString("entryPath"));
				dto.setLoginLimit(rs.getBoolean("loginLimit"));
				dto.setLastLoginDt(rs.getTimestamp("lastLoginDt"));
				dto.setLastLoginIp(rs.getString("lastLoginIp"));
				dto.setLastSaleDt(rs.getTimestamp("lastSaleDt"));
				dto.setLoginCnt(rs.getInt("loginCnt"));
				dto.setSaleCnt(rs.getInt("saleCnt"));
				dto.setSaleAmt(rs.getInt("saleAmt"));
				dto.setMemo(rs.getString("memo"));
				dto.setRecommId(rs.getString("recommId"));
				dto.setRecommFl(rs.getBoolean("recommFl"));
				dto.setPrivateUtilizationFl(rs.getBoolean("privateUtilizationFl"));
				dto.setPrivateFinanceFl(rs.getBoolean("privateFinanceFl"));
				dto.setPrivateApprovalFl(rs.getBoolean("privateApprovalFl"));
				dto.setPrivateApprovalOptionFl(rs.getBoolean("privateApprovalOptionFl"));
				dto.setPrivateOfferFl(rs.getBoolean("privateOfferFl"));
				dto.setPrivateConsignFl(rs.getBoolean("privateConsignFl"));
				dto.setUnder14ConsentFl(rs.getBoolean("under14ConsentFl"));
				dto.setSleepFl(rs.getBoolean("sleepFl"));
				dto.setSleepWakeDt(rs.getTimestamp("sleepWakeDt"));
				dto.setSleepMailFl(rs.getBoolean("sleepMailFl"));
				dto.setSleepSmsFl(rs.getBoolean("sleepSmsFl"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDeleteFl(rs.getString("deleteFl"));
			}
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return dto;
	}
	
	
	//이메일로 아이디 찾기
	public String memberIdFind(String userEmailVal) {
		String idFind = null;
		
		String sql = "SELECT memId FROM MEMBER WHERE EMAIL = ?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userEmailVal);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()) {
				idFind = rs.getString(1);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		
		return idFind;
	}
	
	
	//핸드폰으로 아이디 찾기
	public String memberIdFind2(String userPhoneVal) {
		String idFind = null;
		
		String sql = "SELECT memId FROM MEMBER WHERE cellphone = ?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userPhoneVal);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				idFind = rs.getString(1);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		
		return idFind;
	}
	
	//이메일로 비밀번호찾기
	public String memberPwFind(String userEmailVal) {
		String pwFind = null;
		
		String sql = "SELECT memPw FROM MEMBER WHERE EMAIL = ?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userEmailVal);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()) {
				pwFind = rs.getString(1);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		
		return pwFind;
	}
	
	//핸드폰으로 비밀번호찾기
	public String memberPwFind2(String userPhoneVal) {
		String pwFind = null;
		
		String sql = "SELECT memPw FROM MEMBER WHERE cellphone = ?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userPhoneVal);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				pwFind = rs.getString(1);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		
		return pwFind;
	}
	
	//이메일로 비밀번호 변경
	public int memberPwChanage(String userPw, String way) {
		int pwChanage = 0;
		String sql = "UPDATE member SET memPw=? WHERE EMAIL = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userPw);
			pstmt.setString(2, way);
			pwChanage = pstmt.executeUpdate();			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		
		return pwChanage;
	}
	
	//핸드폰으로 비밀번호 변경
	public int memberPwChanage2(String userPw, String way) {
		int pwChanage = 0;
		String sql = "UPDATE member SET memPw=? WHERE cellphone = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userPw);
			pstmt.setString(2, way);
			pwChanage = pstmt.executeUpdate();			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		
		return pwChanage;
	}
	
	//	파트너스 회원으로 변경
	
	public int memberPartnerFl(int memNo){
		
		int chk =0;
		
		String sql = "update member set partnersFl=1 where memNo=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			System.out.println(pstmt);
			pstmt.setInt(1, memNo);
			chk = pstmt.executeUpdate();
			pstmt.close();
			System.out.println(pstmt);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
		
	}
	
	//기본배송지에 추가할 no찾기
	public int memberDelivery(String memId) {
		String sql = "select memNo from member where memId = ?";
		int mNo = 0;
		
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()) {
				mNo = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return mNo;
	}
	
	//기본배송지 추가
	public int memberDeliveryAdd(int mNo, String zonecode, String address, String addressSub, String time1) {
		String sql = "insert into memberdeliveryadd (memNo,deliveryFl,zonecode,address,addressSub,regDt) values(?,'y',?,?,?,?)";		
		int de = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, mNo);
			pstmt.setString(2, zonecode);
			pstmt.setString(3, address);
			pstmt.setString(4, addressSub);
			pstmt.setString(5, time1);
			de = pstmt.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return de;
	}
	
	//회원수정
	public int memModify(String id, String pw, String email, String phone, String time1) {
		String sql = "update member set memPw = ?, email =  ?, phone = ?, modDt=? where memId = ?";
		int mod = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			pstmt.setString(4, time1);
			pstmt.setString(5, id);
			mod = pstmt.executeUpdate();	
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		return mod;
	}
	
	//회원수정테이블추가
	public int memModifyHistory(int no, String pw, String email, String phone, String time1) {
		int mod = 0;
		String sql ="insert into memberhistory (memNo,processor,regDt) values(?,'member',?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, time1);
			mod = pstmt.executeUpdate();	
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		return mod;
	}
	
	//탈퇴테이블 넣기
	public int memberDeleteReason(String id, String pw, int no, StringBuffer reasons, String reasonDesc, String time1) {
		String sql = "insert into memberhackout (hackType,rejoinFl,memNo,memId,reasonCd,reasonDesc,hackDt,regDt) values('directSelf','y',?,?,?,?,?,?)";
		int md = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, id);
			pstmt.setString(3, reasons.toString());
			pstmt.setString(4, reasonDesc);
			pstmt.setString(5, time1);
			pstmt.setString(6, time1);
			md = pstmt.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return md;
	}
	
	//회원탈퇴여부 = y
	public int memberDeleteFl(String id) {
		int md = 0;
		String sql = "update member set deleteFl = 'y' where memId = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			md = pstmt.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return md;
	}

	//	파트너스 회원 전환
	public int updatePartnersFl(int memNo){
		int chk = 0;
		
		String sql = "update member set partnersFl=1, approvalFl1=1, approvalFl2=1, approvalFl3=1 where memNo=?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			
			chk = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		
		return chk;
	}

	//로그인 기록
	public void memberLoginLog(int memNo, String device, String ipAddress, String time1) {
		int chk = 0;
		
		String sql = "insert into memberloginlog (memNo,loginDevice,ip,regDt) values(?,?,?,?)";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			pstmt.setString(2, device);
			pstmt.setString(3, ipAddress);
			pstmt.setString(4, time1);
			
			chk = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
	}
	
	public ArrayList<memberDTO> getListAll(){
		ArrayList<memberDTO> list = new ArrayList<>();
		
		String sql = "select * from member";
		
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberDTO dto = new memberDTO();
				
				dto.setMemNo(rs.getInt("memNo"));
				dto.setMemId(rs.getString("memId"));
				dto.setGradeSno(rs.getInt("gradeSno"));
				dto.setGradeModDt(rs.getTimestamp("gradeModDt"));
				dto.setGradeValidDt(rs.getTimestamp("gradeValidDt"));
				dto.setMemNm(rs.getString("memName"));
				dto.setMemPw(rs.getString("memPw"));
				dto.setChangPwDt(rs.getTimestamp("changePwDt"));
				dto.setGuidePwDt(rs.getTimestamp("guidePwDt"));
				dto.setAppFl(rs.getBoolean("appFl"));
				dto.setApprovalDt(rs.getTimestamp("approvalDt"));
				dto.setMemFl(rs.getString("memFl"));
				dto.setEntryBenefitOfferDt(rs.getTimestamp("entryBenefitOfferDt"));
				dto.setSexFl(rs.getString("sexFl"));
				dto.setBirthDt(rs.getDate("birthDt"));
				dto.setBirthEventFl(rs.getBoolean("birthEventFl"));
				dto.setEmail(rs.getString("email"));
				dto.setZonecode(rs.getString("zonecode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddressSub(rs.getString("addressSub"));
				dto.setPhoneCountryCode(rs.getString("phoneCountryCode"));
				dto.setPhone(rs.getString("phone"));
				dto.setCellPhoneCountryCode(rs.getNString("cellPhoneCountryCode"));
				dto.setCellPhone(rs.getString("cellPhone"));
				dto.setFax(rs.getString("fax"));
				dto.setPartnersFl(rs.getBoolean("partnersFl"));
				dto.setCompany(rs.getString("company"));
				dto.setApprovalFl1(rs.getBoolean("approvalFl1"));
				dto.setApprovalFl2(rs.getBoolean("approvalFl2"));
				dto.setApprovalFl3(rs.getBoolean("approvalFl3"));
				dto.setService(rs.getString("service"));
				dto.setItem(rs.getString("item"));
				dto.setBusiNo(rs.getString("busiNo"));
				dto.setCeo(rs.getString("ceo"));
				dto.setComZonecode(rs.getString("comZonecode"));
				dto.setComAddress(rs.getString("comAddress"));
				dto.setComAddressSub(rs.getString("comAddressSub"));
				dto.setMileage(rs.getFloat("mileage"));
				dto.setDeposit(rs.getFloat("deposit"));
				dto.setFreedeposit(rs.getFloat("freedeposit"));
				dto.setMaillingFl(rs.getBoolean("mailingFl"));
				dto.setSmsFl(rs.getBoolean("smsFl"));
				dto.setMarriFl(rs.getBoolean("marriFl"));
				dto.setMarriDt(rs.getDate("marriDt"));
				dto.setJob(rs.getString("job"));
				dto.setInterest(rs.getString("interest"));
				dto.setReEntryFl(rs.getBoolean("reEntryFl"));
				dto.setEntryPath(rs.getString("entryPath"));
				dto.setLoginLimit(rs.getBoolean("loginLimit"));
				dto.setLastLoginDt(rs.getTimestamp("lastLoginDt"));
				dto.setLastLoginIp(rs.getString("lastLoginIp"));
				dto.setLastSaleDt(rs.getTimestamp("lastSaleDt"));
				dto.setLoginCnt(rs.getInt("loginCnt"));
				dto.setSaleCnt(rs.getInt("saleCnt"));
				dto.setSaleAmt(rs.getInt("saleAmt"));
				dto.setMemo(rs.getString("memo"));
				dto.setRecommId(rs.getString("recommId"));
				dto.setRecommFl(rs.getBoolean("recommFl"));
				dto.setPrivateUtilizationFl(rs.getBoolean("privateUtilizationFl"));
				dto.setPrivateFinanceFl(rs.getBoolean("privateFinanceFl"));
				dto.setPrivateApprovalFl(rs.getBoolean("privateApprovalFl"));
				dto.setPrivateApprovalOptionFl(rs.getBoolean("privateApprovalOptionFl"));
				dto.setPrivateOfferFl(rs.getBoolean("privateOfferFl"));
				dto.setPrivateConsignFl(rs.getBoolean("privateConsignFl"));
				dto.setUnder14ConsentFl(rs.getBoolean("under14ConsentFl"));
				
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	//총회원수
	public int allCount() {
		int count = 0;
		String sql = "select count(*) from member";
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}		
		return count;
	}
	
	//신규 회원수
	public int newCount(String time1) {
		int count = 0;
		String sql = "select count(*) from member where regDt > ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, time1);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}			
		return count;
	}
	
	//휴먼회원수
	public int sleepCount() {
		int count = 0;
		String sql = "select count(*) from membersleep";
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}			
		return count;
	}
	
	//탈퇴회원수
	public int deleteCount() {
		int count = 0;
		String sql = "select count(*) from memberhackout";
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		return count;
	}
	
	
	
	//회원리스트 검색조건
	private String memberSearch(String kind, String keyword, String apply, String minDay, String maxDay, BigDecimal minMileage,
			BigDecimal maxMileage, String gender, int startRow, int endRow, String mode, String sort, int grade) {
		
		String sql = (mode == "리스트") ? "select * from member where partnersFl = 0" : "select count(*) from member where partnersFl = 0";
		String con = "";
		
		if(!keyword.equals("")){
			if(kind.equals("아이디")) {
				con += " and memId like '%"+keyword+"%'";
			}else if(kind.equals("이름")) {				
				con += " and memName like '%"+keyword+"%'";
			}else if(kind.equals("이메일")) {				
				con += " and email like '%"+keyword+"%'";
			}else if(kind.equals("전화번호")) {				
				con += " and cellphone like '%"+keyword+"%'";
			}
		}
		
		if(!apply.equals("")) {
			if(apply.equals("전체")) {
				con += "";
			}else if(apply.equals("승인")) {
				con += " and appFl = 1";
			}else if(apply.equals("미승인")) {
				con += " and appFl = 0";
			}
		}
		
		if(grade != 0) {
			con += " and gradeSno = "+grade+"";
		}
		
		if(!minDay.equals("")) {
			con += " and regDt >= '"+minDay+"'";
		}
		
		if(!maxDay.equals("")&& !maxDay.equals(" 00:00:00")) {
			con += " and regDt <= '"+maxDay+"'";
		}
		
		if(minMileage.intValue() != 0) {
			con += " and mileage >= "+minMileage+"";
		}
		
		if(maxMileage.intValue() != 0) {
			con += " and mileage <= "+maxMileage+"";
		}
		
		if(!gender.equals("")) {
			if(gender.equals("전체")) {
				con += "";
			}else if(gender.equals("남자")) {
				con += " and sexFl = 'm'";
			}else if(gender.equals("여자")) {
				con += " and sexFl = 'w'";
			}
		}
		
		if(!sort.equals("order by regDt desc")) {
			if(sort.equals("가입일순")) {
				sort = " order by regDt desc";
			}else if(sort.equals("가입일순2")) {
				sort = " order by regDt";
			}else if(sort.equals("등급순")) {
				sort = " order by gradeSno desc";
			}else if(sort.equals("등급순2")) {
				sort = " order by gradeSno";
			}else if(sort.equals("주문금액")) {
				sort = " order by saleAmt desc";
			}else if(sort.equals("주문금액2")) {
				sort = " order by saleAmt";
			}else if(sort.equals("올페이")) {
				sort = " order by deposit desc";
			}else if(sort.equals("올페이2")) {
				sort = " order by deposit";
			}else if(sort.equals("적립금")) {
				sort = " order by mileage desc";
			}else if(sort.equals("적립금2")) {
				sort = " order by mileage";
			}
		}
		
		if(mode == "리스트") {
			sql += con + " "+sort+" limit "+startRow+" , "+endRow+"";
		}else if(mode == "카운트"){
			sql += con;			
		}
			
		System.out.println("sql = "+sql);
			
		return sql;
	}
	
	
	
	//회원리스트
	public List<memberDTO> getMemberList(String kind, String keyword, String apply, String minDay, String maxDay,
			BigDecimal minMileage, BigDecimal maxMileage, String gender, int startRow, int endRow, String sort, int grade) {
		String mode = "리스트";
		String sql = memberSearch(kind,keyword,apply,minDay,maxDay,minMileage,maxMileage,gender,startRow,endRow,mode,sort,grade);
		
		List<memberDTO> list = new ArrayList<memberDTO>();
		
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				memberDTO dto = new memberDTO();
				
				dto.setMemNo(rs.getInt("memNo"));
				dto.setMemId(rs.getString("memId"));
				dto.setGradeSno(rs.getInt("gradeSno"));
				dto.setGradeModDt(rs.getTimestamp("gradeModDt"));
				dto.setGradeValidDt(rs.getTimestamp("gradeValidDt"));
				dto.setMemNm(rs.getString("memName"));
				dto.setMemPw(rs.getString("memPw"));
				dto.setChangPwDt(rs.getTimestamp("changePwDt"));
				dto.setGuidePwDt(rs.getTimestamp("guidePwDt"));
				dto.setAppFl(rs.getBoolean("appFl"));
				dto.setApprovalDt(rs.getTimestamp("approvalDt"));
				dto.setMemFl(rs.getString("memFl"));
				dto.setEntryBenefitOfferDt(rs.getTimestamp("entryBenefitOfferDt"));
				dto.setSexFl(rs.getString("sexFl"));
				dto.setBirthDt(rs.getDate("birthDt"));
				dto.setBirthEventFl(rs.getBoolean("birthEventFl"));
				dto.setEmail(rs.getString("email"));
				dto.setZonecode(rs.getString("zonecode"));
				dto.setAddress(rs.getString("address"));
				dto.setAddressSub(rs.getString("addressSub"));
				dto.setPhoneCountryCode(rs.getString("phoneCountryCode"));
				dto.setPhone(rs.getString("phone"));
				dto.setCellPhoneCountryCode(rs.getNString("cellPhoneCountryCode"));
				dto.setCellPhone(rs.getString("cellPhone"));
				dto.setFax(rs.getString("fax"));
				dto.setPartnersFl(rs.getBoolean("partnersFl"));
				dto.setCompany(rs.getString("company"));
				dto.setApprovalFl1(rs.getBoolean("approvalFl1"));
				dto.setApprovalFl2(rs.getBoolean("approvalFl2"));
				dto.setApprovalFl3(rs.getBoolean("approvalFl3"));
				dto.setService(rs.getString("service"));
				dto.setItem(rs.getString("item"));
				dto.setBusiNo(rs.getString("busiNo"));
				dto.setCeo(rs.getString("ceo"));
				dto.setComZonecode(rs.getString("comZonecode"));
				dto.setComAddress(rs.getString("comAddress"));
				dto.setComAddressSub(rs.getString("comAddressSub"));
				dto.setMileage(rs.getFloat("mileage"));
				dto.setDeposit(rs.getFloat("deposit"));
				dto.setFreedeposit(rs.getFloat("freedeposit"));
				dto.setMaillingFl(rs.getBoolean("mailingFl"));
				dto.setSmsFl(rs.getBoolean("smsFl"));
				dto.setMarriFl(rs.getBoolean("marriFl"));
				dto.setMarriDt(rs.getDate("marriDt"));
				dto.setJob(rs.getString("job"));
				dto.setInterest(rs.getString("interest"));
				dto.setReEntryFl(rs.getBoolean("reEntryFl"));
				dto.setEntryPath(rs.getString("entryPath"));
				dto.setLoginLimit(rs.getBoolean("loginLimit"));
				dto.setLastLoginDt(rs.getTimestamp("lastLoginDt"));
				dto.setLastLoginIp(rs.getString("lastLoginIp"));
				dto.setLastSaleDt(rs.getTimestamp("lastSaleDt"));
				dto.setLoginCnt(rs.getInt("loginCnt"));
				dto.setSaleCnt(rs.getInt("saleCnt"));
				dto.setSaleAmt(rs.getInt("saleAmt"));
				dto.setMemo(rs.getString("memo"));
				dto.setRecommId(rs.getString("recommId"));
				dto.setRecommFl(rs.getBoolean("recommFl"));
				dto.setPrivateUtilizationFl(rs.getBoolean("privateUtilizationFl"));
				dto.setPrivateFinanceFl(rs.getBoolean("privateFinanceFl"));
				dto.setPrivateApprovalFl(rs.getBoolean("privateApprovalFl"));
				dto.setPrivateApprovalOptionFl(rs.getBoolean("privateApprovalOptionFl"));
				dto.setPrivateOfferFl(rs.getBoolean("privateOfferFl"));
				dto.setPrivateConsignFl(rs.getBoolean("privateConsignFl"));
				dto.setUnder14ConsentFl(rs.getBoolean("under14ConsentFl"));
				dto.setSleepFl(rs.getBoolean("sleepFl"));
				dto.setSleepWakeDt(rs.getTimestamp("sleepWakeDt"));
				dto.setSleepMailFl(rs.getBoolean("sleepMailFl"));
				dto.setSleepSmsFl(rs.getBoolean("sleepSmsFl"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDeleteFl(rs.getString("deleteFl"));
				
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}


	//회원리스트 카운트
	public int getMemberListCount(String kind, String keyword, String apply, String minDay, String maxDay,
			BigDecimal minMileage, BigDecimal maxMileage, String gender, int startRow, int endRow, String sort, int grade) {
		
		String mode = "카운트";
		String sql = memberSearch(kind,keyword,apply,minDay,maxDay,minMileage,maxMileage,gender,startRow,endRow,mode,sort,grade);

		int count = 0;

		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		return count;
	}
	
	//회원 탈퇴
	public int memberDelFl(int memNo) {
		int del = 0;
		String sql = "update member set deleteFl = 'y', modDt = now() where memNo = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,memNo);
			del = pstmt.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return del;
	}
	
	//관리자 탈퇴테이블 log
	public int memberhackout(int memNo) {
		int del = 0;
		String sql = "insert into memberhackout (hackType, memNo, adminMemo, managerSno, hackDt,"
				+ "state, regDt) values('directManager',?,'관리자 삭제',1,now(),'탈퇴완료',now())";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,memNo);
			del = pstmt.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return del;
	}
	
	//회원 휴먼해제
	public int memberSleepFl(int memNo) {
		int sleepFl = 0;
		String sql = "update member set sleepFl = '0' , sleepWakeDt = now(), modDt = now() where memNo = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,memNo);
			sleepFl = pstmt.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return sleepFl;
	}
	
	//휴먼테이블 log
	public int membersleep(int memNo) {
		int sleep = 0;
		String sql = "insert into membersleep (sleepFl,sleepDt,memNo,regDt)"
				   + "values(0,now(),?,now())";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,memNo);
			sleep = pstmt.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}

		return sleep;
	}
	
	//등급리스트
	public List<membergradeDTO> getMembergradeList() {
		List<membergradeDTO> mg = new ArrayList<>();
		String sql = "select * from membergrade";
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				membergradeDTO g = new membergradeDTO();
				g.setSno(rs.getInt("sno"));
				g.setGradeNm(rs.getString("gradeNm"));
				g.setGradeSort(rs.getInt("gradeSort"));
				g.setGradeIcon(rs.getString("gradeIcon"));
				g.setGradeImage(rs.getString("gradeImage"));
				g.setDcType(rs.getString("dcType"));
				g.setDcPercent(rs.getBigDecimal("dcPercent"));
				g.setDcPrice(rs.getBigDecimal("dcPrice"));
				g.setOverlapDcType(rs.getString("overlapDcType"));
				g.setOverlapDcPercent(rs.getBigDecimal("overlapDcPercent"));
				g.setOverlapDcPrice(rs.getBigDecimal("overlapDcPrice"));
				g.setMileageLine(rs.getInt("mileageLine"));
				g.setMileageType(rs.getBoolean("mileageType"));
				g.setMileagePercent(rs.getBigDecimal("mileagePercent"));
				g.setMileagePrice(rs.getBigDecimal("mileagePrice"));
				g.setDeliveryFreeFl(rs.getBoolean("deliveryFreeFl"));
				g.setOrderCnt(rs.getInt("orderCnt"));
				g.setOrderPriceMore(rs.getInt("orderPriceMore"));
				g.setOrderPriceBelow(rs.getInt("orderPriceBelow"));
				g.setCouponCd(rs.getString("couponCd"));
				g.setManagerNo(rs.getInt("managerNo"));
				g.setManagerNm(rs.getString("managerNm"));
				g.setRegDt(rs.getTimestamp("regDt"));
				g.setModDt(rs.getTimestamp("modDt"));
				mg.add(g);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		
		
		return mg;
	}
	
	//등급리스트
	public membergradeDTO getGrade(int grade) {
		membergradeDTO g = new membergradeDTO();
		String sql = "select * from membergrade where sno = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,grade);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				g.setSno(rs.getInt("sno"));
				g.setGradeNm(rs.getString("gradeNm"));
				g.setGradeSort(rs.getInt("gradeSort"));
				g.setGradeIcon(rs.getString("gradeIcon"));
				g.setGradeImage(rs.getString("gradeImage"));
				g.setDcType(rs.getString("dcType"));
				g.setDcPercent(rs.getBigDecimal("dcPercent"));
				g.setDcPrice(rs.getBigDecimal("dcPrice"));
				g.setOverlapDcType(rs.getString("overlapDcType"));
				g.setOverlapDcPercent(rs.getBigDecimal("overlapDcPercent"));
				g.setOverlapDcPrice(rs.getBigDecimal("overlapDcPrice"));
				g.setMileageLine(rs.getInt("mileageLine"));
				g.setMileageType(rs.getBoolean("mileageType"));
				g.setMileagePercent(rs.getBigDecimal("mileagePercent"));
				g.setMileagePrice(rs.getBigDecimal("mileagePrice"));
				g.setDeliveryFreeFl(rs.getBoolean("deliveryFreeFl"));
				g.setOrderCnt(rs.getInt("orderCnt"));
				g.setOrderPriceMore(rs.getInt("orderPriceMore"));
				g.setOrderPriceBelow(rs.getInt("orderPriceBelow"));
				g.setCouponCd(rs.getString("couponCd"));
				g.setManagerNo(rs.getInt("managerNo"));
				g.setManagerNm(rs.getString("managerNm"));
				g.setRegDt(rs.getTimestamp("regDt"));
				g.setModDt(rs.getTimestamp("modDt"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		
		
		return g;
	}

	//회원수정내역
	public List<memberhistoryDTO> memberhistoryList(int memNo) {
		List<memberhistoryDTO> h = new ArrayList<memberhistoryDTO>();
		String sql = "select * from memberhistory where memNo = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,memNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberhistoryDTO hh = new memberhistoryDTO();
				hh.setSno(rs.getInt("sno"));
				hh.setMemNo(rs.getInt("memNo"));
				hh.setProcessor(rs.getString("processor"));
				hh.setManagerNo(rs.getInt("managerNo"));
				hh.setProcessorIp(rs.getString("processorIp"));
				hh.setUpdateColumn(rs.getString("updateColumn"));
				hh.setBeforeValue(rs.getString("beforeValue"));
				hh.setAfterValue(rs.getString("afterValue"));
				hh.setRegDt(rs.getTimestamp("regDt"));
				hh.setModDt(rs.getTimestamp("modDt"));
				h.add(hh);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		
		return h;
	}
	
	//회원탈퇴내역
	public List<memberhackoutDTO> memberhackoutList(int memNo) {
		List<memberhackoutDTO> md = new ArrayList<>();
		String sql = "select * from memberhackout where memNo = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,memNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberhackoutDTO dd = new memberhackoutDTO();
				dd.setSno(rs.getInt("sno"));
				dd.setHackType(rs.getString("hackType"));
				dd.setRejoinFl(rs.getString("rejoinFl"));
				dd.setMemNo(rs.getInt("memNo"));
				dd.setDupeinfo(rs.getString("dupeinfo"));
				dd.setReasonCd(rs.getString("reasonCd"));
				dd.setReasonDesc(rs.getString("reasonDesc"));
				dd.setAdminMemo(rs.getString("adminMemo"));
				dd.setManagerSno(rs.getInt("managerSno"));
				dd.setManagerIp(rs.getString("managerIp"));
				dd.setHackDt(rs.getTimestamp("hackDt"));
				dd.setRegIp(rs.getString("regIp"));
				dd.setState(rs.getString("state"));
				dd.setRegDt(rs.getTimestamp("regDt"));
				dd.setModDt(rs.getTimestamp("modDt"));
				md.add(dd);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		
		return md;
	}
	
	//회원 수정
	public int memberMod(memberDTO dto) {
		int mod = 0;
		String sql = "update member set memId = ?, memName = ?, gradeSno = ?, appFl = ?, sleepFl = ?, "
				   + "birthDt = ?, sexFl = ?, email = ?, cellphone = ?, mailingFl = ?, smsFl = ?, "
				   + "zonecode = ?, address = ?, addressSub = ?, deleteFl = ?, modDt = now() where memNo = ?";
		if(dto.getMemPw() != null) {
			sql = "update member set memId = ?, memName = ?, gradeSno = ?, appFl = ?, sleepFl = ?, "
				+ "birthDt = ?, sexFl = ?, email = ?, cellphone = ?, mailingFl = ?, smsFl = ?, "
				+ "zonecode = ?, address = ?, addressSub = ?, deleteFl = ?, memPw = ?, modDt = now() where memNo = ?";
		}
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getMemId());
			pstmt.setString(2, dto.getMemNm());
			pstmt.setInt(3, dto.getGradeSno());
			pstmt.setBoolean(4, dto.isAppFl());
			pstmt.setBoolean(5, dto.isSleepFl());
			pstmt.setDate(6, dto.getBirthDt());
			pstmt.setString(7, dto.getSexFl());
			pstmt.setString(8, dto.getEmail());
			pstmt.setString(9, dto.getCellPhone());
			pstmt.setBoolean(10, dto.isMaillingFl());
			pstmt.setBoolean(11, dto.isSmsFl());
			pstmt.setString(12, dto.getZonecode());
			pstmt.setString(13, dto.getAddress());
			pstmt.setString(14, dto.getAddressSub());
			pstmt.setString(15, dto.getDeleteFl());
			if(dto.getMemPw() != null) {			
				pstmt.setString(16, dto.getMemPw());
				pstmt.setInt(17, dto.getMemNo());
			}else {
				pstmt.setInt(16, dto.getMemNo());
			}
			mod = pstmt.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}			
		return mod;
	}
	
	//관리자 수정테이블 insert
	public int memberModLog(memberDTO dto, String ip) {
		int mod = 0;
		String sql ="insert into memberhistory (memNo, processor, managerNo, "
				+ "processorIp, regDt)"
				+ "values(?,'administrator',1,?,now())";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, dto.getMemNo());
			pstmt.setString(2, ip);
			mod = pstmt.executeUpdate();	
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
		}	
		return mod;
	}
	
	//해당 등급회원수
	public int getMemberGradeCnt(int sno) {
		int cnt = 0;
		String sql = "select count(*) from member where gradeSno = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("count(*)");
			}
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}

		return cnt;
	}
	
	//등급삭제
	public int gradeDel(int gradeSno) {
		int del = 0;
		String sql = "delete from membergrade where sno = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, gradeSno);
			del = pstmt.executeUpdate();

		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return del;
	}
	
	//등급조건의 포함된 회원리스트 뽑아오는 함수
	public List<memberDTO> gradeRatingCondition(int orderCnt, int orderPriceMore, int orderPriceBelow) {
		List<memberDTO> list = new ArrayList<memberDTO>();
		String sql = "select memNo from member where saleCnt >= ? and saleAmt >= ?";	
		if(orderPriceBelow != 0) {
			   sql = "select memNo from member where saleCnt >= ? and saleAmt >= ? and saleAmt < "+orderPriceBelow+"";
		}
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, orderCnt);
			pstmt.setInt(2, orderPriceMore);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				memberDTO dto = new memberDTO();
				dto.setMemNo(rs.getInt("memNo"));
				list.add(dto);
			}

		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}
	
	//등급 수동평가
	public int gradeRating(int memNo, int sno) {
		int rating = 0;
		String sql = "update member set gradeSno = ? where memNo = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sno);
			pstmt.setInt(2, memNo);
			rating = pstmt.executeUpdate();

		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		
		return rating;
	}
	
	//등급이름 중복확인
	public String getGradeNm(String gradeNm) {
		String name = null;
		String sql = "select gradeNm from membergrade where gradeNm = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, gradeNm);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				name = rs.getString("gradeNm");
			}
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}

		return name;
	}

	//등급추가
	public int gradeInsert(membergradeDTO dto) {
		int insert = 0;
		String sql = "insert into membergrade (gradeNm, gradeSort, gradeImage,"
				+ "orderPriceMore, orderPriceBelow, orderCnt, priceLine,"
				+ "mileagePercent, couponCd, managerNo, managerNm, regDt) values(?,?,?,?,?,?,?,?,?,?,?,now())";
		//마지막 sort번호 가져오기
		int sort = gradeSort();
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getGradeNm());
			pstmt.setInt(2, (sort+1));
			pstmt.setString(3, dto.getGradeImage());
			pstmt.setInt(4, dto.getOrderPriceMore());
			pstmt.setInt(5, dto.getOrderPriceBelow());
			pstmt.setInt(6, dto.getOrderCnt());
			pstmt.setInt(7, dto.getPriceLine());
			pstmt.setBigDecimal(8, dto.getMileagePercent());
			pstmt.setString(9, dto.getCouponCd());
			pstmt.setInt(10, dto.getManagerNo());
			pstmt.setString(11, dto.getManagerNm());
			insert = pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return insert;
	}

	private int gradeSort() {
		int sort = 0;
		String sql = "select max(gradeSort) FROM  membergrade";
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sort = rs.getInt("max(gradeSort)");
			}
		}catch(SQLException se){
			se.printStackTrace();
		}

		return sort;
	}

	//등급수정
	public int gradeMod(membergradeDTO dto) {
		int mod = 0;
		String sql = "update membergrade set gradeNm = ?, gradeImage = ?, orderPriceMore = ?,"
				+ "orderPriceBelow = ?, orderCnt = ?, priceLine = ?, mileagePercent = ?,"
				+ "couponCd = ?,managerNo = ?, managerNm = ?,  modDt = now() where sno = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getGradeNm());
			pstmt.setString(2, dto.getGradeImage());
			pstmt.setInt(3, dto.getOrderPriceMore());
			pstmt.setInt(4, dto.getOrderPriceBelow());
			pstmt.setInt(5, dto.getOrderCnt());
			pstmt.setInt(6, dto.getPriceLine());
			pstmt.setBigDecimal(7, dto.getMileagePercent());
			pstmt.setString(8, dto.getCouponCd());
			pstmt.setInt(9, dto.getManagerNo());
			pstmt.setString(10, dto.getManagerNm());
			pstmt.setInt(11, dto.getSno());
			mod = pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return mod;
	}
	
	
	//마일리지 검색조건
	public List<membermileageDTO> getMemberMileageList(String kind, String keyword, String handleMode, String date01,
			String date02, BigDecimal price01, BigDecimal price02, int startRow, int endRow, String sort) {
		
		List<membermileageDTO> list = new ArrayList<>();
		String mode = "리스트";
		String sql = mileageSearch(kind,keyword,handleMode,date01,date02,price01,price02,startRow,endRow,sort,mode);
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				membermileageDTO dto = new membermileageDTO();
				dto.setSno(rs.getInt("sno"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setManagerNo(rs.getInt("managerNo"));
				dto.setHandleMode(rs.getString("handleMode"));
				dto.setHandleCd(rs.getString("handleCd"));
				dto.setHandleNo(rs.getString("handleNo"));
				dto.setBeforeMileage(rs.getBigDecimal("beforeMileage"));
				dto.setAfterMileage(rs.getBigDecimal("afterMileage"));
				dto.setMileage(rs.getBigDecimal("mileage"));
				dto.setReasonCd(rs.getString("reasonCd"));
				dto.setContents(rs.getString("contents"));
				dto.setDeleteFl(rs.getString("deleteFl"));
				dto.setDeleteScheduleDt(rs.getTimestamp("deleteScheduleDt"));
				dto.setDeleteDt(rs.getTimestamp("deleteDt"));
				dto.setRegIp(rs.getString("regIp"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}		

		return list;
	}
	

	//마일리지 검색결과 카운트
	public int memberMileageCount(String kind, String keyword, String handleMode, String date01, String date02,
			BigDecimal price01, BigDecimal price02, int startRow, int endRow, String sort) {
		
		int count = 0;
		String mode = "카운트";
		String sql = mileageSearch(kind,keyword,handleMode,date01,date02,price01,price02,startRow,endRow,sort,mode);
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	//마일리지 검색 sql
	private String mileageSearch(String kind, String keyword, String handleMode, String date01, String date02,
			BigDecimal price01, BigDecimal price02, int startRow, int endRow, String sort, String mode) {
		String sql = (mode == "리스트") ? "select * from membermileage where sno > 0" : "select count(*) from membermileage where sno > 0";
		String con = "";
		
		if(!keyword.equals("")){
			if(kind.equals("아이디")) {
				ArrayList<Integer> memNo = memId(keyword);
				if(!memNo.isEmpty()) {					
					String no = memNo.toString().replaceAll("\\[|\\]", "").replaceAll(", ",", ");
					con += " and memNo in ("+no+")";
				}
			}else if(kind.equals("이름")) {
				ArrayList<Integer> memNo = memNm(keyword);
				if(!memNo.isEmpty()) {					
					String no = memNo.toString().replaceAll("\\[|\\]", "").replaceAll(", ",", ");
					con += " and memNo in ("+no+")";
				}
			}else if(kind.equals("관리자")) {
				ArrayList<Integer> managerNo = managerNm(keyword);
				if(!managerNo.isEmpty()) {					
					String no = managerNo.toString().replaceAll("\\[|\\]", "").replaceAll(", ",", ");
					con += " and managerNo in ("+no+")";
				}
			}
		}
		
		if(!handleMode.equals("")) {
			if(handleMode.equals("전체")) {
				con += "";
			}else if(handleMode.equals("지급")) {
				con += " and handleMode = '지급'";
			}else if(handleMode.equals("차감")) {
				con += " and handleMode = '차감'";
			}else if(handleMode.equals("적립")) {
				con += " and handleMode = '적립'";
			}
		}
		
		if(!date01.equals("")) {
			con += " and regDt >= '"+date01+"'";
		}
		
		if(!date02.equals("")&& !date02.equals(" 00:00:00")) {
			con += " and regDt <= '"+date02+"'";
		}
		
		if(price01.intValue() != 0) {
			con += " and mileage >= "+price01+"";
		}
		
		if(price02.intValue() != 0) {
			con += " and mileage <= "+price02+"";
		}
		
		
		if(!sort.equals("order by regDt desc")) {
			if(sort.equals("등록순")) {
				sort = " order by regDt desc";
			}else if(sort.equals("등록순2")) {
				sort = " order by regDt";
			}else if(sort.equals("적립금")) {
				sort = " order by mileage desc";
			}else if(sort.equals("적립금2")) {
				sort = " order by mileage";
			}
		}
		
		if(mode == "리스트") {
			sql += con + " "+sort+" limit "+startRow+" , "+endRow+"";
		}else if(mode == "카운트"){
			sql += con;			
		}
			
		System.out.println("sql = "+sql);
			
		return sql;
	}
	
	//올페이 검색조건
	public List<memberdepositDTO> getMemberdepositList(String kind, String keyword, String handleMode, String date01,
			String date02, BigDecimal price01, BigDecimal price02, int startRow, int endRow, String sort) {
		
		List<memberdepositDTO> list = new ArrayList<>();
		String mode = "리스트";
		String sql = depositSearch(kind,keyword,handleMode,date01,date02,price01,price02,startRow,endRow,sort,mode);
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				memberdepositDTO dto = new memberdepositDTO();
				dto.setSno(rs.getInt("sno"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setManagerNo(rs.getInt("managerNo"));
				dto.setHandleMode(rs.getString("handleMode"));
				dto.setHandleCd(rs.getString("handleCd"));
				dto.setHandleNo(rs.getString("handleNo"));
				dto.setBeforeDeposit(rs.getBigDecimal("beforeDeposit"));
				dto.setAfterDeposit(rs.getBigDecimal("afterDeposit"));
				dto.setDeposit(rs.getBigDecimal("deposit"));
				dto.setReasonCd(rs.getString("reasonCd"));
				dto.setRegIp(rs.getString("regIp"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}		

		return list;
	}
	

	//올페이 검색결과 카운트
	public int memberdepositCount(String kind, String keyword, String handleMode, String date01, String date02,
			BigDecimal price01, BigDecimal price02, int startRow, int endRow, String sort) {
		
		int count = 0;
		String mode = "카운트";
		String sql = depositSearch(kind,keyword,handleMode,date01,date02,price01,price02,startRow,endRow,sort,mode);
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	//올페이 검색sql
	private String depositSearch(String kind, String keyword, String handleMode, String date01, String date02,
			BigDecimal price01, BigDecimal price02, int startRow, int endRow, String sort, String mode) {
		String sql = (mode == "리스트") ? "select * from memberdeposit where sno > 0" : "select count(*) from memberdeposit where sno > 0";
		String con = "";
		
		if(!keyword.equals("")){
			if(kind.equals("아이디")) {
				ArrayList<Integer> memNo = memId(keyword);
				if(!memNo.isEmpty()) {					
					String no = memNo.toString().replaceAll("\\[|\\]", "").replaceAll(", ",", ");
					con += " and memNo in ("+no+")";
				}
			}else if(kind.equals("이름")) {
				ArrayList<Integer> memNo = memNm(keyword);
				if(!memNo.isEmpty()) {					
					String no = memNo.toString().replaceAll("\\[|\\]", "").replaceAll(", ",", ");
					con += " and memNo in ("+no+")";
				}
			}else if(kind.equals("관리자")) {
				ArrayList<Integer> managerNo = managerNm(keyword);
				if(!managerNo.isEmpty()) {					
					String no = managerNo.toString().replaceAll("\\[|\\]", "").replaceAll(", ",", ");
					con += " and managerNo in ("+no+")";
				}
			}
		}
		
		if(!handleMode.equals("")) {
			if(handleMode.equals("전체")) {
				con += "";
			}else if(handleMode.equals("지급")) {
				con += " and handleMode = '지급'";
			}else if(handleMode.equals("차감")) {
				con += " and handleMode = '차감'";
			}else if(handleMode.equals("입금")) {
				con += " and handleMode = '입금'";
			}else if(handleMode.equals("환불")) {
				con += " and handleMode = '환불'";
			}
		}
		
		if(!date01.equals("")) {
			con += " and regDt >= '"+date01+"'";
		}
		
		if(!date02.equals("")&& !date02.equals(" 00:00:00")) {
			con += " and regDt <= '"+date02+"'";
		}
		
		if(price01.intValue() != 0) {
			con += " and deposit >= "+price01+"";
		}
		
		if(price02.intValue() != 0) {
			con += " and deposit <= "+price02+"";
		}
		
		
		if(!sort.equals("order by regDt desc")) {
			if(sort.equals("등록순")) {
				sort = " order by regDt desc";
			}else if(sort.equals("등록순2")) {
				sort = " order by regDt";
			}else if(sort.equals("올페이")) {
				sort = " order by deposit desc";
			}else if(sort.equals("올페이2")) {
				sort = " order by deposit";
			}
		}
		
		if(mode == "리스트") {
			sql += con + " "+sort+" limit "+startRow+" , "+endRow+"";
		}else if(mode == "카운트"){
			sql += con;			
		}
			
		System.out.println("sql = "+sql);
			
		return sql;
	}
	
	//managerNm으로 managerNo 찾기
	private ArrayList<Integer> managerNm(String keyword) {
		ArrayList<Integer> no = new ArrayList<>();
		String sql = "select sno from admin where name like ?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int i = rs.getInt("sno"); 
				no.add(i);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}

		return no;
	}
	
	//memNm으로 memNo 찾기
	private ArrayList<Integer> memNm(String keyword) {
		ArrayList<Integer> no = new ArrayList<>();
		String sql = "select memNo from member where memName like ?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int i = rs.getInt("memNo"); 
				no.add(i);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}

		return no;
	}
	
	//memId로 memNo찾기
	private ArrayList<Integer> memId(String keyword) {
		ArrayList<Integer> no = new ArrayList<>();
		String sql = "select memNo from member where memId like ?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int i = rs.getInt("memNo"); 
				no.add(i);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}

		return no;
	}
	
	//마일리지 리스트 삭제
	public int mileageListDel(int no) {
		int del = 0;
		String sql = "delete from membermileage where sno = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			del = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return del;
	}
	
	
	//올페이 리스트 삭제
	public int depositListDel(int no) {
		int del = 0;
		String sql = "delete from memberdeposit where sno = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			del = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return del;
	}
	
	//현재 회원이 가지고 있는 마일리지
	public BigDecimal mileage(int memNo) {
		BigDecimal mileage = new BigDecimal(0);
		String sql = "select mileage from member where memNo = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mileage = rs.getBigDecimal("mileage");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return mileage;
	}
	
	//관리자 회원 마일리지 지급
	public int payTheMileage(int memNo, BigDecimal afterMileage) {
		int payTo = 0;
		String sql = "update member set mileage = ?, modDt = now() where memNo = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setBigDecimal(1, afterMileage);
			pstmt.setInt(2, memNo);
			payTo = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return payTo;
	}
	
	//관리자 마일리지 지급 로그
	public int payTheMileageLog(membermileageDTO dto) {
		int log = 0;
		String sql = "insert into membermileage (memNo, managerNo, handleMode,"
				+ "  handleCd, handleNo, beforeMileage, afterMileage, mileage,"
				+ "  reasonCd, contents, regIp)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getMemNo());
			pstmt.setInt(2, dto.getManagerNo());
			pstmt.setString(3, dto.getHandleMode());
			pstmt.setString(4, dto.getHandleCd());
			pstmt.setString(5, dto.getHandleNo());
			pstmt.setBigDecimal(6, dto.getBeforeMileage());
			pstmt.setBigDecimal(7, dto.getAfterMileage());
			pstmt.setBigDecimal(8, dto.getMileage());
			pstmt.setString(9, dto.getReasonCd());
			pstmt.setString(10, dto.getContents());
			pstmt.setString(11, dto.getRegIp());			
			log = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return log;
	}
	
	//현재 회원이 가지고 있는 올페이
	public BigDecimal deposit(int memNo) {
		BigDecimal allpay = new BigDecimal(0);
		String sql = "select deposit from member where memNo = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				allpay = rs.getBigDecimal("deposit");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allpay;
	}
	
	//관리자 회원 올페이 지급
	public int payTheDeposit(int memNo, BigDecimal afterDeposit) {
		int payTo = 0;
		String sql = "update member set deposit = ?, modDt = now() where memNo = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setBigDecimal(1, afterDeposit);
			pstmt.setInt(2, memNo);
			payTo = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return payTo;
	}
	
	//관리자 올페이 지급 로그
	public int payTheDepositLog(memberdepositDTO dto) {
		int log = 0;
		String sql = "insert into memberdeposit (memNo, managerNo, handleMode,"
				+ "  handleCd, handleNo, beforeDeposit, afterDeposit, deposit,"
				+ "  reasonCd, regIp) values(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getMemNo());
			pstmt.setInt(2, dto.getManagerNo());
			pstmt.setString(3, dto.getHandleMode());
			pstmt.setString(4, dto.getHandleCd());
			pstmt.setString(5, dto.getHandleNo());
			pstmt.setBigDecimal(6, dto.getBeforeDeposit());
			pstmt.setBigDecimal(7, dto.getAfterDeposit());
			pstmt.setBigDecimal(8, dto.getDeposit());
			pstmt.setString(9, dto.getReasonCd());
			pstmt.setString(10, dto.getRegIp());
			log = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return log;
	}

	
}
