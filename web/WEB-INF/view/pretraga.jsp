<div class="home">
    <div class="home_background parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/news.jpg" data-speed="0.8"></div>
    <div class="home_container">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="home_content text-center">
                        <div class="home_title">Pretraga</div>
                        <div class="breadcrumbs">
                            <ul>
                                <li><a href="/">Pocetna</a></li>
                                <li>Pretraga</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="news">
    <div class="container">

        <c:if test="${poruka != null}">
            <div class="row">
                <div class="col-lg-10 offset-lg-1">
                    <div class="section_title text-center"><h2>${poruka}</h2></div>
                    <div class="section_subtitle">Pokusajte ponovo.</div>
                </div>
            </div>
        </c:if>

        <div class="row">

            <div class="col-lg-8">
                <div class="news_posts">

                    <c:forEach var="kurs" items="${kursevi}">
                        <div class="col-lg-6">
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
                                        <div class="course_author_name">By <a href="instruktori?id=${kurs.korisnikId.korisnikId}">${kurs.korisnikId.korisnikIme} ${kurs.korisnikId.korisnikPrezime}</a></div>
                                        <div class="course_sales ml-auto"><span>${kurs.getEvidencijaCollection().size()}</span> Studenata</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="sidebar">
                    <div class="sidebar_search">
                        <form action="pretraga" method="GET" id="sidebar_search_form" class="sidebar_search_form">
                            <input type="text" name="q" class="sidebar_search_input" placeholder="Pretraga" required="required">
                            <button class="sidebar_search_button"><i class="fa fa-search" aria-hidden="true"></i></button>
                        </form>
                    </div>
                    <div class="sidebar_categories">
                        <div class="sidebar_title">Kategorije</div>
                        <div class="sidebar_links">
                            <ul>
                                <c:forEach var="kategorija" items="${kategorije}">
                                    <li><a href="kategorija?id=${kategorija.kategorijaId}">${kategorija.kategorijaNaziv}</a></li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="sidebar_latest_posts">
                        <div class="sidebar_title">Najpopularniji kursevi</div>
                        <div class="latest_posts">

                            <c:forEach var="kurs" items="${najpopularnijiKursevi}">
                                <div class="latest_post d-flex flex-row align-items-start justify-content-start">
                                    <div><div class="latest_post_image"><img src="${kurs.kursSlika}" alt="${kurs.kursIme}"></div></div>
                                    <div class="latest_post_body">
                                        <div class="latest_post_date">${kurs.kursDatumObjavljivanja.toString()}</div>
                                        <div class="latest_post_title"><a href="kurs?id=${kurs.kursId}">${kurs.kursIme}</a></div>
                                        <div class="latest_post_author">Autor <a href="instruktor?id=${kurs.korisnikId.korisnikId}">${kurs.korisnikId.korisnikIme} ${kurs.korisnikId.korisnikPrezime}</a></div>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                    <div class="sidebar_elearn">
                        <div class="elearn">
                            <div class="elearn_background" style="background-image:url(resources/img/website/elearn.jpg)"></div>
                            <div class="elearn_content d-flex flex-column align-items-center justify-content-end">
                                <div class="elearn_line">Osvojite 20% popusta</div>
                                <div class="elearn_link"><a href="registracija">Registracija</a></div>
                                <div class="dcount">
                                    <div class="dcount_content d-flex flex-column align-items-center justify-content-center">
                                        <div class="dcount_value">20%</div>
                                        <div class="dcount_text">popust</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>