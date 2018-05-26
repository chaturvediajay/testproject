<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- head -->
<%@include file="adminHead.jsp"%>
<link href="../resources/dt/css/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css" />
<link href="../resources/admin/css/description.css" rel="stylesheet"
	type="text/css" />
<!-- End head -->
<body class="theme-red">
	${error}

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

	<form method="post">
		<c:forEach var="fp" items="${product_desc}">
			<c:set var="sUrl" value="${fn:split(fp.url,',')}" />
			
			<div class="container-fliud">
				<div class="wrapper row">
					<div class="preview col-md-6">

						<c:if test="${fn:length(sUrl) > 0}">
							<img src="${pageContext.request.contextPath}/temp/img/${sUrl[0]}" 
							alt="${fp.title}" class="img-responsive center-block" "="" style="height: 370px;">
						</c:if>
						<div class="preview-pic tab-content">
							<%--  
					
						
			<ul class="preview-thumbnail nav nav-tabs">
						  <li class="active"><a data-target="#pic-1" data-toggle="tab"><img src="http://placekitten.com/200/126" /></a></li>
						  <li><a data-target="#pic-2" data-toggle="tab"><img src="http://placekitten.com/200/126" /></a></li>
						  <li><a data-target="#pic-3" data-toggle="tab"><img src="http://placekitten.com/200/126" /></a></li>
						  <li><a data-target="#pic-4" data-toggle="tab"><img src="http://placekitten.com/200/126" /></a></li>
						  <li><a data-target="#pic-5" data-toggle="tab"><img src="http://placekitten.com/200/126" /></a></li>
						</ul>				
				<div class="tab-pane active" id="pic-1"><img src="${pageContext.request.contextPath}/${sUrl[0]}" style="height: 400px;"/></div>
			
						  <div class="tab-pane" id="pic-2"><img src="http://placekitten.com/400/252" /></div>
						  <div class="tab-pane" id="pic-3"><img src="http://placekitten.com/400/252" /></div>
						  <div class="tab-pane" id="pic-4"><img src="http://placekitten.com/400/252" /></div>
						  <div class="tab-pane" id="pic-5"><img src="http://placekitten.com/400/252" /></div> --%>
						</div>
						
						<ul class="preview-thumbnail nav nav-tabs">
						
						
						<c:set var="num" value="3" />
						
						<c:forEach var="i" begin="0" end="${fn:length(sUrl)-1}">
								<li><a data-target="#pic-${num}" data-toggle="tab">
								<img src="${pageContext.request.contextPath}/temp/img/${sUrl[i]}" style=" height: 100px;"></a>
								<c:set var="num" value="${num+1}" />
							</li>
						</c:forEach>
						</ul>

					</div>
					<div class="details col-md-6">
						<form method="post">
							<h3 class="product-title">${fp.title}</h3>
							<div class="rating">
								<div class="stars">
									<span class="fa fa-star checked"></span> <span
										class="fa fa-star checked"></span> <span
										class="fa fa-star checked"></span> <span class="fa fa-star"></span>
									<span class="fa fa-star"></span>
								</div>
								<span class="review-no">41 reviews</span>
							</div>
							<p class="product-description">${fp.description}</p>
							<h4 class="price">
								current price: <span>${fp.mrp}</span> <input id="tmrp"
									type="text" onkeypress="return isNumber(event)" value="0"
									size="4">

							</h4>
							<h4 class="price">
								Selling price: <span>${fp.smrp}</span> <input id="tsmrp"
									type="text" onkeypress="return isNumber(event)" value="0"
									size="4">
							</h4>
							<h5 class="sizes">
								sizes: <span class="size" data-toggle="tooltip"
									title="${fp.size}">${fp.size}</span>
							</h5>
							<h5 class="colors">
								colors: <span class="color green not-available"
									data-toggle="tooltip" title="avaiable">${fp.color}</span>
							</h5>
							<div class="action">
								<c:if test="${fp.visible==1}">
									<input type="submit" name="submitButton"
										onclick="enableDisable('disable')" class="add-to-cart btn" value="Disable">
								</c:if>
								<c:if test="${fp.visible==0}">
									<input type="submit" name="submitButton"
										onclick="enableDisable('enable')" class="add-to-cart btn" value="Enable">
								</c:if>

							</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<input type="hidden" name="pvid" value="${fp.visible}" /> 
		<input type="hidden" name="update" value="pv" />
	    <input type="hidden" name="pkey" value="pv" />
	</form>
	<input type="submit" name="updateButton" class="add-to-cart btn"
		onclick="UpdateIdinHF('field_update')" value="update"> </section>


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
		function UpdateIdinHF(opti) {
			var bol = false;
			//var id = $(obj).attr('id');
			//var name = $(obj).attr('name');
			//var value = $(obj).attr('value');
			//var IsChecked = $(obj).is(':checked');
			var js1 = new Object();
			//js1.name = name;
			//js1.value = value;
			js1.pkey = getUrlParameter('pkey');
			
			if ('field_update' == opti) {
				js1.smrp = $('#tsmrp').val();
				js1.mrp = $('#tmrp').val();

				if (js1.smrp > 0 & js1.mrp > 0) {
					console.log(bol+'  '+(js1.smrp <= js1.mrp));

						console.log(bol+'  '+(js1.smrp <= js1.mrp));
						bol = true;
						console.log(bol+'  '+(js1.smrp <= js1.mrp));
				}
			}
			
			
			if (bol) {
				js1.opt = opti;
				var json = {
					'json' : JSON.stringify(js1)
				};
				//ajaxRequest(json, 'get', '/admin/${sessionScope.admin}/menus',divTest);
				ajaxCallRequestResponse('json', json, 'post',
						getUrl('/description'), divTest);
			}
			

			// callDattableMethod();
		}
		function divTest(data) {
			var obj = JSON.stringify(data);
			var jsonobject = JSON.parse(obj);

			alert(jsonobject);
		//	$('.details').html(jsonobject.data);
			 location.reload();

			
		}
		function enableDisable(opti) {
			var bol = false;
			var js1 = new Object();
			js1.pkey = getUrlParameter('pkey');
			
		
			if ('disable' == opti | 'enable' == opti) {
				alert(' js1.pkey='+js1.pkey+'  button value='+opti);
				bol = true;
			}
			
			if (bol) {
				js1.opt = opti;
				var json = {
					'json' : JSON.stringify(js1)
				};
				//ajaxRequest(json, 'get', '/admin/${sessionScope.admin}/menus',divTest);
				ajaxCallRequestResponse('json', json, 'post',
						getUrl('/description'), divTest2);
			}
			

			// callDattableMethod();
		}
		
		function divTest2(data) {
			
		//	$('.details').html(jsonobject.data);
			 location.reload();

			
		}
	</script>

</body>
</html>