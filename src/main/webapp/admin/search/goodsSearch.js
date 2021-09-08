function sendGoods(){
    var check = document.querySelectorAll('input[name="ck"]:checked');
    if(check.length > 0){		    	
	    var val = [];
	    for(var i = 0; i < check.length; i++){
			val.push(check[i].value);
		}
		var d = confirm("선택한"+check.length+"개를 적용하시겠습니가?");
		if(d){
			$.ajax({
				type:"post",
				url:"../goods/sendGoodsData",
				traditional : true,	//ajax 배열 보는법
				data: {
					goodsNo:val
				},
				dataType:"json",
				success:function(i){
					sendChildValue(i);
				},
				error:function(){
					alert('적용실패(통신장애)');
				}
				
			});
		}
    }else{
    	alert("상품을 선택해주세요.");
    }
	
}

function sendChildValue(i){
	opener.setChildValue(i);	
	window.close();
}

  	
/* 날짜 객체 받아서 문자열로 리턴하는 함수 */
function getDateStr(myDate){
	return (myDate.getFullYear() + '-' + (myDate.getMonth() + 1) + '-' + myDate.getDate())
}
/* 어제 날짜를 문자열로 반환 */
function yesterday() {
var d = new Date()
var dayOfMonth = d.getDate()
d.setDate(dayOfMonth - 1)
return getDateStr(d)
}

/* 오늘 날짜를 문자열로 반환 */
function today() {
var d = new Date()
return getDateStr(d)
}

/* 오늘로부터 1주일전 날짜 반환 */
function lastWeek() {
var d = new Date()
var dayOfMonth = d.getDate()
d.setDate(dayOfMonth - 7)
return getDateStr(d)
}

/* 오늘로부터 1개월전 날짜 반환 */
function lastMonth() {
var d = new Date()
var monthOfYear = d.getMonth()
d.setMonth(monthOfYear - 1)
return getDateStr(d)
}

/* 오늘로부터 3개월전 날짜 반환 */
function threeMonth() {
var d = new Date()
var monthOfYear = d.getMonth()
d.setMonth(monthOfYear - 3)
return getDateStr(d)
}


$("#srch_area :button").click(function(){
    event.preventDefault();
    var rname = $(this).attr("name")
    $("#search_end_date").val(today())	        
    //------오늘날짜로 기본설정-----
    if( rname == "yesterday"){
   		$("#search_start_date").val(yesterday());
    //------어제날짜
    }else if( rname == "today"){
        $("#search_start_date").val(today())
    //------오늘날짜
    }else if(rname == "week") {
        $("#search_start_date").val(lastWeek())
    //------1주일전날짜
    }else if(rname == "month"){
        $("#search_start_date").val(lastMonth())
    //------1달전날짜
    }else if(rname == "3month"){
        $("#search_start_date").val(threeMonth())
    //------3달전날짜
    }else{
    	$("#search_end_date").val('');
    	$("#search_start_date").val('');
    }
});


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