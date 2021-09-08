package controller.admin.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.goodsDTO.goodsDTO;
import service.adminService.adminGoodsService;

/**
 * Servlet implementation class adminSendGoodsDataController
 */
@WebServlet("/admin/goods/sendGoodsData")
public class adminSendGoodsDataController extends HttpServlet {
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
		
		adminGoodsService ags = new adminGoodsService();
		
		String[] goodsNos = request.getParameterValues("goodsNo");
		int[] goodsNo = Arrays.stream(goodsNos).mapToInt(Integer::parseInt).toArray();
		List<goodsDTO> gd = new ArrayList<goodsDTO>();
		
		for(int i = 0; i < goodsNo.length; i++) {
			goodsDTO g = ags.getGoods(goodsNo[i]);
			gd.add(g);
		}
		
		PrintWriter out = response.getWriter();
		
		JSONArray arr = new JSONArray();
		
		for(int i=0; i<gd.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("goodsNo",gd.get(i).getGoodsNo());
			obj.put("representImg",gd.get(i).getRepresentImg());
			obj.put("goodsNm",gd.get(i).getGoodsNm());
			obj.put("fixedPrice",gd.get(i).getFixedPrice());
			obj.put("scmNo",gd.get(i).getScmNo());
			obj.put("totalStock",gd.get(i).getTotalStock());
			obj.put("goodsSellFl",gd.get(i).getGoodsSellFl());
			arr.add(obj);
		}
		
       String jsonInfo = arr.toJSONString();
		
	   out.print(jsonInfo);		
		
	}

}
