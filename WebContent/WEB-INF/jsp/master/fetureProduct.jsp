<c:if test="${not empty proList}">
<c:set var="total" value="0" scope="page" />
	<c:set var="proCount" value="${fn:length(featuredPro)}" scope="page" />
	<c:forEach var="fp" items="${proList}">
	<c:set var="total" value="${total+1}" scope="page" />
	<c:set var="sUrl" value="${fn:split(fp.url,',')}" />
<div class="item">
				<div class=" box-in">
			<div class=" grid_box">		
			<h4><a class="product_name" href="single?pkey=${fp.pkey}">${fp.title}</a> </h4>
							 <a href="${pageContext.request.contextPath}/single?pkey=${fp.pkey}" >
							 <img src="${pageContext.request.contextPath}/temp/img/${sUrl[0]}" class="img-responsive" style=" height: 270px; width: 250px;" alt="">
							 	<div class="zoom-icon">
									<ul class="in-by">
										<li><h5>sizes:</h5></li>   
									</ul>
						<ul class="in-by-color">
							<li><h5>colors:</h5></li>                   
							<li><span > </span></li>
							<li><span class="color"> </span></li>
						</ul>
						</div> </a> 	
		           </div>
					<!---->
						<div class="grid_1 simpleCart_shelfItem">
							<a href="single?pkey=${fp.pkey}" class="cup item_add"><span class=" item_price" >&#x24;${fp.smrp}  <i> </i> </span></a>
						</div>
					<!---->
				</div>
			</div>
			</c:forEach>
			</c:if>