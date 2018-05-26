package paypal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.PaymentLogic;

import java.util.*;

public class IntregationPayPal {
	private String resultString;
	private String token="";
	private Map finalMap = new HashMap();

	@SuppressWarnings("rawtypes")
	public String paypalPay(HttpServletRequest request, HttpServletResponse response, double amt,String trasaction_code) {
		Map result = new HashMap();
		String data1 = "";
		try {

			/* get url */

			String scheme = request.getScheme() + "://";
			String serverPort = (String.valueOf(request.getServerPort()) == "80") ? "" : ":" + request.getServerPort();
			String serverName = request.getServerName();
			String contextPath = request.getContextPath();
			String url = scheme + serverName + serverPort + contextPath;

			data1 = URLEncoder.encode("USER", "UTF-8") + "="
					+ URLEncoder.encode("moon_orbitter_api1.yahoo.com", "UTF-8");
			data1 += "&" + URLEncoder.encode("PWD", "UTF-8") + "=" + URLEncoder.encode("6U7RE5F3J8Y7L4FD", "UTF-8");
			data1 += "&" + URLEncoder.encode("SIGNATURE", "UTF-8") + "="
					+ URLEncoder.encode("AUeySLdFVLcjbkBc6TMw9LPWYQDiAp1Mv3irq2Cr88k7xAjtCiPWTWWz", "UTF-8");
			data1 += "&" + URLEncoder.encode("METHOD", "UTF-8") + "="
					+ URLEncoder.encode("SetExpressCheckout", "UTF-8");

			data1 += "&" + URLEncoder.encode("RETURNURL", "UTF-8") + "=" + URLEncoder.encode(url + "/reciept?trasaction_code="+trasaction_code+"&method=paypal", "UTF-8");
			data1 += "&" + URLEncoder.encode("CANCELURL", "UTF-8") + "=" + URLEncoder.encode(url + "/cancel?trasaction_code="+trasaction_code+"&method=paypal", "UTF-8");

			data1 += "&" + URLEncoder.encode("VERSION", "UTF-8") + "=" + URLEncoder.encode("104.0", "UTF-8");
			data1 += "&" + URLEncoder.encode("AMT", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(amt), "UTF-8");
			data1 += "&" + URLEncoder.encode("CURRENCYCODE", "UTF-8") + "=" + URLEncoder.encode("USD", "UTF-8");
			data1 += "&" + URLEncoder.encode("L_NAME0", "UTF-8") + "=" + URLEncoder.encode("blagot infotech", "UTF-8");
			data1 += "&" + URLEncoder.encode("L_AMT0", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(amt), "UTF-8");
			data1 += "&" + URLEncoder.encode("CUSTOM", "UTF-8") + "="
					+ URLEncoder.encode("Thank You For business", "UTF-8");
			data1 += "&" + URLEncoder.encode("DESC", "UTF-8") + "=" + URLEncoder.encode("Product Details", "UTF-8");
			data1 += "&" + URLEncoder.encode("NOSHIPPING", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");

			// INSURANCEAMT=0.00
			// SHIPDISCAMT=0.00
			// INSURANCEOPTIONOFFERED=false
			// L_NAME0=Books
			// L_NAME1=CDs
			// L_NUMBER0=ABC123
			// L_NUMBER1=BY-Z4736
			// L_QTY0=2
			// L_QTY1=3
			// L_TAXAMT0=0.00
			// L_TAXAMT1=0.00
			// L_AMT0=154.00
			// L_AMT1=50.00
			// PAYMENTREQUEST_0_CURRENCYCODE=USD
			// PAYMENTREQUEST_0_AMT=524.20
			// PAYMENTREQUEST_0_ITEMAMT=458.00
			// PAYMENTREQUEST_0_SHIPPINGAMT=20.00
			// PAYMENTREQUEST_0_HANDLINGAMT=0.00
			// PAYMENTREQUEST_0_TAXAMT=46.20
			// PAYMENTREQUEST_0_DESC=test EC payment
			// PAYMENTREQUEST_0_NOTIFYURL=http://www.ccaples.com/mts/ipn/ipn-listener.php
			// PAYMENTREQUEST_0_INSURANCEAMT=0.00
			// PAYMENTREQUEST_0_SHIPDISCAMT=0.00
			// PAYMENTREQUEST_0_SELLERPAYPALACCOUNTID=chad@yourdomain.com
			// PAYMENTREQUEST_0_INSURANCEOPTIONOFFERED=false
			// PAYMENTREQUEST_0_SHIPTONAME=Usman Adeel Mulla
			// PAYMENTREQUEST_0_SHIPTOSTREET=1 Main St
			// PAYMENTREQUEST_0_SHIPTOCITY=San Jose
			// PAYMENTREQUEST_0_SHIPTOSTATE=CA
			// PAYMENTREQUEST_0_SHIPTOZIP=95131
			// PAYMENTREQUEST_0_SHIPTOCOUNTRYCODE=US
			// PAYMENTREQUEST_0_SHIPTOCOUNTRYNAME=United States
			// PAYMENTREQUEST_0_ADDRESSSTATUS=Confirmed
			// L_PAYMENTREQUEST_0_NAME0=Books
			// L_PAYMENTREQUEST_0_NAME1=CDs
			// L_PAYMENTREQUEST_0_NUMBER0=ABC123
			// L_PAYMENTREQUEST_0_NUMBER1=BY-Z4736
			// L_PAYMENTREQUEST_0_QTY0=2
			// L_PAYMENTREQUEST_0_QTY1=3
			// L_PAYMENTREQUEST_0_TAXAMT0=0.00
			// L_PAYMENTREQUEST_0_TAXAMT1=0.00
			// L_PAYMENTREQUEST_0_AMT0=154.00
			// L_PAYMENTREQUEST_0_AMT1=50.00
			// PAYMENTREQUESTINFO_0_ERRORCODE=0

		} catch (Exception e) {
			e.printStackTrace();
		}
		result = post(data1);
		Iterator<?> iter = result.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();
			System.out.println(mEntry.getKey() + " : " + mEntry.getValue());

		}
		// Sandbox (for testing) : https://api.sandbox.paypal.com
		// Live (production) : https://api.paypal.com
		String url = "";
		if (result != null) {

			token = (String) result.get("TOKEN");
			url = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express" + "-" + "checkout&token="
					+ (String) result.get("TOKEN");

		}

		return url;
	}

	public String successPage(HttpServletRequest request) {

		// ServletRequestAttributes attr = (ServletRequestAttributes)
		// RequestContextHolder.currentRequestAttributes();
		// HttpServletRequest request = attr.getRequest();

		// HttpServletRequest request = ServletActionContext.getRequest();

		String payerID = request.getParameter("PayerID");
		token = request.getParameter("token");
		doPaypalPayment(payerID, token);
		return "success";
	}

	public String failurePage() {

		return "failurePage";
	}

	public void doPaypalPayment(String payerID, String token2) {
		Map result = new HashMap();
		String data = "";
		
		double amt=PaymentLogic.getAmt(token2);
		try {

			data = URLEncoder.encode("USER", "UTF-8") + "="
					+ URLEncoder.encode("moon_orbitter_api1.yahoo.com", "UTF-8");
			data += "&" + URLEncoder.encode("PWD", "UTF-8") + "=" + URLEncoder.encode("6U7RE5F3J8Y7L4FD", "UTF-8");
			data += "&" + URLEncoder.encode("SIGNATURE", "UTF-8") + "="
					+ URLEncoder.encode("AUeySLdFVLcjbkBc6TMw9LPWYQDiAp1Mv3irq2Cr88k7xAjtCiPWTWWz", "UTF-8");

			data += "&" + URLEncoder.encode("METHOD", "UTF-8") + "="
					+ URLEncoder.encode("DoExpressCheckoutPayment", "UTF-8");
			data += "&" + URLEncoder.encode("PAYERID", "UTF-8") + "=" + URLEncoder.encode(payerID, "UTF-8");
			data += "&" + URLEncoder.encode("PAYMENTACTION", "UTF-8") + "=" + URLEncoder.encode("Sale", "UTF-8");
			data += "&" + URLEncoder.encode("TOKEN", "UTF-8") + "=" + URLEncoder.encode(token2, "UTF-8");
			data += "&" + URLEncoder.encode("AMT", "UTF-8") + "=" + URLEncoder.encode(amt+"", "UTF-8");
			data += "&" + URLEncoder.encode("VERSION", "UTF-8") + "=" + URLEncoder.encode("104.0", "UTF-8");
			data += "&" + URLEncoder.encode("CURRENCYCODE", "UTF-8") + "=" + URLEncoder.encode("USD", "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		result = post(data);
	}

	public void deformatNVP() {
		try {
			String delims = "[&]";
			String equals = "[=]";
			String[] tokens = this.resultString.split(delims);

			System.out.println("resultString:-  " + resultString);
			
			
			PaymentLogic.updateOrderStatus(resultString,token);
			
			
			System.out.println("tokens.length  " + tokens.length);

			for (int i = 0; i < tokens.length; i++) {
				String[] newTokens = tokens[i].split(equals);
				this.finalMap.put(URLDecoder.decode(newTokens[0], "UTF-8"), URLDecoder.decode(newTokens[1], "UTF-8"));
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		// return finalMap;
	}

	public Map post(String data) {
		try {
			// Connect to the url
			URL url = new URL("https://api-3t.sandbox.paypal.com/nvp");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			// Post the data
			System.out.println("data : " + data);
			wr.write(data);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			this.resultString = rd.readLine();
			deformatNVP();
			wr.close();
			rd.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return finalMap;
	}

	public void doPaypalPaymentWithCreditCard() {
		try {
			// Load the caller service

			// Create a new map to hold the result
			Map result = new HashMap();

			// Construct data request string
			String data = URLEncoder.encode("USER", "UTF-8") + "="
					+ URLEncoder.encode("moon_orbitter_api1.yahoo.com", "UTF-8");
			data += "&" + URLEncoder.encode("PWD", "UTF-8") + "=" + URLEncoder.encode("6U7RE5F3J8Y7L4FD", "UTF-8");
			data += "&" + URLEncoder.encode("SIGNATURE", "UTF-8") + "="
					+ URLEncoder.encode("AUeySLdFVLcjbkBc6TMw9LPWYQDiAp1Mv3irq2Cr88k7xAjtCiPWTWWz", "UTF-8");
			data += "&" + URLEncoder.encode("METHOD", "UTF-8") + "=" + URLEncoder.encode("DoDirectPayment", "UTF-8");
			data += "&" + URLEncoder.encode("AMT", "UTF-8") + "=" + URLEncoder.encode("20.10", "UTF-8");
			data += "&" + URLEncoder.encode("VERSION", "UTF-8") + "=" + URLEncoder.encode("80.0", "UTF-8");
			data += "&" + URLEncoder.encode("IPADDRESS", "UTF-8") + "=" + URLEncoder.encode("192.168.1.0", "UTF-8");
			data += "&" + URLEncoder.encode("PAYMENTACTION", "UTF-8") + "=" + URLEncoder.encode("Sale", "UTF-8");
			data += "&" + URLEncoder.encode("CREDITCARDTYPE", "UTF-8") + "=" + URLEncoder.encode("Visa", "UTF-8");
			data += "&" + URLEncoder.encode("ACCT", "UTF-8") + "=" + URLEncoder.encode("4532513511140817", "UTF-8");
			data += "&" + URLEncoder.encode("EXPDATE", "UTF-8") + "=" + URLEncoder.encode("102014", "UTF-8");
			data += "&" + URLEncoder.encode("CVV2", "UTF-8") + "=" + URLEncoder.encode("123", "UTF-8");
			data += "&" + URLEncoder.encode("FIRSTNAME", "UTF-8") + "=" + URLEncoder.encode("Jason", "UTF-8");
			data += "&" + URLEncoder.encode("LASTNAME", "UTF-8") + "=" + URLEncoder.encode("Michels", "UTF-8");
			data += "&" + URLEncoder.encode("STREET", "UTF-8") + "=" + URLEncoder.encode("123", "UTF-8");
			data += "&" + URLEncoder.encode("CITY", "UTF-8") + "=" + URLEncoder.encode("Papillion", "UTF-8");
			data += "&" + URLEncoder.encode("STATE", "UTF-8") + "=" + URLEncoder.encode("NE", "UTF-8");
			data += "&" + URLEncoder.encode("ZIP", "UTF-8") + "=" + URLEncoder.encode("68046", "UTF-8");
			data += "&" + URLEncoder.encode("COUNTRYCODE", "UTF-8") + "=" + URLEncoder.encode("US", "UTF-8");

			result = post(data);

			// This will iterate through the entire response map
			Iterator iter = result.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry mEntry = (Map.Entry) iter.next();
				System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
			}

			// Now that you have a response check to see if it is a success
			String ack = "" + result.get("ACK");
			if ("Success".equals(ack)) {
				System.out.println("Congratulations your transaction was a success");
			} else {
				System.out.println("There was an error with your request.");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public String getToken() {
		return token;
	}

	public static void main(String[] args) {
		String qu = "TOKEN=EC%2d90J63013E3691051F&SUCCESSPAGEREDIRECTREQUESTED=false&TIMESTAMP=2018%2d04%2d16T10%3a02%3a55Z&CORRELATIONID=c606cfe5579fb"
				+ "&ACK=Success&VERSION=104%2e0&BUILD=45840284&TRANSACTIONID=4FE20382G0552003S&TRANSACTIONTYPE=expresscheckout&PAYMENTTYPE=instant"
				+ "&ORDERTIME=2018%2d04%2d16T10%3a02%3a55Z&AMT=1500%2e00&FEEAMT=43%2e80&TAXAMT=0%2e00&CURRENCYCODE=USD&PAYMENTSTATUS=Completed"
				+ "&PENDINGREASON=None&REASONCODE=None&PROTECTIONELIGIBILITY=Eligible&INSURANCEOPTIONSELECTED=false&SHIPPINGOPTIONISDEFAULT=false"
				+ "&PAYMENTINFO_0_TRANSACTIONID=4FE20382G0552003S&PAYMENTINFO_0_TRANSACTIONTYPE=expresscheckout&PAYMENTINFO_0_PAYMENTTYPE=instant"
				+ "&PAYMENTINFO_0_ORDERTIME=2018%2d04%2d16T10%3a02%3a55Z&PAYMENTINFO_0_AMT=1500%2e00"
				+ "&PAYMENTINFO_0_FEEAMT=43%2e80&PAYMENTINFO_0_TAXAMT=0%2e00&PAYMENTINFO_0_CURRENCYCODE=USD"
				+ "&PAYMENTINFO_0_PAYMENTSTATUS=Completed&PAYMENTINFO_0_PENDINGREASON=None&PAYMENTINFO_0_REASONCODE=None"
				+ "&PAYMENTINFO_0_PROTECTIONELIGIBILITY=Eligible&PAYMENTINFO_0_PROTECTIONELIGIBILITYTYPE=ItemNotReceivedEligible%2cUnauthorizedPaymentEligible"
				+ "&PAYMENTINFO_0_SELLERPAYPALACCOUNTID=moon_orbitter%40yahoo%2ecom&PAYMENTINFO_0_SECUREMERCHANTACCOUNTID=KFWN243JLKM52&PAYMENTINFO_0_ERRORCODE=0"
				+ "&PAYMENTINFO_0_ACK=Success";
		 String que[]=qu.split("&");
		 for(int i=0;i<que.length;i++){
		
		 System.out.println(que[i]);
		 }

//		Map params = getQueryMap(qu);
//		String id = (String) params.get("ACK");
//		System.out.println(id);
	}

	public static Map<String, String> getQueryMap(String query) {
		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String[] p = param.split("=");
			String name = p[0];
			if (p.length > 1) {
				String value = p[1];
				map.put(name, value);
			}
		}
		return map;
	}

	public String getResultString() {
		return resultString;
	}
	
//{"order_id":"sl9297813809","tracking_id":"306003435193","bank_ref_no":"1511337703911","order_status":"Success","card_name":"AvenuesTest","status_message":"Y","currency":"INR","amount":"1500.0","merchant_param1":"2966035902","merchant_param2":"ccavenue","merchant_param3":"additional Info","merchant_param4":"additional Info","merchant_param5":"additional Info","offer_type":"null","offer_code":"null","discount_value":"0.0","mer_amount":"1500.0","trans_date":"22/11/2017 13:32:19","payment_mode":"paypal","firstname":"Mahesh Singh","lastname":"Mahesh Singh"}
}

