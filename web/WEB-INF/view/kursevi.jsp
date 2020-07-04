<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="sr">
    <head>
        <title>KursMania - Kursevi</title>
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
        <link rel="stylesheet" type="text/css" href="${initParam.stiloviPutanja}courses.css">
        <link rel="stylesheet" type="text/css" href="${initParam.stiloviPutanja}courses_responsive.css">
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
        <div class="home_background parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/courses.jpg" data-speed="0.8"></div>
        <div class="home_container">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="home_content text-center">
                            <div class="home_title">Kursevi</div>
                            <div class="breadcrumbs">
                                <ul>
                                    <li><a href="">Pocetna</a></li>
                                    <li>Kursevi</li>
                                </ul>
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
                    <div class="section_title text-center"><h2>Izaberite odgovorajuci kurs</h2></div>
                    <div class="section_subtitle">Suspendisse tincidunt magna eget massa hendrerit efficitur. Ut euismod pellentesque imperdiet. Cras laoreet gravida lectus, at viverra lorem venenatis in. Aenean id varius quam. Nullam bibendum interdum dui, ac tempor lorem convallis ut</div>
                </div>
            </div>

            <c:if test="${poruka != null}">
                <div class="row">
                    <div class="col-lg-10 offset-lg-1">
                        <div class="section_title text-center"><h2>${poruka}</h2></div>
                    </div>
                </div>
            </c:if>

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

            <c:if test="${istaknut != null}">
                <div class="row featured_row">
                    <div class="col-lg-6 featured_col">
                        <div class="featured_content">
                            <div class="featured_header d-flex flex-row align-items-center justify-content-start">
                                <div class="featured_tag"><a href="#">Istaknut</a></div>
                                <div class="course_tag"><a href="kategorija?id=${istaknut.kategorijaId.kategorijaId}">${istaknut.kategorijaId.kategorijaNaziv}</a></div>
                                <div class="featured_price ml-auto">Cena: <span>${istaknut.kursCena}$</span></div>
                            </div>
                            <div class="featured_title"><h3><a href="kurs?id=${istaknut.kursId}">${istaknut.kursIme}</a></h3></div>
                            <div class="featured_text">${istaknut.kursOpis}</div>
                            <div class="featured_footer d-flex align-items-center justify-content-start">
                                <div class="featured_author_image"><img src="${istaknut.korisnikId.korisnikAvatar}" alt="${istaknut.kursIme}"></div>
                                <div class="featured_author_name">Autor <a href="instruktor?id=${istaknut.korisnikId.korisnikId}">${istaknut.korisnikId.korisnikIme} ${istaknut.korisnikId.korisnikPrezime}</a></div>
                                <div class="featured_sales ml-auto"><span>${istaknut.getEvidencijaCollection().size()}</span> Studenata</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 featured_col">
                        <div class="featured_background" style="background-image:url(${istaknut.kursSlika})"></div>
                    </div>
                </div>
            </c:if>
            <div class="row courses_row">
                <c:forEach var="kurs" items="${kursevi}">
                    <div class="col-lg-4 col-md-6">
                        <div class="course">
                            <div class="course_image"><img src="${kurs.kursSlika}" alt="${kurs.kursIme}"></div>
                            <div class="course_body">
                                <div class="course_header d-flex flex-row align-items-center justify-content-start">
                                    <div class="course_tag"><a href="kategorija?id=${kurs.kategorijaId.kategorijaId}">${kurs.kategorijaId.kategorijaNaziv}</a></div>
                                    <div class="course_price ml-auto">Cena: <span>${kurs.kursCena}$</span></div>
                                </div>
                                <div class="course_title"><h3><a href="kurs?id=${kurs.kursId}">${kurs.kursIme}</a></h3></div>
                                <div class="course_text">${kurs.kursOpis}</div>
                                <div class="course_footer d-flex align-items-center justify-content-start">
                                    <div class="course_author_image"><img src="${kurs.korisnikId.korisnikAvatar}" alt="${kurs.korisnikId.korisnikIme} ${kurs.korisnikId.korisnikPrezime}"></div>
                                    <div class="course_author_name">Autor <a href="instruktor?id=${kurs.korisnikId.korisnikId}">${kurs.korisnikId.korisnikIme} ${kurs.korisnikId.korisnikPrezime}</a></div>
                                    <div class="course_sales ml-auto"><span>${kurs.getEvidencijaCollection().size()}</span> Studenata</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>         
            </div>

            <div class="row">
                <div class="col">
                    <div class="courses_paginations">
                        <ul>
                            <c:forEach var="i" begin="1" end="${brojStranica}">
                                <li <c:if test="${i == index}">class="active"</c:if>><a href="kursevi?page=${i}">0${i}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="includes/footer.jsp" />

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
