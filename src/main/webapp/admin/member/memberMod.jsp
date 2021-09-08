<%@page import="dto.memberDTO.memberhackoutDTO"%>
<%@page import="dto.memberDTO.memberhistoryDTO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="dto.memberDTO.membergradeDTO"%>
<%@page import="dto.memberDTO.memberDTO"%>
<%@page import="dto.scmDTO.scmDTO"%>
<%@page import="dto.goodsDTO.goodsOptionDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.goodsDTO.goodsDTO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat" %>
<%	
 	memberDTO mem = (memberDTO)request.getAttribute("info");
	membergradeDTO grade = (membergradeDTO)request.getAttribute("grade");
	ArrayList<memberhistoryDTO> history = (ArrayList)request.getAttribute("history");
	ArrayList<memberhackoutDTO> hackout = (ArrayList)request.getAttribute("hackout");
	DecimalFormat df = new DecimalFormat("#,###,###");
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
<script type="text/javascript" src="http://jsgetip.appspot.com"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/member/memberMod.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/partners/reset.css">

<jsp:include page="../adminHeader.jsp"/>
	<div class="cont_wrap">
		<div id="wrap">
        <div class="top_title">
            <h1>회원수정</h1>
        </div>
        <form action="./memberModifyProcess" method="post" name="modify">
        	<input type="hidden" id="myIp" name="ip">
        	<input type="hidden" name="memNo" value=<%=mem.getMemNo()%>>
	        <div class="top_botton">
	        	<button class="bottom" type="button" onclick="save()">저장</button>
	        	<button class="bottom" type="button" onclick="history.back()">취소</button>
	        </div>
	        <div class="wrap wrap01">
	            <h2>기본정보</h2>
	            <div class="wrapContainer">
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>회원아이디</p>
		                <div class="sub_cont">
		                	<input type="text" name="memId" value=<%=mem.getMemId()%>>
		                </div>
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>회원이름</p>
		                <div class="sub_cont">
							<input type="text" name="memNm" value=<%=mem.getMemNm()%>>
						</div>
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>등급</p>
		            	<div class="sub_cont">
			                <select name="grade" onclick="membergrade()" id="grade">
			                   <option value=<%=(grade != null && grade.getSno() != 0)?grade.getSno():""%>><%=(grade != null)?grade.getGradeNm():"등급선택"%></option>
			                </select>
		                </div>
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>생년월일</p>
		                <div class="sub_cont">
			            	<input type="text" name="birth" value=<%=date.format(mem.getBirthDt())%>>
		                </div>
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>성별</p>
		                <div class="sub_cont">
			                <label><input type="radio" name="gender" value="m" <%if(mem != null && mem.getSexFl().equals("m")){  %> checked = "checked" <%} %>>남자</label>
			                <label><input type="radio" name="gender" value="w" <%if(mem != null && mem.getSexFl().equals("w")){  %> checked = "checked" <%} %>>여자</label>
		                </div>
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>주문금액</p>
		                <div class="sub_cont">
		                	<p><%=df.format(mem.getSaleAmt())%>원</p>
		                </div>               
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>적립금</p>
		                <div class="sub_cont">
		                	<p><%=df.format(mem.getMileage())%></p>
		                </div>               
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>올페이</p>
		                <div class="sub_cont">
		                	<p><%=df.format(mem.getDeposit())%></p>
		                </div>               
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>회원비밀번호</p>
		                <div class="sub_cont">
							<input type="hidden" id="pwVal"  value="1">
							<div id="pwChange">
								<button onclick="pwChangeBtn()">비밀번호 변경</button>
							</div>
						</div>
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>주소</p>
		                <div class="sub_cont address">
		                	<div class="sub2"><input type="text" name="zonecode" id="zone" value=<%=mem.getZonecode()%>><button type="button" onclick="adr()">주소찾기</button></div>
							<input type="text" name="address" id="addr" value="<%=mem.getAddress()%>"><input type="text" name="addressSub" id="addrSub" value="<%=mem.getAddressSub()%>">
						</div>
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>이메일</p>
		                <div class="sub_cont on">
							<div class="sub2"><input type="text" name="email" id="userEmail" value=<%=mem.getEmail()%>><input type="button" id="emailBtn" disabled="disabled" onclick="emailCheck()" value="중복확인"></div>
							<input type="hidden" id="email_value" value="1">
							<label><input type="checkbox" name="mailingFl" value="1" <%if(mem.isMaillingFl()){ %>checked="checked"<%} %>>정보/이벤트 메일 수신에 동의합니다.</label>
						</div>
						<span class="pr">*수신동의설정 안내메일의 자동발송여부에 따라 회원정보의 수신동의설정 변경 시 해당 회원에게 안내메일이 자동 발송됩니다.</span>
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>휴대폰번호</p>
		                <div class="sub_cont on">
							<input type="text" name="callPhone" value=<%=mem.getCellPhone()%>>
							<label><input type="checkbox" name="smsFl" value="1" <%if(mem.isSmsFl()){ %>checked="checked"<%} %>>정보/이벤트 SMS 수신에 동의합니다.</label>
		                </div>
						<span class="pr">*수신동의설정 안내메일의 자동발송여부에 따라 회원정보의 수신동의설정 변경 시 해당 회원에게 SMS가 자동 발송됩니다.</span>
		            </div>
	            </div>
	        </div>
	        <div class="wrap wrap01">
	            <h2>추가정보</h2>
	            <div class="wrapContainer02">
	            	<div class="cont cont01">
		                <p><i class="fas fa-circle"></i>가입승인여부</p>
		                <div class="sub_cont">
			                <label for="ra02"><input type="radio" name="apply" id="ra02" value="1" <%if(mem != null && mem.isAppFl()){  %> checked = "checked" <%} %>>승인</label>
			                <label for="ra03"><input type="radio" name="apply" id="ra03" value="0"<%if(mem != null && !mem.isAppFl()){  %> checked = "checked" <%} %>>미승인</label>
		                </div>
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>회원가입일</p>
		                <div class="sub_cont">
		                	<p><%=date.format(mem.getRegDt()) %></p>
		                </div>               
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>최종로그인일</p>
		                <div class="sub_cont">
		                	<p><%=(mem.getLastLoginDt() != null)?date.format(mem.getLastLoginDt()) : "00-00-00"%></p>
		                </div>              
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>방문횟수</p>
		                <div class="sub_cont">
		                	<p><%=mem.getLoginCnt()%></p>
		                </div>            
		            </div>
		           	<div class="cont cont01">
		                <p><i class="fas fa-circle"></i>휴면회원여부</p>
		                <div class="sub_cont">
			                <label><input type="radio" name="sleepFl" value="0"<%if(mem != null && !mem.isSleepFl()){  %> checked = "checked" <%} %>>정상회원</label>
			                <label><input type="radio" name="sleepFl" value="1" <%if(mem != null && mem.isSleepFl()){  %> checked = "checked" <%} %>>휴면회원</label>
		                </div>
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>휴면해제일</p>
		                <div class="sub_cont">
		                	<p><%=(mem.getSleepWakeDt() != null)?date.format(mem.getSleepWakeDt()):"00-00-00"%></p>
		                </div>              
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>탈퇴여부</p>
		                <div class="sub_cont">
			                <label><input type="radio" name="deleteFl" value="n" <%if(mem != null && mem.getDeleteFl() == "n"){  %> checked = "checked" <%} %>>정상회원</label>
		                	<label><input type="radio" name="deleteFl" value="y" <%if(mem != null && mem.getDeleteFl() == "y"){  %> checked = "checked" <%} %>>탈퇴회원</label>
		                </div>              
		            </div>
	            </div>
	        </div>
	        <div class="wrap wrap01">
	            <h2>개인정보 수집·이용 선택동의 내역 </h2>
	            <div class="wrapContainer03">
	            	<div class="cont cont01">
		                <p><i class="fas fa-circle"></i>개인정보 수집ㆍ이용</p>
		                <div class="sub_cont">
		                	<p><%if(mem.isPrivateApprovalFl()){ %>동의함<%}else{%>동의안함<%} %></p>
		                </div>               
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>개인정보 취급위탁</p>
		                <div class="sub_cont">
							<p><%if(mem.isPrivateConsignFl()){ %>동의함<%}else{%>동의안함<%} %></p>
		                </div>
		            </div>
		            <div class="cont cont01">
		                <p><i class="fas fa-circle"></i>개인정보 제3자 제공</p>               
		                <div class="sub_cont">
							<p><%if(mem.isPrivateOfferFl()){ %>동의함<%}else{%>동의안함<%} %></p>
		                </div>
		            </div>
	            </div>
	        </div>
	        <div class="wrap wrap04">
	            <h2>회원정보 수정이력</h2>
	            <div class="sub_cont">
	            	<table>
	            		<tr>
	            			<th>번호</th>
	            			<th>변경일자</th>
	            			<th>처리자</th>
	            			<th>IP주소</th>
	            			<th>변경항목</th>
	            			<th>변경내용</th>
	            		</tr>
	            		<tfoot>
	            			<%if(!history.isEmpty()){ %>
		            			<%for(int i=0; i<history.size(); i++){ %>
			            		<tr>
			            			<td><%=(i+1)%></td>
			            			<td><%=date.format(history.get(i).getRegDt())%></td>
			            			<td><%=(history.get(i).getProcessor() == "administrator")?"관리자":"회원"%></td>
			            			<td><%=history.get(i).getProcessorIp()%></td>
			            			<td><%=history.get(i).getUpdateColumn() %></td>
			            			<td><%=history.get(i).getBeforeValue()%> => <%=history.get(i).getAfterValue()%></td>
			            		</tr>
			            		<%} %>
		            		<%}%>
	            		</tfoot>
	            	</table>
	            </div>
	        </div>
	        <div class="wrap wrap04">
	            <h2>회원정보 탈퇴내역</h2>
	            <div class="sub_cont">
	            	<table>
	            		<tr>
	            			<th>번호</th>
	            			<th>탈퇴유형</th>
	            			<th>재가입가능여부</th>
	            			<th>불편사항</th>
	            			<th>충고말씀</th>
	            			<th>관리메모</th>
	            			<th>탈퇴일</th>
	            		</tr>
	            		<tfoot>
	            			<%if(!hackout.isEmpty()){ %>
		            			<%for(int i=0; i<hackout.size(); i++){ %>
			            		<tr>
			            			<td><%=(i+1)%></td>
			            			<td><%=(hackout.get(i).getHackType() == "directSelf")?"회원탈퇴":"관리자" %></td>
			            			<td><%=(hackout.get(i).getRejoinFl() == "y")?"재가입의향있음":"재가입의향없음"%></td>
			            			<td><%=hackout.get(i).getReasonCd() %></td>
			            			<td><%=hackout.get(i).getReasonDesc()%></td>
			            			<td><%=hackout.get(i).getAdminMemo() %></td>
			            			<td><%=date.format(hackout.get(i).getHackDt())%></td>
			            		</tr>
			            		<%} %>
		            		<%}%>
	            		</tfoot>
	            	</table>
	            </div>
	        </div>
        </form>
        <button class="bottom" type="button" onclick="history.back()">취소</button>
       	<button class="bottom" type="button" onclick="save()">저장</button>
    </div>
	</div>
	<script src="${pageContext.request.contextPath }/admin/js/member/modify.js"></script>
	<script src="${pageContext.request.contextPath }/admin/js/common/grade.js"></script>
	<script src="${pageContext.request.contextPath }/admin/js/common/address.js"></script>