<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:remove var="loginSession" />

<html>
<!-- head -->
<%@include file="head.jsp"%>
<!-- End head -->
<style type="text/css">
body {
	background: #333;
}

.form_bg {
	background-color: #eee;
	color: #666;
	padding: 20px;
	border-radius: 10px;
	position: absolute;
	border: 1px solid #fff;
	margin: auto;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	width: 320px;
	height: 280px;
}

.align-center {
	text-align: center;
}
</style>
<body>
	<!--banner-->
	<div class="container">
		<div class="row">
			<div class="form_bg">
				<form id="login" method="post">
					<h2 class="text-center">admin login</h2>
					<br />
					<div class="form-group">
						<input type="text" class="form-control" name="username"
							placeholder="User id">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="pswd"
							placeholder="Password">

					</div>
					<br />
					<div class="align-center">
						<button type="submit" class="btn btn-default" >Login</button>
						
					</div>
				</form>
			</div>
		</div>
	</div>
	<!--   part : Just a footer -->
	<div
		style="position: fixed; bottom: 10px; left: 10px; background: #4679BC; padding: 4px; border-radius: 2px; border: 1px solid #4679AA">
		<a href="http://www.chouhanrugs.com/" title="more ..."
			style="padding: 6px; text-decoration: none; font-size: 12px; color: #fff; letter-spacing: 1.5px;">
			back to home</a>
	</div>

	<!---->
	<!---->
	<!--footer-->
	<!-- End footer -->
	<a href="#" id="toTop">To Top</a>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/separate.js"></script>
	<script>
		$(function() {
			jQuery.validator.addMethod("lettersonly", function(value, element) {
				return this.optional(element) || /[a-zA-Z]+$/i.test(value);
			}, "Letters only please with no space");
			jQuery.validator.addMethod("noSpace", function(value, element) {
				return value.indexOf(" ") < 0 && value != "";
			}, "No space please and don't leave it empty");
			var ruleCall = {
				username : {
					required : true,
					minlength : 5,
					noSpace : true
				},
				pswd : {
					required : true,
					minlength : 5,
					noSpace : true
				}
			};
			var msg = {
				username : "Enter username or email id",
				pswd : {
					required : "Please provide a password",
					minlength : "Your password must be at least 5 characters long"
				}
			};
			validatorAjax("#login", ruleCall, msg, 0);
		});
		function submitForm(form, id) {
			form.submit();
		}
	</script>
</body>

</html>