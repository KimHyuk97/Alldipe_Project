function cate1Action(){
	var xhr = new XMLHttpRequest();
	
	var cate1 = document.getElementById("cate1");
	var cate2 = document.getElementById("cate2");
	var cate3 = document.getElementById("cate3");
	var category = document.getElementById("cateCd");
	
	xhr.open("post",window.location.pathname.substring(0,window.location.pathname.indexOf('/',1)+1)+"partners/goods/getCategory");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	
	xhr.responseType="json";
	var data = "code=0&length=3";
//	var after;
	
	xhr.send(data);
	
//	if(xhr.status === 200){
//		after = stringToArrayBuffer(xhr.response);
//	}else{
//		alert('Something bad happen!\n(' + xhr.status + ")" + xhr.statusText);
//	}
	
	xhr.onload = function(){
		
		var jlist = xhr.response;
		
		if(cate1.hasChildNodes()){
			while(cate1.hasChildNodes()){
				cate1.removeChild(cate1.firstChild);
			}
		}
		const child = document.createElement('option');
		child.className = "cate1_list";
		child.value = "";
		child.innerText = "대분류";
		cate1.append(child);
		
		for(var i = 0; i<jlist.length; i++){
			const child = document.createElement('option');
			child.className = "cate1_list";
			child.value = jlist[i].cateCd;
			child.innerText = jlist[i].cateNm;
			if(category.value.substring(0,3) == jlist[i].cateCd){
				child.selected = "selected";
			}
			cate1.append(child);
		}
		
	}
	
}

function cate2Action(){
	
	var xhr = new XMLHttpRequest();
	
	var cate1 = document.getElementById("cate1");
	var cate2 = document.getElementById("cate2");
	var cate3 = document.getElementById("cate3");
	var category = document.getElementById("cateCd");
	
	xhr.open("post",window.location.pathname.substring(0,window.location.pathname.indexOf("/",1)+1)+"partners/goods/getCategory");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	
	xhr.responseType="json";
	var data = "code=" + document.getElementById('cate1').value + "&length=6";
	
	xhr.send(data);
	
	xhr.onload = function(){
		
		var jlist = xhr.response;
		
		if(cate2.hasChildNodes()){
			while(cate2.hasChildNodes()){
				cate2.removeChild(cate2.firstChild);
			}
		}
		if(cate3.hasChildNodes()){
			while(cate3.hasChildNodes()){
				cate3.removeChild(cate3.firstChild);
			}
			const child = document.createElement('option');
			child.value = "";
			child.innerText = "소분류";
			cate3.append(child);
		}
		
		const child = document.createElement('option');
		child.className = "cate2_list";
		child.value = "";
		child.innerText = "중분류";
		cate2.append(child);
		
		for(var i = 0; i<jlist.length; i++){
			const child = document.createElement('option');
			child.className = "cate2_list";
			child.value = jlist[i].cateCd;
			child.innerText = jlist[i].cateNm;
			cate2.append(child);
		}
		
	}
	
}
		
function cate3Action(){
	var xhr = new XMLHttpRequest();
	
	var cate2 = document.getElementById("cate2");
	var cate3 = document.getElementById("cate3");
	var category = document.getElementById("cateCd");
	
	xhr.open("post",window.location.pathname.substring(0,window.location.pathname.indexOf("/",1)+1)+"partners/goods/getCategory");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	
	xhr.responseType="json";
	var data = "code=" + document.getElementById('cate2').value + "&length=9";
	
	xhr.send(data);
	
	xhr.onload = function(){
		
		var jlist = xhr.response;
		
		if(cate3.hasChildNodes()){
			while(cate3.hasChildNodes()){
				cate3.removeChild(cate3.firstChild);
			}
			const child = document.createElement('option');
			child.className = "cate3_list";
			child.value = "";
			child.innerText = "소분류";
			cate3.append(child);
		}
				
		for(var i = 0; i<jlist.length; i++){
			const child = document.createElement('option');
			child.className = "cate3_list";
			child.value = jlist[i].cateCd;
			child.innerText = jlist[i].cateNm;
			cate3.append(child);
		}
		
	}
}

function stringToArrayBuffer(str){
	var buf = new ArrayBuffer(str.length);
	var bufView = new Uint8Array(buf);
	
	for(var i = 0; i<str.length; i++){
		bufView[i] = str.charCodeAt(i);
	}
	
	return buf;
}

function setCategory(){
	
	var xhr = new XMLHttpRequest();
	
	var cate2 = document.getElementById("cate2");
	var cate3 = document.getElementById("cate3");
	var category = document.getElementById("cateCd");
	
	xhr.open("post",window.location.pathname.substring(0,window.location.pathname.indexOf("/",1)+1)+"partners/goods/getCategoryUpdate");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	
	xhr.responseType="json";
	var data = "code=" + cateCd.value;
	
	xhr.send(data);
	
	xhr.onload = function(){
		
		var jlist = xhr.response;
		
		if(cate2.hasChildNodes()){
			while(cate2.hasChildNodes()){
				cate2.removeChild(cate2.firstChild);
			}
			const child = document.createElement('option');
			child.value = "";
			child.innerText = "중분류";
			cate2.append(child);
		}
		if(cate3.hasChildNodes()){
			while(cate3.hasChildNodes()){
				cate3.removeChild(cate3.firstChild);
			}
			const child = document.createElement('option');
			child.value = "";
			child.innerText = "소분류";
			cate3.append(child);
		}
		
		for(var i = 0; i<jlist.length; i++){
			const child = document.createElement('option');
			
			if(jlist[i].cateCd.length<=6){
				child.className = "cate2_list";
				child.value = jlist[i].cateCd;
				child.innerText = jlist[i].cateNm;
				if(cateCd.value.substring(0,6) == jlist[i].cateCd){
					child.selected = "selected";
				}
				cate2.append(child);
			}else{
				child.className = "cate3_list";
				child.value = jlist[i].cateCd;
				child.innerText = jlist[i].cateNm;
				if(cateCd.value.substring(0,9) == jlist[i].cateCd){
					child.selected = "selected";
				}
				cate3.append(child);
			}
		}
		
	}
	
}

function onCategory(){
	var xhr = new XMLHttpRequest();
	
	xhr.open("post","./getCategory1");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	xhr.responseType="json";
	var data = "code=" + document.getElementById("cate3").value;
	
	xhr.send(data);
	
	xhr.onload = function(){
		var jlist = xhr.response;
		
		document.getElementById("commission").value = jlist.commission;
		document.getElementById("cateCd").value = jlist.cateCd;
		
	}
}

