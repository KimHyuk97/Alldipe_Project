<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/partners/certification.css">
</head>
<body>
	<div class="header">
		<div class="header_inner">
            <img alt="파트너스 로고" src="${pageContext.request.contextPath }/fileF/partners/partnersLogo.png">
		</div>
	</div>
	<div class="container">
		<div class="container_inner">
			<div class="container_inner_top">
				<span class="container_title">사업자 인증</span>
			</div>
			<div class="container_inner_content">
				<div class="businessNo_div">
					<span class="content_title">사업자등록번호</span>
					<input type="text" name="busiNum1" class="inputbox">-<input type="text" name="busiNum2" class="inputbox">-<input type="busiNum3" class="inputbox">
					<p>사업자등록번호 10자리 숫자를 입력하세요.</p>
				</div>
				<div class="certifi_info">
					<span class="info_title">입점 시 필요서류 안내</span><br>
					<span class="info_sub">공통 서류</span><span class="info_text">사업자등록증 또는 사업자등록증명원 / 통신판매업 신고증 / 통장 사본</span><br>
					<span class="info_sub">선택 서류</span><span class="info_text">건강기능식품 판매업증 / 의료기기 판매업증</span>
				</div>
				<div class="button_div">
					<button type="button" class="btn" onclick="">돌아가기</button>
					<button type="button" class="btn" onclick="">인증신청</button>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="footer_inner">
            <span class="footer_title">입점 문의</span>
            <span class="footer_text">02-6949-3397</span>     
            <a href="#"><span>개인정보 취급방침</span></a>
        </div>
	</div>
</body>
</html>