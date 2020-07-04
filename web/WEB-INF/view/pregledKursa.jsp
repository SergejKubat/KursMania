<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="sr">
    <head>
        <title>KursMania - ${kurs.kursIme} - Pregled</title>
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
        <link rel="stylesheet" type="text/css" href="${initParam.stiloviPutanja}shop-item.css">
        <link rel="stylesheet" type="text/css" href="${initParam.stiloviPutanja}courses.css">
        <link rel="stylesheet" type="text/css" href="${initParam.stiloviPutanja}courses_responsive.css">
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

            .checked {
                color: #ff8a00;
            }

            .tag:hover {
                background-color: #007E33;
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
                            <div class="home_title">Kurs</div>
                            <div class="breadcrumbs">
                                <ul>
                                    <li><a href="/">Pocetna</a></li>
                                    <li><a href="kursevi">Kursevi</a></li>
                                    <li>${kurs.kursIme}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">

        <div class="card mt-4">
            <div class="video">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="video_container_outer">
                                <div class="video_container">
                                    <video id="vid1" class="video-js vjs-default-skin" controls data-setup='{ "poster": "${kurs.kursSlika}", "techOrder": ["youtube"], "sources": [{ "type": "video/youtube", "src": "${kurs.kursVideo}"}], "youtube": { "iv_load_policy": 1 } }'>
                                    </video>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col">
                        <h3 class="card-title">${kurs.kursIme}</h3>
                    </div>
                    <div class="col">
                        <p class="float-right">${kurs.evidencijaCollection.size()} studenata</p>
                    </div>
                </div>
                <br>
                <h5>Cena: <span style="font-size: 24px">${kurs.kursCena}$</span></h5>
                <br>
                <h5>Kategorija: <a href="kategorija?id=${kurs.kategorijaId.kategorijaId}">${kurs.kategorijaId.kategorijaNaziv}</a></h5>
                <br>
                <h5>Jezik: ${kurs.jezikId.jezikNaziv}</h5>
                <br>
                <h5>Datum objavljivanja: ${kurs.kursDatumObjavljivanja}</h5>
                <br>
                <h5>Poslednja promena: ${kurs.datumPoslednjePromene}</h5>
                <br>
                <p class="card-text">${kurs.kursOpis}</p>
                <br>
                <h5>Tagovi:</h5>
                <div id="tagovi">
                    <c:forEach var="tag" items="${kurs.kursTagCollection}">
                        <div class="badge badge-success tag" style="font-size: 14px">${tag.tagId.tagIme} <i id="tag-${tag.ktId}" class="fa fa-times-circle"></i></div>
                        </c:forEach>
                </div>
                <br><br>
                <div class="card">

                    <div class="card-header">
                        <h3>Pregled ocena:</h3>
                    </div>

                    <br>

                    <div class="card-body">

                        <div class="row">
                            <div class="col-3">
                                <div style="margin-top: 40px">
                                    <h1 class="text-center">${kursProsecnaOcena}</h1>
                                    <div class="text-center">
                                        <c:forEach var = "i" begin = "0" end = "5">
                                            <c:if test="${kursZvezdice > i}">
                                                <span class="fa fa-star checked" style="font-size: 20px"></span>
                                            </c:if>
                                            <c:if test="${kursZvezdice < i}">
                                                <span class="fa fa-star" style="font-size: 20px"></span>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                    <p class="text-center">${kursBrojOcena} ocena</p>
                                </div>
                            </div>
                            <div class="col-6">                    
                                <br>

                                <div class="progress">
                                    <div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: ${procenatPetica}%" aria-valuenow="${procenatPetica}" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>

                                <br>

                                <div class="progress">
                                    <div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: ${procenatCetvorki}%" aria-valuenow="${procenatCetvorki}" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>

                                <br>

                                <div class="progress">
                                    <div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: ${procenatTrojki}%" aria-valuenow="${procenatTrojki}" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>

                                <br>

                                <div class="progress">
                                    <div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: ${procenatDvojki}%" aria-valuenow="${procenatDvojki}" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>

                                <br>

                                <div class="progress">
                                    <div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: ${procenatJedinica}%" aria-valuenow="${procenatJedinica}" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>

                                <br>
                            </div>
                            <div class="col-3">

                                <br>

                                <div class="ml-3" style="position: relative; bottom: 5px;">
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star checked" style="font-size: 20px; margin-right: 5px"></span> ${procenatPetica}%
                                </div>

                                <div class="ml-3" style="position: relative; top: 10px;">
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star" style="font-size: 20px; margin-right: 5px"></span> ${procenatCetvorki}%
                                </div>

                                <div class="ml-3" style="position: relative; top: 20px;">
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star" style="font-size: 20px"></span>
                                    <span class="fa fa-star" style="font-size: 20px; margin-right: 5px"></span> ${procenatTrojki}%
                                </div>

                                <div class="ml-3" style="position: relative; top: 35px;">
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star" style="font-size: 20px"></span>
                                    <span class="fa fa-star" style="font-size: 20px"></span>
                                    <span class="fa fa-star" style="font-size: 20px; margin-right: 5px"></span> ${procenatDvojki}%
                                </div>

                                <div class="ml-3" style="position: relative; top: 50px;">
                                    <span class="fa fa-star checked" style="font-size: 20px"></span>
                                    <span class="fa fa-star" style="font-size: 20px"></span>
                                    <span class="fa fa-star" style="font-size: 20px"></span>
                                    <span class="fa fa-star" style="font-size: 20px"></span>
                                    <span class="fa fa-star" style="font-size: 20px; margin-right: 5px"></span> ${procenatJedinica}%
                                </div>

                                <br>

                            </div>

                        </div>

                    </div>

                    <br>

                </div>

                <br>
                <div id="zahtevi">
                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h5 class="mb-0">
                                <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne1" aria-expanded="true" aria-controls="collapseOne1">
                                    Zahtevi
                                </button>
                            </h5>
                        </div>

                        <div id="collapseOne1" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                            <div class="card-body">
                                ${kurs.kursZahtevi}
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <br>
                <div class="row">
                    <div class="col-4">
                        <h4>Sadrzaj kursa:</h4>
                    </div>
                    <div class="col-4">
                        <p class="float-right">Broj lekcija: ${brojLekcija}</p>
                    </div>
                    <div class="col-4">
                        <p class="float-right">Duzina: ${duzinaKursa}</p>
                    </div>
                </div>
                <div id="sekcije">
                    <c:forEach var="sekcija" items="${kurs.sekcijaCollection}" varStatus="loop">
                        <div class="card">
                            <div class="card-header" id="heading${sekcija.sekcijaId}">
                                <div class="row">
                                    <div class="col">
                                        <h5 class="mb-0">
                                            <button class="btn btn-link" data-toggle="collapse" data-target="#collapse${sekcija.sekcijaId}" aria-expanded="true" aria-controls="collapse${sekcija.sekcijaId}">
                                                ${sekcija.sekcijaNaslov}
                                            </button>
                                        </h5>
                                    </div>
                                    <div class="col">
                                        <p class="float-right">${sekcija.lekcijaCollection.size()} lekcije</p>
                                    </div>
                                </div>
                            </div>

                            <div id="collapse${sekcija.sekcijaId}" class="collapse <c:if test="${loop.index == 0}">show</c:if>" aria-labelledby="heading${sekcija.sekcijaId}" data-parent="#accordion">
                                    <div class="card-body">

                                        <div class="row">

                                            <div class="col-4">
                                                <div class="list-group" id="list-tab" role="tablist">
                                                <c:forEach var="lekcija" items="${sekcija.lekcijaCollection}" varStatus="loop">
                                                    <a class="list-group-item list-group-item-action <c:if test="${loop.index == 0}">active</c:if>" id="list-${lekcija.lekcijaId}-list" data-toggle="list" href="#list-${lekcija.lekcijaId}" role="tab" aria-controls="${lekcija.lekcijaId}">${lekcija.lekcijaIme}</a>
                                                </c:forEach>
                                            </div>
                                        </div>

                                        <div class="col-8">
                                            <div class="tab-content" id="nav-tabContent">
                                                <c:forEach var="lekcija" items="${sekcija.lekcijaCollection}" varStatus="loop">
                                                    <div class="tab-pane fade show <c:if test="${loop.index == 0}">active</c:if>" id="list-${lekcija.lekcijaId}" role="tabpanel" aria-labelledby="list-${lekcija.lekcijaId}-list">
                                                        <p>${lekcija.lekcijaOpis}</p>
                                                        <br>
                                                        <a href="lekcija?id=${lekcija.lekcijaId}"><button class="btn btn-primary btn-primary"><i class="fa fa-video-camera"></i> Idi na lekciju</button></a>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <br>

        <div class="card card-outline-secondary my-4">
            <div class="card-header">
                Komentari (${kurs.komentarCollection.size()})
            </div>
            <div class="card-body">
                <div id="komentari">
                    <c:forEach var="komentar" items="${kurs.komentarCollection}">
                        <div id="komentar-${komentar.komentarId}" class="row">
                            <div class="col-9">
                                <p>${komentar.komentarSadrzaj}</p>
                                <small class="text-muted">Autor ${komentar.korisnikId.korisnikIme} ${komentar.korisnikId.korisnikPrezime} datum ${komentar.komentarDatum} ${komentar.komentarVreme}</small>
                            </div>
                            <div class="col-3">
                                <button id="btnKomentar-${komentar.komentarId}" class="btn btn-danger p-1 float-right" style="margin-top: 10px"><i class="fa fa-trash-o"></i> Obrisi komentar</button>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="card card-outline-secondary my-4">
            <div class="card-header">
                Studenti (${kurs.evidencijaCollection.size()})
            </div>
            <div class="card-body">
                <div class="list-group">
                    <c:forEach var="evidencija" items="${kurs.evidencijaCollection}">
                        <div class="list-group-item list-group-item-action flex-column align-items-start">
                            <div class="row">
                                <div class="col-1">
                                    <img src="${evidencija.korisnikId.korisnikAvatar}" alt="Korisnik" style="width: 32px; height: 32px; border-radius: 100%; display: inline;" />
                                </div>
                                <div class="col-11">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h5 class="mb-1" style="position: relative; right: 50px;">${evidencija.korisnikId.korisnikIme} ${evidencija.korisnikId.korisnikPrezime}</h5>
                                        <small>${evidencija.evidencijaDatum}</small>
                                    </div>
                                </div>
                            </div>
                            <p class="mb-1">${evidencija.korisnikId.korisnikOpis}</p>
                            <small>${evidencija.korisnikId.korisnikTitula}</small>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>

    <jsp:include page="includes/footer.jsp" />

    <script>
        <c:forEach var="komentar" items="${kurs.komentarCollection}">
        var btnKomentar${komentar.komentarId} = document.querySelector('#btnKomentar-${komentar.komentarId}');
        btnKomentar${komentar.komentarId}.addEventListener('click', function (e) {
            id = e.target.id.split('-')[1];
            var komentar = document.querySelector('#komentar-' + id);
            komentar.remove();
            brisanje('komentar', id);
        });
        </c:forEach>

        <c:forEach var="tag" items="${kurs.kursTagCollection}">
        var btnTag${tag.ktId} = document.querySelector('#tag-${tag.ktId}');
        btnTag${tag.ktId}.addEventListener('click', function (e) {
            id = e.target.id.split('-')[1];
            e.target.parentElement.remove();
            brisanje('tag', id);
        });
        </c:forEach>

        function brisanje(tip, id) {
            var xhr = new XMLHttpRequest();
            var url;
            if (tip === 'komentar') {
                url = 'obrisiKomentar?id=' + id;
            }
            if (tip === 'tag') {
                url = 'obrisiTag?id=' + id;
            }
            xhr.open('DELETE', url);
            xhr.send();
        }
    </script>

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