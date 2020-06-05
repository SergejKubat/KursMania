<link href="resources/css/shop-item.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="resources/css/courses.css">
<link rel="stylesheet" type="text/css" href="resources/css/courses_responsive.css">
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
                                ${prosecnaOcena}
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
                        <h5 class="text-center"><a href="kupovina">Iskoristi kupon</a></h5>
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
                    <h3 class="card-title">${kurs.kursIme}</h3>
                    <h4>$24.99</h4>
                    <h5><a href="/kategorija?id=${kurs.kategorijaId.kategorijaId}">${kurs.kategorijaId.kategorijaNaziv}</a></h5>
                    <h5>Jezik: ${kurs.jezikId.jezikNaziv}</h5>
                    <h5>Datum objavljivanja: ${kurs.kursDatumObjavljivanja}</h5>
                    <h5>Poslednja promena: ${kurs.datumPoslednjePromene}</h5>
                    <p class="card-text">${kurs.kursOpis}</p>
                    <c:forEach var = "i" begin = "0" end = "5">
                        <c:if test="${kursZvezdice > i}">
                            <span class="fa fa-star" style="color: #ff8a00"></span>
                        </c:if>
                        <c:if test="${kursZvezdice < i}">
                            <span class="fa fa-star"></span>
                        </c:if>
                    </c:forEach> ${kursProsecnaOcena} (${kursBrojOcena} ocena)
                    <br><br>
                    <a href="/kupovina?id=${kurs.kursId}" class="btn btn-lg btn-success"><i class="fa fa-shopping-cart"></i> Kupi odmah</a>
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
                    <div class="row">
                        <div class="col">
                            <h4>Sadrzaj kursa:</h4>
                        </div>
                        <div class="col">
                            <p class="float-right">Broj lekcija: ${brojLekcija}</p>
                        </div>
                        <div class="col">
                            <p class="float-right">Duzina: ${duzinaKursa}</p>
                        </div>
                    </div>
                    <div id="sekcije">
                        <c:forEach var="sekcija" items="${sekcije}">
                            <div class="card">
                                <div class="card-header" id="headingOne">
                                    <div class="row">
                                        <div class="col">
                                            <h5 class="mb-0">
                                                <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne2" aria-expanded="true" aria-controls="collapseOne2">
                                                    ${sekcija.sekcijaNaslov}
                                                </button>
                                            </h5>
                                        </div>
                                        <div class="col">
                                            <p class="float-right">${sekcija.lekcijaCollection.size()}</p>
                                        </div>
                                    </div>
                                </div>

                                <div id="collapseOne2" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                                    <div class="card-body">

                                        <div class="row">
                                            <div class="col-4">
                                                <div class="list-group" id="list-tab" role="tablist">
                                                    <c:forEach var="lekcija" items="sekcija.lekcijaCollection">
                                                        <a class="list-group-item list-group-item-action active" id="list-${lekcija.lekcijaIme}-list" data-toggle="list" href="#list-${lekcija.lekcijaIme}" role="tab" aria-controls="home">${lekcija.lekcijaIme}</a>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <div class="col-8">
                                                <div class="tab-content" id="nav-tabContent">
                                                    <c:forEach var="lekcija" items="sekcija.lekcijaCollection">
                                                        <div class="tab-pane fade show active" id="list-${lekcija.lekcijaIme}" role="tabpanel" aria-labelledby="list-${lekcija.lekcijaIme}-list">
                                                            <p>${lekcija.lekcijaOpis}</p>
                                                            <br>
                                                            <a href="#" class="btn btn-primary btn-primary"><i class="fa fa-video-camera"></i> Prikazi snimak</a>
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
                <div class="col-lg-4 col-md-6">
                    <div class="course">
                        <div class="course_image"><img src="resources/img/website/course_1.jpg" alt=""></div>
                        <div class="course_body">
                            <div class="course_header d-flex flex-row align-items-center justify-content-start">
                                <div class="course_tag"><a href="#">Featured</a></div>
                                <div class="course_price ml-auto">Price: <span>$35</span></div>
                            </div>
                            <div class="course_title"><h3><a href="courses.html">Online Literature Course</a></h3></div>
                            <div class="course_text">Maecenas rutrum viverra sapien sed ferm entum. Morbi tempor odio eget lacus tempus pulvinar.</div>
                            <div class="course_footer d-flex align-items-center justify-content-start">
                                <div class="course_author_image"><img src="resources/img/website/featured_author.jpg" alt="https://unsplash.com/@anthonytran"></div>
                                <div class="course_author_name">By <a href="#">James S. Morrison</a></div>
                                <div class="course_sales ml-auto"><span>352</span> Sales</div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Course -->
                <div class="col-lg-4 col-md-6">
                    <div class="course">
                        <div class="course_image"><img src="resources/img/website/course_2.jpg" alt=""></div>
                        <div class="course_body">
                            <div class="course_header d-flex flex-row align-items-center justify-content-start">
                                <div class="course_tag"><a href="#">New</a></div>
                                <div class="course_price ml-auto">Price: <span>$35</span></div>
                            </div>
                            <div class="course_title"><h3><a href="courses.html">Social Media Course</a></h3></div>
                            <div class="course_text">Maecenas rutrum viverra sapien sed ferm entum. Morbi tempor odio eget lacus tempus pulvinar.</div>
                            <div class="course_footer d-flex align-items-center justify-content-start">
                                <div class="course_author_image"><img src="resources/img/website/course_author_2.jpg" alt="https://unsplash.com/@anthonytran"></div>
                                <div class="course_author_name">By <a href="#">Mark Smith</a></div>
                                <div class="course_sales ml-auto"><span>352</span> Sales</div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Course -->
                <div class="col-lg-4 col-md-6">
                    <div class="course">
                        <div class="course_image"><img src="resources/img/website/course_3.jpg" alt=""></div>
                        <div class="course_body">
                            <div class="course_header d-flex flex-row align-items-center justify-content-start">
                                <div class="course_tag"><a href="#">Featured</a></div>
                                <div class="course_price ml-auto">Price: <span>$35</span></div>
                            </div>
                            <div class="course_title"><h3><a href="courses.html">Online Marketing Course</a></h3></div>
                            <div class="course_text">Maecenas rutrum viverra sapien sed ferm entum. Morbi tempor odio eget lacus tempus pulvinar.</div>
                            <div class="course_footer d-flex align-items-center justify-content-start">
                                <div class="course_author_image"><img src="resources/img/website/course_author_3.jpg" alt="https://unsplash.com/@anthonytran"></div>
                                <div class="course_author_name">By <a href="#">Julia Williams</a></div>
                                <div class="course_sales ml-auto"><span>352</span> Sales</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card card-outline-secondary my-4">
                <div class="card-header">
                    Komentari
                </div>
                <div class="card-body">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>
                    <small class="text-muted">Posted by Anonymous on 3/1/17</small>
                    <hr>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>
                    <small class="text-muted">Posted by Anonymous on 3/1/17</small>
                    <hr>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>
                    <small class="text-muted">Posted by Anonymous on 3/1/17</small>
                    <hr>
                    <a href="#" class="btn btn-success">Leave a Review</a>
                </div>
            </div>

        </div>

    </div>

</div>