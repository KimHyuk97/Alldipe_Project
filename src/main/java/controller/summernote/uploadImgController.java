package controller.summernote;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/summernote/uploadImg")
public class uploadImgController extends HttpServlet {

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
		
		String path = request.getSession().getServletContext().getRealPath("/fileF/summernote/");
		int size = 10*1024*1024;
		
		String fileName = "";
		
		try{
			MultipartRequest multi = new MultipartRequest(request, 
														path,
														size,
														"utf-8",
														new DefaultFileRenamePolicy());
			Enumeration files = multi.getFileNames();
			String file = (String)files.nextElement();
			fileName = multi.getFilesystemName(file);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String uploadPath = fileName;
		
		JsonObject jobj = new JsonObject();
		jobj.addProperty("url", uploadPath);
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		String str = gson.toJson(jobj);
		
		out.print(str);
	}
	
}
