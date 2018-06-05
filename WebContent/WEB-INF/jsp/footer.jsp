<div class="footer">
	<div class="container">
		<div class="col-md-3 footer-left">
			<a href="index.html&lt;img src=" images="" logo.png"="" alt=""
				style="height: 30px;"></a>
			<p class="footer-class">
				© 2018 ${company_name} All Rights Reserved | <a href="${url}"
					target="_blank">${company_name} </a>
			</p>
		</div>
		<div class="col-md-2 footer-middle">
			<ul>
				<li><a href="${pageContext.request.contextPath}/aboutUs">about
						us</a></li>
				<li><a href="${pageContext.request.contextPath}/contactUs">
						contact us</a></li>
				<li><a href="${pageContext.request.contextPath}/gallery">
						our stores</a></li>
			</ul>
		</div>
		<div class="col-md-4 footer-left-in">
			<ul class="term">
				<li><a href="${pageContext.request.contextPath}/terms">terms
						and conditions</a></li>
				<li><a href="#"> chouhanrugs in media</a></li>
				<li><a href="${pageContext.request.contextPath}/testimonial">
						testimonials</a></li>
			</ul>
			<ul class="term">
				<li><a href="${pageContext.request.contextPath}/joinUs">join
						us</a></li>
				<li><a href="#"> chouhanrugs videos</a></li>

				<c:if test="${sessionScope.loginSession == null}">
					<li><a href="${pageContext.request.contextPath}/adminLogin/">
							admin login</a></li>
				</c:if>

				<c:if test="${sessionScope.loginSession != null}">
					<li><a href="${pageContext.request.contextPath}/logout/">
							Logout</a></li>
				</c:if>


			</ul>
			<div class="clearfix"></div>
		</div>
		<div class="col-md-3 footer-right">
			<h5>WE SUPPORT</h5>
			<ul>
				<li><a href="#"><i> </i></a></li>
				<li><a href="#"><i class="we"> </i></a></li>
				<li><a href="#"><i class="we-in"> </i></a></li>
				<li><a href="#"><i class="we-at"> </i></a></li>
				<li><a href="#"><i class="we-at-at"> </i></a></li>
			</ul>
		</div>
		<div class="clearfix"></div>
	</div>
	<a href="#" id="toTop" style="display: block;"><span
		id="toTopHover" style="opacity: 0;"></span><span id="toTopHover"></span>
		<span id="toTopHover" style="opacity: 1;"> </span></a>

</div>