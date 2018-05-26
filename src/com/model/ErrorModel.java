package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "error_model")
public class ErrorModel {

	@Id
	@GeneratedValue
	private int eid;

	@Column(name = "class_name", nullable = false)
	private String class_name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "status", columnDefinition = "boolean default false", nullable = false)
	private int status;
	
	@Column(name = "createDate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createDate;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name: "+class_name+"  "+"descrption:- "+description+" status:- "+status+"  eid:-  "+eid
			+" create date:- "+createDate;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
