const panel = document.getElementById("item_list");
var pageNo = 1;
var b_height = 10;

function setList(cate){
	
	const xhr = new XMLHttpRequest();
	
	var data = new Object();
	
	data.cate = cate;
	data.pageNo = 1;
	data.memNo = document.getElementById("memNo").value;
	
	var dataStr = JSON.stringify(data);
	
	xhr.open("post","../goods/getList");
	xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8");
	
	xhr.responseType = "json";
	
	xhr.send(dataStr);
	
	xhr.onload = function(){
		
		startLoading();
		
		var jlist = xhr.response;
		
		const itemWrap = document.getElementsByClassName("category_item_wrap");
		const cnt = itemWrap.length;
		for(var i = 0; i<cnt; i++){
			itemWrap[0].remove();
		}
		if(jlist.length>0){
			for(var i = 0; i<jlist.length; i++){
				
				if(jlist[i].adultFl && document.getElementById("adultFl").value!="true"){
					//	성인전용 상품 : 성인인증 x	=> 노출 X
					
				}else{
				
					const item = document.createElement('li');
					item.className = "category_item_wrap";
					
					const imgbox = document.createElement("div");
					imgbox.className = "item_img_box";
					const link = document.createElement("a");
					link.href="./goodsView?goodsNo=" + jlist[i].goodsNo;
					const img = document.createElement("img");
					
					link.appendChild(img);
					imgbox.appendChild(link);
					
					item.appendChild(imgbox);
					
					const infobox = document.createElement("div");
					infobox.className = "item_info_box";
					const link2 = document.createElement("a");
					link2.href="./goodsView?goodsNo=" + jlist[i].goodsNo;
					const name = document.createElement("div");
					name.className = "item_name";
					name.innerText = jlist[i].goodsNm;
					const price = document.createElement("div");
					price.className = "item_price";
					
					if(jlist[i].memberOnly && document.getElementById("memNo").value == "false"){
						const non_member = document.createElement("span");
						non_member.innerText = "할인클럽";
						
						price.appendChild(non_member);
					}else{
						const price_title = document.createElement("span");
						price_title.innerText = "할인혜택가";
						const discount = document.createElement("span");
						discount.className = "discountRate";
						discount.innerText = jlist[i].discountPercent;
						const before_price = document.createElement("span");
						before_price.className = "before_price";
						price_title.innerText = jlist[i].goodsPrice;
						const after_price = document.createElement("span");
						after_price.className = "after_price";
						after_price.innerText = jlist[i].fixedPrice;
						
						price.appendChild(price_title);
						price.appendChild(before_price);
						price.appendChild(after_price);
						
					}
					
					const tagbox = document.createElement("div");
					tagbox.className="item_tag_box";
					
					
					
					link2.appendChild(name);
					link2.appendChild(price);
					link2.appendChild(tagbox);
					
					infobox.appendChild(link2);
					
					item.appendChild(infobox);
					
					panel.appendChild(item);
				}
			}
		}
		
		endLoading();
		
		
	}
	
}

function addList(cate){
	
	const xhr = new XMLHttpRequest();

	var after_height = Math.floor((document.body.offsetHeight - window.scrollY)/1000);
	
	if(b_height == after_height){
		return;
	}else{
		b_height = after_height;
	}
	
	if(b_height<=2){
				
		var data = new Object();
		
		data.cate = cate;
		data.pageNo = pageNo;
		data.memNo = document.getElementById("memNo").value;
		
		var dataStr = JSON.stringify(data);
		
		xhr.open("post","../goods/addList");
		xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8");
		
		xhr.responseType = "json";
		
		xhr.send(dataStr);
		
		xhr.onload = function(){
			startLoading();
			var jlist = xhr.response;
			
			const itemWrap = document.getElementsByClassName("category_item_wrap");
			
			if(jlist.length>0){
				for(var i = 0; i<jlist.length; i++){
					
					if(jlist[i].adultFl && document.getElementById("adultFl").value!="true"){
						//	성인전용 상품 : 성인인증 x	=> 노출 X
						
					}else{
					
						const item = document.createElement('li');
						item.className = "category_item_wrap";
						
						const imgbox = document.createElement("div");
						imgbox.className = "item_img_box";
						const link = document.createElement("a");
						link.href="./goodsView?goodsNo=" + jlist[i].goodsNo;
						const img = document.createElement("img");
						
						link.appendChild(img);
						imgbox.appendChild(link);
						
						item.appendChild(imgbox);
						
						const infobox = document.createElement("div");
						infobox.className = "item_info_box";
						const link2 = document.createElement("a");
						link2.href="./goodsView?goodsNo=" + jlist[i].goodsNo;
						const name = document.createElement("div");
						name.className = "item_name";
						name.innerText = jlist[i].goodsNm;
						const price = document.createElement("div");
						price.className = "item_price";
						
						if(jlist[i].memberOnly && document.getElementById("memNo").value == "false"){
							const non_member = document.createElement("span");
							non_member.innerText = "할인클럽";
							
							price.appendChild(non_member);
						}else{
						
							const price_title = document.createElement("span");
							price_title.innerText = "할인혜택가";
							const discount = document.createElement("span");
							discount.className = "discountRate";
							discount.innerText = jlist[i].discountPercent;
							const before_price = document.createElement("span");
							before_price.className = "before_price";
							price_title.innerText = jlist[i].goodsPrice;
							const after_price = document.createElement("span");
							after_price.className = "after_price";
							after_price.innerText = jlist[i].fixedPrice; 
							price_title.innerText = "할인혜택가";
							

							price.appendChild(price_title);
							price.appendChild(before_price);
							price.appendChild(after_price);
							
						}
						
						const tagbox = document.createElement("div");
						tagbox.className="item_tag_box";
						
						link2.appendChild(name);
						link2.appendChild(price);
						link2.appendChild(tagbox);
						
						infobox.appendChild(link2);
						
						item.appendChild(infobox);
						
						panel.appendChild(item);
					}
				}
			}
			pageNo++;
			endLoading();
			
		}
		
		after_height = Math.floor((document.body.offsetHeight - window.scrollY)/1000);
		
		if(b_height == after_height){
			return;
		}else{
			b_height = after_height;
		}
	}
	
}