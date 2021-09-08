//전체카테고리
var cate_view = document.getElementById('cate_view'),
	categoryList = document.querySelector('.categoryList'),
	sub_cate = document.querySelector('.sub_cate'),
	medium_cate = document.querySelector('.medium_cate'),
	small_cate = document.querySelector('.small_cate');
				
cate_view.addEventListener('click', function(){
	$.ajax({
		type:"post",
		url:"category",
		dataType:"json",
		success:function(i){
			categoryList.classList.toggle('active');
			largeCate(i);
		},
		error:function(){
			console.log('x');
		}
	});
});


//전체카테고리 on
function largeCate(i){
	let s = "";
	s += "<ul>";
	for(let j = 0; j < i.length; j++){	
		s += "<li id='cateOn"+j+"' onmouseover='largeCateSub("+j+","+i[j].cateCd+")' onmouseout='largeOut("+j+")'>"+i[j].cateNm+"</li>";
	}
	s += "</ul>";
	
	sub_cate.innerHTML = s;
	sub_cate.style.display = "block";
}

//대분류카테고리에 마우스 올리면	중분류on
function largeCateSub(j,i){
	let cateCd = "00"+i;

	$.ajax({
		type:"post",
		url:"mediumCategory",
		data:{
			cateCd:cateCd,
		},
		dataType:"json",
		success:function(k){
			mediumCate(k,j);
		},
		error:function(){
		
		}
	});
}

function largeOut(j){
	if(small_cate.style.display == "block"){
		small_cate.style.display = "none";
	}
}

//중분류 on
function mediumCate(k,j){
	let s = "";
	s += "<ul>";
	for(let z = 0; z < k.length; z++){	
		s += "<li onmouseover='mediumCateSub("+z+","+k[z].cateCd+")'>"+k[z].cateNm+"</li>";
	}
	s += "</ul>";
	medium_cate.innerHTML = s;
	medium_cate.style.display = "block";

	
	if(small_cate.style.display == "block"){
		small_cate.style.display = "none";
	}
		
}


//중분류에 마우스를 올리면 소분류 on
function mediumCateSub(j,k){
	let cateCd;
	
	if(String(k).charAt(0) == "8"){
		cateCd = "00"+k;
	}else{
		cateCd = "00"+k.toString(8);
	}
		$.ajax({
			type:"post",
			url:"smallCategory",
			data:{
				cateCd:cateCd,
			},
			dataType:"json",
			success:function(i){
				smallCate(i);
			},
			error:function(){
			
			}
		});
}

//소분류 on
function smallCate(k){
	let s = "";
	s += "<ul>";
	for(let j = 0; j < k.length; j++){	
		s += "<li><a href='categoryGoods?cateCd="+k[j].cateCd+"'>"+k[j].cateNm+"</a></li>";
	}
	s += "</ul>";
	small_cate.innerHTML = s;
	small_cate.style.display = "block";
}



//전체카테고리 end

//쿠키확인
function getCookie(name) { 

     var nameOfCookie = name + "="; 
     var x = 0;

     while (x <= document.cookie.length) {  
          var y = (x + nameOfCookie.length); 
          if (document.cookie.substring(x, y) == nameOfCookie) { 

               if ((endOfCookie = document.cookie.indexOf(";", y)) == -1){	            	   
                    endOfCookie = document.cookie.length;
               } 
               return unescape(document.cookie.substring(y, endOfCookie)); 
          }
          x = document.cookie.indexOf(" ", x) + 1;
          
          if (x == 0){	        	  
               break; 
          }
     }
     return "";

}



//장바구니 쿠키
if(document.getElementById('cart') != null){
	document.getElementById('cart').addEventListener('click',() => {
	
		let siteKey = "";	
				
		var cookie = getCookie('siteKey');
		if (cookie != "") {
		     siteKey = cookie;
		}else{
			//인증코드 만들기
			var chars = '0123456789';
			var stringLength = 10;
			var randomstring = '';
				
			for (var i = 0; i < stringLength; i++) {
				var rnum = Math.floor(Math.random() * chars.length)
				randomstring += chars.substring(rnum, rnum + 1)
			}
			
			siteKey = randomstring;
			
			//쿠키저장
			var date = new Date();
			date.setFullYear(date.getFullYear()+1);
			var coo = '';
			coo += 'siteKey ='+ siteKey +'; expires = ' + date.toUTCString();
			document.cookie = coo;
		}
		
		location.href="cart?memNo=0&siteKey="+siteKey;
	});
}
//장바구니 쿠키 end

//카테고리 색 변경
const newGoods = document.getElementById('new'),
		  best = document.getElementById('best'),	
		  freeD = document.getElementById('freeD'),
		  festival = document.getElementById('festival'),
		  event = document.getElementById('event'),
		  brand = document.getElementById('brand'),
		  special = document.getElementById('special');
	
	var link2 = document.location.href;
	const link = link2.slice(45);

	if(link == "cateCd=1"){
		special.setAttribute('class','cateOn');
	}else{
		special.setAttribute('class','');
	}
	if(link == "cateCd=2"){
		newGoods.setAttribute('class','cateOn');
	}else{
		newGoods.setAttribute('class','');
	}
	if(link == "cateCd=3"){
		best.setAttribute('class','cateOn');
	}else{
		best.setAttribute('class','');
	}
	if(link == "cateCd=4"){
		freeD.setAttribute('class','cateOn');
	}else{
		freeD.setAttribute('class','');
	}
	if(link == "cateCd=5"){
		festival.setAttribute('class','cateOn');
	}else{
		festival.setAttribute('class','');
	}
	if(link == "cateCd=6"){
		event.setAttribute('class','cateOn');
	}else{
		event.setAttribute('class','');
	}
	if(link == "cateCd=7"){
		brand.setAttribute('class','cateOn');
	}else{
		brand.setAttribute('class','');
	}
	
//검색
const searchBtn = document.getElementById('searchBtn'),
	  keywordBox = document.getElementById('keyword'),
	  searchTerms = document.getElementById('searchTerms'),
	  latestkeyword = document.getElementById('latestkeyword'),
	  bestKeyword = document.getElementById('bestKeyword');

//검색창 클릭하면
keywordBox.addEventListener('click',() => {

	let siteKey = "";	
	let memNo = $('#memNumber').data('no');
	
	if(memNo == "" || memNo == null || memNo < 0){
		memNo = 0;
	}

	var cookie = getCookie('siteKey');
		
	if (cookie != "") {
	     siteKey = cookie;
	}else{
		//인증코드 만들기
		var chars = '0123456789';
		var stringLength = 10;
		var randomstring = '';
			
		for (var i = 0; i < stringLength; i++) {
			var rnum = Math.floor(Math.random() * chars.length)
			randomstring += chars.substring(rnum, rnum + 1)
		}
		
		siteKey = randomstring;
		
		//쿠키저장
		var date = new Date();
		date.setFullYear(date.getFullYear()+1);
		var coo = '';
		coo += 'siteKey ='+ siteKey +'; expires = ' + date.toUTCString();
		document.cookie = coo;
	}
	
	document.getElementById('siteKeyVal').value = siteKey;
	document.getElementById('memNoVal').value = memNo;
	
	//최근검색어 불러오기
	$.ajax({
		type:"post",
		url:"latestkeyword",
		data:{
			siteKey : siteKey,
			memNo : memNo
		},
		dataType:"json",
		success:function(i){
			latestKeywordView(i);
		},
		error:function(request,status,error){
	        alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
	    },		
	});
		
	document.getElementById('keyword').style.borderBottom = "none";
	searchTerms.style.display = "flex";
});

//최근검색어
function latestKeywordView(i){
	let siteKey = "";	
	let memNo = $('#memNumber').data('no');;
	
	if(memNo == "" || memNo == null || memNo < 0){
		memNo = 0;
	}

	var cookie = getCookie('siteKey');
		
	if (cookie != "") {
	     siteKey = cookie;
	}else{
		//인증코드 만들기
		var chars = '0123456789';
		var stringLength = 10;
		var randomstring = '';
			
		for (var i = 0; i < stringLength; i++) {
			var rnum = Math.floor(Math.random() * chars.length)
			randomstring += chars.substring(rnum, rnum + 1)
		}
		
		siteKey = randomstring;
		
		//쿠키저장
		var date = new Date();
		date.setFullYear(date.getFullYear()+1);
		var coo = '';
		coo += 'siteKey ='+ siteKey +'; expires = ' + date.toUTCString();
		document.cookie = coo;
	}
	
	let v = "";
	v += "<h1>최근검색어</h1>";
	v += "<ul class='latest'>";
	for(let q = 0; q < i.length; q++){	
		v += "<li><a href='search?siteKey="+siteKey+"&memNo="+memNo+"&keyword="+i[q].keyword+"'>"+i[q].keyword+"</a></li>";
	}	
	v += "<ul>";
	
	latestkeyword.innerHTML = v;	
	bestkeywordFun();
}

//인기 검색어 불러오기
function bestkeywordFun(){
	$.ajax({
		type:"post",
		url:"bestkeyword",
		dataType:"json",
		success:function(w){
			bestKeywordView(w);
		},
		error:function(request,status,error){
			alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
		}
	});
}


//인기검색어
function bestKeywordView(i){
	let siteKey = "";	
	let memNo = $('#memNumber').data('no');;
	
	if(memNo == "" || memNo == null || memNo < 0){
		memNo = 0;
	}

	var cookie = getCookie('siteKey');
		
	if (cookie != "") {
	     siteKey = cookie;
	}else{
		//인증코드 만들기
		var chars = '0123456789';
		var stringLength = 10;
		var randomstring = '';
			
		for (var i = 0; i < stringLength; i++) {
			var rnum = Math.floor(Math.random() * chars.length)
			randomstring += chars.substring(rnum, rnum + 1)
		}
		
		siteKey = randomstring;
		
		//쿠키저장
		var date = new Date();
		date.setFullYear(date.getFullYear()+1);
		var coo = '';
		coo += 'siteKey ='+ siteKey +'; expires = ' + date.toUTCString();
		document.cookie = coo;
	}
	
	let w = "";
	w += "<h1>인기검색어</h1>";
	w += "<ul class='latest'>";
	for(let q = 0; q < i.length; q++){	
		w += "<li><a href='search?siteKey="+siteKey+"&memNo="+memNo+"&keyword="+i[q].keyword+"'>"+(q+1) +".&nbsp;&nbsp;"+ i[q].keyword+"</a></li>";
	}	
	w += "<ul>";
	
	bestKeyword.innerHTML = w;	
}

$("body").click(function(e) { 
    if($("#searchTerms").css("display") == "flex") {
           if(!$('#memNumber').has(e.target).length) { 
           		searchTerms.style.display = "none";
				document.getElementById('keyword').style.borderBottom = "2px solid #396ef3";
           }
    }else if($(".categoryList.active").css("display") == "flex"){
    		if(!$('.categoryList').has(e.target).length) { 
           		categoryList.classList.remove('.active');
           }
    }
});

searchBtn.addEventListener('click',() => {
	if(keywordBox.value == "" || keywordBox.value == null){
		alert('검색어를 입력해주세요.');
	}else{	
		searchForm.submit();
	}
});
//검색 끝
function endLoading() {
	document.getElementById("load").style.display = "none";
}

function startLoading() {
	document.getElementById("load").style.display = "block";
}
	
	
	