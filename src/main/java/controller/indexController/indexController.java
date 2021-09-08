package controller.indexController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.brandDTO.brandDTO;
import dto.goodsDTO.goodsDTO;
import dto.themeDTO.themeDTO;
import service.brand.brandService;
import service.goodsService.goodsService;
import service.themeService.themeService;

@WebServlet("/index")
public class indexController extends HttpServlet {
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
		
		//	index 기본 테마 목록
		//	[이번주 핫딜, 브랜드, 인기상품, 무료배송, 올타임딜]
		int[] display = {1,2,3,4,55};
		
		themeService ts = new themeService();
		goodsService gs = new goodsService();
		
		ArrayList<themeDTO> list = ts.getThemeList(display); 
		String cate = request.getParameter("cate");
		
		HttpSession session = request.getSession();
		session.setAttribute("themeList", list);
		
		ArrayList goodsList = new ArrayList<>();
		ArrayList<goodsDTO> goods = null;
		
		if(list!=null && list.size()>0){
			
			for(themeDTO dto : list){
				goods = new ArrayList<>();
				
				if(dto.getThemeNm().equals("브랜드")){
					
					brandService bs = new brandService();
					brandDTO brand = bs.getToCode(dto.getRelationCd());
					
					session.setAttribute("brand", brand);
					
				}
				
				String[] arr = dto.getGoodsNos().split(",");
				
				if(arr.length>0){
					for(String str : arr){
						int goodsNo = Integer.parseInt(str);
						goodsDTO product = gs.getGoodsInfo(goodsNo);
						
						goods.add(product);
					}
				}
				
				goodsList.add(goods);
			}
		}
		
		session.setAttribute("goodsList", goodsList);
		
		System.out.println("controller list size : " + goodsList.size());
		
		RequestDispatcher dis = request.getRequestDispatcher("./index.jsp");
		dis.forward(request, response);
		
	}

}
