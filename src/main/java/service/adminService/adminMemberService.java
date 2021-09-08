package service.adminService;

import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;

import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.math.BigDecimal;
import java.util.*;

import DAO.couponDAO.couponDAO;
import DAO.memberDAO.memberDAO;
import dto.adminDTO.adminDTO;
import dto.couponDTO.couponDTO;
import dto.memberDTO.memberDTO;
import dto.memberDTO.memberdepositDTO;
import dto.memberDTO.membergradeDTO;
import dto.memberDTO.memberhackoutDTO;
import dto.memberDTO.memberhistoryDTO;
import dto.memberDTO.membermileageDTO;

public class adminMemberService {
	
	//회원리스트
	public List<memberDTO> getmemberList(String kind, String keyword, String apply, String minDay, String maxDay,
			BigDecimal minMileage, BigDecimal maxMileage, String gender, int startRow, int endRow, String sort, int grade) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
				
		List<memberDTO> list = dao.getMemberList(kind,keyword,apply,minDay,maxDay,minMileage,maxMileage,gender,startRow,endRow,sort,grade);
		
		close(con);
		
		return list;
	}
	

	//회원리스트 카운트
	public int memberListCount(String kind, String keyword, String apply, String minDay, String maxDay,
			BigDecimal minMileage, BigDecimal maxMileage, String gender, int startRow, int endRow, String sort, int grade) {
		
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.getMemberListCount(kind,keyword,apply,minDay,maxDay,minMileage,maxMileage,gender,startRow,endRow,sort,grade);
		
		close(con);
		
		return count;
	}

	//관리자 맴버 탈퇴
	public int memberDel(int memNo) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int del = dao.memberDelFl(memNo);
		
		int delhackout = 0;
		if(del > 0) {
			delhackout = dao.memberhackout(memNo);
		}
		
		if(delhackout > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return del;
	}

	//관리자 맴버 휴먼해제
	public int memberSleepFl(int memNo) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int sleepFl = dao.memberSleepFl(memNo);
		
		int sleep = 0;
		if(sleepFl > 0) {			
			sleep = dao.membersleep(memNo);
		}
		
		if(sleep > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return sleepFl;
	}

	//등급리스트
	public List<membergradeDTO> getmembergrade() {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<membergradeDTO> mg = dao.getMembergradeList();
		
		close(con);
		
		return mg;
	}

	//등급정보
	public membergradeDTO getGrade(int grade) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		membergradeDTO mg = dao.getGrade(grade);
		
		close(con);
		
		return mg;
	}

	//회원정보
	public memberDTO getMember(int memNo) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		memberDTO info = dao.getMember(memNo);
		
		close(con);
		
		return info;
	}

	//회원 수정내역
	public List<memberhistoryDTO> memberhistoryList(int memNo) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<memberhistoryDTO> mh = dao.memberhistoryList(memNo);
		
		close(con);
		
		return mh;
	}
	
	//회원 탈퇴내역
	public List<memberhackoutDTO> memberhackoutList(int memNo) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<memberhackoutDTO> md = dao.memberhackoutList(memNo);
		
		close(con);
		
		return md;
	}
	
	//회원 수정, 수정 내역 로그 insert
	public int memberModify(memberDTO dto, String ip) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int mod = dao.memberMod(dto);
		
		int modlog = 0;
		if(mod > 0) {			
			modlog = dao.memberModLog(dto,ip);
		}
		
		if(modlog > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return mod;
	}
	
	//등급회원수
	public int membergradeCnt(int sno) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int cnt = dao.getMemberGradeCnt(sno);
		
		close(con);
		
		return cnt;
	}

	
	//등급삭제
	public int gradeDel(int gradeSno) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int del = dao.gradeDel(gradeSno);
		
		if(del > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return del;
	}

	//등급 수동평가
	public int gradeRating() { // cnt == grade list 총개수
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int rating = 0;
		
		// 1. 각 등급들의 등급조건들을 뽑아온다.
		List<membergradeDTO> dto = dao.getMembergradeList();
		
		for(int i=0; i < dto.size(); i++) {
			//2. 각 등급들의 등급조건이 맞는 회원들을 뽑아온다
			List<memberDTO> mlist = dao.gradeRatingCondition(dto.get(i).getOrderCnt(),dto.get(i).getOrderPriceMore(),dto.get(i).getOrderPriceBelow());
			//3. 뽑아온 회원들의 등급을 변경시킨다.
			for(int j=0; j < mlist.size(); j++) {				
				rating = dao.gradeRating(mlist.get(j).getMemNo(),dto.get(i).getSno());
			}
		}
		
		if(rating > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return rating;
	}

	//등급이름중복확인
	public int gradeNm(String gradeNm) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int msg = 0;
		
		String name = dao.getGradeNm(gradeNm);
		if(name != null) {
			msg = 0;
		}else {
			msg = 1;
		}
	
		close(con);
		
		return msg;
	}

	//등급 쿠폰
	public couponDTO getCouponList(String couponNo) {
		couponDAO dao = couponDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		couponDTO dto = dao.getCoupon(couponNo);
		
		close(con);
		
		return dto;
	}

	//등급 추가/수정
	public int gradeAction(membergradeDTO dto, String mode) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int action = 0;
		if(mode.equals("추가")) {
			System.out.println("추가");
			//등급추가
			action = dao.gradeInsert(dto);
		}else {
			//등급수정
			System.out.println("수정");
			action  = dao.gradeMod(dto);
		}
		
		if(action > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);

		return action;
	}

	//마일리지 관리 검색조건
	public List<membermileageDTO> getMileageList(String kind, String keyword, String handleMode, String date01,
			String date02, BigDecimal price01, BigDecimal price02, int startRow, int endRow, String sort) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<membermileageDTO> list = dao.getMemberMileageList(kind,keyword,handleMode,date01,date02,price01,price02,startRow,endRow,sort);
		
		close(con);
		
		return list;
	}
	
	//마일리지 관리 검색조건 카운트
	public int mileageListCount(String kind, String keyword, String handleMode, String date01, String date02,
			BigDecimal price01, BigDecimal price02, int startRow, int endRow, String sort) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.memberMileageCount(kind,keyword,handleMode,date01,date02,price01,price02,startRow,endRow,sort);
		
		close(con);
		
		return count;
	}

	//올페이 관리 검색조건
	public List<memberdepositDTO> getDepositList(String kind, String keyword, String handleMode, String date01,
			String date02, BigDecimal price01, BigDecimal price02, int startRow, int endRow, String sort) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<memberdepositDTO> list = dao.getMemberdepositList(kind,keyword,handleMode,date01,date02,price01,price02,startRow,endRow,sort);
		
		close(con);
		
		return list;
	}

	//올페이 관리 검색조건 카운트
	public int DepositListCount(String kind, String keyword, String handleMode, String date01, String date02,
			BigDecimal price01, BigDecimal price02, int startRow, int endRow, String sort) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
			
		int count = dao.memberdepositCount(kind,keyword,handleMode,date01,date02,price01,price02,startRow,endRow,sort);
		
		close(con);
		
		return count;
	}

	//적립금 리스트 삭제
	public int mileageListDel(int no) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int del = dao.mileageListDel(no);
		
		if(del > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return del;
	}

	//올페이 리스트 삭제
	public int depositDel(int no) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int del = dao.depositListDel(no);
		
		if(del > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return del;
	}

	//관리자 마일리지 지금
	public int payTheMileage(membermileageDTO dto) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		//회원 마일리지 업데이트
		int payTo = dao.payTheMileage(dto.getMemNo(),dto.getAfterMileage());
		
		if(payTo > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return payTo;
	}
	
	//마일리지 로그
	public int payTheMileageLog(membermileageDTO dto) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int log = dao.payTheMileageLog(dto);
		
		if(log > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
				
		return log;
	}


	//관리자 올페이 지급
	public int payTheDeposit(memberdepositDTO dto) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
				
		//올페이 지급 및 차감
		int payTo = dao.payTheDeposit(dto.getMemNo(),dto.getAfterDeposit());
		
		if(payTo > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return payTo;
	}

	//올페이 로그
	public int payTheDepositLog(memberdepositDTO dto) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int log = dao.payTheDepositLog(dto);
		
		if(log > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return log;
	}
	
	//회원이 가지고 있는 적립금
	public BigDecimal getMileage(int memNo) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		BigDecimal pay = dao.mileage(memNo);	
		
		close(con);
		
		return pay;
	}

	//회원이 가지고 있는 올페이
	public BigDecimal getDeposit(int memNo) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		BigDecimal pay = dao.deposit(memNo);	
		
		close(con);
		
		return pay;
	}
	

	
}
