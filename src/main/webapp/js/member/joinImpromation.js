let inputClick01 = document.getElementById('inputClick01'),
	pw = document.getElementById('inputClick02'),
	inputClick03 = document.getElementById('inputClick03'),
	sub1 = document.querySelector('.sub1'),
	sub2 = document.querySelector('.sub2'),
	sub3 = document.querySelector('.sub3'),
	sub4 = document.querySelector('.sub4'),
	sub5 = document.querySelector('.sub5');
	



//아이디 정규식
inputClick01.addEventListener('keyup', () => {
	var id = inputClick01.value,
		num = id.search(/[0-9]/g), //숫자
		eng = id.search(/[a-zA-Z]/ig), // 대소문자
		spe = id.search(/[~`!>@?/<#"'\$;:\]%\.\^,&\[*()_\+\-=|\\{}]/ig); // 특수문자
	
	document.getElementById('line01').style.verticalAlign = "baseline";
	sub1.setAttribute('class', 'subView');

	if(id.length > 5 && num >= 0 && eng >= 0 && spe == -1 && id.search(/\s/) == -1){
		document.getElementById('idcheck').innerHTML = 'o';
		document.getElementById('id_check').style.display = "table-cell";
	}else{
		document.getElementById('idcheck').innerHTML = 'x';
		document.getElementById('id_check').style.display = "none";
	}
});

//아이디 중복검사
const id_check = document.getElementById('id_check');

id_check.addEventListener('click', () => {
	const userId = inputClick01.value;
	$.ajax({
		type : "POST",
		url : "memberIdCheck",
		data : {
			"id" : userId
		},
		dataType : "json",
		success : function(checkId){
			if(checkId != null){
				document.getElementById('idcheck02').innerHTML = 'x';
			}else{				
				document.getElementById('idcheck02').innerHTML = 'o';
				document.getElementById('id_value').value = 'y';
			}	
		},
		error : function(){
			alert('통신장애');
		}
	});
});


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

//이름
document.getElementById('nameVal').addEventListener('keyup', () => {
	document.getElementById('name_value').value = "y";
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
		url : "memberEmailCheck",
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
		url:"talSend",
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



//주소창
const adr01 = document.querySelector('.adr01'),
 	  adr02 = document.querySelector('.adr02');

function adrSearch() {
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
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;

				document.getElementById('line05').style.verticalAlign = "baseline";
				adr01.style.display = 'none';
				adr02.style.display = 'flex';
				adr02.style.marginTop = 10+'px';
				
				// 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
                if(document.getElementById("sample6_detailAddress") != null){
                	document.getElementById("adr_value").value = "y";
                }
            }
        }).open();
    }

//추천인 아이디
const eventId = document.getElementById('eventId'),
	  evnetIdView = document.getElementById('eventIdView');


eventId.addEventListener('click',() => {
	eventIdView.classList.toggle('eventId');	
});
    
    
$(function () {
    // 체크박스 전체 선택
    $(".box_02").on("click", "#check_all", function () {
	    $(this).parents(".box_02").find('input').prop("checked", $(this).is(":checked"));
    	if(document.getElementById('check_all').checked == true){
    		document.getElementById("check_1").value = 1;
	        document.getElementById("check_2").value = 1;
	        document.getElementById("check_3").value = 1;
	        document.getElementById("check_4").value = 1;
	        document.getElementById("check_5").value = 1;
    	}else{    	
	        document.getElementById("check_1").value = 0;
	        document.getElementById("check_2").value = 0;
	        document.getElementById("check_3").value = 0;
	        document.getElementById("check_4").value = 0;
	        document.getElementById("check_5").value = 0;
    	}
    });

    // 체크박스 개별 선택
    $(".box_02").on("click", ".check", function () {
        var is_checked = true;

        $(".box_02 .check").each(function () {
            is_checked = is_checked && $(this).is(":checked");
        });

        $("#check_all").prop("checked", is_checked);
    });
})   

// 체크박스 개별 선택 후 값 주기
function check(i){
	if(i.checked == true){
		i.value = 1;
		console.log('i= '+ i.value);
	}else{
		i.value = 0;
		console.log('i= '+ i.value);
	}
}
    
    
//submit
const submit = document.getElementById('join_submit');
	  

submit.addEventListener('click', () => {
	if(!document.getElementById('id_value').value){
		alert('아이디를 확인해주세요');
		inputClick01.focus();
	}else if(!document.getElementById('pw_value').value){
		alert('비밀번호를 확인해주세요');
		inputClick02.focus();
	}else if(!document.getElementById('nameVal').value){
		alert('이름을 입력해주세요');
		document.getElementById('nameVal').focus();
	}else if(!document.getElementById('email_value').value){
		alert('이메일를 확인해주세요');
		userEmail.focus();
	//}else if(!document.getElementById("phone_value").value){
	//alert('휴대폰인증을 받아주세요');
	//	talVal.focus();
	}else if(!document.getElementById("adr_value").value){
		alert('주소를 입력해주세요');
		document.getElementById('sample6_detailAddress').focus();
	}else if(!document.getElementById("check_1").value && document.getElementById("check_1").value == 0){
		alert('이용약관동의를 체크해주세요.');
	}else if(!document.getElementById("check_2").value && document.getElementById("check_2").value == 0){
		alert('개인정보수집 및 이용약관 동의를 체크해주세요.');
	}else if(!document.getElementById("check_3").value && document.getElementById("check_3").value == 0){
		alert('전자금융거래 이용약관 동의를 체크해주세요.');
	}else if(!document.getElementById("check_4").value && document.getElementById("check_4").value == 0){
		alert('개인정보 제 3자 제공를 체크해주세요.');
	}else if(!document.getElementById("check_5").value && document.getElementById("check_5").value == 0){
		alert('만 14세 이상의 동의를 체크해주세요.');
	}else{
		join_form.submit();
	}
});	



   
    
