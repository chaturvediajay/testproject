package com.scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;

import com.cart.OrderInit;
import com.logic.CartLogic;
import com.logic.ProductLogic;
import com.model.CartModel;

public class CartSession {
	final static Logger logger = Logger.getLogger(CartSession.class);
	private double grand_total=0;
	private double subtotal=0;
	private double charge_corrier=0;
	private  double charge_free=0;
	private  double ship_charge=0;

	public boolean removeValue(String key, HttpServletRequest request) {
		boolean bol = false;
		try {
			SessionUser su = new SessionUser();
			if (su.getSession(request, "pkey")) {
				HashMap<String, CartModel> hmap = (HashMap<String, CartModel>) request.getSession()
						.getAttribute("pkey");
				hmap.remove(key);
				if (hmap.size() > 0)
					su.setSessionHM(request, "pkey", hmap);
				else
					request.getSession().removeAttribute("pkey");
				bol = true;
			}

		} catch (Exception e) {
			logger.error("CartSession error(41)removeValue  " + e.toString());
		}
		return bol;
	}

	public static CartModel GetSesionPkeyValue(String pkey, HttpServletRequest request) {
		CartModel cm = null;
		try {
			// cm = new ProductLogic().getProductInfo(pkey, "pkey",request);
		} catch (Exception e) {
			logger.error("CartSession error(50)CartModel  " + e.toString());
		}
		return cm;
	}

	public List<CartModel> removeValueJq(String key, HttpServletRequest request) {
		List<CartModel> lcm = null;

		try {
			SessionUser su = new SessionUser();
			if (su.getSession(request, "pkey")) {
				HashMap<String, CartModel> hmap = (HashMap<String, CartModel>) request.getSession()
						.getAttribute("pkey");
				hmap.remove(key);
				if (hmap.size() > 0)
					su.setSessionHM(request, "pkey", hmap);
				else
					request.getSession().removeAttribute("pkey");
				// lcm = CartSession.listCartModel(request);
				lcm = CartSession.getCartSession(request);

			}
		} catch (Exception e) {
			logger.error("CartSession error(74)removeValueJq  " + e.toString());
		}
		return lcm;
	}

	public static boolean updateCartPkey(HttpServletRequest request, String pkey) {
		try {
			List<CartModel> lcm = new ArrayList<>();
			HashMap<String, CartModel> hcm = new HashMap<>();
			SessionUser su = new SessionUser();
			if (su.getSession(request, "pkey")) {
				HashMap<String, CartModel> hmap = (HashMap<String, CartModel>) request.getSession()
						.getAttribute("pkey");
				if (hmap.size() > 0) {
					for (Map.Entry<String, CartModel> entry : hcm.entrySet()) {
						String key = entry.getKey();
						// do something with key and/or tab
					}
				}
			}
		} catch (Exception e) {
			logger.error("CartSession error(94)updateCartPkey  " + e.toString());
		}

		return false;
	}

	private static String keyValue(HttpServletRequest request) {
		if (request.getParameter("key") != null) {
			String key[] = request.getParameter("key").split("-");
			// String pkey = key[key.length - 1];
			return key[key.length - 1];
		}
		return null;

	}

	public static List<CartModel> getCartSession(HttpServletRequest request) {
		Session session = null;
		SessionUser su = new SessionUser();
		HashMap<String, CartModel> hmap = null;
		List<CartModel> lcm = null;
		try {
			if (request.getParameter("scope") != null) {
				String key[] = request.getParameter("scope").split("-");
				String pkey = key[key.length - 1].toString();
				if (su.getSession(request, "pkey")) {
					lcm = new ArrayList<>();
					hmap = (HashMap<String, CartModel>) request.getSession().getAttribute("pkey");
					CartModel cm = null;
					if (hmap.containsKey(pkey))
						cm = hmap.get(pkey);
					else
						cm = CartLogic.addCart(request);
					hmap.put(pkey, cm);

				} else {
					lcm = new ArrayList<>();
					hmap = new HashMap<>();
					CartModel cm2 = CartLogic.addCart(request);
					hmap.put(pkey, cm2);
					System.out.println("put new");
				}
			} else {
				if (su.getSession(request, "pkey")) {
					hmap = (HashMap<String, CartModel>) request.getSession().getAttribute("pkey");
				}
			}
			lcm = getCartData(hmap);

			su.setSessionListObject(request, "pkey", hmap);
		} catch (Exception e) {
			logger.error("CartSession error(145)getCartSession  " + e.toString());
		} finally {
			if (session != null)
				session.close();
		}
		return lcm;

	}

	public static List<CartModel> getCartData(HashMap<String, CartModel> hm) {
		List<CartModel> lcm = null;
		try {
			if (hm != null) {
				if (hm != null) {
					if (hm.size() > 0) {
						lcm = new ArrayList<>();
						for (Map.Entry<String, CartModel> entry : hm.entrySet()) {
							CartModel cm = entry.getValue();
							lcm.add(cm);
						}
					}
				}
			} else
				lcm = Collections.emptyList();
		} catch (Exception e) {
			logger.error("CartSession error(162)getCartData  " + e.toString());
		}
		return lcm;
	}

	public CartModel getCartSessionPkeyValue(HttpServletRequest request, String pkey) {
		CartModel cm = null;

		HashMap<String, CartModel> hmap = null;

		SessionUser su = new SessionUser();

		try {
			if (su.getSession(request, "pkey")) {

				hmap = (HashMap<String, CartModel>) request.getSession().getAttribute("pkey");
				if (hmap.containsKey(pkey))
					cm = hmap.get(pkey);
				getCartValueSumAmt(request);

			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return cm;

	}

	private  void getCartValueSumAmt(HttpServletRequest request) {
		int temp_total=0;
		HashMap<String, CartModel> hmap = null;
		SessionUser su = new SessionUser();
		try {
			if (su.getSession(request, "pkey")) {
				grand_total=0;
				subtotal=0;
				ship_charge=0;
				hmap = (HashMap<String, CartModel>) request.getSession().getAttribute("pkey");
				if (hmap.size() > 0) {
					getPropertyChargeCourier();
					for (Map.Entry<String, CartModel> entry : hmap.entrySet()) {
						String key = entry.getKey();
						CartModel cm = entry.getValue();
						subtotal+=Double.parseDouble(cm.getSmrp())*cm.getQty();
						temp_total+=Double.parseDouble(cm.getSmrp())*cm.getQty()+charge_corrier;
						ship_charge+=ship_charge+charge_corrier;
						// do ----------something with key and/or tab----------
					}
				}
				if(charge_free<=subtotal)
					ship_charge=0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("242  "+e.toString());
		}
	}
	
	private void getPropertyChargeCourier(){
		HashMap<String, String> hm = new HashMap<>();
		hm.put("charge.corrier", "");
		hm.put("charge.free", "");
		JSONObject json = new JSONObject();
		for (String key : PropertiesFile.readAttribute(hm).keySet()) {
			if (key.equals("charge.corrier"))
				charge_corrier=Double.parseDouble(hm.get(key));
			else if (key.equals("charge.free"))
				charge_free=Double.parseDouble(hm.get(key));
		}
	}

	// public static boolean savePkeySession(HttpServletRequest request) {
	// SessionUser su = new SessionUser();
	// HashMap<String, CartModel> hmap = null;
	// boolean bol = false;
	// String key[] = request.getParameter("key").split("-");
	// String pkey = key[key.length - 1];
	// if (su.getSession(request, "pkey")) {
	// // ArrayList<String> arr = (ArrayList<String>)
	// // request.getSession().getAttribute("pkey");
	// hmap = (HashMap<String, CartModel>)
	// request.getSession().getAttribute("pkey");
	// if (hmap.size() > 0) {
	// Set set = hmap.entrySet();
	// Iterator iterator = set.iterator();
	// while (iterator.hasNext()) {
	// Map.Entry me = (Map.Entry) iterator.next();
	// if (!me.getKey().equals(key)) {
	// CartModel cm = (CartModel) me.getValue();
	// hmap.put(pkey, cm);
	// bol = true;
	// }
	// }
	// }
	// } else {
	// hmap = new HashMap<>();
	// hmap.put(pkey, null);
	// bol = true;
	// }
	// su.setSessionHM(request, "pkey", hmap);
	// Set set = hmap.entrySet();
	// Iterator iterator = set.iterator();
	// while (iterator.hasNext()) {
	// Map.Entry me = (Map.Entry) iterator.next();
	// System.out.println("key value:- " + me.getKey() + " " + me.getValue());
	// }
	//
	// return bol;
	// }

	// public static List<CartModel> listCartModel(HttpServletRequest request) {
	// HashMap<String, CartModel> hmap = null;
	// List<CartModel> lcm = null;
	// SessionUser su = new SessionUser();
	// if (su.getSession(request, "pkey")) {
	// lcm = new ArrayList<>();
	// hmap = ProductLogic.getProductInfoList(request);
	// for (Map.Entry<String, CartModel> entry : hmap.entrySet()) {
	// CartModel cm = entry.getValue();
	// System.out.println("key:- " + entry.getKey() + " value:- " +
	// entry.getValue());
	// String pkey_ppid = cm.getPkey() + cm.getQty();
	// if (pkey_ppid.length() > 11)
	// cm = entry.getValue();
	// else
	// cm = ProductLogic.getProductInfo(entry.getKey());
	//
	// lcm.add(cm);
	// hmap.put(entry.getKey(), cm);
	//
	// }
	// su.setSessionHM(request, "pkey", hmap);
	// // hmap = (HashMap<String, CartModel>)
	// // request.getSession().getAttribute("pkey");
	// // for (Map.Entry<String, CartModel> entry : hmap.entrySet()) {
	// // CartModel cm = entry.getValue();
	// // System.out.println("qty " + cm.getQty());
	// //
	// // }
	//
	// }
	// return lcm;
	// }

	public static boolean updateCart(HttpServletRequest request, String pkey, int qty) {
		SessionUser su = new SessionUser();
		boolean bol = false;
		try {
			if (su.getSession(request, "pkey")) {
				HashMap<String, CartModel> hmap = (HashMap<String, CartModel>) request.getSession()
						.getAttribute("pkey");
				if (hmap.size() > 0) {
					for (Map.Entry<String, CartModel> entry : hmap.entrySet()) {
						if (entry.getKey().equals(pkey)) {
							CartModel cm = new CartModel();
							cm = entry.getValue();
							cm.setQty(qty);
							hmap.put(pkey, cm);
							su.setSessionListObject(request, "pkey", hmap);
							bol = true;
							break;
						} else
							System.out.println(entry.getKey() + " else " + pkey);
					}

				}
			}
		} catch (Exception e) {
			logger.error("CartSession error(263)updateCart  " + e.toString());
		}
		return bol;
	}

	public static void saveItemDetails(HttpServletRequest request, int uid, String orderId) {
		// for (CartModel cm : CartLogic.getCartProduct(request)) {
		// cm = new CartModel(uid, orderId, cm);
		// Session session =
		// com.model.HibernateUtil.getSessionFactory().openSession();
		// Transaction ts = null;
		// Integer itemID = 0;
		// try {
		// ts = session.beginTransaction();
		// itemID = (Integer) session.save(cm);
		// ts.commit();
		// } catch (Exception e) {
		// if (ts != null)
		// ts.rollback();
		// logger.error("CartSession error(281)saveItemDetails " +
		// e.toString());
		// } finally {
		// session.close();
		// }
		// }
	}

	/*-- serparte key or ppid--*/
	private static String[] getPkeyOrPPID(String pkey) {
		String first = pkey.substring(0, 10); // gives "How ar"
		String second = pkey.substring(10, pkey.length()); // gives "e you?"
		return new String[] { first, second };
	}

	public double getGrand_total() {
		return grand_total;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public  double getShip_charge() {
		return ship_charge;
	}
}