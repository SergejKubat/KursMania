<link href="resources/css/shop-item.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="resources/css/courses.css">
<link rel="stylesheet" type="text/css" href="resources/css/courses_responsive.css">
<div class="home">
    <div class="home_background parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/contact.jpg" data-speed="0.8"></div>
    <div class="home_container">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="home_content text-center">
                        <div class="home_title">Instruktori</div>
                        <div class="breadcrumbs">
                            <ul>
                                <li><a href="/">Pocetna</a></li>
                                <li>Instruktori</li>
                            </ul>
                        </div>
                    </div>
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
