<%@page import="dto.orderDTO.orderGoods"%>
<%@page import="dto.couponDTO.memberCouponDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<orderGoods> list = (List)request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/member/memberCouponSet.css">
<script src="${pageContext.request.contextPath }/js/member/memberCouponSet.js"></script>
</head>
<body>
	<header>
		<h3>Coupon List</h3>
	</header>
	<div class="container">
		<div class="table_div">
			<table id="orderlist">
				<colgroup>
					<col width="3%">
					<col width="37%">
					<col width="25%">
					<col width="10%">
					<col width="25%">
				</colgroup>
				<tr>
					<th>no.</th>
					<th>주문상품</th>
					<th>상품가격</th>
					<th>쿠폰선택</th>
					<th>할인금액</th>
					<
				</tr>
	<%
		if(list!=null && list.size()>0){
			for(int i = 1; i<=list.size(); i++){
		%>
				<tr>
					<td><%=i %></td>
					<td><%=list.get(i).getGoodsNm() %></td>
					<td><%=list.get(i).getSumPrice() %></td>
					<td><button onclick="">쿠폰적용</button></td>
					<td><%=list.get(i).getSumPrice().subtract(list.get(i).getCouponGoodsDcPrice()) %></td>
				</tr>	
	<%		}
		}else{%>
				<tr>
					<td>주문 목록이 없습니다.</td>
				</tr>
	<%	} %>
			</table>
		</div>
		<div class="btn_div">
			<button onclick="">적용하기</button>
			<button onclick="">취소</button>
		</div>
	</div>
	<footer>
	</footer>
</body>
</html>