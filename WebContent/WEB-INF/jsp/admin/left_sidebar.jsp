<aside id="leftsidebar" class="sidebar">
	<!-- User Info -->
	<div class="user-info">
		<div class="image">
			<img src="images/user.png" width="48" height="48" alt="User">
		</div>
		<div class="info-container">
			<div class="name" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">${sessionScope.loginSession.name}</div>
			<div class="email">${sessionScope.loginSession.email}</div>
			<div class="btn-group user-helper-dropdown">
				<i class="material-icons" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="true">keyboard_arrow_down</i>
				<ul class="dropdown-menu pull-right">
					<li><a href="javascript:void(0);"
						class=" waves-effect waves-block"><i class="material-icons">person</i>Profile</a></li>
					<li role="seperator" class="divider"></li>
					<li><a href="javascript:void(0);"
						class=" waves-effect waves-block"><i class="material-icons">group</i>Followers</a></li>
					<li><a href="javascript:void(0);"
						class=" waves-effect waves-block"><i class="material-icons">shopping_cart</i>Sales</a></li>
					<li><a href="javascript:void(0);"
						class=" waves-effect waves-block"><i class="material-icons">favorite</i>Likes</a></li>
					<li role="seperator" class="divider"></li>
					<li><a href="${pageContext.request.contextPath}/logout"
						class=" waves-effect waves-block"><i class="material-icons">input</i>Sign
							Out</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- #User Info -->
	<!-- Menu -->
	<div class="menu">
		<div class="slimScrollDiv"
			style="position: relative; overflow: hidden; width: auto; height: 361px;">
			<ul class="list"
				style="overflow: hidden; width: auto; height: 361px;">
				<li class="header">MAIN NAVIGATION</li>
				<li class="active"><a
					href="${pageContext.request.contextPath}/admin/"
					class="toggled waves-effect waves-block"> <i
						class="material-icons">home</i> <span>Home</span>
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/admin/menuEntry"
					class="waves-effect waves-block"> <i class="material-icons">text_fields</i>
						<span>Add Menus</span>
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/admin/productRegistration"
					class="waves-effect waves-block"> <i class="material-icons">text_fields</i>
						<span>Register New Product</span>
				</a></li>

				<li><a
					href="${pageContext.request.contextPath}/admin/product_list"
					class="waves-effect waves-block"> <i class="material-icons">text_fields</i>
						<span>Product List</span>
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/admin/orderList"
					class="waves-effect waves-block"> <i class="material-icons">text_fields</i>
						<span>Order List</span>
				</a></li>
				
				<li><a
					href="${pageContext.request.contextPath}/admin/slide_control"
					class="waves-effect waves-block"> <i class="material-icons">text_fields</i>
						<span>slide control</span>
				</a></li>
				
				
				
				
				
				
				
				
				
				product_list
				<li><a href="${pageContext.request.contextPath}/admin/"
					class=" waves-effect waves-block"> <i class="material-icons">text_fields</i>
						<span>Testing</span>
				</a></li>

				<li><a href="pages/helper-classes.html"
					class=" waves-effect waves-block"> <i class="material-icons">layers</i>
						<span>Helper Classes</span>
				</a></li>
			</ul>
			<div class="slimScrollBar"
				style="background: rgba(0, 0, 0, 0.5); width: 4px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 0px; z-index: 99; right: 1px; height: 140.13px;"></div>
			<div class="slimScrollRail"
				style="width: 4px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 0px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div>
		</div>
	</div>
	<!-- #Menu -->
	<!-- Footer -->
	<div class="legal">
		<div class="copyright">
			© 2016 - 2018 <a href="javascript:void(0);">blagot infotech</a>.
		</div>
		<div class="version">
			<b>Version: </b> 1.0.2
		</div>
	</div>
	<!-- #Footer -->
</aside>