<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--상단-->
<jsp:include page="../adminHeader.jsp" flush="false"/>
	<div class="cont_wrap">
		<h1>쿠폰등록</h1>
		<form action="./insertCoupon" method="post">
			<input type="radio" name="kind" value="online" checked="checked">온라인 <input type="radio" name="couponKind" value="offline">오프라인<br>
			<input type="text" name="useType" placeholder="쿠폰 사용타입"><br>
			<select name="saveType">
				<option value="down">회원다운로드</option>
				<option value="auto">자동발급</option>
				<option value="manual">수동발급</option>
			</select><br>
			<input type="text" name="couponNm" placeholder="쿠폰이름"><br>
			<input type="text" name="couponDesc" placeholder="설명"><br>
			시자날짜 : <input type="date" name="usePeriodStartDate"> ~ 종료날짜 <input type="date" name="usePeriodEndDate"><br>
			쿠폰 기능 구분
			<select name="kindType">
				<option value="sale">상품 구매 시</option>
			</select><br>
			<input type="text" name="benefit" placeholder="쿠폰 할인율"><br>
			<select name="benefitType">
				<option value="percent">정율 할인</option>
				<option value="price">정액 할인</option>
			</select><br>
			노출  시작일 <input type="date" name="displayStartDate"> ~ 노출 종료일 <input type="date" name="couponDisplayEndDate"><br>
			기간만료 안내<input type="radio" name="limitSmsFl" value="true">발송 <input type="radio" name="limitSmsFl" value="false" checked="checked">미발송<br>
			쿠폰적용 상품
			<input type="checkbox" name="all" value="true">전체
			<input type="checkbox" name="scm"  value="scm">입점사
			<input type="checkbox" name="category" value="category">카테고리
			<input type="checkbox" name="brand" value="brand">브랜드
			<input type="checkbox" name="product" value="product">상품
			<br>
			<br><br>
			
			<input type="text" name="minOrderPrice" placeholder="쿠폰 사용 가능한 최저 구매 금액"><br>
			<input type="radio" name="applyDuplicateType" value="true">가능<input type="radio" name="applyDuplicateType" value="false" checked="checked">불가능<br>
			<br>
			<input type="submit" value="쿠폰 등록">
			
		</form>
	</div>
<!--하단-->
<jsp:include page="../adminFooter.jsp" flush="false"/>