package controller.admin.promotion;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.env.IModule.IService;

import dto.couponDTO.couponDTO;
import service.coupon.couponService;

@WebServlet("/admin/promotion/insertCoupon")
public class insertCouponController extends HttpServlet{

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
		
		String kind = request.getParameter("kind");
		String useType = request.getParameter("useType");
		String saveType = request.getParameter("saveType");
		String couponNm = request.getParameter("couponNm");
		String couponDesc = request.getParameter("couponDesc");
		String usePeriodStartDate = request.getParameter("usePeriodStartDate");
		String usePeriodEndDate = request.getParameter("usePeriodEndDate");
		String kindType = request.getParameter("kindType");
		String benefit = request.getParameter("benefit");
		String benefitType = request.getParameter("benefitType");
		String displayStartDate = request.getParameter("displayStartDate");
		String displayEndDate = request.getParameter("displayEndDate");
		String limitSmsFl = request.getParameter("limitSmsFl");
		String applyProductType = "";
		
		if(request.getParameter("all")!=null){
			applyProductType += "scm, category, brand, product";
		}else{
			if(request.getParameter("scm")!=null){
				applyProductType += "scm";
			}
			if(request.getParameter("category")!=null){
				applyProductType += ",category";			
			}
			if(request.getParameter("brand")!=null){
				applyProductType += ",brand";
			}
			if(request.getParameter("product")!=null){
				applyProductType += ",product";
			}
		}
		
		String minOrderPrice = request.getParameter("minOrderPrice");
		String applyDupplicateType = request.getParameter("applyDupplicateType");
		
		
		System.out.println("usePeriodStartDate:" + usePeriodStartDate);
		System.out.println(usePeriodStartDate);
		
		
		System.out.println(applyProductType);
		
		String[] type= applyProductType.split(",");
		System.out.println("type = "+Arrays.toString(type));
		
		couponDTO dto = new couponDTO();
		
		dto.setKind(kind);
		dto.setUseType(useType);
		dto.setSaveType(saveType);
		dto.setCouponNm(couponNm);
		dto.setCouponDesc(couponDesc);
		
		if(usePeriodStartDate!= null && !usePeriodStartDate.equals("")){
			dto.setUsePeriodStartDate(Date.valueOf(usePeriodStartDate));
		}else{
			dto.setUsePeriodStartDate(null);
		}
		if(usePeriodEndDate!= null && !usePeriodEndDate.equals("")){
			dto.setUsePeriodStartDate(Date.valueOf(usePeriodEndDate));
		}else{
			dto.setUsePeriodStartDate(null);
		}
		
		dto.setKindType(kindType);
		
		if(!benefit.equals("") && benefit!=null){
			dto.setBenefit(new BigDecimal(benefit));
		}else{
			dto.setBenefit(new BigDecimal(0));
		}
		
		dto.setBenefitType(benefitType);
		dto.setLimitSmsFl(Boolean.valueOf(limitSmsFl));
		dto.setApplyProductType(applyProductType);
		
		if(minOrderPrice!= null && !minOrderPrice.equals("")){
			dto.setMinOrderPrice(new BigDecimal(minOrderPrice));
		}else{
			dto.setMinOrderPrice(new BigDecimal(0));
		}
		
		
		dto.setApplyDuplicateType(Boolean.valueOf(applyDupplicateType));
		
		if(displayStartDate!= null && displayStartDate.equals("")){
			dto.setUsePeriodStartDate(Date.valueOf(displayStartDate));
		}else{
			dto.setUsePeriodStartDate(null);
		}
		if(displayEndDate!= null && displayEndDate.equals("")){
			dto.setUsePeriodStartDate(Date.valueOf(displayEndDate));
		}else{
			dto.setUsePeriodStartDate(null);
		}
		
		System.out.println(dto);
		
		couponService cs = new couponService();
		
		int chk = cs.insertCoupon(dto);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		if(chk>0){
			System.out.println("성공");
			out.println("aert('쿠폰을 등록했습니다.');");
			out.println("location.href='./couponList';");
			
		}else{
			System.out.println("실패");
			out.println("aert('쿠폰 등록에 실패 했습니다.');");
			out.println("history.back();");
		}
		out.println("<script>");
		
		
	}
	
}
