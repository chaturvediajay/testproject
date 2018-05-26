package com.cart;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import com.logic.PaymentLogic;

public class TrasactionModel {
	public TrasactionModel() {
		// TODO Auto-generated constructor stub
	}

	public static JsonObject saveData(HttpServletRequest request) {
		Enumeration<String> kayParams = request.getParameterNames();
		JsonObject jobj = new JsonObject();
		while (kayParams.hasMoreElements()) {
			String key = (String) kayParams.nextElement();
			String value = request.getParameter(key).toString();
			jobj = jobjData(key, value, jobj);
			// result += key + "=" + request.getParameter(key) +
			// (kayParams.hasMoreElements() ? "," : "");
		}
		jobj.addProperty("payment_mode", "payumoney");
		String txStatus = jobj.get("status").getAsString();
		System.out.println("txStatus:- " + txStatus);
		if (txStatus.equals("success") | txStatus.equals("\"success\"")) {
			PaymentLogic.trasactionUpdate(jobj.get("udf1").toString(), jobj.get("txnid").toString(), jobj.toString(), 1);
		} else
			PaymentLogic.trasactionUpdate(jobj.get("udf1").toString(), jobj.get("txnid").toString(), jobj.toString(), 2);

		return jobj;
	}

	private static JsonObject jobjData(String key, String value, JsonObject jobj) {
		if (!value.isEmpty()) {
			if (key.equals("mihpayid"))
				jobj.addProperty(key, value);
			if (key.equals("status"))
				jobj.addProperty(key, value);
			else if (key.equals("mode"))
				jobj.addProperty(key, value);
			else if (key.equals("unmappedstatus"))
				jobj.addProperty(key, value);
			else if (key.equals("txnid"))
				jobj.addProperty(key, value);
			else if (key.equals("amount"))
				jobj.addProperty(key, value);
			else if (key.equals("addedon"))
				jobj.addProperty(key, value);
			else if (key.equals("email"))
				jobj.addProperty(key, value);
			else if (key.equals("phone"))
				jobj.addProperty(key, value);
			else if (key.startsWith("udf"))
				jobj.addProperty(key, value);
			else if (key.startsWith("field"))
				jobj.addProperty(key, value);
			else if (key.equals("PG_TYPE"))
				jobj.addProperty(key, value);
			else if (key.equals("bank_ref_num"))
				jobj.addProperty(key, value);
			else if (key.equals("bankcode"))
				jobj.addProperty(key, value);
			else if (key.equals("bankcode"))
				jobj.addProperty(key, value);
			else if (key.equals("error_Message"))
				jobj.addProperty(key, value);
			else if (key.equals("cardnum"))
				jobj.addProperty(key, value);
			else if (key.equals("cardhash"))
				jobj.addProperty(key, value);
//			else if (key.equals("amount_split"))
//				jobj.addProperty(key, value);
			else if (key.equals("net_amount_debit"))
				jobj.addProperty(key, value);
			else if (key.equals("payuMoneyId"))
				jobj.addProperty(key, value);
			else if (key.equals("discount"))
				jobj.addProperty(key, value);

		}
		return jobj;
	}

	private static int getFirstNumericDigit(String num) {
		Matcher matcher = Pattern.compile("\\d+").matcher(num);
		matcher.find();
		try {
			return Integer.valueOf(matcher.group());
		} catch (IllegalStateException e) {
		}
		return 0;

	}

	public static void main(String args[]) {
		String s = "";

		System.out.println(s.isEmpty());

	}
}
