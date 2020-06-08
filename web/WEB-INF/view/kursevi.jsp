<link rel="stylesheet" type="text/css" href="resources/css/courses.css">
<link rel="stylesheet" type="text/css" href="resources/css/courses_responsive.css">
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
                                <li><a href="/">Pocetna</a></li>
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

        <div class="row">
            <div class="col">
                <div class="course_search">
                    <form action="#" class="course_search_form d-flex flex-md-row flex-column align-items-start justify-content-between">
                        <div><input type="text" class="course_input" placeholder="Course" required="required"></div>
                        <div><input type="text" class="course_input" placeholder="Level" required="required"></div>
                        <button class="course_button"><span>search course</span><span class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button>
                    </form>
                </div>
            </div>
        </div>

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
                            <div class="course_title"><h3><a href="/kurs?id=${kurs.kursId}">${kurs.kursIme}</a></h3></div>
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
                        <c:forEach var="i" begin="0" end="${brojStranica}">
                            <li <c:if test="${i + 1 == 1}">class="active"</c:if>><a href="kursevi?page=${i + 1}">0${i + 1}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
