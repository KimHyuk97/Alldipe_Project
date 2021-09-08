<%@page import="dto.adminDTO.adminDTO"%>
<%@page import="dto.goodsDTO.goodsDTO"%>
<%@page import="dto.boardDTO.boardDTO"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	boardDTO board = (boardDTO)request.getAttribute("board");
	goodsDTO goods = (goodsDTO)request.getAttribute("goods");
	adminDTO admin = (adminDTO)request.getAttribute("admin");
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
 <title>올디프관리자 페이지</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/fileF/header/alldipeIcon.ico" type="image/x-icon"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/board/answer.css">
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
</head>
<body>
<form action="answer" method="post" name="form">
<div class="main_title">
	<h1>게시글 답변</h1>
	<div>
		<button type="button" onclick="javascript:window.close();">목록</button>
		<button type="button" onclick="submit()">저장</button>
	</div>
</div>
<div class="container">
	<div class="main_container">
		<div class="sub_title"><p>게시글보기</p></div>
		<div class="sub_container">
			<div class="content">
				<div><p>게시판종류</p></div>
				<div><%=board.getBoardType() %></div>
			</div>
			<div class="content">
				<div><p>작성자</p></div>
				<div><%=board.getWriterNm() %></div>
			</div>
			<div class="content">
				<div><p>등록시간</p></div>
				<div><%=date.format(board.getRegDt()) %></div>
			</div>
			<div class="content">
				<div><p>아이피</p></div>
				<div><%=board.getWriterIp() %></div>
			</div>			
			<div class="content">
				<div><p>제목</p></div>
				<div><%=board.getTitle() %></div>
			</div>
			<%if(goods != null){ %>
			<div class="content">
				<div><p>상품정보</p></div>
				<div>
					<a href="<%=goods.getGoodsNo()%>"><%=goods.getRepresentImg() %>/<%=goods.getGoodsNm() %>/<%=goods.getFixedPrice() %></a>
				</div>
			</div>
			<%} %>
			<div class="content">
				<div><p>내용</p></div>
				<div><%=board.getContents()%></div>
			</div>
		</div>
	</div>
	<div class="main_container02">
		<div class="sub_title"><p>게시글 답변하기</p></div>
		<div class="sub_container">
			<div class="content">
				<div><p>답변 작성자</p></div>
				<div>
					<input type="hidden" name="sno" value=<%=board.getNo()%>>
					<input type="hidden" name="adminNo" value=<%=(board.getAdminNo() > 0)?board.getAdminNo():admin.getSno() %>>
					<%=(board.getAdminNo() > 0)?board.getAdminNo():admin.getId()%>
				</div>
			</div>
			<div class="content">
				<div><p>답변내용</p></div>
				<div><textarea cols="100" rows="10" id="content" name="answerContent" ><%=(board.getAnswerContent()!=null)?board.getAnswerContent():""%></textarea></div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
<script>
function submit(){
	if(document.getElementById("content").value == "" || document.getElementById("content").value == null){
		alert("답변내용을 적어주세요");
	}else{
		form.submit();
	}
}
</script>
</html>