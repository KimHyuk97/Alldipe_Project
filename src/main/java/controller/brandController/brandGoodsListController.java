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
 * Servlet implementation class brandGoodsListController
 */
@WebServlet("/brandGoodsList")
public class brandGoodsListController extends HttpServlet {
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
		
		String brandCd = request.getParameter("brandCd");
		goodsService goods = new goodsService();
		//상품만불러오기 상단카테고리상품
		List<goodsDTO> hg = goods.HeaderCategoryBrandGoodsList(brandCd);
		
		JSONArray arr = new JSONArray();
		
		for(int i=0; i<hg.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("goodsNo",hg.get(i).getGoodsNo());
			obj.put("representImg",hg.get(i).getRepresentImg());
			obj.put("goodsNm",hg.get(i).getGoodsNm());
			obj.put("goodsDiscountPercent",hg.get(i).getGoodsDiscountPercent());
			obj.put("goodsDiscountPrice",hg.get(i).getGoodsDiscountPrice());
			obj.put("goodsPrice",hg.get(i).getGoodsPrice());
			obj.put("fixedPrice",hg.get(i).getFixedPrice());
			arr.add(obj);
		}
		String jsonInfo = arr.toJSONString();
		
		out.print(jsonInfo);	
	}

}
