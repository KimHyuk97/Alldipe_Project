function save(){
	var form = document.getElementById("form");
	
	if(check()){
		
		var chk = confirm('상품을 수정하시겠습니까?');
		if(chk){
			form.submit();
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

function optionText(){
	const idx = document.getElementById("optionCnt").value;
	const optionlist = document.getElementById("optionlist");
	delChild(optionlist);
	
	for(var i = 0; i < idx; i++){
		const child = document.createElement('div');
		child.className = 'optionli';
		
		const input1 = document.createElement('input');
		input1.type = 'textbox';
		input1.className = 'optionNm';
		input1.placeholder = '예시 : 색상';
		const input2 = document.createElement('input');
		input2.type = 'textbox';
		input2.className = 'optionVal';
		input2.placeholder = '예시 : 블랙,화이트(,로 구분)';
		
		child.appendChild(input1);
		child.appendChild(input2);
		
		optionlist.appendChild(child);
	}
}

function optionPlus(){
	const optionlist = document.getElementById("optionlist");

	
	let optionli2 = document.querySelectorAll('.optionli').length;
	if(optionli2 < 5){	
		const child = document.createElement('div');
		child.className = 'optionli';
		
		const input1 = document.createElement('input');
		input1.type = 'textbox';
		input1.className = 'optionNm';
		input1.placeholder = '예시 : 색상';
		const input2 = document.createElement('input');
		input2.type = 'textbox';
		input2.className = 'optionVal';
		input2.placeholder = '예시 : 블랙,화이트(,로 구분)';
		
		child.appendChild(input1);
		child.appendChild(input2);
		
		optionlist.appendChild(child);
		document.getElementById("optionCnt")[optionli2].selected = true;
		
	}
}

function optionM(){
	const optionlist2 = document.getElementById("optionlist");
	const length = document.querySelectorAll('.optionli').length;	
	const a = length -2;
	console.log(a);
	if(length > 1){
		optionlist2.removeChild(optionlist2.childNodes[length]);
		document.getElementById("optionCnt")[a].selected = true;
	}

}

function optionChange(){
	document.getElementById('selectAll').checked = true;
	selectItem();
	optionDel();
	
	const table = document.getElementById('optionTable');
	const idx = document.getElementById('optionCnt').value;
	const head = document.getElementById('optionHead');
	const col = document.getElementById('optionCol');
	const option = document.getElementsByClassName('optionNm');
	
	const optionVal = new Array(document.getElementsByClassName('optionVal').length);
	
	head.colSpan = idx;
	
	delChild(col);
	
	for(var i = 0; i<idx; i++){
		
		optionVal[idx-i-1] = document.getElementsByClassName('optionVal')[i].value.split(",");
		
		var child = document.createElement('td');
		child.innerText = option[i].value;
		
		col.appendChild(child);
	}
	
	const val = getOptionList(optionVal);
	
	for(var i=0; i<val.length; i++){
		var tr = document.createElement('tr');
		tr.id="tr" + i;
		tr.className = "option"
		
		var select = document.createElement('td');
		const box = document.createElement("input");
		
		box.type="checkbox";
		box.className = "select_item";
		box.name="select_item";
		box.value="tr" + i;
		select.appendChild(box);
		
		tr.appendChild(select);
		
		const str = val[i].split(",");
		
		for(var j = 0; j<str.length; j++){
			var td = document.createElement('td');
			td.innerText = str[j];
			tr.appendChild(td);
			
			const name = document.createElement('input');
			name.setAttribute("type","hidden");
			name.value = option[j].value;
			name.setAttribute("name", "optionNm"+j+ String((i+1)).padStart(2,'0'));
			tr.appendChild(name);
			
			const value = document.createElement('input');
			value.setAttribute("type","hidden");
			value.value = str[j];
			value.setAttribute("name", "optionVal"+j+String((i+1)).padStart(2,'0'));
			tr.appendChild(value);
		}
		
		var fixed = document.createElement('td');
		
		const box1 = document.createElement('input');
		box1.setAttribute("type","text");
		box1.className="optionInput";
		box1.setAttribute("name", "fixedCost"+String((i+1)).padStart(2,'0'));
		fixed.appendChild(box1);
		tr.appendChild(fixed);
		
		const box5 = document.createElement('input');
		box5.type="hidden";
		box5.name = "optionNo" + String((i+1)).padStart(2,'0');
		box5.value = i;
		tr.appendChild(box5);
		
		
		var sales = document.createElement('td');
		const box2 = document.createElement('input');
		box2.type="textbox";
		box2.name = "salesCost" + String((i+1)).padStart(2,'0');
		box2.className="optionInput";
		sales.appendChild(box2);
		tr.appendChild(sales);
		
		
		var stock = document.createElement('td');
		const box3 = document.createElement('input');
		box3.type="textbox";
		box3.name = "stockCnt" + String((i+1)).padStart(2,'0');
		box3.className="optionInput";
		stock.appendChild(box3);
		tr.appendChild(stock);
		
		var sellerCd = document.createElement('td');
		const box4 = document.createElement('input');
		box4.type="textbox";
		box4.name = "sellerCd" + String((i+1)).padStart(2,'0');
		box4.className="optionInput";
		sellerCd.appendChild(box4);
		tr.appendChild(sellerCd);
		
		
		
		var text = document.createElement('td');
		const box6 = document.createElement('input');
		box6.type="textbox";
		box6.name = "comment" + String((i+1)).padStart(2,'0');
		box6.className="optionInput";
		tr.appendChild(text);
		
		table.appendChild(tr);
		
	}
	
}

function delChild(parent){
	while(parent.hasChildNodes()){
		parent.removeChild(parent.firstChild);
	}
}

function getOptionList(option){
	var val;
	
	for(var i = option.length; i>1; i--){
	
		val = repeat(option[i-1], option[i-2]);
		option[i-1] = null;
		option[i-2] = val;
		
	}
	
	return option[0];
}

function repeat(arr1, arr2){
	
	const value = new Array(arr1.length*arr2.length);
	
	var cnt = 0;
	
	for(var i = 0; i<arr1.length; i++){
		 for(var j = 0; j<arr2.length; j++){
			 value[cnt] = arr1[i] + "," + arr2[j];
			 cnt++;
		 }
	}
	
	return value;
}

function selectItem(){
	const selectItem = document.getElementsByClassName("select_item");
	if(document.getElementById("selectAll").checked){
		for(var i = 0; i<selectItem.length; i++){
			selectItem[i].checked = true;
		}
	}else{
		for(var i = 0; i<selectItem.length; i++){
			selectItem[i].checked = false;
		}
	}
}

function optionDel(){
	const select = document.getElementsByClassName('select_item');
	
	for(var i = select.length-1; i>=0; i--){
		if(select[i].checked){
			document.getElementById(select[i].value).parentNode.removeChild(document.getElementById(select[i].value));
		}
	}
	
	if(document.getElementById('selectAll').checked){
		document.getElementById('selectAll').checked = false;
	}
}

function calPercent(){
	const goodsPrice = document.getElementById("goodsPrice").value;
	const fixedPrice = document.getElementById("fixedPrice").value;
	
	if(goodsPrice!= "" && fixedPrice!= ""){
		document.getElementById("discountPercent").value = ((goodsPrice-fixedPrice)/goodsPrice*100);
	}else{
		document.getElementById("discountPercent").value = "";
		return;
	}
	
}

function check(){
	const form = document.getElementById("form");
	
	if(form.goodsNm.value == ""){
		alert("상품명을 입력해 주세요.");
		return false;
	}
	if(form.cateCd.value == ""){
		alert("카테고리를 선택해 주세요.");
		return false;
	}
	if(form.salesUnit.value == ""){
		alert("판매 단위 개수를 설정해 주세요.");
		return false;
	}
	if(form.goodsPrice.value == ""){
		alert("상품 가격을 입력해 주세요.");
		return false;
	}
	if(form.goodsPrice.value == ""){
		alert("상품 가격을 입력해 주세요.");
		return false;
	}
	if(form.fixedPrice.value == ""){
		alert("판매할 가격을 입력해 주세요.");
		return false;
	}	
	return true;
	
}
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

function optionMod(goodsNo){
	window.open("./goodsOptionMod?goodsNo="+goodsNo+"", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=450,width=1700,height=800");
}


