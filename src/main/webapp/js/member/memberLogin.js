const userId = document.getElementById('userId'),
	  userPw = document.getElementById('userPw'),
	  idNull = document.getElementById('idNull'),
	  pwNull = document.getElementById('pwNull'),
	  loginBtn = document.getElementById('loginBtn'),
	  submitId = document.getElementById('submitId');


//ip주소확인
document.getElementById('myIp').value=ip();
//ip주소 끝

//쿠키확인하기
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
//쿠키 검색
var cookie = getCookie('saveId');
if(cookie != null){
	userId.value = cookie;	
}else{
	userId.value = '';
}	

userId.addEventListener('keydown', () => {
	userId.style.borderBottom = '1px solid #e5e5e5';
	idNull.style.display = 'none';
});

userPw.addEventListener('keydown', () => {
	userPw.style.borderBottom = '1px solid #e5e5e5';
	pwNull.style.display = 'none';
});

//로그인 버튼
loginBtn.addEventListener('click', () => {
	login();
});

//엔터키
function enterkey() {
	if (window.event.keyCode == 13) {
		login();
	}
}

//아이디저장(쿠키)
submitId.addEventListener('click',() => {
	if(submitId.value = "y"){
		submitId.value = ""
	}else{
		submitId.value = "y";
	}
});

//로그인 시작
function login() {
	if ((userId.value == null || userId.value == '') && (userPw.value == null || userPw.value == '')) {
		idNull.style.display = 'flex';
		pwNull.style.display = 'flex';
		userId.style.borderBottom = "1px solid red";
		userPw.style.borderBottom = "1px solid red";
	} else if (userId.value == null || userId.value == '') {
		idNull.style.display = 'flex';
		userId.style.borderBottom = "1px solid red";
	} else if (userPw.value == null || userPw.value == '') {
		pwNull.style.display = 'flex';
		userPw.style.borderBottom = "1px solid red";
	} else {
		if(submitId.value === "y" || submitId.value == ""){
			saveId();
		}
		siteKey();
		login_form.submit();
	}
}

//쿠키저장
function saveId(){

	var cookie = getCookie('saveId');
	
	if(cookie != null || cookie == ""){
		deleteCookie('saveId');
		var date = new Date();
    	date.setFullYear(date.getFullYear()+1);

		var coo = '';
		coo += 'saveId ='+ userId.value +'; expires = ' + date.toUTCString();
		document.cookie = coo;		
		
	}else{
		var date = new Date();
    	date.setFullYear(date.getFullYear()+1);

		var coo = '';
		coo += 'saveId ='+ userId.value +'; expires = ' + date.toUTCString();
		document.cookie = coo;	
	}
}


//쿠키삭제
var deleteCookie = function(name) {
document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}

//사이트키 확인
function siteKey(){
	var cookie = getCookie('siteKey');
		if (cookie != "") {
		     document.getElementById('siteKey').value = cookie;
		}else{
			//인증코드 만들기
			var chars = '0123456789';
			var stringLength = 10;
			var randomstring = '';
				
			for (var i = 0; i < stringLength; i++) {
				var rnum = Math.floor(Math.random() * chars.length);
				randomstring += chars.substring(rnum, rnum + 1);
			}
			
			document.getElementById('siteKey').value = randomstring;
			//쿠키저장
			var date = new Date();
	    	date.setFullYear(date.getFullYear()+1);
			var coo = '';
			coo += 'siteKey ='+ siteKey +'; expires = ' + date.toUTCString();
			document.cookie = coo;
		}		
}
