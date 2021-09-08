const loginBtn = document.getElementById('loginBtn'),
	  id       = document.getElementById('id'),
	  pw       = document.getElementById('pw'),
	  checkOn  = document.getElementById('checkOn');
console.log('1');
window.onload = cookieRequest;

//쿠키 검색
function cookieRequest(){
	var cookie = getCookie('allAdId');
	if(cookie != null && cookie != ""){
		id.value = cookie;	
		checkOn.checked = true;
	}else{
		id.value = "";
		checkOn.checked = false;
	}	
};



//쿠키확인
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
//쿠키삭제
var deleteCookie = function(name) {
	document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}

//enterKey
function enterkey(){
	if (window.event.keyCode == 13) {
		login();
	} 
}


//로그인 버튼,아이디 저장
loginBtn.addEventListener('click', login);

function login(){
	if(id.value == null){
		alert('아이디를 입력해주세요.');		
	}else if(pw.value == null){
		alert('비밀번호를 입력해주세요.')
	}else{
		//아이디 저장
		if(checkOn.checked == true){
			let allAdId = "";	
					
			var cookie = getCookie('allAdId');
			if (cookie != "") {
			     allAdId = cookie;
			}else{		
				allAdId = id.value;			
				//쿠키저장
				var date = new Date();
				date.setFullYear(date.getFullYear()+1);
				var coo = '';
				coo += 'allAdId ='+ allAdId +'; expires = ' + date.toUTCString();
				document.cookie = coo;
			}	
		}else if(checkOn.checked == false){
			deleteCookie('allAdId');
		}			
		loginAction.submit();
	}
}

