package com.servlet;

import java.io.IOException;
import java.net.URI;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.OrderInit;
import com.cart.OrderTrasaction;
import com.cart.PaymentDetails;
import com.logic.CartLogic;
import com.model.LoginSession;
import com.model.ShippingModel;
import com.scope.DateConvert;

import paypal.IntregationPayPal;
import paypal.PaypalAction;

/**
 * Servlet implementation class PayPalPayment
 */
@WebServlet("/PayPalPayment")
public class PayPalPayment extends HttpServlet {
	private double totalAmt = 0;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayPalPayment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub
	 * 
	 * 
	 * PaypalAction pl=new PaypalAction(); pl.doPaypalPaymentWithCreditCard();
	 * //pl.paypalPay(request, response);
	 * 
	 * 
	 * 
	 * // response.getWriter().append("Served at: "
	 * ).append(request.getContextPath()); }
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String orderId = OrderInit.getOrderId();
		Random rand = new Random();
		Long trasactionId = (long) (rand.nextDouble() * 10000000000L);

		IntregationPayPal ip = new IntregationPayPal();
		String tracation_code=UUID.randomUUID().toString().replaceAll("-", "");
		String str = ip.paypalPay(request, response, OrderInit.totalOrderAmt(request),tracation_code);

		System.out.println("rediect url: " + str);
		if (ip.getToken() != null) {


			if (getRequestSaveData(request, orderId, ip.getToken(),tracation_code)) {
				response.sendRedirect(str);
			}
		}

		// doGet(request, response);
	}

	private boolean getRequestSaveData(HttpServletRequest request, String orderId, String trsactionId,String tracation_code) {
		boolean bol = false;
		if (orderId != null & trsactionId != null) {
			setTotalAmt(OrderInit.totalOrderAmt(request));
			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
			ShippingModel sm = (ShippingModel) request.getSession().getAttribute("ship");
			sm.setOrderId(orderId);
			sm.setUid(ls.getId());
			bol = CartLogic.orderDetailSave(sm, orderId, request);
			PaymentDetails pd = new PaymentDetails();
			pd.setTxid(trsactionId);
			pd.setOrderId(orderId);
			pd.setTrasaction_code(tracation_code);
			pd.setDatetime(DateConvert.nowTimeGMT());
			bol = OrderTrasaction.saveOrder(pd);
		}
		return bol;

	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}


}
