package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

@Entity
@Table(name = "shipping")
public class ShippingModel {
	public ShippingModel() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "uid", nullable = false)
	private int uid;

	@Column(name = "orderId", nullable = false)
	private String orderId;

	@Column(name = "sName", length = 50)
	private String sName;

	@Column(name = "sAddress1", length = 150)
	private String sAddress1;

	@Column(name = "sAddress2", length = 200)
	private String sAddress2;

	@Column(name = "sMobile", length = 15)
	private String sMobile;

	@Column(name = "sState", length = 50)
	private String sState;

	@Column(name = "sCity", length = 50)
	private String sCity;

	@Column(name = "sPincode", length = 50)
	private String sPincode;

	@Transient
	private ShippingModel sm;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "sName:-" + sName + " sAddress1:-" + sAddress1 + " sAddress2:-" + sAddress2 + " sModile:- " + sMobile
				+ " sState:-" + sState + "  sCity:-" + sCity + " sPincode:-" + sPincode;
	}

	public ShippingModel(HttpServletRequest request) {
		this.sName = request.getParameter("sName");
		this.sAddress1 = request.getParameter("sStreet");
		this.sAddress2 = request.getParameter("sAddress");
		this.sMobile = request.getParameter("sMobile");
		this.sState = request.getParameter("sState");
		this.sCity = request.getParameter("sCity");
		this.sPincode = request.getParameter("sPincode");
	}

	public List<String> checkValid() {

		List<String> lStr = new ArrayList<>();
		if (sName == null | sName.length() < 3)
			lStr.add("Enter Shipping name");
		if (sAddress1 == null | sAddress1.length() < 3)
			lStr.add("Enter Shipping plot number");
		if (sAddress2 == null | sAddress2.length() < 3)
			lStr.add("Enter Shipping address");
		if (sMobile == null | sMobile.length() < 3)
			lStr.add("Enter password");
		if (sState == null | sState.length() < 3)
			lStr.add("Choose shipping state");
		if (sCity == null | sCity.length() < 3)
			lStr.add("choose shipping city");
		if (sPincode == null | sPincode.length() != 6)
			lStr.add("choose pincode");
		return lStr;
	}

	public void sessionShipSave(HttpServletRequest request, boolean replace) {
		if (replace)
			request.getSession().setAttribute("ship", sm);
		else
			request.getSession().removeAttribute("ship");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsAddress1() {
		return sAddress1;
	}

	public void setsAddress1(String sAddress1) {
		this.sAddress1 = sAddress1;
	}

	public String getsAddress2() {
		return sAddress2;
	}

	public void setsAddress2(String sAddress2) {
		this.sAddress2 = sAddress2;
	}

	public String getsMobile() {
		return sMobile;
	}

	public void setsMobile(String sMobile) {
		this.sMobile = sMobile;
	}

	public String getsState() {
		return sState;
	}

	public void setsState(String sState) {
		this.sState = sState;
	}

	public String getsCity() {
		return sCity;
	}

	public void setsCity(String sCity) {
		this.sCity = sCity;
	}

	public String getsPincode() {
		return sPincode;
	}

	public void setsPincode(String sPincode) {
		this.sPincode = sPincode;
	}

	public ShippingModel getSm() {
		return sm;
	}

	public void setSm(ShippingModel sm) {
		this.sm = sm;
	}

	

}
