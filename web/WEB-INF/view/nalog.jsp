<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="sr">
    <head>
        <title>KursMania - ${korisnik.korisnikIme} ${korisnik.korisnikPrezime}</title>
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

            label {
                cursor: pointer;
            }

            #avatar {
                opacity: 0;
                position: absolute;
                z-index: -1;
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
                            <div class="home_title">${korisnik.korisnikIme} ${korisnik.korisnikPrezime}</div>
                            <div class="breadcrumbs">
                                <ul>
                                    <li><a href="#">Pregled naloga</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="m-5">

        <div class="w3-content w3-margin-top" style="max-width:1400px;">

            <div class="w3-row-padding">

                <div class="w3-third">

                    <div class="w3-white w3-text-grey w3-card-4">
                        <div class="w3-display-container">
                            <img src="${korisnik.korisnikAvatar}" style="width:100%" alt="Avatar">
                            <div class="w3-display-bottomleft w3-container w3-text-black">
                                <h2>${korisnik.korisnikIme} ${korisnik.korisnikPrezime}</h2>
                            </div>
                        </div>
                        <div class="text-center">
                            <br>
                            <label for="avatar" style="font-size: 20px"><i class="fa fa-picture-o"></i> Izaberi sliku</label>
                            <br>
                            <form action="azuriranjeSlike" method="POST" enctype="multipart/form-data">
                                <input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg" value="Promeni sliku">
                                <input type="submit" value="Promeni sliku">
                            </form>
                        </div>
                        <div class="w3-container mt-4">
                            <div class="row">
                                <div class="col-6">
                                    <p><i class="fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal"></i>${korisnik.korisnikTitula}</p>
                                </div>
                                <div class="col-6">
                                    <a class="float_right" href="izmenaNaloga"><i class="fa fa-pencil"></i> Izmena</a></p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6">
                                    <p><i class="fa fa-home fa-fw w3-margin-right w3-large w3-text-teal"></i>${korisnik.korisnikMesto}</p>
                                </div>
                                <div class="col-6">
                                    <a class="float_right" href="izmenaNaloga"><i class="fa fa-pencil"></i> Izmena</a></p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6">
                                    <p><i class="fa fa-map-marker fa-fw w3-margin-right w3-large w3-text-teal"></i>${korisnik.korisnikAdresa}</p>
                                </div>
                                <div class="col-6">
                                    <a class="float_right" href="izmenaNaloga"><i class="fa fa-pencil"></i> Izmena</a></p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-9">
                                    <p><i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal"></i>${korisnik.korisnikEmail}</p>
                                </div>
                                <div class="col-3">
                                    <a class="float_right" href="izmenaNaloga"><i class="fa fa-pencil"></i> Izmena</a></p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-6">
                                    <p><i class="fa fa-phone fa-fw w3-margin-right w3-large w3-text-teal"></i>${korisnik.korisnikBrojTelefona}</p>
                                </div>
                                <div class="col-6">
                                    <a class="float_right" href="izmenaNaloga"><i class="fa fa-pencil"></i> Izmena</a></p>
                                </div>
                            </div>              
                            <hr>
                        </div>
                    </div><br>

                </div>

                <div class="w3-twothird">

                    <div class="w3-container w3-card w3-white w3-margin-bottom">
                        <div class="row">
                            <div class="col-6">
                                <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-list-alt fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Opis</h2>
                            </div>
                            <div class="col-6">
                                <a class="float_right mt-4" href="izmenaNaloga"><i class="fa fa-pencil"></i> Izmena</a></p>
                            </div>
                        </div> 
                        <div class="w3-container">
                            <h5 class="w3-opacity"><b>${korisnik.korisnikTitula}</b></h5>
                            <p>${korisnik.korisnikOpis}</p>
                            <hr>
                        </div>
                    </div>

                    <c:if test="${korisnik.rolaId.rolaId == 2}">
                        <div class="w3-container w3-card w3-white w3-margin-bottom">
                            <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-book fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Moji kursevi</h2>
                            <c:forEach var="kurs" items="${mojiKursevi}">
                                <div class="w3-container">
                                    <h5 class="w3-opacity"><b><a href="kurs?id=${kurs.kursId}">${kurs.kursIme}</a></b></h5>
                                    <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>${kurs.kursDatumObjavljivanja}</h6>
                                    <p>${kurs.kursOpis}</p>
                                    <hr>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>

                    <div class="w3-container w3-card w3-white w3-margin-bottom">
                        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-book fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Kupljeni kursevi</h2>
                        <c:forEach var="kurs" items="${kursevi}">
                            <div class="w3-container">
                                <h5 class="w3-opacity"><b><a href="kurs?id=${kurs.kursId}">${kurs.kursIme}</a></b></h5>
                                <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>${kurs.kursDatumObjavljivanja}</h6>
                                <p>${kurs.kursOpis}</p>
                                <hr>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="w3-container w3-card w3-white w3-margin-bottom">
                        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-comments fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Komentari</h2>
                        <c:forEach var="komentar" items="${komentari}">
                            <div id="komentar-${komentar.komentarId}" class="row">
                                <div class="col-9">
                                    <div class="w3-container">
                                        <h5 class="w3-opacity"><b><a href="kurs?id=${komentar.kursId.kursId}">${komentar.kursId.kursIme}</a></b></h5>
                                        <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>${komentar.komentarDatum} ${komentar.komentarVreme}</h6>
                                        <p>${komentar.komentarSadrzaj}</p>
                                        <hr>
                                    </div>
                                </div>
                                <div class="col-3" style="text-align: center">
                                    <button id="btnKomentar-${komentar.komentarId}" class="btn btn-danger p-1" style="margin-top: 30px"><i class="fa fa-trash-o"></i> Obrisi komentar</button>
                                </div>
                            </div>
                        </c:forEach> 
                    </div>

                    <div class="w3-container w3-card w3-white w3-margin-bottom">
                        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-star fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Ocene</h2>
                        <c:forEach var="ocena" items="${ocene}">
                            <div id="ocena-${ocena.ocenaId}" class="row">
                                <div class="col-9">
                                    <div class="w3-container">
                                        <h5 class="w3-opacity">
                                            <a href="kurs?id=${ocena.kursId.kursId}">${ocena.kursId.kursIme}</a> -
                                            <c:forEach var = "i" begin = "0" end = "5">
                                                <c:if test="${ocena.ocenaVrednost > i}">
                                                    <span class="fa fa-star" style="color: #ff8a00"></span>
                                                </c:if>
                                                <c:if test="${ocena.ocenaVrednost < i}">
                                                    <span class="fa fa-star"></span>
                                                </c:if>
                                            </c:forEach>
                                            ${ocena.ocenaVrednost}
                                        </h5>
                                        <hr>
                                    </div>
                                </div>
                                <div class="col-3">
                                    <button id="btnOcena-${ocena.ocenaId}" class="btn btn-danger p-1" style="margin-top: 10px"><i class="fa fa-trash-o"></i> Obrisi ocenu</button>
                                </div>
                            </div>
                        </c:forEach> 
                    </div>

                </div>

            </div>

        </div>
    </div>

    <script>
        <c:forEach var="komentar" items="${komentari}">
        var btnKomentar${komentar.komentarId} = document.querySelector('#btnKomentar-${komentar.komentarId}');
        btnKomentar${komentar.komentarId}.addEventListener('click', function (e) {
            id = e.target.id.split('-')[1];
            var komentar = document.querySelector('#komentar-' + id);
            komentar.remove();
            brisanje('komentar', id);
        });
        </c:forEach>
        <c:forEach var="ocena" items="${ocene}">
        var btnOcena${ocena.ocenaId} = document.querySelector('#btnOcena-${ocena.ocenaId}');
        btnOcena${ocena.ocenaId}.addEventListener('click', function (e) {
            id = e.target.id.split('-')[1];
            var ocena = document.querySelector('#ocena-' + id);
            ocena.remove();
            brisanje('ocena', id);
        });
        </c:forEach>

        function posaljiZahtev(tip, id) {
            var xhr = new XMLHttpRequest();
            var url;
            if (tip === 'komentar') {
                url = 'obrisiKomentar?id=' + id;
            }
            if (tip === 'ocena') {
                url = 'obrisiOcenu?id=' + id;
            }
            xhr.open('DELETE', url);
            xhr.send();
        }
    </script>

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