package com.model;

import java.util.ArrayList;
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
import javax.persistence.UniqueConstraint;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = { "title" }))
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "pid")
	private int pid;

	@Column(name = "pkey", nullable = false)
	private String pkey;

	@Column(name = "m1Id", nullable = false)
	private int m1Id;

	@Transient
	private String tM1;
	@Transient
	private String tM2;
	@Transient
	private String tM3;

	@Column(name = "m2Id", nullable = false)
	private int m2Id;

	@Column(name = "m3Id", nullable = false)
	private int m3Id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "uid", nullable = false)
	private int uid;

	@Column(name = "length", nullable = false)
	private int length;

	@Column(name = "width", nullable = false)
	private int width;

	@Column(name = "height", nullable = false)
	private int height;

	@Column(name = "weight", nullable = false)
	private int weight;

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
		return "m1:- " + m1Id + " m2:- " + m2Id + "  m3:- " + m3Id + " title:- " + title + " length:-" + length
				+ " weight:-" + weight + "  width:-" + width;
		// return "pkey:- " + pkey;
	}

	public List<String> checkValid(HttpServletRequest request) {
		m1Id = Integer.parseInt(request.getParameter("categories"));
		m2Id = Integer.parseInt(request.getParameter("someselect"));
		m3Id = Integer.parseInt(request.getParameter("modelselect"));
		title = request.getParameter("title");
		length = Integer.parseInt(request.getParameter("length"));
		weight = Integer.parseInt(request.getParameter("weight"));
		height = Integer.parseInt(request.getParameter("height"));
		width = Integer.parseInt(request.getParameter("width"));

		List<String> lStr = new ArrayList<>();
		if (m3Id < -1)
			lStr.add("Select categories");
		if (title == null | title.length() < 3)
			lStr.add("Enter title");
		if (length < -1)
			lStr.add("Enter product length");
		if (weight < -1)
			lStr.add("Enter product weight");
		if (height < -1)
			lStr.add("Enter product height");
		if (width < -1)
			lStr.add("Enter product width");
		return lStr;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getM1Id() {
		return m1Id;
	}

	public void setM1Id(int m1Id) {
		this.m1Id = m1Id;
	}

	public int getM2Id() {
		return m2Id;
	}

	public void setM2Id(int m2Id) {
		this.m2Id = m2Id;
	}

	public int getM3Id() {
		return m3Id;
	}

	public void setM3Id(int m3Id) {
		this.m3Id = m3Id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String gettM1() {
		return tM1;
	}

	public void settM1(String tM1) {
		this.tM1 = tM1;
	}

	public String gettM2() {
		return tM2;
	}

	public void settM2(String tM2) {
		this.tM2 = tM2;
	}

	public String gettM3() {
		return tM3;
	}

	public void settM3(String tM3) {
		this.tM3 = tM3;
	}
}
