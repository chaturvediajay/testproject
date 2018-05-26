package com.ccavenue;

import com.google.gson.JsonObject;

public class JsonObjectCcavenue {
	
	public static JsonObject jobjData(String key, String value, JsonObject jobj) {
		if (!value.isEmpty()) {
			if (key.equals("order_id"))
				jobj.addProperty(key, value);
			else if (key.equals("tracking_id"))
				jobj.addProperty(key, value);
			else if (key.equals("bank_ref_no"))
				jobj.addProperty(key, value);
			else if (key.equals("order_status"))
				jobj.addProperty(key, value);
			else if (key.equals("order_status"))
				jobj.addProperty(key, value);
			else if (key.equals("card_name"))
				jobj.addProperty(key, value);
			else if (key.equals("status_message"))
				jobj.addProperty(key, value);
			else if (key.equals("status_message"))
				jobj.addProperty(key, value);
			else if (key.equals("currency"))
				jobj.addProperty(key, value);
			else if (key.equals("amount"))
				jobj.addProperty(key, value);
			else if (key.equals("merchant_param1"))
				jobj.addProperty(key, value);
			else if (key.equals("merchant_param2"))
				jobj.addProperty(key, value);
			else if (key.equals("merchant_param3"))
				jobj.addProperty(key, value);
			else if (key.equals("merchant_param4"))
				jobj.addProperty(key, value);
			else if (key.equals("merchant_param5"))
				jobj.addProperty(key, value);
			else if (key.equals("offer_type"))
				jobj.addProperty(key, value);
			else if (key.equals("offer_code"))
				jobj.addProperty(key, value);
			else if (key.equals("discount_value"))
				jobj.addProperty(key, value);
			else if (key.equals("mer_amount"))
				jobj.addProperty(key, value);
			else if (key.equals("trans_date"))
				jobj.addProperty(key, value);







//			pname:-  bin_country pvalue:-
			
			
			
			
//			pname:- order_status  pvalue:-Success
//			pname:-  failure_message pvalue:-
//			pname:-  payment_mode pvalue:-Net Banking

		}
		return jobj;
	}

}
