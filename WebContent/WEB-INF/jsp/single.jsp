<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.UUID"%>
<%!
    public String generateRandomNumber(){
        return UUID.randomUUID().toString().replace("-", "");
    }
%>
<html>
<!--JS-->	
	<%@include file="mainJs.jsp"%>
<!-- End JS -->
<!-- head -->
	<%@include file="head.jsp"%>
<!-- End head -->
<body> <c:set var="rnd"
		value="<%=generateRandomNumber()%>"
		scope="page"></c:set>
<!--header-->	
	<%@include file="header.jsp"%>
<!-- End header -->
	<c:forEach var="fp" items="${product_desc}">
<c:set var="sUrl" value="${fn:split(fp.url,',')}" />
<c:set var="num" value="0" scope="page" />	
	
	
	
<div class="content">
	<div class="container">
		<div class="panel-body">
				<div class="col-md-9 top-in-single ">
					<div class="col-md-5 single-top">	
						<ul id="etalage" class="etalage" style="display: block; width: 302px; height: 537px;">
						
						
						
					<c:if test="${fn:length(sUrl) > 0}">
					
					
							<li class="etalage_thumb thumb_2 etalage_thumb_active" style="background-image: none; display: list-item; opacity: 1;">
								<img class="etalage_thumb_image img-responsive" src="${pageContext.request.contextPath}/temp/img/${sUrl[0]}" style="display: inline; width: 300px; height: 400px; opacity: 1;">
								<img class="etalage_source_image img-responsive" src="${pageContext.request.contextPath}/temp/img/${sUrl[0]}" alt="">
							</li>
					
						
							<li class="etalage_thumb thumb_2 etalage_thumb_active" style="background-image: none; display: list-item; opacity: 1;">
								<img class="etalage_thumb_image img-responsive" src="${pageContext.request.contextPath}/temp/img/${sUrl[0]}" style="display: inline; width: 300px; height: 400px; opacity: 1;">
								<img class="etalage_source_image img-responsive" src="${pageContext.request.contextPath}/temp/img/${sUrl[0]}" alt="">
							</li>
							
					</c:if>			
							
						<c:set var="num" value="3" />
						
						<c:forEach var="i" begin="0" end="${fn:length(sUrl)-1}">
							<li class="etalage_thumb thumb_${num}" style="background-image: none; display: none; opacity: 0;">
								<img class="etalage_thumb_image img-responsive" src="${pageContext.request.contextPath}/temp/img/${sUrl[i]}" style="display: inline; width: 300px; height: 400px; opacity: 1;">
								<img class="etalage_source_image img-responsive" src="${pageContext.request.contextPath}/temp/img/${sUrl[i]}" alt="">
								
									<c:set var="num" value="${num+1}" />
								
							</li>
						
						
						
						</c:forEach>	
					</div>	
					<div class="col-md-7 single-top-in">
						<div class="single-para">
							<h4>${fp.title}</h4>
							<p>${fp.description}</p>
							<div class="star-on">
								<ul>
									<li><a href="#"><i> </i></a></li>
									<li><a href="#"><i> </i></a></li>
									<li><a href="#"><i> </i></a></li>
									<li><a href="#"><i> </i></a></li>
									<li><a href="#"><i> </i></a></li>
								</ul>
								<div class="review">
									<a href="#"> 3 reviews </a>/
									<a href="#">  Write a review</a>
								</div>
							<div class="clearfix"> </div>
							</div>
								<label class="add-to price">${currency_dollar}${fp.smrp}</label>
							 		<label class="add-to price">${currency_dollar}${fp.mrp}</label>
							<div class="available">
								<h6>Available Options :</h6>
								<ul>
in stock message<div id="msg"></div>
			
								<li>Size:<select id="size" onchange="changeLayer('size','${fp.pkey}')">
									<option value="0">select</option>
									
												<c:forTokens var="sp"  delims="," items="${fp.size}">
								   <option value="${sp}">${sp}</option>
								</c:forTokens>

								</select></li>
								<li>Color:
										<select id="color" onchange="changeLayer('color','${fp.pkey}')">
										<option value="0">select</option>
									</select></li>
							</ul>
						</div>
								<a id="call"  href="#" class="btn disabled cart">add to cart</a>
								
							
						</div>
					</div>
				<div class="clearfix"> </div>
				  <!----- tabs-box ---->
				</div>
				<div class="clearfix"> </div>		
		</div>
	</div>
</div>
</c:forEach>
	
	<!--footer-->	
	<%@include file="footer.jsp"%>
<!-- End footer -->
<a href="#" id="toTop">To Top</a>
<script type="text/javascript">

$(document).ready(function() {
	$('#simpleCart_quantity').text('0');
	$('#simpleCart_total').text('0');
	loadCart();
});


function changeLayer(value, pkey) {
	$(".label").addClass("label label-success");
	$("#msg").text('select size and color');
	var js1 = new Object();
	js1.pkey = pkey;
	js1.value = $('#' + value + ' :selected').val();
	js1.cate = value;
	var json = {
		'json' : JSON.stringify(js1)
	};
	if(js1.value==0){
		$("a").attr("href", '#');
		$(".label").removeClass().addClass("label label-danger");
		$('#call').removeClass().addClass("btn disabled cart");
		if(value=='size')
			$('#color option[value!=0]').remove();
	}
	 	else {
	 		console.log('ls--1- '+json);
	 		ajaxCallRequestResponse('json', json, 'get', getUrl('/products_size'),	homeview);
	 		
	 	}
}
function homeview(response) {
	
	$(".label").addClass("label label-success");
	var amt = 0;
	var obj = JSON.stringify(response);
	var par = JSON.parse(obj);
	if (par.cate == 'size') {
		var da = eval(par.data);
		selectGet('#color', da, '#size');
	} else if (par.cate == 'color') {
		var da = eval(response);
		var par = JSON.parse(obj);
			par = JSON.parse(par.data);
			$(".label").removeClass().addClass("label label-success");
			$('a.btn').removeClass().addClass("btn cart");
			$("a").attr("href", 'cart?scope=title12'+"-"+par.pkey+''+par.ppid);
			$('.add-to .price').html("<div>&#8377;"+par.smrp+"<span class=\"label label-default arrowed\">10%"+
			"</span></div><span class=\"price-old\">&#8377;"+par.mrp+"</span>");
			$("#msg").text('Ready Stock');
			$('#call').removeClass().addClass("btn cart");
			console.log("true");
		} else {
			$(".label").removeClass().addClass("label label-danger");
			$('a.btn').removeClass().addClass(
					"cart");
			$("#msg").text('not available=');
		}
	}
</script>


</body>
</html>