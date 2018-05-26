//package com.controller;
//
//import java.io.IOException;
//import com.json.JqueryDataTable;
//import java.util.Collections;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//import org.apache.log4j.Logger;
//import org.json.JSONObject;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.logic.Menu1logic;
//import com.logic.MerchantLogic;
//import com.logic.OrderLogic;
//import com.logic.RegistrationLogic;
//import com.model.LoginSession;
//import com.model.OrderDetails;
//import com.model.ProductInfoModel;
//import com.model.ProductPrice;
//import com.scope.CartSession;
//import com.scope.SessionUser;
//import com.util.VerifyPage;
//
//@Controller
//@RequestMapping(value = { "/" })
//public class OrderController {
//	private final SessionUser su = new SessionUser();
//	final static Logger logger = Logger.getLogger(OrderController.class);
//
//	@RequestMapping(method = { RequestMethod.GET }, value = { "orderDetail" })
//	public String getOrderDetail(@ModelAttribute(value = "pp") @Valid ProductPrice pp, ModelMap model,
//			HttpSession session, HttpServletRequest request) {
//		LoginSession ls = (LoginSession) session.getAttribute("loginSession");
//		System.out.println("Page Random No= " + request.getSession().getAttribute("rand"));
//		if (request.getSession().getAttribute("rand") != null) {
//			if (ls != null) {
//
//				return "master/orderDetail";
//			}
//			return "master/orderDetail";
//		}
//		return "master/orderDetail";
//	}
//
//	@RequestMapping(method = { RequestMethod.POST }, value = { "orderDetail" })
//	public void postOrderDetail(@RequestParam("json") String json, HttpServletResponse response, HttpSession session,
//			HttpServletRequest request) throws Exception {
//		LoginSession ls = (LoginSession) session.getAttribute("loginSession");
//		JSONObject obj = new JSONObject(json);
//		System.out.println("case 2");
//		try {
//			if (VerifyPage.verifyPageWithLogin(request)) {
//
//				String orderId = obj.getString("orderId").trim();
//				int opt = obj.getInt("opt");
//				String cas = obj.getString("cas").trim();
//
//				switch (cas) {
//				case "order_Info":
//					obj.put("data", OrderLogic.orderDescription(orderId, ls));
//					obj.put("status", true);
//					break;
//				case "item_confirm":
//					String gid = obj.getString("gid").trim();
//					obj.put("data", OrderLogic.updateCartModel(orderId, opt, gid, ls) ? "Action Confirmed..."
//							: "Action Failed...");
//					obj.put("status", true);
//					break;
//				case "order_update":
//					String actionCas = obj.getString("actionCas").trim();
//					String msg = obj.getString("msg").trim();
//					obj.put("data", OrderLogic.updateOrderDetails(orderId, opt, actionCas, msg, ls)
//							? "Action Confirmed..." : "Action Failed...");
//					obj.put("status", true);
//					break;
//				case "payment_update":
//					String actionCas2 = obj.getString("actionCas").trim();
//					String msg2 = obj.getString("msg").trim();
//					obj.put("data", OrderLogic.updatePayment(orderId, opt, actionCas2, msg2, ls) ? "Action Confirmed..."
//							: "Action Failed...");
//					obj.put("status", true);
//					break;
//				case "dispatch_update":
//					String gid2 = obj.getString("gid").trim();
//					String actionCas1 = obj.getString("actionCas").trim();
//					obj.put("data", OrderLogic.updateDispatchTable(orderId, opt, gid2, "dispatch_action", ls)
//							? "Action Confirmed..." : "Action Failed...");
//					obj.put("status", true);
//					break;
//				case "submit_ship":
//					int i = OrderLogic.insertOrderDispatch(obj);
//					if (i > 0) {
//						String gid1 = obj.getString("gid").trim();
//						OrderLogic.updateCartModel(orderId, 1, gid1, ls);
//						obj.put("data", "<div class=\"alert alert-success\">"
//								+ "<strong>Danger!</strong> successfully insert." + "</div>");
//					} else {
//						obj.put("data", "<div class=\"alert alert-info\">"
//								+ "<strong>Danger!</strong> please try again." + "</div>");
//					}
//					obj.put("status", true);
//					break;
//
//				default:
//					obj.put("status", false);
//				}
//			}
//		} catch (Exception ex) {
//			obj.put("data", "exception:- " + ex.toString());
//			obj.put("url", "no");
//			obj.put("res", true);
//			obj.put("status", true);
//		} finally {
//			obj.remove("orderId");
//			obj.remove("cas");
//			obj.remove("permit");
//			obj.remove("opt");
//			obj.remove("gid");
//
//			obj.remove("cName");
//			obj.remove("cAddress");
//			obj.remove("cContact");
//			obj.remove("cEmail");
//			obj.remove("trackId");
//			response.setContentType("application/json");
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().write(obj.toString());
//		}
//
//	}
//
//	@RequestMapping(value = { "/orderListByUid" }, method = { RequestMethod.GET })
//	public void getOrderByUid(@RequestParam("json") String json, HttpServletResponse response,
//			HttpServletRequest request) throws Exception {
//		JSONObject obj = new JSONObject(json);
//		String categories = obj.getString("categories").trim();
//		String permit = obj.getString("permit").trim();
//		try {
//			if (permit != null | permit != "" & categories != null | categories != "") {
//				switch (categories) {
//				case "order_byUid":
//					obj.put("data", OrderLogic.orderList(1, Integer.parseInt(permit), request));
//					break;
//				case "status_emp":
//					obj.put("data", OrderLogic.orderList(2, Integer.parseInt(permit), request));
//					break;
//				case "status_admin":
//					obj.put("data", OrderLogic.orderList(3, Integer.parseInt(permit), request));
//					break;
//				case "order_byUid_verify":
//					obj.put("data", OrderLogic.orderList(4, Integer.parseInt(permit), request));
//					break;
//				case "order_cancel_byUid":
//					obj.put("data", OrderLogic.orderList(5, Integer.parseInt(permit), request));
//					break;
//				case "order_cancel":
//					obj.put("data", OrderLogic.orderList(6, Integer.parseInt(permit), request));
//					break;
//				case "order_dispatch_byUid":
//					obj.put("data", OrderLogic.orderDispatchList(1, Integer.parseInt(permit), request));
//					break;
//				case "order_dispatch_emp":
//					obj.put("data", OrderLogic.orderDispatchList(2, Integer.parseInt(permit), request));
//					break;
//				case "order_dispatch_admin":
//					obj.put("data", OrderLogic.orderDispatchList(3, Integer.parseInt(permit), request));
//					break;
//
//				}
//			} else {
//				obj.put("data", "0");
//			}
//			obj.put("res", true);
//			obj.put("url", "no");
//		} catch (Exception ex) {
//			obj.put("data", " exception:- " + ex.toString());
//			obj.put("url", "no");
//			obj.put("res", true);
//		} finally {
//			response.setContentType("application/json");
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().write(obj.toString());
//			obj.remove("categories");
//			obj.remove("uid");
//		}
//
//	}
//
//	@RequestMapping(value = { "order_List_" }, method = { RequestMethod.GET })
//	public void TestPagePost(HttpServletRequest request, HttpServletResponse response, Model model) {
//		int p1 = 0;
//		int p2 = 0;
//		int confirm = 0;
//		List<OrderDetails> list = Collections.emptyList();
//		try {
//			Enumeration<String> kayParams = request.getParameterNames();
//			while (kayParams.hasMoreElements()) {
//				String key = (String) kayParams.nextElement();
//				String value = request.getParameter(key).toString();
//				System.out.println("key " + key + " value:- " + value);
//				if (key.equals("p1"))
//					p1 = Integer.parseInt(value);
//				else if (key.equals("p2"))
//					p2 = Integer.parseInt(value);
//				else if (key.equals("confirm"))
//					confirm = Integer.parseInt(value);
//			}
//			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
//			int start = Integer.parseInt(request.getParameter("start"));
//			int length = Integer.parseInt(request.getParameter("length").toString());
//			int draw = Integer.parseInt(request.getParameter("draw").toString());
//
//			String[] keyword = new String[4];
//			keyword[0] = request.getParameter("draw").toString();/*--draw--*/
//			keyword[1] = request.getParameter("order[0][column]").toString();/*--indec column--*/
//			keyword[2] = request.getParameter("order[0][dir]").toString();/*--asc/desc--*/
//			keyword[3] = request.getParameter("search[value]").toString();/*--keyword search--*/
//
//			HashMap<String, String> hm = new HashMap<>();
//
//			OrderLogic pl = new OrderLogic();
//			list = pl.order_List(p1, p2, confirm, request, start, length);
//			try {
//				Gson gson = new Gson();
//				JsonObject jsonResponse = new JsonObject();
//				jsonResponse.addProperty("draw", draw);
//				jsonResponse.addProperty("recordsTotal", pl.getCount());
//				jsonResponse.addProperty("recordsFiltered", pl.getCount());
//				jsonResponse.addProperty("data", gson.toJson(list).toString());
//				System.out.println("json object " + gson.toJson(list).toString());
//
//				response.setContentType("application/Json");
//				response.getWriter().print(jsonResponse.toString());
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				response.setContentType("text/html");
//				response.getWriter().print(e.getMessage());
//			}
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@RequestMapping(method = { RequestMethod.GET }, value = { "orderSummery" })
//	public String getorderSummery(ModelMap model, HttpSession session, HttpServletRequest request) {
//		LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
//		if (request.getSession().getAttribute("loginSession") != null) {
//			if (ls.getAuthorize() == 3 | ls.getAuthorize() == 1) {
//				return "master/orderSummery";
//			}
//			return "/";
//		}
//		return "/";
//	}
//
//	@RequestMapping(method = { RequestMethod.POST }, value = { "orderSummery" })
//	public void getorderSummery1TestPagePost(HttpServletRequest request, HttpServletResponse response, Model model) {
//		List<ProductInfoModel> list = null;
//		OrderLogic ol = new OrderLogic();
//		try {
//			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
//			JqueryDataTable jt = new JqueryDataTable();
//			/* request set */
//			jt.getRequestData(request);
//			String[] keyword = new String[4];
//			keyword[0] = String.valueOf(jt.getDraw());/*--indec column--*/
//			keyword[1] = jt.getOrder_column();/*--indec column--*/
//			keyword[2] = jt.getOrder_dir();/*--asc/desc--*/
//			keyword[3] = jt.getSearch_value();/*--keyword search--*/
//
//			list = OrderLogic.getOrderSummery("", 0, ls, jt.getStart(), jt.getLength());
//			if (list == null)
//				list = Collections.EMPTY_LIST;
//			/* list convert to datatable json format */
//			jt.getResposeJson(response, ol.getCount(), new Gson().toJson(list));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			logger.error("orderSummery post method:- " + e.toString());
//		}
//	}
//
//	@RequestMapping(method = { RequestMethod.GET }, value = { "vendorWiseOrder" })
//	public String getVendorWiseOrder(ModelMap model, HttpSession session, HttpServletRequest request) {
//		LoginSession ls = (LoginSession) session.getAttribute("loginSession");
//		// System.out.println("Page Random No= " +
//		// request.getSession().getAttribute("rand"));
//		// if (request.getSession().getAttribute("rand") != null)
//		if (ls.getAuthorize() == 3 | ls.getAuthorize() == 1 | ls.getOptUser() == 2) {
//			if (request.getParameter("uid") != null)
//				return "master/vendorWiseOrder";
//		}
//		return "/";
//	}
//
//	@RequestMapping(method = { RequestMethod.POST }, value = { "vendorWiseOrder" })
//	public void postVendorWiseOrder(HttpServletRequest request, HttpServletResponse response, Model model)
//			throws IOException {
//		List<ProductInfoModel> list = null;
//		OrderLogic ol = new OrderLogic();
//		try {
//			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
//			JqueryDataTable jt = new JqueryDataTable();
//			/* request set */
//			jt.getRequestData(request);
//			String[] keyword = new String[4];
//			keyword[0] = String.valueOf(jt.getDraw());/*--indec column--*/
//			keyword[1] = jt.getOrder_column();/*--indec column--*/
//			keyword[2] = jt.getOrder_dir();/*--asc/desc--*/
//			keyword[3] = jt.getSearch_value();/*--keyword search--*/
//			list = OrderLogic.getVendorWiseSummery(ls, request.getParameter("uid").toString(),
//					jt.getStart(), jt.getLength());
//			if (list == null)
//				list = Collections.EMPTY_LIST;
//			jt.getResposeJson(response, ol.getCount(), new Gson().toJson(list));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			logger.error("orderSummery post method:- " + e.toString());
//		}
//
//	}
//
//	@RequestMapping(method = { RequestMethod.GET }, value = { "productWiseOrder" })
//	public String getProductWiseOrder(ModelMap model, HttpSession session, HttpServletRequest request) {
//		if (request.getSession().getAttribute("rand") != null
//				& request.getSession().getAttribute("loginSession") != null)
//			if (request.getParameter("pkey") != null)
//				return "master/productWiseOrder";
//		return "/";
//	}
//
//	@RequestMapping(method = { RequestMethod.POST }, value = { "productWiseOrder" })
//	public void postProductWiseOrder(HttpServletRequest request, HttpServletResponse response, Model model)
//			throws IOException {
//		List<ProductInfoModel> list = null;
//		OrderLogic ol = new OrderLogic();
//		try {
//			JqueryDataTable jt = new JqueryDataTable();
//			/* request set */
//			jt.getRequestData(request);
//			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
//			String[] keyword = new String[4];
//			keyword[0] = String.valueOf(jt.getDraw());/*--indec column--*/
//			keyword[1] = jt.getOrder_column();/*--indec column--*/
//			keyword[2] = jt.getOrder_dir();/*--asc/desc--*/
//			keyword[3] = jt.getSearch_value();/*--keyword search--*/
//			if (ls.getAuthorize() == 1 | ls.getAuthorize() == 3) {
//				list = (List<ProductInfoModel>) ol.jDatatableWithPaymentDetailList(jt.getPkey(), 0, jt.getStart(),
//						jt.getLength(), keyword, request);
//			} else {
//				list = (List<ProductInfoModel>) ol.jDatatableWithPaymentDetailList(jt.getPkey(), ls.getId(),
//						jt.getStart(), jt.getLength(), keyword, request);
//			}
//			if (list == null)
//				list = Collections.EMPTY_LIST;
//			System.out.println("list size:-  " + list.size());
//
//			/* list convert to datatable json format */
//			jt.getResposeJson(response, ol.getCount(), new Gson().toJson(list).toString());
//
//		} catch (Exception e) {
//			logger.error("productWiseOrder_post " + e.toString());
//		}
//	}
//
//	@RequestMapping(value = "/merchantWiseRegistration", method = RequestMethod.GET)
//	public String getMerchantWiseRegistration(HttpServletRequest request, ModelMap model,
//			HttpServletResponse response) {
//		LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
//		System.out.println(" Get Post Paget  merchantWiseRegistration");
//
//		return "master/merchantWiseRegistration";
//	}
//
//	@RequestMapping(method = { RequestMethod.GET }, value = { "/merchantWiseRegistration123" })
//	public void postMerchantWiseRegistration(HttpServletResponse response, HttpServletRequest request)
//			throws Exception {
//		LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
//		List<ProductInfoModel> list = null;
//		MerchantLogic ml = new MerchantLogic();
//		try {
//			JqueryDataTable jt = new JqueryDataTable();
//			/* request set */
//			jt.getRequestData(request);
//			String[] keyword = new String[4];
//			keyword[0] = String.valueOf(jt.getDraw());/*--indec column--*/
//			keyword[1] = jt.getOrder_column();/*--indec column--*/
//			keyword[2] = jt.getOrder_dir();/*--asc/desc--*/
//			keyword[3] = jt.getSearch_value();/*--keyword search--*/
//			if (ls.getAuthorize() == 1 | ls.getAuthorize() == 3) {
//				list = (List<ProductInfoModel>) ml.getMerchantProRegistration(0, jt.getStart(), jt.getLength(), keyword,
//						request);
//			} else {
//				list = (List<ProductInfoModel>) ml.getMerchantProRegistration(ls.getId(), jt.getStart(), jt.getLength(),
//						keyword, request);
//			}
//			if (list == null)
//				list = Collections.EMPTY_LIST;
//			System.out.println("list size:-  " + list.size());
//
//			/* list convert to datatable json format */
//			jt.getResposeJson(response, ml.getCount(), new Gson().toJson(list).toString());
//
//		} catch (Exception e) {
//
//		}
//
//	}
//	@RequestMapping(value = "/productDescription", method = RequestMethod.GET)
//	public String getProductDescription(HttpServletRequest request, ModelMap model,
//			HttpServletResponse response) {
//		LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
//		System.out.println(" Get Post Paget  merchantWiseRegistration"+request.getParameter("pkey"));
//		
//		try {
//			logger.info("productsGet productPageGet start 122"+request.getParameter("pkey"));
//			if (!request.getParameter("pkey").isEmpty()) {
//				String pkey[] = request.getParameter("pkey").split("-");
//				String key = pkey[pkey.length - 1];
//				model.addAttribute("p_detail", CartSession.getProductDetail(key,request));
//			} else
//				return "index";
//
//			logger.info("productsGet productPageGet end 135");
//		} catch (Exception e) {
//			logger.error("HomeController error(131)productPageGet  "+e.toString());
//		}
//
//		return "master/productDescription";
//	}
//
//
//}
