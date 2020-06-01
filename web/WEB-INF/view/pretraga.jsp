<link rel="stylesheet" type="text/css" href="resources/css/news.css">
<link rel="stylesheet" type="text/css" href="resources/css/news_responsive.css">
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
        <div class="row">

            <!-- News Posts -->
            <div class="col-lg-8">
                <div class="news_posts">

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
            </div>
            <!-- Sidebar -->
            <div class="col-lg-4">
                <div class="sidebar">
                    <div class="sidebar_search">
                        <form action="#" id="sidebar_search_form" class="sidebar_search_form">
                            <input type="text" class="sidebar_search_input" placeholder="Search" required="required">
                            <button class="sidebar_search_button"><i class="fa fa-search" aria-hidden="true"></i></button>
                        </form>
                    </div>
                    <div class="sidebar_categories">
                        <div class="sidebar_title">Categories</div>
                        <div class="sidebar_links">
                            <ul>
                                <li><a href="#">Education</a></li>
                                <li><a href="#">Social Media</a></li>
                                <li><a href="#">Lifestyle & Events</a></li>
                                <li><a href="#">Online Learning</a></li>
                                <li><a href="#">Uncategorized</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="sidebar_latest_posts">
                        <div class="sidebar_title">Latest Posts</div>
                        <div class="latest_posts">

                            <!-- News Post -->
                            <div class="latest_post d-flex flex-row align-items-start justify-content-start">
                                <div><div class="latest_post_image"><img src="resources/img/website/news_1.jpg" alt="https://unsplash.com/@beccatapert"></div></div>
                                <div class="latest_post_body">
                                    <div class="latest_post_date">April 02, 2018</div>
                                    <div class="latest_post_title"><a href="news.html">Why Choose online education?</a></div>
                                    <div class="latest_post_author">By <a href="#">William Smith</a></div>
                                </div>
                            </div>

                            <!-- News Post -->
                            <div class="latest_post d-flex flex-row align-items-start justify-content-start">
                                <div><div class="latest_post_image"><img src="resources/img/website/news_2.jpg" alt="https://unsplash.com/@nbb_photos"></div></div>
                                <div class="latest_post_body">
                                    <div class="latest_post_date">April 02, 2018</div>
                                    <div class="latest_post_title"><a href="news.html">Books, Kindle or tablet?</a></div>
                                    <div class="latest_post_author">By <a href="#">William Smith</a></div>
                                </div>
                            </div>

                            <!-- News Post -->
                            <div class="latest_post d-flex flex-row align-items-start justify-content-start">
                                <div><div class="latest_post_image"><img src="resources/img/website/news_3.jpg" alt="https://unsplash.com/@rawpixel"></div></div>
                                <div class="latest_post_body">
                                    <div class="latest_post_date">April 02, 2018</div>
                                    <div class="latest_post_title"><a href="news.html">Why Choose online education?</a></div>
                                    <div class="latest_post_author">By <a href="#">William Smith</a></div>
                                </div>
                            </div>

                            <!-- News Post -->
                            <div class="latest_post d-flex flex-row align-items-start justify-content-start">
                                <div><div class="latest_post_image"><img src="resources/img/website/news_4.jpg" alt="https://unsplash.com/@jtylernix"></div></div>
                                <div class="latest_post_body">
                                    <div class="latest_post_date">April 02, 2018</div>
                                    <div class="latest_post_title"><a href="news.html">Books, Kindle or tablet?</a></div>
                                    <div class="latest_post_author">By <a href="#">William Smith</a></div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="sidebar_elearn">
                        <div class="elearn">
                            <div class="elearn_background" style="background-image:url(resources/img/website/elearn.jpg)"></div>
                            <div class="elearn_content d-flex flex-column align-items-center justify-content-end">
                                <div class="elearn_line">Get a 20% Discount</div>
                                <div class="elearn_link"><a href="#">Register now</a></div>
                                <div class="dcount">
                                    <div class="dcount_content d-flex flex-column align-items-center justify-content-center">
                                        <div class="dcount_value">20%</div>
                                        <div class="dcount_text">off</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Pagination -->
        <div class="row">
            <div class="col">
                <div class="news_pagination">
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