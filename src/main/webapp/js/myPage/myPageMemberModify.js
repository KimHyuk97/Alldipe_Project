const submit2 = document.getElementById('submit2'),
	  impromationForm01 = document.getElementById('impromationForm01'),
	  impromationForm02 = document.getElementById('impromationForm02'),
	  pw = document.getElementById('pw'),
      sub2 = document.querySelector('.sub2'),
	  sub3 = document.querySelector('.sub3'),
	  sub4 = document.querySelector('.sub4'),
	  sub5 = document.querySelector('.sub5');

let inputClick01 = document.getElementById('inputClick01'),
    inputClick02 = document.getElementById('inputClick02'),
	inputClick03 = document.getElementById('inputClick03');


//pw확인
submit2.addEventListener('click',pwCheck);

//엔터키
function enterkey() {
	if (window.event.keyCode == 13) {
		pwCheck();
	}
}

function pwCheck(){
	const  id = document.getElementById('id').value,
	  	   pw = document.getElementById('pw').value;
	if(pw == ""){
		alert('비밀번호를 확인해주세요.');
	}else{
		$.ajax({
			type:"post",
			url:"../memberImpromationModify",
			data:{
				id : id,
				pw : pw
			},
			success:function(){
				impromationForm01.style.display = "none";
				impromationForm02.style.display = "block";
			},
			error:function(){
				alert('비밀번호를 정확히 입력해주세요.');
			}
		});			
	}
	
}

//비밀번호 정규식
inputClick02.addEventListener('keyup', () => {
	var pw = inputClick02.value,
		pwLength = pw.length,	
		num = pw.search(/[0-9]/g), //숫자
		eng = pw.search(/[a-zA-Z]/ig), // 대소문자
		spe = pw.search(/[~`!>@?/<#"'\$;:\]%\.\^,&\[*()_\+\-=|\\{}]/ig); // 특수문자
	
	document.getElementById('line02').style.verticalAlign = "baseline";
	sub2.setAttribute('class', 'subView');
	
	if(pwLength > 9 ){
		document.getElementById('pwcheck').innerHTML = 'o';
		if(num >= 0 && eng >= 0 && spe != -1 && pw.search(/\s/) == -1){
			document.getElementById('pwcheck02').innerHTML = 'o';
			document.getElementById('line03').style.display = "table-row";
		}else{
			document.getElementById('pwcheck02').innerHTML = 'x';
			document.getElementById('line03').style.display = "none";
		}
	}else{
		document.getElementById('pwcheck').innerHTML = 'x';
	}
			
});

//비밀번호 확인
inputClick03.addEventListener('keyup', () => {
	var pw = inputClick02.value,
		pwConfirm = inputClick03.value;
	
	document.getElementById('line03').style.verticalAlign = "baseline";
	sub3.setAttribute('class', 'subView');
		
	if(pw === pwConfirm){
		document.getElementById('pwcheckOn').innerHTML = 'o';
		document.getElementById('pw_value').value = 'y';
	}else{
		document.getElementById('pwcheckOn').innerHTML = 'x';
	}
});


//이메일 정규식 검사
const userEmail = document.getElementById('userEmail'),
	  email_check = document.getElementById('email_check');

userEmail.addEventListener('keyup', () => {
	const userEmailVal = userEmail.value,
	      emailCheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	      
	document.querySelector('#line04').style.verticalAlign = "baseline";
	sub4.setAttribute('class', 'subView');
	
	//이메일 정규식검사
	if(userEmailVal === null || !emailCheck.test(userEmailVal)){
		document.getElementById('emailCheckOn').innerHTML = "x";
		email_check.style.display = "none";
	}else{
		document.getElementById('emailCheckOn').innerHTML = "o";
		email_check.style.display = "table-cell";
	}
});

//이메일 중복확인
email_check.addEventListener('click', () => {
	const userEmailVal = userEmail.value;
	$.ajax({
		type : "POST",
		url : "../memberEmailCheck",
		data : {
			"userEmail" : userEmailVal
		},
		dataType : "json",
		success : function(checkEmail){
			if(checkEmail != null){
				document.getElementById('emailCheckOn02').innerHTML = 'x';
			}else{				
				document.getElementById('emailCheckOn02').innerHTML = 'o';
				document.getElementById('email_value').value = 'y';
			}	
		},
		error : function(){
			alert('통신장애');
		}
	});
});


//인증코드 만들기
var chars = '0123456789';
var stringLength = 6;
var randomstring = '';
	
for (var i = 0; i < stringLength; i++) {
	var rnum = Math.floor(Math.random() * chars.length)
	randomstring += chars.substring(rnum, rnum + 1)
}


//휴대폰 정규식
const talVal = document.getElementById('talVal');

talVal.addEventListener('keyup', () => {
	var talCheck = /^\d{3}\d{3,4}\d{4}$/,
		tal = talVal.value;
		
	document.querySelector('#line05').style.verticalAlign = "baseline";
	sub5.setAttribute('class', 'subView');
	
	console.log(talCheck.test(tal));
	
	if(talVal === null || !talCheck.test(tal)){
		document.getElementById('talCheck').innerHTML = "x";
		document.getElementById('talbtn').style.display = "none";
	}else{
		document.getElementById('talCheck').innerHTML = "o";
		document.getElementById('talbtn').style.display = "table-cell";
	}
});


//핸드폰인증번호 전송   
$('#talbtn').click(function(){
	talVal02 = $('#talVal').val();
	var message = randomstring;
	
	$.ajax({
		type:"post",
		url:"../talSend",
		data:{ 
			"talVal" : talVal02,
			 message : message 
		},
		dataType : "text",
		success:function(result){
			alert('인증번호가 발송되었습니다.');
			document.getElementById('certificationBefore').style.display = "none";
			document.getElementById('certificationAfter').style.display = "block";
			document.getElementById('certificationNum').focus();
		},
		error:function(){
			alert('통신장애(전화번호)');
		}
	});
});

//인증코드 확인
const certificationBtn = document.getElementById('certification');

certificationBtn.addEventListener('click', () => {
	var message = randomstring;
	var certificationNum = document.getElementById('certificationNum').value;
	
	if(message === certificationNum){
		alert('인증되었습니다.');
		document.getElementById('talCheck2').innerHTML = "o";
		certificationBtn.style.display = "none";
		document.getElementById("phone_value").value = 'y';
	}else{
		alert('인증번호가 틀렸습니다.');	
		document.getElementById('talCheck2').innerHTML = "x";
	}
}); 

//수정버튼
const modify2 = document.getElementById('memberModify');
modify2.addEventListener('click',() => {
	var pw = inputClick02.value,
		pwCheck2  = "sessionScope.mem.memPw";
		
	if(pw === pwCheck2){
		alert('현재비밀번호와 동일합니다.');
	}else{
		modify.submit();	
		alert('수정되었습니다.');
	}
});

//회원탈퇴
const memberDelete = document.getElementById('memberDelete');

memberDelete.addEventListener('click',()=>{
	location.href="../memberDeletePage";
});
