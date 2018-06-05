<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
		<div class="block-header"></div>
		<div class="row clearfix">
			<div class="row">
				<div class="col-xs-12">
					&#8201;

					<div class="panel panel-default">
						<span class="label label-default">Order Information</span>
						<div class="table table-bordered table-responsive table-hover">
							<div id="tab1" style="display: block;">
								<table class="table table-bordered table-responsive table-hover">
									<tbody>
										<tr>
											<th>Product Image</th>
											<th>Product name</th>
											<th>Product Key</th>
											<th>Price</th>
											<th>Selling Price</th>
											<th>Quantity</th>
											<th>Shipping Charge</th>
											<th>Total</th>
										</tr>
										<c:forEach items="${itemDetail}" var="num">
											<tr>
												<td class="image-column"><a target="_blank"
													href="${pageContext.request.contextPath}/single?pkey=${num.pkey}">
														<img
														src="${pageContext.request.contextPath}/temp/img/eVY135tpf6.jpg"
														width="40px" height="40px" alt="juice">
												</a></td>
												<td>${num.title}</td>
												<td>${num.pkey}</td>
												<td>${num.mrp}</td>
												<td>${num.smrp}</td>
												<td>${num.qty}</td>
												<c:set var="sValue"
															value="${sValue=sValue+1}" />
													
													<c:set var="totalValue"
														value="${totalValue=totalValue+num.smrp*num.qty}" />
												<c:if test="${totalValue gt free}">
												<td>&#x24;0</td>
													<td>${num.smrp*num.qty}</td>
												</c:if>
												<c:if test="${totalValue eq free}">
												<td>&#x24;0</td>
													<td><strong>&#x24;Free shipping</strong></td>
												</c:if>
												<c:if test="${totalValue le free}">
												<td>&#x24;${courier}</td>
												<td>${num.smrp*num.qty+courier*num.qty}</td>
												</c:if>
											</tr>
										</c:forEach>

										<tr>
											<td class="align-right" colspan="4"><span
												class="price big">Total</span></td>
											<c:if test="${totalValue gt free}">
												<td><strong>&#x24;${totalValue}</strong></td>
												<td><strong>&#x24;Free shipping</strong></td>
												<td>&#x24;${totalValue}</td>
											</c:if>
											<c:if test="${totalValue eq free}">
												<td><strong>&#x24;${totalValue}</strong></td>
												<td><strong>&#x24;Free shipping</strong></td>
												<td>&#x24;${totalValue}</td>
											</c:if>
											<c:if test="${totalValue le free}">
												<td><strong>&#x24;${totalValue}</strong></td>
												<td><strong>&#x24;${sValue*courier}</strong></td>
												<td>&#x24;${totalValue+sValue*courier}</td>
											</c:if>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>

			</div>
			<div class="row">

				<div class="col-xs-12">
					&#8201;

					<div class="panel panel-default">
						<span class="label label-default">Payment Information</span>
						<div class="table table-bordered table-responsive table-hover">
						<c:forEach items="${orderDetail}" var="innerList">
							<table class="table table-bordered table-hover">
								<tbody>
									<tr>
										<th>Order number</th>
										<td>${innerList[0]} </td>
									</tr>

									<tr>
										<th>Order date</th>
										<td>
										<fmt:formatDate type="both" dateStyle="medium"
												timeStyle="medium" value="${innerList[2]}" /></td>
									</tr>

									<tr>
										<th>Payment status</th>
										<td> 
										 <c:choose>
											<c:when test="${innerList[4] eq 1}">
											       Confirmed
											    </c:when>
												<c:otherwise>
												    pending
												    </c:otherwise>
											</c:choose>
											</td>
									</tr>
									<tr>
										<th>Shipment</th>
										<td  id="shipmentResult">
										 <c:choose>
										 		<c:when test="${innerList[4] eq -1}">
											        
											         <button type="button" onclick="updateOrder(-1,'${innerList[0]}','update_order');" class="btn btn-warning waves-effect">Cancel Order</button>
											       <button type="button" onclick="updateOrder(1,'${innerList[0]}','update_order');" class="btn btn-success waves-effect">Confirm Dispatch</button>
											    </c:when>
												<c:when test="${innerList[4] eq 1}">
											       <c:if test="${innerList[3] eq 0  }">
						                                <button type="button" onclick="updateOrder(1,'${innerList[0]}','update_order');" class="btn btn-success waves-effect">Confirm Dispatch</button>
						                                <button type="button" onclick="updateOrder(-1,'${innerList[0]}','update_order');" class="btn btn-warning waves-effect">Cancel Order</button>
											       </c:if>
											       <c:if test="${innerList[3] eq -1}">
											        	Order Cancel by admin
											       </c:if>
											        <c:if test="${innerList[3] eq 1}">
											       		Order dispatch
											       </c:if>
											    </c:when>
											   <c:when test="${innerList[4] eq 0}">
											        <c:if test="${innerList[3] eq 0}">
						                                <button type="button" onclick="updateOrder(-1,'${innerList[0]}','update_order');" class="btn btn-warning waves-effect">Cancel Order</button>
						                                <button type="button" onclick="updateOrder(1,'${innerList[0]}','update_order');" class="btn btn-success waves-effect">Confirm Dispatch</button>
											       </c:if>
											        <c:if test="${innerList[3] eq -1}">
											        	Order Cancel
											       </c:if>
											       
											    </c:when>
												<c:otherwise>
												    -----
												    </c:otherwise>
											</c:choose>
										
										</td>
									</tr>
									<tr>
										<th>Total</th>
										<td><span class="price">${innerList[1]}</span></td>
									</tr>
								</tbody>
							</table>
</c:forEach>




						</div>
					</div>

				</div>
				<!-- /.row -->
			</div>
			<div class="row">

				<div class="col-xs-12">
					&#8201;

					<div class="panel panel-default">

						<div class="table table-bordered table-responsive table-hover">

							<div class="col-lg-6 col-md-6 col-sm-6">
								<div class="carousel-heading">
									<h4>Bill to</h4>
								</div>
								<table class="orderinfo-table table-responsive">
									<tbody>
										<tr>
											<th>Name</th>
											<td>${regInfo.name}</td>
										</tr>
										<tr>
											<th>Address 1</th>
											<td>${regInfo.street}</td>
										</tr>
										<tr>
											<th>Address 2</th>
											<td>${regInfo.address}</td>
										</tr>
										<tr>
											<th>ZIP / Postal code</th>
											<td>${regInfo.pincode}</td>
										</tr>

										<tr>
											<th>City</th>
											<td>${regInfo.city}</td>
										</tr>
										<tr>
											<th>State</th>
											<td>${regInfo.state}</td>
										</tr>
										<tr>
										<tr>
											<th>Country</th>
											<td>India</td>
										</tr>
										<tr>
											<th>Phone</th>
											<td>+91-${regInfo.mobile}</td>
										</tr>
									</tbody>
								</table>

							</div>

							<div class="col-lg-6 col-md-6 col-sm-6">

								<div class="carousel-heading">
									<h4>Ship to</h4>
								</div>

								<table class="orderinfo-table">
									<tbody>
										<tr>
											<th>Name</th>
											<td>${shippingAdd.sName}</td>
										</tr>
										<tr>
											<th>Address 1</th>
											<td>${shippingAdd.sAddress1}</td>
										</tr>
										<tr>
											<th>Address 2</th>
											<td>${shippingAdd.sAddress2}</td>
										</tr>
										<tr>
											<th>ZIP / Postal code</th>
											<td>${shippingAdd.sPincode}</td>
										</tr>

										<tr>
											<th>City</th>
											<td>${shippingAdd.sCity}</td>
										</tr>
										<tr>
											<th>State</th>
											<td>${shippingAdd.sState}</td>
										</tr>
										<tr>
										<tr>
											<th>Country</th>
											<td>India</td>
										</tr>
										<tr>
											<th>Phone</th>
											<td>+91-${shippingAdd.sMobile}</td>
										</tr>
									</tbody>
								</table>
							</div>


						</div>
					</div>

				</div>
				<!-- /.row -->
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
	<link
		href="${pageContext.request.contextPath}/dt/css/buttons.dataTables.min.css"
		rel="stylesheet" type="text/css" />


	<script
		src="${pageContext.request.contextPath}/dt/js/jquery.dataTables.min.js"
		type="text/javascript"></script>

	<script
		src="${pageContext.request.contextPath}/dt/js/dataTables.buttons.min.js"
		type="text/javascript"></script>

	<script type="text/javascript">
	function updateOrder(value,orderId,con){
			var js1=new Object();
			js1.value=value;
			js1.condition=con;
			js1.orderId=orderId;
			var json={'json':JSON.stringify(js1)} ;
			ajaxCallRequestResponse('json',json,'post','orderInfo',output);
	
	}
	function output(res){
		var str='';
		alert(res.data);
		for (var key in res) {
		    str+=res[key]+'\n'
		}
		 document.getElementById('shipmentResult').innerHTML = str;
	}
	
		
	</script>
</body>
</html>