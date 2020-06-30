<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="sr">
    <head>
        <title>KursMania - Kupovina - ${kurs.kursIme}</title>
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
        <link rel="stylesheet" type="text/css" href="${initParam.stiloviPutanja}payment.css">
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

                    <div class="container py-5">
                        <div class="row mb-4">
                            <div class="col-lg-8 mx-auto text-center">
                                <h1 class="display-4">Kupovina kursa</h1>
                            </div>
                        </div> 
                        <div class="row">
                            <div class="col-lg-6 mx-auto">
                                <c:if test="${poruka != null}">
                                    <h2>${poruka}</h2>
                                </c:if>
                                <div class="card ">
                                    <div class="card-header">
                                        <div class="bg-white shadow-sm pt-4 pl-2 pr-2 pb-2">
                                            <ul role="tablist" class="nav bg-light nav-pills rounded nav-fill mb-3">
                                                <li class="nav-item"> <a data-toggle="pill" href="#credit-card" class="nav-link active "> <i class="fa fa-credit-card-alt"></i> Kreditna kartica </a> </li>
                                            </ul>
                                        </div>
                                        <div class="tab-content">
                                            <div id="credit-card" class="tab-pane fade show active pt-3">
                                                <form method="POST" action="kupovina?id=${kurs.kursId}" role="form">
                                                    <div class="form-group"> <label for="username">
                                                            <h6>Vlasnik kartice</h6>
                                                        </label> <input type="text" name="ime" placeholder="Ime" required pattern="[a-zA-Z0-9]{3,32}" class="form-control"> </div>
                                                    <div class="form-group"> <label for="cardNumber">
                                                            <h6>Broj kartice</h6>
                                                        </label>
                                                        <div class="input-group"> <input type="text" name="brojKartice" placeholder="Ispravan broj kartice" class="form-control " required pattern="^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$">
                                                            <div class="input-group-append"> <span class="input-group-text text-muted"> <i class="fa fa-cc-visa"></i> <i class="fa fa-cc-mastercard"></i> <i class="fa fa-cc-amex"></i> </span> </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-sm-8">
                                                            <div class="form-group"> <label><span class="hidden-xs">
                                                                        <h6>Datum isteka</h6>
                                                                    </span></label>
                                                                <div class="input-group"> <input type="number" placeholder="MM" name="mesec" class="form-control" min="1" max="12" required> <input type="number" placeholder="GG" name="godina" class="form-control" min="1900" required> </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="form-group mb-4"> <label data-toggle="tooltip" title="Trocifreni CV kod sa vase kartice">
                                                                    <h6>CVV <i class="fa fa-question-circle d-inline"></i></h6>
                                                                </label> <input type="text" name="cvv" required pattern="[0-9]{3,4}" class="form-control"> </div>
                                                        </div>
                                                    </div>
                                                    <input type="hidden" name="kursId" value="${kursId}">
                                                    <div class="card-footer"> <input type="submit" class="subscribe btn btn-primary btn-block shadow-sm" value="Potvrdi">
                                                        </form>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
            <script>
                $(function () {
                    $('[data-toggle="tooltip"]').tooltip()
                })
            </script>
</body>
</html>

