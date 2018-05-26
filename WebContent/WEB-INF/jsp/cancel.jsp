<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- head -->
<%@include file="head.jsp"%>

<style>
.products-grid {
	padding: 30px 0;
}

.product {
	position: relative;
	margin-top: 50px;
	padding: 0 0;
}
</style>
<!-- End head -->
<body>
	<c:forEach var="listValue" items="${cancel_view}">
		<!--header-->
		<%@include file="header.jsp"%>
		<!-- End header -->
		<!--JS-->
		<%@include file="mainJs.jsp"%>
		<!-- End JS -->
		<!--banner-->
		<div class="content">
			<div class="container">
				<table class="content_table" border="0" width="100%" cellspacing="0"
					cellpadding="0" align="center">
					<tbody>
						<tr>
							<td colspan="4" height="20">&nbsp;</td>
						</tr>
						<tr>
							<td width="24">&nbsp;</td>
							<td class="btn_space" width="293">&nbsp;</td>
							<td class="logo_img" width="155"><a
								style="text-decoration: none; font-family: Arial, Helvetica, sans-serif; font-size: 20px; font-weight: bold; color: #6ac451"
								href="${pageContext.request.contextPath}" rel="noreferrer"><img
									src="images/logo.png"
									alt="blaGOt></a></td>
				<td width="26">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" height="17">&nbsp;</td>
			</tr>
			<tr>
				<td style="min-height: 1px; line-height: 0px; font-size: 0px"
					colspan="4" bgcolor="#d6d6d6" height="1"><img
					style="display: block"
					src="https://test.payumoney.com/media/images//spacer.png" alt=""
					width="1" height="1"></td>
			</tr>
			<tr>
				<td colspan="4" height="10">&nbsp;</td>
			</tr>
			<tr>
				<td width="24">&nbsp;</td>
				<td
					style="font-family: Arial, Helvetica, sans-serif; font-size: 14px; color: #666666"
					colspan="2">
					<p>Dear ${listValue[4]}</p>
				</td>
				<td width="26">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" height="0">&nbsp;</td>
			</tr>
			<tr>
				<td width="24">&nbsp;</td>
				<td
					style="font-family: Arial, Helvetica, sans-serif; font-size: 14px; color: #666666; line-height: 20px"
					colspan="2">We noticed that your transaction through PayUMoney
					might not have been successful.</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" height="5">&nbsp;</td>
			</tr>
			<tr>
				<td width="24">&nbsp;</td>
				<td
					style="font-family: Arial, Helvetica, sans-serif; font-size: 14px; color: #666666; line-height: 20px"
					colspan="2">Following are the transaction details:</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" height="15">&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td colspan="2">
					<table border="0" width="100%" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td style="line-height: 1px" colspan="9" bgcolor="#d6d6d6"
									height="1"><img style="display: block"
									src="https://test.payumoney.com/media/images//spacer.png"
									alt="" width="1" height="1"></td>
							</tr>
						
							<tr>
								<td style="line-height: 1px" colspan="9" bgcolor="#d6d6d6"
									height="1"><img style="display: block"
									src="https://test.payumoney.com/media/images//spacer.png"
									alt="" width="1" height="1"></td>
							</tr>
							<tr>
								<td bgcolor="#d6d6d6" width="1">&nbsp;</td>
								<td class="tbl_spc" bgcolor="#ffffff" width="19" height="35">&nbsp;</td>
								<td class="tbl_value"
									style="color: #000000; font-family: Arial, Helvetica, sans-serif; font-size: 14px; line-height: 16px"
									bgcolor="#ffffff">Payment Amount</td>
								<td bgcolor="#fff" width="5">&nbsp;</td>
								<td class="tbl_spc" bgcolor="#d6d6d6" width="1">&nbsp;</td>
								<td class="tbl_spc" bgcolor="#ffffff" width="18">&nbsp;</td>
								<td class="tbl_value"
									style="color: #000000; font-family: Arial, Helvetica, sans-serif; font-size: 14px; line-height: 16px"
									bgcolor="#ffffff">&nbsp; ${listValue[0]}</td>
								<td bgcolor="#fff" width="5">&nbsp;</td>
								<td bgcolor="#d6d6d6" width="1">&nbsp;</td>
							</tr>
							<tr>
								<td style="line-height: 1px" colspan="9" bgcolor="#d6d6d6"
									height="1"><img style="display: block"
									src="https://test.payumoney.com/media/images//spacer.png"
									alt="" width="1" height="1"></td>
							</tr>
							<tr>
								<td bgcolor="#d6d6d6" width="1">&nbsp;</td>
								<td class="tbl_spc" bgcolor="#ffffff" width="19" height="35">&nbsp;</td>
								<td class="tbl_value"
									style="color: #000; font-family: Arial, Helvetica, sans-serif; font-size: 14px; line-height: 16px"
									bgcolor="#ffffff" width="40%">Payment Id</td>
								<td bgcolor="#fff" width="5">&nbsp;</td>
								<td bgcolor="#d6d6d6" width="1">&nbsp;</td>
								<td class="tbl_spc" bgcolor="#ffffff" width="18">&nbsp;</td>
								<td class="tbl_value"
									style="color: #000000; font-family: Arial, Helvetica, sans-serif; font-size: 14px; line-height: 16px"
									bgcolor="#ffffff">&nbsp;${listValue[1]}</td>
								<td bgcolor="#fff" width="5">&nbsp;</td>
								<td bgcolor="#d6d6d6" width="1">&nbsp;</td>
							</tr>
							<tr>
								<td style="line-height: 1px" colspan="9" bgcolor="#d6d6d6"
									height="1"><img style="display: block"
									src="https://test.payumoney.com/media/images//spacer.png"
									alt="" width="1" height="1"></td>
							</tr>
							<tr>
								<td style="line-height: 1px" colspan="9" bgcolor="#d6d6d6"
									height="1"><img style="display: block"
									src="https://test.payumoney.com/media/images//spacer.png"
									alt="" width="1" height="1"></td>
							</tr>
							<tr>
								<td bgcolor="#d6d6d6" width="1">&nbsp;</td>
								<td class="tbl_spc" bgcolor="#ffffff" width="19" height="35">&nbsp;</td>
								<td class="tbl_value"
									style="color: #000; font-family: Arial, Helvetica, sans-serif; font-size: 14px; line-height: 16px"
									bgcolor="#ffffff" width="40%">Date/Time of Payment</td>
								<td bgcolor="#fff" width="5">&nbsp;</td>
								<td bgcolor="#d6d6d6" width="1">&nbsp;</td>
								<td class="tbl_spc" bgcolor="#ffffff" width="18">&nbsp;</td>
								<td class="tbl_value"
									style="color: #000000; font-family: Arial, Helvetica, sans-serif; font-size: 14px; line-height: 16px"
									bgcolor="#ffffff">&nbsp;${listValue[2]}</td>
								<td bgcolor="#fff" width="5">&nbsp;</td>
								<td bgcolor="#d6d6d6" width="1">&nbsp;</td>
							</tr>
							<tr>
								<td style="line-height: 1px" colspan="9" bgcolor="#d6d6d6"
									height="1"><img style="display: block"
									src="https://test.payumoney.com/media/images//spacer.png"
									alt="" width="1" height="1"></td>
							</tr>
							<tr>
								<td bgcolor="#d6d6d6" width="1">&nbsp;</td>
								<td class="tbl_spc" bgcolor="#ffffff" width="19" height="35">&nbsp;</td>
								<td class="tbl_value"
									style="color: #000; font-family: Arial, Helvetica, sans-serif; font-size: 14px; line-height: 16px"
									bgcolor="#ffffff" width="40%">Failure Message</td>
								<td bgcolor="#fff" width="5">&nbsp;</td>
								<td bgcolor="#d6d6d6" width="1">&nbsp;</td>
								<td class="tbl_spc" bgcolor="#ffffff" width="18">&nbsp;</td>
								<td class="tbl_value"
									style="color: #000000; font-family: Arial, Helvetica, sans-serif; font-size: 14px; line-height: 16px"
									bgcolor="#ffffff">
									  <c:if test = "${listValue[3] eq -1}">
									         payment denied
									      </c:if>
									</td>
								<td bgcolor="#fff" width="5">&nbsp;</td>
								<td bgcolor="#d6d6d6" width="1">&nbsp;</td>
							</tr>
							<tr>
								<td style="line-height: 1px" colspan="9" bgcolor="#d6d6d6"
									height="1"><img style="display: block"
									src="https://test.payumoney.com/media/images//spacer.png"
									alt="" width="1" height="1"></td>
							</tr>
						</tbody>
					</table>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" height="25">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" height="10">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" height="15">&nbsp;</td>
			</tr>
			<tr>
				<td width="24">&nbsp;</td>
				<td
					style="font-family: Arial, Helvetica, sans-serif; font-size: 14px; color: #666666; line-height: 20px"
					colspan="2">In case your card, bank account gets
					debited for the failed transaction, you need not worry. We will
					auto-refund the amount.</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" height="17">&nbsp;</td>
			</tr>
			<tr>
				<td width="24">&nbsp;</td>
				<td
					style="font-family: Arial, Helvetica, sans-serif; font-size: 14px; font-weight: bold; color: #000; line-height: 24px"
					colspan="2">Best Regards,</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td width="24">&nbsp;</td>
				<td
					style="font-family: Arial, Helvetica, sans-serif; font-size: 14px; color: #000; line-height: 24px"
					colspan="2">blagot Support</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" height="10">&nbsp;</td>
			</tr>
			<tr>
				<td style="line-height: 1px" colspan="4" bgcolor="#d6d6d6"
					height="1"><img style="display: block"
					src="https://test.payumoney.com/media/images//spacer.png" alt=""
					width="1" height="1"></td>
			</tr>
			<tr>
				<td colspan="4" bgcolor="#f3f3f3" height="10">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" bgcolor="#f3f3f3">
					<table border="0" cellspacing="0" cellpadding="0" align="">
						<tbody>
							<tr>
								<td width="15">&nbsp;</td>
								<td
									style="font-family: Arial, Helvetica, sans-serif; font-size: 14px; color: #666666; line-height: 20px"
									colspan="2">If you have any issues, feel free to contact
									us at <a style="text-decoration: none; color: #ea8a00"
									href="mailto:info@blagot.com"
									rel="noreferrer">info@blagot.com</a>
								</td>
								<td width="12">&nbsp;</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4" bgcolor="#f3f3f3" height="10">&nbsp;</td>
			</tr>
		</tbody>
	</table>
	</c:forEach>
		</div>
	</div>









</body>

</html>