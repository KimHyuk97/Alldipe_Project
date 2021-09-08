package service.adminService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static db.JdbcUtil.*;

import DAO.adminDAO.adminDAO;
import DAO.brandDAO.brandDAO;
import DAO.categoryDAO.categoryDAO;
import DAO.goodsDAO.goodsDAO;
import DAO.goodsDAO.goodsOptionDAO;
import DAO.goodsDAO.goodsSearchDAO;
import DAO.themeDAO.themeDAO;
import dto.boardDTO.reviewDTO;
import dto.brandDTO.brandDTO;
import dto.categoryDTO.categoryDTO;
import dto.goodsDTO.goodsDTO;
import dto.goodsDTO.goodsOptionDTO;
import dto.scmDTO.scmDTO;
import dto.themeDTO.themeDTO;

public class adminGoodsService {

	public ArrayList<goodsDTO> getGoodsList(String applyFl){
		ArrayList<goodsDTO> list = new ArrayList<>();
		
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		list = dao.GoodsList(applyFl);
		
		close(con);
		
		return list;
	}
	public ArrayList<goodsDTO> getGoodsList(){
		ArrayList<goodsDTO> list = new ArrayList<>();
		
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		list = dao.GoodsList();
		
		close(con);
		
		return list;
	}
	
	
	public goodsDTO getApplyGoods(int goodsNo){
		
		goodsDTO dto = null;
		
		goodsDAO dao = goodsDAO.getInstance();
		
		Connection con = getConnection();
		dao.setConnection(con);
		
		dto = dao.getGoods(goodsNo);
		
		close(con);
		
		return dto;
	}
	
	public int setApplyReject(int goodsNo, String msg){
		int chk = 0;
		
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		chk = dao.setApplyReject(goodsNo, msg);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	
	public int setApplyApprove(int goodsNo){
		int chk = 0;
		
		Connection con = getConnection();
		goodsDAO dao = goodsDAO.getInstance();
		
		dao.setConnection(con);
		
		chk = dao.setApplyApprove(goodsNo);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	//카테고리 코드
	public List<categoryDTO> categroyLsit(String cateCd) {
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		List<categoryDTO> lc = new ArrayList<categoryDTO>();
		if(cateCd.length() == 3) {
			lc = dao.mediumCategoryList(cateCd);
		}else if(cateCd.length() == 6) {
			lc = dao.smallCategoryList(cateCd);
		}else {
			lc = dao.categoryList();
		}
		close(con);
		
		return lc;
	}
	
	//구매후기 goodsNo구하는 service
	public List<goodsDTO> goodsNoList(String kind, String keyword) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		List<goodsDTO> gd = new ArrayList<goodsDTO>();

		if(kind.equals("상품명")) {	
			gd = dao.getGoodsNoList(keyword);
		}else if(kind.equals("공급사명")){
			gd = dao.scmNmList(keyword);
		}
		
		close(con);
		return gd;
	}
		
	//관리자 상품구매후기 전체검색
	public List<reviewDTO> reviewList3Search(int startRow, int endRow) {
		goodsSearchDAO dao = goodsSearchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<reviewDTO> rw = dao.reviewListSearch3(startRow,endRow);
		
		close(con);
		
		return rw;
	}
	
	//리뷰 갯수 구하기
	public int listCount() {
		goodsSearchDAO dao = goodsSearchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int listCount = dao.reviewCount();
		
		close(con);
		
		return listCount;
	}
	//선택된 리뷰 삭제 여부 1로 변경하기
	public int deleteFl(int reviewSno2) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int d = dao.reviewDeleteFl(reviewSno2);
		
		if(d > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return d;
	}
	
	//구매후기 검색
	public List<reviewDTO> reviewSearchResult(int goodsPt, int potoFl, String kind, String keyword, String gNo3, int startRow, int endRow) {
		goodsSearchDAO dao = goodsSearchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<reviewDTO> rw = dao.reviewSearchResult(goodsPt,potoFl,kind,keyword,gNo3,startRow,endRow);
		
		close(con);
		
		return rw;
	}
	
	//리뷰상세
	public reviewDTO reviewView(int sno) {
		adminDAO dao = adminDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		reviewDTO r = dao.reviewView(sno);
		
		close(con);
		
		return r;
	}
	
	public int reviewSearchResultCount(int goodsPt, int potoFl, String kind, String keyword, String gNo3) {
		goodsSearchDAO dao = goodsSearchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.reviewSearchResultCount(goodsPt,potoFl,kind,keyword,gNo3);
		
		close(con);
		
		return count;
	}
	
	//브랜드 검색조건
	public List<brandDTO> searchBrandList() {
		brandDAO dao = brandDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<brandDTO> bd = dao.brandList();
		
		close(con);
		
		return bd;
	}
	
	//상품리스트 조건별 검색
	public List<goodsDTO> searchGoodsList(String kind, String keyword, String minDate, String maxDate, String cateCd,
			String brand, int minPrice, int maxPrice, String goodsStatus, String search, int startRow, int endRow) {
		goodsSearchDAO dao = goodsSearchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<goodsDTO> gd = dao.searchGoodsList(kind,keyword,minDate,maxDate,cateCd,brand,minPrice,maxPrice,goodsStatus,startRow,endRow,search);
		
		close(con);
		
		return gd;
	}
	//상품리스트 조건별 갯수구하기
	public int searchGoodsCount(String kind, String keyword, String minDate, String maxDate, String cateCd,
			String brand, int minPrice, int maxPrice, String goodsStatus) {	
		goodsSearchDAO dao = goodsSearchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.searchGoodsListCount(kind,keyword,minDate,maxDate,cateCd,brand,minPrice,maxPrice,goodsStatus);
	
		close(con);
		
		return count;
	}
	
	//상품 삭제 0 -> 1
	public int goodsDeleteFl(int goodsNo) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int d = dao.goodsDeleteFl(goodsNo);
		
		if(d > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return d;
	}
	
	public goodsDTO getGoods(int goodsNo) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		goodsDTO d = dao.getGoods(goodsNo);
		
		close(con);
		
		return d;
	}
	
	//상품수정할때 수정할 상품정보의 옵션정보가져오기
	public List<goodsOptionDTO> getOption(int goodsNo) {
		goodsOptionDAO dao = goodsOptionDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		List<goodsOptionDTO> o = dao.getList(goodsNo);
		
		close(con);
		
		return o;
	}
	
	//브랜드찾기
	public brandDTO getBrand(String brandCd) {
		brandDAO dao = brandDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		brandDTO b = dao.getToCode(brandCd);
		
		close(con);
		
		return b;
	}
	
	//카테고리찾기
	public categoryDTO getCategory(String cateCd) {
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		categoryDTO c = dao.getCate(cateCd);
		
		close(con);
		
		return c;
	}
	
	
	
	//scmNm찾기
	public scmDTO scmList(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//옵션수정
	public int addGoodsOption(ArrayList<goodsOptionDTO> options) {
		goodsOptionDAO dao = goodsOptionDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int chk = 0;
		int del = 0;
		del = dao.deleteGoodsOption(options.get(0).getGoodsNo());
//		if(del > 0) {
			for(goodsOptionDTO dto : options){
					chk = dao.insertDto(dto);
			}
//		}
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	
	//상품수정
	public int modGoods(goodsDTO dto) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int mod = dao.modGoods(dto);
		
		if(mod > 0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return mod;
	}
	
	//테마리스트
	public List<themeDTO> searchThemeList() {
		themeDAO dao = themeDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<themeDTO> th = dao.getThemeList();
		
		close(con);
		
		return th;
	}
	
	//선택된 테마찾기
	public themeDTO getTheme(int theme) {
		themeDAO dao = themeDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		themeDTO th = dao.getTheme(theme);
		
		close(con);
		
		return th;
	}
	
	//카테고리 관련상품 찾기
	public List<goodsDTO> getCategoryRecomGoodsList(String recomGoodsNo) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<goodsDTO> g = new ArrayList<goodsDTO>();
		
		String[] gno = recomGoodsNo.split(",");
		
		int[] goodsNo = Arrays.stream(gno).mapToInt(Integer::parseInt).toArray();
		
		for(int i = 0; i <goodsNo.length; i++) {
			goodsDTO gg = dao.getGoods(goodsNo[i]);
			g.add(gg);
		}
				
		close(con);
		
		return g;
	}
	
	//테마 관련상품 찾기
	public List<goodsDTO> getThemeGoodsList(String goodsNos) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<goodsDTO> g = new ArrayList<goodsDTO>();
		
		String[] gno = goodsNos.split(",");
		
		int[] goodsNo = Arrays.stream(gno).mapToInt(Integer::parseInt).toArray();	
		
		for(int i = 0; i <goodsNo.length; i++) {
			goodsDTO gg = dao.getGoods(goodsNo[i]);
			g.add(gg);
		}
				
		close(con);
		
		return g;
	}
	
	//카테고리 번호 찾기
	public String getCateCd(String cateCd, int i) {
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		String code = dao.getCateCd(cateCd,i);
		
		close(con);
		
		return code;
	}
	//카테고리 추가
	public int categoryAdd(categoryDTO dto) {
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int add = dao.categoryAdd(dto);
		
		if(add>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return add;
	}
	//카테고리 수정
	public int categoryMod(categoryDTO dto) {
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int mod = dao.categoryMod(dto);
		
		if(mod>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return mod;
	}
	
	//카테고리 삭제
	public int categoryDel(int sno) {
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int del = dao.categoryDel(sno);
		
		if(del>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return del;
	}
	
	//브랜드 추가
	public int brandAdd(brandDTO dto) {
		brandDAO dao = brandDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int add = dao.adminBrandInsert(dto);
		
		if(add>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return add;
	}
	
	//브랜드 수정
	public int brandMod(brandDTO dto) {
		brandDAO dao = brandDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int add = dao.brandMod(dto);
		
		if(add>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return add;

	}
	
	//브랜드 삭제
	public int brandDel(int sno) {
		brandDAO dao = brandDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int del = dao.brandDel(sno);
		
		if(del>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return del;
	}
	
	
	
	
	//테마 추가
	public int themeAdd(themeDTO dto) {
		themeDAO dao = themeDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int add = dao.themeInsert(dto);
		
		if(add > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return add;
	}
	
	//테마수정
	public int themeMod(themeDTO dto) {
		themeDAO dao = themeDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int mod = dao.themeMod(dto);
		
		if(mod>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return mod;
	}
	
	//테마삭제
	public int themeDel(int sno) {
		themeDAO dao = themeDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int del = dao.themeDel(sno);
		
		if(del>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return del;
	}
	
}
