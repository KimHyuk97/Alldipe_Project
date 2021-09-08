package DAO.partners.depositDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.partners.deposit.depositDTO;

public class depositDAO {

	private static depositDAO bdao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static depositDAO getInstance(){
		if(bdao == null){
			bdao = new depositDAO();
		}
		return bdao;
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	
	
	
	
	
}
