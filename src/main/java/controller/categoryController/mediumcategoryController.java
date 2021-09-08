package controller.categoryController;

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

import dto.categoryDTO.categoryDTO;
import service.categoryService.categoryService;

@WebServlet("/mediumCategory")
public class mediumcategoryController extends HttpServlet {
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
		
		String cateCd = request.getParameter("cateCd");
		categoryService cs = new categoryService();
		
		//카테고리 리스트
		List<categoryDTO> mlc = cs.mediumCategoryList(cateCd);			
		
		if(mlc != null || mlc.isEmpty()) {
			PrintWriter out = response.getWriter();
			
			JSONArray arr = new JSONArray();
			
			for(int i=0; i<mlc.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("sno",mlc.get(i).getSno());
				obj.put("cateNm",mlc.get(i).getCateNm());
				obj.put("cateCd",mlc.get(i).getCateCd());
				arr.add(obj);
			}
	       String jsonInfo = arr.toJSONString();			
		   out.print(jsonInfo);		
		
		}
	}

}
