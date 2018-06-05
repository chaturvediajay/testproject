<div class="top-nav" style=" padding-left: 17px;">
			  <ul class="megamenu skyblue">
				      <c:set var="num" value="0" scope="page" />
				      <c:forEach var="m1" items="${menus1}"> 
				      <c:set var="num1" value="${num+1}" />
				      <c:if test="${num1 == 1}">
					<li class="active grid" style="display: inline-block; overflow: hidden;"><a href="#"> ${m1.menu}</a>
		             </c:if>
		             <div class="megapanel" style="display: none; opacity: 1;">
					<c:forEach var="m2" items="${menus2}">
		      		 <c:set var="num2" value="${num+1}" />
		      		 <c:if test="${m1.m1id ==m2.m1id}">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<ul>
										<c:if test="${num2 == 1}">
										<li><a href="${pageContext.request.contextPath}/products?q=${fn:replace(m2.submenu," ","-")}">${m2.submenu}</a></li>
	                           			</c:if>
									</ul>	
								</div>							
							</div>
						  </div>
						 </c:if>
                	  </c:forEach>		
						</div>
					</li>
				       </c:forEach>
					<li style="display: inline-block; overflow: hidden;"><a href="#">furniture &amp; decor</a>
					 <div class="megapanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<ul>
										<li><a href="decor.html">Bed</a></li>
										<li><a href="decor.html">Chest</a></li>
										<li><a href="decor.html">Stool &amp; Reider</a></li>
										<li><a href="decor.html">Chair &amp; Carpet</a></li>
										<li><a href="decor.html">Curtain &amp; Brix</a></li>
										<li><a href="decor.html">Shelf</a></li>
										<li><a href="decor.html">Desk</a></li>
										<li><a href="decor.html">Sofa &amp; Anson</a></li>
										<li><a href="decor.html">Bench</a></li>
										<li><a href="decor.html">Sink</a></li>
										<li><a href="decor.html">Lamp</a></li>
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<ul>
										<li><a href="decor.html">Armchair</a></li>
										<li><a href="decor.html">Reider Bench</a></li>
										<li><a href="decor.html">Carpet &amp; Desk</a></li>
										<li><a href="decor.html">Wardrobe &amp; Lamp</a></li>
										<li><a href="decor.html">Car seat</a></li>
										<li><a href="decor.html">Lounger</a></li>
										<li><a href="decor.html">Anson</a></li>
										<li><a href="decor.html">Karlstad</a></li>
										<li><a href="decor.html">Camilla &amp; Wardrobe</a></li>
										<li><a href="decor.html">Colton</a></li>
										<li><a href="decor.html">Brix</a></li>
									</ul>	
								</div>							
							</div>
							<div class="col1">
							<iframe src="https://player.vimeo.com/video/16878818" webkitallowfullscreen="" mozallowfullscreen="" allowfullscreen=""></iframe> 
							</div>
						  </div>
						</div></li>
						 <li class="grid" style="display: inline-block; overflow: hidden;"><a href="health.html">health &amp; beauty</a>
			    </li>		
			  </ul> 
				</div>