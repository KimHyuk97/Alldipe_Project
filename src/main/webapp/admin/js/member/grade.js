//등급추가 버튼
function gradeAdd(){
	location.href="memberGradeInsert?mode=추가";
}

//등급수정 버튼
function gradeMod(sno){
	location.href="memberGradeInsert?mode=수정&sno="+sno;
}

//등급삭제
function del(){
	var check = document.querySelectorAll('input[name="ck"]:checked');
	if(check.length > 0){		    	
		var val = [];
		for(var i = 0; i < check.length; i++){
			val.push(check[i].value);
		}
		
		var d = confirm("선택하신 등급을 삭제처리하시겠습니까?");
		if(d){
			$.ajax({
				type:"post",
				url:"./gradeDel",
				traditional : true,	//ajax 배열 보는법
				data: {
					sno:val
				},
				dateType:"json",
				success:function(i){
					if(i == 1){
						alert("삭제되었습니다.");
						location.reload();
					}else{
						alert("실패");
					}
				},
				error:function(){
					alert('통신장애')
				}
				
			});
		}
	}else{
		alert("삭제할 등급을 선택해주세요.");
	}
}

//회원등급 수동평가
function gradeRating(count){ //count = grade list 개수
	$.ajax({
		type:"post",
		url:"./gradeRating",
		dataType:"text",
		success:function(msg){
			if(msg == 1){
				alert("평가 완료되었습니다.");
				location.reload();
			}else{
				alert("실패");
			}
		},
		error:function(){
			alert('통신장애')
		}
	});
}