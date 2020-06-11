<style>
    label {
        cursor: pointer;
    }

    #avatar {
        opacity: 0;
        position: absolute;
        z-index: -1;
    }
</style>
<div class="home">
    <div class="home_background parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/courses.jpg" data-speed="0.8"></div>
    <div class="home_container">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="home_content text-center">
                        <div class="home_title">${korisnik.korisnikIme} ${korisnik.korisnikPrezime}</div>
                        <div class="breadcrumbs">
                            <ul>
                                <li><a href="#">Pregled naloga</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="m-5">

    <div class="w3-content w3-margin-top" style="max-width:1400px;">

        <div class="w3-row-padding">

            <div class="w3-third">

                <div class="w3-white w3-text-grey w3-card-4">
                    <div class="w3-display-container">
                        <img src="${korisnik.korisnikAvatar}" style="width:100%" alt="Avatar">
                        <div class="w3-display-bottomleft w3-container w3-text-black">
                            <h2>${korisnik.korisnikIme} ${korisnik.korisnikPrezime}</h2>
                        </div>
                    </div>
                    <div class="text-center">
                        <br>
                        <label for="avatar" style="font-size: 20px"><i class="fa fa-picture-o"></i> Promeni sliku</label>
                        <br>
                        <form action="azuriranjeSlike" method="POST" enctype="multipart/form-data">
                            <input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg" value="Promeni sliku">
                            <input type="submit" value="Promeni sliku">
                        </form>
                    </div>
                    <div class="w3-container">
                        <p><i class="fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal"></i>${korisnik.korisnikTitula}</p>
                        <p><i class="fa fa-home fa-fw w3-margin-right w3-large w3-text-teal"></i>${korisnik.korisnikMesto}</p>
                        <p><i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal"></i>${korisnik.korisnikEmail}</p>
                        <p><i class="fa fa-phone fa-fw w3-margin-right w3-large w3-text-teal"></i>${korisnik.korisnikBrojTelefona}</p>
                        <hr>

                        <p class="w3-large"><b><i class="fa fa-asterisk fa-fw w3-margin-right w3-text-teal"></i>Skills</b></p>
                        <p>Adobe Photoshop</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:90%">90%</div>
                        </div>
                        <p>Photography</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:80%">
                                <div class="w3-center w3-text-white">80%</div>
                            </div>
                        </div>
                        <p>Illustrator</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:75%">75%</div>
                        </div>
                        <p>Media</p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal" style="width:50%">50%</div>
                        </div>
                        <br>

                        <p class="w3-large w3-text-theme"><b><i class="fa fa-globe fa-fw w3-margin-right w3-text-teal"></i>Languages</b></p>
                        <p>English</p>
                        <div class="w3-light-grey w3-round-xlarge">
                            <div class="w3-round-xlarge w3-teal" style="height:24px;width:100%"></div>
                        </div>
                        <p>Spanish</p>
                        <div class="w3-light-grey w3-round-xlarge">
                            <div class="w3-round-xlarge w3-teal" style="height:24px;width:55%"></div>
                        </div>
                        <p>German</p>
                        <div class="w3-light-grey w3-round-xlarge">
                            <div class="w3-round-xlarge w3-teal" style="height:24px;width:25%"></div>
                        </div>
                        <br>
                    </div>
                </div><br>

            </div>

            <div class="w3-twothird">

                <div class="w3-container w3-card w3-white w3-margin-bottom">
                    <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-list-alt fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Opis</h2>
                    <div class="w3-container">
                        <h5 class="w3-opacity"><b>${korisnik.korisnikTitula}</b></h5>
                        <p>${korisnik.korisnikOpis}</p>
                        <hr>
                    </div>
                </div>

                <div class="w3-container w3-card w3-white w3-margin-bottom">
                    <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-book fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Kursevi</h2>
                    <c:forEach var="kurs" items="${kursevi}">
                        <div class="w3-container">
                            <h5 class="w3-opacity"><b>${kurs.kursIme}</b></h5>
                            <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>${kurs.kursDatumObjavljivanja}</h6>
                            <p>${kurs.kursOpis}</p>
                            <hr>
                        </div>
                    </c:forEach>
                </div>

                <div class="w3-container w3-card w3-white">
                    <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-comments fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Komentari</h2>
                    <c:forEach var="komentar" items="${komentari}">
                        <div class="w3-container">
                            <h5 class="w3-opacity"><b>${komentar.komentarDatum} ${komentar.komentarVreme}</b></h5>
                            <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>Forever</h6>
                            <p>${komentar.komentarSadrzaj}</p>
                            <hr>
                        </div>
                    </c:forEach> 
                </div>

            </div>

        </div>

    </div>
</div>