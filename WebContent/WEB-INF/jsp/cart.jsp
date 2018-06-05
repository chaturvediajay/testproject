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
			 <h1>My Shopping Bag (<span id="simpleCart_quantity" class="simpleCart_quantity1"></span>) </h1>
			 <c:set var="num" value="0" scope="page" />
			 <c:set var="total" value="0" scope="page" />
			 <c:if test="${not empty retrive}">
			 <c:forEach var="fp" items="${retrive}">
<c:set var="sUrl" value="${fn:split(fp.url,',')}" />
			 	 <div class="cart-header">
				 <div id="close${num}" class="close1">
				 <a href="javascript:;" onclick="act(this,${num},'${fp.pkey}${fp.ppid}','delete','html');"
												class="text-danger" data-toggle="tooltip"
												data-placement="top" data-original-title="Remove"><i
													class="close1"></i></a> </div>
				 <div class="cart-sec simpleCart_shelfItem">
						<div class="cart-item cyc">
						<c:if test="${fn:length(sUrl) > 0}">
							 <img src="${pageContext.request.contextPath}/temp/img/${sUrl[0]}" class="img-responsive" alt="">
							 </c:if>
						</div>
					   <div class="cart-item-info">
						<h3><a href="#">${fp.title}</a><span>Model No: 3578</span></h3>
						<ul class="qty">
							<li><p>Size : ${fp.size}</p></li>
							<li><p>color : ${fp.color}</p></li>
							<li></li>
							<li><p>Qty : <select data-width="80px"
												id="qtyId" class="selectpicker"
												onchange="changeLayer(this,${num},'${fp.pkey}${fp.ppid}','cart_update','html');">
													<option value="1" ${fp.qty == 1 ? "selected" : ""}>1</option>
													<option value="2" ${fp.qty == 2 ? "selected" : ""}>2</option>
													<option value="3" ${fp.qty == 3 ? "selected" : ""}>3</option>
													<option value="4" ${fp.qty == 4 ? "selected" : ""}>4</option>
													<option value="5" ${fp.qty == 5 ? "selected" : ""}>5</option>
													<option value="6" ${fp.qty == 6 ? "selected" : ""}>6</option>
													<option value="7" ${fp.qty == 7 ? "selected" : ""}>7</option>
											</select></p></li>
						</ul>
							 <div class="delivery">
							 <p id="num${num}">Charges-- : ${currency_dollar}${fp.qty*fp.smrp}</p>
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
			 <a class="continue" href="shipping">Add Shipping Address</a>
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
			 <a class="order" href="#">Place Order</a>
			 <div class="total-item">
				<!-- <h3>OPTIONS</h3>
				 <h4>COUPONS</h4>
				 <a class="cpns" href="#">Apply Coupons</a>-->
				 <p><a href="login">Log In</a> to use accounts</p>
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
				
				
				
				$(document).ready(function(c) {
					$('.close1').on('click', function(c){
						$('.cart-header').fadeOut('slow', function(c){
							$('.cart-header').remove();
						});
						});	  
					});
			   </script>
 <script>$(document).ready(function(c) {
					$('.close2').on('click', function(c){
							$('.cart-header2').fadeOut('slow', function(c){
						$('.cart-header2').remove();
					});
					});	  
					});
 
 
 function changeLayer(btn, num, pkey, cal, metod) {
		var js1 = new Object();
		js1.value =$(btn).find('option:selected').text();
		js1.cate=cal;
		js1.pkey = pkey;
		js1.num=num;
		//js1.size = size;
		//js1.cate = value;
		var json = {
			'json' : JSON.stringify(js1)
		};
		ajaxCallRequestResponse('json', json, 'post', 'cart',homeview);
	}
 
 function homeview(res) {
	var obj = JSON.stringify(res.data);
		var jsonobject = JSON.parse(obj);
		var d=JSON.parse(jsonobject);
		console.log('-------  '+d.total);
		$('.total').html('&#x24;'+d.total);
		$('#num'+d.num).html('Charges-- : &#x24;'+(d.smrp*d.qty));
		$('#idtotal').html('&#x24;'+(parseInt(d.total)+parseInt(d.ship)));
		$('.dlc').html('&#x24;'+d.ship);
	}
			 </script>			   
			   

</body>
</html>