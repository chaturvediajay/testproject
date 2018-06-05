<script src="${pageContext.request.contextPath}/js/jquery.min.js?randId=${rand}"></script>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script src="${pageContext.request.contextPath}/js/move-top.js?randId=${rand}"></script>
<script src="${pageContext.request.contextPath}/js/easing.js?randId=${rand}"></script>
<script type="text/javascript">
					jQuery(document).ready(function($) {
						$(".scroll").click(function(event){		
							event.preventDefault();
							$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
						});
					});
</script>
<script src="${pageContext.request.contextPath}/js/megamenu.js?randId=${rand}"></script>
<script src="${pageContext.request.contextPath}/js/simpleCart.min.js?randId=${rand}"></script>
<script src="${pageContext.request.contextPath}/js/easyResponsiveTabs.js?randId=${rand}"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js?randId=${rand}"></script>
<script src="${pageContext.request.contextPath}/js/separate.js?randId=${rand}"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script src="${pageContext.request.contextPath}/js/jquery.etalage.min.js?randId=${rand}"></script>
		<script>
			jQuery(document).ready(function($){

				$('#etalage').etalage({
					thumb_image_width: 300,
					thumb_image_height: 400,
					source_image_width: 900,
					source_image_height: 1200,
					show_hint: true,
					click_callback: function(image_anchor, instance_id){
						alert('Callback example:\nYou clicked on an image with the anchor: "'+image_anchor+'"\n(in Etalage instance: "'+instance_id+'")');
					}
				});

			});
		</script>
		
		<script type="text/javascript">
						$(document).ready(function() {
							/*
							var defaults = {
					  			containerID: 'toTop', // fading element id
								containerHoverID: 'toTopHover', // fading element hover id
								scrollSpeed: 1200,
								easingType: 'linear' 
					 		};
							*/
							
							$().UItoTop({ easingType: 'easeOutQuart' });
							
						});
					</script>