<%@page import="dto.boardDTO.boardDTO"%>
<%@page import="dto.boardDTO.noticeDTO"%>
<%@page import="dto.orderDTO.orderGoods"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.scmDTO.scmDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	scmDTO sdto = (scmDTO)session.getAttribute("scm");
	ArrayList<orderGoods> ogList = (ArrayList)session.getAttribute("ogList");
	ArrayList<boardDTO> nList = (ArrayList)session.getAttribute("nList");
	
	System.out.println("nList : " + nList);
%>
<jsp:include page="./header.jsp"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/partners/main.css">
	<!--jquery-->
    <script src="${pageContext.request.contextPath }/js/jquery/jquery.min.js"></script>
    <!--차트-->
    <script src="https://d3js.org/d3.v3.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>
<div class="cont_wrap">
        <div class="box_wrap">
            <div class="left_box">
                <div class="box_bg01">
                    <!--상품등록-->
                    <div class="cont_box box01">
                        <div class="title_box">
                            <p class="cont_title">
                                상품을 등록하세요!
                            </p>
                        </div>
                        <p class="cont_text">올디프 회원들이 대표님의 상품을 기다리고 있습니다.<br>지금 상품을 등록해보세요!
                        </p>
                        <a href="./goods/insert" class="cont_btn">상품 등록하기</a>
                    </div>
                    <!--주문관리-->
                    <div class="cont_box box02">
                        <div class="title_box">
                            <p class="cont_title">
                                주문관리
                            </p>
                            <span>주문건을 확인해주세요</span>
                            <a href="./order/list" class="more_view">전체보기 ></a>
                        </div>
                        <table class="order_table">
                            <thead>
                                <tr>
                                    <th class="num">번호</th>
                                    <th class="item_name">상품명</th>
                                    <th class="item_price">가격</th>
                                    <th>상품 수</th>
                                    <th>결제완료</th>
                                    <th>상품준비</th>
                                    <th>배송중</th>
                                    <th>배송완료</th>
                                    <th>교환/환불</th>
                                    <th>구매확정</th>
                                </tr>
                            </thead>
                            <div class=""></div>
                            <tbody>
<%
	if(ogList!=null && ogList.size()>0){
		for(orderGoods og : ogList){
%>
								<tr>
									<td class="num"><%=og.getOrderNo() %></td>
									<td class="item_name"><%=og.getGoodsNm() %></td>
									<td class="item_price"><%=og.getFixedPrice() %></td>
									<td><%=og.getGoodsCnt() %></td>
									<td>1</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
								</tr>
<%
		}
	}else{
%>
								<tr>
									<td colspan="10">등록된 주문이 없습니다.</td>
								</tr>
<%	} %>                    </tbody>
                        </table>
                    </div>
                </div>
                <!--공지사항-->
                <div class="box_bg02">
                    <div class="cont_box box03">
                        <div class="title_box">
                            <p class="cont_title">
                                공지사항
                            </p>
                            <span class="pro"><a href="#">프로모션</a></span>
                            <span><a href="#">프로모션</a></span>
                            <a href="./notice/list?no=1&theme=all" class="more_view">전체보기 ></a>
                        </div>
                        <ul class="notice_list">
<%	if(nList!= null && nList.size()>0){ 
		for(boardDTO nd : nList){
			System.out.println(nd.toString());
%>							
<%-- 							<li><div class="nList_li_div" onclick="location.href='./notice/notice?no=<%=nd.getSno()%>';"><%=nd.getSno()%>||<%=nd.getTitle() %> : <%=nd.getContents() %></div></li> --%>
<% 		}
	}else{%>
							<li>등록된 공지사항이 없습니다.</li>
<% 	}%>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="right_box">
                <!--사업자인증-->
                <div class="box_bg03">
                    <div class="cont_box box04">
                        <div class="title_box">
                            <p class="cont_title">
                                사업자 인증하기
                            </p>
                        </div>
                        <p class="cont_text">사업자 인증 후 상품 판매가 시작됩니다! <br>안전한 비즈니스를 위해 사업자인증 필수!</p>
                        <a href="./certification" class="cont_btn">사업자 인증하기</a>
                    </div>
                    <!--오늘의 매출-->
                    <div class="cont_box box05">
                        <div class="title_box">
                            <p class="cont_title">
                                오늘의 매출
                            </p>
                            <span>이번달은 이만큼!</span>
                            <a href="#" class="more_view">더 보기 ></a>
                        </div>
                        <!--차트-->
                        <div class="chart_wrap">
                            <div id="chart"></div>
                        </div>

                    </div>
                    <!--상품 가져오기-->
                    <div class="cont_box box06">
                        <div class="title_box">
                            <p class="cont_title">
                                상품 가져오기
                            </p>
                        </div>
                        <p class="cont_text">플레이오토를 통해 다른 사이트에 판매중인 상품을 가져올 수 있습니다.<br>빠르게 상품 등록을해보세요!</p>
                        <a href="#" class="cont_btn">상품 가져오기</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<jsp:include page="footer.jsp"/>
<script>
    /*차트그래프*/
    
    window.onload = function(){
    	endLoading();
    }
    
    $(function() {
        var chart = c3.generate({
            data: {
                colors: {
                    매출: '#ff0000',
                    환불: 'orange'
                },
                x: 'x',
                columns: [
                    /*x축*/
                    ['x', '04/01', '04/02', '04/03', '04/04', '04/05', '04/06', '04/07', '04/08'],
                    /*매출 그래프*/
                    ['매출', 0, 0, 0, 0, 0, 0, 0, 0],
                    /*환불 그래프*/
                    ['환불', 0, 0, 0, 0, 0, 0, 0, 0]
                ]
            },
            axis: {
                x: {
                    type: 'category',
                    categories: ['cat1', 'cat2', 'cat3', 'cat4', 'cat5', 'cat6', 'cat7', 'cat8'],
                }
            }
        });
    });
</script></html>
