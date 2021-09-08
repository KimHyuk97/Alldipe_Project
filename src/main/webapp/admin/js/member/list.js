function formSubmit(){
	list.submit();
}

//삭제
function deleteFl(){
	var check = document.querySelectorAll('input[name="ck"]:checked');
	if(check.length > 0){		    	
		var val = [];
		for(var i = 0; i < check.length; i++){
			val.push(check[i].value);
		}
		
		var d = confirm("선택하신 회원 "+check.length+"명을 탈퇴처리하시겠습니까?");
		if(d){
			$.ajax({
				type:"post",
				url:"./deleteFl",
				traditional : true,	//ajax 배열 보는법
				data: {
					memNo:val
				},
				dateType:"json",
				success:function(i){
					if(i == 1){
						alert("탈퇴처리되었습니다.");
						location.reload();
					}else{
						alert("실패");
					}
				},
				error:function(){
					alert('통신장애')
				}
				
			});
		}
	}else{
		alert("탈퇴처리할 회원을 선택해주세요.");
	}
}

//삭제
function sleepFl(){
	var check = document.querySelectorAll('input[name="ck"]:checked');
	if(check.length > 0){		    	
		var val = [];
		for(var i = 0; i < check.length; i++){
			val.push(check[i].value);
		}
		
		var d = confirm("선택하신 회원 "+check.length+"명을 휴면해제시겠습니가?");
		if(d){
			$.ajax({
				type:"post",
				url:"./sleepFl",
				traditional : true,	//ajax 배열 보는법
				data: {
					memNo:val
				},
				dateType:"json",
				success:function(i){
					if(i == 1){
						alert("휴면 해제되었습니다.");
						location.reload();
					}else{
						alert("실패");
					}
				},
				error:function(){
					alert('통신장애')
				}
				
			});
		}
	}else{
		alert("휴면해제할 회원을 선택해주세요.");
	}
}

//회원수정
function memberMod(memNo,grade){
	location.href="adminMemberMod?memNo="+memNo+"&grade="+grade+"";
}


// 지급/차감 버튼
function payMode(mode){
	var check = document.querySelectorAll('input[name="ck"]:checked');
	if(check.length > 0){		    	
		payModal(mode);
	}else{
		alert(mode+"(을/를) 지급/차감할 회원을 선택해주세요.");
	}
}

// 지급/차감 팝업창
function payModal(mode){	
	var mode2;
	if(mode == "적립금"){
		mode2 = 1;
	}else if(mode == "올페이"){
		mode2 = 2;
	}
	var o = "";	
	o += "<div class='modalContainer'>";
	o += "<div class='title'>";
	o += "<h1>"+mode+" 지급/차감</h1>";
	o += "<button class='modalClose' onclick='modalClose()'>X</button>";
	o += "</div>";
	o += "<div class='modalContent'>";
	o += "<table>";
	o += "<tr>";
	o += "<th>지급/차감 여부</th>";
	o += "<td>";
	o += "<input type='hidden' id='handlemode' value='지급'>"
	o += "<label><input type='radio' name='handlemode' onclick='handle(event)' value='지급' checked='checked'>지급</label>";
	o += "<label><input type='radio' name='handlemode' onclick='handle(event)' value='차감'>차감</label>";
	o += "</td>";
	o += "</tr>";
	o += "<tr>";
	o += "<th>금액설정</th>";
	o += "<td><span id='Fl'>(+) </span><input type='text' id='price'  pattern='[0-9]+'> 원</td>";
	o += "</tr>";
	o += "<tr>";
	o += "<th>지급/차감 사유</th>";
	o += "<td><textarea rows='10' cols='50' id='reasonCd' style='resize: none;'></textarea></td>";
	o += "</tr>";
	o += "</table>";
	o += "</div>";
	o += "<div><button class='payToBtn' type='button' onclick='payTo("+mode2+")'>지급/차감</button></div>";
	o += "</div>";
	
	
	document.getElementById("modal").innerHTML = o;
	document.getElementById("modal").style.display = "block";
	
}

//팝업닫기
function modalClose(){
	document.getElementById("modal").style.display = "none";
}

// 지급/차감 여부버튼
function handle(event){
	if(event.target.value == "지급"){
		document.getElementById("Fl").innerHTML = "(+)";
	}else{
		document.getElementById("Fl").innerHTML = "(-)";
	}
	document.getElementById("handlemode").value = event.target.value;
}

// 지급/차감
function payTo(mode){
	var mapping;
	if(mode == "1"){
		mapping = "./payTheMileage";
	}else if(mode == "2"){
		mapping = "./payTheDeposit";
	}
	
	var check = document.querySelectorAll('input[name="ck"]:checked');
	    	
	var val = [];
	for(var i = 0; i < check.length; i++){
		val.push(check[i].value);
	}
	const handlemode = document.getElementById("handlemode").value,
		  price = document.getElementById("price").value,
		  reasonCd = document.getElementById("reasonCd").value;
		
	$.ajax({
		type:"post",
		url: mapping,		
		traditional : true,	//ajax 배열 보는법
		data: {
			sno : val,
			handlemode : handlemode,
			price : price,
			reasonCd : reasonCd,
			ip : ip()
		},
		dateType:"json",
		success:function(i){
			if(i == 1){
				alert("처리되었습니다.");
				document.getElementById("modal").style.display = "none";
				opener.parent.location.reload();
				window.close();
			}else if(i == 0){
				alert("실패");
			}
		},
		error:function(){
			alert('통신장애')
		}
	});
	
}


