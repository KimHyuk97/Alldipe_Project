package controller.admin.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.brandDTO.brandDTO;
import dto.themeDTO.themeDTO;
import service.adminService.adminGoodsService;

/**
 * Servlet implementation class adminSearchThemeList
 */
@WebServlet("/admin/search/adminSearchThemeList")
public class adminSearchThemeList extends HttpServlet {
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
		adminGoodsService ags = new adminGoodsService();
		
		List<themeDTO> th = ags.searchThemeList();
		
		JSONArray arr = new JSONArray();

		for(int i=0; i<th.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("sno",th.get(i).getSno());
			obj.put("themeNm",th.get(i).getThemeNm());
			arr.add(obj);
		}
       String jsonInfo = arr.toJSONString();
	   out.print(jsonInfo);		
	}

}
