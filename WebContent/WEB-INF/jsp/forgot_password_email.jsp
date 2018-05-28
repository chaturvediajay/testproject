<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- head -->
<%@include file="head.jsp"%>
<!--JS-->
<%@include file="mainJs.jsp"%>
<!-- End JS -->
<style>
.form-gap {
	padding-top: 70px;
}
</style>
<body>
	<c:set var="forgot_pswd" scope="session"><%=java.lang.Math.round(java.lang.Math.random() * 10000)%></c:set>
	<!--header-->
	<%@include file="header.jsp"%>
	<!-- End header -->
	<div class="form-gap"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<c:if test="${trace > 0}">
							<c:if test="${param.ctx=='recover'}">
								<div class="text-center">
									<h3>
										<i class="fa fa-lock fa-4x"></i>
									</h3>
									<h2 class="text-center">Forgot Password?</h2>
									<p>You can reset your password here.</p>
									<div class="panel-body">
										<form id="register-form" role="form" autocomplete="off"
											class="form" method="post">
											<div class="form-group">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-envelope color-blue"></i></span> <input
														id="email" name="email" placeholder="email address"
														class="form-control" type="email">
												</div>
											</div>
											<div class="form-group">
												<input name="recover-submit"
													class="btn btn-lg btn-primary btn-block" value="Next"
													type="submit">
											</div>
											<input type="hidden" class="hide" name="token" id="token"
												value="${forgot_pswd}">
										</form>
									</div>
								</div>
							</c:if>


							<c:if test="${param.ctx=='change'}">

								<div class="show" id="reset_pwd">
									<h3>
										<i class="fa fa-lock fa-4x"></i>
									</h3>
									<h2 class="text-center">Reset Password</h2>
									<p>reset your password.</p>
									<div class="panel-body">

										<form id="reset_password" role="form" autocomplete="off"
											class="form" method="post">
											<div class="form-group">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon glyphicon-lock color-blue"></i></span>
													<input id="password" name="password"
														placeholder="new password" class="form-control"
														type="password">
												</div>
											</div>
											<div class="form-group">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon glyphicon-lock color-blue"></i></span>
													<input id="confirmPwd" name="confirmPwd"
														placeholder="confirm parssword" class="form-control"
														type="password">
												</div>
											</div>
											<div class="form-group">
												<input name="recover-submit"
													class="btn btn-lg btn-primary btn-block"
													value="Reset Password" type="submit">
											</div>
											<input type="hidden" class="hide" name="token" id="token"
												value="${forgot_pswd}">
										</form>
									</div>
								</div>
							</c:if>
						</c:if>
						<span class="label label-info">${msg}</span>
					</div>
					<c:if test="${trace eq 0}">
						<span class="label label-info">request can't not be found</span>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<!--footer-->
	<%@include file="footer.jsp"%>
	<!-- End footer -->
	<a href="#" id="toTop">To Top</a>
</body>
</html>