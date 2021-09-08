package controller.myPageController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.myPageDTO.memberMileageDTO;
import service.myPageService.myPageDeliveryService;
import service.myPageService.myPageMemberService;

/**
 * Servlet implementation class myPageMemberMileageController
 */
@WebServlet("/memberMileage")
public class myPageMemberMileageController extends HttpServlet {
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
		
		myPageMemberService mileage = new myPageMemberService();
		
		List<memberMileageDTO> mpd = mileage.mileageSelect(memNo);
		
		if(mpd != null) {
			PrintWriter out = response.getWriter();
			
			JSONArray arr = new JSONArray();
			
			for(int i=0; i<mpd.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("sno",mpd.get(i).getSno());
				obj.put("memNo",mpd.get(i).getMemNo());
				obj.put("handleCd",mpd.get(i).getHandleCd());
				obj.put("mileage",mpd.get(i).getMileage());
				obj.put("contents",mpd.get(i).getContents());
				obj.put("deleteFl",mpd.get(i).getDeleteFl()); //사용상태
				obj.put("deleteDt",mpd.get(i).getDeleteDt());
				obj.put("regDt",mpd.get(i).getRegDt());
				arr.add(obj);
			}
	       String jsonInfo = arr.toJSONString();			
		   out.print(jsonInfo);		
		}
	}
}
