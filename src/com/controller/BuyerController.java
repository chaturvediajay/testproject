package com.controller;


import com.google.gson.Gson;
import com.logic.BuyerDetails;
import com.logic.CartLogic;
import com.logic.MenuLogic;
import com.logic.ProductLogic;
import com.logic.RegLogic;
import com.model.CartModel;
import com.model.LoginSession;
import com.model.Menu1;
import com.model.Menu2;
import com.model.Menu3;
import com.model.OrderDetails;
import com.model.Registration;
import com.model.ShippingModel;
import com.scope.AbstractClass;
import com.scope.ReadFile;
import com.scope.SessionUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = { "/buyer" })
public class BuyerController {
	final static Logger logger = Logger.getLogger(AdminController.class);
	@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
	public String indexGet(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:/";
			else
				map.addAttribute("featuredPro", " Get Controller");

		} catch (Exception e) {
			logger.error("BuyerController error(35)indexGet  " + e.toString());
		}
		return "buyer/index";
	}
	@RequestMapping(value = { "/account" }, method = { RequestMethod.GET })
	public String getAccount(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:/";
			else{
				map.addAttribute("regInfom", RegLogic.getProductUserDetail(request));
				map.addAttribute("mistate", ReadFile.getState(request));
			}
		} catch (Exception e) {
			logger.error("BuyerController error(35)indexGet  " + e.toString());
		}
		return "buyer/account";
	}
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public void postAccountUpdate(@RequestParam("json") String json, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		JSONObject obj = new JSONObject(json);
		try {System.out.println("City  get**");
//			if (RegLogic.verifyPageWithLogin(request)) {
				LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
				 System.out.println("obj json " );
				// System.out.println("obj json " + obj.toString());
				String con = obj.getString("condition").trim();
				Registration reg = new Registration();
				Session session = null;
				switch (con) {
				case "email-update":
					String email = obj.getString("email").trim();
					reg = (Registration) AbstractClass.getClassByIdWithoutSession(ls.getId(), reg, session, 0,
							"classById");
					if (reg != null) {
						if (!reg.getEmail().equals(email)) {
							reg.setEmail(email);
							reg = (Registration) AbstractClass.getClassByIdWithoutSession(reg.getId(), reg, session, 1,
									"updateClass");
							obj.put("data", "Email change sucessfully");
							obj.put("status", true);
						} else {
							obj.put("data", "This Email id already exits");
							obj.put("status", false);
						}
					}
					break;
				case "getCity":
					if (ls != null & ls.getId() > 0) {
						System.out.println("City  get**");
				obj.put("data", new Gson().toJson((Object) ReadFile.getCityPin(request, "city.json", obj.getString("id"),"",con)));
				obj.put("status", true);
					}
					break;
				case "getPin":
					if (ls != null & ls.getId() > 0) {
						System.out.println(" state=="+obj.getString("state")+"   city=="+obj.getString("id")+"   select=="+obj.getString("select"));
						obj.put("data", new Gson().toJson((Object) ReadFile.getCityPin(request, "city.json", obj.getString("id"),obj.getString("state"),con)));
						obj.put("status", true);
					}
					break;
				}
				assert (reg != null);

				obj.remove("email");
				obj.remove("condition");
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			obj.put("url", "no");
			obj.put("res", true);
			obj.put("status", false);
		} finally {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(obj.toString());
		}

	}
	
	@RequestMapping(value = { "/changePassword" }, method = { RequestMethod.GET })
	public String getChangePwd(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:/";
			else
				return "buyer/changePassword";

		} catch (Exception e) {
			logger.error("AdminController error(54)getForms  " + e.toString());
		}
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public void postChangePassword(@RequestParam("json") String json, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
		JSONObject obj = new JSONObject(json);
		try {
			if (ls.getId() > 0 | request.getSession().getAttribute("rand") != null) {
				obj.put("data", BuyerDetails.setNewPassword(obj, ls));
				obj.put("res", true);
				obj.put("url", "no");
			} else {
				obj.put("data", "0");
				obj.put("res", true);
				obj.put("url", "no");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			obj.put("data", "getProductKey exception:- " + e.toString());
			obj.put("url", "no");
			obj.put("res", true);
		} finally {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(obj.toString());
		}

	}
	


	@RequestMapping(value = { "/orderList" }, method = { RequestMethod.GET })
	public String getOrderList(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:/";
			else
				return  "buyer/orderList";

		} catch (Exception e) {
			logger.error("AdminController error(54)getForms  " + e.toString());
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = { "/orderDes" }, method = { RequestMethod.GET })
	public String getOrderDes(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:/";

		} catch (Exception e) {
			logger.error("BuyerController error(35)indexGet  " + e.toString());
		}
		return "buyer/orderDes";
	}
	
	@RequestMapping(value = { "/orderInfo" }, method = { RequestMethod.GET })
	public String getOrderInfo(HttpServletRequest request, ModelMap map) {
		LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
		String orderId = request.getParameter("ordNo");
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:/";
			else{
				OrderDetails od = (OrderDetails) CartLogic.getOrderInfo(ls, orderId, 1);
//				map.addAttribute("orderDetail", od);
				map.addAttribute("orderDetail", CartLogic.getOrderDetailWithPaymentGatewayResponse(orderId));
				map.addAttribute("itemDetail", (List<CartModel>) CartLogic.listCartModelByOrderNo(orderId,ls));
				map.addAttribute("regInfo", RegLogic.getProductUserDetail(request));
				map.addAttribute("shippingAdd", (ShippingModel) CartLogic.getOrderInfo(ls, orderId, 2));
				
				try {
					JSONObject jsonObject=new JSONObject(od.getOther());
					map.addAttribute("courier", jsonObject.getInt("charge.corrier"));
					map.addAttribute("free", jsonObject.getInt("charge.free"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		} catch (Exception e) {
			logger.error("BuyerController error(35)indexGet  " + e.toString());
		}
		return "buyer/orderInfo";
	}

	

}
