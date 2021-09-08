package controller.myPageController;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.myPageDTO.myPageDTO;
import service.myPageService.myPageDeliveryAddService;
import service.myPageService.myPageDeliveryService;

/**
 * Servlet implementation class myPageDeliveryAddController
 */
@WebServlet("/addAddressController")
public class myPageDeliveryAddController extends HttpServlet {
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
		String deliveryFl = request.getParameter("deliveryFl");
		if(deliveryFl ==  "") {
			deliveryFl = "n";
		}
		String zone = request.getParameter("zone");
		String addr = request.getParameter("addr");
		String address = request.getParameter("address");
		String getName = request.getParameter("getName");
		String phone = request.getParameter("phone");
				
		//현재시간
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd hh:mm:ss");
		Date time = new Date();				
		String time1 = format.format(time);	
		
		myPageDeliveryAddService mpda = new myPageDeliveryAddService();
		
		int mpdaa = 0;

		if(deliveryFl.equals("y")) {
			//기본배송지 설정
			int df = mpda.selectDeliveryFl(deliveryFl,memNo,zone,addr,address,getName,phone);
			if(df>0) {
				mpdaa = mpda.deliveryAddressAdd(memNo,deliveryFl,zone,addr,address,getName,phone,time1);
			}
			
		}else {
			mpdaa = mpda.deliveryAddressAdd(memNo,deliveryFl,zone,addr,address,getName,phone,time1);
		}
				
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
