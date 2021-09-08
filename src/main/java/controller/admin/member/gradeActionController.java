package controller.admin.member;

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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dto.adminDTO.adminDTO;
import dto.goodsDTO.goodsDTO;
import dto.goodsDTO.goodsOptionDTO;
import dto.memberDTO.membergradeDTO;
import dto.scmDTO.scmDTO;
import service.adminService.adminMemberService;
import service.partners.goods.partnersGoodsService;

/**
 * Servlet implementation class gradeActionController
 */
@WebServlet("/admin/member/gradeAction")
public class gradeActionController extends HttpServlet {
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
		adminMemberService am = new adminMemberService();
		
		HttpSession session = request.getSession();
		
		adminDTO admin = (adminDTO)session.getAttribute("admin");
		if(admin == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("location.href='../login';");			
			out.println("</script>");
		}else {
								
			String path = request.getSession().getServletContext().getRealPath("/fileF/grade/");
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
					
					if(item.isFormField()){
						// 일반 파라미터
						String name = item.getFieldName();
						String value = item.getString("utf-8");
						
						list.put(name, value);
						
						
						System.out.println(name+ " = " + list.get(name));
						
					}else{
						//	파일 파라미터
						if(item.getSize()>0){
							String separator = File.separator;
							
							int idx = item.getName().lastIndexOf(separator);
							
							String fileName = item.getName().substring(idx+1);
							
							String savedFileName = System.currentTimeMillis() +fileName;
							
							if(!savedFileName.isEmpty()) {							
								filelist.add(savedFileName);
							}else {
								filelist.add("");
							}
							
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
				membergradeDTO dto = new membergradeDTO();
				String mode = (String)list.get("mode");
				
				dto.setSno(((String)list.get("sno") != null && !((String)list.get("sno")).equals(""))?Integer.parseInt((String)list.get("sno")):0);
				dto.setGradeNm((String)list.get("gradeNm"));
				dto.setOrderPriceMore(Integer.parseInt((String)list.get("orderPriceMore")));
				dto.setOrderPriceBelow(Integer.parseInt((String)list.get("orderPriceBelow")));
				dto.setOrderCnt(Integer.parseInt((String)list.get("orderCnt")));
				dto.setPriceLine(Integer.parseInt((String)list.get("priceLine")));
				
				dto.setMileagePercent(new BigDecimal((String)list.get("mileagePercent")));
				
				dto.setCouponCd((String)list.get("couponNo"));
				//	이미지 파일 설정
				if(filelist.size()>0){
					for(int i = 0; i<filelist.size(); i++){
						dto.setGradeImage((filelist.get(i) != null)?filelist.get(i):"");
					}
				}
				dto.setManagerNo(admin.getSno());
				dto.setManagerNm(admin.getId());
							
				System.out.println("dto == "+dto);
				int chk = am.gradeAction(dto,mode);
				
				if(chk>0){
					System.out.println("저장 성공");
				}
				
				PrintWriter out = response.getWriter();
				out.println("<script>");
				
				if(chk>0){
					System.out.println("성공");
					out.println("alert('등급이 "+mode+"되었습니다.');");
					out.println("location.href='./memberGrade';");
				}else{
					System.out.println("실패");
					out.println("alert('실패했습니다.');");
					out.println("history.back();");
				}
				
				out.println("</script>");
				
			}
		
		
		}
		
		
		
	}

}
