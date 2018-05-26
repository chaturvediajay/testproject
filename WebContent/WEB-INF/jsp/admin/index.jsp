<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="cache-control" content="max-age=0" />
<META HTTP-EQUIV="Expires" CONTENT="-1">
<meta http-equiv="Pragma" content="no-cache">
<title>Welcome To | kritifab Account</title>
<!-- Favicon-->
<link rel="icon" href="favicon.ico" type="image/x-icon">
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:400,700&amp;subset=latin,cyrillic-ext"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" type="text/css">

<!-- Bootstrap Core Css -->
<link href="../resources/admin/plugins/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<!-- Waves Effect Css -->
<link href="../resources/admin/plugins/node-waves/waves.css"
	rel="stylesheet">

<!-- Animation Css -->
<link href="../resources/admin/plugins/animate-css/animate.css"
	rel="stylesheet">

<!-- Morris Chart Css-->
<link href="../resources/admin/plugins/morrisjs/morris.css"
	rel="stylesheet">

<!-- Custom Css -->
<link href="../resources/admin/css/style.css" rel="stylesheet">

<!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
<link href="../resources/admin/css/themes/all-themes.css"
	rel="stylesheet">
<style type="text/css">
.jqstooltip {
	position: absolute;
	left: 0px;
	top: 0px;
	visibility: hidden;
	background: rgb(0, 0, 0) transparent;
	background-color: rgba(0, 0, 0, 0.6);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000,
		endColorstr=#99000000);
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";
	color: white;
	font: 10px arial, san serif;
	text-align: left;
	white-space: nowrap;
	padding: 5px;
	border: 1px solid white;
	box-sizing: content-box;
	z-index: 10000;
}

.jqsfield {
	color: white;
	font: 10px arial, san serif;
	text-align: left;
}
</style>
</head>
<body class="theme-red">
	<!-- Page Loader -->
	<div class="page-loader-wrapper" style="display: none;">
		<div class="loader">
			<div class="preloader">
				<div class="spinner-layer pl-red">
					<div class="circle-clipper left">
						<div class="circle"></div>
					</div>
					<div class="circle-clipper right">
						<div class="circle"></div>
					</div>
				</div>
			</div>
			<p>Please wait...</p>
		</div>
	</div>

	<!-- #END# Page Loader -->
	<!-- Overlay For Sidebars -->
	<div class="overlay"></div>
	<!-- #END# Overlay For Sidebars -->
	<!-- Search Bar -->
	<div class="search-bar">
		<div class="search-icon">
			<i class="material-icons">search</i>
		</div>
		<input type="text" placeholder="START TYPING...">
		<div class="close-search">
			<i class="material-icons">close</i>
		</div>
	</div>
	<!-- #END# Search Bar -->
	<!-- Top Bar -->
	<!-- brand -->
	<%@include file="nav_bar.jsp"%>
	<!-- End brand -->


	<!-- #Top Bar -->
	<section> <!-- Left Sidebar --> <%@include
		file="left_sidebar.jsp"%> <!-- #END# Left Sidebar -->
	</section>

	<section class="content">
	<div class="container-fluid">
		<div class="block-header">
			<h2>DASHBOARD</h2>
			${featuredPro}
		</div>

		<!-- Widgets -->
		<div class="row clearfix">
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="info-box bg-pink hover-expand-effect">
					<div class="icon">
						<i class="material-icons">playlist_add_check</i>
					</div>
					<div class="content">
						<div class="text">NEW TASKS</div>
						<div class="number count-to" data-from="0" data-to="125"
							data-speed="15" data-fresh-interval="20">125</div>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="info-box bg-cyan hover-expand-effect">
					<div class="icon">
						<i class="material-icons">help</i>
					</div>
					<div class="content">
						<div class="text">NEW TICKETS</div>
						<div class="number count-to" data-from="0" data-to="257"
							data-speed="1000" data-fresh-interval="20">257</div>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="info-box bg-light-green hover-expand-effect">
					<div class="icon">
						<i class="material-icons">forum</i>
					</div>
					<div class="content">
						<div class="text">NEW COMMENTS</div>
						<div class="number count-to" data-from="0" data-to="243"
							data-speed="1000" data-fresh-interval="20">243</div>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<div class="info-box bg-orange hover-expand-effect">
					<div class="icon">
						<i class="material-icons">person_add</i>
					</div>
					<div class="content">
						<div class="text">NEW VISITORS</div>
						<div class="number count-to" data-from="0" data-to="1225"
							data-speed="1000" data-fresh-interval="20">1225</div>
					</div>
				</div>
			</div>
		</div>
		<!-- #END# Widgets -->
		<!-- CPU Usage -->

		<!-- #END# CPU Usage -->
		<div class="row clearfix">
			<!-- Visitors -->
			<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				<div class="card">
					<div class="body bg-pink">
						<div class="sparkline" data-type="line" data-spot-radius="4"
							data-highlight-spot-color="rgb(233, 30, 99)"
							data-highlight-line-color="#fff"
							data-min-spot-color="rgb(255,255,255)"
							data-max-spot-color="rgb(255,255,255)"
							data-spot-color="rgb(255,255,255)" data-offset="90"
							data-width="100%" data-height="92px" data-line-width="2"
							data-line-color="rgba(255,255,255,0.7)"
							data-fill-color="rgba(0, 188, 212, 0)">
							<canvas width="270" height="92"
								style="display: inline-block; width: 270px; height: 92px; vertical-align: top;"></canvas>
						</div>
						<ul class="dashboard-stat-list">
							<li>TODAY <span class="pull-right"><b>1 200</b> <small>USERS</small></span>
							</li>
							<li>YESTERDAY <span class="pull-right"><b>3 872</b> <small>USERS</small></span>
							</li>
							<li>LAST WEEK <span class="pull-right"><b>26 582</b>
									<small>USERS</small></span>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- #END# Visitors -->
			<!-- Latest Social Trends -->
			<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				<div class="card">
					<div class="body bg-cyan">
						<div class="m-b--35 font-bold">LATEST SOCIAL TRENDS</div>
						<ul class="dashboard-stat-list">
							<li>#socialtrends <span class="pull-right"> <i
									class="material-icons">trending_up</i>
							</span>
							</li>
							<li>#materialdesign <span class="pull-right"> <i
									class="material-icons">trending_up</i>
							</span>
							</li>
							<li>#adminbsb</li>
							<li>#freeadmintemplate</li>
							<li>#bootstraptemplate</li>
							<li>#freehtmltemplate <span class="pull-right"> <i
									class="material-icons">trending_up</i>
							</span>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- #END# Latest Social Trends -->
			<!-- Answered Tickets -->
			<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				<div class="card">
					<div class="body bg-teal">
						<div class="font-bold m-b--35">ANSWERED TICKETS</div>
						<ul class="dashboard-stat-list">
							<li>TODAY <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
							</li>
							<li>YESTERDAY <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
							</li>
							<li>LAST WEEK <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
							</li>
							<li>LAST MONTH <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
							</li>
							<li>LAST YEAR <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
							</li>
							<li>ALL <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- #END# Answered Tickets -->
		</div>

		<div class="row clearfix">
			<!-- Task Info -->
			<a href="${pageContext.request.contextPath}/logout">Logout</a>
			<!-- #END# Task Info -->
			<!-- Browser Usage -->

			<!-- #END# Browser Usage -->
		</div>
	</div>
	</section>

	<!-- Js Files -->
	<%@include file="js_files.jsp"%>
	<!-- #END# Js Files -->


</body>
</html>