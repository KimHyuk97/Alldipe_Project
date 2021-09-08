//결제하기
const buyBtn = document.getElementById('payment'),
	  loginBtn = document.getElementById('login');

//선택 여부
var chList = document.querySelectorAll('input[name="chList"]:checked').length;

if(buyBtn != null){
	if(chList <= 0){
		buyBtn.disabled = 'disabled';
		buyBtn.value = '상품을 선택해 주세요.';
		buyBtn.style.background = '#ddd';
		buyBtn.style.color = '#fff';
	}
}

//사이트키 찾기
let siteKey = "";	

function getCookie(name) { 

     var nameOfCookie = name + "="; 
     var x = 0;

     while (x <= document.cookie.length) {  
          var y = (x + nameOfCookie.length); 
          if (document.cookie.substring(x, y) == nameOfCookie) { 

               if ((endOfCookie = document.cookie.indexOf(";", y)) == -1){	            	   
                    endOfCookie = document.cookie.length;
               } 
               return unescape(document.cookie.substring(y, endOfCookie)); 
          }
          x = document.cookie.indexOf(" ", x) + 1;
          
          if (x == 0){	        	  
               break; 
          }
     }
     return "";

}

//삭제
function cartD(sno){
	//여기서 넘어온 optionNo은 option의 sno입니다.
	if(confirm("삭제하시겠습니까?")){
		
		var data = "sno=" + sno;
		var xhr = new XMLHttpRequest();
	
		xhr.open("post", "./cartD");
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		
		xhr.send(data);
		
		xhr.onload = function(){
			
			var result = xhr.responseText;
			
			if(result=="1"){
				alert("삭제성공");
			}else{
				alert("삭제실패");
			}
			
			location.reload();
			
		}
	}
	
}

//상품옵션수 마이스너스
function minus(i,fixed,price){
	if(i==i){	
		var minus = document.getElementById('goodsCnt'+i).value;
	    var fixedPrice = fixed;
	    let optionPrice = document.getElementById('optionfixedPrice'+i).value;
		var goodsPrice = price;
		
	    let minus2 = parseInt(minus);
		if(minus < 2){
	        minus2 = 1;
	        alert('하나상품 이하로 내려갈 순 없습니다.');
		}else{
	        minus2 = minus2 - 1;							   
		    let fixedPrice2 = parseInt(fixedPrice);								   //합계금액
		    let optionPrice2 = parseInt(optionPrice);	 							//옵션값	    
		  	let goodsPrice2 = parseInt(goodsPrice);	
		  	
		  	let checkPrice2 = parseInt(document.getElementById('tp'+i).value);
		    console.log('마이너스하기전checkPrice'+checkPrice2);
			
			//상품금액 표출및 값 설정
			document.getElementById('price'+i).innerHTML = fixedPrice2*minus2 +"원";
		    document.getElementById('goodsPrice'+i).value = goodsPrice2*minus2;
			
			//합계금액 설정
			document.getElementById('totalFixedPrice'+i).innerHTML = fixedPrice2*minus2;
			document.getElementById('tp'+i).value = fixedPrice2*minus2;
			console.log('마이너스하기후checkPrice'+checkPrice2);
			
			//옵션값 설정
			document.getElementById('opPrice'+i).innerHTML = optionPrice2*minus2;
			
			//상품개수 설정
			document.getElementById('goodsCnt'+i).value = minus2;
			document.getElementById('goodsCnt'+i).innerHTML = minus2;
			
			 if(document.getElementById('check'+i).checked == true){
		    	var k = "m";
		    	var g = fixedPrice2;
		    	var d = 0;
				checkPrice(g,0,d,k);
			}
	        
		}		
	}
}

//상품옵션수 플러스
function plus(i,fixed,price,stock){
	if(i==i){
		var plus = document.getElementById('goodsCnt'+i).value;
	    var fixedPrice = fixed;
	    let optionPrice = document.getElementById('optionfixedPrice'+i).value;
		var goodsPrice = price;
		var plus2 = parseInt(plus);
		if(plus2 < stock){
			plus2 = plus2 + 1;	
			
		    let fixedPrice2 = parseInt(fixedPrice);								   //합계금액
		    let optionPrice2 = parseInt(optionPrice);	 							//옵션값
		    let goodsPrice2 = parseInt(goodsPrice);	 							//옵션값
		    let checkPrice2 = parseInt(document.getElementById('tp'+i).value);
		    console.log('플러스하기전checkPrice'+checkPrice2);
		    
		    //상품금액
			document.getElementById('price'+i).innerHTML = fixedPrice2*plus2 + "원";
		    document.getElementById('goodsPrice'+i).value = goodsPrice2*plus2;
		        
		    //합계금액 설정
			document.getElementById('totalFixedPrice'+i).innerHTML = fixedPrice2*plus2;
			document.getElementById('tp'+i).value = fixedPrice2*plus2;
			console.log('플러스하기후checkPrice'+checkPrice2);
			
			//옵션값 설정
			document.getElementById('opPrice'+i).innerHTML = optionPrice2*plus2;
			
			//상품갯수 설정
			document.getElementById('goodsCnt'+i).value = plus2;
			document.getElementById('goodsCnt'+i).innerHTML = plus2;
			
		    if(document.getElementById('check'+i).checked == true){
		    	var k = "p";
		    	var g = fixedPrice2;
		    	var d = 0;
				checkPrice(g,0,d,k);	    	
			}
			
		}else{
			alert('재고량이 없습니다.');
		}
	    
	}
}

//체크박스
var allCheck = document.getElementById('allCheck'), //전체선택id값
	check = document.querySelectorAll('.check'), //단일선택 List
	checkLength = check.length; //단일상품 길이

//선택되면 가격정하기
function checkPrice(g,c,d,k){	
	let goodsPrice = parseInt(document.getElementById('itemsPrice').value),
		deliveryCost = parseInt(document.getElementById('deliveryPrice').value);
	
	if(k == "p"){
		goodsPrice += g; 
		deliveryCost += c;	
	}else if(k == "m"){		
		goodsPrice = goodsPrice - g; 
		deliveryCost = deliveryCost - c;	
	}else{
		goodsPrice = g; 
		deliveryCost = c;	
	}
	
	document.getElementById('itemsPrice').value = goodsPrice;
	document.getElementById('deliveryPrice').value = deliveryCost;
	document.getElementById('totalItemsPrice').value = goodsPrice+deliveryCost;
	
	document.getElementById('total_goodsPrice').innerHTML = goodsPrice+"원";
	document.getElementById('total_deliveryCost').innerHTML = deliveryCost+"원";
	document.getElementById('total_fixedPrice').innerHTML = goodsPrice+deliveryCost;
}
	
//단일상품선택
function chk(i){
	var chListLength = 0;
	
    if(document.getElementById('check'+i).checked == true){
		check[i].checked = true;
		check[i].setAttribute('checked','checked');	

    	chListLength = document.querySelectorAll('input[name="chList"]:checked').length; //선택된 개수
    	
    	if(chListLength == checkLength){
    		allCheck.checked = true;
			allCheck.setAttribute('checked','checked');
    	}    
    	
    	let fixedPrice = parseInt(document.getElementById('tp'+i).value),   
		 	deliveryCost =	parseInt(document.getElementById('deliveryCost'+i).value),	
		 	dcPrice = parseInt(document.getElementById('dc'+i).value);
    	var k = "p";
    	 
    	 //가격정하러 
    	 checkPrice(fixedPrice,deliveryCost,dcPrice,k);
    	 
    	 if(buyBtn != null){
	    	 //버튼 활성화
	    	 buyBtn.removeAttribute('disabled');
			 buyBtn.value = '결제하기';
			 buyBtn.style.background = '#396ef3';
		 }
	}else{		
		check[i].checked = false;
		check[i].removeAttribute('checked');
		
		
		chListLength = document.querySelectorAll('input[name="chList"]:checked').length; //선택된 개수
		if(chListLength != checkLength){
    		allCheck.checked = false;
			allCheck.removeAttribute('checked');
    	}
    	
    	let fixedPrice = parseInt(document.getElementById('tp'+i).value),   
		 	deliveryCost =	parseInt(document.getElementById('deliveryCost'+i).value),	
		 	dcPrice = parseInt(document.getElementById('dc'+i).value);
    	var k = "m";
		checkPrice(fixedPrice,deliveryCost,dcPrice,k);
		
		//선택된 개수가 0개면 버튼 비활성화
		if(buyBtn != null){
			if(chListLength <= 0){
				buyBtn.disabled = 'disabled';
				buyBtn.value = '상품을 선택해 주세요.';
				buyBtn.style.background = '#ddd';
				buyBtn.style.color = '#fff';
			}
		}
	}  
}

	
//전체선택
allCheck.addEventListener('click',() => {
	let goodsPrice = 0;
	let fixedPrice = 0;
	let deliveryCost = 0;
	let dcPrice = 0;
		if(allCheck.checked == true){
			for(var i = 0; i<checkLength; i++){
				if(check[i].disabled==true){
					continue;
				}
				check[i].checked = true;
				
				goodsPrice += parseInt(document.getElementById('goodsPrice'+i).value);
				fixedPrice += parseInt(document.getElementById('tp'+i).value);   
				deliveryCost +=	parseInt(document.getElementById('deliveryCost'+i).value);	
				dcPrice += parseInt(document.getElementById('dc'+i).value);
		    }
			
			document.getElementById('itemsPrice').value = fixedPrice;
			document.getElementById('deliveryPrice').value = deliveryCost;
			document.getElementById('totalItemsPrice').value = fixedPrice+deliveryCost;
			
			document.getElementById('total_goodsPrice').innerHTML = fixedPrice+"원";
			document.getElementById('total_deliveryCost').innerHTML = deliveryCost+"원";
			document.getElementById('total_fixedPrice').innerHTML = fixedPrice+deliveryCost;
			
			if(buyBtn != null){
				//버튼활성화
				buyBtn.removeAttribute('disabled');
				buyBtn.value = '결제하기';
				buyBtn.style.background = '#396ef3';
			}
		}else{
			for(var i = 0; i<checkLength; i++){			
				check[i].checked = false;
					
				document.getElementById('itemsPrice').value = 0;
				document.getElementById('deliveryPrice').value = 0;
				document.getElementById('totalItemsPrice').value = 0;
				
				document.getElementById('total_goodsPrice').innerHTML = 0+"원";
				document.getElementById('total_deliveryCost').innerHTML = 0+"원";
				document.getElementById('total_fixedPrice').innerHTML = 0;
				
			}
			
			if(buyBtn != null){
				//버튼 비활성화
				buyBtn.disabled = 'disabled';
				buyBtn.value = '상품을 선택해 주세요.';
				buyBtn.style.background = '#ddd';
				buyBtn.style.color = '#fff';
			}
	
		}
});	

	  
//로그인버튼
if(loginBtn != null){
	loginBtn.addEventListener('click',() => {
		alert('로그인 후 이용해주세요.');
		location.href="login";
	});
}

if(buyBtn != null){
	buyBtn.addEventListener('click', () => {
			if(confirm("주문페이지로 이동하시겠습니까?")){
				if(document.querySelectorAll('input[name="chList"]:checked').length>0){
					
//					for(var i = 0; i<document.getElementsByClassName("check").length; i++){
//						document.getElementsByClassName("check")[i].value = document.getElementsByClassName("check")[i].value + "," + document.getElementsByClassName("qty")[i].value;
//					}
					
					document.form.submit();
				}else{
					alert("선택된 항목이 없습니다!");
					return false;
				}
			}
	});
}



	