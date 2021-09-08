package DAO.myPageDAO;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.memberDTO.memberDTO;
import dto.myPageDTO.memberCouponDTO;
import dto.myPageDTO.memberMileageDTO;
import dto.myPageDTO.myPageDTO;
import dto.orderDTO.orderGoods;

public class myPageDAO {
	private static myPageDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public static myPageDAO getInstance() {
		if (dao == null) {
			dao = new myPageDAO();
		}
		return dao;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	//배송지정보 불러오기
	public List<myPageDTO> myPageDeliveryDAO(int memNo) {
		List<myPageDTO> mp = new ArrayList<myPageDTO>();
		String sql = "select * from memberdeliveryadd where memNo = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				myPageDTO mp2 = new myPageDTO();
				mp2.setdNo(rs.getInt("dNo"));
				mp2.setMemNo(rs.getInt("memNo"));
				mp2.setDeliveryFl(rs.getString("deliveryFl"));
				mp2.setZonecode(rs.getString("zonecode"));
				mp2.setAddress(rs.getString("address"));
				mp2.setAddressSub(rs.getString("addressSub"));
				mp2.setGetName(rs.getString("getName"));
				mp2.setPhone(rs.getString("phone"));
				mp2.setRegDt(rs.getTimestamp("regDt"));
				
				mp.add(mp2);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
	
		return mp;
	}
	
	//배송지정보 저장
	public int myPageDeliveryAdd(int memNo, String deliveryFl, String zone, String addr, String address, String getName,
			String phone, String time1) {
		int mda = 0;
		String sql = "insert into memberdeliveryadd (memNo,deliveryFl,zonecode,address,addressSub,getName,phone,regDt) values(?,?,?,?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			pstmt.setString(2, deliveryFl);
			pstmt.setString(3, zone);
			pstmt.setString(4, addr);
			pstmt.setString(5, address);
			pstmt.setString(6, getName);
			pstmt.setString(7, phone);
			pstmt.setString(8, time1);
			mda=pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		 
		return mda;
	}
	
	
	//배송지수정
	public int myPageDeliveryModify(int memNo, String zone, String addr, String address, String getName, String phone,
			String time1, int dNo, String deliveryFl) {
		int mda = 0;
		String sql = "update memberdeliveryadd set deliveryFl=?, zonecode = ?,address = ?,addressSub = ?,getName = ?,phone = ?,modDt = ? where dNo = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, deliveryFl);
			pstmt.setString(2, zone);
			pstmt.setString(3, addr);
			pstmt.setString(4, address);
			pstmt.setString(5, getName);
			pstmt.setString(6, phone);
			pstmt.setString(7, time1);
			pstmt.setInt(8, dNo);
			mda=pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		 
		return mda;
	}
	
	
	//기본배송지 변경
	public int deliveryUpdate(String deliveryFl, int memNo, String zone, String addr, String address, String getName, String phone) {
		int mda = 0;
		int mem = 0;
		String sql = "update memberdeliveryadd set deliveryFl = 'n' where deliveryFl = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, deliveryFl);
			mda=pstmt.executeUpdate();
			System.out.println("mda"+mda);
			if(mda > 0) {
				String sql2 = "update member set zonecode = ?, address = ?, addressSub = ?, getName = ?, phone =? where memNo = ?";
				pstmt=con.prepareStatement(sql2);
				pstmt.setString(1, zone);
				pstmt.setString(2, addr);
				pstmt.setString(3, address);
				pstmt.setString(4, getName);
				pstmt.setString(5, phone);
				pstmt.setInt(6, memNo);
				mem=pstmt.executeUpdate();
				System.out.println("mem"+mem);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}	

		return mem;
	}
	
	//배송지 삭제
	public int myPageDeliveryDelete(int dNo) {
		int mda = 0;
		String sql = "delete from memberdeliveryadd where dNo = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, dNo);
			mda=pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
		return mda;
	}
	
	//비밀번호 확인
	public String PwCheck(String id, String pw) {
		String sql = "select memPw from member where memId=? and memPw=?";
		String pwCheck = null;
				
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				pwCheck = rs.getString(1);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
	
		return pwCheck;
	}
	
	//orderGoods주문내역
	public List<orderGoods> memberOrderGoods(int memNo) {
		List<orderGoods> mp = new ArrayList<orderGoods>();
		String sql = "select * from orderGoods where memNo = ?";	
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				orderGoods mp2 = new orderGoods();
				mp2.setSno(rs.getInt("sno")); 
				mp2.setOrderNo(rs.getString("orderNo")); 
				mp2.setOrderStatus(rs.getString("orderStatus")); 
				mp2.setInvoiceNo(rs.getString("invoiceNo")); 
				mp2.setGoodsNo(rs.getInt("goodsNo"));
				mp2.setRepresentImg(rs.getString("representImg"));
				mp2.setGoodsNm(rs.getString("goodsNm"));
				mp2.setGoodsCnt(rs.getInt("goodsCnt"));
				mp2.setGoodsOptionNm(rs.getString("goodsOptionNm"));
				mp2.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				mp2.setOptionPrice(rs.getBigDecimal("optionPrice"));
				mp2.setDeliveryPrice(rs.getBigDecimal("deliveryPrice"));
				mp2.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				mp2.setGoodsDcPrice(rs.getBigDecimal("goodsDcPrice"));
				mp2.setMemberDcPrice(rs.getBigDecimal("memberDcPrice"));
				mp2.setCouponGoodsDcPrice(rs.getBigDecimal("couponGoodsDcPrice"));
				mp2.setCouponCd(rs.getInt("couponCd"));
				mp2.setTimeSaleFl(rs.getBoolean("timeSaleFl"));
				mp2.setTimeSaleDcPrice(rs.getBigDecimal("timeSaleDcPrice"));
				mp2.setGoodsMileage(rs.getBigDecimal("goodsMileage"));
				mp2.setMemMileage(rs.getBigDecimal("memMileage"));
				mp2.setCancelDt(rs.getTimestamp("cancelDt"));
				mp2.setPaymentDt(rs.getTimestamp("paymentDt"));
				mp2.setSendSmsFl(rs.getString("sendSmsFl"));
				mp2.setInvoiceCompanySno(rs.getInt("invoiceCompanySno"));
				mp2.setInvoiceDt(rs.getTimestamp("invoiceDt"));
				mp2.setDeliveryDt(rs.getTimestamp("deliveryDt"));
				mp2.setDeliveryCompleteDt(rs.getTimestamp("deliveryCompleteDt"));
				mp2.setFinishDt(rs.getTimestamp("finishDt"));
				mp2.setGoodsDiscountInfo(rs.getString("goodsDiscountInfo"));
				mp2.setGoodsMileageAddInfo(rs.getString("goodsMileageAddInfo"));
				mp2.setRegDt(rs.getTimestamp("regDt"));
				mp.add(mp2);
			}
				
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}		
		return mp;
	}
	
	//회원쿠폰
	public List<memberCouponDTO> memberCounpon(int memNo) {
		List<memberCouponDTO> mp = new ArrayList<memberCouponDTO>();
		String sql = "select * from membercoupon where memNo = ?";	
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				memberCouponDTO mp2 = new memberCouponDTO();
				mp2.setMemberCouponNo(rs.getInt("memberCouponNo")); 
				mp2.setCouponNm(rs.getString("couponNm")); 
				mp2.setCouponBenefit(rs.getBigDecimal("couponBenefit")); 
				mp2.setMemberCouponStartDate(rs.getTimestamp("memberCouponStartDate")); 
				mp2.setMemberCouponEndDate(rs.getTimestamp("memberCouponEndDate")); 
				mp.add(mp2);
			}
				
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}		
		return mp;
	}
	
	//회원마일리지
	public List<memberMileageDTO> memberMileage(int memNo) {
		List<memberMileageDTO> mp = new ArrayList<memberMileageDTO>();
		String sql = "select * from membermileage where memNo = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				memberMileageDTO mp2 = new memberMileageDTO();
				mp2.setSno(rs.getInt("sno")); //일련번호
				mp2.setMemNo(rs.getInt("memNo")); //회원번호
				mp2.setHandleCd(rs.getString("handleCd")); //주문번호
				mp2.setMileage(rs.getDouble("mileage")); //적립금
				mp2.setContents(rs.getString("contents")); //내용
				mp2.setDeleteFl(rs.getString("deleteFl")); //사용상태
				mp2.setDeleteDt(rs.getTimestamp("deleteDt")); //소멸일
				mp2.setRegDt(rs.getTimestamp("regDt")); //등록일
				
				mp.add(mp2);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}	
	
		return mp;
	}
	
	//기본배송지 n -> y변경
	public int changeDeliveryFl(int dNo, String deliveryFl, int memNo) {
		int mda = 0;
		int mem = 0;
		String sql2 = "update memberdeliveryadd set deliveryFl = 'n' where deliveryFl = 'y'";
		try {
			pstmt=con.prepareStatement(sql2);
			int mda2=pstmt.executeUpdate();	
			if(mda2 > 0) {
				String sql = "update memberdeliveryadd set deliveryFl = 'y' where dNo = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, dNo);
				mda=pstmt.executeUpdate();
				if(mda > 0) {
					String sql4 = "select zonecode, address, addressSub from memberdeliveryadd where dNo = ?";
					
					pstmt=con.prepareStatement(sql4);
					pstmt.setInt(1, dNo);
					rs=pstmt.executeQuery();
					
					if(rs.next()) {
						String sql3 = "update member set zonecode = ?, address = ?, addressSub = ? where memNo = ?";
						pstmt=con.prepareStatement(sql3);
						pstmt.setString(1, rs.getString("zonecode"));
						pstmt.setString(2, rs.getString("address"));
						pstmt.setString(3, rs.getString("addressSub"));
						pstmt.setInt(4, memNo);
						System.out.println("pstmt==="+pstmt);
						mem=pstmt.executeUpdate();
					}
				}				
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}
		return mem;			
	}	

}

