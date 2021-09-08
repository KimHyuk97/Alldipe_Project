package controller.admin.board;

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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dto.adminDTO.adminDTO;
import dto.boardDTO.boardDTO;
import dto.memberDTO.membergradeDTO;
import service.adminService.adminBoardService;

/**
 * Servlet implementation class noticeInsertController
 */
@WebServlet("/admin/board/insertNotice")
public class noticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		adminBoardService ab = new adminBoardService();	
		
		HttpSession session = request.getSession();
		adminDTO admin = (adminDTO) session.getAttribute("admin");
		
		PrintWriter out = response.getWriter();
		
		if(admin != null && admin.getSno() > 0){		
			String path = request.getSession().getServletContext().getRealPath("/fileF/notice/");
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
				boardDTO dto = new boardDTO();
				String mode = (String)list.get("mode");
				
				dto.setNo(((String)list.get("sno") != null && !((String)list.get("sno")).equals(""))?Integer.parseInt((String)list.get("sno")):0);
				dto.setPartnersFl(new Boolean((String)list.get("partnersFl")));
				dto.setTheme((String)list.get("theme"));
				dto.setTitle((String)list.get("title"));
				dto.setContents((String)list.get("contents"));
				dto.setBoardType("공지사항");
				
				//	이미지 파일 설정
				if(filelist.size()>0){
					for(int i = 0; i<filelist.size(); i++){
						dto.setImg((filelist.get(i) != null)?filelist.get(i):"");
					}
				}
				dto.setAdminNo(admin.getSno());
				dto.setWriterId(admin.getId());
				dto.setWriterIp((String)list.get("ip"));
				dto.setWriterNm(admin.getName());
							
				System.out.println("dto == "+dto);
				int chk = ab.noticeAction(dto,mode);
				
				if(chk>0){
					System.out.println("저장 성공");
				}
				
				
				out.println("<script>");
				
				if(chk>0){
					System.out.println("성공");
					out.println("alert('공지사항이 "+mode+"되었습니다.');");
					out.println("location.href='./notice';");
				}else{
					System.out.println("실패");
					out.println("alert('실패했습니다.');");
					out.println("history.back();");
				}
				
				out.println("</script>");
			
			
			}
			
			
		}else{					
			out.println("<script>");
			out.println("alert('로그인해주세요.');");
			out.println("location.href='../login';");
			out.println("</script>");
		}
	}

}
