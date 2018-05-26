
package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.html.DescriptionHtml;
import com.logic.CartLogic;
import com.logic.MenuLogic;
import com.logic.PaymentLogic;
import com.logic.ProductLogic;
import com.logic.RegLogic;
import com.logic.SlideLogic;
import com.model.CartModel;
import com.model.LoginSession;
import com.model.Menu1;
import com.model.OrderDetails;
import com.model.Product;
import com.model.ProductDetail;
import com.model.ShippingModel;

@Controller
@RequestMapping(value = { "/admin" })
public class AdminController {
	final static Logger logger = Logger.getLogger(AdminController.class);
	@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
	public String indexGet(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:/";
			else
				map.addAttribute("featuredPro", " Get Controller");

		} catch (Exception e) {
			logger.error("AdminController error(42)indexGet  " + e.toString());
		}
		return "admin/index";
	}

	@RequestMapping(value = { "/menuEntry" }, method = { RequestMethod.GET })
	public String getForms(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:/";
			else
				map.addAttribute("menu", MenuLogic.getMenuObj((Object) new Menu1(), 0));

		} catch (Exception e) {
			logger.error("AdminController error(54)getForms  " + e.toString());
		}
		return "admin/menuEntry";
	}

	@RequestMapping(method = { RequestMethod.POST }, value = { "/addMenus" })
	public void postOrderDetail(@RequestParam("json") String json, HttpServletResponse response, HttpSession session,
			HttpServletRequest request) throws Exception {
		JSONObject obj = new JSONObject(json);
		boolean bol = false;
		try {
			String queryString = obj.getString("opt");
			if (queryString.equals("opt.retrive")) {
				obj.put("data", new Gson()
						.toJson((Object) MenuLogic.getMenu2Bym1id(obj.getInt("id"), obj.getString("select"))));
				bol = true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("AdminController error(74)postOrderDetail  " + e.toString());
		} finally {
			obj.remove("opt");
			obj.put("url", "no");
			obj.put("res", bol);
			obj.put("status", bol);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(obj.toString());
		}

	}

	@RequestMapping(method = { RequestMethod.POST }, value = { "/menusAdd" })
	public void submitMenus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			int m1id = Integer.valueOf(request.getParameter("m1id").trim());
			int m2id = Integer.valueOf(request.getParameter("m2id").trim());

			String title = request.getParameter("title").trim();
			String categories = request.getParameter("categories.add").trim();

			String json = MenuLogic.addMenus((int) m1id, (int) m2id, (String) title, (String) categories);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			logger.error("AdminController error(101)submitMenus  " + e.toString());
		}
	}

	@RequestMapping(value = { "/productRegistration" }, method = { RequestMethod.GET })
	public String getProductRegistration(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:/";
			else
				map.addAttribute("menu", MenuLogic.getMenuObj((Object) new Menu1(), 0));

		} catch (Exception e) {
			logger.error("AdminController error(114)getProductRegistration  " + e.toString());
		}
		return "admin/productRegistration";
	}

	@RequestMapping(value = { "/productRegistration" }, method = { RequestMethod.POST })
	public String postProductRegistration(HttpServletRequest request, ModelMap map) {
		try {
			Product product = new Product();
			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
			product.setUid(ls.getId());
			if (product.checkValid(request).size() > 0)
				map.addAttribute("error", product.checkValid(request));

			else {
				if (ProductLogic.product_registration(product))
					map.addAttribute("error", "Product register sucessfull.");
				else
					map.addAttribute("error", "try later.");
			}
			System.out.println(product);
		} catch (Exception e) {
			logger.error("AdminController error(119)postProductRegistration  " + e.toString());
		}
		return "admin/productRegistration";
	}

	@RequestMapping(value = "/productAdd", method = RequestMethod.GET)
	public void addProduct(@RequestParam("json") String json, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
		JSONObject obj = new JSONObject(json);
		try {
			if (ls != null) {
				Product pro = new Product();
				ProductDetail pd = new ProductDetail();
				pro.setTitle(obj.getString("title").trim());
				pro.setM1Id(obj.getInt("m1"));
				pro.setM2Id(obj.getInt("m2"));
				pro.setM3Id(obj.getInt("m3"));
				/* object remove */
				obj.remove("title");
				obj.remove("m1");
				obj.remove("m2");
				obj.remove("m3");
				int num = 1;
				if (num > 0) {
					obj.put("data", "sucess");
					obj.put("url", "no");
					obj.put("res", true);
				} else {
					obj.put("data", "not sucess");
					obj.put("url", "no");
					obj.put("res", true);
				}
			}
		} catch (Exception e) {
			logger.error("AdminController error(171)addProduct  " + e.toString());
			obj.put("data", "payment exception:- " + e.toString());
			obj.put("url", "no");
			obj.put("res", true);
		} finally {
			System.out.println("obj json " + obj.toString());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(obj.toString());
		}
	}

	@RequestMapping(value = { "/addProduct" }, method = { RequestMethod.GET })
	public String getAddProduct(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:/";
			else {
				Product pro = ProductLogic.getByPkey(request);
				if (pro != null)
					map.addAttribute("pro", pro);
				else
					map.addAttribute("pro", "no data found");
			}

			// map.addAttribute("menu", MenuLogic.getMenuObj((Object) new
			// Menu1(), 0));

		} catch (Exception e) {
			logger.error("AdminController error(200)getAddProduct  " + e.toString());
		}
		return "admin/addProduct";
	}

	@RequestMapping(value = { "/addProduct" }, method = { RequestMethod.POST })
	public String postAddProduct(HttpServletRequest request, ModelMap map) {
		int cas = 0;
		try {

			System.out.println("addProduct insert  " + ProductLogic.addProduct(request));
			cas = ProductLogic.addProduct(request) ? 1 : 0;

		} catch (ConstraintViolationException ex) {
			cas = 2;
		}

		catch (Exception e) {
			System.out.println(e.toString());
			cas = 3;
			logger.error("AdminController error(220) postAddProduct  " + e.toString());
		}

		map.addAttribute("error", cas);
		return "admin/addProduct";
	}

	@RequestMapping(value = { "/product_list" }, method = { RequestMethod.GET })
	public String getproduct_listt(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:/";

		} catch (Exception e) {
			logger.error("AdminController error(234) getproduct_listt  " + e.toString());
		}
		return "admin/product_list";
	}
	@RequestMapping(value = { "/description" }, method = { RequestMethod.GET })
	public String getDescription(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:/";
			else{
				String pkey = request.getParameter("pkey");
				System.out.println("pkey:-  " + pkey);
				if (pkey != null)
					map.addAttribute("product_desc", ProductLogic.getProductHome("single", pkey,0,0));
				else
					return "admin/description";
				
			}
				

		} catch (Exception e) {
			logger.error("AdminController error(244) getDescription  " + e.toString());
		}
		return "admin/description";
	}
	
//	@RequestMapping(value = { "/description" }, method = { RequestMethod.POST })
//	public String postDescription(HttpServletRequest request, ModelMap map) {
//		try {
//			if (RegLogic.checkLogin(request))
//				return "redirect:/";
//			else{
//				String pkey = request.getParameter("pkey");
//				System.out.println("pkey: post-  " + pkey);
//				if (pkey != null){
//					ProductLogic.product_Detail_permission(request,pkey);
//					map.addAttribute("product_desc", ProductLogic.getProductHome("single", pkey));
//					return "admin/description";
//				}
//			}
//		} catch (Exception e) {
//			logger.error("AdminController error(244) getDescription  " + e.toString());
//		}
//		return "redirect:/";
//	}
	
	@RequestMapping(method = { RequestMethod.POST }, value = { "/description" })
	public void postDescription(@RequestParam("json") String json, HttpServletResponse response, HttpSession session,
			HttpServletRequest request, ModelMap map) throws Exception {
		JSONObject obj = new JSONObject(json);
		boolean bol = false;
		try {
			String opt = obj.getString("opt");
			if (opt.equals("field_update")) {
				System.out.println("opt:--- " + opt);
				obj.put("data", DescriptionHtml.getProductDescription(obj.getString("pkey"),obj));
				bol = true;
			}
			if (opt.equals("enable") | opt.equals("disable")) {
				System.out.println("opt:--- " + opt);
				String pkey = obj.getString("pkey");
				if (pkey != null){
					obj.put("data","");
					bol=ProductLogic.product_Detail_permission(request,pkey,opt);
					//map.addAttribute("product_desc", ProductLogic.getProductHome("single", pkey,0,0));
				
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("AdminController error(74)postOrderDetail  " + e.toString());
		} finally {
			obj.remove("opt");
			obj.put("url", "no");
			obj.put("res", bol);
			obj.put("status", bol);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(obj.toString());
			System.out.println(obj.toString());
		}

	}

	
	
	
	@RequestMapping(method = { RequestMethod.POST }, value = { "paymentManage" })
	public void getPayRecord(@RequestParam("json") String json, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		String msg = "";
		JSONObject obj = new JSONObject(json);
		String cas = obj.getString("cas").trim();
		try {
			obj.remove(cas);
			switch (cas) {
			case "getRec":
				String meth = obj.getString("method").trim();
				//obj.put("data", new Gson().toJson(OrderLogic.getPayRec(meth)));
				obj.put("url", "no");
				obj.put("res", true);
				break;
			case "payApprove":
				//if (OrderLogic.approvePayment(obj))
				//	msg += "<div class=\"alert alert-success\">" + "<strong>Approved Successfully!' + '</div>'";
				//else
					msg += "<div class=\"alert alert-info\">" + "<strong>Please try later!' + '</div>'";
				obj.put("data", new Gson().toJson(msg));
				obj.put("url", "no");
				obj.put("res", true);
				break;
			}

		} catch (Exception e) {
			obj.put("data", "exception:- " + e.toString());
			obj.put("url", "no");
			obj.put("res", true);
			obj.put("valid", "default");
		} finally {
			System.out.println("obj json " + obj.toString());
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
				return  "admin/orderList";

		} catch (Exception e) {
			logger.error("AdminController error(54)getForms  " + e.toString());
		}
		return "redirect:/";
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
		return "admin/orderInfo";
	}
	
	
	@RequestMapping(value = "/orderInfo", method = RequestMethod.POST)
	public void postAccountUpdate(@RequestParam("json") String json, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		JSONObject obj = new JSONObject(json);
		try {
				LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
				String con = obj.getString("condition").trim();
				switch (con) {
				case "update_order":
							String orderId = obj.getString("orderId").trim();
							int value=Integer.parseInt(obj.getString("value").trim());
							boolean bol=PaymentLogic.updateOrderDispatch(orderId, value);
							if(bol){
								obj.put("data", "update successfully.");
								obj.put("status", bol);
								
							}
							else
								obj.put("data", "please try again ");
								obj.put("status", bol);
								break;
				}
				obj.remove("orderId");
				obj.remove("value");
				obj.remove("orderId");
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
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = { "/slide_control" }, method = { RequestMethod.GET })
	public String getproduct_list(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:/";
			else
				return  "admin/slide_control";

		} catch (Exception e) {
			logger.error("AdminController error(54)getForms  " + e.toString());
		}
		return "redirect:/";
	}
	
	@RequestMapping(method = { RequestMethod.POST }, value = { "/slide_control" })
	public void postSlide_control(@RequestParam("json") String json, HttpServletResponse response, HttpSession session,
			HttpServletRequest request) throws Exception {
		JSONObject obj = new JSONObject(json);
		boolean bol = false;
		try {
			String queryString = obj.getString("opt");
			System.out.println("opt:--- "+queryString);
			if (queryString.equals("update_slide")) {
				obj.put("data", new Gson()
						.toJson((Object) SlideLogic.trasactionUpdate(json)));
				bol = true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("AdminController error(74)postOrderDetail  " + e.toString());
		} finally {
			obj.remove("opt");
			obj.put("url", "no");
			obj.put("res", bol);
			obj.put("status", bol);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(obj.toString());
			System.out.println(obj.toString());
		}

	}
	
	
	
	
	
	
}