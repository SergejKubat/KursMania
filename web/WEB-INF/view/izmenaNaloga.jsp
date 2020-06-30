<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="sr">
    <head>
        <title>KursMania - Izmena naloga</title>
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
                            <div class="home_title">Izmena naloga</div>
                            <div class="breadcrumbs">
                                <ul>
                                    <li><a href="/">Pocetna</a></li>
                                    <li>Izmena informacija o nalogu</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="contact" style="width: 1000px; text-align: center; margin: 0 auto;">
        <div class="container-fluid">
            <div class="row row-xl-eq-height" style="margin-bottom: 36px">

                <div class="contact_form_container" style="width: 80%;">
                    <c:if test="${poruka == null}">
                        <div class="col-lg-10 offset-lg-1">
                            <div class="section_title text-center"><h2>Izmena</h2></div>
                            <div>
                                <p style="text-align: center">Izmenite informacije o vasem nalogu.</p>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${poruka != null}">
                        <div class="col-lg-10 offset-lg-1">
                            <div class="section_title text-center"><h2>Greska</h2></div>
                            <div>
                                <p style="text-align: center">${poruka}</p>
                            </div>
                        </div>
                    </c:if>
                    <br>
                    <form action="izmenaNaloga" method="POST" class="contact_form">
                        <div>
                            <div class="row">
                                <div class="col-lg-6 contact_name_col">
                                    <input type="text" name="ime" class="contact_input" value="${korisnik.korisnikIme}" placeholder="Ime" required="required">
                                </div>
                                <div class="col-lg-6">
                                    <input type="text" name="prezime" class="contact_input" value="${korisnik.korisnikPrezime}" placeholder="Prezime" required="required">
                                </div>
                            </div>
                        </div>
                        <div><input type="email" name="email" class="contact_input" value="${korisnik.korisnikEmail}" placeholder="Email"></div>
                        <div><input type="tel" name="brt" class="contact_input" value="${korisnik.korisnikBrojTelefona}" placeholder="Broj telefona" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"></div>
                        <div><input type="text" name="mesto" class="contact_input" value="${korisnik.korisnikMesto}" placeholder="Mesto"></div>
                        <div><input type="text" name="adresa" class="contact_input" value="${korisnik.korisnikAdresa}" placeholder="Adresa"></div>
                        <div><input type="text" name="titula" class="contact_input" value="${korisnik.korisnikTitula}" placeholder="Titula"></div>
                        <div><textarea name="opis" class="contact_input contact_textarea" value="${korisnik.korisnikOpis}" placeholder="Dodajte opis"></textarea></div>
                        <button class="contact_button"><span>Izmena informacija</span><span class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button>
                    </form>
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
