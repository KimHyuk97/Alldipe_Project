package controller.partners.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dto.categoryDTO.categoryDTO;
import service.categoryService.categoryService;

@WebServlet("/partners/goods/getCategoryUpdate")
public class getCategoryUpdate extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String cateCd = request.getParameter("code");
		
		System.out.println("code : " + cateCd);
		
		categoryService cs = new categoryService();
		
		ArrayList<categoryDTO> list = new ArrayList<>();
		
		ArrayList<categoryDTO> get = cs.getCateList(cateCd.substring(0, 3),6);
		for(categoryDTO dto : get){
			list.add(dto);
		}
		
		get = cs.getCateList(cateCd.substring(0, 6), 9);
		for(categoryDTO dto : get){
			list.add(dto);
		}
		
		JsonArray arr = new JsonArray();
		
		for(categoryDTO dto : list){
			JsonObject j = new JsonObject();
			
			j.addProperty("sno", dto.getSno());
			j.addProperty("cateNm", dto.getCateNm());
			j.addProperty("cateCd", dto.getCateCd());
			j.addProperty("keyword", dto.getKeyword());
			j.addProperty("adsgoods", dto.getAdsgoods());
			j.addProperty("cateDisplayFl", dto.getCateDisplayFl());
			j.addProperty("cateImg", dto.getCateImg());
			j.addProperty("cateOverImg", dto.getCateOverImg());
			j.addProperty("cateOnlyAdultFl", dto.getCateOnlyAdultFl());
			j.addProperty("cateOnlyAdultDisplayFl", dto.getCateOnlyAdultDisplayFl());
			j.addProperty("cateSort", dto.getCateSort());
			j.addProperty("recomGoodsNo", dto.getRecomGoodsNo());
			j.addProperty("commission", dto.getCommission());
			j.addProperty("regDt", (dto.getRegDt()!=null?dto.getRegDt().toString():null));
			j.addProperty("modDt", (dto.getModDt()!=null?dto.getModDt().toString():null));
			
			arr.add(j);
		}
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		String str = gson.toJson(arr);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println(str);
		System.out.println(str);
		
	}
	
}
