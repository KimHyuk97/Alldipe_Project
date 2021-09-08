function callbackApply(){
	
	const order =  document.querySelector('form');
	
	var selected = document.getElementsByClassName("select_item");
	for(var i = 0; i<selected.length; i++){
		if(selected[i].checked && orderStatus[i].innerText != "반품신청"){
			alert('반품 신청 상태의 주문만 수정 가능합니다.');
			return false;
		}
	}
	if(confirm("해당 주문을 수거요청 처리 하시겠습니까?")){
		document.getElementById("changeStatus").value="수거요청";
		order.submit();
	}
}

function refundApprove(){
	
	const order =  document.querySelector('form');
	
	var selected = document.getElementsByClassName("select_item");
	
	if(confirm("해당 주문을 환불승인 처리 하시겠습니까?")){
		document.getElementById("changeStatus").value="환불승인";
		order.submit();
	}
}

function refundWait(){
	
	const order =  document.querySelector('form');
	
	var selected = document.getElementsByClassName("select_item");
	
	if(confirm("해당 주문을 환불보류 처리 하시겠습니까?")){
		document.getElementById("changeStatus").value="환불보류";
		order.submit();
	}
}

function refundReject(){
	
	const order =  document.querySelector('form');
	
	var selected = document.getElementsByClassName("select_item");
	
	if(confirm("해당 주문을 환불거절 처리 하시겠습니까?")){
		document.getElementById("changeStatus").value="환불거절";
		order.submit();
	}
}

function callbackChange(){
	
}

function selectItem(){
	
	const selectAll = document.getElementById("selectAll");
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

function search(){
	
	startLoading();
	
	const table = document.getElementById("list_table");
	
	const list = document.getElementsByClassName("orderList_tr");
	const cnt = list.length;
	for(var i = 0; i<cnt; i++){
		list[0].remove();
	}
	
	var data = new Object();
	
	data.scmNo = document.getElementById("scmNo").value;
	data.dateType=document.getElementById("dateType").value;
	data.startDt = document.getElementById("startDt").value;
	data.endDt = document.getElementById("endDt").value;
	
	if(document.getElementById("orderState").value == "all"){
		data.orderState = "환불신청,수거진행중,환불보류,환불불가,환불완료";
	}else{
		data.orderState = document.getElementById("orderState").value;
	}
	
	data.keywordType = document.getElementById("keywordType").value;
	data.keyword = document.getElementById("keyword").value;
	
	var dataStr = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	
	xhr.open("post","./getOrderList");
	xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8");
	
	xhr.responseType="json";
	
	xhr.send(dataStr);
	
	xhr.onload = function(){
		
		var jlist = xhr.response;
		document.getElementsByClassName("color_span")[0].innerText = jlist.length;
		
		if(jlist.length>0){
			
			for(var i = 0; i<jlist.length; i++){
				
				const tr = document.createElement("div");
				tr.className="orderList_tr";
				
				const ch = document.createElement("div");
				ch.className = "list_col1";
				
				const check = document.createElement("input");
				check.type="checkbox";
				check.className = "select_item";
				check.name = "select_item";
				check.value=jlist[i].sno;
				
				ch.appendChild(check);
				
				const num = document.createElement("div");
				num.className = "list_col2";
				num.textContent = jlist[i].sno;
				
				const regDt = document.createElement("div");
				regDt.className = "list_col3";
				regDt.textContent = jlist[i].regDt;
				
				const afterDate = document.createElement("div");
				afterDate.className = "list_col4";
				
				const now = new Date();
				const regDate = new Date(jlist[i].regDt);
				
				afterDate.textContent = (now.getDate()-regDate.getDate());
				
				const orderNo = document.createElement("div");
				orderNo.className = "list_col5";
				orderNo.textContent = jlist[i].orderNo;
				
				const state = document.createElement("div");
				state.className = "list_col6 orderStatus";
				state.textContent = jlist[i].orderStatus;
				
				const buyer = document.createElement("div");
				buyer.className = "list_col7";
				buyer.textContent = jlist[i].memNm;
				
				const optionNo = document.createElement("div");
				optionNo.className = "list_col8";
				optionNo.textContent = jlist[i].goodsNm + " "+ jlist[i].goodsOptionNm;
				
				const fixedPrice= document.createElement("div");
				fixedPrice.className = "list_col9";
				fixedPrice.textContent = jlist[i].fixedPrice;
				
				const deliveryCost = document.createElement("div");
				deliveryCost.className = "list_col10";
				deliveryCost.textContent = jlist[i].deliveryPrice;
				
				const admin = document.createElement("div");
				admin.className = "list_col11";
				
				
				tr.appendChild(ch);
				tr.appendChild(num);
				tr.appendChild(regDt);
				tr.appendChild(afterDate);
				tr.appendChild(orderNo);
				tr.appendChild(state);
				tr.appendChild(buyer);
				tr.appendChild(optionNo);
				tr.appendChild(fixedPrice);
				tr.appendChild(deliveryCost);
				tr.appendChild(admin);
				
				table.appendChild(tr);
				
			}
		}else{
			const tr = document.createElement("div");
			tr.id="orderList_tr";
			const no = document.createElement("div");
			no.className = "orderList_tr";
			no.colspan = 9;
			const sp = document.createElement("span");
			sp.textContent = "검색된 상품이 없습니다.";
			
			no.appendChild(sp);
			tr.appendChild(no);
			table.appendChild(tr);
		}
		endLoading();
		
	}
	
}