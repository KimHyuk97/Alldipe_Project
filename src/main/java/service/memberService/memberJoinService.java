package service.memberService;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.memberDAO.memberDAO;

public class memberJoinService {

	public int memberJoin(String memId, String memPw, String memName, String gender, String email, String cellphone,
			String zonecode, String address, String addressSub, String birthDt, String recommId, int privateUtilizationFl, int privateApprovalFl, int privateFinanceFl, int privateOfferFl, int under14ConsentFl, String time1) {
		
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		
		int join  = dao.memberJoin(memId,memPw,memName,gender,email,cellphone,zonecode,address,addressSub,birthDt,recommId,privateUtilizationFl,privateApprovalFl,privateFinanceFl,privateOfferFl,under14ConsentFl,time1);
		
		if(join > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return join;
	}
	
	//배송지 설정
	public int memberDelivery(String memId, String zonecode, String address, String addressSub, String time1) {
		memberDAO dao = memberDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		
		int mNo  = dao.memberDelivery(memId);
		
		int de = 0;
		
		if(mNo > 0) {
			de = dao.memberDeliveryAdd(mNo,zonecode,address,addressSub,time1);
			
				if(de > 0) {
					commit(con);
				}else{
					rollback(con);
				}

		}
		
		close(con);
		
		return de;
	}



}
