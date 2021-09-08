package controller.partnerController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.scmDTO.scmAddressDTO;
import dto.scmDTO.scmDTO;
import service.partnerService.getScmService;

@WebServlet("/partners/insertAddress")
public class insertScmAddressController extends HttpServlet {

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
		
		System.out.println("insertAddress");
		
		request.setCharacterEncoding("utf-8");
		
		scmAddressDTO dto = new scmAddressDTO();
		
		HttpSession session = request.getSession();
		
		scmDTO scm = (scmDTO)session.getAttribute("scm");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		
		if(scm==null){
			//	로그인이 안된 상황
			
			System.out.println("scm 없음!");
			
			out.println("<script>");
			out.println("alert('주소지 등록에 실패했습니다.');");
			out.println("location.href='${pageContext.request.contextPath}/partners/login';");
			out.println("<script>");
		}else{
		
			System.out.println("scm : " + scm.getScmNo());
			
			dto.setScmNo(scm.getScmNo());
			dto.setAddrNm(request.getParameter("addrNm"));
			dto.setZonecode(request.getParameter("zonecode"));
			dto.setAddress(request.getParameter("address"));
			dto.setAddressSub(request.getParameter("addressSub"));
			dto.setExtraAddress(request.getParameter("extraAddress"));
			
			getScmService gss = new getScmService();
			
			int chk = gss.insertScmAddress(dto);
			
			
			System.out.println("service 끝");
			
			out.println("<script>");
			if(chk>0){
				
				//	등록 성공
				System.out.println("등록 성공");
				out.println("alert('주소지를 등록했습니다.');");
				out.println("window.opener.location.reload();");
				out.println("window.close();");
				
			}else{
				//	등록 실패
				System.out.println("등록 실패");
				
				out.println("alert('주소지 등록에 실패했습니다.');");
				out.println("window.close();");
				
			}
			out.println("</script>");
			
		}
	}
	
}
