package controller.partners.brand;

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

import dto.brandDTO.brandDTO;
import service.brand.brandService;

@WebServlet("/partners/goods/getBrand")
public class getBrandController extends HttpServlet {

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
		
		String brandNm = request.getParameter("brand");
		
		System.out.println("brand Name : " + brandNm);
		
		brandService bs = new brandService();
		
		ArrayList<brandDTO> list = null;
		if(!brandNm.equals("")){
			list = bs.getList(brandNm);
		}
		
		
		JsonArray arr = new JsonArray();
		
		if(list!=null && list.size()>0){
			for(brandDTO dto : list){
				
				JsonObject j = new JsonObject();
				
				j.addProperty("sno", dto.getSno());
				j.addProperty("brandCd", dto.getBrandCd());
				j.addProperty("brandNm", dto.getBrandNm());
				j.addProperty("cateCd", dto.getCateCd());
				j.addProperty("keyword", dto.getKeyword());
				j.addProperty("brandImg", dto.getBrandImg());
				j.addProperty("brandImgMobile", dto.getBrandImgMobile());
				j.addProperty("likeCnt", dto.getLikeCnt());
				j.addProperty("regIp", dto.getRegIp());
				j.addProperty("regDt", (dto.getRegDt()!=null)?dto.getRegDt().toString():null);
				j.addProperty("modDt", (dto.getModDt()!=null)?dto.getModDt().toString():null);
				
				arr.add(j);
			}
		}
		Gson gson = new GsonBuilder().serializeNulls().create();
		String str = gson.toJson(arr);
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println(str);
		System.out.println(str);
		out.close();
	}
	
	
}
