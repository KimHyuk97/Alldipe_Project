package controller.partnerController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.partnerDAO.partnerDAO;
import dto.scmDTO.scmAddressDTO;
import dto.scmDTO.scmDTO;
import service.partnerService.getScmService;

@WebServlet("/partners/addressList")
public class scmAddressListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		scmDTO scm = (scmDTO)session.getAttribute("scm");
		String target = request.getParameter("target");
		
		System.out.println("address List Controller");
		
		System.out.println("scm : " + scm);
		
		System.out.println("target : " + target);
		
		if(scm != null){
			
			System.out.println("scm 존재");
			
			getScmService gss = new getScmService();
			
			ArrayList<scmAddressDTO> list = gss.getScmAddress(scm.getScmNo());
			session.setAttribute("scmAddress", list);
			
		}
		RequestDispatcher dis = request.getRequestDispatcher("./popup/addressList.jsp");
		dis.forward(request, response);
		
	}
	
}
