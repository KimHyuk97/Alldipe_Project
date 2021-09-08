     //체크박스 전체 선택 및 전체 해제
     $(document).ready(function(){
    //최상단 체크박스 클릭
        $("#ck_all").click(function(){
            //클릭되었으면
            if($("#ck_all").prop("checked")){
                //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
                $(".ck").prop("checked",true);
                //클릭이 안되있으면
            }else{
                //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
                $(".ck").prop("checked",false);
            }
        })
    })
    //탭이벤트//
    function CB(){
		const val = event.target.value;
        if(event.target.value == 0){
           location.href="goodsClassify?page="+val;
        }else if(event.target.value == 1){
           location.href="goodsClassify?page="+val;
        }else if(event.target.value == 2){
           location.href="goodsClassify?page="+val;
        }
    }

function search(page){
	if(page == 0){
		const cate  = document.getElementById("large").value,
			  cate2 = document.getElementById("middle").value,
			  cate3 = document.getElementById("small").value;
		location.href="goodsClassify?page=0&mode=수정&cate="+cate+"&cate2="+cate2+"&cate3="+cate3;
	}else if(page == 1){
		const brand = document.getElementById("brand").value;
		location.href="goodsClassify?page=1&mode=수정&brand="+brand;
	}else if(page == 2){
		const theme = document.getElementById("theme").value;
		location.href="goodsClassify?page=2&mode=수정&theme="+theme;
	}
}

//카테고리 1,2,3 구분생성
function cateAdd(i){
	const cate        = document.getElementById("large"),
		  cate2       = document.getElementById("middle"),
		  optionVal01 = cate.options[cate.selectedIndex].value,
		  optionVal02 = cate2.options[cate2.selectedIndex].value;
		  
	if(i == 0){
		location.href="goodsClassify?page=0&mode=대분류추가";
	}else if(i == 1){
		if(optionVal01 != "" && optionVal01 != null ){
			location.href="goodsClassify?page=0&mode=중분류추가&cate="+optionVal01;
		}else{			
			alert("대분류 카테고리를 선택해주세요.");
		}
	}else if(i == 2){
		if((optionVal01 != "" && optionVal01 != null )&&(optionVal02 != "" && optionVal02 != null)){
			location.href="goodsClassify?page=0&mode=소분류추가&cate="+optionVal01+"&cate2="+optionVal02;
		}else{			
			alert("중분류 카테고리를 선택해주세요.");
		}
	}
}

function brandAdd(){
	location.href="goodsClassify?page=1&mode=브랜드추가";
}

function themeAdd(){
	location.href="goodsClassify?page=2&mode=테마추가";
}

//추천상품 생성
function goodsModal(){
	window.open("./searchGoodsList", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=50,width=1500,height=800");
}

let no = 1;
function setChildValue(i){	  
    const table = document.getElementById("requestGoodsData");
	for(var j = 0; j < i.length; j++){
		const tr = document.createElement("tr");
		tr.id = "tr"+i[j].goodsNo;
		
		const td = document.createElement("td");
		const input = document.createElement("input");
		input.type = "checkbox";
		input.name = "recomGoodsNo";
		input.className = "ck";
		input.id = "ck"+i[j].goodsNo;
		input.value = i[j].goodsNo;
		td.appendChild(input);
		tr.appendChild(td);
		
		const td2 = document.createElement("td");
		td2.innerText = no;
		no++;
		tr.appendChild(td2);
				
		const td3 = document.createElement("td");
		td3.innerText = i[j].representImg;
		tr.appendChild(td3);
		
		const td4 = document.createElement("td");
		td4.innerText = i[j].goodsNm;
		tr.appendChild(td4);
		
		const td5 = document.createElement("td");
		td5.innerText = i[j].fixedPrice;
		tr.appendChild(td5);
		
		const td6 = document.createElement("td");
		td6.innerText = i[j].scmNo;
		tr.appendChild(td6);
		
		const td7 = document.createElement("td");
		if(i[j].totalStock <= 0){
			td7.innerText = "품절";
		}else{
			td7.innerText = i[j].totalStock;
		}
		tr.appendChild(td7);
		
		const td8 = document.createElement("td");
		if(i[j].goodsSellFl == -1){
			td8.innerText = "관리자판매중지";
		}else if(i[j].goodsSellFl == 0){
			td8.innerText = "판매중지";
		}else if(i[j].goodsSellFl == 1){
			td8.innerText = "판매중";
		}
		tr.appendChild(td8);
	
		table.appendChild(tr);
		
	}


}

function goodsDisplay(val){
	const table = document.getElementById("requestGoodsData");
	const tr = document.getElementById("requestGoodsData").getElementsByTagName("tr"); //tbody가 가지고 있는 tr요소들
	let checktr = [];
	let idx = [];

	var check = document.querySelectorAll('input[name="recomGoodsNo"]');
 	for(var i = 0; i < check.length; i++){
    	if(check[i].checked){
			checktr.push("tr"+check[i].value);
			for(var j = 0; j < checktr.length; j++){
				if(checktr[j] == tr[i].id){
					idx.push(i);
				}	
			}
		}			
	 }

		
	if(val == "maxDown"){
		for(var z = idx.length-1; z >=0; z--){		
			table.insertBefore(tr[idx[z]],null);
		}
	}else if(val == "down"){
		for(var z = idx.length-1; z >=0 ; z--){						
			table.insertBefore(tr[idx[z]],tr[idx[z]+2]);
		}
	}else if(val == "up"){
		for(var z = 0; z < idx.length; z++){		
			table.insertBefore(tr[idx[z]],tr[idx[z]-1]);
		}
	}else if(val == "maxUp"){
		for(var z = 0; z < idx.length; z++){				
			table.insertBefore(tr[idx[z]],table.firstChild);
		}
	}
	
}


//추천상품 삭제
function goodsdel(){
	var check = document.querySelectorAll('input[name="recomGoodsNo"]:checked');
    
    if(check.length > 0){		    	

	    for(var i = 0; i < check.length; i++){
	    	if(check[i].checked){
				document.getElementById('requestGoodsData').removeChild(document.getElementById('tr'+check[i].value));
			}
		}
		
		if(document.getElementById('ck_all').checked){
			document.getElementById('ck_all').checked = false;
		}
			
    }else{
    	alert("삭제할 상품를 선택해주세요.");
    }
}



window.onload = function(){
	relationClassify()
}

function relationClassify(){             		
 	var check = document.getElementsByClassName("relation");
	 for(var i = 0; i < check.length; i++) {
	    if(check[i].checked) {
	    	if(check[i].value == 0){
	     		document.getElementById('relation01').style.display = "inline-block";
	     		document.getElementById('relation02').style.display = "none";
	     	}else if(check[i].value == 1){
	     		document.getElementById('relation01').style.display = "none";
	     		document.getElementById('relation02').style.display = "inline-block";
	     	}
	    }
	 }
}
           


//생성
function add(page){
    var form = document.createElement("form");
    var parm = new Array();
    var input = new Array();
	
	
	if(page == 0){	//카테고리 추가
        const recomGoodsNo = [];
        if(document.getElementsByName("recomGoodsNo")[0].value != "on" || document.getElementsByName("recomGoodsNo")[0].value != null){
	        for(var k = 0; k < document.getElementsByName("recomGoodsNo").length; k++){
	        	recomGoodsNo.push(document.getElementsByName("recomGoodsNo")[k].value);
	        }	        
        }else{
        	recomGoodsNo.push("");
        }
        
        const adultDisplayFl02 = document.getElementsByName("adultDisplayFl")[0];
        var adultDisplayFl = "";
        if(adultDisplayFl02.checked){
        	adultDisplayFl = "true";
        }else{
        	adultDisplayFl = "false";
        }
        
        form.action = "categoryClassifyAdd";
        form.method = "post";
        form.enctype = "multipart/form-data";    

        parm.push( ['cateCd', document.getElementsByName("cateCd")[0].value]);
        parm.push( ['cateNm', document.getElementsByName("cateNm")[0].value]);
        parm.push( ['displayFl', document.getElementsByName("displayFl")[0].value]);
        parm.push( ['commission', document.getElementsByName("commission")[0].value]);
        parm.push( ['keyword', document.getElementsByName("keyword")[0].value]);
        parm.push( ['file', document.getElementsByName("file")[0].value]);
        parm.push( ['overFile', document.getElementsByName("overFile")[0].value]);
        parm.push( ['adultFl', document.getElementsByName("adultFl")[0].value]);
        parm.push( ['adultDisplayFl', adultDisplayFl]);
        parm.push( ['recomGoodsNo', recomGoodsNo]);
        
	}else if(page == 1){	//브랜드 추가
		form.action = "brandClassifyAdd";
        form.method = "post";
        form.enctype = "multipart/form-data";
        parm.push( ['brandNm', document.getElementsByName("brandNm")[0].value] );
        parm.push( ['keyword', document.getElementsByName("keyword")[0].value] );
        parm.push( ['file', document.getElementsByName("file")[0].value] );
        
	}else if(page == 2){	//테마추가.
		const recomGoodsNo = [];
        if(document.getElementsByName("recomGoodsNo")[0].value != "on"){
	        for(var k = 0; k < document.getElementsByName("recomGoodsNo").length; k++){
	        	recomGoodsNo.push(document.getElementsByName("recomGoodsNo")[k].value);
	        }	        
        }else{
        	recomGoodsNo.push("");
        }
		form.action = "themeClassifyAdd";
        form.method = "post";
        form.enctype = "multipart/form-data";
        parm.push( ['themeNm', document.getElementsByName("themeNm")[0].value] );
        parm.push( ['themeDescription', document.getElementsByName("themeDescription")[0].value] );
        parm.push( ['cate', document.getElementsByName("cate")[0].value] );
        parm.push( ['cate2', document.getElementsByName("cate2")[0].value] );
        parm.push( ['cate3', document.getElementsByName("cate3")[0].value] );
        parm.push( ['brand', document.getElementsByName("brand")[0].value] );
        parm.push( ['file', document.getElementsByName("file")[0].value] );
        parm.push( ['recomGoodsNo', recomGoodsNo]);
	}

	
    for (var i = 0; i < parm.length; i++) {
        input[i] = document.createElement("input");
        input[i].setAttribute("type", "hidden");
        input[i].setAttribute('name', parm[i][0]);
        input[i].setAttribute("value", parm[i][1]);
        form.appendChild(input[i]);
    }
    document.body.appendChild(form);
    form.submit();
}

//수정
function mod(page){
	var form = document.createElement("form");
    var parm = new Array();
    var input = new Array();
	
	
	if(page == 0){	//카테고리 수정
        const recomGoodsNo = [];
        if(document.getElementsByName("recomGoodsNo")[0].value != "on"){
	        for(var k = 0; k < document.getElementsByName("recomGoodsNo").length; k++){
	        	recomGoodsNo.push(document.getElementsByName("recomGoodsNo")[k].value);
	        }	        
        }else{
        	recomGoodsNo.push("");
        }
        
        const adultDisplayFl02 = document.getElementsByName("adultDisplayFl")[0];
        var adultDisplayFl = "";
        if(adultDisplayFl02.checked){
        	adultDisplayFl = "true";
        }else{
        	adultDisplayFl = "false";
        }
        
        form.action = "categoryClassifyMod";
        form.method = "post";
        form.enctype = "multipart/form-data";    
		parm.push( ['cateSno', document.getElementsByName("cateSno")[0].value]);
        parm.push( ['cateCd', document.getElementsByName("cateCd")[0].value]);
        parm.push( ['cateNm', document.getElementsByName("cateNm")[0].value]);
        parm.push( ['displayFl', document.getElementsByName("displayFl")[0].value]);
        parm.push( ['commission', document.getElementsByName("commission")[0].value]);
        parm.push( ['keyword', document.getElementsByName("keyword")[0].value]);
        parm.push( ['file', document.getElementsByName("file")[0].value]);
        parm.push( ['overFile', document.getElementsByName("overFile")[0].value]);
        parm.push( ['adultFl', document.getElementsByName("adultFl")[0].value]);
        parm.push( ['adultDisplayFl', adultDisplayFl]);
        parm.push( ['recomGoodsNo', recomGoodsNo]);
        
	}else if(page == 1){	//브랜드 수정
		form.action = "brandClassifyMod";
        form.method = "post";
        form.enctype = "multipart/form-data";
        parm.push( ['brandSno', document.getElementsByName("brandSno")[0].value]);
        parm.push( ['brandNm', document.getElementsByName("brandNm")[0].value] );
        parm.push( ['keyword', document.getElementsByName("keyword")[0].value] );
        parm.push( ['file', document.getElementsByName("file")[0].value] );
        
	}else if(page == 2){	//테마수정
		const recomGoodsNo = [];
        if(document.getElementsByName("recomGoodsNo")[0].value != "on"){
	        for(var k = 0; k < document.getElementsByName("recomGoodsNo").length; k++){
	        	recomGoodsNo.push(document.getElementsByName("recomGoodsNo")[k].value);
	        }	        
        }else{
        	recomGoodsNo.push("");
        }
		form.action = "themeClassifyMod";
        form.method = "post";
        form.enctype = "multipart/form-data";
        parm.push( ['themeSno', document.getElementsByName("themeSno")[0].value]);
        parm.push( ['themeNm', document.getElementsByName("themeNm")[0].value] );
        parm.push( ['themeDescription', document.getElementsByName("themeDescription")[0].value] );
        parm.push( ['cate', document.getElementsByName("cate")[0].value] );
        parm.push( ['cate2', document.getElementsByName("cate2")[0].value] );
        parm.push( ['cate3', document.getElementsByName("cate3")[0].value] );
        parm.push( ['brand', document.getElementsByName("brand")[0].value] );
        parm.push( ['file', document.getElementsByName("file")[0].value] );
        parm.push( ['recomGoodsNo', recomGoodsNo]);
	}

	
    for (var i = 0; i < parm.length; i++) {
        input[i] = document.createElement("input");
        input[i].setAttribute("type", "hidden");
        input[i].setAttribute('name', parm[i][0]);
        input[i].setAttribute("value", parm[i][1]);
        form.appendChild(input[i]);
    }
    document.body.appendChild(form);
    form.submit();
}

//삭제
function del(page){
	var form = document.createElement("form");
    var parm = new Array();
    var input = new Array();
	
	
	if(page == 0){	//카테고리 삭제     
        form.action = "categoryClassifyDel";
        form.method = "post";    
		parm.push( ['cateSno', document.getElementsByName("cateSno")[0].value]);
             
	}else if(page == 1){	//브랜드 삭제
		form.action = "brandClassifyDel";
        form.method = "post";
        parm.push( ['brandSno', document.getElementsByName("brandSno")[0].value]);
              
	}else if(page == 2){	//테마삭제
		form.action = "themeClassifyDel";
        form.method = "post";
        parm.push( ['themeSno', document.getElementsByName("themeSno")[0].value]);
    }

	
    for (var i = 0; i < parm.length; i++) {
        input[i] = document.createElement("input");
        input[i].setAttribute("type", "hidden");
        input[i].setAttribute('name', parm[i][0]);
        input[i].setAttribute("value", parm[i][1]);
        form.appendChild(input[i]);
    }
    document.body.appendChild(form);
    form.submit();
}
