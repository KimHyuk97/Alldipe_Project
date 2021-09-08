package controller.goodsController;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;

import service.goodsService.goodsService;

/**
 * Servlet implementation class goodsQAController
 */
@WebServlet("/goodsQAWrite")
public class goodsQAController extends HttpServlet {
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
		int scmNo = Integer.parseInt(request.getParameter("scmNo"));
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		String writerNm = request.getParameter("memNm");
		String writerId = request.getParameter("writerId");
		String writerIp = request.getParameter("writerIp");
		int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
		String title = request.getParameter("title");
		Boolean isSecret = Boolean.valueOf(request.getParameter("isSecret"));
		String writerPw2 = null;		
		String writerPw = null;
		
		if(request.getParameter("pw") != null) {
			writerPw2 = request.getParameter("pw");
			//암호화
			DataEncrypt sha256Enc 	= new DataEncrypt();
			writerPw 		= sha256Enc.encrypt(writerPw2);
		}
		
		
		String contents = request.getParameter("contents");
		
		int qa = gd.qaWrite(memNo,writerNm,writerId,writerIp,title,contents,isSecret,writerPw,goodsNo,scmNo);
		
		if(qa > 0) {
			response.sendRedirect("goodsView?goodsNo="+goodsNo);
		}
		
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
