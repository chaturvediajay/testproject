<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- head -->
	<%@include file="head.jsp"%>
<!-- End head -->
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
<body> 
<!--header-->	
	<%@include file="header.jsp"%>
<!-- End header -->
<div class="container"  style="padding:50px 0">
    <div class="col-lg-4 ">
    
    </div>
	<div class="col-lg-4 well" class="text-center">
	<div class="logo">
				<a href="${pageContext.request.contextPath}/"><img src="images/logonew.png" alt="" style="
    height: 58px;
    margin-left: 31px;
    margin-bottom: -58px;
    margin-top: 17px;
    "></a>
			</div>
			<br><br><br><br><br>
	<h3 class="well text-center">login</h3>
	${error}
	<div class="row">
				<form id="login" method="post">
					<div class="col-sm-12">
					<div  class="row" style="padding-bottom: 15px;">
								<label>Email Address</label>
								<input name="username" type="text" placeholder="Enter Email Address Here.." class="form-control">
					</div>
					<div class="row" style="padding-bottom: 15px;">
								<label>password</label>
								<input name="pswd" type="password" placeholder="Enter password" class="form-control">
					</div>	
					<div class="row" style="padding-bottom: 15px;">
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
                </div>
                <!-- /.col -->
            </div>
					<p><a href="${pageContext.request.contextPath}/signUp" class="text-center">Register a new membership</a></p>
					
					<p><a href="forgot_password_email?ctx=recover" data-toggle="collapse">I forgot my password</a>	</p>				
					</div>
				</form> 
				</div>
	</div>
	<div class="col-lg-4">
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
				pswd : { required : true, minlength : 5,noSpace : true }  };
		var msg ={
				username : "Enter username or email id",  
				pswd : { required : "Please provide a password",
					minlength : "Your password must be at least 5 characters long" } };  
		validatorAjax("#login", ruleCall, msg, 0);  });
		function submitForm(form,id){
				  form.submit();
		}
	</script>

</body>
</html>