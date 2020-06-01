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
                        <div class="home_title">Instruktor</div>
                        <div class="breadcrumbs">
                            <ul>
                                <li><a href="/">Pocetna</a></li>
                                <li><a href="/instruktori">Instruktori</a></li>
                                <li>${instruktor.korisnikIme} ${instruktor.korisnikPrezime}</li>
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
            <div style="padding: 50px 50px;">
                <img src="${instruktor.korisnikAvatar}" style="border-radius: 100%; height: 150px; width: 150px" alt="${instruktor.korisnikIme} ${instruktor.korisnikPrezime}">
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><a href="#"><i class="fa fa-file-o"></i> Web sajt</a></li>
                <li class="list-group-item"><a href="#"><i class="fa fa-linkedin-square"></i> LinkedIn</a></li>
                <li class="list-group-item"><a href="#"><i class="fa fa-twitter"></i> Twitter</a></li>
                <li class="list-group-item"><a href="#"><i class="fa fa-facebook"></i> Facebook</a></li>
                <li class="list-group-item"><a href="#"><i class="fa fa-youtube-play"></i> Youtube</a></li>
            </ul>
        </div>

        <div class="col-lg-9">

            <div class="card mt-4" style="border: 0 !important">
                <div class="card-body">
                    <h5>INSTRUKTOR</h5>
                    <h1 class="card-title">${instruktor.korisnikIme} ${instruktor.korisnikPrezime} - ${instruktor.korisnikTitula}</h1>
                    <div class="row">
                        <div class="col">
                            <h4>Studenti:</h4>
                            <h4 style="margin: 0">3095</h4>
                        </div>
                        <div class="col">
                            <h4>Ocene:</h4>
                            <h4>1890</h4>
                        </div>
                    </div>
                    <div class="opis" style="margin-top: 20px;">
                        <h3>O meni</h3>
                        <p>${instruktor.korisnikOpis}</p>
                        <h3>Moji kursevi (${kursevi.size()})</h3>
                        <br>
                        <div class="row">
                            <c:forEach var="kurs" items="${kursevi}">
                                <div class="col">
                                    <div class="course">
                                        <div class="course_image"><img src="${kurs.kursSlika}" alt="${kurs.kursIme}"></div>
                                        <div class="course_body">
                                            <div class="course_header d-flex flex-row align-items-center justify-content-start">
                                                <div class="course_tag"><a href="kategorija?id=${kurs.kategorijaId.kategorijaId}">${kurs.kategorijaId.kategorijaNaziv}</a></div>
                                                <div class="course_price ml-auto">Cena: <span>${kurs.kursCena}$</span></div>
                                            </div>
                                            <div class="course_title"><h3><a href="/kurs?id='${kurs.kursId}'">${kurs.kursIme}</a></h3></div>
                                            <div class="course_text">${kurs.kursOpis}</div>
                                            <div class="course_footer d-flex align-items-center justify-content-start">
                                                <div class="course_author_image"><img src="resources/img/website/course_author_2.jpg" alt="https://unsplash.com/@anthonytran"></div>
                                                <div class="course_author_name">By <a href="instruktor?id=${kurs.korisnikId.korisnikId}">${kurs.korisnikId.korisnikIme} ${kurs.korisnikId.korisnikPrezime}</a></div>
                                                <div class="course_sales ml-auto"><span>352</span> Sales</div>
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

</div>