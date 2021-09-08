<%@page import="dto.brandDTO.brandDTO"%>
<%@page import="dto.categoryDTO.categoryDTO"%>
<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<link href="${pageContext.request.contextPath}/admin/css/goods/total_productlist.css" rel="stylesheet" type="text/css">
<%
	String goodsStatus = (String)request.getAttribute("goodsStatus");
	String kind = (String)request.getAttribute("kind");
	String keyword = (String)request.getAttribute("keyword");
	String minDate = (String)request.getAttribute("minDate");
	String maxDate = (String)request.getAttribute("maxDate");
	String cateCd = (String)request.getAttribute("cateCd");
	brandDTO brand = (brandDTO)request.getAttribute("brand");
	int minPrice = (Integer)request.getAttribute("minPrice");
	int maxPrice = (Integer)request.getAttribute("maxPrice");
	String search = (String)request.getAttribute("search");
	int limit = (Integer)request.getAttribute("limit");

	categoryDTO cate1 = (categoryDTO)request.getAttribute("c1");
	categoryDTO cate2 = (categoryDTO)request.getAttribute("c2");
	categoryDTO cate3 = (categoryDTO)request.getAttribute("c3");
%>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>

	 <div class="cont_wrap">

        <div class="search_cont">
        <form action="./goodsList" method="post">
            <div class="sch_tit">
              <h2>상품 리스트</h2> 
              <button type="reset">초기화</button>
              <button>검색</button> 
            </div>
            <div class="sch_ck_cont">
            	<table>
            		<thead class="filter">
	            		<tr>
	            			<th>검색어</th>
	            			<td>
		            			<select name="kind">
			                    	<option value="전체" <%if(kind != null && kind.equals("전체")){ %>selected = "selected"<%} %>>전체</option>
			                        <option value="상품명"<%if(kind != null && kind.equals("상품명")){ %>selected = "selected"<%} %>>상품명</option>
			                        <option value="상품코드"<%if(kind != null && kind.equals("상품코드")){ %>selected = "selected"<%} %>>상품코드</option>
			                        <option value="공급사명"<%if(kind != null && kind.equals("공급사명")){ %>selected = "selected"<%} %>>공급사명</option>
			                        <option value="키워드"<%if(kind != null && kind.equals("키워드")){ %>selected = "selected"<%} %>>키워드</option>
			                    </select>
			                    <input type="text" name="keyword" value=<%=(keyword != null)?keyword:""%>>
	            			</td>
	            		</tr>
	            		<tr>
	            			<th>기간검색</th>
	            			<td>
	            				<div class="goodsListDate">							
				            		<input type='text' name="minDate" id="search_start_date" value=<%=(minDate != null)?minDate:""%>> ~ <input type='text' name="maxDate" id="search_end_date" value=<%=(maxDate != null)?maxDate:""%>>
				                    <ul class="daySearch" id="srch_area">
				                        <li><button id="r_yes" name="yesterday">어제</button></li>
				                        <li><button id="r_today" name="today">오늘</button></li>
				                        <li><button id="r_week" name="week">1주일</button></li>
				                        <li><button id="r_month" name="month">1개월</button></li>
				                        <li><button id="r_3month" name="3month">3개월</button></li>
				                        <li><button id="r_all" name="allday">전체</button></li>
				                    </ul>
			                    </div>
	            			</td>
	            		</tr>
            		</thead>
            		<tbody id="subFilter">            		
	            		<tr>
	            			<th>카테고리 선택</th>
	            			<td>
	            			    <select name="cate" id="large" <%if(cate1 != null && cate1.getCateCd() != null){%> onclick="cateAction()" <%}%>  onchange="cateCheck()">
				                   <option value=<%=(cate1 != null && cate1.getCateCd() != null)? cate1.getCateCd() : ""%>><%=(cate1 != null && cate1.getCateNm() != null)? cate1.getCateNm() : "대분류"%></option>
				                </select>
				                <select name="cate2" id="middle" <%if(cate1 != null && cate1.getCateCd() != null){%> onclick="cateMiddle('<%=cate1.getCateCd() %>')" <%}%>  onchange="cateCheck2()">
				                   <option value=<%=(cate2 != null && cate2.getCateCd() != null)? cate2.getCateCd() : ""%>><%=(cate2 != null && cate2.getCateNm() != null)? cate2.getCateNm() : "중분류"%></option>
				                </select>
				                <select name="cate3" id="small" <%if(cate2 != null && cate2.getCateCd() != null){%> onclick="cateSmall('<%=cate2.getCateCd() %>')" <%}%> >
				                   <option value=<%=(cate3 != null && cate3.getCateCd() != null)? cate3.getCateCd() : ""%>><%=(cate3 != null && cate3.getCateNm() != null)? cate3.getCateNm() : "소분류"%></option>
				                </select>
	            			</td>
	            		</tr>
	            		<tr>
	            			<th>브랜드</th>
	            			<td>
	            				<select name="brand" id="brand" onclick="brandList()">
			                        <option value=<%=(brand != null)?brand.getBrandCd():""%>><%=(brand != null)?brand.getBrandNm():"브랜드선택"%></option>
			                    </select>
	            			</td>
	            		</tr>
	            		<tr>
	            			<th>판매가</th>
	            			<td>
								<input type="text" name="minPrice" value=<%=(minPrice >= 0)?minPrice:""%>>
			                    이상 ~
			                    <input type="text" name="maxPrice" value=<%=(maxPrice > 0)?maxPrice:""%>>
			                    이하
	            			</td>
	            		</tr>
	            		<tr>
	            			<th>상품상태</th>
	            			<td>
	            				<label for="ra04"><input type="radio" name="goodsStatus" value="전체" <%if(goodsStatus != null && goodsStatus.equals("전체")){ %>checked = "checked"<%} %>>전체</label>
			                    <label for="ra05"><input type="radio" name="goodsStatus" value="품절" <%if(goodsStatus != null && goodsStatus.equals("품절")){ %>checked = "checked"<%} %>>품절</label>
			                    <label for="ra06"><input type="radio" name="goodsStatus" value="삭제" <%if(goodsStatus != null && goodsStatus.equals("삭제")){ %>checked = "checked"<%} %>>삭제상품</label>
			                    <label for="ra06"><input type="radio" name="goodsStatus" value="정상" <%if(goodsStatus != null && goodsStatus.equals("정상")){ %>checked = "checked"<%} %>>정상</label>
	            			</td>
	            		</tr>
            		</tbody>
            	</table>				
                <div class="subFilter"><span id="subFilterOn"> 상세검색 펼침 </span></div>
                <script>                
                	const subFilterOn = document.getElementById('subFilterOn'),
                		  subFilter = document.getElementById('subFilter');
                	
                	subFilterOn.addEventListener('click', () => {
                		subFilter.classList.toggle('on');
                		if(subFilter.getAttribute('class','on')){
	                		subFilterOn.innerHTML = "상세검색 닫기";                			
                		}else{
                			subFilterOn.innerHTML = "상세검색 펼침";
                		}
                	});
                                
                </script>
            </div> 
        </form>    
        </div>
        <div class="sch_result">
            <h3>상품 검색결과 <p>${listCount}건</p> </h3>
            <div class="sch_btn">
                <div class="btn_left">
                 <button onclick="deleteFl()">선택삭제</button>  
                </div>
                <div class="btn_right">
                    <button><i class="far fa-file-excel"></i> 엑셀 다운로드</button>
                    <select onchange="againSearch2()">
                        <option value="order by regDt desc" <%if(search != null && search.equals("order by regDt desc")){ %>selected = "selected"<%} %>>등록일 ↑</option>
                        <option value="order by regDt" <%if(search != null && search.equals("order by regDt")){ %>selected = "selected"<%} %>>등록일 ↓</option>
                        <option value="order by orderCnt desc" <%if(search != null && search.equals("order by orderCnt desc")){ %>selected = "selected"<%} %>>주문량 ↑</option>
                        <option value="order by orderCnt" <%if(search != null && search.equals("order by orderCnt")){ %>selected = "selected"<%} %>>주문량 ↓</option>
                        <option value="order by hitCnt desc" <%if(search != null && search.equals("order by hitCnt desc")){ %>selected = "selected"<%} %>>조회 ↑</option>
                        <option value="order by hitCnt" <%if(search != null && search.equals("order by hitCnt")){ %>selected = "selected"<%} %>>조회 ↓</option>
                        <option value="order by reviewCnt desc" <%if(search != null && search.equals("order by reviewCnt desc")){ %>selected = "selected"<%} %>>상품평 ↑</option>
                        <option value="order by reviewCnt" <%if(search != null && search.equals("order by reviewCnt")){ %>selected = "selected"<%} %>>상품평 ↓</option>
                    </select>
                    <select onchange="againSearch()">
                        <option value="10"   <%if(limit == 10){ %>selected = "selected"<%} %>>10개보기</option>
                        <option value="20"   <%if(limit == 20){ %>selected = "selected"<%} %>>20개보기</option>
                        <option value="30"   <%if(limit == 30){ %>selected = "selected"<%} %>>30개보기</option>
                        <option value="50"   <%if(limit == 50){ %>selected = "selected"<%} %>>50개보기</option>
                        <option value="100"  <%if(limit == 100){ %>selected = "selected"<%} %>>100개보기</option>
                        <option value="200"  <%if(limit == 200){ %>selected = "selected"<%} %>>200개보기</option>
                        <option value="300"  <%if(limit == 300){ %>selected = "selected"<%} %>>300개보기</option>
                        <option value="400"  <%if(limit == 400){ %>selected = "selected"<%} %>>400개보기</option>
                        <option value="500"  <%if(limit == 500){ %>selected = "selected"<%} %>>500개보기</option>
                        <option value="1000" <%if(limit == 1000){ %>selected = "selected"<%} %>>1000개보기</option>
                    </select>  
                </div>
                
            </div>
            <table>
	            <colgroup>
					<col width="5%"/>
					<col width="5%"/>
					<col width="10%"/>
					<col width="5%"/>
					<col width="35%"/>
					<col width="5%"/>
					<col width="5%"/>
					<col width="5%"/>
					<col width="5%"/>
					<col width="5%"/>
					<col width="10%"/>
					<col width="5%"/>
				</colgroup>
                <tr>
                    <th><input type="checkbox" name="ck01" class="ck" id="ck_all"> </th>
                    <th>번호</th>
                    <th>상품코드</th>
                    <th>이미지</th>
                    <th>상품명</th>
                    <th>판매가</th>
                    <th>공급사</th>
                    <th>노출상태</th>
                    <th>판매상태</th>
                    <th>재고</th>
                    <th>등록일/수정일</th>
                    <th>수정</th>
                </tr>
                <c:forEach var="g" items="${gd}" varStatus="status">                
	                <tr>
	                    <td><input type="checkbox" name="ck" class="ck" id="ck01" value="${g.goodsNo}"></td>
	                    <td>${status.count}</td>
	                    <td>${g.goodsNo}</td>
	                    <td><img src="${g.representImg}"></td>
	                    <td>${g.goodsNm}</td>
	                    <td><fmt:formatNumber value="${g.fixedPrice}" type="number"/>원</td>
	                    <td>${g.scmNo}</td>
	                    <td>
	                    	<c:choose>
		                    	<c:when test="${g.delFl == false}">
		                    		노출중
		                    	</c:when>
		                    	<c:otherwise>
		                    		미노출
		                    	</c:otherwise>
	                    	</c:choose>  
	                    </td>
	                    <td>
	                    	<c:choose>
		                    	<c:when test="${g.goodsSellFl == 0}">
		                    		판매중지
		                    	</c:when>
		                    	<c:when test="${g.goodsSellFl < 0}">
		                    		판매중지(관리자 제한)
		                    	</c:when>
		                    	<c:otherwise>
		                    		판매중
		                    	</c:otherwise>
	                    	</c:choose>      	
	                    </td>
	                    <td>${g.totalStock}</td>
	                    <td><fmt:formatDate value="${g.regDt}" pattern="yyyy-MM-dd"/><c:if test="${g.modDt != null }"> / <br/><fmt:formatDate value="${g.modDt}" pattern="yyyy-MM-dd"/></c:if></td>
	                    <td><input type="button" value="수정" onclick="goodsMod(${status.index},${g.goodsNo},${paging.page})"></td>
	                </tr>
                </c:forEach>
            </table>
        </div>
       	<div style="margin: 30px 0; text-align: center;">
			<c:if test="${paging.page<=1 }"></c:if>
			<c:if test="${paging.page>1 }">
				<a href="goodsList?page=${paging.page-1}&keyword=${keyword}&minDate=${minDate}&maxDate=${maxDate}&cateCd=${cateCd}&brandCd=${brandCd}&minPrice=${minPrice}&maxPrice=${maxPrice}&goodsStatus=${goodsStatus}">[이전]</a>&nbsp;+
			</c:if> 
			<c:forEach begin="${paging.startPage}" end="${paging.endPage }" var="i" step="1">
				<c:choose>
					<c:when test="${i eq paging.page }">	
						[${i}]								
					</c:when>
					<c:otherwise>
						<a href="goodsList?page=${i}&kind=${kind}&keyword=${keyword}&minDate=${minDate}&maxDate=${maxDate}&cateCd=${cateCd}&brandCd=${brandCd}&minPrice=${minPrice}&maxPrice=${maxPrice}&goodsStatus=${goodsStatus}">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.page>=paging.maxPage}"></c:if>
			<c:if test="${paging.page<paging.maxPage}">
				<a href="goodsList?page=${paging.page+1}&keyword=${keyword}&minDate=${minDate}&maxDate=${maxDate}&cateCd=${cateCd}&brandCd=${brandCd}&minPrice=${minPrice}&maxPrice=${maxPrice}&goodsStatus=${goodsStatus}">&nbsp;[다음]</a>
			</c:if>
		</div>
    </div>
<script src="${pageContext.request.contextPath }/admin/js/common/category.js"></script>
<script src="${pageContext.request.contextPath }/admin/js/common/brand.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/common/day.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/common/checkbox.js"></script>
<script> 
<%if(cate1 == null){%>
 window.onload= function(){
  	cateAction();
 }
<%}%>
	  
function goodsMod(i,no,page){
	location.href="goodsMod?goodsNo="+no+"&page="+page+"";
}  

function againSearch(){
	var i = event.target.value;
	location.href="goodsList?page=${paging.page}&keyword=${keyword}&minDate=${minDate}&maxDate=${maxDate}&cateCd=${cateCd}&brandCd=${brandCd}&minPrice=${minPrice}&maxPrice=${maxPrice}&goodsStatus=${goodsStatus}&search=${search}&limit="+i+"";
}

function againSearch2(){
	var i = event.target.value;
	location.href="goodsList?page=${paging.page}&keyword=${keyword}&minDate=${minDate}&maxDate=${maxDate}&cateCd=${cateCd}&brandCd=${brandCd}&minPrice=${minPrice}&maxPrice=${maxPrice}&goodsStatus=${goodsStatus}&limit=${limit}&search="+i+"";
}

//삭제
function deleteFl(){
	var check = document.querySelectorAll('input[name="ck"]:checked');
	if(check.length > 0){		    	
		var val = [];
		for(var i = 0; i < check.length; i++){
			val.push(check[i].value);
		}
		
		var d = confirm("선택한"+check.length+"개를 삭제하시겠습니가?");
		if(d){
			$.ajax({
				type:"post",
				url:"./delete",
				traditional : true,	//ajax 배열 보는법
				data: {
					goodsNo:val
				},
				dateType:"json",
				success:function(i){
					if(i == 1){
						alert("삭제되었습니다.");
						location.reload();
					}else{
						alert("삭제 실패");
					}
				},
				error:function(){
					alert('삭제실패(통신장애)')
				}
				
			});
		}
	}else{
		alert("삭제할 상품을 선택해주세요.");
	}
}
</script>