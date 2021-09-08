function formSubmit(){
	form.submit();
}

//등급삭제
function del(){
	var check = document.querySelectorAll('input[name="ck"]:checked');
	if(check.length > 0){		    	
		var val = [];
		for(var i = 0; i < check.length; i++){
			val.push(check[i].value);
		}
		
		var d = confirm("선택하신 게시판을 삭제처리하시겠습니까?");
		if(d){
			$.ajax({
				type:"post",
				url:"./boardDel",
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
		alert("삭제할 게시판을 선택해주세요.");
	}
}

//답변
function answer(sno){
	window.open("./boardAdminAnswer?sno="+sno+"", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=450,width=1030,height=700");
}

//수정
function noticeMod(sno){
	location.href="./notice?sno="+sno+"";
}

