package service.cartService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import DAO.cartDAO.cartDAO;
import dto.cartDTO.cartDTO;

public class cartService {
	
	//장바구니 불러오기
	public List<cartDTO> cart(String siteKey, int memNo) {
		cartDAO dao = cartDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		List<cartDTO> cd = dao.cart(memNo,siteKey);
		
		close(con);
		
		return cd;
	}
	
	public ArrayList<cartDTO> getList(String[] arr){
		ArrayList<cartDTO> list = new ArrayList<>();
		
		cartDAO dao = cartDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		list = dao.getList(arr);
		
		close(con);
		
		return list;
	}
	
	//장바구니 상품 중복 검사
	public String cartSelect(int memNo, int goodsNo, int optionNo, String siteKey, String goodsOptionNm) {
		cartDAO dao = cartDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		String cartSelect = dao.cartSelect(memNo,goodsNo,optionNo,siteKey,goodsOptionNm);
		
		close(con);
		
		return cartSelect;
	}
	
	//장바구니 담기
	public int cartAdd(cartDTO dto) {
		cartDAO dao = cartDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int cartAdd = dao.cartAdd(dto);
		
		if(cartAdd > 0) {
			commit(con);			
		} else {
			rollback(con);
		}
		
		close(con);
		
		
		return cartAdd;
	}
	
	//로그인하려는 회원에게 로그인 전에 담았던 상품들 넘겨주기
	public void memberCartAdd(String siteKey, int memNo) {
		cartDAO dao = cartDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int cartAdd = dao.membercartAdd(siteKey,memNo);
		
		if(cartAdd > 0) {
			commit(con);			
		} else {
			rollback(con);
		}
		
		close(con);
		
	}	
	
	//장바구니 상품 삭제
	public int cartDelete(int sno) {
		cartDAO dao = cartDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int cd = dao.cartDelete(sno);
		
		if(cd > 0) {
			commit(con);			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return cd;
	}
	
	public int setCartCnt(String[] list){
		
		cartDAO dao = cartDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int chk = 0;
		
		for(String str : list){
			String[] arr = str.split(",");
			chk = dao.setCartCnt(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
			if(chk<1){
				break;
			}
		}
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
		
	}
		
}
