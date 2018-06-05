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
.error-template {
    padding: 42px 15px;
    text-align: center;
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
			
			
			<c:if test="${trace ne 0}">
			
			
			
			
				<div class="panel panel-default">
					
						<c:if test="${trace > 0}">
						
							<c:if test="${param.ctx=='recover'}">
							<div class="panel-body">
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
								</div>
							</c:if>


							<c:if test="${param.ctx=='change'}">
								<div class="panel-body">
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
								</div>
							</c:if>
							
						</c:if>
						<span class="label label-info">${msg}</span>
						
						
						
						
					
				</div>
				
				
				
				</c:if>
				
				
				
				
			</div>
		</div>
		<c:if test="${trace eq 0}">
		<div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Oops!</h1>
                <h2>
                    404 Not Found</h2>
                <div class="error-details">
                    Sorry, an error has occured, Requested page not found!
                </div>
                <div class="error-actions">
                    <a href="${pageContext.request.contextPath}/" class="btn btn-primary btn-lg">
                    <span class="glyphicon glyphicon-home"></span>Take Me Home </a>
                    <a href="${pageContext.request.contextPath}/contactUs" class="btn btn-default btn-lg">
                    <span class="glyphicon glyphicon-envelope"></span> Contact Support </a>
                </div>
            </div>
        </div>
    </div>
    </c:if>
	</div>
	<!--footer-->
	<%@include file="footer.jsp"%>
	<!-- End footer -->
	<a href="#" id="toTop">To Top</a>
</body>
</html>