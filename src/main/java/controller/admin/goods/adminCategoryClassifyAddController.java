package controller.admin.goods;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
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

import dto.categoryDTO.categoryDTO;
import dto.goodsDTO.goodsDTO;
import service.adminService.adminGoodsService;

@WebServlet("/admin/goods/categoryClassifyAdd")
public class adminCategoryClassifyAddController extends HttpServlet {
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
				//	파일 파라미터
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
			categoryDTO dto = new categoryDTO();
			
			dto.setCateCd(((String)list.get("cateCd")).equals("")?"":(String)list.get("cateCd"));
			if(dto.getCateCd().length() < 3) {
				String cateCd = ags.getCateCd(dto.getCateCd(),3);

				String code = Integer.toString(Integer.parseInt(cateCd)+1);
				String realCode = "";
				
				if(code.length() == 1) {
					realCode = "00"+ code;
				}else if(code.length() == 2) {
					realCode = "0"+ code;
				}
				
				dto.setCateCd(realCode); 
				System.out.println("최종 카테고리 코드 == "+dto.getCateCd());
				
			}else if(dto.getCateCd().length() < 6) {
				String cateCd = ags.getCateCd(dto.getCateCd(),6);

				String code = Integer.toString(Integer.parseInt(cateCd)+1);

				String realCode = "";

				if(code.length() == 4) {
					realCode = "00"+ code;
				}else if(code.length() == 5) {
					realCode = "0"+ code;
				}					
				dto.setCateCd(realCode); 
				System.out.println("최종 카테고리 코드 == "+dto.getCateCd());
			}else {
				String cateCd = ags.getCateCd(dto.getCateCd(),9);
				String code = Integer.toString(Integer.parseInt(cateCd)+1);
				String realCode = "";
				
				if(code.length() == 7) {
					realCode = "00"+ code;
				}else if(code.length() == 8) {
					realCode = "0"+ code;
				}
				
				dto.setCateCd(realCode); 
				System.out.println("최종 카테고리 코드 == "+dto.getCateCd());
			}
			
			
			
			dto.setCateNm((String)list.get("cateNm"));
			dto.setCateDisplayFl(new Boolean((String)list.get("displayFl")));
			dto.setCommission(new BigDecimal(((String)list.get("commission")).equals("")?"0":((String)list.get("commission"))));
			dto.setKeyword((String)list.get("keyword"));
			dto.setCateOnlyAdultFl(new Boolean((String)list.get("adultFl")));
			dto.setCateOnlyAdultDisplayFl(new Boolean((String)list.get("adultDisplayFl")));
			dto.setRecomGoodsNo(((String)list.get("recomGoodsNo")).equals("")?"":((String)list.get("recomGoodsNo")));
			
			//	이미지 파일 설정
			if(filelist.size()>0){
				for(int i = 0; i<filelist.size(); i++){
					if(i<1){
						dto.setCateImg(filelist.get(i));
					}
				}
			}

			int chk = ags.categoryAdd(dto);
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			
			if(chk>0){
				out.println("alert('카테고리를 추가하였습니다!');");
				out.println("location.href='./goodsClassify?page=0'");
			}else{
				out.println("alert('실패..');");
				out.println("window.history.back();");
			}
			out.println("</script>");
		}
	}
		
	

}
