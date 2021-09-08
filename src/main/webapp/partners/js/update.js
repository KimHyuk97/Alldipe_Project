function getSearch(){
	
	startLoading();
	
	const state = document.getElementsByClassName("state");
	const table = document.getElementById("list_table");
	const scmNo = document.getElementById("scmNo");
	//	jsonObject 생성
	var data = new Object();
	data.scmNo = scmNo.value;
	data.dateCate = document.getElementById("dateCate").value;
	data.startDate = document.getElementById("search_start_date").value;
	data.endDate = document.getElementById("search_end_date").value;
	
	if(document.getElementById("cate3").value == "소분류"){
		if(document.getElementById("cate2").value == "중분류"){
			if(document.getElementById("cate1").value == "대분류"){
				data.cateCd = "";			
			}else{
				data.cateCd = document.getElementById("cate1").value;
			}
		}else{
			data.cateCd = document.getElementById("cate2").value;
		}
	}else{
		data.cateCd = document.getElementById("cate3").value;
	}
	
	
	var list = "";
	for(var i = 0; i<state.length; i++){
		if(state[i].checked){
			list += "," + state[i].value;
		}
	}
	
	data.state = list;
	
	list = "";
	
	if(document.getElementById("saleState").checked){
		data.saleState = document.getElementById("saleState").value;	
	}else{
		data.saleState = "";
	}
	
	data.keywordType = document.getElementById("keywordType").value;
	data.keyword = document.getElementById("keyword").value;
	
	
	//	ajax 실행
	var xhr = new XMLHttpRequest();
	
	var dataStr = JSON.stringify(data);
	
	xhr.open("post", "./getGoodsList");
	xhr.setRequestHeader("Content-Type","application/json; charset=utf-8");
	
	xhr.responseType="json";
	
	xhr.send(dataStr);
	
	xhr.onload = function(){
		var jlist = xhr.response;
		
		const list = document.getElementsByClassName("goodsList_tr");
		const cnt = list.length;
		for(var i = 0; i<cnt; i++){
			list[0].remove();
		}
		document.getElementById("list_cnt").innerText = jlist.length;
		if(jlist.length>0){
		
			for(var i = 0; i<jlist.length; i++){
				
				const tr = document.createElement("div");
				tr.className="goodsList_tr";
				
				const ch = document.createElement("div");
				ch.className = "list_col1";
				
				const check = document.createElement("input");
				check.type="checkbox";
				check.className = "select_item";
				check.value=jlist[i].goodsNo;
				check.setAttribute("onchange","selectCount(" + i + ")");
				
				ch.appendChild(check);
				
				const no = document.createElement("div");
				no.textContent = jlist[i].goodsNo;
				no.className = "list_col2";
				
				const state = document.createElement("div");
				if(jlist[i].goodsSellFl){
					state.textContent = "판매중";
				}else{
					state.textContent = "판매대기";
				}
				
				state.className = "list_col3";
				const goodsNm = document.createElement("div");
				goodsNm.textContent = jlist[i].goodsNm;
				goodsNm.className = "list_col4";
				goodsNm.setAttribute("onclick","location.href='./Update?goods=" + jlist[i].goodsNo+"'");
				
				const point = document.createElement("div");
				point.className = "list_col5";
				
				const like = document.createElement("div");
				like.className = "list_col6";
				
				
				const sales = document.createElement("div");
				sales.className = "list_col7";
				sales.textContent = jlist[i].orderCnt;
				
				const totalStock = document.createElement("div");
				totalStock.className = "list_col8";
				totalStock.textContent = jlist[i].totalStock;
				
				const discountInfo = document.createElement("div");
				discountInfo.className = "list_col9";
				discountInfo.textContent = jlist[i].discountInfo;
				const regDt = document.createElement("div");
				regDt.className = "list_col10";
				regDt.textContent = jlist[i].regDt;
				const modi = document.createElement("div");
				modi.className = "list_col11";
				
				tr.appendChild(ch);
				tr.appendChild(no);
				tr.appendChild(state);
				tr.appendChild(goodsNm);
				tr.appendChild(point);
				tr.appendChild(like);
				tr.appendChild(sales);
				tr.appendChild(totalStock);
				tr.appendChild(discountInfo);
				tr.appendChild(regDt);
				table.appendChild(tr);
			}
			
		}else{
			const tr = document.createElement("div");
			tr.id="goodsList_tr";
			const no = document.createElement("div");
			no.className = "goodsList_tr";
			no.colspan = 11;
			const sp = document.createElement("span");
			sp.textContent = "검색된 상품이 없습니다.";
			
			no.appendChild(sp);
			tr.appendChild(no);
			table.appendChild(tr);
		}
		endLoading();
	}
	
}


function stateAll(){
	const state = document.getElementsByClassName("state");
	if(document.getElementById("stateAll").checked){
		for(var i = 0; i<state.length; i++){
			state[i].checked = true;
		}
	}else{
		for(var i = 0; i<state.length; i++){
			state[i].checked = false;
		}
	}
}

function selectAll(){
	const state = document.getElementsByClassName("select_item");
	if(document.getElementById("selectAll").checked){
		for(var i = 0; i<state.length; i++){
			state[i].checked = true;
			selectCount(i);
		}
	}else{
		for(var i = 0; i<state.length; i++){
			state[i].checked = false;
			selectCount(i);
		}
	}
}

function selectCount(i){
	var selectedCnt = (document.getElementById("selectCnt").innerText)*1;
	const select = document.getElementsByClassName("select_item");
	if(select[i].checked){
		selectedCnt++;
	}else{
		selectedCnt--;
	}
	
	document.getElementById("selectCnt").innerText = selectedCnt;
}

function openDiv(div){
	document.getElementById(div).style.display = "block";
	if(div == "brand_background"){
		brandMaker();
	}
}
function closeDiv(div){
	document.getElementById(div).style.display = "none";
	if(div == "displayFl_background"){
		const displayFl = document.getElementsByClassName("memberOnly");
		for(var i=0; i<displayFl.length; i++){
			displayFl[i].checked = false;
		}
		choiceDisplayFl('memberOnly','memberOnly_css')
	}
	if(div == "adultFl_background"){
		const displayFl = document.getElementsByClassName("adultFl");
		for(var i=0; i<displayFl.length; i++){
			displayFl[i].checked = false;
		}
		choiceDisplayFl('adultFl','adultFl_css');
	}
	if(div == "saleState_background"){
		const displayFl = document.getElementsByClassName("saleState");
		for(var i=0; i<displayFl.length; i++){
			displayFl[i].checked = false;
		}
		choiceDisplayFl('saleState','saleState_css')
	}
}

function choiceDisplayFl(class1, class2){
	const select = document.getElementsByClassName(class1);
	const display = document.getElementsByClassName(class2);
	for(var i = 0; i<select.length; i++){
		if(select[i].checked){
			display[i].style.backgroundColor = "#1E46A8";
		}else{
			display[i].style.backgroundColor = "#396ef3";
		}
	}
}

function brandMaker(){
	const option = document.getElementById("brandMaker");
	if(option.value == "brand"){
		document.getElementById("brand_search_div").style.display = "block";
		document.getElementById("maker_input_div").style.display = "none";
	}else if(option.value=="makerNm"){
		document.getElementById("brand_search_div").style.display = "none";
		document.getElementById("maker_input_div").style.display = "block";
	}
}

function optionChange(option){
	
	var data = new Object();
	const select = document.getElementsByClassName("select_item");
	
	var str = "";
	for(var i = 0; i<select.length; i++){
		if(select[i].checked == true){
			str+=select[i].value + ",";
		}
	}
	
	data.goodsNo = str;
	data.option = option;
	if(document.getElementsByClassName(option)[0].checked==true){
		data.value = document.getElementsByClassName(option)[0].value;
	}else{
		data.value = document.getElementsByClassName(option)[1].value;
	}
	
	var dataStr = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	
	xhr.open("post","./setSelectOption");
	xhr.setRequestHeader("Content-Type","application/json; charset=utf-8");
	
	xhr.send(dataStr);
	
	
	xhr.onload = function(){
		
		startLoading();
		
		if(xhr.status == 200){
			
			const flag = xhr.responseText;
			
			if(flag == "true"){
				alert("수정완료");
				location.reload();
			}else{
				alert("수정 실패");
			}
		}else{
			alert("에러");
		}
	}
}