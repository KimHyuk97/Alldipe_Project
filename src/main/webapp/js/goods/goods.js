var buyBtn = document.getElementById('buyBtn'),
	loginBtn = document.getElementById('loginBtn'),
	loginBtn2 = document.getElementById('loginBtn2'),
	cartBtn = document.getElementById('cartBtn'),
	btnDisabled = 0;

$(function () {

    /*메뉴고정*/
    var navOffset = $('.cont_nav_wrap').offset();
    $(window).scroll(function () {
        if ($(document).scrollTop() > navOffset.top) {
            $('.cont_nav_wrap').addClass('nav_fixed');
        } else {
            $('.cont_nav_wrap').removeClass('nav_fixed');
        }
    });

    /*slider*/
    var galleryThumbs = new Swiper('.gallery-thumbs', {
        spaceBetween: 10,
        slidesPerView: 5,
        loop: true,
        freeMode: true,
        loopedSlides: 1, //looped slides should be the same
        watchSlidesVisibility: true,
        watchSlidesProgress: true,
    });
    var galleryTop = new Swiper('.gallery-top', {
        spaceBetween: 10,
        loop: true,
        loopedSlides: 1, //looped slides should be the same
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        thumbs: {
            swiper: galleryThumbs,
        },
    });
});

 $(function() {
            var menuList = $(".cont_list"),
                sectionList = $(".section");

            //메뉴클릭 시 섹션위치를 찾아 움직이는 스크립트 
            menuList.click(function(e) {
                e.preventDefault(); 
                
                var menuIndex = $(this).index(),
                    sectionIndex = sectionList.eq(menuIndex); 
                var sectionOffset = sectionIndex.offset().top - 70;

                $("html, body").animate({
                    scrollTop: sectionOffset
                }); // 해당 속성값 만큼 스크롤 시켜줌
            });


        });

 /*옵션리스트*/
    $(".option_box").click(function() {
        $(".op_list_wrap").slideToggle();
    });


    /*카운트*/
    jQuery(document).ready(function() {
        $('.plus_btn').click(function(e) {
            e.preventDefault();
            fieldName = $(this).attr('field');
            var currentVal = parseInt($('input[name=' + fieldName + ']').val());
            if (!isNaN(currentVal)) {
                $('input[name=' + fieldName + ']').val(currentVal + 1);
            } else {
                $('input[name=' + fieldName + ']').val(1);
            }
        });
        $(".minus_btn").click(function(e) {
            e.preventDefault();
            fieldName = $(this).attr('field');
            var currentVal = parseInt($('input[name=' + fieldName + ']').val());
            if (!isNaN(currentVal) && currentVal > 1) {
                $('input[name=' + fieldName + ']').val(currentVal - 1);
            } else {
                $('input[name=' + fieldName + ']').val(1);
            }
        });
    });

//옵션선택
function option(i,totalStock,optionNo,nm,nm2,nm3,nm4,nm5,price,fixedPrice,goodsPrice){
	document.getElementById('optionVal').style.display = "flex";
	document.getElementById('op_list_wrap').style.display = "none";
	const totalPrice = fixedPrice+price;
	if(i == i){	
		var ww = "";
		ww += "<input type='hidden' name='optionNo' id='optionNo' value='"+optionNo+"'><input type='hidden' id='goodsOptionNm' name='goodsOptionNm' value='"+nm+nm2+nm3+nm4+nm5+"'><input type='hidden' id='optionPriceVal' name='optionfixedPrice' value='"+price+"'>";
        ww += "<div class='op_text' id='op_text'>"+nm+nm2+nm3+nm4+nm5+"</div>";
        ww += "<div class='op_count'>";
        ww += "<div class='item_count'>";
        ww += "<input type='button' value='-' onclick='minus("+price+","+i+","+fixedPrice+","+goodsPrice+")' class='minus_btn' field='quantity' />";
        ww += "<input type='text' name='goodsCnt' id='goodsCnt' value='1' min='1' class='qty' readonly/>";
        ww += "<input type='button' value='+' onclick='plus("+price+","+i+","+fixedPrice+","+totalStock+","+goodsPrice+")' class='plus_btn' field='quantity' /></div></div>";
		ww += "<div class='op_price' id='op_price"+i+"' value='"+price+"'>"+"+"+price+"원"+"</div>";		
      	ww += "<input type='button' class='optionCloseBtn' onclick='closeBtn()' value='X'>"
      	document.getElementById('optionVal').style.display = "flex";
      	document.getElementById('optionVal').innerHTML = ww;

		document.getElementById('goods_total_price').innerHTML = totalPrice;
		document.getElementById('total_price').style.display = "block";
		
		//버튼 활성화를 위한 값 지정
		btnDisabled = 1;
		
	}
}
//상품옵션수 플러스
function plus(price,i,fixedPrice,totalStock,goodsPrice){ //옵션 가격, 순서, 판매가격, 재고량
	var plus = document.getElementById('goodsCnt').value;
	var plus2 = parseInt(plus);
	
	if(plus >= totalStock){
		alert('재고량이 없습니다.');
	}else{
		plus2 = plus2 + 1;
		let optionPrice = parseInt(price);	//옵션가격
		
		//상품가격
		document.getElementById('goodsPrice').value = goodsPrice*plus2;
		document.getElementById('goodsPrice22').innerHTML = goodsPrice*plus2;
		
		//옵션가격
		document.getElementById('op_price'+i).innerHTML = "+"+optionPrice*plus2+"원";
		document.getElementById('op_price'+i).value = optionPrice*plus2;

		//전체가격
		document.getElementById('goods_total_price').innerHTML = (optionPrice*plus2)+(fixedPrice*plus2);
		document.getElementById('fixedPrice22').innerHTML = document.getElementById('fixedPrice').value*plus2;	//판매가격(화면 표출)
	
		document.getElementById('goodsCnt').value = plus2;
		document.getElementById('goodsCnt').innerHTML = plus2;
	}
}


//상품옵션수 마이스너스
function minus(price,i,fixedPrice,goodsPrice){
	var minus = document.getElementById('goodsCnt').value;
	var minus2 = parseInt(minus);
	if(minus < 2){
		minus2 = 1;
	}else{
		minus2 = minus2 - 1;
	}
	let optionPrice = parseInt(price);
	
	//상품가격
	document.getElementById('goodsPrice').value = goodsPrice*minus2;
	document.getElementById('goodsPrice22').innerHTML = goodsPrice*minus2;
	
	//옵션가격
	document.getElementById('op_price'+i).innerHTML = "+"+optionPrice*minus2+"원";
	document.getElementById('op_price'+i).value = optionPrice*minus2;
		
	//전체가격
	document.getElementById('goods_total_price').innerHTML = (optionPrice*minus2)+(fixedPrice*minus2);
	document.getElementById('fixedPrice22').innerHTML = document.getElementById('fixedPrice').value/minus2;	//판매가격(화면 표출)
	
	document.getElementById('goodsCnt').value = minus2;
	document.getElementById('goodsCnt').innerHTML = minus2;
}

//상품옵션 닫기
function closeBtn(){
	document.getElementById('optionVal').style.display = "none";
	document.getElementById('total_price').style.display = "none";
	//버튼 활성화를 위한 값 지정
	btnDisabled = 0;
}



//리뷰

//갤러리 open
function reviewGallery(){
	document.getElementById('primgList').style.display = "block";
}


//갤러리 상세내용
function reviewImgSelect(i){
	document.getElementById('reviewView'+i).classList.add('on');
	document.getElementById('prImgListMain2').style.display = "block";
	document.getElementById('prImgListMain').style.display = "none";
}

//갤러리 close
if(document.getElementById('primgListClose') != null){
	document.getElementById('primgListClose').addEventListener('click', () => {
		document.getElementById('primgList').style.display = "none";
	});	
}

//갤러리 뒤로가기
function primgListClose2(s){
	console.log(s);
	document.getElementById('reviewView'+s).classList.remove('on');
	document.getElementById('prImgListMain2').style.display = "none";
	document.getElementById('prImgListMain').style.display = "flex";
}

//리뷰  end

//qa리스트
	if(document.getElementById('qaWrite') != null){
		document.getElementById('qaWrite').addEventListener('click', () => {
			document.getElementById('qaModal').style.display = "block";
			document.getElementById('writerIp').value = ip();
		});
	}
	function qaClose(){
		document.getElementById('qaModal').style.display = "none";
	}
	
	//비밀글
	function secretFl(){
		if(document.getElementById('scFl').checked == true){
			document.getElementById('scPW').style.display = "table-row";
			document.getElementById('scFl').value = "true";
		}else{
			document.getElementById('scPW').style.display = "none";
			document.getElementById('scFl').value = "false";
		}
	}
	
	//문의작성 버튼
	if(document.getElementById('qaBtn') != null){
		document.getElementById('qaBtn').addEventListener('click', () => {
			if(document.getElementById('qaTitle').value == "" || document.getElementById('qaTitle').value == null){
				alert('제목을 입력해주세요.');
				document.getElementById('qaTitle').focus();
			}else if(document.getElementById('qaContent').value == "" || document.getElementById('qaContent').value == null){
				alert('내용을 입력해주세요.');
				document.getElementById('qaContent').focus();
			}else if(document.getElementById('scFl').value == "y" && (document.getElementById('qaPw').value == "" || document.getElementById('qaPw').value == null)){
				alert('비밀번호를 입력해주세요.');
				document.getElementById('qaPw').focus();
			}else{		
				alert('문의가 완료되었습니다.');
				goodsQAWrite.submit();
			}
			
		});
	}

	//비밀글 open
	function secretPw(i,qwNo,memNo){
		console.log('qwNo'+qwNo);
		console.log('memNo'+memNo);
		if(qwNo == memNo){
			var l = "<input type='password' id='secretPassword2' placeholder='비밀번호를 입력해주세요.'><input type='button' onclick='scpwFlBtn("+i+")' value='확인'>";
			document.getElementById('secretContent2'+i).innerHTML = l;
			document.getElementById('secretContent'+i).style.display = "none";
		}else{
			alert('비밀글로 작성자만 볼 수 있습니다.');
		}
	}
	
	function scpwFlBtn(i){	
		var pw = document.getElementById('secretPassword2').value;
		$.ajax({
			type:"post",
			url:"qaPwCheck",
			data:{
				pw:pw,
			},
			dataType:"text",
			success:function(title){
				if(title != null){
					document.getElementById('secretContent2'+i).style.display = "none";
					document.getElementById('stc'+i).style.display = "block"; 
					document.getElementById('stc'+i).style.textIndent = 20+"px";
					document.getElementById('stc'+i).innerHTML = title;
					showQa(i);
				}else{
					alert("비밀번호가 틀렸습니다");
				}
			},
			error: function(){
				console.log("통신장애");
			}				
		});		
	}
	
	//문의글 내용보기
	function showQa(i){
		document.getElementById('qaContentView'+i).style.display = "table-row";
		document.getElementById('qaContentShow'+i).classList.toggle('qaContentShow');		
	}

//qa end
	
//로그인버튼
if(loginBtn != null){
	loginBtn.addEventListener('click', login);	
}

if(loginBtn != null){
	loginBtn2.addEventListener('click', login);	
}

function login(){
	alert('로그인 후 이용해주세요.');	
	location.href="login";
}

		
		

//결제버튼
if(buyBtn != null){
	buyBtn.addEventListener('click',() => {
		if(btnDisabled == 0){
			alert('옵션을 선택해주세요.');
		}else{
			orderGoods.submit();					
		}
	});
}




//장바구니 버튼
if(cartBtn != null){
	cartBtn.addEventListener('click', () => {
		if(btnDisabled == 0){
			alert('옵션을 선택해주세요.');
		}else{
				var	goodsNo = document.getElementById('goodsNo').value,
					scmNo = document.getElementById('scmNo').value,
					goodsCnt = document.getElementById('goodsCnt').value,
					optionNo = document.getElementById('optionNo').value, //옵션 sno
					memNo = document.getElementById('memNo').value,
					goodsOptionNm = document.getElementById('goodsOptionNm').value,
					goodsNm = document.getElementById('goodsNm').value,
					makerNm = document.getElementById('makerNm').value,
					deliveryPrice = document.getElementsByClassName('d_price')[0].innerText,
					representImg = document.getElementById('representImg').value;
				
				if(memNo < 0 || memNo == null || memNo == ""){
					memNo = 0;
				}		
			
				//쿠키확인하기
				let siteKey = "";	
				
				function getCookie(name) { 
			
				     var nameOfCookie = name + "="; 
				     var x = 0;
			
				     while (x <= document.cookie.length) {  
				          var y = (x + nameOfCookie.length); 
				          if (document.cookie.substring(x, y) == nameOfCookie) { 
			
				               if ((endOfCookie = document.cookie.indexOf(";", y)) == -1){	            	   
				                    endOfCookie = document.cookie.length;
				               } 
				               return unescape(document.cookie.substring(y, endOfCookie)); 
				          }
				          x = document.cookie.indexOf(" ", x) + 1;
				          
				          if (x == 0){	        	  
				               break; 
				          }
				     }
				     return "";
			
				}
				
				var cookie = getCookie('siteKey');
				if (cookie != "") {
				     siteKey = cookie;
				}else{
					//인증코드 만들기
					var chars = '0123456789';
					var stringLength = 10;
					var randomstring = '';
						
					for (var i = 0; i < stringLength; i++) {
						var rnum = Math.floor(Math.random() * chars.length)
						randomstring += chars.substring(rnum, rnum + 1)
					}
			
					siteKey = randomstring;
					
					//쿠키저장
					var date = new Date();
			    	date.setFullYear(date.getFullYear()+1);
					var coo = '';
					coo += 'siteKey ='+ siteKey +'; expires = ' + date.toUTCString();
					document.cookie = coo;
				}
			
				//장바구니 상품 담기
				$.ajax({
					type : "POST",
					url:"cartGoodsAdd",
					data:{
						memNo:memNo,
						scmNo:scmNo,
						goodsNo:goodsNo,
						goodsCnt:goodsCnt,
						optionNo:optionNo,  //옵션 sno
						goodsOptionNm : goodsOptionNm,
						siteKey:siteKey,
						goodsNm:goodsNm,
						makerNm:makerNm,
						deliveryPrice:deliveryPrice,
						representImg:representImg
					},
					success:function(o){
						if(o == 1){
							var cartGo = confirm('장바구니에 상품이 담겼습니다. 장바구니페이지로 이동하시겠습니까?');
							if(cartGo == true){
								location.href="cart?memNo="+memNo+"&siteKey="+siteKey;
							}	
						}else{
							var cartGo = confirm('이미 장바구니에 담긴 상품입니다. 장바구니페이지로 이동하시겠습니까?');
							if(cartGo == true){
								location.href="cart?memNo="+memNo+"&siteKey="+siteKey;
							}
						}
					},
					error:function(){
						alert('d');
					},
				});
			}
	});	
}	
				

   