const c = document.getElementById('c'), //쿠폰
	  p = document.getElementById('p'), //포인트
	  title01 = document.getElementById('title01'),
	  title02 = document.getElementById('title02'),
	  title03 = document.getElementById('title03'),
	  title04 = document.getElementById('title04'),
	  title05 = document.getElementById('title05'),
	  title06 = document.getElementById('title06'),
	  content01 = document.getElementById('content01'), 
	  content02 = document.getElementById('content02'), 
	  content03 = document.getElementById('content03'), 
	  content04 = document.getElementById('content04'),
	  content05 = document.getElementById('content05'), 
	  content06 = document.getElementById('content06');

//주문내역 start
const orderGoodsList = document.getElementById('orderGoodsList'),
	  orderGoodsView = document.getElementById('orderGoodsView');

window.addEventListener('DOMContentLoaded', orderGoods);
title01.addEventListener('click',orderGoods);

function orderGoods(){
	const memNo = $('#orderGoodsViewTable').data('no');
	$.ajax({
		type: "post",
		url : "memberOrderGoods",
		data: {memNo:memNo},
		dataType:"json",
		success:function(i){
			content01.style.display = "block";
			content02.style.display = "none";
			content03.style.display = "none";
			content04.style.display = "none";
			content05.style.display = "none";
			content06.style.display = "none";
			title01.setAttribute('class','on');
			title02.setAttribute('class','');
			title03.setAttribute('class','');
			title04.setAttribute('class','');
			title05.setAttribute('class','');
			title06.setAttribute('class','');
			orderGoodsList.style.display="block";
			orderGoodsView.style.display="none";
			orderGoodsView22(i);
		},
		error:function(){
			console.log('x')	
		}
	});
}

function orderGoodsView22(i){
	const orderGoodsViewTable = document.getElementById('orderGoodsViewTable');
	let w = "";
	if(i != ""){
		for(let v=0; v < i.length; v++){
			w += "<tr>";
			w += "<td>"+i[v].goodsImg+i[v].goodsNm+i[v].goodsModleNo+i[v].goodsOptionNm+"</td>";
			w += "<td>"+i[v].regDt+"</td>";
			w += "<td><input type='button' value='"+i[v].orderNo+"' onClick='orderImpromation("+i[v]+")'></td>";
			w += "<td>"+i[v].fixedPrice+i[v].goodsCnt+"</td>";			
			w += "<td>"+i[v].orderStatus+"<input type='button' value='배송조회' onClick='deliveryStatus("+i[v].invoiceNo+")'><input type='button' value='리뷰작성' onClick='reviewWrite("+i[v].goodsNo+","+i[v].memNo+")'></td>";
			w += "</tr>";
		}	
	}else{
		w += "<tr><td colspan='5' class='noData'>주문내역이 없습니다.</td></tr>";
	}
	orderGoodsViewTable.innerHTML=w;
}

function orderImpromation(i){
	orderGoodsList.style.display="none";
	orderGoodsView.style.display="block";
	document.getElementById('orderNumber').innerHTML = i.orderNo;
	document.getElementById('orderDate').innerHTML = i.regDt;

	let view = '';
	
	view +="<td>"+i.goodsImg+i.goodsNm+i.goodsModleNo+i.goodsOptionNm+"</td>";
	view +="<td>"+i.memberDcPirce+"</td>";
	view +="<td>"+i.couponGoodsDcPrice+"</td>";
	view +="<td>"+i.goodsMileage+"</td>";
	view +="<td>"+i.goodsPrice+i.goodsCnt+"</td>";
	view +="<td>"+i.orderStatus+"<input type='button' value='배송조회' onClick='deliveryStatus("+i.invoiceNo+")'><input type='button' value='리뷰작성' onClick='reviewWrite("+i.goodsNo+","+i.memNo+")'></td>";
	
	document.getElementById('orderGoodsViewContent').innerHTML = view;
}

//주문내역 end

//배송정보조회start
function deliveryStatus(invoiceNo){
	
}
//배송정보조회end

//상품후기 start
const reviewPossble = document.getElementById('reviewPossble'),
	  myReviewView = document.getElementById('myReviewView'),
	  reviewAfter = document.getElementById('reviewAfter'),
	  reviewBefore = document.getElementById('reviewBefore'),
	  reviewAfterView = document.getElementById('reviewAfterView');

title03.addEventListener('click',() => {
	const  memNo = $('#reviewAfter').data('no');
	
	$.ajax({
		type: "post",
		url : "memberOrderGoods",
		data: {memNo:memNo},
		dataType:"json",
		success:function(i){
			content01.style.display = "none";
			content02.style.display = "none";
			content03.style.display = "block";
			content04.style.display = "none";
			content05.style.display = "none";
			content06.style.display = "none";
				
			title01.setAttribute('class','');
			title02.setAttribute('class','');
			title03.setAttribute('class','on');
			title04.setAttribute('class','');
			title05.setAttribute('class','');
			title06.setAttribute('class','');
			
			myReviewView.setAttribute('class','');
			reviewPossble.setAttribute('class','on');
			reviewBefore.style.display = "none"
			reviewAfter.style.display = "block";
			
			reviewPossbleView(i);
		},
		error:function(){
			console.log('x')	
		}
	});

});

//후기작성 가능테이블 start
reviewPossble.addEventListener('click',() => {
	const  memNo = $('#reviewAfter').data('no');
	
	myReviewView.setAttribute('class','');
	reviewPossble.setAttribute('class','on');
	reviewBefore.style.display = "none"
	reviewAfter.style.display = "block";
	
	$.ajax({
		type: "post",
		url : "memberOrderGoods",
		data: {memNo:memNo},
		dataType:"json",
		success:function(i){
			reviewPossbleView(i);
		},
		error:function(){
			console.log('x')	
		}
	});
});

function reviewPossbleView(i){
	let w = "";
	if(i != "" || i != null){
		for(let v=0; v < i.length; v++){
			w += "<tr>";
			w += "<td>"+i[v].goodsImg+i[v].goodsNm+i[v].goodsModleNo+i[v].goodsOptionNm+"</td>";
			w += "<td>"+i[v].regDt+i[v].orderStatus+"</td>";		
			w += "<td><input type='button' value='리뷰작성' onClick='reviewWrite("+i[v].goodsNo+","+i[v].memNo+")'></td>";
			w += "</tr>";
		}
	}else{
		w += "<tr><td colspan='5'>구매확정 된 상품이 없습니다.</td></tr>"
	}
	reviewAfterView.innerHTML=w;
}
	
//후기작성 가능 테이블 end

//작성된 후기 정보테이블
myReviewView.addEventListener('click',() => {
	const  memNo = $('#reviewAfter').data('no');
	
	myReviewView.setAttribute('class','on');
	reviewPossble.setAttribute('class','');
	reviewBefore.style.display = "block"
	reviewAfter.style.display = "none";
});


//상품후기 end

//리뷰쓰기start
function reviewWrite(gNo,mNo){
	
}
//리뷰쓰기end

//쿠폰 start
c.addEventListener('click',coupon);
title04.addEventListener('click',coupon);

function coupon(){
	const memNo = $('#coupon').data('no');
	$.ajax({
		type:"post",
		url:"memberCoupon",
		data:{
			memNo:memNo
		},
		dataType:"json",
		success:function(i){
			content01.style.display = "none";
			content02.style.display = "none";
			content03.style.display = "none";
			content04.style.display = "block";
			content05.style.display = "none";
			content06.style.display = "none";
			
			title01.setAttribute('class','');
			title02.setAttribute('class','');
			title03.setAttribute('class','');
			title04.setAttribute('class','on');
			title05.setAttribute('class','');
			title06.setAttribute('class','');
			
			couponView(i);	
		},error:function(){
			console.log('x');	
		}
	});
}

function couponView(i){
	var coupon = document.getElementById('coupon');
	let output = "";
	
	if(i.sno < 0){
		for(let v=0; v < i.length; v++){
			output += "<tr>";
			output += "<td>"+i[v].memberCouponNo+"</td>";
			output += "<td>"+i[v].couponNm+"</td>";
			output += "<td>"+i[v].couponBenefit+"</td>";
			output += "<td>"+i[v].memberCouponStartDate+"ㅡ"+i[v].memberCouponEndDate+"</td>";			
			output += "<td>"+i[v].memberCouponEndDate+"</td>";
			output += "</tr>";
		}
		coupon.innerHTML=output;
	}else{
		output += "<tr>";
		output += "<td>발급 받은 쿠폰이 존재하지 없습니다</td>";
		output += "</tr>";
		coupon.innerHTML=output;
	}
}	

//쿠폰end

//적립금 start
p.addEventListener('click',mileageSelect)
title05.addEventListener('click',mileageSelect)
	
function mileageSelect(){
	const memNo = $('#mileage22').data('no');
	$.ajax({
		type:"post",
		url:"memberMileage",
		data:{
			memNo:memNo
		},
		dataType:"json",
		success:function(i){
			content01.style.display = "none";
			content02.style.display = "none";
			content03.style.display = "none";
			content04.style.display = "none";
			content05.style.display = "block";
			content06.style.display = "none";
			
			title01.setAttribute('class','');
			title02.setAttribute('class','');
			title03.setAttribute('class','');
			title04.setAttribute('class','');
			title05.setAttribute('class','on');
			title06.setAttribute('class','');
	
			mileageView(i);
		},
		error:function(){
			console.log('x');	
		}
	
	});
}

function mileageView(i){
	var mileage = document.getElementById('mileage22');
	let output = "";
	
	if(i.sno < 0){
		for(let v=0; v < i.length; v++){
			output += "<tr>";
			output += "<td>"+i[v].deleteDt+"</td>";
			output += "<td>"+i[v].mileage+"</td>";
			output += "<td>"+i[v].contents+"</td>";
			if(i[v].handleCd != null){
				output += "<td>"+i[v].handleCd+"</td>"	;		
			}else{
				output += "<td> ㅡ </td>";
			}
			output += "<td>"+i[v].regDt+"</td>";
			output += "</tr>";
		}
		mileage.innerHTML=output;
	}else{
		output += "<tr>";
		output += "<td>적립금 내역이 존재하지 없습니다</td>";
		output += "</tr>";
		mileage.innerHTML=output;
	}
}
//적립금end

//배송지 관련

title02.addEventListener('click',() => {
	const memNo = $('#delivery').data('no');
	 $.ajax({
		type: "post",
		url : "myPageDelivery",
		data: {memNo:memNo},
		dataType:"json",
		success:function(i){
			content01.style.display = "none";
			content02.style.display = "block";
			content03.style.display = "none";
			content04.style.display = "none";
			content05.style.display = "none";
			content06.style.display = "none";
			title01.setAttribute('class','');
			title02.setAttribute('class','on');
			title03.setAttribute('class','');
			title04.setAttribute('class','');
			title05.setAttribute('class','');
			title06.setAttribute('class','');
			selectDelivery(i);	
		},
		error:function(){
			console.log('x');	
		}
	});
});


function selectDelivery(i){
	const dd = document.getElementById('delivery');
	let output = "";
	document.getElementById('addAddress2').value = "";
	document.getElementById('addAddress3').value = "";
	document.getElementById('nn3').value = "";
	document.getElementById('pp3').value = "";
	
	if(i != null){
		for(let v=0; v < i.length; v++){
			output += "<tr>";
			if(i[v].deliveryFl == "y"){
				output += "<td><input type='radio' name='deliveryFl' value='"+i[v].deliveryFl+"' checked></td>";
				i[v].deliveryFl = 1;
			}else{
				output += "<td><input type='radio' name='deliveryFl' onchange='handleChange("+i[v].dNo+")' value='"+i[v].deliveryFl+"'></td>";
				
				i[v].deliveryFl = 2;
			}
			output += "<td>"+i[v].address +"&nbsp;&nbsp;"+ i[v].addressSub+"</td>";
			if(i[v].getName != null){
				output += "<td>"+i[v].getName+"</td>";
			}else{
				output += "<td></td>";
			}
			if(i[v].phone != null){
				output += "<td>"+i[v].phone+"</td>";
			}else{
				output += "<td></td>";
			}
			output += "<td><input type='button' value='수정' onclick='dModify("+v+','+i[v].dNo+")'></td>";
			output += "<td><input type='button' value='삭제' onclick='dDelete32("+v+','+i[v].dNo+','+i[v].deliveryFl+")'></td></tr>";
		}
		dd.innerHTML=output;
	}else{
		
	}
}

//기본배송지 변경
function handleChange(a) {
  var  memNo = $('#delivery').data('no');
  	    
  $.ajax({
		type: "post",
		url : "basicDeliveryChange",
		data: {
			deliveryFl : 'n',
			memNo : memNo,
			dNo : a,
		},
		success:function(){
			alert('기본배송지가 변경되었습니다.');
		},
		error:function(){
			console.log('x');	
		}
	});
}




//주소창 삭제
function dDelete32(v,i,y){
	const memNo = $('#delivery').data('no');
	
	if(y == 1){
		alert('기본배송지는 삭제할 수 없습니다.');
	}else{
	 const	deleteMsg = confirm("배송지를 삭제하시겠습니까?");
		if(deleteMsg == true){
		  	$.ajax({
				type: "post",
				url : "deleteAddr",
				data: {
					dNo : i,
					memNo : memNo
				},
				dataType:"json",
				success:function(i){
					selectDelivery(i);	
				},
				error:function(){
					console.log('x');	
				}
			});
		}
	}
}


//수정창 
function dModify(v,i){
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
	     document.getElementById("deModify").style.display="block";	
	     document.getElementById('zone3').value = data.zonecode;
	     document.getElementById('addrVal3').value = addr;
	     document.getElementById('dNo').value = i;
	     
    }
  }).open();
}

const mClose = document.getElementById('mClose'),
	  addA3 = document.getElementById('addAddress03'),
	  dModifyBtn = document.getElementById('dModify');

mClose.addEventListener('click',() => {
	document.getElementById("deModify").style.display="none";
});


addA3.addEventListener('click',dModify);


document.getElementById('deliveryFl3').addEventListener('click',() => {
	document.getElementById('deliveryFl3').value = 'y';
});

//수정하기
dModifyBtn.addEventListener('click',() => {
	const zone = document.getElementById('zone3').value,
		  addr = document.getElementById('addrVal3').value,
		  address = document.getElementById('addAddress3').value,
		  getName = document.getElementById('nn3').value,
		  phone = document.getElementById('pp3').value,
		  memNo = document.getElementById('memNo33').value,
		  dNo = document.getElementById('dNo').value,
		  deliveryFl = document.getElementById('deliveryFl3').value;
		  
	if(address == ""){
		alert('상세주소를 입력해주세요.');
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
				selectDelivery(i);	
				document.getElementById("deModify").style.display="none";
				alert('수정되었습니다.');	

				
			},
			error:function(){
				console.log('x');	
			}
		});
	}
		  
		  
});




//배송지 추가 (창)
const addA = document.getElementById('addAddress'),
	  addA2 = document.getElementById('addAddress02'),
	  dClose = document.getElementById('dClose'),
	  dSave = document.getElementById('dSave');
	  
addA.addEventListener('click',addAddr);
addA2.addEventListener('click',addAddr);

function addAddr(){
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
	     document.getElementById("deAdd").style.display="block";	
	     document.getElementById('zone2').value = data.zonecode;
	     document.getElementById('addrVal2').value = addr;				 
    }
  }).open();
}

//배송지추가 창 닫기
dClose.addEventListener('click',() => {
	document.getElementById("deAdd").style.display="none";
});

//기본배송지로 설정하기
document.getElementById('deliveryFl2').addEventListener('click',deliveryFl);
addA3.addEventListener('click',dModify);

//기본배송지로 설정하기
function deliveryFl(){
	document.getElementById('deliveryFl2').value="y";
}

//배송지추가
dSave.addEventListener('click',() => {
	if(document.getElementById("addAddress").value = ''){
		alert('나머지주소를 입력해주새요.');
		document.getElementById("addAddress").style.borderColor = 'red'; 
		document.getElementById("addAddress").focus();
	}else{
		const zone = document.getElementById("zone2").value,
			  addrVal = document.getElementById("addrVal2").value,
			  addAddress = document.getElementById("addAddress2").value,
			  memNo = $('#delivery').data('no'),
			  deliveryFl = document.getElementById("deliveryFl2").value;			  
		
		$.ajax({
			type: "post",
			url : "addAddressController",
			data: {
				memNo : memNo,
				zone : zone,
				addr : addrVal,
				address : addAddress,
				deliveryFl : deliveryFl
			},
			dataType:"json",
			success:function(i){
				selectDelivery(i);	
				document.getElementById("deAdd").style.display="none";
			},
			error:function(){
				console.log('x');	
			}
		});
	}
});

title06.addEventListener('click',() => {
	content01.style.display = "none";
	content02.style.display = "none";
	content03.style.display = "none";
	content04.style.display = "none";
	content05.style.display = "none";
	content06.style.display = "block";

	title01.setAttribute('class','');
	title02.setAttribute('class','');
	title03.setAttribute('class','');
	title04.setAttribute('class','');
	title05.setAttribute('class','');
});
