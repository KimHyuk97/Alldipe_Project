package controller.orderController;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.goodsDAO.goodsOptionDAO;
import dto.cartDTO.cartDTO;
import dto.goodsDTO.goodsDTO;
import dto.goodsDTO.goodsOptionDTO;
import dto.memberDTO.memberDTO;
import dto.memberDTO.membergradeDTO;
import dto.orderDTO.orderGoods;
import service.cartService.cartService;
import service.goodsService.goodsOptionService;
import service.goodsService.goodsService;
import service.memberService.memberGradeService;
import service.order.orderGoodsService;
import service.order.orderService;

/**
 * Servlet implementation class orderController
 */
@WebServlet("/order")
public class orderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String[] list = request.getParameterValues("chList");
		ArrayList<cartDTO> cartList = null;
		
		HttpSession session = request.getSession(); 
		memberDTO mem = (memberDTO)session.getAttribute("mem");
		
		System.out.println("list : " + Arrays.toString(list));
		
		int chk = 0;
		
		if(list.length>0){
			
			cartService cs = new cartService();
			goodsOptionService gos = new goodsOptionService();
			
			goodsService gs = new goodsService();
			memberGradeService mgs = new memberGradeService();
			orderGoodsService os = new orderGoodsService();
			
			chk = os.deleteOrderGoods(mem.getMemNo());
			if(!(chk>0)){
				System.out.println("미주문 상품목록 삭제실패");
			}
//			chk = cs.setCartCnt(list);
			//	장바구니 리스트 불러오기
//			cartList = cs.cart("", mem.getMemNo());
			cartList = cs.getList(list);
			
			int cnt = 0;
			//	ordergoods 생성 후 전달
			for(cartDTO cart : cartList){
				
				System.out.println("옵션번호 : " + cart.getOptionNo());
				goodsOptionDTO option = gos.getGoodsOption(cart.getOptionNo());
				
				goodsDTO goods = gs.getGoods(cart.getGoodsNo());
				membergradeDTO grade = mgs.getGrade(mem.getGradeSno());
				
				orderGoods og = new orderGoods();
				og.setOrderCd(cnt);
				
				og.setGoodsNm(cart.getGoodsNm());
				og.setMemNo(mem.getMemNo());
				og.setScmNo(cart.getScmNo());
				og.setGoodsNo(cart.getGoodsNo());
				og.setOptionNo(cart.getOptionNo());
				og.setGoodsOptionNm(cart.getGoodsOptionNm());
				og.setGoodsCnt(cart.getGoodsCnt());
				og.setFixedPrice(goods.getFixedPrice());
				og.setOptionFixedPrice(option.getOptionFixedPrice());
				og.setSumPrice(goods.getFixedPrice().add(option.getOptionFixedPrice()));
				og.setRepresentImg(cart.getRepresentImg());
				og.setDeliveryPrice(cart.getDeliveryCost());
				og.setTaxSupplyGoodsPrice(new BigDecimal(0));
				og.setTaxVatGoodsPrice(new BigDecimal(0));
				og.setTaxFreeGoodsPrice(new BigDecimal(0));
				og.setGoodsDcPrice(new BigDecimal(0));
				og.setMemberDcPrice(new BigDecimal(0));
				og.setCouponGoodsDcPrice(new BigDecimal(0));
				og.setDeliveryPrice(cart.getDeliveryCost());
				og.setMakerNm(cart.getMakerNm());
				
				if(grade.isMileageType()){
					//	적립금액
					og.setMemMileage(grade.getMileagePrice());
				}else{
					//	퍼센트
					og.setMemMileage(og.getSumPrice().multiply(grade.getMileagePercent().divide(new BigDecimal(100))));
					
				}
				
				
				chk = os.insertOrdergoods(og);
				
				if(!(chk>0)){
					System.out.println("ordergoods insert Error!");
					break;
				}
			}
			
			ArrayList<orderGoods> ogList = os.getOGList("주문중", mem.getMemNo());
			
			request.setAttribute("orderGoods", ogList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("./order/order.jsp");
			dispatcher.forward(request, response);
		}else{
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('구매할 상품이 없습니다!');");
			out.println("location.href='./index';");
			out.println("</script>");
			
			out.close();
		}
		
	}

}
