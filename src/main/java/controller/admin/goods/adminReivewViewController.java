package controller.admin.goods;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.boardDTO.reviewDTO;
import dto.goodsDTO.goodsDTO;
import oracle.net.aso.g;
import service.adminService.adminGoodsService;

/**
 * Servlet implementation class adminReivewViewController
 */
@WebServlet("/admin/goods/reviewView")
public class adminReivewViewController extends HttpServlet {
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
		
		int sno = Integer.parseInt(request.getParameter("sno"));
		reviewDTO r = ags.reviewView(sno);
		goodsDTO gd = new goodsDTO();
		if(r.getGoodsNo() > 0) {
			gd = ags.getGoods(r.getGoodsNo());
		}
		
		PrintWriter out = response.getWriter();
				
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("sno",r.getSno());
		obj.put("orderNo",r.getOrderNo());
		obj.put("goodsNo",r.getGoodsNo());
		obj.put("title",r.getTitle());
		obj.put("memNo",r.getMemNo());
		obj.put("writer",r.getWriter());
		obj.put("ip",r.getIp());
		obj.put("privateFl",r.isPrivateFl());
		obj.put("contents",r.getContents());
		obj.put("goodsPt",r.getGoodsPt());
		obj.put("reviewImg",r.getReviewImg());
		obj.put("addMileageFl",r.isAddMileageFl());
//		obj.put("regDt",r.getRegDt());
		obj.put("viewCnt",r.getViewCnt());
		obj.put("goodsNm",gd.getGoodsNm());
		obj.put("representImg", gd.getRepresentImg());
		arr.add(obj);


       String jsonInfo = arr.toJSONString();
       out.print(jsonInfo);		

		
	}

}
