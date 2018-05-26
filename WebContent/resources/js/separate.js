 

var count_down=function(){
		/* Set the date we're counting down to*/
		var countDownDate = new Date("Sep 15, 2017 00:00:01").getTime();  
		/* Update the count down every 1 second */ 
		var countdownfunction = setInterval(function() {  /* Get todays date and time */ var now = new Date().getTime();  /* Find the distance between now an the count down date */ var distance = countDownDate - now;  /* Time calculations for days, hours, minutes and seconds */ var days = Math.floor(distance / (1000 * 60 * 60 * 24)); var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)); var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60)); var seconds = Math.floor((distance % (1000 * 60)) / 1000);  /* Output the result in an element with id="demo" */ document.getElementById("demo").innerHTML = days + "d " + hours + "h " + minutes + "m " + seconds + "s ";  /* If the count down is over, write some text */ if (distance < 0) { clearInterval(countdownfunction); document.getElementById("demo").innerHTML = "EXPIRED"; } }, 1000);

}
var id = '';
var bt = '';
var ca = '';
var me = '';


productInfoHtml = function() {
	var strHtml = '';
	strHtml = "<div class=\"col-lg-6\" id=\"allProd\">"
			+ "<h1 class=\"page-header\">Product Information</h1>"
			+ "<label><button onclick=\"permition(1);\">Allow to sale</button></label>"
			+ "<label><button onclick=\"permition(0);\">Pending to Permition</button></label>"
			+ "<label><button onclick=\"permition(0);\">out of stock</button></label>"
			+ "<label><button onclick=\"permition(0);\">pending for verify</button></label>"
			+ "<div id=\"permitedList\"></div></div>";
	"<div class=\"col-lg-6\"><div id=\"message\">";
	"</div></div>";
	return strHtml;
}
function cartValue(url, divId, cate, key) {

	$.ajax({
		method : "GET",
		url : getContextPath() + url,
		async : true,
		data : {
			category : cate,
			keyid : key
		}
	}).done(function(msg) {
		$('#' + divId).html(msg);
	});
}
function removeCart(key) {
	cartValue('/cartAccess', 'cart-dropdown', 'remove', key);
}
function removeCartDesc(key) {

	var js1 = new Object();
	js1.category = 'remove-cart';
	js1.keyid = key;
	var json = {
		'json' : JSON.stringify(js1)
	};
	ajaxRequest(json, 'get', '/cartAccess1', divTest);

	EnableDisable('amtTotal');

}
function addCart() {
	var spl = getParameterByName('key');
	spl = spl.split("-");
	var len = spl.length - 1;
	cartValue('/cartAccess', 'retrive', 'add', spl[len]);
}
function notLessThenZero(num) {
	num.onkeydown = function(e) {
		if (!((e.keyCode > 95 && e.keyCode < 106)
				|| (e.keyCode > 47 && e.keyCode < 58) || e.keyCode == 8)) {
			return false;
		}
		if ($(this).val() == '0')
			return $(this).val('1');
		if ($(this).val().length > 1)
			return $(this).val(1);
	}
}

function getAjax(typePostGet, url, divId, cate, keyid) {

	$.ajax({
		method : typePostGet,
		url : getContextPath() + url,
		dataType : 'json',
		async : true,
		data : {
			category : cate,
			keyid : keyid
		},
	}).done(function(msg) {
		// alert(msg);
		$('#' + divId).html(msg);
	});
}
function myFunction() {
	 var base = document.getElementsByTagName('base')[0];
	    if (base && base.href && (base.href.length > 0)) {
	        base = base.href;
	    } else {
	        base = document.URL;
	    }
	    return base.substr(0,
	        base.indexOf("/", base.indexOf("/", base.indexOf("//") + 2) + 1))+'/';
}
function ajaxCall(requestData, requestURL, responseResultDiv) {
	var js = new Object();
	js.name = 'ajay';
	js.fname = 'mahesh';
	var json1 = {
		firstName : "John",
		lastName : "Doe",
		age : 50,
		eyeColor : "blue"
	};// {json:JSON.stringify(obj)};
	var jsonfile = {
		json : JSON.stringify(json1)
	};
	requestURL=myFunction()+requestURL;
//	requestURL = getContextPath() + requestURL;
//	requestURL = requestURL.replace('/blaGot/undefined', '');
//	requestURL = requestURL.replace('undefined', '');
	// alert(requestURL);
	// beforeAjaxCallCss();
	alert(requestURL);
	$.ajax({
		async : true,
		type : 'get',
		contentType : 'application/json; charset=utf-8"',
		url : requestURL,// "${pageContext.request.contextPath}/test",
		data : requestData,
		dataType : 'json',// "json",
		timeout : 50000,// 100000,
		async : true,
		cache : false,
		success : function(data) {
			ajaxindicatorstop();
			responseResultDiv(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);

			disableEnableFormClick(false);
			// responseResultDiv(e);
			// /display(e);
		},
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", 'application/json; charset=utf-8"');
			xhr.setRequestHeader("Content-Type",
					'application/json; charset=utf-8"');
		//	ajaxindicatorstart('loading data.. please wait..');
		},
		done : function(e) {
			disableEnableFormClick(false);
			console.log("DONE");
		}
	}, 10000);
}
var ajaxCallRequestResponse = function(ContentdataType, requestData,
		requestType, requestURL, responseResultDiv) {
	alert('call ajaxCallRequestResponse = '+requestURL+' '+requestType);
	// requestType=get/post-- requestURL='localhost'--- ContentdataType='json'
	console.log("get ajaxx call  " + requestURL);
	//requestURL = getContextPath() + requestURL;
	//requestURL=getContextPath() + requestURL;
	
	// requestURL = requestURL.replace('undefined', '');
	$.ajax({
		url : requestURL,
		type : requestType,
		dataType : ContentdataType,
		data : requestData,
		async : true,
		timeout : 10000,
		success : function(data) {
			var obj = JSON.stringify(data);
			var par = JSON.parse(obj);
			responseResultDiv(par);
			ajaxindicatorstop();
		},
		beforeSend : function(xhr) {
		//	ajaxindicatorstart('loading data.. please wait..');
		},
		error : function(xhr, textStatus, errorThrown) {
			ajaxindicatorstop();
			if (XMLHttpRequest.readyState == 4) {
				// HTTP error (can be checked by XMLHttpRequest.status and
				// XMLHttpRequest.statusText)
				console.log('HTTP error ');
			} else if (XMLHttpRequest.readyState == 0) {
				// Network error (i.e. connection refused, access denied due to
				// CORS, etc.)
				console.log('Network error ');
			} else {
				console.log('something weird is happening ');
				console.log(xhr+'\n'+'\n'+ errorThrown);
				// something weird is happening
			}

			/*
			 * if (jqXHR.status === 0) { return ('Not connected.\nPlease verify
			 * your network connection.'); } else if (jqXHR.status == 404) {
			 * return ('The requested page not found. [404]'); } else if
			 * (jqXHR.status == 500) { return ('Internal Server Error [500].'); }
			 * else if (exception === 'parsererror') { return ('Requested JSON
			 * parse failed.'); } else if (exception === 'timeout') { return
			 * ('Time out error.'); } else if (exception === 'abort') { return
			 * ('Ajax request aborted.'); } else { return ('Uncaught Error.\n' +
			 * jqXHR.responseText); }
			 */
		},
	});

}
function ajaxRequest(requestData, requestType, requestURL, responseResultDiv) {
	requestURL = getContextPath() + requestURL;
	requestURL = requestURL.replace('/chouhanrugs/undefined', '');
	requestURL = requestURL.replace('undefined', '');
	$.ajax({
		type : requestType, /* get or post */
		contentType : 'application/json; charset=utf-8"',
		url : requestURL,// "${pageContext.request.contextPath}/test",
		data : requestData,
		async : true,
		dataType : 'json',// "json",
		timeout : 80000,// 100000,
		async : true,
		cache : false,
		success : function(data) {
			ajaxindicatorstop();
			responseResultDiv(data);
		},
		error : function(e) {
			// console.log("ERROR: ", e);
			disableEnableFormClick(false);
			ajaxindicatorstop();
			responseResultDiv(e);
			// responseResultDiv(e);
			// /display(e);
		},
		beforeSend : function(xhr) {
			//ajaxindicatorstart('loading data.. please wait..');
			xhr.setRequestHeader("Accept", 'application/json; charset=utf-8"');
			xhr.setRequestHeader("Content-Type",
					'application/json; charset=utf-8"');
		//	ajaxindicatorstart('loading data.. please wait..');
			// disableEnableFormClick(true);
		},
		done : function(e) {
			disableEnableFormClick(false);
			console.log("DONE");
			ajaxindicatorstop();
		}
	}, 5000);
}
function beforeAjaxCallCss() {
	$("body").prepend("<div class=\"overlay\"></div>");

	$(".overlay").css({
		"position" : "absolute",
		"width" : $(document).width(),
		"height" : $(document).height(),
		"z-index" : 99999,
	}).fadeTo(0, 0.8);
}
function getParameterByName(name, url) {
	if (!url) {
		url = window.location.href;
	}
	name = name.replace(/[\[\]]/g, "\\$&");
	var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex
			.exec(url);
	if (!results)
		return null;
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, " "));
}
function spacePswd(id, code) {
	$(id).on('keydown', function(e) {
		if (e.keyCode == code) {
			e.keyCode = 0; // <---
			return false;
		}
	});
}
function disableEnableFormClick(status) {

	$(document).mousedown(function(e) {
		if (status)
			if (e.button == 2 | e.button == 0 | e.button == 1)
				return false;
			else
				return true;
	});
};
function getContextPath() {
	// var urlJ =document.location.origin;
	var urlJ = document.location.origin
			+ window.location.pathname.substring(0, window.location.pathname
					.indexOf("/", 2));
	urlJ = urlJ.replace('/undefined', '');
	urlJ = urlJ.replace('/blaGot/undefined', '');
	urlJ = urlJ.replace('undefined', '');
	// var urlJ =document.location.origin;

	// console.log("Output; "+urlJ);

	return urlJ;
}
function test() {
	console.log("window.location:  "
			+ window.location.pathname.substring(0, window.location.pathname
					.indexOf("/", 2)));
	var pathname = window.location.pathname;
	// var url = window.location.href;
	console.log("Output;");
	console.log(location.hostname);
	console.log(document.domain);
	// alert(window.location.hostname)

	console.log("document.URL : " + document.URL);
	console.log("document.location.href : " + document.location.href);
	console.log("document.location.origin : " + document.location.origin);
	console.log("document.location.hostname : " + document.location.hostname);
	console.log("document.location.host : " + document.location.host);
	console.log("document.location.pathname : " + document.location.pathname);

	console.log("window.location.protocol : " + window.location.protocol);
	console.log("window.location.port : " + window.location.port);
	console.log("5656 : " + window.location.protocol);

}
/* page loading */
function ajaxindicatorstart(text) {
	if (jQuery('body').find('#resultLoading').attr('id') != 'resultLoading') {
		jQuery('body')
				.append(
						'<div id="resultLoading" style="display:none"><div><img src="images/ajax-loader.gif"><div>'
								+ text
								+ '</div></div><div class="bg"></div></div>');
	}

	jQuery('#resultLoading').css({
		'width' : '100%',
		'height' : '100%',
		'position' : 'fixed',
		'z-index' : '10000000',
		'top' : '0',
		'left' : '0',
		'right' : '0',
		'bottom' : '0',
		'margin' : 'auto'
	});

	jQuery('#resultLoading .bg').css({
		'background' : '#000000',
		'opacity' : '0.7',
		'width' : '100%',
		'height' : '100%',
		'position' : 'absolute',
		'top' : '0'
	});

	jQuery('#resultLoading>div:first').css({
		'width' : '250px',
		'height' : '75px',
		'text-align' : 'center',
		'position' : 'fixed',
		'top' : '0',
		'left' : '0',
		'right' : '0',
		'bottom' : '0',
		'margin' : 'auto',
		'font-size' : '16px',
		'z-index' : '10',
		'color' : '#ffffff'

	});

	jQuery('#resultLoading .bg').height('100%');
	jQuery('#resultLoading').fadeIn(300);
	jQuery('body').css('cursor', 'wait');
}

function ajaxindicatorstop() {
	jQuery('#resultLoading .bg').height('100%');
	jQuery('#resultLoading').fadeOut(300);
	jQuery('body').css('cursor', 'default');
}

function callAjax() {
	jQuery.ajax({
		type : "GET",
		url : "fetch_data.php",
		cache : false,
		success : function(res) {
			jQuery('#ajaxcontent').html(res);
		}
	});
}
function EnableDisable(divCss) {
	val = $('#' + divCss).val();
	// alert(val);
	if (val == undefined | val == '' | val == null | val <= 0)
		$('.row').show();
	else
		$('.row').hide();
}
/* page load */

function loadCart() {
	var js1 = new Object();
	js1.category = 'retrive';
	js1.keyid = '';
	js1.metod = 'jq';
	var json = {
		'json' : JSON.stringify(js1)
	};
	ajaxRequest(json, 'get', '/cartAccess1', cartview);
}
function cartview(response) {
	
	var obj = JSON.stringify(response);
	var par = JSON.parse(obj);
	 var da = eval(par.data);
	 da = response.data;
	var amt = 0;
//	$('#cartBox').html('');
if (da != null & da.length>5 ) {
	/*		da = eval(da);
		for (var i = 0; i < da.length; i++) {
			var object = da[i];
			amt = amt + (object['smrp'] * object['qty']); *
		}
	*/	
		$('.simpleCart_quantity1').text(da.length);
		$('.simpleCart_total1').text(par.grand_total);
		
		console.log("da.length "+par.grand_total+":"+da.length);
		
//		if(da.length>0){
//			alert('da.length=='+da.length)
//			
//			$('#cartBox').append("<a href=\"${pageContext.request.contextPath}/cart\">"
//					+ "<h3> <div class=\"total\">"
//					+ "<span class=\"simpleCart_total\">"+amt+"</span> (<span id=\"simpleCart_quantity\" class=\"simpleCart_quantity\">"+da.length+"</span> items)</div>"
//					+ "<img src=\"images/cart.png\" alt=\"\"></h3>"
//					+ "</a>"
//					+ "<div class=\"clearfix\"> </div>");
//			
//		}
//		else
//			{
//			$('#cartBox').append("<p><a href=\"javascript:;\" class=\"simpleCart_empty\"><img src=\"images/cart-c.png\" alt=\"\"></a></p>"
//					+ "<div class=\"clearfix\"> </div>");
//			
//			}
	
		

	} else {
		$('#test').hide();
		$('.cartBox').html(0);
	}

}

function loadFinalCart(cas) {
	var js1 = new Object();
	js1.category = 'retriveFinal';
	js1.keyid = '';
	js1.metod = 'jq';
	js1.cas = cas;
	var json = {
		'json' : JSON.stringify(js1)
	};
	ajaxRequest(json, 'get', '/cartAccess1', cartview2);
}
function cartview2(response) {
	var obj = JSON.stringify(response);
	var par = JSON.parse(obj);
	 var da = eval(par.data);
	 da = response.data;
	 var total=0;
	 var cond=0;
	 var courierCharge=0;
	 var i=0;
	 $("#table_id tbody").empty();
	 var table = document.getElementById("cart");
	 var cell1 = '';
	 var newRow = $("<tr>");		
	 var cols = "";
	 var sessionValue= $("#smLocation").data('value');
	 var smPin= $("#smPincode").data('value');
	 var smCity= $("#smCity").data('value');
	 var smState= $("#smState").data('value');
	 var amt = 0;
		if (da != null & da.length>5 ) {
			da = eval(da);
			$('.cartvalue').text(da.length);
			for (i = 0; i < da.length; i++) {
				var row  = table.insertRow(i);
				cell1=row.insertCell(0);
				var total1=0;
				var courierCharge1=0;
				var object = da[i];
				var loc=0;
				
				 if(object['location'] == 1)
					{
					 loc=0;
						if(smState.trim() == object['mi_state'].toString().trim())
							{
							loc=0;
							if(smCity.trim() == object['mi_city'].toString().trim())
								{
									loc=1;
								}
								
							}
							
					}
				   else if(object['location'] == 2)
					{
						loc=0;
						if(smState.trim() == object['mi_state'].toString().trim())
							{
							loc=2;
							if(smCity.trim() == object['mi_city'].toString().trim())
								{
									loc=1;
								}
							}
						
					}
				   else if(object['location'] == 3)
					{
					   loc=3;
						if(smState.trim() == object['mi_state'].toString().trim())
							{
							loc=2;
							if(smCity.trim() == object['mi_city'].toString().trim())
								{
									loc=1;
								}
							}
					}
				
				cell1.className = 'img-cart';
				cell1.innerHTML = "<a href=\"products_page?pkey='"+ object['title']+'-'+ object['pkey']+"\">"
					+"<img alt=\"Product\" src='img/smaller/"+ object['url'].split(",", 1)+ "' class=\"img-thumbnail\">"
					+"</a>";
					
				cell1=row.insertCell(1)	
				cell1.className = 'input-qty';
				cell1.innerHTML = "<p><a href=\"products_page?pkey=${fp.title}-${fp.pkey}\""
					+"class=\"d-block\">"+ object['title']+"</a>"
					+"</p> <small></small>";
				
				
				cell1=row.insertCell(2);	
				cell1.className = 'unit';
				cell1.innerHTML=object['qty'];
				
				cell1=row.insertCell(3);	
				cell1.className = 'unit';
				cell1.innerHTML= object['smrp'];
				
				
				cell1=row.insertCell(4);	
				cell1.className = 'sub';
				cell1.innerHTML= object['qty']*object['smrp'];
				console.log(sessionValue <= object['location']  +'  '+ (sessionValue < object['location'])+
						' sessionValue: '+sessionValue+ "  object['location']:- "+object['location'])
				cell1=row.insertCell(5);	
					if (loc>0 ) {
						if(loc==1)
							cell1.innerHTML= "<span class=\"label label-success\">Available for One day delivery</span>";
						if(loc==2)
							cell1.innerHTML= "<span class=\"label label-success\">Available delivery within 5 days</span>";
						if(loc==3)
							cell1.innerHTML= "<span class=\"label label-success\">Available for delivery within 10 days</span>";
						courierCharge+=object['qty']*courier;
						total+=object['qty']*object['smrp'];
						
					}
					else if(loc==0)
						{
						cond+=1;
						cell1.innerHTML= "<a href=\"#\" data-toggle=\"tooltip\" "
									+ "onclick=\"act(this,0,'"
									+ object['pkey']
									+ object['ppid']
									+ "','delete','final');\" title=\"Remove\"><span id=\"msg\" " +
									"class=\"label label-danger\">Remove</span></a>";
						}
					
			}
			cell1='';
			 
			if(cond==0)
				$('a.btn').removeClass().addClass("btn btn-theme m-b-1");
			else 
				$('a.btn').removeClass().addClass("btn btn-theme m-b-1 disabled");
		   console.log(i);
			row = table.insertRow(da.length);
				
			if(courier>0)
				{
				if(total>freeCourier){

				    cell1 = row.insertCell(0);		
					cell1.className = 'text-right';
					cell1.colSpan= 4;
					cell1.innerHTML= "Courier Charge";
							
					

				    cell1 = row.insertCell(1);		

					cell1.id='Corrier';
					cell1.colSpan = 2
					cell1.innerHTML= "0";
					 
				}
				if(total<freeCourier){

				    cell1 = row.insertCell(0);		
					cell1.className = 'text-right';
					cell1.colSpan = 4
					cell1.innerHTML= "Courier Charge";

				    cell1 = row.insertCell(1);		
					cell1.id='Corrier';
					cell1.colSpan = 2
					cell1.innerHTML= courierCharge;
					total=total+courierCharge;
				
				}
				}
			
			row = table.insertRow(da.length+1);
			cell1=row.insertCell(0);
			cell1.className = 'text-right';
			cell1.innerHTML= "Total";
			cell1.colSpan = 4
			
			cell1=row.insertCell(1);
			cell1.id='Corrier';
			cell1.colSpan = 2
			cell1.innerHTML=total ;
			
		} 
}


function isValidInput(ths, con) {
	$('#' + ths)
			.keydown(
					function(e) {
						if (e.shiftKey || e.ctrlKey || e.altKey) {
							e.preventDefault();
						} else {
							var key = e.keyCode;
							if (!((key == 8) || (key == 32) || (key == 46)
									|| (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {

								e.preventDefault();
							}
						}
						var val = $('#' + ths).val();
						$('#' + ths).val(val.replace(/ +(?= )/g, ''));

					});

}
function act(btn, num, pkey, cal, metod) {
	ca = cal;
	me = metod;
	switch (cal) {
	case 'delete':
		if (metod == 'html') {
			// if (confirm("Are you sure want to delete product in cart?")){
			var js1 = new Object();
			js1.category = 'remove-cart';
			js1.keyid = pkey;
			js1.metod = me;
			bt = btn;
			var json = {
				'json' : JSON.stringify(js1)
			};
			ajaxRequest(json, 'get', '/cartAccess1', actRes);
			// }
		}
		if (metod == 'jq') {
			// if (confirm("Are you sure want to delete thr row?")){
			var js1 = new Object();
			js1.category = 'remove-cart';
			js1.keyid = pkey;
			js1.metod = me;
			bt = btn;
			var json = {
				'json' : JSON.stringify(js1)
			};
			ajaxRequest(json, 'get', '/cartAccess1', actRes);
			// }
		}
		if (metod == 'final') {
			// if (confirm("Are you sure want to delete thr row?")){
			var js1 = new Object();
			js1.category = 'remove-cart';
			js1.keyid = pkey;
			js1.metod = me;
			bt = btn;
			var json = {
				'json' : JSON.stringify(js1)
			};
			ajaxRequest(json, 'get', '/cartAccess1', actRes);
			// }
		}
		
	case 'update':
		if (metod == 'html') {
			var js1 = new Object();
			js1.category = 'update-cart';
			js1.keyid = pkey;
			js1.metod = me;
			js1.qty = $('#qtyId :selected').val();
			id = num;
			var json = {
				'json' : JSON.stringify(js1)
			};
			bt = btn;
			ajaxRequest(json, 'get', '/cartAccess1', actRes);
		}

		break;
	default:

	}
}
function actRes(res) {
	var obj = JSON.stringify(res);
	var jobj = JSON.parse(obj);
	if (jobj.valid) {
		switch (ca) {
		case 'delete':
			if (me == 'html') {
				var tr = bt.closest('tr');
				tr.remove();
				window.location.href = getContextPath() + '/cart';
			}
			if (me == 'jq') {
				if (jobj.data == '') {
					$('#cartvalue').text('0');
					$('#test').remove();
				} else
					loadCart();
				/*
				 * $('#cartvalue').text('0'); $(bt).closest("table").remove();
				 * $('.table-responsive').text('empty cart');
				 */

			}
			if (me == 'final') {
				if (jobj.data == '') {
					$('#cartvalue').text('0');
					$('#test').remove();
				} else
					loadCart();
				loadFinalCart('final')
			}
			

			break;
		case 'update':
			if (me == 'html') {
				var qty =$('#qtyId :selected').val();
				var amt = $("#unit" + id).text();
				$("#sub" + id).text(amt * qty);
				loadCart();
			}
			break;
		}
	} else
		// alert('please try later;');
		delete window.ca;
	delete window.me;
	delete window.bt;
	$('.cartvalue').html(0);
}

function amtCalc() {

}

function loadHomeTable(id) {
	var js1 = new Object();
	js1.category = 'retrive';
	js1.id = id;
	js1.keyid = '';
	js1.metod = 'jq';
	var json = {
		'json' : JSON.stringify(js1)
	};
	ajaxRequest(json, 'get', '/homePageManage', homeview);
}
function homeview(response) {
	$('#layerData').html('');
	var obj = JSON.stringify(response);
	var par = JSON.parse(obj);
	var da = eval(par.data);
	var amt = 0;
	if (da != null) {

		for (var i = 0; i < da.length; i++) {
			var object = da[i];
			$('#layerData')
					.append(
							"<table class=\"table table-bordered\">"
									+ "<thead>"
									+ "<tr>"
									+ "+ <th>Id</th>"
									+ "<th>title</th>"
									+ "<th>description</th>"
									+ "<th>key</th>"
									+ "<th>View</th>"
									+

									"</tr>"
									+ "</thead>"
									+ "<tbody>"
									+ "<td>"
									+ object['id']
									+ "</td>"
									+ "<td>"
									+ object['title']
									+ "</td>"
									+ "<td><a data-toggle=\"modal\" href=\"#myModal\" class=\"btn btn-primary\">update</a>"
									+ "<a data-toggle=\"modal\" href=\"\" onclick=\"getDesc('"
									+ object['id']
									+ "','view','jq');\" class=\"btn btn-primary\">view</a></td>"
									+ "<td id=\"pkey\">"
									+ object['productkey']
									+ "</td>"
									+ "<td><a href=\"#\" class=\"confirm-delete btn mini red-stripe\" role=\"button\" onClick=\"removeItem("
									+ object['productkey'] + "," + object['id']
									+ ")\" data-title=\"johnny\" data-id=\""
									+ object['productkey']
									+ "\">Delete</a></td>" + "</tbody>"
									+ "</table>");
		}

	} else {
		$('#layerData').hide();
	}

}
function btnSearch() {
	var s = $('#sKeyword').val();
	alert(s);
	if (s != 'undefined' & s.length > 0)
		window.location.href = getContextPath() + '/products/' + s;
}

var fieldsetEnableDisable = function(id, bol, div) {
	// load function
	$(div).click(function() {
		$(id).removeAttr("disabled", bol);
	});
}

function validationJquery(id, condition, space) {
	// var space=0;
	$("#" + id).keypress(
			function(event) {
				var inputValue = event.which;
				switch (condition) {
				case 'alphabet':
					if (!(inputValue >= 65 && inputValue <= 120)
							&& (inputValue != 32 && inputValue != 0)) {
						event.preventDefault();
					}
					if (!space) {
						if (inputValue == 8 | inputValue == 32)
							event.preventDefault();
					}

					break;
				case 'numeric':
					if (!(inputValue >= 48 && inputValue <= 57)) {
						event.preventDefault();
					}

					break;

				case 'username':
					if (inputValue == 8 | inputValue == 32)
						event.preventDefault();
					break;
				break;
			case 'email':
				var pattern = new RegExp(
						/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
				if (!pattern.test(emailAddress)) {
					$("#" + id).after("<p>Enter correct email id</p>");
					event.preventDefault();
				}
				if (!space) {
					if (inputValue == 8 | inputValue == 32)
						event.preventDefault();
				}
				break;
			break;

		default:
			if (!space) {
				if (inputValue == 8 | inputValue == 32)
					event.preventDefault();
			}
			;
		}
		// if(space==0)
		// if(inputValue == 8 | inputValue == 32)
		// event.preventDefault();

		// var spaceCount=$("#" + id).val().split(" ").length;
		// console.log(spaceCount+' '+(spaceCount >= length));
		// if(spaceCount >= length)
		// event.preventDefault();

	});
}
function validInputField(id, condition) {
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	var val = $("#" + id).val();
	switch (condition) {
	case 'validateEmail':
		return emailReg.test(val);
		break;
	case 'numeric':
		break;
	default:
		;
	}
}
var selectGet = function(id, da, msgId) {
	var mySelect = $(id);
	mySelect.html('<option value="0">select</option>');
	if (da != null) {
		for (var i = 0; i < da.length; i++) {
				mySelect.append('<option value="'+da[i].pdid+'">'+da[i].color+'</option>');
		}

	} else {
		$(msgId).append('<option>not available</option>');
	}
}
var getUrl=function(url){
	 var loc = window.location.pathname;
     loc = loc.substring(0, loc.lastIndexOf('/'));
     return loc+url;
}

var validatorAjax=function(id,ruleCall,msg,num){
	$.validator.setDefaults({
		debug : false,
		success : "valid"
	});
	$().ready(function() {
	
		
		$(id).validate({
			onsubmit : true,
			onblur : false,
			onkeyup : false,
			rules : ruleCall,
			messages : msg,
		    submitHandler: function(form) {
			     // onSubmit(form);
		    	submitForm(form,num);
			    }
		});
	});
}

var jDataTable=function(urlRequest,typeRequest,requestColumns,id,myFunction){
	$(id).DataTable({
		"processing" : true,
		"serverSide" : true,
		"pageLength" : 10,
		"autoWidth": false,
		"ajax" : {
			"url" : urlRequest,
			"type" : typeRequest,/* "GET",*/
			 "data":myFunction,
			"dataSrc" : function(json) {
				return $.parseJSON(json.data);
			}
		},
		"columns" : requestColumns,
	});
}





var getUrlParameter = function(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};


/* datatable with parametter */


function callDataTableWith (d,id,url,requestType,columnData,buttonData){
	/*  var da=function ( d ) {
		
        // d.custom = $('#myInput').val();
        // etc
    }*/
	 $(id).DataTable( {
           "processing" : true,
			"serverSide" : true,
			"bDestroy": true,
			"autoWidth": false,
			"pageLength" : 10,
            "dom": 'Bfrtip',
            "ajax" : {
				"url" : url,
				"type" : requestType,
				 "data": d,
				"dataSrc" : function(json) {
					return $.parseJSON(json.data);
				}
			},
			
			"columns" : columnData,
            "buttons": buttonData,
          } );
}


function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if ( (charCode > 31 && charCode < 48) || charCode > 57) {
        return false;
    }
    return true;
}







/* end datatable with parametter */


/*
 * 
 * 
 * //var da=eval(par.data); ///for (var i = 0; i < da.length; i++) { //var
 * object = da[i]; //for (var property in object) { // console.log('item ' + i + ': ' +
 * property + '=' + object[property]); //} //}
 * 
 * 
 * 
 * 
 * function call <p id="ajaxcontent"></p> jQuery(document).ajaxStart(function () {
 * //show ajax indicator ajaxindicatorstart('loading data.. please wait..');
 * }).ajaxStop(function () { //hide ajax indicator ajaxindicatorstop(); });
 */
/* ---- end:- function page loading */
function getCallHost() {
	window.location.pathname.substring(0, window.location.pathname.indexOf("/",
			2));
	var pathname = window.location.pathname;
	var url = window.location.href;
	console.log("Output;");  
	console.log(location.hostname);
	console.log(document.domain);
	alert(window.location.hostname)

	console.log("document.URL : "+"document.URL : "+document.URL);
	console.log("document.location.href : "+document.location.href);
	console.log("document.location.origin : "+document.location.origin);
	console.log("document.location.hostname : "+document.location.hostname);
	console.log("document.location.host : "+document.location.host);
	console.log("document.location.pathname : "+document.location.pathname);
}
