package controller.partners.brand;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.brandDTO.brandDTO;
import service.brand.brandService;

@WebServlet("/partners/goods/newBrand")
public class insertBrandController extends HttpServlet {

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
		
		String brandNm = request.getParameter("brandNm");
		
		System.out.println("brandNm : " + brandNm);
		
		brandService bs = new brandService();
		
		brandDTO dto = new brandDTO();
		dto.setBrandNm(brandNm);
		
		int chk = bs.insertBrand(dto);
		if(chk>0){
			dto = bs.getBrand(brandNm);
		}
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		System.out.println(chk + "," + dto.getBrandNm() + "," + dto.getBrandCd());
		out.println(chk + "," + dto.getBrandNm() + "," + dto.getBrandCd());
		
		out.close();
		
	}
	
}
