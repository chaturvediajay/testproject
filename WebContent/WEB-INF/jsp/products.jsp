<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- head -->
<%@include file="head.jsp"%>
<!-- End head -->
<style>
.previos {
	display: inline;
}

.nextu {
	display: inline;
}
</style>
<body>




	<!--header-->
	<%@include file="header.jsp"%>
	<!-- End header -->
	<!--banner-->
	<div class="container">
		<div class="product">
			<h2 class="new">Top ${param.q} Brands</h2>
			<div class="clearfix"></div>
		</div>
		<div class="item"></div>

		<!---->
		<nav>
		<ul class="pager">
			<li><a href="javascript:void(0);" onclick="pageing('prev')"
				class="previos">Previous</a></li>
			<li><a href="javascript:void(0);" onclick="pageing('nxt')"
				class="nextu">Next</a></li>
		</ul>
		</nav>


		<br /> <br />

	</div>
	<!--footer-->
	<%@include file="footer.jsp"%>
	<!-- End footer -->
	<a href="#" id="toTop">To Top</a>
	<!--JS-->
	<%@include file="mainJs.jsp"%>
	<!-- End JS -->
	<script>
		var num = 0;
		var count = 0;
		var pageSize = 0;
		$(document).ready(function() {
			$('#simpleCart_quantity').text('0');
			$('#simpleCart_total').text('0');
			loadCart();
			pageing('load');

		});
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
		function pageing(cate) {
			$(".next").hide();
			$(".previous").hide();
			$.ajax({
				url : 'paging_search',
				type : 'post',
				dataType : 'json',
				data : {
					_count : num,
					_query : getUrlParameter("q"),
					_tag : 'search'
				},
				success : function(data) {
					console.log(data.name);
					$('.item').html(data.name);
					console.log('row_count:- ' + data.row_count)
					count = data.row_count;
					pag(cate);
				
				}
			});
		}
	</script>
</body>
</html>