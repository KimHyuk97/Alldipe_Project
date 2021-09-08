/**
 * 
 */

var mainNext22 = document.getElementById('mainNext22'), //다음버튼
mainPrev22 = document.getElementById('mainPrev22'), //이전버튼
ulView11 = document.querySelector('.ulView11'),
ulViewLength = itemsLength = document.querySelectorAll('.ulView22').length,
ulWidth = 1200,
idx2 = 0;
		 
		 
if(ulViewLength > 0){
ulView11.style.width = (ulWidth*ulViewLength)+"px";
}
	 
	 
function moveSlide22(v){
ulView11.style.left = -(ulWidth * v)+"px";
idx2 = v;
}
	 
	 
mainNext22.addEventListener('click',function(){
if(idx2 < ulViewLength-1){
 moveSlide22(idx2+1);
}else{
 moveSlide22(0);
}
});
	 
mainPrev22.addEventListener('click',function(){
if(idx2 > 0){
 moveSlide22(idx2 - 1);	
}else{
 moveSlide22(idx2+1);
}
});

var mainNext = document.getElementById('mainNext'), //다음버튼
mainPrev = document.getElementById('mainPrev'), //이전버튼
mainSwiperUl = document.querySelector('.mainSwiperUl'), //사진을 감싸고 있는 부모
itemsLength = document.querySelectorAll('.mainSwiperUl li').length, //사진의 갯수
itemsWidth = 1200, // 사진의 크기
idx = 0, // 임의로 만든 수
liCount = document.getElementById('liCount'), //전체 사진 중 몇번 째 사진인지 알려주는 변수
liLength = document.getElementById('liLength'), //전체 사진 수
mainrollingColor = document.getElementById('mainrollingColor'), //배경색
viewController22 = document.getElementById('viewController22'); //전체보기

function mainColor(num2){
 if(num2 == 0){
	 mainrollingColor.style.background = '#11100c';
 }else if(num2 == 2){
	 mainrollingColor.style.background = '#396ef3';
 }else if(num2 == 3){
	 mainrollingColor.style.background = '#F2EFFB';
 }else if(num2 == 4){
	 mainrollingColor.style.background = '#cfc9c1';
 }else if(num2 == 5){
	 mainrollingColor.style.background = '#efeae6';
 }else if(num2 == 6){
	 mainrollingColor.style.background = '#F2F2F2';
 }else if(num2 == 7){
	 mainrollingColor.style.background = '#000c1d';
 }else if(num2 == 8){
	 mainrollingColor.style.background = '#2b2a26';
 }else if(num2 == 9){
	 mainrollingColor.style.background = '#112965';
 }else if(num2 == 10){
	 mainrollingColor.style.background = '#396ef3';
 }else if(num2 == 11){
	 mainrollingColor.style.background = '#afd1d5';
 }else if(num2 == 12){
	 mainrollingColor.style.background = '#d2e1ff';
 }else if(num2 == 13){
	 mainrollingColor.style.background = '#070b18';
 }else{
	 mainrollingColor.style.background = '#11100c';
 }
}	

mainColor(idx);

if(itemsLength > 1){
mainSwiperUl.style.width = (itemsLength * itemsWidth)+"px";
liLength.innerHTML = itemsLength;			
liCount.innerHTML = idx+1;
startTimer();
}

function moveSlide(num){
mainSwiperUl.style.left = -itemsWidth*num+"px";
mainColor(num+1);
idx = num;
liCount.innerHTML = idx+1;
}

var isPause = false;
var timer;

function startTimer(){
isPause = false;
timer = setInterval(function(){
 timeSwiper();
}, 5000);
}

function stopTimer(){
clearInterval(timer);
isPause = true;
}

function timeSwiper(){
if(!isPause){
  if(idx < itemsLength-1){
	 moveSlide(idx+1);
  }else{
	 moveSlide(0);
	 idx == 0;
 }
}
}

mainPrev.addEventListener('click',function(){
if(idx > 0){
 moveSlide(idx-1);
}else{
 moveSlide(itemsLength-1);
}
});

mainNext.addEventListener('click',function(){
if(idx < itemsLength-1){
 moveSlide(idx+1);
}else{
 moveSlide(0);
 idx = 0;
}
});

function stop(){
if (document.getElementById("imgChange").getAttribute('src')=='./fileF/swiper/ongoing.png'){
 document.getElementById("imgChange").src = './fileF/swiper/start.png';
 stopTimer();
}else if (document.getElementById("imgChange").getAttribute('src') == './fileF/swiper/start.png'){
 document.getElementById("imgChange").src = './fileF/swiper/ongoing.png';			
	startTimer();
}      
}

function view(){
viewController22.style.display = 'block';
mainSwiperUl.style.opacity = 0.5;
}

function none(){
viewController22.style.display = 'none';
mainSwiperUl.style.opacity = 1;
}