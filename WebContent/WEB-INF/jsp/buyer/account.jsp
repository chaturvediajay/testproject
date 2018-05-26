<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
<title>Welcome To | kritifab </title>
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
		</div>
		<div class="row clearfix">
			<!-- Visitors -->
				<div class="title m-b-2">
				My Profile
				</div>
				<div class="row">&#8201;
					<div class="col-xs-12">
						<ul class="list-group list-group-nav">
							<li class="list-group-item"><strong> Name</strong>
								<p>${regInfom.name}</p></li>
							<li class="list-group-item"><strong>User Name</strong>
								<p>${regInfom.username}</p></li>
						  
							<li class="list-group-item"><strong>Email Address</strong>
								<fieldset id="loginField" disabled="">
								<input type="email" class="input-sm" size="" value="${regInfom.email}" name="email" >
		    						<a href="#" id="clickUpdate">update</a>
								</fieldset></li>
							<li class="list-group-item"><strong>Complete your information</strong>
								<a onclick="addInfo();" class="btn btn-theme pull-right"><i
							class="fa fa-pencil"></i> Edit My Address</a></li>
							<div id="errorForm"></div>
							<div id="addId">
					<li class="list-group-item"><strong>Plot no. / street:-</strong>
					<input type="text" class="form-control" id="street" value="${regInfom.street}"></li>
					<li class="list-group-item"><strong>Complete Address:-</strong>
					<input type="text" class="form-control" id="address" value="${regInfom.address}"></li>
					<li class="list-group-item"><strong>Region / State:-</strong>
					<select class="form-control" id="categories" 
					onchange="changeCate('someselect','pin_delivery_model','categories')" 
					name="state" ng-model="state" ng-init="state='${regInfom.state}'">
					<option value="" >Select</option>
					<c:forEach items="${mistate}" var="entry">
						<option value="${entry.key}"<c:if test="${regInfom.state eq entry.key}">selected="selected"</c:if>>${entry.value}</option>
					</c:forEach>	
					</select></li>
					<li class="list-group-item"><strong>City:-</strong>
					<select class="form-control" id="someselect"
					onchange="getPin('modelselect','pin_delivery_model','someselect')"
					ng-model="city" ng-init="city'${regInfom.city}'">
							<option value="">Select</option>
							<option value="${regInfom.city}"<c:if test="${regInfom.city ne null}">selected="selected"</c:if>>${regInfom.city}</option>
					</select>
					</li>
					<li class="list-group-item"><strong>Pincode:-</strong>
					<select class="form-control" id="mySelect"
					
					ng-model="pincode" ng-init="pincode'${regInfom.pincode}'">
							<option value="">Select</option>
							<option value="${regInfom.pincode}"<c:if test="${regInfom.pincode ne null}">selected="selected"</c:if>>${regInfom.pincode}</option>
					</select>
					</li>
					
				 	<input type="submit" value="submit" onclick="updateShip('updateReg');" class="btn btn-theme pull-right">
					</div>
							
						</ul>
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
	<%@include file="../admin/js_files.jsp"%>
	<!-- #END# Js Files -->
<script type="text/javascript">
		$(document).ready(function() {
			
			getContent(1);
			$('#addId').hide();
			$('input[type="file"]').ajaxfileupload({
		   	      'action': '<%=request.getContextPath()%>/UploadFile?cate=single',	
		   	      'type': 'post', 
		   	  'onComplete': function(response) {	        
		   	        $('#upload').hide();
		   	        $('#imgivew').empty();
		   	         $.each($.parseJSON(response.url), function (index, value) {
		   	        	 console.log('**1***   '+value);
		   	        	 value=value.match(/[-_\w]+[.][\w]+$/i)[0];
		   	        	console.log('**2***   '+value);
		   	        	updateImg('updateImage',value);
		   	                $('#imgivew').prepend('<img width="100px" height="50px" alt="" class="img-circle img-responsive"  src="../temp/img/'+value+'" />');
		   	            });
		   	        
		   	      },
		   	      'onStart': function() {
		   	        $('#upload').show(); 
		   	       
		   	      }
		   	 });
			 
		});
		
		 $('#clickUpdate').on('click', function (e) {
		    	alert('sdfdsf');
		    	var email="input[name='email']" ;
				var isDisabled = $('#loginField').is(':disabled');
				   if (isDisabled) {
					   alert('hello1');
				   //	fieldsetEnableDisable(email,true,'#loginField');
				   	$('#loginField').attr("disabled",false);
				    } else {
				    	alert('hello2');
			    	//fieldsetEnableDisable(email,false,'#loginField');
			    	    updateEmail();
				    	$('#loginField').attr("disabled",true);
				    }
		    });
		function updateEmail(email){
			var email=$("input[name='email']").val().trim();
			if(email.trim()!='' & email!='undefined'){
				var js1=new Object();
				js1.email=email;
				js1.condition='email-update';
				var json={'json':JSON.stringify(js1)} ;
				ajaxCallRequestResponse('json',json,'post','account',output)
			}
			else
				alert('please enter correct email id!');
			}
		

		function output(res){
			var str='';
			for (var key in res) {
			    str+=res[key]+'\n'
			}
			alert(str);
		}
		

		function getContent(divCase) {

			if (divCase == 1) {
				$("#btn1").addClass("active");
				$("#btn2").removeClass("active");
				$("#btn3").removeClass("active");
				$("#btn4").removeClass("active");
				$("#profile").show();
				$("#address").show();
				$("#history").hide();
				$("#password").hide();
			} else if (divCase == 2) {
				$("#btn1").removeClass("active");
				$("#btn2").addClass("active");
				$("#btn3").removeClass("active");
				$("#btn4").removeClass("active");
				$("#address").show();
				$("#history").hide();
				$("#password").hide();
				$("#profile").hide();
			} else if (divCase == 3) {
				$("#btn1").removeClass("active");
				$("#btn2").removeClass("active");
				$("#btn3").addClass("active");
				$("#btn4").removeClass("active");
				$("#address").hide();
				$("#history").show();
				$("#password").hide();
				$("#profile").hide();
			} else if (divCase == 4) {
				$("#btn1").removeClass("active");
				$("#btn2").removeClass("active");
				$("#btn3").removeClass("active");
				$("#btn4").addClass("active");
				$("#address").hide();
				$("#history").hide();
				$("#password").show();
				$("#profile").hide();
			}
		}

		function changePwd() {
			var oldPwd = $("#oldInputPasswd").val().trim();
			var newPwd = $("#newInputPasswd").val().trim();
			var retypeNewPwd = $("#retypeInputPasswd").val().trim();
			var js1 = new Object();
			js1.oldPwd = oldPwd;
			js1.newPwd = newPwd;
			js1.retypeNewPwd = retypeNewPwd;
			if (newPwd == retypeNewPwd) {
				var json = {
					'json' : JSON.stringify(js1)
				};
				//ajaxCall(json, '/changePassword', pInfo);
				ajaxCallRequestResponse('json',json,'get','../changePassword',pInfo);
			} else
				alert('please enter same password!');

		}
		function pInfo(res) {
			var obj = JSON.stringify(res);
			var jsonobject = JSON.parse(obj);
			alert(jsonobject.data);
			if (jsonobject.data != '0')
				$('#pInfo').html(jsonobject.data).slideDown('slow');
			else {
				//$('.row message').hide();
				$('#pInfo').html(
						'<div class="alert alert-danger">'
								+ '<strong>No results found!' + '</div>')
						.slideDown('slow');
			}
		}

		function addInfo() {
			$('#addId').show();
			
			}


		function updateShip(cas) {
			
			var js1 = new Object();
			js1.cas = cas;
			js1.street = $('#street').val().trim();
			js1.address = $('#address').val().trim();
			js1.city = $('select#someselect option:selected').val();
			js1.pincode = $('select#mySelect option:selected').val();
			js1.state =  $('select#categories option:selected').val();
			alert('state='+js1.state+'   city='+js1.city+' pincode='+js1.pincode);
			if (js1.street != '') {
				if (js1.address != '') {
					if (js1.city != '') {
						if ($.isNumeric(js1.pincode) & (js1.pincode.length == 6)) {
							if (js1.state != '') {

								var json = {
										'json' : JSON.stringify(js1)
									};
									ajaxCallRequestResponse('json',json,'get','../updateShipp',div3);
							}
							 else
							$('#errorForm').html('<span class=\"label label-danger\">Insert State!</span>');
						}
						 else
						$('#errorForm').html('<span class=\"label label-danger\">Insert Pincode in numeric!</span>');
					}
					 else
					$('#errorForm').html('<span class=\"label label-danger\">Insert City!</span>');
				}
				 else
				$('#errorForm').html('<span class=\"label label-danger\">Insert complete address!</span>');
			}
			 else
			$('#errorForm').html('<span class=\"label label-danger\">Insert Your plot no.!</span>');
			
			
		}
		function div3(response) {
			var obj = JSON.stringify(response);
			var par = JSON.parse(obj);
			if (par != null) {
					$('#errorForm').html("<span class=\"label label-success\">Success!</span>");
					$('#addId').hide();
				}

			else {
				$('#errorForm').append("<span class=\"label label-info\">please try again!</span>");
			}
			

		}
		function updateImg(cas,url) {
			console.log('url '+url);
			var js1 = new Object();
			js1.cas = cas;
			js1.url = url;
					var json = {
							'json' : JSON.stringify(js1)
						};
				//	ajaxRequest(json, 'get','/updateShipp', div);
					ajaxCallRequestResponse('json',json,'get','../updateShipp',div3);
		}
		function div(response) {
			var obj = JSON.stringify(response);
			var par = JSON.parse(obj);
			if (par != null) {
					$('#errorForm').append("<span class=\"label label-success\">Success!</span>");
					$('#addId').hide();
				}

			else {
				$('#errorForm').append("<span class=\"label label-info\">please try again!</span>");
			}
			

		}
	</script>
	
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
	//https://api.myjson.com/bins/14t4g test?action=list
		
			
	$(document).ready(function() {
		//console.log(getContextPath());
		
		
		//var inputData = [ ["Prakash", "Software Analyst", 5000], ["Akshay", "Software Analyst", 6000] ];

		       //  var exampleTable =;
		       callDattableMethod('0','0');
		          
		
		var table =$('#example1').DataTable({
			"processing" : true,
			"serverSide" : true,
			"pageLength" : 10,
			"autoWidth": false,
			"ajax" : {
				"url" : getUrl('/tdatatable34'),
				"type" : "GET",
				"dataSrc" : function(json) {
					return $.parseJSON(json.data);
				}
			},
			"columns" : [ {
				"data" : "orderId",
				 "render": function (data, type, row, meta) {
				        return '<a href="orderInfo?ordNo=' + data + '">' + data + '</a>';
				    }
			},
		    {"data" : "cDate",
		    "render": function (data) {
		            var date = new Date(data);
		            var month = date.getMonth() + 1;
		            return  date.getDate() + "-" +(month.length > 1 ? month : "0" + month) + "-" + date.getFullYear();
		    
		    }
		    },
		    {"data" : "smrp"},
		    {"data" : "status",
		    	"render": function (data, type, row, meta) {
		    		if(data==0)
			        return 'processing';
		    		else if(data==1)
					        return 'confirm';
		    		else if(data==2)
				        return 'cancel';
			    }
		    
		    }
			],
		});
	
	});
var columnData=[ { "data" : "orderId", "render": function (data, type, row, meta) { return '<a href="orderInfo?ordNo=' + data + '">' + data + '</a>'; }
}, {"data" : "cDate", "render": function (data) { var date = new Date(data); var month = date.getMonth() + 1; return  date.getDate() + "-" +(month.length > 1 ? month : "0" + month) + "-" + date.getFullYear();  }
}, {"data" : "smrp"},
{"data" : "status", "render": function (data, type, row, meta) { if(data==0) return 'processing'; else if(data==1) return 'confirm'; else if(data==2) return 'cancel'; }
 } ];
 
 var buttonData=[ { text: 'My button 1', action: function () { callDattableMethod('maahi','lok'); }
                 }, { text: 'My button 2', action: function () { callDattableMethod('ajay','mahesh'); }
                 }, { text: 'My button 3', action: function () {
 console.log('3'); callDattableMethod('one','two');
 } } ];
var callDattableMethod=function(act,val){
	var d=new Object();
	var id='#example';
    d.act = act;
    d.p1 = val;
    d.p2 = val;
	callDataTableWith(d,id,getUrl('/tdatatable34'),'get',columnData,buttonData);	
}





function changeCate(divId, categories, cateid) {
	$('#mySelect').find('option').remove().end().append('<option value="">Select</option>').val('');
	var id = $('#' + cateid + ' :selected').val();
	$select = $("#" + divId);
	$select.find("option").remove();
	var js1 = new Object();
	js1.id = id;
	js1.select = categories;
	js1.condition='getCity';
		var json = {
			'json' : JSON.stringify(js1)
		};
		ajaxCallRequestResponse('json',json,'post','account',divTest12);
}
function divTest12(data) {
	$select.find("option").remove();
	var obj = JSON.stringify(data);
	var jsonobject = JSON.parse(obj);
	console.log(JSON.parse(jsonobject.data));
	$("<option>").val(0).text('Select').appendTo($select);
	$.each(JSON.parse(jsonobject.data), function(key, value) {
		console.log(key + ":" + value);
			   $("<option>").val(key).text(key).appendTo($select);
			   
	});

	select='';
	
	

}
function getPin(divId, categories, cateid) {
	$('#mySelect').find('option').remove();
	var id = $('#' + cateid + ' :selected').val();
	$select = $("#" + divId);
	$select.find("option").remove();
	var js1 = new Object();
	js1.id = id;
	js1.select = categories;
	js1.condition='getPin';
	js1.state = $('select#categories option:selected').val();
		var json = {
			'json' : JSON.stringify(js1)
		};
		ajaxCallRequestResponse('json',json,'post','account',divTest123);
}
function divTest123(data) {
	$select.find("option").remove();
	var obj = JSON.stringify(data);
	var jsonobject = JSON.parse(obj);
	console.log(JSON.parse(jsonobject.data));
	$('#mySelect').append('<option value="">Select</option>').val();
	
	$.each(JSON.parse(jsonobject.data), function(key, value) {
		console.log(key + ":" + value);
		$('#mySelect').append('<option value="'+key+'">'+value+'</option>').val(key);
			   
	});

	select='';
	
	

}
	
</script>

</body>
</html>