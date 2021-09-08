package DAO.cartDAO;

import static db.JdbcUtil.close;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.cartDTO.cartDTO;



public class cartDAO {
	private static cartDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;


	public static cartDAO getInstance() {
		if (dao == null) {
			dao = new cartDAO();
		}
		return dao;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	//장바구니 불러오기
	public List<cartDTO> cart(int memNo, String siteKey) {
		List<cartDTO> lcd = new ArrayList<cartDTO>();
		if(memNo == 0) {
			String sql = "SELECT * FROM cart WHERE memNo = ? and siteKey = ?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, memNo);
				pstmt.setString(2, siteKey);
				rs=pstmt.executeQuery();
				
				
				while(rs.next()) {
					cartDTO cd  = new cartDTO();
					cd.setSno(rs.getInt("sno"));
					cd.setSiteKey(rs.getString("siteKey"));
					cd.setMemNo(rs.getInt("memNo"));
					cd.setScmNo(rs.getInt("scmNo"));
					cd.setGoodsNo(rs.getInt("goodsNo"));
					cd.setOptionNo(rs.getInt("optionNo"));
					cd.setGoodsCnt(rs.getInt("goodsCnt"));
					cd.setGoodsOptionNm(rs.getString("goodsOptionNm"));
					cd.setTmpOrderNo(rs.getString("tmpOrderNo"));
					cd.setUseBundleGoods(rs.getInt("useBundleGoods"));
					cd.setLinkMainTheme(rs.getString("linkMainTheme"));
					cd.setGoodsNm(rs.getString("goodsNm"));
					cd.setMakerNm(rs.getString("makerNm"));
					cd.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
					cd.setOptionPrice(rs.getBigDecimal("optionPrice"));
					cd.setSumPrice(rs.getBigDecimal("sumPrice"));
					cd.setTotalStock(rs.getInt("totalStock"));
					cd.setRepresentImg(rs.getString("representImg"));
					cd.setDeliveryCost(rs.getBigDecimal("deliveryCost"));
					cd.setDeliveryCostAddJeju(rs.getBigDecimal("deliveryCostAddJeju"));
					cd.setDeliveryCostAdd(rs.getBigDecimal("deliveryCostAdd"));
					cd.setRegDt(rs.getTimestamp("regDt"));
					cd.setModDt(rs.getTimestamp("modDt"));
					lcd.add(cd);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}			
		}else {
			String sql = "SELECT * FROM cart WHERE memNo = ?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, memNo);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					cartDTO cd  = new cartDTO();
					cd.setSno(rs.getInt("sno"));
					cd.setSiteKey(rs.getString("siteKey"));
					cd.setMemNo(rs.getInt("memNo"));
					cd.setScmNo(rs.getInt("scmNo"));
					cd.setGoodsNo(rs.getInt("goodsNo"));
					cd.setOptionNo(rs.getInt("optionNo"));
					cd.setGoodsCnt(rs.getInt("goodsCnt"));
					cd.setGoodsOptionNm(rs.getString("goodsOptionNm"));
					cd.setTmpOrderNo(rs.getString("tmpOrderNo"));
					cd.setUseBundleGoods(rs.getInt("useBundleGoods"));
					cd.setLinkMainTheme(rs.getString("linkMainTheme"));
					cd.setGoodsNm(rs.getString("goodsNm"));
					cd.setMakerNm(rs.getString("makerNm"));
					cd.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
					cd.setOptionPrice(rs.getBigDecimal("optionPrice"));
					cd.setSumPrice(rs.getBigDecimal("sumPrice"));
					cd.setTotalStock(rs.getInt("totalStock"));
					cd.setRepresentImg(rs.getString("representImg"));
					cd.setDeliveryCost(rs.getBigDecimal("deliveryCost"));
					cd.setDeliveryCostAddJeju(rs.getBigDecimal("deliveryCostAddJeju"));
					cd.setDeliveryCostAdd(rs.getBigDecimal("deliveryCostAdd"));
					cd.setRegDt(rs.getTimestamp("regDt"));
					cd.setModDt(rs.getTimestamp("modDt"));
					lcd.add(cd);
				}
			}catch(SQLException se){
				se.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}	
			
		}
		
		
		return lcd;
		
	}
	
	public ArrayList<cartDTO> getList(String[] arr){
		
		ArrayList<cartDTO> list = new ArrayList<>();
		
		String sql = "select * from cart where sno in (";
		
		String condition = "";
		for(int i = 0; i<arr.length; i++){
			condition += ",?";
		}
		
		sql += condition.substring(1) + ")";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			
			for(int i = 0; i<arr.length; i++){
				pstmt.setInt(i+1, Integer.parseInt(arr[i]));
			}
			
			rs = pstmt.executeQuery();
			
			list = list(rs);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	private ArrayList<cartDTO> list(ResultSet rs){
		ArrayList<cartDTO> list = new ArrayList<>();
		
		try{
			while(rs.next()) {
				cartDTO cd  = new cartDTO();
				cd.setSno(rs.getInt("sno"));
				cd.setSiteKey(rs.getString("siteKey"));
				cd.setMemNo(rs.getInt("memNo"));
				cd.setScmNo(rs.getInt("scmNo"));
				cd.setGoodsNo(rs.getInt("goodsNo"));
				cd.setOptionNo(rs.getInt("optionNo"));
				cd.setGoodsCnt(rs.getInt("goodsCnt"));
				cd.setGoodsOptionNm(rs.getString("goodsOptionNm"));
				cd.setTmpOrderNo(rs.getString("tmpOrderNo"));
				cd.setUseBundleGoods(rs.getInt("useBundleGoods"));
				cd.setLinkMainTheme(rs.getString("linkMainTheme"));
				cd.setGoodsNm(rs.getString("goodsNm"));
				cd.setMakerNm(rs.getString("makerNm"));
				cd.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				cd.setOptionPrice(rs.getBigDecimal("optionPrice"));
				cd.setSumPrice(rs.getBigDecimal("sumPrice"));
				cd.setTotalStock(rs.getInt("totalStock"));
				cd.setRepresentImg(rs.getString("representImg"));
				cd.setDeliveryCost(rs.getBigDecimal("deliveryCost"));
				cd.setDeliveryCostAddJeju(rs.getBigDecimal("deliveryCostAddJeju"));
				cd.setDeliveryCostAdd(rs.getBigDecimal("deliveryCostAdd"));
				cd.setRegDt(rs.getTimestamp("regDt"));
				cd.setModDt(rs.getTimestamp("modDt"));
	
				list.add(cd);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	

	//장바구니 담기
	public int cartAdd(cartDTO dto) {
		int ca = 0;
		String sql = "insert into cart(siteKey,memNo,scmNo,goodsNo,optionNo,goodsOptionNm,goodsCnt,goodsNm,makerNm,representImg,deliveryCost,regDt) values(?,?,?,?,?,?,?,?,?,?,?,now())"; 
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getSiteKey());
			pstmt.setInt(2, dto.getMemNo());
			pstmt.setInt(3, dto.getScmNo());
			pstmt.setInt(4, dto.getGoodsNo());
			pstmt.setInt(5, dto.getOptionNo());
			pstmt.setString(6, dto.getGoodsOptionNm());
			pstmt.setInt(7, dto.getGoodsCnt());
			pstmt.setString(8, dto.getGoodsNm());
			pstmt.setString(9, dto.getMakerNm());
			pstmt.setString(10, dto.getRepresentImg());
			pstmt.setBigDecimal(11, dto.getDeliveryCost());
			ca=pstmt.executeUpdate();
			
		}catch(SQLException se){
			se.printStackTrace();
		}
		
		return ca;
	}
	
	//상품중복검사
	public String cartSelect(int memNo, int goodsNo, int optionNo, String siteKey, String goodsOptionNm) {
		String cs = null;
		String sql = "select goodsNo from cart where memNo = ? and goodsNo = ? and optionNo = ? and goodsOptionNm = ? and siteKey = ?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, goodsNo);
			pstmt.setInt(3, optionNo);
			pstmt.setString(4, goodsOptionNm);
			pstmt.setString(5, siteKey);
			rs=pstmt.executeQuery();
			
			
			while(rs.next()) {
				cs = rs.getString(1);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return cs;
	}
	
	
	//로그인하려는 회원에게 로그인 전에 담았던 장바구니 상품들 넘겨주기
	public int membercartAdd(String siteKey, int memNo) {
		List<cartDTO> cd = new ArrayList<cartDTO>();		
		
		//로그인하기 전 상품의 정보들를 담음
		List<cartDTO> lc = new ArrayList<cartDTO>();
		
		//로그인 전 담았던 장바구니상품 조회
		String sql = "select * from cart where siteKey = ? and memNo = 0";
		int cs = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, siteKey);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				cartDTO ct = new cartDTO();
				ct.setSiteKey(rs.getString("siteKey"));
				ct.setMemNo(rs.getInt("memNo"));
				ct.setScmNo(rs.getInt("scmNo"));
				ct.setGoodsNo(rs.getInt("goodsNo"));
				ct.setOptionNo(rs.getInt("optionNo"));
				ct.setGoodsCnt(rs.getInt("goodsCnt"));
				ct.setGoodsOptionNm(rs.getString("goodsOptionNm"));
				ct.setTmpOrderNo(rs.getString("tmpOrderNo"));
				ct.setUseBundleGoods(rs.getInt("useBundleGoods"));
				ct.setLinkMainTheme(rs.getString("linkMainTheme"));
				ct.setGoodsNm(rs.getString("goodsNm"));
				ct.setMakerNm(rs.getString("makerNm"));
				ct.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
				ct.setOptionPrice(rs.getBigDecimal("optionPrice"));
				ct.setSumPrice(rs.getBigDecimal("sumPrice"));
				ct.setTotalStock(rs.getInt("totalStock"));
				ct.setRepresentImg(rs.getString("representImg"));
				ct.setDeliveryCost(rs.getBigDecimal("deliveryCost"));
				ct.setDeliveryCostAddJeju(rs.getBigDecimal("deliveryCostAddJeju"));
				ct.setDeliveryCostAdd(rs.getBigDecimal("deliveryCostAdd"));
				ct.setRegDt(rs.getTimestamp("regDt"));
				ct.setModDt(rs.getTimestamp("modDt"));
				cd.add(ct);
				//비교상품
				cartDTO lc2 = new cartDTO();
				lc2.setSiteKey(rs.getString("siteKey"));
				lc2.setGoodsNo(rs.getInt("goodsNo"));
				lc2.setOptionNo(rs.getInt("optionNo"));
				lc2.setGoodsOptionNm(rs.getString("goodsOptionNm"));
				lc.add(lc2);
			}
			System.out.println("로그인하긴 전 장바구니 상품cd-======"+cd);
			
		}catch(SQLException se){
			se.printStackTrace();
		}
		
		//로그인한 회원의 장바구니 상품 조회
		List<cartDTO> cd2 = new ArrayList<cartDTO>();		
		
		if(!cd.isEmpty()) {
			String sql2 = "select * from cart where memNo = ? and siteKey = ?";
						
			try {
				pstmt=con.prepareStatement(sql2);
				pstmt.setInt(1, memNo);
				pstmt.setString(2, siteKey);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {						
					cartDTO ct2 = new cartDTO();
					ct2.setSiteKey(rs.getString("siteKey"));
					ct2.setGoodsNo(rs.getInt("goodsNo"));
					ct2.setOptionNo(rs.getInt("optionNo"));		
					ct2.setGoodsOptionNm(rs.getString("goodsOptionNm"));
					cd2.add(ct2);
				}							
				
			}catch(SQLException se){
				se.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
		}
		System.out.println("로그인한 아이디의 장바구니 상품들 cd2=== "+ cd2);
		
		if(!cd2.isEmpty()) {	
			//cd에 들어있는 goodsNo과 optionNo을 회원이 가지고 있는지 조회한다 . 만약 들고 오면 중복상품이므로 그 상품들을 list<>로 저장
			String sql4 = "select * from cart where siteKey = ? and memNo= ? and goodsNo = ? and optionNo = ?";
			List<cartDTO> gs = new ArrayList<cartDTO>();
			try {
				for(int j = 0; j < cd.size(); j++) {
					pstmt = con.prepareStatement(sql4);
					pstmt.setString(1, siteKey);
					pstmt.setInt(2, memNo);
					pstmt.setInt(3, cd.get(j).getGoodsNo());
					pstmt.setInt(4, cd.get(j).getOptionNo());
					rs=pstmt.executeQuery();
					if(rs.next()) {
						cartDTO cz2 = new cartDTO();
						cz2.setMemNo(rs.getInt("memNo"));
						cz2.setGoodsNo(rs.getInt("goodsNo"));
						cz2.setOptionNo(rs.getInt("optionNo"));
						gs.add(cz2);
					}
				}
				//만약 중복상품이 있으면 
				if(!gs.isEmpty()) {
					//쿠키에 저장된 중복상품들을 삭제한다.
					int cs2 = 0;
					String sql5 = "delete from cart where memNo = 0 and siteKey =? and goodsNo = ? and optionNo = ?";
					for(int z = 0; z < gs.size(); z++) {
						pstmt = con.prepareStatement(sql5);
						pstmt.setString(1, siteKey);
						pstmt.setInt(2, gs.get(z).getGoodsNo());
						pstmt.setInt(3, gs.get(z).getOptionNo());
						cs2 = pstmt.executeUpdate();
					}
					if(cs2 > 0) {
						//만약 삭제가 되었다면 다시 쿠키에 저장된 상품들을 검색해서 가져온 후 list로 저장한다. 
						String sql7 = "select * from cart where memNo= 0 and siteKey = ?";
						List<cartDTO> gs2 = new ArrayList<cartDTO>();
						pstmt = con.prepareStatement(sql7);
						pstmt.setString(1, siteKey);
						rs=pstmt.executeQuery();
						while(rs.next()) {
							cartDTO cz3 = new cartDTO();
							cz3.setSiteKey(rs.getString("siteKey"));
							cz3.setMemNo(rs.getInt("memNo"));
							cz3.setScmNo(rs.getInt("scmNo"));
							cz3.setGoodsNo(rs.getInt("goodsNo"));
							cz3.setOptionNo(rs.getInt("optionNo"));
							cz3.setGoodsCnt(rs.getInt("goodsCnt"));
							cz3.setGoodsOptionNm(rs.getString("goodsOptionNm"));
							cz3.setTmpOrderNo(rs.getString("tmpOrderNo"));
							cz3.setUseBundleGoods(rs.getInt("useBundleGoods"));
							cz3.setLinkMainTheme(rs.getString("linkMainTheme"));
							cz3.setGoodsNm(rs.getString("goodsNm"));
							cz3.setMakerNm(rs.getString("makerNm"));
							cz3.setGoodsPrice(rs.getBigDecimal("goodsPrice"));
							cz3.setOptionPrice(rs.getBigDecimal("optionPrice"));
							cz3.setSumPrice(rs.getBigDecimal("sumPrice"));
							cz3.setTotalStock(rs.getInt("totalStock"));
							cz3.setRepresentImg(rs.getString("representImg"));
							cz3.setDeliveryCost(rs.getBigDecimal("deliveryCost"));
							cz3.setDeliveryCostAddJeju(rs.getBigDecimal("deliveryCostAddJeju"));
							cz3.setDeliveryCostAdd(rs.getBigDecimal("deliveryCostAdd"));
							cz3.setRegDt(rs.getTimestamp("regDt"));
							cz3.setModDt(rs.getTimestamp("modDt"));
							gs2.add(cz3);
						}
						if(!gs2.isEmpty()) {
							//만약 상품검색한 값이 있으면 회원 장바구니에 추가한다.
							String sql3 = "insert into cart(siteKey,memNo,scmNo,goodsNo,optionNo,goodsOptionNm,goodsCnt,goodsNm,makerNm,goodsPrice,optionPrice,sumPrice,totalStock,representImg,deliveryCost,deliveryCostAddJeju,deliveryCostAdd,regDt) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							try {
								for(int w = 0; w < gs2.size(); w++) {	
									pstmt=con.prepareStatement(sql3);
									pstmt.setString(1, siteKey);
									pstmt.setInt(2, memNo);
									pstmt.setInt(3, gs2.get(w).getScmNo());
									pstmt.setInt(4, gs2.get(w).getGoodsNo());
									pstmt.setInt(5, gs2.get(w).getOptionNo());
									pstmt.setString(6, gs2.get(w).getGoodsOptionNm());
									pstmt.setInt(7, gs2.get(w).getGoodsCnt());
									pstmt.setString(8, gs2.get(w).getGoodsNm());
									pstmt.setString(9, gs2.get(w).getMakerNm());
									pstmt.setBigDecimal(10, gs2.get(w).getGoodsPrice());
									pstmt.setBigDecimal(11, gs2.get(w).getOptionPrice());
									pstmt.setBigDecimal(12, gs2.get(w).getSumPrice());
									pstmt.setInt(13,gs2.get(w).getTotalStock());
									pstmt.setString(14, gs2.get(w).getRepresentImg());
									pstmt.setBigDecimal(15, gs2.get(w).getDeliveryCost());
									pstmt.setBigDecimal(16, gs2.get(w).getDeliveryCostAddJeju());
									pstmt.setBigDecimal(17, gs2.get(w).getDeliveryCostAdd());
									pstmt.setTimestamp(18, gs2.get(w).getRegDt());
									cs = pstmt.executeUpdate();						
								}
								if(cs>0) {
									//추가하면 쿠키에 있는 나머지 상품들을 지운다.
									String sql6 = "delete from cart where siteKey = ? and memNo = 0";
									pstmt=con.prepareStatement(sql6);
									pstmt.setString(1,siteKey);
									pstmt.executeUpdate();	
								}
							}catch(SQLException se){
								se.printStackTrace();
							}
						}
					}
				}else {
					//중복상품이 없으면 추가한다.
					String sql3 = "insert into cart(siteKey,memNo,scmNo,goodsNo,optionNo,goodsOptionNm,goodsCnt,goodsNm,makerNm,goodsPrice,optionPrice,sumPrice,totalStock,representImg,deliveryCost,deliveryCostAddJeju,deliveryCostAdd,regDt) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					try {
						for(int j = 0; j < cd.size(); j++) {	
							pstmt=con.prepareStatement(sql3);
							pstmt.setString(1, siteKey);
							pstmt.setInt(2, memNo);
							pstmt.setInt(3, cd.get(j).getScmNo());
							pstmt.setInt(4, cd.get(j).getGoodsNo());
							pstmt.setInt(5, cd.get(j).getOptionNo());
							pstmt.setString(6, cd.get(j).getGoodsOptionNm());
							pstmt.setInt(7, cd.get(j).getGoodsCnt());
							pstmt.setString(8, cd.get(j).getGoodsNm());
							pstmt.setString(9, cd.get(j).getMakerNm());
							pstmt.setBigDecimal(10, cd.get(j).getGoodsPrice());
							pstmt.setBigDecimal(11, cd.get(j).getOptionPrice());
							pstmt.setBigDecimal(12, cd.get(j).getSumPrice());
							pstmt.setInt(13,cd.get(j).getTotalStock());
							pstmt.setString(14, cd.get(j).getRepresentImg());
							pstmt.setBigDecimal(15, cd.get(j).getDeliveryCost());
							pstmt.setBigDecimal(16, cd.get(j).getDeliveryCostAddJeju());
							pstmt.setBigDecimal(17, cd.get(j).getDeliveryCostAdd());
							pstmt.setTimestamp(18, cd.get(j).getRegDt());
							cs = pstmt.executeUpdate();						
						}
						if(cs>0) {
							String sql9 = "delete from cart where siteKey = ? and memNo = 0";
							pstmt=con.prepareStatement(sql9);
							pstmt.setString(1,siteKey);
							pstmt.executeUpdate();	
						}
					}catch(SQLException se){
						se.printStackTrace();
					}	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	
		}else {
			System.out.println("로그인한 아이디에 상품이 없으면 여기");
			String sql3 = "insert into cart(siteKey,memNo,scmNo,goodsNo,optionNo,goodsOptionNm,goodsCnt,goodsNm,makerNm,goodsPrice,optionPrice,sumPrice,totalStock,representImg,deliveryCost,deliveryCostAddJeju,deliveryCostAdd,regDt) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			try {
				for(int j = 0; j < cd.size(); j++) {	
					pstmt=con.prepareStatement(sql3);
					pstmt.setString(1, siteKey);
					pstmt.setInt(2, memNo);
					pstmt.setInt(3, cd.get(j).getScmNo());
					pstmt.setInt(4, cd.get(j).getGoodsNo());
					pstmt.setInt(5, cd.get(j).getOptionNo());
					pstmt.setString(6, cd.get(j).getGoodsOptionNm());
					pstmt.setInt(7, cd.get(j).getGoodsCnt());
					pstmt.setString(8, cd.get(j).getGoodsNm());
					pstmt.setString(9, cd.get(j).getMakerNm());
					pstmt.setBigDecimal(10, cd.get(j).getGoodsPrice());
					pstmt.setBigDecimal(11, cd.get(j).getOptionPrice());
					pstmt.setBigDecimal(12, cd.get(j).getSumPrice());
					pstmt.setInt(13,cd.get(j).getTotalStock());
					pstmt.setString(14, cd.get(j).getRepresentImg());
					pstmt.setBigDecimal(15, cd.get(j).getDeliveryCost());
					pstmt.setBigDecimal(16, cd.get(j).getDeliveryCostAddJeju());
					pstmt.setBigDecimal(17, cd.get(j).getDeliveryCostAdd());
					pstmt.setTimestamp(18, cd.get(j).getRegDt());
					cs = pstmt.executeUpdate();	
				}
				if(cs>0) {
					String sql4 = "delete from cart where siteKey = ? and memNo = 0";
					pstmt=con.prepareStatement(sql4);
					pstmt.setString(1,siteKey);
					pstmt.executeUpdate();	
				}
			}catch(SQLException se){
				se.printStackTrace();
			}	
		}
		
	
		
		return cs;
		
	}

	//장바구니 삭제
	public int cartDelete(int sno) {
		int cd = 0;
		String sql = "delete from cart where sno=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sno);
			cd = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cd;
	}
	
	public int setCartCnt(int sno, int cnt){
		int chk = 0;
		
		String sql = "update cart set goodsCnt=? where sno=?";
		
		try{
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, cnt);
			pstmt.setInt(2, sno);
			
			chk = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return chk;
	}
	

}
