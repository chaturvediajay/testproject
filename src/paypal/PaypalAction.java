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
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaypalAction {

	private static final long serialVersionUID = 1L;
	private String resultString;
	private String token;
	private Map finalMap = new HashMap();

	public void paypalPay(HttpServletRequest request,HttpServletResponse response) {
		Integer payPercent = 10;
		Map result = new HashMap();
		String data1 = "";

		try {
			data1 = URLEncoder.encode("USER", "UTF-8") + "=" + URLEncoder.encode("jychtrvd-facilitator-1_api1.gmail.com", "UTF-8");
			data1 += "&" + URLEncoder.encode("PWD", "UTF-8") + "=" + URLEncoder.encode("2XK29ZBGV8JJNU6F", "UTF-8");
			data1 += "&" + URLEncoder.encode("SIGNATURE", "UTF-8") + "="
					+ URLEncoder.encode("AFcWxV21C7fd0v3bYYYRCpSSRl31AlJWi13cIzpQ6mmJHfArm9wPBuTs", "UTF-8");
			data1 += "&" + URLEncoder.encode("METHOD", "UTF-8") + "="
					+ URLEncoder.encode("SetExpressCheckout", "UTF-8");

			data1 += "&" + URLEncoder.encode("RETURNURL", "UTF-8") + "=" + URLEncoder.encode(
					request.getRequestURL().toString().replaceAll(request.getServletPath(), "") + "/paypal",
					"UTF-8");
			data1 += "&" + URLEncoder.encode("CANCELURL", "UTF-8") + "=" + URLEncoder.encode(
					request.getRequestURL().toString().replaceAll(request.getServletPath(), "") + "/cancel",
					"UTF-8");

			data1 += "&" + URLEncoder.encode("VERSION", "UTF-8") + "=" + URLEncoder.encode("104.0", "UTF-8");
			data1 += "&" + URLEncoder.encode("AMT", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8");
			data1 += "&" + URLEncoder.encode("CURRENCYCODE", "UTF-8") + "=" + URLEncoder.encode("INR", "UTF-8");
			data1 += "&" + URLEncoder.encode("L_NAME0", "UTF-8") + "=" + URLEncoder.encode("Sample Test", "UTF-8");
			data1 += "&" + URLEncoder.encode("L_AMT0", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8");
			data1 += "&" + URLEncoder.encode("CUSTOM", "UTF-8") + "="
					+ URLEncoder.encode("Thank You For business", "UTF-8");
			data1 += "&" + URLEncoder.encode("DESC", "UTF-8") + "=" + URLEncoder.encode("Product Details", "UTF-8");
			data1 += "&" + URLEncoder.encode("NOSHIPPING", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		result = post(data1);

		Iterator<?> iter = result.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();

		}

		if (result != null) {

			token = (String) result.get("TOKEN");
			String url = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express" + "-" + "checkout&token="
					+ (String) result.get("TOKEN");
			try {
				response.sendRedirect(url);
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}

	public String successPage(HttpServletRequest request) {

		String payerID = request.getParameter("PayerID");
		String token = request.getParameter("token");
		doPaypalPayment(payerID, token);
		return "success";
	}

	public String failurePage() {

		return "failurePage";
	}

	public void doPaypalPayment(String payerID, String token2) {
		Map result = new HashMap();
		String data = "";
		try {

			data = URLEncoder.encode("USER", "UTF-8") + "=" + URLEncoder.encode("jychtrvd-facilitator-1_api1.gmail.com", "UTF-8");
			data += "&" + URLEncoder.encode("PWD", "UTF-8") + "=" + URLEncoder.encode("2XK29ZBGV8JJNU6F", "UTF-8");
			data += "&" + URLEncoder.encode("SIGNATURE", "UTF-8") + "="
					+ URLEncoder.encode("AFcWxV21C7fd0v3bYYYRCpSSRl31AlJWi13cIzpQ6mmJHfArm9wPBuTs", "UTF-8");

			data += "&" + URLEncoder.encode("METHOD", "UTF-8") + "="
					+ URLEncoder.encode("DoExpressCheckoutPayment", "UTF-8");
			data += "&" + URLEncoder.encode("PAYERID", "UTF-8") + "=" + URLEncoder.encode(payerID, "UTF-8");
			data += "&" + URLEncoder.encode("PAYMENTACTION", "UTF-8") + "=" + URLEncoder.encode("Sale", "UTF-8");
			data += "&" + URLEncoder.encode("TOKEN", "UTF-8") + "=" + URLEncoder.encode(token2, "UTF-8");
			data += "&" + URLEncoder.encode("AMT", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8");
			data += "&" + URLEncoder.encode("VERSION", "UTF-8") + "=" + URLEncoder.encode("104.0", "UTF-8");
			data += "&" + URLEncoder.encode("CURRENCYCODE", "UTF-8") + "=" + URLEncoder.encode("INR", "UTF-8");

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

	// @Override
	// public void setSession(Map<String, Object> arg0) {
	// // TODO Auto-generated method stub
	//
	// }

	public void doPaypalPaymentWithCreditCard() {
		try {
			// Load the caller service

			// Create a new map to hold the result
			Map result = new HashMap();

			// Construct data request string
			String data = URLEncoder.encode("USER", "UTF-8") + "=" + URLEncoder.encode("jychtrvd-facilitator-1_api1.gmail.com", "UTF-8");
			data += "&" + URLEncoder.encode("PWD", "UTF-8") + "=" + URLEncoder.encode("2XK29ZBGV8JJNU6F", "UTF-8");
			data += "&" + URLEncoder.encode("SIGNATURE", "UTF-8") + "="
					+ URLEncoder.encode("AFcWxV21C7fd0v3bYYYRCpSSRl31AlJWi13cIzpQ6mmJHfArm9wPBuTs", "UTF-8");
			data += "&" + URLEncoder.encode("METHOD", "UTF-8") + "=" + URLEncoder.encode("DoDirectPayment", "UTF-8");
			data += "&" + URLEncoder.encode("AMT", "UTF-8") + "=" + URLEncoder.encode("20.10", "UTF-8");
			data += "&" + URLEncoder.encode("VERSION", "UTF-8") + "=" + URLEncoder.encode("80.0", "UTF-8");
			data += "&" + URLEncoder.encode("IPADDRESS", "UTF-8") + "=" + URLEncoder.encode("192.168.1.0", "UTF-8");
			data += "&" + URLEncoder.encode("PAYMENTACTION", "UTF-8") + "=" + URLEncoder.encode("Sale", "UTF-8");
			data += "&" + URLEncoder.encode("CREDITCARDTYPE", "UTF-8") + "=" + URLEncoder.encode("VISA", "UTF-8");
			data += "&" + URLEncoder.encode("ACCT", "UTF-8") + "=" + URLEncoder.encode("4311190570865589", "UTF-8");
			data += "&" + URLEncoder.encode("EXPDATE", "UTF-8") + "=" + URLEncoder.encode("112022", "UTF-8");
			data += "&" + URLEncoder.encode("CVV2", "UTF-8") + "=" + URLEncoder.encode("123", "UTF-8");
			data += "&" + URLEncoder.encode("FIRSTNAME", "UTF-8") + "=" + URLEncoder.encode("Jason", "UTF-8");
			data += "&" + URLEncoder.encode("LASTNAME", "UTF-8") + "=" + URLEncoder.encode("Michels", "UTF-8");
			data += "&" + URLEncoder.encode("STREET", "UTF-8") + "=" + URLEncoder.encode("123", "UTF-8");
			data += "&" + URLEncoder.encode("CITY", "UTF-8") + "=" + URLEncoder.encode("jaipur", "UTF-8");
			data += "&" + URLEncoder.encode("STATE", "UTF-8") + "=" + URLEncoder.encode("RJ", "UTF-8");
			data += "&" + URLEncoder.encode("ZIP", "UTF-8") + "=" + URLEncoder.encode("302020", "UTF-8");
			data += "&" + URLEncoder.encode("COUNTRYCODE", "UTF-8") + "=" + URLEncoder.encode("IN", "UTF-8");
			data += "&" + URLEncoder.encode("CURRENCYCODE", "UTF-8") + "=" + URLEncoder.encode("INR", "UTF-8");
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
	}// end of main function
public static void main(String[] args) {
	new  PaypalAction().doPaypalPaymentWithCreditCard();
}

}
