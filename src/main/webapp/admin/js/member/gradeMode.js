/**
 * 	등급 수정, 추가 스크립트
 */

//등급이름 정규식(keyup 이벤트)
function gradeVal(){
	var val = (document.getElementById("gradeNm").value).length,
		size = document.getElementById("gradeNmlimit"),
		btn  = document.getElementById("overlapBtn");
	
	if(val < 26){		
		size.innerHTML = val;
		btn.disabled = false;
		document.getElementById("gradeNameOverlap").value = 1; //중복확인 값
	}else{
		alert("등급이름은 25자 이하여만 합니다.");
		btn.disabled = true;
		document.getElementById("gradeNameOverlap").value = 0; //중복확인 값
	}

}


//등급이름 중복확인
function overlap(){
	var val = document.getElementById("gradeNm").value; //등급이름 값		
	$.ajax({
		type:"post",
		url:"./gradeNameOverlap",
		data:{
			gradeNm : val
		},
		dataType:"text",
		success:function(i){
			if(i == 1){
				document.getElementById("gradeNameOverlap").value = 0;
				alert("사용 가능한 등급이름입니다.");
			}else{
				alert("이미 등록된 등급이름입니다.");
			}
		},
		error:function(){
			alert("통신장애");
		}
		
	});
}

//쿠폰리스트 불러오기
function coupon(sno){	//sno == 등급의 쿠폰리스트가 있을 때 쿠폰리스트의 길이 아니면 0
	$.ajax({
		type:"post",
		url:"../promotion/ajaxcouponList",
		dataType:"json",
		success:function(i){
			couponList(i,sno);
		},
		error:function(){
			alert("통신장애");
		}
	});
}

function couponList(i,sno){	//sno == 등급의 쿠폰리스트가 있을 때 쿠폰리스트의 길이 아니면 0
	document.getElementById("modal").style.display = "block";
	var o = "";
	o += "<div class='couponListTitle'><h1>쿠폰리스트</h1></div>";
	o += "<div><button class='closeBtn' type='button' onclick='couponListClose()'>X</button></div>";
	o += "<div class='couponList02'><table>";
	o += "<colgroup><col width='5%'/><col width='5%'/><col width='15%'/><col width='15%'/><col width='15%'/><col width='5%'/><col width='10%'/></colgroup>";
	o += "<tr>";
	o += "<th><input type='checkbox' name='ckall' class='ck' id='ck_all' onclick='checkbox()'></th>";
	o += "<th>번호</th>";
	o += "<th>쿠폰명</th>";
	o += "<th>쿠폰설명</th>";
	o += "<th>사용기간</th>";
	o += "<th>할인률</th>";
	o += "<th>등록일</th>";
	o += "</tr>";
	o += "<tfoot>";
	for(var j=0; j<i.length; j++){	
	var obj = {
		couponNo : i[j].couponNo,
		couponNm : i[j].couponNm
	}
	o += "<tr>";
	o += "<td><input type='checkbox' name='ck' class='ck' id='ck01' value='"+j+"'></td>"; //값이 j인 이유는 선택된 j 번째 couponNo,couponNm을 가져오기위해
	o += "<td><input type='hidden' id='couponNo"+j+"' value="+i[j].couponNo+">"+i[j].couponNo+"</td>";
	o += "<td><input type='hidden' id='couponNm"+j+"' value="+i[j].couponNm+">"+i[j].couponNm+"</td>";
	o += "<td>"+i[j].couponDesc+"</td>";
	o += "<td>발급일로부터"+i[j].couponUsedDay+"일</td>";
	o += "<td>"+i[j].couponBenefit+"</td>";
	o += "<td>"+i[j].regDt+"</td>";
	o += "</tr>";
	}
	o += "</tfoot>";
	o += "</table>";
	o += "<button class='couponSelected' onclick='couponSelected("+sno+")'>선택적용</button>";
	o += "</div>";
	
	document.getElementById("couponContent").innerHTML = o;
}

function checkbox(){	
   if($("#ck_all").prop("checked")){
        //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
        $(".ck").prop("checked",true);
        //클릭이 안되있으면
    }else{
        //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
        $(".ck").prop("checked",false);
    }
}

//쿠폰팝업 없애기
function couponListClose(){
	document.getElementById("modal").style.display = "none";
}

//쿠폰선택적용
function couponSelected(sno){ //=sno == 등급의 쿠폰리스트가 있을 때 쿠폰리스트의 길이 아니면 0 , 
	var check = document.querySelectorAll('input[name="ck"]:checked');
	if(check.length > 0){		    	
		var val = [];

		for(var i = 0; i < check.length; i++){		
			val.push(check[i].value);
		}
	
		setOpenerVal(val,check.length,sno);
	}else{
		alert("쿠폰을 선택해주세요.");
	}
}

//쿠폰추가
function setOpenerVal(val,size,sno){ //sno == 등급의 쿠폰리스트가 있을 때 쿠폰리스트의 길이 아니면 0
	for(var i=0; i<size; i++){
		var div = document.createElement("div");
		div.className = "coupon";
		div.id = "coupon"+(i+sno); //선택된 쿠폰이 있을 시 길이 설정이 주기위해 쿠폰리스트의 길이를 더함 
				
		var span = document.createElement("span");
		span.innerText = document.getElementById("couponNm"+val[i]).value;
		document.getElementById("couponList").appendChild(span);
		div.appendChild(span);
		
		
		var input = document.createElement("input");
		input.type = "hidden";
		input.value = document.getElementById("couponNo"+val[i]).value;
		input.name  = "couponCd";
		div.appendChild(input);
		
		var button = document.createElement("button");
		button.setAttribute("onclick","couponDel("+(i+sno)+")");
		button.innerText = "x";
		button.type = "button";
		div.appendChild(button);
		
		document.getElementById("couponList").appendChild(div);
	}
	couponListClose();
}

//쿠폰삭제
function couponDel(i){
	document.getElementById('couponList').removeChild(document.getElementById('coupon'+i));
}

//신규쿠폰등록
function couponAdd(){
	
}

//저장버튼
function save(){
	if(document.getElementById("gradeNm").value == ""){
		alert("등급이름을 입력해주세요.");
		document.getElementById("gradeNm").focus();
		
	}else if(document.getElementById("orderPriceMore").value == ""){
		alert("주문금액기준을 입력해주세요.");
		document.getElementById("orderPriceMore").focus();
		
	}else if(document.getElementById("orderPriceBelow").value == ""){
		alert("주문금액기준을 입력해주세요.");
		document.getElementById("orderPriceBelow").focus();
		
	}else if(document.getElementById("orderPriceMore").value >= document.getElementById("orderPriceBelow").value){
		alert("주문금액기준 최대금액이 최소금액보다 작습니다.");
		document.getElementById("orderPriceBelow").focus();
		
	}else if(document.getElementById("orderCnt").value == ""){
		alert("주문건수를 입력해주세요.");
		document.getElementById("orderCnt").focus();
		
	}else if(document.getElementById("priceLine").value == ""){
		alert("구매금액 기준치를 입력해주세요.");
		document.getElementById("priceLine").focus();
		
	}else if(document.getElementById("mileagePercent").value == ""){
		alert("추가적립금을 입력해주세요.");
		document.getElementById("mileagePercent").focus();
	}else if(document.getElementById("gradeNameOverlap").value == 1){
		alert("입력하신 등급이름을 중복확인해주세요.");
	}else{
		if(document.getElementsByName("couponCd").length > 0){
			var input = document.createElement("input");
			input.type = "hidden";
			input.name = "couponNo";
			var val = ""
			for(var i = 0; i < document.getElementsByName("couponCd").length; i ++){				
				if(i == document.getElementsByName("couponCd").length-1) {
					val += document.getElementsByName("couponCd")[i].value;
				}else {
					val += document.getElementsByName("couponCd")[i].value+",";
				}
			}
			console.log("couponNo = "+val);
			input.value = val;
			document.getElementById("formData").appendChild(input);
		}
		form.submit();
	}
}