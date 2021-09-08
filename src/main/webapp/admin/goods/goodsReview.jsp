<%@page import="dto.boardDTO.reviewDTO"%>
<%@page import="dto.pageDTO.pageDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/goods/total_review.css">
<%
	String kind = (String)request.getAttribute("kind");
	String keyword = (String)request.getAttribute("keyword");
	int goodsPt = (Integer)request.getAttribute("goodsPt");
	int poto = (Integer)request.getAttribute("poto");
	pageDTO paging = (pageDTO)request.getAttribute("paging");
	ArrayList<reviewDTO> rw = (ArrayList)request.getAttribute("rw");
%>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
 <div class="cont_wrap">
	<form action="./goodsReview" method="post">
        <div class="search_cont">
            <div class="sch_tit">
              <h2>구매후기 관리</h2>
              <input type="submit" value="검색">
            </div>
            <div class="sch_ck_cont">
                <div class="sch_check">
                   <p>검색어</p>
                   <select name="kind">
                        <option value="작성자" <%if(kind != null && kind.equals("작성자")){ %>selected = "selected"<%} %>>작성자</option>
                        <option value="제목" <%if(kind != null && kind.equals("제목")){ %>selected = "selected"<%} %>>제목</option>
                        <option value="상품명" <%if(kind != null && kind.equals("상품명")){ %>selected = "selected"<%} %>>상품명</option>
                        <option value="공급사명" <%if(kind != null && kind.equals("공급사명")){ %>selected = "selected"<%} %>>공급사명</option>
                    </select>
                    <input type="text" name="keyword" value=<%=(keyword != null)?keyword:""%>>
                </div>
                 <div class="sch_check">
                    <p>리뷰 평점</p>
                    <label for="ra01"><input type="radio" name="goodsPt" value="6" <%if(goodsPt == 6){ %>checked = "checked"<%} %>>전체</label>
                    <label for="ra01"><input type="radio" name="goodsPt" value="5" <%if(goodsPt == 5){ %>checked = "checked"<%} %>>5</label>
                    <label for="ra02"><input type="radio" name="goodsPt" value="4" <%if(goodsPt == 4){ %>checked = "checked"<%} %>>4</label>
                    <label for="ra03"><input type="radio" name="goodsPt" value="3" <%if(goodsPt == 3){ %>checked = "checked"<%} %>>3</label>
                    <label for="ra04"><input type="radio" name="goodsPt" value="2" <%if(goodsPt == 2){ %>checked = "checked"<%} %>>2</label>
                    <label for="ra05"><input type="radio" name="goodsPt" value="1" <%if(goodsPt == 1){ %>checked = "checked"<%} %>>1</label>
                </div>
                <div class="sch_check">
                    <p>리뷰 형식</p>
                    <label for="ra01"><input type="radio" name="poto" value="2" <%if(poto == 2){ %>checked = "checked"<%}%>>전체</label>
                    <label for="ra01"><input type="radio" name="poto" value="1" <%if(poto == 1){ %>checked = "checked"<%}%>>포토 리뷰</label>
                    <label for="ra02"><input type="radio" name="poto" value="0" <%if(poto == 0){ %>checked = "checked"<%}%>>텍스트 리뷰</label>
                </div>
        	</div>     
        </div>
	</form>
        <div class="sch_result">
        	<div class="pageCount"><h3>상품결과 <span><%=paging.getListCount()%></span>건</h3></div>       	
            <div class="deleteFl"><input type="button" onclick="deleteFl()" value="선택삭제"></div>
            <table>
            	<colgroup>
            		<col width="5%"/>
            		<col width="5%"/>
            		<col width="10%"/>
            		<col width="15%"/>
            		<col width="45%"/>
            		<col width="30%"/>
            	</colgroup>
                <tr>
                    <td><input type="checkbox" name="ckAll" class="ck" id="ck_all"> </td>
                    <td>번호</td>
                    <td>작성자</td>
                    <td>별점</td>
                    <td>제목</td>
                    <td>등록일</td>  
                </tr>
                <%for(int i = 0; i < rw.size(); i++){ %>
               	<tr>
               		<td><input type="checkbox" name="ck" class="ck" value="<%=rw.get(i).getSno()%>"></td>
               		 <td><%=(i+1) %></td>
                    <td><%=rw.get(i).getWriter()%></td>
                    <td>
               			<%for(int pt = 0; pt < rw.get(i).getGoodsPt(); pt++){ %>
               				<i class="fas fa-star"></i>
               			<%} %>
					</td>
                    <td class="title"><button class="reviewbtn" onclick="reviewContent(<%=rw.get(i).getSno()%>)"><%=rw.get(i).getTitle() %></button></td>
                    <td><fmt:formatDate value="<%=rw.get(i).getRegDt() %>" pattern="yyyy-MM-dd"/></td>
               	</tr>
                <%} %>   
            </table>
        </div>
        <div id="primgList"></div>
        <div class="bottom">
           	<div style="margin: 30px 0; text-align: center;">
				<input type="hidden" name="page" value="${paging.page}">
				<c:if test="${paging.page<=1 }">[이전]&nbsp;</c:if>
				<c:if test="${paging.page>1 }">
					<a href="goodsReview?page=${paging.page-1}">[이전]</a>&nbsp;</c:if> 
					<c:forEach begin="${paging.startPage}" end="${paging.endPage }" var="i" step="1">
					<c:choose>
						<c:when test="${i eq paging.page }">	
							[${i}]								
						</c:when>
						<c:otherwise>
							<a href="goodsReview?page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${paging.page>=paging.maxPage}">&nbsp;[다음]</c:if>
				<c:if test="${paging.page<paging.maxPage}">
					<a href="goodsReview?page=${paging.page+1}">&nbsp;[다음]</a>
				</c:if>
			</div>
        </div>
    </div>
<script src="${pageContext.request.contextPath}/admin/js/goods/goodsReview.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/common/day.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/common/checkbox.js"></script>