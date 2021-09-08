<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>올디프 파트너스</title>
    <!--css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/reset.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/partners_index.css">
    <!--script-->
    <script src="${pageContext.request.contextPath }/js/jquery/jquery.min.js"></script>
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/partners/partners_index.js"></script>
</head>

<body>
    <header class="clearfix">
        <div class="inner">
            <h1 class="partners_logo">
                <a href="#">
                </a>
            </h1>
            <nav>
                <ul class="top_menu01 clearfix">
                    <li><a href="#">프로모션</a></li>
                    <li><a href="#">공지사항</a></li>
                    <li><a href="#">교육자료</a></li>
                </ul>
                <ul class="top_menu02 clearfix">
                    <li><a href="./login">로그인</a></li>
                    <li><a href="#">입점신청</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <div class="swiper-container main_baner">
        <div class="swiper-wrapper">
            <div class="swiper-slide">Slide 1</div>
            <div class="swiper-slide">Slide 2</div>
            <div class="swiper-slide">Slide 3</div>
        </div>
        <div class="inner">
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
        </div>
    </div>
    <div class="cont_wrap">
        <div class="inner">
            <div class="cont_menu clearfix">
                <a href="#">입점 신청</a>
                <a href="#">입점 제안서</a>
                <a href="#">입점 가이드</a>
            </div>
            <div class="cont_box01"></div>
            <div class="cont_box02"></div>
            <div class="cont_box03 clearfix">
                <p class="cont_title">교육자료</p>
                <div class="swiper-container">
                    <div class="swiper-wrapper">
                        <div class="cont_list swiper-slide">
                            <a href="#"></a>
                        </div>
                        <div class="cont_list swiper-slide">
                            <a href="#"></a>
                        </div>
                        <div class="cont_list swiper-slide">
                            <a href="#"></a>
                        </div>
                    </div>
                </div>
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
            <div class="cont_box04">
                <p class="cont_title">올디프 MD가 알려주는 가이드 영상</p>
                <div class="swiper-container">
                    <div class="swiper-wrapper">
                        <div class="cont_list swiper-slide clearfix">
                            <div class="cont_info_box"></div>
                            <div class="cont_video_box">
                                <video src="" controls autoplay></video>
                                <iframe src=""></iframe>
                            </div>
                        </div>
                        <div class="cont_list swiper-slide clearfix">
                            <div class="cont_info_box"></div>
                            <div class="cont_video_box">

                            </div>
                        </div>
                        <div class="cont_list swiper-slide clearfix">
                            <div class="cont_info_box"></div>
                            <div class="cont_video_box">
                
                            </div>
                        </div>
                    </div>
                </div>
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
        </div>
    </div>
    <footer>
        <div class="inner">
            <div class="footer_wrap"></div>
        </div>
    </footer>
</body>
</html>