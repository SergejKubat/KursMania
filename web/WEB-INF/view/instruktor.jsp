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
                                <li><a href="instruktori">Instruktori</a></li>
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
                <p>Prosecna ocena:</p>
                <c:forEach var = "i" begin = "0" end = "5">
                    <c:if test="${zvezdice > i}">
                        <span class="fa fa-star" style="color: #ff8a00"></span>
                    </c:if>
                    <c:if test="${zvezdice < i}">
                        <span class="fa fa-star"></span>
                    </c:if>
                </c:forEach>
                ${prosecnaOcena} <br> (${brojOcena} ocena)
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
                    <br>
                    <div class="row">
                        <div class="col">
                            <h4 style="text-align: center;"><i class="fa fa-graduation-cap"></i> Studenti:</h4>
                            <h4 style="text-align: center;">${brojStudenata}</h4>
                        </div>
                        <div class="col">
                            <h4 style="text-align: center;"><i class="fa fa-star"></i> Ocene:</h4>
                            <h4 style="text-align: center;">${brojOcena}</h4>
                        </div>
                    </div>
                    <div class="opis" style="margin-top: 20px;">
                        <h3>O meni</h3>
                        <br>
                        <p>${instruktor.korisnikOpis}</p>
                        <br>
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
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>