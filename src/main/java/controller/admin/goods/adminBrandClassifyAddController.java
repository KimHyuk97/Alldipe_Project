package controller.admin.goods;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dto.brandDTO.brandDTO;
import dto.categoryDTO.categoryDTO;
import service.adminService.adminGoodsService;

/**
 * Servlet implementation class adminBrandClassifyAddController
 */
@WebServlet("/admin/goods/brandClassifyAdd")
public class adminBrandClassifyAddController extends HttpServlet {
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
		String path = request.getSession().getServletContext().getRealPath("/fileF/goods/");
		int size = 10*1024*1024;

		File file = new File(path);
		
		DiskFileItemFactory fif = new DiskFileItemFactory();
		fif.setRepository(file);
		fif.setSizeThreshold(size);
		ServletFileUpload fileUpload = new ServletFileUpload(fif);
		
		HashMap list = new HashMap<>();
		
		ArrayList<String> filelist = new ArrayList<>();
		
		try{
			
			List<FileItem> items = fileUpload.parseRequest(request);
			
			for(FileItem item : items){		
				//	?????? ????????????
				if(item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					
					list.put(name, value);
					
					System.out.println(name+ " = " + list.get(name));
				}else{
					if(item.getSize()>0){
						String separator = File.separator;
						
						int idx = item.getName().lastIndexOf(separator);
						
						String fileName = item.getName().substring(idx+1);
						
						String savedFileName = System.currentTimeMillis() +fileName;
						
						filelist.add(savedFileName);
						
						File uploadFile = new File(file + separator + savedFileName);
						
						item.write(uploadFile);
						
						System.out.println("file List : " + filelist.size());
						
					}
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(list.size()>0){
			brandDTO dto = new brandDTO();
					
			dto.setBrandNm((String)list.get("brandNm"));		
			dto.setKeyword(((String)list.get("keyword")).equals("")?"":((String)list.get("keyword")));;
			
			//	????????? ?????? ??????
			if(filelist.size()>0){
				for(int i = 0; i<filelist.size(); i++){
					if(i<1){
						dto.setBrandImg(filelist.get(i));
					}
				}
			}

			int chk = ags.brandAdd(dto);
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			
			if(chk>0){
				out.println("alert('?????????("+dto.getBrandNm().toString()+")??? ?????????????????????!');");
				out.println("location.href='./goodsClassify?page=1'");
			}else{
				out.println("alert('??????..');");
				out.println("window.history.back();");
			}
			out.println("</script>");
		}
		
		
		
	}

}
