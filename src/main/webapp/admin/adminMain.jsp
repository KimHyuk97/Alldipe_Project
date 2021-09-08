<%@page import="dto.adminDTO.adminDTO"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<!--차트-->
<script src="https://d3js.org/d3.v3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>
<!--차트css-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/main/total_cate_contents.css">
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
<%	adminDTO admin = (adminDTO)session.getAttribute("admin");%>

<%
	if(admin==null){
%>
<script>
	alert('잘못된 접근입니다!');
	location.href='${pageContext.request.contextPath}/admin/adminIndex.jsp';
</script>
<%	}else{%>
	<!--상단-->
	<jsp:include page="adminHeader.jsp" flush="false"/>
	<!--contents-->
    <div class="cont_wrap">
        <div class="box_wrap">
            <div class="left_box">
                <div class="box_bg01">
                    <!--상품등록-->
                    <div class="cont_box box01">
                        <div class="title_box">
                            <p class="cont_title">
                                신규 상품을 확인해주세요!
                            </p>
                        </div>
                        <p class="cont_text">새로 들어온 상품을 확인하고 판매를 시작해주세요.</p>
                        <a href="#" class="cont_btn">상품 확인하기</a>
                    </div>
                    <!--주문관리-->
                    <div class="cont_box box02">
                        <div class="title_box">
                            <p class="cont_title">
                                주문관리
                            </p>
                            <span>주문건을 확인해주세요</span>
                            <a href="#" class="more_view">전체보기 ></a>
                        </div>
                        <table class="order_table">
                            <thead>
                                <tr>
                                    <th class="num">번호</th>
                                    <th class="item_name">상품명</th>
                                    <th class="item_price">가격</th>
                                    <th>총주문건</th>
                                    <th>주문번호</th>
                                    <th>상품준비</th>
                                    <th>배송중</th>
                                    <th>배송완료</th>
                                    <th>교환/환불</th>
                                    <th>구매확정</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var ="o" items="${orderList}" varStatus="status" >
                            		<c:set var="today" value="<%=new java.util.Date()%>" />
									<c:set var="date"><fmt:formatDate value="${today}" pattern="yyyy-MM-dd" /></c:set> 
                            		<c:if test="${o.regDt > date}">
                            			<c:if test="${status.index < 3}">
			                                <tr>
			                                    <td class="num">${status.count}</td>
			                                    <td class="item_name">${o.orderNo}</td>
			                                    <td class="item_price"><fmt:formatNumber value="${o.settlePrice}" type="number"/></td>
			                                    <td class="order_count">0</td>
			                                    <td class="check check_01">0</td>
			                                    <td class="check check_02">0</td>
			                                    <td class="check check_03">0</td>
			                                    <td class="check check_04">0</td>
			                                    <td class="check check_05">0</td>
			                                    <td class="check check_06">0</td>
			                                </tr>		
		                                </c:if>
                            		</c:if>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!--공지사항-->
                <div class="box_bg02">
                    <div class="cont_box box03">
                        <div class="title_box">
                            <p class="cont_title">문의사항</p>
                            <span id="qa01" class="pro">올디프</span>&nbsp;
                            <span id="qa02">파트너스</span>
                            <a href="./board/boardList" class="more_view">전체보기 ></a>
                        </div>
                        <ul class="notice_list" id="qaList01">
                        	<c:choose>
                        		<c:when test="${!empty b}">
		                        	<c:forEach var="b" items="${b}" varStatus="status">
		                        			<c:if test="${status.count < 6 && b.partnersFl == false}">
		                        				<li><a href="#">[${b.cate}] ${b.title}</a></li>
		                        			</c:if>
		                        	</c:forEach>                        		
                        		</c:when>
                        		<c:otherwise>
									<li>등록된 문의사항이 없습니다.</li>
                        		</c:otherwise>
                        	</c:choose>
                        </ul>
                    	<ul class="notice_list02" id="qaList02">
                        	<c:choose>
                        		<c:when test="${!empty b}">
		                        	<c:forEach var="b" items="${b}" varStatus="status">
		                        			<c:if test="${status.count < 6 && b.partnersFl == true}">
		                        				<li><a href="#">[${b.cate}] ${b.title}</a></li>
		                        			</c:if>
		                        	</c:forEach>                        		
                        		</c:when>
                        		<c:otherwise>
									<li>등록된 문의사항이 없습니다.</li>
                        		</c:otherwise>
                        	</c:choose>
                    	</ul>
                    </div>
                </div>
                <script>
                
                	//문의글
                	const qa01 = document.getElementById("qa01"),
                		  qa02 = document.getElementById("qa02"),
                		  qaList01 = document.getElementById("qaList01"),
                		  qaList02 = document.getElementById("qaList02");
                	
                	qa01.addEventListener('click',() => {
                		qaList01.style.display = "block";
                		qaList02.style.display = "none";
                	});
                	
                	qa02.addEventListener('click',() => {
                		qaList01.style.display = "none";
                		qaList02.style.display = "block";
                	});
                	
                </script>
            </div>
            <div class="right_box">
                <!--사업자인증-->
                <div class="box_bg03">
                    <div class="cont_box box04">
                        <div class="title_box">
                            <p class="cont_title">
                                오늘의 회원
                                <a href="">더 보기 ></a>
                            </p>
                        </div>
                        <table >
                            <tr>
                                <td>총 회원수</td>
                                <td>신규회원수</td>
                                <td>휴먼회원수</td>
                                <td>탈퇴회원수</td>
                            </tr>
                            <tr>
                                <td>${allCount}</td>
                                <td>${newCount}</td>
                                <td>${sleepCount}</td>
                                <td>${deleteCount}</td>
                            </tr>
                        </table>
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
                                오늘의 일정
                                <a href="">일정추가 +</a>
                            </p>
                            <div class="title_bottom">
                                <p>05.07</p>
                                
                            </div>
                        </div>
                        <div class="schedule">
                            오늘의 일정이 없습니다.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
    /*차트그래프*/	
	function getToday(i){
	    var date = new Date();
	    var year = date.getFullYear();
	    var month = 1 + date.getMonth();
	    var day = date.getDate()-i;
	    
	    if (month < 10) { month = "0" + month; } if (day < 10) { day = "0" + day; }


	    return month + "-" + day;
	}
    
    function getPrice(i,j){ 		 
	    var settlePrice = 0;	 
	    var s = j;
	    if(s == 0){
	    	s = "주문완료";
	    }else{
	    	s = "환불";
	    }
	    
	    var sp = "";
	    
	    $.ajax({
	    	type:"post",
	    	url:"adminSalesStatus",
	    	data:{
	    		no : i,
	    		status : s,
	    	},
	    	dataType:"json",
	    	async: false, //값을 리턴시 해당코드를 추가하여 동기로 변경
	    	success:function(result){
	 			sp = result;
	    	},
	    	error:function(){
	    		console.log('통신장애');
	    	}
	    });

	    return sp;
    }
	
    var chart = c3.generate({
        data: {
            colors: {
                매출: '#ff0000',
                환불: 'orange'
            },
            x: 'x',
            columns: [
            	
                /*x축*/
                ['x', getToday(6), getToday(5), getToday(4), getToday(3), getToday(2), getToday(1), getToday(0)],
                
                
                
                /*매출 그래프*/
                ['매출', getPrice(5,0),getPrice(4,0),getPrice(3,0),getPrice(2,0),getPrice(1,0),getPrice(0,0),getPrice(-1,0)],
                
                
                
                /*환불 그래프*/
                ['환불', getPrice(5,1),getPrice(4,1),getPrice(3,1),getPrice(2,1),getPrice(1,1),getPrice(0,1),getPrice(-1,1)]
            ]
        },
        axis: {
            x: {
                type: 'category',
                categories: ['cat1', 'cat2', 'cat3', 'cat4', 'cat5', 'cat6', 'cat7'],
            }
        }
    });
    </script>
    <!--//contents-->
	<!--하단-->
	<jsp:include page="adminFooter.jsp" flush="false"/>
<%	}%>