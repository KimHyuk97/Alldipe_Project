package controller.myPageController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.myPageDTO.myPageDTO;
import service.myPageService.myPageDeliveryService;

/**
 * Servlet implementation class myPageDeliveryController
 */
@WebServlet("/myPageDelivery")
public class myPageDeliveryController extends HttpServlet {
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
				
		int memNo = Integer.parseInt(request.getParameter("memNo"));

		myPageDeliveryService mpds = new myPageDeliveryService();
		
		List<myPageDTO> mpd = mpds.deliveryAdd(memNo);
				
		
		if(mpd != null) {
			PrintWriter out = response.getWriter();
			
			JSONArray arr = new JSONArray();
			
			for(int i=0; i<mpd.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("dNo",mpd.get(i).getdNo());
				obj.put("memNo",mpd.get(i).getMemNo());
				obj.put("deliveryFl",mpd.get(i).getDeliveryFl());
				obj.put("zonecode",mpd.get(i).getZonecode());
				obj.put("address",mpd.get(i).getAddress());
				obj.put("addressSub",mpd.get(i).getAddressSub());
				obj.put("getName",mpd.get(i).getGetName());
				obj.put("phone",mpd.get(i).getPhone());
				arr.add(obj);
			}
	       String jsonInfo = arr.toJSONString();			
		   out.print(jsonInfo);		
		}
	}

}
