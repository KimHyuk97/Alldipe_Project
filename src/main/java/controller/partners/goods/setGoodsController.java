package controller.partners.goods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import dto.goodsDTO.goodsDTO;
import service.goodsService.goodsService;

@WebServlet("/partners/goods/setSelectOption")
public class setGoodsController extends HttpServlet{

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
		
		StringBuffer jb = new StringBuffer();
		
		String line = null;
		
		try{
			
			BufferedReader r = request.getReader();
			while((line=r.readLine())!=null){
				jb.append(line);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JsonParser ps = new JsonParser();
		JsonElement jobj = ps.parse(jb.toString()).getAsJsonObject();
		
		String goodsNos = jobj.getAsJsonObject().get("goodsNo").getAsString();
		
		String option = jobj.getAsJsonObject().get("option").getAsString();
		String value = jobj.getAsJsonObject().get("value").getAsString();
		
		System.out.println(goodsNos);
		System.out.println(option);
		System.out.println(value);
		
		String[] goodsNo = goodsNos.split(",");
		
		int chk = 0;
		
		if(goodsNo.length>0){
			goodsService gs = new goodsService();
			
			if(option.equals("memberOnly")){
				for(String no : goodsNo){
					chk = gs.updateOption("memberOnly", value, Integer.parseInt(no));
				}
			}else if(option.equals("adultFl")){
				for(String no : goodsNo){
					chk = gs.updateOption("onlyAdultFl", value, Integer.parseInt(no));
				}
			}else if(option.equals("saleState")){
				for(String no : goodsNo){
					//	관리자 판매중지 상품 관련
					int goodsNum =  Integer.parseInt(no);
					goodsDTO dto = gs.getGoods(goodsNum);
					
					if(dto.getGoodsSellFl()>0){
						chk = gs.updateOption("goodsSellFl", value, goodsNum);
					}
				}
			}
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(chk>0){
			out.print("true");
		}else{
			out.print("false");
		}
		
		out.close();
		
	}
	
}
