package controller.admin.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.memberDTO.membergradeDTO;
import service.adminService.adminMemberService;

/**
 * Servlet implementation class adminMemberGradeListController
 */
@WebServlet("/admin/member/membergrade")
public class adminMemberGradeListController extends HttpServlet {
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
		PrintWriter out = response.getWriter();
		adminMemberService am = new adminMemberService();
		
		List<membergradeDTO> mg = am.getmembergrade();
		
		JSONArray arr = new JSONArray();

		for(int i=0; i<mg.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("sno",mg.get(i).getSno());
			obj.put("gradeNm",mg.get(i).getGradeNm());
			arr.add(obj);
		}
	    String jsonInfo = arr.toJSONString();
	
		out.print(jsonInfo);	
		
	}

}
