package controller.admin.goods;

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

import dto.brandDTO.brandDTO;
import service.adminService.adminGoodsService;

/**
 * Servlet implementation class adminSearchBrandListController
 */
@WebServlet("/admin/goods/adminSearchbrandList")
public class adminSearchBrandListController extends HttpServlet {
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
		
		List<brandDTO> bd = ags.searchBrandList();
		
		JSONArray arr = new JSONArray();

		for(int i=0; i<bd.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("sno",bd.get(i).getSno());
			obj.put("brandNm",bd.get(i).getBrandNm());
			obj.put("brandCd",bd.get(i).getBrandCd());
			arr.add(obj);
		}
       String jsonInfo = arr.toJSONString();

	   out.print(jsonInfo);		
		
		
	}

}
