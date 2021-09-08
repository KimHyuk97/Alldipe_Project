package DAO.boardDAO;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.boardDTO.answerDTO;
import dto.boardDTO.boardDTO;
import dto.boardDTO.noticeDTO;
import dto.boardDTO.qaDTO;
import service.paging.paging;

public class boardDAO {

	private static boardDAO dao;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public static boardDAO getInstance(){
		if(dao == null){
			dao = new boardDAO();
		}
		
		return dao;
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	

//	기본세팅 End
	
	public boardDTO getBoard(int sno) {
		boardDTO dto = new boardDTO();
		String sql = "select * from board where sno = ?";
		try{			
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, sno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){		
				dto.setNo(rs.getInt("sno"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setWriterNm(rs.getString("writerNm"));
				dto.setWriterId(rs.getString("writerId"));
				dto.setWriterIp(rs.getString("writerIp"));
				dto.setPartnersFl(rs.getBoolean("partnersFl"));
				dto.setBoardType(rs.getString("boardType"));
				dto.setTheme(rs.getString("theme"));
				dto.setCate(rs.getString("cate"));
				dto.setSort(rs.getInt("sort"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setImg(rs.getString("img"));
				dto.setSecret(rs.getBoolean("isSecret"));
				dto.setPassword(rs.getString("pw"));
				dto.setViewCnt(rs.getInt("viewCnt"));
				dto.setGoodsNo(rs.getInt("goodsNo"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setOrderNo(rs.getString("orderNo"));
				dto.setReplyStatus(rs.getString("replyStatus"));
				dto.setDelete(rs.getBoolean("isDelete"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDeleteDt(rs.getTimestamp("deleteDt"));
				dto.setAdminNo(rs.getInt("adminNo"));
				dto.setAnswerDt(rs.getTimestamp("answerDt"));
				dto.setAnswerContent(rs.getString("answerContent"));				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dto;
	}
	
//	공지사항 페이징 리스트 가져오기
	public int insertBoard(boardDTO dto){
		
		int chk = 0;

		String sql = "insert into board (memNo, writerNm, writerId, writerIp, partnersFl, boardType, theme, cate, sort, title, contents, img, isSecret, pw, goodsNo, scmNo, orderNo)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getMemNo());
			pstmt.setString(2, dto.getWriterNm());
			pstmt.setString(3, dto.getWriterId());
			pstmt.setString(4, dto.getWriterIp());
			pstmt.setBoolean(5, dto.isPartnersFl());
			pstmt.setString(6, dto.getBoardType());
			pstmt.setString(7, dto.getTheme());
			pstmt.setString(8, dto.getCate());
			pstmt.setInt(9, dto.getSort());
			pstmt.setString(10, dto.getTitle());
			pstmt.setString(11, dto.getContents());
			pstmt.setString(12, dto.getImg());
			pstmt.setBoolean(13, dto.isSecret());
			pstmt.setString(14, dto.getPassword());
			pstmt.setInt(15, dto.getGoodsNo());
			pstmt.setInt(16, dto.getScmNo());
			pstmt.setString(17, dto.getOrderNo());
			System.out.println(pstmt);
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
		
	}
	
	public ArrayList<boardDTO> getBoardList(boolean partnersFl, String boardType, String theme, String cate, int start, int limit){
		
		ArrayList<boardDTO> list = new ArrayList<>();
		
		String sql = "select * from board where partnersFl=? and boardType=? and theme=? and cate=? limit ?,?";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setBoolean(1, partnersFl);
			pstmt.setString(2, boardType);
			pstmt.setString(3, theme);
			pstmt.setString(4, cate);
			pstmt.setInt(5, start);
			pstmt.setInt(6, limit);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				boardDTO dto = new boardDTO();
				
				dto.setNo(rs.getInt("sno"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setWriterNm(rs.getString("writerNm"));
				dto.setWriterId(rs.getString("writerId"));
				dto.setWriterIp(rs.getString("writerIp"));
				dto.setPartnersFl(rs.getBoolean("partnersFl"));
				dto.setBoardType(rs.getString("boardType"));
				dto.setTheme(rs.getString("theme"));
				dto.setCate(rs.getString("cate"));
				dto.setSort(rs.getInt("sort"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setImg(rs.getString("img"));
				dto.setSecret(rs.getBoolean("isSecret"));
				dto.setPassword(rs.getString("pw"));
				dto.setViewCnt(rs.getInt("viewCnt"));
				dto.setGoodsNo(rs.getInt("goodsNo"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setOrderNo(rs.getString("orderNo"));
				dto.setReplyStatus(rs.getString("replyStatus"));
				dto.setDelete(rs.getBoolean("isDelete"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDeleteDt(rs.getTimestamp("deleteDt"));
				dto.setAdminNo(rs.getInt("adminNo"));
				dto.setAnswerDt(rs.getTimestamp("answerDt"));
				dto.setAnswerContent(rs.getString("answerContent"));
			
				list.add(dto);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	
public ArrayList<boardDTO> getBoardList(boolean partnersFl, String boardType, String theme, int start, int limit){
		
		ArrayList<boardDTO> list = new ArrayList<>();
		
		String sql = "select * from board where partnersFl=? and boardType=? and theme=? limit ?,?";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setBoolean(1, partnersFl);
			pstmt.setString(2, boardType);
			pstmt.setString(3, theme);
			pstmt.setInt(4, start);
			pstmt.setInt(5, limit);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				boardDTO dto = new boardDTO();
				
				dto.setNo(rs.getInt("sno"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setWriterNm(rs.getString("writerNm"));
				dto.setWriterId(rs.getString("writerId"));
				dto.setWriterIp(rs.getString("writerIp"));
				dto.setPartnersFl(rs.getBoolean("partnersFl"));
				dto.setBoardType(rs.getString("boardType"));
				dto.setTheme(rs.getString("theme"));
				dto.setCate(rs.getString("cate"));
				dto.setSort(rs.getInt("sort"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setImg(rs.getString("img"));
				dto.setSecret(rs.getBoolean("isSecret"));
				dto.setPassword(rs.getString("pw"));
				dto.setViewCnt(rs.getInt("viewCnt"));
				dto.setGoodsNo(rs.getInt("goodsNo"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setOrderNo(rs.getString("orderNo"));
				dto.setReplyStatus(rs.getString("replyStatus"));
				dto.setDelete(rs.getBoolean("isDelete"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDeleteDt(rs.getTimestamp("deleteDt"));
				dto.setAdminNo(rs.getInt("adminNo"));
				dto.setAnswerDt(rs.getTimestamp("answerDt"));
				dto.setAnswerContent(rs.getString("answerContent"));
			
				list.add(dto);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public int getTotalCnt(boolean partnersFl, String boardType, String theme){
		int chk = 0;
		
		String sql = "select count(sno) from board where partnersFl=? and boardType=? and theme=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, partnersFl);
			pstmt.setString(2, boardType);
			pstmt.setString(3, theme);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				chk = rs.getInt("count(sno)");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	
	public int getTotalCnt(boolean partnersFl, String boardType, String theme, String cate){
		int chk = 0;
		
		String sql = "select count(sno) from board where partnersFl=? and boardType=? and theme=? and cate=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, partnersFl);
			pstmt.setString(2, boardType);
			pstmt.setString(3, theme);
			pstmt.setString(4, cate);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				chk = rs.getInt("count(sno)");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}

	public List<boardDTO> BoardList(String kind, String keyword, String boardType, String date01, String date02, int startRow, int endRow, String sort, String mode) {
		List<boardDTO> list = new ArrayList<boardDTO>();		
		String sql = boardListSql(kind,keyword,boardType,date01,date02,startRow,endRow,sort,mode);
		
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();			
			while(rs.next()){				
				boardDTO dto = new boardDTO();				
				dto.setNo(rs.getInt("sno"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setWriterNm(rs.getString("writerNm"));
				dto.setWriterId(rs.getString("writerId"));
				dto.setWriterIp(rs.getString("writerIp"));
				dto.setPartnersFl(rs.getBoolean("partnersFl"));
				dto.setBoardType(rs.getString("boardType"));
				dto.setTheme(rs.getString("theme"));
				dto.setCate(rs.getString("cate"));
				dto.setSort(rs.getInt("sort"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setImg(rs.getString("img"));
				dto.setSecret(rs.getBoolean("isSecret"));
				dto.setPassword(rs.getString("pw"));
				dto.setViewCnt(rs.getInt("viewCnt"));
				dto.setGoodsNo(rs.getInt("goodsNo"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setOrderNo(rs.getString("orderNo"));
				dto.setReplyStatus(rs.getString("replyStatus"));
				dto.setDelete(rs.getBoolean("isDelete"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDeleteDt(rs.getTimestamp("deleteDt"));
				dto.setAdminNo(rs.getInt("adminNo"));
				dto.setAnswerDt(rs.getTimestamp("answerDt"));
				dto.setAnswerContent(rs.getString("answerContent"));			
				list.add(dto);				
			}		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public int BoardListCount(String kind, String keyword, String boardType, String date01, String date02, int startRow,
			int endRow, String sort, String mode) {
		int cnt = 0;
		
		String sql = boardListSql(kind,keyword,boardType,date01,date02,startRow,endRow,sort,mode);
		
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
	
	//게시판 sql
	private String boardListSql(String kind, String keyword, String boardType, String date01, String date02, int startRow, int endRow, String sort, String mode) {
		String sql = (mode == "리스트") ? "select * from board where isDelete < 1" : "select count(*) from board where isDelete < 1";
		String con = "";
		
		if(!keyword.equals("")){
			if(kind.equals("작성자")) {		
				con += " and writerNm like '%"+keyword+"%'";
			}else if(kind.equals("작성자ID")) {
				con += " and writerId like '%"+keyword+"%'";
			}else if(kind.equals("제목")) {
				con += " and title like '%"+keyword+"%'";
			}else if(kind.equals("전체")){
				con += " and (writerNm like '%"+keyword+"%' or writerId like '%"+keyword+"%' or title like '%"+keyword+"%')";
			}
		}
		
		if(!boardType.equals("")) {
			if(boardType.equals("전체")) {
				con += "";
			}else if(boardType.equals("상품문의")) {
				con += " and boardType = '상품문의'";
			}else if(boardType.equals("자주묻는질문")) {
				con += " and boardType = '자주묻는질문'";
			}else if(boardType.equals("1:1문의")) {
				con += " and boardType = '1:1문의'";
			}else if(boardType.equals("공지사항")) {
				con += " and boardType = '공지사항'";
			}
		}
		
		if(!date01.equals("")) {
			con += " and regDt >= '"+date01+"'";
		}
		
		if(!date02.equals("")&& !date02.equals(" 00:00:00")) {
			con += " and regDt <= '"+date02+"'";
		}
		
		
		if(!sort.equals("order by regDt desc")) {
			if(sort.equals("등록순")) {
				sort = " order by regDt desc";
			}else if(sort.equals("등록순2")) {
				sort = " order by regDt";
			}else if(sort.equals("조회수")) {
				sort = " order by viewCnt desc";
			}else if(sort.equals("조회수2")) {
				sort = " order by viewCnt";
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
	
	//게시판 삭제
	public int boardDel(int no) {
		int del = 0;
		String sql  = "update board set isDelete = 1, modDt = now(), deleteDt = now()  where sno = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			del = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return del;
	}
	
	
	//답변하기
	public int answerInsert(int adminNo, String answerContent, int sno) {
		int answer = 0;
		String sql = "update board set adminNo = ?, answerContent = ?, answerDt = now(), modDt = now() where sno = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, adminNo);
			pstmt.setString(2, answerContent);
			pstmt.setInt(3, sno);
			answer = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return answer;
	}
	
	//공지사항 수정
	public int updateBoard(boardDTO dto) {
		int update = 0;
		String sql = "update board set partnersFl = ?, theme = ?, title = ?, contents = ?,"
				+ "img = ?, modDt = now() where sno = ?";
	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, dto.isPartnersFl());
			pstmt.setString(2, dto.getTheme());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContents());
			pstmt.setString(5, dto.getImg());
			pstmt.setInt(6, dto.getNo());
			
			update = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return update;
	}
	
	
}

