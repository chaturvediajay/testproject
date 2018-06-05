<!DOCTYPE HTML>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:bundle basename="trasaction">
	<fmt:message var="company_introduction" key="company.introduction" />
	<fmt:message var="company_name" key="company.name" />
	<fmt:message var="company_director_name" key="company.director.name" />
	<fmt:message var="company_director_visible"
		key="company.director.visible" />
	<fmt:message var="company_address" key="company.address" />
	<fmt:message var="company_contact" key="company.contact" />
	<fmt:message var="company_email" key="company.email" />
	<fmt:message var="company_facebook" key="company.facebook" />
	<fmt:message var="company_twitter" key="company.twitter" />
</fmt:bundle>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta charset="utf-8">
<!-- Description, Keywords and Author -->
<meta name="google-site-verification"
	content="9t_JoUYdXM3lpzgXgp3QR7QcPZGthGROACtpvfN0qpA" />
<meta name="description"
	content=">Online shopping site in India to buy wide range of fashion,hand block printed suits, saree, dress materials,
				Ladies Designer Wear Wholesalers,Dress Material Wholesalers in Sanganer,
				Printed Fabric Retailers,Printed Fabric Wholesalers,Salwar Kameez Retailers,Designer Saree Retailers">
<meta name="author" content="blagot infotech">
<title>:: ${company_name} ::</title>
<link rel="shortcut icon" href="images/logo.png" type="image/x-icon">
<!-- style -->
<link href="uc/css/style.css" rel="stylesheet">
<!-- style -->
<!-- bootstrap -->
<link href="uc/css/bootstrap.min.css" rel="stylesheet">
<!-- responsive -->

<link href="uc/css/responsive.css" rel="stylesheet">

<!-- font-awesome -->

<link href="uc/css/font-awesome.min.css" rel="stylesheet">

<!-- font-awesome -->


</head>

<body>
	<!-- main-wrapper-iamge -->

	<main id="main" role="main-wrapper-iamge">
	<div class="over-bg-color">

		<!-- container -->

		<div class="container">

			<!-- tab-content -->

			<div class="tab-content text-center">

				<!-- Countdown -->

				<section id="home" class="tab-pane fade in active">
					<article role="countdown" class="countdown-pan">
						<div id="countdown" class="text-center"></div>
						<p>we are working hard for better expirience</p>
					</article>
				</section>

				<!-- Countdown -->

				<!-- introduction -->

				<section id="menu1" class="tab-pane fade">
					<article role="introduction" class="introduction-pan">
						<header class="page-title">
							<h2>Our Introduction</h2>
						</header>
						<p>${company_introduction}</p>

						<!-- services -->

						<ul role="services">
							<li><i class="fa fa-diamond" aria-hidden="true"></i>
								<h6>Branding Consuting</h6>
								<p>
									We are a team of talented people<br /> with big ideas and
									creative.
								</p></li>
							<li><i class="fa fa-camera-retro" aria-hidden="true"></i>
								<h6>Fashion Photography</h6>
								<p>
									We are a team of talented people<br /> with big ideas and
									creative.
								</p></li>
							<!-- 
              <li> <i class="fa fa-bullhorn" aria-hidden="true"></i>
                <h6>Digital Marketing</h6>
                <p>We are a team of talented people<br/>
                  with big ideas and creative.</p>
              </li> -->
						</ul>

						<!-- services -->

					</article>
				</section>

				<!-- introduction -->


				<!-- Contact -->

				<section id="menu3" class="tab-pane fade">
					<article role="contact" class="contact-pan">
						<header class="page-title">
							<h2>Stay in touch with us</h2>
						</header>
						<h3>
							<a href="mailto:${company_email}">${company_email}</a>
						</h3>
						<ul>
							<li><i class="fa fa-map-marker" aria-hidden="true"></i>

								${company_address}</li>
							<li><i class="fa fa-phone" aria-hidden="true"></i> <a
								href="tel:${company_contact}">${company_contact}</a></li>
						</ul>
					</article>
				</section>
			</div>
			<!-- tab-content -->

		</div>

		<!-- container -->

		<!-- header -->

		<header role="header">
			<hgroup>

				<!-- logog -->

				<h1>
					<a href="#" title="${company_name}"
						style="text-transform: capitalize;">${company_name}</a>
				</h1>

				<!-- logog -->

				<!-- nav -->

				<nav role="nav" id="header-nav" class="nav navy">
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#home"
							title="Countdown">Countdown</a></li>
						<li><a data-toggle="tab" href="#menu1" title="Introduction">introduction</a></li>
						<li><a data-toggle="tab" href="#menu3" title="Contact">Contact</a></li>
					</ul>
					<div role="socil-icons" class="mobile-social">
						<!--    <li><a href="#" target="_blank" title="twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a></li> -->
						<li><a href="${company_facebook}" target="_blank"
							title="facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
						<!--       <li><a href="#" target="_blank" title="google-plus"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li> -->
						<!--        <li><a href="#" target="_blank" title="pinterest"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li> -->
					</div>
				</nav>

				<!-- nav -->

				<!-- Socil Icon -->

				<ul role="socil-icons" class="desk-social">
					<li><a href="${company_twitter}" target="_blank"
						title="twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
					<li><a href="#" target="_blank" title="facebook"><i
							class="fa fa-facebook" aria-hidden="true"></i></a></li>
					<!--   <li><a href="#" target="_blank" title="google-plus"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li> -->
					<!--   <li><a href="#" target="_blank" title="pinterest"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li> -->
				</ul>

				<!-- Socil Icon -->

			</hgroup>
			<footer class="desk">
				<p>
					&copy; All rights reserved. blagot infotech2018 Made with <i
						class="fa fa-heart" aria-hidden="true"></i> by blagot team
				</p>
			</footer>
		</header>

		<!-- header -->

		<footer class="mobile">
			<p>
				&copy; All rights reserved.<a target="_blank">blagot infotech</a>2018
				Made with <i class="fa fa-heart" aria-hidden="true"></i> by blagot
				team
			</p>
		</footer>
	</div>
	</main>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

	<script src="uc/js/jquery.min.js" type="text/javascript"></script>

	<!-- custom -->

	<script src="uc/js/custom.js" type="text/javascript"></script>
	<script src="uc/js/nav-custom.js" type="text/javascript"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->

	<script src="uc/js/bootstrap.min.js" type="text/javascript"></script>

	<!-- jquery.countdown -->

	<script src="uc/js/countdown-js.js" type="text/javascript"></script>
	<script src="uc/js/html5shiv.js" type="text/javascript"></script>
</body>
</html>