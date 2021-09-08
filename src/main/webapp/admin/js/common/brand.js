function brandList(){
	$.ajax({
		type:"post",
		url:"./adminSearchbrandList",
		dataType:"json",
		success:function(i){
			brandInfo(i);
		},
		error:function(){
			alert('통신장애');
		}
	}); 		
}

function brandInfo(i){
	var o = "";
	o +="<option value=''>브랜드선택</option>";
	for(var j=0; j < i.length; j++){
		o += "<option value='"+i[j].brandCd+"'>"+i[j].brandNm+"</option>";
	}
	document.getElementById('brand').setAttribute('onclick','');
	document.getElementById('brand').innerHTML = o;
}