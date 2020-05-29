<link rel="stylesheet" type="text/css" href="resources/css/courses.css">
<link rel="stylesheet" type="text/css" href="resources/css/courses_responsive.css">
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
                <div class="section_subtitle">Prikazani su svi kursevi sa kategorijom ${kat.kategorijaNaziv}.</div>
            </div>
        </div>

        <!-- Course Search -->
        <div class="row">
            <div class="col">
                <div class="course_search">
                    <form action="/pretraga" method=""GET class="course_search_form d-flex flex-md-row flex-column align-items-start justify-content-between">
                        <div><input type="text" name="q" class="course_input" placeholder="Napisite nesto..." required="required"></div>
                        <button class="course_button"><span>Pretraga</span><span class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Featured Course -->
        <div class="row featured_row">
            <div class="col-lg-6 featured_col">
                <div class="featured_content">
                    <div class="featured_header d-flex flex-row align-items-center justify-content-start">
                        <div class="featured_tag"><a href="#">Featured</a></div>
                        <div class="featured_price ml-auto">Price: <span>$35</span></div>
                    </div>
                    <div class="featured_title"><h3><a href="courses.html">Online Literature Course</a></h3></div>
                    <div class="featured_text">Maecenas rutrum viverra sapien sed fermentum. Morbi tempor odio eget lacus tempus pulvinar. Donec vehicula efficitur nibh, in pretium nulla interdum lacus vehicula efficitur nibh, in pretiumvehicula efficitur nibh, in pretium tempus non.</div>
                    <div class="featured_footer d-flex align-items-center justify-content-start">
                        <div class="featured_author_image"><img src="resources/img/website/featured_author.jpg" alt=""></div>
                        <div class="featured_author_name">By <a href="#">James S. Morrison</a></div>
                        <div class="featured_sales ml-auto"><span>352</span> Sales</div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 featured_col">
                <div class="featured_background" style="background-image:url(resources/img/website/featured.jpg)"></div>
            </div>
        </div>
        <div class="row courses_row">

            <c:forEach var="kurs" items="${kursevi}">
                <div class="col-lg-4 col-md-6">
                    <div class="course">
                        <div class="course_image"><img src="${kurs.kursSlika}" alt="${kurs.kursIme}"></div>
                        <div class="course_body">
                            <div class="course_header d-flex flex-row align-items-center justify-content-start">
                                <div class="course_tag"><a href="#">New</a></div>
                                <div class="course_price ml-auto">Cena: <span>${kurs.kursCena}$</span></div>
                            </div>
                            <div class="course_title"><h3><a href="/kurs?id='${kurs.kursId}'">${kurs.kursIme}</a></h3></div>
                            <div class="course_text">${kurs.kursOpis}</div>
                            <div class="course_footer d-flex align-items-center justify-content-start">
                                <div class="course_author_image"><img src="resources/img/website/course_author_2.jpg" alt="https://unsplash.com/@anthonytran"></div>
                                <div class="course_author_name">By <a href="#">${kurs.korisnikId.korisnikIme} ${kurs.korisnikId.korisnikPrezime}</a></div>
                                <div class="course_sales ml-auto"><span>352</span> Sales</div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>

        <!-- Pagination -->
        <div class="row">
            <div class="col">
                <div class="courses_paginations">
                    <ul>
                        <li class="active"><a href="#">01</a></li>
                        <li><a href="#">02</a></li>
                        <li><a href="#">03</a></li>
                        <li><a href="#">04</a></li>
                        <li><a href="#">05</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
