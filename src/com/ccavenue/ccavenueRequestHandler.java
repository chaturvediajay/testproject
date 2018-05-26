package com.ccavenue;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.cart.PaymentDetails;
import com.logic.CartLogic;
import com.model.LoginSession;
import com.model.ShippingModel;
import com.scope.DateConvert;

public class ccavenueRequestHandler {
	final static Logger logger = Logger.getLogger(ccavenueRequestHandler.class);
	private static String merchantId = "130952";
	private static String accessCode = "AVYI70ED30BL87IYLB";
	private static String workingKey = "788561F55D60BFDD004E8D45300F65D9";

	public static String RequestHandler(HttpServletRequest request) {
		String ccaRequest = "";
		// ccaRequest = ccaRequest + pname + "=" + pvalue + "&";
//		LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
//		ShippingModel sm = (ShippingModel) request.getSession().getAttribute("shippingModel");
		
//		HashMap<String, String> hm=new HashMap<>();
	
		ccaRequest="delivery_address=Vile Parle"
				+ "&delivery_name=Sam&language=EN&billing_address=Santacruz&promo_code="
				+ "&merchant_id=130952&integration_type=iframe_normal&tid=1500772238206&billing_tel=0229874789&billing_state=MH"
				+ "&billing_email=jychtrvd@gmail.com&customer_identifier=&billing_zip=400054¤cy=INR&merchant_param1=additional Info."
				+ "&merchant_param2=additional Info.&merchant_param3=additional Info.&merchant_param4=additional Info.&merchant_param5=additional Info.&delivery_country=India&amount=1.00&billing_country=India&delivery_state=Maharashtra&billing_name=Peter&billing_city=Mumbai&delivery_tel=0221234321&order_id=od25695&cancel_url=http://blagot.com/feb/ccavResponseHandler&delivery_city=Mumbai&delivery_zip=400038&redirect_url=http://blagot.com/feb/ccavResponseHandler&";
		
		
//		ccaRequest+= "billing_address" + "=" + "Mansarovar" + "&";
//		ccaRequest+= "billing_name" + "=" + "ajay" + "&";
//		ccaRequest+= "billing_city" + "=" + "jaipur" + "&";		
//		ccaRequest+= "billing_state" + "=" + "rajasthan" + "&";
//		ccaRequest+= "billing_zip" + "=" + "302020" + "&";
//		ccaRequest+= "billing_country" + "=" + "india" + "&";
//		ccaRequest+= "billing_tel" + "=" + "9829918923" + "&";
//		ccaRequest+= "billing_email" + "=" + "jychtrvd@gmail.com" + "&";
//		
//		ccaRequest+= "delivery_name" + "=" + "Mansarovar" + "&";
//		ccaRequest+= "delivery_address" + "=" + "ajay" + "&";
//		ccaRequest+= "delivery_city" + "=" + "jaipur" + "&";		
//		ccaRequest+= "delivery_state" + "=" + "rajasthan" + "&";
//		ccaRequest+= "delivery_zip" + "=" + "302020" + "&";
//		ccaRequest+= "delivery_country" + "=" + "india" + "&";
//		ccaRequest+= "delivery_tel" + "=" + "9829918923" + "&";
//		ccaRequest+= "billing_email" + "=" + "jychtrvd@gmail.com" + "&";
//		
//		ccaRequest+= "merchant_param1" + "=" + "merchant_param1" + "&";
//		ccaRequest+= "merchant_param2" + "=" + "merchant_param2" + "&";
//		ccaRequest+= "merchant_param3" + "=" + "merchant_param3" + "&";
//		ccaRequest+= "merchant_param4" + "=" + "merchant_param4" + "&";
//		ccaRequest+= "merchant_param5" + "=" + "merchant_param5" + "&";
//
//		ccaRequest+= "language" + "=" + "en" + "&";
//		ccaRequest+= "promo_code" + "=" + "" + "&";
//		ccaRequest+= "merchant_id" + "=" + "130952" + "&";
//		ccaRequest+= "customer_identifier" + "=" + "9999999999" + "&";
//		
//		ccaRequest+= "redirect_url" + "=" + "/google" + "&";
//		ccaRequest+= "cancel_url" + "=" + "/hotmail" + "&";
//		ccaRequest+= "amount" + "=" + "1.0" + "&";
//
//		ccaRequest+= "tid" + "=" + "1500704092715" + "&";
//		ccaRequest+= "order_id" + "=" + "od59654123" + "&";
//		ccaRequest+= "currency" + "=" + "INR" ;
//		
		
		
	
		
	

		
	
		
		// Iterate all key/value pairs
//		for (Entry<String, String> entry : hm.entrySet()) {
//			ccaRequest += ccaRequest + entry.getKey() + "=" + entry.getValue() + "&";
//        }
		
	return ccaRequest;
		
		
		
	}
public static void main(String[] args) {
	RequestHandler(null);
}
	private boolean saveOrder(Map<String, String> values, HttpServletRequest request) {
		boolean bol = false;
		if (values.get("txnid") != null & !values.get("txnid").isEmpty()) {
			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
			ShippingModel sm = (ShippingModel) request.getSession().getAttribute("shippingModel");
			sm.setOrderId(values.get("udf1"));
			sm.setUid(ls.getId());
			bol = CartLogic.orderDetailSave(sm, values.get("udf1"), request);
			PaymentDetails pd = new PaymentDetails();
			pd.setTxid(values.get("txnid"));
			pd.setOrderId(values.get("udf1"));
			pd.setDatetime(DateConvert.nowTimeGMT());
//			bol = OrderLogic.saveOrder(pd);
		}
		return bol;
		// --txid-- udf 1 to 10 -- amount --- payumoneyid
		// ---status---unmappedstatus---addedon(datetime) --
		// PG_TYPE---bankcode--error--
		// paymentmethod-- ordeid--txid --status--datetime--visible

		// - - 0:pending 1:sucessfull 2:cancel 3:failer
	}
}
