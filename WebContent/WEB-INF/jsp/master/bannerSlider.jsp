
<c:if test="${not empty slide_top}">
	<c:set var="total" value="0" scope="page" />
	<c:set var="proCount" value="${fn:length(featuredPro)}" scope="page" />
	<c:forEach var="fp" items="${slide_top}">
		<c:set var="total" value="${total+1}" scope="page" />
		<c:set var="sUrl" value="${fn:split(fp.url,',')}" />

		<c:if test="${total==1}">
			<article style="position: absolute; width: 100%; opacity: 0;">
				<div class="banner-wrap">
		</c:if>
		<c:if test="${total==1}">
			<div class="banner-top">
		</c:if>
		<c:if test="${total==2}">
			<div class="banner-top banner-bottom">
		</c:if>

		<a href="${pageContext.request.contextPath}/single?pkey=${fp.pkey}">
			<c:if test="${total==1}">
				<div class="banner-top-in">
			</c:if> <c:if test="${total==2}">
				<div class="banner-top-in at">
				
			</c:if> 
			
			<img src="${pageContext.request.contextPath}/temp/img/${sUrl[0]}"
			class="img-responsive" alt="" style=" height: 200px; width: 176px;">
			</div>
		</a>
		<div class="cart-at grid_1 simpleCart_shelfItem">
			<div class="item_add">
				<span class="item_price">${fp.smrp} &#x24;- <i> </i>
				</span>
			</div>
			<div class="off">
				<fmt:parseNumber var="amt" integerOnly="true" type="number"
					value="${fp.mrp-fp.smrp/fp.mrp*100}% " />
				<label>&#x24;${amt} off</label>
				<p>${fp.title}</p>
			</div>
		</div>
		<div class="clearfix"></div>

		</div>
		<c:if test="${total==2}">
			<c:set var="total" value="${total=0}" scope="page" />
			<div class="clearfix"></div>

			</div>
			</article>
		</c:if>

	</c:forEach>

</c:if>