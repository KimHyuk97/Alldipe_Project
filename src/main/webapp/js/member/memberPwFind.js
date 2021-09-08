const phone = document.getElementById('phone'),
	  email = document.getElementById('email'),
	  phoneFind = document.getElementById('phoneFind'),
	  emailFind = document.getElementById('emailFind'),
	  nameVal = document.getElementById('nameVal'),
	  phoneVal = document.getElementById('phoneVal'),
	  emailVal = document.getElementById('emailVal'),
	  phoneBtn = document.getElementById('phoneBtn'),
	  emailBtn = document.getElementById('emailBtn');
	
//등록된 휴대폰인증 클릭 했을 때  
phone.addEventListener('click',() => {
	phone.setAttribute('class','choiceBox');
	email.setAttribute('class','choice');
	phoneFind.style.display = 'block';
	emailFind.style.display = "none";
});

//인증코드 만들기
var chars = '0123456789';
var stringLength = 6;
var randomstring = '';
	
for (var i = 0; i < stringLength; i++) {
	var rnum = Math.floor(Math.random() * chars.length)
	randomstring += chars.substring(rnum, rnum + 1)
}


//휴대폰인증 버튼
phoneBtn.addEventListener('click',() => {
	var message = randomstring;
	
	var talCheck = /^\d{3}\d{3,4}\d{4}$/,
		tal = phoneVal.value;
	
	
	if(phoneVal.value === ''){
		alert('휴대폰번호를 입력해주세요.');
	}else if(!talCheck.test(tal)){
		alert('휴대폰번호를 확인해주세요.');
	}else{
		$.ajax({
			type:"post",
			url:"../talSend",
			data:{ 
				"talVal" : phoneVal.value,
				 message : message 
			},
			dataType : "text",
			success:function(result){
				alert('인증번호가 발송되었습니다.');
				document.getElementById('certificationBeforeP').style.display = "none";
				document.getElementById('certificationAfterP').style.display = "block";
				document.getElementById('certificationNumP').focus();
			},
			error:function(){
				alert('통신장애(전화번호)');
			}
		});	
	}
});


//핸드폰인증코드 확인
document.getElementById('certificationP').addEventListener('click', () => {
	var message = randomstring;
	var certificationNum = document.getElementById('certificationNumP').value;
	var userPhoneVal = phoneVal.value;
	
	
	if(message === certificationNum){
		alert('인증되었습니다.');
		$.ajax({
			type:"post",
			url:"../pwFind",
			data:{
				"userPhoneVal" : userPhoneVal
			},
			dataType:"text",
			success:function(result){
				if(result != null){
					document.getElementById('phoneFind').style.display = "none";
					document.getElementById('way').value = userPhoneVal;
					document.getElementById('resultId').style.display = "block";			
				}else{
					alert('없는 전화번호입니다.');
				}
			},
			error : function(request, error){
				alert('통신장애');	
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			}
		});
	}else{
		alert('인증번호가 틀렸습니다.');	
	}
}); 


//등록된 이메일인증 클릭 했을 때
email.addEventListener('click',() => {
	phone.setAttribute('class','choice');
	email.setAttribute('class','choiceBox');
	phoneFind.style.display = 'none';
	emailFind.style.display = "block";
});

//이메일인증 버튼
emailBtn.addEventListener('click',()=>{
	var userEmailVal = emailVal.value;
	var message = randomstring;
	var emailCheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

	
	//이메일 정규식검사
	if(userEmailVal == ''){
		document.getElementById('emailCheckOn').innerHTML = "이메일을 입력해주세요.";
	}else if(!emailCheck.test(userEmailVal)){
		document.getElementById('emailCheckOn').innerHTML = "잘못된 이메일입니다.";
	}else{
		$.ajax({
			type:"post",
			url:"../mailSend",
			data:{
				"userEmailVal" : userEmailVal,
				"messageCode": message
			},
			dataType:"text",
			success:function(){
				alert('인증코드가 전송되었습니다.');
				certification();
			},
			error : function(request, error){
				alert('통신장애');	
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			}
		});
	}
});


//인증코드 input창
function certification(){
	document.getElementById('certificationBefore').style.display = "none";
	document.getElementById('certificationAfter').style.display = "block";
}

//인증코드 확인
const certificationBtn = document.getElementById('certification');

certificationBtn.addEventListener('click', () => {
	var message = randomstring;
	var certificationNum = document.getElementById('certificationNum').value;
	var userEmailVal = emailVal.value;
	
	if(message === certificationNum){
		
		alert('인증되었습니다.');
		$.ajax({
			type:"post",
			url:"../pwFind",
			data:{
				"userEmailVal" : userEmailVal
			},
			dataType:"text",
			success:function(result){
				if(result != null){
					document.getElementById('emailFind').style.display = "none";
					document.getElementById('resultId').style.display = "block";	
					document.getElementById('way').value = userEmailVal;
				}else{
					alert('없는 이메일입니다.');
				}
			},
			error : function(request, error){
				alert('통신장애');	
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			}
		});
	}else{
		alert('인증번호가 틀렸습니다.');	
	}
}); 


//비밀번호 변경 정규식
document.getElementById('chanageBtn').addEventListener('click',() => {
	var pw = document.getElementById('afterPw').value,
		pwCheck = document.getElementById('afterPwCheck').value,
		pwLength = pw.length,	
		num = pw.search(/[0-9]/g), //숫자
		eng = pw.search(/[a-zA-Z]/ig), // 대소문자
		spe = pw.search(/[~`!>@?/<#"'\$;:\]%\.\^,&\[*()_\+\-=|\\{}]/ig); // 특수문자
			
	if(pwLength > 9 && num < 0 && eng < 0 && spe == -1 && pw.search(/\s/) != -1){
		alert('비밀번호 정규식이 맞지않습니다.');
	}else if(pwCheck != pw){
		alert('비밀번호가 불일치합니다.');
	}else{
		pw_chanage.submit();
		alert('수정되었습니다.');
	}
	
	
});


