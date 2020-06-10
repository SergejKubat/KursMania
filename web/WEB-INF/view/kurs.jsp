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

    <div class="row">

        <div class="col-lg-3">
            <div class="teachers">
                <div>
                    <div class="teacher">
                        <div class="teacher_image"><img src="${kurs.getKorisnikId().korisnikAvatar}" alt="${kurs.getKorisnikId().korisnikIme} ${kurs.getKorisnikId().korisnikPrezime}"></div>
                        <div class="teacher_body text-center">
                            <div class="teacher_title"><a href="instruktor?id=${kurs.getKorisnikId().korisnikId}">${kurs.getKorisnikId().korisnikIme} ${kurs.getKorisnikId().korisnikPrezime}</a></div>
                            <div class="teacher_subtitle">
                                <p>${kurs.getKorisnikId().korisnikOpis}</p>
                                <p>Broj kurseva: ${kurs.getKorisnikId().getKursCollection().size()}</p>
                                <p>Broj studenata: ${brojStudenata}</p>
                                <p>Prosecna ocena:</p>
                                <c:forEach var = "i" begin = "0" end = "5">
                                    <c:if test="${zvezdice > i}">
                                        <span class="fa fa-star" style="color: #ff8a00"></span>
                                    </c:if>
                                    <c:if test="${zvezdice < i}">
                                        <span class="fa fa-star"></span>
                                    </c:if>
                                </c:forEach>
                                ${prosecnaOcena} (${brojOcena} ocena)
                            </div>
                            <div class="teacher_social">
                                <ul>
                                    <li><a href="#"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div>
                        <h4>Detalji o kursu:</h4>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><i class="fa fa-tasks"></i> Broj sekcija: ${kurs.getSekcijaCollection().size()}</li>
                            <li class="list-group-item"><i class="fa fa-puzzle-piece"></i> Bloj lekcija: ${brojLekcija}</li>
                            <li class="list-group-item"><i class="fa fa-video-camera"></i> Duzina celog kursa: ${duzinaKursa}</li>
                            <li class="list-group-item"><i class="fa fa-universal-access"></i> Dozivotni pristup</i></li>
                            <li class="list-group-item"><i class="fa fa-mobile"></i> Pristup preko mobilnog</li>
                        </ul>
                        <br><br>
                        <h5 class="text-center"><a href="kupovina?id=${kurs.kursId}">Iskoristi kupon</a></h5>
                        <br>
                        <hr>
                        <br>
                        <h5 class="text-center"><a href="#"><i class="fa fa-share"></i> Podeli</a></h5>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-9">

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
                            <p class="float-right">${kursBrojStudenata} studenata</p>
                        </div>
                    </div>
                    <br>
                    <h4>${kurs.kursCena}$</h4>
                    <br>
                    <h5><a href="/kategorija?id=${kurs.kategorijaId.kategorijaId}">${kurs.kategorijaId.kategorijaNaziv}</a></h5>
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
                    <c:forEach var="tag" items="${tagovi}">
                        <a href="pretraga?q=${tag.tagId.tagIme}" class="badge badge-success" style="font-size: 14px">${tag.tagId.tagIme}</a>
                    </c:forEach>
                    <br><br>
                    <c:forEach var = "i" begin = "0" end = "5">
                        <c:if test="${kursZvezdice > i}">
                            <span class="fa fa-star" style="color: #ff8a00"></span>
                        </c:if>
                        <c:if test="${kursZvezdice < i}">
                            <span class="fa fa-star"></span>
                        </c:if>
                    </c:forEach> ${kursProsecnaOcena} (${kursBrojOcena} ocena)
                    <br><br>
                    <a href="kupovina?id=${kurs.kursId}" class="btn btn-lg btn-success"><i class="fa fa-shopping-cart"></i> Kupi odmah</a>
                    <br><br>
                    <h4>Sta treba da znate pre ovog kursa?</h4>
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
                    <h4>Sta treba da naucite na ovom kursu?</h4>
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
                        <c:forEach var="sekcija" items="${sekcije}" varStatus="loop">
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
                                                            <button class="btn btn-primary btn-primary video-open" data-video="https://www.youtube.com/watch?v=zhYdRCgCw7o" data-toggle="modal" data-target="#videoModal"><i class="fa fa-video-camera"></i> Prikazi snimak</button>
                                                            <div class="modal fade" id="videoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-body">
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                            <video controls width="100%">
                                                                                <source src="" type="video/mp4">
                                                                            </video>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
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
            <h2 class="text-center">Preporuceni kursevi:</h2>
            <div class="row courses_row">
                <c:forEach var="preporuceniKurs" items="${preporuceniKursevi}">
                    <div class="col-5">
                        <div class="course">
                            <div class="course_image"><img src="${preporuceniKurs.kursSlika}" alt="${preporuceniKurs.kursIme}"></div>
                            <div class="course_body">
                                <div class="course_header d-flex flex-row align-items-center justify-content-start">
                                    <div class="course_tag"><a href="kategorija?id=${preporuceniKurs.kategorijaId.kategorijaId}">${preporuceniKurs.kategorijaId.kategorijaNaziv}</a></div>
                                    <div class="course_price ml-auto">Cena: <span>${preporuceniKurs.kursCena}$</span></div>
                                </div>
                                <div class="course_title"><h3><a href="kurs?id=${preporuceniKurs.kursId}">${preporuceniKurs.kursIme}</a></h3></div>
                                <div class="course_text">${preporuceniKurs.kursOpis}</div>
                                <div class="course_footer d-flex align-items-center justify-content-start">
                                    <div class="course_author_image"><img src="${preporuceniKurs.korisnikId.korisnikAvatar}"></div>
                                    <div class="course_author_name">Autor <a href="#">${preporuceniKurs.korisnikId.korisnikIme} ${preporuceniKurs.korisnikId.korisnikPrezime}</a></div>
                                    <div class="course_sales ml-auto"><span>${kurs.getEvidencijaCollection().size()}</span> Studenata</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="card card-outline-secondary my-4">
                <div class="card-header">
                    Komentari (${kursBrojKomentara})
                </div>
                <div class="card-body">
                    <c:forEach var="komentar" items="${komentari}">
                        <p>${komentar.komentarSadrzaj}</p>
                        <small class="text-muted">Autor ${komentar.korisnikId.korisnikIme} ${komentar.korisnikId.korisnikPrezime} on ${komentar.komentarDatum} ${komentar.komentarVreme}</small>
                        <hr>
                    </c:forEach>
                    <a href="#" class="btn btn-success">Postavi komentar</a>
                </div>
            </div>

        </div>

    </div>

</div>