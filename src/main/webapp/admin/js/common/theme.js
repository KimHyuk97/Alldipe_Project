function themeList(){
	$.ajax({
		type:"post",
		url:"../search/adminSearchThemeList",
		dataType:"json",
		success:function(i){
			themeInfo(i);
		},
		error:function(){
			alert('통신장애');
		}
	}); 		
}

function themeInfo(i){
	var o = "";
	o +="<option value=''>테마선택</option>";
	for(var j=0; j < i.length; j++){
		o += "<option value='"+i[j].sno+"'>"+i[j].themeNm+"</option>";
	}
	document.getElementById('theme').setAttribute('onclick','');
	document.getElementById('theme').innerHTML = o;
}