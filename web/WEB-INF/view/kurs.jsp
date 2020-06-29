<style>
    .checked {
        color: #ff8a00;
    }
</style>
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
                        <c:if test="${korisnik != null}">
                            <h5 class="text-center"><a href="kupovina?id=${kurs.kursId}&kupon=1">Iskoristi kupon</a></h5>
                        </c:if>
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
                    <c:forEach var="tag" items="${tagovi}">
                        <a href="pretraga?q=${tag.tagId.tagIme}" class="badge badge-success" style="font-size: 14px">${tag.tagId.tagIme}</a>
                    </c:forEach>
                    <br><br>
                    <h5>Ocene:</h5>
                    <c:forEach var = "i" begin = "0" end = "5">
                        <c:if test="${kursZvezdice > i}">
                            <span class="fa fa-star checked" style="font-size: 20px"></span>
                        </c:if>
                        <c:if test="${kursZvezdice < i}">
                            <span class="fa fa-star" style="font-size: 20px"></span>
                        </c:if>
                    </c:forEach> ${kursProsecnaOcena} (${kursBrojOcena} ocena)
                    <br><br>

                    <c:if test="${korisnik != null}">

                        <h5>Oceni kurs:</h5>
                        <c:if test="${ocenaVrednost == -1}">

                            <div id="ocena">
                                <c:forEach var = "i" begin = "1" end = "5">
                                    <span id="o-${i}" class="fa fa-star" style="font-size: 20px"></span>
                                </c:forEach>
                                <button id="ocenaBtn" class="btn btn-primary ml-1">Oceni</button>
                                <p id="ocena-potvrda" style="display: none">Ocena dodata.</p>
                            </div>
                            <br>

                        </c:if>

                        <c:if test="${ocenaVrednost != -1}">

                            <div id="ocena">
                                <c:forEach var = "i" begin = "1" end = "5">
                                    <c:if test="${ocenaVrednost >= i}">
                                        <span id="o-${i}" class="fa fa-star checked" style="font-size: 20px"></span>
                                    </c:if>
                                    <c:if test="${ocenaVrednost < i}">
                                        <span id="o-${i}" class="fa fa-star" style="font-size: 20px"></span>
                                    </c:if>
                                </c:forEach>
                                <button id="ocenaBtn" class="btn btn-primary ml-1">Promeni ocenu</button>
                                <p id="ocena-potvrda" style="display: none">Ocena promenjena.</p>
                            </div>
                            <br>

                        </c:if>

                    </c:if>

                    <c:if test="${kupljen == null}">
                        <button id="kupovina" class="btn btn-lg btn-success" data-toggle="modal" data-target="#exampleModalCenter"><i class="fa fa-shopping-cart"></i> Kupi odmah</button>
                        <c:if test="${korisnik == null}">
                            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle">Niste prijavljeni!</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div style="width: 80%;">
                                                <form action="prijava" method="POST" id="prijava" class="contact_form">
                                                    <div><input type="email" name="email" class="contact_input" placeholder="Email" required="required"></div>
                                                    <div><input type="password" name="lozinka" class="contact_input" placeholder="Lozinka" required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}"></div>
                                                    <div><input type="hidden" name="kursId" value="${kurs.kursId}"></div>
                                                    <button class="contact_button"><span>Prijava</span><span class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:if>
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
                    <div id="komentari">
                        <c:forEach var="komentar" items="${komentari}">
                            <p>${komentar.komentarSadrzaj}</p>
                            <small class="text-muted">Autor ${komentar.korisnikId.korisnikIme} ${komentar.korisnikId.korisnikPrezime} datum ${komentar.komentarDatum} ${komentar.komentarVreme}</small>
                            <hr>
                        </c:forEach>
                    </div>
                    <c:if test="${korisnik != null}">
                        <button id="komentarisi" class="btn btn-success">Postavi komentar</button>
                    </c:if>
                </div>
            </div>

            <c:if test="${korisnik != null}">
                <div id="komentarBody" style="width: 80%; margin-bottom: 30px; display: none;">
                    <form id="komentarForma" class="contact_form">
                        <div><textarea id="komentarTekst" class="contact_input contact_textarea" placeholder="Napisite komentar"></textarea></div>
                        <button id="posaljiKomentar" class="contact_button"><span>Posalji komentar</span><span class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button>
                    </form>
                </div>
            </c:if>

        </div>

    </div>

</div>
<c:if test="${korisnik != null}">
    <script>

        var komentarisi = document.querySelector('#komentarisi');
        var komentarBody = document.querySelector('#komentarBody');
        komentarisi.addEventListener('click', function () {
            komentarBody.style.display = 'block';
            var komentarForma = document.querySelector('#komentarForma');
            var komentarTekst = document.querySelector('#komentarTekst');
            komentarForma.addEventListener('submit', function (e) {
                e.preventDefault();
                var tekst = komentarTekst.value;
                postaviKomentar(tekst);
                komentarTekst.value = '';
            });
        });

        function postaviKomentar(komentar) {
            var xhr = new XMLHttpRequest();
            var url = 'dodavanjeKomentara';
            var parametri = 'komentar=' + komentar + '&kursId=' + ${kurs.kursId};

            xhr.open('POST', url);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var komentari = document.querySelector('#komentari');
                    var datum = new Date();
                    var mesec = datum.getMonth() + 1;
                    var mesec = mesec > 9 ? mesec : '0' + mesec;
                    var dan = datum.getDate() > 9 ? datum.getDate() : '0' + datum.getDate();
                    var godina = datum.getFullYear();
                    var sat = datum.getHours() > 9 ? datum.getHours() : '0' + datum.getHours();
                    var minut = datum.getMinutes() > 9 ? datum.getMinutes() : '0' + datum.getMinutes();
                    komentari.innerHTML += '<p>' + komentar + '</p><small class="text-muted">Autor ${korisnik.korisnikIme} ${korisnik.korisnikPrezime} Datum ' + mesec + '-' + dan + '-' + godina + ' ' + sat + ':' + minut + '</small><hr>';
                }
            }

            xhr.send(parametri);
        }

        <c:if test="${kupljen == null}">
        var kupovinaBtn = document.querySelector('#kupovina');

        kupovinaBtn.addEventListener('click', function () {
            window.location.href = '';
            window.location.replace("kupovina?id=${kurs.kursId}");
        });
        </c:if>

        var ocena = document.querySelector('#ocena');
        var ocenaBtn = document.querySelector('#ocenaBtn');
        var ocenaPotvrda = document.querySelector('#ocena-potvrda');
        var ocenaVrednost;

        ocena.addEventListener('click', function (e) {
            var o = e.target;
            var oVrednost = o.id.split('-')[1];
            if (oVrednost !== null && oVrednost > 0 && oVrednost < 6) {
                ocenaVrednost = oVrednost;
                for (var i = 1; i <= 5; i++) {
                    document.querySelector('#o-' + i).classList.remove('checked');
                }
                for (var i = 1; i <= oVrednost; i++) {
                    document.querySelector('#o-' + i).classList.add('checked');
                }
            }
        });

        ocenaBtn.addEventListener('click', function () {
            if (ocenaVrednost) {
        <c:if test="${ocenaVrednost == -1}">
                dodajOcenu(ocenaVrednost);
        </c:if>
        <c:if test="${ocenaVrednost != -1}">
                promeniOcenu(ocenaVrednost);
        </c:if>
            }
        });

        function dodajOcenu(ocena) {
            var xhr = new XMLHttpRequest();
            var url = 'dodavanjeOcene';
            var parametri = 'ocena=' + ocena + '&kursId=' + ${kurs.kursId};

            xhr.open('POST', url);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    ocenaPotvrda.style.display = 'block';
                }
            }

            xhr.send(parametri);
        }

        function promeniOcenu(ocena) {
            var xhr = new XMLHttpRequest();
            var url = 'promenaOcene';
            var parametri = 'ocenaId=' + ${ocenaId} + '&ocena=' + ocena + '&kursId=' + ${kurs.kursId};

            xhr.open('PUT', url);

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    ocenaPotvrda.style.display = 'block';
                }
            }

            xhr.send(parametri);
        }
    </script>
</c:if>