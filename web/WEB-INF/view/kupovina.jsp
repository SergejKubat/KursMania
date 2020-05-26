<link rel="stylesheet" type="text/css" href="resources/css/contact.css">
<link rel="stylesheet" type="text/css" href="resources/css/contact_responsive.css">
<link rel="stylesheet" type="text/css" href="resources/css/payment.css">
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

                <div class="container py-5">
                    <!-- For demo purpose -->
                    <div class="row mb-4">
                        <div class="col-lg-8 mx-auto text-center">
                            <h1 class="display-4">Bootstrap Payment Forms</h1>
                        </div>
                    </div> <!-- End -->
                    <div class="row">
                        <div class="col-lg-6 mx-auto">
                            <div class="card ">
                                <div class="card-header">
                                    <div class="bg-white shadow-sm pt-4 pl-2 pr-2 pb-2">
                                        <!-- Credit card form tabs -->
                                        <ul role="tablist" class="nav bg-light nav-pills rounded nav-fill mb-3">
                                            <li class="nav-item"> <a data-toggle="pill" href="#credit-card" class="nav-link active "> <i class="fa fa-credit-card-alt"></i> Credit Card </a> </li>
                                            <li class="nav-item"> <a data-toggle="pill" href="#paypal" class="nav-link "> <i class="fa fa-paypal"></i> Paypal </a> </li>
                                            <li class="nav-item"> <a data-toggle="pill" href="#net-banking" class="nav-link "> <i class="fa fa-university"></i> Net Banking </a> </li>
                                        </ul>
                                    </div> <!-- End -->
                                    <!-- Credit card form content -->
                                    <div class="tab-content">
                                        <!-- credit card info-->
                                        <div id="credit-card" class="tab-pane fade show active pt-3">
                                            <form role="form">
                                                <div class="form-group"> <label for="username">
                                                        <h6>Card Owner</h6>
                                                    </label> <input type="text" name="username" placeholder="Card Owner Name" required class="form-control "> </div>
                                                <div class="form-group"> <label for="cardNumber">
                                                        <h6>Card number</h6>
                                                    </label>
                                                    <div class="input-group"> <input type="text" name="cardNumber" placeholder="Valid card number" class="form-control " required>
                                                        <div class="input-group-append"> <span class="input-group-text text-muted"> <i class="fa fa-cc-visa"></i> <i class="fa fa-cc-mastercard"></i> <i class="fa fa-cc-amex"></i> </span> </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-8">
                                                        <div class="form-group"> <label><span class="hidden-xs">
                                                                    <h6>Expiration Date</h6>
                                                                </span></label>
                                                            <div class="input-group"> <input type="number" placeholder="MM" name="" class="form-control" required> <input type="number" placeholder="YY" name="" class="form-control" required> </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="form-group mb-4"> <label data-toggle="tooltip" title="Three digit CV code on the back of your card">
                                                                <h6>CVV <i class="fa fa-question-circle d-inline"></i></h6>
                                                            </label> <input type="text" required class="form-control"> </div>
                                                    </div>
                                                </div>
                                                <div class="card-footer"> <button type="button" class="subscribe btn btn-primary btn-block shadow-sm"> Confirm Payment </button>
                                            </form>
                                        </div>
                                    </div> <!-- End -->
                                    <!-- Paypal info -->
                                    <div id="paypal" class="tab-pane fade pt-3">
                                        <h6 class="pb-2">Select your paypal account type</h6>
                                        <div class="form-group "> <label class="radio-inline"> <input type="radio" name="optradio" checked> Domestic </label> <label class="radio-inline"> <input type="radio" name="optradio" class="ml-5">International </label></div>
                                        <p> <button type="button" class="btn btn-primary "><i class="fab fa-paypal mr-2"></i> Log into my Paypal</button> </p>
                                        <p class="text-muted"> Note: After clicking on the button, you will be directed to a secure gateway for payment. After completing the payment process, you will be redirected back to the website to view details of your order. </p>
                                    </div> <!-- End -->
                                    <!-- bank transfer info -->
                                    <div id="net-banking" class="tab-pane fade pt-3">
                                        <div class="form-group "> <label for="Select Your Bank">
                                                <h6>Select your Bank</h6>
                                            </label> <select class="form-control" id="ccmonth">
                                                <option value="" selected disabled>--Please select your Bank--</option>
                                                <option>Bank 1</option>
                                                <option>Bank 2</option>
                                                <option>Bank 3</option>
                                                <option>Bank 4</option>
                                                <option>Bank 5</option>
                                                <option>Bank 6</option>
                                                <option>Bank 7</option>
                                                <option>Bank 8</option>
                                                <option>Bank 9</option>
                                                <option>Bank 10</option>
                                            </select> </div>
                                        <div class="form-group">
                                            <p> <button type="button" class="btn btn-primary "><i class="fas fa-mobile-alt mr-2"></i> Proceed Pyment</button> </p>
                                        </div>
                                        <p class="text-muted">Note: After clicking on the button, you will be directed to a secure gateway for payment. After completing the payment process, you will be redirected back to the website to view details of your order. </p>
                                    </div> <!-- End -->
                                    <!-- End -->
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

        </div>
    </div>

    <script>
        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        })
    </script>

