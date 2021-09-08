package DAO.goodsDAO;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.boardDTO.reviewDTO;
import dto.goodsDTO.goodsDTO;

public class goodsSearchDAO {

	private static goodsSearchDAO dao;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;


	public static goodsSearchDAO getInstance() {
		if (dao == null) {
			dao = new goodsSearchDAO();
		}
		return dao;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//	메서드 시작
	
	public ArrayList<goodsDTO> SearchList(String sql){
		
		ArrayList<goodsDTO> list = new ArrayList<>();
		
		goodsDTO dto = null;
		
		try{
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				dto = new goodsDTO();
				
				dto.setGoodsNo(rs.getInt("goodsNo"));
				dto.setGoodsNm(rs.getString("goodsNm"));
				dto.setGoodsSellFl(rs.getInt("goodsSellFl"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setApplyFl(rs.getString("applyFl"));
				dto.setApplyMsg(rs.getString("applyMsg"));
				dto.setApplyDt(rs.getTimestamp("applyDt"));
				dto.setCommission(rs.getBigDecimal("commission"));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setRepresentImg(rs.getString("representImg"));
				dto.setSubImg(rs.getString("subImg"));				
				dto.setBrandCd(rs.getString("brandCd"));
				dto.setMakerNm(rs.getString("makerNm"));
				dto.setOriginNm(rs.getString("originNm"));
				dto.setGoodsMustInfo(rs.getString("goodsMustInfo"));
				dto.setKcmarkInfo(rs.getString("kcmarkInfo"));
				dto.setOnlyAdultFl(rs.getBoolean("onlyAdultFl"));
				dto.setTaxFreeFl(rs.getString("taxFreeFl"));
				dto.setTaxPercent(rs.getBigDecimal("taxPercent"));
				dto.setGoodsWeight(rs.getBigDecimal("goodsWeight"));
				dto.setFixedSales(rs.getString("fixedSales"));
				dto.setFixedOrderCnt(rs.getString("fixedOrderCnt"));
				dto.setSalesUnit(rs.getInt("salesUnit"));
				dto.setMinOrderCnt(rs.getInt("minOrderCnt"));
				dto.setMaxOrderCnt(rs.getInt("maxOrderCnt"));
				
				dto.setSalesStartDt(rs.getTimestamp("salesStartDt"));
				dto.setSalesEndDt(rs.getTimestamp("salesEndDt"));
				dto.setRestockFl(rs.getBoolean("restockFl"));
				dto.setDiscountInfo(rs.getString("discountInfo"));
				dto.setPeriodDiscountStart(rs.getTimestamp("periodDiscountStart"));
				dto.setPeriodDiscountEnd(rs.getTimestamp("periodDiscountEnd"));
				dto.setGoodsDiscountFl(rs.getBoolean("goodsDiscountFl"));
				dto.setGoodsDiscountType(rs.getBoolean("goodsDiscountType"));
				dto.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				dto.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				dto.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				dto.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				dto.setCostPrice(rs.getBigDecimal("costPrice"));
				
				dto.setShortDescription(rs.getString("shortDescription"));
				dto.setEventDescription(rs.getString("eventDescription"));
				dto.setGoodsDescription(rs.getString("goodsDescription"));
				dto.setDeliveryCompany(rs.getString("deliveryCompany"));
				dto.setDeliveryWay(rs.getString("deliveryWay"));
				dto.setDeliveryKind(rs.getString("deliveryKind"));
				dto.setDeliveryFreeCondition(rs.getBigDecimal("deliveryFreeCondition"));
				dto.setRelationGoodsNo(rs.getString("relationGoodsNo"));
				dto.setDetailInfoDelivery(rs.getString("detailInfoDelivery"));
				dto.setDetailInfoAS(rs.getString("detailInfoAS"));
				dto.setDetailInfoRefund(rs.getString("detailInfoRefund"));
				dto.setDetailInfoExchange(rs.getString("detailInfoExchange"));
				
				dto.setTotalStock(rs.getInt("totalStock"));
				dto.setOrderCnt(rs.getInt("orderCnt"));
				dto.setOrderGoodsCnt(rs.getInt("orderGoodsCnt"));
				dto.setHitCnt(rs.getInt("hitCnt"));
				dto.setWishCnt(rs.getInt("wishCnt"));
				dto.setReviewCnt(rs.getInt("reviewCnt"));
				dto.setDelFl(rs.getBoolean("delFl"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDelDt(rs.getTimestamp("delDt"));
				dto.setCouponCd(rs.getString("couponCd"));
				dto.setIcon(rs.getString("icon"));
				
				list.add(dto);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	//	리스트 가져오기
	
	public ArrayList<goodsDTO> getGoodsList(String scmNo, String dateCate, String startDate, String endDate, 
			String cateCd, String state, String saleState, String keywordType, String keyword){
		
		String sql = "select * from goods where ";
		
		ArrayList<String> condition = new ArrayList<>();
		
		String con = "scmNo=" + scmNo;
		if(!dateCate.equals("")){
			con += " and "+ dateCate + "<'" + endDate + "'";
		}
		if(!startDate.equals("")){
			con += " and "+dateCate+">'"+startDate+"'";
		}
		if(!cateCd.equals("")){
			con += " and cateCd='"+cateCd+"'";
		}
		if(!keywordType.equals("") && !keyword.equals("")){
			con += " and "+keywordType+" like '%"+keyword+"%'";
		}
		if(!saleState.equals("")){
			con+=" and totalStock=0";
		}
		if(!state.equals("")){
			String[] arr = state.split(",");
			for(String str : arr){
				if(str.equals("apply")){
					String con1 = con;
					con1+=" and applyFl='a'";
					condition.add(con1);
				}
				if(str.equals("wait")){
					String con1 = con;
					con+=" and goodsSellFl=0";
					condition.add(con1);
				}
				if(str.equals("adminOff")){
					String con1 = con;
					con+=" and goodsSellFl=-1";
					condition.add(con1);
				}
				if(str.equals("saleOff")){
					String con1 = con;
					con+=" and salesEndDt<now()";
					condition.add(con1);
				}
			}
			
		}
		
		if(condition.size()>0){
			con = "";
			
			for(String str : condition){
				con += " or " + str;
			}
			
			con = con.substring(3);
		}
		
		sql += con;
		sql += " order by regDt desc";
		System.out.println("sql : "+sql);
		
		ArrayList<goodsDTO> list = SearchList(sql);
		
		return list;
	}
	
	
	//관리자 구매후기 전체 검색
	public List<reviewDTO> reviewListSearch3(int startRow, int endRow) {
		ArrayList<reviewDTO> rw = new ArrayList<reviewDTO>();
		String sql = "select * from review where isDelete = 0 order by regDt desc limit ?, ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				reviewDTO rr = new reviewDTO();
				rr.setSno(rs.getInt("sno"));
				rr.setOrderNo(rs.getInt("orderNo"));
				rr.setGoodsNo(rs.getInt("goodsNo"));
				rr.setTitle(rs.getString("title"));
				rr.setMemNo(rs.getInt("memNo"));
				rr.setWriter(rs.getString("writer"));
				rr.setIp(rs.getString("ip"));
				rr.setPrivateFl(rs.getBoolean("privateFl"));
				rr.setContents(rs.getString("contents"));
				rr.setGoodsPt(rs.getInt("goodsPt"));
				rr.setReviewImg(rs.getString("reviewImg"));
				rr.setAddMileageFl(rs.getBoolean("addMileageFl"));
				rr.setRegDt(rs.getTimestamp("regDt"));
				rr.setViewCnt(rs.getInt("viewCnt"));
				rw.add(rr);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return rw;
	}

	public int reviewCount() {
		String sql = "select count(*) from review where isDelete = 0";
		int count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return count;
	}
	
	//상품 구매후기 조건별검색
	public List<reviewDTO> reviewSearchResult(int goodsPt, int potoFl, String kind, String keyword, String gNo3,
			int startRow, int endRow) {
		String sql = "select * from review where isDelete = 0";
				
		String con = "";
		
		if(goodsPt > 0){
			if(goodsPt > 5) {
				con += " and goodsPt"+ "<" + goodsPt + "";
			}else {				
				con += " and goodsPt"+ "=" + goodsPt + "";
			}
		}

		if(potoFl > 0){
			if(potoFl == 1){				
				String poto = "<img";
				con += " and reviewImg like '%"+poto+"%'";
			}
		}
		
		if(!kind.equals("")){
			if(kind.equals("상품명") || kind.equals("공급사명")) {
				con += " and goodsNo in ("+gNo3+")";
			}else if(kind.equals("작성자")) {				
				con += " and writer = '"+keyword+"'";
			}else if(kind.equals("제목")) {				
				con += " and title like '%"+keyword+"%'";
			}
			
		}
		
		sql += con + " order by regDt desc limit "+startRow+" , "+endRow+"";

		System.out.println("sql = "+sql);
		
		ArrayList<reviewDTO> list = reviewSearchListSql(sql);
		
		return list;
	}
		
	private ArrayList<reviewDTO> reviewSearchListSql(String sql) {
		ArrayList<reviewDTO> list = new ArrayList<reviewDTO>();
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				reviewDTO rr = new reviewDTO();
				rr.setSno(rs.getInt("sno"));
				rr.setOrderNo(rs.getInt("orderNo"));
				rr.setGoodsNo(rs.getInt("goodsNo"));
				rr.setTitle(rs.getString("title"));
				rr.setMemNo(rs.getInt("memNo"));
				rr.setWriter(rs.getString("writer"));
				rr.setIp(rs.getString("ip"));
				rr.setPrivateFl(rs.getBoolean("privateFl"));
				rr.setContents(rs.getString("contents"));
				rr.setGoodsPt(rs.getInt("goodsPt"));
				rr.setReviewImg(rs.getString("reviewImg"));
				rr.setAddMileageFl(rs.getBoolean("addMileageFl"));
				rr.setRegDt(rs.getTimestamp("regDt"));
				rr.setViewCnt(rs.getInt("viewCnt"));
				list.add(rr);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}
	
	public int reviewSearchResultCount(int goodsPt, int potoFl, String kind, String keyword, String gNo3) {
		String sql = "select count(*) from review where isDelete = 0";
		
		String con = "";
		
		if(goodsPt > 0){
			if(goodsPt > 5) {
				con += " and goodsPt"+ "<" + goodsPt + "";
			}else {				
				con += " and goodsPt"+ "=" + goodsPt + "";
			}
		}

		if(potoFl > 0){
			if(potoFl == 1){				
				String poto = "<img";
				con += " and reviewImg like '%"+poto+"%'";
			}
		}
		
		if(!kind.equals("")){
			if(kind.equals("상품명") || kind.equals("공급사명")) {
				con += " and goodsNo in ("+gNo3+")";
			}else if(kind.equals("작성자")) {				
				con += " and writer = '"+keyword+"'";
			}else if(kind.equals("제목")) {				
				con += " and title like '%"+keyword+"%'";
			}
			
		}
		
		sql += con;

		System.out.println("sql = "+sql);
		
		int count = searchGoodsListCountSQL(sql);
		
		return count;
	}
	
	
	//상품 구매후기 조건별 검색 end
	
	
	
	

	//상품리스트 조건별 검색 start
	public List<goodsDTO> searchGoodsList(String kind, String keyword, String minDate, String maxDate, String cateCd,
			String brand, int minPrice, int maxPrice, String goodsStatus, int startRow, int endRow, String search) {
		
		String sql = "select * from goods where delFl = 0";
		
		if(!goodsStatus.equals("")) {
			if(goodsStatus.equals("품절")) {
				sql = "select * from goods where totalStock = 0";
			}else if(goodsStatus.equals("삭제")) {
				sql = "select * from goods where delFl = 1";
			}else if(goodsStatus.equals("정상")) {
				sql = "select * from goods where delFl = 0 and goodsSellFl = 0";
			}		
		}
		
		String con = "";

		
		if(!kind.equals("")){
			if(kind.equals("상품명")) {
				con += " and goodsNm like '%"+keyword+"%'";
			}else if(kind.equals("상품코드")) {				
				con += " and goodsNo = "+keyword+"";
			}else if(kind.equals("공급사명")) {
				int scmNo = scmNo(keyword);
				con += " and scmNo = "+scmNo+"";
			}else if(kind.equals("전체")) {
				int scmNo = scmNo(keyword);
				con += " and (goodsNm like '%"+keyword+"%' or goodsNo like '%"+keyword+"%' or scmNo = "+scmNo+" or keyword like '%"+keyword+"%')";
			}else if(kind.equals("키워드")) {
				con +=" and keyword like '%"+keyword+"%'";
			}
			
		}
		if(!minDate.equals("")) {
			con += " and regDt >= '"+minDate+"'";
		}
		
		if(!maxDate.equals("") && !maxDate.equals("00:00:00")) {
			con += " and regDt <= '"+maxDate+"'";
		}
		
		if(!cateCd.equals("")) {
			con += " and cateCd = '"+cateCd+"'";
		}
		
		if(!brand.equals("")) {
			con += " and brandCd = '"+brand+"'";
		}
		
		if(minPrice >= 0) {
			con += " and fixedPrice >= "+minPrice+"";
		}
		
		if(maxPrice > 1) {
			con += " and fixedPrice <= "+maxPrice+"";
		}
		
		sql += con + " "+search+" limit "+startRow+" , "+endRow+"";

		System.out.println("sql = "+sql);
		
		ArrayList<goodsDTO> list = searchGoodsListSQL(sql);
		
		return list;
	}
	
	private ArrayList<goodsDTO> searchGoodsListSQL(String sql) {
		ArrayList<goodsDTO> list = new ArrayList<goodsDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				goodsDTO dto = new goodsDTO();
				
				dto.setGoodsNo(rs.getInt("goodsNo"));
				dto.setGoodsNm(rs.getString("goodsNm"));
				dto.setGoodsSellFl(rs.getInt("goodsSellFl"));
				dto.setScmNo(rs.getInt("scmNo"));
				dto.setApplyFl(rs.getString("applyFl"));
				dto.setApplyMsg(rs.getString("applyMsg"));
				dto.setApplyDt(rs.getTimestamp("applyDt"));
				dto.setSalesStartDt(rs.getTimestamp("salesStartDt"));
				dto.setSalesEndDt((rs.getTimestamp("salesEndDt")));
				dto.setCateCd(rs.getString("cateCd"));
				dto.setBrandCd(rs.getString("brandCd"));
				dto.setMakerNm(rs.getString("makerNm"));
				dto.setKeyword(rs.getString("keyword"));
				dto.setCommission(rs.getBigDecimal("commission"));
				dto.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				dto.setFixedPrice(rs.getBigDecimal("fixedPrice"));
				dto.setDiscountPercent(rs.getBigDecimal("DiscountPercent"));
				dto.setTotalStock(rs.getInt("totalStock"));
				dto.setDiscountInfo(rs.getString("discountInfo"));
				dto.setPeriodDiscountStart(rs.getTimestamp("periodDiscountStart"));
				dto.setPeriodDiscountEnd(rs.getTimestamp("periodDiscountEnd"));
				dto.setGoodsDiscountFl(rs.getBoolean("goodsDiscountFl"));
				dto.setGoodsDiscountType(rs.getBoolean("goodsDiscountType"));
				dto.setGoodsDiscountPercent(rs.getBigDecimal("goodsDiscountPercent"));
				dto.setGoodsDiscountPrice(rs.getBigDecimal("goodsDiscountPrice"));
				dto.setCostPrice(rs.getBigDecimal("costPrice"));
				dto.setOriginNm(rs.getString("originNm"));
				dto.setGoodsMustInfo(rs.getString("goodsMustInfo"));
				dto.setKcmarkInfo(rs.getString("kcmarkInfo"));
				dto.setOnlyAdultFl(rs.getBoolean("onlyAdultFl"));
				dto.setTaxFreeFl(rs.getString("taxFreeFl"));
				dto.setTaxPercent(rs.getBigDecimal("taxPercent"));
				dto.setGoodsWeight(rs.getBigDecimal("goodsWeight"));
				dto.setFixedSales(rs.getString("fixedSales"));
				dto.setFixedOrderCnt(rs.getString("fixedOrderCnt"));
				dto.setSalesUnit(rs.getInt("salesUnit"));
				dto.setMinOrderCnt(rs.getInt("minOrderCnt"));
				dto.setMaxOrderCnt(rs.getInt("maxOrderCnt"));
				dto.setRestockFl(rs.getBoolean("restockFl"));
				dto.setRepresentImg(rs.getString("representImg"));
				dto.setSubImg(rs.getString("subImg"));
				dto.setShortDescription(rs.getString("shortDescription"));
				dto.setEventDescription(rs.getString("eventDescription"));
				dto.setGoodsDescription(rs.getString("goodsDescription"));
				dto.setShipmentZonecode(rs.getString("shipmentZonecode"));
				dto.setShipmentAddress(rs.getString("shipmentAddress"));
				dto.setShipmentAddressSub(rs.getString("shipmentAddressSub"));
				dto.setRecoveryZonecode(rs.getString("recoveryZonecode"));
				dto.setRecoveryAddress(rs.getString("recoveryAddress"));
				dto.setRecoveryAddressSub(rs.getString("recoveryAddressSub"));
				dto.setDeliveryCompany(rs.getString("deliveryCompany"));
				dto.setDeliveryType(rs.getString("deliveryType"));
				dto.setDeliveryWay(rs.getString("deliveryWay"));
				dto.setDeliveryKind(rs.getString("deliveryKind"));
				dto.setDeliveryFreeCondition(rs.getBigDecimal("deliveryFreeCondition"));
				dto.setDeliveryCost(rs.getBigDecimal("deliveryCost"));
				dto.setDeliveryArea(rs.getString("deliveryArea"));
				dto.setDeliveryCostAddJeju(rs.getBigDecimal("deliveryCostAddJeju"));
				dto.setDeliveryCostAdd(rs.getBigDecimal("deliveryCostAdd"));
				dto.setDeliveryRefundCost(rs.getBigDecimal("deliveryRefundCost"));
				dto.setRelationGoodsNo(rs.getString("relationGoodsNo"));
				dto.setDetailInfoDelivery(rs.getString("detailInfoDelivery"));
				dto.setDetailInfoAS(rs.getString("detailInfoAS"));
				dto.setDetailInfoRefund(rs.getString("detailInfoRefund"));
				dto.setDetailInfoExchange(rs.getString("detailInfoExchange"));
				dto.setOrderCnt(rs.getInt("orderCnt"));
				dto.setOrderGoodsCnt(rs.getInt("orderGoodsCnt"));
				dto.setHitCnt(rs.getInt("hitCnt"));
				dto.setWishCnt(rs.getInt("wishCnt"));
				dto.setReviewCnt(rs.getInt("reviewCnt"));
				dto.setCouponCd(rs.getString("couponCd"));
				dto.setIcon(rs.getString("icon"));
				dto.setDelFl(rs.getBoolean("delFl"));
				dto.setAdminMsg(rs.getString("adminMsg"));
				dto.setRegDt(rs.getTimestamp("regDt"));
				dto.setModDt(rs.getTimestamp("modDt"));
				dto.setDelDt(rs.getTimestamp("delDt"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}

	//공급사 번호 얻기
	private int scmNo(String keyword) {
		int scmNo = 0;
		String sql = "select scmNo from scm where companyNm = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				scmNo = rs.getInt("scmNo");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return scmNo;
	}
	
	
	//조건별 상품리스트 갯수
	public int searchGoodsListCount(String kind, String keyword, String minDate, String maxDate, String cateCd,
			String brand, int minPrice, int maxPrice, String goodsStatus) {
		
		String sql = "select count(*) from goods where delFl = 0";
		
		if(!goodsStatus.equals("")) {
			if(goodsStatus.equals("품절")) {
				sql = "select count(*) from goods where totalStock = 0";
			}else if(goodsStatus.equals("삭제")) {
				sql = "select count(*) from goods where delFl = 1";
			}else if(goodsStatus.equals("정상")) {
				sql = "select count(*) from goods where delFl = 0 and goodsSellFl = 0";
			}		
		}
		
		String con = "";

		
		if(!kind.equals("")){
			if(kind.equals("상품명")) {
				con += " and goodsNm like '%"+keyword+"%'";
			}else if(kind.equals("상품코드")) {				
				con += " and goodsNo = "+keyword+"";
			}else if(kind.equals("공급사명")) {
				int scmNo = scmNo(keyword);
				con += " and scmNo = "+scmNo+"";
			}else if(kind.equals("전체")) {
				int scmNo = scmNo(keyword);
				con += " and (goodsNm like '%"+keyword+"%' or goodsNo like '%"+keyword+"%' or scmNo = "+scmNo+" or keyword like '%"+keyword+"%')";
			}else if(kind.equals("키워드")) {
				con +=" and keyword like '%"+keyword+"%'";
			}
			
		}
		if(!minDate.equals("")) {
			con += " and regDt >= '"+minDate+"'";
		}
		
		if(!maxDate.equals("") && !maxDate.equals("00:00:00")) {
			con += " and regDt <= '"+maxDate+"'";
		}
		
		if(!cateCd.equals("")) {
			con += " and cateCd = '"+cateCd+"'";
		}
		
		if(!brand.equals("")) {
			con += " and brandCd = '"+brand+"'";
		}
		
		if(minPrice >= 0) {
			con += " and fixedPrice >= "+minPrice+"";
		}
		
		if(maxPrice > 0) {
			con += " and fixedPrice <= "+maxPrice+"";
		}
		
		sql += con;
		
		System.out.println("countSql = "+sql);
		
		int count = searchGoodsListCountSQL(sql);
		
		return count;
	}

	private int searchGoodsListCountSQL(String sql) {
		int count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return count;
	}
	
	//상품리스트 조건별검색 end
}
