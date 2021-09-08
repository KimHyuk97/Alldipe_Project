//ip주소확인
document.getElementById('myIp').value=ip();
//ip주소 끝

//비밀번호 변경
function pwChangeBtn(){
	var o = "";
	o += "<span class='pwtext'>비밀번호</span><input type='password' id='pw'> <button type='button' id='pwBtn02' onclick='pwC()'>정규식</button><br/>"
	o += "<p id='check01'></p>"

	document.getElementById("pwChange").innerHTML = o;
}


//비밀번호 정규식
function pwC(){
	var pw = document.getElementById("pw").value,
		pwLength = pw.length,	
		num = pw.search(/[0-9]/g), //숫자
		eng = pw.search(/[a-zA-Z]/ig), // 대소문자
		spe = pw.search(/[~`!>@?/<#"'\$;:\]%\.\^,&\[*()_\+\-=|\\{}]/ig); // 특수문자
		
	if(pwLength > 9 ){		
		if(num >= 0 && eng >= 0 && spe != -1 && pw.search(/\s/) == -1){
			
			document.getElementById('check01').innerHTML = "";
			document.getElementById('pwBtn02').setAttribute('onclick','pwChangeBtn()');
			document.getElementById('pwBtn02').innerHTML = "재검색";
			document.getElementById("pwVal").value = 0;
			
			const div = document.createElement("div");
						
			const span = document.createElement("span");
			span.className = "pwtext";
			span.innerText = "비밀번호확인";
			div.appendChild(span);
			
			const input = document.createElement("input");
			input.type = "password";
			input.name = "memPw";
			input.id   = "pwCheck";
			input.setAttribute("onKeyup","pwC2()");
			div.appendChild(input);
			
			const p2 = document.createElement("p");
			p2.id    = "check02";
			div.appendChild(p2);
						
			document.getElementById("pwChange").appendChild(div);
			
		}else{
			document.getElementById('check01').innerHTML = '영문/숫자/특수문자(공백 제외)만 허용합니다';
		}
	}else{
		document.getElementById('check01').innerHTML = '비밀번호는 10자 이상입니다.';
	}
}


//비밀번호 확인
function pwC2(){
	var pw = document.getElementById("pw").value,
		pwConfirm = document.getElementById('pwCheck').value;
		
	if(pw === pwConfirm){
		document.getElementById('check02').innerHTML = "";
		document.getElementById("pwVal").value = 1;
	}else{
		document.getElementById('check02').innerHTML = '비밀번호가 서로 다릅니다.';
	}	
}


//이메일 정규식 검사
const userEmail = document.getElementById('userEmail');
userEmail.addEventListener('keyup', () => {
	console.log('1');
	const userEmailVal = userEmail.value,
	      emailCheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	
	//이메일 정규식검사
	if(userEmailVal != null && !emailCheck.test(userEmailVal)){
		document.getElementById('emailBtn').disabled = true;
		document.getElementById('email_value').value = 0;
	}else{
		document.getElementById('emailBtn').disabled = false;
		document.getElementById('email_value').value = 1;
	}
});

//이메일 중복확인
function emailCheck(){
	const userEmail = document.getElementById('userEmail'),
		  userEmailVal = userEmail.value;
	$.ajax({
		type : "POST",
		url : "../../memberEmailCheck",
		data : {
			"userEmail" : userEmailVal
		},
		dataType : "json",
		success : function(checkEmail){
			if(checkEmail == null){				
				document.getElementById('email_value').value = 1;
				alert("사용가능한 이메일입니다.");
			}else{
				document.getElementById('email_value').value = 0;
				alert("이미 사용중인 이메일입니다.");
			}
		},
		error : function(){
			alert('통신장애');
		}
	});
}


//수정하기
function save(){
	if(document.getElementById('email_value').value == 0){
		alert("이메일 중복확인을 해주세요.");
	}else if(document.getElementById("pwVal").value == 0){
		alert("비밀번호를 확인해주세요.");
	}else{
		modify.submit();
	}
}
