package com.cart;

import java.util.HashMap;
import java.util.Properties;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.logic.CartLogic;
import com.model.CartModel;
import com.scope.TrippleDes;

public class OrderInit {
	public static String getOrderId() {
		return TrippleDes.randomStringNumber(2, "str") + TrippleDes.randomStringNumber(10, "num");
	}

	public static double totalOrderAmt(HttpServletRequest request) {
		double total1 = 0.00d;
		int num = 0;
		try {
			Properties properties = new Properties();
			properties
					.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("trasaction.properties"));
			System.out.println(properties.get("charge.weight"));
			HashMap<String, CartModel> hm = CartLogic.getProductInfoList(request);
			if (hm != null) {
				if (hm.size() > 0) {
					for (Entry<String, CartModel> entry : hm.entrySet()) {
						CartModel cm = entry.getValue();
						System.out
								.println("before total:- " + total1 + " size:- " + hm.size() + " : " + entry.getKey());
						total1 = total1 + (cm.getQty() * Double.parseDouble(cm.getSmrp()));
						System.out.println("after total:- " + total1);
						num++;
					}
				}
			}
			if (total1 <= 500)
				total1 = total1 + (Double.parseDouble(properties.get("charge.corrier").toString()) * num);
			System.out.println("after final total  :- " + total1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			System.out.println("total1   " + total1);
		}

		return total1;
	}

}
