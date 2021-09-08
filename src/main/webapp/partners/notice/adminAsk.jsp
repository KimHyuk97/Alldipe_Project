<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"/>
<!-- 	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet"> -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/summernote/summernote-lite.css">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<!-- include summernote css/js -->
	<script src="${pageContext.request.contextPath }/js/summernote/summernote-lite.js"></script>
	<script src="${pageContext.request.contextPath }/js/summernote/lang/summernote-ko-KR.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/partners/partners_ask02.css">
	
	
	<div class="cont_wrap">
		<div class="title">
            문의하기
        </div>
        <span class="must">*필수 입력 사항</span>
        <div class="content">
        	<form name="form" action="./insertQa" method="post" onsubmit="postForm()">
        	<input type="hidden" name="ip" value="">
            <div class="content_txt">
                <p>문의유형*</p>
                <select name="cate" class="box01">
                    <option value="상품문의">상품문의</option>
                    <option value="상품문의">상품등록/관리 문의</option>
                    <option value="상품문의">배송/반품/교환 문의</option>
                    <option value="상품문의">정산/세금계산서 문의</option>
                    <option value="상품문의">회원정보 문의</option>
                </select>
            </div>
            <div class="content_txt">
                <p>제목*</p>
                <input name="title" type="text" placeholder="제목을 최대 50자 이내로 등록하세요." class="box02">
            </div>
            <div class="content_txt">
                <p>내용*</p>
                <textarea class="summernote" name="contents"></textarea>
            </div>
            <div class="content_txt">
                <p>첨부파일</p>
                <input type="file" name="file1" id="" class="box04">
            </div>
            <div class="content_txt">
                <p>수신여부*</p>
                <input type="checkbox" name="sms" value="true" id="" class="check check01">SMS
                <input type="checkbox" name="email" value="true" id="" class="check check02">E-Mail
            </div>
            <div class="content_txt">
                <p>비밀번호</p>
                <input type="password" name="password" id="" class="password">
            </div>
        </div>
        <div class="agree_box">
            <input type="checkbox" name="agree01" value="true" id="" >(필수) 개인정보 수집, 이용동의 <a href="">전문보기</a>
            <input type="checkbox" name="agree02" value="true" id="" class="checkbox02">(필수) 개인정보 제 3자 제공동의 <a href="">전문보기</a>
        </div>
        <div class="button_box">
           <button type="submit">문의접수</button> 
        </div>
        </form>
	</div>
	 <script>
	 	$('.summernote').summernote({
		  height: 150,
		  lang: "ko-KR"
		});
	 	
	 	window.onload = function(){
	 		endLoading();
	 	}
    </script>
</body>
</html>