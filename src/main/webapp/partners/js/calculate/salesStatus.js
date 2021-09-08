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
	data.startDt = document.getElementById("startDt").value;
	data.endDt = document.getElementById("endDt").value;
	data.orderState = "구매확정";
	
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
//			검색된 상품 리스트
			for(var i = 0; i<jlist.length; i++){
				
				
				
			}
		}else{
//			검색된 상품이 없는 경우
		}
		endLoading();
		
	}
	
}