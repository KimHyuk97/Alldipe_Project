package service.goodsService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import DAO.categoryDAO.categoryDAO;
import DAO.goodsDAO.goodsDAO;
import dto.boardDTO.boardDTO;
import dto.boardDTO.qaDTO;
import dto.boardDTO.reviewDTO;
import dto.brandDTO.brandDTO;
import dto.categoryDTO.categoryDTO;
import dto.goodsDTO.goodsDTO;
import dto.goodsDTO.goodsOptionDTO;
import dto.scmDTO.scmDTO;
import service.paging.paging;

public class goodsService {

	//페이지카운트
	public int listCount(String cateCd) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int listCount = dao.goodsListCount(cateCd);
		
		if(listCount > 0) {
			commit(con);			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return listCount;
	}
	
	//상품리스트페이지
	public List<goodsDTO> goodsList(String cateCd, int startRow, int endRow) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<goodsDTO> goodsListResult = dao.goodsList(cateCd,startRow,endRow);
		
		close(con);
		
		return goodsListResult;
	}
	
	
	public List<goodsDTO> HeaderCategorygoodsList(String cateCd) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<goodsDTO> goodsListResult = dao.HeaderCategorygoodsList(cateCd);
		
		close(con);
		
		return goodsListResult;
	}
	
	//브랜드관
	public List<brandDTO> HeaderCategoryBrand(String cateCd) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<brandDTO> goodsListResult = dao.HeaderCategoryBrand(cateCd);
		
		close(con);
		
		return goodsListResult;
	}
	
	//브랜드 상품리스트
	public List<goodsDTO> HeaderCategoryBrandGoodsList(String brandCd) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<goodsDTO> goodsListResult = dao.HeaderCategoryBrandGoodsList(brandCd);
		
		close(con);
		
		return goodsListResult;
	}
	
	
	//상품상세페이지
	public goodsDTO goodsView(int goodsNo) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		goodsDTO goodsView = dao.goodsView(goodsNo);
		
		close(con);
		
		return goodsView;
	}
	
	//옵션정보 불러오기
	public List<goodsOptionDTO> goodsOptionList(int goodsNo) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<goodsOptionDTO> goodsOption = dao.getOptionList(goodsNo);
		
		close(con);
		
		return goodsOption;
	}
	
	
	//관련상품
	public List<goodsDTO> relationGoods(String relationGoodsNo) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<goodsDTO> relationGoods = dao.relationGoods(relationGoodsNo);
		
		close(con);
		
		return relationGoods;
	}

	public goodsDTO getGoods(int goodsNo){
		
		System.out.println("goodsService goodsNo : " + goodsNo);
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		goodsDTO dto = dao.goodsView(goodsNo);
		
		close(con);
		
		return dto;
	}
	
	public goodsDTO getGoodsInfo(int goodsNo){
		
		goodsDTO dto = null;
		
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		dto = dao.getGoods(goodsNo);
		
		close(con);
		
		return dto;
		
	}
	
	public ArrayList<categoryDTO> getAllCategory(){
		
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		ArrayList<categoryDTO> list = dao.getAllCategory();
		
		close(con);
		
		return list;
		
	}
	
	//리뷰정보
	public List<reviewDTO> goodsReviewList(int goodsNo) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<reviewDTO> rv = dao.goodsReviewList(goodsNo);
		
		close(con);
		
		return rv;
	}
	
	//문의글정보
	public List<boardDTO> qaList(int goodsNo) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<boardDTO> qa = dao.qaList(goodsNo);
		
		close(con);
		
		return qa;
	}

	//공급사 정보
	public scmDTO scmInfo(int scmNo) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		scmDTO scm = dao.scmInfo(scmNo);
		
		close(con);
		
		return scm;
	}
	
	
	//문의글
	public int qaWrite(int memNo, String writerNm, String writerId, String writerIp, String title,
			String contents, Boolean isSecret, String writerPw, int goodsNo, int scmNo) {
		
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int qa = dao.qaWrite(memNo,writerNm,writerId,writerIp,title,contents,isSecret,writerPw,goodsNo,scmNo);
		
		if(qa > 0) {
			commit(con);			
		} else {
			rollback(con);
		}
		
		
		close(con);
		
		return qa;
	}
	
	//문의글 비밀번호 찾기
	public String qaPwCheck(String qw2) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		String qaPw = dao.qaPwCheck(qw2);
		
		close(con);
		
		
		return qaPw;
	}
	
	//조회수 +
	public void goodsHit(int goodsNo) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		dao.goodsHit(goodsNo);
		
	}
	
	public ArrayList<goodsDTO> getToCondition(String condition, paging p){
		
		ArrayList<goodsDTO> list = new ArrayList<>();
		
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		list = dao.getToCondition(condition, p);
		
		close(con);
		
		return list;
	}
	
	public ArrayList<goodsDTO> getList(String cateCd, paging p){
		
		ArrayList<goodsDTO> list = new ArrayList<>();
		
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		list = dao.getList(cateCd, p);
		
		close(con);
		
		return list;
	}
	
	public int updateOption(String option, String value, int goodsNo){
		
		int chk = 0;
		
		goodsDTO dto = getGoods(goodsNo);
		
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		chk = dao.updateOption(option, value, goodsNo);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
		
	}
	
	
}
