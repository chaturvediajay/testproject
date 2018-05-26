package com.model;

public class LoginSession {
	private int id;
	private String email;
	private String name;
	private String username;
	private String url;
	private int authorize;
	private int optUser;
	private int merchentAuth;
	private int company_id;
	private String secret;

	public int getMerchentAuth() {
		return merchentAuth;
	}

	public void setMerchentAuth(int merchentAuth) {
		this.merchentAuth = merchentAuth;
	}

	public int getOptUser() {
		return optUser;
	}

	public void setOptUser(int optUser) {
		this.optUser = optUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAuthorize() {
		return authorize;
	}

	public void setAuthorize(int authorize) {
		this.authorize = authorize;
	}

	@Override
	public String toString() {
		return id + "," + name+ "," + email + "," + authorize+", "+
				username+", "+company_id;

	}

	public String[] stringArray() {
		return new String[] { Integer.toString(getId()), getName(), getEmail(), Integer.toString(getAuthorize()) };

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public LoginSession() {

	}

	public LoginSession(int id, String email, String name, int authorize) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.authorize = authorize;
	}

}
