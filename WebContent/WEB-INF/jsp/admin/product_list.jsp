<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="cache-control" content="max-age=0" />
<META HTTP-EQUIV="Expires" CONTENT="-1">
<meta http-equiv="Pragma" content="no-cache">
<title>Welcome To | kritifab Account | product list</title>
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
<link	href="../resources/dt/css/jquery.dataTables.min.css"	rel="stylesheet" type="text/css" />
</head>
<body class="theme-red">
	<c:set var="rand"
		value="<%=java.lang.Math.round(java.lang.Math.random() * 1000000)%>"
		scope="session"></c:set>
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
			
		</div>
		<!-- #END# Widgets -->
		<!-- CPU Usage -->

		<!-- #END# CPU Usage -->
		<div class="row clearfix">
			<!-- Visitors -->
			<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
		<table id="example" class="display" width="100%">
			
		</table>
			</div>
			<!-- #END# Visitors -->
			<!-- Latest Social Trends -->
			<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
					
			</div>
			<!-- #END# Latest Social Trends -->
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
 <input type="hidden" name="pval" value="${rand}"> 
	<!-- Js Files -->
	<%@include file="js_files.jsp"%>
	<!-- #END# Js Files -->
<script
	src="${pageContext.request.contextPath}/dt/js/jquery.dataTables.min.js"	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/dt/js/dataTables.buttons.min.js"	type="text/javascript"></script>
	
	<script>
	 $(document).ready(function(){	
		 callDattableMethod('1','1');
	 });
		var columnData=[ 
{"title" : "Product Name","render" : function(data, type, row, meta) {return row[1];}},

{"title" : "view","render" :  function(data, type, row, meta) {  
	
	return "<a href=\"addProduct?pkey="+row[0]+"&scope="+$('input[name=pval]').val()+'"'+" class=\"btn btn-success\" >details</a>";
	} },  
		  ];
	
		 var buttonData=[];
		var callDattableMethod=function(p1,p2,cas){
			var d=new Object();
			var id='#example';
		    d.p1 = p1;
		    d.p2 = p2;
		    d.cas = 'dfdfdf';
			callDataTableWith(d,id,getUrl('/getProductList'),'get',columnData,buttonData);
			
		}
	 
	</script>	
</body>
</html>