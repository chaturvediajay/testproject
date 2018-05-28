
package com.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cart.TrasactionModel;
import com.ccavenue.ccavenuePayment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.logic.CartLogic;
import com.logic.MenuLogic;
import com.logic.PaymentLogic;
import com.logic.ProductLogic;
import com.logic.RegLogic;
import com.model.CartModel;
import com.model.LoginSession;
import com.model.OrderDetails;
import com.model.Registration;
import com.model.ShippingModel;
import com.scope.CartSession;
import com.scope.SendEmail;
import com.scope.SessionUser;

import paypal.IntregationPayPal;

@Controller
public class HomeController {
	final static Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
	public String indexGet(HttpServletRequest request, ModelMap map) {
		try {

			// map.addAttribute("proList", ProductLogic.getProductHome("all",
			// ""));
			// map.addAttribute("getProp", getLoggerPath().getAbsolutePath());

		} catch (Exception e) {
			logger.error("HomeController error(42)indexGet  " + e.toString());
		}
		return "index";
	}

	@RequestMapping(value = { "/up" }, method = { RequestMethod.GET })
	public String upxGet(HttpServletRequest request, ModelMap map) {
		try {

			MenuLogic m1l = new MenuLogic();
			m1l.getAllMwnu(m1l);
			map.addAttribute("menus1", m1l.getLmenu1());
			map.addAttribute("menus2", m1l.getLmenu2());
			// map.addAttribute("menus3", m1l.getLmenu3());
			map.addAttribute("proList", ProductLogic.getProductHome("all", "", 1, 10));
			map.addAttribute("slide_top", ProductLogic.getProductHome("slide_top_all", "", 0, 0));
			map.addAttribute("getProp", getLoggerPath().getAbsolutePath());

		} catch (Exception e) {
			logger.error("HomeController error(42)indexGet  " + e.toString());
		}
		return "index1";
	}

	private File getLoggerPath() {

		Logger logger = Logger.getLogger(HomeController.class); // Defining the
																// Logger
		FileAppender appender = (FileAppender) logger.getAppender("anything");
		return new File(appender.getFile());
	}

	@RequestMapping(value = { "/" }, method = { RequestMethod.POST })
	public String indexPOST(HttpServletRequest request, ModelMap model) {
		model.addAttribute("msg", "ajay chaturvedi");
		return "test";
	}

	@RequestMapping(value = { "/products" }, method = { RequestMethod.GET })
	public String getProduct(HttpServletRequest request, ModelMap map) {
		try {
			MenuLogic m1l = new MenuLogic();
			m1l.getAllMwnu(m1l);
			map.addAttribute("menus1", m1l.getLmenu1());
			map.addAttribute("menus2", m1l.getLmenu2());

		} catch (Exception e) {
			logger.error("HomeController error(58) getProduct  " + e.toString());
		}
		return "products";
	}

	@RequestMapping(value = { "/single" }, method = { RequestMethod.GET })
	public String getSingle(HttpServletRequest request, ModelMap map) {
		try {
			MenuLogic m1l = new MenuLogic();
			m1l.getAllMwnu(m1l);
			map.addAttribute("menus1", m1l.getLmenu1());
			map.addAttribute("menus2", m1l.getLmenu2());
			String pkey = request.getParameter("pkey");
			System.out.println("pkey:-  " + pkey);
			if (pkey != null)
				map.addAttribute("product_desc", ProductLogic.getProductHome("pkey", pkey, 0, 0));
			else
				return "index";
		} catch (Exception e) {
			logger.error("HomeController error(73) getSingle  " + e.toString());
		}
		return "single";
	}

	@RequestMapping(value = { "/cart" }, method = { RequestMethod.GET })
	public String getCheckout(HttpServletRequest request, ModelMap map) {
		try {
			MenuLogic m1l = new MenuLogic();
			m1l.getAllMwnu(m1l);
			map.addAttribute("menus1", m1l.getLmenu1());
			map.addAttribute("menus2", m1l.getLmenu2());

			map.addAttribute("retrive", CartSession.getCartSession(request));
		} catch (Exception e) {
			logger.error("HomeController error(83) getCheckout  " + e.toString());
		}
		return "cart";
	}

	@RequestMapping(value = { "/cart" }, method = { RequestMethod.POST })
	public void getCartAvail(@RequestParam("json") String json, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		JSONObject obj = new JSONObject(json);
		String pkey = obj.getString("pkey").trim();
		String value = obj.getString("value").trim();
		String num = obj.getString("num").trim();
		String cate = obj.getString("cate").trim();
		System.out.println("pkey= " + cate + "  pkey  " + pkey);
		try {
			CartModel cm = null;
			if (cate.equals("qtyId"))
				// obj.put("data", new
				// Gson().toJson(ProductLogic.getColorList(pkey, size)));
				;
			else if (cate.equals("cart_update")) {
				CartSession cs = new CartSession();
				if (cs.updateCart(request, pkey, Integer.parseInt(value))) {
					cm = cs.getCartSessionPkeyValue(request, pkey);
					String str = "{\"pkey\":\"" + pkey + "\",\"smrp\":\"" + cm.getSmrp() +

							"\",\"total\":\"" + cs.getSubtotal() + "\",\"ship\":\"" + cs.getShip_charge()
							+ "\",\"num\":\"" + num + "\",\"ship\":\"" + cs.getShip_charge() +

							"\",\"mrp\":\"" + cm.getMrp() + "\",\"qty\":\"" + cm.getQty() + "\"}";
					obj.put("data", str);
				}
			}
			obj.put("url", "no");
			obj.put("res", true);
		} catch (Exception e) {
			obj.put("data", "exception:- " + e.toString());
			obj.put("url", "no");
			obj.put("res", true);
			obj.put("valid", "default");
		} finally {
			obj.remove("pkey");
			obj.remove("value");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(obj.toString());
		}
	}

	@RequestMapping(value = { "/contactUs" }, method = { RequestMethod.GET })
	public String getContactUs(HttpServletRequest request, ModelMap map) {
		try {
			MenuLogic m1l = new MenuLogic();
			m1l.getAllMwnu(m1l);
			map.addAttribute("menus1", m1l.getLmenu1());
			map.addAttribute("menus2", m1l.getLmenu2());

		} catch (Exception e) {
			logger.error("HomeController error(88) getContactUs  " + e.toString());
		}
		return "contactUs";
	}

	@RequestMapping(value = { "/contactUs" }, method = { RequestMethod.POST })
	public String postContactUs(HttpServletRequest request, ModelMap map) {
		try {
			String name = request.getParameter("name");
			String username = request.getParameter("username");
			String userMsg = request.getParameter("userMsg");

			System.out.println("post contactUs name=" + name + "   userName=" + username + "  msg=" + userMsg);
			String str = "Dear sir i am " + name + " My email Id:-" + username + " meassage:- " + userMsg + "";
			boolean bol = SendEmail.send("Query ", str, "kr.maheshsingh@gmail.com");

			if (bol) {
				map.addAttribute("error", "Submit Successfully.");
			} else {
				map.addAttribute("error", "request not completed.");
			}
			return "contactUs";

		} catch (Exception e) {
			logger.error("HomeController error(213) contactUs  " + e.toString());
		}
		return "contactUs";
	}

	@RequestMapping(value = { "/shipping" }, method = { RequestMethod.GET })
	public String getShipping(HttpServletRequest request, ModelMap map) {

		MenuLogic m1l = new MenuLogic();
		m1l.getAllMwnu(m1l);
		map.addAttribute("menus1", m1l.getLmenu1());
		map.addAttribute("menus2", m1l.getLmenu2());

		http: // localhost:8080/kritifab/login?shop=payment

		try {
			if (RegLogic.checkLogin(request))
				return "redirect:login?shop=payment";

		} catch (Exception e) {
			logger.error("HomeController error(98) getShipping  " + e.toString());
		}
		return "shipping";
	}

	@RequestMapping(value = { "/shipping" }, method = { RequestMethod.POST })
	public String postShipping(HttpServletRequest request, ModelMap map) {
		try {
			MenuLogic m1l = new MenuLogic();
			m1l.getAllMwnu(m1l);
			map.addAttribute("menus1", m1l.getLmenu1());
			map.addAttribute("menus2", m1l.getLmenu2());
			ShippingModel sm = new ShippingModel(request);
			if (sm.checkValid().size() == 0) {
				sm.setSm(sm);
				sm.sessionShipSave(request, true);
			} else {
				map.addAttribute("error", sm.checkValid());
				return "shipping";
			}
		} catch (Exception e) {
			logger.error("HomeController error(122) postShipping  " + e.toString());
		}
		return "redirect:payment";
	}

	@RequestMapping(value = { "/aboutUs" }, method = { RequestMethod.GET })
	public String getAboutUs(HttpServletRequest request, ModelMap map) {
		try {
			MenuLogic m1l = new MenuLogic();
			m1l.getAllMwnu(m1l);
			map.addAttribute("menus1", m1l.getLmenu1());
			map.addAttribute("menus2", m1l.getLmenu2());

		} catch (Exception e) {
			logger.error("HomeController error(132) getAboutUs  " + e.toString());
		}
		return "aboutUs";
	}

	@RequestMapping(value = { "/gallery" }, method = { RequestMethod.GET })
	public String getGallery(HttpServletRequest request, ModelMap map) {
		try {
			MenuLogic m1l = new MenuLogic();
			m1l.getAllMwnu(m1l);
			map.addAttribute("menus1", m1l.getLmenu1());
			map.addAttribute("menus2", m1l.getLmenu2());

		} catch (Exception e) {
			logger.error("HomeController error(142) getGallery  " + e.toString());
		}
		return "gallery";
	}

	@RequestMapping(value = { "/terms" }, method = { RequestMethod.GET })
	public String getTerms(HttpServletRequest request, ModelMap map) {
		try {
			MenuLogic m1l = new MenuLogic();
			m1l.getAllMwnu(m1l);
			map.addAttribute("menus1", m1l.getLmenu1());
			map.addAttribute("menus2", m1l.getLmenu2());

		} catch (Exception e) {
			logger.error("HomeController error(152) getTerms  " + e.toString());
		}
		return "terms";
	}

	@RequestMapping(value = { "/testimonial" }, method = { RequestMethod.GET })
	public String getTestimonial(HttpServletRequest request, ModelMap map) {
		try {
			MenuLogic m1l = new MenuLogic();
			m1l.getAllMwnu(m1l);
			map.addAttribute("menus1", m1l.getLmenu1());
			map.addAttribute("menus2", m1l.getLmenu2());

		} catch (Exception e) {
			logger.error("HomeController error(162) getTestimonial  " + e.toString());
		}
		return "testimonial";
	}

	@RequestMapping(value = { "/joinUs" }, method = { RequestMethod.GET })
	public String getJoinUs(HttpServletRequest request, ModelMap map) {
		try {
			MenuLogic m1l = new MenuLogic();
			m1l.getAllMwnu(m1l);
			map.addAttribute("menus1", m1l.getLmenu1());
			map.addAttribute("menus2", m1l.getLmenu2());

		} catch (Exception e) {
			logger.error("HomeController error(172) getJoinUs  " + e.toString());
		}
		return "joinUs";
	}

	@RequestMapping(value = { "/signUp" }, method = { RequestMethod.GET })
	public String getSignUp(HttpServletRequest request, ModelMap map) {
		try {
			MenuLogic m1l = new MenuLogic();
			m1l.getAllMwnu(m1l);
			map.addAttribute("menus1", m1l.getLmenu1());
			map.addAttribute("menus2", m1l.getLmenu2());
			if (RegLogic.checkLogin(request))
				return "signUp";
		} catch (Exception e) {
			logger.error("HomeController error(184) getSignUp error(184)  " + e.toString());
		}
		return "signUp";
	}

	@RequestMapping(value = { "/signUp" }, method = { RequestMethod.POST })
	public String postSignUp(HttpServletRequest request, ModelMap map) {
		Registration reg = new Registration();
		MenuLogic m1l = new MenuLogic();
		m1l.getAllMwnu(m1l);
		map.addAttribute("menus1", m1l.getLmenu1());
		map.addAttribute("menus2", m1l.getLmenu2());
		try {
			if (reg.checkValid(request).size() > 0)
				map.addAttribute("error", reg.checkValid(request));

			else {
				if (RegLogic.regNewUser(reg, this.getLocalHost(request)))
					map.addAttribute("error", "account create sucessfully.");
				else
					map.addAttribute("error", "try later.");
			}
		} catch (Exception e) {
			logger.error("HomeController error(203) getSignUp  " + e.toString());
		}
		return "signUp";
	}

	@RequestMapping(value = { "/login" }, method = { RequestMethod.GET })
	public String getLogin(HttpServletRequest request, ModelMap map) {

		try {
			if (RegLogic.checkLogin(request)) {
				MenuLogic m1l = new MenuLogic();
				m1l.getAllMwnu(m1l);
				map.addAttribute("menus1", m1l.getLmenu1());
				map.addAttribute("menus2", m1l.getLmenu2());
				return "login";
			}

			else {
				// "redirect:login?shop=payment";
				String query = request.getParameter("shop");
				if (query != null & query.equals("payment"))
					return "redirect:shipping";
			}
		} catch (Exception e) {
			logger.error("HomeController--getLogin(214) error(214)  " + e.toString());
		}
		return "redirect:buyer/";
	}

	@RequestMapping(value = { "/login" }, method = { RequestMethod.POST })
	public String postLogin(HttpServletRequest request, ModelMap map) {
		try {
			System.out.println("post login");
			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
			if (ls == null) {
				if (RegLogic.loginUser(request)) {
					ls = (LoginSession) request.getSession().getAttribute("loginSession");

					if (request.getParameter("shop") != null) {
						String query = request.getParameter("shop");
						if (query != null & query.equals("payment"))
							return "redirect:shipping";

					}

					else
						return "redirect:buyer/";
				} else
					map.addAttribute("error", "try later.");
			}

		} catch (Exception e) {
			logger.error("HomeController error(228) postLogin  " + e.toString());
		}
		return "login";
	}

	@RequestMapping(value = { "/adminLogin" }, method = { RequestMethod.GET })
	public String getAdminLogin(HttpServletRequest request, ModelMap map) {
		try {

		} catch (Exception e) {
			logger.error("HomeController error(13) getAdminLogin  " + e.toString());
		}
		return "adminLogin";
	}

	@RequestMapping(value = { "/adminLogin" }, method = { RequestMethod.POST })
	public String postAdminLogin(HttpServletRequest request, ModelMap map) {
		try {
			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
			System.out.println(ls);
			if (ls == null) {
				if (RegLogic.loginUser(request)) {
					ls = (LoginSession) request.getSession().getAttribute("loginSession");
					if (ls.getAuthorize() == 1)
						return "redirect:../admin/";
				} else
					map.addAttribute("error", "Invalid Entry.");
			}

		} catch (Exception e) {
			logger.error("HomeController error(228) postLogin  " + e.toString());
		}
		return "adminLogin";
	}

	@RequestMapping(value = { "/logout" }, method = { RequestMethod.GET })
	public String getlogout(HttpServletRequest request) {
		request.getSession().removeAttribute("loginSession");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}

	@RequestMapping(value = { "/payment" }, method = { RequestMethod.GET })
	public String getpayment(HttpServletRequest request, ModelMap map) {
		if (RegLogic.checkLogin(request))
			return "redirect:login";
		else {
			MenuLogic m1l = new MenuLogic();
			m1l.getAllMwnu(m1l);
			map.addAttribute("menus1", m1l.getLmenu1());
			map.addAttribute("menus2", m1l.getLmenu2());
			map.addAttribute("retrive", CartSession.getCartSession(request));
			return "payment";
		}

	}

	@RequestMapping(value = { "/reciept" }, method = { RequestMethod.GET })
	public String getReceipt(HttpServletRequest request, ModelMap model) {
		try {

			IntregationPayPal ip = new IntregationPayPal();

			ip.successPage(request);

			SessionUser su = new SessionUser();
			if (su.getSession(request, "order_no") & su.getSession(request, "loginSession")) {
				LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
				String order_no = (String) request.getSession().getAttribute("order_no");
				model.addAttribute("order_no", order_no);
				model.addAttribute("billingAdd", (Registration) RegLogic.getProductUserDetail(request));
				OrderDetails od = (OrderDetails) CartLogic.getOrderInfo(ls, order_no, 1);
				model.addAttribute("orderDetail", od);
				model.addAttribute("other", CartLogic.getPayDetail(od.getOther(), od.getTotal()));
				model.addAttribute("shippingAdd", (ShippingModel) CartLogic.getOrderInfo(ls, order_no, 2));
				model.addAttribute("itemDetail", (List<CartModel>) CartLogic.listCartModelByOrderNo(order_no, ls));

				request.getSession().removeAttribute("order_no");
			} else
				model.addAttribute("order_no", "No order found");

		} catch (Exception ex) {
			logger.error("HomeController error(283) getReceipt  " + ex.toString());
			model.addAttribute("msg", "error:- " + ex.toString());
		}
		return "reciept";
	}

	@RequestMapping(value = { "/reciept" }, method = { RequestMethod.POST })
	public String postReceipt(HttpServletRequest request, ModelMap map) {
		try {
			Enumeration<String> kayParams = request.getParameterNames();
			String result = "";
			String key = "";
			if (request.getParameter("encResp") != null | request.getParameter("encResp").length() > 0)
				ccavenuePayment.getResponse(request);
			else
				System.out.println(" /receipt  " + TrasactionModel.saveData(request));

			String serverName = request.getServerName();
			int portNumber = request.getServerPort();
			String contextPath = request.getContextPath();

			System.out.println(" ^^^^^^^  " + serverName + ":" + portNumber + contextPath);
		} catch (Exception e) {
			logger.error("HomeController error(306) getReceipt  " + e.toString());

			map.addAttribute("msg", "HomeController error(306) getReceipt  " + e.toString());
		}
		return "redirect:reciept";
	}

	@RequestMapping(value = { "/cancel" }, method = { RequestMethod.POST })
	public String postCancel(HttpServletRequest request, Model model) {
		try {
			Enumeration<String> kayParams = request.getParameterNames();
			while (kayParams.hasMoreElements()) {
				String key = (String) kayParams.nextElement();
				// String result = key + "=" + request.getParameter(key) +
				// (kayParams.hasMoreElements() ? "," : "") + "\n";
				String val = request.getParameter(key);
				if (key.equals("tracking_id") & val.length() > 0) {
					JsonObject jobj = TrasactionModel.saveData(request);
					if (jobj != null) {
						jobj = ccavenuePayment.getResponse(request);
						model.addAttribute("firstname", jobj.get("firstname"));
						model.addAttribute("lastname", jobj.get("lastname"));
						model.addAttribute("time", jobj.get("trans_date"));
						model.addAttribute("error", jobj.get("order_status"));
						model.addAttribute("payid", jobj.get("tracking_id"));
						model.addAttribute("amount", jobj.get("mer_amount"));
						return "cancel";
					}
				} else if (key.equals("mihpayid") & val.length() > 0) {
					JsonObject jobj = TrasactionModel.saveData(request);
					if (jobj != null) {
						model.addAttribute("firstname", jobj.get("firstname"));
						model.addAttribute("lastname", jobj.get("lastname"));
						model.addAttribute("time", jobj.get("addedon"));
						model.addAttribute("error", jobj.get("error_Message"));
						model.addAttribute("payid", jobj.get("payuMoneyId"));
						model.addAttribute("amount", jobj.get("amount"));
						return "cancel";
					}
				}

			}
		} catch (Exception e) {
			logger.error("HomeController error(347) postCancel  " + e.toString());
		}
		return "redirect:/";
	}

	// @RequestMapping(value = { "/cancel" }, method = { RequestMethod.GET })
	// public String getCancel(@ModelAttribute("firstname") String firstname,
	// @ModelAttribute("lastname") String lastname,
	// @ModelAttribute("time") String time, @ModelAttribute("error") String
	// error,
	// @ModelAttribute("payid") String payid, @ModelAttribute("amount") String
	// amount, ModelMap model) {
	// try {
	// if (firstname.length() > 0 & lastname.length() > 0 & time.length() > 0 &
	// payid.length() > 0) {
	// model.addAttribute("firstname", firstname.replaceAll("\\+", ""));
	// model.addAttribute("lastname", lastname.replaceAll("\\+", ""));
	// model.addAttribute("time", time.replaceAll("\\+", ""));
	// model.addAttribute("error", error.replaceAll("\\+", ""));
	// model.addAttribute("payid", payid.replaceAll("\\+", ""));
	// model.addAttribute("amount", amount.replaceAll("\\+", ""));
	// return "cancel";
	// }
	//
	// } catch (Exception e) {
	// logger.error("HomeController error(368) getCancel " + e.toString());
	// }
	//
	// return "redirect:/";
	// }
	@RequestMapping(value = { "/cancel" }, method = { RequestMethod.GET })
	public String getCancel(HttpServletRequest request, ModelMap model) {
		if (request.getParameter("method").equals("paypal")) {
			String trasaction_code = request.getParameter("trasaction_code");
			String tokenId = request.getParameter("token");
			model.addAttribute("cancel_view", PaymentLogic.orderCancel(trasaction_code, tokenId, -1));
			// Enumeration<String> kayParams = request.getParameterNames();
			// while (kayParams.hasMoreElements()) {
			// String key = (String) kayParams.nextElement();
			// String value = request.getParameter(key).toString();
			//
			//
			// System.out.println(key+" --- "+value);
			// }
		}
		return "cancel";
	}

	@RequestMapping(value = { "/forgot_password_email" }, method = { RequestMethod.GET })
	public String getforgot_password(HttpServletRequest request, ModelMap model) {
		String ctx = request.getParameter("ctx");
		if (ctx != null) {
			if (ctx.equals("recover"))
				model.addAttribute("trace", 1);
			else if (ctx.equals("change") & request.getParameter("code") != null)
				model.addAttribute("trace", RegLogic.reg_rowCount("update_account_code",request.getParameter("code")));
			else
				model.addAttribute("trace", 0);
		} else
			model.addAttribute("trace", 0);

		return "forgot_password_email";
	}

	@RequestMapping(value = { "/forgot_password_email" }, method = { RequestMethod.POST })
	public String postforgot_password(HttpServletRequest request, ModelMap model) {
		model.addAttribute("msg", "password not change");
		String email = request.getParameter("email");
		String ctx = request.getParameter("ctx");
		if (ctx != null) {
			if (ctx.equals("recover")) {
				String token = request.getParameter("token");
				if (RegLogic.forgot_password_email(email, getLocalHost(request))
						& request.getSession().getAttribute("forgot_pswd").equals(token))
					model.addAttribute("msg", "check your email account.(" + email + ")");
				else
					model.addAttribute("msg", "incorrect request found.(" + email + ")");
			} else if (ctx.equals("change")) {
				model.addAttribute("msg", RegLogic.change_password(request) ? "password change sucessfully."
						: "incorrect request found, please write a mail.");
			}
		}
		return "forgot_password_email";
	}

	private String getLocalHost(HttpServletRequest request) {
		String server = request.getServerName();
		int port = request.getServerPort();
		if (port != 0) {
			return String.valueOf(server) + ":" + port + request.getContextPath();
		}
		return String.valueOf(server) + request.getContextPath();
	}
}
