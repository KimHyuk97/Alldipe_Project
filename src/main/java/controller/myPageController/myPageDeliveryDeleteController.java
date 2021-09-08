package controller.myPageController;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.myPageDTO.myPageDTO;
import service.myPageService.myPageDeliveryDeleteService;
import service.myPageService.myPageDeliveryModifyService;

/**
 * Servlet implementation class myPageDeleteController
 */
@WebServlet("/deleteAddr")
public class myPageDeliveryDeleteController extends HttpServlet {
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
		
		int dNo = Integer.parseInt(request.getParameter("dNo"));//현재 주소번호
		int memNo = Integer.parseInt(request.getParameter("memNo"));//현재 주소번호
								
		myPageDeliveryDeleteService mpda = new myPageDeliveryDeleteService();
		
		int mpdaa = mpda.deliveryDelete(dNo);
		
		if(mpdaa > 0) {
			List<myPageDTO> mpds = mpda.deliveryAddressSelect(memNo);
			
			if(mpds != null) {
				PrintWriter out = response.getWriter();
				
				JSONArray arr = new JSONArray();
				
				for(int i=0; i<mpds.size(); i++) {
					JSONObject obj = new JSONObject();
					obj.put("dNo",mpds.get(i).getdNo());
					obj.put("memNo",mpds.get(i).getMemNo());
					obj.put("deliveryFl",mpds.get(i).getDeliveryFl());
					obj.put("zonecode",mpds.get(i).getZonecode());
					obj.put("address",mpds.get(i).getAddress());
					obj.put("addressSub",mpds.get(i).getAddressSub());
					obj.put("getName",mpds.get(i).getGetName());
					obj.put("phone",mpds.get(i).getPhone());
					arr.add(obj);
				}
				
		       String jsonInfo = arr.toJSONString();
				
			   out.print(jsonInfo);		
			}
			
		}

	}
}
