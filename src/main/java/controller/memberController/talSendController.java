package controller.memberController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@WebServlet("/talSend")
public class talSendController extends HttpServlet {
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
		 
		String talVal = request.getParameter("talVal");
		String massage = request.getParameter("message");
		String api_key = "NCSHF41K5FGM3XO0";
		String api_secret = "6RCZXUNV7A7VTFBEIZM1XYCAGRBMPWWV";
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", talVal);
		params.put("from", "01051890892"); //������ �ڱ��ȣ (����)
		params.put("type", "SMS");
		params.put("text", "인증번호 : "+ massage);
		params.put("app_version", "test app 1.2"); // application name and version

		try {
		  //send() �� �޽����� ������ �Լ�  
		  JSONObject obj = (JSONObject) coolsms.send(params);
		  System.out.println(obj.toString());
		} catch (CoolsmsException e) {
		  System.out.println(e.getMessage());
		  System.out.println(e.getCode());
		}
	}


}
