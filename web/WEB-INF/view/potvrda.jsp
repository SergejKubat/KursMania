<div class="home">
    <div class="home_background parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/contact.jpg" data-speed="0.8"></div>
    <div class="home_container">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="home_content text-center">
                        <div class="home_title">Kupovina</div>
                        <div class="breadcrumbs">
                            <ul>
                                <li><a href="">Pocetna</a></li>
                                <li>Kupovina</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="contact">
    <div class="container-fluid">
        <div class="row row-xl-eq-height" style="margin-bottom: 36px;">
            <div class="col-xl-6">
                <div class="contact_content">
                    <div class="row">
                        <div class="col-xl-6">
                            <div class="contact_about">
                                <div class="logo_container">
                                    <a href="#">
                                        <div class="logo_content d-flex flex-row align-items-end justify-content-start">
                                            <div class="logo_text">${kurs.kursIme}</div>
                                        </div>
                                    </a>
                                </div>
                                <div class="contact_about_text">
                                    <p>${kurs.kursOpis}</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-6">
                            <div class="contact_info_container">
                                <div class="contact_info_main_title">O kursu</div>
                                <div class="contact_info">
                                    <div class="contact_info_item">
                                        <div class="contact_info_title">Broj studenata:</div>
                                        <div class="contact_info_line">${brojStudenata}</div>
                                    </div>
                                    <div class="contact_info_item">
                                        <div class="contact_info_title">Broj sekcija:</div>
                                        <div class="contact_info_line">${brojSekcija}</div>
                                    </div>
                                    <div class="contact_info_item">
                                        <div class="contact_info_title">Broj lekcija:</div>
                                        <div class="contact_info_line">${brojLekcija}</div>
                                    </div>
                                    <div class="contact_info_item">
                                        <div class="contact_info_title">Autor</div>
                                        <div class="contact_info_line"><a href="instruktor?id=${kurs.korisnikId.korisnikId}">${kurs.korisnikId.korisnikIme} ${kurs.korisnikId.korisnikPrezime}</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-xl-6 map_col">

                <div class="card">
                    <div class="card-header">
                        <h1>Cestitamo, uspesno ste kupili kurs!</h1>
                    </div>
                    <div class="card-body">
                        <a href="kurs?id=${kurs.kursId}"><button class="contact_button"><span>Idite na kurs</span><span class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button></a>
                    </div>
                </div>             

            </div>
        </div>



