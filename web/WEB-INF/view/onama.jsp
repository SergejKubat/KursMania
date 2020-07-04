<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="sr">
    <head>
        <title>KursMania - O nama</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Elearn project">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap4/bootstrap.min.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link href="resources/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="resources/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
        <link rel="stylesheet" type="text/css" href="resources/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
        <link rel="stylesheet" type="text/css" href="resources/plugins/OwlCarousel2-2.2.1/animate.css">
        <link rel="stylesheet" type="text/css" href="${initParam.stiloviPutanja}about.css">
        <link rel="stylesheet" type="text/css" href="${initParam.stiloviPutanja}about_responsive.css">
        <link href="resources/plugins/video-js/video-js.css" rel="stylesheet" type="text/css">
        <link rel="icon" href="resources/img/website/favicon/favicon.ico">
        <style>
            .dropdown-submenu {
                position: relative;
            }

            .dropdown-submenu .dropdown-menu {
                top: 0;
                left: 100%;
                margin-top: -1px;
            }

            ::-webkit-scrollbar {
                width: 10px;
            }

            ::-webkit-scrollbar-thumb {
                background: #ff8a00; 
            }


            ::-webkit-scrollbar-thumb:hover {
                background: #ff6600; 
            }
        </style>
    </head>

    <jsp:include page="includes/header.jsp" />

    <div class="home">
        <div class="home_background parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/about.jpg" data-speed="0.8"></div>
        <div class="home_container">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="home_content text-center">
                            <div class="home_title">O nama</div>
                            <div class="breadcrumbs">
                                <ul>
                                    <li><a href="/">Pocetna</a></li>
                                    <li>O nama</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="about">
        <div class="container">
            <div class="row about_row row-lg-eq-height">
                <div class="col-lg-6">
                    <div class="about_content">
                        <div class="about_title">Nasi ciljevi</div>
                        <div class="about_text">
                            <p>Suspendisse tincidunt magna eget massa hendrerit efficitur. Ut euismod pellentesque imperdiet. Cras laoreet gravida lectus, at viverra lorem venenatis in. Aenean id varius quam. Nullam bibendum interdum dui, ac tempor lorem convallis ut. Maecenas rutrum viverra sapien sed fermentum. Morbi tempor odio eget lacus tempus pulvinar. Praesent vel nisl fermentum, gravida augue. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer id convallis libero, sed blandit nibh. Nam ultricies tristique nibh, consequat ornare nibh. Quisque vitae odio ligula.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="about_image"><img src="resources/img/website/about_1.jpg" alt="https://unsplash.com/@jtylernix"></div>
                </div>
            </div>
            <div class="row about_row row-lg-eq-height">
                <div class="col-lg-6 order-lg-1 order-2">
                    <div class="about_image"><img src="resources/img/website/about_1.jpg" alt=""></div>
                </div>
                <div class="col-lg-6 order-lg-2 order-1">
                    <div class="about_content">
                        <div class="about_title">Vizija</div>
                        <div class="about_text">
                            <p>Suspendisse tincidunt magna eget massa hendrerit efficitur. Ut euismod pellentesque imperdiet. Cras laoreet gravida lectus, at viverra lorem venenatis in. Aenean id varius quam. Nullam bibendum interdum dui, ac tempor lorem convallis ut. Maecenas rutrum viverra sapien sed fermentum. Morbi tempor odio eget lacus tempus pulvinar. Praesent vel nisl fermentum, gravida augue. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer id convallis libero, sed blandit nibh. Nam ultricies tristique nibh.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="milestones">
        <div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/milestones.jpg" data-speed="0.8"></div>
        <div class="container">
            <div class="row milestones_container">

                <div class="col-lg-3 milestone_col">
                    <div class="milestone text-center">
                        <div class="milestone_icon"><img src="resources/img/website//milestone_1.svg" alt=""></div>
                        <div class="milestone_counter" data-end-value="${brojKurseva}">0</div>
                        <div class="milestone_text">Kursevi</div>
                    </div>
                </div>

                <div class="col-lg-3 milestone_col">
                    <div class="milestone text-center">
                        <div class="milestone_icon"><img src="resources/img/website/milestone_2.svg" alt=""></div>
                        <div class="milestone_counter" data-end-value="${brojStudenata}">0</div>
                        <div class="milestone_text">Studenti</div>
                    </div>
                </div>

                <div class="col-lg-3 milestone_col">
                    <div class="milestone text-center">
                        <div class="milestone_icon"><img src="resources/img/website/milestone_3.svg" alt=""></div>
                        <div class="milestone_counter" data-end-value="${brojInstruktora}">0</div>
                        <div class="milestone_text">Instruktori</div>
                    </div>
                </div>

                <div class="col-lg-3 milestone_col">
                    <div class="milestone text-center">
                        <div class="milestone_icon"><img src="resources/img/website/milestone_4.svg" alt=""></div>
                        <div class="milestone_counter" data-end-value="${brojJezika}">0</div>
                        <div class="milestone_text">Jezici</div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="teachers">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="teachers_title text-center">Upoznajte instruktore</div>
                </div>
            </div>
            <div class="row teachers_row">

                <c:forEach var="instruktor" items="${instruktori}">
                    <div class="col-lg-4 col-md-6">
                        <div class="teacher">
                            <div class="teacher_image"><img src="${instruktor.korisnikAvatar}" alt="${instruktor.korisnikIme} ${instruktor.korisnikIme}"></div>
                            <div class="teacher_body text-center">
                                <div class="teacher_title"><a href="instruktor?id=${instruktor.korisnikId}">${instruktor.korisnikIme} ${instruktor.korisnikPrezime}</a></div>
                                <div class="teacher_subtitle">${instruktor.korisnikTitula}</div>
                                <div class="teacher_social">
                                    <ul>
                                        <li><a href="#"><i class="fa fa-linkedin-square"></i></a></li>
                                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                        <li><a href="#"><i class="fa fa-facebook"></i></i></a></li>
                                        <li><a href="#"><i class="fa fa-youtube-play"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
            <div class="row">
                <div class="col text-center">
                    <div class="button teachers_button"><a href="instruktori">Svi instruktori<div class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></div></a></div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="includes/footer.jsp" />

    <script src="resources/js/jquery-3.2.1.min.js"></script>
    <script src="resources/css/bootstrap4/popper.js"></script>
    <script src="resources/css/bootstrap4/bootstrap.min.js"></script>
    <script src="resources/plugins/greensock/TweenMax.min.js"></script>
    <script src="resources/plugins/greensock/TimelineMax.min.js"></script>
    <script src="resources/plugins/scrollmagic/ScrollMagic.min.js"></script>
    <script src="resources/plugins/greensock/animation.gsap.min.js"></script>
    <script src="resources/plugins/greensock/ScrollToPlugin.min.js"></script>
    <script src="resources/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
    <script src="resources/plugins/easing/easing.js"></script>
    <script src="resources/plugins/video-js/video.min.js"></script>
    <script src="resources/plugins/video-js/Youtube.min.js"></script>
    <script src="resources/plugins/parallax-js-master/parallax.min.js"></script>
    <script src="resources/js/custom.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyCIwF204lFZg1y4kPSIhKaHEXMLYxxuMhA"></script>
</body>
</html>