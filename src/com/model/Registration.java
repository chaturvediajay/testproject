package com.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "registration")
public class Registration {

	@Id
	@GeneratedValue
	@Column(name = "uid")
	private int uid;

	@NotNull
	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "pswd")
	private String pswd;

	@Column(name = "name")
	private String name;
	
	@Column(name = "url")
	private String url;
	
	
	@Column(name = "street")
	private String street;
	@Column(name = "address")
	private String address;
	@Column(name = "city")
	private String city;
	@Column(name = "pincode")
	private String pincode;
	@Column(name = "state")
	private String state;
	

	@Transient
	private String cpswd;

	@Column(name = "access", nullable = false)
	private int access = 0;

	@Column(name = "authorize")
	private int authorize = 0;

	@Column(name = "mobile", length = 15, nullable = false)
	private String mobile;

	@Transient
	@Column(name = "createDate", columnDefinition = "TIMESTAMP default Current_TIMESTAMP", nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	@org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
	private Date createDate;

	@Transient
	private int optUser;

	public Registration(String username, String email, String pswd, String name) {
		this.username = username;
		this.email = email;
		this.pswd = pswd;
		this.name = name;
	}

	public boolean checkUnique( Session session, String email, String username, String mobile) {
		System.out.println("email="+email+" username = "+username+" mobile="+mobile);
		String str = "SELECT COUNT(*) FROM  Registration where email='" + email + "' or username='"
				+ username + "' or mobile='" + mobile + "'";
		System.out.println("unique "+str);
		boolean bool = (long) session.createQuery(str).uniqueResult() > 0 ? false : true;
		// boolean bool = false;
		// switch (num) {
		// case 1:
		// bool = (long) session.createQuery(str + "email='" + email + "' or "+"
		// username='" + username + "' or mobile="+mobile+"'" ).uniqueResult() >
		// 0 ? false : true;
		// break;
		// case 2:
		// bool = (long) session.createQuery(str + "username='" + value +
		// "'").uniqueResult() > 0 ? false : true;
		// break;
		// case 3:
		// bool = (long) session.createQuery(str + "mobile='" + value +
		// "'").uniqueResult() > 0 ? false : true;
		// break;
		// }

		return bool;

	}

	public List<String> checkValid(HttpServletRequest request){
		
		name = request.getParameter("name");
		username= request.getParameter("username");
		email= request.getParameter("email");
		mobile= request.getParameter("mobile");
		pswd= request.getParameter("pswd");
		cpswd=request.getParameter("cpswd");
		
		List<String> lStr = new ArrayList<>();
		if (name == null | name.length() < 3)
			lStr.add("Enter name");
		if (username == null | username.length() < 3)
			lStr.add("Enter username");
		if (email == null | email.length() < 3)
			lStr.add("Enter email");
		if (pswd == null | pswd.length() < 3)
			lStr.add("Enter password");
		if (cpswd == null | cpswd.length() < 3)
			lStr.add("Enter confirm password");
		if (!cpswd.equals(pswd))
			lStr.add("password not be same");
		if (mobile == null | mobile.length()!=10)
			lStr.add("Enter mobile ");
		return lStr;
	}

	public Registration() {
	}

	@Override
	public String toString() {
		return "sttaus: " + username + " " + email + " " + pswd + " " + cpswd + " " + name + " optUser:-" + optUser;
	}

	public int getId() {
		return this.uid;
	}

	public void setId(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPswd() {

		return this.pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	public int getAuthorize() {
		return this.authorize;

	}

	public void setAuthorize(int authorize) {
		this.authorize = authorize;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCpswd() {

		return this.cpswd;
	}

	public void setCpswd(String cpswd) {
		this.cpswd = cpswd;

	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public boolean getRegValue(HttpServletRequest request){
		name = request.getParameter("name");
		username= request.getParameter("username");
		email= request.getParameter("email");
		mobile= request.getParameter("mobile");
		pswd= request.getParameter("pswd");
		cpswd=request.getParameter("cpswd");
		return false;
	}


	public static void main(String args[]) {

		Session session = com.model.HibernateUtil.getSessionFactory().openSession();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		System.out.println("connect:- "+session.isConnected());
		session.clear();
		session.close();
		
		System.out.println("connect:- "+session.isConnected());

	}

	public int getOptUser() {
		return optUser;
	}

	public void setOptUser(int optUser) {
		this.optUser = optUser;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}