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
    <section>
        <!-- Left Sidebar -->
			<%@include file="left_sidebar.jsp"%>
        <!-- #END# Left Sidebar -->
    </section>

    <section class="content">
        <div class="container-fluid">
            <div class="block-header">
                <h2>DASHBOARD</h2>
            </div>

            <!-- Widgets -->
            
            <!-- #END# Widgets -->
            <!-- CPU Usage -->
            
            <!-- #END# CPU Usage -->
            <div class="row clearfix">
               <div class="col-md-12 md-margin-bottom-40">
						<div class="headline">
							<h2>Add Menus</h2>
						</div>
						<div class="row">
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group"><div id="somediv">${error}</div>
										<label for="categories">Select Menu-1</label> <select
											class="form-control" id="categories"
											onchange="changeCate('someselect','menu1','categories')"
											name="categories">
											<option value="0">select</option>
											<c:forEach var="menus" items="${menu}">
												<option value="${menus.m1id}">${menus.menu}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
									<div class="form-group">
										<div class="col-sm-4">
										<label for="categories">Enter new Menu-1</label>
											<input class="form-control" id="txtcategories" type="text"
												data-onload="isValidInput('txtcategories','')"> 
											</div>
										<div class="col-sm-2">
										<label for="categories">Submit new Menu-1</label>
											<input class="btn btn-default" onclick="submitMenu('menu1')"
												type="submit" value="add Menu-1">
										</div>
									</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label for="categories">Select Menu-2</label> <select
											class="form-control" id="someselect"
											onchange="changeCate('modelselect','menu2','someselect')">
											<option value="0">Select</option>
										</select>
									</div>
								</div>
									<div class="form-group">
									<div class="col-sm-4">
									<label for="categories">Enter new Menu-2</label>
										<input class="form-control" id="txtcompany" type="text"
											data-onload="isValidInput('txtcompany','')"> 
										</div>
									<div class="col-sm-2">
									<label for="categories">Submit new Menu-2</label>
										<input
											class="btn btn-default" onclick="submitMenu('menu2')"
											type="submit" value="add Menu-2">
									</div>
									</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label for="categories">Select Menu-3</label> <select
											class="form-control" id="modelselect">
											<option value="0">Select</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-4">
									<label for="categories">Enter new Menu-3</label>
										<input class="form-control"
											data-onload="isValidInput('txtmodel','')" id="txtmodel"
											type="text">
										</div>
									<div class="col-sm-2">
									<label for="categories">Submit new Menu-3</label>
										<input class="btn btn-default"
											onclick="submitMenu('menu3')" type="submit"
											value="add Menu-3">
									</div>
									</div>
							</div>
						</div>
					</div> 
            </div>

            <div class="row clearfix">
                <!-- Task Info -->
                
                <!-- #END# Task Info -->
                <!-- Browser Usage -->
                
                <!-- #END# Browser Usage -->
            </div>
        </div>
    </section>

    	<!-- Js Files -->
        <%@include file="js_files.jsp"%>
        <!-- #END# Js Files -->
        <script type="text/javascript">
		$(function() {
			isValidInput('txtcategories', '');
			isValidInput('txtcompany', '');
			isValidInput('txtmodel', '');
		});
		
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
			//read key
			alert(jsonobject.res);
			$.each(JSON.parse(jsonobject.data), function(key, value) {
				console.log(key + ":" + value)
					   $("<option>").val(key).text(value).appendTo(
								$select);
			});

			select='';

		}
		function submitMenu(categories) {
			var condition = false;
			var cateId = $('#categories option:selected').val();
			var compID = $('#someselect option:selected').val();
			var modelId = $('#modelselect option:selected').val();
			alert('sfdsfsdd');
			$('#somediv').empty();
			var ur =getUrl('/menusAdd?m1id='
					+ checkNull(cateId)
					+ '&m2id='
					+ checkNull(compID)
					+ "&categories.add="
					+ categories
					+ '&m3id='
					+ checkNull(modelId));
			alert(ur);
			if (categories == 'menu1') {
				if ($('#txtcategories').val().trim().length > 0) {
					ur += '&title=' + $('#txtcategories').val().trim();
					condition = true;
				} else
					
					$('#somediv').html("<span class=\"label label-danger\">Enter menu 1</span>");

			} else if (categories == 'menu2') {
				if (cateId > 0) {
					if ($('#txtcompany').val().trim().length > 0) {
						ur += '&title=' + $('#txtcompany').val().trim();
						condition = true;
					} else
						$('#somediv').html("<span class=\"label label-danger\">Enter menu 2</span>");
				} else
					$('#somediv').html("<span class=\"label label-warning\">Choose menu 1</span>");
			} else if (categories = 'menu3') {
				if (compID > 0) {
					if ($('#txtmodel').val().trim().length > 0) {
						ur += '&title=' + $('#txtmodel').val().trim();
						condition = true;
					} else
						$('#somediv')
								.html(
										"<span class=\"label label-danger\">Enter menu 3</span>");
				} else
					$('#somediv')
							.html(
									"<span class=\"label label-warning\">Choose menu 2</span>");
			}
			//'${pageContext.request.contextPath}/admin/${sessionScope.user}/categoriesAdd?id='+id+"&categories.select="+categories;
			if (condition) {

				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : ur,
					//	data : JSON.stringify(Cate),
					//	dataType : 'json',
					timeout : 100000,
					success : function(data) {
						//alert(condition);
						console.log("SUCCESS: ", data);

					},
					error : function(e) {
						console.log("ERROR 456: ", e);
						$('#somediv').html(e.responseText);
						$('#txtcategories').val('');
						$('#txtcompany').val('');
						$('#txtmodel').val('');
						alert(e.responseText);
						location.reload();
					},
					done : function(e) {
						console.log("DONE");
						$('#somediv').html(e.responseText);

					}
				});
			}

		}

		function checkNull(num) {
			if (num < 1)
				return 0;
			else
				return num;

		}
		var getUrl=function(url){
			 var loc = window.location.pathname;
		     loc = loc.substring(0, loc.lastIndexOf('/'));
		     return loc+url;
		}

	</script>


</body>
</html>
