<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="sr">
    <head>
        <title>KursMania</title>
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
        <link rel="stylesheet" type="text/css" href="resources/css/main_styles.css">
        <link rel="stylesheet" type="text/css" href="resources/css/responsive.css">
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

    <jsp:include page="WEB-INF/view/includes/header.jsp" />

    <div class="home">
        <div class="home_slider_container">

            <div class="owl-carousel owl-theme home_slider">

                <div class="owl-item">
                    <div class="home_slider_background" style="background-image:url(resources/img/website/index.jpg)"></div>
                    <div class="home_container">
                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <div class="home_content text-center">
                                        <div class="home_logo"><img src="resources/img/website/home_logo.png" alt=""></div>
                                        <div class="home_text">
                                            <div class="home_title">Online kursevi</div>
                                            <div class="home_subtitle">Maecenas rutrum viverra sapien sed fermentum. Morbi tempor odio eget lacus tempus pulvinar. Praesent vel nisl fermentum, gravida augue ut, fermentum ipsum.</div>
                                        </div>
                                        <div class="home_buttons">
                                            <div class="button home_button"><a href="onama">Detalji<div class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></div></a></div>
                                            <div class="button home_button"><a href="kursevi">Svi kursevi<div class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></div></a></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="owl-item">
                    <div class="home_slider_background" style="background-image:url(resources/img/website/index.jpg)"></div>
                    <div class="home_container">
                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <div class="home_content text-center">
                                        <div class="home_logo"><img src="resources/img/website/home_logo.png" alt=""></div>
                                        <div class="home_text">
                                            <div class="home_title">Online kursevi</div>
                                            <div class="home_subtitle">Maecenas rutrum viverra sapien sed fermentum. Morbi tempor odio eget lacus tempus pulvinar. Praesent vel nisl fermentum, gravida augue ut, fermentum ipsum.</div>
                                        </div>
                                        <div class="home_buttons">
                                            <div class="button home_button"><a href="/onama">Detalji<div class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></div></a></div>
                                            <div class="button home_button"><a href="/kursevi">Svi kursevi<div class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></div></a></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="owl-item">
                    <div class="home_slider_background" style="background-image:url(resources/img/website/index.jpg)"></div>
                    <div class="home_container">
                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <div class="home_content text-center">
                                        <div class="home_logo"><img src="resources/img/website/home_logo.png" alt=""></div>
                                        <div class="home_text">
                                            <div class="home_title">Online kursevi</div>
                                            <div class="home_subtitle">Maecenas rutrum viverra sapien sed fermentum. Morbi tempor odio eget lacus tempus pulvinar. Praesent vel nisl fermentum, gravida augue ut, fermentum ipsum.</div>
                                        </div>
                                        <div class="home_buttons">
                                            <div class="button home_button"><a href="/onama">Detalji<div class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></div></a></div>
                                            <div class="button home_button"><a href="/kursevi">Svi kursevi<div class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></div></a></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="featured">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="home_slider_nav_container d-flex flex-row align-items-start justify-content-between">
                        <div class="home_slider_nav home_slider_prev trans_200"><i class="fa fa-angle-left" aria-hidden="true"></i></div>
                        <div class="home_slider_nav home_slider_next trans_200"><i class="fa fa-angle-right" aria-hidden="true"></i></div>
                    </div>
                    <div class="featured_container">
                        <div class="row">
                            <div class="col-lg-6 featured_col">
                                <div class="featured_content">
                                    <div class="featured_header d-flex flex-row align-items-center justify-content-start">
                                        <div class="featured_tag"><a href="#">Popularni</a></div>
                                        <div class="featured_price ml-auto">Cena: <span>$35</span></div>
                                    </div>
                                    <div class="featured_title"><h3><a href="courses.html">JavaScript ES6/7/8</a></h3></div>
                                    <div class="featured_text">Maecenas rutrum viverra sapien sed fermentum. Morbi tempor odio eget lacus tempus pulvinar. Donec vehicula efficitur nibh, in pretium nulla interdum non.</div>
                                    <div class="featured_footer d-flex align-items-center justify-content-start">
                                        <div class="featured_author_image"><img src="resources/img/website/featured_author.jpg" alt=""></div>
                                        <div class="featured_author_name">Autor: <a href="#">James S. Morrison</a></div>
                                        <div class="featured_sales ml-auto"><span>352</span> ucenika</div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 featured_col">
                                <div class="featured_background" style="background-image:url(resources/img/website/featured.jpg)"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="courses">
        <div class="container">
            <div class="row">
                <div class="col-lg-10 offset-lg-1">
                    <div class="section_title text-center"><h2>Izaberite kurs</h2></div>
                    <div class="section_subtitle">Suspendisse tincidunt magna eget massa hendrerit efficitur. Ut euismod pellentesque imperdiet. Cras laoreet gravida lectus, at viverra lorem venenatis in. Aenean id varius quam. Nullam bibendum interdum dui, ac tempor lorem convallis ut</div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="course_search">
                        <datalist id="kategorije">
                            <c:forEach var="kategorija" items="${kategorije}">
                                <option value="${kategorija.kategorijaNaziv}">
                                </c:forEach>
                        </datalist>
                        <datalist id="preporuke">
                        </datalist>
                        <form action="pretraga" method="GET" class="course_search_form d-flex flex-md-row flex-column align-items-start justify-content-between">
                            <div><input name="q" id="ime" type="text" list="preporuke" class="course_input" placeholder="Kljucna rec" required="required"></div>
                            <div><input name="oblast" type="text" list="kategorije" class="course_input" placeholder="Oblast" required="required"></div>
                            <button class="course_button"><span>Pronadji kurs</span><span class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">

                    <div class="courses_slider_container">
                        <div class="owl-carousel owl-theme courses_slider">

                            <div class="owl-item">
                                <div class="course">
                                    <div class="course_image"><img src="resources/img/website/course_1.jpg" alt=""></div>
                                    <div class="course_body">
                                        <div class="course_header d-flex flex-row align-items-center justify-content-start">
                                            <div class="course_tag"><a href="#">Popularno</a></div>
                                            <div class="course_price ml-auto">Cena: <span>$35</span></div>
                                        </div>
                                        <div class="course_title"><h3><a href="courses.html">JavaScript ES6/7/8</a></h3></div>
                                        <div class="course_text">Maecenas rutrum viverra sapien sed ferm entum. Morbi tempor odio eget lacus tempus pulvinar.</div>
                                        <div class="course_footer d-flex align-items-center justify-content-start">
                                            <div class="course_author_image"><img src="resources/img/website/featured_author.jpg" alt="https://unsplash.com/@anthonytran"></div>
                                            <div class="course_author_name">Autor <a href="#">James S. Morrison</a></div>
                                            <div class="course_sales ml-auto"><span>352</span> ucenika</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="owl-item">
                                <div class="course">
                                    <div class="course_image"><img src="resources/img/website/course_2.jpg" alt=""></div>
                                    <div class="course_body">
                                        <div class="course_header d-flex flex-row align-items-center justify-content-start">
                                            <div class="course_tag"><a href="#">Novo</a></div>
                                            <div class="course_price ml-auto">Cena: <span>$35</span></div>
                                        </div>
                                        <div class="course_title"><h3><a href="courses.html">Social Media Course</a></h3></div>
                                        <div class="course_text">Maecenas rutrum viverra sapien sed ferm entum. Morbi tempor odio eget lacus tempus pulvinar.</div>
                                        <div class="course_footer d-flex align-items-center justify-content-start">
                                            <div class="course_author_image"><img src="resources/img/website/course_author_2.jpg" alt=""></div>
                                            <div class="course_author_name">By <a href="#">Mark Smith</a></div>
                                            <div class="course_sales ml-auto"><span>352</span> ucenika</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="owl-item">
                                <div class="course">
                                    <div class="course_image"><img src="resources/img/website/course_3.jpg" alt="https://unsplash.com/@annademy"></div>
                                    <div class="course_body">
                                        <div class="course_header d-flex flex-row align-items-center justify-content-start">
                                            <div class="course_tag"><a href="#">Popularno</a></div>
                                            <div class="course_price ml-auto">Price: <span>$35</span></div>
                                        </div>
                                        <div class="course_title"><h3><a href="courses.html">Marketing Course</a></h3></div>
                                        <div class="course_text">Maecenas rutrum viverra sapien sed ferm entum. Morbi tempor odio eget lacus tempus pulvinar.</div>
                                        <div class="course_footer d-flex align-items-center justify-content-start">
                                            <div class="course_author_image"><img src="resources/img/website/course_author_3.jpg" alt=""></div>
                                            <div class="course_author_name">By <a href="#">Julia Williams</a></div>
                                            <div class="course_sales ml-auto"><span>352</span> ucenika</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="owl-item">
                                <div class="course">
                                    <div class="course_image"><img src="resources/img/website/course_3.jpg" alt="https://unsplash.com/@annademy"></div>
                                    <div class="course_body">
                                        <div class="course_header d-flex flex-row align-items-center justify-content-start">
                                            <div class="course_tag"><a href="#">Popularno</a></div>
                                            <div class="course_price ml-auto">Price: <span>$35</span></div>
                                        </div>
                                        <div class="course_title"><h3><a href="courses.html">Novi kurs</a></h3></div>
                                        <div class="course_text">Maecenas rutrum viverra sapien sed ferm entum. Morbi tempor odio eget lacus tempus pulvinar.</div>
                                        <div class="course_footer d-flex align-items-center justify-content-start">
                                            <div class="course_author_image"><img src="resources/img/website/course_author_3.jpg" alt=""></div>
                                            <div class="course_author_name">By <a href="#">Julia Williams</a></div>
                                            <div class="course_sales ml-auto"><span>352</span> ucenika</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="courses_slider_nav courses_slider_prev trans_200"><i class="fa fa-angle-left" aria-hidden="true"></i></div>
                        <div class="courses_slider_nav courses_slider_next trans_200"><i class="fa fa-angle-right" aria-hidden="true"></i></div>

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
                        <div class="milestone_icon"><img src="resources/img/website/milestone_1.svg" alt=""></div>
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

    <div class="grouped_title" style="text-align: center; margin: 20px 0;">Zasto?</div>

    <div class="video">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="video_container_outer">
                        <div class="video_container">
                            <video id="vid1" class="video-js vjs-default-skin" controls data-setup='{ "poster": "resources/img/website/video.jpg", "techOrder": ["youtube"], "sources": [{ "type": "video/youtube", "src": "https://youtu.be/5_MRXyYjHDk"}], "youtube": { "iv_load_policy": 1 } }'>
                            </video>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="join">
        <div class="container">
            <div class="row">
                <div class="col-lg-10 offset-lg-1">
                    <div class="section_title text-center"><h2>Pridruzi nam se!</h2></div>
                    <div class="section_subtitle">Suspendisse tincidunt magna eget massa hendrerit efficitur. Ut euismod pellentesque imperdiet. Cras laoreet gravida lectus, at viverra lorem venenatis in. Aenean id varius quam. Nullam bibendum interdum dui, ac tempor lorem convallis ut</div>
                </div>
            </div>
        </div>
        <div class="button join_button"><a href="registracija">Registracija<div class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></div></a></div>
    </div>
    
    <jsp:include page="WEB-INF/view/includes/footer.jsp" />

    <script src="resources/js/preporuke.js"></script>
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
