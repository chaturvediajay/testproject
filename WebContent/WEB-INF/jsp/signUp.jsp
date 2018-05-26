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
	<h1 class="well text-center" >create your account</h1>

	<c:forEach items="${error}" var="element"> 

   ${element}<br />
  
</c:forEach>
	<div class="row">
				<form id="reg" method="post">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label> Name</label>
								<input name="name" type="text" placeholder="Enter First Name Here.." class="form-control">
							</div>
							<div class="col-sm-6 form-group">
								<label>User Name</label>
								<input name="username" type="text" placeholder="Enter Last Name Here.." class="form-control">
							</div>
						</div>							
					<div class="row">
							<div class="col-sm-6 form-group">
								<label>Email Address</label>
								<input name="email" type="email" placeholder="Enter Email Address Here.." class="form-control">
							</div>
							<div class="col-sm-6 form-group">
								<label>Phone Number</label>
								<input name="mobile" type="text" placeholder="Enter Phone Number Here.." class="form-control">
							</div>
					</div>	
					<div class="row">
							<div class="col-sm-6 form-group">
								<label>password</label>
								<input name="pswd" id="pswd" type="password" placeholder="Enter password" class="form-control">
							</div>
							<div class="col-sm-6 form-group">
								<label>confirm password</label>
								<input name="cpswd" type="password" placeholder="confirm password" class="form-control">
							</div>
					</div>	
					    <div class="col-xs-8">
    					<a href="${pageContext.request.contextPath}/login" data-toggle="collapse"><h4>i have already account</h4></a>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">Register</button>
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
		var ruleCall ={ name : "required",
				username : { required : true, minlength : 5, lettersonly : true, noSpace : true }, 
email : { required : true, email : true, }, pswd : { required : true, minlength : 5 }, 

cpswd : { required : true, minlength : 5, equalTo : "#pswd" }, 

mobile : { required : true, minlength : 10, maxlength : 10, number : true },  };
		var msg ={
				name : "Please enter your firstname", 
				username : "Enter username!",
				email : "Please enter a valid email address",  
				pswd : { required : "Please provide a password",
					minlength : "Your password must be at least 5 characters long" }, 
					cpswd : { required : "Please provide a confirm password", 
						minlength : "Your password must be at least 5 characters long", 
						equalTo : "Please enter the same password as above" }, 
						mobile : { required : "Please provide a your mobile number", 
							minlength : "Your mobile number must be at least 10 characters long",
							maxlength : "Your mobile number must be  at least 10 characters long", }, };  
		validatorAjax("#reg", ruleCall, msg, 0);  });
		function submitForm(form,id){
				  form.submit();
		}
	</script>
</body>
</html>