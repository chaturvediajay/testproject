<div class="header">
	<div class="top-header" style="padding: 1em 0px 1em;">		
		<div class="container">
		<div class="top-head">
			<div class="header-para">
				<ul class="social-in">
					<li><a href="#"><i> </i></a></li>						
					<li><a href="https://www.facebook.com/HomeFurnisinghRugsCarpets/?ref=br_rs"><i class="ic"> </i></a></li>
					<li><a href="#"><i class="ic1"> </i></a></li>
					
				</ul>			
			</div>	
			
			<ul class="header-in">
				<li><a href="${pageContext.request.contextPath}/products">  brands</a></li>
				<li><a href="${pageContext.request.contextPath}/aboutUs">about us</a> </li>
				<li><a href="${pageContext.request.contextPath}/contactUs">   contact us</a></li>
				<li><a href="${pageContext.request.contextPath}/login">login</a></li>
			</ul>
			<div class="search-top">
				<div class="search">
					<form>
						<input type="text" value="search about something ?" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'search about something ?';}">
						<input type="submit" value="">
					</form>
				</div>
				<div class="world">
					<ul>
						<li><a href="#"><span> </span></a> </li>
						<li><select class="in-drop">
							  <option>EN</option>
							</select>
						</li>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
				<div class="clearfix"> </div>
		</div>
		</div>
	</div>
		<!---->
	
		<div class="header-top" style=" padding-top: 12px; padding-bottom: 12px;">
		
		
		
		
		<div class="container">
		<div class="head-top">
			<div class="logo">
				<a href="${pageContext.request.contextPath}/"><img class="img-responsive" src="/chouhanrugs/images/logo.png" alt="" style="
     height: 43px;
    margin-left: -10px;
    margin-bottom: -68px;
    margin-top: -11px;
    "></a>

   
			</div>
		
		
		
		<%@include file="menus.jsp"%>
		
			<div class="cart box_1" id="cartBox">
			
			<h3> <div class="total">
							<a href="${pageContext.request.contextPath}/cart" class="simpleCart_empty">${currency_dollar}<span class="simpleCart_total1"></span>
							
							<%--  (<span id="simpleCart_quantity" class="simpleCart_quantity1"></span> items)--%></div>
							<img src="images/cart.png" alt=""></h3>
						</a>
						<%-- <p><a href="cart" class="simpleCart_empty"><img src="images/cart-c.png" alt=""></a></p>  --%>
						<div class="clearfix"> </div>
						
						
					</div>
				<div class="clearfix"> </div>
		</div>
		</div>
	</div>
</div>