<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- head -->
	<%@include file="head.jsp"%>
	<style>
	
.products-grid {
    padding: 30px 0;
}
.product {
	position:relative;
	margin-top: 50px;
	padding: 0 0;
}
	</style>
<!-- End head -->
<body> 
<!--header-->	
	<%@include file="header.jsp"%>
<!-- End header -->
<!--JS-->	
	<%@include file="mainJs.jsp"%>
<!-- End JS -->
<!--banner-->
	<div class="banner">
<div class="container">	
		  <div class="wmuSlider example1">
			   <div class="wmuSliderWrapper">
			<%@include file="master/bannerSlider.jsp"%>
			</div>
			 <ul class="wmuSliderPagination">
                	<li><a href="#" class="">0</a></li>
                	<li><a href="#" class="">1</a></li>
                	<li><a href="#" class="wmuActive">2</a></li>
                </ul>
		</div>
		<!---->
		  <script src="js/jquery.wmuSlider.js"></script> 
			  <script>
       			$('.example1').wmuSlider({
					 pagination : false,
					 nav : false,
				});         
   		     </script> 	
		
		</div>   
	</div>
<div class="content">
	<div class="container">
		<div class="content-top">
		<h2 class="new">New Products</h2>
		<div class="pink">
			<!-- requried-jsfiles-for owl -->
		<link href="css/owl.carousel.css" rel="stylesheet">
		<script src="js/owl.carousel.js"></script>
			<script>
				$(document).ready(function() {
					$("#owl-demo").owlCarousel({
						items : 4,
						lazyLoad : true,
						autoPlay : true,
						pagination : true,
					});
				});
			</script>
		<!-- //requried-jsfiles-for owl -->
			<div id="owl-demo" class="owl-carousel text-center">
			<%@include file="master/fetureProduct.jsp"%>
				<div class="clearfix"> </div>
			</div>
		</div>
		 </div>
			</div>
			<div class="container">
		<div class="product">
		<h2 class="new">NEW ARRIVALS</h2>
				
		
		<div class="all">
		</div>
		<!---->
		</div>
			<div class="clearfix"> </div>
		</div>
			
					
			<nav>
		<ul class="pager">
			<li><a href="javascript:void(0);" onclick="pageing('prev')"
				class="previos">Previous</a></li>
			<li><a href="javascript:void(0);" onclick="pageing('nxt')"
				class="nextu">Next</a></li>
		</ul>
		</nav>
		<!--      -feature product---- -->
	<!---->
			<div class="content-bottom">
				<div class="col-md-8 latter">
					<h6>NEWSLETTER</h6>
					<p>sign up now to our newsletter discount! to get the Welcome discount</p>
					
					<div class="clearfix"> </div>
				</div>
				<div class="col-md-4 latter-right">
						<form>
						<div class="join">
							<input type="text" value="your email here" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'your email here';}">
							<i> </i>
						</div>
							<input type="submit" value="join us">
						</form>
				</div>
				<div class="clearfix"> </div>
			</div>
	<!---->
		<!-- partcipate company-->
	<%@include file="participate.jsp"%>
	</div>
</div>
	<!--footer-->	
	<%@include file="footer.jsp"%>
<!-- End footer -->
	
<a href="#" id="toTop">To Top</a>

<a href="logout" >clean up</a>

<script>
var num=0;
var count=0;
$(document).ready(function() {
	$('#simpleCart_quantity').text('0');
	$('#simpleCart_total').text('0');
	//window.onload = paging('prev');
	loadCart();
	 pageing();
});

function paging(cate){
	if(cate=='nxt'){
		count++;
		$(".previous").removeAttr('disabled');
	}
	else if(cate=='prev')
		if(count>0){
			count--;
			$(".previous").removeAttr('disabled');
		}
		else
			$(".previous").attr('disabled','disabled');
	var newurl = window.location.protocol + "//" + window.location.host + window.location.pathname + '?page='+count;
    window.history.pushState({path:newurl},'',newurl);
    
    pageing();
    
    
//	if(num>0){
//		if(count==num){
	//		$(".next").attr('disabled','disabled');
//			$("previous").removeAttr('disabled');
//		}
//	}
//	else{
//		$(".next").attr('disabled','disabled');
//		$("previous").removeAttr('disabled');
		
//	}
	
		
}				
function get(){
	console.log(getUrlParameter('myNewUrlQuery'));
}			
			   </script>
			   
		
<script type="text/javascript">  
var num = 0;
var count = 0;
var pageSize = 0;
$(document).ready(function() {
	$('#simpleCart_quantity').text('0');
	$('#simpleCart_total').text('0');
	loadCart();
	pageing('load');
});
function pageing(cate){
	$(".next").hide();
	$(".previous").hide();
                $.ajax({  
                    url:'paging_response',  
                    type:'post',  
                    dataType: 'json',
                    data : {
        				_count : count,
        				_query:"all"
        				
        			},
                    success: function(data) {  
                    	console.log(data.name);
                    	$('.all').html(data.name);
                      //  $('#name').val(data.name);  
                      // $('#email').val(data.email);
                    	count = data.row_count;
    					pag(cate);
                    }  
                });   
}

function pag(cate) {
	if (cate == 'load') {
		if (num == 0)
			$(".previos").hide();
		if(count==0){
			$(".nextu").hide();
			$(".previos").hide();
		}
	}
	if(cate=='prev')
{
		 if(count==num & num>1){
			$(".nextu").show();
			$(".previos").show();
			num--;
		}
		 else  if(count>num & num>0){
				$(".nextu").show();
				$(".previos").show();
				num--;
				if(num==0){
					$(".previos").hide();
				}
			}
}
	if(cate=='nxt'){
		if (count > num) {
			$(".nextu").show();
			$(".previos").show();
			num++;
		} 
		else if(count == num & count>0){
			$(".nextu").hide();
			$(".previos").show();
		}
	}
}
</script>			   
			   
</body>


</html>