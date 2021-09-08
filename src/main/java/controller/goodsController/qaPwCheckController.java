package controller.goodsController;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;

import controller.goodsController.goodsQAController.DataEncrypt;
import service.goodsService.goodsService;

/**
 * Servlet implementation class qaPwCheckController
 */
@WebServlet("/qaPwCheck")
public class qaPwCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		goodsService gd = new goodsService();
				
		String qw = null;
		String qw2 = null;
		if(request.getParameter("pw") != null) {
			qw = request.getParameter("pw");
			DataEncrypt sha256Enc 	= new DataEncrypt();
			qw2 = sha256Enc.encrypt(qw);	
		}
		String qaPw = gd.qaPwCheck(qw2);
				
		PrintWriter out = response.getWriter();
		out.print(qaPw);
		
		
	}
	
	public class DataEncrypt{
		MessageDigest md;
		String strSRCData = "";
		String strENCData = "";
		String strOUTData = "";
		
		public DataEncrypt(){ }
		public String encrypt(String writerPw){
			String passACL = null;
			MessageDigest md = null;
			try{
				md = MessageDigest.getInstance("SHA-256");
				md.reset();
				md.update(writerPw.getBytes());
				byte[] raw = md.digest();
				passACL = encodeHex(raw);
			}catch(Exception e){
				System.out.print("암호화 에러" + e.toString());
			}
			return passACL;
		}
		
		public String encodeHex(byte [] b){
			char [] c = Hex.encodeHex(b);
			return new String(c);
		}
	}
	
	
}
