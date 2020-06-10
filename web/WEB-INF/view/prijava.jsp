<div class="home">
    <div class="home_background parallax_background parallax-window" data-parallax="scroll" data-image-src="resources/img/website/contact.jpg" data-speed="0.8"></div>
    <div class="home_container">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="home_content text-center">
                        <div class="home_title">Prijava</div>
                        <div class="breadcrumbs">
                            <ul>
                                <li><a href="/">Pocetna</a></li>
                                <li>Prijava</li>
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
                        <div class="col-lg-10 offset-lg-1">
                            <div class="section_title text-center"><h2>Prijavite se</h2></div>
                            <div class="section_subtitle">Donec blandit libero id lectus sagittis, sed tempus ipsum faucibus. Phasellus metus lorem, lobortis in eros et, bibendum laoreet ante. Integer vel purus ut libero tempus eleifend. Ut metus diam, semper ac finibus vel, varius molestie erat. Sed ac eros ipsum. Duis ut arcu a turpis lobortis congue. Nullam sollicitudin, sapien nec elementum pellentesque, ante lorem efficitur quam, vel faucibus elit nulla eu libero. Aenean rhoncus et nulla sit amet commodo. Curabitur id volutpat velit. Integer vehicula lectus a nulla ultrices fringilla. Duis erat tellus, interdum quis molestie ut, tempor eu leo. Nam nec vulputate erat, eget finibus dolor. Vivamus ut porta lacus. Fusce nec turpis vitae lectus hendrerit consequat. Praesent commodo massa ante, at cursus orci suscipit ac. Cras tristique sapien purus, vel luctus odio commodo eget.</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-xl-6 map_col">
                <div class="contact_form_container" style="width: 80%;">
                    <c:if test="${poruka != null}">
                        <div class="row mb-4">
                        <div class="col-lg-8 mx-auto text-center">
                            <h1 class="display-5">${poruka}</h1>
                        </div>
                    </div> 
                    </c:if>
                    <form action="prijava" method="POST" id="registracija" class="contact_form">
                        <div><input type="email" name="email" class="contact_input" placeholder="Email" required="required"></div>
                        <div><input type="password" name="lozinka" class="contact_input" placeholder="Lozinka" required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}"></div>
                        <button class="contact_button"><span>Prijava</span><span class="button_arrow"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>