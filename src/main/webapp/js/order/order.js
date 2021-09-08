//배송지 창
function deliveryMod(memNo){
	//내 배송지 불러오기
	$.ajax({
		type:"post",
		url:"myPageDelivery",
		data:{memNo:memNo},
		dataType:"json",
		success:function(i){
			selectDelivery(i);
			document.getElementById('deliveryPopup').style.display = "block";	
		},
		error:function(i){
			console.log('x');	
		},
	});
	
}

function selectDelivery(i){
	const dd = document.getElementById('deliveryPopup');
	let output = "";	
	const m = 1;
	const a = 2; 
	if(i != null){
		output += "<div class='deliveryMode'><input type='button' value='X' class='dclose' onclick='dclose()'><div class='dNm'><h1>배송지</h1></div><table>";
		output += "<colgroup><col width='15%'/><col width='70%'/><col width='15%'/></colgroup>";
		output += "<tr><th>선택</th><th>배송정보</th><th>수정</th></tr>";
		
		for(let v=0; v < i.length; v++){
			output += "<tr>";
			output += "<input type='hidden' id='getName22"+v+"' value='"+i[v].getName+"'>";
			output += "<input type='hidden' id='phone22"+v+"' value='"+i[v].phone+"'>";
			output += "<input type='hidden' id='zonecode22"+v+"' value='"+i[v].zonecode+"'>";
			output += "<input type='hidden' id='addressSub22"+v+"' value='"+i[v].addressSub+"'>";
			output += "<input type='hidden' id='address22"+v+"' value='"+i[v].address+"'>";
			output += "<td><input type='button' class='checkDDD' onclick='dcheck("+v+")' value=''></td>";
			output += "<td>";
			if(i[v].getName != null || i[v].phone != null){
				output += "<div class='dimp'><p style='font-weight:bold'>"+i[v].address +"&nbsp;&nbsp;"+ i[v].addressSub+"</p>"
				output += '<p>'+i[v].getName +'</p><p>'+i[v].phone+'</p></div></td>';
			}else{
				output += "<p style='font-weight:bold'>"+i[v].address +"&nbsp;&nbsp;"+ i[v].addressSub+"</p></td>";
			}
			output += "<td><input type='button' class='modImage' value='' onclick='dMod("+v+","+i[v].dNo+")'></td>";
		}
		output += "</table><input type='button' class='dadd' value='배송지 추가 +' onclick='addD()'></div>";
		dd.innerHTML=output;
	}
}

//배송지선택
function dcheck(v){
	var zonecode = document.getElementById('zonecode22'+v).value;
	var address = document.getElementById('address22'+v).value;
	var addressSub = document.getElementById('addressSub22'+v).value;
	var getName = document.getElementById('getName22'+v).value;
	var phone = document.getElementById('phone22'+v).value;
	
	document.getElementById('dAddress').innerHTML = zonecode+ address + addressSub;	
	document.getElementById('receiverZonecode').value= zonecode;
	document.getElementById('receiverAddress').value= address;
	document.getElementById('receiverAddressSub').value= addressSub;
	if(getName != 'null'){		
		document.getElementById('dGetName').innerHTML = getName;
		document.getElementById('receiverName').value= getName;
	}
	
	if(phone != 'null'){
		document.getElementById('dPhone').innerHTML = phone;	
		document.getElementById('receiverCellPhone').value= phone;	
	}
	
	document.getElementById('deliveryPopup').style.display = "none";
}

//배송지 닫기
function dclose(){
	document.getElementById('deliveryPopup').style.display = "none";	
}

//배송지 수정
function dMod(v,i){	
	new daum.Postcode({
     oncomplete: function(data) {
                
     var addr = ''; // 주소 변수
     var extraAddr = ''; // 참고항목 변수

               
     if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
          addr = data.roadAddress;
     } else { // 사용자가 지번 주소를 선택했을 경우(J)
          addr = data.jibunAddress;
     }

     // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
     if(data.userSelectedType === 'R'){
	     // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	     // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	     if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	        extraAddr += data.bname;
	     }
	     // 건물명이 있고, 공동주택일 경우 추가한다.
	     if(data.buildingName !== '' && data.apartment === 'Y'){
	        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	     }
	     // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	     if(extraAddr !== ''){
	        extraAddr = ' (' + extraAddr + ')';
	     }
     }
	
     	// 우편번호와 주소 정보를 해당 필드에 넣는다.  
	    dModifyMode(data.zonecode,addr,i);	     
	     
    }
  }).open();
}

//배송지 수정창
function dModifyMode(zonecode,addr,i){
	const mod = document.getElementById('deliveryMod22'),
		  memNo = $('#deliveryMod22').data('no');
		  
	var d = "";
	d += "<div class='deliveryMode'>";
	d += "<input type='hidden' id='dNo' value='"+i+"'>";
	d += "<input type='hidden' name='memNo' id='memNo33' value='"+memNo+"'>";
	d += "<input type='hidden' id='deliveryFl3' value='n'>";
	d += "<input type='hidden' id='zone3' value='"+zonecode+"'>";
	d += "<input type='hidden' id='addrVal3' value='"+addr+"'>";
	d += "<div style='position:relative; height: 80px;'><h1>배송지 수정</h1><input type='button' style='position:absolute; bottom:1em; left:1em;' onclick='modClose()' value='뒤로가기'><input style='position:absolute; bottom:1em; right:1em;' type='button' onclick='addAddress()' value='재검색'></div>"
	d += "<table><tr>";
    d += "<th>우편번호</th>";
    d += "<td><p class='addFont'>"+zonecode+"</p></td></tr>";
    d += "<tr><th>주소</th>";
    d += "<td><p class='addFont'>"+addr+"</p></td></tr>";
    d += "<tr><th>상세주소</th>";
    d += "<td><input type='text' id='addAddress3' placeholder='나머지 주소를 입력해주세요.'></td></tr>";
    d += "<tr><th>받으실 분</th>";
    d += "<td><input type='text' id='nn3' placeholder='받으실 분 성함을 입력해주세요.'></td></tr>";
    d += "<tr><th>전화번호</th>";
    d += "<td><input type='text' id='pp3' placeholder='전화번호를 입력해주세요.'></td></tr></table>";
    d += "<input type='button' class='modBtn' onclick='dm()' value='수정'></div>";
	
	mod.innerHTML=d;
	
	document.getElementById('addAddress3').focus();
	document.getElementById('deliveryPopup').style.display = "none";
	mod.style.display = "block";
}

//뒤로가기 버튼
function modClose(){
	document.getElementById("deliveryMod22").style.display="none";
	document.getElementById('deliveryPopup').style.display = "block";	
}


//수정 창 재검색
function addAddress(){
	dMod();
}

//수정하기
function dm(){
	const zone = document.getElementById('zone3').value,
		  addr = document.getElementById('addrVal3').value,
		  address = document.getElementById('addAddress3').value,
		  getName = document.getElementById('nn3').value,
		  phone = document.getElementById('pp3').value,
		  memNo = document.getElementById('memNo33').value,
		  dNo = document.getElementById('dNo').value,
		  deliveryFl = document.getElementById('deliveryFl3').value;

			
	if(address == "" || address == null){
		alert('상세주소를 입력해주세요.');
		document.getElementById('addAddress3').focus();
	}else if(getName == "" || getName == null){
		alert('받으실 분 성함을 입력해주세요.');
		document.getElementById('nn3').focus();
	}else if(phone == "" || phone == null){
		alert('받으실 분 전화번호를 입력해주세요.');
		document.getElementById('pp3').focus();
	}else{
		$.ajax({
			type: "post",
			url : "modifyAddr",
			data: {
				memNo : memNo,
				zone : zone,
				addr : addr,
				address : address,
				getName : getName,
				phone : phone,
				dNo : dNo,
				deliveryFl : deliveryFl
			},
			dataType:"json",
			success:function(i){
				deliveryMod(memNo);	
				document.getElementById("deliveryMod22").style.display="none";
				document.getElementById('deliveryPopup').style.display = "block";	
				alert('수정되었습니다.');	
			},
			error:function(){
				console.log('x');	
			}
		});
	}
}

//배송지 추가
function addD(){
	new daum.Postcode({
	     oncomplete: function(data) {
	                
	     var addr = ''; // 주소 변수
	     var extraAddr = ''; // 참고항목 변수
	
	               
	     if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	          addr = data.roadAddress;
	     } else { // 사용자가 지번 주소를 선택했을 경우(J)
	          addr = data.jibunAddress;
	     }
	
	     // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	     if(data.userSelectedType === 'R'){
		     // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		     // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		     if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		        extraAddr += data.bname;
		     }
		     // 건물명이 있고, 공동주택일 경우 추가한다.
		     if(data.buildingName !== '' && data.apartment === 'Y'){
		        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		     }
		     // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		     if(extraAddr !== ''){
		        extraAddr = ' (' + extraAddr + ')';
		     }
	     }
		
	     	// 우편번호와 주소 정보를 해당 필드에 넣는다.  
		    dAddMode(data.zonecode,addr);	     
		     
	    }
  	}).open();
}


function  dAddMode(zone,addr){
	const add = document.getElementById('deliveryadd22'),
		  memNo = $('#deliveryadd22').data('no');
		  
	var d = "";
	d += "<div class='deliveryMode'>";
	d += "<input type='hidden' name='memNo' id='memNo33' value='"+memNo+"'>";
	d += "<input type='hidden' id='deliveryFl3' value='n'>";
	d += "<input type='hidden' id='zone3' value='"+zone+"'>";
	d += "<input type='hidden' id='addrVal3' value='"+addr+"'>";
	d += "<div style='position:relative; height: 80px;'><h1>배송지 추가</h1><input type='button' style='position:absolute; bottom:1em; left:1em;' onclick='addClose()' value='뒤로가기'><input style='position:absolute; bottom:1em; right:1em;' type='button' onclick='addAddress22()' value='재검색'></div>"
	d += "<table><tr>";
    d += "<th>우편번호</th>";
    d += "<td><p class='addFont'>"+zone+"</p></td></tr>";
    d += "<tr><th>주소</th>";
    d += "<td><p class='addFont'>"+addr+"</p></td></tr>";
    d += "<tr><th>상세주소</th>";
    d += "<td><input type='text' id='addAddress3' placeholder='나머지 주소를 입력해주세요.'></td></tr>";
    d += "<tr><th>받으실 분</th>";
    d += "<td><input type='text' id='nn3' placeholder='받으실 분 성함을 입력해주세요.'></td></tr>";
    d += "<tr><th>전화번호</th>";
    d += "<td><input type='text' id='pp3' placeholder='전화번호를 입력해주세요.'></td></tr></table>";
    d += "<input type='button' class='modBtn' onclick='addDelivery()' value='추가'></div>";
	
	add.innerHTML=d;
	
	document.getElementById('addAddress3').focus();
	document.getElementById('deliveryPopup').style.display = "none";
	add.style.display = "block";
}

//뒤로가기 버튼
function addClose(){
	document.getElementById("deliveryadd22").style.display="none";
	document.getElementById('deliveryPopup').style.display = "block";	
}


//수정 창 재검색
function addAddress22(){
	addD();
}


//배송지추가
function addDelivery(){
	const zone = document.getElementById('zone3').value,
		  addr = document.getElementById('addrVal3').value,
		  address = document.getElementById('addAddress3').value,
		  memNo = document.getElementById('memNo33').value,
		  deliveryFl = document.getElementById('deliveryFl3').value,
		  getName = document.getElementById('nn3').value,
		  phone = document.getElementById('pp3').value;	

	if(address == "" || address == null){
		alert('상세주소를 입력해주세요.');
		document.getElementById('addAddress3').focus();
	}else if(getName == "" || getName == null){
		alert('받으실 분 성함을 입력해주세요.');
		document.getElementById('nn3').focus();
	}else if(phone == "" || phone == null){
		alert('받으실 분 전화번호를 입력해주세요.');
		document.getElementById('pp3').focus();
	}else{		
		$.ajax({
			type: "post",
			url : "addAddressController",
			data: {
				memNo : memNo,
				zone : zone,
				addr : addr,
				address : address,
				deliveryFl : deliveryFl,
				getName : getName,
				phone : phone,
			},
			dataType:"json",
			success:function(i){
				deliveryMod(memNo);	
				document.getElementById("deliveryadd22").style.display="none";
				document.getElementById('deliveryPopup').style.display = "block";	
			},
			error:function(){
				console.log('x');	
			}
		});
	}
}



//받으실 장소
function subAddr(event) {
    if(event.target.value == "택배함"){
    	var o = "";
    	o += "<div class='subDDD'><h1>택배함 정보</h1>"
    	o += "<input type='text' name='pickUpContent' placeholder='택배함 위치 / 택배함 번호 ㆍ 비밀번호'>";
    	o += "<p class='pr'>※ 지정되지 않은 택배함은 위치만 적어주세요.<br/>※ 배송완료 후 택배함 번호와 비밀번호를 알려드립니다.</p></div>";
    	
    	document.getElementById('subAddr').style.display = 'block';
    	document.getElementById('subAddr').innerHTML = o;
    }else{
    	document.getElementById('subAddr').innerHTML = "";
    	document.getElementById('subAddr').style.display = 'none';
    }
}


//공동현관 비밀번호
function doorPw22(event){
    if(event.target.value == "공동현관"){
    	var o = "";
    	o += "<div class='subDDD'><h1>공동현관 비밀번호</h1>"
    	o += "<input type='text' name='meansContent' placeholder='예 : #1234*'>";
    	o += "<p class='pd'>※ 특수문자를 포함한 정확한 비밀번호를 입력해주세요.</p></div>";
    	
    	document.getElementById('doorPassword').style.display = 'block';
    	document.getElementById('doorPassword').innerHTML = o;
    }else{
    	document.getElementById('doorPassword').innerHTML = "";
    	document.getElementById('doorPassword').style.display = 'none';
    }
}

//ip주소확인
document.getElementById('myIp').value=ip();
//ip주소 끝


//coupon

//쿠폰조회튼
document.getElementById('couponFied').addEventListener('click',() => {
	document.getElementById('couponMode').style.display = "block";
});

//쿠폰조회 닫기
document.getElementById('cClose').addEventListener('click',() => {
	document.getElementById('couponMode').style.display = "none";
});

function couponSelect(i,price,goodsNo,scmNo){	
	
	const memberCouponNo = document.getElementsByName('mcn').length;
	$.ajax({
		type:"post",
		url:"./orderCouponList",
		data:{
			goodsNo : goodsNo,
			scmNo : scmNo,
			price : price,
		},
		dataType:"json",
		success:function(v){	
			for(var k = 0; k < memberCouponNo; k++){					
				for(var j = 0; j < v.length; j++){				
					if(document.getElementById('mcn'+k).value == v[j].memberCouponNo){
						v.splice(j,1)
					}
				}
			}		
			memberCouponList(v,i,price,goodsNo,scmNo);
		},
		error:function(request,status,error){
        	alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
        }
	});
	
}
//쿠폰리스트
function memberCouponList(v,q,price,goodsNo,scmNo){	//v = 쿠폰리스트 값, q = 시리얼넘
	var i = "";
	i += "<div class='couponList'>";
	i += "<div class='couponListTitle'>";
	i += "<h1>적용 가능 쿠폰 리스트</h1>";
	i += "<span>해당 상품에 적용 가능한 쿠폰입니다. 쿠폰을 선택해주세요.</span>";
	i += "</div>";
	i += "<div>";
	i += "<table><colgroup><col width='50%'/><col width='25%'/><col width='25%'/></colgroup><tr><th>쿠폰명</th><th>할인율</th><th>할인금액</th></tr>";
	for(var s = 0; s < v.length; s++){	
		i += "<tr>";
		i += "<td style='text-align: start; padding-left: 15px;'><input type='hidden' id='memberCouponNo2"+s+"' value='"+v[s].memberCouponNo+"'><input type='hidden' id='couponSno"+s+"' value='"+v[s].couponNo+"'>";
		i += "<input style='margin-right:10px;' type='radio' onclick='couponCheck("+q+","+price+","+goodsNo+","+scmNo+","+s+")' name='couponSel' value='"+v[s].couponSalePrice+"'>"+v[s].couponNm+"</td>";
		i += "<td>"+v[s].couponBenefit+"%</td>";
		i += "<td>"+v[s].couponSalePrice+"원</td>";
		i += "</tr>";
	}
	i += "</table>";
	i += "</div>";
	i += "<div class='couponRealBtn'><input type='button' value='취소' onclick='csClose()'></div>";
	i += "</div>";
	
	document.getElementById('couponListModal').innerHTML = i;
	document.getElementById('couponListModal').style.display = "block";
}

//쿠폰리스트 닫기
function csClose(){
	document.getElementById('couponListModal').style.display = "none";
}

//쿠폰선택
function couponCheck(q,price,goodsNo,scmNo,s){ //q = 상품 시리얼넘버 , s = 쿠폰시리얼넘버
	var radioChk = document.getElementsByName('couponSel');
	var chkCnt = 0;
	var chkVal;
	for(var i=0; i < radioChk.length; i++){
		if(radioChk[i].checked == true){
			chkCnt++;
			chkVal = radioChk[i].value;
		}
	}
	if(chkCnt < 1){
		alert('쿠폰을 선택해주세요.');
	}else{		
		document.getElementById('mcn'+q).value = document.getElementById('memberCouponNo2'+s).value;	//쿠폰고유번호
		document.getElementById('cn'+q).value = document.getElementById('couponSno'+s).value;			//쿠폰번호
		document.getElementById('csp'+q).value = chkVal;												//쿠폰할인금액
		
		document.getElementById('couponSalePrice'+q).innerHTML = chkVal+"원";
		
		document.getElementById('couponBtnChange'+q).innerHTML = "<input type='button' value='취소' onclick='couponCancel("+q+","+price+","+goodsNo+","+scmNo+")'>";
		document.getElementById('couponListModal').style.display = "none";
		document.getElementById('couponModeBtn').innerHTML = "<input type='button' value='쿠폰적용' onclick='cBtn("+q+")'>";
//		document.getElementById('cClose').style.display = "none";
	}
}

//쿠폰선택된 쿠폰 취소하는 버튼
function couponCancel(q,price,goodsNo,scmNo){
	document.getElementById('mcn'+q).value = "";
	document.getElementById('cn'+q).value = "";
	document.getElementById('csp'+q).value = 0;
	document.getElementById('couponBtnChange'+q).innerHTML = "<input type='button' onclick='couponSelect("+q+","+price+","+goodsNo+","+scmNo+")' value='쿠폰선택'>";
	document.getElementById('couponSalePrice'+q).innerHTML = 0+"원";
	
}

//선택된 쿠폰 적용하기
function cBtn(q){
	var couponPrice23 = parseInt(0);
	
	document.getElementById('memberCouponNo'+q).value = document.getElementById('mcn'+q).value;
	document.getElementById('couponNo'+q).value = document.getElementById('cn'+q).value;
	document.getElementById('couponGoodsDcPrice'+q).value = document.getElementById('csp'+q).value;
	

	//총 쿠폰할인금액
	var bcsp = document.getElementsByName('csp').length;

	for(var i = 0; i < bcsp; i++){
		couponPrice23 = couponPrice23 + parseInt(document.getElementById('csp'+i).value);
		document.getElementById('goodsCouponSalePrice'+i).innerHTML = document.getElementById('csp'+i).value;	//각 상품 쿠폰할인금액 구하기
	}

	
	document.getElementById('coupontotalSalePrice').innerHTML = couponPrice23;								//총 상품 쿠폰할인금액 구하기
	
	document.getElementById('coupontotalSalePrice2').value = couponPrice23;
	ordertotalPriceSave();
	document.getElementById('couponMode').style.display = "none";
}

//coupon end

//mileage 
	var memberMileage = parseInt($('#mileage').data('mileage'));	//회원 마일리지
	var totalprice = parseInt(document.getElementById('amt').value);	//총가격
	var maxPercent = 0.5;											//50% 마일리지 사용 제안
	var m = parseInt(0);											//사용할 수 있는 마일리지 계산을 위해 변수 선언
	
	if(totalprice >= parseInt(20000)){									//마일리지는 총가격이 2만원 이상일때만 사용가능
		
		m = totalprice*maxPercent;										//사용할 있는 마일리지 계산			
		
		if(m >= 10000){												//만약 사용할 수 있는 마일리지가 만원 이상이면 사용할 수 있는 마일리지를 만원으로 설정
			m = 10000;
		}		
		
		if(memberMileage >= m){										//만약 내 마일리지가 사용할 수 있는 마일리지보다 높으면 사용할 수 있는 마일리지를 최대사용가능한 마일리지로 변경
			memberMileage = m;
		}
		
		document.getElementById('mileageUseFl').innerHTML= memberMileage;
		document.getElementById('possibleMileage').value = memberMileage;
				
	}else{
		document.getElementById('mileageUseFl').innerHTML= 0;
		document.getElementById('possibleMileage').value = 0;
	}
	
	//적립금 최대사용 버튼
	function usePoint(){
		const mileage = document.getElementById('possibleMileage').value;

		document.getElementById('useMileage').value = mileage;
		
		document.getElementById('mileagetotalSalePrice').innerHTML = mileage;
		document.getElementById('mileagetotalSalePrice2').value = mileage;
		
		document.getElementById('mileageGoodsDcPrice').value = mileage;
		ordertotalPriceSave(); //총가격 정하기
	}
	
	//사용하려는 마일리지 체크
	function mileageCheck(val){
		const pm = parseInt(document.getElementById('possibleMileage').value);

		document.getElementById('mileageGoodsDcPrice').value = val;
		
		document.getElementById('mileagetotalSalePrice').innerHTML = val;
		document.getElementById('mileagetotalSalePrice2').value = val;

		if(parseInt(val) > pm){
			document.getElementById('useMileage').value = pm;
			document.getElementById('mileageGoodsDcPrice').value = pm;
			document.getElementById('mileagetotalSalePrice').innerHTML = pm;
			document.getElementById('mileagetotalSalePrice2').value = pm;

			alert('최대로 사용할 수 있는 적립금을 초과하였습니다.');
		}
		
		ordertotalPriceSave();//총가격 정하기
	}
	
//mileage 끝

//totalPrice 정하기, memberAddMileage
	function ordertotalPriceSave(){
		let amt = document.getElementById('amt').value,
			mileage = document.getElementById('mileagetotalSalePrice2').value,
			coupon  = document.getElementById('coupontotalSalePrice2').value;
	
		document.getElementById('orderTotalPrice').innerHTML = (parseInt(amt) - parseInt(mileage)) - parseInt(coupon) + "원";
		memberAddMileage()
	}
	
	function memberAddMileage(){
		$.ajax({
			type:"post",
			url : "memberAddMileage",
			dataType: "json",
			success:function(result){
				document.getElementById('memberAddMileage').innerHTML = result;
			},
			error:function(){
			
			}
		});
	}
//totalPrice 정하기 끝

//payMethod
	function transType(event){	
	console.log(event.target.value);
		if(event.target.value == 0){	
			
			var a = "";
			a += "<label class='radioBtn'>신용카드";
			a += "<input type='radio' value='CARD' name='PayMethod' checked>";
			a += "<span class='radioCheck radioCheck02'></span>";
			a += "</label>";
	
			a += "<label class='radioBtn'>계좌이체";
			a += "<input type='radio' value='BANK' name='PayMethod'>";
			a += "<span class='radioCheck radioCheck02'></span>";
			a += "</label>";
		
			a += "<label class='radioBtn'>가상계좌";
			a += "<input type='radio' value='VBANK' name='PayMethod'>";
			a += "<span class='radioCheck radioCheck02'></span>";
			a += "</label>";
			
			document.getElementById('paymethodClick').innerHTML = a;
			
		}else if(event.target.value == 1){
			
			var a = "";	
			a += "<label class='radioBtn'>계좌이체";
			a += "<input type='radio' value='BANK' name='PayMethod' checked>";
			a += "<span class='radioCheck radioCheck02'></span>";
			a += "</label>";
		
			a += "<label class='radioBtn'>가상계좌";
			a += "<input type='radio' value='VBANK' name='PayMethod'>";
			a += "<span class='radioCheck radioCheck02'></span>";
			a += "</label>";
			
			document.getElementById('paymethodClick').innerHTML = a;	
		}
	}

//payMethod end


//order
function orderPayment(){
	//	
	if(document.getElementById("check_1").checked){
		if(checkPlatform(window.navigator.userAgent) == "mobile"){
			document.payForm.action = "https://web.nicepay.co.kr/v3/v3Payment.jsp";
			document.payForm.acceptCharset="euc-kr";
			document.payForm.submit();
		}else{
			goPay(document.payForm);
		}
	}else{
		alert("약관 동의는 필수사항입니다.");
		return false;
	}
}

function nicepaySubmit(){
	document.payForm.submit();
}

function nicepayClose(){
	alert("결제가 취소 되었습니다");
}

function checkPlatform(ua) {
	if(ua === undefined) {
		ua = window.navigator.userAgent;
	}
	
	ua = ua.toLowerCase();
	var platform = {};
	var matched = {};
	var userPlatform = "pc";
	var platform_match = /(ipad)/.exec(ua) || /(ipod)/.exec(ua) 
		|| /(windows phone)/.exec(ua) || /(iphone)/.exec(ua) 
		|| /(kindle)/.exec(ua) || /(silk)/.exec(ua) || /(android)/.exec(ua) 
		|| /(win)/.exec(ua) || /(mac)/.exec(ua) || /(linux)/.exec(ua)
		|| /(cros)/.exec(ua) || /(playbook)/.exec(ua)
		|| /(bb)/.exec(ua) || /(blackberry)/.exec(ua)
		|| [];
	
	matched.platform = platform_match[0] || "";
	
	if(matched.platform) {
		platform[matched.platform] = true;
	}
	
	if(platform.android || platform.bb || platform.blackberry
			|| platform.ipad || platform.iphone 
			|| platform.ipod || platform.kindle 
			|| platform.playbook || platform.silk
			|| platform["windows phone"]) {
		userPlatform = "mobile";
	}
	
	if(platform.cros || platform.mac || platform.linux || platform.win) {
		userPlatform = "pc";
	}
	
	return userPlatform;
}
//order end

