<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- head -->
<%@include file="adminHead.jsp"%>
<!-- End head -->
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
	<!-- Right Sidebar --> <%@include file="right_sidebar.jsp"%>
	<!-- #END# Right Sidebar --> </section>

	<section class="content">
	<div class="container-fluid">

		<!-- Widgets -->

		<!-- #END# Widgets -->
		<!-- CPU Usage -->

		<!-- #END# CPU Usage -->
		<div class="row clearfix">
<c:forEach items="${error}" var="element"> 
   ${element}<br />
</c:forEach>

			<div class="col-md-12 md-margin-bottom-40">
				<div class="headline">
					<h2>New Product Entry</h2>
				</div>

				<form id="product_submit" method="post">
					<div class="row">
						<div class="col-sm-12">
							<div class="form-group">
								${PostData} <label for="categories">Select Categories:</label> <select
									class="form-control" id="categories"
									onchange="changeCate('someselect','menu1','categories')"
									name="categories">
									<option value="">select</option>
									<c:forEach var="menus" items="${menu}">

										<option value="${menus.m1id}">${menus.menu}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="form-group">
								<label for="categories">Choose Company</label> <select
									class="form-control" id="someselect" name="someselect"
									onchange="changeCate('modelselect','menu2','someselect')">
									<option value="">Select</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="form-group">
								<label for="categories">Product Model</label> <select
									class="form-control" id="modelselect" name="modelselect"
									onemptied="please select">
									<option value="">Select</option>
								</select>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-12">
							<div class="form-group">
								<label for="categories">Enter Product Title</label> <input
									class="form-control" type="text" id="title" name="title">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="panel-body">
							<div class="form-group">
								<div class="col-xs-2">
									<input type="text" class="form-control" name="length"
										placeholder="length" />
								</div>
								<div class="col-xs-2">
									<input type="text" class="form-control" name="width"
										placeholder="width" />
								</div>
								<div class="col-xs-2">
									<input type="text" class="form-control" name="height"
										placeholder="height" />
								</div>
								<div class="col-xs-2">
									<input type="text" class="form-control" name="weight"
										placeholder="weight" />
								</div>
							</div>
							<font face="verdana" size="2"><div id="message"></div></font> <input
								type="submit" class="btn btn-primary" value="Save changes">

						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>
	<!-- Js Files -->
	<%@include file="js_files.jsp"%>
	<!-- #END# Js Files -->
	<script type="text/javascript">
	var select='';
	function changeCate(divId, categories, cateid) {
		var id = $('#' + cateid + ' :selected').val();
		$select = $("#" + divId);
		$select.find("option").remove();
		var js1 = new Object();
		js1.id = id;
		js1.select = categories;
		js1.opt="opt.retrive";
		if (id >= 0) {
			var json = {
				'json' : JSON.stringify(js1)
			};
			//ajaxRequest(json, 'get', '/admin/${sessionScope.admin}/menus',divTest);
			ajaxCallRequestResponse('json', json,'post',getUrl('/addMenus'),divTest);
		} else
			alert("no data found");
	}
	function divTest(data) {
		$select.find("option").remove();
		var obj = JSON.stringify(data);
		var jsonobject = JSON.parse(obj);
		console.log(JSON.parse(jsonobject.data));
		$.each(JSON.parse(jsonobject.data), function(key, value) {
				   $("<option>").val(key).text(value).appendTo(
							$select);
		});

		select='';
	}
	</script>
	<script type="text/javascript">
	$(function() {
		console.log('ajay');
		jQuery.validator.addMethod("lettersonly", function(value, element) {
			return this.optional(element) || /[a-zA-Z]+$/i.test(value);
		}, "Letters only please with no space");
		jQuery.validator.addMethod("noSpace", function(value, element) {
			return value.indexOf(" ") < 0 && value != "";
		}, "No space please and don't leave it empty");
		jQuery.validator.addMethod("categories", function(value, element) {
			return this.optional(element) || value != -1;
		}, "No space please and don't leave it empty");
		jQuery.validator.addMethod("someselect", function(value, element) {
			return this.optional(element) || value != -1 || value != 0;
		}, "No space please and don't leave it empty");
		jQuery.validator.addMethod("modelselect", function(value, element) {
			return this.optional(element) || value != -1;
		}, "No space please and don't leave it empty");
		var ruleCall ={ title : "required",
				length : { required : true, noSpace : true,digits: true }, 
				width : { required : true,digits: true } ,
				height : { required : true,digits: true },
				weight : { required : true,digits: true },
				categories:{required : true,categories : true},
				someselect:{required : true,someselect : true},
				modelselect:{required : true,modelselect : true},};
		var msg ={
				title : "Enter title",  
				length : "Enter length in digit", 
				width : "Enter width in digit", 
				height : "Enter height in digit", 
				weight : "Enter weight in digit", 
				categories : "select Category1", 
				someselect : "select Category2", 
				modelselect : "select Category3"};  
		validatorAjax("#product_submit", ruleCall, msg, 0);  });
		function submitForm(form,id){
				  form.submit();
		}
		// number: true
	</script>

</body>
</html>