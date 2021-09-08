function calPercent(){
	const goodsPrice = document.getElementById("goodsPrice").value;
	const fixedPrice = document.getElementById("fixedPrice").value;
	
	if(goodsPrice!= "" && fixedPrice!= ""){
		document.getElementById("discountPercent").value = Math.round((goodsPrice-fixedPrice)/goodsPrice*100);
	}else{
		document.getElementById("discountPercent").value = "";
		return;
	}
	
}

function update(){
	var form = document.getElementById("form");
	var chk = confirm('상품을 수정하시겠습니까?');

	if(chk){
		form.submit();
	}

}
