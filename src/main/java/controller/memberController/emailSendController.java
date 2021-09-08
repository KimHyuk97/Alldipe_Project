package controller.memberController;

import java.util.Properties;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/mailSend")
public class emailSendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public emailSendController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
		
	}
	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		
		// mail server ����
		String host = "smtp.naver.com";
		String user = "vmnetworks@naver.com"; // �ڽ��� ���̹� ����
		String password = "kms121812!!";// �ڽ��� ���̹� �н�����
		
		
		//���� ���� �ּ�, �̸��� ����
		String email = request.getParameter("userEmailVal");
		String message = request.getParameter("messageCode");
	
		
		//�޴� ��� �ּ�
		String to_email = email;
		
		// SMTP ���� ������ �����Ѵ�.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.ssl.enable", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.debug", "true");
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		

		// email ����
		try {	
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user));
			//���� �̸��� ����
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
			// ���� ����
			msg.setSubject("올디프 인증번호입니다.");
			// ���� ����
			msg.setText("인증번호 : " + message);
			Transport.send(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
		
}
