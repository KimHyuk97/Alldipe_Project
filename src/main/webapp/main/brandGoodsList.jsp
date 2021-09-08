<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/goods/brand.css">

<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
<div class="wrap">
<div class="cont_top">
    <div class="slider_container">
        <!-- Slider main container -->
        <div class="swiper-container">
            <div class="swiper-wrapper">
	              <div class="swiper-slide">Slide 1</div>
	              <div class="swiper-slide">Slide 2</div>
	              <div class="swiper-slide">Slide 3</div>
	              <div class="swiper-slide">Slide 4</div>
            </div>
            <!-- Add Arrows -->
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
        </div>
    </div>
    <div class="title">
        <a href=""><span>오늘의 브랜드 &gt;</span></a>
    </div>
    <div class="top_menu">
    	<div class="brandCate">
	    	<ul>
	    		<li value="1">패션</li>
	    		<li value="2">뷰티</li>
	    		<li value="3">유아동/출산</li>
	    		<li value="4">식품</li>
	    		<li value="5">가구/인테리어</li>
	    		<li value="6">가전/디지털</li>
	    		<li value="7">주방/생활용품</li>
	    		<li value="8">스포츠/자동차</li>
	    		<li value="9">문구/취미/도서/반려</li>
	    		<li value="10">여행/티켓/E-쿠폰</li>
	    	</ul>
    	</div>
		<div>
			<div class='swiper-container02'>
				<div class='swiper-wrapper'  id="brandView">
				</div>
			</div>
			<div id="brandallView"></div>
		</div>
    </div>
    
    <div class="line">
      <div class="line_box"></div>   
    </div>
   
    <div class="title title02">
        <a href=""><span>브랜드 베스트 상품 &gt;</span></a>
    </div>
</div>
<div class="content" id="goodsContents">

</div>
	<script>
       	let li = document.querySelectorAll('.brandCate ul li');
       	
       	//로딩되면 패션카테고리의 브랜드를 가져옴
       	$(document).ready(function(){
       		$.ajax({
				type:"post",
				url:"brandList",
				data:{
					cateCd : li[0].value,
    			},
				dataType:"json",
				success:function(i){	
					brandList(i);
					li[0].style.background = "#396ef3";
					li[0].style.color = "#fff";
					brandSelect(i[0].brandCd);
				},
				error:function(){
					console.log('x');
				}
			});       		
       	});
       	
       	//카테고리를 클릭하면 해당 카테고리 데이터를 가져옴
        for(var i = 0; i < li.length; i++) {
        	li[i].addEventListener('click',function(e){	
				
				$.ajax({
					type:"post",
					url:"brandList",
					data:{
	    				cateCd : e.target.value,
	    			},
					dataType:"json",
					success:function(i){
						if(i != ""){								
							brandList(i);
							e.target.style.background = "#396ef3";
							e.target.style.color = "#fff";
						}else{
							alert('현재 카테고리는 비어있습니다.');
						}
					},
					error:function(){
						console.log('x');
					}
				});
				
        	});
        }
        
        //선택한 카테고리 데이터 리스트
        function brandList(i){
        	var o = "";
        	for(var w = 0; w < i.length; w++){
	        	o += "<button onclick='brandSelect("+i[w].brandCd+")'>";
	        	o += "<div class='swiper-slide'>";
	        	o += "<input type='hidden' name='sno' value='"+i[w].sno+"'>";
	        	o += "<input type='hidden' name='brandCd' value='"+i[w].brandCd+"'>";
	        	o += "<ul class='brand_content'>";
	        	o += "<li>"+i[w].brandImg+"</li>";
	        	o += "<li>"+i[w].brandNm+"</li>";
	         	o += "</ul></div></button>";	        		
        	}
        	var j = "<div><input type='button' value='전체보기' onclick='allView()' ></div>"
        	document.getElementById("brandallView").innerHTML = j;
        	document.getElementById('brandView').innerHTML = o;
        }
		
        //브랜드 전체보기
        function allView(i){
        	console.log(i)
        	var o = "";
        	o += "<div>";
        	for(var v = 0; v < i.length; v++){
	        	o += "<div>";
	        	o += "<input type='hidden' name='sno' value='"+i[v].sno+"'>";
	        	o += "<input type='hidden' name='brandCd' value='"+i[v].brandCd+"'>";
	        	o += "<ul>";
	        	o += "<li>"+i[v].brandImg+"</li>";
	        	o += "<li>"+i[v].brandNm+"</li>";
	         	o += "</ul></div>";	        		
        	}
        	o += "</div>";
        	
        	document.getElementById('brandView').innerHTML = o;
        }
        
        //선택한 브랜드 상품 보기
        function brandSelect(brandCd){
        	$.ajax({
				type:"post",
				url:"brandGoodsList",
				data:{
					brandCd : brandCd,
    			},
				dataType:"json",
				success:function(i){
					if(i != ""){								
						goodsList(i);
					}else{
						alert('현재 브랜드의 상품이 비어있습니다.');
					}
				},
				error:function(){
					console.log('x');
				}
			});	
        }
        
        function goodsList(s){
        	var o = "";
        	o += "<div>"
    		for(var i = 0; i < s.length; i++){
    			o += "<div class='content_box'>";
    			o += "<a href='goodsView?goodsNo="+s[i].goodsNo+"'>";
    			o += "<img src='"+s[i].representImg+"'>";
    			o += "<div class='content_txt'>";
    			o += "<div class='content_title'>"+s[i].goodsNm+"</div>";
    			o += "<div class='price'>";
    			o += "할인혜택가 <span>"+s[i].goodsDiscountPercent+"%</span> <p>"+s[i].goodsPrice+"원</p>  <span>"+s[i].fixedPrice+"원</span>";
    			o += "</div>";
        	    o += "<div class='mark'>";
        	    o += "<p>페스티벌</p>";
        	    o += "<p>무료배송</p>";
        	    o += "</div>";
        	    o += "</div> ";
        	    o += "</a>";
        	    o += "</div>";
    		}
        	o += "</div>";
    	    document.getElementById('goodsContents').innerHTML = o;
        }
        
        </script>
    
<script>
        var swiper = new Swiper('.swiper-container', {
            loop:true,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
        });

        var swiper = new Swiper('.swiper-container02', {
            
            slidesPerView: 1,
            spaceBetween: 10,
            // init: false,
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            breakpoints: {
                640: {
                slidesPerView: 2,
                spaceBetween: 20,
                },
                768: {
                slidesPerView: 4,
                spaceBetween: 40,
                },
                1024: {
                slidesPerView: 5,
                spaceBetween: 50,
                },
            }
    });
</script>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>