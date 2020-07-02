<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <div class="super_container">

        <header class="header">

            <div class="top_bar">
                <div class="top_bar_container">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="top_bar_content d-flex flex-row align-items-center justify-content-start">
                                    <ul class="top_bar_contact_list">
                                        <li><div class="question"><a href="kontakt">Pitanja?</a></div></li>
                                        <li>
                                            <div><a href="tel:0116688933">(011) 6688933</a></div>
                                        </li>
                                        <li>
                                            <div><a href="mailto:info@kursmania.com">info@kursmania.com</a></div>
                                        </li>
                                    </ul>
                                    <div class="top_bar_login ml-auto">
                                        <ul>
                                            <c:if test="${korisnik == null}">
                                                <li><a href="registracija">Registracija</a></li>
                                                <li><a href="prijava">Prijava</a></li>
                                                </c:if>
                                                <c:if test="${korisnik != null}">
                                                <li><a href="nalog"><img src="${korisnik.korisnikAvatar}" alt="Korisnik" style="width: 32px; height: 32px; border-radius: 100%; margin-right: 5px" />${korisnik.korisnikIme} ${korisnik.korisnikPrezime}</a></li>
                                                <li><a href="odjava">Odjava</a></li>
                                                </c:if>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>				
            </div>

            <div class="header_container">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="header_content d-flex flex-row align-items-center justify-content-start">
                                <div class="logo_container">
                                    <a href="/">
                                        <div class="logo_content d-flex flex-row align-items-end justify-content-start">
                                            <div class="logo_img"><img src="resources/img/website/logo.png" alt=""></div>
                                            <div class="logo_text">KursMania</div>
                                        </div>
                                    </a>
                                </div>
                                <nav class="main_nav_contaner ml-auto">
                                    <ul class="main_nav">
                                        <li class="active"><a href="/KursMania">Pocetna</a></li>
                                        <li <c:if test="${navigationSelector == 1}">class="active"</c:if>><a href="onama">O nama</a></li>
                                        <li <c:if test="${navigationSelector == 2}">class="active"</c:if>><a href="kursevi">Kursevi</a></li>
                                        <li <c:if test="${navigationSelector == 3}">class="active"</c:if>><a href="instruktori">Instruktori</a></li>
                                        <li <c:if test="${navigationSelector == 4}">class="active"</c:if>><a href="kontakt">Kontakt</a></li>
                                            <li>
                                                <div class="dropdown">
                                                    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Kategorija
                                                        <span class="caret"></span></button>
                                                    <ul class="dropdown-menu">
                                                    <c:forEach var="kategorija" items="${kategorije}">
                                                        <li><a tabindex="-1" href="kategorija?id=${kategorija.kategorijaId}">${kategorija.kategorijaNaziv}</a></li>
                                                        </c:forEach>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                    <div class="search_button"><i class="fa fa-search" aria-hidden="true"></i></div>

                                    <div class="hamburger menu_mm">
                                        <i class="fa fa-bars menu_mm" aria-hidden="true"></i>
                                    </div>
                                </nav>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="header_search_container">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="header_search_content d-flex flex-row align-items-center justify-content-end">
                                <datalist id="preporuke">                                        
                                </datalist>
                                <form action="pretraga" method="GET" class="header_search_form">
                                    <input type="search" name="q" list="browsers" class="search_input" placeholder="Pretraga" required="required">
                                    <button class="header_search_button d-flex flex-column align-items-center justify-content-center">
                                        <i class="fa fa-search" aria-hidden="true"></i>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>			
            </div>			
        </header>

        <div class="menu d-flex flex-column align-items-end justify-content-start text-right menu_mm trans_400">
            <div class="menu_close_container"><div class="menu_close"><div></div><div></div></div></div>
            <div class="search">
                <form action="#" class="header_search_form menu_mm">
                    <input type="search" class="search_input menu_mm" placeholder="Search" required="required">
                    <button class="header_search_button d-flex flex-column align-items-center justify-content-center menu_mm">
                        <i class="fa fa-search menu_mm" aria-hidden="true"></i>
                    </button>
                </form>
            </div>
            <nav class="menu_nav">
                <ul class="menu_mm">
                    <li class="menu_mm"><a href="">Pocetna</a></li>
                    <li class="menu_mm"><a href="kursevi">Kursevi</a></li>
                    <li class="menu_mm"><a href="instruktori">Instruktori</a></li>
                    <li class="menu_mm"><a href="kontakt">Kontakt</a></li>
                        <c:if test="${korisnik == null}">
                        <li class="menu_mm"><a href="registracija">Registracija</a></li>
                        <li class="menu_mm"><a href="prijava">Prijava</a></li>
                        </c:if>
                        <c:if test="${korisnik != null}">
                        <li class="menu_mm"><a href="nalog"><img src="resources/img/ostale/default_avatar.png" alt="Korisnik" style="width: 32px; height: 32px; border-radius: 100%; margin-right: 5px" />${korisnik.korisnikIme} ${korisnik.korisnikPrezime}</a></li>
                        <li class="menu_mm"><a href="odjava">Odjava</a></li>
                        </c:if>
                </ul>
            </nav>
            <div class="menu_extra">
                <div class="menu_phone"><span class="menu_title">telefon:</span>(011) 6688933</div>
                <div class="menu_social">
                    <span class="menu_title">Pratite nas</span>
                    <ul>
                        <li><a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
                        <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                        <li><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                        <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>

