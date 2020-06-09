<div class="home">
    <div class="home_background parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/contact.jpg" data-speed="0.8"></div>
    <div class="home_container">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="home_content text-center">
                        <div class="home_title">Registracija</div>
                        <div class="breadcrumbs">
                            <ul>
                                <li><a href="/">Pocetna</a></li>
                                <li>Registracija</li>
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
                                            <div class="logo_img"><img src="resources/img/website/logo.png" alt=""></div>
                                            <div class="logo_text">learn</div>
                                        </div>
                                    </a>
                                </div>
                                <div class="contact_about_text">
                                    <p>Suspendisse tincidunt magna eget massa hendrerit efficitur. Ut euismod pellentesque imperdiet. Cras laoreet gravida lectus, at viverra lorem venenatis in. Aenean id varius quam. Nullam bibendum interdum dui, ac tempor lorem convallis ut. Maecenas rutrum viverra sapien sed fermentum. Morbi tempor odio eget lacus tempus pulvinar.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-6">
                            <div class="contact_info_container">
                                <div class="contact_info_main_title">Contact Us</div>
                                <div class="contact_info">
                                    <div class="contact_info_item">
                                        <div class="contact_info_title">Address:</div>
                                        <div class="contact_info_line">1481 Creekside Lane Avila Beach, CA 93424</div>
                                    </div>
                                    <div class="contact_info_item">
                                        <div class="contact_info_title">Phone:</div>
                                        <div class="contact_info_line">+53 345 7953 32453</div>
                                    </div>
                                    <div class="contact_info_item">
                                        <div class="contact_info_title">Email:</div>
                                        <div class="contact_info_line">yourmail@gmail.com</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-xl-6 map_col">
                <div class="contact_form_container" style="width: 80%;">
                    <form action="registracija" method="POST" id="registracija" class="contact_form">
                        <div>
                            <div class="row">
                                <div class="col-lg-6 contact_name_col">
                                    <input type="text" name="ime" class="contact_input" placeholder="Ime" required="required">
                                </div>
                                <div class="col-lg-6">
                                    <input type="text" name="prezime" class="contact_input" placeholder="Prezime" required="required">
                                </div>
                            </div>
                        </div>
                        <div><input type="email" name="email" class="contact_input" placeholder="Email" required="required"></div>
                        <div><input type="tel" name="brt" class="contact_input" placeholder="Broj telefona" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" required="required"></div>
                        <div><input type="text" name="mesto" class="contact_input" placeholder="Mesto" required="required"></div>
                        <div><input type="text" name="adresa" class="contact_input" placeholder="Adresa" required="required"></div>
                        <div><input type="password" name="lozinka" class="contact_input" placeholder="Lozinka" required="required"></div>
                        <button class="contact_button"><span>Registracija</span><span class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>
