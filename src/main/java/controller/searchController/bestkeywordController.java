package controller.searchController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.searchDTO.searchDTO;
import service.searchService.searchService;

/**
 * Servlet implementation class bestkeywordController
 */
@WebServlet("/bestkeyword")
public class bestkeywordController extends HttpServlet {
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
				
		PrintWriter out = response.getWriter();
		searchService search = new searchService();
		
		//인기검색어
		List<searchDTO> bd = search.bestKeyword();
		
		if(bd != null) {
			
			JSONArray arr = new JSONArray();
			
			for(int i=0; i<bd.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("keyword",bd.get(i).getKeyword());
				arr.add(obj);
			}
	       String jsonInfo = arr.toJSONString();	
		   out.print(jsonInfo);		
		}
		
		
	}

}
