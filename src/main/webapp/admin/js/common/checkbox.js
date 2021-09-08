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
    });
});