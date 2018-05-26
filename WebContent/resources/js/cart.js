var js1 = new Object();
var bol = true;
checkBusiness = function() {
	js1.bName = $('#bName').val().trim();
	js1.bAddress = $('#bAddress').val().trim();
	js1.bArea = $('#bArea').val().trim();
	js1.bLandmark = $('#bLandmark').val().trim();
	js1.bCity = $('#bCity').val().trim();
	js1.bState = $('#bState').val().trim();
	js1.bPincode = $('#bPincode').val().trim();
	js1.bContact = $('#bContact').val().trim();

	if (checkEmpty('bName'))
		addTextAfterInput('bName', 'Enter name!')
	if (checkEmpty('bAddress'))
		addTextAfterInput('bArea', 'Enter address!')
	if (checkEmpty('bArea'))
		addTextAfterInput('bArea', 'Enter address area!')
	if (checkEmpty('bLandmark'))
		addTextAfterInput('bLandmark', 'Enter adress landmark!')
	if (checkEmpty('bCity'))
		addTextAfterInput('bCity', 'Enter city!')
	if (checkEmpty('bState'))
		addTextAfterInput('bState', 'Enter state!')
	if (checkEmpty('bPincode'))
		addTextAfterInput('bPincode', 'Enter pincode!')
	if (checkEmpty('bContact'))
		addTextAfterInput('bContact', 'Enter contact!')
}
getAddress = function() {
	$('#bAddr').html(
			'<strong>' + $('#bName').val() + '</strong><br>'
					+ $('#bAddress').val() + ',' + $('#bArea').val() + '<br>'
					+ $('#bLandmark').val() + '<br>' + $('#bCity').val()
					+ '<br>' + $('#bState').val() + '-' + $('#bPincode').val()
					+ '<br><abbr title="Phone">P:</abbr> +91-'
					+ $('#bContact').val()).slideDown('slow');
	$('#sAddr').html(
			'<strong>' + $('#sName').val() + '</strong><br>'
					+ $('#sAddress').val() + ',' + $('#sArea').val() + '<br>'
					+ $('#sLandmark').val() + '<br>' + $('#sCity').val()
					+ '<br>' + $('#sState').val() + '-' + $('#sPincode').val()
					+ '<br><abbr title="Phone">P:</abbr> +91-'
					+ $('#sContact').val()).slideDown('slow');
}
addAddress = function() {
	checkBusiness();
	var json = {
		'json' : JSON.stringify(js1)
	};
	ajaxCallRequestResponse('json', json, 'post', '/shipping', output)
}
output = function(res) {
	var obj = JSON.stringify(res);
	obj = JSON.parse(obj);
	if(obj.res)
		$('#checkoutWizard').wizard('next');
	else
		alert("please try later");
}
addTextAfterInput = function(id, msg) {
	$("#" + id).after(msg);

}
checkEmpty = function(id) {
	if ($('#' + id).val() == '0' || $('#' + id).val() == ''
			|| $('#' + id).val() == 'undefined' || $('#' + id).val() == null) {
		bol = false;
		return true;
	}

	return false;
}