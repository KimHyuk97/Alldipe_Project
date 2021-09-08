<%@page import="dto.scmDTO.scmDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/scm/total_supply.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
<%
	scmDTO scm = (scmDTO)session.getAttribute("scm");
	session.removeAttribute("scm");
%>
	<div class="cont_wrap">
        <div class="top_tit">
            <h1>공급사 관리</h1>
        </div>
        <div class="search_cont">
            <div class="sch_tit">
              <h2>공급사 검색</h2> 
              <button>검색</button> 
            </div>
            <div class="sch_ck_cont">
                <div class="sch_check">
                    <p>검색어</p>
                    <select name="통합검색" id="">
                        <option value="">통합검색</option>
                    </select>
                    <input type="text" placeholder="검색어를 입력해주세요.">
                </div>
                <div class="sch_check">
                    <p>운영상태</p>
                    <label for="ck01" id="la01"><input type="radio" name="radio01" id="ck01">전체</label>
                    <label for="ck02" id="la01"><input type="radio" name="radio01" id="ck02">운영</label>
                    <label for="ck03" id="la01"><input type="radio" name="radio01" id="ck03">탈퇴</label>
                </div>
                <div class="sch_check">
                    <p>상품등록권한</p>
                    <label for="ck04" id="la01"><input type="radio" name="radio02" id="ck04">전체</label>
                    <label for="ck05" id="la01"><input type="radio" name="radio02" id="ck05">자동승인</label>
                    <label for="ck06" id="la01"><input type="radio" name="radio02" id="ck06">관리자승인</label>
                </div>
                <div class="sch_check">
                    <p>운영상태</p>
                    <label for="ck07" id="la01"><input type="radio" name="radio03" id="ck07">전체</label>
                    <label for="ck08" id="la01"><input type="radio" name="radio03" id="ck08">자동승인</label>
                    <label for="ck09" id="la01"><input type="radio" name="radio03" id="ck09">관리자승인</label>
                </div>
                <div class="sch_check">
                    <p>운영상태</p>
                    <label for="ck10" id="la01"><input type="radio" name="radio04" id="ck10">전체</label>
                    <label for="ck11" id="la01"><input type="radio" name="radio04" id="ck11">자동승인</label>
                    <label for="ck12" id="la01"><input type="radio" name="radio04" id="ck12">관리자승인</label>
                </div>
            </div>     
        </div>
        <div class="sch_result">
            <h3>공급사 검색결과 <p>0건</p> </h3>
            <div class="sch_btn">
                <button>선택삭제</button>
                <select name="" id="">
                    <option value="">10개보기</option>
                    <option value="">10개보기</option>
                    <option value="">10개보기</option>
                </select>
            </div>
            <table>
                <tr>
                    <td class="img"> <img src="" alt=""> </td>
                    <td>상태</td>
                    <td>아이디</td>
                    <td>공급사명</td>
                    <td>공급사코드</td>
                    <td>상품등록권한</td>
                    <td>상품수정권한</td>
                    <td>상품삭제권한</td>
                    <td>등록일</td>
                    <td>담당자</td>
                    <td>수정</td>
                </tr>
<%	if(scm!=null){ %>
				<tr>
					<td>-</td>
                    <td>-</td>
                    <td><%=scm.getCompanyNm()%></td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td><button onclick="location.href='./scmApprove?scmNo=<%=scm.getScmNo()%>';">업체 승인</button></td>
				</tr>		
<%	} %>
			</table>
	           <div class="num">
	               <a href="">&lt;</a>
	               <a href="">1</a>
	               <a href="">2</a>
	               <a href="">3</a>
	               <a href="">&gt;</a>
	           </div>
        </div>
    </div>
<!--하단-->
<jsp:include page="../adminFooter.jsp" flush="false"/>