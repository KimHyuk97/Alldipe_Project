<%@page import="dto.goodsDTO.goodsOptionDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/admin/js/goods/goodsMod.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/goods/goodsOptionMod.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/partners/reset.css">
<%	
 	ArrayList<goodsOptionDTO> list = (ArrayList)request.getAttribute("o");
 	
 	ArrayList<String> optionStr = null;
 	
 		if(list!=null && list.size()>0){
 			
 			optionStr = new ArrayList<>();
 			
 			if(list.get(0).getOptionNm1()!=null){
 				optionStr.add(list.get(0).getOptionNm1());
 			}
			if(list.get(0).getOptionNm2()!=null){
				optionStr.add(list.get(0).getOptionNm2());
 			}
			if(list.get(0).getOptionNm3()!=null){
 				optionStr.add(list.get(0).getOptionNm3());
 			}
			if(list.get(0).getOptionNm4()!=null){
 				optionStr.add(list.get(0).getOptionNm4());
 			}
			if(list.get(0).getOptionNm5()!=null){
 				optionStr.add(list.get(0).getOptionNm5());
 			}
 		}	
 	
%>
<div class="cont_wrap">
<div id="wrap">
<div class="wrap wrap05">
	<form action="./goodsOptionModifyProcess" method="post" enctype="multipart/form-data">
            <h2>옵션설정</h2>
			<input type="hidden" value="${goodsNo}" name="goodsNo">
	        <div class="top_botton">
	        	<button class="bottom" type="button" onclick="window.close()">취소</button>
	        	<button class="bottom" type="submit">저장</button>
	        </div>
            <div class="cont cont05 cont_01">
                <p><i class="fas fa-circle"></i>옵션사용</p>
                <label for="use01"><input type="radio" name="use" id="use01" checked="checked" value="0"><span>사용</span> </label>
                <label for="use02"><input type="radio" name="use" id="use02" value="1"><span>사용안함</span> </label>
            </div>
            <div class="option_div">
	            <div class="cont cont05 cont_03">
	                <p><i class="fas fa-circle"></i>옵션개수</p>
	                <select id="optionCnt" onchange="optionText()">
	                    <option value="1">1개</option>
	                    <option value="2">2개</option>
	                    <option value="3">3개</option>
	                    <option value="4">4개</option>
	                    <option value="5">5개</option>
	                </select>
	            </div>
	            <div class="cont cont05 cont_04" style="position:relative;">
	                <p><i class="fas fa-circle"></i>옵션등록</p>
	               	<button type="button" class="optionPlus" onclick="optionPlus()">+</button>
	               	<button type="button" class="optionM" onclick="optionM()">-</button>
	            	<button type="button" class="apply pos" onclick="optionChange()">적용하기</button>
	                <div id="optionlist">
	                	<div class="optionli">
			                <input type="textbox" class="optionNm" placeholder="예시 : 색상">
			                <input type="textbox" class="optionVal" placeholder="예시 : 블랙,화이트(,로 구분)">
		                </div>
	                </div> 
	                
				</div>
	            <div class="cont cont05 cont_04">
	            	<button type="button" class="del" onclick="optionDel()">삭제하기</button>
	            	<table id="optionTable">
	                    <tr>
	                        <td rowspan="2"><input type="checkbox"  class="ck" id="selectAll" onclick="selectItem()"></td>
	                        <td id="optionHead" colspan=<%=(optionStr!=null)?optionStr.size():"1" %>>옵션명</td>
	                        <td rowspan="2">옵션 정상가</td>
	                        <td rowspan="2">옵션 판매가</td>
	                        <td rowspan="2">재고수량</td>
	                        <td rowspan="2">판매자상품코드</td>
	                    </tr>
	                    <tr id="optionCol">
<%
		if(optionStr!=null &&  optionStr.size()>0){
			for(String str : optionStr){ 
%>					
							<td><%=str%></td>
<%
			}
		}
%>
	                    </tr>
<%
		if(list!=null && list.size()>0){
			for(int i = 0; i<list.size(); i++){
%>
						<tr id="tr<%=i%>" class="option">
							<td>
								<input type="hidden" name="optionNo<%=String.format("%02d", i+1) %>" value=<%=list.get(i).getOptionNo()%>>
								<input type="checkbox" class="select_item" name="select_item" value="tr<%=i %>">
							</td>
<%
				if(list.get(i).getOptionValue1()!=null){
%>
							<td><%=list.get(i).getOptionValue1() %></td>
							<input type="hidden" value=<%=list.get(i).getOptionNm1() %> name="optionNm0<%=String.format("%02d", i+1) %>">
							<input type="hidden" value=<%=list.get(i).getOptionValue1() %> name="optionVal0<%=String.format("%02d", i+1) %>">
<%
				}
%>

<%
				if(list.get(i).getOptionValue2()!=null){
					%>
							<td><%=list.get(i).getOptionValue2() %></td>
							<input type="hidden" value=<%=list.get(i).getOptionNm2() %> name="optionNm1<%=String.format("%02d", i+1) %>">
							<input type="hidden" value=<%=list.get(i).getOptionValue2() %> name="optionVal1<%=String.format("%02d", i+1) %>">
					<%
				}
%>

<%
				if(list.get(i).getOptionValue3()!=null){
					%>
							<td><%=list.get(i).getOptionValue3() %></td>
							<input type="hidden" value=<%=list.get(i).getOptionNm3() %> name="optionNm2<%=String.format("%02d", i+1) %>">
							<input type="hidden" value=<%=list.get(i).getOptionValue3() %> name="optionVal2<%=String.format("%02d", i+1) %>">
					<%
				}
%>

<%
				if(list.get(i).getOptionValue4()!=null){
					%>
							<td><%=list.get(i).getOptionValue4() %></td>
							<input type="hidden" value=<%=list.get(i).getOptionNm4() %> name="optionNm3<%=String.format("%02d", i+1) %>">
							<input type="hidden" value=<%=list.get(i).getOptionValue4() %> name="optionVal3<%=String.format("%02d", i+1) %>">
					<%
				}
%>

<%
				if(list.get(i).getOptionValue5()!=null){
					%>
							<td><%=list.get(i).getOptionValue5() %></td>
							<input type="hidden" value=<%=list.get(i).getOptionNm5() %> name="optionNm4<%=String.format("%02d", i+1) %>">
							<input type="hidden" value=<%=list.get(i).getOptionValue5() %> name="optionVal4<%=String.format("%02d", i+1) %>">
					<%
				}
%>
							<td>
								<input type="text" class="optionInput" name="fixedCost<%=String.format("%02d", i+1) %>" value=<%=(list.get(i).getOptionPrice()!=null)?list.get(i).getOptionPrice():"0"%>>
							</td>
							<td>
								<input type="text" class="optionInput" name="salesCost<%=String.format("%02d", i+1) %>" value=<%=(list.get(i).getOptionPrice()!=null)?list.get(i).getOptionFixedPrice():"0"%>>
							</td>
							<td>
								<input type="text" class="optionInput" name="stockCnt<%=String.format("%02d", i+1) %>" value=<%=list.get(i).getStockCnt()%>>
							</td>
							<td>
								<input type="text" class="optionInput" name="sellerCd<%=String.format("%02d", i+1) %>" value=<%=(list.get(i).getSellerCd()!=null)?list.get(i).getSellerCd():"" %>>
							</td>
						</tr>
							
<%
			}
		}
%>
	                </table>
	                
	            </div>
	            
            </div>
            </form>
        </div>
	</div>
</div>