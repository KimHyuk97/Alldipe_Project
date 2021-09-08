<%@page import="dto.goodsDTO.goodsDTO"%>
<%@page import="java.util.*"%>
<%@page import="dto.themeDTO.themeDTO"%>
<%@page import="dto.brandDTO.brandDTO"%>
<%@page import="dto.categoryDTO.categoryDTO"%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/goods/total_page.css">
<%
	String mode = (String)request.getAttribute("mode");
	categoryDTO cate1 = (categoryDTO)request.getAttribute("cate1");
	categoryDTO cate2 = (categoryDTO)request.getAttribute("cate2");
	categoryDTO cate3 = (categoryDTO)request.getAttribute("cate3");
	categoryDTO cate = (categoryDTO)request.getAttribute("cateList");
	
	brandDTO brand = (brandDTO)request.getAttribute("brand");
	themeDTO theme = (themeDTO)request.getAttribute("theme");
	int p = (Integer)request.getAttribute("page");
	ArrayList<goodsDTO> cd = (ArrayList)request.getAttribute("categoryRecomGoodsList");
%>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
<div class="cont_wrap">
     <input type="hidden" name="page" value="${page}">
     <div class="top_tit01">
         <h2>상품분류</h2>
     </div>
     <div class="nav">
         <ul>
             <li value="0" onclick="CB()" >카테고리 페이지 관리</li>
             <li value="1" onclick="CB()" >브랜드 페이지 관리</li>
             <li value="2" onclick="CB()" >테마 관리</li>
         </ul>
         <button type="button" onclick="search(${page})">검색</button>
         <button type="reset">초기화</button>
     </div>
<%if(p == 0){%>
     <section class="<%if(p == 0){%>content01 on<%}else{%>content01<%}%>" id="content01">
	 	<div class="top_tit">
         	<div class="top_tit02">
	           	<p>카테고리 선택</p>
    	        <select name="cate" id="large" <%if(cate1 != null && cate1.getCateCd() != null){%> onclick="cateAction()" <%}%>  onchange="cateCheck()">
                   <option value=<%=(cate1 != null && cate1.getCateCd() != null)? cate1.getCateCd() : ""%>><%=(cate1 != null && cate1.getCateNm() != null)? cate1.getCateNm() : "대분류"%></option>
                </select>
                <select name="cate2" id="middle" <%if(cate1 != null && cate1.getCateCd() != null){%> onclick="cateMiddle('<%=cate1.getCateCd() %>')" <%}%>  onchange="cateCheck2()">
                   <option value=<%=(cate2 != null && cate2.getCateCd() != null)? cate2.getCateCd() : ""%>><%=(cate2 != null && cate2.getCateNm() != null)? cate2.getCateNm() : "중분류"%></option>
                </select>
                <select name="cate3" id="small" <%if(cate2 != null && cate2.getCateCd() != null){%> onclick="cateSmall('<%=cate2.getCateCd() %>')" <%}%> >
                   <option value=<%=(cate3 != null && cate3.getCateCd() != null)? cate3.getCateCd() : ""%>><%=(cate3 != null && cate3.getCateNm() != null)? cate3.getCateNm() : "소분류"%></option>
                </select>
		     </div>
		     <div class="top_tit02">
            	<p>카테고리 생성</p>
	     	    <button type="button" onclick="cateAdd(0)">1차 카테고리추가</button>
                <button type="button" onclick="cateAdd(1)">2차 카테고리추가</button>
                <button type="button" onclick="cateAdd(2)">3차 카테고리추가</button>
		     </div>
     	</div>       	 
         <div class="cont_wrap_box <%if(mode != null && mode != ""){%>on<%}else{%>off<%}%>">
         	 <%if(mode.equals("수정") && cate != null) {%>
         	 <div class="nav">
         	 	<button type="button" onclick="del(<%=p%>)">삭제</button>
         	 	<button type="button" onclick="mod(<%=p%>)">수정</button>
         	 	<input type="hidden" name="cateSno" value=<%=cate.getSno() %>>
         	 	<input type="hidden" name="cateCd" value=<%=cate.getCateCd() %>>
         	 </div>
         	 <%}else if(mode.equals("대분류추가") || mode.equals("중분류추가") || mode.equals("소분류추가")){%>
         	 <div class="nav">
         	 	<button type="button" onclick="add(<%=p%>)">생성</button>
         	 </div>
         	 <%} %>
         	 
         	 <%if(mode.equals("대분류추가")){ %>
         	 <input type="hidden" name="cateCd">
         	 <%}else if(mode.equals("중분류추가")){%>
             <div class="cont_box">
                <p>상위 카테고리</p>
                <input type="text" value=<%=(cate1 != null && cate1.getCateNm() != null)? cate1.getCateNm():""%> readonly>
                <input type="hidden" name="cateCd" value=<%=(cate1 != null && cate1.getCateCd() != null)?cate1.getCateCd():""%>>
             </div>         	 
         	 <%}else if(mode.equals("소분류추가")){%>
         	 <div class="cont_box">
                <p>상위 카테고리</p>
                <input type="text" value=<%=(cate1 != null && cate1.getCateNm() != null)? cate1.getCateNm():""%> readonly>
                <input type="text" value=<%=(cate2 != null && cate2.getCateNm() != null)? cate2.getCateNm():""%> readonly>
                <input type="hidden" name="cateCd" value=<%=(cate2 != null && cate2.getCateCd() != null)?cate2.getCateCd():""%>>
             </div>	
         	 <%} %>
         	 
             <div class="cont_box">
                <p>카테고리명</p>
                <input type="text" name="cateNm" value=<%=(cate != null)?cate.getCateNm() : "" %>>
                <br><br>
                <span>* 기본적으로 입력된 텍스트로 해당 카테고리 보여집니다.카테고리를 이미지로 노출하려면 아래 "카테고리 이미지등록"항목에 등록하세요.</span>
             </div>
             <div class="cont_box">
                <p>노출여부</p> 
                <label><input type="radio" name="displayFl" value="true" <%if(cate != null && cate.getCateDisplayFl()){ %>checked="checked"<%}else{ %>checked="checked"<%} %> >사용함</label>
                <label><input type="radio" name="displayFl" value="false" <%if(cate != null && !cate.getCateDisplayFl()){ %>checked="checked"<%} %>>사용안함</label>
             </div>
             <div class="cont_box">
                <p>카테고리 수수료</p> 
                <input type="text" name="commission" value=<%=(cate != null && cate.getCommission() != null)?cate.getCommission() : "" %>>
             </div>
             <div class="cont_box">
                <p>키워드</p> 
                <input type="text" name="keyword" value=<%=(cate != null && cate.getKeyword() != null)?cate.getKeyword() : "" %>>
             </div>
             <div class="cont_box">
                 <p>이미지 등록</p> 
                 <input type="file" name="file" src=<%=(cate != null && cate.getCateImg() != null)?cate.getCateImg() : "" %> alt="">
              </div>
              <div class="cont_box">
                 <p>마우스오버 이미지 등록</p> 
                 <input type="file" name="overFile" src=<%=(cate != null && cate.getCateOverImg() != null)?cate.getCateOverImg():"" %> alt="">
              </div>
              
              <div class="cont_box">
                 <p>성인인증 여부</p> 
                 <label><input type="radio" name="adultFl" <%if(cate != null && cate.getCateOnlyAdultFl()){ %> checked="checked" <%}else{ %>checked="checked"<%} %> value="true"> 사용함</label>
                 <label><input type="radio" name="adultFl" <%if(cate != null && !cate.getCateOnlyAdultFl()){ %> checked="checked" <%} %> value="false"> 사용안함</label>
                 <label><input type="checkbox" name="adultDisplayFl" value="true" <%if(cate != null && cate.getCateOnlyAdultDisplayFl()){ %> checked="checked" <%} %>>(미인증 고객 카테고리 노출함)</label>
                 <br> <br>
                 <span>* 해당 카테고리의 상품리스트 페이지 접근시 성인인증확인 인트로 페이지가 출력되어 보여집니다. 성인인증 기능은 별도의 인증 서비스 신청완료 후 이용 가능합니다.</span>
              </div>
              <div class="cont_box">
                 <p>추천상품</p> 
                 <br>
                 <div class="btn_position">
                 	<div class="btn_left">
                    	<button type="button" onclick="goodsDisplay('maxDown')">맨아래로</button>
                    	<button type="button" onclick="goodsDisplay('down')">아래로</button>
                    	<button type="button" onclick="goodsDisplay('up')">위로</button>
                    	<button type="button" onclick="goodsDisplay('maxUp')">맨위로</button>
                 	</div>
	                <div class="btn_right">
	                	<button type="button" onclick="goodsModal()">상품선택</button>
	                    <button type="button" onclick="goodsdel()">선택삭제</button>
	                </div>	
                 </div>  
                 <table>
                 	 <colgroup>
                 	 	<col width="5%"/>
                 	 	<col width="5%"/>
                 	 	<col width="10%"/>
                 	 	<col width="35%"/>
                 	 	<col width="10%"/>
                 	 	<col width="5%"/>
                 	 	<col width="5%"/>
                 	 	<col width="5%"/>
                 	 </colgroup>
                     <tr>
                         <th><input type="checkbox" class="ck" id="ck_all"> </th>
                         <th>번호</th>
                         <th>이미지</th>
                         <th>상품명</th>
                         <th>판매가</th>
                         <th>공급사</th>
                         <th>재고</th>
                         <th>판매여부</th>
                     </tr>
                     <%if(cd != null){ %>
                    	<tbody id="requestGoodsData">
                     		<%for(int i = 0; i < cd.size(); i++ ){ %>
                     			<tr id="tr<%=cd.get(i).getGoodsNo()%>">
                     				<td><input type="checkbox" name="recomGoodsNo" class="ck" id="ck<%=cd.get(i).getGoodsNo()%>" value="<%=cd.get(i).getGoodsNo()%>"></td>
                     				<td><%=(i+1)%></td>
                     				<td><%=cd.get(i).getRepresentImg() %></td>
                     				<td><%=cd.get(i).getGoodsNm() %></td>
                     				<td><%=cd.get(i).getFixedPrice() %></td>
                     				<td><%=cd.get(i).getScmNo() %></td>
                     				<td><%=(cd.get(i).getTotalStock() <= 0)?"품절": cd.get(i).getTotalStock()%></td>
                     				<td><%=(cd.get(i).getGoodsSellFl() == 0)?"판매중지":"판매중"%></td>
                     			</tr>
                     		<%} %>
	                     </tbody>
                     <%}else{ %>
						<tbody id="requestGoodsData"></tbody>
                     <%} %>
                 </table>
              </div>
         </div>
     </section>
<%}else if(p == 1){ %>
     <section class="<%if(p == 1){%>content01 on<%}else{%>content01<%}%>" id="content02">
         <div class="top_tit">             
             <div class="top_tit02">
            	<p>브랜드 선택</p>
	     	    <select name="brand" id="brand" onclick="brandList()">
                 <option value=<%=(brand != null)?brand.getBrandCd():""%>><%=(brand != null)?brand.getBrandNm():"브랜드선택"%></option>
             </select>
		     </div>
             
             <div class="top_tit02">
            	<p>브랜드 생성</p>
	     	    <button type="button" onclick="brandAdd()">브랜드 추가</button>
		     </div>
         </div>
         <div class="cont_wrap_box <%if(mode != null && mode != ""){%>on<%}else{%>off<%}%>">
	         <%if(mode.equals("수정") && brand != null) {%>
         	 <div class="nav">
         	 	<button type="button" onclick="del(<%=p%>)">삭제</button>
         	 	<button type="button" onclick="mod(<%=p%>)">수정</button>
         	 	<input type="hidden" name="brandSno" value=<%=brand.getSno() %>>
         	 </div>
         	 <%}else if(mode.equals("브랜드추가")){%>
         	 <div class="nav">
         	 	<button type="button" onclick="add(<%=p%>)">생성</button>
         	 </div>
         	 <%} %>
             <div class="cont_box">
                <p>브랜드 명</p> 
                <input type="text" name="brandNm" value=<%=(brand != null && brand.getBrandNm() != null)?brand.getBrandNm():"" %>>
                <br>
                <span>* 기본적으로 입력된 텍스트로 해당 브랜드 보여집니다.브랜드 이미지로 노출하려면 아래 "브랜드 이미지등록"항목에 등록하세요.</span>
             </div>
             <div class="cont_box">
                <p>키워드</p> 
                <input type="text" name="keyword" value=<%=(brand != null && brand.getKeyword() != null)?brand.getKeyword() : "" %>>
             </div>
             <div class="cont_box">
                 <p>이미지 등록</p> 
                 <input type="file" name="file" src=<%=(brand != null && brand.getBrandImg() != null)?brand.getBrandImg():"" %> alt="">
             </div>
         </div>
     </section>
<%}else if(p == 2) {%>
     <section class="<%if(p == 2){%>content01 on<%}else{%>content01<%}%>" id="content03">
         <div class="top_tit">         
             <div class="top_tit02">
            	<p>테마 선택</p>
	     	     <select name="theme" id="theme" onclick="themeList()"> 
	                 <option value=<%=(theme != null)?theme.getSno() : ""%>><%=(theme != null)?theme.getThemeNm():"테마선택"%></option>
	             </select>
		     </div>
             
             <div class="top_tit02">
            	<p>테마 생성</p>
	     	     <button type="button" onclick="themeAdd()">테마 추가</button>
		     </div>
         </div>
         <div class="cont_wrap_box <%if(mode != null && mode != ""){%>on<%}else{%>off<%}%>">
	         <%if(mode.equals("수정") && theme != null) {%>
         	 <div class="nav">
         	 	<button type="button" onclick="del(<%=p%>)">삭제</button>
         	 	<button type="button" onclick="mod(<%=p%>)">수정</button>
         	 	<input type="hidden" name="themeSno" value=<%=theme.getSno() %>>
         	 </div>
         	 <%}else if(mode.equals("테마추가")){%>
         	 <div class="nav">
         	 	<button type="button" onclick="add(<%=p%>)">생성</button>
         	 </div>
         	 <%} %>
             <div class="cont_box">
                <p>테마 명</p> 
                <input type="text" name="themeNm" value=<%=(theme != null && theme.getThemeNm() != null)?theme.getThemeNm():"" %>>
                <br>
                <span>* 기본적으로 입력된 텍스트로 해당 테마 보여집니다. 테마 이미지로 노출하려면 아래 "테마 이미지등록" 항목에 등록하세요.</span>
             </div>
             <div class="cont_box">
                <p>테마 설명</p> 
                <input type="text" name="themeDescription" value=<%=(theme != null && theme.getThemeDescription() != null)?theme.getThemeDescription():"" %>>
             </div>
             <div class="cont_box">
                <p>연결 컨텐츠 분류</p> 
                <label><input type="radio" class="relation" name="relation" onclick="relationClassify()" value="0" <%if(theme != null && theme.getRelationCd().contains("B") == false){ %>checked = "checked"<%} %>>카테고리</label>
                <label><input type="radio" class="relation" name="relation" onclick="relationClassify()" value="1" <%if(theme != null && theme.getRelationCd().contains("B") == true){ %>checked = "checked"<%} %>>브랜드</label>
                <label><input type="radio" class="relation" name="relation" onclick="relationClassify()" value="2" <%if((theme != null && theme.getRelationCd().equals("")) || theme == null){ %>checked = "checked"<%}%>>없음</label>
             </div>
             <div class="cont_box">
                <p>연결 컨텐츠 선택</p> 	
                	<div id="relation01">
		                <p>카테고리 선택</p>
           			    <select name="cate" id="large" <%if(cate1 != null && cate1.getCateCd() != null){%> onclick="cateAction()" <%}%>  onchange="cateCheck()">
		                   <option value=<%=(cate1 != null && cate1.getCateCd() != null)? cate1.getCateCd() : ""%>><%=(cate1 != null && cate1.getCateNm() != null)? cate1.getCateNm() : "대분류"%></option>
		                </select>
		                <select name="cate2" id="middle" <%if(cate1 != null && cate1.getCateCd() != null){%> onclick="cate2(<%=cate1.getCateCd() %>)" <%}%>  onchange="cateCheck2()">
		                   <option value=<%=(cate2 != null && cate2.getCateCd() != null)? cate2.getCateCd() : ""%>><%=(cate2 != null && cate2.getCateNm() != null)? cate2.getCateNm() : "중분류"%></option>
		                </select>
		                <select name="cate3" id="small" <%if(cate2 != null && cate2.getCateCd() != null){%> onclick="cate3(<%=cate2.getCateCd() %>)" <%}%> >
		                   <option value=<%=(cate3 != null && cate3.getCateCd() != null)? cate3.getCateCd() : ""%>><%=(cate3 != null && cate3.getCateNm() != null)? cate3.getCateNm() : "소분류"%></option>
		                </select>
	                </div>
	                <div id="relation02">
		                <select name="brand" id="brand" onclick="brandList()">
			                 <option value=<%=(brand != null)?brand.getBrandCd():""%>><%=(brand != null)?brand.getBrandNm():"브랜드선택"%></option>
			             </select>
	                </div>	                
             </div>
             <div class="cont_box">
                 <p>이미지 등록</p> 
                 <input type="file" name="file" src=<%=(theme != null && theme.getRepresentImg() != null)?theme.getRepresentImg():"" %> alt="">
             </div>
			 <div class="cont_box">
                 <p>추천상품</p> 
                 <br>
                 <div class="btn_position">
	             	<div class="btn_left">
	                   	<button type="button" onclick="goodsDisplay('maxDown')">맨아래로</button>
	                   	<button type="button" onclick="goodsDisplay('down')">아래로</button>
	                   	<button type="button" onclick="goodsDisplay('up')">위로</button>
	                   	<button type="button" onclick="goodsDisplay('maxUp')">맨위로</button>
	                </div>
	                <div class="btn_right">
	                	<button type="button" onclick="goodsModal()">상품선택</button>
	                    <button type="button" onclick="goodsdel()">선택삭제</button>
	                </div>		
                 </div>  
                 <table>
                 	 <colgroup>
                 	 	<col width="5%"/>
                 	 	<col width="5%"/>
                 	 	<col width="10%"/>
                 	 	<col width="35%"/>
                 	 	<col width="10%"/>
                 	 	<col width="5%"/>
                 	 	<col width="5%"/>
                 	 	<col width="5%"/>
                 	 </colgroup>
                     <tr>
                         <th><input type="checkbox" class="ck" id="ck_all"> </th>
                         <th>번호</th>
                         <th>이미지</th>
                         <th>상품명</th>
                         <th>판매가</th>
                         <th>공급사</th>
                         <th>재고</th>
                         <th>판매여부</th>
                     </tr>
                     <c:choose>
                     	<c:when test="${themeGoodsList != null}">
                     		<tbody id="requestGoodsData">
                     		<c:forEach var="c" items="${themeGoodsList}" varStatus="status">
			                      <tr id="tr${c.goodsNo}">
			                         <td><input type="checkbox" name="recomGoodsNo" class="ck" id="ck${c.goodsNo}" value="${c.goodsNo}"></td>
			                         <td>${status.count}</td>
			                         <td>${c.representImg}</td>
			                         <td>${c.goodsNm}</td>
			                         <td>${c.fixedPrice }</td>
			                         <td>${c.scmNo}</td>
			                         <td>
			                         	<c:if test = "${c.totalStock <= 0}">품절</c:if>
			                         	<c:if test = "${c.totalStock > 0}">${c.totalStock}</c:if>
			                         </td>
			                         <td>
			                         	<c:if test="${c.goodsSellFl == -1}">관리자판매중지</c:if>
			                         	<c:if test="${c.goodsSellFl == 0}">판매중지</c:if>
			                         	<c:if test="${c.goodsSellFl == 1}">판매중</c:if>
			                         </td>
			                     </tr>
		                     </c:forEach>
		                     </tbody>
                     	</c:when>
                     	<c:otherwise>
                     		<tbody id="requestGoodsData"></tbody>
                     	</c:otherwise>
                     </c:choose>
                 </table>
              </div>
         </div>
     </section>
<%} %>
</div>

<script src="${pageContext.request.contextPath }/admin/js/common/category.js"></script>
<script src="${pageContext.request.contextPath }/admin/js/common/brand.js"></script>
<script src="${pageContext.request.contextPath }/admin/js/common/theme.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/goods/goodsClassify.js"></script>
<%if(cate1 == null){%>
<script>
window.onload= function(){
 	cateAction();
}
</script>
<%}%>