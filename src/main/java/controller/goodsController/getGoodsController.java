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

@WebServlet("/goods/getList")
public class getGoodsController extends HttpServlet {
//	private static final long serialVersionUID = 1L;

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
		String pageNo = jobj.getAsJsonObject().get("pageNo").getAsString();
		String memNo = jobj.getAsJsonObject().get("memNo").getAsString();
		
		themeService ts = new themeService();
		themeDTO theme = ts.getTheme(category);
		
		ArrayList<goodsDTO> list = new ArrayList<>();
		
		goodsService gs = new goodsService();
		
		if(theme!=null){
			
			String[] strs = theme.getGoodsNos().split(",");
		
			if(strs.length>0){
				for(String str : strs){
					goodsDTO gdto = gs.getGoodsInfo(Integer.parseInt(str));
					list.add(gdto);
				}
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
		
		response.setContentType("application/json; charset=utf-8");
		
		String jsonInfo = arr.toJSONString();
		
		PrintWriter out = response.getWriter();
		out.print(jsonInfo);
		out.close();
	}
	
	
}
