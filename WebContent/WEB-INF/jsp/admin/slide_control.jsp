<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>Welcome To | kritifab</title>
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
		<div class="block-header"></div>
		<div class="row clearfix">
			<div class="title m-b-2">
				<span>Change Password</span>
			</div>
			<div class="row">
				<div class="col-xs-12">
					&#8201;
					<table id="example" class="display" width="100%">
					</table>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!-- Js Files -->
	<%@include file="../admin/js_files.jsp"%>
	<!-- #END# Js Files -->
	<link
		href="${pageContext.request.contextPath}/dt/css/jquery.dataTables.min.css"
		rel="stylesheet" type="text/css" />

	<script
		src="${pageContext.request.contextPath}/dt/js/jquery.dataTables.min.js"
		type="text/javascript"></script>

	<script
		src="${pageContext.request.contextPath}/dt/js/dataTables.buttons.min.js"
		type="text/javascript"></script>

	<script>
		var num = 0;
		$(document).ready(
				function() {
					var num = 0;
					callDattableMethod('1', '1');
					// Handle click on "Select all" control
					$('#example-select-all').on(
							'click',
							function() {
								// Get all rows with search applied

								var rows = table.rows({
									'search' : 'applied'
								}).nodes();
								// Check/uncheck checkboxes for all rows in the table
								$('input[type="checkbox"]', rows).prop(
										'checked', this.checked);
							});

					$('#table input[type="checkbox"]').click(function() {
						console.log('suggested-in-comment', 'click');
					});

				});
		var columnData = [
				{
					"title" : "<th><input type=\"checkbox\" name=\"select_all\" value=\"1\" id=\"example-select-all\"></th>",
					"render" : function(data, type, row, meta) {
						num = num + 1;
						num = meta.row + meta.settings._iDisplayStart + 1;
						console.log((row[2]==1)+'  '+row[2]);
						if(row[2]==1)
						   return '<input type="checkbox" onclick=\"UpdateIdinHF(this);\" name="id[]" value="'
								+ $('<div/>').text(row[0]).html() + '" checked>';
						else
						   return '<input type="checkbox" onclick=\"UpdateIdinHF(this);\" name="id[]" value="'
								+ $('<div/>').text(row[0]).html() + '">';			
					}
				},
				{
					"title" : "Name",
					"render" : function(data, type, row, meta) {

						return row[1];
					}
				},
				 ];

		var buttonData = [];
		var callDattableMethod = function(p1, p2) {
			console.log('callDattableMethod');
			var d=new Object();
			var id='#example';
		    d.p1 = p1;
		    d.p2 = p2;
		    d.cas = 'dfdfdf';
			callDataTableWith(d,id,getUrl('/getProductList'),'get',columnData,[]);

		}
		function UpdateIdinHF(obj) {
			var id = $(obj).attr('id');
			var name = $(obj).attr('name');
			var value = $(obj).attr('value');
			var IsChecked = $(obj).is(':checked');
			var js1 = new Object();
			js1.name = name;
			js1.value = value;
			js1.IsChecked = IsChecked;
			
			console.log(JSON.stringify(js1));
			alert()
			js1.opt = 'update_slide';
				var json = {
					'json' : JSON.stringify(js1)
				};
				//ajaxRequest(json, 'get', '/admin/${sessionScope.admin}/menus',divTest);
				ajaxCallRequestResponse('json', json, 'post',
						getUrl('/slide_control'), divTest);
			 

			// callDattableMethod();
		}
		function divTest(data) {
		callDattableMethod('1', '1');
			//var obj = JSON.stringify(data);
			//var jsonobject = JSON.parse(obj);

			//console.log(JSON.parse(jsonobject.data));
			//console.log('data load   ' + data);
		}
	</script>

</body>
</html>