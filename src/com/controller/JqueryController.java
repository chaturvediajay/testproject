package com.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cart.OrderInit;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.json.JqueryDataTable;
import com.logic.DTProductList;
import com.logic.ProductLogic;
import com.logic.RegLogic;
import com.model.LoginSession;
import com.model.Product;
import com.model.ShippingModel;
import com.scope.CartSession;

@Controller
public class JqueryController {
	final static Logger logger = Logger.getLogger(JqueryController.class);

	@RequestMapping(value = "/products_size", method = RequestMethod.GET)
	public void getColorAvail(@RequestParam("json") String json, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		JSONObject obj = new JSONObject(json);
		String cate = obj.getString("cate").trim();
		System.out.println(" Catagory== " + cate);
		try {
			if (cate.equals("size")) {
				String pkey = obj.getString("pkey").trim();
				String value = obj.getString("value").trim();
				obj.put("data", new Gson().toJson(ProductLogic.getColorList(pkey, value)));
			} else if (cate.equals("color")) {
				String pkey = obj.getString("pkey").trim();
				String value = obj.getString("value").trim();
				obj.put("data", ProductLogic.getProductCount(pkey, value, cate, obj));
			} else if (cate.equals("locationId")) {
				int status = obj.getInt("status");
				String pin = obj.getString("pin").trim();
				// obj.put("data", ProductLogic.getLocation(status, pin, obj));
			}
			obj.put("url", "no");
			obj.put("res", true);
		} catch (Exception e) {
			obj.put("data", "exception:- " + e.toString());
			obj.put("url", "no");
			obj.put("res", true);
			obj.put("valid", "default");

			logger.error("JqueryController error(48)getColorAvail  " + e.toString());
		} finally {
			obj.remove("pkey");
			obj.remove("value");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(obj.toString());
		}
	}

	@RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
	public void getOrderList(HttpServletRequest request, HttpServletResponse response, Model model) {
		LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
		List<Product> list = null;
		// ProductList pl=new ProductList();
		DTProductList dt = new DTProductList();
		try {
			JqueryDataTable jt = new JqueryDataTable();
			/* request set */
			jt.getRequestData(request);
			String[] keyword = new String[4];
			keyword[0] = String.valueOf(jt.getDraw());/*--indec column--*/
			keyword[1] = jt.getOrder_column();/*--indec column--*/
			keyword[2] = jt.getOrder_dir();/*--asc/desc--*/
			keyword[3] = jt.getSearch_value();/*--keyword search--*/

			System.out.println("getProductList**********" + ls.getAuthorize());
			list = dt.OrderListGet(request, jt.getStart(), jt.getLength());

			if (list == null)
				list = Collections.EMPTY_LIST;
			System.out.println("list size:-  " + list.size());

			/* list convert to datatable json format */
			jt.getResposeJson(response, dt.getCount(), new Gson().toJson(list).toString());
			System.out.println("json format:-  " + new Gson().toJson(list).toString());

		} catch (Exception e) {
			logger.error("JQueryDataTable error(53)TestPagePost  " + e.toString());
		}
	}

	@RequestMapping(value = "/updateShipp", method = RequestMethod.GET)
	public void updateRegistration(@RequestParam("json") String json, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		ShippingModel sm = null;
		JSONObject obj = new JSONObject(json);
		LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
		String cas = obj.getString("cas").trim();
		System.out.println(" cas2= " + cas);
		try {
			if (ls != null) {
				switch (cas) {
				case "updateReg":
					System.out.println("upadet ship....");
					obj.put("data", new Gson().toJson(RegLogic.setRegAddress(obj, ls)));
					obj.put("url", "no");
					obj.put("res", true);
					break;
				case "regShip":
					// System.out.println(" cas3= "+cas);
					// sm = new ShippingModel(obj,ls,2);
					// boolean bol = sm.jsonObject(request, sm);
					// System.out.println(" bol= "+bol+" cas="+cas);
					// obj.put("url",cas);
					// obj.put("res", bol);
					break;
				case "updateImage":
					// obj.put("data", new
					// Gson().toJson(RegistrationLogic.setImage(ls,
					// obj.getString("url").trim(),request)));
					obj.put("url", "no");
					obj.put("res", true);
					break;
				}
			}
		} catch (Exception e) {
			obj.put("data", "exception:- " + e.toString());
			obj.put("url", "no");
			obj.put("res", true);
			obj.put("valid", "default");
		} finally {
			obj.remove("cas");
			System.out.println("obj json " + obj.toString());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(obj.toString());
		}

	}

	@RequestMapping(value = "/cartAccess1", method = RequestMethod.GET)
	public void saveNewUsers(@RequestParam("json") String json, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		JSONObject obj = new JSONObject(json);
		String keyid = obj.getString("keyid").trim();
		String cate = obj.getString("category").trim();
		String metod = obj.getString("metod").trim();
		Gson gson = new Gson();
		JsonElement jsonElement = null;
		try {
			// CartSession cs;
			obj.remove("keyid");
			obj.remove("category");
			obj.remove("metod");
			switch (cate) {
			case "retrive":
				if (metod.equals("jq")) {
					jsonElement = gson.toJsonTree(CartSession.getCartSession(request));
					obj.put("data", jsonElement);
					
				}
				obj.put("url", "no");
				obj.put("res", true);
				break;
			case "add":
				CartSession cs = new CartSession();
				String keyid11 = keyid;
				// cs = new CartSession(keyid11, "", "test1", "test2", "test3");
				// cs.addValue(request);
				// String str = cs.retriveCart1(request);
				// obj.put("data", str);
				obj.put("url", "no");
				obj.put("res", true);
				break;
			case "retriveFinal":
				String cas = obj.getString("cas").trim();
				if (metod.equals("jq"))
					obj.put("data", new Gson().toJson(CartSession.getCartSession(request)));
				obj.put("url", cas);
				obj.put("res", true);
				break;
			case "update-cart":
				cs = new CartSession();
				int qty = obj.getInt("qty");
				if (metod.equals("html"))
					obj.put("data", cs.updateCart(request, keyid, qty));
				obj.put("url", "no");
				obj.put("res", true);
				obj.put("valid", metod);
				break;
			case "remove-cart":
				cs = new CartSession();
				if (metod.equals("html"))
					obj.put("data", cs.removeValue(keyid, request));
				else if (metod.equals("jq"))
					obj.put("data", cs.removeValueJq(keyid, request));
				else if (metod.equals("final"))
					obj.put("data", cs.removeValueJq(keyid, request));
				obj.put("url", "no");
				obj.put("res", true);
				obj.put("valid", "html");
				break;
			}
			obj.put("grand_total", OrderInit.totalOrderAmt(request));
		} catch (Exception e) {
			obj.put("data", "0");
			obj.put("url", "no");
			obj.put("res", true);
			obj.put("valid", "default");
			System.out.println(e.toString());
		} finally {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(obj.toString());
			System.out.println(obj.toString());
		}

	}

}
