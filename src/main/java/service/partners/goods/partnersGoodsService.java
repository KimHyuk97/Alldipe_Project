package service.partners.goods;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.partners.goods.goodsDAO;
import DAO.tempDAO.tempGoodsDAO;
import dto.goodsDTO.goodsDTO;
import dto.goodsDTO.goodsOptionDTO;

public class partnersGoodsService {

	public int getGoodsNo(){
		
		Connection con = getConnection();
		
		goodsDAO dao = goodsDAO.getInstance();
		
		dao.setConnection(con);
		
		int no = dao.getGoodsNo();
		
		close(con);
		
		return no+1;
		
	}
	
	public int addGoods(goodsDTO dto){
		
		int chk = 0;
		
		Connection con = getConnection();
		
		goodsDAO dao = goodsDAO.getInstance();
		dao.setConnection(con);
		
		chk = dao.insertGoods(dto);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	
	public int addGoodsOption(ArrayList<goodsOptionDTO> list){
		int chk = 0;
		
		Connection con = getConnection();
		goodsDAO dao = goodsDAO.getInstance();
		dao.setConnection(con);
		
		for(goodsOptionDTO dto : list){
			chk = dao.insertGoodsOption(dto);
			
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
	
	
//	임시저장 관련 
	//	상품정보 임시저장
	public int addTempGoods(goodsDTO dto){
		
		int chk = 0;
		
		Connection con = getConnection();
		
		tempGoodsDAO dao = tempGoodsDAO.getInstance();
		dao.setConnection(con);
		
		chk = dao.insertGoodsTemp(dto);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	
	public int addOptionTemp(ArrayList<goodsOptionDTO> list){
		int chk = 0;
		Connection con = getConnection();
		tempGoodsDAO dao = tempGoodsDAO.getInstance();
		dao.setConnection(con);
		
		for(goodsOptionDTO dto : list){
			chk = dao.insertOptionTemp(dto);
			if(chk<1){
				System.out.println("오류 및 종료!");
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
	
	public boolean isGoodsTemp(int scmNo){
		
		Connection con = getConnection();
		tempGoodsDAO dao = tempGoodsDAO.getInstance();
		dao.setConnection(con);
		
		boolean flag = dao.isGoodsTemp(scmNo);
		
		close(con);
		
		//	임시저장 데이터가 있으면 true, 없으면 false
		return flag;
	}
	
	//	 임시저장 데이터 가져오기
	public goodsDTO getGoodsTemp(int scm){
		
		tempGoodsDAO dao = tempGoodsDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		goodsDTO dto = dao.getTempGoods(scm);
		
		close(con);
		
		return dto;
	}
	
	//	임시저장 옵션 가져오기
	public ArrayList<goodsOptionDTO> getOptionTemp(int scmNo){
		
		ArrayList<goodsOptionDTO> list = new ArrayList<>();
		
		Connection con = getConnection();		
		tempGoodsDAO dao = tempGoodsDAO.getInstance();
		
		dao.setConnection(con);
		list = dao.getTempOption(scmNo);
		
		close(con);
		
		return list;
	}
	
	//	임시데이터 삭제 서비스
	public int deleteTempGoods(int scmNo){
		
		Connection con = getConnection();
		tempGoodsDAO dao = tempGoodsDAO.getInstance();
		dao.setConnection(con);
		
		int chk = dao.deleteGoodsTemp(scmNo);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	
	public int deleteOptionTemp(int scmNo){
		
		Connection con = getConnection();
		tempGoodsDAO dao = tempGoodsDAO.getInstance();
		dao.setConnection(con);
		
		int chk = dao.deleteOptionTemp(scmNo);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	
//	상품 수정
	
	public int updateGoods(goodsDTO dto){
		
		int chk = 0;
		
		Connection con = getConnection();
		
		goodsDAO dao = goodsDAO.getInstance();
		dao.setConnection(con);
		
		System.out.println("업데이트 정보 : " + dto.toString());
		
		chk = dao.updateGoods(dto);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		System.out.println(chk);
		
		return chk;
	}
	
}
