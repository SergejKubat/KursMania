<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="sr">
    <head>
        <title>KursMania - Potvrda</title>
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
        <link rel="stylesheet" type="text/css" href="${initParam.stiloviPutanja}contact.css">
        <link rel="stylesheet" type="text/css" href="${initParam.stiloviPutanja}contact_responsive.css">
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
        <div class="home_background parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/contact.jpg" data-speed="0.8"></div>
        <div class="home_container">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="home_content text-center">
                            <div class="home_title">Kupovina</div>
                            <div class="breadcrumbs">
                                <ul>
                                    <li><a href="">Pocetna</a></li>
                                    <li>Kupovina</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="contact">
        <div class="container-fluid">
            <div class="row row-xl-eq-height" style="margin-bottom: 36px;">
                <div class="col-xl-6">
                    <div class="contact_content">
                        <div class="row">
                            <div class="col-xl-6">
                                <div class="contact_about">
                                    <div class="logo_container">
                                        <a href="#">
                                            <div class="logo_content d-flex flex-row align-items-end justify-content-start">
                                                <div class="logo_text">${kurs.kursIme}</div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="contact_about_text">
                                        <p>${kurs.kursOpis}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="contact_info_container">
                                    <div class="contact_info_main_title">O kursu</div>
                                    <div class="contact_info">
                                        <div class="contact_info_item">
                                            <div class="contact_info_title">Broj studenata:</div>
                                            <div class="contact_info_line">${brojStudenata}</div>
                                        </div>
                                        <div class="contact_info_item">
                                            <div class="contact_info_title">Broj sekcija:</div>
                                            <div class="contact_info_line">${brojSekcija}</div>
                                        </div>
                                        <div class="contact_info_item">
                                            <div class="contact_info_title">Broj lekcija:</div>
                                            <div class="contact_info_line">${brojLekcija}</div>
                                        </div>
                                        <div class="contact_info_item">
                                            <div class="contact_info_title">Autor</div>
                                            <div class="contact_info_line"><a href="instruktor?id=${kurs.korisnikId.korisnikId}">${kurs.korisnikId.korisnikIme} ${kurs.korisnikId.korisnikPrezime}</a></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-6 map_col">

                    <div class="card mt-5 mr-5">
                        <div class="card-header">
                            <h1>Cestitamo, uspesno ste kupili kurs!</h1>
                            <h3>Cena koju ste platili je: ${kurs.kursCena}$ - ${kupon.kuponPopust}% = <b>${krajnjaCena}$</b></h3>
                        </div>
                        <div class="card-body">
                            <a href="kurs?id=${kurs.kursId}"><button class="contact_button"><span>Idite na kurs</span><span class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button></a>
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



