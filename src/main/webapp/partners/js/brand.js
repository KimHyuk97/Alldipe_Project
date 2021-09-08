function brandSearch(){
	
	var xhr = new XMLHttpRequest();
	document.getElementById("brand_search").style.display == "block";
	xhr.open("post", window.location.pathname.substring(0,window.location.pathname.indexOf('/',1)+1)+"partners/goods/getBrand");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	
	xhr.responseType="json";
	
	var data = "brand=" + document.getElementById("brand").value;
	
	xhr.send(data);
	
	xhr.onload = function(){
		
		const list = document.getElementById("brand_search");
		
		while(list.hasChildNodes()){
			list.removeChild(list.firstChild);
		}
		
		var jlist = xhr.response;
		
		
		if(jlist!=null && jlist.length>0){
			list.style.display = "flex";
			var o = "";
			o += "<p class='brandTitle'>입력한 브랜드를 선택해주세요 !</p>";
			o += "<ul class='brandContent'>";
			for(var i = 0; i<jlist.length; i++){
				o += "<li><span class='brand_name' onclick='selectBrand(\"" + jlist[i].brandNm + "\",\"" + jlist[i].brandCd +"\")'>" + jlist[i].brandNm + "</span></li>";
			}
			o += "</ul>";
			list.innerHTML = o;
		}else{
			list.style.display = "flex";
			list.innerHTML = "<span class='brand_name'>검색된 브랜드가 없습니다.</span><span class='brand_name' onclick='newBrand()'>새로만들기</span>";
			
		}
		
		
	}
}

function selectBrand(brandNm, brandCd){
	document.getElementById("brand").value = brandNm;
	document.getElementById("brandCd").value = brandCd;
	document.getElementById("brand_search").style.display = "none";
}

function newBrand(){
	var brandNm = document.getElementById("brand").value;
	var xhr = new XMLHttpRequest();
	xhr.open("post", window.location.pathname.substring(0,window.location.pathname.indexOf('/',1)+1)+"partners/goods/newBrand");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	
	var data = "brandNm=" + brandNm;
	
	alert(data);
	
	xhr.send(data);
	
	xhr.onload = function(){
		
		var result = xhr.responseText;
		var str = result.split(",");
		
		if(str[0][0]=="1"){
			alert("성공");
			document.getElementById("brand").value = str[1];
			document.getElementById("brandCd").value = str[2];
			document.getElementById("brand_search").style.display = "none";
		}else{
			alert("실패");
		}
	}
}