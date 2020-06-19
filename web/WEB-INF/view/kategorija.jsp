<div class="home">
    <div class="home_background parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/courses.jpg" data-speed="0.8"></div>
    <div class="home_container">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="home_content text-center">
                        <div class="home_title">${kat.kategorijaNaziv}</div>
                        <div class="breadcrumbs">
                            <ul>
                                <li><a href="/">Pocetna</a></li>
                                <li>${kat.kategorijaNaziv}</li>
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
                <div class="section_title text-center"><h2>${kat.kategorijaNaziv}</h2></div>
                <c:if test="${poruka == null}">
                    <div class="section_subtitle">Prikazani su svi kursevi sa kategorijom ${kat.kategorijaNaziv}.</div>
                </c:if>
                    <c:if test="${poruka != null}">
                    <div class="section_subtitle">${poruka}</div>
                </c:if>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <div class="course_search">
                    <datalist id="kategorije">
                        <c:forEach var="kategorija" items="${kategorije}">
                            <option value="${kategorija.kategorijaNaziv}">
                            </c:forEach>
                    </datalist>
                    <form action="pretraga" method="GET" class="course_search_form d-flex flex-md-row flex-column align-items-start justify-content-between">
                        <div><input name="q" type="text" class="course_input" placeholder="Kljucna rec" required="required"></div>
                        <div><input name="oblast" type="text" list="kategorije" class="course_input" placeholder="Oblast" required="required"></div>
                        <button class="course_button"><span>Pronadji kurs</span><span class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button>
                    </form>
                </div>
            </div>
        </div>

        <c:if test="${poruka == null}">
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
                            <div class="featured_author_image"><img src="${istaknut.korisnikId.korisnikAvatar}" alt="${istaknut.korisnikId.korisnikIme} ${istaknut.korisnikId.korisnikPrezime}"></div>
                            <div class="featured_author_name">By <a href="instruktor?id=${istaknut.korisnikId.korisnikId}">${istaknut.korisnikId.korisnikIme} ${istaknut.korisnikId.korisnikPrezime}</a></div>
                            <div class="featured_sales ml-auto"><span>${istaknut.evidencijaCollection.size()}</span> Studenata</div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 featured_col">
                    <div class="featured_background" style="background-image:url(${istaknut.kursSlika})"></div>
                </div>
            </div>
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
                                <div class="course_title"><h3><a href="/kurs?id='${kurs.kursId}'">${kurs.kursIme}</a></h3></div>
                                <div class="course_text">${kurs.kursOpis}</div>
                                <div class="course_footer d-flex align-items-center justify-content-start">
                                    <div class="course_author_image"><img src="${kurs.korisnikId.korisnikAvatar}" alt="${kurs.korisnikId.korisnikIme} ${kurs.korisnikId.korisnikPrezime}"></div>
                                    <div class="course_author_name">By <a href="instruktor?id=${kurs.korisnikId.korisnikId}">${kurs.korisnikId.korisnikIme} ${kurs.korisnikId.korisnikPrezime}</a></div>
                                    <div class="course_sales ml-auto"><span>${kurs.evidencijaCollection.size()}</span> Studenata</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </c:if>

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
