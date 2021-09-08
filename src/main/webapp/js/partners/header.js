function viewContents(num){
	const content = document.querySelector(".menu_li_title");
	
	alert(content.display);
	
	if(content.style.display == 'block'){
		content.style.display = 'none';
	}
	
}

$("#menu01").on("click", function(e){
    e.preventDefault();
    $("#list01").toggle( "normal");
});
$("#menu02").on("click", function(e){
    e.preventDefault();
    $("#list02").toggle( "normal");
});
$("#menu03").on("click", function(e){
    e.preventDefault();
    $("#list03").toggle( "normal");
});
$("#menu04").on("click", function(e){
    e.preventDefault();
    $("#list04").toggle( "normal");
});
$("#menu05").on("click", function(e){
    e.preventDefault();
    $("#list05").toggle( "normal");
});
$("#menu06").on("click", function(e){
    e.preventDefault();
    $("#list06").toggle( "normal");
});
$("#menu07").on("click", function(e){
    e.preventDefault();
    $("#list07").toggle( "normal");
});
$("#menu08").on("click", function(e){
    e.preventDefault();
    $("#list08").toggle( "normal");
});