package controller.goodsController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import dto.goodsDTO.goodsDTO;
import dto.themeDTO.themeDTO;
import service.goodsService.goodsService;
import service.paging.paging;
import service.paging.pagingService;
import service.themeService.themeService;

@WebServlet("/goods/addList")
public class addGoodsController extends HttpServlet {

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
		//	필요한 Request
		//	cateCd, pageNo
		StringBuffer jb = new StringBuffer();
		
		String line = null;
		
		try{
			BufferedReader r = request.getReader();
			
			while((line=r.readLine())!= null){
				jb.append(line);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JsonParser ps = new JsonParser();
		JsonElement jobj = ps.parse(jb.toString()).getAsJsonObject();
		
		String category = jobj.getAsJsonObject().get("cate").getAsString();
		String pageStr = jobj.getAsJsonObject().get("pageNo").getAsString();
		String memNo = jobj.getAsJsonObject().get("memNo").getAsString();
		
		
		int pageNo = 1;
		if(pageStr!=null && !pageStr.equals("")){
			pageNo = Integer.parseInt(pageStr);
		}
		paging p = pagingService.getPage(pageNo, 12);
		themeService ts = new themeService();
		themeDTO theme = ts.getTheme(category);
		
		goodsService gs = new goodsService();
		
		ArrayList<goodsDTO> list = new ArrayList<>();
		ArrayList<goodsDTO> goods = null;
		if(theme.getRelationCd()==null || theme.getRelationCd().equals("") || theme.getRelationCd().equals("000")){
//			검색 조건 추가
			String condition = "where goodsSellFl>0 ";
			
			if(theme.getSno()==56 || theme.getSno() == 70){
				condition += "order by regDt desc";
			}
			
			goods = gs.getToCondition(condition, p);
			
//			상품 목록 검색
			for(goodsDTO dto : goods){
				list.add(dto);
			}
		}else{
			
			goods = gs.getList(category, p);
			for(goodsDTO dto : goods){
				list.add(dto);
			}
		}
		
		JSONArray arr = new JSONArray();
		
		for(int i=0; i<list.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("goodsNo",list.get(i).getGoodsNo());
			obj.put("representImg",list.get(i).getRepresentImg());
			obj.put("goodsNm",list.get(i).getGoodsNm());
			obj.put("discountPercent",list.get(i).getDiscountPercent());
			obj.put("goodsPrice",list.get(i).getGoodsPrice());
			obj.put("fixedPrice",list.get(i).getFixedPrice());
			obj.put("memberOnly", list.get(i).isMemberOnly());
			obj.put("adultFl", list.get(i).isOnlyAdultFl());
			
			arr.add(obj);
			
		}
		String jsonInfo = arr.toJSONString();
		
		response.setContentType("application/json; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print(jsonInfo);
		out.close();
		
	}
	
}
