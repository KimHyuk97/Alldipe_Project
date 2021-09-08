function membergrade(){
	$.ajax({
		type:"post",
		url:"./membergrade",
		dataType:"json",
		success:function(i){
			gradeInfo(i);
		},
		error:function(){
			alert('통신장애');
		}
	}); 		
}

function gradeInfo(i){
	var o = "";
	o +="<option value=''>등급선택</option>";
	for(var j=0; j < i.length; j++){
		o += "<option value='"+i[j].sno+"'>"+i[j].gradeNm+"</option>";
	}
	document.getElementById('grade').setAttribute('onclick','');
	document.getElementById('grade').innerHTML = o;
}