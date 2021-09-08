package service.partners.user;

import java.sql.Connection;
import DAO.memberDAO.memberDAO;
import DAO.partnerDAO.partnerDAO;
import dto.memberDTO.memberDTO;

import static db.JdbcUtil.*;

public class partnersUserService {

	public int partnersLoginService(String id, String pw){
		
		int chk = -1;
		
		Connection con = getConnection();
		memberDAO mdao = memberDAO.getInstance();
		mdao.setConnection(con);
		
		memberDTO mdto = mdao.memberLogin(id, pw);
		
		if(mdto.getMemNo()>0){
			//	올디프 회원인 경우 => 파트너 유무 확인
			partnerDAO pdao = partnerDAO.getInstance();
			pdao.setConnection(con);
			mdto.setPartnersFl(pdao.memberPartnersFl(mdto.getMemNo()));
			
			System.out.println("partner Login Service memNo :" + mdto.getMemNo());
			System.out.println("partnerFl : " + mdto.isPartnersFl());
			System.out.println("member : " + mdto.toString());
			if(mdto.isPartnersFl()){
				//	파트너 회원인 경우\
				chk = mdto.getMemNo();
				
			}else{
				//	파트너 회원이 아닌 경우
				chk = 0;
			}
			
		}else{
			//	올디프 회원이 아닌 경우 => 회원가입 페이지로 가야지
			chk = -1;
		}
		close(con);
		
		return chk;
		
	}
	
	public int agreeService(int memNo){
		
		int chk = 0;
		
		Connection con = getConnection();
		
		memberDAO dao = memberDAO.getInstance();
		dao.setConnection(con);
		
		chk = dao.updatePartnersFl(memNo);
		System.out.println("partners Agree : " + chk);
		
		partnerDAO pdao = partnerDAO.getInstance();
		pdao.setConnection(con);
		chk = pdao.addScm(memNo);
		System.out.println("scm add : " + chk);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	
}
