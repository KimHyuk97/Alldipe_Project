<%@page import="java.util.ArrayList"%>
<%@page import="dto.scmDTO.scmAddressDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<scmAddressDTO> list = (ArrayList)session.getAttribute("scmAddress");
	session.removeAttribute("scmAddress");
	String target = request.getParameter("target");
	System.out.println("페이지 리스트 사이즈 : " + list.size());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="inner">
		<h2>주소록</h2><button class="button" onclick="window.open('./popup/insertAddress.jsp','주소지 추가','width=400, height=600');">주소지 추가</button>
		<ul>
<%
	if(list != null && list.size()>0){
		int cnt = 0;
		for(scmAddressDTO dto : list){%>
			<div onclick="select(<%=cnt%>)"><li><span class="addr<%=cnt%>"><%=dto.getZonecode() %></span> : <span class="addr<%=cnt%>"><%=dto.getAddress() %></span> , <span class="addr<%=cnt%>"><%=dto.getAddressSub() %></span></li><button type="button" onclick="">수정</button><button type="button" onclick="goDelete(<%=dto.getSno()%>);">삭제</button></div>
<%		cnt++;
		}
	}
%>		</ul>
	</div>
</body>
<script type="text/javascript">
	function select(num){
		const target = opener.document.getElementsByClassName('<%=target%>');
		const addr = document.getElementsByClassName('addr' + num);
		for(var i = 0; i<target.length; i++){
			target[i].value = addr[i].innerText;
		}
		window.close();
	}
	
	function goDelete(num){
		var chk = confirm('삭제하시겠습니까?');
		if(chk){
			location.href='./deleteAddress?sno='+num;
		}
	}
	
	
</script>
</html>