package controller.admin.goods;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dto.goodsDTO.goodsOptionDTO;
import service.adminService.adminGoodsService;

/**
 * Servlet implementation class adminGoodsOptionModifyProcessController
 */
@WebServlet("/admin/goods/goodsOptionModifyProcess")
public class adminGoodsOptionModifyProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		adminGoodsService ags = new adminGoodsService();
		
		String path = request.getSession().getServletContext().getRealPath("/fileF/goods/");
		int size = 10*1024*1024;
		
		int optionCnt = 0;
		int optionNmCnt = 0;
		
		File file = new File(path);
		
		DiskFileItemFactory fif = new DiskFileItemFactory();
		fif.setRepository(file);
		fif.setSizeThreshold(size);
		ServletFileUpload fileUpload = new ServletFileUpload(fif);
		
		HashMap list = new HashMap<>();
		ArrayList<goodsOptionDTO> options = new ArrayList<>();
		
		ArrayList<String> filelist = new ArrayList<>();
		
		try{
			
			List<FileItem> items = fileUpload.parseRequest(request);
			
			for(FileItem item : items){
				
				if(item.isFormField()){
					// 일반 파라미터
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					
					list.put(name, value);
					
					if(name.contains("optionNm")){
						optionNmCnt++;
					}
					
					if(name.contains("optionNo")){
						optionCnt++;
					}
					
					System.out.println(name+ " = " + list.get(name));
		
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//			옵션 등록
		if(optionCnt != 0 && optionNmCnt!= 0){
			System.out.println("optionCnt === " +optionCnt);
			System.out.println("optionNmCnt === "+optionNmCnt);
			optionNmCnt = optionNmCnt/optionCnt;
		}
		goodsOptionDTO option = null;
		for(int i = 1; i<=optionCnt; i++){
			option = new goodsOptionDTO();
			option.setGoodsNo(Integer.parseInt((String)list.get("goodsNo")));
			option.setOptionNo(Integer.parseInt((String)list.get("optionNo" + String.format("%02d", i))));
			
			
			for(int j = 0; j<optionNmCnt; j++){
								
				if(j==0){
					option.setOptionNm1((String)list.get("optionNm" + j + String.format("%02d", i)));
					option.setOptionValue1((String)list.get("optionVal" + j + String.format("%02d", i)));
				}else if(j==1){
					option.setOptionNm2((String)list.get("optionNm" + j + String.format("%02d", i)));
					option.setOptionValue2((String)list.get("optionVal" + j + String.format("%02d", i)));
				}else if(j==2){
					option.setOptionNm3((String)list.get("optionNm" + j + String.format("%02d", i)));
					option.setOptionValue3((String)list.get("optionVal" + j + String.format("%02d", i)));
				}else if(j==3){
					option.setOptionNm4((String)list.get("optionNm" + j + String.format("%02d", i)));
					option.setOptionValue4((String)list.get("optionVal" + j + String.format("%02d", i)));
				}else if(j==4){
					option.setOptionNm5((String)list.get("optionNm" + j + String.format("%02d", i)));
					option.setOptionValue5((String)list.get("optionVal" + j + String.format("%02d", i)));
				}
			}
			
			option.setOptionPrice(new BigDecimal((String)list.get("fixedCost" + String.format("%02d", i))));
			option.setOptionFixedPrice(new BigDecimal((String)list.get("salesCost" + String.format("%02d", i))));
			option.setStockCnt(Integer.parseInt((String)list.get("stockCnt" + String.format("%02d", i))));
			option.setSellerCd((String)list.get("sellerCd" + String.format("%02d", i)));
			
			option.setOptionMemo((String)list.get("comment" + String.format("%02d", i)));
			
			System.out.println(i+"번쨰 옵션 리스트 =====     " +option.toString());
			System.out.println("-------------------------------------------------------------------------------------------------");
			System.out.println("");
			
			options.add(option);
			
		}
		
		int chk = ags.addGoodsOption(options);
		
		PrintWriter out = response.getWriter();
				
		out.println("<script>");
		
		if(chk>0){
			System.out.println("goods Option Insert Clear");
			out.println("alert('수정되었습니다!');");
			out.println("window.close();");
		}else{
			out.println("alert('수정 실패..');");
			out.println("window.close();");
		}
		out.println("</script>");
			
	}

}
