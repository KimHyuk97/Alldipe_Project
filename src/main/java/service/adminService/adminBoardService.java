package service.adminService;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import DAO.boardDAO.boardDAO;
import dto.boardDTO.boardDTO;

public class adminBoardService {
	
	//게시판 리스트
	public List<boardDTO> getBoardList(String kind, String keyword, String boardType, String date01, String date02, int startRow, int endRow, String sort) {
		boardDAO dao = boardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		String mode = "리스트";
		List<boardDTO> list = dao.BoardList(kind,keyword,boardType,date01,date02,startRow,endRow,sort,mode);
		
		close(con);
		
		return list;
	}
	
	//게시판 카운트
	public int getBoardListCount(String kind, String keyword, String boardType, String date01, String date02,
			int startRow, int endRow, String sort) {
		boardDAO dao = boardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		String mode = "카운트";
		int cnt = dao.BoardListCount(kind,keyword,boardType,date01,date02,startRow,endRow,sort,mode);
		
		close(con);
		
		return cnt;
	}
	
	//게시판 선택삭제
	public int boardDel(int no) {
		boardDAO dao = boardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int del = dao.boardDel(no);
		
		if(del > 0) {
			commit(con);
		}else {
			rollback(con);
		}
				
		close(con);
		
		return del;
	}
	
	//게시판
	public boardDTO getBoard(int sno) {
		boardDAO dao = boardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		boardDTO dto = dao.getBoard(sno);
		
		close(con);
		
		return dto;
	}
	
	//답변하기
	public int answerInsert(int adminNo, String answerContent, int sno) {
		boardDAO dao = boardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int answer = dao.answerInsert(adminNo,answerContent,sno);
		
		if(answer > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return answer;
	}
	
	//공지사항 추가/수정
	public int noticeAction(boardDTO dto, String mode) {
		boardDAO dao = boardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int action = 0;
		
		if(mode.equals("등록")) {
			System.out.println("등록");
			action = dao.insertBoard(dto);
		}else {
			System.out.println("수정");
			action = dao.updateBoard(dto);
		}
		
		if(action > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return action;
	}

}
