package com.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.annotations.GenerationTime;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "product_detail", uniqueConstraints = @UniqueConstraint(columnNames = { "size", "color","pdid" }))
public class ProductDetail {
	final static Logger logger = Logger.getLogger(ProductDetail.class);

	@Id
	@GeneratedValue
	@Column(name = "pdid")
	private int pdid;

	@Column(name = "pkey", nullable = false)
	private String pkey;

	@Column(name = "size", length = 50, nullable = false)
	private String size;

	@Column(name = "color", length = 50, nullable = false)
	private String color;

	@Column(name = "mrp", nullable = false)
	private String mrp;

	@Column(name = "smrp", nullable = false)
	private String smrp;

	@Column(name = "count", nullable = false)
	private int count;

	@Column(name = "description", length = 255, nullable = false)
	private String description;

	@Column(name = "url", length = 255, nullable = false)
	private String url;

	@Column(name = "uid", nullable = false)
	private int uid;

	@Column(name = "visible", length = 1, columnDefinition = "int default 0")
	private int visible;

	@Column(name = "status", length = 1, columnDefinition = "int default 0")
	private int status;

	@Column(name = "cdate", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@Temporal(value = TemporalType.TIMESTAMP)
	@org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
	private Date cdate;

	@Column(name = "udate", nullable = true)
	private Date udate;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		logger.info("logger details**********    mrp:- " + mrp + " smrp:-" + smrp + " count:- " + count + " pkey:-"
				+ pkey + " size:-" + size + "  color:-" + color);
		return "mrp:- " + mrp + " smrp:-" + smrp + " count:- " + count + " pkey:-" + pkey + " size:-" + size
				+ "  color:-" + color + "  url:-" + url + " uid:-" + uid;
	}

	public boolean validation() {
		boolean bol = true;
		if (mrp.equals("") | mrp == null)
			bol = false;
		if (smrp.equals("") | smrp == null)
			bol = false;
		if (count == 0 | count < 1)
			bol = false;
		return bol;
	}

	public ProductDetail(JSONObject obj, LoginSession ls) {
		try {
			size = obj.getString("size");
			color = obj.getString("color");
			mrp = obj.getString("mrp");
			smrp = obj.getString("smrp");

			count = obj.getInt("count");
			description = obj.getString("description");
			url = obj.getString("url");
			uid = obj.getInt("uid");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ProductDetail() {
		// TODO Auto-generated constructor stub
	}

	public int getPdid() {
		return pdid;
	}

	public void setPid(int pdid) {
		this.pdid = pdid;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int isVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getUdate() {
		return udate;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getVisible() {
		return visible;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean setImgUrl(HttpServletRequest request, String session_name) {
		ArrayList<String> arrlist = (ArrayList<String>) (request.getSession().getAttribute(session_name));
		if (arrlist != null) {
			for(String arr:arrlist){
				url+=arr+",";
				url=url.replace("null", "");
			}
			return true;
		}

		return false;
	}

}
