function cateAction(){
	$.ajax({
		type:"post",
		url:"./cate2",
		dataType:"json",
		success:function(i){
			l(i);
		},
		error:function(request, status, error){
			console.log(error);
		}
	});   
}

//대분류
function l(i){
	var a = "";
	
	document.getElementById("large").setAttribute('onclick','');
	a += "<select name='cate' id='large'  onchange='cateCheck()'>";
	a +="<option value=''>대분류</option>";
	for(var j=0; j < i.length; j++){
		a += "<option value='"+i[j].cateCd+"'>"+i[j].cateNm+"</option>";
	}
	a += "</select>";
	
	document.getElementById("large").innerHTML = a;
	
	var b = "";
	b +="<option value=''>중분류</option>";
		
	document.getElementById("middle").innerHTML = b;
	
	var c = "";
	c +="<option value=''>소분류</option>";
		
	document.getElementById("small").innerHTML = c;
}

function cateCheck(){
	var cateCd = event.target.value;
	if(event.target.value = cateCd){
		cateMiddle(cateCd);
	}
}

function cateMiddle(cateCd){
	$.ajax({
		type:"post",
		url:"./cate2",
		data:{cateCd : cateCd},
		dataType:"json",
		success:function(i){
			m(i);
		},
		error:function(request, status, error){
			console.log(error);
		}
	});                  		
}

//중분류
function m(i){
	document.getElementById("middle").setAttribute('onclick','');
	var o = "";
	o += "<select name='cate2' id='large2'  onchange='cateCheck2()'>";
	o +="<option value=''>중분류</option>";
	for(var j=0; j < i.length; j++){
		o += "<option value='"+i[j].cateCd+"'>"+i[j].cateNm+"</option>";
	}
	o += "</select>";
	
	document.getElementById("middle").innerHTML = o;
	
	var a = "";
	a +="<option value=''>소분류</option>";
		
	document.getElementById("small").innerHTML = a;
}


//소분류
function cateCheck2(){
	var cateCd = event.target.value;
	if(event.target.value = cateCd){
		cateSmall(cateCd);
	}
}

function cateSmall(cateCd){
	$.ajax({
		type:"post",
		url:"./cate2",
		data:{cateCd : cateCd},
		dataType:"json",
		success:function(i){
			s(i);
		},
		error:function(request, status, error){
			console.log(error);
		}
	});     
}

//소분류
function s(i){
	document.getElementById("small").setAttribute('onclick','');
	var o = "";
	o +="<option value=''>소분류</option>";
	for(var j=0; j < i.length; j++){
		o += "<option value='"+i[j].cateCd+"'>"+i[j].cateNm+"</option>";
	}
	
	document.getElementById("small").innerHTML = o;
}