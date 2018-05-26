<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- head -->
<%@include file="adminHead.jsp"%>
<link href="../resources/dt/css/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css" />
<!-- End head -->
<body class="theme-red">
	${error} <c:remove var="img" />
	<c:set var="rand"
		value="<%=java.lang.Math.round(java.lang.Math.random() * 100000)%>"
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
	<!-- Right Sidebar --> <%@include file="right_sidebar.jsp"%>
	<!-- #END# Right Sidebar --> </section>

	<section class="content">
	<div class="container-fluid">
		<!-- Widgets -->
		<!-- #END# Widgets -->
		<!-- CPU Usage -->

		<!-- #END# CPU Usage -->
		<div class="row clearfix">


			<div class="col-md-12 md-margin-bottom-60">
				<div class="headline">
					<h2>New Product Entry</h2>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<form id="product" method="post">

							<div class="row clearfix">
								<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
									<div class="info-box bg-pink hover-expand-effect">
										<div class="content">

											<div class="text">Menu-1</div>
											<div class="form-group">
												<div id="somediv">${error}</div>
												${pro.tM1}
											</div>

										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
									<div class="info-box bg-cyan hover-expand-effect">
										<div class="content">
											<div class="text">Menu-2</div>
											<div class="form-group">${pro.tM2}</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
									<div class="info-box bg-light-green hover-expand-effect">
										<div class="content">
											<div class="text">Menu-3</div>
											<div class="form-group">${pro.tM3}</div>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label for="size">Select Size</label> <select
											class="form-control" name="size">
											<option value="">Select</option>
											<option value="x">X</option>
											<option value="xl">XL</option>
											<option value="xxl">XXL</option>
											<option value="full">full</option>
										</select>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label for="color">Select Color</label> <select
											class="form-control" name="color">
											<option value="">Select</option>
											<option value="WHITE">WHITE</option>
											<option value="SILVER">SILVER</option>
											<option value="GRAY">GRAY</option>
											<option value="BLACK">BLACK</option>
											<option value="RED">RED</option>
											<option value="MAROON">MAROON</option>
											<option value="YELLOW">YELLOW</option>
											<option value="OLIVE">OLIVE</option>
											<option value="LIME">LIME</option>
											<option value="GREEN">GREEN</option>
											<option value="AQUA">AQUA</option>
											<option value="TEAL">TEAL</option>
											<option value="BLUE">BLUE</option>
											<option value="NAVY">NAVY</option>
											<option value="FUCHSIA">FUCHSIA</option>
											<option value="PURPLE">PURPLE</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label for="mrp">Market Price:-</label> <input
											class="form-control" name="mrp" type="text"
											placeholder="Enter Market Price"
											data-onload="isValidInput('txtcategories','')">
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label for="smrp">Selling Price:-</label> <input
											class="form-control" name="smrp" type="text"
											placeholder="Enter Sell price include tax"
											data-onload="isValidInput('txtcategories','')">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label for="qty">Quantity:-</label> <input
											class="form-control" name="qty" type="text"
											placeholder="Enter Quantiy"
											data-onload="isValidInput('txtcategories','')">
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label for="sample">Sample:-</label> <input
											class="form-control" name="sample" type="text"
											placeholder="Blank "
											data-onload="isValidInput('txtcategories','')">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="form-group">
										<label for="description">Description:-</label>
										<textarea class="form-control" rows="5"
											placeholder="please write description about product..."
											name="description"></textarea>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<label for="sample">Upload Image:-</label> <input
										class="lowercase input-file uniform_on" id="datafile"
										type="file" name="datafile" />
									<div id="upload" style="display: none;">Uploading..</div>
									<div id="imgivew"></div>
									<small id="fileHelp" class="form-text text-muted">Attach
										photograph related to products.</small>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2">
									<input type="hidden" name="pkey" value="${pro.pkey}"> <label
										for="submit">Submit</label> <input class="btn btn-default"
										type="submit" value="submit">
								</div>
							</div>
						</form>
					</div>
					<div class="col-sm-6">
						Product List
						<table id="example" class="display" width="100%"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>

	<!-- Js Files -->
	<%@include file="js_files.jsp"%>
	<!-- #END# Js Files -->

	<!-- ajax fileupload -->
	<script
		src="${pageContext.request.contextPath}/js/jquery.ajaxfileupload.js"></script>
	<script
		src="${pageContext.request.contextPath}/dt/js/jquery.dataTables.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/dt/js/dataTables.buttons.min.js"
		type="text/javascript"></script>

	<script type="text/javascript">
$(function() {
	jQuery.validator.addMethod("lettersonly", function(value, element) {
		return this.optional(element) || /[a-zA-Z]+$/i.test(value);
	}, "Letters only please with no space");
	jQuery.validator.addMethod("noSpace", function(value, element) {
		return value.indexOf(" ") < 0 && value != "";
	}, "No space please and don't leave it empty");
	jQuery.validator.addMethod("size", function(value, element) {
		return this.optional(element) || value != -1;
	}, "No space please and don't leave it empty");
	jQuery.validator.addMethod("color", function(value, element) {
		return this.optional(element) || value != -1 || value != 0;
	}, "No space please and don't leave it empty");
	jQuery.validator.addMethod("modelselect", function(value, element) {
		return this.optional(element) || value != -1;
	}, "No space please and don't leave it empty");
	var ruleCall ={ title : "required",
			description : { required : true, noSpace : false }, 
			qty : { required : true,number: true } ,
			mrp : { required : true,number: true },
			smrp : { required : true,number: true },
			size:{required : true,size : true},
			color:{required : true,color : true},};
	var msg ={
			description : "Enter description",  
			qty : "Enter length in digit", 
			smrp : "Enter smrp in digit", 
			mrp : "Enter mrp in digit", 
			weight : "Enter weight in digit", 
			size : "Enter size", 
			color : "enter color"};  
	validatorAjax("#product", ruleCall, msg, 0);  });
	function submitForm(form,id){
			  form.submit();
	}
	$(document).ready(
			function(){
				$('input[type="file"]').ajaxfileupload({
			   	      'action': '<%=request.getContextPath()%>/UploadFile?cate=multiple',
												'type' : 'post',
												'onComplete' : function(
														response) {
													$('#upload').hide();
													$('#imgivew').empty();
													$
															.each($.parseJSON(response.url),
																	function(
																			index,
																			value) {
																		jQuery(
																				"#imgu")
																				.attr(
																						'src',
																						'../temp/img/'
																								+ value);
																		$(
																				"#datafile")
																				.val(
																						'');
																		alert(value);
																		$(
																				'#imgivew')
																				.val(
																						value);
																		$(
																				'#imgivew')
																				.prepend(
																						'<img ng-model="docUrl" ng-init="docUrl='+value+'" width="100px" height="50px" alt="document upload" id="docUrl" name="docUrl" class="img-circle img-responsive"  src="../temp/img/'+value+'" />');
																	});
												},
												'onStart' : function() {
													$('#upload').show();
												}
											});
						});
		// number: true
	</script>

	<script>
		$(document).ready(function() {
			callDattableMethod(getParameterByName('pkey'), '1');
		});
		var columnData = [ {
			"title" : "color",
			"render" : function(data, type, row, meta) {
				return row.color;
			}
		}, {
			"title" : "size",
			"render" : function(data, type, row, meta) {
				return row.size;
			}
		}, {
			"title" : "MRP",
			"render" : function(data, type, row, meta) {
				return row.mrp;
			}
		}, {
			"title" : "SMRP",
			"render" : function(data, type, row, meta) {
				return row.smrp;
			}
		}, {
			"title" : "count",
			"render" : function(data, type, row, meta) {
				return row.count;
			}
		}, {
			"title" : "details",
			"render" : function(data, type, row, meta) {
				return '<a href="description?pkey=' + row.pkey+row.pdid + '">view</a>';
			}
		}, ];
		var buttonData = [];
		var callDattableMethod = function(p1, p2) {
			var d = new Object();
			var id = '#example';
			d.p1 = p1;
			d.p2 = p2;
			d.cas = 'dfdfdf';
			callDataTableWith(d, id, getUrl('/getProductListWithPkey'), 'get',
					columnData, []);

		}
	</script>


</body>
</html>