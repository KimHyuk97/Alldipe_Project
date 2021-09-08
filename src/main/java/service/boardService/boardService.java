package service.boardService;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.boardDAO.boardDAO;
import dto.boardDTO.boardDTO;
import service.paging.paging;

import static db.JdbcUtil.*;

public class boardService {

//	파트너 공지사항
	//	파트너 공지사항 4개 불러오기
	public ArrayList<boardDTO> getNoticeMain(){
		
		boardDAO dao = new boardDAO();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<boardDTO> list = null;
		
		list = dao.getBoardList(true,"notice", "",1,4 );
		
		close(con);
		
		return list;
	}
	
	//	카테고리 구분 목록 검색
	public ArrayList<boardDTO> getBoardList(boolean partnersFl, String type, String theme, String cate, paging p){
		
		boardDAO dao = new boardDAO();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<boardDTO> list = dao.getBoardList(partnersFl, type, theme, cate, ((p.getPageNo()-1)*p.getPageSize()), p.getPageSize());
		
		close(con);
		
		return list;
	}
	
	//	카테고리 구분없이 목록 검색
	public ArrayList<boardDTO> getBoardList(boolean partnersFl, String type, String theme, paging p){
		
		boardDAO dao = new boardDAO();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<boardDTO> list = dao.getBoardList(partnersFl,type, theme, ((p.getPageNo()-1)*p.getPageSize()), p.getPageSize());
		
		close(con);
		
		return list;
	}
	
	
	
//	파트너 공지사항 끝
	//	조건별 게시글 수 조회
	
	
	//	공지사항 등록
	public int insertBorad(boardDTO dto){
		int chk = 0;
		
		boardDAO dao = boardDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		chk = dao.insertBoard(dto);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	
//조건별 총 게시글 수
	public int getTotalCnt(boolean partnersFl, String boardType, String theme){
		int chk = 0;
		
		boardDAO dao = boardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		chk = dao.getTotalCnt(partnersFl, boardType, theme);
		
		close(con);
		
		return chk;
	}
	
	public int getTotalCnt(boolean partnersFl, String boardType, String theme, String cate){
		int chk = 0;
		
		boardDAO dao = boardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		chk = dao.getTotalCnt(partnersFl, boardType, theme, cate);
		
		close(con);
		
		return chk;
	}
	
}
