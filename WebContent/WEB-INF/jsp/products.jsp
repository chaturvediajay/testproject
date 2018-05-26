<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- head -->
	<%@include file="head.jsp"%>
<!-- End head -->
<body> 
<!--header-->	
	<%@include file="header.jsp"%>
<!-- End header -->

<!--banner-->

	<div class="container">
		<div class="product">
		<h2 class="new">Top Brands</h2>
		<nav>
               <ul class="pager">
                  <li><a href="javascript:void(0);" onclick="paging('prev')" class="waves-effect">Previous</a></li>
                  <li><a href="javascript:void(0);"  onclick="paging('nxt')" class="waves-effect">Next</a></li>
               </ul>
        </nav>
		<div class="clearfix"> </div>
		</div>
		<!---->	
</div>

	
	<!--footer-->	
	<%@include file="footer.jsp"%>
<!-- End footer -->
	
<a href="#" id="toTop">To Top</a>

<!--JS-->	
	<%@include file="mainJs.jsp"%>
<!-- End JS -->



<script>
var num=0;
var count=1;
$(document).ready(function() {
	$('#simpleCart_quantity').text('0');
	$('#simpleCart_total').text('0');
	loadCart();
	
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
function pageing(){
                $.ajax({  
                    url:'paging_response',  
                    type:'post',  
                    dataType: 'json',
                    data : {
        				_count : count
        			},
                    success: function(data) {  
                    	console.log(data.name);
                    	$('.product h2').html(data.name);
                      //  $('#name').val(data.name);  
                      // $('#email').val(data.email);  
                    }  
                });   
}
</script>		
</body>
</html>