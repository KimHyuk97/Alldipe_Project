$(function () {
    var swiper1 = new Swiper('.swiper-container.main_baner', {
        navigation: {
            nextEl: '.main_baner .swiper-button-next',
            prevEl: '.main_baner .swiper-button-prev',
        },
        loop: true,
    });

    var swiper2 = new Swiper('.cont_box03 .swiper-container', {
        slidesPerView: 3,
        spaceBetween: 45,
        navigation: {
            nextEl: '.cont_box03 .swiper-button-next',
            prevEl: '.cont_box03 .swiper-button-prev',
        },
        loop: true,

    });
    
     var swiper3 = new Swiper('.cont_box04 .swiper-container', {
        navigation: {
            nextEl: '.cont_box04 .swiper-button-next',
            prevEl: '.cont_box04 .swiper-button-prev',
        },
        loop: true,

    });
});
