/**
 * 
 */
let prevBtn = document.getElementById('prevBtn'),
			nextBtn = document.getElementById('nextBtn'),
			rollingBan = document.querySelector('.rollingBan ul'),
			banCount = document.querySelectorAll('.rollingBan ul li').length,
			banerWidth = 1200,
			count = 0;
		
		
		if(banCount > 0){
			rollingBan.style.width = (banerWidth * banCount) + 'px';
		}
		
		var timer = setInterval(function(){
						if(count >= banCount-1){
							banerMoving(0);
						}else{
							banerMoving(count+1);
						}
					}, 5000);
		
		//이미지 교체
		function banerMoving(i){
			rollingBan.style.left = -(banerWidth * i) + 'px';
			count = i;
		}
		
		//이전 버튼
		prevBtn.addEventListener('click',function(){
			if(count == 0){
				banerMoving(banCount-1);
			}else{
				banerMoving(count-1);	
			}
		});
		
		//다음버튼
		nextBtn.addEventListener('click',function(){
			if(count < banCount-1){
				banerMoving(count+1);	
			}else{
				banerMoving(0);
			}	
		});
		