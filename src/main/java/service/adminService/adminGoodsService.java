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
	//???????????? ??????
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
	
	//???????????? goodsNo????????? service
	public List<goodsDTO> goodsNoList(String kind, String keyword) {
		goodsDAO dao = goodsDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		List<goodsDTO> gd = new ArrayList<goodsDTO>();

		if(kind.equals("?????????")) {	
			gd = dao.getGoodsNoList(keyword);
		}else if(kind.equals("????????????")){
			gd = dao.scmNmList(keyword);
		}
		
		close(con);
		return gd;
	}
		
	//????????? ?????????????????? ????????????
	public List<reviewDTO> reviewList3Search(int startRow, int endRow) {
		goodsSearchDAO dao = goodsSearchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<reviewDTO> rw = dao.reviewListSearch3(startRow,endRow);
		
		close(con);
		
		return rw;
	}
	
	//?????? ?????? ?????????
	public int listCount() {
		goodsSearchDAO dao = goodsSearchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int listCount = dao.reviewCount();
		
		close(con);
		
		return listCount;
	}
	//????????? ?????? ?????? ?????? 1??? ????????????
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
	
	//???????????? ??????
	public List<reviewDTO> reviewSearchResult(int goodsPt, int potoFl, String kind, String keyword, String gNo3, int startRow, int endRow) {
		goodsSearchDAO dao = goodsSearchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<reviewDTO> rw = dao.reviewSearchResult(goodsPt,potoFl,kind,keyword,gNo3,startRow,endRow);
		
		close(con);
		
		return rw;
	}
	
	//????????????
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
	
	//????????? ????????????
	public List<brandDTO> searchBrandList() {
		brandDAO dao = brandDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<brandDTO> bd = dao.brandList();
		
		close(con);
		
		return bd;
	}
	
	//??????????????? ????????? ??????
	public List<goodsDTO> searchGoodsList(String kind, String keyword, String minDate, String maxDate, String cateCd,
			String brand, int minPrice, int maxPrice, String goodsStatus, String search, int startRow, int endRow) {
		goodsSearchDAO dao = goodsSearchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<goodsDTO> gd = dao.searchGoodsList(kind,keyword,minDate,maxDate,cateCd,brand,minPrice,maxPrice,goodsStatus,startRow,endRow,search);
		
		close(con);
		
		return gd;
	}
	//??????????????? ????????? ???????????????
	public int searchGoodsCount(String kind, String keyword, String minDate, String maxDate, String cateCd,
			String brand, int minPrice, int maxPrice, String goodsStatus) {	
		goodsSearchDAO dao = goodsSearchDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.searchGoodsListCount(kind,keyword,minDate,maxDate,cateCd,brand,minPrice,maxPrice,goodsStatus);
	
		close(con);
		
		return count;
	}
	
	//?????? ?????? 0 -> 1
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
	
	//?????????????????? ????????? ??????????????? ????????????????????????
	public List<goodsOptionDTO> getOption(int goodsNo) {
		goodsOptionDAO dao = goodsOptionDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		List<goodsOptionDTO> o = dao.getList(goodsNo);
		
		close(con);
		
		return o;
	}
	
	//???????????????
	public brandDTO getBrand(String brandCd) {
		brandDAO dao = brandDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		brandDTO b = dao.getToCode(brandCd);
		
		close(con);
		
		return b;
	}
	
	//??????????????????
	public categoryDTO getCategory(String cateCd) {
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		categoryDTO c = dao.getCate(cateCd);
		
		close(con);
		
		return c;
	}
	
	
	
	//scmNm??????
	public scmDTO scmList(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//????????????
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
	
	//????????????
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
	
	//???????????????
	public List<themeDTO> searchThemeList() {
		themeDAO dao = themeDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<themeDTO> th = dao.getThemeList();
		
		close(con);
		
		return th;
	}
	
	//????????? ????????????
	public themeDTO getTheme(int theme) {
		themeDAO dao = themeDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		themeDTO th = dao.getTheme(theme);
		
		close(con);
		
		return th;
	}
	
	//???????????? ???????????? ??????
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
	
	//?????? ???????????? ??????
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
	
	//???????????? ?????? ??????
	public String getCateCd(String cateCd, int i) {
		categoryDAO dao = categoryDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		String code = dao.getCateCd(cateCd,i);
		
		close(con);
		
		return code;
	}
	//???????????? ??????
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
	//???????????? ??????
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
	
	//???????????? ??????
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
	
	//????????? ??????
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
	
	//????????? ??????
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
	
	//????????? ??????
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
	
	
	
	
	//?????? ??????
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
	
	//????????????
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
	
	//????????????
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
