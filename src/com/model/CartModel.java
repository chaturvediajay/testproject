package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item_details")
public class CartModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "orderId", nullable = false)
	private String orderId;

	@Column(name = "uid", nullable = false)
	private int uid;

	@Transient
	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "pkey", nullable = false)
	private String pkey;

	@Transient
	@Column(name = "name", nullable = true)
	private String name;

	@Column(name = "mrp", nullable = false)
	private String mrp;

	@Column(name = "smrp", nullable = false)
	private String smrp;

	@Transient
	@Column(name = "size", nullable = true)
	private String size;

	@Transient
	@Column(name = "color", nullable = true)
	private String color;

	@Transient
	@Column(name = "count", nullable = true)
	private String count;

	@Transient
	@Column(name = "url", nullable = true)
	private String url;

	@Column(name = "pid", nullable = true)
	private int pid;

	@Column(name = "pmid", nullable = true)
	private int pmid; // ppid

	@Column(name = "gid", nullable = true)
	private String gid;

	@Column(name = "qty", nullable = true)
	private int qty = 1;

	@Column(name = "ppid", nullable = true)
	private String ppid;

	@Column(name = "visible", columnDefinition = "boolean default false", nullable = false)
	private int visible;

	@Column(name = "status", nullable = false)
	private int status;

	@Column(name = "confirm", nullable = false)
	private int confirm;

	@Column(name = "company_id", nullable = false)
	private int company_id;

	@Transient
	private String cDate;

	@Transient
	private String description;

	@Column(name = "location", nullable = true)
	private int location;

	@Transient
	private String mi_state;

	@Transient
	private String mi_city;

	@Transient
	private String mi_pincode;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = uid + " orderId:- " + orderId + " getTitle:-" + getTitle() + " pkey:-" + pkey + " name:-" + name
				+ " mrp:-" + mrp + " smrp:-" + smrp + " size:-" + size + " color:-" + color + " count:-" + count
				+ " url:-" + url + " pid:-" + pid + " pmid:-" + pmid + " ppid:-" + ppid + " gid:-" + gid + " qty: "
				+ qty;
		return str;
	}

	@Transient
	private String companyName;

	public CartModel() {

	}

	public CartModel(int uid, String orderId, CartModel cartModel) {
		this.uid = uid;
		this.orderId = orderId;
		this.title = cartModel.getTitle();
		this.pkey = cartModel.getPkey();
		this.name = cartModel.getName();
		this.mrp = cartModel.getMrp();
		this.smrp = cartModel.getSmrp();
		this.size = cartModel.getSize();
		this.color = cartModel.getColor();
		this.count = cartModel.getCount();
		this.url = cartModel.getUrl();
		this.pid = cartModel.getPid();
		this.pmid = cartModel.getPmid();
		this.gid = cartModel.getGid();
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getSmrp() {
		return smrp;
	}

	public void setSmrp(String smrp) {
		this.smrp = smrp;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPmid() {
		return pmid;
	}

	public void setPmid(int pmid) {
		this.pmid = pmid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getPpid() {
		return ppid;
	}

	public void setPpid(String ppid) {
		this.ppid = ppid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public static void main(String args[]) {
		// create an empty list
		List emptylst = Collections.emptyList();

		System.out.println("Created empty immutable list: " + emptylst);
		emptylst = new ArrayList<>();
		// try to add elements
		emptylst.add("A");
		emptylst.add("B");
		System.out.println("Created empty immutable list: " + emptylst);
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public String getMi_state() {
		return mi_state;
	}

	public void setMi_state(String mi_state) {
		this.mi_state = mi_state;
	}

	public String getMi_city() {
		return mi_city;
	}

	public void setMi_city(String mi_city) {
		this.mi_city = mi_city;
	}

	public String getMi_pincode() {
		return mi_pincode;
	}

	public void setMi_pincode(String mi_pincode) {
		this.mi_pincode = mi_pincode;
	}

}
