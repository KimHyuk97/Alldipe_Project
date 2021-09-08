package controller.brandController;

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
import dto.goodsDTO.goodsDTO;
import service.goodsService.goodsService;

/**
 * Servlet implementation class brandController
 */
@WebServlet("/brandList")
public class brandController extends HttpServlet {
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
		
		String cateCd = request.getParameter("cateCd");
		goodsService goods = new goodsService();
		//상품만불러오기 상단카테고리상품
		List<brandDTO> hg = goods.HeaderCategoryBrand(cateCd);
		
		JSONArray arr = new JSONArray();
		
		for(int i=0; i<hg.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("sno",hg.get(i).getSno());
			obj.put("brandNm",hg.get(i).getBrandNm());
			obj.put("brandCd",hg.get(i).getBrandCd());
			obj.put("brandImg",hg.get(i).getBrandImg());
			obj.put("likeCnt",hg.get(i).getLikeCnt());
			arr.add(obj);
		}
		String jsonInfo = arr.toJSONString();
		
		out.print(jsonInfo);	
	}
}
