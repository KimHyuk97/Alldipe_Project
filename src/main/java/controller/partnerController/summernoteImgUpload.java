package controller.partnerController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/uploadSummernoteImageFile")
public class summernoteImgUpload extends HttpServlet {

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
		
		int size = 10*1024*1024;
		
		String filename = "";
		
		String realFolder = request.getSession().getServletContext().getRealPath("profileUpload");
		
		UUID uuid = UUID.randomUUID();
		
		try{
			MultipartRequest multi = new MultipartRequest(request,
					realFolder, size, "utf-8", new DefaultFileRenamePolicy());
			
			Enumeration files = multi.getFileNames();
			String file = (String)files.nextElement();
			filename = multi.getFilesystemName(file);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String uploadPath = "/upload/" + filename;
		
		JSONObject jobj = new JSONObject();
		jobj.put("url", uploadPath);
		
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		
		out.println(jobj.toJSONString());
		
		
	}
	
}
