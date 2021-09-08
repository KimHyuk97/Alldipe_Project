package service.order;

import java.sql.Connection;

import DAO.orderDAO.orderLogDAO;
import dto.orderDTO.orderLogDTO;
import static db.JdbcUtil.*;

public class orderLogService {

	public int insertLog(orderLogDTO dto){
		int chk = 0;
		
		orderLogDAO dao = orderLogDAO.getInstance();
		Connection con = getConnection();
		
		dao.setConnection(con);
		
		chk = dao.insertLog(dto);
		
		if(chk>0){
			commit(con);
		}else{
			rollback(con);
		}
		
		close(con);
		
		return chk;
	}
	
}
