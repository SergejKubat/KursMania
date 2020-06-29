<div class="home">
    <div class="home_background parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/contact.jpg" data-speed="0.8"></div>
    <div class="home_container">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="home_content text-center">
                        <div class="home_title">Lekcija</div>
                        <div class="breadcrumbs">
                            <ul>
                                <li><a href="">Pocetna</a></li>
                                <li><a href="kurs?id=${lekcija.sekcijaId.kursId.kursId}">${lekcija.sekcijaId.kursId.kursIme}</a></li>
                                <li>${lekcija.lekcijaIme}</li>
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

                    <div class="video">
                        <div class="video_container_outer">
                            <div class="video_container">
                                <video style="height: 600px; width: 800px" poster="${lekcija.sekcijaId.kursId.kursSlika}" controls>
                                    <source src="${lekcija.videoId.videoAdresa}" type="video/mp4">                      
                                </video>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div class="col-xl-6 map_col">

                <div class="contact_content">
                    <h3>Sekcija: <b>${lekcija.sekcijaId.sekcijaNaslov}</b></h3>
                    <br>
                    <div class="card">
                        <div class="card-header">
                            <h1>${lekcija.lekcijaIme}</h1>
                        </div>
                        <div class="card-body">
                            ${lekcija.lekcijaOpis}
                        </div>
                    </div>
                    <c:if test="${sledecaLekcija != null}">
                        <br>
                        <h4>Sledeca lekcija: <b>${sledecaLekcija.lekcijaIme}</b></h4>
                        <a href="lekcija?id=${sledecaLekcija.lekcijaId}"><button class="contact_button"><span>Sledeca lekcija</span><span class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button></a>
                    </c:if>
                </div>

            </div>
        </div>
    </div>
</div>
