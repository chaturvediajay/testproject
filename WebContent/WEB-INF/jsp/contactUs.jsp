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
<!--JS-->
<%@include file="mainJs.jsp"%>
<!-- End JS -->
<body>
	<!--header-->
	<%@include file="header.jsp"%>
	<!-- End header -->
	<div class="container">
		<div class="contact">
			<h2>CONTACT US...</h2>
			<div class="contact-in">
				<div class=" col-md-3 contact-right">
					<h5>Chouhan Rugs</h5>
					<p>Near HP Petrol Pump Main Road</p>
					<p>Lawan, Dausa ,PIN-303004,</p>
					<p>RAJASTHAN, INDIA.</p>
					<p>Phone:(00) +91 81 0481 0707</p>
					<p>Fax: (000) 000 00 00 0</p>
					<p>
						Email: <a href="mailto:info@chouhanrugs.com">info@chouhanrugs.com</a>
					</p>
					<p>
						Follow on: <a href="#">Facebook</a>, <a href="#">Twitter</a>
					</p>

					<p>Monday-Friday</p>
					<p>
						10:00 am - 5.00 pm IST (Indian Standard Time)<br>
					</p>
					<p>Note:- Closed on bank holidays</p>
				</div>





				<div class=" col-md-9 contact-left">


					${error}

					<form id="queryForm" method="post">
						<div class="col-sm-12">
							<div>
								<span>Name</span> <input name="name" id="name" type="text"
									placeholder="Enter Name Here.." class="form-control">
							</div>
							<div>
								<span>Email</span> <input name="username" id="username"
									type="text" placeholder="Enter Email Address Here.."
									class="form-control">
							</div>
							<div>
								<span>Subject</span>
								<textarea name="userMsg" id="userMsg" type="text"
									placeholder="Type Your Message" class="form-control"> </textarea>
							</div>
							<!-- /.col -->
							<div>
								<br>
								<button type="submit" class="btn btn-primary btn-block btn-flat">Submit</button>
							</div>
							<!-- /.col -->
						</div>
				</div>
				</form>





			</div>


			<div class="clearfix"></div>
		</div>

	</div>
	</div>
	<div class="map">
		<iframe
			src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d14248.076365787652!2d76.20069298198352!3d26.775661362903868!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x155effe84bbf062e!2sChouhan+International!5e0!3m2!1sen!2sin!4v1527846093976"
			width="600" height="450" frameborder="0" style="border: 0"
			allowfullscreen></iframe>
	</div>



	<!--footer-->
	<%@include file="footer.jsp"%>
	<!-- End footer -->

	<a href="#" id="toTop">To Top</a>

	<!--JS-->


	<!-- End JS -->
	<script>
		$(function() {
			jQuery.validator.addMethod("lettersonly", function(value, element) {
				return this.optional(element) || /[a-zA-Z]+$/i.test(value);
			}, "Letters only please with no space");
			jQuery.validator.addMethod("noSpace", function(value, element) {
				return value.indexOf(" ") < 0 && value != "";
			}, "No space please and don't leave it empty");
			var ruleCall = {
				name : "required",
				name : {
					required : true,
					minlength : 3,
					noSpace : false
				},
				username : {
					required : true,
					minlength : 5,
					lettersonly : true,
					noSpace : true
				},
				userMsg : {
					required : true,
					minlength : 10,
					noSpace : false
				}
			};
			var msg = {
				name : "Enter your name",
				username : "Enter username or email id",
				pswd : "Please type your message minimum 10 word."
			};
			validatorAjax("#queryForm", ruleCall, msg, 0);
		});
		function submitForm(form, id) {
			form.submit();
		}
	</script>
</body>
</html>