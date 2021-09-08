$(function(){
     /*이번주핫딜 슬라이드*/
    var swiper = new Swiper('.section01 .swiper-container', {
        slidesPerView: 5,
        spaceBetween: 8,
        navigation: {
            nextEl: '.section01 .swiper-button-next',
            prevEl: '.section01 .swiper-button-prev',
        },
        loop: false,
    });
    /*롤링배너 슬라이드*/
    var swiper = new Swiper('.section02 .swiper-container', {

        navigation: {
            nextEl: '.section02 .swiper-button-next',
            prevEl: '.section02 .swiper-button-prev',
        },
        loop: false,
    });
    /*올타임딜 슬라이드*/
    var swiper = new Swiper('.section03 .swiper-container', {
        slidesPerView: 3,
        spaceBetween: 30,
        navigation: {
            nextEl: '.section03 .swiper-button-next',
            prevEl: '.section03 .swiper-button-prev',
        },
        loop: false,
        pagination: {
            el: '.swiper-pagination',
        }
    });
    
    /*카테로리 탭메뉴*/
    $("#category_wrap").tabs();
    
    
});

