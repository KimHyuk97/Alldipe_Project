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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dto.goodsDTO.goodsDTO;
import dto.goodsDTO.goodsOptionDTO;
import service.adminService.adminGoodsService;
import service.partners.goods.partnersGoodsService;

/**
 * Servlet implementation class adminGoodsModifyProcessController
 */
@WebServlet("/admin/goods/goodsModProcess")
public class adminGoodsModifyProcessController extends HttpServlet {
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
		HttpSession session = request.getSession();
		
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
		
		System.out.println("");
		
		if(list.size()>0){
			goodsDTO dto = new goodsDTO();
			
			System.out.println("사이즈가 >0 일때");
			
			dto.setGoodsNo(Integer.parseInt((String)list.get("goodsNo")));
			dto.setGoodsNm((String)list.get("goodsNm"));
			dto.setGoodsSellFl(Integer.parseInt((String)list.get("goodsSellFl")));
			dto.setMemberOnly(new Boolean((String)list.get("memberOnly")));
			dto.setCateCd((String)list.get("cateCd"));
			dto.setBrandCd((String)list.get("brandCd"));
			dto.setMakerNm((String)list.get("makerNm"));
			dto.setKeyword((String)list.get("keyword"));
			dto.setCommission(new BigDecimal(((String)list.get("commission")).equals("")?"0":((String)list.get("commission"))));
			dto.setGoodsPrice(new BigDecimal(((String)list.get("goodsPrice")).equals("")?"0":((String)list.get("goodsPrice"))));
			dto.setFixedPrice(new BigDecimal(((String)list.get("fixedPrice")).equals("")?"0":((String)list.get("fixedPrice"))));
			dto.setDiscountPercent(new BigDecimal(((String)list.get("discountPercent")).equals("")?"0":((String)list.get("discountPercent"))));
			dto.setCostPrice(new BigDecimal(((String)list.get("costPrice")).equals("")?"0":((String)list.get("costPrice"))));
			
			dto.setGoodsWeight(new BigDecimal(((String)list.get("goodsWeight")).equals("")?"0":((String)list.get("goodsWeight"))));
			dto.setTaxPercent(new BigDecimal(((String)list.get("taxPercent")).equals("")?"0":((String)list.get("taxPercent"))));
			
			dto.setOriginNm((String)list.get("originNm"));
			dto.setOnlyAdultFl(new Boolean((String)list.get("adultFl")));
			dto.setTaxFreeFl((String)list.get("tax"));
			dto.setFixedOrderCnt((String)list.get("fixedOrderCnt"));
			dto.setGoodsDescription((String)list.get("goodsDescription"));
			
			
			dto.setSalesUnit(Integer.parseInt((String)list.get("salesUnit")));
			
			dto.setLimitFl(new Boolean((String)list.get("limit")));
			dto.setLimitOption((String)list.get("limitOption"));
			
			
			if(new Boolean((String)list.get("limit"))){
				dto.setMaxOrderCnt(Integer.parseInt((String)list.get("maxOrderCnt")));
				dto.setMinOrderCnt(Integer.parseInt((String)list.get("minOrderCnt")));
			}else{
				dto.setMaxOrderCnt(0);
				dto.setMinOrderCnt(0);
			}
			
			//	이미지 파일 설정
			if(filelist.size()>0){
				String str = "";
				for(int i = 0; i<filelist.size(); i++){
					if(i<1){
						dto.setRepresentImg(filelist.get(i));
					}else{
						str+=","+filelist.get(i);
					}
				}
				dto.setSubImg(str);
			}
			
//			dto.setShipmentZonecode((String)list.get("shipmentZonecode"));
//			dto.setShipmentAddress((String)list.get("shipmentAddress"));
//			dto.setShipmentAddressSub((String)list.get("shipmentAddressSub"));
//			dto.setRecoveryZonecode((String)list.get("recoveryZonecode"));
//			dto.setRecoveryAddress((String)list.get("recoveryAddress"));
//			dto.setRecoveryAddressSub((String)list.get("recoveryAddressSub"));
//			dto.setDeliveryCompany((String)list.get("deliveryCompany"));
//			dto.setDeliveryCost(new BigDecimal(((String)list.get("deliveryCost")).equals("")?"0":(String)list.get("deliveryCost")));
//			dto.setDeliveryRefundCost(new BigDecimal(((String)list.get("deliveryRefundCost")).equals("")?"0":(String)list.get("deliveryRefundCost")));
//			dto.setDeliveryType((String)list.get("deliveryType"));
//			dto.setDeliveryWay((String)list.get("deliveryWay"));
//			dto.setDeliveryKind((String)list.get("deliveryKind"));
//			dto.setDeliveryArea((String)list.get("deliveryAreaFl"));
//			dto.setDeliveryCostAddJeju(new BigDecimal(
//					((String)list.get("deliveryCostAddJeju")).equals("")?"0":(String)list.get("deliveryCostAddJeju"))
//					);
//			dto.setDeliveryCostAdd(new BigDecimal(((String)list.get("deliveryCostAdd")).equals("")?"0":(String)list.get("deliveryCostAdd")));
//			dto.setDeliveryFreeCondition(
//						new BigDecimal(((String)list.get("deliveryFreeCondition")).equals("")?"0":(String)list.get("deliveryFreeCondition"))
//					);
			
			dto.setDetailInfoDelivery((String)list.get("detailInfoDelivery"));
			dto.setDetailInfoAS((String)list.get("detailInfoAS"));
			dto.setDetailInfoRefund((String)list.get("detailInfoRefund"));
			dto.setDetailInfoExchange((String)list.get("detailInfoExchange"));
			
			int chk = ags.modGoods(dto);
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			
			if(chk>0){
				out.println("alert('수정되었습니다!');");
				out.println("location.href='./goodsList'");
			}else{
				out.println("alert('수정 실패..');");
				out.println("window.history.back();");
			}
			out.println("</script>");
		}
	}

}
