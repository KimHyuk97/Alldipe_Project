package controller.admin.goods;

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

import dto.categoryDTO.categoryDTO;
import service.adminService.adminGoodsService;

/**
 * Servlet implementation class cateLargeController
 */
@WebServlet("/admin/goods/cate2")
public class adminCateListController extends HttpServlet {
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
		String cateCd = (request.getParameter("cateCd") != null)?request.getParameter("cateCd"):"";
		List<categoryDTO> cd = ags.categroyLsit(cateCd);
		JSONArray arr = new JSONArray();

		for(int i=0; i<cd.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("cateNm",cd.get(i).getCateNm());
			obj.put("sno",cd.get(i).getSno());
			obj.put("cateCd",cd.get(i).getCateCd());
			arr.add(obj);
		}
       String jsonInfo = arr.toJSONString();

	   out.print(jsonInfo);		
	}

}
