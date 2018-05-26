<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- head -->
	<%@include file="head.jsp"%>
<!-- End head -->
<!--JS-->	
	<%@include file="mainJs.jsp"%>
<!-- End JS -->
<body> 
<!--header-->	
	<%@include file="header.jsp"%>
<!-- End header -->



<div class="container">
	<div class="check">	 
			
		 <div class="col-md-9 cart-items">
			 <h1>My Shopping Bag (2)</h1>
			 <c:set var="num" value="0" scope="page" />
			 <c:set var="total" value="0" scope="page" />
			 <c:if test="${not empty retrive}">
			 <c:forEach var="fp" items="${retrive}">
			 <c:set var="sUrl" value="${fn:split(fp.url,',')}" />
			 	 <div class="cart-header">
				 <div class="cart-sec simpleCart_shelfItem">
						<div class="cart-item cyc">
							 <img src="${pageContext.request.contextPath}/temp/img/${sUrl[0]}" class="img-responsive" alt="">
						</div>
					   <div class="cart-item-info">
						<h3><a href="#">${fp.title}</a><span>Model No: 3578</span></h3>
						<ul class="qty">
							<li><p>Size : ${fp.size}</p></li>
							<li><p>color : ${fp.color}</p></li>
							<li><p>Qty : ${fp.qty}</p></li>
						</ul>
							 <div class="delivery">
							 <p>Charges-- : ${currency_dollar}${fp.qty*fp.smrp}</p>
							 <span>Delivered in 2-3 bussiness days</span>
							 <c:set var="total" value="${total+(fp.qty*fp.smrp)}" />
							 
							 
							 <div class="clearfix"></div>
				        </div>	
					   </div>
					   <div class="clearfix"></div>
											
				  </div>
			 </div>
			 <c:set var="num" value="${num+1}" />
			 </c:forEach>
		</c:if>	 
		 </div>
		 
		 
		 

<c:if test="${charge_free > total}">
<c:set var="gr_total" value="${total+num*charge_corrier}" />
</c:if>
<c:if test="${charge_free <= total}">
<c:set var="gr_total" value="${total}" />
</c:if>

		  <div class="col-md-3 cart-total">
			 <a class="checkout" href="#">Edit Shopping Cart</a>
			  <div class="price-details">
				 <h3>Price Details</h3>
				 <span>Total</span>
				 <span class="total">${currency_dollar}${total}</span>
				 <span>Delivery Charges</span>
				 <span class="dlc">
				 <c:if test="${charge_free > total}">${currency_dollar}${num*charge_corrier}</c:if>
				 <c:if test="${charge_free <= total}">${currency_dollar}0</c:if>		
				 </span>
				 <div class="clearfix"></div>				 
			 </div>	
			 <ul class="total_price">
			   <li class="last_price"> <h4>TOTAL</h4></li>	
			   <li class="last_price" ><span id="idtotal">${currency_dollar}${gr_total}</span></li>
			   <div class="clearfix"> </div>
			 </ul>
			
			 
			 <div class="clearfix"></div>
			 <form action="PayPalPayment" autocomplete="off" name="ccPayment" method=POST>
					<input class="order" type="submit" value="Place Order with payment" style=" background: #fa7455; padding: 10px 20px;
   						 font-size: 1em;  color: #fff; text-decoration: none; display: block; font-weight: 600;
   						 text-align: center;  margin: 3em 0;">
			 </form>
			 
			 <div class="total-item">
			 <h3><ins>Shipping Address</ins></h3>
				<address>
			<strong>${sessionScope.ship.sName}</strong><br> ${sessionScope.ship.sAddress1}<br>
			${sessionScope.ship.sAddress2}<br> ${sessionScope.ship.sCity}<br>
			${sessionScope.ship.sState}.${sessionScope.ship.sPincode}<br><abbr
		title="Phone">P:</abbr> +91-${sessionScope.ship.sMobile}
		</address>
				 <p><a href="shipping">Edit Shipping Address</a></p>
			 </div> 
			</div>
		
			<div class="clearfix"> </div>
	 </div>
	 </div>
	


	
	<!--footer-->	
	<%@include file="footer.jsp"%>
<!-- End footer -->
	
<a href="#" id="toTop">To Top</a>


		<script>
				$(document).ready(function() {
					$('#simpleCart_quantity').text('0');
					$('#simpleCart_total').text('0');
					loadCart();
				});
				
</script>
</body>
</html>











