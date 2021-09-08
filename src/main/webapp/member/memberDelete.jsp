<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" flush="false"/>
<div>
	<div><h1>회원탈퇴 안내</h1></div>
	<div>
		<form action="memberDelete" name="member_delete" method="post">		
			<input type="hidden" name="id" value="${sessionScope.mem.memId}">
			<input type="hidden" name="memNo" value="${sessionScope.mem.memNo}">
			<table>
				<tr>
					<th>회원탈퇴안내</th>
					<td>
						<p></p>
					</td>
				</tr>
				<tr>
					<th>불편사항</th>
					<td>
						<input type="checkBox" name="reason" value="고객서비스">고객서비스
						<input type="checkBox" name="reason" value="배송분만">배송불만
						<input type="checkBox" name="reason" value="교환/환불/반품">교환/환불/반품
						<input type="checkBox" name="reason" value="방문 빈도가 낮음">방문 빈도가 낮음
						<input type="checkBox" name="reason" value="상품가격">상품가격
						<input type="checkBox" name="reason" value="개인 정보유출 우러">개인 정보유출 우러
						<input type="checkBox" name="reason" value="쇼핑몰의 신뢰도">쇼핑몰의 신뢰도
						<input type="checkBox" name="reason" value="쇼핑기능">쇼핑기능
						<textarea name="reasonDesc" id="reasonDesc" placeholder="고객님의 진심어린 충고 부탁드립니다."></textarea>
					</td>
				</tr>
				<tr>
					<td><input type="button" id="deleteBtn" value="회원탈퇴"></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script>
	document.getElementById('deleteBtn').addEventListener('click',() => {
		const	deleteMsg = confirm("탈퇴하시겠습니까?");
		if(deleteMsg == true){
			member_delete.submit();
			alert('탈퇴하였습니다.');
		}else{
			location.href="index";
		}
	});
</script>
<jsp:include page="../footer.jsp" flush="false"/>