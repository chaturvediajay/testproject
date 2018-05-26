<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<!-- head -->
	<%@include file="head.jsp"%>
<style type="text/css">
label.error {
	font-size: 11px;
	background-color: #cc0000;
	color: #FFFFFF;
	padding: 3px;
	margin-left: 5px;
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
}
</style>
<!-- End head -->
<body> 
<!--header-->	
	<%@include file="header.jsp"%>
<!-- End header -->
<div class="container">
	<div class="col-lg-12 well">
	<h1 class="well text-center" >Add Shipping Address</h1>
	<div class="row">
	<c:forEach items="${error}" var="msg">
    <p>${msg}</p>
</c:forEach>
				<form id="reg" method="post">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label> Shipping Name</label>
								<input name="sName" value="${empty sessionScope.ship.sName?'':sessionScope.ship.sName}" type="text" placeholder="Enter Shipping Name Here.." class="form-control">
							</div>
							<div class="col-sm-6 form-group">
								<label>Street/plot no.:'</label>
								<input name="sStreet" value="${empty sessionScope.ship.sAddress1?'':sessionScope.ship.sAddress1}" type="text" placeholder="Enter plot number Here.." class="form-control">
							</div>
						</div>							
					<div class="row">
							<div class="col-sm-6 form-group">
								<label>Shipping Address</label>
								<input name="sAddress" value="${empty sessionScope.ship.sAddress2?'':sessionScope.ship.sAddress2}" type="text" placeholder="Enter Shipping Address Here.." class="form-control">
							</div>
							<div class="col-sm-6 form-group">
								<label>Contact Number</label>
								<input name="sMobile" value="${empty sessionScope.ship.sMobile?'':sessionScope.ship.sMobile}" type="text" placeholder="Enter Contact Number Here.." class="form-control">
							</div>
					</div>	
					<div class="row">
							<div class="col-sm-6 form-group">
								<label>State</label>
								<select class="form-control" name="sState" >
								<option value="">Select</option>
								<option selected="selected" value="rajasthan">Rajasthan</option>
									</select>
							</div>
							<div class="col-sm-6 form-group">
								<label>City</label>
									<select class="form-control" name="sCity" >
									<option value="">Select</option>
									<option selected="selected" value="jaipur">jaipur</option>
									</select>
							</div>
					</div>	
					<div class="row">
							<div class="col-sm-6 form-group">
								<label>Pincode</label>
								<select class="form-control" name="sPincode" >
								<option value="">Select</option>
								<option selected="selected" value="302020">302020</option>
									</select>
							</div>
					</div>	
					   
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">Payment</button>
                </div>				
					</div>
				</form> 
				</div>
	</div>
	</div>
	<!--footer-->	
	<%@include file="footer.jsp"%>
<!-- End footer -->
<a href="#" id="toTop">To Top</a>
<!--JS-->	
	<%@include file="mainJs.jsp"%>
<!-- End JS -->
	<script>
	$(function() {
		jQuery.validator.addMethod("lettersonly", function(value, element) {
			return this.optional(element) || /[a-zA-Z]+$/i.test(value);
		}, "Letters only please with no space");
		jQuery.validator.addMethod("noSpace", function(value, element) {
			return value.indexOf(" ") < 0 && value != "";
		}, "No space please and don't leave it empty");
		var ruleCall ={ sStreet : "required",sAddress: "required",
				sName : { required : true, lettersonly : true},  pswd : { required : true, minlength : 5 }, 

cpswd : { required : true, minlength : 5, equalTo : "#pswd" }, 
sMobile : { required : true, minlength : 10, maxlength : 10, number : true },
mobile : { required : true, minlength : 10, maxlength : 10, number : true },  };
		var msg ={
				sName : "Please enter Shipping Name", 
				sStreet : "Enter Street Address!",
				sAddress : "Enter Shipping Address",  
				sMobile : { required : "Please enter shipping contact", minlength : " contact must be at least 10 digit", maxlength : " contact must be at least 10 digit", },
				sPincode : { required : "Please provide a your mobile number" }, };  
		validatorAjax("#reg", ruleCall, msg, 0);
	
	

		$('#simpleCart_quantity').text('0');
		$('#simpleCart_total').text('0');
		loadCart();
		

	  }
	
	
	
	
	);
		function submitForm(form,id){
				  form.submit();
		}
	</script>
</body>
</html>