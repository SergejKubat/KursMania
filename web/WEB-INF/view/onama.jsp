<div class="home">
    <div class="home_background parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/about.jpg" data-speed="0.8"></div>
    <div class="home_container">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="home_content text-center">
                        <div class="home_title">O nama</div>
                        <div class="breadcrumbs">
                            <ul>
                                <li><a href="/">Pocetna</a></li>
                                <li>O nama</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="about">
    <div class="container">
        <div class="row about_row row-lg-eq-height">
            <div class="col-lg-6">
                <div class="about_content">
                    <div class="about_title">Nasi ciljevi</div>
                    <div class="about_text">
                        <p>Suspendisse tincidunt magna eget massa hendrerit efficitur. Ut euismod pellentesque imperdiet. Cras laoreet gravida lectus, at viverra lorem venenatis in. Aenean id varius quam. Nullam bibendum interdum dui, ac tempor lorem convallis ut. Maecenas rutrum viverra sapien sed fermentum. Morbi tempor odio eget lacus tempus pulvinar. Praesent vel nisl fermentum, gravida augue. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer id convallis libero, sed blandit nibh. Nam ultricies tristique nibh, consequat ornare nibh. Quisque vitae odio ligula.</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="about_image"><img src="resources/img/website/about_1.jpg" alt="https://unsplash.com/@jtylernix"></div>
            </div>
        </div>
        <div class="row about_row row-lg-eq-height">
            <div class="col-lg-6 order-lg-1 order-2">
                <div class="about_image"><img src="resources/img/website/about_1.jpg" alt=""></div>
            </div>
            <div class="col-lg-6 order-lg-2 order-1">
                <div class="about_content">
                    <div class="about_title">Vizija</div>
                    <div class="about_text">
                        <p>Suspendisse tincidunt magna eget massa hendrerit efficitur. Ut euismod pellentesque imperdiet. Cras laoreet gravida lectus, at viverra lorem venenatis in. Aenean id varius quam. Nullam bibendum interdum dui, ac tempor lorem convallis ut. Maecenas rutrum viverra sapien sed fermentum. Morbi tempor odio eget lacus tempus pulvinar. Praesent vel nisl fermentum, gravida augue. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer id convallis libero, sed blandit nibh. Nam ultricies tristique nibh.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="milestones">
    <div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/milestones.jpg" data-speed="0.8"></div>
    <div class="container">
        <div class="row milestones_container">

            <div class="col-lg-3 milestone_col">
                <div class="milestone text-center">
                    <div class="milestone_icon"><img src="resources/img/website//milestone_1.svg" alt=""></div>
                    <div class="milestone_counter" data-end-value="${brojKurseva}">0</div>
                    <div class="milestone_text">Kursevi</div>
                </div>
            </div>

            <div class="col-lg-3 milestone_col">
                <div class="milestone text-center">
                    <div class="milestone_icon"><img src="resources/img/website/milestone_2.svg" alt=""></div>
                    <div class="milestone_counter" data-end-value="${brojStudenata}">0</div>
                    <div class="milestone_text">Studenti</div>
                </div>
            </div>

            <div class="col-lg-3 milestone_col">
                <div class="milestone text-center">
                    <div class="milestone_icon"><img src="resources/img/website/milestone_3.svg" alt=""></div>
                    <div class="milestone_counter" data-end-value="${brojInstruktora}">0</div>
                    <div class="milestone_text">Instruktori</div>
                </div>
            </div>

            <div class="col-lg-3 milestone_col">
                <div class="milestone text-center">
                    <div class="milestone_icon"><img src="resources/img/website/milestone_4.svg" alt=""></div>
                    <div class="milestone_counter" data-end-value="${brojJezika}">0</div>
                    <div class="milestone_text">Jezici</div>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="teachers">
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="teachers_title text-center">Upoznajte instruktore</div>
            </div>
        </div>
        <div class="row teachers_row">

            <c:forEach var="instruktor" items="${instruktori}">
                <div class="col-lg-4 col-md-6">
                    <div class="teacher">
                        <div class="teacher_image"><img src="${instruktor.korisnikAvatar}" alt="${instruktor.korisnikIme} ${instruktor.korisnikIme}"></div>
                        <div class="teacher_body text-center">
                            <div class="teacher_title"><a href="instruktor?id=${instruktor.korisnikId}">${instruktor.korisnikIme} ${instruktor.korisnikIme}</a></div>
                            <div class="teacher_subtitle">${instruktor.korisnikTitula}</div>
                            <div class="teacher_social">
                                <ul>
                                    <li><a href="#"><i class="fa fa-linkedin-square"></i></a></li>
                                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                    <li><a href="#"><i class="fa fa-facebook"></i></i></a></li>
                                    <li><a href="#"><i class="fa fa-youtube-play"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
        <div class="row">
            <div class="col text-center">
                <div class="button teachers_button"><a href="instruktori">Svi instruktori<div class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></div></a></div>
            </div>
        </div>
    </div>
</div>
