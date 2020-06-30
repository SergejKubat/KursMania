<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="sr">
    <head>
        <title>KursMania - Registracija</title>
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
                            <div class="home_title">Registracija</div>
                            <div class="breadcrumbs">
                                <ul>
                                    <li><a href="/">Pocetna</a></li>
                                    <li>Registracija</li>
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
                            <div class="col-lg-10 offset-lg-1">
                                <div class="section_title text-center"><h2>Pridruzite nam se!</h2></div>
                                <div class="section_subtitle">Donec blandit libero id lectus sagittis, sed tempus ipsum faucibus. Phasellus metus lorem, lobortis in eros et, bibendum laoreet ante. Integer vel purus ut libero tempus eleifend. Ut metus diam, semper ac finibus vel, varius molestie erat. Sed ac eros ipsum. Duis ut arcu a turpis lobortis congue. Nullam sollicitudin, sapien nec elementum pellentesque, ante lorem efficitur quam, vel faucibus elit nulla eu libero. Aenean rhoncus et nulla sit amet commodo. Curabitur id volutpat velit. Integer vehicula lectus a nulla ultrices fringilla. Duis erat tellus, interdum quis molestie ut, tempor eu leo. Nam nec vulputate erat, eget finibus dolor. Vivamus ut porta lacus. Fusce nec turpis vitae lectus hendrerit consequat. Praesent commodo massa ante, at cursus orci suscipit ac. Cras tristique sapien purus, vel luctus odio commodo eget.</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-6 map_col">
                    <div class="contact_form_container" style="width: 80%;">
                        <c:if test="${poruka != null}">
                            <div class="row mb-4">
                                <div class="col-lg-8 mx-auto text-center">
                                    <h1 class="display-5">${poruka}</h1>
                                </div>
                            </div> 
                        </c:if>
                        <form action="registracija" method="POST" id="registracija" class="contact_form">
                            <div>
                                <div class="row">
                                    <div class="col-lg-6 contact_name_col">
                                        <input type="text" name="ime" class="contact_input" value="${ime}" placeholder="Ime" required="required">
                                    </div>
                                    <div class="col-lg-6">
                                        <input type="text" name="prezime" class="contact_input" value="${prezime}" placeholder="Prezime" required="required">
                                    </div>
                                </div>
                            </div>
                            <div><input type="email" name="email" class="contact_input" value="${email}" placeholder="Email" required="required"></div>
                            <div><input type="tel" name="brt" class="contact_input" value="${brt}" placeholder="Broj telefona" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" required="required"></div>
                            <div><input type="text" name="mesto" class="contact_input" value="${mesto}" placeholder="Mesto" required="required"></div>
                            <div><input type="text" name="adresa" class="contact_input" value="${adresa}" placeholder="Adresa" required="required"></div>
                            <div><input type="password" name="lozinka" class="contact_input" placeholder="Lozinka" required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}"></div>
                            <button class="contact_button"><span>Registracija</span><span class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button>
                        </form>
                    </div>
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
